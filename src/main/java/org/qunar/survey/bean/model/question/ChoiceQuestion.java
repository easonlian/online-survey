/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.question;

import lombok.Data;
import org.qunar.survey.bean.entity.ChoiceAnswer;
import org.qunar.survey.bean.entity.Question;

import java.util.List;

/**
 * 单选题
 */
@Data
public class ChoiceQuestion extends Question {

    private List<ChoiceAnswer> answers;
}
