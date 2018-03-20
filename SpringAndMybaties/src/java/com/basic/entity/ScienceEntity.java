/**
 * $Id: ScienceEntity.java,v 1.0 2018/3/5 21:05 pan Exp $
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
 * @version $Id: ScienceEntity.java,v 1.1 2018/3/5 21:05 pan Exp $
 * Created on 2018/3/5 21:05
 */

public class ScienceEntity extends BaseEntity {
    @Id
    private Integer scienceId;
    private Integer scienceType;
    private Integer parentScienceId;
    private String scienceDetail;
    private Integer state;
    private String ext1;
    private String ext2;

    public Integer getScienceId() {
        return scienceId;
    }

    public void setScienceId(Integer scienceId) {
        this.scienceId = scienceId;
    }

    public Integer getScienceType() {
        return scienceType;
    }

    public void setScienceType(Integer scienceType) {
        this.scienceType = scienceType;
    }

    public Integer getParentScienceId() {
        return parentScienceId;
    }

    public void setParentScienceId(Integer parentScienceId) {
        this.parentScienceId = parentScienceId;
    }

    public String getScienceDetail() {
        return scienceDetail;
    }

    public void setScienceDetail(String scienceDetail) {
        this.scienceDetail = scienceDetail;
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
