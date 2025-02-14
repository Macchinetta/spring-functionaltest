<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.xspr.index" />
    <c:set var="functionId" value="xspr" />

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
                <h1 id="screenTitle">XSS対策</h1>

                <div>[XSPR01] Output Escaping</div>
                <ul>
                    <li>
                        xspr01001 (<a id="xspr0101_jsp" href="${pageContext.request.contextPath}/jsp/0101">JSP</a> /
                        <a id="xspr0101_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101">Thymeleaf</a> )
                    </li>
                </ul>

                <div>[XSPR02] JavaScript Escaping</div>
                <ul>
                    <li>
                        xspr0201001 (<a id="xspr0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> /
                        <a id="xspr0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a> )
                    </li>
                    <li>
                        xspr0201002 (<a id="xspr0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> /
                        <a id="xspr0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a> )
                    </li>
                    <li>
                        xspr0201003 (<a id="xspr0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> /
                        <a id="xspr0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/003">Thymeleaf</a> )
                    </li>
                </ul>

                <div>[XSPR03] EventHandler Escaping</div>
                <ul>
                    <li>
                        xspr0301001 (<a id="xspr0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> /
                        <a id="xspr0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a> )
                    </li>
                    <li>
                        xspr0301002 (<a id="xspr0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002">JSP</a> /
                        <a id="xspr0301002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/002">Thymeleaf</a> )
                    </li>
                    <li>
                        xspr0301003 (<a id="xspr0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> /
                        <a id="xspr0301003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/003">Thymeleaf</a> )
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
