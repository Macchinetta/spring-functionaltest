<div id="wrapper">
  <h1 id="screenTitle">グループ化した単項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0103/001"
    modelAttribute="groupedValidationForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="userName" class="col-sm-2 control-label">UserName</form:label>
      <div class="col-sm-10">
        <form:input path="userName" class="form-control" placeholder="UserName" />
        <form:errors path="userName" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="email" class="col-sm-2 control-label">Email</form:label>
      <div class="col-sm-10">
        <form:input path="email" class="form-control" placeholder="Email" />
        <form:errors path="email" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="age" class="col-sm-2 control-label">Age</form:label>
      <div class="col-sm-10">
        <form:input path="age" class="form-control" placeholder="Age" />
        <form:errors path="age" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="country" class="col-sm-2 control-label">Country</form:label>
      <div class="col-sm-10">
        <form:select path="country" class="form-control">
          <form:option value="jp">Japan</form:option>
          <form:option value="sg">Singapore</form:option>
        </form:select>
        <form:errors path="country" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
      </div>
    </div>

  </form:form>

</div>
