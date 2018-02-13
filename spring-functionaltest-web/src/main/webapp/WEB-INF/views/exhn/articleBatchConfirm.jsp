<div id="wrapper">

  <h3 id="screenTitle">記事登録確認画面</h3>

  <form:form action="${pageContext.request.contextPath}/exhn/${f:h(testNumber)}/register"
    modelAttribute="articleBatchRegisterForm" method="POST" cssClass="form-horizontal"
    enctype="multipart/form-data">
    <fieldset>
      <legend>記事ファイル</legend>
      <div class="form-group">
        <form:label path="title" cssClass="col col-md-2 control-label">タイトル</form:label>
        <div class="col col-md-5">
          <span id="title">${f:h(articleBatchRegisterForm.title)}</span>
        </div>
      </div>
      <div class="form-group">
        <form:label path="multipartFile" cssClass="col col-md-2 control-label">ファイル名</form:label>
        <div class="col col-md-5">
          <span id="fileName">${f:h(articleBatchRegisterForm.fileName)}</span>
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2 control-label">一時ファイル名</div>
        <div class="col col-md-5">
          <span id="uploadTemporaryFileId">${f:h(articleSessionInfo.uploadTemporaryFileId)}</span>
        </div>
      </div>
    </fieldset>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:hidden path="fileName" />
        <form:hidden path="title" />
        <form:button id="uploadButton" name="upload" class="btn btn-default">アップロードを確定</form:button>
      </div>
    </div>
  </form:form>
</div>
