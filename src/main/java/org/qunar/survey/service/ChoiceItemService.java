/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.service;

import com.google.common.base.Function;
import org.qunar.survey.bean.entity.AnswerItem;
import org.qunar.survey.bean.entity.ChoiceItem;
import org.qunar.survey.bean.entity.QuestionType;
import org.qunar.survey.bean.model.resp.ChoiceItemResp;
import org.qunar.survey.dao.ChoiceItemDao;
import org.qunar.survey.util.Collections;
import org.qunar.survey.util.Dates;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:10
 */
@Service
public class ChoiceItemService {

    @Autowired
    private ChoiceItemDao choiceItemDao;

    /**
     * 查询题目选项
     * @param questionId 题目id
     * @return 单题选项列表
     */
    @SuppressWarnings("WeakerAccess")
    public List<ChoiceItemResp> getChoiceItems(int questionId) {
        List<ChoiceItem> choiceItems = choiceItemDao.findByQuestionId(questionId);
        return Collections.transform(choiceItems, new Function<ChoiceItem, ChoiceItemResp>() {
            @Override
            public ChoiceItemResp apply(ChoiceItem choiceItem) {
                ChoiceItemResp choiceItemResp = new ChoiceItemResp();
                BeanUtils.copyProperties(choiceItem, choiceItemResp);
                choiceItemResp.setLastUpdate(Dates.formatTime(choiceItem.getUpdateTime()));
                return choiceItemResp;
            }
        });
    }
}
