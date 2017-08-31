/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * json util with jackson
 *
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午1:15
 */
@SuppressWarnings("unused")
public abstract class Jsons {

    private static final Logger logger = LoggerFactory.getLogger(Jsons.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 对象映射
     */
    private static final ObjectMapper objMapper = new ObjectMapper();

    static {
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        objMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Java对象转换为Json串
     * @param obj Java对象
     * @return Json串
     */
    public static String toJson(Object obj) {
        try {
            return objMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("将Java对象转换成Json串出错！");
            return null;
        }
    }

    /**
     * Json串转换为Java对象
     * @param json Json串
     * @param type Java对象类型
     * @return Java对象
     */
    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return objMapper.readValue(json, type);
        } catch (Exception e) {
            logger.error("Json串转换成对象出错：{}", json);
            return null;
        }
    }

    /**
     * Json串转换为Java对象
     * @param json    Json串
     * @param typeRef Java对象类型引用
     * @return Java对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try {
            return objMapper.readValue(json, typeRef);
        } catch (Exception e) {
            logger.error("Json串转换成对象出错：{}", json);
            return null;
        }
    }
}
