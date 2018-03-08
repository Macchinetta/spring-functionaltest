<div id="wrapper">
  <h1 id="screenTitle">単項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0101/001"
    modelAttribute="simpleValidationByJSR303Form" class="form-horizontal">

    <div class="form-group">
      <form:label path="userName" class="col-sm-2 control-label">UserName</form:label>
      <div class="col-sm-10">
        <form:input path="userName" class="form-control" placeholder="UserName" />
        <form:errors path="userName" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
      </div>
    </div>

  </form:form>

</div>
