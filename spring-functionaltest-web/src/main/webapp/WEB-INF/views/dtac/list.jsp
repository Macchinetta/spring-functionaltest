<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dtac.list" />
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
<div class="row">

  <h1 id="screenTitle">ユーザ一覧</h1>
  <div>
    <form:form modelAttribute="userForm"
      action="${pageContext.request.contextPath}/dtac/user/register">
      <form:button name="form" class="btn btn-default">登録</form:button>
    </form:form>
  </div>
  <br>
  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th class="no">No</th>
        <th class="username">ユーザ名</th>
        <th class="enabled">有効/無効</th>
        <th class="authority">権限</th>
      </tr>
    </thead>
    <c:forEach var="user" items="${page.content}" varStatus="rowStatus">
      <tr>
        <td class="no" id="no">${(page.number * page.size)
                            + rowStatus.count}</td>
        <td class="username" id="username">${f:h(user.username)}</td>
        <td class="enabled" id="enabled">${f:h(user.enabled)}</td>
        <td class="authority" id="authority">${f:h(user.authority)}</td>
      </tr>
    </c:forEach>
  </table>

  <div class="paginationPart" align="center">
    <t:pagination page="${page}" disabledHref="javascript:void(0);" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}" />
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-11"></div>
      <div class="col-md-1">
        <form:form modelAttribute="userForm"
          action="${pageContext.request.contextPath}/dtac/user/list">
          <form:button name="back" class="btn btn-default">戻る</form:button>
        </form:form>
      </div>
    </div>
  </div>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>