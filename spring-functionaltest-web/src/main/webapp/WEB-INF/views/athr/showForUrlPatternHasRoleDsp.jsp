<div id="wrapper">

  <h1 id="screenTitle">ROLE_ADMINのユーザのみが表示できる画面であることを確認する。</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>UserName</th>
        <td><span id="username"> <c:if test="${!empty username}">
              <sec:authentication property="principal.username" />
            </c:if>
        </span></td>
      </tr>
      <tr>
        <th>UserEmail</th>
        <td><span id="userEmail"> <c:if test="${!empty userEmail}">
              ${f:h(userEmail)}
            </c:if>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0101/003/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
