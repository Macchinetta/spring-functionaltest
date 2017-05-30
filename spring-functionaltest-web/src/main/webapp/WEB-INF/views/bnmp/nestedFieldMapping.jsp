<div id="wrapper">
    <h1 id="screenTitle">Nestしたフィールドをマッピング</h1>
    <br>
    <form:form
        action="${pageContext.request.contextPath}/bnmp/nestedFieldMapping"
        cssClass="form-horizontal" method="post"
        modelAttribute="beanMapperForm">
        <legend>Nestしたフィールドでもコピーができること</legend>
        <div class="form-group">
            <div class="col col-md-2">First Name(in person)</div>
            <div class="col col-md-3">
                <form:input path="firstName"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">Last Name(in person)</div>
            <div class="col col-md-3">
                <form:input path="lastName"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">age</div>
            <div class="col col-md-3">
                <form:input path="age" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button name="copyNestedFieldBean"
                class="btn btn-default">Nestされたフィールドのコピー</form:button>
        </div>
    </form:form>
</div>