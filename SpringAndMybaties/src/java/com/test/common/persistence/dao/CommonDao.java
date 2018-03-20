package com.test.common.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoKe on 2016/1/19.
 */
public interface CommonDao extends BaseDao{
    public Date getSysDate(@Param("name") String name) throws Exception;
    public List<Map> findBySql(@Param("sql") String sql, @Param("param") Map params) throws Exception;
}
