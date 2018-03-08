<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" scope="request">
  <tiles:insertAttribute name="title" ignore="true" />
</c:set>
<c:set var="functionId" scope="request">
  <tiles:insertAttribute name="functionId" ignore="true" />
</c:set>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<meta name="_csrf_token" content="${_csrf.token}" />
<meta name="_csrf_headerName" content="${_csrf.headerName}" />
<title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message
    code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
<script type="text/javascript">
	
</script>
</head>

<body>
  <div id="header">
    <tiles:insertAttribute name="header" />
  </div>
  <div class="container">
    <div id="searchMenu" style="width: 30%;">
      <tiles:insertAttribute name="menu" />
    </div>
    <div id="searchBody" style="width: 68%">
      <tiles:insertAttribute name="body" />
    </div>
    <div id="dummy">
      <input type="hidden" name="template" value="templateSearch">
    </div>
  </div>
  <tiles:insertAttribute name="footer" />
</body>

<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
<script type="text/javascript">
	
</script>

</html>
