<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athn.loginForUsingBCryptPassword" />
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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">管理者ログイン画面</h1>


  <span id="loginError"> <t:messagesPanel
      messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" panelClassName="alert alert-danger" />
  </span>

  <form:form action="${pageContext.request.contextPath}/jsp/0501/002/authenticate"
    cssClass="form-horizontal" method="post">
    <legend>ログイン</legend>
    <div class="form-group">
      <label for="userName" class="col col-md-2 control-label">Administrator Name</label>
      <div class="col col-md-3">
        <input type="text" class="form-control input-sm" id="username" name="username" />
      </div>
      <div class="col col-md-3"></div>
    </div>
    <div class="form-group">
      <label for="password" class="col col-md-2 control-label">Password</label>
      <div class="col col-md-3">
        <input type="password" class="form-control input-sm" id="password" name="password" />
      </div>
      <div class="col col-md-3"></div>
    </div>
    <div class="form-group">
      <input type="submit" id="login" class="btn btn-default" value="Login">
    </div>
  </form:form>
  <form:form action="${pageContext.request.contextPath}/jsp/0501/002/logout"
    cssClass="form-horizontal" method="post">
    <div class="form-group">
      <input type="submit" id="logout" class="btn btn-default" value="Logout">
    </div>
  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>