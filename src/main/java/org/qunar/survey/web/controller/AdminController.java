/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import org.qunar.survey.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 管理测controller
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午1:48
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private QuestionnaireService questionnaireService;

    /**
     * 问卷列表
     * @param map model map
     * @return jsp
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/questionnaire/list")
    public String questionnaireList(ModelMap map) {
        map.put("list", questionnaireService.getQuestionnaires());
        return "list";
    }

    /**
     * 数据文件下载
     * @param req req
     * @param resp resp
     * @throws Exception Any
     */
    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String file = "survey." + req.getParameter("mid") + ".db";
        String filePath = req.getParameter("dir") + "/" + file;

        File fileOjb = new File(filePath);
        if (!fileOjb.exists()) {
            resp.getWriter().write("no such file");
        }

        String fileName = URLEncoder.encode(file, "UTF-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        InputStream inputStream = new FileInputStream(filePath);
        OutputStream outputStream = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int i;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
