<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athn.topForDispAuthentication" />
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

  <h1 id="screenTitle">ログインしました(topForDispAuthentication)</h1>

  <table class="table table-bordered table-condensed">
    <tr>
      <th>User Name</th>
      <td><span id="username"> <sec:authentication property="principal.account.username" />
      </span></td>

    </tr>
    <tr>
      <th>Last Name</th>
      <td><span id="lastname"> <sec:authentication property="principal.account.lastName" />
      </span></td>
    </tr>
    <tr>
      <th>User Uuid</th>
      <td><span id="useruuid">${f:h(userUuid)}</span></td>
    </tr>
  </table>
  <form:form action="${pageContext.request.contextPath}/jsp/${testNo}/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>