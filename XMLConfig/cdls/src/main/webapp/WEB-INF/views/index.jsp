<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.cdls.index" />
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
                <h1 id="screenTitle">CDLS コードリスト機能</h1>

                <div>[CDLS01] コードリストの読み込み</div>
                <ul>
                    <li>
                        cdls0101001 (<a id="cdls0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> /
                        <a id="cdls0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0101002 (<a id="cdls0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> /
                        <a id="cdls0101002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/002">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0102001 (<a id="cdls0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> /
                        <a id="cdls0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0102002 (<a id="cdls0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> /
                        <a id="cdls0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0103001 (<a id="cdls0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> /
                        <a id="cdls0103001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0103002 (<a id="cdls0103002_jsp" href="${pageContext.request.contextPath}/jsp/0103/002">JSP</a> /
                        <a id="cdls0103002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/002">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0103003 (<a id="cdls0103003_jsp" href="${pageContext.request.contextPath}/jsp/0103/003">JSP</a> /
                        <a id="cdls0103003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/003">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0104001 (<a id="cdls0104001_jsp" href="${pageContext.request.contextPath}/jsp/0104/001">JSP</a> /
                        <a id="cdls0104001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0104/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0104002 (<a id="cdls0104002_jsp" href="${pageContext.request.contextPath}/jsp/0104/002">JSP</a> /
                        <a id="cdls0104002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0104/002">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105001 (<a id="cdls0105001_jsp" href="${pageContext.request.contextPath}/jsp/0105/001?locale=ja">JSP</a> /
                        <a id="cdls0105001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/001?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105002 (<a id="cdls0105002_jsp" href="${pageContext.request.contextPath}/jsp/0105/002?locale=en">JSP</a> /
                        <a id="cdls0105002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/002?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105003 (<a id="cdls0105003_jsp" href="${pageContext.request.contextPath}/jsp/0105/003?locale=ja">JSP</a> /
                        <a id="cdls0105003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/003?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105004 (<a id="cdls0105004_jsp" href="${pageContext.request.contextPath}/jsp/0105/004?locale=en">JSP</a> /
                        <a id="cdls0105004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/004?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105005 (<a id="cdls0105005_jsp" href="${pageContext.request.contextPath}/jsp/0105/005?locale=ja">JSP</a> /
                        <a id="cdls0105005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/005?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105006 (<a id="cdls0105006_jsp" href="${pageContext.request.contextPath}/jsp/0105/006?locale=en">JSP</a> /
                        <a id="cdls0105006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/006?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105007 (<a id="cdls0105007_jsp" href="${pageContext.request.contextPath}/jsp/0105/007?locale=ja">JSP</a> /
                        <a id="cdls0105007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/007?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0105008 (<a id="cdls0105008_jsp" href="${pageContext.request.contextPath}/jsp/0105/008?locale=en">JSP</a> /
                        <a id="cdls0105008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0105/008?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0106001 (<a id="cdls0106001_jsp" href="${pageContext.request.contextPath}/jsp/0106/001">JSP</a> /
                        <a id="cdls0106001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0106/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0107001 (<a id="cdls0107001_jsp" href="${pageContext.request.contextPath}/jsp/0107/001">JSP</a> /
                        <a id="cdls0107001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0107/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0107002 (<a id="cdls0107002_jsp" href="${pageContext.request.contextPath}/jsp/0107/002">JSP</a> /
                        <a id="cdls0107002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0107/002">Thymeleaf</a> )
                    </li>
                </ul>
                <div>[CDLS02] コードリストのリロード</div>
                <ul>
                    <li>
                        cdls0201001 (<a id="cdls0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> /
                        <a id="cdls0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0201002 (<a id="cdls0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> /
                        <a id="cdls0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0202001 (<a id="cdls0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001?locale=ja">JSP</a> /
                        <a id="cdls0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0202002 (<a id="cdls0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/002?locale=en">JSP</a> /
                        <a id="cdls0202002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/002?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0202003 (<a id="cdls0202003_jsp" href="${pageContext.request.contextPath}/jsp/0202/003?locale=ja">JSP</a> /
                        <a id="cdls0202003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/003?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0202004 (<a id="cdls0202004_jsp" href="${pageContext.request.contextPath}/jsp/0202/004?locale=en">JSP</a> /
                        <a id="cdls0202004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/004?locale=en">Thymeleaf</a> )
                    </li>
                </ul>
                <div>[CDLS03] コードリストの独自カスタマイズ</div>
                <ul>
                    <li>
                        cdls0301001 (<a id="cdls0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> /
                        <a id="cdls0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a> )
                    </li>
                </ul>
                <div>[CDLS04] コードリストの記述方法のバリエーション</div>
                <ul>
                    <li>
                        cdls0401001 (<a id="cdls0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001?locale=ja">JSP</a> /
                        <a id="cdls0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0401002 (<a id="cdls0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002?locale=en">JSP</a> /
                        <a id="cdls0401002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/002?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0402001 (<a id="cdls0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001?locale=ja">JSP</a> /
                        <a id="cdls0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0402002 (<a id="cdls0402002_jsp" href="${pageContext.request.contextPath}/jsp/0402/002?locale=en">JSP</a> /
                        <a id="cdls0402002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/002?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0403001 (<a id="cdls0403001_jsp" href="${pageContext.request.contextPath}/jsp/0403/001">JSP</a> /
                        <a id="cdls0403001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0403002 (<a id="cdls0403002_jsp" href="${pageContext.request.contextPath}/jsp/0403/002">JSP</a> /
                        <a id="cdls0403002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/002">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0403003 (<a id="cdls0403003_jsp" href="${pageContext.request.contextPath}/jsp/0403/003">JSP</a> /
                        <a id="cdls0403003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/003">Thymeleaf</a> )
                    </li>
                </ul>
                <div>[CDLS05] SpEL式でのコードリスト参照</div>
                <ul>
                    <li>
                        cdls0501001 (<a id="cdls0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> /
                        <a id="cdls0501001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/001">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0501002 (<a id="cdls0501002_jsp" href="${pageContext.request.contextPath}/jsp/0501/002?locale=ja">JSP</a> /
                        <a id="cdls0501002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/002?locale=ja">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0501003 (<a id="cdls0501003_jsp" href="${pageContext.request.contextPath}/jsp/0501/003?locale=en">JSP</a> /
                        <a id="cdls0501003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/003?locale=en">Thymeleaf</a> )
                    </li>
                    <li>
                        cdls0501004 (<a id="cdls0501004_jsp" href="${pageContext.request.contextPath}/jsp/0501/004?locale=de">JSP</a> /
                        <a id="cdls0501004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/004?locale=de">Thymeleaf</a> )
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
