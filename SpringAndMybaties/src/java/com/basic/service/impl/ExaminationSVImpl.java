/**
 * $Id: ExaminationSVImpl.java,v 1.0 2018/3/13 22:16 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.service.impl;/**
 * Created by pan on 2018/3/13.
 */

import com.basic.dao.IExaminationDao;
import com.basic.entity.*;
import com.basic.service.interfaces.IExaminationSV;
import com.basic.service.interfaces.IScienceSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author pan
 * @version $Id: ExaminationSVImpl.java,v 1.1 2018/3/13 22:16 pan Exp $
 * Created on 2018/3/13 22:16
 */
@Service
public class ExaminationSVImpl implements IExaminationSV{

    @Autowired
    private IExaminationDao examinationDao;

    @Autowired
    private IScienceSV scienceSV;
    /**
     * 查询学生的考试任务
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Override
    public List getExamination(Map paraMap) throws Exception {
        return examinationDao.getExamination(paraMap);
    }

    /**
     * 加载试卷框架
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Override
    public Map loadExamination(Map paraMap) throws Exception {

        Map stuExamination = (Map)getExamination(paraMap).get(0);
        if(null != stuExamination){
            //Map queryExamMap = new HashMap();//查询examination用
            //queryExamMap.put("examinationId",stuExamination.getExaminationId());
            //queryExamMap.put("examinationType",1);
            Map examination = examinationDao.queryExamination(stuExamination).get(0);
            if(null != examination){
                //查询试卷和题目的关系
                List examSubjectRels = examinationDao.queryExamSubjectRel(examination);

                if(null != examSubjectRels && examSubjectRels.size()>0){
                    for(int i=0 ; i<examSubjectRels.size() ; i++){
                        Map ExamsubjectRel = (Map)examSubjectRels.get(i);

                        Map subject = examinationDao.querySubject(ExamsubjectRel).get(0);
                        if(null != subject){
                            if(Integer.parseInt(subject.get("Subject_type").toString()) == 1 ||
                                    Integer.parseInt(subject.get("Subject_type").toString()) == 2){
                                List choice = examinationDao.queryChoice(subject);
                                if(null != choice && choice.size()>0){
                                    subject.put("choices",choice);
                                }else{
                                    throw new Exception("未查询到选项信息！");
                                }
                            }
                            ExamsubjectRel.put("subject",subject);
                        }else{
                            throw new Exception("未查询到题目信息！");
                        }
                    }
                    examination.put("examSubjectRels",examSubjectRels);
                }else{
                    throw new Exception("未查询到试卷和题目关联信息！");
                }
            }else{
                throw new Exception("未查询到对应试卷信息！");
            }
            stuExamination.put("examination",examination);
            return stuExamination;
        }else{
            throw new Exception("未查询到学生考试信息！");
        }
    }

    /**
     * 查询试卷
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Override
    public List queryExamination(Map paraMap) throws Exception {
        List<Map> examinations = examinationDao.queryExamination(paraMap);
        for(Map examination : examinations){
            if("1".equals(examination.get("Examination_type").toString())){
                examination.put("examinationType","考试");
            }else if("2".equals(examination.get("Examination_type").toString())){
                examination.put("examinationType","测验");
            }else if("3".equals(examination.get("Examination_type").toString())){
                examination.put("examinationType","临时试卷");
            }
        }
        return examinations;
    }

    /**
     * 查询试卷和题目的关系
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Override
    public List queryExamSubjectRel(Map paraMap) throws Exception {
        return examinationDao.queryExamSubjectRel(paraMap);
    }

    /**
     * 查询题目
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Override
    public List querySubject(Map paraMap) throws Exception {
        return examinationDao.querySubject(paraMap);
    }

    /**
     * 查询选择题选项
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Override
    public List queryChoice(Map paraMap) throws Exception {
        return examinationDao.queryChoice(paraMap);
    }


    /**
     * 保存学生提交的答卷+修改答卷状态
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Override
    public Map saveAnswer(Map paraMap) throws Exception {
        Map resultMap = new HashMap();

        String[] singleChoiceAnswer = (String[])paraMap.get("singleChoiceAnswer[]");
        String[] multipleChoiceAnswer = (String[])paraMap.get("multipleChoiceAnswer[]");
        String[] subjectiveAnswer = (String[])paraMap.get("subjectiveAnswer[]");
        Integer stuExaminationId = Integer.parseInt(((String[])paraMap.get("stuExaminationId"))[0]);
        resultMap.put("stuExaminationId",stuExaminationId);

        for(int i=0 ; i<singleChoiceAnswer.length ; i++){
            StudentAnswerEntity stuAnswer = new StudentAnswerEntity();
            //查询选项信息（包含题目编号）
            Map queryChoiceMap = new HashMap();
            queryChoiceMap.put("choiceId",singleChoiceAnswer[i]);
            Map choiceMap = examinationDao.queryChoice(queryChoiceMap).get(0);

            stuAnswer.setAnswerId(getNewId("student_answer"));
            stuAnswer.setAnswerDetail(singleChoiceAnswer[i]);
            stuAnswer.setStuExaminationId(stuExaminationId);
            stuAnswer.setRelateSubjectId(Integer.parseInt(choiceMap.get("Relate_subject_id").toString()));
            stuAnswer.setIsComplete(1);
            stuAnswer.setIsRead(0);

            examinationDao.saveStuAnswer(stuAnswer);
        }

        for(int i=0 ; i<multipleChoiceAnswer.length ; i++){
            StudentAnswerEntity stuAnswer = new StudentAnswerEntity();
            //查询选项信息（包含题目编号）
            Map queryChoiceMap = new HashMap();
            queryChoiceMap.put("choiceId",(multipleChoiceAnswer[i].split(","))[0]);
            Map choiceMap = examinationDao.queryChoice(queryChoiceMap).get(0);

            stuAnswer.setAnswerId(getNewId("student_answer"));
            stuAnswer.setAnswerDetail(multipleChoiceAnswer[i]);
            stuAnswer.setStuExaminationId(stuExaminationId);
            stuAnswer.setRelateSubjectId(Integer.parseInt(choiceMap.get("Relate_subject_id").toString()));
            stuAnswer.setIsComplete(1);
            stuAnswer.setIsRead(0);

            examinationDao.saveStuAnswer(stuAnswer);
        }

        for(int i=0 ; i<subjectiveAnswer.length ; i++){
            StudentAnswerEntity stuAnswer = new StudentAnswerEntity();
            Integer subjectId = Integer.parseInt(subjectiveAnswer[i].split("_")[0]);
            String answer = subjectiveAnswer[i].split("_")[1];

            stuAnswer.setAnswerId(getNewId("student_answer"));
            stuAnswer.setAnswerDetail(answer);
            stuAnswer.setStuExaminationId(stuExaminationId);
            stuAnswer.setRelateSubjectId(subjectId);
            stuAnswer.setIsComplete(1);
            stuAnswer.setIsRead(0);

            examinationDao.saveStuAnswer(stuAnswer);
        }

        return resultMap;
    }

    /**
     * 获取Student_Answer新主键
     * @return
     * @throws Exception
     */
    @Override
    public Integer getNewId(String tableName) throws Exception {
        Map result = examinationDao.getNewId(tableName);
        return Integer.parseInt(result.get("Auto_increment").toString());
    }

    @Override
    public Map autoGenerateExam(List paraList) throws Exception {

        Map returnMap = new HashMap();

        List singleChoiceExam = new ArrayList();
        int singleChoiceExamNum = 0;
        List multipleChoiceExam = new ArrayList();
        int multipleChoiceExamNum = 0;
        List subjectiveExam = new ArrayList();
        int subjectiveExamNum = 0;

        if(null != paraList && paraList.size()>0){
            for(int i=0 ; i<paraList.size() ; i++){
                String[] classifyString = ((String)paraList.get(i)).split("_");
                if(null != classifyString && classifyString.length == 4){
                    Map querySubjectMap = new HashMap();
                    querySubjectMap.put("scienceId",classifyString[0]);
                    List allSubjects = querySubject(querySubjectMap);

                    //将查询的题目分组存放
                    List singleChoice = new ArrayList();
                    int singleChoiceNum = 0;
                    List multipleChoice = new ArrayList();
                    int multipleChoiceNum = 0;
                    List subjective = new ArrayList();
                    int subjectiveNum = 0;
                    for(int j=0 ; j<allSubjects.size() ; j++){
                        Map subject = (Map)allSubjects.get(j);
                        if(null != subject && Integer.parseInt(subject.get("Subject_type").toString()) ==1){
                            singleChoice.add(singleChoiceNum++,subject);
                        }else if(null != subject && Integer.parseInt(subject.get("Subject_type").toString()) ==2){
                            multipleChoice.add(multipleChoiceNum++,subject);
                        }else if(null != subject && Integer.parseInt(subject.get("Subject_type").toString()) ==3){
                            subjective.add(subjectiveNum++,subject);
                        }
                    }

                    //校验题目数量
                    int singleChoiceNumThis = Integer.parseInt(classifyString[1]);
                    int multipleChoiceNumThis = Integer.parseInt(classifyString[2]);
                    int subjectiveNumThis = Integer.parseInt(classifyString[3]);

                    if(singleChoice.size() < singleChoiceNumThis){
                        throw new Exception("单选题数量不足！");
                    }
                    if(multipleChoice.size() < multipleChoiceNumThis){
                        throw new Exception("多选题数量不足！");
                    }
                    if(subjective.size() < subjectiveNumThis){
                        throw new Exception("主观题数量不足！");
                    }

                    //生成试卷主体部分
                    List<Integer> singleListId = new ArrayList<>();
                    for(int m=0 ; m<singleChoiceNumThis ; m++){
                        int randomNum = -1;
                        while(true){
                            randomNum = (int)(Math.random() * singleChoice.size());
                            if(!singleListId.contains(randomNum)){
                                break;
                            }
                        }
                        singleListId.add(m,randomNum);
                        singleChoiceExam.add(singleChoiceExamNum++,singleChoice.get(randomNum));
                    }
                    returnMap.put("singlChoices",singleChoiceExam);

                    List<Integer> multipleListId = new ArrayList<>();
                    for(int n=0 ; n<multipleChoiceNumThis ; n++){
                        int randomNum = -1;
                        while(true){
                            randomNum = (int)(Math.random() * multipleChoice.size());
                            if(!multipleListId.contains(randomNum)){
                                break;
                            }
                        }
                        multipleListId.add(n,randomNum);
                        multipleChoiceExam.add(multipleChoiceExamNum++,multipleChoice.get(randomNum));
                    }
                    returnMap.put("multipleChoices",multipleChoiceExam);

                    List<Integer> subjectiveListId = new ArrayList<>();
                    for(int n=0 ; n<subjectiveNumThis ; n++){
                        int randomNum = -1;
                        while(true){
                            randomNum = (int)(Math.random() * subjective.size());
                            if(!subjectiveListId.contains(randomNum)){
                                break;
                            }
                        }
                        subjectiveListId.add(n,randomNum);
                        subjectiveExam.add(subjectiveExamNum++,subjective.get(randomNum));
                    }
                    returnMap.put("subjectives",subjectiveExam);

                }else{
                    throw new Exception("分类信息格式存在错误！");
                }
            }
        }else{
            throw new Exception("缺少分类详情！");
        }
        return returnMap;
    }

    @Override
    public Map saveAutoExamination(Map paraMap) throws Exception {
        Map resultMap = new HashMap();

        if(paraMap.containsKey("singlChoices") && paraMap.containsKey("multipleChoices")
                && paraMap.containsKey("subjectives") && paraMap.containsKey("examTitle")
                && paraMap.containsKey("createrId")){
            List singlChoices = (List)paraMap.get("singlChoices");
            List multipleChoices = (List)paraMap.get("multipleChoices");
            List subjectives = (List)paraMap.get("subjectives");
            String examTitle = (String)paraMap.get("examTitle");
            Integer examType = Integer.parseInt(paraMap.get("examType").toString());
            Integer createrId = Integer.parseInt(paraMap.get("createrId").toString());

            //存储试卷信息
            ExaminationEntity examination = new ExaminationEntity();
            examination.setExaminationId(getNewId("examination"));
            examination.setExaminationTitle(examTitle);
            examination.setState(1);
            examination.setCreater(createrId);
            examination.setExaminationType(examType);
            examination.setCreateDate(new Date());

            examinationDao.saveExamination(examination);
            resultMap.put("examinationId",examination.getExaminationId());

            //存储试卷题目信息
            int sortId = 0;
            for(int i=0 ; i<singlChoices.size() ; i++){
                Map singlechoice = (Map)singlChoices.get(i);

                ExaminationSubjectRelEntity examSubjectRel = new ExaminationSubjectRelEntity();
                examSubjectRel.setRelateId(getNewId("examination_subject_rel"));
                examSubjectRel.setExaminationId(examination.getExaminationId());
                examSubjectRel.setSubjectId(Integer.parseInt(singlechoice.get("Subject_id").toString()));
                examSubjectRel.setSubjectScore(2);
                examSubjectRel.setSortId(++sortId);
                examSubjectRel.setSubjectAnswer(singlechoice.get("Subject_answer").toString());
                examSubjectRel.setState(1);
                examSubjectRel.setSubjectType(Integer.parseInt(singlechoice.get("Subject_type").toString()));
                examinationDao.saveExaminationSubjectRel(examSubjectRel);
            }
            for(int i=0 ; i<multipleChoices.size() ; i++){
                Map multipleChoice = (Map)multipleChoices.get(i);

                ExaminationSubjectRelEntity examSubjectRel = new ExaminationSubjectRelEntity();
                examSubjectRel.setRelateId(getNewId("examination_subject_rel"));
                examSubjectRel.setExaminationId(examination.getExaminationId());
                examSubjectRel.setSubjectId(Integer.parseInt(multipleChoice.get("Subject_id").toString()));
                examSubjectRel.setSubjectScore(2);
                examSubjectRel.setSortId(++sortId);
                examSubjectRel.setSubjectAnswer(multipleChoice.get("Subject_answer").toString());
                examSubjectRel.setState(1);
                examSubjectRel.setSubjectType(Integer.parseInt(multipleChoice.get("Subject_type").toString()));
                examinationDao.saveExaminationSubjectRel(examSubjectRel);
            }
            for(int i=0 ; i<subjectives.size() ; i++){
                Map subjective = (Map)subjectives.get(i);

                ExaminationSubjectRelEntity examSubjectRel = new ExaminationSubjectRelEntity();
                examSubjectRel.setRelateId(getNewId("examination_subject_rel"));
                examSubjectRel.setExaminationId(examination.getExaminationId());
                examSubjectRel.setSubjectId(Integer.parseInt(subjective.get("Subject_id").toString()));
                examSubjectRel.setSubjectScore(5);
                examSubjectRel.setSubjectAnswer("");
                examSubjectRel.setSortId(++sortId);
                examSubjectRel.setState(1);
                examSubjectRel.setSubjectType(Integer.parseInt(subjective.get("Subject_type").toString()));
                examinationDao.saveExaminationSubjectRel(examSubjectRel);
            }



        }else{
            throw new Exception("保存失败，缺少题目信息！");
        }
        return resultMap;
    }

    @Override
    public List querySubject4Design(Map paraMap) throws Exception {

        List<Map> resultList = new ArrayList<>();
        Map examination = examinationDao.queryExamination(paraMap).get(0);
        if(null != examination){
            List examSubjectRels = examinationDao.queryExamSubjectRel(examination);
            if(null != examSubjectRels && examSubjectRels.size() > 0){
                for(int i=0 ; i<examSubjectRels.size() ; i++){
                    Map subject = examinationDao.querySubject((Map)examSubjectRels.get(i)).get(0);
                    String subjectAnswer = "";
                    //选项信息
                    if("1".equals(subject.get("Subject_type").toString()) || "2".equals(subject.get("Subject_type").toString())){
                        List<Map> choices = examinationDao.queryChoice(subject);
                        for(Map choice : choices){
                            subjectAnswer += choice.get("Choice_detail") + "<br>";
                        }
                        subjectAnswer = subjectAnswer.substring(0,subjectAnswer.length()-4);
                    }
                    //所属学科信息
                    Map science = (Map)scienceSV.getScience(subject).get(0);
                    //题型
                    if("1".equals(subject.get("Subject_type").toString())){
                        subject.put("subjectType","单选题");
                    } else if("2".equals(subject.get("Subject_type").toString())){
                        subject.put("subjectType","多选题");
                    } else if("3".equals(subject.get("Subject_type").toString())){
                        subject.put("subjectType","主观题");
                    }
                    subject.put("scienceName",science.get("Science_detail").toString());
                    subject.put("subjectAnswer",subjectAnswer);
                    resultList.add(i,subject);
                }
                return resultList;
            }else{
                throw new Exception("未查询到试卷相关题目信息！");
            }
        }else{
            throw new Exception("未查询到试卷基本信息！");
        }
    }

    @Override
    public Map querySubject4Update(Map paraMap) throws Exception {
        List<Map> examSubjectRels = examinationDao.queryExamSubjectRel(paraMap);
        if(null != examSubjectRels && examSubjectRels.size() == 1){
            Map result = examSubjectRels.get(0);
            Map subject = examinationDao.querySubject(result).get(0);
            Map science = (Map)scienceSV.getScience(subject).get(0);
            //选择题添加选项
            if("1".equals(subject.get("Subject_type").toString()) || "2".equals(subject.get("Subject_type").toString())){
                List choices = examinationDao.queryChoice(subject);
                result.put("choices",choices);
            }
            result.put("subject",subject);
            result.put("science",science);
            return result;

        }else{
            throw new Exception("查询题目信息不存在或不唯一！");
        }
    }


}
