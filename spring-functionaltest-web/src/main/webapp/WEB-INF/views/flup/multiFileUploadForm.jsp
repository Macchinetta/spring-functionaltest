<div id="wrapper">

  <h1 id="screenTitle">複数ファイルのアップロードフォーム</h1>

  <t:messagesPanel />

  <form:form modelAttribute="multiUploadForm" method="post" cssClass="form-horizontal"
    enctype="multipart/form-data">

    <c:forEach var="i" begin="0" end="1" step="1">
      <div class="form-group">
        <form:label path="uploadForms[${i}].multipartFile" cssClass="col col-md-2 control-label">コンテンツファイル1</form:label>
        <div class="col col-md-10">
          <form:input type="file" path="uploadForms[${i}].multipartFile" cssClass="multipartFile" />
          <input type="text" readonly="readonly" id="selectedFiles${i}"
            class="selectedFile form-control">
          <button id="browseFiles${i}" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-folder-open"></span>参照
          </button>
          <form:errors path="uploadForms[${i}].multipartFile" cssClass="text-danger" element="div" />
        </div>
      </div>
    </c:forEach>

    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:button id="uploadButton" name="upload" class="btn btn-default">
          <span class="glyphicon glyphicon-upload"></span>アップロード</form:button>
        <a href="${pageContext.request.contextPath}/flup/" class="btn btn-default"> <span
          class="glyphicon glyphicon-arrow-left"></span>戻る
        </a>
      </div>
    </div>

  </form:form>

</div>
