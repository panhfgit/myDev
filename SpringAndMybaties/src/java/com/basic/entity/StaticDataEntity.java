/**
 * $Id: StaticDataEntity.java,v 1.0 2018/3/12 10:23 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.entity;/**
 * Created by pan on 2018/3/12.
 */

import com.test.common.persistence.entity.BaseEntity;

/**
 * @author pan
 * @version $Id: StaticDataEntity.java,v 1.1 2018/3/12 10:23 pan Exp $
 * Created on 2018/3/12 10:23
 */

public class StaticDataEntity extends BaseEntity{

    private String codeType;
    private String codeValue;
    private String codeText;
    private Integer sortId;
    private String ext1;
    private String ext2;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
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
