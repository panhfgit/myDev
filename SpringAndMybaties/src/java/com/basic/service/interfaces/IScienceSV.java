/**
 * $Id: IScienceSV.java,v 1.0 2018/3/8 14:44 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.service.interfaces;/**
 * Created by pan on 2018/3/8.
 */

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: IScienceSV.java,v 1.1 2018/3/8 14:44 pan Exp $
 * Created on 2018/3/8 14:44
 */

public interface IScienceSV {
    public List getScience(Map paramMap) throws Exception;

    public Integer deleteSciencebyId(Integer scienceId) throws Exception;

    public Integer getScienceNewId() throws Exception;
}
