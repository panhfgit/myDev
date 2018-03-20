package com.test.common.config;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 全局配置类
 * @author
 */
public class Global {
    /**
     * 当前对象实例
     */
    private static Global global = new Global();

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 属性文件加载对象
     */
    private static Map<String, com.test.common.utils.PropertiesLoader> propertiesLoaderMap = Maps.newHashMap();

    static{
        com.test.common.utils.PropertiesLoader loader = new com.test.common.utils.PropertiesLoader("application.properties");
        if(loader==null || loader.getProperties().size()==0){
            com.test.common.utils.PropertiesLoader tempLoader = new com.test.common.utils.PropertiesLoader("sysconfig.properties");
            String configPath = tempLoader.getProperty("config.path");
            if(com.test.common.utils.StringUtils.isNotEmpty(configPath)){
                loader =  new com.test.common.utils.PropertiesLoader(1,configPath+"/application.properties");
            }
        }
        String value = loader.getProperty("rel-properties");
        if(com.test.common.utils.StringUtils.isNotEmpty(value)){
            String[] values = com.test.common.utils.StringUtils.split(",");
            if(values!=null && values.length>0){
                for(String str : values){
                    if(com.test.common.utils.StringUtils.isNotEmpty(value)){
                        String[] properties = com.test.common.utils.StringUtils.split(":");
                        if(properties!=null && properties.length==2){
                            com.test.common.utils.PropertiesLoader relLoader = new com.test.common.utils.PropertiesLoader(properties[1]);
                            propertiesLoaderMap.put(properties[0],relLoader);
                        }
                    }
                }
            }
        }
        propertiesLoaderMap.put("application",loader);
    }

    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

    /**
     * 从默认application.properties文件中获取配置信息
     * @param key
     */
    public static String getConfig(String key){
        return getConfig("application",key);
    }

    /**
     * 获取配置信息
     * @param propertiesKey  properties文件key
     * @param paramKey 参数key
     * */
    public static String getConfig(String propertiesKey,String paramKey){
        String value = map.get(propertiesKey+"-"+paramKey);
        if (value == null){
            com.test.common.utils.PropertiesLoader loader = propertiesLoaderMap.get(propertiesKey);
            if(loader!=null){
                value = loader.getProperty(paramKey);
                map.put(propertiesKey+"-"+paramKey, value != null ? value : com.test.common.utils.StringUtils.EMPTY);
            }
        }
        return value;
    }
}
