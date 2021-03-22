<div id="wrapper">

  <h1 id="screenTitle">アクセスポリシ定義(IPアドレス否許可)が正常に機能していることを確認する。</h1>

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
        <th>Only Manager User link</th>
        <td><span> <a id="managerLink"
            href="${pageContext.request.contextPath}/athr/0601/003/manager">Go To Account Page
              Top Page</a>
        </span></td>
      </tr>
      <tr>
        <th>Only Configuration Manager(LocalHost) link</th>
        <td><span> <a id="configLink"
            href="${pageContext.request.contextPath}/athr/0601/003/configurations">Go To
              Configuration Manager Page</a>
        </span></td>
      </tr>
      <tr>
        <th>Only Administrator link</th>
        <td><span> <a id="adminLink"
            href="${pageContext.request.contextPath}/athr/0601/003/admin/">Go To Manager Page</a>
        </span></td>

      </tr>
      <tr>
        <th>Authenticated User link</th>
        <td><span> <a id="allLink"
            href="${pageContext.request.contextPath}/athr/0601/003/all">Go To Authenticated User
              Page</a>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0601/003/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
