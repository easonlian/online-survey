/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.resp;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Section resp
 *
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 上午11:18
 */
@Data
public class SectionResp {

    private Integer id;                     //  主键
    private String name;                    //  名称
    private List<QuestionResp> questions = Lists.newArrayList();

    private String lastUpdate;          //  最后更新时间
}
