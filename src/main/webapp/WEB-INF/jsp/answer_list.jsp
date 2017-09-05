<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>问卷：${questionnaire.title}</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/common.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>
    <div class="page-header padding-left">
        <h3>问卷：${questionnaire.title}</h3>
    </div>
    <div class="col-md-6 padding-left table-responsive">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>受访人</th>
                    <th>受访电话</th>
                    <th>受访时间</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${answers}" var="questionnaire" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td><a href="./detail-${questionnaire.id}">${questionnaire.user}</a></td>
                        <td><a href="./detail-${questionnaire.id}">${questionnaire.tel}</a></td>
                        <td>${questionnaire.date}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>
