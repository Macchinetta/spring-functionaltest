<div id="wrapper">

  <h1 id="screenTitle">rememberMeのユーザ情報からユーザ名と作成したEmailを表示することを確認する。</h1>

  <table class="table table-bordered table-condensed">
    <tr>
      <th>User Name</th>
      <td><span id="username"> <c:if test="${!empty username}">
            <sec:authentication property="principal.username" />
          </c:if>
      </span></td>
    </tr>
    <tr>
      <th>User Email</th>
      <td><span id="userEmail"> <c:if test="${!empty userEmail}">
              ${f:h(userEmail)}
            </c:if>
      </span></td>
    </tr>
  </table>
  <form:form action="${pageContext.request.contextPath}/athn/2201/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
