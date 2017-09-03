/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:18
 */
public class Dates {

    public static String formatTime(Date updateTime) {
        return formatTime(updateTime, "yyyy-MM-dd HH:mm");
    }

    @SuppressWarnings({"WeakerAccess", "SameParameterValue"})
    public static String formatTime(Date updateTime, String pattern) {
        return new SimpleDateFormat(pattern).format(updateTime);
    }
}
