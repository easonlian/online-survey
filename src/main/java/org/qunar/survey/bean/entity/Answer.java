/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

import lombok.Data;

import java.util.Date;

/**
 * Author: jianyu.lin
 * Date: 2017/9/3 Time: 下午4:00
 */
@Data
public class Answer {

    private Integer id;                 //  主键id
    private Integer questionnaireId;    //  问卷id
    private String user;                //  受访人
    private String tel;                 //  受访人tel

    private AnswerItems data = new AnswerItems();

    private Date createTime;
    private Date updateTime;
}
