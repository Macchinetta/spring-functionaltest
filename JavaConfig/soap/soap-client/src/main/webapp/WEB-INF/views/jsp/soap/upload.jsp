<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.soap.index" />
<c:set var="functionId" value="soap" />

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width" />
  <meta name="contextPath" content="${pageContext.request.contextPath}" />
  <sec:csrfMetaTags />
  <title>
    <spring:message code="label.sf.cmmn.systemName" var="defaultTitle" />
    <spring:message code="${titleKey}" text="${f:h(defaultTitle)}" />
  </title>
  <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
  <script type="text/javascript"
    src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
  <script type="text/javascript"></script>
</head>

<body>
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
    <div id="wrapper">

      <h1 id="screenTitle">file upload</h1>

      <t:messagesPanel />

      <form:form modelAttribute="uploadFileForm" method="post"
        action="${pageContext.request.contextPath}/todo/${f:h(proxy)}/upload" cssClass="form-horizontal"
        enctype="multipart/form-data">

        <div class="form-group">
          <form:label path="multipartFile" cssClass="col col-md-2 control-label">contents file</form:label>
          <div class="col col-md-10">
            <form:input type="file" path="multipartFile" cssClass="multipartFile" />
            <form:errors path="multipartFile" cssClass="text-danger" element="div" />
          </div>
        </div>

        <div class="form-group">
          <div class="col col-md-10 col-md-offset-2">
            <form:button id="upload" name="upload" class="btn btn-default">
              <span class="glyphicon glyphicon-upload"></span>upload
            </form:button>
          </div>
        </div>

      </form:form>

    </div>
    <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>