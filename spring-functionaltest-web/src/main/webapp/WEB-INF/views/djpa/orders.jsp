<div class="row">
  <h1 id="screenTitle"></h1>


  <div>
    <table class="table table-striped table-bordered table-condensed">
      <thead>
        <form:form modelAttribute="orderForm" action="${pageContext.request.contextPath}/djpa/order"
          method="get">
          <tr>
            <th align="left"><form:input path="orderId" class="form-control form-input"
                value="" /></th>
            <th><form:button name="fetch" class="btn btn-default btn-right">Search By Order ID</form:button>
              <form:button name="cmnCondFetch" class="btn btn-default btn-right">Search Using Entity with Common Condition</form:button></th>
          </tr>
          <tr>
            <th align="left"><form:input path="orderStatus.statusName"
                class="form-control form-input" value="" /></th>
            <th><form:button name="searchByStatus" class="btn btn-default btn-right">Search By Order Status</form:button></th>
          </tr>
          <tr>
            <th colspan="2"><form:button name="create" class="btn btn-default btn-right">Place Order</form:button></th>
          </tr>
          <tr>
            <th colspan="2"><form:button name="fetchSummary" class="btn btn-default btn-right">Fetch Result in Custom Object</form:button></th>
          </tr>
        </form:form>
        <form:form modelAttribute="orderForm"
          action="${pageContext.request.contextPath}/djpa/order/delete" method="delete">
          <tr>
            <th align="left" style="width: 150px"><form:input id="deleteId" path="orderId"
                class="form-control form-input" value="" /></th>
            <th colspan="4"><form:button name="delete" class="btn btn-default btn-right">Delete By Order ID</form:button></th>
          </tr>
        </form:form>
      </thead>

    </table>

  </div>
  <c:if test="${null ne orderList && orderList.size() >0}">
    <table class="table table-striped table-bordered table-condensed">
      <thead>
        <tr>
          <th>Sr.No.</th>
          <th>Order Id</th>
          <th>Order Memo</th>
          <th>Order Amount</th>
          <th>Order Status</th>
          <th>Can Be Deleted ?</th>
        </tr>
      </thead>
      **${cmnCodnSrchFlag }**
      <c:forEach items="${orderList}" var="order" varStatus="rowStatus">
        <tr>
          <td class="no" id="no">${rowStatus.count}</td>
          <c:choose>
            <c:when test="${cmnCodnSrchFlag }">
              <td id="orderId_${rowStatus.count}"><a id="id${f:h(rowStatus.count)}"
                href="${pageContext.request.contextPath}/djpa/order/cmnCondFetch/${f:h(order.orderId)}/detail">${f:h(order.orderId)}</a></td>
            </c:when>
            <c:otherwise>
              <td id="orderId_${rowStatus.count}"><a id="id${f:h(rowStatus.count)}"
                href="${pageContext.request.contextPath}/djpa/order/${f:h(order.orderId)}/detail">${f:h(order.orderId)}</a></td>
            </c:otherwise>

          </c:choose>

          <td id="memo_${rowStatus.count}">${f:h(order.orderMemo)}</td>
          <td id="amount_${rowStatus.count}">${f:h(order.orderAmount)}</td>
          <td id="status_${rowStatus.count}">${f:h(order.orderStatus.statusName)}</td>

          <c:choose>
            <c:when test="${order.logicalDelete}">
              <c:set var="canDelete" value="Yes"></c:set>
            </c:when>
            <c:otherwise>
              <c:set var="canDelete" value="No"></c:set>
            </c:otherwise>
          </c:choose>
          <td id="logicalDel_${rowStatus.count}">${f:h(canDelete)}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</div>
