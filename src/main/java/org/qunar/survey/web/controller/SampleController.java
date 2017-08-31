/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.qunar.survey.bean.entity.ChoiceItem;
import org.qunar.survey.bean.entity.Question;
import org.qunar.survey.bean.entity.QuestionType;
import org.qunar.survey.bean.entity.Questionnaire;
import org.qunar.survey.bean.entity.Section;
import org.qunar.survey.bean.model.resp.ChoiceItemResp;
import org.qunar.survey.bean.model.resp.JsonResp;
import org.qunar.survey.bean.model.resp.QuestionResp;
import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.bean.model.resp.SectionResp;
import org.qunar.survey.dao.ChoiceItemDao;
import org.qunar.survey.dao.QuestionDao;
import org.qunar.survey.dao.QuestionnaireDao;
import org.qunar.survey.dao.SectionDao;
import org.qunar.survey.util.Jsons;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * Author: jianyu.lin
 * Date: 2017/8/29 Time: 下午6:27
 */
@Controller
public class SampleController {

    @Autowired
    private QuestionnaireDao questionnaireDao;
    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private ChoiceItemDao choiceItemDao;

    @RequestMapping("/query")
    @ResponseBody
    public JsonResp<QuestionnaireResp> query() {
        Questionnaire questionnaire = questionnaireDao.findById(1);
        List<Section> sections = sectionDao.findByQuestionnaireId(questionnaire.getId());
        return JsonResp.success(buildQuestionnaireResp(questionnaire, sections));
    }

    private QuestionnaireResp buildQuestionnaireResp(Questionnaire questionnaire, List<Section> sections) {
        QuestionnaireResp resp = new QuestionnaireResp();
        BeanUtils.copyProperties(questionnaire, resp);
        resp.setDescList(Jsons.fromJson(questionnaire.getDesc(), new TypeReference<List<String>>() {}));
        resp.setLastUpdate(formatUpdateTime(questionnaire.getUpdateTime()));

        if (CollectionUtils.isEmpty(sections)) {
            return resp;
        }
        for (Section section : sections) {
            SectionResp sectionResp = new SectionResp();
            BeanUtils.copyProperties(section, sectionResp);
            resp.getSections().add(sectionResp);
            sectionResp.setLastUpdate(formatUpdateTime(section.getUpdateTime()));

            List<Question> questions = questionDao.findBySectionId(section.getId());
            if (CollectionUtils.isEmpty(questions)) {
                continue;
            }
            for (Question question : questions) {
                QuestionResp questionResp = new QuestionResp();
                BeanUtils.copyProperties(question, questionResp);
                questionResp.setLastUpdate(formatUpdateTime(question.getUpdateTime()));

                if (question.getType() == QuestionType.FILL_IN_THE_BLACKS) {
                    sectionResp.getQuestions().add(questionResp);
                } else {
                    List<ChoiceItem> choiceItems = choiceItemDao.findByQuestionId(question.getId());
                    if (CollectionUtils.isEmpty(choiceItems)) {
                        continue;
                    }
                    for (ChoiceItem choiceItem : choiceItems) {
                        ChoiceItemResp choiceItemResp = new ChoiceItemResp();
                        BeanUtils.copyProperties(choiceItem, choiceItemResp);
                        questionResp.getChoiceItems().add(choiceItemResp);
                        choiceItemResp.setLastUpdate(formatUpdateTime(choiceItem.getUpdateTime()));
                    }
                    sectionResp.getQuestions().add(questionResp);
                }
            }
        }
        return resp;
    }

    private String formatUpdateTime(Date updateTime) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(updateTime);
    }

    @RequestMapping("/questionnaire/list")
    public String questionnaireList(ModelMap map) {
        map.put("list", transform(questionnaireDao.findByCondition(null)));
        return "list";
    }

    @RequestMapping("/questionnaire/detail")
    public String detail(ModelMap map) {
        Questionnaire questionnaire = questionnaireDao.findById(1);
        List<Section> sections = sectionDao.findByQuestionnaireId(questionnaire.getId());
        map.put("detail", buildQuestionnaireResp(questionnaire, sections));
        return "detail";
    }

    private List<QuestionnaireResp> transform(List<Questionnaire> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        return Lists.transform(entities, new Function<Questionnaire, QuestionnaireResp>() {
            @Override
            public QuestionnaireResp apply(Questionnaire questionnaire) {
                return transform(questionnaire);
            }
        });
    }

    private QuestionnaireResp transform(Questionnaire entity) {
        QuestionnaireResp resp = new QuestionnaireResp();
        BeanUtils.copyProperties(entity, resp);
        resp.setLastUpdate(formatUpdateTime(entity.getUpdateTime()));
        return resp;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() {
        Questionnaire bean = new Questionnaire();
        bean.setTitle("温江花木企业景气调查问卷");
        bean.setYear(2017);
        bean.setQuarter(3);
        bean.setSerialNum("C5296-2表");
        bean.setOffice("国家统计局温江调查队");
        bean.setDocNum("川调函[2017]15号");
        bean.setDesc("[\"本表由花木企业的主要负责人接受访问调查。\", \"填报时间为2017年3、6、9、12月15-25日。\"]");
        int i = questionnaireDao.insertOne(bean);
        return i +  " " + bean;
    }
}
