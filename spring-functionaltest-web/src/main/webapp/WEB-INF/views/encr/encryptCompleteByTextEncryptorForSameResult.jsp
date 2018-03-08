<div id="wrapper">
  <h1 id="screenTitle">暗号化処理完了</h1>
  <br>
  <t:messagesPanel />
  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <td>平文</td>
      <td><span id="rawText">${f:h(encryptionDataForm.rawText)}</span></td>
    </tr>
    <tr>
      <td>暗号化結果1</td>
      <td><span id="encryptedText">${f:h(encryptedText)}</span></td>
    </tr>
    <tr>
      <td>暗号化結果2</td>
      <td><span id="encryptedText2">${f:h(encryptedText2)}</span></td>
    </tr>
  </table>
</div>