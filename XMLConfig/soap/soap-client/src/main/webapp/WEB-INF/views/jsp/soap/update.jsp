<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.soap.index" />
<c:set var="functionId" value="soap" />

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width" />
  <meta name="contextPath" content="${pageContext.request.contextPath}" />
  <sec:csrfMetaTags />
  <title>
    <spring:message code="label.sf.cmmn.systemName" var="defaultTitle" />
    <spring:message code="${titleKey}" text="${f:h(defaultTitle)}" />
  </title>
  <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
  <script type="text/javascript"
    src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
  <script type="text/javascript"></script>
</head>

<body>
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
    <div id="wrapper">

      <h1 id="screenTitle">update todo</h1>
      <br>
      <t:messagesPanel />
      <form:form action="${pageContext.request.contextPath}/todo/${f:h(proxy)}/update" cssClass="form-horizontal"
        method="post" modelAttribute="todoForm">

        <div class="form-group">
          <form:label path="todoId" class="col col-md-2 control-label">todoId</form:label>
          <div class="col col-md-3">
            <form:input path="todoId" cssClass="form-control input-sm" />
          </div>
        </div>

        <div class="form-group">
          <form:label path="title" class="col col-md-2 control-label">title</form:label>
          <div class="col col-md-3">
            <form:input path="title" cssClass="form-control input-sm" />
          </div>
        </div>

        <div class="form-group">
          <form:label path="description" class="col col-md-2 control-label">description</form:label>
          <div class="col col-md-3">
            <form:input path="description" cssClass="form-control input-sm" />
          </div>
        </div>

        <div class="form-group">
          <form:label path="finished" class="col col-md-2 control-label">finished</form:label>
          <div class="col col-md-3">
            <form:checkbox path="finished" cssClass="form-control input-sm" />
          </div>
        </div>

        <div class="form-group">
          <form:button id="update" class="btn btn-default">update</form:button>
        </div>

      </form:form>

    </div>
    <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>