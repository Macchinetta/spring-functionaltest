<h2>Output Escaping</h2>

<form:form action="${pageContext.request.contextPath}/xspr/0101/output" method="post">
    <span>Input Data</span>
    <br />
    <input id="text-output" type="text" class="text" name="outputData" />
    <input id="btn-output" class="mainbtn" type="submit" value="input" />
</form:form>
<br />

<span>Output Data</span>
<p id="xssOutput">${f:h(outputData)}</p>
