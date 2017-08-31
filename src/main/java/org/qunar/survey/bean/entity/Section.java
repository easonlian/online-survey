/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

import lombok.Data;

import java.util.Date;

/**
 * 问卷分块
 */
@Data
public class Section {

    private Integer id;                     //  主键
    private Integer questionnaireId;        //  问卷id
    private Integer sequence;               //  序号
    private String name;                    //  名称

    private Date createTime;
    private Date updateTime;
}
