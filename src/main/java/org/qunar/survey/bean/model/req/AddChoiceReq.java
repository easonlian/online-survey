/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Author: jianyu.lin
 * Date: 2017/9/2 Time: 上午12:41
 */
@Data
public class AddChoiceReq {

    @NotNull
    private Integer questionId;
    @NotEmpty
    private String desc;
}
