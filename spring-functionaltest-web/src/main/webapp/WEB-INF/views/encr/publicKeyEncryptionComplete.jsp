<div id="wrapper">
    <h1 id="screenTitle">暗復号処理完了</h1>
    <br />
    <t:messagesPanel />
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <td>平文</td>
            <td><span id="rawText">${f:h(encryptionDataForm.rawText)}</span></td>
        </tr>
        <tr>
            <td>暗号化結果(BASE64)</td>
            <td><span id="encryptedText">${f:h(encryptedText)}</span></td>
        </tr>
        <tr>
            <td>復号結果</td>
            <td><span id="decryptedText">${f:h(decryptedText)}</span></td>
        </tr>
    </table>
</div>
