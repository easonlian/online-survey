/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qunar.survey.bean.entity.ChoiceItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * choice item dao
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午6:44
 */
@Repository @Mapper
public interface ChoiceItemDao {

    List<ChoiceItem> findByQuestionId(@Param("questionId") int questionId);
}
