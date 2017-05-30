<div id="wrapper">

	<h1 id="screenTitle">ログイン完了画面</h1>

	<table class="table table-bordered table-condensed">
		<tr>
			<th>User Name</th>
			<td><span id="username"> <c:if test="${!empty username}">
						<sec:authentication property="principal.username" />
					</c:if>
			</span></td>
		</tr>
		<tr>
			<th>Test No</th>
			<td><span id="testno"> <c:if test="${!empty testNo}">
                        ${testNo}
                    </c:if>
			</span></td>
		</tr>

		<tr>
			<th>Session</th>
			<td><span id="session"><c:if
						test="${!empty sessionForm.oeratorName}"> ${f:h(sessionForm.oeratorName)}</c:if>
			</span></td>
		</tr>

	</table>

	<form:form
		action="${pageContext.request.contextPath}/spsm/${testNo}/logout"
		method="post">
		<input type="submit" id="logout" class="btn btn-default"
			value="Logout">
	</form:form>

</div>
