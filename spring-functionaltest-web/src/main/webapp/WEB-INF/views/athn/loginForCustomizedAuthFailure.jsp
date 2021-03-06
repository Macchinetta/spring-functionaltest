<div id="wrapper">

  <h1 id="screenTitle">ログインフォーム(loginForCustomizedAuthFailure)</h1>

  <c:if test="${param.containsKey('error')}">
    <span id="loginError"> <t:messagesPanel
        messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" panelClassName="alert alert-danger" />
    </span>
  </c:if>

  <form:form action="${pageContext.request.contextPath}/athn/1302/001/authenticate"
    cssClass="form-horizontal" method="post">
    <legend>ログイン</legend>
    <div class="form-group">
      <label for="userName" class="col col-md-2 control-label">Administrator Name</label>
      <div class="col col-md-3">
        <input type="text" class="form-control input-sm" id="username" name="username" />
      </div>
      <div class="col col-md-3"></div>
    </div>
    <div class="form-group">
      <label for="password" class="col col-md-2 control-label">Password</label>
      <div class="col col-md-3">
        <input type="password" class="form-control input-sm" id="password" name="password" />
      </div>
      <div class="col col-md-3"></div>
    </div>
    <div class="form-group">
      <input type="submit" id="login" class="btn btn-default" value="Login">
    </div>
  </form:form>
</div>

