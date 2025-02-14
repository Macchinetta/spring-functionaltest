<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cdls.codeListVariation" />
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
                <h1 id="screenTitle">コードリストの記述方法のバリエーション</h1>

                <div>CDLS0401001</div>
                <div>
                    <form:select id="cdls0401001_jsp" items="${CL_I18N_PRICE_MAP_LOCALE}" path="codeListForm.code" />
                </div>
                <div>CDLS0401002</div>
                <div>
                    <form:select id="cdls0401002_jsp" items="${CL_I18N_PRICE_MAP_LOCALE}" path="codeListForm.code" />
                </div>
                <div>CDLS0402001</div>
                <div>
                    <form:select id="cdls0402001_jsp" items="${CL_I18N_PRICE_MAP_CODE}" path="codeListForm.code" />
                </div>
                <div>CDLS0402002</div>
                <div>
                    <form:select id="cdls0402002_jsp" items="${CL_I18N_PRICE_MAP_CODE}" path="codeListForm.code" />
                </div>
                <div>CDLS0403001</div>
                <div>
                    <form:select id="cdls0403001_jsp" items="${CL_MONTH_DES}" path="codeListForm.code" />
                </div>
                <div>CDLS0403002</div>
                <div>
                    <form:select id="cdls0403002_jsp" items="${CL_NUMBER_WITHIN_RANGE_INTERVAL}" path="codeListForm.code" />
                </div>
                <div>CDLS0403003</div>
                <div>
                    <form:select id="cdls0403003_jsp" items="${CL_NUMBER_WITHOUT_RANGE_INTERVAL}" path="codeListForm.code" />
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
