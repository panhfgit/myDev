/**
 * $Id: UserSVImpl.java,v 1.0 2018/3/6 14:58 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.service.impl;/**
 * Created by pan on 2018/3/6.
 */

import com.basic.dao.IUserDao;
import com.basic.entity.UserEntity;
import com.basic.service.interfaces.IUserSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: UserSVImpl.java,v 1.1 2018/3/6 14:58 pan Exp $
 * Created on 2018/3/6 14:58
 */
@Service
public class UserSVImpl implements IUserSV{

    @Autowired
    private IUserDao userDao;

    @Override
    public Map checkLogin(Map paraMap) throws Exception {

        Map retMap = new HashMap();
        if(paraMap.containsKey("userId") && paraMap.get("userId") != ""
                && paraMap.containsKey("password") && paraMap.get("password") != ""){

            UserEntity user = userDao.queryUserInfoById(paraMap);

            if(paraMap.get("password").toString().equals(user.getLoginPassword())
                    && user.getState() == 1){
                retMap.put("userInfo",user);
                retMap.put("isLogin",1);
            }else{
                retMap.put("isLogin",0);
            }
        }else{
            retMap.put("isLogin",0);
        }
        return retMap;
    }
}
