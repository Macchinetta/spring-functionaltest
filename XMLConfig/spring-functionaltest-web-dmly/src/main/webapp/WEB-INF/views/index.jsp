<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dmly.index" />
<c:set var="functionId" value="dmly" />

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

      <h1 id="screenTitle">ドメイン層</h1>

      <div>[DMLY01] Entityの実装方法</div>
      <ul>
        <li>dmly0101001 (<a id="dmly0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / Thymeleaf)</li>
        <li>dmly0101002 (<a id="dmly0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> / Thymeleaf)</li>
        <li>dmly0101003 (<a id="dmly0101003_jsp" href="${pageContext.request.contextPath}/jsp/0101/003">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[DMLY02] Repositoryの実装方法</div>
      <ul>
        <li>dmly0201001 (<a id="dmly0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / Thymeleaf)</li>
        <li>dmly0201002 (<a id="dmly0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> / Thymeleaf)</li>
        <li>dmly0201003 (<a id="dmly0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> / Thymeleaf)</li>
        <li>dmly0201004 (<a id="dmly0201004_jsp" href="${pageContext.request.contextPath}/jsp/0201/004">JSP</a> / Thymeleaf)</li>
        <li>dmly0201005 (<a id="dmly0201005_jsp" href="${pageContext.request.contextPath}/jsp/0201/005">JSP</a> / Thymeleaf)</li>
        <li>dmly0201006 (<a id="dmly0201006_jsp" href="${pageContext.request.contextPath}/jsp/0201/006">JSP</a> / Thymeleaf)</li>
        <li>dmly0201007 (<a id="dmly0201007_jsp" href="${pageContext.request.contextPath}/jsp/0201/007">JSP</a> / Thymeleaf)</li>
        <li>dmly0201008 (<a id="dmly0201008_jsp" href="${pageContext.request.contextPath}/jsp/0201/008">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[DMLY03] Serviceの実装方法</div>
      <ul>
        <li>dmly0301001 (<a id="dmly0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / Thymeleaf)</li>
        <li>dmly0302001 (<a id="dmly0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / Thymeleaf)</li>
        <li>dmly0303001 (<a id="dmly0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / Thymeleaf)</li>
      </ul>

      <div>[DMLY04] トランザクションの管理方法</div>
      <ul>
        <li>dmly0401001 (<a id="dmly0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / Thymeleaf)</li>
        <li>dmly0401002 (<a id="dmly0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002">JSP</a> / Thymeleaf)</li>
        <li>dmly0401003 (<a id="dmly0401003_jsp" href="${pageContext.request.contextPath}/jsp/0401/003">JSP</a> / Thymeleaf)</li>
        <li>dmly0401004 (<a id="dmly0401004_jsp" href="${pageContext.request.contextPath}/jsp/0401/004">JSP</a> / Thymeleaf)</li>
        <li>dmly0401005 (<a id="dmly0401005_jsp" href="${pageContext.request.contextPath}/jsp/0401/005">JSP</a> / Thymeleaf)</li>
        <li>dmly0401006 (<a id="dmly0401006_jsp" href="${pageContext.request.contextPath}/jsp/0401/006">JSP</a> / Thymeleaf)</li>
        <li>dmly0401007 (<a id="dmly0401007_jsp" href="${pageContext.request.contextPath}/jsp/0401/007">JSP</a> / Thymeleaf)</li>
        <li>dmly0401008 (<a id="dmly0401008_jsp" href="${pageContext.request.contextPath}/jsp/0401/008">JSP</a> / Thymeleaf)</li>
        <li>dmly0401009 (<a id="dmly0401009_jsp" href="${pageContext.request.contextPath}/jsp/0401/009">JSP</a> / Thymeleaf)</li>
        <li>dmly0401010 (<a id="dmly0401010_jsp" href="${pageContext.request.contextPath}/jsp/0401/010">JSP</a> / Thymeleaf)</li>
        <li>dmly0401011 (<a id="dmly0401011_jsp" href="${pageContext.request.contextPath}/jsp/0401/011">JSP</a> / Thymeleaf)</li>
        <li>dmly0401012 (<a id="dmly0401012_jsp" href="${pageContext.request.contextPath}/jsp/0401/012">JSP</a> / Thymeleaf)</li>
        <li>dmly0401013 (<a id="dmly0401013_jsp" href="${pageContext.request.contextPath}/jsp/0401/013">JSP</a> / Thymeleaf)</li>
        <li>dmly0401014 (<a id="dmly0401014_jsp" href="${pageContext.request.contextPath}/jsp/0401/014">JSP</a> / Thymeleaf)</li>
        <li>dmly0401015 (<a id="dmly0401015_jsp" href="${pageContext.request.contextPath}/jsp/0401/015">JSP</a> / Thymeleaf)</li>
        <li>dmly0401016 (<a id="dmly0401016_jsp" href="${pageContext.request.contextPath}/jsp/0401/016">JSP</a> / Thymeleaf)</li>
        <li>dmly0401017 (<a id="dmly0401017_jsp" href="${pageContext.request.contextPath}/jsp/0401/017">JSP</a> / Thymeleaf)</li>
        <li>dmly0401018 (<a id="dmly0401018_jsp" href="${pageContext.request.contextPath}/jsp/0401/018">JSP</a> / Thymeleaf)</li>
        <li>dmly0401019 (<a id="dmly0401019_jsp" href="${pageContext.request.contextPath}/jsp/0401/019">JSP</a> / Thymeleaf)</li>
        <li>dmly0401020 (<a id="dmly0401020_jsp" href="${pageContext.request.contextPath}/jsp/0401/020">JSP</a> / Thymeleaf)</li>
        <li>dmly0401021 (<a id="dmly0401021_jsp" href="${pageContext.request.contextPath}/jsp/0401/021">JSP</a> / Thymeleaf)</li>
        <li>dmly0401022 (<a id="dmly0401022_jsp" href="${pageContext.request.contextPath}/jsp/0401/022">JSP</a> / Thymeleaf)</li>
        <li>dmly0401023 (<a id="dmly0401023_jsp" href="${pageContext.request.contextPath}/jsp/0401/023">JSP</a> / Thymeleaf)</li>
        <li>dmly0401024 (<a id="dmly0401024_jsp" href="${pageContext.request.contextPath}/jsp/0401/024">JSP</a> / Thymeleaf)</li>
        <li>dmly0401025 (<a id="dmly0401025_jsp" href="${pageContext.request.contextPath}/jsp/0401/025">JSP</a> / Thymeleaf)</li>
        <li>dmly0401026 (<a id="dmly0401026_jsp" href="${pageContext.request.contextPath}/jsp/0401/026">JSP</a> / Thymeleaf)</li>
        <li>dmly0401027 (<a id="dmly0401027_jsp" href="${pageContext.request.contextPath}/jsp/0401/027">JSP</a> / Thymeleaf)</li>
        <li>dmly0401028 (<a id="dmly0401028_jsp" href="${pageContext.request.contextPath}/jsp/0401/028">JSP</a> / Thymeleaf)</li>
        <li>dmly0401029 (<a id="dmly0401029_jsp" href="${pageContext.request.contextPath}/jsp/0401/029">JSP</a> / Thymeleaf)</li>
        <li>dmly0401030 (<a id="dmly0401030_jsp" href="${pageContext.request.contextPath}/jsp/0401/030">JSP</a> / Thymeleaf)</li>
        <li>dmly0401031 (<a id="dmly0401031_jsp" href="${pageContext.request.contextPath}/jsp/0401/031">JSP</a> / Thymeleaf)</li>
        <li>dmly0401032 (<a id="dmly0401032_jsp" href="${pageContext.request.contextPath}/jsp/0401/032">JSP</a> / Thymeleaf)</li>
        <li>dmly0401033 (<a id="dmly0401033_jsp" href="${pageContext.request.contextPath}/jsp/0401/033">JSP</a> / Thymeleaf)</li>
        <li>dmly0401034 (<a id="dmly0401034_jsp" href="${pageContext.request.contextPath}/jsp/0401/034">JSP</a> / Thymeleaf)</li>
        <li>dmly0401035 (<a id="dmly0401035_jsp" href="${pageContext.request.contextPath}/jsp/0401/035">JSP</a> / Thymeleaf)</li>
        <li>dmly0401036 (<a id="dmly0401036_jsp" href="${pageContext.request.contextPath}/jsp/0401/036">JSP</a> / Thymeleaf)</li>
        <li>dmly0401037 (<a id="dmly0401037_jsp" href="${pageContext.request.contextPath}/jsp/0401/037">JSP</a> / Thymeleaf)</li>
        <li>dmly0401038 (<a id="dmly0401038_jsp" href="${pageContext.request.contextPath}/jsp/0401/038">JSP</a> / Thymeleaf)</li>
        <li>dmly0401039 (<a id="dmly0401039_jsp" href="${pageContext.request.contextPath}/jsp/0401/039">JSP</a> / Thymeleaf)</li>
        <li>dmly0401040 (<a id="dmly0401040_jsp" href="${pageContext.request.contextPath}/jsp/0401/040">JSP</a> / Thymeleaf)</li>
        <li>dmly0401041 (<a id="dmly0401041_jsp" href="${pageContext.request.contextPath}/jsp/0401/041">JSP</a> / Thymeleaf)</li>
        <li>dmly0401042 (<a id="dmly0401042_jsp" href="${pageContext.request.contextPath}/jsp/0401/042">JSP</a> / Thymeleaf)</li>
        <li>dmly0401043 (<a id="dmly0401043_jsp" href="${pageContext.request.contextPath}/jsp/0401/043">JSP</a> / Thymeleaf)</li>
        <li>dmly0401044 (<a id="dmly0401044_jsp" href="${pageContext.request.contextPath}/jsp/0401/044">JSP</a> / Thymeleaf)</li>
        <li>dmly0401045 (<a id="dmly0401045_jsp" href="${pageContext.request.contextPath}/jsp/0401/045">JSP</a> / Thymeleaf)</li>
        <li>dmly0401046 (<a id="dmly0401046_jsp" href="${pageContext.request.contextPath}/jsp/0401/046">JSP</a> / Thymeleaf)</li>
        <li>dmly0401047 (<a id="dmly0401047_jsp" href="${pageContext.request.contextPath}/jsp/0401/047">JSP</a> / Thymeleaf)</li>
        <li>dmly0401048 (<a id="dmly0401048_jsp" href="${pageContext.request.contextPath}/jsp/0401/048">JSP</a> / Thymeleaf)</li>
        <li>dmly0401049 (<a id="dmly0401049_jsp" href="${pageContext.request.contextPath}/jsp/0401/049">JSP</a> / Thymeleaf)</li>
        <li>dmly0401050 (<a id="dmly0401050_jsp" href="${pageContext.request.contextPath}/jsp/0401/050">JSP</a> / Thymeleaf)</li>
        <li>dmly0401051 (<a id="dmly0401051_jsp" href="${pageContext.request.contextPath}/jsp/0401/051">JSP</a> / Thymeleaf)</li>
        <li>dmly0401052 (<a id="dmly0401052_jsp" href="${pageContext.request.contextPath}/jsp/0401/052">JSP</a> / Thymeleaf)</li>
        <li>dmly0402001 (<a id="dmly0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / Thymeleaf)</li>
      </ul>

    </div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>