/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.qunar.survey.bean.entity.Questionnaire;
import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.bean.model.resp.SectionResp;
import org.qunar.survey.dao.QuestionnaireDao;
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
     * 获取问卷完整数据
     * @param questionnaireId 问卷id
     * @return 问卷响应
     */
    public QuestionnaireResp getQuestionnaire(int questionnaireId) {
        Questionnaire questionnaire = questionnaireDao.findById(questionnaireId);
        QuestionnaireResp questionnaireResp = new QuestionnaireResp();
        BeanUtils.copyProperties(questionnaire, questionnaireResp);
        questionnaireResp.setDescList(Jsons.fromJson(questionnaire.getDesc(), new TypeReference<List<String>>() {}));
        questionnaireResp.setLastUpdate(Dates.formatTime(questionnaire.getUpdateTime()));
        questionnaireResp.setSections(sectionService.getSections(questionnaireId));
        return questionnaireResp;
    }
}
