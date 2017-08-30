/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean;

import lombok.Data;

import java.util.List;

/**
 * 问卷
 */
@Data
public class Questionnaire {

    /* header */
    private String title;               //  标题
    private int year;                   //  年度
    private int quarter;                //  季度
    private String serialNum;           //  表号
    private String office;              //  制表单位
    private String docNum;              //  文号

    /* body */
    private List<Section> sections;     //  问卷分块

    /* tail */
    private String name;                //  填表人姓名
    private String tel;                 //  填表人电话
    private String date;                //  调表日期
    private List<String> explains;      //  描述
}
