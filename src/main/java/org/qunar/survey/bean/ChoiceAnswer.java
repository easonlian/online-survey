/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean;

import lombok.Data;

/**
 * 问卷选择题备选 base class
 */
@Data
public class ChoiceAnswer {

    private int sequence;           //  题目序号
    private String desc;            //  题目描述
}
