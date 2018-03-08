<div id="wrapper">
  <h1>OAuth Error!</h1>
  <c:if test="${not empty error}">
    <p>${f:h(error.oAuth2ErrorCode)}</p>
    <p>${f:h(error.message)}</p>
  </c:if>
  <br>
</div>
