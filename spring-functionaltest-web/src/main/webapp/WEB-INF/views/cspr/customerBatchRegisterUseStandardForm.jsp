<div id="wrapper">

  <h3 id="screenTitle">顧客一括登録画面</h3>

  <form id="customerBatchRegisterForm" class="form-horizontal"
    action="${pageContext.request.contextPath}/cspr/registerWithTokenFromQuery?${f:h(_csrf.parameterName)}=${f:h(_csrf.token)}"
    method="POST" enctype="multipart/form-data">
    <div class="form-group">
      <label for="multipartFile" class="col col-md-2 control-label">一括登録ファイル</label>
      <div class="col col-md-10">
        <input id="multipartFile" name="multipartFile" type="file" value="" />
        <spring:nestedPath path="customerBatchRegisterForm">
          <form:errors path="multipartFile" cssClass="errorMessage" />
        </spring:nestedPath>
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <button id="uploadButton" name="upload" class="btn btn-default" type="submit" value="Submit">アップロード</button>
      </div>
    </div>
  </form>
</div>
