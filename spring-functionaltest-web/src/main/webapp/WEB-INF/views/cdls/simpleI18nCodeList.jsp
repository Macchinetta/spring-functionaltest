<div id="wrapper">

  <h1 id="screenTitle">SimpleI18nCodeListの読み込み</h1>

  <div>CDLS0105001</div>
  <div>
    <form:select id="cdls0105001" items="${CL_I18N_PRICE}" path="codeListForm.code" />
  </div>
  <div>CDLS0105002</div>
  <div>
    <form:select id="cdls0105002" items="${CL_I18N_PRICE}" path="codeListForm.code" />
  </div>
  <div>CDLS0105003</div>
  <div>
    <form:form modelAttribute="clI18nPriceForm" method="post"
      action="${pageContext.request.contextPath}/cdls/0105/003">
      <form:select id="cdls0105003" items="${CL_I18N_PRICE}" path="id" />
      <form:errors path="id" />
      <button id="cdls0105003Submit" type="submit" name="post">Submit</button>
    </form:form>
    <div id="cdls0105003Result">${f:h(i18nPriceCodeListValue)}</div>
  </div>
  <div>CDLS0105004</div>
  <div>
    <form:form modelAttribute="clI18nPriceForm" method="post"
      action="${pageContext.request.contextPath}/cdls/0105/004">
      <form:select id="cdls0105004" items="${CL_I18N_PRICE}" path="id" />
      <form:errors path="id" />
      <button id="cdls0105004Submit" type="submit" name="post">Submit</button>
    </form:form>
    <div id="cdls0105004Result">${f:h(i18nPriceCodeListValue)}</div>
  </div>
  <div>CDLS0105005</div>
  <div>
    <form:select id="cdls0105005" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
  </div>
  <div>CDLS0105006</div>
  <div>
    <form:select id="cdls0105006" items="${CL_I18N_DB_PRICE}" path="codeListForm.code" />
  </div>
  <div>CDLS0105007</div>
  <div>
    <form:form modelAttribute="clI18nDBPriceForm" method="post"
      action="${pageContext.request.contextPath}/cdls/0105/007">
      <form:select id="cdls0105007" items="${CL_I18N_DB_PRICE}" path="id" />
      <form:errors path="id" />
      <button id="cdls0105007Submit" type="submit" name="post">Submit</button>
    </form:form>
    <div id="cdls0105007Result">${f:h(i18nDBPriceCodeListValue)}</div>
  </div>
  <div>CDLS0105008</div>
  <div>
    <form:form modelAttribute="clI18nDBPriceForm" method="post"
      action="${pageContext.request.contextPath}/cdls/0105/008">
      <form:select id="cdls0105008" items="${CL_I18N_DB_PRICE}" path="id" />
      <form:errors path="id" />
      <button id="cdls0105008Submit" type="submit" name="post">Submit</button>
    </form:form>
    <div id="cdls0105008Result">${f:h(i18nDBPriceCodeListValue)}</div>
  </div>
</div>
