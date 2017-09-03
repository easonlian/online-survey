<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>添加选择题选项</title>
    </head>
    <body>
        <form action="./add" method="post">
            <p>
                <label for="questionId">所属题目</label>
                <select id="questionId" name="questionId">
                    <c:forEach items="${questions}" var="question">
                        <option <c:if test="${question.id == lastQuestionId}">selected="selected"</c:if> value="${question.id}">
                            ${question.id}.&nbsp;${question.desc}
                        </option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label for="desc">选项描述</label>
                <input id="desc" name="desc" type="text" size="80">
            </p>
            <input type="submit" value="提交 ">
        </form>
    </body>
</html>
