/**
 * $Id: IExaminationDao.java,v 1.0 2018/3/13 22:19 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */

package com.basic.dao;/**
 * Created by pan on 2018/3/13.
 */

import com.basic.entity.*;
import com.test.common.persistence.annotation.Dao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @version $Id: IExaminationDao.java,v 1.1 2018/3/13 22:19 pan Exp $
 * Created on 2018/3/13 22:19
 */
@Repository
@Dao(ExaminationEntity.class)
public interface IExaminationDao {
    public List<ExaminationEntity> getExamination(Map paraMap)throws Exception;

    public List<Map> querySubject(Map paraMap)throws Exception;

    public List<Map> queryChoice(Map paraMap)throws Exception;

    public List<Map> queryExamination(Map paraMap)throws Exception;

    public List<Map> queryExamSubjectRel(Map paraMap)throws Exception;

    public Map getNewId(String tableName)throws Exception;

    public void saveStuAnswer(StudentAnswerEntity studentAnswerEntity)throws Exception;
    public void saveExamination(ExaminationEntity examinationEntity)throws Exception;
    public void saveExaminationSubjectRel(ExaminationSubjectRelEntity examinationSubjectRelEntity)throws Exception;
}
