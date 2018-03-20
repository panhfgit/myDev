package com.test.common.utils;

import com.test.common.persistence.dao.CommonDao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by XiaoKe on 2016/1/21.
 */
public class DataQueryUtil {

    public static List<Map> getDataBySql(String sql,Map params) throws Exception{

        CommonDao commonDao = SpringContextHolder.getBean(CommonDao.class);
        return commonDao.findBySql(sql,params);
    }
    public static Date getSysDate() throws Exception{

        CommonDao commonDao = SpringContextHolder.getBean(CommonDao.class);
        return commonDao.getSysDate("a" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 25));
    }
}
