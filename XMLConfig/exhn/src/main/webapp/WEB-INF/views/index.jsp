<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.exhn.index" />
    <c:set var="functionId" value="exhn" />

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
                <h1 id="screenTitle">例外ハンドリング</h1>

                <div>[EXHN02] Serviceでの例外発生</div>
                <ul>
                    <li>
                        exhn0201001 (<a id="exhn0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> /
                        <a id="exhn0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0202001 (<a id="exhn0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> /
                        <a id="exhn0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0203001 (<a id="exhn0203001_jsp" href="${pageContext.request.contextPath}/jsp/0203/001">JSP</a> /
                        <a id="exhn0203001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/001">Thymeleaf</a>)
                    </li>

                    <li>
                        一時ファイル削除 (<a id="deleteTemporaryFile_jsp" href="${pageContext.request.contextPath}/jsp/delete/view">JSP</a> /
                        <a id="deleteTemporaryFile_thyemelaf" href="${pageContext.request.contextPath}/thymeleaf/delete/view">Thymeleaf</a>)
                    </li>
                    <li><a id="initDb" href="${pageContext.request.contextPath}/delete/article">Article_File DB初期化</a></li>
                </ul>

                <div>[EXHN03] 例外発生時のControllerの記述方法</div>
                <ul>
                    <li>
                        exhn0301001 (<a id="exhn0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> /
                        <a id="exhn0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0302001 first (<a id="exhn0302001First_jsp" href="${pageContext.request.contextPath}/jsp/0302/001/001">JSP</a> /
                        <a id="exhn0302001First_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001/001">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0302001 Second (<a id="exhn0302001Second_jsp" href="${pageContext.request.contextPath}/jsp/0302/001/002">JSP</a> /
                        <a id="exhn0302001Second_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001/002">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0302002 (<a id="exhn0302002_jsp" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a> /
                        <a id="exhn0302002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/002">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[EXHN06] SystemExceptionResolverの設定項目</div>
                <ul>
                    <li>
                        exhn0601001 (<a id="exhn0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001">JSP</a> /
                        <a id="exhn0601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0601002 (<a id="exhn0601002_jsp" href="${pageContext.request.contextPath}/jsp/0601/002">JSP</a> /
                        <a id="exhn0601002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/002">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0601003 (<a id="exhn0601003_jsp" href="${pageContext.request.contextPath}/jsp/0601/003">JSP</a> /
                        <a id="exhn0601003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/003">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0601004 (<a id="exhn0601004_jsp" href="${pageContext.request.contextPath}/jsp/0601/004">JSP</a> /
                        <a id="exhn0601004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/004">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0601005 (<a id="exhn0601005_jsp" href="${pageContext.request.contextPath}/jsp/0601/005">JSP</a> /
                        <a id="exhn0601005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/005">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0601006 (<a id="exhn0601006_jsp" href="${pageContext.request.contextPath}/jsp/0601/006">JSP</a> /
                        <a id="exhn0601006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/006">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[EXHN07] DefaultHandlerExceptionResolverで設定されるHTTPレスポンスコードの確認</div>
                <ul>
                    <li>
                        exhn0701001 (<a id="exhn0701001_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> /
                        <a id="exhn0701001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/001">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701002 (<a id="exhn0701002_jsp" href="${pageContext.request.contextPath}/jsp/0701/002">JSP</a> /
                        <a id="exhn0701002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/002">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701003 (<a id="exhn0701003_jsp" href="${pageContext.request.contextPath}/jsp/0701/003">JSP</a> /
                        <a id="exhn0701003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/003">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701004 (<a id="exhn0701004_jsp" href="${pageContext.request.contextPath}/jsp/0701/004">JSP</a> /
                        <a id="exhn0701004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/004">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701005 (<a id="exhn0701005_jsp" href="${pageContext.request.contextPath}/jsp/0701/005">JSP</a> /
                        <a id="exhn0701005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/005">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701006 (<a id="exhn0701006_jsp" href="${pageContext.request.contextPath}/jsp/0701/006">JSP</a> /
                        <a id="exhn0701006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/006">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701007 (<a id="exhn0701007_jsp" href="${pageContext.request.contextPath}/jsp/0701/007">JSP</a> /
                        <a id="exhn0701007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/007">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701008 (<a id="exhn0701008_jsp" href="${pageContext.request.contextPath}/jsp/0701/008">JSP</a> /
                        <a id="exhn0701008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/008">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701009 (<a id="exhn0701009_jsp" href="${pageContext.request.contextPath}/jsp/0701/009">JSP</a> /
                        <a id="exhn0701009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/009">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701010 (<a id="exhn0701010_jsp" href="${pageContext.request.contextPath}/jsp/0701/010">JSP</a> /
                        <a id="exhn0701010_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/010">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701011 (<a id="exhn0701011_jsp" href="${pageContext.request.contextPath}/jsp/0701/011">JSP</a> /
                        <a id="exhn0701011_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/011">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701012 (<a id="exhn0701012_jsp" href="${pageContext.request.contextPath}/jsp/0701/012">JSP</a> /
                        <a id="exhn0701012_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/012">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701013 (<a id="exhn0701013_jsp" href="${pageContext.request.contextPath}/jsp/0701/013">JSP</a> /
                        <a id="exhn0701013_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/013">Thymeleaf</a>)
                    </li>
                    <li>
                        exhn0701015 (<a id="exhn0701015_jsp" href="${pageContext.request.contextPath}/jsp/0701/015">JSP</a> /
                        <a id="exhn0701015_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/015">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[EXHN08] HandlerExceptionResolverLoggingInterceptorの拡張実装の確認</div>
                <ul>
                    <li>
                        exhn0801001 (<a id="exhn0801001_jsp" href="${pageContext.request.contextPath}/jsp/0801/001">JSP</a> /
                        <a id="exhn0801001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0801/001">Thymeleaf</a>)
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
