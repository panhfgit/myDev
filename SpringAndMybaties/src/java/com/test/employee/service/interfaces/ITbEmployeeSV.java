/**
 * $Id: ITbEmployeeSV.java,v 1.0 2018/1/7 15:48 RaoXb Exp $
 * <p>
 * Copyright 2017 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.test.employee.service.interfaces;

import com.test.employee.entity.TbEmployeeEntity;

import java.util.List;

/**
 * @author RaoXb
 * @version $Id: ITbEmployeeSV.java,v 1.1 2018/1/7 15:48 RaoXb Exp $
 * Created on 2018/1/7 15:48
 */
public interface ITbEmployeeSV {

    public List<TbEmployeeEntity> getAllEmployeeByCondition(TbEmployeeEntity employeeEntity)throws Exception;

}