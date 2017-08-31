/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

/**
 * 题目类型
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午1:27
 */
public enum QuestionType {

    FILL_IN_THE_BLACKS(0),
    CHOICE(1),
    MULTI_CHOICE(2),
    ;
    public final int code;

    QuestionType(int code) {
        this.code = code;
    }

    public static QuestionType codeOf(int code) {
        for (QuestionType type : QuestionType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
