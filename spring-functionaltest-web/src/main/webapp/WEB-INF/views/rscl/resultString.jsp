<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="wrapper">

	<h1 id="screenTitle">RESTクライアント処理結果【文字列出力】</h1>
	<c:if test="${!empty f:h(resultDescription)}">
		<h4><span id="resultDescription"> 【&nbsp;${f:h(resultDescription)}&nbsp;】 </span></h4>
	</c:if>
	<div class="col-sm-5">
		RcvString: <span id="rcvString">${f:h(rcvString)}</span>
	</div>
</div>
