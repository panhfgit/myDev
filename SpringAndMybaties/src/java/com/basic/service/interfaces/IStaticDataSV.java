/**
 * $Id: IStaticDataSV.java,v 1.0 2018/3/12 10:28 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.service.interfaces;/**
 * Created by pan on 2018/3/12.
 */

import com.basic.entity.StaticDataEntity;

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: IStaticDataSV.java,v 1.1 2018/3/12 10:28 pan Exp $
 * Created on 2018/3/12 10:28
 */

public interface IStaticDataSV {
    public List<StaticDataEntity> getStaticData(Map paramMap)throws Exception;
}
