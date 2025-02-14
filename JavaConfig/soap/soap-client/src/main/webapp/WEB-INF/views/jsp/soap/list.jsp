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

      <h1 id="screenTitle">show todos</h1>

      <c:if test="${not empty todos}">
        <table id="todos" class="table table-striped table-bordered table-condensed">
          <tr>
            <th>todoId</th>
            <th>title</th>
            <th>description</th>
            <th>finished</th>
            <th>createdAt</th>
          </tr>
          <c:forEach var="todo" items="${todos}" varStatus="status">
            <tr>
              <td><span id="todoId${status.count}">${todo.todoId}</span></td>
              <td><span id="title${status.count}">${todo.title}</span></td>
              <td><span id="description${status.count}">${todo.description}</span></td>
              <td><span id="finished${status.count}">${todo.finished}</span></td>
              <td><span id="createdAt${status.count}">${todo.createdAt}</span></td>
            </tr>
          </c:forEach>
        </table>
      </c:if>

    </div>
    <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>