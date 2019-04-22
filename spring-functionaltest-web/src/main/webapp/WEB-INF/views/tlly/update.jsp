<h2 id="bodyTitle">Update Staff Information</h2>
<form:form modelAttribute="staffForm" action="${pageContext.request.contextPath}/tlly/staff/update">
  <fieldset>
    <legend>Staff Information</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-2">
          <label>First Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="firstName" class="form-control" value="${f:h(staff.firstName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>Last Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="lastName" class="form-control" value="${f:h(staff.firstName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>Authority</label>
        </div>
        <div class="col-md-4">
          <form:select class="form-control" path="authority">
            <option value="STAFF_MNG" selected>Staff Management</option>
            <option value="MASTER_MNG">Master Management</option>
            <option value="STOCK_MNG">Stock Management</option>
            <option value="ORDER_MNG">Order Management</option>
            <option value="SHOW_SHOPPING_MNG">Show Shopping Management</option>
          </form:select>
        </div>
      </div>
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-4">
          <form:button name="update" class="btn btn-default">Update</form:button>
          <form:button name="cancel" class="btn btn-default">Cancel</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>
