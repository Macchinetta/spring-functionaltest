<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.ssmn.index" />
    <c:set var="functionId" value="ssmn" />

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
                <h1 id="screenTitle">SSMN セッション管理</h1>

                <div>[SSMN03] @SessionAttributesアノテーションの使用</div>
                <ul>
                    <li>
                        ssmn0301001 (<a id="ssmn0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> /
                        <a id="ssmn0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301002 (<a id="ssmn0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002">JSP</a> /
                        <a id="ssmn0301002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/002">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301003 (<a id="ssmn0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> /
                        <a id="ssmn0301003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/003">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301004 (<a id="ssmn0301004_jsp" href="${pageContext.request.contextPath}/jsp/0301/004">JSP</a> /
                        <a id="ssmn0301004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/004">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301005 (<a id="ssmn0301005_jsp" href="${pageContext.request.contextPath}/jsp/0301/005">JSP</a> /
                        <a id="ssmn0301005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/005">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301006 (<a id="ssmn0301006_jsp" href="${pageContext.request.contextPath}/jsp/0301/006">JSP</a> /
                        <a id="ssmn0301006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/006">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301007 (<a id="ssmn0301007_jsp" href="${pageContext.request.contextPath}/jsp/0301/007">JSP</a> /
                        <a id="ssmn0301007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/007">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301008 (<a id="ssmn0301008_jsp" href="${pageContext.request.contextPath}/jsp/0301/008">JSP</a> /
                        <a id="ssmn0301008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/008">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0301009 (<a id="ssmn0301009_jsp" href="${pageContext.request.contextPath}/jsp/0301/009">JSP</a> /
                        <a id="ssmn0301009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/009">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0302001 (<a id="ssmn0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> /
                        <a id="ssmn0302001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0302002 (<a id="ssmn0302002_jsp" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a> /
                        <a id="ssmn0302002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/002">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[SSMN04] Spring FrameworkのsessionスコープのBeanの使用</div>
                <ul>
                    <li>
                        ssmn0401001 (<a id="ssmn0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001/shopping/items?init">JSP</a> /
                        <a id="ssmn0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001/shopping/items?init">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0401002 (<a id="ssmn0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002/shopping/items?init">JSP</a> /
                        <a id="ssmn0401002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/002/shopping/items?init">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0402001 (<a id="ssmn0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001/shopping/items?init">JSP</a> /
                        <a id="ssmn0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001/shopping/items?init">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[SSMN06] 同一セッション内のリクエストの同期化</div>
                <ul>
                    <li>
                        ssmn0601001(最初リクエスト実行) (<a id="ssmn060100101_before_jsp" href="${pageContext.request.contextPath}/jsp/0601/001/synchronism/delay">JSP</a> /
                        <a id="ssmn060100102_before_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001/synchronism/delay">Thymeleaf</a>)
                    </li>
                    <li>
                        ssmn0601001(次リクエスト実行) (<a id="ssmn060100101_after_jsp" href="${pageContext.request.contextPath}/jsp/0601/001/synchronism">JSP</a> /
                        <a id="ssmn060100101_after_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001/synchronism">Thymeleaf</a>)
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
