<div id="wrapper">
    <h1 id="screenTitle">期間の取得</h1>
    <br />
    <form:form action="${pageContext.request.contextPath}/dtop/checkterm" method="get" modelAttribute="checkTermForm">
        <legend>特定の期間(日付)に関する判定</legend>
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-condensed table-size">
                <thead>
                    <tr>
                        <th class="getColumn">取得項目</th>
                        <th colspan="3" class="inputColumn">入力項目</th>
                    </tr>
                </thead>
                <tr>
                    <td class="getColumn">期間</td>
                    <td class="dateColumn"><form:input path="targetTermFrom" cssClass="form-control input-sm" /></td>
                    <td class="tildaColumn">～</td>
                    <td class="dateColumn"><form:input path="targetTermTo" cssClass="form-control input-sm" /></td>
                </tr>
                <tr>
                    <td class="getColumn">チェック対象期間</td>
                    <td class="dateColumn"><form:input path="targetCheckTermFrom" cssClass="form-control input-sm" /></td>
                    <td class="tildaColumn">～</td>
                    <td class="dateColumn"><form:input path="targetCheckTermTo" cssClass="form-control input-sm" /></td>
                </tr>
                <tr>
                    <td class="getColumn">チェック対象日付</td>
                    <td class="dateColumn" colspan="3"><form:input path="targetCheckDate" cssClass="form-control input-sm" /></td>
                </tr>
            </table>
        </div>
        <form:button name="checkContainTermToDate" class="btn btn-default">期間内包括(対日付)判定</form:button>
        <form:button name="checkContainTermToTerm" class="btn btn-default">期間内包括(対期間)判定</form:button>
        <form:button name="checkAbutsTerm" class="btn btn-default">期間連続判定</form:button>
        <form:button name="getGapTerm" class="btn btn-default">差分期間取得</form:button>
        <form:button name="getOverlapTerm" class="btn btn-default">重複期間取得</form:button>
    </form:form>
    <br />
    <form:form action="${pageContext.request.contextPath}/dtop/checkterm" method="get" modelAttribute="checkTermForm" class="form-horizonal">
        <legend>Periodクラスを利用して、指定した値分、増加、減少</legend>
        <div class="form-group">
            <div class="row">
                <div class="col col-md-2">取得日付</div>
                <div class="col col-md-2">
                    <form:input path="targetIncDecDate" cssClass="form-control input-sm" />
                </div>
            </div>
            <div class="row">
                <div class="col col-md-2">増加数(月数)</div>
                <div class="col col-md-2">
                    <form:input path="targetIncreaseMonthNum" cssClass="form-control input-sm" />
                </div>
            </div>
            <div class="row">
                <div class="col col-md-2">減少数(月数)</div>
                <div class="col col-md-2">
                    <form:input path="targetDecreaseMonthNum" cssClass="form-control input-sm" />
                </div>
            </div>
            <div class="row">
                <div class="col col-md-2">増加数(日数)</div>
                <div class="col col-md-2">
                    <form:input path="targetIncreaseDayNum" cssClass="form-control input-sm" />
                </div>
            </div>
            <div class="row">
                <div class="col col-md-2">減少数(日数)</div>
                <div class="col col-md-2">
                    <form:input path="targetDecreaseDayNum" cssClass="form-control input-sm" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <form:button name="calcMonthDate" class="btn btn-default">増減月取得</form:button>
            <form:button name="calcMonthAndDayDate" class="btn btn-default">増減月日取得</form:button>
        </div>
    </form:form>
</div>
