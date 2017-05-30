<div id="wrapper">

	<h1 id="screenTitle">AsyncRestRequestの送信</h1>

	<form:form action="${pageContext.request.contextPath}/rscl/${testId}"
		class="form-horizontal">

		<div class="form-group">
			<h4>
				<span id="testDescription"> 【 ${f:h(testDescription)} 】 </span>
			</h4>
		</div>
		<button type="submit" id="send" class="btn btn-default">send</button>
	</form:form>

</div>
