<div id="wrapper">
    <h1 id="screenTitle">エラーメッセージの定義</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0303/001" modelAttribute="defineTypeMismatchMessageForm" class="form-horizontal">
        <div class="form-group">
            <form:label path="shortValue" class="col-sm-2 control-label">ShortValue</form:label>
            <div class="col-sm-10">
                <form:input path="shortValue" class="form-control" placeholder="ShortValue" />
                <form:errors path="shortValue" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="intValue" class="col-sm-2 control-label">IntValue</form:label>
            <div class="col-sm-10">
                <form:input path="intValue" class="form-control" placeholder="IntValue" />
                <form:errors path="intValue" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="longValue" class="col-sm-2 control-label">LongValue</form:label>
            <div class="col-sm-10">
                <form:input path="longValue" class="form-control" placeholder="LongValue" />
                <form:errors path="longValue" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="floatValue" class="col-sm-2 control-label">FloatValue</form:label>
            <div class="col-sm-10">
                <form:input path="floatValue" class="form-control" placeholder="FloatValue" />
                <form:errors path="floatValue" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="doubleValue" class="col-sm-2 control-label">DoubleValue</form:label>
            <div class="col-sm-10">
                <form:input path="doubleValue" class="form-control" placeholder="DoubleValue" />
                <form:errors path="doubleValue" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="shortObject" class="col-sm-2 control-label">ShortObject</form:label>
            <div class="col-sm-10">
                <form:input path="shortObject" class="form-control" placeholder="ShortObject" />
                <form:errors path="shortObject" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="integerObject" class="col-sm-2 control-label">IntegerObject</form:label>
            <div class="col-sm-10">
                <form:input path="integerObject" class="form-control" placeholder="IntegerObject" />
                <form:errors path="integerObject" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="longObject" class="col-sm-2 control-label">LongObject</form:label>
            <div class="col-sm-10">
                <form:input path="longObject" class="form-control" placeholder="LongObject" />
                <form:errors path="longObject" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="floatObject" class="col-sm-2 control-label">FloatObject</form:label>
            <div class="col-sm-10">
                <form:input path="floatObject" class="form-control" placeholder="FloatObject" />
                <form:errors path="floatObject" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="doubleObject" class="col-sm-2 control-label">DoubleObject</form:label>
            <div class="col-sm-10">
                <form:input path="doubleObject" class="form-control" placeholder="DoubleObject" />
                <form:errors path="doubleObject" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="date" class="col-sm-2 control-label">Date</form:label>
            <div class="col-sm-10">
                <form:input path="date" class="form-control" placeholder="Date" />
                <form:errors path="date" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="datetime" class="col-sm-2 control-label">DateTime</form:label>
            <div class="col-sm-10">
                <form:input path="datetime" class="form-control" placeholder="DateTime" />
                <form:errors path="datetime" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="localdate" class="col-sm-2 control-label">LocalDate</form:label>
            <div class="col-sm-10">
                <form:input path="localdate" class="form-control" placeholder="LocalDate" />
                <form:errors path="localdate" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="booleanValue" class="col-sm-2 control-label">BooleanValue</form:label>
            <div class="col-sm-10">
                <form:input path="booleanValue" class="form-control" placeholder="BooleanValue" />
                <form:errors path="booleanValue" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
            </div>
        </div>
    </form:form>
</div>
