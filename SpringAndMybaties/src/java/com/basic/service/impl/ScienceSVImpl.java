/**
 * $Id: ScienceSVImpl.java,v 1.0 2018/3/8 14:46 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.basic.service.impl;/**
 * Created by pan on 2018/3/8.
 */

import com.basic.dao.IScienceDao;
import com.basic.entity.ScienceEntity;
import com.basic.service.interfaces.IScienceSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: ScienceSVImpl.java,v 1.1 2018/3/8 14:46 pan Exp $
 * Created on 2018/3/8 14:46
 */
@Service
public class ScienceSVImpl implements IScienceSV{

    @Autowired
    private IScienceDao scienceDao;

    @Override
    public List getScience(Map paramMap) throws Exception {

        List<ScienceEntity> scienceEntities = scienceDao.getScience(paramMap);

        return scienceDao.getScience(paramMap);
    }

    @Override
    public Integer deleteSciencebyId(Integer scienceId) throws Exception {
        scienceDao.deleteSciencebyId(scienceId);
        return scienceId;
    }

    @Override
    public Integer getScienceNewId() throws Exception {
        Map result = scienceDao.getScienceNewId();
        return Integer.parseInt(result.get("Auto_increment").toString());
    }
}
