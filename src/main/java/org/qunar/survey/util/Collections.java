/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Author: jianyu.lin
 * Date: 2017/9/4 Time: 上午12:12
 */
public class Collections {

    public static <T, K> List<K> transform(List<T> fromList, Function<T, K> function) {
        if (fromList == null) {
            return Lists.newArrayList();
        }
        List<K> toList = Lists.newArrayList();
        for (T from : fromList) {
            toList.add(function.apply(from));
        }
        return toList;
    }
}
