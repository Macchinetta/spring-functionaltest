<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.soap.index" />
<c:set var="functionId" value="soap" />

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<sec:csrfMetaTags />
<title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message
    code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
<script type="text/javascript"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">SOAP SOAP Web Service（サーバ/クライアント）</h1>

  <div>[SOAP0101001]
    クライアントからSOAP通信を行い、SOAPサーバのWebサービスが実行できることの確認</div>
  <ul>
    <li><a id="SOAP0101001-01" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
  </ul>

  <div>[SOAP0102002]
    エンドポイントを指定したクライアントからSOAP通信を行い、SOAPサーバのWebサービスが実行できることの確認</div>
  <ul>
    <li><a id="SOAP0102002-01" href="${pageContext.request.contextPath}/soap/todo/wsdl/list">TODO一覧取得</a></li>
  </ul>

  <div>[SOAP0201001] SOAPサーバで引数の入力チェックができることの確認</div>
  <ul>
    <li><a id="SOAP0201001-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0201001-02"
      href="${pageContext.request.contextPath}/soap/todo/todo/get?form">TODO取得</a></li>
    <li><a id="SOAP0201001-03"
      href="${pageContext.request.contextPath}/soap/todo/todo/get?form">TODO取得</a></li>
  </ul>

  <div>[SOAP0201002] SOAPサーバでJavaBeanの入力チェックができることの確認</div>
  <ul>
    <li><a id="SOAP0201002-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0201002-02" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
    <li><a id="SOAP0201002-03"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0201002-04" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
  </ul>

  <div>[SOAP0201003]
    SOAPサーバでバリデーションのグループ化を用いたJavaBeanの入力チェックができることの確認</div>
  <ul>
    <li><a id="SOAP0201003-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0201003-02"
      href="${pageContext.request.contextPath}/soap/todo/todo/update?form">TODO更新</a></li>
    <li><a id="SOAP0201003-03" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
    <li><a id="SOAP0201003-04"
      href="${pageContext.request.contextPath}/soap/todo/todo/update?form">TODO更新</a></li>
    <li><a id="SOAP0201003-05" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
  </ul>

  <div>[SOAP0201004] SOAPサーバで複数項目の入力チェックができること</div>
  <ul>
    <li><a id="SOAP0201004-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0201004-02" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
    <li><a id="SOAP0201004-03"
      href="${pageContext.request.contextPath}/soap/todo/todo/update?form">TODO更新</a></li>
    <li><a id="SOAP0201004-04" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
  </ul>

  <div>[SOAP0301001]
    クライアントからBASIC認証における、認証情報を送信し、SOAPサーバでBASIC認証を行うことができることと、認証情報を参照できることの確認</div>
  <ul>
    <li><a id="SOAP0301001-01"
      href="${pageContext.request.contextPath}/soap/todo/user/create?form">TODO作成</a></li>
    <li><a id="SOAP0301001-02" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
    <li><a id="SOAP0301001-03"
      href="${pageContext.request.contextPath}/soap/todo/anonymous/create?form">TODO作成</a></li>
    <li><a id="SOAP0301001-04" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
  </ul>

  <div>[SOAP0301002] SOAPサーバで認可を行うことができることの確認</div>
  <ul>
    <li><a id="SOAP0301002-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0301002-02" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
    <li><a id="SOAP0301002-03"
      href="${pageContext.request.contextPath}/soap/todo/admin/delete?form">TODO削除</a></li>
    <li><a id="SOAP0301002-04" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
    <li><a id="SOAP0301002-05"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0301002-06" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
    <li><a id="SOAP0301002-07"
      href="${pageContext.request.contextPath}/soap/todo/user/delete?form">TODO削除</a></li>
    <li><a id="SOAP0301002-08" href="${pageContext.request.contextPath}/soap/todo/todo/list">TODO一覧取得</a></li>
  </ul>

  <div>[SOAP0401002]
    SOAPサーバのサービスでリソース未検出エラー例外が発生した際に、SOAPサーバが例外をSOAPFaultにラップしてスローし、クライアントでキャッチして処理できることの確認</div>
  <ul>
    <li><a id="SOAP0401002-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
    <li><a id="SOAP0401002-02"
      href="${pageContext.request.contextPath}/soap/todo/todo/get?form">TODO取得</a></li>
  </ul>

  <div>[SOAP0401003]
    SOAPサーバのサービスで業務エラー例外が発生した際に、SOAPサーバが例外をSOAPFaultにラップしてスローし、クライアントでキャッチして処理できることの確認</div>
  <ul>
    <li><a id="SOAP0401003-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/create?form">TODO作成</a></li>
  </ul>

  <div>[SOAP0401004]
    SOAPサーバの例外ハンドラで設定されていない例外が発生したとき、システム例外をスローし、クライアントでキャッチして処理できることの確認</div>
  <ul>
    <li><a id="SOAP0401004-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/confirmHandler">例外ハンドラ確認</a></li>
  </ul>

  <div>[SOAP0501001] MTOMを利用したファイル転送ができることを確認する</div>
  <ul>
    <li><a id="SOAP0501001-01"
      href="${pageContext.request.contextPath}/soap/todo/todo/upload?form">ファイルアップロード確認</a></li>
  </ul>

  <div>[SOAP0601001]
    クライアントで設定した時間でSOAPサーバへのリクエストタイムアウトになることを確認する</div>
  <ul>
    <li><a id="SOAP0601001-01"
      href="${pageContext.request.contextPath}/soap/todo/timeout/requestTimeout">リクエストタイムアウト確認</a></li>
  </ul>

  <div>[SOAP0601002]
    クライアントで設定した時間でSOAPサーバへのコネクションタイムアウトになることを確認する</div>
  <ul>
    <li><span id="SOAP0601002-01">コネクションタイムアウトを意図的に発生させることができないため未実施</span></li>
  </ul>

  <div>[SOAP0701001]
    クライアントで設定した時間でSOAPサーバへのリクエストタイムアウトになることを確認する</div>
  <ul>
    <li><a id="SOAP0701001-01"
      href="${pageContext.request.contextPath}/soap/todo/wsimport/list">TODO一覧取得</a></li>
  </ul>

  <div>Todoの初期化</div>
  <ul>
    <li><a id="deleteTodos" href="${pageContext.request.contextPath}/soap/todo/todo/deleteAll">TODO全削除</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>