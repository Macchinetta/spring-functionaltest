<div id="wrapper">

  <h1 id="screenTitle">Controllerの実装方法</h1>
  <ul>
    <li><a id="aply0101001" href="${pageContext.request.contextPath}/aply/0101/001">リクエスト処理メソッドに実行条件のリクエストパスを設定できること。</a></li>
    <li><a id="aply0101002" href="${pageContext.request.contextPath}/aply/0101/002">リクエスト処理メソッドに実行条件のHTTPメソッドを設定できること。</a></li>
    <li><a id="aply0101003" href="${pageContext.request.contextPath}/aply/0101/003">リクエスト処理メソッドに実行条件のリクエストパラメータを設定できること。</a></li>
    <li><a id="aply0101004" href="${pageContext.request.contextPath}/aply/0101/004?001">リクエスト処理メソッドに実行条件のリクエストパラメータの否定を設定できること。</a></li>
    <li><a id="aply0102001" href="${pageContext.request.contextPath}/aply/0102/001/id1/1">リクエスト処理メソッドの引数にリクエストパスの一部を設定できること。</a></li>
    <li><a id="aply0102002" href="${pageContext.request.contextPath}/aply/0102/002/id2/2">リクエスト処理メソッドの引数にリクエストパスのデフォルト値取得でエラーとなること。</a></li>
    <li><a id="aply0102003"
      href="${pageContext.request.contextPath}/aply/0102/003?id=12&name=taro">リクエスト処理メソッドの引数にリクエストパラメータを個別に設定できること。</a></li>
    <li><a id="aply0102004" href="${pageContext.request.contextPath}/aply/0102/004?id=20">リクエスト処理メソッドの引数にリクエストパラメータを個別に設定できること。(サーバーエラー)</a></li>
    <li><a id="aply0102005" href="${pageContext.request.contextPath}/aply/0102/005">リクエスト処理メソッドの引数にリダイレクト属性オブジェクトを設定し、リダイレクト先に情報を渡せること。</a></li>
    <li><a id="aply0102006" href="${pageContext.request.contextPath}/aply/0102/006">リクエスト処理メソッドの引数にリダイレクト属性オブジェクトを設定し、リダイレクト先URLに使用する値を設定できること。</a></li>
    <li><a id="aply0102007" href="${pageContext.request.contextPath}/aply/0102/007">リクエスト処理メソッドの引数にCookie情報から取得した値を設定できること。</a></li>
    <li><a id="aply0102008" href="${pageContext.request.contextPath}/aply/0102/008">リクエスト情報メソッドの引数にHTTPサーブレット応答オブジェクトを渡し、Cookie値を設定できること。</a></li>
    <li><a id="aply0102009"
      href="${pageContext.request.contextPath}/aply/0102/009?commonParam1=111&commonParam2=222">共通情報Bean格納メソッドを作成し、リクエスト処理メソッドの引数に共通情報Beanを設定できること。</a></li>
    <li><a id="aply0102010"
      href="${pageContext.request.contextPath}/aply/0102/010?commonParam1=123&commonParam2=456">共通情報Bean格納メソッドを作成し、リクエスト処理メソッドの引数に共通情報Beanを設定できること。(別Controller確認)</a></li>
  </ul>
</div>
