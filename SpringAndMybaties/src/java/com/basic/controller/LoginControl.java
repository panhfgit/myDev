/**
 * $Id: LoginControl.java,v 1.0 2018/3/6 14:44 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.controller;/**
 * Created by pan on 2018/3/6.
 */

import com.basic.entity.UserEntity;
import com.basic.service.interfaces.IUserSV;

import com.test.common.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pan
 * @version $Id: LoginControl.java,v 1.1 2018/3/6 14:44 pan Exp $
 * Created on 2018/3/6 14:44
 */
@Controller
@RequestMapping(value = "login")
public class LoginControl {


    @Autowired
    private IUserSV userSV;

    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(HttpServletRequest request) {

        Response response = new Response();
        try {
            HttpSession session = request.getSession();
            String userId = request.getParameter("userName");
            String password = request.getParameter("password");
            Map reMap = new HashMap();
            if (null != userId && null != password && !"".equals(userId) && !"".equals(password)) {
                reMap.put("userId", userId);
                reMap.put("password", password);
                Map result = userSV.checkLogin(reMap);
                if(result.containsKey("isLogin") && "1".equals(result.get("isLogin").toString())){
                    UserEntity user = (UserEntity)result.get("userInfo");
                    session.setAttribute("userName",user.getUserName());
                    session.setAttribute("userId",user.getUserId());
                    session.setAttribute("userType",user.getUserType());
                }
                response.setCode(Response.SUCCESS);
                response.setData(result);

            } else {
                response.setCode(Response.ERROR);
                response.setMessage("缺少参数");
            }
        } catch (Exception e) {
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        } finally {
            return response.toString();
        }
    }

}
