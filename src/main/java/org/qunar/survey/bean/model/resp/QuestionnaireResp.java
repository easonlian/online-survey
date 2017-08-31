/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.resp;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 问卷查询resp
 *
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 上午11:16
 */
@Data
public class QuestionnaireResp {

    private Integer id;                 //  主键

    private String title;               //  标题
    private int year;                   //  年度
    private int quarter;                //  季度
    private String serialNum;           //  表号
    private String office;              //  制表单位
    private String docNum;              //  文号

    private List<SectionResp> sections = Lists.newArrayList();

    private List<String> descList;      //  描述

    private String lastUpdate;          //  最后更新时间
}
