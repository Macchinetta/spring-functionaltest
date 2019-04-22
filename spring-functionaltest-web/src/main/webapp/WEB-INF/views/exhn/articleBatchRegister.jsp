<div id="wrapper">

  <h3 id="screenTitle">記事登録画面</h3>

  <div id="count">登録件数:${f:h(count)}</div>
  <br>
  <form:form action="${pageContext.request.contextPath}/exhn/${f:h(testNumber)}/confirm"
    modelAttribute="articleBatchRegisterForm" method="POST" cssClass="form-horizontal"
    enctype="multipart/form-data">
    <fieldset>
      <legend>記事ファイル</legend>
      <div class="form-group">
        <form:label path="title" cssClass="col col-md-2 control-label">タイトル</form:label>
        <div class="col col-md-5">
          <form:input type="text" cssClass="form-control" path="title" />
          <form:errors id="titleError" cssClass="text-danger" path="title" />
        </div>
      </div>
      <div class="form-group">
        <form:label path="multipartFile" cssClass="col col-md-2 control-label">ファイル</form:label>
        <div class="col col-md-5">
          <form:input type="file" path="multipartFile" />
          <form:errors id="multipartFileError" cssClass="text-danger" path="multipartFile" />
        </div>
      </div>
    </fieldset>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:button id="uploadButton" name="upload" class="btn btn-default">アップロード</form:button>
      </div>
    </div>
  </form:form>
</div>
