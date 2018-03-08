<div class="row">
  <h1 id="screenTitle"></h1>
  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <td align="left">Order Id</td>
        <td colspan="6" align="left" id="orderSumId">${orderForm.orderId }</td>
      </tr>
      <tr>
        <td align="left">Order Amount</td>
        <td colspan="6" align="left" id="orderSumAmt">${orderForm.orderAmount }</td>
      </tr>
      <tr>
        <td align="left">Comment</td>
        <td colspan="6" align="left" id="orderSumMemo">${orderForm.orderMemo }</td>
      </tr>
      <tr>
        <td align="left">Status</td>
        <td colspan="6" align="left" id="orderSumStatus">${orderForm.orderStatus.statusName }</td>
      </tr>
      <tr>
        <td align="left">Can be Deleted?</td>
        <c:choose>
          <c:when test="${orderForm.logicalDelete}">
            <c:set var="canDelete" value="Yes"></c:set>
          </c:when>
          <c:otherwise>
            <c:set var="canDelete" value="No"></c:set>
          </c:otherwise>
        </c:choose>
        <td colspan="6" align="left" id="orderSumDelFlag">${canDelete }</td>
      </tr>
    </thead>
    <thead>
      <tr>
        <th>Sr.No.</th>
        <th>Item Number</th>
        <th>Item Code</th>
        <th>Item Name</th>
        <th>Item Price</th>
        <th>Quantity</th>
        <th>Marked For Removal</th>
      </tr>
    </thead>

    <c:forEach items="${orderForm.orderItem}" var="order" varStatus="rowStatus">
      <tr>
        <td class="no" id="no">${rowStatus.count}</td>
        <td id="itNum_${rowStatus.count}"><a id="item${f:h(rowStatus.count)}"
          href="${pageContext.request.contextPath}/djpa/order/${orderForm.orderId }/item/${f:h(order.itemNumber)}/detail">${f:h(order.itemNumber)}</a></td>
        <td id="itCode_${rowStatus.count}">${f:h(order.orderItem.itemCode)}</td>
        <td id="itName_${rowStatus.count}">${f:h(order.orderItem.itemName)}</td>
        <td id="itPr_${rowStatus.count}">${f:h(order.orderItem.itemPrice)}</td>
        <td id="itQty_${rowStatus.count}">${f:h(order.quantity)}</td>
        <td id="itStat_${rowStatus.count}"><c:choose>
            <c:when test="${order.logicalDelete}">Yes</c:when>
            <c:otherwise>No</c:otherwise>
          </c:choose></td>
      </tr>
    </c:forEach>


  </table>
  <table class="table table-striped table-bordered table-condensed">
    <form:form modelAttribute="orderForm"
      action="${pageContext.request.contextPath}/djpa/order/${orderForm.orderId }/addUpdt"
      method="get">
      <thead>
        <tr>
          <th><form:button name="add" class="btn btn-default btn-right">Add/Update Item</form:button>
          </th>
        </tr>
      </thead>
    </form:form>
  </table>
  <table class="table table-striped table-bordered table-condensed">
    <form:form modelAttribute="orderForm"
      action="${pageContext.request.contextPath}/djpa/order/${orderForm.orderId }/update"
      method="post">
      <thead>
        <tr>
          <th><form:input path="orderStatus.statusName" value="" /> <form:button
              name="updateStatus" class="btn btn-default btn-right">Update Order Status</form:button></th>
        </tr>
        <tr></tr>
        <tr>
          <th><form:button name="updateByQuery" class="btn btn-default btn-right">Update Using Query method(Default clearAutomatically)</form:button></th>
        </tr>
        <tr>
          <th><form:button name="updateByQueryClear" class="btn btn-default btn-right">Update Using Query method(clearAutomatically = true)</form:button></th>
        </tr>
        <tr>
          <th><form:button name="updateByQueryErr" class="btn btn-default btn-right">Update Using Query method(Error)</form:button></th>
        </tr>
        <tr>
          <th><form:button name="deleteEntityByQuery" class="btn btn-default btn-right">Delete All Items Using Query Method</form:button></th>
        </tr>

      </thead>
    </form:form>
  </table>
  <a id="back" href="${pageContext.request.contextPath}/djpa/order">Back to Order List</a>
</div>
