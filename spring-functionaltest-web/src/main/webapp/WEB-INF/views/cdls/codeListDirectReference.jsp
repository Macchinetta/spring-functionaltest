<div id="wrapper">

  <h1 id="screenTitle">テンプレートエンジンにSpEL式を記述して、コードリストBeanを参照できること</h1>

  <div>CDLS0501001</div>
  <div>
    <spring:eval var="statuses" expression="@CL_ORDERSTATUS.asMap()" />
    <form:select id="cdls0501001" items="${statuses}" path="codeListForm.code" />
  </div>

  <div>CDLS0501002</div>
  <div>
    <spring:eval var="prices"
      expression="@CL_I18N_PRICE.asMap(requestLocale).isEmpty() ? @CL_I18N_PRICE.asMap(fallBackLocale) : @CL_I18N_PRICE.asMap(requestLocale)" />
    <form:select id="cdls0501002" items="${prices}" path="codeListForm.code" />
  </div>

  <div>CDLS0501003</div>
  <div>
    <spring:eval var="prices"
      expression="@CL_I18N_PRICE.asMap(requestLocale).isEmpty() ? @CL_I18N_PRICE.asMap(fallBackLocale) : @CL_I18N_PRICE.asMap(requestLocale)" />
    <form:select id="cdls0501003" items="${prices}" path="codeListForm.code" />
  </div>

  <div>CDLS0501004</div>
  <div>
    <spring:eval var="prices"
      expression="@CL_I18N_PRICE.asMap(requestLocale).isEmpty() ? @CL_I18N_PRICE.asMap(fallBackLocale) : @CL_I18N_PRICE.asMap(requestLocale)" />
    <form:select id="cdls0501004" items="${prices}" path="codeListForm.code" />
  </div>
</div>