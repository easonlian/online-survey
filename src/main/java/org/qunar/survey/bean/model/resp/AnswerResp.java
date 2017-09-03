/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.resp;

import lombok.Data;

/**
 * Author: jianyu.lin
 * Date: 2017/9/3 Time: 下午10:26
 */
@Data
public class AnswerResp {

    private Integer id;                 //  主键id
    private Integer questionnaireId;    //  问卷id
    private String user;                //  受访人
    private String tel;                 //  受访人tel

    private String date;
}
