/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.qunar.survey.bean.entity.Answer;
import org.qunar.survey.bean.entity.AnswerItem;
import org.qunar.survey.bean.entity.QuestionType;
import org.qunar.survey.bean.model.req.CommitQuestionnaireReq;
import org.qunar.survey.bean.model.resp.ChoiceItemResp;
import org.qunar.survey.bean.model.resp.QuestionResp;
import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.bean.model.resp.SectionResp;
import org.qunar.survey.dao.AnswerDao;
import org.qunar.survey.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 问卷结果service
 * 
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:57
 */
@Service
public class AnswerService {
    
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionnaireService questionnaireService;

    /**
     * 提交问卷
     * @param req req
     * @param answerData data
     * @return answer id
     */
    public Integer commitAnswer(CommitQuestionnaireReq req, Map<String, Object> answerData) {
        Answer answer = new Answer();
        answer.setQuestionnaireId(req.getQuestionnaireId());
        answer.setUser(req.getUser());
        answer.setTel(req.getTel());

        for (String dataKey : answerData.keySet()) {
            String[] parts = dataKey.split("-");
            int questionId = Integer.valueOf(parts[0]);
            QuestionType questionType = QuestionType.codeOf(parts[1]);
            checkNotNull(questionType);
            AnswerItem answerItem = new AnswerItem();
            answerItem.setQuestionId(questionId);
            answerItem.setQuestionType(questionType.getCode());
            switch (questionType) {
                case FILL_IN_THE_BLACKS:
                    String text = (String) answerData.get(dataKey);
                    answerItem.setText(text);
                    break;
                case CHOICE:
                    Integer choiceId = Integer.valueOf(answerData.get(dataKey).toString());
                    answerItem.setChoiceId(choiceId);
                    break;
                case MULTI_CHOICE:
                    @SuppressWarnings("unchecked")
                    List<String> choiceIdStrList = (List<String>) answerData.get(dataKey);
                    List<Integer> choiceIds = Lists.newArrayList();
                    for (String idStr : choiceIdStrList) {
                        choiceIds.add(Integer.valueOf(idStr));
                    }
                    answerItem.setChoiceIds(choiceIds);
                    break;
                case MULTI_FILL_IN_THE_BLACKS:
                    answerItem.setMultiText(Maps.<Integer, String>newHashMap());
                    @SuppressWarnings("unchecked")
                    Map<String, String> textMap = (Map<String, String>) answerData.get(dataKey);
                    for (String itemId : textMap.keySet()) {
                        Integer textId = Integer.valueOf(itemId);
                        answerItem.getMultiText().put(textId, textMap.get(itemId));
                    }
                    System.out.println(answerItem);
                    break;
            }
            answer.getData().getAnswerItems().add(answerItem);
        }
        answerDao.insertOne(answer);
        return answer.getId();
    }

    /**
     * 查询问卷答案
     * @param answerId 问卷结果id
     * @return 已填问卷
     */
    public QuestionnaireResp getQuestionnaireRespWithAnswers(int answerId) {
        Answer answer = answerDao.findById(answerId);
        checkNotNull(answer);
        Map<Integer, AnswerItem> answerItemMap = buildAnswerItemMap(answer);

        QuestionnaireResp questionnaireResp = questionnaireService.getQuestionnaire(answer.getQuestionnaireId());
        for (SectionResp sectionResp : questionnaireResp.getSections()) {
            for (QuestionResp questionResp : sectionResp.getQuestions()) {
                questionResp.setAnswerItem(answerItemMap.get(questionResp.getId()));
                if (CollectionUtils.isEmpty(questionResp.getChoiceItems())) continue;
                for (ChoiceItemResp choiceItemResp : questionResp.getChoiceItems()) {
                    if (questionResp.getAnswerItem() == null) continue;
                    if (questionResp.getType() == QuestionType.CHOICE) {
                        choiceItemResp.setActive(questionResp.getAnswerItem().getChoiceId().equals(choiceItemResp.getId()));
                    } else if (questionResp.getType() == QuestionType.MULTI_CHOICE) {
                        choiceItemResp.setActive(questionResp.getAnswerItem().getChoiceIds().contains(choiceItemResp.getId()));
                    } else if (questionResp.getType() == QuestionType.MULTI_FILL_IN_THE_BLACKS) {
                        choiceItemResp.setText(questionResp.getAnswerItem().getMultiText().get(choiceItemResp.getId()));
                    }
                }
            }
        }
        questionnaireResp.setUser(answer.getUser());
        questionnaireResp.setTel(answer.getTel());
        questionnaireResp.setDate(Dates.formatTime(answer.getCreateTime()));
        return questionnaireResp;
    }

    /**
     * 问题答案map
     * @param answer 问卷答案
     * @return 题目答案map
     */
    private Map<Integer, AnswerItem> buildAnswerItemMap(Answer answer) {
        Map<Integer, AnswerItem> answerItemMap = Maps.newHashMap();
        for (AnswerItem item : answer.getData().getAnswerItems()) {
            answerItemMap.put(item.getQuestionId(), item);
        }
        return answerItemMap;
    }
}
