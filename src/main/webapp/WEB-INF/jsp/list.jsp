<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>问卷列表</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/common.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>
    <div class="page-header padding-left">
        <h3>问卷列表</h3>
    </div>
    <div class="col-md-6 padding-left table-responsive">
        <table class="table table-striped table-hover">
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
                <c:forEach items="${list}" var="answer" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td><a href="./detail">${answer.title}</a></td>
                        <td>${answer.serialNum}</td>
                        <td>${answer.office}</td>
                        <td>${answer.lastUpdate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>
