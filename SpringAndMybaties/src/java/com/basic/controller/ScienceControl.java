/**
 * $Id: ScienceControl.java,v 1.0 2018/3/8 14:42 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.controller;/**
 * Created by pan on 2018/3/8.
 */

import com.basic.entity.ScienceEntity;
import com.basic.service.interfaces.IScienceSV;
import com.test.common.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: ScienceControl.java,v 1.1 2018/3/8 14:42 pan Exp $
 * Created on 2018/3/8 14:42
 */
@Controller
@RequestMapping(value = "science")
public class ScienceControl {

    @Autowired
    private IScienceSV scienceSV;

    @RequestMapping("/getScience")
    @ResponseBody
    public String getScience(HttpServletRequest request){

        Response response = new Response();
        try {
            String firstScience = request.getParameter("firstScience");
            String secondScience = request.getParameter("secondScience");
            String thirdScience = request.getParameter("thirdScience");
            Map reMap = new HashMap();
            if(null != firstScience && !"".equals(firstScience)){
                reMap.put("firstScience",firstScience);
            }
            if(null != secondScience && !"".equals(secondScience)){
                reMap.put("secondScience",secondScience);
            }
            if(null != thirdScience && !"".equals(thirdScience)){
                reMap.put("thirdScience",thirdScience);
            }
            List result = scienceSV.getScience(reMap);
            response.setCode(Response.SUCCESS);
            response.setData(result);
        } catch (Exception e) {
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        } finally {
            return response.toString();
        }
    }

    @RequestMapping("/deleteData")
    @ResponseBody
    public String deleteData(HttpServletRequest request){
        Response response = new Response();

        try{
            Map getParam = request.getParameterMap();
            String[] deleteIds = (String[])getParam.get("deleteIds[]");
            if(null != deleteIds && deleteIds.length>0){
                Integer[] result = new Integer[deleteIds.length];
                for(int i=0 ; i<deleteIds.length ; i++){
                    result[i] = scienceSV.deleteSciencebyId(Integer.parseInt(deleteIds[i]));
                }
                response.setCode(Response.SUCCESS);
                response.setData(result);
            }else{
                response.setCode(Response.ERROR);
                response.setMessage("缺少删除参数！");
            }

        }catch (Exception e){
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        }finally {
            return response.toString();
        }
    }

    @RequestMapping("/addData")
    @ResponseBody
    public String insertScience(HttpServletRequest request){
        Response response = new Response();

        try{
                Integer newId = scienceSV.getScienceNewId();
                response.setCode(Response.SUCCESS);
                response.setData(0);

        }catch (Exception e){
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        }finally {
            return response.toString();
        }
    }
}
