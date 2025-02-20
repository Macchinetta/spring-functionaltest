<div id="wrapper">
    <h1 id="screenTitle">コードリストのリロード</h1>

    <div>CDLS0201001</div>
    <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/cdls/0201/001">
        <form:select id="cdls0201001" path="id" items="${CL_CRON_REFRESH_CODELIST}" />
        <form:errors path="id" />
        <p>
            Value:
            <form:input id="cronValue" path="value" />
            <form:errors path="value" />
        </p>
        <button id="updateCdls0201001" type="submit" name="update">cron table update</button>
    </form:form>

    <div>CDLS0201002</div>
    <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/cdls/0201/002">
        <form:select id="cdls0201002" path="id" items="${CL_REFRESH_CODELIST}" />
        <form:errors path="id" />
        <p>
            Value:
            <form:input id="refreshValue" path="value" />
            <form:errors path="value" />
        </p>
        <button id="updateCdls0201002" type="submit" name="update">refresh table update</button>
    </form:form>
    <a id="codeListRefreshEndPoint" href="${pageContext.request.contextPath}/cdls/0201/002?refresh">コードリストリフレッシュエンドポイント</a>

    <div>CDLS0202001</div>
    <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/cdls/0202/001">
        <form:select id="cdls0202001" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
        <form:errors path="id" />
        <p>
            Value:
            <form:input id="cronValue2" path="value" />
            <form:errors path="value" />
        </p>
        <button id="updateCdls0202001" type="submit" name="update">cron table update</button>
    </form:form>

    <div>CDLS0202002</div>
    <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/cdls/0202/002">
        <form:select id="cdls0202002" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
        <form:errors path="id" />
        <p>
            Value:
            <form:input id="cronValue3" path="value" />
            <form:errors path="value" />
        </p>
        <button id="updateCdls0202002" type="submit" name="update">cron table update</button>
    </form:form>

    <div>CDLS0202003</div>
    <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/cdls/0202/003">
        <form:select id="cdls0202003" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
        <form:errors path="id" />
        <p>
            Value:
            <form:input id="refreshValue2" path="value" />
            <form:errors path="value" />
        </p>
        <button id="updateCdls0202003" type="submit" name="update">refresh table update</button>
    </form:form>
    <a id="codeListRefreshEndPoint2" href="${pageContext.request.contextPath}/cdls/0202/003?refresh">コードリストリフレッシュエンドポイント</a>

    <div>CDLS0202004</div>
    <form:form modelAttribute="updateCodeListForm" action="${pageContext.request.contextPath}/cdls/0202/003">
        <form:select id="cdls0202004" path="id" items="${CL_RELOADABLE_I18N_DB_PRICE}" />
        <form:errors path="id" />
        <p>
            Value:
            <form:input id="refreshValue3" path="value" />
            <form:errors path="value" />
        </p>
        <button id="updateCdls0202004" type="submit" name="update">refresh table update</button>
    </form:form>
    <a id="codeListRefreshEndPoint3" href="${pageContext.request.contextPath}/cdls/0202/004?refresh">コードリストリフレッシュエンドポイント</a>
</div>
