/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

import lombok.Data;

import java.util.Date;

/**
 * 问卷选择题备选 base class
 */
@Data
public class ChoiceAnswer {

    private Integer id;             //  主键
    private Integer questionId;     //  题目id
    private Integer sequence;       //  题目序号
    private String desc;            //  题目描述

    private Date createTime;
    private Date updateTime;
}
