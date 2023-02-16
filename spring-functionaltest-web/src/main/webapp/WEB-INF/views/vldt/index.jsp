<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.vldt.index" />
<c:set var="functionId" value="vldt" />

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

  <h1 id="screenTitle">入力チェック</h1>

  <div>[VLDT01] 単項目チェック</div>
  <ul>
    <li><a id="vldt0101001" href="${pageContext.request.contextPath}/vldt/0101/001">vldt0101001</a></li>
    <li><a id="vldt0101002" href="${pageContext.request.contextPath}/vldt/0101/002">vldt0101002</a></li>
    <li><a id="vldt0101003" href="${pageContext.request.contextPath}/vldt/0101/003">vldt0101003</a></li>
    <li><a id="vldt0101004" href="${pageContext.request.contextPath}/vldt/0101/004">vldt0101004</a></li>
    <li><a id="vldt0101005" href="${pageContext.request.contextPath}/vldt/0101/005">vldt0101005</a></li>
    <li><a id="vldt0101006" href="${pageContext.request.contextPath}/vldt/0101/006">vldt0101006</a></li>
    <li><a id="vldt0101007" href="${pageContext.request.contextPath}/vldt/0101/007">vldt0101007</a></li>
    <li><a id="vldt0101008" href="${pageContext.request.contextPath}/vldt/0101/007">vldt0101008</a></li>
    <li><a id="vldt0101009" href="${pageContext.request.contextPath}/vldt/0101/007">vldt0101009</a></li>
    <li><a id="vldt0102001" href="${pageContext.request.contextPath}/vldt/0102/001">vldt0102001</a></li>
    <li><a id="vldt0102002" href="${pageContext.request.contextPath}/vldt/0102/002">vldt0102002</a></li>
    <li><a id="vldt0103001" href="${pageContext.request.contextPath}/vldt/0103/001">vldt0103001</a></li>
    <li><a id="vldt0103002" href="${pageContext.request.contextPath}/vldt/0103/002">vldt0103002</a></li>
    <li><a id="vldt0103003" href="${pageContext.request.contextPath}/vldt/0103/003">vldt0103003</a></li>
    <li><a id="vldt0104001" href="${pageContext.request.contextPath}/vldt/0104/001">vldt0104001</a></li>
  </ul>

  <div>[VLDT02] 相関項目チェック</div>
  <ul>
    <li><a id="vldt0201001" href="${pageContext.request.contextPath}/vldt/0201/001">vldt0201001</a></li>
    <li><a id="vldt0201002" href="${pageContext.request.contextPath}/vldt/0201/002">vldt0201002</a></li>
    <li><a id="vldt0201003" href="${pageContext.request.contextPath}/vldt/0201/003">vldt0201003</a></li>
    <li><a id="vldt0202001" href="${pageContext.request.contextPath}/vldt/0202/001">vldt0202001</a></li>
    <li><a id="vldt0202002" href="${pageContext.request.contextPath}/vldt/0202/002">vldt0202002</a></li>
  </ul>

  <div>[VLDT03] エラーメッセージの定義</div>
  <ul>
    <li><a id="vldt0301001" href="${pageContext.request.contextPath}/vldt/0301/001">vldt0301001</a></li>
    <li><a id="vldt0302001" href="${pageContext.request.contextPath}/vldt/0302/001">vldt0302001</a></li>
    <li><a id="vldt0303001" href="${pageContext.request.contextPath}/vldt/0303/001">vldt0303001</a></li>
    <li><a id="vldt0304001" href="${pageContext.request.contextPath}/vldt/0304/001">vldt0304001</a></li>
    <li><a id="vldt0305001" href="${pageContext.request.contextPath}/vldt/0305/001">vldt0305001</a></li>
  </ul>

  <div>[VLDT04] 独自のBean Validationアノテーションの作成</div>
  <ul>
    <li><a id="vldt0401001" href="${pageContext.request.contextPath}/vldt/0401/001">vldt0401001</a></li>
    <li><a id="vldt0402001" href="${pageContext.request.contextPath}/vldt/0402/001">vldt0402001</a></li>
    <li><a id="vldt0402002" href="${pageContext.request.contextPath}/vldt/0402/002">vldt0402002</a></li>
  </ul>

  <div>[VLDT05] 共通ライブラリを利用しての入力チェック</div>
  <ul>
    <li><a id="vldt0501001" href="${pageContext.request.contextPath}/vldt/0501/001">vldt0501001</a></li>
    <li><a id="vldt0502001" href="${pageContext.request.contextPath}/vldt/0502/001">vldt0502001</a></li>
    <li><a id="vldt0503001" href="${pageContext.request.contextPath}/vldt/0503/001">vldt0503001</a></li>
  </ul>

  <div>[VLDT06] Method Validation</div>
  <ul>
    <li><a id="vldt0601001" href="${pageContext.request.contextPath}/vldt/0601/001">vldt0601001</a></li>
    <li><a id="vldt0601002" href="${pageContext.request.contextPath}/vldt/0601/002">vldt0601002</a></li>
    <li><a id="vldt0601003" href="${pageContext.request.contextPath}/vldt/0601/003">vldt0601003</a></li>
    <li><a id="vldt0601004" href="${pageContext.request.contextPath}/vldt/0601/004">vldt0601004</a></li>
    <li><a id="vldt0602001" href="${pageContext.request.contextPath}/vldt/0602/001">vldt0602001</a></li>
    <li><a id="vldt0602002" href="${pageContext.request.contextPath}/vldt/0602/002">vldt0602002</a></li>
    <li><a id="vldt0602003" href="${pageContext.request.contextPath}/vldt/0602/003">vldt0602003</a></li>
    <li><a id="vldt0602004" href="${pageContext.request.contextPath}/vldt/0602/004">vldt0602004</a></li>
    <li><a id="vldt0602005" href="${pageContext.request.contextPath}/vldt/0602/005">vldt0602005</a></li>
    <li><a id="vldt0602006" href="${pageContext.request.contextPath}/vldt/0602/006">vldt0602006</a></li>
    <li><a id="vldt0602007" href="${pageContext.request.contextPath}/vldt/0602/007">vldt0602007</a></li>
    <li><a id="vldt0602008" href="${pageContext.request.contextPath}/vldt/0602/008">vldt0602008</a></li>
    <li><a id="vldt0602009" href="${pageContext.request.contextPath}/vldt/0602/009">vldt0602009</a></li>
  </ul>

  <div>[VLDT07] OSコマンドインジェクション</div>
  <ul>
    <li><a id="vldt0701001" href="${pageContext.request.contextPath}/vldt/0701/001">vldt0701001</a></li>
    <li><a id="vldt0701002" href="${pageContext.request.contextPath}/vldt/0701/001">vldt0701002</a></li>
    <li><a id="vldt0702001" href="${pageContext.request.contextPath}/vldt/0701/001">vldt0702001</a></li>
    <li><a id="vldt0702002" href="${pageContext.request.contextPath}/vldt/0701/001">vldt0702002</a></li>
  </ul>

  <div>[VLDT08] 共通ライブラリが用意する入力チェックルール</div>
  <ul>
    <li><a id="vldt0801001" href="${pageContext.request.contextPath}/vldt/0801/001">vldt0801001</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>