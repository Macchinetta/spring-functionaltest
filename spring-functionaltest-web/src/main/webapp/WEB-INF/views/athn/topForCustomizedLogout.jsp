<div id="wrapper">

	<h1 id="screenTitle">ログインしました</h1>

	<table class="table table-bordered table-condensed">
		<tr>
			<th>User Name</th>
			<td><span id="username"> <c:if test="${!empty username}">
						<sec:authentication property="principal.username" />
					</c:if>
			</span></td>
		</tr>
	</table>

	<form:form
		action="${pageContext.request.contextPath}/athn/1501/001/logout">
		<sec:csrfInput />
		<button id="logout">Logout</button>
	</form:form>
</div>
