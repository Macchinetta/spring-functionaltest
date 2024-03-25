<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dnta.japaneseDate" />
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
  <h1 id="screenTitle">和暦</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/jsp/japaneseDate"
    cssClass="form-horizontal" method="get" modelAttribute="dateForm">
    <legend>和暦取得</legend>
    <div class="form-group">
      <table>
        <tr>
          <td>year</td>
          <td><input id="year1" name="year" class="form-control input-sm" type="text" value="" /></td>
        </tr>
        <tr>
          <td>month</td>
          <td><input id="month1" name="month" class="form-control input-sm" type="text"
            value="" /></td>
        </tr>
        <tr>
          <td>day</td>
          <td><input id="day1" name="day" class="form-control input-sm" type="text" value="" /></td>
        </tr>
      </table>
    </div>
    <div class="form-group">
      <form:button id="getNowDate" name="getNowDate" class="btn btn-default">現在時刻取得</form:button>
      <form:button id="getSpecifiedDate" name="getSpecifiedDate" class="btn btn-default">指定日時取得</form:button>
      <form:button id="getSpecifiedJapaneseDate" name="getSpecifiedJapaneseDate"
        class="btn btn-default">和暦指定取得</form:button>
    </div>
  </form:form>
  <br>
  <form:form action="${pageContext.request.contextPath}/jsp/japaneseDate"
    cssClass="form-horizontal" method="get" modelAttribute="dateForm">
    <legend>日時文字列にフォーマット</legend>
    <div class="form-group">
      <table>
        <tr>
          <td>year</td>
          <td><input id="year2" name="year" class="form-control input-sm" type="text" value="" /></td>
        </tr>
        <tr>
          <td>month</td>
          <td><input id="month2" name="month" class="form-control input-sm" type="text"
            value="" /></td>
        </tr>
        <tr>
          <td>day</td>
          <td><input id="day2" name="day" class="form-control input-sm" type="text" value="" /></td>
        </tr>
      </table>
    </div>
    <div class="form-group">
      <form:button id="format" name="format" class="btn btn-default">西暦変換</form:button>
    </div>
  </form:form>
  <br />
  <form:form action="${pageContext.request.contextPath}/jsp/japaneseDate"
    cssClass="form-horizontal" method="get" modelAttribute="changeTypeForm">
    <legend>文字列からのパース</legend>
    <div class="form-group">
      <div class="col col-md-2">
        <label for="name">日時文字列</label>
      </div>
      <div class="col col-md-2">
        <form:input path="targetDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="parse" class="btn btn-default">日付文字列判定</form:button>
    </div>
  </form:form>
  <br>
  <form:form action="${pageContext.request.contextPath}/jsp/japaneseDate"
    cssClass="form-horizontal" method="get" modelAttribute="dateForm">
    <legend>和暦取得</legend>
    <div class="form-group">
      <table>
        <tr>
          <td>year</td>
          <td><input id="year3" name="year" class="form-control input-sm" type="text" value="" /></td>
        </tr>
        <tr>
          <td>month</td>
          <td><input id="month3" name="month" class="form-control input-sm" type="text"
            value="" /></td>
        </tr>
        <tr>
          <td>day</td>
          <td><input id="day3" name="day" class="form-control input-sm" type="text" value="" /></td>
        </tr>
      </table>
    </div>
    <div class="form-group">
      <form:button id="toJapaneseDate" name="toJapaneseDate" class="btn btn-default">西暦変換</form:button>
    </div>
  </form:form>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>