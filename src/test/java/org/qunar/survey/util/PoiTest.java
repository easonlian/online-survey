/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.util;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth.Enum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;


/**
 * poi lib test class
 * Author: jianyu.lin
 * Date: 2017/9/7 Time: 下午10:54
 */
public class PoiTest {

    /**
     * 通过XWPFDocument对内容进行访问。对于XWPF文档而言，用这种方式进行读操作更佳。
     * @throws Exception
     */
    @Test
    public void testReadByDoc() throws Exception {
        InputStream is = new FileInputStream("/Users/easonlian/温江花木企业景气调查问卷（2017年版）_forTest.docx");
//        InputStream is = new FileInputStream("/Users/easonlian/sample.docx");
        XWPFDocument doc = new XWPFDocument(is);
        List<XWPFParagraph> paras = doc.getParagraphs();
        for (XWPFParagraph para : paras) {
            //当前段落的属性
            CTPPr pr = para.getCTP().getPPr();
            System.out.println(para.getText());
        }
        //获取文档中所有的表格
        List<XWPFTable> tables = doc.getTables();
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        for (XWPFTable table : tables) {
            //表格属性
            CTTblPr pr = table.getCTTbl().getTblPr();
            CTTblWidth width = pr.getTblW();
            Enum type = width.getType();
            BigInteger w = width.getW();
            CTTblBorders borders = pr.getTblBorders();
            //获取表格对应的行
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                //获取行对应的单元格
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    System.out.println(cell.getText());
                }
            }
        }
        this.close(is);
    }

    @Test
    public void writeQuestionnaire() throws Exception {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = p1.createRun();
        run.setText("温江花木企业景气调查问卷");
        run.setFontFamily("宋体");
        run.setFontSize(16);


        p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.RIGHT);
        run = p1.createRun();
        run.setText("2017年3季度               ");
        run.setFontFamily("宋体");
        run.setFontSize(9);

        run = p1.createRun();
        run.setText("表    号：C5296-2表");
        run.setFontFamily("宋体");
        run.setFontSize(9);


        p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.RIGHT);
        run = p1.createRun();
        run.setText("制表机关：国家统计局温江调查队");
        run.setFontFamily("宋体");
        run.setFontSize(9);

        p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.RIGHT);
        run = p1.createRun();
        run.setText("文    号：川调函[2017]15号");
        run.setFontFamily("宋体");
        run.setFontSize(9);


        XWPFTable table = doc.createTable(40, 1);

        //表格属性
        CTTblPr tablePr = table.getCTTbl().addNewTblPr();
        //表格宽度
        CTTblWidth width = tablePr.addNewTblW();
        width.setW(BigInteger.valueOf(8320));
        width.setType(STTblWidth.DXA);

        //  border
        CTTblBorders borders = tablePr.addNewTblBorders();
//        CTBorder hBorder=borders.addNewInsideH();
//        hBorder.setVal(STBorder.Enum.forString("dashed"));
//        hBorder.setSz(new BigInteger("1"));
//        hBorder.setColor("0000FF");

//        CTBorder vBorder=borders.addNewInsideV();
//        vBorder.setVal(STBorder.Enum.forString("dotted"));
//        vBorder.setSz(new BigInteger("1"));
//        vBorder.setColor("00FF00");

        CTBorder lBorder=borders.addNewLeft();
        lBorder.setVal(STBorder.Enum.forString("none"));
//        lBorder.setSz(new BigInteger("1"));
//        lBorder.setColor("3399FF");

        CTBorder rBorder=borders.addNewRight();
        rBorder.setVal(STBorder.Enum.forString("none"));
//        rBorder.setSz(new BigInteger("1"));
//        rBorder.setColor("F2B11F");

//        CTBorder tBorder=borders.addNewTop();
//        tBorder.setVal(STBorder.Enum.forString("thick"));
//        tBorder.setSz(new BigInteger("1"));
//        tBorder.setColor("C3599D");

//        CTBorder bBorder=borders.addNewBottom();
//        bBorder.setVal(STBorder.Enum.forString("wave"));
//        bBorder.setSz(new BigInteger("1"));
//        bBorder.setColor("BF6BCC");


        table.getRow(0).setHeight(380);
        XWPFTableCell cell = table.getRow(0).getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        XWPFParagraph p2 = cell.getParagraphArray(0);
        p2.setAlignment(ParagraphAlignment.CENTER);

        run = p2.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(10);
        run.setText("一、企业名称及代码");


        table.getRow(1).setHeight(380);
        cell = table.getRow(1).getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        p2 = cell.getParagraphArray(0);

        run = p2.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(9);
        run.setText("02组织机构代码：");


        table.getRow(2).setHeight(380);
        cell = table.getRow(2).getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        p2 = cell.getParagraphArray(0);
        p2.setAlignment(ParagraphAlignment.CENTER);

        run = p2.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(10);
        run.setText("二、本行业景气状况判断");


        table.getRow(3).setHeight(380);
        cell = table.getRow(3).getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        p2 = cell.getParagraphArray(0);

        run = p2.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(9);
        run.setText("01您对本季度花木行业总体发展状况的看法");


        table.getRow(4).setHeight(380);
        cell = table.getRow(4).getCell(0);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        p2 = cell.getParagraphArray(0);

        run = p2.createRun();
        run.setFontFamily("宋体");
        run.setFontSize(9);
        run.setText("①乐观□　　　　②一般□　　　　③不乐观");

        for (int i = 5; i < 40; i++) {
            table.getRow(i).setHeight(380);
            cell = table.getRow(i).getCell(0);
            cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            p2 = cell.getParagraphArray(0);

            run = p2.createRun();
            run.setFontFamily("宋体");
            run.setFontSize(9);
            run.setText("未完待续...");
        }

        OutputStream os = new FileOutputStream("/Users/easonlian/sample.docx");
        doc.write(os);
        os.close();
    }

    @Test
    public void testWriteToDoc() throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setText("加粗字体");
        run = paragraph.createRun();
        run.setColor("FF0000");
        run.setText("红色字体");
        OutputStream os = new FileOutputStream("/Users/easonlian/sample.docx");
        document.write(os);
        os.close();
    }

    /***
     * 写一个表格
     * @throws Exception
     */
    @Test
    public void testWriteTable() throws Exception {
        XWPFDocument doc = new XWPFDocument();
        //创建一个5行5列的表格
        XWPFTable table = doc.createTable(5, 5);
        //这里增加的列原本初始化创建的那5行在通过getTableCells()方法获取时获取不到，但通过row新增的就可以。
//    table.addNewCol(); //给表格增加一列，变成6列
        table.createRow(); //给表格新增一行，变成6行
        List<XWPFTableRow> rows = table.getRows();
        //表格属性
        CTTblPr tablePr = table.getCTTbl().addNewTblPr();
        //表格宽度
        CTTblWidth width = tablePr.addNewTblW();
        width.setW(BigInteger.valueOf(8000));
        XWPFTableRow row;
        List<XWPFTableCell> cells;
        XWPFTableCell cell;
        int rowSize = rows.size();
        int cellSize;
        for (int i=0; i<rowSize; i++) {
            row = rows.get(i);
            //新增单元格
            row.getCell(0);
            //设置行的高度
            row.setHeight(500);
            //行属性
//       CTTrPr rowPr = row.getCtRow().addNewTrPr();
            //这种方式是可以获取到新增的cell的。
//       List<CTTc> list = row.getCtRow().getTcList();
            cells = row.getTableCells();
            cellSize = cells.size();
            for (int j=0; j<cellSize; j++) {
                cell = cells.get(j);
                if ((i+j)%2==0) {
                    //设置单元格的颜色
                    cell.setColor("ff0000"); //红色
                } else {
                    cell.setColor("0000ff"); //蓝色
                }
                //单元格属性
                CTTcPr cellPr = cell.getCTTc().addNewTcPr();
                cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
                if (j == 3) {
                    //设置宽度
                    cellPr.addNewTcW().setW(BigInteger.valueOf(3000));
                }
                cell.setText(i + ", " + j);
            }
        }
        //文件不存在时会自动创建
        OutputStream os = new FileOutputStream("/Users/easonlian/sample.docx");
        //写入文件
        doc.write(os);
        os.close();
    }

    /**
     * 关闭输入流
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
