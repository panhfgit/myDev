/**
 * $Id: ExaminationSubjectRelEntity.java,v 1.0 2018/3/5 20:59 pan Exp $
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
 * @version $Id: ExaminationSubjectRelEntity.java,v 1.1 2018/3/5 20:59 pan Exp $
 * Created on 2018/3/5 20:59
 */

public class ExaminationSubjectRelEntity extends BaseEntity{
    @Id
    private Integer relateId;
    private Integer examinationId;
    private Integer subjectId;
    private Integer subjectScore;
    private Integer sortId;
    private Integer subjectType;
    private String subjectAnswer;
    private Integer state;
    private String ext1;
    private String ext2;

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Integer subjectScore) {
        this.subjectScore = subjectScore;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectAnswer() {
        return subjectAnswer;
    }

    public void setSubjectAnswer(String subjectAnswer) {
        this.subjectAnswer = subjectAnswer;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
