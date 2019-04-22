<div id="wrapper">

  <h1 id="screenTitle">メール受信</h1>
  <br>
  <form:form method="POST" action="${pageContext.request.contextPath}/emal/receivemail"
    modelAttribute="emailReceivingForm" cssClass="form-horizontal">
    <fieldset>
      <legend>メール受信</legend>
      <div class="form-group">
        <div class="col col-md-2">host</div>
        <div class="col col-md-3">
          <form:input path="host" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">port</div>
        <div class="col col-md-3">
          <form:input path="port" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">user</div>
        <div class="col col-md-3">
          <form:input path="user" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">password</div>
        <div class="col col-md-3">
          <form:input path="password" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">identifier</div>
        <div class="col col-md-3">
          <form:input path="identifier" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <button id="receiveMail" name="receiveMail" class="btn btn-default">メール受信</button>
      </div>
    </fieldset>
  </form:form>
  <br>
  <c:if test="${mail != null}">
    <fieldset>
      <table class="table table-bordered table-condensed col-xs-5 col-sm-5 col-md-5 col-g-5">
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">From</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="from">
              ${f:h(mail.from)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Subject</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="subject">
              ${f:h(mail.subject)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">To</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="to"><c:forEach
                items="${mail.to}" var="v">${f:h(v)}<br>
              </c:forEach></span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Cc</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="cc"><c:forEach
                items="${mail.cc}" var="v">${f:h(v)}<br>
              </c:forEach></span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Reply-To</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="replyTo">
              ${f:h(mail.replyTo)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Date</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="date"><fmt:formatDate
                pattern="yyyy/MM/dd HH:mm:ss" value="${mail.sentDate}" /></span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Body</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="body">
              ${f:h(mail.body)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Content-Type</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="contentType">
              ${f:h(mail.contentType)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Attachment</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="attachment">
              ${f:h(mail.attachment)} </span></td>
        </tr>
        <tr>
          <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">Inline</th>
          <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span id="inline">
              ${f:h(mail.inline)} </span></td>
        </tr>
      </table>
    </fieldset>
  </c:if>

</div>
