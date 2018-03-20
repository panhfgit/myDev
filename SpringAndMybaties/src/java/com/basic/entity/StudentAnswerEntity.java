/**
 * $Id: StudentAnswerEntity.java,v 1.0 2018/3/5 21:08 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.entity;/**
 * Created by pan on 2018/3/5.
 */
import com.test.common.persistence.entity.BaseEntity;
import com.test.common.persistence.annotation.Id;
/**
 * @author pan
 * @version $Id: StudentAnswerEntity.java,v 1.1 2018/3/5 21:08 pan Exp $
 * Created on 2018/3/5 21:08
 */

public class StudentAnswerEntity extends BaseEntity{
    @Id
    private Integer answerId;
    private Integer relateSubjectId;
    private Integer stuExaminationId;
    private String answerDetail;
    private Integer isComplete;
    private Integer isRead;
    private Integer answerScore;
    private String ext1;
    private String ext2;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getRelateSubjectId() {
        return relateSubjectId;
    }

    public void setRelateSubjectId(Integer relateSubjectId) {
        this.relateSubjectId = relateSubjectId;
    }

    public Integer getStuExaminationId() {
        return stuExaminationId;
    }

    public void setStuExaminationId(Integer stuExaminationId) {
        this.stuExaminationId = stuExaminationId;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public Integer getAnswerScore() {
        return answerScore;
    }

    public void setAnswerScore(Integer answerScore) {
        this.answerScore = answerScore;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }
}
