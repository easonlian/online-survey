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
    <div class="padding-left table-responsive">
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
                <c:forEach items="${list}" var="questionnaire" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td><a href="../answer/list-${questionnaire.id}">${questionnaire.title}</a></td>
                        <td>${questionnaire.serialNum}</td>
                        <td>${questionnaire.office}</td>
                        <td>${questionnaire.lastUpdate}</td>
                        <td>
                            <a class="btn btn-sm btn-info" href="../../questionnaire/detail-${questionnaire.id}">查看问卷</a>
                            <button type="button" class="btn btn-sm btn-danger"
                                    data-toggle="modal" data-target="#myModal${questionnaire.id}">
                                <span class="glyphicon glyphicon-qrcode"></span>&nbsp;查看二维码
                            </button>
                            <div class="modal fade" id="myModal${questionnaire.id}" tabindex="-1" role="dialog" aria-labelledby="label">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content modal-sm">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" id="label">问卷二维码</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!--suppress HtmlUnknownAttribute -->
                                            <div dataid="${questionnaire.id}" class="my-qrcode">
                                                <img id="qrcode-logo${questionnaire.id}" src="../../img/logo.jpg"
                                                     style="position: absolute; width: 30px; height: 30px;"/>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var urlPrefix = window.location.protocol + "//" + window.location.host + "/questionnaire/detail-";
            $(".my-qrcode").each(function () {
                var questionnaireId = $(this).attr("dataid");
                $(this).qrcode({
                    correctLevel: 3,
                    text: utf16to8(urlPrefix + questionnaireId)
                });
                //noinspection JSJQueryEfficiency
                var margin = ($(this).find("canvas").attr("height") - $("#qrcode-logo" + questionnaireId).height()) / 2;
                //noinspection JSJQueryEfficiency
                $("#qrcode-logo" + questionnaireId).css("margin", margin);
            });
        });

        function utf16to8(str) {
            var out, i, len, c;
            out = "";
            len = str.length;
            for (i = 0; i < len; i++) {
                c = str.charCodeAt(i);
                if ((c >= 0x0001) && (c <= 0x007F)) {
                    out += str.charAt(i);
                } else if (c > 0x07FF) {
                    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                    out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                } else {
                    out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                }
            }
            return out;
        }
    </script>
</body>
</html>
