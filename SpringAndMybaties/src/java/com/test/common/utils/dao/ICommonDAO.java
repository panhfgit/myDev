package com.test.common.utils.dao;

import com.test.common.persistence.annotation.Dao;
import com.test.common.persistence.dao.CrudDao;

/**
 * Created by haomeng on 2015/11/16.
 */
@Dao(value = ICommonDAO.class)
public interface ICommonDAO extends CrudDao<Object,String> {
}
