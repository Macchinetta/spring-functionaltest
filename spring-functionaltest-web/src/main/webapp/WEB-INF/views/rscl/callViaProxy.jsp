<div id="wrapper">
    <h1 id="screenTitle">RestCallViaProxy</h1>

    <form:form action="${pageContext.request.contextPath}/rscl/${testId}" class="form-horizontal">
        <div class="form-group">
            <label for="path" class="col-sm-1 control-label">パス：</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="path" name="path" placeholder="Path" />
            </div>
        </div>
        <button type="submit" id="send" class="btn btn-default">send</button>
    </form:form>
</div>
