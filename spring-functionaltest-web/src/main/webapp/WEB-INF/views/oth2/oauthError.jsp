<div id="wrapper">
	<h1>OAuth Error!</h1>
	<c:if test="${not empty error}">
		<p id="ErrorCode">${f:h(error.OAuth2ErrorCode)}</p>
		<p>${f:h(error.message)}</p>
	</c:if>
	<c:if test="${not empty errorMsg}">
		<p id="ErrorCode">${f:h(errorMsg)}</p>
	</c:if>
	<br>
</div>
