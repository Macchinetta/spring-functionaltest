<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.emal.sendMail" />
<c:set var="functionId" value="emal" />

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
  <h1 id="screenTitle">メール送信</h1>
  <br>
  <form:form method="POST" action="${pageContext.request.contextPath}/jsp/sendmail"
    modelAttribute="emailSendingForm" enctype="multipart/form-data" cssClass="form-horizontal">
    <fieldset>
      <legend>メール送信</legend>
      <div class="form-group">
        <div class="col col-md-2">To</div>
        <c:forEach items="${emailSendingForm.to}" var="v" varStatus="s">
          <div class="col col-md-3">
            <form:input path="to[${s.index}]" cssClass="quantity form-control input-sm" />
          </div>
        </c:forEach>
      </div>
      <div class="form-group">
        <div class="col col-md-2">Cc</div>
        <c:forEach items="${emailSendingForm.cc}" var="v" varStatus="s">
          <div class="col col-md-3">
            <form:input path="cc[${s.index}]" cssClass="quantity form-control input-sm" />
          </div>
        </c:forEach>
      </div>
      <div class="form-group">
        <div class="col col-md-2">Bcc</div>
        <c:forEach items="${emailSendingForm.bcc}" var="v" varStatus="s">
          <div class="col col-md-3">
            <form:input path="bcc[${s.index}]" cssClass="quantity form-control input-sm" />
          </div>
        </c:forEach>
      </div>
      <div class="form-group">
        <div class="col col-md-2">Reply-To</div>
        <c:if test="${emailSendingForm.testcase == 'allProperties'}">
          <div class="col col-md-3">
            <form:input path="replyTo" cssClass="quantity form-control input-sm" />
          </div>
        </c:if>
      </div>
      <div class="form-group">
        <div class="col col-md-2">Text</div>
        <div class="col col-md-3">
          <form:input path="text" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">Filename</div>
        <c:if test="${emailSendingForm.testcase == 'attachmentMimeMessage'}">
          <div class="col col-md-3">
            <form:input path="filename" cssClass="quantity form-control input-sm" />
          </div>
        </c:if>
      </div>
      <div class="form-group">
        <div class="col col-md-2">Content-Id</div>
        <c:if test="${emailSendingForm.testcase == 'inlineMimeMessage'}">
          <div class="col col-md-3">
            <form:input path="cid" cssClass="quantity form-control input-sm" />
          </div>
        </c:if>
      </div>
      <div class="form-group">
        <div class="col col-md-2">MultipartFile</div>
        <c:if
          test="${emailSendingForm.testcase == 'attachmentMimeMessage' 
                            || emailSendingForm.testcase == 'inlineMimeMessage'}">
          <div class="col col-md-3">
            <form:input type="file" path="multipartFile" />
          </div>
        </c:if>
      </div>
      <div class="form-group">
        <div class="col col-md-2">Template</div>
        <c:if
          test="${emailSendingForm.testcase == 'templatedMessage' 
                            || emailSendingForm.testcase == 'preparationException'}">
          <div class="col col-md-3">
            <form:select path="templateName">
              <form:option value="registration-confirmation">registration-confirmation</form:option>
              <form:option value="non-existence">non-existence</form:option>
            </form:select>
          </div>
        </c:if>
      </div>
      <form:hidden path="testcase" />
      <div class="form-group">
        <button id="sendMail" name="sendMail" class="btn btn-default">メール送信</button>
      </div>
    </fieldset>
  </form:form>
  <br />
</div>
  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>