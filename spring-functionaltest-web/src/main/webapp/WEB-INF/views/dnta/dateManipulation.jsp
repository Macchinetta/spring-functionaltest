<div id="wrapper">
	<h1 id="screenTitle">日付操作</h1>
	<br>
	<form:form action="${pageContext.request.contextPath}/dnta/dateManipulation"
		method="get" modelAttribute="calcDateForm" class="form-horizonal">
		<legend>日時の計算</legend>
		<div class="form-group">
			<div class="row">
				<div class="col col-md-2">取得日時</div>
				<div class="col col-md-2">
					<form:input path="inputDateTime" cssClass="form-control input-sm" />
				</div>
			</div>
			<div class="row">
				<div class="col col-md-2">増加数</div>
				<div class="col col-md-2">
					<form:input path="plus" cssClass="form-control input-sm" />
				</div>
			</div>
			<div class="row">
				<div class="col col-md-2">減少数</div>
				<div class="col col-md-2">
					<form:input path="minus" cssClass="form-control input-sm" />
				</div>
			</div>
			<div class="row">
				<div class="col col-md-2">
					<form:button name="calcYear" class="btn btn-default">増減年取得</form:button>
				</div>
				<div class="col col-md-2">
					<form:button name="calcMonth" class="btn btn-default">増減月取得</form:button>
				</div>
				<div class="col col-md-2">
					<form:button name="calcDay" class="btn btn-default">増減日取得</form:button>
				</div>
				<div class="col col-md-2">
					<form:button name="calcHour" class="btn btn-default">増減時取得</form:button>
				</div>
				<div class="col col-md-2">
					<form:button name="calcMinute" class="btn btn-default">増減分取得</form:button>
				</div>
				<div class="col col-md-2">
					<form:button name="calcSecond" class="btn btn-default">増減秒取得</form:button>
				</div>
			</div>
		</div>
	</form:form>
	<br />
	<form:form action="${pageContext.request.contextPath}/dnta/dateManipulation"
		class="form-horizontal" method="get" modelAttribute="compareDateTimeForm">
		<fieldset>
			<legend>日時比較</legend>
			<div class="form-group">
				<table>
					<tr>
						<td></td>
						<td>年</td>
						<td>月</td>
						<td>日</td>
					</tr>
					<tr>
						<td>Past</td>
						<td><form:input path="year1" cssClass="form-control input-sm" /></td>
						<td><form:input path="month1"
								cssClass="form-control input-sm" /></td>
						<td><form:input path="day1" cssClass="form-control input-sm" /></td>
					</tr>
					<tr>
						<td>Now</td>
						<td><form:input path="year2" cssClass="form-control input-sm" /></td>
						<td><form:input path="month2"
								cssClass="form-control input-sm" /></td>
						<td><form:input path="day2" cssClass="form-control input-sm" /></td>
					</tr>
					<tr>
						<td>Future</td>
						<td><form:input path="year3" cssClass="form-control input-sm" /></td>
						<td><form:input path="month3"
								cssClass="form-control input-sm" /></td>
						<td><form:input path="day3" cssClass="form-control input-sm" /></td>
					</tr>
				</table>
				<button id="compareDate" name="compareDate" class="btn btn-default">日付比較</button>
			</div>
			<div class="form-group">
				<table>
					<tr>
						<td></td>
						<td>時</td>
						<td>分</td>
						<td>秒</td>
					</tr>
					<tr>
						<td>Past</td>
						<td><form:input path="hour1" cssClass="form-control input-sm" /></td>
						<td><form:input path="minute1"
								cssClass="form-control input-sm" /></td>
						<td><form:input path="second1"
								cssClass="form-control input-sm" /></td>
					</tr>
					<tr>
						<td>Now</td>
						<td><form:input path="hour2" cssClass="form-control input-sm" /></td>
						<td><form:input path="minute2"
								cssClass="form-control input-sm" /></td>
						<td><form:input path="second2"
								cssClass="form-control input-sm" /></td>
					</tr>
					<tr>
						<td>Future</td>
						<td><form:input path="hour3" cssClass="form-control input-sm" /></td>
						<td><form:input path="minute3"
								cssClass="form-control input-sm" /></td>
						<td><form:input path="second3"
								cssClass="form-control input-sm" /></td>
					</tr>
				</table>
				<button id="compareTime" name="compareTime" class="btn btn-default">時刻比較</button>
			</div>
		</fieldset>
	</form:form>
	<br />
	<form:form action="${pageContext.request.contextPath}/dnta/dateManipulation"
		cssClass="form-horizontal" method="get"
		modelAttribute="changeTypeForm">
		<legend>日時判定</legend>
		<div class="form-group">
			<div class="col col-md-2">
				<label for="name">日時文字列</label>
			</div>
			<div class="col col-md-2">
				<form:input path="targetDate" cssClass="form-control input-sm" />
			</div>
		</div>
		<div class="form-group">
			<form:button name="checkDateString" class="btn btn-default">日付文字列判定</form:button>
			<form:button name="checkTimeString" class="btn btn-default">時間文字列判定</form:button>
			<form:button name="checkLeapYear" class="btn btn-default">閏年判定</form:button>
		</div>
	</form:form>
	<br />
	<form:form action="${pageContext.request.contextPath}/dnta/dateManipulation"
		cssClass="form-horizontal" method="get"
		modelAttribute="changeTypeForm">
		<legend>年月日時分秒の取得</legend>
		<div class="form-group">
			<div class="col col-md-2">
				<label for="name">日時文字列</label>
			</div>
			<div class="col col-md-2">
				<input id="target" name="targetDate" class="form-control input-sm" type="text" value=""/>
			</div>
		</div>
		<div class="form-group">
			<form:button name="getEachValueOfDate" class="btn btn-default">年月日の取得</form:button>
			<form:button name="getEachValueOfTime" class="btn btn-default">時分秒の取得</form:button>
		</div>
	</form:form>
</div>