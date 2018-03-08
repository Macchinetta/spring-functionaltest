<form:form modelAttribute="deliveryOrderForm"
  action="${pageContext.request.contextPath}/dmly/deliveryorder/${deliveryOrder.deliveryNo}/update">
  <fieldset>
    <legend>配達オーダ更新</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-2">
          <label>配達No</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryNo" class="form-control"
            value="${f:h(deliveryOrder.deliveryNo)}" readonly="true"
            style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>配達種別</label>
        </div>
        <div class="col-md-4">
          <form:select class="form-control" path="deliveryTypeName">
            <c:forEach var="deliveryType" items="${deliveryTypeList}" varStatus="rowStatus">
              <option value="${f:h(deliveryType.deliveryTypeName)}"
                ${deliveryType.deliveryTypeName == deliveryOrder.deliveryType.deliveryTypeName ? 'selected' : ''}>${f:h(deliveryType.deliveryTypeName)}</option>
            </c:forEach>
          </form:select>
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り主名</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderName" class="form-control"
            value="${f:h(deliveryOrder.senderName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り主住所</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderAddress" class="form-control"
            value="${f:h(deliveryOrder.senderAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り先名</label>
        </div>
        <div class="col-md-4">
          <form:input path="recieverName" class="form-control"
            value="${f:h(deliveryOrder.recieverName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り先住所</label>
        </div>
        <div class="col-md-4">
          <form:input path="recieverAddress" class="form-control"
            value="${f:h(deliveryOrder.recieverAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>受付日時</label>
        </div>
        <div class="col-md-4">
          <fmt:formatDate value="${deliveryOrder.acceptDatetime}" pattern="yyyy/MM/dd HH:mm:ss"
            var="acceptDatetime" />
          <form:input path="acceptDatetime" class="form-control" value="${acceptDatetime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>完了日時</label>
        </div>
        <div class="col-md-4">
          <fmt:formatDate value="${deliveryOrder.completionDatetime}" pattern="yyyy/MM/dd HH:mm:ss"
            var="completionDatetime" />
          <form:input path="completionDatetime" class="form-control" value="${completionDatetime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>配達者</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryDriver" class="form-control"
            value="${f:h(deliveryOrder.deliveryDriver)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>状態</label>
        </div>
        <div class="col-md-4">
          <form:select class="form-control" path="deliveryStatus">
            <c:forEach var="deliveryStatus" items="${deliveryStatusList}" varStatus="rowStatus">
              <option value="${f:h(deliveryStatus.deliveryStatus)}"
                ${deliveryStatus.deliveryStatus == deliveryOrder.deliveryStatus ? 'selected' : ''}>${f:h(deliveryStatus.deliveryStatus)}</option>
            </c:forEach>
          </form:select>
        </div>
      </div>
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-4">
          <form:button name="update" class="btn btn-default">更新</form:button>
          <form:button name="delete" class="btn btn-default">削除</form:button>
          <form:button name="cancel" class="btn btn-default">キャンセル</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>
