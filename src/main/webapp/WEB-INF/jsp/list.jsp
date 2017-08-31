<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Questionnaire List</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>
    <div class="page-header">
        <h3>问卷列表</h3>
    </div>
    <div class="col-md-6">
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
    </div>

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>
