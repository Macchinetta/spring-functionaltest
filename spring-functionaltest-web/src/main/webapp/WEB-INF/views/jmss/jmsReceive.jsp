<div id="wrapper">

  <h1 id="screenTitle">メッセージ受信</h1>

  <!-- 受信したデータを取得する場合 -->
  <c:if test="${not empty uniqueIdentifier}">
    <form:form method="POST" action="${pageContext.request.contextPath}/jmss/receivemessage"
      modelAttribute="jmsReceivingForm" cssClass="form-horizontal">
            ユニークキー：<span id="uniqueIdentifier"> ${f:h(uniqueIdentifier)} </span>


      <c:if test="${not empty receiveCount}">
             受信件数：<span id="receiveCount"> ${f:h(receiveCount)} </span>
      </c:if>

      <c:if test="${not empty priority}">
             JMSメッセージ優先度（priority）：<span id="receiveCount">
          ${f:h(priority)} </span>
      </c:if>

      <c:if test="${not empty deliveryMode}">
             JMSメッセージデリバリモード（deliveryMode）：<span id="receiveCount">
          ${f:h(deliveryMode)} </span>
      </c:if>

      <c:if test="${not empty receiveQueue}">
             受信したQueue（receiveQueue）：<span id="receiveQueue">
          ${f:h(receiveQueue)} </span>
      </c:if>

    </form:form>
  </c:if>

  <!-- 受信したデータが存在しなかった場合 -->
  <c:if test="${empty uniqueIdentifier}">
    <c:if test="${empty initFlg}">
           ユニークキー：<span id="uniqueIdentifier"> Not Received! </span>
    </c:if>
  </c:if>

  <!-- 受信したデータを取得しにいく -->
  <c:if test="${empty uniqueIdentifier}">
    <c:if test="${not empty initFlg}">
      <form:form method="POST" action="${pageContext.request.contextPath}/jmss/receivemessage"
        modelAttribute="jmsReceivingForm" cssClass="form-horizontal">
        <form:hidden path="jmsTodoId" />
        <form:hidden path="testCase" />
        <div class="form-group">
          <button id="receiveMessage" name="receiveMessage" class="btn btn-default">結果取得</button>
        </div>
        <%--              <form:input path="uniqueIdentifier"  /> --%>
      </form:form>
    </c:if>
  </c:if>
</div>