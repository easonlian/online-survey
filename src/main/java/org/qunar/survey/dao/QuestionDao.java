/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.qunar.survey.bean.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目dao
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午2:22
 */
@Repository @Mapper
public interface QuestionDao {

    int insertOne(Question bean);

    List<Question> findBySectionId(@Param("sectionId") int sectionId);

    List<Question> findBySectionIds(@Param("sectionIds") List<Integer> sectionIds);
}
