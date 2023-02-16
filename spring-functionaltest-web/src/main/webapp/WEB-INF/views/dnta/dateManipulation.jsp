<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dnta.dateManipulation" />
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
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<div id="wrapper">
  <h1 id="screenTitle">日付操作</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/dnta/dateManipulation" method="get"
    modelAttribute="calcDateForm" class="form-horizonal">
    <legend>日時の計算</legend>
    <div class="form-group">
      <div class="row">
        <div class="col col-md-2">取得日時</div>
        <div class="col col-md-2">
          <form:input path="inputDateTime" cssClass="form-control input-sm" />
        </div>
      </div>
      <div class="row">
        <div class="col col-md-2">増加数</div>
        <div class="col col-md-2">
          <form:input path="plus" cssClass="form-control input-sm" />
        </div>
      </div>
      <div class="row">
        <div class="col col-md-2">減少数</div>
        <div class="col col-md-2">
          <form:input path="minus" cssClass="form-control input-sm" />
        </div>
      </div>
      <div class="row">
        <div class="col col-md-2">
          <form:button name="calcYear" class="btn btn-default">増減年取得</form:button>
        </div>
        <div class="col col-md-2">
          <form:button name="calcMonth" class="btn btn-default">増減月取得</form:button>
        </div>
        <div class="col col-md-2">
          <form:button name="calcDay" class="btn btn-default">増減日取得</form:button>
        </div>
        <div class="col col-md-2">
          <form:button name="calcHour" class="btn btn-default">増減時取得</form:button>
        </div>
        <div class="col col-md-2">
          <form:button name="calcMinute" class="btn btn-default">増減分取得</form:button>
        </div>
        <div class="col col-md-2">
          <form:button name="calcSecond" class="btn btn-default">増減秒取得</form:button>
        </div>
      </div>
    </div>
  </form:form>
  <br />
  <form:form action="${pageContext.request.contextPath}/dnta/dateManipulation"
    class="form-horizontal" method="get" modelAttribute="compareDateTimeForm">
    <fieldset>
      <legend>日時比較</legend>
      <div class="form-group">
        <table>
          <tr>
            <td></td>
            <td>年</td>
            <td>月</td>
            <td>日</td>
          </tr>
          <tr>
            <td>Past</td>
            <td><form:input path="year1" cssClass="form-control input-sm" /></td>
            <td><form:input path="month1" cssClass="form-control input-sm" /></td>
            <td><form:input path="day1" cssClass="form-control input-sm" /></td>
          </tr>
          <tr>
            <td>Now</td>
            <td><form:input path="year2" cssClass="form-control input-sm" /></td>
            <td><form:input path="month2" cssClass="form-control input-sm" /></td>
            <td><form:input path="day2" cssClass="form-control input-sm" /></td>
          </tr>
          <tr>
            <td>Future</td>
            <td><form:input path="year3" cssClass="form-control input-sm" /></td>
            <td><form:input path="month3" cssClass="form-control input-sm" /></td>
            <td><form:input path="day3" cssClass="form-control input-sm" /></td>
          </tr>
        </table>
        <button id="compareDate" name="compareDate" class="btn btn-default">日付比較</button>
      </div>
      <div class="form-group">
        <table>
          <tr>
            <td></td>
            <td>時</td>
            <td>分</td>
            <td>秒</td>
          </tr>
          <tr>
            <td>Past</td>
            <td><form:input path="hour1" cssClass="form-control input-sm" /></td>
            <td><form:input path="minute1" cssClass="form-control input-sm" /></td>
            <td><form:input path="second1" cssClass="form-control input-sm" /></td>
          </tr>
          <tr>
            <td>Now</td>
            <td><form:input path="hour2" cssClass="form-control input-sm" /></td>
            <td><form:input path="minute2" cssClass="form-control input-sm" /></td>
            <td><form:input path="second2" cssClass="form-control input-sm" /></td>
          </tr>
          <tr>
            <td>Future</td>
            <td><form:input path="hour3" cssClass="form-control input-sm" /></td>
            <td><form:input path="minute3" cssClass="form-control input-sm" /></td>
            <td><form:input path="second3" cssClass="form-control input-sm" /></td>
          </tr>
        </table>
        <button id="compareTime" name="compareTime" class="btn btn-default">時刻比較</button>
      </div>
    </fieldset>
  </form:form>
  <br />
  <form:form action="${pageContext.request.contextPath}/dnta/dateManipulation"
    cssClass="form-horizontal" method="get" modelAttribute="changeTypeForm">
    <legend>日時判定</legend>
    <div class="form-group">
      <div class="col col-md-2">
        <label for="name">日時文字列</label>
      </div>
      <div class="col col-md-2">
        <form:input path="targetDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="checkDateString" class="btn btn-default">日付文字列判定</form:button>
      <form:button name="checkTimeString" class="btn btn-default">時間文字列判定</form:button>
      <form:button name="checkLeapYear" class="btn btn-default">閏年判定</form:button>
    </div>
  </form:form>
  <br />
  <form:form action="${pageContext.request.contextPath}/dnta/dateManipulation"
    cssClass="form-horizontal" method="get" modelAttribute="changeTypeForm">
    <legend>年月日時分秒の取得</legend>
    <div class="form-group">
      <div class="col col-md-2">
        <label for="name">日時文字列</label>
      </div>
      <div class="col col-md-2">
        <input id="target" name="targetDate" class="form-control input-sm" type="text" value="" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="getEachValueOfDate" class="btn btn-default">年月日の取得</form:button>
      <form:button name="getEachValueOfTime" class="btn btn-default">時分秒の取得</form:button>
    </div>
  </form:form>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>