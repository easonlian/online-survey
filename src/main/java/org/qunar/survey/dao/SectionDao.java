/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qunar.survey.bean.entity.Section;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 段落dao
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 上午11:11
 */
@Repository @Mapper
public interface SectionDao {

    List<Section> findByQuestionnaireId(@Param("questionnaireId") int questionnaireId);
}
