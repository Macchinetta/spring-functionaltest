<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.encr.index" />
    <c:set var="functionId" value="encr" />

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
                <h1 id="screenTitle">ENCR 暗号化</h1>

                <div>[ENCR01] Spring Securityの暗復号</div>
                <ul>
                    <li>encr0101001 (<a id="encr0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / Thymeleaf)</li>
                    <li>encr0103001 (<a id="encr0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / Thymeleaf)</li>
                    <li>encr0104001 (<a id="encr0104001_jsp" href="${pageContext.request.contextPath}/jsp/0104/001">JSP</a> / Thymeleaf)</li>
                    <li>encr0105001 (<a id="encr0105001_jsp" href="${pageContext.request.contextPath}/jsp/0105/001">JSP</a> / Thymeleaf)</li>
                </ul>

                <div>[ENCR02] Spring Security乱数生成</div>
                <ul>
                    <li>encr0201001 (<a id="encr0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / Thymeleaf)</li>
                    <li>encr0202001 (<a id="encr0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / Thymeleaf)</li>
                    <li>encr0203001 (<a id="encr0203001_jsp" href="${pageContext.request.contextPath}/jsp/0203/001">JSP</a> / Thymeleaf)</li>
                </ul>

                <div>[ENCR03] JCAの公開鍵暗号化方式による暗復号</div>
                <ul>
                    <li>encr0301001 (<a id="encr0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / Thymeleaf)</li>
                    <li>encr0302001 (<a id="encr0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / Thymeleaf)</li>
                    <li>encr0303001 (<a id="encr0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / Thymeleaf)</li>
                </ul>

                <div>[ENCR04] ハイブリッド暗号化方式による暗復号</div>
                <ul>
                    <li>encr0401001 (<a id="encr0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / Thymeleaf)</li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
