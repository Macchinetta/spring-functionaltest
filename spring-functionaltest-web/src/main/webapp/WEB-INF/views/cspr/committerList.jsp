<div id="wrapper">
  <h3 id="screenTitle">コミッター検索画面</h3>

  <div id="xMLHttpRequestResult" class="hidden alert">
    <ul>
      <li id="returnStatus"></li>
    </ul>
  </div>

  <div id="resultMessage" class="hidden alert"></div>

  <div class="text-center">
    <form:form modelAttribute="committerCriteria">
      <form:label path="username" cssClass="space">コミッター名</form:label>
      <form:input path="username" cssClass="space customer-search-field form-control inline" />
      <input id="searchBtn" type="button" class="btn btn-default" value="検索"
        onclick="return cspr.search()" />
    </form:form>
  </div>
  <br>
  <div id="result" class="text-center">
    <table id="committerResultTable"
      class="hidden table table-striped table-bordered table-condensed csprtable">
      <thead>
        <tr>
          <th class="text-center">No.</th>
          <th class="text-center">ユーザ名</th>
          <th class="text-center">Email</th>
          <th />
        </tr>
      </thead>
      <tbody id="committerResult">
      </tbody>
    </table>
  </div>
</div>