<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athr.methodHierarchyAccessAllowedInsertPage" />
<c:set var="functionId" value="athr" />

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
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}_jsp.js"></script>
<script type="text/javascript"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">ROLE_STAFF以上を保持しているデータを入力してregister可のページ</h1>

  <form:form action="${pageContext.request.contextPath}/jsp/0901/003" method="post"
    modelAttribute="systemConfigForm">
    <fieldset>
      <legend>Device</legend>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="device">Device</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="device" name="device">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="broadcast">Broadcast</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="broadcast" name="broadcast">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="ipaddr">Ip Address</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="ipaddr" name="ipaddr">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="netmask">Netmask</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="netmask" name="netmask">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="network">Network</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="network" name="network">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="onboot">On Boot</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="onboot" name="onboot">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="deviceType">Device Type</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="deviceType" name="deviceType">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="gateway">Gateway</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="gateway" name="gateway">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="owner">Owner</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="owner" name="owner">
        </div>
      </div>
      <br> <input type="submit" id="register" class="btn btn-default" value="Register"
        name="register">
    </fieldset>
  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>