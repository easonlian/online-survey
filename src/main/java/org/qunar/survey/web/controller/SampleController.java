/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import org.qunar.survey.bean.entity.Questionnaire;
import org.qunar.survey.dao.QuestionnaireDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Author: jianyu.lin
 * Date: 2017/8/29 Time: 下午6:27
 */
@Controller
public class SampleController {

    @Autowired
    private QuestionnaireDao questionnaireDao;

    @RequestMapping("/query")
    @ResponseBody
    public Questionnaire query() {
        return questionnaireDao.findById(1);
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

    @RequestMapping("/temp")
    @ResponseBody
    public String temp() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:../data/survey", "sa", "sa");
        PreparedStatement pstm = conn.prepareStatement("select * from test");
        ResultSet resultSet = pstm.executeQuery();
        String result = resultSet.toString();
        conn.close();
        return result;
    }
}
