/**
 * $Id: TbEmployeeEntity.java,v 1.0 2018/1/7 15:38 RaoXb Exp $
 * <p>
 * Copyright 2017 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.test.employee.entity;

import com.test.common.persistence.annotation.Id;
import com.test.common.persistence.entity.BaseEntity;

/**
 * @author RaoXb
 * @version $Id: TbEmployeeEntity.java,v 1.1 2018/1/7 15:38 RaoXb Exp $
 * Created on 2018/1/7 15:38
 */
public class TbEmployeeEntity extends BaseEntity {

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private Integer sex;
    private String hobby;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}