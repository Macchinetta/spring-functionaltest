<div id="wrapper">
  <h1 id="screenTitle">悲観的排他ロック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/excn/0402/002"
    modelAttribute="stockForm" cssClass="form-horizontal">

    <div class="form-group">
      <table class="table table-striped">

        <thead>
          <tr>
            <th class="itemCode">itemCode</th>
            <th class="itemName">itemName</th>
            <th class="quantity">quantity</th>
            <th class="version">version</th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td id="result_itemCode" class="itemCode">${f:h(stockForm.itemCode)}</td>
            <td id="result_itemName" class="itemName">${f:h(stockForm.itemName)}</td>
            <td id="result_quantity" class="quantity">${f:h(stockForm.quantity)}</td>
            <td id="result_version" class="version">${f:h(stockForm.version)}</td>
          </tr>
        </tbody>

      </table>
    </div>

    <div class="form-group">
      <form:label path="purchasingQuantity" class="col-sm-2 control-label">PurchasingQuantity</form:label>
      <div class="col-sm-10">
        <form:input path="purchasingQuantity" class="form-control" placeholder="PurchasingQuantity" />
        <form:errors path="purchasingQuantity" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:hidden path="itemCode" class="btn btn-default" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:hidden path="version" class="btn btn-default" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="buy" name="buy" class="btn btn-default">buy</form:button>
      </div>
    </div>

  </form:form>

</div>
