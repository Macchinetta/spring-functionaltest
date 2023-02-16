<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dtop.dateManipulation" />
<c:set var="functionId" value="dtop" />

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
  <h1 id="screenTitle">日付操作</h1>
  <br>

  <form:form action="${pageContext.request.contextPath}/dtop/calcdate" method="get"
    modelAttribute="dateManipulationForm" class="form-horizonal">
    <legend>指定した値分、増加、減少</legend>
    <div class="form-group">
      <div class="row">
        <div class="col col-md-2">取得日付</div>
        <div class="col col-md-2">
          <form:input path="targetIncDecDate" cssClass="form-control input-sm" />
        </div>
      </div>
      <div class="row">
        <div class="col col-md-2">増加数</div>
        <div class="col col-md-2">
          <form:input path="targetIncreaseNum" cssClass="form-control input-sm" />
        </div>
      </div>
      <div class="row">
        <div class="col col-md-2">減少数</div>
        <div class="col col-md-2">
          <form:input path="targetDecreaseNum" cssClass="form-control input-sm" />
        </div>
      </div>
    </div>
    <div class="form-group">
      <form:button name="calcDayDate" class="btn btn-default">増減日取得</form:button>
      <form:button name="calcMonthDate" class="btn btn-default">増減月取得</form:button>
      <form:button name="calcYearDate" class="btn btn-default">増減年取得</form:button>
    </div>
  </form:form>

  <form:form action="${pageContext.request.contextPath}/dtop/calcdate" method="get"
    modelAttribute="dateManipulationForm" class="form-horizonal">
    <legend>特定日付を基準に、期間の最初と最後の日付を取得</legend>
    <div class="form-group">
      <div class="row">
        <div class="col col-md-2">取得日付</div>
        <div class="col col-md-2">
          <form:input path="targetStartEndDate" cssClass="form-control input-sm" />
        </div>
      </div>
    </div>
    <div class="form-group">
      <form:button name="calcMonthStartEndDate" class="btn btn-default">月初月末取得</form:button>
      <form:button name="calcWeekStartEndDate" class="btn btn-default">週初週末取得</form:button>
    </div>
  </form:form>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>