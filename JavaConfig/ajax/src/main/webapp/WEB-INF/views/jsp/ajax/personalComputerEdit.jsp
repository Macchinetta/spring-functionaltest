<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ajax.personalComputerEdit" />
<c:set var="functionId" value="ajax" />

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
  <jsp:include page="../layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <div id="xMLHttpRequestResult" class="hidden alert">
    <ul>
      <li id="returnStatus"></li>
      <li id="contentType"></li>
    </ul>
  </div>

  <div id="resultMessage" class="hidden alert"></div>

  <form id="personalComputerForm" action="${pageContext.request.contextPath}/jsp/0201">
    <spring:nestedPath path="personalComputerForm">
      <fieldset>
        <legend>登録PC編集画面</legend>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <label for="personalComputerId">PC ID</label>
          </div>
          <div class="col col-md-4">
            <input id="personalComputerId" readonly="readonly" class="form-control input-sm"
              value="1" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="personalComputerName">PC名</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="personalComputerName" cssClass="form-control input-sm"
              value="${f:h(personalComputerForm.personalComputerName)}" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="os">OS</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="os" cssClass="form-control input-sm"
              value="${f:h(personalComputerForm.os)}" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="cpu">CPU</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="cpu" cssClass="form-control input-sm"
              value="${f:h(personalComputerForm.cpu)}" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="ram">RAM</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="ram" cssClass="form-control input-sm"
              value="${f:h(personalComputerForm.ram)}" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="videocard">VIDEOCARD</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="videocard" cssClass="form-control input-sm"
              value="${f:h(personalComputerForm.videocard)}" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="hdd">HDD</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="hdd" cssClass="form-control input-sm"
              value="${f:h(personalComputerForm.hdd)}" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="power">POWER</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="power" cssClass="form-control input-sm"
              value="${f:h(personalComputerForm.power)}" />
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col col-md-2">
            <form:label path="price">PRICE(円)</form:label>
          </div>
          <div class="col col-md-4">
            <form:input path="price" cssClass="form-control input-sm" type="text"
              value="${f:h(personalComputerForm.price)}" />
          </div>
        </div>
        <br> <br>
        <div class="form-group col col-md-5">
          <form:button id="retrunToIndex" name="retrunToIndex" class="btn btn-default">戻る</form:button>
          <input id="edit" type="button" name="edit" class="btn btn-default"
            onclick="return ${f:hjs(method)}('${f:hjs(path)}')" value="編集" />
        </div>
      </fieldset>
    </spring:nestedPath>
  </form>
</div>
  </div>
  <jsp:include page="../layout/footer.jsp" />
</body>
</html>