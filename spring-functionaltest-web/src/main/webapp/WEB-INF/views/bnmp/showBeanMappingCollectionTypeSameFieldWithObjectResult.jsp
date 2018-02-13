<div id="wrapper">
  <h1 id="screenTitle">Collection型のフィールドをマッピング</h1>
  <br> Collection型の中身
  <table class="table table-striped table-bordered table-condensed">
    <c:forEach var="emails" items="${resultBean.emails}" varStatus="rowStatus">
      <tr>
        <td class="inputColumn">email&nbsp;${rowStatus.count}</td>
        <td><span id="getEmail${rowStatus.count}">${f:h(emails.email)}</span></td>
      </tr>
    </c:forEach>
  </table>
  <br> オブジェクト参照チェック
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <td class="inputColumn">コピー元BeanのList = コピー結果BeanのList</td>
      <td><span id="getCollectionRefferenceFromTo">${f:h(collectionRefferenceFormTo)}</span></td>
    </tr>
    <tr>
      <td class="inputColumn">コピー先BeanのList = コピー結果BeanのList</td>
      <td><span id="getCollectionRefferenceDestTo">${f:h(collectionRefferenceDestTo)}</span></td>
    </tr>
    <c:forEach var="objectRefferenceResult" items="${objectRefference}" varStatus="rowStatus">
      <tr>
        <td class="inputColumn">email&nbsp;${rowStatus.count}</td>
        <td><span id="getObjectRefference${rowStatus.count}">${f:h(objectRefferenceResult)}</span></td>
      </tr>
    </c:forEach>
  </table>
</div>