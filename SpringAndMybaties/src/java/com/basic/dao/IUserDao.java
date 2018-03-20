/**
 * $Id: IUserDao.java,v 1.0 2018/3/6 15:09 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.dao;/**
 * Created by pan on 2018/3/6.
 */

import com.basic.entity.UserEntity;
import com.test.common.persistence.annotation.Dao;
import com.test.common.persistence.dao.CrudDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: IUserDao.java,v 1.1 2018/3/6 15:09 pan Exp $
 * Created on 2018/3/6 15:09
 */
@Repository
@Dao(UserEntity.class)
public interface IUserDao extends CrudDao<UserEntity,Long>{

    UserEntity queryUserInfoById(Map paraMap) throws Exception;
}
