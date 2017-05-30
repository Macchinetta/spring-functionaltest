<div id="wrapper">

	<h1 id="screenTitle">AsyncRestRequestPass入力</h1>

	<form:form action="${pageContext.request.contextPath}/rscl/${testId}"
		class="form-horizontal">

		<div class="form-group">
			<h4>
				<span id="testDescription"> 【 ${f:h(testDescription)} 】
				</span>
			</h4>
			<div class="form-group">
				<label for="path" class="col-sm-2 control-label">リクエストパス</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="path" name="path"
						placeholder="Path">
				</div>
			</div>
		</div>
		<button type="submit" id="send" class="btn btn-default">send</button>
	</form:form>

</div>
