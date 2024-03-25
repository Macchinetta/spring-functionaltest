<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.flup.temporaryFileUploadList" />
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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">仮アップロード時の一時ファイル一覧</h1>

  <div id="messages">
    <t:messagesPanel />
  </div>

  <c:if test="${not empty temporaryFiles}">

    <div>
      <table class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th class="text-center">No</th>
            <th class="text-center">ファイルID</th>
            <th class="text-center">サイズ</th>
            <th class="text-center">アップロード日時</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${temporaryFiles}" var="temporaryFile" varStatus="rowStatus">
            <tr>
              <td class="text-center">${f:h(rowStatus.count)}</td>
              <td><a target="uploadedTemporaryFile"
                href="${pageContext.request.contextPath}/uploadedTemporaryFiles/${f:u(temporaryFile.fileId)}"><span
                  class="glyphicon glyphicon-new-window"></span>${f:h(temporaryFile.fileId)}</a></td>
              <td class="text-right"><fmt:formatNumber value="${temporaryFile.size/1024}"
                  pattern="###,##0.00 KB" /></td>
              <td class="text-center"><fmt:formatDate value="${temporaryFile.uploadedAt}"
                  pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>

  <form:form method="post" class="form-horizontal">
    <a href="${pageContext.request.contextPath}/flup/temporaryFiles?list" id="refreshButton"
      class="btn btn-default"><span class="glyphicon glyphicon-refresh"></span>最新状態 </a>
    <button id="deleteAllButton" name="delete" class="btn btn-default">
      <span class="glyphicon glyphicon-trash"></span>全ファイル削除
    </button>
  </form:form>

  <div class="text-center">
    <a href="${pageContext.request.contextPath}/" class="btn btn-default"><span
      class="glyphicon glyphicon-arrow-left"></span>ファイルアップロードのトップへ</a>
  </div>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>