<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.stpr.index" />
<c:set var="functionId" value="stpr" />

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

  <h1 id="screenTitle">STPR 文字列処理</h1>

  <div>[STPR01] トリム</div>
  <ul>
    <li><a id="stpr0101001" href="${pageContext.request.contextPath}/stpr/0101/001">stpr0101001</a></li>
    <li><a id="stpr0101002" href="${pageContext.request.contextPath}/stpr/0101/002">stpr0101002</a></li>
    <li><a id="stpr0101003" href="${pageContext.request.contextPath}/stpr/0101/003">stpr0101003</a></li>
  </ul>

  <div>[STPR02] パディング・サプレス</div>
  <ul>
    <li><a id="stpr0201001" href="${pageContext.request.contextPath}/stpr/0201/001">stpr0201001</a></li>
    <li><a id="stpr0201002" href="${pageContext.request.contextPath}/stpr/0201/002">stpr0201002</a></li>
    <li><a id="stpr0201003" href="${pageContext.request.contextPath}/stpr/0201/002">stpr0201003</a>
      - 入力値が異なるだけのため、リンク先は[stpr0201002]と同様</li>
  </ul>

  <div>[STPR03] サロゲートペアを考慮した文字列処理</div>
  <ul>
    <li><a id="stpr0301001" href="${pageContext.request.contextPath}/stpr/0301/001">stpr0301001</a></li>
    <li><a id="stpr0302001" href="${pageContext.request.contextPath}/stpr/0302/001">stpr0302001</a></li>
    <li><a id="stpr0303001" href="${pageContext.request.contextPath}/stpr/0303/001">stpr0303001</a></li>
    <li><a id="stpr0303002" href="${pageContext.request.contextPath}/stpr/0303/001">stpr0303002</a>
      - 入力値が異なるだけのため、リンク先は[stpr0303001]と同様</li>
    <li><a id="stpr0304001" href="${pageContext.request.contextPath}/stpr/0303/001">stpr0304001</a>
      - 入力値が異なるだけのため、リンク先は[stpr0303001]と同様</li>
  </ul>

  <div>[STPR04] 全角半角文字列変換</div>
  <ul>
    <li><a id="stpr0401001" href="${pageContext.request.contextPath}/stpr/04">stpr0401001</a></li>
    <li><a id="stpr0402001" href="${pageContext.request.contextPath}/stpr/04">stpr0402001</a> -
      入力値が異なるだけのため、リンク先は[stpr0401001]と同様</li>
    <li><a id="stpr0403001" href="${pageContext.request.contextPath}/stpr/04">stpr0403001</a> -
      入力値が異なるだけのため、リンク先は[stpr0401001]と同様</li>
    <li><a id="stpr0404001" href="${pageContext.request.contextPath}/stpr/04">stpr0404001</a> -
      入力値が異なるだけのため、リンク先は[stpr0401001]と同様</li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>