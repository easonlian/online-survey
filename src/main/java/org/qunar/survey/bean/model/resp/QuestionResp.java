/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.resp;

import com.google.common.collect.Lists;
import lombok.Data;
import org.qunar.survey.bean.entity.AnswerItem;
import org.qunar.survey.bean.entity.QuestionType;

import java.util.List;

/**
 * 题目响应
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午5:50
 */
@Data
public class QuestionResp {

    private Integer id;             //  主键
    private Integer sectionId;      //  段落id
    private Integer sequence;       //  题目序号
    private String desc;            //  题目描述
    private QuestionType type;      //  题型

    private List<ChoiceItemResp> choiceItems = Lists.newArrayList();

    private String lastUpdate;          //  最后更新时间

    private AnswerItem answerItem;      //  答案
}
