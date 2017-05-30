<div id="wrapper">

  <h1 id="screenTitle">ログイン情報に紐付く付属情報を取得</h1>

    <sec:authentication property="principal" var="customer"/>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>Customer Name</th>
        <td>
          <span id="customerName">
            ${f:h(customer.customerName)}
          </span>
        </td>
      </tr>
      <tr>
        <th>Customer Address</th>
        <td>
          <span id="customerAddress">
            ${f:h(customer.customerAddress)}
          </span>
        </td>
      </tr>
      <tr>
        <th>Company Id</th>
        <td>
          <span id="companyId">
            ${f:h(customer.companyId)}
          </span>
        </td>
      </tr>
    </table>
  <form:form action="${pageContext.request.contextPath}/athn/1702/logout"
    method="post">
    <input type="submit" id="logout" class="btn btn-default" value="Logout">
  </form:form>

</div>
