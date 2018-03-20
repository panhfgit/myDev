/**
 * $Id: ExaminationSVImpl.java,v 1.0 2018/3/13 22:16 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.service.impl;/**
 * Created by pan on 2018/3/13.
 */

import com.basic.dao.IExaminationDao;
import com.basic.entity.ExaminationEntity;
import com.basic.entity.StudentAnswerEntity;
import com.basic.entity.StudentExaminationEntity;
import com.basic.entity.SubjectEntity;
import com.basic.service.interfaces.IExaminationSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: ExaminationSVImpl.java,v 1.1 2018/3/13 22:16 pan Exp $
 * Created on 2018/3/13 22:16
 */
@Service
public class ExaminationSVImpl implements IExaminationSV{

    @Autowired
    private IExaminationDao examinationDao;

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
        return examinationDao.queryExamination(paraMap);
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

            stuAnswer.setAnswerId(getStuAnswerNewId());
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

            stuAnswer.setAnswerId(getStuAnswerNewId());
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

            stuAnswer.setAnswerId(getStuAnswerNewId());
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
    public Integer getStuAnswerNewId() throws Exception {
        Map result = examinationDao.getStuAnswerNewId();
        return Integer.parseInt(result.get("Auto_increment").toString());
    }

    @Override
    public Map autoGenerateExam(List paraList) throws Exception {
        if(null != paraList && paraList.size()>0){
            for(int i=0 ; i<paraList.size() ; i++){
                String[] classifyString = ((String)paraList.get(i)).split("_");
                if(null != classifyString && classifyString.length == 4){
                    Map querySubjectMap = new HashMap();
                    querySubjectMap.put("scienceId",classifyString[0]);
                    List allSubjects = querySubject(querySubjectMap);
                    List singleChoice = new ArrayList();
                    Integer singleChoiceNum = 0;
                    List multipleChoice = new ArrayList();
                    Integer multipleChoiceNum = 0;
                    List subjective = new ArrayList();
                    Integer subjectiveNum = 0;
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
                    if(singleChoice.size() < Integer.parseInt(classifyString[1])){
                        throw new Exception("单选题数量不足！");
                    }
                    if(multipleChoice.size() < Integer.parseInt(classifyString[2])){
                        throw new Exception("多选题数量不足！");
                    }
                    if(subjective.size() < Integer.parseInt(classifyString[3])){
                        throw new Exception("主观题数量不足！");
                    }

                    //生成试卷主题

                }else{
                    throw new Exception("分类信息格式存在错误！");
                }
            }
        }else{
            throw new Exception("缺少分类详情！");
        }
        return null;
    }


}
