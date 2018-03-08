<div id="wrapper">

  <h1 id="screenTitle">update todo</h1>
  <br>
  <t:messagesPanel />
  <form:form action="${pageContext.request.contextPath}/soap/todo/${f:h(proxy)}/update"
    cssClass="form-horizontal" method="post" modelAttribute="todoForm">

    <div class="form-group">
      <form:label path="todoId" class="col col-md-2 control-label">todoId</form:label>
      <div class="col col-md-3">
        <form:input path="todoId" cssClass="form-control input-sm" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="title" class="col col-md-2 control-label">title</form:label>
      <div class="col col-md-3">
        <form:input path="title" cssClass="form-control input-sm" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="description" class="col col-md-2 control-label">description</form:label>
      <div class="col col-md-3">
        <form:input path="description" cssClass="form-control input-sm" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="finished" class="col col-md-2 control-label">finished</form:label>
      <div class="col col-md-3">
        <form:checkbox path="finished" cssClass="form-control input-sm" />
      </div>
    </div>

    <div class="form-group">
      <form:button id="update" class="btn btn-default">update</form:button>
    </div>

  </form:form>

</div>
