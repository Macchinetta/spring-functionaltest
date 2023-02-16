<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.emal.index" />
<c:set var="functionId" value="emal" />

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

  <h1 id="screenTitle">EMAL E-mail送信(SMTP)</h1>

  <div>[EMAL01] JavaMailSenderの設定方法</div>
  <ul>
    <li>EMAL02で同時確認</li>
  </ul>

  <div>[EMAL02] SimpleMailMessageによるメール送信方法</div>
  <ul>
    <li><a id="emal0201001" href="${pageContext.request.contextPath}/emal/0201/001">emal0201001</a></li>
    <li><a id="emal0201002" href="${pageContext.request.contextPath}/emal/0201/002">emal0201002</a></li>
    <li><a id="emal0201003" href="${pageContext.request.contextPath}/emal/0201/003">emal0201003</a></li>
  </ul>

  <div>[EMAL03] MimeMessageによるメール送信方法</div>
  <ul>
    <li><a id="emal0301001" href="${pageContext.request.contextPath}/emal/0301/001">emal0301001</a></li>
    <li><a id="emal0302001" href="${pageContext.request.contextPath}/emal/0302/001">emal0302001</a></li>
    <li><a id="emal0303001" href="${pageContext.request.contextPath}/emal/0303/001">emal0303001</a></li>
    <li><a id="emal0304001" href="${pageContext.request.contextPath}/emal/0304/001">emal0304001</a></li>
  </ul>

  <div>[EMAL04] メール送信時の例外</div>
  <ul>
    <li><a id="emal0401001" href="${pageContext.request.contextPath}/emal/0401/001">emal0401001</a></li>
    <li><a id="emal0401002" href="${pageContext.request.contextPath}/emal/0401/002">emal0401002</a></li>
    <li><a id="emal0401003" href="${pageContext.request.contextPath}/emal/0401/003">emal0401003</a></li>
    <li><a id="emal0401004" href="${pageContext.request.contextPath}/emal/0401/004">emal0401004</a></li>
  </ul>

  <div>[EMAL05] テンプレートを使用したメール本文の作成方法</div>
  <ul>
    <li><a id="emal0501001" href="${pageContext.request.contextPath}/emal/0501/001">emal0501001</a></li>
  </ul>

  <div>[EMAL06] ISO-2022-JPのエンコードについての考慮</div>
  <ul>
    <li><a id="emal0601001" href="${pageContext.request.contextPath}/emal/0601/001">emal0601001</a></li>
    <li><a id="emal0601002" href="${pageContext.request.contextPath}/emal/0601/002">emal0601002</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>