<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.jmss.index" />
<c:set var="functionId" value="jmss" />

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

  <h1 id="screenTitle">JMSS JMS (Java Message Service)</h1>

  <div>[INIT] 初期処理</div>
  <ul>
    <li><a id="キューのクリーン処理"
      href="${pageContext.request.contextPath}/jmss/queuePurge">queueクリア</a></li>
    <li><a id="トピックのクリーン処理"
      href="${pageContext.request.contextPath}/jmss/topicPurge">topicクリア</a></li>
  </ul>

  <div>[JMSS01] コネクションファクトリの定義方法</div>
  <ul>
    <li><a id="jmss0101001" href="${pageContext.request.contextPath}/jmss/0101/001">jmss0101001</a></li>
    <li><a id="jmss0102001" href="${pageContext.request.contextPath}/jmss/0102/001">jmss0102001</a></li>
    <li><a id="jmss0103001" href="${pageContext.request.contextPath}/jmss/0103/001">jmss0103001</a></li>
  </ul>

  <div>[JMSS02] Destinationの名前解決</div>
  <ul>
    <li>jmss0201001 (jmss0101001で実施)</li>
    <li>jmss0202001 (jmss0102001で実施)</li>
  </ul>

  <div>[JMSS03] メッセージ送信</div>
  <ul>
    <li><a id="jmss0301001" href="${pageContext.request.contextPath}/jmss/0301/001">jmss0301001</a></li>
    <li>jmss0301002 (jmss0103001で実施)</li>
    <li><a id="jmss0301003" href="${pageContext.request.contextPath}/jmss/0301/003">jmss0301003</a></li>
    <li><a id="jmss0301004" href="${pageContext.request.contextPath}/jmss/0301/004">jmss0301004</a></li>
    <li><a id="jmss0302001" href="${pageContext.request.contextPath}/jmss/0302/001">jmss0302001</a></li>
    <li><a id="jmss0303001" href="${pageContext.request.contextPath}/jmss/0303/001">jmss0303001</a></li>
    <li><a id="jmss0303002" href="${pageContext.request.contextPath}/jmss/0303/002">jmss0303002</a></li>
  </ul>

  <div>[JMSS04] メッセージ非同期受信</div>
  <ul>
    <li>jmss0401001 (jmss0103001で実施)</li>
    <li><a id="jmss0401002" href="${pageContext.request.contextPath}/jmss/0401/002">jmss0401002</a></li>
    <li><a id="jmss0401003" href="${pageContext.request.contextPath}/jmss/0401/003">jmss0401003</a></li>
    <li>jmss0401004 (jmss0303001で実施)</li>
    <li><a id="jmss0402001" href="${pageContext.request.contextPath}/jmss/0402/001">jmss0402001</a></li>
    <li><a id="jmss0403001" href="${pageContext.request.contextPath}/jmss/0403/001">jmss0403001</a></li>
    <li><a id="jmss0403002" href="${pageContext.request.contextPath}/jmss/0403/002">jmss0403002</a></li>
    <li><a id="jmss0403003" href="${pageContext.request.contextPath}/jmss/0403/003">jmss0403003</a></li>
    <li><a id="jmss0404001" href="${pageContext.request.contextPath}/jmss/0404/001">jmss0404001</a></li>
    <li><a id="jmss0404002" href="${pageContext.request.contextPath}/jmss/0404/002">jmss0404002</a></li>
  </ul>

  <div>[JMSS05] メッセージの同期受信</div>
  <ul>
    <li><a id="jmss0501001" href="${pageContext.request.contextPath}/jmss/0501/001">jmss0501001</a></li>
  </ul>

  <div>[JMSS06] トランザクション管理</div>
  <ul>
    <li><a id="jmss0601001" href="${pageContext.request.contextPath}/jmss/0601/001">jmss0601001</a></li>
    <li><a id="jmss0601002" href="${pageContext.request.contextPath}/jmss/0601/002">jmss0601002</a></li>
    <li><a id="jmss0602001" href="${pageContext.request.contextPath}/jmss/0602/001">jmss0602001</a></li>
    <li><a id="jmss0602002" href="${pageContext.request.contextPath}/jmss/0602/002">jmss0602002</a></li>
    <li><a id="jmss0603001" href="${pageContext.request.contextPath}/jmss/0603/001">jmss0603001</a></li>
    <li><a id="jmss0603002" href="${pageContext.request.contextPath}/jmss/0603/002">jmss0603002</a></li>
    <li><a id="jmss0604001" href="${pageContext.request.contextPath}/jmss/0604/001">jmss0604001</a></li>
    <li><a id="jmss0604002" href="${pageContext.request.contextPath}/jmss/0604/002">jmss0604002</a></li>
    <li><a id="jmss0604005" href="${pageContext.request.contextPath}/jmss/0604/005">jmss0604005</a></li>
    <li><a id="jmss0604006" href="${pageContext.request.contextPath}/jmss/0604/006">jmss0604006</a></li>
  </ul>

  <div>[JMSS07] 受信データの入力チェック</div>
  <ul>
    <li><a id="jmss0701001" href="${pageContext.request.contextPath}/jmss/0701/001">jmss0701001</a></li>
    <li><a id="jmss0701002" href="${pageContext.request.contextPath}/jmss/0701/002">jmss0701002</a></li>
    <li><a id="jmss0701003" href="${pageContext.request.contextPath}/jmss/0701/003">jmss0701003</a></li>
    <li><a id="jmss0701004" href="${pageContext.request.contextPath}/jmss/0701/004">jmss0701004</a></li>
    <li><a id="jmss0701005" href="${pageContext.request.contextPath}/jmss/0701/005">jmss0701005</a></li>
    <li><a id="jmss0701006" href="${pageContext.request.contextPath}/jmss/0701/006">jmss0701006</a></li>
    <li><a id="jmss0701007" href="${pageContext.request.contextPath}/jmss/0701/007">jmss0701007</a></li>
    <li><a id="jmss0701008" href="${pageContext.request.contextPath}/jmss/0701/008">jmss0701008</a></li>
  </ul>

  <div>[JMSS08] 例外処理</div>
  <ul>
    <li>jmss0801001 (jmss0701001で実施)</li>
    <li><a id="jmss0802001" href="${pageContext.request.contextPath}/jmss/0802/001">jmss0802001</a></li>
    <li><a id="jmss0803001" href="${pageContext.request.contextPath}/jmss/0803/001">jmss0803001</a></li>
    <li><a id="jmss0803002" href="${pageContext.request.contextPath}/jmss/0803/002">jmss0803002</a></li>
  </ul>

  <div>[JMSS09] サイズの大きなデータの送受信</div>
  <ul>
    <li><a id="jmss0901001" href="${pageContext.request.contextPath}/jmss/0901/001">jmss0901001</a></li>
  </ul>



</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>