<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>提交题目</title>
    </head>
    <body>
        <form action="./add" method="post">
            <p>
                <label for="sectionId">所属分段</label>
                <select id="sectionId" name="sectionId">
                    <c:forEach items="${sections}" var="section">
                        <option <c:if test="${section.id == lastSectionId}">selected="selected"</c:if> value="${section.id}">${section.name}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label for="desc">题目描述</label>
                <input id="desc" name="desc" type="text">
            </p>
            <p>
                <label for="type">题目描述</label>
                <select id="type" name="type">
                    <option value="0">填空题</option>
                    <option value="1">单选题</option>
                    <option value="2">多选题</option>
                </select>
            </p>
            <input type="submit" value="提交 ">
        </form>
    </body>
</html>
