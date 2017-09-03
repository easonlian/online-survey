/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Author: jianyu.lin
 * Date: 2017/9/3 Time: 下午4:31
 */
@Data
public class AnswerItems {

    private List<AnswerItem> answerItems = Lists.newArrayList();
}
