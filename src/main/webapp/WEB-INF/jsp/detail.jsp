<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Questionnaire Detail</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>
    <div class="page-header">
        <h3>${detail.title}</h3>
        <h5 style="padding-top: 10px;">${detail.year}&nbsp;年&nbsp;${detail.quarter}&nbsp;季度</h5>
        <h5>表<span style="padding-left: 28px;"></span>号：${detail.serialNum}</h5>
        <h5>制表机关：${detail.office}</h5>
        <h5>文<span style="padding-left: 28px;"></span>号：${detail.docNum}</h5>
    </div>

    <%--<div class="col-md-6">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>问卷名称</th>
                    <th>表号</th>
                    <th>制表机关</th>
                    <th>最后更新</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="item" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td><a href="./detail">${item.title}</a></td>
                        <td>${item.serialNum}</td>
                        <td>${item.office}</td>
                        <td>${item.lastUpdate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>--%>
    <p style="padding-top: 50px; padding-bottom: 50px; color: #ff0000;">未完待续</p>

    <div class="page-footer">
        <h5>说明：</h5>
        <c:forEach items="${detail.descList}" var="item" varStatus="status">
            <h5>${status.count}.&nbsp;${item}</h5>
        </c:forEach>
    </div>

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>
