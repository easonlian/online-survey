/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 问卷
 */
@Data
public class Questionnaire {

    private Integer id;                 //  主键

    private String title;               //  标题
    private int year;                   //  年度
    private int quarter;                //  季度
    private String serialNum;           //  表号
    private String office;              //  制表单位
    private String docNum;              //  文号
    private String desc;                //  描述json

    private Date createTime;
    private Date updateTime;
}
