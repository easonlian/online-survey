/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean;

import lombok.Data;

/**
 * 问卷题目 base class
 */
@Data
public abstract class Question {

    private int sequence;           //  题目序号
    private String desc;            //  题目描述
}
