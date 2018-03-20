package com.test.common.persistence.entity;

import com.test.common.utils.Constants;
import com.test.common.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by dizl on 2015/7/15.
 */
public class Rank implements Serializable{
    private String attrName;
    private String rankType =  Constants.OrderType.ASC;//desc  asc

    public Rank(String attrName){
        this.attrName = attrName;
    }

    public Rank(String attrName, String orderType){
        this.attrName = attrName;
        if (StringUtils.isNotEmpty(orderType)) {
            this.rankType = orderType;
        }
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }
}
