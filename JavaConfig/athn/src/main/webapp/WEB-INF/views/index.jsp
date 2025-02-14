<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.athn.index" />
    <c:set var="functionId" value="athn" />

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
                <h1 id="screenTitle">ATHN 認証</h1>

                <sec:authentication property="principal" var="principal" />

                <div>
                    ようこそ
                    <sec:authorize access="isAuthenticated()">
                        <span id="AuthenticateUserName">${f:h(principal.username)}</span>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <span id="AuthenticateUserName"><spring:message code="label.sf.cmmn.defaultUserName" /></span>
                    </sec:authorize>
                    さん
                </div>
                <br />

                <div>[ATHN01] フォーム認証</div>
                <ul>
                    <li>
                        athn0102001 (<a id="athn0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> /
                        <a id="athn0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[ATHN02] 認証成功時のレスポンス・[ATHN10] 認証処理とSpring MVCの連携・[ATHN11] フォーム認証の拡張・[ATHN14] ログアウトの拡張</div>
                <ul>
                    <li>
                        athn0201001 (<a id="athn0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001?loginSuccess">JSP</a> /
                        <a id="athn0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001?loginSuccess">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0201002 (<a id="athn0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/001/login">JSP</a> /
                        <a id="athn0201002_thymeleaf" href="${pageContext.request.contextPath}/jsp/0201/001/login">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[ATHN03] 認証失敗時のレスポンス</div>
                <ul>
                    <li>
                        athn0301001 (<a id="athn0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001/login">JSP</a> /
                        <a id="athn0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001/login">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[ATHN04] DB認証</div>
                <ul>
                    <li>
                        athn0401001 (<a id="athn0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001/login">JSP</a> /
                        <a id="athn0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0402001 (<a id="athn0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001/login">JSP</a> /
                        <a id="athn0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0402004 (<a id="athn0402004_jsp" href="${pageContext.request.contextPath}/jsp/0402/004/login">JSP</a> /
                        <a id="athn0402004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/004/login">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[ATHN05] パスワードのハッシュ化</div>
                <ul>
                    <li>
                        athn0501001 (<a id="athn0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> /
                        <a id="athn0501001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/001">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0501002 (<a id="athn0501002_jsp" href="${pageContext.request.contextPath}/jsp/0501/002/login">JSP</a> /
                        <a id="athn0501002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/002/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0501003 (<a id="athn0501003_jsp" href="${pageContext.request.contextPath}/jsp/0501/003">JSP</a> /
                        <a id="athn0501003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/003">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0501004 (<a id="athn0501004_jsp" href="${pageContext.request.contextPath}/jsp/0501/004/login">JSP</a> /
                        <a id="athn0501004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/004/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0501005 (<a id="athn0501005_jsp" href="${pageContext.request.contextPath}/jsp/0501/005">JSP</a> /
                        <a id="athn0501005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/005">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0501006 (<a id="athn0501006_jsp" href="${pageContext.request.contextPath}/jsp/0501/006/login">JSP</a> /
                        <a id="athn0501006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/006/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0501007 (<a id="athn0501007_jsp" href="${pageContext.request.contextPath}/jsp/0501/007">JSP</a> /
                        <a id="athn0501007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/007">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0501008 (<a id="athn0501008_jsp" href="${pageContext.request.contextPath}/jsp/0501/008/login">JSP</a> /
                        <a id="athn0501008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/008/login">Thymeleaf</a>)
                    </li>

                    <li>
                        athn0502001 (<a id="athn0502001_jsp" href="${pageContext.request.contextPath}/jsp/0502/001">JSP</a> /
                        <a id="athn0502001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/001">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0502002 (<a id="athn0502002_jsp" href="${pageContext.request.contextPath}/jsp/0502/002/login">JSP</a> /
                        <a id="athn0502002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/002/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0502003 (<a id="athn0502003_jsp" href="${pageContext.request.contextPath}/jsp/0502/003/login">JSP</a> /
                        <a id="athn0502003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/003/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0502004 (<a id="athn0502004_jsp" href="${pageContext.request.contextPath}/jsp/0502/004/login">JSP</a> /
                        <a id="athn0502004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/004/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0502005 (<a id="athn0502005_jsp" href="${pageContext.request.contextPath}/jsp/0502/005/login">JSP</a> /
                        <a id="athn0502005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/005/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN06] 認証イベントのハンドリング</div>
                <ul>
                    <li>
                        athn0601001 (<a id="athn0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001/login">JSP</a> /
                        <a id="athn0601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0602001 (<a id="athn0602001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001/login">JSP</a> /
                        <a id="athn0602001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0602002 (<a id="athn0602002_jsp" href="${pageContext.request.contextPath}/jsp/0602/002/login">JSP</a> /
                        <a id="athn0602002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/002/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN07] ログアウト・[ATHN08] ログアウト成功時のレスポンス</div>
                <ul>
                    <li>
                        athn0701001 (<a id="athn0701001_jsp" href="${pageContext.request.contextPath}/jsp/0701/001/login">JSP</a> /
                        <a id="athn0701001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/001/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn0702001 (<a id="athn0702001_jsp" href="${pageContext.request.contextPath}/jsp/0702/001/login">JSP</a> /
                        <a id="athn0702001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0702/001/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN09] 認証情報へのアクセス</div>
                <ul>
                    <li>
                        athn0901001 (<a id="athn0901001_jsp" href="${pageContext.request.contextPath}/jsp/0901/login">JSP</a> /
                        <a id="athn0901001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0901/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN12] 認証成功時のレスポンスの拡張</div>
                <ul>
                    <li>
                        athn1201001 (<a id="athn1201001_jsp" href="${pageContext.request.contextPath}/jsp/1201/001/afterLogin">JSP</a> /
                        <a id="athn1201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1201/001/afterLogin">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1202001 (<a id="athn1202001_jsp" href="${pageContext.request.contextPath}/jsp/1202/001/login">JSP</a> /
                        <a id="athn1202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1202/001/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN13] 認証失敗時のレスポンスの拡張</div>
                <ul>
                    <li>
                        athn1302001 (<a id="athn1302001_jsp" href="${pageContext.request.contextPath}/jsp/1302/001/login">JSP</a> /
                        <a id="athn1302001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1302/001/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN15] ログアウト成功時のレスポンスの拡張</div>
                <ul>
                    <li>
                        athn1501002 (<a id="athn1501002_jsp" href="${pageContext.request.contextPath}/jsp/1501/001/login">JSP</a> /
                        <a id="athn1501002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1501/001/login">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[ATHN16] エラーメッセージの拡張</div>
                <ul>
                    <li>
                        athn1601001 (<a id="athn1601001_jsp" href="${pageContext.request.contextPath}/jsp/1601/001/login">JSP</a> /
                        <a id="athn1601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1601/001/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1602001 (<a id="athn1602001_jsp" href="${pageContext.request.contextPath}/jsp/1602/001/login">JSP</a> /
                        <a id="athn1602001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1602/001/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN17] 業務処理向けの拡張</div>
                <ul>
                    <li>
                        athn1701001 (<a id="athn1701001_jsp" href="${pageContext.request.contextPath}/jsp/1701/001">JSP</a> /
                        <a id="athn1701001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1701/001">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1702001 (<a id="athn1702001_jsp" href="${pageContext.request.contextPath}/jsp/1702/001">JSP</a> /
                        <a id="athn1702001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1702/001">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1703001 (<a id="athn1703001_jsp" href="${pageContext.request.contextPath}/jsp/1703/001">JSP</a> /
                        <a id="athn1703001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1703/001">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN18] PasswordEncoderのカスタマイズ</div>
                <ul>
                    <li>
                        athn1801001 (<a id="athn1801001_jsp" href="${pageContext.request.contextPath}/jsp/1801/001">JSP</a> /
                        <a id="athn1801001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1801/001">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1801002 (<a id="athn1801002_jsp" href="${pageContext.request.contextPath}/jsp/1801/002/login">JSP</a> /
                        <a id="athn1801002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1801/002/login">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1801003 (<a id="athn1801003_jsp" href="${pageContext.request.contextPath}/jsp/1801/002/login">JSP</a> /
                        <a id="athn1801003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1801/002/login">Thymeleaf</a>)
                    </li>
                </ul>

                <div>[ATHN19] 非推奨パッケージのPasswordEncoderの利用</div>
                <ul>
                    <li>
                        athn1901001 (<a id="athn1901001_jsp" href="${pageContext.request.contextPath}/jsp/1901/001">JSP</a> /
                        <a id="athn1901001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1901/001">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1901002 (<a id="athn1901002_jsp" href="${pageContext.request.contextPath}/jsp/1901/002">JSP</a> /
                        <a id="athn1901002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1901/002">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1901003 (<a id="athn1901003_jsp" href="${pageContext.request.contextPath}/jsp/1901/003">JSP</a> /
                        <a id="athn1901003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1901/003">Thymeleaf</a>)
                    </li>
                    <li>
                        athn1901004 (<a id="athn1901004_jsp" href="${pageContext.request.contextPath}/jsp/1901/004">JSP</a> /
                        <a id="athn1901004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1901/004">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[ATHN21] 遷移先の指定が可能な認証成功ハンドラ</div>
                <ul>
                    <li>GFWの試験で実施するため試験不要</li>
                </ul>
                <div>[ATHN22] Spring MVCでリクエストを受けてログインフォームを表示する</div>
                <ul>
                    <li>本試験が全般的にSpring MVCを使用しているため、試験不要</li>
                </ul>
                <div>[ATHN22] Remeber Meの使用</div>
                <ul>
                    <li>
                        athn2201001 (<a id="athn2201001_jsp" href="${pageContext.request.contextPath}/jsp/2201/001">JSP</a> /
                        <a id="athn2201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/2201/001">Thymeleaf</a>)
                    </li>
                    <li>
                        athn2201001showInfo (<a id="athn2201001showInfo_jsp" href="${pageContext.request.contextPath}/jsp/2201?loginSuccess">JSP</a> /
                        <a id="athn2201001showInfo_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/2201?loginSuccess">Thymeleaf</a>)
                    </li>
                </ul>
                <div>[ATHN23] ログアウト成功時の認証イベントのハンドリング</div>
                <ul>
                    <li>
                        athn2301001 (<a id="athn2301001_jsp" href="${pageContext.request.contextPath}/jsp/2301/001/login">JSP</a> /
                        <a id="athn2301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/2301/001/login">Thymeleaf</a>)
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
