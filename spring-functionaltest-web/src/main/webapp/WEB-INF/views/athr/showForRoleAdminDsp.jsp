<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athr.showForRoleAdminDsp" />
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
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<div id="wrapper">
  <sec:authorize url="/athr/0401/001/afterLogin/admin/menu" var="hasAdminAuthority" />
  <h1 id="screenTitle">カスタムJSPでROLE_ADMINを保持していたら文言が表示することを確認する。</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>Message</th>
        <td><span id="staffRole"> <sec:authorize access="hasRole('ADMIN')">
              This screen is for ROLE_ADMIN
            </sec:authorize>
        </span></td>
      </tr>
      <tr>
        <th>Only Role_Admin User link</th>
        <td><span id="staffRoleLink"> <sec:authorize
              url="/athr/0401/001/afterLogin/admin/menu">
              <a href="${pageContext.request.contextPath}/athr">Go To ATHR Top Page</a>
            </sec:authorize>
        </span></td>
      </tr>
      <tr>
        <th>Only Role_Admin User link(variable)</th>
        <td><span id="staffRoleVarLink"> <c:if test="${hasAdminAuthority}">
              <a href="${pageContext.request.contextPath}/athr">Go To ATHR Top Page(variable)</a>
            </c:if>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0201/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>