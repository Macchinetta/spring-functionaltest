<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="wrapper">

	<h1 id="screenTitle">コンバートするユーザ情報入力</h1>

	<form:form action="${pageContext.request.contextPath}/rscl/${testId}"
		class="form-horizontal">

		<div class="form-group">
			<h4>
				<span id="testDescription"> 【&nbsp;${f:h(testDescription)}&nbsp;】 </span>
			</h4>
			<div class="form-group">
				<label for="message" class="col-sm-2 control-label">メッセージ：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="message" name="message"
						placeholder="Message">
				</div>
			</div>
		</div>
		<button type="submit" id="send" class="btn btn-default">send</button>
	</form:form>
</div>
