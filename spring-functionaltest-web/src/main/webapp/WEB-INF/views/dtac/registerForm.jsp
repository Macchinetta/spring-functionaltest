<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dtac.registerForm" />
<c:set var="functionId" value="dtac" />

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
<form:form modelAttribute="userForm" action="${pageContext.request.contextPath}/dtac/user/register">
  <fieldset>
    <legend>ユーザ登録</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-2">
          <label>ユーザ名</label>
        </div>
        <div class="col-md-4">
          <form:input path="username" class="form-control" value="${f:h(user.username)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>パスワード</label>
        </div>
        <div class="col-md-4">
          <form:input path="password" class="form-control" value="${f:h(user.password)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>有効/無効</label>
        </div>
        <div class="col-md-4">
          <form:input path="enabled" class="form-control" value="${f:h(user.enabled)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>権限</label>
        </div>
        <div class="col-md-4">
          <form:input path="authority" class="form-control" value="${f:h(user.enabled)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-4">
          <form:button name="register" class="btn btn-default">登録</form:button>
          <form:button name="cancel" class="btn btn-default">キャンセル</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>