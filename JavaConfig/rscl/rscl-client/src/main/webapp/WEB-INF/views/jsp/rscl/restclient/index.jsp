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
                    <h3>RestTemplate</h3>
                    <ul>
                        <li class="title-header">＜Overview関連 - ClientHttpRequestFactory＞</li>
                        <li><a id="rstpContentLength1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0101001">Content-lengthの取得（バッファリングなし）</a></li>
                        <li><a id="rstpContentLength2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0102001">Content-lengthの取得（バッファリングあり）</a></li>
                        <li class="title-header">＜Overview関連 - HttpMessageConverter＞</li>
                        <li><a id="rstpConverter1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0201001">HttpMessageConverter（ByteArrayHttpMessageConverter）</a></li>
                        <li><a id="rstpConverter2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0202001">HttpMessageConverter（StringHttpMessageConverter）</a></li>
                        <li><a id="rstpConverter3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0203001">HttpMessageConverter（ResourceHttpMessageConverter）</a></li>
                        <li><a id="rstpConverter4" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0204001">HttpMessageConverter（SourceHttpMessageConverter）</a></li>
                        <li><a id="rstpConverter5" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0205001">HttpMessageConverter（AllEncompassingFormHttpMessageConverter-変換成功）</a></li>
                        <li><a id="rstpConverter6" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0206001">HttpMessageConverter（AllEncompassingFormHttpMessageConverter-変換失敗）</a></li>
                        <li><a id="rstpConverter7" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0207001">HttpMessageConverter（JacksonJsonHttpMessageConverter）</a></li>
                        <li><a id="rstpConverter8" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0208001">HttpMessageConverter（Jaxb2RootElementHttpMessageConverter）</a></li>
                        <li class="title-header">＜HowToUse関連 - BaseURL＞</li>
                        <li><a id="rstpBaseUrl1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0301001">BaseURL（相対URL）</a></li>
                        <li><a id="rstpBaseUrl2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0302001">BaseURL（絶対URL）</a></li>
                        <li class="title-header">＜HowToUse関連 - HTTPメソッド＞</li>
                        <li><a id="rstpGet" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0401001">GET送信-単一件取得（正常）</a></li>
                        <li><a id="rstpGetList" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0402001">GET送信-複数件取得（正常）</a></li>
                        <li><a id="rstpPost" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0403001">POST送信（正常）</a></li>
                        <li><a id="rstpPut" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0404001">PUT送信（正常）</a></li>
                        <li><a id="rstpPatch" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0405001">PATCH送信（正常）</a></li>
                        <li><a id="rstpDelete" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0406001">DELETE送信（正常）</a></li>
                        <li><a id="rstpHead" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0407001">HEAD送信（正常）</a></li>
                        <li class="title-header">＜HowToUse関連 - エラーハンドリング＞</li>
                        <li><a id="rstpErrorHandle1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0501001">エラーハンドリング（2xx系）</a></li>
                        <li><a id="rstpErrorHandle2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0502001">エラーハンドリング（4xx系）</a></li>
                        <li><a id="rstpErrorHandle3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0503001">エラーハンドリング（5xx系）</a></li>
                        <li><a id="rstpErrorHandle4" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0504001">エラーハンドリング（Unknown）</a></li>
                        <li><a id="rstpErrorHandle5" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0505001">エラーリトライ（成功）</a></li>
                        <li><a id="rstpErrorHandle6" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0506001">エラーリトライ（失敗）</a></li>
                        <li><a id="rstpStatusHandle1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0507001">ステータスコードハンドリング（2xx系）</a></li>
                        <li><a id="rstpStatusHandle2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0508001">ステータスコードハンドリング（4xx系）</a></li>
                        <li><a id="rstpStatusHandle3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0509001">ステータスコードハンドリング（5xx系）</a></li>
                        <li><a id="rstpStatusHandle4" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0510001">ステータスコードハンドリング（Unknown）</a></li>
                        <li><a id="rstpStatusHandle5" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0511001">ステータスコードハンドリング：NoOpResponseErrorHandler使用（2xx系）</a></li>
                        <li><a id="rstpStatusHandle6" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0512001">ステータスコードハンドリング：NoOpResponseErrorHandler使用（4xx系）</a></li>
                        <li><a id="rstpStatusHandle7" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0513001">ステータスコードハンドリング：NoOpResponseErrorHandler使用（5xx系）</a></li>
                        <li><a id="rstpStatusHandle8" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0514001">ステータスコードハンドリング：NoOpResponseErrorHandler使用（Unknown）</a></li>
                        <li><a id="rstpStatusHandle9" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0515001">ステータスコードリトライ（成功）</a></li>
                        <li><a id="rstpStatusHandle10" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0516001">ステータスコードリトライ（失敗）</a></li>
                        <li><br /></li>
                        <li><br /></li>
                        <li class="title-header">＜HowToUse関連 - 共通リクエストヘッダ＞</li>
                        <li><a id="rstpCommonRequestHeader" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0601001">共通リクエストヘッダーの設定</a></li>
                        <li><br /></li>
                        <li class="title-header">＜HowToUse関連 - 認証＞</li>
                        <li><a id="rstpAuth1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0701001">認証成功（Basic）</a></li>
                        <li><a id="rstpAuth2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0702001">認証失敗（Basic）</a></li>
                        <li><a id="rstpAuth3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0703001">認証未設定（Basic）</a></li>
                        <li class="title-header">＜HowToUse関連 - HttpComponentsClientHttpRequestFactoryのタイムアウト＞</li>
                        <li><a id="rstpTimeoutConnectTimeout" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0801001">Timeout（Connect Timeout）</a></li>
                        <li><a id="rstpTimeoutReadTimeout" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0802001">Timeout（Read Timeout）</a></li>
                        <li class="title-header">＜HowToUse関連 - ファイルアップロード＞</li>
                        <li><a id="rstpFileUpload" href="${pageContext.request.contextPath}/jsp/rscl/restclient/0901001">ファイルアップロード</a></li>
                        <li class="title-header">＜HowToUse関連 - ファイルダウンロード＞</li>
                        <li><a id="rstpFileDownload1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1001001">ファイルダウンロード（byte）</a></li>
                        <li><a id="rstpFileDownload2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1002001">ファイルダウンロード（Resource）</a></li>
                        <li><a id="rstpFileDownload3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1003001">ファイルダウンロード（InputStream：正常）</a></li>
                        <li><a id="rstpFileDownload4" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1004001">ファイルダウンロード（InputStream：エラー）</a></li>
                        <li><a id="rstpFileDownload5" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1005001">レスポンス情報取得（正常）</a></li>
                        <li><a id="rstpFileDownload6" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1006001">レスポンス情報取得（エラー）</a></li>
                        <li class="title-header">＜HowToExtends関連 - レスポンス取得、リクエスト設定＞</li>
                        <li><a id="rstpResponse" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1101001">レスポンス情報取得</a></li>
                        <li><a id="rstpRequest" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1102001">リクエスト情報設定</a></li>
                        <li class="title-header">＜HowToExtends関連 - 個別リクエストヘッダ＞</li>
                        <li><a id="rstpRequestHeader1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1201001">リクエストヘッダーの設定（Content-Type）</a></li>
                        <li><a id="rstpRequestHeader2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1202001">リクエストヘッダーの設定（Accept）</a></li>
                        <li><a id="rstpRequestHeader3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1203001">リクエストヘッダーの設定（Custom）</a></li>
                        <li class="title-header">＜HowToExtends関連 - 埋め込みパラメータ＞</li>
                        <li><a id="rstpUriVariables" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1301001">埋め込みパラメータの設定（LocalDate⇒String）</a></li>
                        <li class="title-header">＜HowToExtends関連 - SSL自己署名証明書＞</li>
                        <li><a id="rstpSslSelfSigned1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401001">SSL自己署名証明書の設定(サーバ1 : 0秒待機)</a></li>
                        <li><a id="rstpSslSelfSigned2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401002">SSL自己署名証明書の設定(サーバ1 : 6秒待機)</a></li>
                        <li><a id="rstpSslSelfSigned3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401003">SSL自己署名証明書の設定(サーバ1 : 6秒待機)</a></li>
                        <li><a id="rstpSslSelfSigned4" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401004">SSL自己署名証明書の設定(サーバ1 : 20秒待機)</a></li>
                        <li><a id="rstpSslSelfSigned5" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401005">SSL自己署名証明書の設定(サーバ2 : 6秒待機)</a></li>
                        <li><a id="rstpSslSelfSigned6" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401006">SSL自己署名証明書の設定(サーバ3 : 6秒待機)</a></li>
                        <li><a id="rstpSslSelfSigned7" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401007">SSL自己署名証明書の設定(存在しないサーバ)</a></li>
                        <li><a id="rstpSslSelfSigned8" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401008">SSL自己署名証明書の設定(サーバ4 : 0秒待機)</a></li>
                        <li><a id="rstpSslSelfSigned9" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1401009">SSL自己署名証明書の設定(サーバ4 : SoTimeout)</a></li>
                        <li class="title-header">＜HowToExtends関連 - カスタムコンバータ＞</li>
                        <li><a id="rstpCustomConverter1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1601001">カスタムコンバータの設定</a></li>
                        <li><a id="rstpCustomConverter2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1602001">カスタムコンバータの設定（application/json）</a></li>
                        <li class="title-header">＜HowToExtends関連 - リクエスト送信前後処理＞</li>
                        <li><a id="rstpInterceptors" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1701001">ロギングの設定</a></li>
                        <li><a id="rstpInterceptors2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1702001">複数インターセプターの設定</a></li>
                        <li class="title-header">＜Appendix関連 - RESTful URL＞</li>
                        <li><a id="rstpRestfulUrl1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1801001">RESTful URLの設定（getForObject）</a></li>
                        <li><a id="rstpRestfulUrl2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1802001">RESTful URLの設定（exchange）</a></li>
                        <li class="title-header">＜Appendix関連 - Proxy＞</li>
                        <li><a id="rstpProxy1" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1901001">Proxyの設定（HttpComponentsClientHttpRequestFactory）</a></li>
                        <li><a id="rstpProxy2" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1902001">Proxyの設定（ユーザ、パスワードあり-認証成功）</a></li>
                        <li><a id="rstpProxy3" href="${pageContext.request.contextPath}/jsp/rscl/restclient/1903001">Proxyの設定（ユーザ、パスワードあり-認証失敗）</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
