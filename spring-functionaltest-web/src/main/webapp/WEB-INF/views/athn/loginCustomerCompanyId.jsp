<div id="wrapper">

  <h1 id="screenTitle">Customer CompanyId Login Page</h1>

  <form:form action="${pageContext.request.contextPath}/athn/1702/customerAuthenticate"
    method="post">
    <c:if test="${param.containsKey('error')}">
      <span id="customerCompanyIdLoginError"> <t:messagesPanel
          messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" panelClassName="alert alert-danger" />
      </span>
    </c:if>
    <fieldset>
      <legend>Login</legend>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="userName">UserName</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="username" name="username">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="companyId">Company ID</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="companyId" name="companyId">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="password">Password</label>
        </div>
        <div class="col col-md-3">
          <input type="password" class="form-control input-sm" id="password" name="password">
        </div>
      </div>
      <br> <br> <input type="submit" id="companyIdLogin" class="btn btn-default"
        name="LoginSuccess" value="Login">
    </fieldset>
  </form:form>
  <br>
  <form:form action="${pageContext.request.contextPath}/athn/1702/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
