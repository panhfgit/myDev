/**
 * $Id: StaticDataSVImpl.java,v 1.0 2018/3/12 10:29 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.service.impl;/**
 * Created by pan on 2018/3/12.
 */

import com.basic.dao.IStaticDataDao;
import com.basic.entity.StaticDataEntity;
import com.basic.service.interfaces.IStaticDataSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: StaticDataSVImpl.java,v 1.1 2018/3/12 10:29 pan Exp $
 * Created on 2018/3/12 10:29
 */
@Service
public class StaticDataSVImpl implements IStaticDataSV{

    @Autowired
    private IStaticDataDao staticDataDao;

    @Override
    public List<StaticDataEntity> getStaticData(Map paramMap) throws Exception {
        return staticDataDao.getStaticData(paramMap);
    }
}
