<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" scope="request">
  <tiles:insertAttribute name="title" ignore="true" />
</c:set>
<c:set var="functionId" scope="request">
  <tiles:insertAttribute name="functionId" ignore="true" />
</c:set>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<meta name="_csrf_token" content="${_csrf.token}" />
<meta name="_csrf_headerName" content="${_csrf.headerName}" />
<title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message
    code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
</head>
<body>
  <!-- ナビゲーションバー -->
  <sec:authentication property="principal" var="principal" />

  <div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
          data-target=".navbar-collapse">
          <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
        </button>
        <a id="springTestTop" class="navbar-brand" href="${pageContext.request.contextPath}"><spring:message
            code="label.sf.cmmn.systemName" /></a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav pull-right">
          <li class="nav-divider"></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
              class="glyphicon glyphicon-user"></span> <span id="loginUserName"> <sec:authorize
                  access="isAuthenticated()">
                        ${f:h(principal.username)}
                    </sec:authorize> <sec:authorize access="!isAuthenticated()">
                  <spring:message code="label.sf.cmmn.defaultUserName" />
                </sec:authorize>
            </span> さん <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#users/update"><span class="glyphicon glyphicon-edit"></span>
                  ユーザー情報変更</a></li>
              <li><a href="${pageContext.request.contextPath}/logout"><span
                  class="glyphicon glyphicon-off"></span> ログアウト</a></li>
            </ul></li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
  </div>

  <div class="container">
    <h2 id="bodyTitle">Delete Complete</h2>
    <form:form modelAttribute="staffForm"
      action="${pageContext.request.contextPath}/tlly/staff/delete">
      <fieldset>
        <legend>Staff Information</legend>
        <div class="container">
          <div class="row">
            <div class="col-md-2">
              <label>First Name</label>
            </div>
            <div class="col-md-4">
              <form:input path="firstName" class="form-control" readonly="true"
                style="background-color: whitesmoke;" value="${f:h(staff.firstName)}" />
            </div>
          </div>
          <div class="row">
            <div class="col-md-2">
              <label>Last Name</label>
            </div>
            <div class="col-md-4">
              <form:input path="lastName" class="form-control" readonly="true"
                style="background-color: whitesmoke;" value="${f:h(staff.lastName)}" />
            </div>
          </div>
          <div class="row">
            <div class="col-md-2">
              <label>Authority</label>
            </div>
            <div class="col-md-4">
              <form:select class="form-control" path="authority" disabled="true"
                style="background-color: whitesmoke;">
                <option value="STAFF_MNG" ${staff.authority.equals("STAFF_MNG") ? 'selected' : ''}>Staff
                  Management</option>
                <option value="MASTER_MNG" ${staff.authority.equals("MASTER_MNG") ? 'selected' : ''}>Master
                  Management</option>
                <option value="STOCK_MNG" ${staff.authority.equals("STOCK_MNG") ? 'selected' : ''}>Stock
                  Management</option>
                <option value="ORDER_MNG" ${staff.authority.equals("ORDER_MNG") ? 'selected' : ''}>Order
                  Management</option>
                <option value="SHOW_SHOPPING_MNG"
                  ${staff.authority.equals("SHOW_SHOPPING_MNG") ? 'selected' : ''}>Show
                  Shopping Management</option>
              </form:select>
            </div>
          </div>
          <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-4">
              <form:button name="back" class="btn btn-default">Back</form:button>
            </div>
          </div>
        </div>
      </fieldset>
    </form:form>
  </div>

  <div>
    <hr>
    <p id="copyright">Copyright &copy; 20XX CompanyName</p>
  </div>
</body>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
<script type="text/javascript">
	
</script>
</html>