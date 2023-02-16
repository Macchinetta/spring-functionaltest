<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athr.showForAccessPolicyIpAddressDeny" />
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

  <h1 id="screenTitle">アクセスポリシ定義(IPアドレス否許可)が正常に機能していることを確認する。</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>UserName</th>
        <td><span id="username"> <c:if test="${!empty username}">
              <sec:authentication property="principal.username" />
            </c:if>
        </span></td>
      </tr>
      <tr>
        <th>Only Manager User link</th>
        <td><span> <a id="managerLink"
            href="${pageContext.request.contextPath}/athr/0601/003/manager">Go To Account Page
              Top Page</a>
        </span></td>
      </tr>
      <tr>
        <th>Only Configuration Manager(LocalHost) link</th>
        <td><span> <a id="configLink"
            href="${pageContext.request.contextPath}/athr/0601/003/configurations">Go To
              Configuration Manager Page</a>
        </span></td>
      </tr>
      <tr>
        <th>Only Administrator link</th>
        <td><span> <a id="adminLink"
            href="${pageContext.request.contextPath}/athr/0601/003/admin">Go To Manager Page</a>
        </span></td>

      </tr>
      <tr>
        <th>Authenticated User link</th>
        <td><span> <a id="allLink"
            href="${pageContext.request.contextPath}/athr/0601/003/all">Go To Authenticated User
              Page</a>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0601/003/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>