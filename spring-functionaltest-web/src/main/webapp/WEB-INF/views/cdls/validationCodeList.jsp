<div id="wrapper">
    <h1 id="screenTitle">コード値の入力チェック</h1>

    <div>CDLS0107001</div>
    <div>
        <form:form action="${pageContext.request.contextPath}/cdls/0107/001?post" method="post" modelAttribute="clOrderStatusForm">
            <form:select id="cdls0107001" path="id">
                <form:options items="${CL_ORDERSTATUS}" />
            </form:select>
            <form:errors id="errorCDLS0107001" path="id" />
            <button type="submit" id="submitCDLS0107001">Submit Codelist</button>
        </form:form>
    </div>
    <div>CDLS0107002</div>
    <div>
        <form:form action="${pageContext.request.contextPath}/cdls/0107/002?post" method="post" modelAttribute="clOrderStatusForm">
            <form:select id="cdls0107002" path="id">
                <form:option value="nonExistentValue" label="nonExistentValue" />
            </form:select>
            <form:errors id="errorCDLS0107002" path="id" />
            <button type="submit" id="submitCDLS0107002">Submit Codelist</button>
        </form:form>
    </div>
</div>
