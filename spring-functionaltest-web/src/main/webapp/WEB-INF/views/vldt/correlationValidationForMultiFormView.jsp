<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.vldt.correlationValidationForMultiFormView" />
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
  <h1 id="screenTitle">相関項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0201/002"
    modelAttribute="userForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="password" class="col-sm-2 control-label">Password</form:label>
      <div class="col-sm-10">
        <form:password path="password" class="form-control" placeholder="Password" />
        <form:errors path="password" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="confirmPassword" class="col-sm-2 control-label">ConfirmPassword</form:label>
      <div class="col-sm-10">
        <form:password path="confirmPassword" class="form-control" placeholder="ConfirmPassword" />
        <form:errors path="confirmPassword" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validateUser" name="validateUser" class="btn btn-default">validateUser</form:button>
      </div>
    </div>

  </form:form>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0201/002"
    modelAttribute="userDetailsForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="age" class="col-sm-2 control-label">Age</form:label>
      <div class="col-sm-10">
        <form:input path="age" class="form-control" placeholder="Age" />
        <form:errors path="age" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="dateOfBirth" class="col-sm-2 control-label">DateOfBirth</form:label>
      <div class="col-sm-10">
        <form:input path="dateOfBirth" class="form-control" placeholder="DateOfBirth" />
        <form:errors path="dateOfBirth" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validateUserDetails" name="validateUserDetails" class="btn btn-default">validateUserDetails</form:button>
      </div>
    </div>

  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>