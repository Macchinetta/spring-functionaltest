<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.pgnt.articleList" />
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
<h3 id="screenTitle">Article List Screen</h3>
<div class="text-center">
  <form:form action="${pageContext.request.contextPath}/pgnt/${f:hjs(path)}" method="get"
    modelAttribute="articleSearchCriteria">
    <fieldset>
      <legend>Article List</legend>
      <form:label cssClass="space" path="title">Title</form:label>
      <form:input cssClass="space title-text-field form-control inline" path="title" />
      <input id="searchBtn" type="submit" name="Search" class="btn btn-default" value="Search" />
    </fieldset>
  </form:form>
</div>

<c:if test="${page != null}">
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(articleSearchCriteria)}" />
  </div>
  <table class="table table-striped table-bordered table-condensed pgnttable">
    <thead>
      <tr>
        <th>No.</th>
        <th>Category</th>
        <th>Title</th>
        <th>Overview</th>
        <th>Publish Date</th>
      </tr>
    </thead>

    <c:forEach var="article" items="${page.content}" varStatus="rowStatus">
      <tr>
        <td>${f:h(article.articleId)}</td>
        <td>${f:h(article.category)}</td>
        <td><a href="">${f:h(article.title)}</a></td>
        <td>${f:h(article.overview)}</td>
        <td><fmt:formatDate value="${article.publishDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
      </tr>
    </c:forEach>

  </table>
  <div class="text-center">
    <t:pagination page="${page}" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}&${f:query(articleSearchCriteria)}" />
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