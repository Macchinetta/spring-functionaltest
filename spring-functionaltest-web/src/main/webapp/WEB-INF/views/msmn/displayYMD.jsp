<div id="wrapper">
    <h1 id="screenTitle">Display YMD</h1>
    <div class="container">
        <form:form modelAttribute="sampleForm">
            <form:label path="year">
                <spring:message code="label.aa.bb.year" />
            </form:label>:
            <form:input path="year" />
            <br>
            <form:label path="month">
                <spring:message code="label.aa.bb.month" />
            </form:label>:
            <form:input path="month" />
            <br>
            <form:label path="day">
                <spring:message code="label.aa.bb.day" />
            </form:label>:
            <form:input path="day" />
        </form:form>
    </div>
</div>