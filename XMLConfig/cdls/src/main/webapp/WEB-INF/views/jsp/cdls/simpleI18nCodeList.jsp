<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cdls.simpleI18nCodeList" />
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
                <h1 id="screenTitle">SimpleI18nCodeListの読み込み</h1>

                <div>CDLS0105001</div>
                <div>
                    <form:select id="cdls0105001_jsp" items="${CL_I18N_PRICE}" path="codeListForm.code" />
                </div>
                <div>CDLS0105002</div>
                <div>
                    <form:select id="cdls0105002_jsp" items="${CL_I18N_PRICE}" path="codeListForm.code" />
                </div>
                <div>CDLS0105003</div>
                <div>
                    <form:form modelAttribute="clI18nPriceForm" method="post" action="${pageContext.request.contextPath}/jsp/0105/003">
                        <form:select id="cdls0105003_jsp" items="${CL_I18N_PRICE}" path="id" />
                        <form:errors path="id" />
                        <button id="cdls0105003_jspSubmit" type="submit" name="post">Submit</button>
                    </form:form>
                    <div id="cdls0105003_jspResult">${f:h(i18nPriceCodeListValue)}</div>
                </div>
                <div>CDLS0105004</div>
                <div>
                    <form:form modelAttribute="clI18nPriceForm" method="post" action="${pageContext.request.contextPath}/jsp/0105/004">
                        <form:select id="cdls0105004_jsp" items="${CL_I18N_PRICE}" path="id" />
                        <form:errors path="id" />
                        <button id="cdls0105004_jspSubmit" type="submit" name="post">Submit</button>
                    </form:form>
                    <div id="cdls0105004_jspResult">${f:h(i18nPriceCodeListValue)}</div>
                </div>
                <div>CDLS0105005</div>
                <div>
                    <form:select id="cdls0105005_jsp" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
                </div>
                <div>CDLS0105006</div>
                <div>
                    <form:select id="cdls0105006_jsp" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
                </div>
                <div>CDLS0105007</div>
                <div>
                    <form:form modelAttribute="clI18nDBPriceForm" method="post" action="${pageContext.request.contextPath}/jsp/0105/007">
                        <form:select id="cdls0105007_jsp" items="${CL_I18N_DB_PRICE}" path="id" />
                        <form:errors path="id" />
                        <button id="cdls0105007_jspSubmit" type="submit" name="post">Submit</button>
                    </form:form>
                    <div id="cdls0105007_jspResult">${f:h(i18nDBPriceCodeListValue)}</div>
                </div>
                <div>CDLS0105008</div>
                <div>
                    <form:form modelAttribute="clI18nDBPriceForm" method="post" action="${pageContext.request.contextPath}/jsp/0105/008">
                        <form:select id="cdls0105008_jsp" items="${CL_I18N_DB_PRICE}" path="id" />
                        <form:errors path="id" />
                        <button id="cdls0105008_jspSubmit" type="submit" name="post">Submit</button>
                    </form:form>
                    <div id="cdls0105008_jspResult">${f:h(i18nDBPriceCodeListValue)}</div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
