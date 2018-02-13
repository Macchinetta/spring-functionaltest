<div id="wrapper">
  <h1 id="screenTitle">PDFファイルのダウンロード</h1>
  <form:form method="POST" action="${pageContext.request.contextPath}/dbsp/0301/005"
    modelAttribute="downloadForm" cssClass="form-horizontal">
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
          <input type="submit" id="downloadButton" name="file" value="ダウンロード"
            class="btn btn-default">
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-10 col-md-offset-2">
          <input type="submit" id="fourth" name="fourth" value="第四画面へ"
            class="btn btn-default">
        </div>
      </div>
    </fieldset>
  </form:form>
</div>
