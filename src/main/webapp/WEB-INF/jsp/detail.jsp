<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>问卷详情</title>
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
                                    <input type="text" id="${question.id}" class="form-control" placeholder="请在此处填写答案">
                                </c:when>
                                <c:when test="${question.type == 'CHOICE'}">
                                    <div class="btn-group btn-group-sm" data-toggle="buttons">
                                        <c:forEach items="${question.choiceItems}" var="choice" varStatus="cStatus">
                                            <label class="btn btn-default" style="margin-right: 15px;">
                                                <input dataid="${choice.id}" type="radio">${cStatus.count}.&nbsp;${choice.desc}
                                            </label>
                                        </c:forEach>
                                    </div>
                                    <span class="label label-info text-right" style="position: absolute;">${question.type.desc}</span>
                                </c:when>
                                <c:when test="${question.type == 'MULTI_CHOICE'}">
                                    <div class="btn-group btn-group-sm" data-toggle="buttons-checkbox">
                                        <c:forEach items="${question.choiceItems}" var="choice" varStatus="cStatus">
                                            <button dataid="${choice.id}" class="btn btn-default" style="margin-right: 15px;">${cStatus.count}.&nbsp;${choice.desc}</button>
                                        </c:forEach>
                                    </div>
                                    <span class="label label-info text-right" style="position: absolute;">${question.type.desc}</span>
                                </c:when>
                                <c:when test="${question.type == 'MULTI_FILL_IN_THE_BLACKS'}">
                                    <c:forEach items="${question.choiceItems}" var="choice" varStatus="cStatus">
                                        <span style="font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px;">${cStatus.count}.&nbsp;${choice.desc}</span><br/>
                                        <input type="text" dataid="${choice.id}" class="form-control multi-fill-in-the-blacks"
                                               style="margin-top: 8px; margin-bottom: 8px;" placeholder="请在此处填写答案">
                                    </c:forEach>
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
                        <input id="userName" type="text" class="form-control" placeholder="受访人姓名" aria-describedby="userNameLabel">
                    </div>
                </td>
                <td>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon" id="userTelLabel">电话</span>
                        <input id="userTel" type="text" class="form-control" placeholder="受访人电话" aria-describedby="userTelLabel">
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button id="submit" type="submit" class="btn btn-primary btn-block">提交调查表</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <%--<div class="progress">
        <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar"
             aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 80%; min-width: 2em;">
            80%
        </div>
    </div>--%>

    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>

    <script type="text/javascript">
        var questionnaireId = ${questionnaire.id};
        $(document).ready(function() {
            $("#submit").click(function() {
                var answerMap = {};
                var errors = [];
                $(".answerArea").each(function() {
                    var questionId = $(this).attr("dataid");
                    var questionType = $(this).attr("datatype");
                    if (questionType == "FILL_IN_THE_BLACKS") {
                        var text = $("#" + questionId).val();
                        if (text == "") {
                            errors[errors.length] = "题目: '" + $(".question_" + questionId + "").text() + "' 还没有填写！";
                        } else {
                            answerMap[questionId + "-" + questionType] = text;
                        }
                    } else if (questionType == "CHOICE") {
                        var choiceId = $(this).find(".active input").attr("dataid")
                        if (choiceId == undefined) {
                            errors[errors.length] = "题目: '" + $(".question_" + questionId + "").text() + "' 还没有选择！";
                        } else {
                            answerMap[questionId + "-" + questionType] = choiceId;
                        }
                    } else if (questionType == "MULTI_CHOICE") {
                        var choiceIds = [];
                        $(this).find(".active").each(function () {
                            choiceIds[choiceIds.length] = $(this).attr("dataid");
                        });
                        if (choiceIds == "") {
                            errors[errors.length] = "题目: '" + $(".question_" + questionId + "").text() + "' 还没有选择！";
                        } else {
                            answerMap[questionId + "-" + questionType] = choiceIds;
                        }
                    } else if (questionType == "MULTI_FILL_IN_THE_BLACKS") {
                        var textMap = {};
                        $(this).find("input").each(function () {
                            var text = $(this).val();
                            if (text.length > 0) {
                                textMap[$(this).attr("dataid")] = text;
                            }
                        });
                        if (Object.keys(textMap).length <= 0) {
                            errors[errors.length] = "题目: '" + $(".question_" + questionId + "").text() + "' 还没有填写！";
                        } else {
                            answerMap[questionId + "-" + questionType] = textMap;
                        }
                    }
                });

                var userName = $("#userName").val();
                var userTel = $("#userTel").val();
                if (userName == "" || userTel == "") {
                    errors[errors.length] = "请填写受访人姓名及电话"
                } else if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(userTel))){
                    errors[errors.length] = "请填写正确的受访人电话"
                }
                if (errors.length > 0) {
                    alert(errors.join("\n"));
                    return;
                }

                //noinspection JSUnusedLocalSymbols
                $.ajax({
                    type: 'POST',
                    url: "./commit",
                    data: {
                        "questionnaireId": questionnaireId,
                        "user": userName,
                        "tel": userTel,
                        "answerData": JSON.stringify(answerMap)
                    },
                    success: function (data) {
                        alert(data.desc);
                        if (data.code == 0) {
                            $("#submit").attr("disabled", "disabled");
                            $(window).attr("location", "../answer/detail-" + data.data)
                        }
                    },
                    error: function (data) {
                        alert("服务繁忙，请稍后重试！")
                    }
                });
            });
        });
    </script>
</body>
</html>
