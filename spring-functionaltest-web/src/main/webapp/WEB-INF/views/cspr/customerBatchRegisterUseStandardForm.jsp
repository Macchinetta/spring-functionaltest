<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.cspr.customerBatchRegisterUseStandardForm" />
<c:set var="functionId" value="cspr" />

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

  <h3 id="screenTitle">顧客一括登録画面</h3>

  <form id="customerBatchRegisterForm" class="form-horizontal"
    action="${pageContext.request.contextPath}/cspr/registerWithTokenFromQuery?${f:h(_csrf.parameterName)}=${f:h(_csrf.token)}"
    method="POST" enctype="multipart/form-data">
    <div class="form-group">
      <label for="multipartFile" class="col col-md-2 control-label">一括登録ファイル</label>
      <div class="col col-md-10">
        <input id="multipartFile" name="multipartFile" type="file" value="" />
        <spring:nestedPath path="customerBatchRegisterForm">
          <form:errors path="multipartFile" cssClass="errorMessage" />
        </spring:nestedPath>
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <button id="uploadButton" name="upload" class="btn btn-default" type="submit" value="Submit">アップロード</button>
      </div>
    </div>
  </form>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>