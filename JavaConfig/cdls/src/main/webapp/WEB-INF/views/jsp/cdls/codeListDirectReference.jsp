<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cdls.codeListDirectReference" />
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
                <h1 id="screenTitle">テンプレートエンジンにSpEL式を記述して、コードリストBeanを参照できること</h1>

                <div>CDLS0501001</div>
                <div>
                    <spring:eval var="statuses" expression="@CL_ORDERSTATUS.asMap()" />
                    <form:select id="cdls0501001_jsp" items="${statuses}" path="codeListForm.code" />
                </div>

                <div>CDLS0501002</div>
                <div>
                    <spring:eval var="prices" expression="@CL_I18N_PRICE.asMap(requestLocale).isEmpty() ? @CL_I18N_PRICE.asMap(fallBackLocale) : @CL_I18N_PRICE.asMap(requestLocale)" />
                    <form:select id="cdls0501002_jsp" items="${prices}" path="codeListForm.code" />
                </div>

                <div>CDLS0501003</div>
                <div>
                    <spring:eval var="prices" expression="@CL_I18N_PRICE.asMap(requestLocale).isEmpty() ? @CL_I18N_PRICE.asMap(fallBackLocale) : @CL_I18N_PRICE.asMap(requestLocale)" />
                    <form:select id="cdls0501003_jsp" items="${prices}" path="codeListForm.code" />
                </div>

                <div>CDLS0501004</div>
                <div>
                    <spring:eval var="prices" expression="@CL_I18N_PRICE.asMap(requestLocale).isEmpty() ? @CL_I18N_PRICE.asMap(fallBackLocale) : @CL_I18N_PRICE.asMap(requestLocale)" />
                    <form:select id="cdls0501004_jsp" items="${prices}" path="codeListForm.code" />
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
