<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.exhn.employeeEdit" />
<c:set var="functionId" value="exhn" />

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
  <h3 id="screenTitle">社員情報編集画面</h3>

  <t:messagesPanel />
  
  ${pageContext.request.contextPath}

  <form:form action="${pageContext.request.contextPath}/exhn${f:h(testNumber)}"
    cssClass="form-horizontal" method="post" modelAttribute="employeeForm">
    <fieldset>
      <legend>社員情報</legend>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="employeeId">社員ID</label>
        </div>
        <div class="col col-md-4">
          <form:input readonly="true" id="employeeId" path="employeeId"
            cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="employeeId" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="employeeName">名前</label>
        </div>
        <div class="col col-md-4">
          <form:input id="employeeName" path="employeeName" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="employeeName" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="email">Email</label>
        </div>
        <div class="col col-md-4">
          <form:input id="email" path="email" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="email" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="address">住所</label>
        </div>
        <div class="col col-md-4">
          <form:input id="address" path="address" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="address" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group col col-md-5">
        <form:button name="backToIndex" class="btn btn-default">戻る</form:button>
        <form:button name="update" class="btn btn-default">更新</form:button>
      </div>
    </fieldset>

  </form:form>

</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>