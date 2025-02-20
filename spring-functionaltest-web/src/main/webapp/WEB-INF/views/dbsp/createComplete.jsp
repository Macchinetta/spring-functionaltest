<div id="wrapper">
    <h1 id="screenTitle">ユーザ登録完了画面</h1>
    <form:form action="${pageContext.request.contextPath}/dbsp/" method="get" modelAttribute="userCreateForm">
        output:
        <div id="output">${f:h(output)}</div>
        <br />
        <form:button name="backToTop">Top</form:button>
    </form:form>
</div>
