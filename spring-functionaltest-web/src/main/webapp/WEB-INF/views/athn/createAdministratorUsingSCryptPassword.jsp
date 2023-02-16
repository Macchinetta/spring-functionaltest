<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athn.createAdministratorUsingSCryptPassword" />
<c:set var="functionId" value="athn" />

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
  <h1 id="screenTitle">管理者登録</h1>
  <br>
  <t:messagesPanel />
  <form:form action="${pageContext.request.contextPath}/athn/0501/005/createAdminUsingSCrypt"
    cssClass="form-horizontal" method="post" modelAttribute="administratorForm">
    <legend>管理者登録</legend>
    <div class="form-group">
      <form:label path="username" class="col col-md-2 control-label">Administrator Name</form:label>
      <div class="col col-md-3">
        <form:input path="username" cssClass="form-control input-sm" />
      </div>
      <div class="col col-md-3">
        <form:errors path="username" cssClass="errorMessage" />
      </div>
    </div>
    <div class="form-group">
      <form:label path="password" class="col col-md-2 control-label">Password</form:label>
      <div class="col col-md-3">
        <form:password path="password" cssClass="form-control input-sm" />
      </div>
      <div class="col col-md-3">
        <form:errors path="password" cssClass="errorMessage" />
      </div>
    </div>
    <div class="form-group">
      <form:button id="create" class="btn btn-default">管理者登録</form:button>
    </div>
  </form:form>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>