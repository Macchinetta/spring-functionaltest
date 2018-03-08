<div id="wrapper">
  <h1 id="screenTitle">売買完了</h1>

  <t:messagesPanel />

  <table class="table table-striped">
    <thead>
      <tr>
        <th class="itemCode">itemCode</th>
        <th class="itemName">itemName</th>
        <th class="quantity">quantity</th>
        <th class="version">version</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td id="result_itemCode" class="itemCode">${f:h(stock.itemCode)}</td>
        <td id="result_itemName" class="itemName">${f:h(stock.itemName)}</td>
        <td id="result_quantity" class="quantity">${f:h(stock.quantity)}</td>
        <td id="result_version" class="version">${f:h(stock.version)}</td>
        <td id="databaseId" class="version">${f:h(databaseId)}</td>
      </tr>
    </tbody>
  </table>

</div>
