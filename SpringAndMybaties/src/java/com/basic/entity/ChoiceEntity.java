/**
 * $Id: ChoiceEntity.java,v 1.0 2018/3/5 20:35 pan Exp $
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
 * @version $Id: ChoiceEntity.java,v 1.1 2018/3/5 20:35 pan Exp $
 * Created on 2018/3/5 20:35
 */

public class ChoiceEntity extends BaseEntity {
    @Id
    private Integer choiceId;
    private Integer relateSubjectId;
    private String choiceDetail;
    private Integer choiceType;
    private Integer state;
    private String ext;

    public Integer getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }

    public Integer getRelateSubjectId() {
        return relateSubjectId;
    }

    public void setRelateSubjectId(Integer relateSubjectId) {
        this.relateSubjectId = relateSubjectId;
    }

    public String getChoiceDetail() {
        return choiceDetail;
    }

    public void setChoiceDetail(String choiceDetail) {
        this.choiceDetail = choiceDetail;
    }

    public Integer getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(Integer choiceType) {
        this.choiceType = choiceType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
