<div id="wrapper">

  <h1 id="screenTitle">ユーザ情報詳細</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>UserName</th>
        <td><span id="username"> <c:if test="${!empty username}">
              ${f:h(username)}
            </c:if>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0601/019/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
