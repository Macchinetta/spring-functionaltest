<div id="wrapper">

  <h1 id="screenTitle">共通JSPをインクルードした画面</h1>

  <t:messagesPanel />

  <ul>
    <li id="message1">共通JSPで宣言しているタグライブラリを利用してエスケープ処理した文字列を以下に表示します。</li>
    <li id="message2">${f:h("<input name='test'>")}</li>
  </ul>
</div>
