/**
 * $Id: IUserSV.java,v 1.0 2018/3/6 14:53 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.service.interfaces;/**
 * Created by pan on 2018/3/6.
 */

import java.util.Map;

/**
 * @author pan
 * @version $Id: IUserSV.java,v 1.1 2018/3/6 14:53 pan Exp $
 * Created on 2018/3/6 14:53
 */

public interface IUserSV {

    public Map checkLogin(Map paraMap) throws Exception;

}
