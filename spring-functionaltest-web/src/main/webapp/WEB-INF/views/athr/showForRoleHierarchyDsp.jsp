<div id="wrapper">

  <h1 id="screenTitle">権限を階層で保持しているユーザの表示を確認する。</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>Message</th>
        <td>
          <span id="roleHierarchy">
             This feature is for ROLE_ADMIN or ROLE_STAFF
        </span>
        </td>
      </tr>
    </table>
  </fieldset>
  <form:form
    action="${pageContext.request.contextPath}/athr/0901/001/logout"
    method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
