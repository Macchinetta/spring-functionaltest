<div id="wrapper">
  <h3 id="screenTitle">社員情報編集完了画面</h3>

  <form:form action="${pageContext.request.contextPath}/exhn/${f:h(testNumber)}"
    cssClass="form-horizontal" method="get" modelAttribute="employeeForm">
    <fieldset>
      <legend>社員情報</legend>
      <div class="form-group">
        <div class="col col-md-2">
          <label>社員ID</label>
        </div>
        <div class="col col-md-4">${f:h(employeeForm.employeeId)}</div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label>名前</label>
        </div>
        <div class="col col-md-4">${f:h(employeeForm.employeeName)}</div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label>Email</label>
        </div>
        <div class="col col-md-4">${f:h(employeeForm.email)}</div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label>住所</label>
        </div>
        <div class="col col-md-4">${f:h(employeeForm.address)}</div>
      </div>
      <div class="form-group col col-md-5">
        <form:button id="backToIndex" class="btn btn-default">戻る</form:button>
      </div>
    </fieldset>

  </form:form>

</div>