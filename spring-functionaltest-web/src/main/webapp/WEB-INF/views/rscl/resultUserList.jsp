<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="wrapper">

	<h1 id="screenTitle">RESTクライアント処理結果【ユーザリスト情報出力】</h1>
	<c:if test="${!empty f:h(resultDescription)}">
		<h4>
			<span id="resultDescription"> 【 ${f:h(resultDescription)} 】 </span>
		</h4>
	</c:if>
	<table
		class="table table-striped table-bordered table-condensed pgnttable">
		<thead>
			<tr>
				<th>name</th>
				<th>age</th>
			</tr>
		</thead>
		<c:forEach var="user" items="${userList}" varStatus="status">
			<tr>
				<td><span id="name${status.count}">${user.name}</span></td>
				<td><span id="age${status.count}">${user.age }</span></td>
			</tr>
		</c:forEach>
	</table>
</div>
