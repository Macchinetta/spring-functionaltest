<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.djpa.registerForm" />
<c:set var="functionId" value="djpa" />

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
<form:form modelAttribute="bookForm" action="${pageContext.request.contextPath}/jsp/book/register">
  <fieldset>
    <legend>書籍登録</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <label>書籍ID</label>
        </div>
        <div class="col-md-4">
          <form:input path="bookId" class="form-control" value="" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>カテゴリ</label>
        </div>
        <div class="col-md-4">
          <form:input path="categoryName" class="form-control" value="" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>タイトル</label>
        </div>
        <div class="col-md-4">
          <form:input path="title" class="form-control" value="" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>書籍コード</label>
        </div>
        <div class="col-md-4">
          <form:input path="clobCode" class="form-control" value="" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>書籍コード(バイナリ)</label>
        </div>
        <div class="col-md-4">
          <form:input path="blobCode" class="form-control" value="" readonly="true"
            style="background-color: whitesmoke;" />
        </div>
        <div class="col-md-4" style="padding-top: 0.5em;">
          <a>自動生成</a>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>価格</label>
        </div>
        <div class="col-md-4">
          <form:input path="price" class="form-control" value="" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>発売日</label>
        </div>
        <div class="col-md-4">
          <form:input path="releaseDate" class="form-control" value="" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
          <ul>
            <li><form:button name="register" class="btn btn-default">登録</form:button></li>
            <li><form:button name="errorReg" class="btn btn-default">RollBack Demo</form:button></li>
            <li><form:button name="cancel" class="btn btn-default">キャンセル</form:button></li>
            <li><form:button name="crudReg" class="btn btn-default">Crude Repository Verification</form:button></li>
            <li><form:button name="saveAndFlush" class="btn btn-default">Add using Save And Flush</form:button></li>
            <li><form:button name="flush" class="btn btn-default">Add using Flush</form:button></li>
            <li><form:button name="customRepoSave" class="btn btn-default">Add Using Custom Repository</form:button></li>
            <li><form:button name="noIntfInheriRepoSave" class="btn btn-default">Add Using No Interface Inheritance</form:button></li>
            <li><form:button name="registerDIV" class="btn btn-default">Register For Data Integrity Violation</form:button></li>
          </ul>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>