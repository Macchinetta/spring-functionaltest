<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.aply.index" />
<c:set var="functionId" value="aply" />

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

      <h1 id="screenTitle">APLY アプリケーション層</h1>

        <div>[APLY01] Controllerの実装方法</div>
        <ul>
          <li>aply0101 (<a id="aply0101_jsp" href="${pageContext.request.contextPath}/jsp/0101">JSP</a> / <a id="aply0101_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101">Thymeleaf</a> )</li>
        </ul>

        <div>[APLY02] フォームオブジェクトの実装方法</div>
        <ul>
          <li>aply0201001 (<a id="aply0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="aply0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a> )</li>
          <li>aply0201002 (<a id="aply0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> / <a id="aply0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a> )</li>
          <li>aply0201003 (<a id="aply0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> / <a id="aply0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/003">Thymeleaf</a> )</li>
          <li>aply0201004 (<a id="aply0201004_jsp" href="${pageContext.request.contextPath}/jsp/0201/004">JSP</a> / <a id="aply0201004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/004">Thymeleaf</a> )</li>
          <li>aply0201005 (<a id="aply0201005_jsp" href="${pageContext.request.contextPath}/jsp/0201/005">JSP</a> / <a id="aply0201005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/005">Thymeleaf</a> )</li>
          <li>aply0201006 (<a id="aply0201006_jsp" href="${pageContext.request.contextPath}/jsp/0201/006">JSP</a> / <a id="aply0201006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/006">Thymeleaf</a> )</li>
          <li>aply0201007 (<a id="aply0201007_jsp" href="${pageContext.request.contextPath}/jsp/0201/007">JSP</a> / <a id="aply0201007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/007">Thymeleaf</a> )</li>
          <li>aply0202001 (<a id="aply0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="aply0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a> )</li>
          <li>aply0202002 (<a id="aply0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/002">JSP</a> / <a id="aply0202002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/002">Thymeleaf</a> )</li>
          <li>aply0202003 (<a id="aply0202003_jsp" href="${pageContext.request.contextPath}/jsp/0202/003">JSP</a> / <a id="aply0202003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/003">Thymeleaf</a> )</li>
          <li>aply0203001 (<a id="aply0203001_jsp" href="${pageContext.request.contextPath}/jsp/0203/001">JSP</a> / <a id="aply0203001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/001">Thymeleaf</a> )</li>
          <li>aply0203002 (<a id="aply0203002_jsp" href="${pageContext.request.contextPath}/jsp/0203/002">JSP</a> / <a id="aply0203002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/002">Thymeleaf</a> )</li>
          <li>aply0203003 (<a id="aply0203003_jsp" href="${pageContext.request.contextPath}/jsp/0203/003">JSP</a> / <a id="aply0203003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/003">Thymeleaf</a> )</li>
          <li>aply0203004 (<a id="aply0203004_jsp" href="${pageContext.request.contextPath}/jsp/0203/004">JSP</a> / <a id="aply0203004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/004">Thymeleaf</a> )</li>
          <li>aply0203005 (<a id="aply0203005_jsp" href="${pageContext.request.contextPath}/jsp/0203/005">JSP</a> / <a id="aply0203005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/005">Thymeleaf</a> )</li>
          <li>aply0203006 (<a id="aply0203006_jsp" href="${pageContext.request.contextPath}/jsp/0203/006">JSP</a> / <a id="aply0203006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/006">Thymeleaf</a> )</li>
          <li>aply0203007 (<a id="aply0203007_jsp" href="${pageContext.request.contextPath}/jsp/0203/007">JSP</a> / <a id="aply0203007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/007">Thymeleaf</a> )</li>
          <li>aply0203008 (<a id="aply0203008_jsp" href="${pageContext.request.contextPath}/jsp/0203/008">JSP</a> / <a id="aply0203008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/008">Thymeleaf</a> )</li>
          <li>aply0203009 (<a id="aply0203009_jsp" href="${pageContext.request.contextPath}/jsp/0203/009">JSP</a> / <a id="aply0203009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/009">Thymeleaf</a> )</li>
          <li>aply0203010 (<a id="aply0203010_jsp" href="${pageContext.request.contextPath}/jsp/0203/010">JSP</a> / <a id="aply0203010_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0203/010">Thymeleaf</a> )</li>
        </ul>

        <div>[APLY03] JSPの実装方法</div>
        <ul>
          <li>aply0301001 (<a id="aply0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / <a id="aply0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a> )</li>
          <li>aply0302001 (<a id="aply0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / <a id="aply0302001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001">Thymeleaf</a> )</li>
          <li>aply0302002 (<a id="aply0302002_jsp" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a> / <a id="aply0302002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/002">Thymeleaf</a> )</li>
          <li>aply0302003 (<a id="aply0302003_jsp" href="${pageContext.request.contextPath}/jsp/0302/003">JSP</a> / <a id="aply0302003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/003">Thymeleaf</a> )</li>
          <li>aply0302004 (<a id="aply0302004_jsp" href="${pageContext.request.contextPath}/jsp/0302/004">JSP</a> / <a id="aply0302004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/004">Thymeleaf</a> )</li>
          <li>aply0303001 (<a id="aply0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / <a id="aply0303001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/001">Thymeleaf</a> )</li>
          <li>aply0303002 (<a id="aply0303002_jsp" href="${pageContext.request.contextPath}/jsp/0303/002">JSP</a> / <a id="aply0303002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/002">Thymeleaf</a> )</li>
          <li>aply0304001 (<a id="aply0304001_jsp" href="${pageContext.request.contextPath}/jsp/0304/001">JSP</a> / <a id="aply0304001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0304/001">Thymeleaf</a> )</li>
          <li>aply0305001 (<a id="aply0305001_jsp" href="${pageContext.request.contextPath}/jsp/0305/001">JSP</a> / <a id="aply0305001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0305/001">Thymeleaf</a> )</li>
        </ul>

        <div>[APLY04] Controller共通処理の実装方法</div>
        <ul>
          <li>aply0401001 (<a id="aply0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / <a id="aply0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001">Thymeleaf</a> )</li>
          <li>aply0401002 (<a id="aply0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002">JSP</a> / <a id="aply0401002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/002">Thymeleaf</a> )</li>
          <li>aply0401003 (<a id="aply0401003_jsp" href="${pageContext.request.contextPath}/jsp/0401/003">JSP</a> / <a id="aply0401003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/003">Thymeleaf</a> )</li>
          <li>aply0401004 (<a id="aply0401004_jsp" href="${pageContext.request.contextPath}/jsp/0401/004">JSP</a> / <a id="aply0401004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/004">Thymeleaf</a> )</li>
          <li>aply0401005 (<a id="aply0401005_jsp" href="${pageContext.request.contextPath}/jsp/0401/005">JSP</a> / <a id="aply0401005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/005">Thymeleaf</a> )</li>
          <li>aply0401006 (<a id="aply0401006_jsp" href="${pageContext.request.contextPath}/jsp/0401/006">JSP</a> / <a id="aply0401006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/006">Thymeleaf</a> )</li>
          <li>aply0401007 (<a id="aply0401007_jsp" href="${pageContext.request.contextPath}/jsp/0401/007">JSP</a> / <a id="aply0401007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/007">Thymeleaf</a> )</li>
          <li>aply0401008 (<a id="aply0401008_jsp" href="${pageContext.request.contextPath}/jsp/0401/008">JSP</a> / <a id="aply0401008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/008">Thymeleaf</a> )</li>
          <li>aply0401009 (<a id="aply0401009_jsp" href="${pageContext.request.contextPath}/jsp/0401/009">JSP</a> / <a id="aply0401009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/009">Thymeleaf</a> )</li>
          <li>aply0402001 (<a id="aply0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / <a id="aply0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001">Thymeleaf</a> )</li>
          <li>aply0402002 (<a id="aply0402002_jsp" href="${pageContext.request.contextPath}/jsp/0402/002">JSP</a> / <a id="aply0402002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/002">Thymeleaf</a> )</li>
        </ul>

        <div>[APLY10] ThymeleafのテンプレートHTMLの実装</div>
        <ul>
          <li>aply1001001 (<a id="aply1001001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/001">Thymeleaf</a> )</li>
          <li>aply1001002 (<a id="aply1001002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/002">Thymeleaf</a> )</li>
          <li>aply1001003 (<a id="aply1001003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/003">Thymeleaf</a> )</li>
          <li>aply1001004 (<a id="aply1001004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/004">Thymeleaf</a> )</li>
          <li>aply1001005 (<a id="aply1001005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/005">Thymeleaf</a> )</li>
          <li>aply1001006 (<a id="aply1001006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/006">Thymeleaf</a> )</li>
          <li>aply1001007 (<a id="aply1001007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/007">Thymeleaf</a> )</li>
          <li>aply1001008 (<a id="aply1001008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/008">Thymeleaf</a> )</li>
          <li>aply1001009 (<a id="aply1001009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/009">Thymeleaf</a> )</li>
          <li>aply1001010 (<a id="aply1001010_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/010">Thymeleaf</a> )</li>
          <li>aply1001011 (<a id="aply1001011_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/011">Thymeleaf</a> )</li>
          <li>aply1001012 (<a id="aply1001012_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/012">Thymeleaf</a> )</li>
          <li>aply1001013 (<a id="aply1001013_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/013">Thymeleaf</a> )</li>
          <li>aply1001014 (<a id="aply1001014_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/014">Thymeleaf</a> )</li>
          <li>aply1001015 (<a id="aply1001015_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/015">Thymeleaf</a> )</li>
          <li>aply1001016 (<a id="aply1001016_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/016">Thymeleaf</a> )</li>
          <!--/* <li>aply1001017 (<a id="aply1001017_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/017">Thymeleaf</a> )</li> */-->
          <li>aply1001018 (<a id="aply1001018_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/018">Thymeleaf</a> )</li>
          <li>aply1001019 (<a id="aply1001019_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/019">Thymeleaf</a> )</li>
          <li>aply1001020 (<a id="aply1001020_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/020">Thymeleaf</a> )</li>
          <!--/* <li>aply1001021 (<a id="aply1001021_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/021">Thymeleaf</a> )</li> */-->
          <li>aply1001022 (<a id="aply1001022_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/022">Thymeleaf</a> )</li>
          <li>aply1001023 (<a id="aply1001023_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/023">Thymeleaf</a> )</li>
          <li>aply1001024 (<a id="aply1001024_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/024">Thymeleaf</a> )</li>
          <li>aply1001025 (<a id="aply1001025_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/025">Thymeleaf</a> )</li>
          <li>aply1001026 (<a id="aply1001026_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/026">Thymeleaf</a> )</li>
          <li>aply1001027 (<a id="aply1001027_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/027">Thymeleaf</a> )</li>
          <li>aply1001028 (<a id="aply1001028_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/028">Thymeleaf</a> )</li>
          <li>aply1001029 (<a id="aply1001029_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/029">Thymeleaf</a> )</li>
          <li>aply1001030 (<a id="aply1001030_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/030">Thymeleaf</a> )</li>
          <li>aply1001031 (<a id="aply1001031_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/031">Thymeleaf</a> )</li>
          <li>aply1001032 (<a id="aply1001032_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1001/032">Thymeleaf</a> )</li>
          <li>aply1002001 (<a id="aply1002001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1002/001">Thymeleaf</a> )</li>
          <li>aply1002002 (<a id="aply1002002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1002/002">Thymeleaf</a> )</li>
          <li>aply1002003 (<a id="aply1002003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1002/003">Thymeleaf</a> )</li>
          <li>aply1002004 (<a id="aply1002004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1002/004">Thymeleaf</a> )</li>
          <li>aply1002005 (<a id="aply1002005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1002/005">Thymeleaf</a> )</li>
          <li>aply1002006 (<a id="aply1002006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1002/006">Thymeleaf</a> )</li>
          <li>aply1003001 (<a id="aply1003001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1003/001">Thymeleaf</a> )</li>
          <li>aply1003002 (<a id="aply1003002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1003/002">Thymeleaf</a> )</li>
          <li>aply1003003 (<a id="aply1003003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1003/003">Thymeleaf</a> )</li>
          <li>aply1003004 (<a id="aply1003004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1003/004">Thymeleaf</a> )</li>
          <li>aply1004001 (<a id="aply1004001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1004/001">Thymeleaf</a> )</li>
          <li>aply1004002 (<a id="aply1004002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1004/002">Thymeleaf</a> )</li>
          <li>aply1004003 (<a id="aply1004003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1004/003">Thymeleaf</a> )</li>
          <li>aply1005001 (<a id="aply1005001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1005/001">Thymeleaf</a> )</li>
          <li>aply1005002 (<a id="aply1005002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1005/002">Thymeleaf</a> )</li>
          <li>aply1006001 (<a id="aply1006001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1006/001">Thymeleaf</a> )</li>
          <li>aply1006002 (<a id="aply1006002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1006/002">Thymeleaf</a> )</li>
          <li>aply1007001 (<a id="aply1007001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1007/001">Thymeleaf</a> )</li>
          <li>aply1007002 (<a id="aply1007002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1007/002">Thymeleaf</a> )</li>
          <li>aply1008001 (<a id="aply1008001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1008/001">Thymeleaf</a> )</li>
          <li>aply1009001 (<a id="aply1009001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1009/001">Thymeleaf</a> )</li>
          <li>aply1009002 (<a id="aply1009002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/1009/002">Thymeleaf</a> )</li>
        </ul>

      <br>
      
      <a id="spring-functionaltest-web" href="${baseWeb}">spring-functionaltest-web へ移動</a>

    </div>
  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>