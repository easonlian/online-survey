/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

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

    @RequestMapping("/test")
    @ResponseBody
    public String home() {
        return "Hello World! OMG!!!";
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
