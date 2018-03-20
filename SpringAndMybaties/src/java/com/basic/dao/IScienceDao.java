/**
 * $Id: IScienceDao.java,v 1.0 2018/3/8 14:48 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.dao;/**
 * Created by pan on 2018/3/8.
 */

import com.basic.entity.ScienceEntity;
import com.test.common.persistence.annotation.Dao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: IScienceDao.java,v 1.1 2018/3/8 14:48 pan Exp $
 * Created on 2018/3/8 14:48
 */
@Repository
@Dao(ScienceEntity.class)
public interface IScienceDao {
    public List<ScienceEntity> getScience(Map paraMap) throws Exception;

    public void deleteSciencebyId(Integer scienceId)throws Exception;

    public Map getScienceNewId()throws Exception;
}
