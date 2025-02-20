<h2 id="bodyTitle">Update Result</h2>
<!-- prettier-ignore -->
<form:form modelAttribute="staffForm" action="${pageContext.request.contextPath}/tlly/staff/update">
    <fieldset>
        <legend>Staff Information</legend>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <label>First Name</label>
                </div>
                <div class="col-md-4">
                    <form:input path="firstName" class="form-control" readonly="true" style="background-color: whitesmoke" value="${f:h(staff.firstName)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>Last Name</label>
                </div>
                <div class="col-md-4">
                    <form:input path="lastName" class="form-control" readonly="true" style="background-color: whitesmoke" value="${f:h(staff.lastName)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>Authority</label>
                </div>
                <div class="col-md-4">
                    <form:select class="form-control" path="authority" disabled="true" style="background-color: whitesmoke">
                        <option value="STAFF_MNG" ${staff.authority.equals("STAFF_MNG") ? 'selected' : ''}>Staff
                        Management</option>
                        <option value="MASTER_MNG" ${staff.authority.equals("MASTER_MNG") ? 'selected' : ''}>Master
                        Management</option>
                        <option value="STOCK_MNG" ${staff.authority.equals("STOCK_MNG") ? 'selected' : ''}>Stock
                        Management</option>
                        <option value="ORDER_MNG" ${staff.authority.equals("ORDER_MNG") ? 'selected' : ''}>Order
                        Management</option>
                        <option value="SHOW_SHOPPING_MNG"
                        ${staff.authority.equals("SHOW_SHOPPING_MNG") ? 'selected' : ''}>Show
                        Shopping Management</option>
                    </form:select>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <form:button name="back" class="btn btn-default">Back</form:button>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
