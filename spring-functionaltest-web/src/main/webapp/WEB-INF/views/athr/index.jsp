<div id="wrapper">

  <h1 id="screenTitle">ATHR 認可</h1>

  <sec:authentication property="principal" var="principal" />

  <div>
    ようこそ
    <sec:authorize access="isAuthenticated()">
      <span id="loginUserName">${f:h(principal.username)}</span>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
      <span id="loginUserName"><spring:message code="label.sf.cmmn.defaultUserName" /></span>
    </sec:authorize>
    さん
  </div>
  <br>

  <div>[ATHN02] Webリソースへの認可</div>
  <ul>
    <li><a id="athr0201001" href="${pageContext.request.contextPath}/athr/0201/001/afterLogin">athr0201001</a></li>
    <li><a id="athr0201002" href="${pageContext.request.contextPath}/athr/0201/002/restrict">athr0201002</a></li>
    <li><a id="athr0201003"
      href="${pageContext.request.contextPath}/athr/0201/004/showForAccessSuccessfully">athr0201003</a></li>
  </ul>
  <div>[ATHN03] メソッドへの認可・[ATHR05]
    認可エラー時のレスポンス・[ATHR06] アクセスポリシーの定義</div>
  <ul>
    <li><a id="athr0301001" href="${pageContext.request.contextPath}/athr/0301/001/">athr0301001</a></li>
  </ul>

  <div>[ATHR04] JSPの画面項目への認可</div>
  <ul>
    <li><a id="athr0401001" href="${pageContext.request.contextPath}/athr/0401/001">athr0401001</a></li>
  </ul>

  <div>[ATHR06] アクセスポリシーの定義・[ATHN01]
    アクセスポリシーの記述方法</div>
  <ul>
    <li><a id="athr0601001" href="${pageContext.request.contextPath}/athr/0601/001">athr0601001</a></li>
    <li><a id="athr0601002" href="${pageContext.request.contextPath}/athr/0601/002">athr0601002</a></li>
    <li><a id="athr0601003" href="${pageContext.request.contextPath}/athr/0601/003">athr0601003</a></li>
    <li><a id="athr0601004" href="${pageContext.request.contextPath}/athr/0601/004">athr0601004</a></li>
    <li><a id="athr0601019" href="${pageContext.request.contextPath}/athr/0601/019">athr0601019</a></li>
    <li><a id="athr0601020" href="${pageContext.request.contextPath}/athr/0601/020">athr0601020</a></li>
    <li><a id="athr0601021" href="${pageContext.request.contextPath}/athr/0601/021">athr0601021</a></li>
    <li><a id="athr0601022" href="${pageContext.request.contextPath}/athr/0601/022">athr0601022</a></li>
    <li><a id="athr0602003" href="${pageContext.request.contextPath}/athr/0901/003">athr0602003</a></li>

  </ul>
  <div>[ATHR07] 認可エラー時のレスポンス (認証済みユーザー編)</div>
  <ul>
    <li><a id="athr0701001" href="${pageContext.request.contextPath}/athr/0701/001">athr0701001</a></li>
  </ul>

  <div>[ATHR08] 認可エラー時のレスポンス 未認証ユーザー編)</div>
  <ul>
    <li>athr0802001で集約</li>

    <li><a id="athr0802001" href="${pageContext.request.contextPath}/athr/0802/001/afterLogin">athr0802001（通常）</a>

    </li>
    <li><a id="athr0802001API" href="${pageContext.request.contextPath}/athr/0802/001/api">athr0802001（API）</a></li>
  </ul>

  <div>[ATHR09] ロール階層機能</div>
  <ul>
    <li><a id="athr0901001" href="${pageContext.request.contextPath}/athr/0901/001">athr0901001</a></li>
    <li><a id="athr0901002" href="${pageContext.request.contextPath}/athr/0901/002">athr0901002</a></li>
    <li><a id="athr0901003" href="${pageContext.request.contextPath}/athr/0901/003">athr0901003</a></li>
  </ul>

</div>
