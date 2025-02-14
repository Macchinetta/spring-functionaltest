<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.athr.showMethodAccessAllowedPage" />
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

  <h1 id="screenTitle">Method認可されたユーザが開くページ</h1>

  <fieldset>
    <legend>Device Information</legend>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>systemConfig.device</th>
        <td><span id="device"> ${f:h(systemConfig.device)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.broadcast</th>
        <td><span id="broadcast"> ${f:h(systemConfig.broadcast)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.ipaddr</th>
        <td><span id="ipaddr"> ${f:h(systemConfig.ipaddr)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.netmask</th>
        <td><span id="netmask"> ${f:h(systemConfig.netmask)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.network</th>
        <td><span id="network"> ${f:h(systemConfig.network)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.onboot</th>
        <td><span id="onboot"> ${f:h(systemConfig.onboot)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.deviceType</th>
        <td><span id="deviceType"> ${f:h(systemConfig.deviceType)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.gateway</th>
        <td><span id="gateway"> ${f:h(systemConfig.gateway)} </span></td>
      </tr>
      <tr>
        <th>systemConfig.owner</th>
        <td><span id="owner"> ${f:h(systemConfig.owner)} </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/jsp/0301/001/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>