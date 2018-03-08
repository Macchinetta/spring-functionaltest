<div id="wrapper">

  <h1 id="screenTitle">個別指定したAuthenticationEntryPointが正常に機能することを確認する（リソース別に異なるAuthenticationEntryPointを使用する）。</h1>

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
  <form:form action="${pageContext.request.contextPath}/athr/0801/001/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
