<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.spsc.resultImage" />
    <c:set var="functionId" value="spsc" />

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
                <h3 id="screenTitle">結果画面</h3>

                <br />
                <a id="spsc0101001" href="${pageContext.request.contextPath}/spsc">go to SPSC index</a> <br />
                <a id="spsc0501005" href="https://${connectIp}:${connectHttpsPort}/spring-functionaltest-web/spsc/0501/005">HTTPS Access</a>

                <br />
                <img src="${pageContext.request.contextPath}/resources/image/Duke.png" width="128" height="128" title="display image with relative path" />

                <br />
                <img src="http://${connectIp}:${connectHttpPort}/spring-functionaltest-web-spsc/resources/image/Duke.png" width="128" height="128" title="display image with http" />

                <br />
                <img src="https://${connectIp}:${connectHttpsPort}/spring-functionaltest-web-spsc/resources/image/Duke.png" width="128" height="128" title="display image with https" />

                <br />
                <img src="http://${connectIp}:${connectHttpsPort}/spring-functionaltest-web-spsc/resources/image/Duke.png" width="128" height="128" title="display image by CSP Header derective" />
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
