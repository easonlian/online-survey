/*
 * Copyright (c) 2017 Qunar.com. All Rights Reserved.
 */
package org.qunar.survey.util.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.qunar.survey.bean.entity.QuestionType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * transform question type
 *
 * Author: jianyu.lin
 * Date: 2017/8/31 Time: 下午1:30
 */
@MappedJdbcTypes(JdbcType.SMALLINT)
public class QuestionTypeHandler extends BaseTypeHandler<QuestionType> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement,
                                    int i, QuestionType questionType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, questionType.code);
    }

    @Override
    public QuestionType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return QuestionType.codeOf(resultSet.getInt(s));
    }

    @Override
    public QuestionType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return QuestionType.codeOf(resultSet.getInt(i));
    }

    @Override
    public QuestionType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return QuestionType.codeOf(callableStatement.getInt(i));
    }
}
