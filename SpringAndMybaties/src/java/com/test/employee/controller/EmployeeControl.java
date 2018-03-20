/**
 * $Id: EmployeeControl.java,v 1.0 2018/1/7 17:38 RaoXb Exp $
 * <p>
 * Copyright 2017 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.test.employee.controller;

import com.test.common.support.Response;
import com.test.employee.entity.TbEmployeeEntity;
import com.test.employee.service.interfaces.ITbEmployeeSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author RaoXb
 * @version $Id: EmployeeControl.java,v 1.1 2018/1/7 17:38 RaoXb Exp $
 * Created on 2018/1/7 17:38
 */
@Controller
@RequestMapping(value = "employee")
public class EmployeeControl {

    @Autowired
    private ITbEmployeeSV employeeSV;

    @RequestMapping("/getAllEmployeeInfo")
    @ResponseBody
    public String getAllEmployeeInfo(){
        Response response = new Response();
        try{
            TbEmployeeEntity employeeEntity = new TbEmployeeEntity();
            employeeEntity.setState(new Integer(1));
            List<TbEmployeeEntity> result = employeeSV.getAllEmployeeByCondition(employeeEntity);
            response.setCode(Response.SUCCESS);
            response.setData(result);
        }catch (Exception e){
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        }finally {
            return response.toString();
        }
    }
}