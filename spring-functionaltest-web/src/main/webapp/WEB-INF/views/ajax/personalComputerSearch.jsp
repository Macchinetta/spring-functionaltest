<div id="wrapper">

  <div id="xMLHttpRequestResult" class="hidden alert">
    <ul>
      <li>ステータスコード:<span id="returnStatus"></span></li>
      <li>Content-Type:<span id="contentType"></span></li>
    </ul>
  </div>

  <div id="resultMessage" class="hidden alert"></div>

  <div class="text-center">
    <form id="personalComputerCriteria">
      <spring:nestedPath path="personalComputerCriteria">
        <fieldset>
          <legend>登録PC検索画面</legend>
          <form:label path="personalComputerName" cssClass="space">検索するPC名を入力</form:label>
          <form:input path="personalComputerName"
            cssClass="space pc-search-field form-control inline" />

          <form:button id="searchBtn" name="search" class="btn btn-default"
            onclick="return ajax.search()">検索</form:button>
        </fieldset>
      </spring:nestedPath>
    </form>
  </div>
  <br>
  <div id="result" class="text-center">
    <table id="personalComputerResultTable"
      class="hidden table table-striped table-bordered table-condensed csprtable">
      <thead>
        <tr>
          <th class="text-center">No.</th>
          <th class="text-center">PC NAME</th>
          <th class="text-center">OS</th>
          <th class="text-center">CPU</th>
          <th class="text-center">RAM</th>
          <th class="text-center">VIDEOCARD</th>
          <th class="text-center">HDD</th>
          <th class="text-center">POWER</th>
          <th class="text-center">PRICE</th>
        </tr>
      </thead>
      <tbody id="personalComputerResult">
      </tbody>
    </table>
  </div>
</div>