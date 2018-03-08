<div id="wrapper">

  <h1 id="screenTitle">Choose Process(For Method Authorize)</h1>

  <form:form action="${pageContext.request.contextPath}/athr/0301/001" method="post">
    <br>
    <fieldset>
      <div class="form-group">
        <div class="col col-md-2">
          <input type="submit" id="insert" class="btn btn-default" value="insert" name="insert">
        </div>
      </div>
    </fieldset>
  </form:form>
  <br> <br>

  <form:form action="${pageContext.request.contextPath}/athr/0301/001" method="get">
    <fieldset>
      <legend>Criteria</legend>
      <br>
      <div class="form-group">
        <div class="col col-md-2">
          <input type="submit" id="select" class="btn btn-default" value="select" name="select">
        </div>
        <div class="col col-md-2">
          <label for="device">Device</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="device" name="device">
        </div>
        <div class="col col-md-2">
          <label for="device">Execute User</label>
        </div>
        <div class="col col-md-3">
          <input type="text" class="form-control input-sm" id="executeUser" name="executeUser">
        </div>
      </div>
    </fieldset>
  </form:form>
</div>
