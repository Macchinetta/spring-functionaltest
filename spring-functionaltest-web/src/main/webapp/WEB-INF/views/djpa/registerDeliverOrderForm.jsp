<form:form modelAttribute="deliveryOrderStatusForm"
  action="${pageContext.request.contextPath}/djpa/delivery/order/register">
  <fieldset>
    <legend>Deliver Order Details</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <label>Delivery ID.</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliverNumber" class="form-control"
            value="${f:h(delOrder.deliverNumber)}" readonly="true"
            style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Delivery Type</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryType" class="form-control" value="${f:h(delOrder.deliveryType)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Sender Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderName" class="form-control" value="${f:h(delOrder.senderName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Sender Address</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderAddress" class="form-control"
            value="${f:h(delOrder.senderAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Receiver Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="receiverName" class="form-control" value="${f:h(delOrder.receiverName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Receiver Address</label>
        </div>
        <div class="col-md-4">
          <form:input path="receiverAddress" class="form-control"
            value="${f:h(delOrder.receiverAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Acceptance Time</label>
        </div>
        <div class="col-md-4">
          <fmt:formatDate value="${delOrder.acceptDateTime}" pattern="yyyy/MM/dd" var="releaseDate" />
          <form:input path="acceptDateTime" class="form-control" value="${acceptDateTime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Completion Time</label>
        </div>
        <div class="col-md-4">
          <fmt:formatDate value="${delOrder.completionDateTime}" pattern="yyyy/MM/dd"
            var="releaseDate" />
          <form:input path="completionDateTime" class="form-control" value="${completionDateTime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Driver Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryDriver" class="form-control"
            value="${f:h(delOrder.deliveryDriver)}" />
        </div>
      </div>

      <div class="row">
        <div class="col-md-4">
          <label>Deliver Status</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryStatus" class="form-control"
            value="${f:h(delOrder.deliveryStatus)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
          <form:button name="add" class="btn btn-default">Add</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>
