<div id="wrapper">

  <div id="xMLHttpRequestResult" class="hidden alert">
    <ul>
      <li id="returnStatus"></li>
      <li id="contentType"></li>
    </ul>
  </div>
  <div id="resultMessage" class="hidden alert"></div>

  <div class="text-center">
    <form id="messageBoardForm">
      <spring:nestedPath path="messageBoardForm">
        <fieldset>
          <legend>Spring 掲示板</legend>

          <form:label path="comment" cssClass="space">コメント</form:label>
          <form:input path="comment" cssClass="space write-comment-field form-control inline" />
          <form:button id="writeBtn" class="btn btn-default"
            onclick="return ajax.write('${f:hjs(path)}')">書き込み</form:button>
        </fieldset>
      </spring:nestedPath>
    </form>
  </div>
  <br>

  <div class="text-center">ご自由にコメントしてください!!</div>
  <div id="result">
    <c:forEach var="messages" items="${messageResults}" varStatus="rowStatus">
      <ul>
        <li>${f:h(messages.comment)}</li>
      </ul>
    </c:forEach>
  </div>
</div>