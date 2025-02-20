<div id="wrapper">
    <h3 id="screenTitle">
        <spring:message code="title.intr.userDetails" />
    </h3>
    <hr />
    <div>Session Stored Locale Value <label id="sessionLocale">${f:h(sessionScope['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'])}</label></div>
    <br />

    <div>
        <label id="name"><spring:message code="label.sf.intr.name" /></label> : Tom Hollander
    </div>
    <div>
        <label id="gender"><spring:message code="label.sf.intr.gender" /></label> : Man
    </div>
    <div>
        <label id="age"><spring:message code="label.sf.intr.age" /></label> : <span id="ageValue"><spring:message code="label.sf.intr.age.value" /></span>
    </div>

    <form action="${pageContext.request.contextPath}/intr" method="get">
        <div align="right">
            <input type="submit" class="btn btn-default" value="<spring:message code='label.sf.intr.back' />" />
            <input type="submit" class="btn btn-default" value="<spring:message code='label.sf.intr.change' />" />
        </div>
    </form>
</div>
