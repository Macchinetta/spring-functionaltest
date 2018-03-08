<div id="wrapper">

  <h1 id="screenTitle">ファイルアップロード(フォーム)</h1>

  <t:messagesPanel />

  <form:form modelAttribute="screenFlowUploadForm" method="post" cssClass="form-horizontal"
    enctype="multipart/form-data">

    <div class="form-group">
      <form:label path="multipartFile" cssClass="col col-md-2 control-label">コンテンツファイル</form:label>
      <div class="col col-md-10">
        <form:input type="file" path="multipartFile" cssClass="multipartFile" />
        <input type="text" readonly="readonly" id="selectedFile" class="selectedFile form-control">
        <button id="browseFile" type="button" class="btn btn-default">
          <span class="glyphicon glyphicon-folder-open"></span>参照
        </button>
        <form:errors path="multipartFile" cssClass="text-danger" element="div" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="description" cssClass="col col-md-2 control-label">説明</form:label>
      <div class="col col-md-10">
        <form:textarea path="description" rows="3" class="description form-control" />
        <form:errors path="description" cssClass="text-danger" element="div" />
      </div>
    </div>

    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:button id="confirmButton" name="confirm" class="btn btn-default">
          <span class="glyphicon glyphicon-play"></span>確認</form:button>
        <a href="${pageContext.request.contextPath}/flup/" class="btn btn-default"> <span
          class="glyphicon glyphicon-arrow-left"></span>戻る
        </a>
      </div>
    </div>

  </form:form>

</div>
