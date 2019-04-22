<div id="wrapper">

  <h1 id="screenTitle">コードリストの記述方法のバリエーション</h1>

  <div>CDLS0401001</div>
  <div>
    <form:select id="cdls0401001" items="${CL_I18N_PRICE_MAP_LOCALE}" path="codeListForm.code" />
  </div>
  <div>CDLS0401002</div>
  <div>
    <form:select id="cdls0401002" items="${CL_I18N_PRICE_MAP_LOCALE}" path="codeListForm.code" />
  </div>
  <div>CDLS0402001</div>
  <div>
    <form:select id="cdls0402001" items="${CL_I18N_PRICE_MAP_CODE}" path="codeListForm.code" />
  </div>
  <div>CDLS0402002</div>
  <div>
    <form:select id="cdls0402002" items="${CL_I18N_PRICE_MAP_CODE}" path="codeListForm.code" />
  </div>
  <div>CDLS0403001</div>
  <div>
    <form:select id="cdls0403001" items="${CL_MONTH_DES}" path="codeListForm.code" />
  </div>
  <div>CDLS0403002</div>
  <div>
    <form:select id="cdls0403002" items="${CL_NUMBER_WITHIN_RANGE_INTERVAL}"
      path="codeListForm.code" />
  </div>
  <div>CDLS0403003</div>
  <div>
    <form:select id="cdls0403003" items="${CL_NUMBER_WITHOUT_RANGE_INTERVAL}"
      path="codeListForm.code" />
  </div>
</div>