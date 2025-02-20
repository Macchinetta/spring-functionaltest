<div id="wrapper">
    <h1 id="screenTitle">Display YMD(UTF-8)</h1>
    <div class="container">
        <form:form modelAttribute="sampleForm">
            <form:label path="year">${year}</form:label>:
            <form:input path="year" />
            <br>
            <form:label path="month">${month}</form:label>:
            <form:input path="month" />
            <br>
            <form:label path="day">${day}</form:label>:
            <form:input path="day" />
        </form:form>
    </div>
</div>