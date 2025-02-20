<form:form modelAttribute="cartForm" action="${pageContext.request.contextPath}/djpa/order/${orderId}/addToOrder" method="post">
    <table class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>Sr.No.</th>
                <th>Item Code</th>
                <th>Item Name</th>
                <th>Item Price</th>
                <th>Quantity to purchase</th>
            </tr>
        </thead>
        <c:forEach items="${itemList}" var="item" varStatus="rowStatus">
            <tr>
                <td class="no" id="no">${rowStatus.count}</td>
                <td id="itemCode_${rowStatus.count}"><form:input path="items[${rowStatus.index}].itemCode" value="${f:h(item.itemCode)}" readonly="true" style="background-color: whitesmoke" /></td>

                <td id="itName_${status.count}"><form:input path="items[${rowStatus.index}].itemName" value="${f:h(item.itemName)}" readonly="true" style="background-color: whitesmoke" /></td>

                <td id="itPrice_${status.count}"><form:input path="items[${rowStatus.index}].itemPrice" value="${f:h(item.itemPrice)}" readonly="true" style="background-color: whitesmoke" /></td>

                <td id="itQty_${status.count}"><form:input path="items[${rowStatus.index}].quantity" value="" /></td>
            </tr>
        </c:forEach>
    </table>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:textarea path="memo" />
        </div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:button name="addtoOrder" class="btn btn-default">Place Order</form:button>
        </div>
    </div>
</form:form>
