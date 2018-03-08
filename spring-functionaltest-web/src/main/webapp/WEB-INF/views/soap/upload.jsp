<div id="wrapper">

  <h1 id="screenTitle">file upload</h1>

  <t:messagesPanel />

  <form:form modelAttribute="uploadFileForm" method="post"
    action="${pageContext.request.contextPath}/soap/todo/${f:h(proxy)}/upload"
    cssClass="form-horizontal" enctype="multipart/form-data">

    <div class="form-group">
      <form:label path="multipartFile" cssClass="col col-md-2 control-label">contents file</form:label>
      <div class="col col-md-10">
        <form:input type="file" path="multipartFile" cssClass="multipartFile" />
        <form:errors path="multipartFile" cssClass="text-danger" element="div" />
      </div>
    </div>

    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:button id="upload" name="upload" class="btn btn-default">
          <span class="glyphicon glyphicon-upload"></span>upload</form:button>
      </div>
    </div>

  </form:form>

</div>
