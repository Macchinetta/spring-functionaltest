<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athr.loginForCustomizedAuthorizeErrorForAuthenticated" />
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
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}_jsp.js"></script>
<script type="text/javascript"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">Login Page(CustomizedAuthorizeErrorForAuthenticated)</h1>

  <form:form action="${pageContext.request.contextPath}/jsp/0701/001/authenticate" method="post">
    <c:if test="${param.containsKey('error')}">
      <span id="loginError"> <t:messagesPanel
          messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
      </span>
    </c:if>
    <fieldset>
      <legend>Login</legend>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="userName">UserName</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="username" name="username">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="password">Password</label>
        </div>
        <div class="col col-md-3">
          <input type="password" class="form-control input-sm" id="password" name="password">
        </div>
      </div>
      <br> <br> <input type="submit" id="login" class="btn btn-default" value="Login">
    </fieldset>
  </form:form>
  <br>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>