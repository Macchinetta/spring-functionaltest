<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athr.index" />
<c:set var="functionId" value="athr" />

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

      <h1 id="screenTitle">ATHR 認可</h1>

      <sec:authentication property="principal" var="principal" />

      <div>
        ようこそ
        <sec:authorize access="isAuthenticated()">
          <span id="loginUserName">${f:h(principal.username)}</span>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
          <span id="loginUserName"><spring:message code="label.sf.cmmn.defaultUserName" /></span>
        </sec:authorize>
        さん
      </div>
      <br>

      <div>[ATHN02] Webリソースへの認可</div>
      <ul>
        <li>athr0201001 (<a id="athr0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001/afterLogin">JSP</a> / <a id="athr0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001/afterLogin">Thymeleaf</a>)</li>
        <li>athr0201002 (<a id="athr0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002/restrict">JSP</a> / <a id="athr0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002/restrict">Thymeleaf</a>)</li>
        <li>athr0201003 (<a id="athr0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/004/showForAccessSuccessfully">JSP</a> / <a id="athr0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/004/showForAccessSuccessfully">Thymeleaf</a>)</li>
      </ul>
      <div>[ATHN03] メソッドへの認可・[ATHR05]
        認可エラー時のレスポンス・[ATHR06] アクセスポリシーの定義</div>
      <ul>
        <li>athr0301001 (<a id="athr0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / <a id="athr0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a>)</li>
      </ul>

      <div>[ATHR04] JSPの画面項目への認可</div>
      <ul>
        <li>athr0401001 (<a id="athr0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / <a id="athr0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001">Thymeleaf</a>)</li>
      </ul>

      <div>[ATHR06] アクセスポリシーの定義・[ATHN01]
        アクセスポリシーの記述方法</div>
      <ul>
        <li>athr0601001 (<a id="athr0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001">JSP</a> / <a id="athr0601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001">Thymeleaf</a>)</li>
        <li>athr0601002 (<a id="athr0601002_jsp" href="${pageContext.request.contextPath}/jsp/0601/002">JSP</a> / <a id="athr0601002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/002">Thymeleaf</a>)</li>
        <li>athr0601003 (<a id="athr0601003_jsp" href="${pageContext.request.contextPath}/jsp/0601/003">JSP</a> / <a id="athr0601003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/003">Thymeleaf</a>)</li>
        <li>athr0601004 (<a id="athr0601004_jsp" href="${pageContext.request.contextPath}/jsp/0601/004">JSP</a> / <a id="athr0601004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/004">Thymeleaf</a>)</li>
        <li>athr0601019 (<a id="athr0601019_jsp" href="${pageContext.request.contextPath}/jsp/0601/019">JSP</a> / <a id="athr0601019_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/019">Thymeleaf</a>)</li>
        <li>athr0601020 (<a id="athr0601020_jsp" href="${pageContext.request.contextPath}/jsp/0601/020">JSP</a> / <a id="athr0601020_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/020">Thymeleaf</a>)</li>
        <li>athr0601021 (<a id="athr0601021_jsp" href="${pageContext.request.contextPath}/jsp/0601/021">JSP</a> / <a id="athr0601021_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/021">Thymeleaf</a>)</li>
        <li>athr0601022 (<a id="athr0601022_jsp" href="${pageContext.request.contextPath}/jsp/0601/022">JSP</a> / <a id="athr0601022_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/022">Thymeleaf</a>)</li>
        <li>athr0602003 (<a id="athr0602003_jsp" href="${pageContext.request.contextPath}/jsp/0901/003">JSP</a> / <a id="athr0602003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0901/003">Thymeleaf</a>)</li>

      </ul>
      <div>[ATHR07] 認可エラー時のレスポンス (認証済みユーザー編)</div>
      <ul>
        <li>athr0701001 (<a id="athr0701001_jsp" href="${pageContext.request.contextPath}/jsp/0701/001">JSP</a> / <a id="athr0701001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0701/001">Thymeleaf</a>)</li>
      </ul>

      <div>[ATHR08] 認可エラー時のレスポンス 未認証ユーザー編)</div>
      <ul>
        <li>athr0802001で集約</li>

        <li>athr0802001（通常） (<a id="athr0802001_jsp" href="${pageContext.request.contextPath}/jsp/0802/001/afterLogin">JSP</a> / <a id="athr0802001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0802/001/afterLogin">Thymeleaf</a>)</li>
        <li>athr0802001（API） (<a id="athr0802001API_jsp" href="${pageContext.request.contextPath}/jsp/0802/001/api">JSP</a> / <a id="athr0802001API_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0802/001/api">Thymeleaf</a>)</li>
      </ul>

      <div>[ATHR09] ロール階層機能</div>
      <ul>
        <li>athr0901001 (<a id="athr0901001_jsp" href="${pageContext.request.contextPath}/jsp/0901/001">JSP</a> / <a id="athr0901001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0901/001">Thymeleaf</a>)</li>
        <li>athr0901002 (<a id="athr0901002_jsp" href="${pageContext.request.contextPath}/jsp/0901/002">JSP</a> / <a id="athr0901002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0901/002">Thymeleaf</a>)</li>
        <li>athr0901003 (<a id="athr0901003_jsp" href="${pageContext.request.contextPath}/jsp/0901/003">JSP</a> / <a id="athr0901003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0901/003">Thymeleaf</a>)</li>
      </ul>

      <br>
          
      <a id="spring-functionaltest-web" href="${baseWeb}">spring-functionaltest-web へ移動</a>

    </div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>