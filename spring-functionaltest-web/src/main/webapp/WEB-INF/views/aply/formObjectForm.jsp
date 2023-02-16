<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.aply.formObjectForm" />
<c:set var="functionId" value="aply" />

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

  <h1 id="screenTitle">フォームオブジェクトの実装 入力画面</h1>
  <br />
  <form:form method="post" cssClass="form-horizontal" modelAttribute="formObjectForm">
    <form:errors cssClass="errorMessage" />
    <fieldset>
      <div class="form-group">
        <div class="col col-md-4">value1(数値書式"#,#")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value1" />
        </div>
        <div class="col col-md-3">
          <span id="value1ValidationErrorMessage"><form:errors path="value1"
              cssClass="errorMessage" /></span>
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value2(数値書式"style=NUMBER")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value2" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value3(数値書式"style=CURRENCY")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value3" />
        </div>
        <div class="col col-md-3">
          <form:errors path="value3" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value4(数値書式"style=PERCENT")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value4" />
        </div>
        <div class="col col-md-3">
          <form:errors path="value4" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value5(日時書式"yyyyMMdd")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value5" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value6(InitBinder利用)</div>
        <div class="col col-md-2">
          <form:input type="text" path="value6" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value7(InitBinder利用)</div>
        <div class="col col-md-2">
          <form:input type="text" path="value7" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <form:button id="submit" name="submit" class="btn btn-default">submit</form:button>
      </div>
    </fieldset>
  </form:form>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>