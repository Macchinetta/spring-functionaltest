<div id="wrapper">

  <h1 id="screenTitle">ログインページ</h1>

  <form:form action="${pageContext.request.contextPath}/dtac/authenticate" method="post">
    <c:if test="${param.containsKey('error')}">
      <span id="accountLoginError"> <t:messagesPanel
          messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
      </span>
    </c:if>
    <fieldset>
      <legend>ログイン</legend>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="userName">ユーザ名</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="username" name="username">
        </div>
      </div>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="password">パスワード</label>
        </div>
        <div class="col col-md-3">
          <input type="password" class="form-control input-sm" id="password" name="password">
        </div>
      </div>
      <br> <br> <input type="submit" id="login" class="btn btn-default"
        value="ログイン">
    </fieldset>
  </form:form>
  <br>
  <form:form action="${pageContext.request.contextPath}/dtac/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="ログアウト">
  </form:form>

</div>
