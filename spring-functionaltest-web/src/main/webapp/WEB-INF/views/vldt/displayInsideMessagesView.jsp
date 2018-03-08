<div id="wrapper">
  <h1 id="screenTitle">単項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0101/003"
    modelAttribute="displayInsideMessagesForm" class="form-horizontal">

    <div class="form-group">
      <div class="col-sm-10">
        <form:errors path="*" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="userName" class="col-sm-2 control-label">UserName</form:label>
      <div class="col-sm-10">
        <form:input path="userName" class="form-control" placeholder="UserName" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="email" class="col-sm-2 control-label">Email</form:label>
      <div class="col-sm-10">
        <form:input path="email" class="form-control" placeholder="Email" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="age" class="col-sm-2 control-label">Age</form:label>
      <div class="col-sm-10">
        <form:input path="age" class="form-control" placeholder="Age" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
      </div>
    </div>

  </form:form>

</div>
