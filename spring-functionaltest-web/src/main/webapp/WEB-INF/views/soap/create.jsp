<div id="wrapper">

  <h1 id="screenTitle">create todo</h1>
  <br>
  <t:messagesPanel />
  <form:form action="${pageContext.request.contextPath}/soap/todo/${f:h(proxy)}/create"
    cssClass="form-horizontal" method="post" modelAttribute="todoForm">

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
      <form:button id="create" class="btn btn-default">create</form:button>
    </div>

  </form:form>

</div>
