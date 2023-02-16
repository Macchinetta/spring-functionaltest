<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.flup.screenFlowFileUploadComplete" />
<c:set var="functionId" value="flup" />

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

  <h1 id="screenTitle">ファイルアップロード(完了)</h1>

  <t:messagesPanel />

  <div class="form-horizontal">

    <c:if test="${not empty uploadedContent}">

      <div class="form-group">
        <label for="fileIdText" class="col col-md-2 control-label">ファイルID</label>
        <div class="col col-md-10">
          <span id="fileIdText" class="display-item form-control">${f:h(uploadedContent.fileId)}</span>
        </div>
      </div>

      <div class="form-group">
        <label for="fileNameText" class="col col-md-2 control-label">ファイル名</label>
        <div class="col col-md-10">
          <span id="fileNameText" class="display-item form-control">${f:h(uploadedContent.fileName)}</span>
        </div>
      </div>

      <div class="form-group">
        <label for="fileContentText" class="col col-md-2 control-label">データ</label>
        <div class="col col-md-10">
          <span id="fileContentText" class="display-item form-control">${f:h(uploadedContent.fileContent)}</span>
        </div>
      </div>

      <div class="form-group">
        <label for="descriptionText" class="col col-md-2 control-label">説明</label>
        <div class="col col-md-10">
          <span id="descriptionText" class="display-item form-control">${f:br(f:h(uploadedContent.description))}</span>
        </div>
      </div>

      <div class="form-group">
        <div class="col col-md-10 col-md-offset-2">
          <form action="${pageContext.request.contextPath}/flup/files" method="get">
            <input type="hidden" name="fileId" value="${f:h(uploadedContent.fileId)}" />
            <button id="downloadButton" name="download" class="btn btn-default">
              <span class="glyphicon glyphicon-download"></span>ダウンロード
            </button>
          </form>
        </div>
      </div>

    </c:if>

  </div>

  <div class="text-center">
    <a href="${pageContext.request.contextPath}/flup" class="btn btn-default"><span
      class="glyphicon glyphicon-arrow-left"></span>ファイルアップロードのトップへ</a>
  </div>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>