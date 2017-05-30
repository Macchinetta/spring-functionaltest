<div id="wrapper">

	<h1 id="screenTitle">Invalid Session Error</h1>

	<form:form
		action="${pageContext.request.contextPath}/spsm/${testNo}/logout"
		method="post">
		<input type="submit" id="logout" class="btn btn-default"
			value="Logout">
	</form:form>

</div>
