/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

/**
 * Author: jianyu.lin
 * Date: 2017/9/5 Time: 下午3:38
 */
public abstract class BaseController {

    @Autowired
    private QuestionnaireService questionnaireService;

    /**
     * 展示问卷填写页面
     * @param questionnaireId 问卷id
     * @param map model map
     */
    void getQuestionnaireDetail(int questionnaireId, ModelMap map) {
        QuestionnaireResp questionnaire = questionnaireService.getQuestionnaire(questionnaireId);
        map.put("questionnaire", questionnaire);
    }
}
