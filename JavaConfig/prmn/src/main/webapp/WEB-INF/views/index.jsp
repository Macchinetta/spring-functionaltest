<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.prmn.index" />
    <c:set var="functionId" value="prmn" />

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
                <h1 id="screenTitle">PRMN プロパティ管理</h1>

                <div>[PRMN01] プロパティ利用の設定方法</div>
                <ul>
                    <li>prmn0101001 (<a id="prmn0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / Thymeleaf)</li>
                    <li>prmn0101002 (<a id="prmn0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> / Thymeleaf)</li>
                    <li>prmn0102001 (<a id="prmn0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / Thymeleaf)</li>
                    <li>prmn0102002 (<a id="prmn0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / Thymeleaf)</li>
                    <li>prmn0102003 (<a id="prmn0102003_jsp" href="${pageContext.request.contextPath}/jsp/0102/003">JSP</a> / Thymeleaf)</li>
                    <li>prmn0103001 (<a id="prmn0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / Thymeleaf)</li>
                </ul>

                <div>[PRMN02] bean定義ファイルのプロパティの利用方法</div>
                <ul>
                    <li>prmn0201001 (<a id="prmn0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / Thymeleaf)</li>
                    <li>prmn0101001 (<a id="prmn0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / Thymeleaf)</li>
                </ul>

                <div>[PRMN03] Javaクラスでのプロパティ利用方法</div>
                <ul>
                    <li>prmn0101001 (<a id="prmn0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / Thymeleaf)</li>
                    <li>prmn0101001 (<a id="prmn0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / Thymeleaf)</li>
                </ul>

                <div>[PRMN04] プロパティ機能の拡張方法</div>
                <ul>
                    <li>prmn0101001 (<a id="prmn0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / Thymeleaf)</li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
