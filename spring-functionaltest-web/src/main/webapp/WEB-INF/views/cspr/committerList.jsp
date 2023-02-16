<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.cspr.committerList" />
<c:set var="functionId" value="cspr" />

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
  <h3 id="screenTitle">コミッター検索画面</h3>

  <div id="xMLHttpRequestResult" class="hidden alert">
    <ul>
      <li id="returnStatus"></li>
    </ul>
  </div>

  <div id="resultMessage" class="hidden alert"></div>

  <div class="text-center">
    <form:form modelAttribute="committerCriteria">
      <form:label path="username" cssClass="space">コミッター名</form:label>
      <form:input path="username" cssClass="space customer-search-field form-control inline" />
      <input id="searchBtn" type="button" class="btn btn-default" value="検索"
        onclick="return cspr.search()" />
    </form:form>
  </div>
  <br>
  <div id="result" class="text-center">
    <table id="committerResultTable"
      class="hidden table table-striped table-bordered table-condensed csprtable">
      <thead>
        <tr>
          <th class="text-center">No.</th>
          <th class="text-center">ユーザ名</th>
          <th class="text-center">Email</th>
          <th />
        </tr>
      </thead>
      <tbody id="committerResult">
      </tbody>
    </table>
  </div>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>