<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.aply.formObjectComplete" />
    <c:set var="functionId" value="aply" />

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width" />
        <meta name="contextPath" content="${pageContext.request.contextPath}" />
        <sec:csrfMetaTags />
        <title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
        <script type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
        <div class="container">
            <div id="wrapper">
                <h1 id="screenTitle">フォームオブジェクトの実装 完了画面</h1>

                <table class="table table-striped table-bordered table-condensed">
                    <tr>
                        <th class="inputColumn">value1</th>
                        <td><span id="value1">${f:h(formObjectForm.value1)}</span></td>
                    </tr>
                    <tr>
                        <th class="inputColumn">value2</th>
                        <td><span id="value2">${f:h(formObjectForm.value2)}</span></td>
                    </tr>
                    <tr>
                        <th class="inputColumn">value3</th>
                        <td><span id="value3">${f:h(formObjectForm.value3)}</span></td>
                    </tr>
                    <tr>
                        <th class="inputColumn">value4</th>
                        <td><span id="value4">${f:h(formObjectForm.value4)}</span></td>
                    </tr>
                    <tr>
                        <th class="inputColumn">value5</th>
                        <td><span id="value5">${f:h(formObjectForm.value5)}</span></td>
                    </tr>
                    <tr>
                        <th class="inputColumn">value6</th>
                        <td><span id="value6">${f:h(formObjectForm.value6)}</span></td>
                    </tr>
                    <tr>
                        <th class="inputColumn">value7</th>
                        <td><span id="value7">${f:h(formObjectForm.value7)}</span></td>
                    </tr>
                </table>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
