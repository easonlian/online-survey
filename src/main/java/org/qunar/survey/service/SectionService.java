/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.service;

import com.google.common.base.Function;
import org.qunar.survey.bean.entity.Section;
import org.qunar.survey.bean.model.resp.SectionResp;
import org.qunar.survey.dao.QuestionDao;
import org.qunar.survey.dao.SectionDao;
import org.qunar.survey.util.Collections;
import org.qunar.survey.util.Dates;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:27
 */
@Service
public class SectionService {

    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private QuestionService questionService;

    public List<SectionResp> getSections(int questionnaireId) {
        List<Section> sections = sectionDao.findByQuestionnaireId(questionnaireId);
        return Collections.transform(sections, new Function<Section, SectionResp>() {
            @Override
            public SectionResp apply(Section section) {
                SectionResp sectionResp = new SectionResp();
                BeanUtils.copyProperties(section, sectionResp);
                sectionResp.setLastUpdate(Dates.formatTime(section.getUpdateTime()));
                sectionResp.setQuestions(questionService.getQuestions(section.getId()));
                return sectionResp;
            }
        });
    }
}
