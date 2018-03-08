<div id="wrapper">

  <h1 id="screenTitle">[入力]半角空白を除いた文字列を取得できることを確認する</h1>
  <br>
  <t:messagesPanel />
  <form:form action="${pageContext.request.contextPath}/stpr/0101/001/trimWhitespace"
    cssClass="form-horizontal" method="post" modelAttribute="stringProcessing01Form">
    <div class="form-group">
      <form:label path="targetText" class="col col-md-2 control-label">トリム対象文字列</form:label>
      <div class="col col-md-3">
        <form:input path="targetText" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button id="trim" class="btn btn-default">トリム</form:button>
    </div>
  </form:form>
</div>
