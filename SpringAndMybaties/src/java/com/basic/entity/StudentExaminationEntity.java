/**
 * $Id: StudentExaminationEntity.java,v 1.0 2018/3/5 21:12 pan Exp $
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
 * @version $Id: StudentExaminationEntity.java,v 1.1 2018/3/5 21:12 pan Exp $
 * Created on 2018/3/5 21:12
 */

public class StudentExaminationEntity extends BaseEntity{
    @Id
    private Integer stuExaminationId;
    private Integer studentId;
    private Integer examinationId;
    private String examinationTitle;
    private Integer isComplete;
    private Integer isRead;
    private Integer scoreNum;
    private String ext1;
    private String ext2;

    public Integer getStuExaminationId() {
        return stuExaminationId;
    }

    public void setStuExaminationId(Integer stuExaminationId) {
        this.stuExaminationId = stuExaminationId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String getExaminationTitle() {
        return examinationTitle;
    }

    public void setExaminationTitle(String examinationTitle) {
        this.examinationTitle = examinationTitle;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
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
