<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.djpa.jpaUpdateForm" />
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
<form:form modelAttribute="bookForm"
  action="${pageContext.request.contextPath}/jsp/book/${book.bookId}/update">
  <fieldset>
    <legend>書籍更新</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <label>書籍ID</label>
        </div>
        <div class="col-md-4">
          <form:input path="bookId" class="form-control" value="${f:h(book.bookId)}" readonly="true"
            style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>カテゴリ</label>
        </div>
        <div class="col-md-4">
          <form:input path="categoryName" class="form-control"
            value="${f:h(book.category.categoryName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>タイトル</label>
        </div>
        <div class="col-md-4">
          <form:input path="title" class="form-control" value="${f:h(book.title)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>書籍コード</label>
        </div>
        <div class="col-md-4">
          <form:input path="clobCode" class="form-control" value="${f:h(book.clobCode)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>書籍コード(バイナリ)</label>
        </div>
        <div class="col-md-4">
          <form:input path="blobCode" class="form-control" value="${f:h(book.blobCodeHex)}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>価格</label>
        </div>
        <div class="col-md-4">
          <form:input path="price" class="form-control" value="${f:h(book.price)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>発売日</label>
        </div>
        <div class="col-md-4">
          <fmt:formatDate value="${book.releaseDate}" pattern="yyyy/MM/dd" var="releaseDate" />
          <form:input path="releaseDate" class="form-control" value="${releaseDate}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>作成者</label>
        </div>
        <div class="col-md-4">

          <form:input path="createdBy" class="form-control" value="${f:h(book.createdBy)}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>作成日時</label>
        </div>
        <div class="col-md-4">
          <javatime:format value="${book.createdDate}" pattern="yyyy/MM/dd" var="crDate" />
          <form:input path="createdDate" class="form-control" value="${crDate}" readonly="true"
            style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>最終更新者</label>
        </div>
        <div class="col-md-4">
          <form:input path="lastModifiedBy" class="form-control" value="${f:h(book.lastModifiedBy)}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>最終更新日時</label>
        </div>
        <div class="col-md-4">
          <javatime:format value="${book.lastModifiedDate}" pattern="yyyy/MM/dd" var="modDate" />
          <form:input path="lastModifiedDate" class="form-control" value="${modDate}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
          <form:hidden path="version" value="${f:h(book.version)}" />
          <form:button name="update" class="btn btn-default">更新</form:button>
          <form:button name="delete" class="btn btn-default">削除</form:button>
          <form:button name="cancel" class="btn btn-default">キャンセル</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>