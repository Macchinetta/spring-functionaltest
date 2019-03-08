<div id="wrapper">
  <h1 id="screenTitle">secondView</h1>
  <div>
    <input type="button" id="ajaxButton" value="Ajax通信テスト" onclick="return ajaxTest();" />
    <br>
    outputData：<span id="outputData"></span>
  </div>

  <form:form method="post" action="${pageContext.request.contextPath}/dbsp/0301/007">
    <input type="submit" id="third" name="third" value="third" />
  </form:form>
</div>