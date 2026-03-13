<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <c:set var="titleKey" value="title.rscl.index" />
    <c:set var="functionId" value="rscl" />

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width" />
        <meta name="contextPath" content="${pageContext.request.contextPath}" />
        <sec:csrfMetaTags />
        <title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
        <script type="text/javascript"></script>
        <style>
            .container {
                width: 100%;
            }
            .container h1 {
                width: 100%;
                text-align: center;
                font-size: 24px;
                margin-bottom: 20px;
            }
            .card-list {
                justify-items: center;
                grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
            }
            .card-list > .card:only-child {
                max-width: 1000px;
                width: 100%;
                margin-left: auto;
                margin-right: auto;
            }
            .card {
                box-sizing: border-box;
                padding: 16px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #f9f9f9;
                display: flex;
                flex-direction: column;
            }
            .card ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }
            .card li {
                padding: 8px 0;
                border-bottom: 1px solid #eee;
            }
            .card li:last-child {
                border-bottom: none;
            }
            .title-header {
                font-size: 18px;
                font-weight: bold;
                margin-bottom: 12px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
        <div class="container">
            <h1 id="screenTitle">RSCL RESTクライアント処理</h1>
            <div class="card-list">
                <div class="card">
                    <h3>Web Client</h3>
                    <ul>
                        <li class="title-header">Appendix -  非同期通信のデータ登録＞</li>
                        <li><a id="wbclEntry1" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4801001">CompletableFutureを利用したデータの登録（非同期）</a></li>
                        <li><a id="wbclEntry2" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4802001">CompletableFutureを利用したデータの登録（同期）</a></li>
                        <li class="title-header">Appendix -  エラーハンドリング＞</li>
                        <li><a id="wbclError1" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4901001">アプリケーション全体でエラーハンドリング</a></li>
                        <li><a id="wbclError2" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4902001">リクエスト毎でエラーハンドリング</a></li>
                        <li><a id="wbclError3" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4903001">例外のエラーハンドリング（2xx系）</a></li>
                        <li><a id="wbclError4" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4904001">例外のエラーハンドリング（4xx系）</a></li>
                        <li><a id="wbclError5" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4905001">例外のエラーハンドリング（5xx系）</a></li>
                        <li><a id="wbclError6" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4906001">例外のエラーハンドリング（Unknown）</a></li>
                        <li><a id="wbclError7" href="${pageContext.request.contextPath}/jsp/rscl/webclient/4907001">例外のエラーハンドリング（ストリーム中の例外）</a></li>
                        <li class="title-header">Appendix -  リクエスト送信前の共通処理の適用＞</li>
                        <li><a id="wbclFilter1" href="${pageContext.request.contextPath}/jsp/rscl/webclient/5001001">ExchangeFilterFunctionの設定(ロギング)</a></li>
                        <li><a id="wbclFilter2" href="${pageContext.request.contextPath}/jsp/rscl/webclient/5002001">ExchangeFilterFunctionの設定(ヘッダ確認)</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
