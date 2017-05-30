<div id="wrapper">

	<h1 id="screenTitle">Rest処理におけるタイムアウト設定</h1>

	<form:form action="${pageContext.request.contextPath}/rscl/${testId}"
		class="form-horizontal">

		<div class="form-group">
			<h4>
				<span id="testDescription"> 【 ${f:h(testDescription)} 】 </span>
			</h4>
		</div>
		<button type="submit" id="occur" class="btn btn-default">occur</button>
	</form:form>

</div>
