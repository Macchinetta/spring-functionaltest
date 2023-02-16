<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.emal.receiveMail" />
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
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">メール受信</h1>
  <br>
  <form:form method="POST" action="${pageContext.request.contextPath}/emal/receivemail"
    modelAttribute="emailReceivingForm" cssClass="form-horizontal">
    <fieldset>
      <legend>メール受信</legend>
      <div class="form-group">
        <div class="col col-md-2">host</div>
        <div class="col col-md-3">
          <form:input path="host" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">port</div>
        <div class="col col-md-3">
          <form:input path="port" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">user</div>
        <div class="col col-md-3">
          <form:input path="user" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">password</div>
        <div class="col col-md-3">
          <form:input path="password" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">identifier</div>
        <div class="col col-md-3">
          <form:input path="identifier" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <button id="receiveMail" name="receiveMail" class="btn btn-default">メール受信</button>
      </div>
    </fieldset>
  </form:form>
  <br>
  <c:if test="${mail != null}">
    <fieldset>
      <table class="table table-bordered table-condensed col-xs-5 col-sm-5 col-md-5 col-g-5">
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">From</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="from">
              ${f:h(mail.from)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Subject</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="subject">
              ${f:h(mail.subject)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">To</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="to"><c:forEach
                items="${mail.to}" var="v">${f:h(v)}<br>
              </c:forEach></span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Cc</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="cc"><c:forEach
                items="${mail.cc}" var="v">${f:h(v)}<br>
              </c:forEach></span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Reply-To</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="replyTo">
              ${f:h(mail.replyTo)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Date</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="date"><fmt:formatDate
                pattern="yyyy/MM/dd HH:mm:ss" value="${mail.sentDate}" /></span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Body</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="body">
              ${f:h(mail.body)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Content-Type</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="contentType">
              ${f:h(mail.contentType)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Attachment</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="attachment">
              ${f:h(mail.attachment)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Inline</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="inline">
              ${f:h(mail.inline)} </span></td>
        </tr>
      </table>
    </fieldset>
  </c:if>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>