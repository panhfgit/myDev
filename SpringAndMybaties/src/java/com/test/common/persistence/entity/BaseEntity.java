package com.test.common.persistence.entity;

import com.test.common.persistence.annotation.Column;
import com.test.common.utils.DateUtils;
import com.test.common.utils.SQLHelper;
import com.test.common.utils.StringUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dizl on 2015/5/11.
 */
public abstract class BaseEntity implements Serializable {
    @Column(isColumn = false)
    @JsonIgnore
    protected static final Long serialVersionUID = -1941046831377985500L;
    @Column(isColumn = false)
    @JsonIgnore
    private Page page;
    @JsonIgnore
    @Column(isColumn = false)
    private Rank[] ranks;
    /**
     * 变更的字段
     */
    @Column(isColumn = false)
    @JsonIgnore
    private transient Set updateFields = new HashSet();

    /**
     * 获取主键
     */
    public Long newId() throws Exception {
        String tableName = SQLHelper.getSimpleTableName(this.getClass());
        return 0L;//IdGeneratorFactory.newId(tableName);
    }

    /**
     * 获取string类型的主键
     */
    public String newStringId() throws Exception {
        String tableName = SQLHelper.getTableName(this.getClass());
        return "";//IdGeneratorFactory.newStringId(tableName);
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Rank[] getRanks() {
        return ranks;
    }

    public void setRanks(Rank[] ranks) {
        this.ranks = ranks;
    }

    public void setRank(Rank rank) {
        Rank[] ranks = new Rank[1];
        ranks[0] = rank;
        this.setRanks(ranks);
    }

    public Boolean set(String key, Object value) throws Exception {
        boolean b=false;
        Method[] methods = this.getClass().getMethods();
        if(value==null||"".equals(value.toString())){
            value=null;
        }
        for (int i = 0; i < methods.length; i++) {
            if (("set" + StringUtils.toCapitalizeCamelCase(key)).toLowerCase().equals(methods[i].getName().toLowerCase())) {
                switch (methods[i].getParameterTypes()[0].getName()) {
                    case "java.lang.String": {
                        methods[i].invoke(this, StringUtils.toString(value));
                        b=true;
                        break;
                    }
                    case "java.lang.Long": {
                        methods[i].invoke(this, value==null?null:StringUtils.toLong(value));
                        b=true;
                        break;
                    }
                    case "java.lang.Integer": {
                        methods[i].invoke(this, value==null?null:StringUtils.toInteger(value));
                        b=true;
                        break;
                    }
                    case "java.util.Date": {
                        methods[i].invoke(this, DateUtils.parseDate(value));
                        b=true;
                        break;
                    }
                }
            }
        }
        return b;
    }

    public Object get(String key) throws Exception {
        if (key.indexOf("_") > 0) {
            key = StringUtils.toCapitalizeCamelCase(key);
        } else {
            key = key.substring(0, 1).toUpperCase() + key.substring(1);
        }

        Method[] methods = this.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (("get" + key).equals(methods[i].getName())) {
                Object result = methods[i].invoke(this);
                return result;
            }
        }
        return null;
    }

    private static class EntityInterceptor implements MethodInterceptor {
        private static final String METHOD_PREFIX="set";

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            String methodName=method.getName().toLowerCase();
            if(method!=null && methodName.startsWith(METHOD_PREFIX)){
                BaseEntity baseEntity = (BaseEntity)obj;
                baseEntity.updateFields.add(methodName.substring(METHOD_PREFIX.length()));
            }
            Object result = methodProxy.invokeSuper(obj, args); //调用业务类（父类中）的方法
            return result;
        }
    }

    public static Object getProxyInstance(Class<? extends BaseEntity> targetClazz){
        Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
        enhancer.setSuperclass(targetClazz);  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(new EntityInterceptor());
        // 创建动态代理类对象并返回
        return enhancer.create();
    }
}