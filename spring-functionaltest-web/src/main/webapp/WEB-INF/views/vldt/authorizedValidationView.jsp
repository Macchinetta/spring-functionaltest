<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.vldt.authorizedValidationView" />
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
  <h1 id="screenTitle">認証されたユーザの単項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0103/003/authentication"
    class="form-horizontal">

    <c:if test="${param.containsKey('error')}">
      <span id="accountLoginError"> <t:messagesPanel
          messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
      </span>
    </c:if>

    <div class="form-group">
      <label for="userName" class="col-sm-2 control-label">UserName</label>
      <div class="col-sm-10">
        <input type="text" id="username" name="username" class="form-control" placeholder="UserName" />
      </div>
    </div>

    <div class="form-group">
      <label for="userName" class="col-sm-2 control-label">Password</label>
      <div class="col-sm-10">
        <input type="text" id="password" name="password" class="form-control" placeholder="Password" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" id="login" name="login" class="btn btn-default">Login</button>
      </div>
    </div>

  </form:form>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0103/003/logout"
    class="form-horizontal">

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" id="logout" name="logout" class="btn btn-default">Logout</button>
      </div>
    </div>

  </form:form>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0103/003"
    modelAttribute="authorizedValidationForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="deliveryAddress" class="col-sm-2 control-label">DeliveryAddress</form:label>
      <div class="col-sm-10">
        <form:input path="deliveryAddress" class="form-control" placeholder="DeliveryAddress" />
        <form:errors path="deliveryAddress" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
      </div>
    </div>

  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>