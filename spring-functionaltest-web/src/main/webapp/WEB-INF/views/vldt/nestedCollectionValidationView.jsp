<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.vldt.nestedCollectionValidationView" />
<c:set var="functionId" value="vldt" />

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
  <h1 id="screenTitle">ネストした単項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0102/002"
    modelAttribute="nestedCollectionValidationForm" class="form-horizontal">

    <div class="form-group">
      <div class="col-sm-10">
        <form:errors path="addresses" class="text-danger" />
      </div>
    </div>

    <c:forEach items="${ nestedCollectionValidationForm.addresses }" varStatus="status">
      <legend>Address${ f:h(status.index + 1) }</legend>

      <div class="form-group">
        <form:label path="addresses[${ status.index }].userName" class="col-sm-2 control-label">UserName</form:label>
        <div class="col-sm-10">
          <form:input path="addresses[${ status.index }].userName" class="form-control"
            placeholder="UserName" />
          <form:errors path="addresses[${ status.index }].userName" class="text-danger" />
        </div>
      </div>

      <div class="form-group">
        <form:label path="addresses[${ status.index }].postCode" class="col-sm-2 control-label">PostCode</form:label>
        <div class="col-sm-10">
          <form:input path="addresses[${ status.index }].postCode" class="form-control"
            placeholder="PostCode" />
          <form:errors path="addresses[${ status.index }].postCode" class="text-danger" />
        </div>
      </div>

      <div class="form-group">
        <form:label path="addresses[${ status.index }].address" class="col-sm-2 control-label">Address</form:label>
        <div class="col-sm-10">
          <form:input path="addresses[${ status.index }].address" class="form-control"
            placeholder="Address" />
          <form:errors path="addresses[${ status.index }].address" class="text-danger" />
        </div>
      </div>

      <c:if test="${ status.index > 0 }">
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <form:button id="remove" name="remove" class="btn btn-default">remove</form:button>
          </div>
        </div>
      </c:if>

    </c:forEach>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="add" name="add" class="btn btn-default">add</form:button>
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
      </div>
    </div>

  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>