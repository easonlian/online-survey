/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Function;
import org.qunar.survey.bean.entity.Questionnaire;
import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.dao.QuestionnaireDao;
import org.qunar.survey.util.Collections;
import org.qunar.survey.util.Dates;
import org.qunar.survey.util.Jsons;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问卷调查service
 *
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:05
 */
@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireDao questionnaireDao;
    @Autowired
    private SectionService sectionService;

    /**
     * 获取问卷列表
     * @return 问卷列表
     */
    public List<QuestionnaireResp> getQuestionnaires() {
        List<Questionnaire> questionnaires = questionnaireDao.findByCondition(null);
        return Collections.transform(questionnaires, new Function<Questionnaire, QuestionnaireResp>() {
            @Override
            public QuestionnaireResp apply(Questionnaire questionnaire) {
                return getQuestionnaire(questionnaire.getId(), false);
            }
        });
    }

    /**
     * 获取问卷完整数据
     * @param questionnaireId 问卷id
     * @return 问卷响应
     */
    public QuestionnaireResp getQuestionnaire(int questionnaireId) {
        return getQuestionnaire(questionnaireId, true);
    }

    /**
     * 获取问卷完整数据
     * @param questionnaireId 问卷id
     * @return 问卷响应
     */
    private QuestionnaireResp getQuestionnaire(int questionnaireId, boolean hasSection) {
        Questionnaire questionnaire = questionnaireDao.findById(questionnaireId);
        QuestionnaireResp questionnaireResp = new QuestionnaireResp();
        BeanUtils.copyProperties(questionnaire, questionnaireResp);
        questionnaireResp.setDescList(Jsons.fromJson(questionnaire.getDesc(), new TypeReference<List<String>>() {}));
        questionnaireResp.setLastUpdate(Dates.formatTime(questionnaire.getUpdateTime()));
        if (hasSection) questionnaireResp.setSections(sectionService.getSections(questionnaireId));
        return questionnaireResp;
    }
}
