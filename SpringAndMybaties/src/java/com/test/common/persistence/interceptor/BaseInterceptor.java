/**
 * Copyright &copy; 2012-2014 <a href="https://github.com//jeesite">JeeSite</a> All rights reserved.
 */
package com.test.common.persistence.interceptor;

import com.test.common.config.Global;
import com.test.common.persistence.dialect.Dialect;
import com.test.common.persistence.dialect.db.MySQLDialect;
import com.test.common.persistence.dialect.db.OracleDialect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import java.io.Serializable;
import java.util.Properties;

/**
 * Mybatis拦截器基类
 * @author poplar.yfyang /
 * @version 2013-8-28
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {
    protected Log log = LogFactory.getLog(this.getClass());
	private static final long serialVersionUID = 1L;
    protected Dialect DIALECT;

    /**
     * 设置属性，支持自定义方言类和制定数据库的方式
     * <code>dialectClass</code>,自定义方言类。可以不配置这项
     * <ode>dbms</ode> 数据库类型，插件支持的数据库
     * <code>sqlPattern</code> 需要拦截的SQL ID
     * @param p 属性
     */
    protected void initProperties(Properties p) {
    	Dialect dialect = null;
        String dbType = Global.getConfig("jdbc.type");
        if ("mysql".equals(dbType)){
        	dialect = new MySQLDialect();
        }else if("oracle".equals(dbType)){
        	dialect = new OracleDialect();
        }
        if (dialect == null) {
            throw new RuntimeException("mybatis dialect error.");
        }
        DIALECT = dialect;
    }
}
