/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.web.controller;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.qunar.survey.bean.entity.AnswerItem;
import org.qunar.survey.bean.model.resp.AnswerResp;
import org.qunar.survey.bean.model.resp.ChoiceItemResp;
import org.qunar.survey.bean.model.resp.QuestionResp;
import org.qunar.survey.bean.model.resp.QuestionnaireResp;
import org.qunar.survey.bean.model.resp.SectionResp;
import org.qunar.survey.service.AnswerService;
import org.qunar.survey.service.QuestionnaireService;
import org.qunar.survey.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.List;

import static org.apache.poi.xwpf.usermodel.ParagraphAlignment.CENTER;
import static org.apache.poi.xwpf.usermodel.ParagraphAlignment.LEFT;
import static org.apache.poi.xwpf.usermodel.ParagraphAlignment.RIGHT;
import static org.qunar.survey.bean.entity.QuestionType.CHOICE;
import static org.qunar.survey.bean.entity.QuestionType.MULTI_CHOICE;

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
    @Autowired
    private AnswerService answerService;

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

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/answer/list-{questionnaireId}")
    public String answerList(@PathVariable int questionnaireId, ModelMap map) {
        QuestionnaireResp questionnaireResp = questionnaireService.getQuestionnaire(questionnaireId, false);
        List<AnswerResp> answerRespList = answerService.getQuestionnaireAnswers(questionnaireId);
        map.put("questionnaire", questionnaireResp);
        map.put("answers", answerRespList);
        return "answer_list";
    }

    /**
     * 跳转问卷结果详情
     * @param answerId 回答id
     * @param map model map
     * @return page name
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/answer/detail-{answerId}")
    public String answerDetail(@PathVariable int answerId, ModelMap map) {
        map.put("questionnaire", answerService.getQuestionnaireRespWithAnswers(answerId));
        return "answer_detail";
    }

    /**
     * 数据文件下载
     * @param req req
     * @param resp resp
     * @throws Exception Any
     */
    @RequestMapping("/export-word")
    public void generateWord(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Integer answerId = Integer.valueOf(req.getParameter("aid"));
        QuestionnaireResp questionnaireResp = answerService.getQuestionnaireRespWithAnswers(answerId);

        String name = String.format(
                "问卷_%s_%s_%s.docx", questionnaireResp.getTitle(),
                questionnaireResp.getUser(), Strings.encMobile(questionnaireResp.getTel())
        );
        String fileName = URLEncoder.encode(name, "UTF-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);


        XWPFDocument doc = new XWPFDocument();

        buildParagraph(doc, CENTER, questionnaireResp.getTitle(), 16);
        buildParagraph(doc, RIGHT, String.format("%s 年 %s 季度", questionnaireResp.getYear(), questionnaireResp.getQuarter()), 9);
        buildParagraph(doc, RIGHT, String.format("表号： %s", questionnaireResp.getDocNum()), 9);
        buildParagraph(doc, RIGHT, String.format("制表机关： %s", questionnaireResp.getOffice()), 9);
        buildParagraph(doc, RIGHT, String.format("文号： %s", questionnaireResp.getSerialNum()), 9);

        XWPFTable table = doc.createTable(0, 1);
        CTTblPr tablePr = table.getCTTbl().addNewTblPr();
        CTTblWidth width = tablePr.addNewTblW();
        width.setW(BigInteger.valueOf(8320));
        width.setType(STTblWidth.DXA);
        CTTblBorders borders = tablePr.addNewTblBorders();
//        CTBorder ihBorder = borders.addNewInsideH();
//        ihBorder.setVal(STBorder.Enum.forString("none"));
//        CTBorder ivBorder = borders.addNewInsideV();
//        ivBorder.setVal(STBorder.Enum.forString("none"));
//        CTBorder tBorder = borders.addNewTop();
//        tBorder.setVal(STBorder.Enum.forString("none"));
//        CTBorder bBorder = borders.addNewBottom();
//        bBorder.setVal(STBorder.Enum.forString("none"));
        CTBorder lBorder=borders.addNewLeft();
        lBorder.setVal(STBorder.Enum.forString("none"));
        CTBorder rBorder=borders.addNewRight();
        rBorder.setVal(STBorder.Enum.forString("none"));

        table.removeRow(0);
        for (SectionResp sectionResp : questionnaireResp.getSections()) {
            if (table.getNumberOfRows() > 0) {
                buildParagraph(table.createRow(), LEFT, "", 9);
            }
            buildParagraph(table.createRow(), CENTER, sectionResp.getName(), 10);
            int questionLen = sectionResp.getQuestions().size();
            for (int i = 0; i < questionLen; i++) {
                QuestionResp questionResp = sectionResp.getQuestions().get(i);
                AnswerItem answerItem = questionResp.getAnswerItem();
                String questionDesc = (i + 1) + ". " + questionResp.getDesc();
                switch (questionResp.getType()) {
                    case FILL_IN_THE_BLACKS:
                        String text = questionResp.getAnswerItem().getText();
                        buildParagraph(table.createRow(), LEFT, questionDesc, text);
                        break;
                    case CHOICE:
                    case MULTI_CHOICE:
                        buildParagraph(table.createRow(), LEFT, questionDesc, 9);

                        List<ChoiceItemResp> choiceItems = questionResp.getChoiceItems();
                        List<Pair<Boolean, String>> choicePairs = Lists.newArrayList();
                        for (int j = 0; j < choiceItems.size(); j++) {
                            boolean chose = answerItem != null && (
                                    (questionResp.getType() == CHOICE && answerItem.getChoiceId().equals(choiceItems.get(j).getId()))
                                    || (questionResp.getType() == MULTI_CHOICE && answerItem.getChoiceIds().contains(choiceItems.get(j).getId()))
                            );
                            String desc = buildSubNumber(j + 1) + choiceItems.get(j).getDesc() + (chose ? "✔" : "");
                            choicePairs.add(Pair.of(chose, desc));
                        }
                        buildParagraph(table.createRow(), LEFT, choicePairs);
                        break;
                    case MULTI_FILL_IN_THE_BLACKS:
                        buildParagraph(table.createRow(), LEFT, questionDesc, 9);

                        List<ChoiceItemResp> subQuestions = questionResp.getChoiceItems();
                        int j = 0;
                        for (ChoiceItemResp subQuestion : subQuestions) {
                            String subQuestionDesc = buildSubNumberWithPrefixIndent(++j) + subQuestion.getDesc();
                            String subText = answerItem == null ? "" : answerItem.getMultiText().get(subQuestion.getId());
                            buildParagraph(table.createRow(), LEFT, subQuestionDesc, subText);
                        }
                        break;
                }
            }
        }

        buildParagraph(doc, LEFT, "", 9);

        List<String> descList = questionnaireResp.getDescList();
        if (CollectionUtils.isNotEmpty(descList)) {
            List<Pair<Boolean, String>> descPairs = Lists.newArrayList();
            descPairs.add(Pair.of(false, "      说明： " + descList.get(0)));
            buildParagraph(doc, LEFT, descPairs);
            for (int i = 1; i < descList.size(); i++) {
                //noinspection unchecked
                buildParagraph(doc, LEFT, Lists.newArrayList(Pair.of(false, "             " + descList.get(i))));
            }
        }

        List<Pair<Boolean, String>> tailPairs = Lists.newArrayList();
        tailPairs.add(Pair.of(false, "      填表人姓名： "));
        tailPairs.add(Pair.of(true, questionnaireResp.getUser()));
        tailPairs.add(Pair.of(false, "    电话： "));
        tailPairs.add(Pair.of(true, questionnaireResp.getTel()));
        buildParagraph(doc, LEFT, tailPairs);

        List<Pair<Boolean, String>> datePairs = Lists.newArrayList();
        String[] dateParts = questionnaireResp.getDate().split(" ")[0].split("-");
        datePairs.add(Pair.of(false, "               报表日期： "));
        datePairs.add(Pair.of(true, dateParts[0]));
        datePairs.add(Pair.of(false, " 年 "));
        datePairs.add(Pair.of(true, dateParts[1]));
        datePairs.add(Pair.of(false, " 月 "));
        datePairs.add(Pair.of(true, dateParts[2]));
        datePairs.add(Pair.of(false, " 日"));
        buildParagraph(doc, RIGHT, datePairs);


        doc.write(resp.getOutputStream());
    }

    private String buildSubNumber(int num) {
        return "(" + num + ") ";
    }

    private String buildSubNumberWithPrefixIndent(int num) {
        return "    (" + num + ") ";
    }

    private void buildParagraph(XWPFDocument doc, ParagraphAlignment align, String text, int fontSize) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(align);

        XWPFRun run = paragraph.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(fontSize);
        run.setText(text);
    }

    private void buildParagraph(XWPFTableRow row, ParagraphAlignment align, String text, int fontSize) {
        row.setHeight(380);
        XWPFTableCell cell = row.getTableCells().isEmpty() ? row.createCell() : row.getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

        XWPFParagraph paragraph = cell.getParagraphArray(0);
        paragraph.setAlignment(align);

        XWPFRun run = paragraph.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(fontSize);
        run.setText(text);
    }

    private void buildParagraph(XWPFTableRow row, ParagraphAlignment align, String desc, String text) {
        row.setHeight(380);
        XWPFTableCell cell = row.getTableCells().isEmpty() ? row.createCell() : row.getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

        XWPFParagraph paragraph = cell.getParagraphArray(0);
        paragraph.setAlignment(align);

        XWPFRun run = paragraph.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(9);
        run.setText(desc + "  ");

        run = paragraph.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(9);
        run.setText(text);
        highLightRunText(run);
    }

    private void buildParagraph(XWPFDocument doc, ParagraphAlignment align, List<Pair<Boolean, String>> choicePairs) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(align);
        buildParagraph(paragraph, choicePairs, false);
    }

    private void buildParagraph(XWPFTableRow row, ParagraphAlignment align, List<Pair<Boolean, String>> choicePairs) {
        row.setHeight(380);
        XWPFTableCell cell = row.getTableCells().isEmpty() ? row.createCell() : row.getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

        XWPFParagraph paragraph = cell.getParagraphArray(0);
        paragraph.setAlignment(align);

        buildParagraph(paragraph, choicePairs, true);
    }

    private void buildParagraph(XWPFParagraph paragraph,
                                List<Pair<Boolean, String>> choicePairs, boolean indent) {
        for (Pair<Boolean, String> choicePair : choicePairs) {

            if (indent) {
                XWPFRun run = paragraph.createRun();
                run.setFontFamily("宋体");
                run.setFontSize(9);
                run.setText("    ");
            }

            XWPFRun run = paragraph.createRun();
            run.setFontFamily("宋体");
            run.setFontSize(9);
            run.setText(choicePair.getRight());

            if (choicePair.getLeft()) {
                highLightRunText(run);
            }
        }
    }

    /**
     * 高亮显示文字
     * @param pRun run
     */
    @SuppressWarnings("unused")
    private void highLightRunText(XWPFRun pRun) {
//        CTRPr pRpr = pRun.getCTR() == null ? pRun.getCTR().addNewRPr() : pRun.getCTR().getRPr();
//        CTHighlight highLight = pRpr.isSetHighlight() ? pRpr.getHighlight() : pRpr.addNewHighlight();
//        highLight.setVal(STHighlightColor.Enum.forInt(7));
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
