<!-- ナビゲーションバー -->
<c:if test="${principal.user != null}">
  <sec:authentication property="principal.user" var="user" />
</c:if>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse"
        data-target=".navbar-collapse">
        <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}"><spring:message
          code="label.sf.cmmn.systemName" /></a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav pull-right">
        <li class="nav-divider"></li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
            class="glyphicon glyphicon-user"></span> <c:choose>
              <c:when test="${user != null}">${f:h(user.firstName)}&nbsp;${f:h(user.lastName)}</c:when>
              <c:otherwise>
                <spring:message code="label.sf.cmmn.defaultUserName" />
              </c:otherwise>
            </c:choose> さん <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="#users/update"><span class="glyphicon glyphicon-edit"></span>
                ユーザ情報変更</a></li>
            <li><a href="${pageContext.request.contextPath}/logout"><span
                class="glyphicon glyphicon-off"></span> ログアウト</a></li>
          </ul></li>
      </ul>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>
