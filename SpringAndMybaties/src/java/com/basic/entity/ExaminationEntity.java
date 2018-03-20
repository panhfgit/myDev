/**
 * $Id: ExaminationEntity.java,v 1.0 2018/3/5 20:12 pan Exp $
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
 * @version $Id: ExaminationEntity.java,v 1.1 2018/3/5 20:12 pan Exp $
 * Created on 2018/3/5 20:12
 */

public class ExaminationEntity extends BaseEntity {
    @Id
    private Integer examinationId;
    private String examinationTitle;
    private Integer examinationType;
    private Integer creater;
    private Date createDate;
    private Integer state;
    private String ext1;
    private String ext2;

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

    public Integer getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(Integer examinationType) {
        this.examinationType = examinationType;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
