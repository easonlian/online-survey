package org.qunar.survey.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qunar.survey.bean.entity.Questionnaire;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 问卷dao
 */
@Repository @Mapper
public interface QuestionnaireDao {

    int insertOne(Questionnaire bean);

    Questionnaire findById(@Param("id") int id);

    List<Questionnaire> findByCondition(Questionnaire bean);
}
