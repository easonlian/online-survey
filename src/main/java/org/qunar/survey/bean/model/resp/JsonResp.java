/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.bean.model.resp;

/**
 * standard json resp
 *
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 上午11:21
 */
@SuppressWarnings("unused")
public class JsonResp<T> {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;

    private int code;
    private String desc;
    private T data;

    private JsonResp(int code, T data, String desc) {
        this.data = data;
        this.code = code;
        this.desc = desc;
    }

    public static <T> JsonResp<T> create(int code, T data, String desc) {
        return new JsonResp<T>(code, data, desc);
    }

    public static <T> JsonResp<T> create(int code, T data) {
        return new JsonResp<T>(code, data, null);
    }

    public static <T> JsonResp<T> success(T data) {
        return new JsonResp<T>(SUCCESS, data, null);
    }

    public static <T> JsonResp<T> success(T data, String desc) {
        return new JsonResp<T>(SUCCESS, data, desc);
    }

    public static <T> JsonResp<T> error(String desc) {
        return new JsonResp<T>(ERROR, null, desc);
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
