<div id="wrapper">

  <h1 id="screenTitle">Extension of the AsyncRest</h1>

  <form:form action="${pageContext.request.contextPath}/rscl/${testId}" class="form-horizontal">

    <div class="form-group">
      <h4>
        <span id="testDescription"> 【&nbsp;${f:h(testDescription)}&nbsp;】 </span>
      </h4>
    </div>
    <button type="submit" id="send" class="btn btn-default">send</button>
  </form:form>

</div>
