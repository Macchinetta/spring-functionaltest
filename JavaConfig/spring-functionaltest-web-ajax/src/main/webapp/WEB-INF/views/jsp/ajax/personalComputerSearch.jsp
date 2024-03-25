<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ajax.personalComputerSearch" />
<c:set var="functionId" value="ajax" />

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
  <jsp:include page="../layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <div id="xMLHttpRequestResult" class="hidden alert">
    <ul>
      <li>ステータスコード:<span id="returnStatus"></span></li>
      <li>Content-Type:<span id="contentType"></span></li>
    </ul>
  </div>

  <div id="resultMessage" class="hidden alert"></div>

  <div class="text-center">
    <form id="personalComputerCriteria">
      <spring:nestedPath path="personalComputerCriteria">
        <fieldset>
          <legend>登録PC検索画面</legend>
          <form:label path="personalComputerName" cssClass="space">検索するPC名を入力</form:label>
          <form:input path="personalComputerName"
            cssClass="space pc-search-field form-control inline" />

          <form:button id="searchBtn" name="search" class="btn btn-default"
            onclick="return ajax.search()">検索</form:button>
        </fieldset>
      </spring:nestedPath>
    </form>
  </div>
  <br>
  <div id="result" class="text-center">
    <table id="personalComputerResultTable"
      class="hidden table table-striped table-bordered table-condensed csprtable">
      <thead>
        <tr>
          <th class="text-center">No.</th>
          <th class="text-center">PC NAME</th>
          <th class="text-center">OS</th>
          <th class="text-center">CPU</th>
          <th class="text-center">RAM</th>
          <th class="text-center">VIDEOCARD</th>
          <th class="text-center">HDD</th>
          <th class="text-center">POWER</th>
          <th class="text-center">PRICE</th>
        </tr>
      </thead>
      <tbody id="personalComputerResult">
      </tbody>
    </table>
  </div>
</div>
  </div>
  <jsp:include page="../layout/footer.jsp" />
</body>
</html>