<div id="wrapper">
    <h1 id="screenTitle">差分時間更新</h1>
    <br />
    <form:form action="./confirm" cssClass="form-horizontal" method="post" modelAttribute="reloadForm">
        <fieldset>
            <div class="form-group">
                <div class="col col-md-4">差分</div>
                <div class="col col-md-2">
                    <form:input id="diff" type="text" path="diffValue" />
                </div>
                <div class="col col-md-3"></div>
            </div>
            <div class="form-group">
                <div class="col col-md-4"></div>
                <div class="col col-md-2">
                    <button name="regist" type="submit" class="btn btn-primary">登録</button>
                </div>
                <div class="col col-md-3"></div>
            </div>
        </fieldset>
    </form:form>
</div>