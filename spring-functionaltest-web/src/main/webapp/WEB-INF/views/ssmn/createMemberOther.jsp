<div id="wrapper">
  <h1 id="screenTitle">会員情報登録 その他</h1>
  <br>
  <form:form cssClass="form-horizontal" method="post" modelAttribute="memberForm">
    <fieldset>
      <legend>その他情報</legend>
      <div class="form-group">
        <div class="col col-md-2">職業</div>
        <div class="col col-md-3">
          <form:input path="occupation" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="occupation" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">メールアドレス</div>
        <div class="col col-md-3">
          <form:input path="mailAddress" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="mailAddress" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <form:button name="redoAddress" class="btn btn-default">戻る</form:button>
        <form:button name="confirm" class="btn btn-default">確認</form:button>
      </div>
    </fieldset>
  </form:form>
</div>