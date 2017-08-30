/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

import lombok.Data;

import java.util.Date;

/**
 * 问卷题目 base class
 */
@Data
public class Question {

    private Integer id;             //  主键
    private Integer sectionId;      //  段落id
    private Integer sequence;       //  题目序号
    private String desc;            //  题目描述
    private Integer type;           //  题型

    private Date createTime;
    private Date updateTime;
}
