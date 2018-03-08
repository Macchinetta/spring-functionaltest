<div id="wrapper">

  <h1 id="screenTitle">削除対象のリクエストパス入力</h1>

  <form:form action="${pageContext.request.contextPath}/rscl/${testId}" class="form-horizontal">

    <div class="form-group">
      <h4>
        <span id="testDescription"> 【&nbsp;${f:h(testDescription)}&nbsp;】 </span>
      </h4>

      <div class="form-group">
        <label for="path" class="col-sm-1 control-label">パス：</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="path" name="path" placeholder="Path">
        </div>
      </div>
    </div>
    <button type="submit" id="delete" class="btn btn-default">Delete</button>
  </form:form>
</div>
