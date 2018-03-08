<div id="wrapper">

  <h1 id="screenTitle">Staff Login Page(For ROLE_ADMIN JSP Tag Display)</h1>

  <fieldset>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>Only Role_Admin User link</th>
        <td><span id="staffRole"> <sec:authorize
              url="/athr/0201/002/afterLogin/admin/menu">
              <a href="${pageContext.request.contextPath}/athr/">Go To ATHR Top Page</a>
            </sec:authorize>
        </span></td>
      </tr>
    </table>
  </fieldset>
  <br>
  <form:form action="${pageContext.request.contextPath}/athr/0201/002/logout" method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
