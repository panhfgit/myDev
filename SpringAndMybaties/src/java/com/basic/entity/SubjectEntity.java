/**
 * $Id: SubjectEntity.java,v 1.0 2018/3/5 20:24 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.entity;/**
 * Created by pan on 2018/3/5.
 */

import com.test.common.persistence.annotation.Id;
import com.test.common.persistence.entity.BaseEntity;

import java.util.Date;

/**
 * @author pan
 * @version $Id: SubjectEntity.java,v 1.1 2018/3/5 20:24 pan Exp $
 * Created on 2018/3/5 20:24
 */

public class SubjectEntity extends BaseEntity {
    @Id
    private Integer subjectId;
    private Integer subjectType;
    private String subjectDetail;
    private Integer scienceType;
    private Integer scienceId;
    private Date createDate;
    private Integer creater;
    private Integer state;
    private String subjectAnswer;
    private String ext1;
    private String ext2;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectDetail() {
        return subjectDetail;
    }

    public void setSubjectDetail(String subjectDetail) {
        this.subjectDetail = subjectDetail;
    }

    public Integer getScienceType() {
        return scienceType;
    }

    public void setScienceType(Integer scienceType) {
        this.scienceType = scienceType;
    }

    public Integer getScienceId() {
        return scienceId;
    }

    public void setScienceId(Integer scienceId) {
        this.scienceId = scienceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSubjectAnswer() {
        return subjectAnswer;
    }

    public void setSubjectAnswer(String subjectAnswer) {
        this.subjectAnswer = subjectAnswer;
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
