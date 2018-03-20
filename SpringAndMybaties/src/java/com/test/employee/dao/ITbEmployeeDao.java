/**
 * $Id: ITbEmployeeDao.java,v 1.0 2018/1/7 15:41 RaoXb Exp $
 * <p>
 * Copyright 2017 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.test.employee.dao;

import com.test.common.persistence.annotation.Dao;
import com.test.common.persistence.dao.CrudDao;
import com.test.employee.entity.TbEmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author RaoXb
 * @version $Id: ITbEmployeeDao.java,v 1.1 2018/1/7 15:41 RaoXb Exp $
 * Created on 2018/1/7 15:41
 */
@Repository
@Dao(TbEmployeeEntity.class)
public interface ITbEmployeeDao extends CrudDao<TbEmployeeEntity,Long> {

    List<TbEmployeeEntity> queryAllData() throws Exception;
}