/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.service;

import com.google.common.base.Function;
import org.qunar.survey.bean.entity.Question;
import org.qunar.survey.bean.model.resp.QuestionResp;
import org.qunar.survey.dao.QuestionDao;
import org.qunar.survey.util.Collections;
import org.qunar.survey.util.Dates;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:24
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private ChoiceItemService choiceItemService;

    public List<QuestionResp> getQuestions(int sectionId) {
        List<Question> questions = questionDao.findBySectionId(sectionId);
        return Collections.transform(questions, new Function<Question, QuestionResp>() {
            @Override
            public QuestionResp apply(Question question) {
                QuestionResp questionResp = new QuestionResp();
                BeanUtils.copyProperties(question, questionResp);
                questionResp.setLastUpdate(Dates.formatTime(question.getUpdateTime()));
                questionResp.setChoiceItems(choiceItemService.getChoiceItems(question.getId()));
                return questionResp;
            }
        });
    }
}
