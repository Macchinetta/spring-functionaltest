<div id="wrapper">

  <h3 id="screenTitle">顧客一括登録画面</h3>

  <form:form action="${pageContext.request.contextPath}/cspr/register"
    modelAttribute="customerBatchRegisterForm" method="POST" cssClass="form-horizontal"
    enctype="multipart/form-data">
    <div class="form-group">
      <form:label path="multipartFile" cssClass="col col-md-2 control-label">一括登録ファイル</form:label>
      <div class="col col-md-10">
        <form:input type="file" path="multipartFile" />
        <span id="validationErrorMessage"><form:errors cssClass="errorMessage"
            path="multipartFile" /></span>
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:button id="uploadButton" name="upload" class="btn btn-default">アップロード</form:button>
      </div>
    </div>
  </form:form>
</div>
