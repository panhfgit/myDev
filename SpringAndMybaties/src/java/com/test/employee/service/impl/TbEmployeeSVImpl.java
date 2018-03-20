/**
 * $Id: TbEmployeeSVImpl.java,v 1.0 2018/1/7 15:49 RaoXb Exp $
 * <p>
 * Copyright 2017 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.test.employee.service.impl;

import com.test.employee.dao.ITbEmployeeDao;
import com.test.employee.entity.TbEmployeeEntity;
import com.test.employee.service.interfaces.ITbEmployeeSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RaoXb
 * @version $Id: TbEmployeeSVImpl.java,v 1.1 2018/1/7 15:49 RaoXb Exp $
 * Created on 2018/1/7 15:49
 */
@Service
public class TbEmployeeSVImpl implements ITbEmployeeSV {

    @Autowired
    private ITbEmployeeDao employeeDao;

    @Override
    public List<TbEmployeeEntity> getAllEmployeeByCondition(TbEmployeeEntity employeeEntity) throws Exception {
//        return employeeDao.findByEntity(employeeEntity);
        return employeeDao.queryAllData();
    }
}