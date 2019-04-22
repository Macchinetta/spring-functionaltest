<div id="wrapper">

  <h1 id="screenTitle">モデルに格納された値により条件分岐して表示切替ができることの確認（&lt;c:choose&gt;形式）</h1>

  <c:choose>
    <c:when test="${jspForm.conditions == 'gold'}">
      <span id="message1">「gold」と入力された場合の分岐</span>
    </c:when>
    <c:when test="${jspForm.conditions == 'silver'}">
      <span id="message1">「silver」と入力された場合の分岐</span>
    </c:when>
    <c:otherwise>
      <span id="message1">「gold」と「silver」以外の入力の場合の分岐</span>
    </c:otherwise>
  </c:choose>
</div>
