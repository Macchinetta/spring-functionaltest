<div id="wrapper">

  <h1 id="screenTitle">Rest File Upload</h1>

  <form:form action="${pageContext.request.contextPath}/rscl/${testId}" class="form-horizontal">

    <div class="form-group">
      <h4>
        <span id="testDescription"> 【&nbsp;${f:h(testDescription)}&nbsp;】 </span>
      </h4>
    </div>
    <button type="submit" id="upload" class="btn btn-default">Upload</button>
  </form:form>

</div>
