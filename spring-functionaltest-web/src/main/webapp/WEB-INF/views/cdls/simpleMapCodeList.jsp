<div id="wrapper">
    <h1 id="screenTitle">SimpleMapCodeListの読み込み</h1>

    <div>CDLS0101001</div>
    <div>
        <form:form modelAttribute="codeListForm">
            <form:select id="cdls0101001" path="code">
                <form:option value="" label="--Select--" />
                <form:options items="${CL_ORDERSTATUS}" />
            </form:select>
        </form:form>
    </div>
    <div>CDLS0101002</div>
    <div>
        <form:form modelAttribute="clOrderStatusForm" method="post" action="${pageContext.request.contextPath}/cdls/0101/002">
            <form:select id="cdls0101002" items="${CL_ORDERSTATUS}" path="id" />
            <form:errors path="id" />
            <button id="cdls0101002Submit" type="submit" name="post">Submit</button>
        </form:form>
        <div id="cdls0101002Result">${f:h(orderStatusCodeListValue)}</div>
    </div>
</div>
