<div id="wrapper">

  <h1 id="screenTitle">SCryptPasswordEncoderを使用して認証</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>Administrator Name</th>
        <td><span id="username"> <c:if test="${!empty username}">
              <sec:authentication property="principal.username" />
            </c:if>
        </span></td>
      </tr>
      <tr>
        <th>Password</th>
        <td><span id="password"> <c:if test="${!empty username}">
              ${f:h(administratorPassword)}
            </c:if>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athn/0501/006/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
