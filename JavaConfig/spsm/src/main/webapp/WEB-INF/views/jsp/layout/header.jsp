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

