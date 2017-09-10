/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.util;

/**
 * Author: jianyu.lin
 * Date: 2017/9/10 Time: 下午2:19
 */
public class Strings {

    public static String encMobile(String mobile) {
        return mobile.substring(0, 3) + "*****" + mobile.substring(7, 11);
    }
}
