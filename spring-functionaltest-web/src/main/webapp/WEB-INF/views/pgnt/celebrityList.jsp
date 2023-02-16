<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.pgnt.celebrityList" />
<c:set var="functionId" value="pgnt" />

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
<h3 id="screenTitle">Celebrity List Screen</h3>
<div class="text-center">
  <form:form action="${pageContext.request.contextPath}/pgnt/${f:hjs(path)}" method="get"
    modelAttribute="celebritySearchCriteria">
    <fieldset>
      <legend>Celebrity List</legend>
      <form:label cssClass="space" path="firstName">First Name</form:label>
      <form:input cssClass="space name-text-field form-control inline" path="firstName" />

      <form:label cssClass="space" path="lastName">Last Name</form:label>
      <form:input cssClass="space name-text-field form-control inline" path="lastName" />

      <form:select cssClass="space sort-field form-control inline" path="sort">
        <form:option id="celebrityIdASC" value="celebrity_id,ASC">No. ASC</form:option>
        <form:option id="celebrityIdDESC" value="celebrity_id,DESC">No. DESC</form:option>
        <form:option id="firstNameASC" value="first_name,ASC">First Name ASC</form:option>
        <form:option id="firstNameDESC" value="first_name,DESC">First Name DESC</form:option>
        <form:option id="lastNameASC" value="last_name,ASC">Last Name ASC</form:option>
        <form:option id="lastNameDESC" value="last_name,DESC">Last Name DESC</form:option>
      </form:select>

      <input id="searchBtn" type="submit" name="Search" class="btn btn-default" value="Search" />
    </fieldset>
  </form:form>
</div>

<c:if test="${page != null}">
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(celebritySearchCriteria)}" />
  </div>
  <table class="table table-striped table-bordered table-condensed pgnttable">
    <thead>
      <tr>
        <th>No.</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th />
      </tr>
    </thead>

    <c:forEach var="user" items="${page.content}" varStatus="rowStatus">
      <tr>
        <td>${f:h(user.celebrityId)}</td>
        <td>${f:h(user.firstName)}</td>
        <td>${f:h(user.lastName)}</td>
        <td class="text-center"><input type="submit" class="btn btn-default" value="Details" />
        </td>
      </tr>
    </c:forEach>

  </table>
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(celebritySearchCriteria)}" />
  </div>
  <div class="text-center">
    <fmt:formatNumber value="${page.totalElements}" />
    results
  </div>
  <c:if test="${page.totalElements != 0}">
    <div class="text-center">${f:h(page.number + 1) }&nbsp;/&nbsp;${f:h(page.totalPages)}&nbsp;Pages
    </div>
  </c:if>
</c:if>


  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>