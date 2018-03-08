<div id="wrapper">
  <h1 id="screenTitle">日付操作</h1>
  <br>

  <form:form action="${pageContext.request.contextPath}/dtop/calcdate" method="get"
    modelAttribute="dateManipulationForm" class="form-horizonal">
    <legend>指定した値分、増加、減少</legend>
    <div class="form-group">
      <div class="row">
        <div class="col col-md-2">取得日付</div>
        <div class="col col-md-2">
          <form:input path="targetIncDecDate" cssClass="form-control input-sm" />
        </div>
      </div>
      <div class="row">
        <div class="col col-md-2">増加数</div>
        <div class="col col-md-2">
          <form:input path="targetIncreaseNum" cssClass="form-control input-sm" />
        </div>
      </div>
      <div class="row">
        <div class="col col-md-2">減少数</div>
        <div class="col col-md-2">
          <form:input path="targetDecreaseNum" cssClass="form-control input-sm" />
        </div>
      </div>
    </div>
    <div class="form-group">
      <form:button name="calcDayDate" class="btn btn-default">増減日取得</form:button>
      <form:button name="calcMonthDate" class="btn btn-default">増減月取得</form:button>
      <form:button name="calcYearDate" class="btn btn-default">増減年取得</form:button>
    </div>
  </form:form>

  <form:form action="${pageContext.request.contextPath}/dtop/calcdate" method="get"
    modelAttribute="dateManipulationForm" class="form-horizonal">
    <legend>特定日付を基準に、期間の最初と最後の日付を取得</legend>
    <div class="form-group">
      <div class="row">
        <div class="col col-md-2">取得日付</div>
        <div class="col col-md-2">
          <form:input path="targetStartEndDate" cssClass="form-control input-sm" />
        </div>
      </div>
    </div>
    <div class="form-group">
      <form:button name="calcMonthStartEndDate" class="btn btn-default">月初月末取得</form:button>
      <form:button name="calcWeekStartEndDate" class="btn btn-default">週初週末取得</form:button>
    </div>
  </form:form>
</div>