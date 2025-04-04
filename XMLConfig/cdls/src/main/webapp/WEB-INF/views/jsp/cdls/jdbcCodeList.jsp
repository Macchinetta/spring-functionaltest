<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cdls.jdbcCodeList" />
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
                <h1 id="screenTitle">JdbcCodeListの読み込み</h1>

                <div>CDLS0103001</div>
                <div>
                    <form:select id="cdls0103001_jsp" items="${CL_AUTHORITIES}" path="codeListForm.code" />
                </div>
                <div>CDLS0103002</div>
                <div>
                    <form:form modelAttribute="clAuthoritiesForm" method="post" action="${pageContext.request.contextPath}/jsp/0103/002">
                        <form:select id="cdls0103002_jsp" items="${CL_AUTHORITIES}" path="id" />
                        <form:errors path="id" />
                        <button id="cdls0103002_jspSubmit" type="submit" name="post">Submit</button>
                    </form:form>
                    <div id="cdls0103002_jspResult">${f:h(authoritiesCodeListValue)}</div>
                </div>
                <div>CDLS0103003</div>
                <div>
                    <form:form id="cdls0103003_jsp" modelAttribute="authorityForCollectionForm" method="post" action="${pageContext.request.contextPath}/jsp/0103/003">
                        <form:checkboxes items="${CL_AUTHORITIES}" path="authorities" delimiter="<br />" />
                        <br />
                        <form:errors path="authorities*" />
                        <button id="cdls0103003_jspSubmit" type="submit" name="post">Submit</button>
                    </form:form>
                    <div id="cdls0103003_jspResult">
                        <c:forEach var="authoritiy" items="${authorities}">
                            <div>${f:h(authoritiy)}</div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
