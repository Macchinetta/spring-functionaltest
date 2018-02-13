<div id="wrapper">

  <h1 id="screenTitle">ファイルアップロード(複数ファイル)</h1>

  <t:messagesPanel />

  <div class="form-horizontal">

    <c:forEach items="${uploadedContents}" var="uploadedContent" varStatus="rowStatus">

      <div class="panel panel-default">

        <div class="panel-heading">
          <span>${f:h(rowStatus.count)}件目のアップロードファイル</span>
        </div>

        <div class="panel-body">

          <div class="form-group">
            <label class="col col-md-2 control-label">ファイル名</label>
            <div class="col col-md-10">
              <span id="fileNameText${rowStatus.index}" class="display-item form-control">${f:h(uploadedContent.fileName)}</span>
            </div>
          </div>

          <div class="form-group">
            <label class="col col-md-2 control-label">データ</label>
            <div class="col col-md-10">
              <span id="fileContentText${rowStatus.index}" class="display-item form-control">${f:h(uploadedContent.fileContent)}</span>
            </div>
          </div>

        </div>

      </div>

    </c:forEach>

  </div>

  <div class="text-center">
    <a href="${pageContext.request.contextPath}/flup/" class="btn btn-default"><span
      class="glyphicon glyphicon-arrow-left"></span>ファイルアップロードのトップへ</a>
  </div>

</div>
