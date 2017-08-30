/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Author: jianyu.lin
 * Date: 2017/8/29 Time: 下午7:39
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    //  http://www.h2database.com/html/tutorial.html#tutorial_starting_h2_console
    //  http://blog.csdn.net/guicaizhou/article/details/51862423

}
