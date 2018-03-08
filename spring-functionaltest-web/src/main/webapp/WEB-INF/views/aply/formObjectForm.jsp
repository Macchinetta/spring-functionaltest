<div id="wrapper">

  <h1 id="screenTitle">フォームオブジェクトの実装 入力画面</h1>
  <br />
  <form:form method="post" cssClass="form-horizontal" modelAttribute="formObjectForm">
    <form:errors cssClass="errorMessage" />
    <fieldset>
      <div class="form-group">
        <div class="col col-md-4">value1(数値書式"#,#")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value1" />
        </div>
        <div class="col col-md-3">
          <span id="value1ValidationErrorMessage"><form:errors path="value1"
              cssClass="errorMessage" /></span>
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value2(数値書式"style=NUMBER")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value2" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value3(数値書式"style=CURRENCY")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value3" />
        </div>
        <div class="col col-md-3">
          <form:errors path="value3" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value4(数値書式"style=PERCENT")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value4" />
        </div>
        <div class="col col-md-3">
          <form:errors path="value4" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value5(日時書式"yyyyMMdd")</div>
        <div class="col col-md-2">
          <form:input type="text" path="value5" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value6(InitBinder利用)</div>
        <div class="col col-md-2">
          <form:input type="text" path="value6" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <div class="col col-md-4">value7(InitBinder利用)</div>
        <div class="col col-md-2">
          <form:input type="text" path="value7" />
        </div>
        <div class="col col-md-3"></div>
      </div>
      <div class="form-group">
        <form:button id="submit" name="submit" class="btn btn-default">submit</form:button>
      </div>
    </fieldset>
  </form:form>
</div>
