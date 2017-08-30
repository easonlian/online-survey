/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
