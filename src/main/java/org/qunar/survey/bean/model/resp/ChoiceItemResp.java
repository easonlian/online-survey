/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.resp;

import lombok.Data;

/**
 * choice item resp
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午6:49
 */
@Data
public class ChoiceItemResp {

    private Integer id;             //  主键
    private Integer questionId;     //  题目id
    private Integer sequence;       //  题目序号
    private String desc;            //  题目描述
    private String lastUpdate;      //  最后更新时间

    private Boolean active;         //  是否选中
}
