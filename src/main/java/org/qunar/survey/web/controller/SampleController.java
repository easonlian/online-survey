/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.qunar.survey.bean.entity.Answer;
import org.qunar.survey.bean.entity.ChoiceItem;
import org.qunar.survey.bean.entity.Question;
import org.qunar.survey.bean.entity.QuestionType;
import org.qunar.survey.bean.entity.Questionnaire;
import org.qunar.survey.bean.entity.Section;
import org.qunar.survey.bean.model.req.AddChoiceReq;
import org.qunar.survey.bean.model.req.AddQuestionReq;
import org.qunar.survey.bean.model.resp.AnswerResp;
import org.qunar.survey.bean.model.resp.JsonResp;
import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.dao.AnswerDao;
import org.qunar.survey.dao.ChoiceItemDao;
import org.qunar.survey.dao.QuestionDao;
import org.qunar.survey.dao.QuestionnaireDao;
import org.qunar.survey.dao.SectionDao;
import org.qunar.survey.service.QuestionnaireService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
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

    @Autowired
    private QuestionnaireService questionnaireService;

    //  测试接口
    @RequestMapping("/query-{questionnaireId}")
    @ResponseBody
    public JsonResp<QuestionnaireResp> query(@PathVariable int questionnaireId) {
        QuestionnaireResp questionnaireResp = questionnaireService.getQuestionnaire(questionnaireId);
        return JsonResp.success(questionnaireResp);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() {
        Questionnaire bean = new Questionnaire();
        bean.setTitle("xxxxx问卷");
        bean.setYear(2017);
        bean.setQuarter(4);
        bean.setSerialNum("C5xxxx6-2表");
        bean.setOffice("国家统计局温江调查队");
        bean.setDocNum("川调函[2017]25号");
        bean.setDesc("[\"本表由花木企业的主要负责人xxxxx接受访问调查。\", \"填报时间为2017年3、6、9、12月15-25日。\"]");
        int i = questionnaireDao.insertOne(bean);
        return i + " " + bean;
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/question/add.html")
    public String addQuestionPage(ModelMap map, @RequestParam(value = "sectionId", required = false) Integer sectionId) {
        map.put("sections", sectionDao.findByQuestionnaireId(1));
        map.put("lastSectionId", sectionId);
        return "add_question";
    }

    @RequestMapping("/question/add")
    public String addQuestion(@Valid AddQuestionReq req, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/question/add.html?ret=false";
        }
        Question question = new Question();
        question.setSectionId(req.getSectionId());
        question.setDesc(req.getDesc());
        question.setType(QuestionType.codeOf(req.getType()));
        List<Question> questions = questionDao.findBySectionId(req.getSectionId());
        question.setSequence(questions.size() + 1);
        int ret = questionDao.insertOne(question);
        return "redirect:/question/add.html?ret=" + (ret == 1) + "&sectionId=" + req.getSectionId();
    }

    @RequestMapping("/choice/add.html")
    public String addChoicePage(ModelMap map, @RequestParam(value = "questionId", required = false) Integer questionId) {
        List<Section> sections = sectionDao.findByQuestionnaireId(1);
        List<Integer> sectionIds = Lists.transform(sections, new Function<Section, Integer>() {
            @Override
            public Integer apply(Section input) {
                return input.getId();
            }
        });

        List<Question> questions = questionDao.findBySectionIds(sectionIds);
        map.put("questions", questions);
        map.put("lastQuestionId", questionId);
        return "add_choice";
    }

    @RequestMapping("/choice/add")
    public String addChoice(@Valid AddChoiceReq req, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/choice/add.html?ret=false";
        }
        ChoiceItem choice = new ChoiceItem();
        choice.setQuestionId(req.getQuestionId());
        choice.setDesc(req.getDesc());
        List<ChoiceItem> choices = choiceItemDao.findByQuestionId(req.getQuestionId());
        choice.setSequence(choices.size() + 1);
        int ret = choiceItemDao.insertOne(choice);
        return "redirect:/choice/add.html?ret=" + (ret == 1) + "&questionId=" + req.getQuestionId();
    }
}
