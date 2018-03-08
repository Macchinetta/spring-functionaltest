<div id="wrapper">

  <h1 id="screenTitle">Excelファイルのダウンロード</h1>

  <form:form method="POST" action="${pageContext.request.contextPath}/fldw/0201/001"
    modelAttribute="contentDownloadForm" cssClass="form-horizontal">
    <fieldset>
      <legend>新規口座開設用情報入力</legend>
      <div class="form-group">
        <div class="col col-md-2">名前</div>
        <div class="col col-md-3">
          <form:input path="name" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">住所</div>
        <div class="col col-md-3">
          <form:input path="address" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">生年月日(yyyyMMdd)</div>
        <div class="col col-md-3">
          <form:input path="birthdate" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-10 col-md-offset-2">
          <input type="submit" id="downloadButton" name="excel" value="ダウンロード"
            class="btn btn-default">
        </div>
      </div>
    </fieldset>
  </form:form>
</div>
