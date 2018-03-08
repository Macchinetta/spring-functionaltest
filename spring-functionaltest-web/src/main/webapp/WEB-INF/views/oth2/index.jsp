<div id="wrapper">

  <h1 id="screenTitle">OAUTH</h1>

  <div>[OTH2 (Common)]</div>
  <ul>
    <li><a id="oth20000001" href="${pageContext.request.contextPath}/oth2/client/common/clear">Clear
        Authorization</a></li>
  </ul>

  <div>[OTH201 (Authorization Code Grant)]</div>
  <ul>
    <li><a id="oth20101001"
      href="${pageContext.request.contextPath}/oth2/client/01/01/001/get?id=123">GET</a> <a
      id="oth20101002" href="${pageContext.request.contextPath}/oth2/client/01/01/002/post?id=123">POST</a>
      <a id="oth20101003" href="${pageContext.request.contextPath}/oth2/client/01/01/003/put/123">PUT</a>
      <a id="oth20101004" href="${pageContext.request.contextPath}/oth2/client/01/01/004/delete/123">DELETE</a>
      <a id="oth20101010" href="${pageContext.request.contextPath}/oth2/client/01/01/010/delete/123">DELETE(Not
        registered scope in client)</a></li>
  </ul>

  <div>[OTH201 (Implicit Grant)]</div>
  <ul>
    <li><a id="oth20102001" href="${pageContext.request.contextPath}/oth2/client/01/02/001/get">GET</a>
      <a id="oth20102002" href="${pageContext.request.contextPath}/oth2/client/01/02/002/post">POST</a>
      <a id="oth20102003" href="${pageContext.request.contextPath}/oth2/client/01/02/003/put">PUT</a>
      <a id="oth20102004" href="${pageContext.request.contextPath}/oth2/client/01/02/004/delete">DELETE</a>
      <a id="oth20102010" href="${pageContext.request.contextPath}/oth2/client/01/02/010/delete">DELETE(Not
        registered scope in client)</a></li>
  </ul>

  <div>[OTH201 (Client Password Credential Grant)]</div>
  <ul>
    <li><a id="oth20103001"
      href="${pageContext.request.contextPath}/oth2/client/01/03/001/get/123">GET</a> <a
      id="oth20103002" href="${pageContext.request.contextPath}/oth2/client/01/03/002/post/123">POST</a>
      <a id="oth20103003" href="${pageContext.request.contextPath}/oth2/client/01/03/003/put/123">PUT</a>
      <a id="oth20103004" href="${pageContext.request.contextPath}/oth2/client/01/03/004/delete/123">DELETE</a>
      <a id="oth20103005" href="${pageContext.request.contextPath}/oth2/client/01/03/005/delete/123">DELETE(Not
        registered scope in client)</a></li>
  </ul>

  <div>[OTH201 (Resource Owner Password Credential Grant)]</div>
  <ul>
    <li><a id="oth20104001" href="${pageContext.request.contextPath}/oth2/client/01/04/">GET</a>
      <a id="oth20104002" href="${pageContext.request.contextPath}/oth2/client/01/04/">POST</a> <a
      id="oth20104003" href="${pageContext.request.contextPath}/oth2/client/01/04/">PUT</a> <a
      id="oth20104004" href="${pageContext.request.contextPath}/oth2/client/01/04/">DELETE</a> <a
      id="oth20104005" href="${pageContext.request.contextPath}/oth2/client/01/04/">DELETE(Not
        registered scope in client)</a></li>
  </ul>

  <div>[OTH202 (Authorization Code Grant / Access Token cooperate via Memory)]</div>
  <ul>
    <li><a id="oth20201001"
      href="${pageContext.request.contextPath}/oth2/client/02/01/001/get/123">GET</a> <a
      id="oth20201002" href="${pageContext.request.contextPath}/oth2/client/02/01/002/post/123">POST</a>
      <a id="oth20201003" href="${pageContext.request.contextPath}/oth2/client/02/01/003/put/123">PUT</a>
      <a id="oth20201004" href="${pageContext.request.contextPath}/oth2/client/02/01/004/delete/123">DELETE</a>
    </li>
  </ul>

  <div>[OTH203 (Authorization Code Grant / Access Token cooperate via Remote)]</div>
  <ul>
    <li><a id="oth20301001"
      href="${pageContext.request.contextPath}/oth2/client/03/01/001/get/123">GET</a> <a
      id="oth20301002" href="${pageContext.request.contextPath}/oth2/client/03/01/002/post/123">POST</a>
      <a id="oth20301003" href="${pageContext.request.contextPath}/oth2/client/03/01/003/put/123">PUT</a>
      <a id="oth20301004" href="${pageContext.request.contextPath}/oth2/client/03/01/004/delete/123">DELETE</a>
      <a id="oth21001002" href="${pageContext.request.contextPath}/oth2/client/03/01/005/get/123">GET(DEAFAULT)</a>
      <a id="oth21101007" href="${pageContext.request.contextPath}/oth2/client/03/01/006/get/123">GET(unauthorized
        at check token endpoint)</a></li>
  </ul>

  <div>[OTH205 (Authorization Code Grant / Scope RU)]</div>
  <ul>
    <li><a id="oth20502001"
      href="${pageContext.request.contextPath}/oth2/client/05/02/001/get/123">GET</a></li>
  </ul>

  <div>[OTH207 (Revoke Token)]</div>
  <ul>
    <li><a id="oth20701001"
      href="${pageContext.request.contextPath}/oth2/client/token/revoke/authcode_db">REVOKE(AUTHCODE
        via DB)</a> <a id="oth20701002"
      href="${pageContext.request.contextPath}/oth2/client/token/revoke/authcode_memory">REVOKE(AUTHCODE
        via Memory)</a> <a id="oth20701003"
      href="${pageContext.request.contextPath}/oth2/client/token/revoke/tokenLocalstorage">REVOKE(LocalStorage)</a>
      <a id="oth20701004"
      href="${pageContext.request.contextPath}/oth2/client/token/revoke/credential_db">REVOKE(RESOURCE
        OWNER and CLIENT via DB)</a></li>
  </ul>

  <div>[OTH211 (Authorization Code Grant / Error Request)]</div>
  <ul>
    <li><a id="oth21101002"
      href="${pageContext.request.contextPath}/oth2/client/11/01/002/get/123">GET</a> <a
      id="oth21101004" href="${pageContext.request.contextPath}/oth2/client/11/01/004/get/123">GET</a>
      <a id="oth21101006" href="${pageContext.request.contextPath}/oth2/client/11/01/006/get">GET</a>
    </li>
  </ul>
</div>