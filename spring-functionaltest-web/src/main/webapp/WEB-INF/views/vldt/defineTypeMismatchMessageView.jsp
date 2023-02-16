<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.vldt.defineTypeMismatchMessageView" />
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
  <h1 id="screenTitle">エラーメッセージの定義</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0303/001"
    modelAttribute="defineTypeMismatchMessageForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="shortValue" class="col-sm-2 control-label">ShortValue</form:label>
      <div class="col-sm-10">
        <form:input path="shortValue" class="form-control" placeholder="ShortValue" />
        <form:errors path="shortValue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="intValue" class="col-sm-2 control-label">IntValue</form:label>
      <div class="col-sm-10">
        <form:input path="intValue" class="form-control" placeholder="IntValue" />
        <form:errors path="intValue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="longValue" class="col-sm-2 control-label">LongValue</form:label>
      <div class="col-sm-10">
        <form:input path="longValue" class="form-control" placeholder="LongValue" />
        <form:errors path="longValue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="floatValue" class="col-sm-2 control-label">FloatValue</form:label>
      <div class="col-sm-10">
        <form:input path="floatValue" class="form-control" placeholder="FloatValue" />
        <form:errors path="floatValue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="doubleValue" class="col-sm-2 control-label">DoubleValue</form:label>
      <div class="col-sm-10">
        <form:input path="doubleValue" class="form-control" placeholder="DoubleValue" />
        <form:errors path="doubleValue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="shortObject" class="col-sm-2 control-label">ShortObject</form:label>
      <div class="col-sm-10">
        <form:input path="shortObject" class="form-control" placeholder="ShortObject" />
        <form:errors path="shortObject" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="integerObject" class="col-sm-2 control-label">IntegerObject</form:label>
      <div class="col-sm-10">
        <form:input path="integerObject" class="form-control" placeholder="IntegerObject" />
        <form:errors path="integerObject" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="longObject" class="col-sm-2 control-label">LongObject</form:label>
      <div class="col-sm-10">
        <form:input path="longObject" class="form-control" placeholder="LongObject" />
        <form:errors path="longObject" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="floatObject" class="col-sm-2 control-label">FloatObject</form:label>
      <div class="col-sm-10">
        <form:input path="floatObject" class="form-control" placeholder="FloatObject" />
        <form:errors path="floatObject" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="doubleObject" class="col-sm-2 control-label">DoubleObject</form:label>
      <div class="col-sm-10">
        <form:input path="doubleObject" class="form-control" placeholder="DoubleObject" />
        <form:errors path="doubleObject" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="date" class="col-sm-2 control-label">Date</form:label>
      <div class="col-sm-10">
        <form:input path="date" class="form-control" placeholder="Date" />
        <form:errors path="date" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="localdatetime" class="col-sm-2 control-label">LocalDateTime</form:label>
      <div class="col-sm-10">
        <form:input path="localdatetime" class="form-control" placeholder="LocalDateTime" />
        <form:errors path="localdatetime" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="localdate" class="col-sm-2 control-label">LocalDate</form:label>
      <div class="col-sm-10">
        <form:input path="localdate" class="form-control" placeholder="LocalDate" />
        <form:errors path="localdate" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="booleanValue" class="col-sm-2 control-label">BooleanValue</form:label>
      <div class="col-sm-10">
        <form:input path="booleanValue" class="form-control" placeholder="BooleanValue" />
        <form:errors path="booleanValue" class="text-danger" />
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