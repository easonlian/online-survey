/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.util.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.qunar.survey.bean.entity.AnswerItems;
import org.qunar.survey.util.Jsons;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: jianyu.lin
 * Date: 2017/9/3 Time: 下午5:04
 */
@MappedJdbcTypes(JdbcType.LONGVARCHAR)
@MappedTypes(AnswerItems.class)
@SuppressWarnings("unused")
public class AnswerItemsHandler extends BaseTypeHandler<AnswerItems> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, AnswerItems parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Jsons.toJson(parameter));
    }

    @Override
    public AnswerItems getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Jsons.fromJson(rs.getString(columnName), AnswerItems.class);
    }

    @Override
    public AnswerItems getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Jsons.fromJson(rs.getString(columnIndex), AnswerItems.class);
    }

    @Override
    public AnswerItems getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Jsons.fromJson(cs.getString(columnIndex), AnswerItems.class);
    }
}
