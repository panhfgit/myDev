/**
 * $Id: IStaticDataDao.java,v 1.0 2018/3/12 10:31 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.dao;/**
 * Created by pan on 2018/3/12.
 */


import com.basic.entity.StaticDataEntity;
import com.test.common.persistence.annotation.Dao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: IStaticDataDao.java,v 1.1 2018/3/12 10:31 pan Exp $
 * Created on 2018/3/12 10:31
 */
@Repository
@Dao(StaticDataEntity.class)
public interface IStaticDataDao {

    public List<StaticDataEntity> getStaticData(Map paramMap)throws Exception;
}
