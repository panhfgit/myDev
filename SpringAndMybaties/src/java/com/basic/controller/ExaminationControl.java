/**
 * $Id: ExaminationControl.java,v 1.0 2018/3/13 22:10 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.controller;/**
 * Created by pan on 2018/3/13.
 */

import com.basic.service.interfaces.IExaminationSV;
import com.test.common.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: ExaminationControl.java,v 1.1 2018/3/13 22:10 pan Exp $
 * Created on 2018/3/13 22:10
 */
@Controller
@RequestMapping(value = "examination")
public class ExaminationControl {

    @Autowired
    private IExaminationSV examinationSV;

    @RequestMapping("/getExamination")
    @ResponseBody
    public String getExamination(HttpServletRequest request){
        Response response = new Response();
        try {
            HttpSession session = request.getSession();
            Integer userId = (Integer)session.getAttribute("userId");
            Integer userType = (Integer)session.getAttribute("userType");
            if(null != userId && userId != 0 && null != userType && userType == 1){
                Map paraMap = new HashMap();
                paraMap.put("userId",userId);
                List result = examinationSV.getExamination(paraMap);
                response.setCode(Response.SUCCESS);
                response.setData(result);
                response.setMessage("查询成功！");
            }else{
                response.setCode(Response.ERROR);
                response.setMessage("身份信息获取异常！");
            }
        }catch (Exception e){
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        }finally {
            return response.toString();
        }
    }

    @RequestMapping("/joinExamination")
    @ResponseBody
    public String loadExamination(HttpServletRequest request){
        Response response = new Response();
        try{
            String stuExaminationId = request.getParameter("stuExaminationId");
            if(null != stuExaminationId && !"".equals(stuExaminationId)){
                Map paraMap = new HashMap();
                paraMap.put("stuExaminationId",stuExaminationId);
                Map result = examinationSV.loadExamination(paraMap);
                response.setCode(Response.SUCCESS);
                response.setData(result);
                response.setMessage("加载数据成功成功！");
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

    @RequestMapping("/saveAnswer")
    @ResponseBody
    public String saveAnswer(HttpServletRequest request){
        Response response = new Response();
        try{
            Map getParam = request.getParameterMap();
            String stuExaminationId = ((String[])getParam.get("stuExaminationId"))[0];
            if(null != stuExaminationId && !"".equals(stuExaminationId)){
                Map result = examinationSV.saveAnswer(getParam);
                response.setCode(Response.SUCCESS);
                response.setData(result);
                response.setMessage("保存数据成功！");
            }else{
                response.setCode(Response.ERROR);
                response.setMessage("缺少参数stuExaminationId");
            }
        }catch (Exception e){
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        }finally {
            return response.toString();
        }
    }

    @RequestMapping("/autoGenerateExam")
    @ResponseBody
    public String autoGenerateExam(HttpServletRequest request){
        Response response = new Response();
        try{
            Map getParam = request.getParameterMap();
            String[] paramString = (String[])getParam.get("classifyInfo[]");
            if(null != paramString && paramString.length>0){
                List paraList = new ArrayList();
                for(int i=0 ; i<paramString.length ; i++){
                    paraList.add(i,paramString[i]);
                }
                Map result = examinationSV.autoGenerateExam(paraList);
                response.setCode(Response.SUCCESS);
                response.setData(result);
                response.setMessage("自动生成成功！");
            }else{
                response.setCode(Response.ERROR);
                response.setMessage("缺少具体的分类信息");
            }
        }catch (Exception e){
            response.setCode(Response.ERROR);
            response.setMessage(e.getMessage());
        }finally {
            return response.toString();
        }
    }
}
