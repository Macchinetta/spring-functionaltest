<div id="wrapper">
  <sec:authorize url="/athr/0401/001/afterLogin/admin/menu" var="hasAdminAuthority" />
  <h1 id="screenTitle">カスタムJSPでROLE_ADMINを保持していたら文言が表示することを確認する。</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>Message</th>
        <td><span id="staffRole"> <sec:authorize access="hasRole('ADMIN')">
              This screen is for ROLE_ADMIN
            </sec:authorize>
        </span></td>
      </tr>
      <tr>
        <th>Only Role_Admin User link</th>
        <td><span id="staffRoleLink"> <sec:authorize
              url="/athr/0401/001/afterLogin/admin/menu">
              <a href="${pageContext.request.contextPath}/athr/">Go To ATHR Top Page</a>
            </sec:authorize>
        </span></td>
      </tr>
      <tr>
        <th>Only Role_Admin User link(variable)</th>
        <td><span id="staffRoleVarLink"> <c:if test="${hasAdminAuthority}">
              <a href="${pageContext.request.contextPath}/athr/">Go To ATHR Top Page(variable)</a>
            </c:if>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0201/001/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
