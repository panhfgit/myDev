package com.test.common.utils;

import com.test.common.persistence.annotation.Dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoKe on 2016/1/19.
 */
@Dao(QueryDataUtil.class)
public interface QueryDataUtil {
    public Date getSysDate() throws Exception;
    public List<Map> findBySqlToMap(Map<String,Object> map) throws Exception;
}
