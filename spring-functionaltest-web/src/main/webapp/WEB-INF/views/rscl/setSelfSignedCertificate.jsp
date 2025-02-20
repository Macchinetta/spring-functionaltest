<div id="wrapper">
    <h1 id="screenTitle">Restクライアントにおける自己署名証明書</h1>

    <form:form action="${pageContext.request.contextPath}/rscl/${testId}" class="form-horizontal">
        <div class="form-group">
            <h4>
                <span id="testDescription"> 【&nbsp;${f:h(testDescription)}&nbsp;】 </span>
            </h4>
        </div>
        <button type="submit" id="set" class="btn btn-default">set</button>
    </form:form>
</div>
