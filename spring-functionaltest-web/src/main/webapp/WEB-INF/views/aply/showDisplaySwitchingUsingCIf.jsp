<div id="wrapper">

  <h1 id="screenTitle">モデルに格納された値により条件分岐して表示切替ができることの確認（&lt;c:if&gt;形式）</h1>

  <c:if test="${jspForm.conditions == 'complete'}">
    <span id="message1">「complete」と入力された場合の分岐</span>
  </c:if>
  <c:if test="${jspForm.conditions != 'complete'}">
    <span id="message1">「complete」と入力されなかった場合の分岐</span>
  </c:if>
</div>
