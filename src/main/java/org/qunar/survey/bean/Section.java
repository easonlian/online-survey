/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean;

import lombok.Data;

import java.util.List;

/**
 * 问卷分块
 */
@Data
public class Section {

    private List<Question> questions;
}
