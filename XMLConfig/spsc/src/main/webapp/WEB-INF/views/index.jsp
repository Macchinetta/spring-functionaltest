<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.spsc.index" />
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
                <h1 id="screenTitle">Spring Security 基本設定</h1>

                <div>[spsc01] spring security の基本設定</div>
                <ul>
                    <li>
                        spsc0101001 (<a id="spsc0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> /
                        <a id="spsc0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[spsc02] セキュリティヘッダ出力機能の適用</div>
                <ul>
                    <li>
                        spsc0201001 (<a id="spsc0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> /
                        <a id="spsc0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0201002 (<a id="spsc0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> /
                        <a id="spsc0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0201003 (<a id="spsc0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> /
                        <a id="spsc0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/003">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0201004 (<a id="spsc0201004_jsp" href="${pageContext.request.contextPath}/jsp/0201/004">JSP</a> /
                        <a id="spsc0201004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/004">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0201005 (<a id="spsc0201005_jsp" href="${pageContext.request.contextPath}/jsp/0201/005">JSP</a> /
                        <a id="spsc0201005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/005">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0201006 (<a id="spsc0201006_jsp" href="${pageContext.request.contextPath}/jsp/0201/006">JSP</a> /
                        <a id="spsc0201006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/006">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[spsc03] カスタムヘッダの出力</div>
                <ul>
                    <li>
                        spsc0301001 (<a id="spsc0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> /
                        <a id="spsc0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[spsc04] リクエストパターン毎のセキュリティヘッダの出力</div>
                <ul>
                    <li>
                        spsc0401001(Not Secure) (<a id="spsc0401001NotSecure_jsp" href="${pageContext.request.contextPath}/jsp/0401/001/notsecure/001">JSP</a> /
                        <a id="spsc0401001NotSecure_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001/notsecure/001">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0401001(Secure) (<a id="spsc0401001Secure_jsp" href="${pageContext.request.contextPath}/jsp/0401/001/secure/001">JSP</a> /
                        <a id="spsc0401001Secure_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001/secure/001">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[spsc05] CSPセキュリティヘッダの出力</div>
                <ul>
                    <li>
                        spsc0501001 (<a id="spsc0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> /
                        <a id="spsc0501001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/001">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0501002 (<a id="spsc0501002_jsp" href="${pageContext.request.contextPath}/jsp/0501/002">JSP</a> /
                        <a id="spsc0501002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/002">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0501003 (<a id="spsc0501003_jsp" href="${pageContext.request.contextPath}/jsp/0501/003">JSP</a> /
                        <a id="spsc0501003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/003">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0501004 (<a id="spsc0501004_jsp" href="${pageContext.request.contextPath}/jsp/0501/004">JSP</a> /
                        <a id="spsc0501004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/004">Thymeleaf</a>)
                    </li>
                    <li>
                        spsc0501005 (<a id="spsc0501005_jsp" href="${pageContext.request.contextPath}/jsp/0501/005">JSP</a> /
                        <a id="spsc0501005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/005">Thymeleaf</a>)
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
