/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

/**
 * 题目类型
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午1:27
 */
@SuppressWarnings("unused")
public enum QuestionType {

    FILL_IN_THE_BLACKS(0, "填空"),
    CHOICE(1, "单选"),
    MULTI_CHOICE(2, "多选"),
    MULTI_FILL_IN_THE_BLACKS(3, "多填空")
    ;
    public final int code;
    private final String desc;

    QuestionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    public static QuestionType codeOf(int code) {
        for (QuestionType type : QuestionType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }

    public static QuestionType codeOf(String name) {
        for (QuestionType type : QuestionType.values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
