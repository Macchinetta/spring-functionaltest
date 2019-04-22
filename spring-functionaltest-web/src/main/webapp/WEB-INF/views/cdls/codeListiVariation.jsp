<div id="wrapper">

  <h1 id="screenTitle">コードリストの記述方法のバリエーション</h1>

  <div>CDLS0401001</div>
  <div>
    <c:if test="${!empty CL_I18N_PRICE_MAP_LOCALE}">
      <form:select id="cdls0401001" items="${CL_I18N_PRICE_MAP_LOCALE}" path="codeListForm.code" />
    </c:if>
  </div>
  <div>CDLS0401002</div>
  <div>
    <c:if test="${!empty CL_I18N_PRICE_MAP_LOCALE}">
      <form:select id="cdls0401002" items="${CL_I18N_PRICE_MAP_LOCALE}" path="codeListForm.code" />
    </c:if>
  </div>
  <div>CDLS0401003</div>
  <div>
    <c:if test="${!empty CL_I18N_PRICE_MAP_CODE}">
      <form:select id="cdls0401003" items="${CL_I18N_PRICE_MAP_CODE}" path="codeListForm.code" />
    </c:if>
  </div>
  <div>CDLS0401004</div>
  <div>
    <c:if test="${!empty CL_I18N_PRICE_MAP_CODE}">
      <form:select id="cdls0401004" items="${CL_I18N_PRICE_MAP_CODE}" path="codeListForm.code" />
    </c:if>
  </div>

  <div>CDLS0402001</div>
  <div>
    <c:if test="${!empty CL_MONTH_DES}">
      <form:select id="cdls0402001" items="${CL_MONTH_DES}" path="codeListForm.code" />
    </c:if>
  </div>
  <div>CDLS0402002</div>
  <div>
    <c:if test="${!empty CL_NUMBER_RANGE_INTERVAL}">
      <form:select id="cdls0402002" items="${CL_NUMBER_RANGE_INTERVAL}" path="codeListForm.code" />
    </c:if>
  </div>
</div>