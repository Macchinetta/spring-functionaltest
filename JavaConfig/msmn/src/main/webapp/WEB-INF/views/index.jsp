<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.xspr.index" />
    <c:set var="functionId" value="msmn" />

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
                <h1 id="screenTitle">メッセージ管理</h1>

                <div>[MSMN01] プロパティファイルに設定したメッセージの表示</div>
                <ul>
                    <li>
                        [MSMN0101001] プロパティを使用する際の設定 (JSP / Thymeleaf )
                    </li>
                    <li>
                        [MSMN0102001] プロパティに設定したメッセージの表示(ISO-8859-1) (<a id="msmn0102001_jsp" href="${pageContext.request.contextPath}/jsp/01/02/001">JSP</a> /
                        <a id="msmn0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/01/02/001">Thymeleaf</a> )
                    </li>
                    <li>
                        [MSMN0102002] プロパティに設定したメッセージの表示(UTF-8) (<a id="msmn0102002_jsp" href="${pageContext.request.contextPath}/jsp/01/02/002">JSP</a> /
                        <a id="msmn0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/01/02/002">Thymeleaf</a> )
                    </li>
                    <li>
                        [MSMN0102003] 複数のプロパティ(ロケールの切り替え) (JSP / Thymeleaf) ※国際化で実施
                    </li>
                    <li>
                        [MSMN0102004] 複数のプロパティ ※MSMN0102001で実施
                    </li>
                </ul>

                <div>[MSMN02] 結果メッセージの表示</div>
                <ul>
                    <li>
                        [MSMN0201001] 通常 (<a id="msmn0201001_jsp" href="${pageContext.request.contextPath}/jsp/02/01/001">JSP</a> /
                        <a id="msmn0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/02/01/001">Thymeleaf</a> )
                    </li>
                    <li>
                        [MSMN0202001] 複数メッセージ (<a id="msmn0202001_jsp" href="${pageContext.request.contextPath}/jsp/02/02/001">JSP</a> /
                        <a id="msmn0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/02/02/001">Thymeleaf</a> )
                    </li>
                    <li>
                        [MSMN0203001] プレースホルダー (JSP / Thymeleaf) ※MSMN0201002で実施
                    </li>
                    <li>
                        [MSMN0203002] カンマあり (JSP / Thymeleaf) ※MSMN0201002でで実施
                    </li>
                    <li>
                        [MSMN0203003] カンマなし (<a id="msmn0203003_jsp" href="${pageContext.request.contextPath}/jsp/02/03/003">JSP</a> /
                        <a id="msmn0203003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/02/03/003">Thymeleaf</a> )
                    </li>
                    <li>
                        [MSMN0204001] 結果メッセージの表示（メッセージタイプの設定） (JSP / Thymeleaf) ※MSMN0205001で実施
                    </li>
                    <li>
                        [MSMN0205001] 結果メッセージの表示（属性の指定） (<a id="msmn0205001_jsp" href="${pageContext.request.contextPath}/jsp/02/05/001">JSP</a> /
                        <a id="msmn0205001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/02/05/001">Thymeleaf</a> )
                    </li>
                    <li>
                        [MSMN0206] 業務例外メッセージの表示 (<a id="msmn0206_jsp" href="${pageContext.request.contextPath}/jsp/02/06">JSP</a> /
                        <a id="msmn0206_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/02/06">Thymeleaf</a> )
                    </li>
                </ul>

                <div>[MSMN03] 独自メッセージタイプの作成</div>
                <ul>
                    <li>
                        [MSMN0301001] 独自メッセージタイプの作成(<a id="msmn0301001_jsp" href="${pageContext.request.contextPath}/jsp/03/01/001">JSP</a> /
                        <a id="msmn0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/03/01/001">Thymeleaf</a> )
                    </li>
                </ul>
                
                <div>[MSMN04] &lt;t:messagesPanel&gt;タグの属性変更</div>
                <ul>
                    <li>
                        [MSMN04] &lt;t:messagesPanel&gt;タグの属性変更 (<a id="msmn04_jsp" href="${pageContext.request.contextPath}/jsp/04">JSP</a> / Thymeleaf )
                    </li>
                </ul>
                
                <div>[MSMN05] ResultMessagesを使用しない結果メッセージの表示</div>
                <ul>
                    <li>
                        [MSMN05] ResultMessage以外を出力 (<a id="msmn05_jsp" href="${pageContext.request.contextPath}/jsp/05">JSP</a> / Thymeleaf )
                    </li>
                </ul>
                
                <div>[MSMN06] メッセージキー定数クラスの自動生成ツール</div>
                <ul>
                    <li>
                        [MSMN0601001] メッセージキー定数クラスの自動生成 (JSP / Thymeleaf) ※手動で実施
                    </li>
                    <li>
                        [MSMN0601002] 定数を使用したメッセージの表示  (<a id="msmn0601002_jsp" href="${pageContext.request.contextPath}/jsp/06/01/002">JSP</a> /
                        <a id="msmn0601002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/06/01/002">Thymeleaf</a> )
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
