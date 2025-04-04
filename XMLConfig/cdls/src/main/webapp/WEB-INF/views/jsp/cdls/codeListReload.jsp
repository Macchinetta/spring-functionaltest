<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cdls.codeListReload" />
    <c:set var="functionId" value="cdls" />

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
                <h1 id="screenTitle">コードリストのリロード</h1>

                <div>CDLS0201001</div>
                <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/jsp/0201/001">
                    <form:select id="cdls0201001_jsp" path="id" items="${CL_CRON_REFRESH_CODELIST}" />
                    <form:errors path="id" />
                    <p>
                        Value:
                        <form:input id="cronValue" path="value" />
                        <form:errors path="value" />
                    </p>
                    <button id="updateCdls0201001_jsp" type="submit" name="update">cron table update</button>
                </form:form>

                <div>CDLS0201002</div>
                <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/jsp/0201/002">
                    <form:select id="cdls0201002_jsp" path="id" items="${CL_REFRESH_CODELIST}" />
                    <form:errors path="id" />
                    <p>
                        Value:
                        <form:input id="refreshValue" path="value" />
                        <form:errors path="value" />
                    </p>
                    <button id="updateCdls0201002_jsp" type="submit" name="update">refresh table update</button>
                </form:form>
                <a id="codeListRefreshEndPoint" href="${pageContext.request.contextPath}/jsp/0201/002?refresh">コードリストリフレッシュエンドポイント</a>

                <div>CDLS0202001</div>
                <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/jsp/0202/001">
                    <form:select id="cdls0202001_jsp" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
                    <form:errors path="id" />
                    <p>
                        Value:
                        <form:input id="cronValue2" path="value" />
                        <form:errors path="value" />
                    </p>
                    <button id="updateCdls0202001_jsp" type="submit" name="update">cron table update</button>
                </form:form>

                <div>CDLS0202002</div>
                <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/jsp/0202/002">
                    <form:select id="cdls0202002_jsp" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
                    <form:errors path="id" />
                    <p>
                        Value:
                        <form:input id="cronValue3" path="value" />
                        <form:errors path="value" />
                    </p>
                    <button id="updateCdls0202002_jsp" type="submit" name="update">cron table update</button>
                </form:form>

                <div>CDLS0202003</div>
                <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/jsp/0202/003">
                    <form:select id="cdls0202003_jsp" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
                    <form:errors path="id" />
                    <p>
                        Value:
                        <form:input id="refreshValue2" path="value" />
                        <form:errors path="value" />
                    </p>
                    <button id="updateCdls0202003_jsp" type="submit" name="update">refresh table update</button>
                </form:form>
                <a id="codeListRefreshEndPoint2" href="${pageContext.request.contextPath}/jsp/0202/003?refresh">コードリストリフレッシュエンドポイント</a>

                <div>CDLS0202004</div>
                <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/jsp/0202/003">
                    <form:select id="cdls0202004_jsp" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
                    <form:errors path="id" />
                    <p>
                        Value:
                        <form:input id="refreshValue3" path="value" />
                        <form:errors path="value" />
                    </p>
                    <button id="updateCdls0202004_jsp" type="submit" name="update">refresh table update</button>
                </form:form>
                <a id="codeListRefreshEndPoint3" href="${pageContext.request.contextPath}/jsp/0202/004?refresh">コードリストリフレッシュエンドポイント</a>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
