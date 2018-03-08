
<div id="wrapper">
  <h3 id="businessError">一時ファイル削除画面</h3>

  <div>一時ファイル削除用フォーム</div>
  <form action="${pageContext.request.contextPath}/exhn/delete" method="POST"
    class="form-horizontal">
    <div class="form-group">
      <label for="temporaryFileName" class="col col-md-2 control-label">一時ファイル名</label>
      <div class="col col-md-5">
        <input type="text" class="form-control" id="temporaryFileName" name="temporaryFileName" />
      </div>
    </div>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <input type="submit" id="deleteButton" name="delete" class="btn btn-default" value="削除" />
      </div>
    </div>
    <input type="hidden" name="${f:h(_csrf.parameterName)}" value="${f:h(_csrf.token)}" />
  </form>
</div>