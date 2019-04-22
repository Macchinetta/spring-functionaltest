<div id="wrapper">

  <h1 id="screenTitle">権限を階層で保持しているユーザの情報をJSPTagでの表示制御を確認する。</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>Message</th>
        <td><span id="roleHierarchy"> <sec:authorize access="hasRole('STAFF')">
              <p>This feature is for ROLE_ADMIN or ROLE_STAFF</p>
            </sec:authorize>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <form:form action="${pageContext.request.contextPath}/athr/0901/002/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
