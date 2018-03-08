<div id="wrapper">
  <h1 id="screenTitle">モデルに格納されたコレクション型の値を表示できることの確認</h1>
  <br> Collection型の中身
  <table class="table table-striped table-bordered table-condensed">
    <c:forEach var="jspForm" items="${jspFormListForm.jspFormList}" varStatus="rowStatus">
      <tr>
        <th class="inputColumn">firstName&nbsp;${rowStatus.count}</th>
        <td><span id="getFirstName${rowStatus.count}">${f:h(jspForm.firstName)}</span></td>
      </tr>
      <tr>
        <th class="inputColumn">lastName&nbsp;${rowStatus.count}</th>
        <td><span id="getLastName${rowStatus.count}">${f:h(jspForm.lastName)}</span></td>
      </tr>
    </c:forEach>
  </table>
</div>