<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.welcome.index" />
<c:set var="functionId" value="welcome" />

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<sec:csrfMetaTags />
<title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message
    code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
<script type="text/javascript"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">Spring Functional Test Top Page</h1>

  <ul>
    <li><a id="ajaxLink" href="${pageContext.request.contextPath}-ajax">[AJAX] AJAX機能テスト</a></li>
    <li><a id="aplyLink" href="${pageContext.request.contextPath}-aply">[APLY] アプリケーション層テスト</a></li>
    <li><a id="athnLink" href="${pageContext.request.contextPath}-athn">[ATHN] 認証機能テスト</a></li>
    <li><a id="athrLink" href="${pageContext.request.contextPath}-athr">[ATHR] 認可機能テスト</a></li>
    <li><a id="cdlsLink" href="${pageContext.request.contextPath}-cdls">[CDLS] コードリストテスト</a></li>
    <li><a id="csprLink" href="${pageContext.request.contextPath}-cspr">[CSPR] CSRF対策機能テスト</a></li>
    <li><a id="dbspLink" href="${pageContext.request.contextPath}-dbsp">[DBSP] 二重送信防止テスト</a></li>
    <li><a id="dmlyLink" href="${pageContext.request.contextPath}-dmly">[DMLY] ドメイン層テスト</a></li>
    <li><a id="dam3Link" href="${pageContext.request.contextPath}-dam3">[DAM3] データベースアクセス（Mybatis3編）</a></li>
    <%-- <li><a id="dam3mb3Link" href="${pageContext.request.contextPath}/dam3mb3/">[DAM3] データベースアクセス（Mybatis3編：追加）</a></li> --%>
    <li><a id="djpaLink" href="${pageContext.request.contextPath}-djpa">[DJPA] JPA Confirmation</a></li>
    <li><a id="dntaLink" href="${pageContext.request.contextPath}-dnta">[DNTA] 日付操作(Date and Time API)機能テスト</a></li>
    <li><a id="dtacLink" href="${pageContext.request.contextPath}-dtac">[DTAC] データベースアクセス（共通編）</a></li>
    <li><a id="dtopLink" href="${pageContext.request.contextPath}-dtop">[DTOP] 日付操作(Joda Time)機能テスト</a></li>
    <li><a id="emalLink" href="${pageContext.request.contextPath}-emal">[EMAL] E-mail送信(SMTP)</a></li>
    <li><a id="exhnLink" href="${pageContext.request.contextPath}-exhn">[EXHN] 例外ハンドリング機能テスト</a></li>
    <li><a id="excnLink" href="${pageContext.request.contextPath}-excn">[EXCN] 排他制御機能テスト</a></li>    
    <li><a id="encrLink" href="${pageContext.request.contextPath}-encr">[ENCR] 暗号化テスト</a></li>
    <li><a id="fldwLink" href="${pageContext.request.contextPath}-fldw">[FLDW] ファイルダウンロード機能テスト</a></li>
    <li><a id="flupLink" href="${pageContext.request.contextPath}-flup">[FLUP] ファイルアップロード 機能テスト</a></li>
    <li><a id="hlchLink" href="${pageContext.request.contextPath}-hlch">[HLCH] ヘルスチェックテスト</a></li>
    <li><a id="intrLink" href="${pageContext.request.contextPath}-intr">[INTR] 国際化機能テスト</a></li>
    <li><a id="jmssLink" href="${pageContext.request.contextPath}-jmss">[JMSS] JMSテスト</a></li>
    <li><a id="stprLink" href="${pageContext.request.contextPath}-lggn">[LGGN] ロギングテスト</a></li>
    <li><a id="oth2Link" href="${pageContext.request.contextPath}-oauth2-client">[OTH2] OAuth2テスト（Spring Security）</a></li>
    <li><a id="pgntLink" href="${pageContext.request.contextPath}-pgnt">[PGNT] ページネーション機能テスト</a></li>
    <li><a id="prmnLink" href="${pageContext.request.contextPath}-prmn">[PRMN] プロパティ管理機能テスト</a></li>
    <li><a id="rsclLink" href="${pageContext.request.contextPath}-rscl">[RSCL] RESTクライアントテスト</a></li>
    <%-- FY2022 SOAP スコープアウト --%>
    <%-- <li><a id="soapLink" href="${pageContext.request.contextPath}-soap">[SOAP] SOAPテスト</a></li> --%>
    <li><a id="spsmLink" href="${pageContext.request.contextPath}-spsm">[SPSM] SpringSecurityセッション管理テスト</a></li>
    <li><a id="spscLink" href="${pageContext.request.contextPath}-spsc">[SPSC] Spring Security(header)テスト</a></li>        
    <li><a id="ssmnLink" href="${pageContext.request.contextPath}-ssmn">[SSMN] セッション管理機能テスト</a></li>
    <li><a id="stprLink" href="${pageContext.request.contextPath}-stpr">[STPR] 文字列操作テスト</a></li>
    <li><a id="sydtLink" href="${pageContext.request.contextPath}-sydt">[SYDT] システム時刻機能テスト</a></li>
    <li><a id="vldtLink" href="${pageContext.request.contextPath}-vldt">[VLDT] 入力チェック機能テスト</a></li>
    <li><a id="wbclLink" href="${pageContext.request.contextPath}-wbcl">[WBCL] WebClientテスト</a></li>
    <li><a id="xsprLink" href="${pageContext.request.contextPath}-xspr">[XSPR] XSS対策</a></li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>