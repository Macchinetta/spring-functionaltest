<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cspr.index" />
    <c:set var="functionId" value="cspr" />

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
                <h1 id="screenTitle">CSRF対策</h1>

                <div>[CSPR01] CSRF対策機能の適用</div>
                <ul>
                    <li>
                        cspr0101001 (<a id="cspr0101001_jsp" href="${pageContext.request.contextPath}/jspDisabledcspr/0101/001">JSP</a> /
                        <a id="cspr0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleafDisabledcspr/0101/001">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[CSPR02] HTMLフォーム使用時の連携</div>
                <ul>
                    <li>
                        cspr0201001 (<a id="cspr0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> /
                        <a id="cspr0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0201002 (<a id="cspr0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> /
                        <a id="cspr0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0201003 (<a id="cspr0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> /
                        <a id="cspr0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/003">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0201004 (<a id="cspr0201004_jsp" href="${pageContext.request.contextPath}/jsp/0201/004">JSP</a> /
                        <a id="cspr0201004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/004">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0201005 (<a id="cspr0201005_jsp" href="${pageContext.request.contextPath}/jsp/0201/005">JSP</a> /
                        <a id="cspr0201005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/005">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0201006 (<a id="cspr0201006_jsp" href="${pageContext.request.contextPath}/jsp/0201/006">JSP</a> /
                        <a id="cspr0201006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/006">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0201007 (<a id="cspr0201007_jsp" href="${pageContext.request.contextPath}/jsp/0201/007">JSP</a> /
                        <a id="cspr0201007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/007">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0201008 (<a id="cspr0201008_jsp" href="${pageContext.request.contextPath}/jsp/0201/008">JSP</a> /
                        <a id="cspr0201008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/008">Thymeleaf</a>)
                    </li>
                    <li>cspr0201009 (JSP / <a id="cspr0201009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/009">Thymeleaf</a>)</li>
                </ul>

                <div>[CSPR03] Ajax使用時の連携</div>
                <ul>
                    <li>
                        cspr0301001 (<a id="cspr0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> /
                        <a id="cspr0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0301002 (<a id="cspr0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002">JSP</a> /
                        <a id="cspr0301002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/002">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0301003 (<a id="cspr0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> /
                        <a id="cspr0301003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/003">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0301004 (<a id="cspr0301004_jsp" href="${pageContext.request.contextPath}/jsp/0301/004">JSP</a> /
                        <a id="cspr0301004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/004">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[CSPR04] マルチパートリクエスト時の留意点</div>
                <ul>
                    <li>
                        cspr0401001 (<a id="cspr0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> /
                        <a id="cspr0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001">Thymeleaf</a>)
                    </li>
                    <li>
                        cspr0401002 (<a id="cspr0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002">JSP</a> /
                        <a id="cspr0401002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/002">Thymeleaf</a>)
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
