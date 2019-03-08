<div id="wrapper">
  <h1 id="screenTitle">Beanのnull・空フィールド除外設定</h1>
  <br>
  <form:form action="${pageContext.request.contextPath}/bnmp/excludeNullEmptyMapping"
    cssClass="form-horizontal" method="post" modelAttribute="excludeNullEmptyForm">
    <legend>null・空フィールド除外設定方法確認</legend>
    <div class="form-group">
      <div class="col col-md-2">First Name</div>
      <div class="col col-md-3">
        <form:input path="firstName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">Last Name</div>
      <div class="col col-md-3">
        <form:input path="lastName" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">age</div>
      <div class="col col-md-3">
        <form:input path="age" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-2">birthDate(yyyyMMdd)</div>
      <div class="col col-md-3">
        <form:input path="birthDate" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="excludeNullEmpty" class="btn btn-default">Null、空フィールド除外設定</form:button>
    </div>
  </form:form>
</div>