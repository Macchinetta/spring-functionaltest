<div id="wrapper">
    <h1 id="screenTitle">疑似乱数(鍵)生成完了</h1>
    <br />
    <t:messagesPanel />
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <td>鍵長</td>
            <td><span id="keyLength">${f:h(encryptionDataForm.keyLength)}</span></td>
        </tr>
        <tr>
            <td>疑似乱数(鍵)(BASE64)</td>
            <td><span id="generatedKey">${f:h(generatedKey)}</span></td>
        </tr>
    </table>
</div>
