<div id="wrapper">
  <p>
    <c:if test="${empty resultStartDate}">
            期間：<span id="getDateResult">なし</span>
    </c:if>
    <c:if test="${!(empty resultStartDate)}">
            開始日：<span id="getStartDateResult"><joda:format pattern="yyyy/MM/dd"
          value="${resultStartDate}" /></span>
      <br>
            終了日：<span id="getEndDateResult"><joda:format pattern="yyyy/MM/dd"
          value="${resultEndDate}" /></span>
    </c:if>
  </p>
</div>