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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">入力チェック</h1>

  <div>[VLDT01] 単項目チェック</div>
  <ul>
    <li>vldt0101001 (<a id="vldt0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / <a id="vldt0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a> )</li>
    <li>vldt0101002 (<a id="vldt0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> / <a id="vldt0101002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/002">Thymeleaf</a> )</li>
    <li>vldt0101003 (<a id="vldt0101003_jsp" href="${pageContext.request.contextPath}/jsp/0101/003">JSP</a> / <a id="vldt0101003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/003">Thymeleaf</a> )</li>
    <li>vldt0101004 (<a id="vldt0101004_jsp" href="${pageContext.request.contextPath}/jsp/0101/004">JSP</a> / <a id="vldt0101004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/004">Thymeleaf</a> )</li>
    <li>vldt0101005 (<a id="vldt0101005_jsp" href="${pageContext.request.contextPath}/jsp/0101/005">JSP</a> / <a id="vldt0101005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/005">Thymeleaf</a> )</li>
    <li>vldt0101006 (<a id="vldt0101006_jsp" href="${pageContext.request.contextPath}/jsp/0101/006">JSP</a> / <a id="vldt0101006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/006">Thymeleaf</a> )</li>
    <li>vldt0101007 (<a id="vldt0101007_jsp" href="${pageContext.request.contextPath}/jsp/0101/007">JSP</a> / <a id="vldt0101007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/007">Thymeleaf</a> )</li>
    <li>vldt0101008 (<a id="vldt0101008_jsp" href="${pageContext.request.contextPath}/jsp/0101/007">JSP</a> / <a id="vldt0101008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/007">Thymeleaf</a> )</li>
    <li>vldt0101009 (<a id="vldt0101009_jsp" href="${pageContext.request.contextPath}/jsp/0101/007">JSP</a> / <a id="vldt0101009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/007">Thymeleaf</a> )</li>
    <li>vldt0101010 (<a id="vldt0101010_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/010">Thymeleaf</a> )</li>
    <li>vldt0101011 (<a id="vldt0101011_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/011">Thymeleaf</a> )</li>
    <li>vldt0101012 (<a id="vldt0101012_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/012">Thymeleaf</a> )</li>
    <li>vldt0101013 (<a id="vldt0101013_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/013">Thymeleaf</a> )</li>
    <li>vldt0101014 (<a id="vldt0101014_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/014">Thymeleaf</a> )</li>
    <li>vldt0102001 (<a id="vldt0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / <a id="vldt0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a> )</li>
    <li>vldt0102002 (<a id="vldt0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="vldt0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a> )</li>
    <li>vldt0103001 (<a id="vldt0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="vldt0103001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a> )</li>
    <li>vldt0103002 (<a id="vldt0103002_jsp" href="${pageContext.request.contextPath}/jsp/0103/002">JSP</a> / <a id="vldt0103002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/002">Thymeleaf</a> )</li>
    <li>vldt0103003 (<a id="vldt0103003_jsp" href="${pageContext.request.contextPath}/jsp/0103/003">JSP</a> / <a id="vldt0103003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/003">Thymeleaf</a> )</li>
    <li>vldt0101001 (<a id="vldt0104001_jsp" href="${pageContext.request.contextPath}/jsp/0104/001">JSP</a> / <a id="vldt0104001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0104/001">Thymeleaf</a> )</li>
  </ul>

  <div>[VLDT02] 相関項目チェック</div>
  <ul>
    <li>vldt0201001 (<a id="vldt0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="vldt0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a> )</li>
    <li>vldt0201002 (<a id="vldt0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> / <a id="vldt0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a> )</li>
    <li>vldt0201003 (<a id="vldt0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> / <a id="vldt0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/003">Thymeleaf</a> )</li>
    <li>vldt0202001 (<a id="vldt0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="vldt0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a> )</li>
    <li>vldt0202002 (<a id="vldt0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/002">JSP</a> / <a id="vldt0202002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/002">Thymeleaf</a> )</li>
  </ul>

  <div>[VLDT03] エラーメッセージの定義</div>
  <ul>
    <li>vldt0301001 (<a id="vldt0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / <a id="vldt0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a> )</li>
    <li>vldt0302001 (<a id="vldt0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / <a id="vldt0302001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001">Thymeleaf</a> )</li>
    <li>vldt0303001 (<a id="vldt0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / <a id="vldt0303001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/001">Thymeleaf</a> )</li>
    <li>vldt0304001 (<a id="vldt0304001_jsp" href="${pageContext.request.contextPath}/jsp/0304/001">JSP</a> / <a id="vldt0304001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0304/001">Thymeleaf</a> )</li>
    <li>vldt0305001 (<a id="vldt0305001_jsp" href="${pageContext.request.contextPath}/jsp/0305/001">JSP</a> / <a id="vldt0305001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0305/001">Thymeleaf</a> )</li>
  </ul>

  <div>[VLDT04] 独自のBean Validationアノテーションの作成</div>
  <ul>
    <li>vldt0401001 (<a id="vldt0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / <a id="vldt0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001">Thymeleaf</a> )</li>
    <li>vldt0402001 (<a id="vldt0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / <a id="vldt0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001">Thymeleaf</a> )</li>
    <li>vldt0402002 (<a id="vldt0402002_jsp" href="${pageContext.request.contextPath}/jsp/0402/002">JSP</a> / <a id="vldt0402002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/002">Thymeleaf</a> )</li>
  </ul>

  <div>[VLDT05] 共通ライブラリを利用しての入力チェック</div>
  <ul>
    <li>vldt0501001 (<a id="vldt0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> / <a id="vldt0501001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/001">Thymeleaf</a> )</li>
    <li>vldt0502001 (<a id="vldt0502001_jsp" href="${pageContext.request.contextPath}/jsp/0502/001">JSP</a> / <a id="vldt0502001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/001">Thymeleaf</a> )</li>
  </ul>

  <div>[VLDT06] Method Validation</div>
  <ul>  
    <li>vldt0601001 (<a id="vldt0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001">JSP</a> / <a id="vldt0601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001">Thymeleaf</a> )</li>
    <li>vldt0601002 (<a id="vldt0601002_jsp" href="${pageContext.request.contextPath}/jsp/0601/002">JSP</a> / <a id="vldt0601002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/002">Thymeleaf</a> )</li>
    <li>vldt0601003 (<a id="vldt0601003_jsp" href="${pageContext.request.contextPath}/jsp/0601/003">JSP</a> / <a id="vldt0601003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/003">Thymeleaf</a> )</li>
    <li>vldt0601004 (<a id="vldt0601004_jsp" href="${pageContext.request.contextPath}/jsp/0601/004">JSP</a> / <a id="vldt0601004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/004">Thymeleaf</a> )</li>
    <li>vldt0602001 (<a id="vldt0602001_jsp" href="${pageContext.request.contextPath}/jsp/0602/001">JSP</a> / <a id="vldt0602001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/001">Thymeleaf</a> )</li>
    <li>vldt0602002 (<a id="vldt0602002_jsp" href="${pageContext.request.contextPath}/jsp/0602/002">JSP</a> / <a id="vldt0602002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/002">Thymeleaf</a> )</li>
    <li>vldt0602003 (<a id="vldt0602003_jsp" href="${pageContext.request.contextPath}/jsp/0602/003">JSP</a> / <a id="vldt0602003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/003">Thymeleaf</a> )</li>
    <li>vldt0602004 (<a id="vldt0602004_jsp" href="${pageContext.request.contextPath}/jsp/0602/004">JSP</a> / <a id="vldt0602004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/004">Thymeleaf</a> )</li>
    <li>vldt0602005 (<a id="vldt0602005_jsp" href="${pageContext.request.contextPath}/jsp/0602/005">JSP</a> / <a id="vldt0602005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/005">Thymeleaf</a> )</li>
    <li>vldt0602006 (<a id="vldt0602006_jsp" href="${pageContext.request.contextPath}/jsp/0602/006">JSP</a> / <a id="vldt0602006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/006">Thymeleaf</a> )</li>
    <li>vldt0602007 (<a id="vldt0602007_jsp" href="${pageContext.request.contextPath}/jsp/0602/007">JSP</a> / <a id="vldt0602007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/007">Thymeleaf</a> )</li>
    <li>vldt0602008 (<a id="vldt0602008_jsp" href="${pageContext.request.contextPath}/jsp/0602/008">JSP</a> / <a id="vldt0602008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/008">Thymeleaf</a> )</li>
    <li>vldt0602009 (<a id="vldt0602009_jsp" href="${pageContext.request.contextPath}/jsp/0602/009">JSP</a> / <a id="vldt0602009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/009">Thymeleaf</a> )</li>
  </ul>

  <div>[VLDT07] OSコマンドインジェクション</div>
  <ul>
    <li>vldt0701001 (<a id="vldt0701001_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> / <a id="vldt0701001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/001">Thymeleaf</a> )</li>
    <li>vldt0701002 (<a id="vldt0701002_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> / <a id="vldt0701002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/001">Thymeleaf</a> )</li>
    <li>vldt0702001 (<a id="vldt0702001_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> / <a id="vldt0702001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/001">Thymeleaf</a> )</li>
    <li>vldt0702002 (<a id="vldt0702002_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> / <a id="vldt0702002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/001">Thymeleaf</a> )</li>
  </ul>

  <div>[VLDT08] 共通ライブラリが用意する入力チェックルール</div>
  <ul>
    <li>vldt0801001 (<a id="vldt0801001_jsp" href="${pageContext.request.contextPath}/jsp/0801/001">JSP</a> / <a id="vldt0801001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0801/001">Thymeleaf</a> )</li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>