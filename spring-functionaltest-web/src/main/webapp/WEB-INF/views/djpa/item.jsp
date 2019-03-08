<form:form modelAttribute="orderItemForm"
  action="${pageContext.request.contextPath}/djpa/order/item/modify" method="post">

  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th>OrderId</th>
        <td><form:input path="orderId" value="${f:h(orderId)}" readonly="true"
            style="background-color: whitesmoke;" /></td>
      </tr>
      <tr>
        <th>Item Number</th>
        <td><form:input path="itemNumber" value="${f:h(itemNumber)}" readonly="true"
            style="background-color: whitesmoke;" /></td>
      </tr>
      <tr>
        <th>Item Code</th>
        <td><form:input path="orderItem.itemCode" value="${f:h(orderItem.itemCode)}"
            readonly="true" style="background-color: whitesmoke;" /></td>
      </tr>
      <tr>
        <th>Item Name</th>
        <td><form:input path="orderItem.itemName" value="${f:h(orderItem.itemName)}"
            readonly="true" style="background-color: whitesmoke;" /></td>
      </tr>
      <tr>
        <th>Item Price</th>
        <td><form:input path="orderItem.itemPrice" value="${f:h(orderItem.itemPrice)}"
            readonly="true" style="background-color: whitesmoke;" /></td>
      </tr>
      <tr>
        <th>Quantity to purchase</th>
        <td><form:input path="quantity" value="${f:h(quantity)}" /></td>
      </tr>
      <tr>
        <th>Item Can be removed</th>
        <c:choose>
          <c:when test="${orderItemForm.logicalDelete}">
            <c:set var="canDelete" value="Yes"></c:set>
          </c:when>
          <c:otherwise>
            <c:set var="canDelete" value="No"></c:set>
          </c:otherwise>
        </c:choose>
        <td id="itStat">${f:h(canDelete)}</td>
      </tr>
    </thead>
  </table>
  <div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
      <form:button name="update" class="btn btn-default">Update</form:button>
      <form:button name="dirRelatedEntityUpdate" class="btn btn-default">Directly Update Related Entity</form:button>
      <form:button name="delete" class="btn btn-default">Delete Related Entity Using Parent</form:button>
      <form:button name="dirRelatedEntityDelete" class="btn btn-default">Direct Delete Related Entity</form:button>
      <form:button name="deleteNoSuccess" class="btn btn-default">Unsuccessful Deletion of Related Entity</form:button>
    </div>
  </div>
</form:form>
<a id="back" href="${pageContext.request.contextPath}/djpa/order">Back to Order List</a>
