<div id="wrapper">
    <h1 id="screenTitle">JdbcCodeListの読み込み</h1>

    <div>CDLS0103001</div>
    <div>
        <form:select id="cdls0103001" items="${CL_AUTHORITIES}" path="codeListForm.code" />
    </div>
    <div>CDLS0103002</div>
    <div>
        <form:form modelAttribute="clAuthoritiesForm" method="post" action="${pageContext.request.contextPath}/cdls/0103/002">
            <form:select id="cdls0103002" items="${CL_AUTHORITIES}" path="id" />
            <form:errors path="id" />
            <button id="cdls0103002Submit" type="submit" name="post">Submit</button>
        </form:form>
        <div id="cdls0103002Result">${f:h(authoritiesCodeListValue)}</div>
    </div>
    <div>CDLS0103003</div>
    <div>
        <form:form id="cdls0103003" modelAttribute="authorityForCollectionForm" method="post" action="${pageContext.request.contextPath}/cdls/0103/003">
            <form:checkboxes items="${CL_AUTHORITIES}" path="authorities" delimiter="<br />" />
            <br />
            <form:errors path="authorities*" />
            <button id="cdls0103003Submit" type="submit" name="post">Submit</button>
        </form:form>
        <div id="cdls0103003Result">
            <c:forEach var="authoritiy" items="${authorities}">
                <div>${f:h(authoritiy)}</div>
            </c:forEach>
        </div>
    </div>
</div>
