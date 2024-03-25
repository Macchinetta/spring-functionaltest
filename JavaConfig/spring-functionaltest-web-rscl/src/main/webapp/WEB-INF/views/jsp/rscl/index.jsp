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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
    <div id="wrapper">

      <h1 id="screenTitle">RSCL RESTクライアント処理</h1>

      <div>[RSCL01] RESTクライアントの実装</div>
      <ul>
        <li><a id="rscl0101001" href="${pageContext.request.contextPath}/jsp/rscl/0101/001">rscl0101001</a></li>
        <li><a id="rscl0102001" href="${pageContext.request.contextPath}/jsp/rscl/0102/001">rscl0102001</a></li>
        <li><a id="rscl0103001" href="${pageContext.request.contextPath}/jsp/rscl/0103/001">rscl0103001</a></li>
        <li><a id="rscl0104001" href="${pageContext.request.contextPath}/jsp/rscl/0104/001">rscl0104001</a></li>
        <li><a id="rscl0105001" href="${pageContext.request.contextPath}/jsp/rscl/0105/001">rscl0105001</a></li>
        <li><a id="rscl0106001" href="${pageContext.request.contextPath}/jsp/rscl/0106/001">rscl0106001</a></li>
        <li><a id="rscl0106002" href="${pageContext.request.contextPath}/jsp/rscl/0106/002">rscl0106002</a></li>
        <li><a id="rscl0107001" href="${pageContext.request.contextPath}/jsp/rscl/0107/001">rscl0107001</a></li>
        <li><a id="rscl0108001" href="${pageContext.request.contextPath}/jsp/rscl/0108/001">rscl0108001</a></li>
        <li><a id="rscl0109001" href="${pageContext.request.contextPath}/jsp/rscl/0109/001">rscl0109001</a></li>
        <li><a id="rscl0110001" href="${pageContext.request.contextPath}/jsp/rscl/0110/001">rscl0110001</a></li>
        <li><a id="rscl0111001" href="${pageContext.request.contextPath}/jsp/rscl/0111/001">rscl0111001</a></li>
        <li><a id="rscl0112001" href="${pageContext.request.contextPath}/jsp/rscl/0112/001">rscl0112001</a></li>
        <li><a id="rscl0113001" href="${pageContext.request.contextPath}/jsp/rscl/0113/001">rscl0113001</a></li>
      </ul>

      <div>[RSCL02] 各種HttpMessageConverterの利用</div>
      <ul>
        <li><a id="rscl0201001" href="${pageContext.request.contextPath}/jsp/rscl/0201/001">rscl0201001</a></li>
        <li><a id="rscl0202001" href="${pageContext.request.contextPath}/jsp/rscl/0202/001">rscl0202001</a></li>
        <li><a id="rscl0203001" href="${pageContext.request.contextPath}/jsp/rscl/0203/001">rscl0203001</a></li>
        <li><a id="rscl0204001" href="${pageContext.request.contextPath}/jsp/rscl/0204/001">rscl0204001</a></li>
        <li><a id="rscl0205001" href="${pageContext.request.contextPath}/jsp/rscl/0205/001">rscl0205001</a></li>
        <li><a id="rscl0209001" href="${pageContext.request.contextPath}/jsp/rscl/0209/001">rscl0209001</a></li>
        <li><a id="rscl0211001" href="${pageContext.request.contextPath}/jsp/rscl/0211/001">rscl0211001</a></li>
      </ul>

      <div>[RSCL03] リクエストヘッダの設定</div>
      <ul>
        <li><a id="rscl0303001" href="${pageContext.request.contextPath}/jsp/rscl/0303/001">rscl0303001</a></li>
      </ul>

      <div>[RSCL04] 例外ハンドリング</div>
      <ul>
        <li><a id="rscl0402001" href="${pageContext.request.contextPath}/jsp/rscl/0402/001">rscl0402001</a></li>
        <li><a id="rscl0403001" href="${pageContext.request.contextPath}/jsp/rscl/0403/001">rscl0403001</a></li>
        <li><a id="rscl0404001" href="${pageContext.request.contextPath}/jsp/rscl/0404/001">rscl0404001</a></li>
        <li><a id="rscl0405001" href="${pageContext.request.contextPath}/jsp/rscl/0405/001">rscl0405001</a></li>
        <li><a id="rscl0406001" href="${pageContext.request.contextPath}/jsp/rscl/0406/001">rscl0406001</a></li>
      </ul>

      <div>[RSCL05] タイムアウト設定</div>
      <ul>
        <li><a id="rscl0501001" href="${pageContext.request.contextPath}/jsp/rscl/0501/001">rscl0501001</a></li>
        <li><a id="rscl0502001" href="${pageContext.request.contextPath}/jsp/rscl/0502/001">rscl0502001</a></li>
      </ul>

      <div>[RSCL06] HTTPS接続</div>
      <div>[RSCL07] 自己署名証明書</div>
      <ul>
        <li><a id="rscl0601001" href="${pageContext.request.contextPath}/jsp/rscl/0601/001">rscl0601001</a> (サーバー1 : 0秒待機)</li>
        <li><a id="rscl0601002" href="${pageContext.request.contextPath}/jsp/rscl/0601/002">rscl0601002</a> (サーバー1 : 6秒待機)</li>
        <li><a id="rscl0601003" href="${pageContext.request.contextPath}/jsp/rscl/0601/003">rscl0601003</a> (サーバー1 : 6秒待機)</li>
        <li><a id="rscl0601004" href="${pageContext.request.contextPath}/jsp/rscl/0601/004">rscl0601004</a> (サーバー1 : 20秒待機)</li>
        <li><a id="rscl0601005" href="${pageContext.request.contextPath}/jsp/rscl/0601/005">rscl0601005</a> (サーバー2 : 6秒待機)</li>
        <li><a id="rscl0601006" href="${pageContext.request.contextPath}/jsp/rscl/0601/006">rscl0601006</a> (サーバー3 : 6秒待機)</li>
        <li><a id="rscl0601007" href="${pageContext.request.contextPath}/jsp/rscl/0601/007">rscl0601007</a> (存在しないサーバ)</li>
        <li><a id="rscl0601008" href="${pageContext.request.contextPath}/jsp/rscl/0601/008">rscl0601008</a> (サーバー4 : 0秒待機)</li>
        <li><a id="rscl0601009" href="${pageContext.request.contextPath}/jsp/rscl/0601/009">rscl0601009</a> (サーバー4 : SoTimeout)</li>
      </ul>

      <div>[RSCL09] ファイルアップロード</div>
      <ul>
        <li><a id="rscl0901001" href="${pageContext.request.contextPath}/jsp/rscl/0901/001">rscl0901001</a></li>
      </ul>

      <div>[RSCL11] RESTクライアントの拡張</div>
      <ul>
        <li><a id="rscl1102001" href="${pageContext.request.contextPath}/jsp/rscl/1102/001">rscl1102001</a></li>
        <li><a id="rscl1103002" href="${pageContext.request.contextPath}/jsp/rscl/1103/002">rscl1103002</a></li>
        <li><a id="rscl1104001" href="${pageContext.request.contextPath}/jsp/rscl/1104/001">rscl1104001</a></li>
        <li><a id="rscl1105001" href="${pageContext.request.contextPath}/jsp/rscl/1105/001">rscl1105001</a></li>
      </ul>

      <div>[RSCL14] HTTP Proxyの設定</div>
      <ul>
        <li><a id="rscl1401001" href="${pageContext.request.contextPath}/jsp/rscl/1401/001">rscl1401001</a></li>
        <li><a id="rscl1401002" href="${pageContext.request.contextPath}/jsp/rscl/1401/002">rscl1401002</a></li>
        <li><a id="rscl1402001" href="${pageContext.request.contextPath}/jsp/rscl/1402/001">rscl1402001</a></li>
      </ul>

    </div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>