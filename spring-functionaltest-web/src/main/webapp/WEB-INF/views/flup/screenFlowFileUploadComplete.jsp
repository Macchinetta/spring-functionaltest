<div id="wrapper">

  <h1 id="screenTitle">ファイルアップロード(完了)</h1>

  <t:messagesPanel />

  <div class="form-horizontal">

    <c:if test="${not empty uploadedContent}">

      <div class="form-group">
        <label class="col col-md-2 control-label">ファイルID</label>
        <div class="col col-md-10">
          <span id="fileIdText" class="display-item form-control">${f:h(uploadedContent.fileId)}</span>
        </div>
      </div>

      <div class="form-group">
        <label class="col col-md-2 control-label">ファイル名</label>
        <div class="col col-md-10">
          <span id="fileNameText" class="display-item form-control">${f:h(uploadedContent.fileName)}</span>
        </div>
      </div>

      <div class="form-group">
        <label class="col col-md-2 control-label">データ</label>
        <div class="col col-md-10">
          <span id="fileContentText" class="display-item form-control">${f:h(uploadedContent.fileContent)}</span>
        </div>
      </div>

      <div class="form-group">
        <label class="col col-md-2 control-label">説明</label>
        <div class="col col-md-10">
          <span id="descriptionText" class="display-item form-control">${f:br(f:h(uploadedContent.description))}</span>
        </div>
      </div>

      <div class="form-group">
        <div class="col col-md-10 col-md-offset-2">
          <form action="${pageContext.request.contextPath}/flup/files" method="get">
            <input type="hidden" name="fileId" value="${f:h(uploadedContent.fileId)}" />
            <button id="downloadButton" name="download" class="btn btn-default">
              <span class="glyphicon glyphicon-download"></span>ダウンロード
            </button>
          </form>
        </div>
      </div>

    </c:if>

  </div>

  <div class="text-center">
    <a href="${pageContext.request.contextPath}/flup/" class="btn btn-default"><span
      class="glyphicon glyphicon-arrow-left"></span>ファイルアップロードのトップへ</a>
  </div>

</div>
