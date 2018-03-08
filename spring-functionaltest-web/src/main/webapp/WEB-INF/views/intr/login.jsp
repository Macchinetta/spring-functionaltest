<div id="wrapper">
  <h1 id="screenTitle">ログイン</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/intr/0301/001/authentication"
    class="form-horizontal">

    <c:if test="${param.containsKey('error')}">
      <span id="accountLoginError"> <t:messagesPanel
          messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
      </span>
    </c:if>

    <div class="form-group">
      <label for="userName" class="col-sm-2 control-label">UserName</label>
      <div class="col-sm-10">
        <input type="text" id="username" name="username" class="form-control" placeholder="UserName" />
      </div>
    </div>

    <div class="form-group">
      <label for="userName" class="col-sm-2 control-label">Password</label>
      <div class="col-sm-10">
        <input type="text" id="password" name="password" class="form-control" placeholder="Password" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" id="login" name="login" class="btn btn-default">Login</button>
      </div>
    </div>

  </form:form>

  <form:form method="post" action="${pageContext.request.contextPath}/intr/0301/001/logout"
    class="form-horizontal">

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" id="logout" name="logout" class="btn btn-default">Logout</button>
      </div>
    </div>

  </form:form>
</div>
