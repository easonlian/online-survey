<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>问卷答案详情</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/common.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>

    <div class="page-header padding-left" style="margin-bottom: 30px;">
        <h3>${questionnaire.title}</h3>
        <h5 style="padding-top: 10px;">${questionnaire.year}&nbsp;&nbsp;年&nbsp;&nbsp;${questionnaire.quarter}&nbsp;&nbsp;季度</h5>
        <h5>表<span style="padding-left: 28px;"></span>号：${questionnaire.serialNum}</h5>
        <h5>制表机关：${questionnaire.office}</h5>
        <h5>文<span style="padding-left: 28px;"></span>号：${questionnaire.docNum}</h5>
    </div>

    <div class="page- padding-left">
        <c:forEach items="${questionnaire.sections}" var="section">
            <div>
                <h4>${section.name}</h4>
                <table class="table table-striped">
                    <tbody>
                    <c:forEach items="${section.questions}" var="question" varStatus="qStatus">
                        <tr><td>
                            <p><h5 class="question_${question.id}">${qStatus.count}.&nbsp;${question.desc}</h5></p>
                            <div class="answerArea" dataid="${question.id}" datatype="${question.type}">
                            <c:choose>
                                <c:when test="${question.type == 'FILL_IN_THE_BLACKS'}">
                                    <c:choose>
                                        <c:when test="${question.answerItem != null}">
                                            <input type="text" id="${question.id}" class="form-control active"
                                                   disabled="disabled" value="${question.answerItem.text}" placeholder="未填写答案">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" id="${question.id}" class="form-control" disabled="disabled" placeholder="未填写答案">
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:when test="${question.type == 'CHOICE'}">
                                    <div class="btn-group btn-group-sm" data-toggle="buttons">
                                        <c:forEach items="${question.choiceItems}" var="choice" varStatus="cStatus">
                                            <label class="btn btn-default ${choice.active ? "active" : ""}" style="margin-right: 15px;">
                                                <input dataid="${choice.id}" type="radio">${cStatus.count}.&nbsp;${choice.desc}
                                                <c:if test="${choice.active}">
                                                    <span class="label label-success" style="padding-left: 4px; padding-right: 4px; position: absolute;">选</span>
                                                </c:if>
                                            </label>
                                        </c:forEach>
                                    </div>
                                    <span class="label label-info text-right" style="position: absolute;">${question.type.desc}</span>
                                </c:when>
                                <c:when test="${question.type == 'MULTI_CHOICE'}">
                                    <div class="btn-group btn-group-sm">
                                        <c:forEach items="${question.choiceItems}" var="choice" varStatus="cStatus">
                                            <button dataid="${choice.id}" class="btn btn-default ${choice.active ? "active" : ""}" style="margin-right: 15px;">
                                                ${cStatus.count}.&nbsp;${choice.desc}
                                                <c:if test="${choice.active}">
                                                    <span class="label label-success" style="padding-left: 4px; padding-right: 4px; position: absolute;">选</span>
                                                </c:if>
                                            </button>
                                        </c:forEach>
                                    </div>
                                    <span class="label label-info text-right" style="position: absolute;">${question.type.desc}</span>
                                </c:when>
                            </c:choose>
                            </div>
                        </td></tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>

    <hr>

    <div class="page-header padding-left" style="margin-top: 30px; margin-bottom: 0; padding-bottom: 0;">
        <h5>说明：</h5>
        <c:forEach items="${questionnaire.descList}" var="questionnaire" varStatus="status">
            <h5>${status.count}.&nbsp;${questionnaire}</h5>
        </c:forEach>

        <table class="table table-striped" style="margin-top: 20px;">
            <tbody>
            <tr>
                <td>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon" id="userNameLabel">姓名</span>
                        <input id="userName" type="text" class="form-control" value="${questionnaire.user}" disabled="disabled" placeholder="未填写受访人姓名" aria-describedby="userNameLabel">
                    </div>
                </td>
                <td>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon" id="userTelLabel">电话</span>
                        <input id="userTel" type="text" class="form-control" value="${questionnaire.tel}" disabled="disabled" placeholder="未填写受访人电话" aria-describedby="userTelLabel">
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <h5 class="text-right" style="margin-right: 10px;">受访时间：${questionnaire.date}</h5>
    </div>

    <%--<div class="progress">
        <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar"
             aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 80%; min-width: 2em;">
            80%
        </div>
    </div>--%>

    <script src="../../js/jquery.min.js"></script>
</body>
</html>
