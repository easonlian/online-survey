/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import org.qunar.survey.bean.model.req.CommitQuestionnaireReq;
import org.qunar.survey.bean.model.resp.JsonResp;
import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.service.AnswerService;
import org.qunar.survey.service.QuestionnaireService;
import org.qunar.survey.util.Collections;
import org.qunar.survey.util.Jsons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 用户测controller
 * <p>
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:04
 */
@Controller
public class UserController {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private AnswerService answerService;

    /**
     * 展示问卷填写页面
     *
     * @param questionnaireId 问卷id
     * @param map             model map
     * @return detail page
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/questionnaire/detail-{questionnaireId}")
    public String questionnaireDetail(@PathVariable int questionnaireId, ModelMap map) {
        QuestionnaireResp questionnaire = questionnaireService.getQuestionnaire(questionnaireId);
        map.put("questionnaire", questionnaire);
        return "detail";
    }

    /**
     * 提交问卷结果
     *
     * @param req    请求参数
     * @param result check result
     * @return resp
     * @throws Exception Any
     */
    @RequestMapping(value = "/questionnaire/commit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResp commitQuestionnaire(@Valid CommitQuestionnaireReq req, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            List<String> errors = Collections.transform(result.getAllErrors(), new Function<ObjectError, String>() {
                @Override
                public String apply(ObjectError error) {
                    return error.getDefaultMessage();
                }
            });
            return JsonResp.error(Joiner.on('，').join(errors));
        }
        Map<String, Object> answerData = parseAnswerData(req, result);
        if (answerData == null) {
            return JsonResp.error("提交失败，请检查问卷填写项！");
        }
        Integer answerId = answerService.commitAnswer(req, answerData);
        return answerId != null ? JsonResp.success(answerId, "提交成功，感谢您配合调查！") : JsonResp.error("提交失败！");
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/answer/detail-{answerId}")
    public String answerDetail(@PathVariable int answerId, ModelMap map) {
        map.put("questionnaire", answerService.getQuestionnaireRespWithAnswers(answerId));
        return "answer_detail";
    }

    /**
     * 校验请求参数
     *
     * @param req    req
     * @param result valid result
     * @return answer data
     */
    private Map<String, Object> parseAnswerData(CommitQuestionnaireReq req, BindingResult result) {
        if (result.hasErrors()) return null;
        return Jsons.fromJson(req.getAnswerData(), new TypeReference<Map<String, Object>>() {
        });
    }
}
