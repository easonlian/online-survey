/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Author: jianyu.lin
 * Date: 2017/9/3 Time: 下午1:10
 */
@Data
public class CommitQuestionnaireReq {

    @NotNull
    private Integer questionnaireId;
    @NotEmpty(message = "请填写受访人姓名")
    private String user;
    @NotEmpty(message = "请填写受访人电话")
    private String tel;
    @NotEmpty
    private String answerData;
}
