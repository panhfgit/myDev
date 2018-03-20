package com.test.common.persistence.interceptor;


import com.test.common.persistence.entity.BaseEntity;
import com.test.common.persistence.entity.Page;
//import com.test.common.split.SplitTableFactory;
import com.test.common.utils.Constants;
import com.test.common.utils.ReflectionsUtils;
import com.test.common.utils.SQLHelper;
import com.test.common.utils.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by dizl on 2015/4/13.
 * 利用mybatis的拦截器进行分表
 */
@Intercepts( { @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class}) })
@Component
public class PrepareInterceptor extends BaseInterceptor {
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("SplitTableInterceptor intercept... ...");

        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();
        Object params = statementHandler.getBoundSql().getParameterObject();

        boolean hasPage = false;
        Page page = null;
        {
            String resultSql = sql;
            if (resultSql.trim().toUpperCase().startsWith("SELECT")) {
                String str = StringUtils.replace(resultSql, " ", "");
                if (!StringUtils.startsWithIgnoreCase(str, "SELECTCOUNT")) {
                    BaseEntity entity = (BaseEntity) SQLHelper.getBaseEntity(params);
                    page = (Page) SQLHelper.getPage(params);
                    if (entity != null || page != null) {
                        if (entity != null) {
                            if (entity.getPage() != null && entity.getPage().getPageSize() > 0) {
                                page = entity.getPage();
                                hasPage = true;
                            }
                        } else if (page != null && page.getPageSize() > 0) {
                            hasPage = true;
                        }
                    }
                }
            }
        }
        sql = convert(sql, params);

        if (hasPage && page != null) {
            String tempSql = statementHandler.getBoundSql().getSql();
            tempSql = splitTableConvert(tempSql,params);

            MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
            // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
            // 可以分离出最原始的的目标类)
            while (metaStatementHandler.hasGetter("h")) {
                Object object = metaStatementHandler.getValue("h");
                metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
            }
            // 分离最后一个代理对象的目标类
            while (metaStatementHandler.hasGetter("target")) {
                Object object = metaStatementHandler.getValue("target");
                metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
            }
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            Connection connection = (Connection) invocation.getArgs()[0];
            setPageParameter(tempSql, connection, mappedStatement, statementHandler.getBoundSql(), page);
        }

        ReflectionsUtils.setFieldValue(statementHandler.getBoundSql(), "sql", sql);
        log.info("splitTable转化后的SQL语句："+sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        super.initProperties(properties);
    }

    private String convert(String sql,Object params) throws Exception {

        String resultSql = sql;
        if (resultSql.trim().toUpperCase().startsWith("SELECT")) {
            String str = StringUtils.replace(resultSql, " ", "");
            if(!StringUtils.startsWithIgnoreCase(str,"SELECTCOUNT")){
                BaseEntity entity = (BaseEntity) SQLHelper.getBaseEntity(params);
                Page page = (Page) SQLHelper.getPage(params);
                if (entity != null || page != null) {
                    if (entity != null) {
                        resultSql = pageConvert(orderConvert(resultSql, entity), entity, null);
                    } else if (page != null) {
                        resultSql = pageConvert(resultSql, entity, page);
                    }
                }
            }
        }
        return splitTableConvert(resultSql,params);
    }

    private String orderConvert(String sql,BaseEntity entity){
        if(entity.getRanks()!=null && entity.getRanks().length>0){//进行排序
            StringBuffer sb = new StringBuffer();
            String simpleTableName = SQLHelper.getSimpleTableName(entity.getClass());
            for(int i=0;i<entity.getRanks().length;i++){
                String filedColumn = SQLHelper.getFieldColumn(entity.getClass(), entity.getRanks()[i].getAttrName());
                if (StringUtils.isNotEmpty(filedColumn)) {
                    if(sb.length()<=0){
                        sb.append(" ORDER BY ");
                    }else{
                        sb.append(",");
                    }
                    if(sql.toUpperCase().contains(" "+simpleTableName+"_"+filedColumn + " ")){
                        sb.append(simpleTableName + "_" + filedColumn + " " + entity.getRanks()[i].getRankType());
                    }else {
                        sb.append(filedColumn + " " + entity.getRanks()[i].getRankType());
                    }
                }
            }
            return sql + sb.toString();
        }
        return sql;
    }

    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, Page page) {
        // 记录总记录数
        String countSql = "select count(1) from (" + sql + ") total";

        log.info("splitTable转化后的COUNT-SQL语句：" + countSql);
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(),
                    boundSql.getParameterObject());
            // 从原有BoundSql中获取参数映射，设置到count的BoundSql中，这样就可以在配置文件中使用bind标签
            for (ParameterMapping pm : boundSql.getParameterMappings()) {
                String property = pm.getProperty();
                if (null != property && "" != property) {
                    Object value = boundSql.getAdditionalParameter(property);
                    if (value != null) {
                        countBS.setAdditionalParameter(property, value);
                    }
                }
            }
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            page.setCount(totalCount);
        } catch (SQLException e) {
            log.error("Ignore this exception", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Ignore this exception", e);
            }
            try {
                countStmt.close();
            } catch (SQLException e) {
                log.error("Ignore this exception", e);
            }
        }
    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject)
            throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    private String pageConvert(String sql,BaseEntity entity,Page page) throws Exception{
        if (entity != null) {
            if (entity.getPage() != null && entity.getPage().getPageSize() > 0) {//分页查询
                return SQLHelper.generatePageSql(sql, entity.getPage(), DIALECT);
            }
        } else if (page != null && page.getPageSize() > 0) {
            return SQLHelper.generatePageSql(sql, page, DIALECT);
        }
        return sql;
    }

    private String splitTableConvert(String sql,Object params) throws Exception{
        String resultSql = sql;
        for(;resultSql.contains(Constants.SplitTable.splitTableStartFlag)&&resultSql.contains(Constants.SplitTable.splitTableEndFlag);){
            int startIdx = resultSql.indexOf(Constants.SplitTable.splitTableStartFlag,0)+Constants.SplitTable.splitTableStartFlag.length();
            int endIdx = resultSql.indexOf(Constants.SplitTable.splitTableEndFlag,startIdx);
            String tableName = resultSql.substring(startIdx, endIdx);
            String splitTableName = "";//SplitTableFactory.getSplitTable(tableName, params);
            resultSql = StringUtils.replace(resultSql,Constants.SplitTable.splitTableStartFlag+tableName+Constants.SplitTable.splitTableEndFlag,splitTableName);
        }
        return resultSql;
    }
}