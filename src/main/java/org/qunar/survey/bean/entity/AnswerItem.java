/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Author: jianyu.lin
 * Date: 2017/9/3 Time: 下午4:50
 */
@Data
public class AnswerItem {

    private Integer questionId;
    private Integer questionType;

    private String text;                        //  填空题答案
    private Integer choiceId;                   //  单选题答案
    private List<Integer> choiceIds;            //  多选题答案
    private Map<Integer, String> multiText;     //  多重填空题
}
