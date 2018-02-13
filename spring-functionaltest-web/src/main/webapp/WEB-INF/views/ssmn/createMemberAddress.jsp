<div id="wrapper">
  <h1 id="screenTitle">会員情報登録 住所</h1>
  <br>
  <form:form cssClass="form-horizontal" method="post" modelAttribute="memberForm">
    <fieldset>
      <legend>住所情報</legend>
      <div class="form-group">
        <div class="col col-md-2">郵便番号</div>
        <div class="col col-md-3">
          <form:input path="zipCode" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="zipCode" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">都道府県</div>
        <div class="col col-md-3">
          <form:input path="state" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="state" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">市区町村</div>
        <div class="col col-md-3">
          <form:input path="city" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="city" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">番地建物</div>
        <div class="col col-md-3">
          <form:input path="address" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-3">
          <form:errors path="address" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <form:button name="redoPersonal" class="btn btn-default">戻る</form:button>
        <form:button name="otherForm" class="btn btn-default">次へ</form:button>
      </div>
    </fieldset>
  </form:form>
</div>