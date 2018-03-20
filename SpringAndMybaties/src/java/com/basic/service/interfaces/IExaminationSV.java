/**
 * $Id: IExaminationSV.java,v 1.0 2018/3/13 22:14 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.service.interfaces;/**
 * Created by pan on 2018/3/13.
 */

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: IExaminationSV.java,v 1.1 2018/3/13 22:14 pan Exp $
 * Created on 2018/3/13 22:14
 */

public interface IExaminationSV {
    public List getExamination(Map paraMap)throws Exception;

    public Map loadExamination(Map paraMap)throws Exception;

    public List queryExamination(Map paraMap)throws Exception;

    public List queryExamSubjectRel(Map paraMap)throws Exception;

    public List querySubject(Map paraMap)throws Exception;

    public List queryChoice(Map paraMap)throws Exception;

    public Map saveAnswer(Map paraMap)throws Exception;

    public Integer getStuAnswerNewId()throws Exception;

    public Map autoGenerateExam(List paraList)throws Exception;
}
