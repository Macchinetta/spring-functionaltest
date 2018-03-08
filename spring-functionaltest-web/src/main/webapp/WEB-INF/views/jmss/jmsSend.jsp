<div id="wrapper">

  <h1 id="screenTitle">メッセージ送信</h1>

  <form:form method="POST" action="${pageContext.request.contextPath}/jmss/sendmessage"
    modelAttribute="jmsSendingForm" cssClass="form-horizontal">
    <div class="form-group">
      <form:label path="jmsTodoId" class="col-sm-2 control-label">ユニークキー：</form:label>
      <div class="col col-md-4">
        <form:input path="jmsTodoId" cssClass="quantity form-control input-sm" />
      </div>
    </div>

    <form:hidden path="testCase" />
    <div class="form-group">
      <button id="sendMessage" name="sendMessage" class="btn btn-default">メッセージ送信</button>
    </div>

  </form:form>

</div>

