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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
    <div id="wrapper">

      <h1 id="screenTitle">JMSS JMS (Java Message Service)</h1>

      <div>[INIT] 初期処理</div>
      <ul>
        <li><a id="キューのクリーン処理" href="${pageContext.request.contextPath}/jmss/queuePurge">queueクリア</a></li>
        <li><a id="トピックのクリーン処理" href="${pageContext.request.contextPath}/jmss/topicPurge">topicクリア</a></li>
      </ul>

      <div>[JMSS01] コネクションファクトリの定義方法</div>
      <ul>
        <li>jmss0101001 (<a id="jmss0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0102001 (<a id="jmss0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0103001 (<a id="jmss0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[JMSS02] Destinationの名前解決</div>
      <ul>
        <li>jmss0201001 (jmss0101001で実施)</li>
        <li>jmss0202001 (jmss0102001で実施)</li>
      </ul>

      <div>[JMSS03] メッセージ送信</div>
      <ul>
        <li>jmss0301001 (<a id="jmss0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0301002 (jmss0103001で実施)</li>
        <li>jmss0301003 (<a id="jmss0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> / Thymeleaf)</li>
        <li>jmss0301004 (<a id="jmss0301004_jsp" href="${pageContext.request.contextPath}/jsp/0301/004">JSP</a> / Thymeleaf)</li>
        <li>jmss0302001 (<a id="jmss0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0303001 (<a id="jmss0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0303002 (<a id="jmss0303002_jsp" href="${pageContext.request.contextPath}/jsp/0303/002">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[JMSS04] メッセージ非同期受信</div>
      <ul>
        <li>jmss0401001 (jmss0103001で実施)</li>
        <li>jmss0401002 (<a id="jmss0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002">JSP</a> / Thymeleaf)</li>
        <li>jmss0401003 (<a id="jmss0401003_jsp" href="${pageContext.request.contextPath}/jsp/0401/003">JSP</a> / Thymeleaf)</li>
        <li>jmss0401004 (jmss0303001で実施)</li>
        <li>jmss0402001 (<a id="jmss0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0403001 (<a id="jmss0403001_jsp" href="${pageContext.request.contextPath}/jsp/0403/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0403002 (<a id="jmss0403002_jsp" href="${pageContext.request.contextPath}/jsp/0403/002">JSP</a> / Thymeleaf)</li>
        <li>jmss0403003 (<a id="jmss0403003_jsp" href="${pageContext.request.contextPath}/jsp/0403/003">JSP</a> / Thymeleaf)</li>
        <li>jmss0404001 (<a id="jmss0404001_jsp" href="${pageContext.request.contextPath}/jsp/0404/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0404002 (<a id="jmss0404002_jsp" href="${pageContext.request.contextPath}/jsp/0404/002">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[JMSS05] メッセージの同期受信</div>
      <ul>
        <li>jmss0501001 (<a id="jmss0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[JMSS06] トランザクション管理</div>
      <ul>
        <li>jmss0601001 (<a id="jmss0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0601002 (<a id="jmss0601002_jsp" href="${pageContext.request.contextPath}/jsp/0601/002">JSP</a> / Thymeleaf)</li>
        <li>jmss0602001 (<a id="jmss0602001_jsp" href="${pageContext.request.contextPath}/jsp/0602/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0602002 (<a id="jmss0602002_jsp" href="${pageContext.request.contextPath}/jsp/0602/002">JSP</a> / Thymeleaf)</li>
        <li>jmss0603001 (<a id="jmss0603001_jsp" href="${pageContext.request.contextPath}/jsp/0603/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0603002 (<a id="jmss0603002_jsp" href="${pageContext.request.contextPath}/jsp/0603/002">JSP</a> / Thymeleaf)</li>
        <li>jmss0604001 (<a id="jmss0604001_jsp" href="${pageContext.request.contextPath}/jsp/0604/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0604002 (<a id="jmss0604002_jsp" href="${pageContext.request.contextPath}/jsp/0604/002">JSP</a> / Thymeleaf)</li>
        <li>jmss0604005 (<a id="jmss0604005_jsp" href="${pageContext.request.contextPath}/jsp/0604/005">JSP</a> / Thymeleaf)</li>
        <li>jmss0604006 (<a id="jmss0604006_jsp" href="${pageContext.request.contextPath}/jsp/0604/006">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[JMSS07] 受信データの入力チェック</div>
      <ul>
        <li>jmss0701001 (<a id="jmss0701001_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0701002 (<a id="jmss0701002_jsp" href="${pageContext.request.contextPath}/jsp/0701/002">JSP</a> / Thymeleaf)</li>
        <li>jmss0701003 (<a id="jmss0701003_jsp" href="${pageContext.request.contextPath}/jsp/0701/003">JSP</a> / Thymeleaf)</li>
        <li>jmss0701004 (<a id="jmss0701004_jsp" href="${pageContext.request.contextPath}/jsp/0701/004">JSP</a> / Thymeleaf)</li>
        <li>jmss0701005 (<a id="jmss0701005_jsp" href="${pageContext.request.contextPath}/jsp/0701/005">JSP</a> / Thymeleaf)</li>
        <li>jmss0701006 (<a id="jmss0701006_jsp" href="${pageContext.request.contextPath}/jsp/0701/006">JSP</a> / Thymeleaf)</li>
        <li>jmss0701007 (<a id="jmss0701007_jsp" href="${pageContext.request.contextPath}/jsp/0701/007">JSP</a> / Thymeleaf)</li>
        <li>jmss0701008 (<a id="jmss0701008_jsp" href="${pageContext.request.contextPath}/jsp/0701/008">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[JMSS08] 例外処理</div>
      <ul>
        <li>jmss0801001 (jmss0701001で実施)</li>
        <li>jmss0802001 (<a id="jmss0802001_jsp" href="${pageContext.request.contextPath}/jsp/0802/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0803001 (<a id="jmss0803001_jsp" href="${pageContext.request.contextPath}/jsp/0803/001">JSP</a> / Thymeleaf)</li>
        <li>jmss0803002 (<a id="jmss0803002_jsp" href="${pageContext.request.contextPath}/jsp/0803/002">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[JMSS09] サイズの大きなデータの送受信</div>
      <ul>
        <li>jmss0901001 (<a id="jmss0901001_jsp" href="${pageContext.request.contextPath}/jsp/0901/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <br>
          
      <a id="spring-functionaltest-web" href="${baseWeb}">spring-functionaltest-web へ移動</a>

    </div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>