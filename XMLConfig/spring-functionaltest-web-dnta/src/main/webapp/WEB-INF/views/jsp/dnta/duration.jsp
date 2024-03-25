<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dnta.duration" />
<c:set var="functionId" value="dnta" />

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
  <h1 id="screenTitle">期間</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/jsp/duration" class="form-horizontal"
    method="get" modelAttribute="compareDateForm">
    <fieldset>
      <legend>日付比較</legend>
      <div class="form-group">
        <table>
          <tr>
            <td>year1</td>
            <td>month1</td>
            <td>day1</td>
          </tr>
          <tr>
            <td><form:input path="year1" cssClass="form-control input-sm" /></td>
            <td><form:input path="month1" cssClass="form-control input-sm" /></td>
            <td><form:input path="day1" cssClass="form-control input-sm" /></td>
          </tr>
          <tr>
            <td>year2</td>
            <td>month2</td>
            <td>day2</td>
          </tr>
          <tr>
            <td><form:input path="year2" cssClass="form-control input-sm" /></td>
            <td><form:input path="month2" cssClass="form-control input-sm" /></td>
            <td><form:input path="day2" cssClass="form-control input-sm" /></td>
          </tr>
        </table>
        <button id="compareDate" name="compareDate" class="btn btn-default">日付比較</button>
      </div>
    </fieldset>
  </form:form>
  <br />
  <form:form action="${pageContext.request.contextPath}/jsp/duration" cssClass="form-horizontal"
    method="get" modelAttribute="compareTimeForm">
    <fieldset>
      <legend>時間比較</legend>
      <div class="form-group">
        <table>
          <tr>
            <td>hour1</td>
            <td>minute1</td>
            <td>second1</td>
          </tr>
          <tr>
            <td><form:input path="hour1" cssClass="form-control input-sm" /></td>
            <td><form:input path="minute1" cssClass="form-control input-sm" /></td>
            <td><form:input path="second1" cssClass="form-control input-sm" /></td>
          </tr>
          <tr>
            <td>hour2</td>
            <td>minute2</td>
            <td>second2</td>
          </tr>
          <tr>
            <td><form:input path="hour2" cssClass="form-control input-sm" /></td>
            <td><form:input path="minute2" cssClass="form-control input-sm" /></td>
            <td><form:input path="second2" cssClass="form-control input-sm" /></td>
          </tr>
        </table>
        <button id="compareTime" name="compareTime" class="btn btn-default">時間比較</button>
      </div>
    </fieldset>
  </form:form>
  <form:form action="${pageContext.request.contextPath}/jsp/duration" cssClass="form-horizontal"
    method="get" modelAttribute="compareZonedDateTimeForm">
    <fieldset>
      <legend>時間比較(サマータイム)</legend>
      <div class="form-group">
        <table>
          <tr>
            <td>year1</td>
            <td>month1</td>
            <td>day1</td>
            <td>hour1</td>
            <td>minute1</td>
            <td>second1</td>
            <td>zone1</td>
          </tr>
          <tr>
            <td><form:input path="zonedDateTimeyYear1" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeMonth1" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeDay1" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeHour1" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeMinute1" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeSecond1" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeZone1" cssClass="form-control input-sm" /></td>
          </tr>
          <tr>
            <td>year2</td>
            <td>month2</td>
            <td>day2</td>
            <td>hour2</td>
            <td>minute2</td>
            <td>day2</td>
            <td>zone2</td>
          </tr>
          <tr>
          <tr>
            <td><form:input path="zonedDateTimeYear2" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeMonth2" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeDay2" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeHour2" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeMinute2" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeSecond2" cssClass="form-control input-sm" /></td>
            <td><form:input path="zonedDateTimeZone2" cssClass="form-control input-sm" /></td>
          </tr>
        </table>
        <button id="compareTimeWithSummerTime" name="compareTimeWithSummerTime"
          class="btn btn-default">時間比較(サマータイム)</button>
      </div>
    </fieldset>
  </form:form>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>