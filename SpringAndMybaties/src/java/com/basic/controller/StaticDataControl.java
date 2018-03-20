/**
 * $Id: StaticDataControl.java,v 1.0 2018/3/12 10:40 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.controller;/**
 * Created by pan on 2018/3/12.
 */

/**
 * @author pan
 * @version $Id: StaticDataControl.java,v 1.1 2018/3/12 10:40 pan Exp $
 * Created on 2018/3/12 10:40
 */

import com.basic.service.interfaces.IStaticDataSV;
import com.test.common.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "staticData")
public class StaticDataControl {

    @Autowired
    private IStaticDataSV staticDataSV;

    @RequestMapping("/getStaticDataByType")
    @ResponseBody
    public String getStaticDataByType(HttpServletRequest request){
        Response response = new Response();
        try{
            String codeType  = request.getParameter("codeType");
            if(null != codeType && !"".equals(codeType)){
                Map paraMap = new HashMap();
                paraMap.put("codeType",codeType);
                List result = staticDataSV.getStaticData(paraMap);
                response.setData(result);
                response.setCode(Response.SUCCESS);
            }else{
                response.setCode(Response.ERROR);
                response.setMessage("缺少参数");
            }
        }catch (Exception e){
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        }finally {
            return response.toString();
        }
    }
}
