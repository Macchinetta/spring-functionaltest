<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.vldt.variousSimpleValidationView" />
<c:set var="functionId" value="vldt" />

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<sec:csrfMetaTags />
<title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message
    code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
<script type="text/javascript"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<div id="wrapper">
  <h1 id="screenTitle">単項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0101/006"
    modelAttribute="variousSimpleValidationBVForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="notnull" class="col-sm-2 control-label">NotNull</form:label>
      <div class="col-sm-10">
        <form:input path="notnull" class="form-control" placeholder="NotNull" />
        <form:errors path="notnull" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="notempty" class="col-sm-2 control-label">NotEmpty</form:label>
      <div class="col-sm-10">
        <form:input path="notempty" class="form-control" placeholder="NotEmpty" />
        <form:errors path="notempty" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="notblank" class="col-sm-2 control-label">NotBlank</form:label>
      <div class="col-sm-10">
        <form:input path="notblank" class="form-control" placeholder="NotBlank" />
        <form:errors path="notblank" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="nullvalue" class="col-sm-2 control-label">NullValue</form:label>
      <div class="col-sm-10">
        <form:input path="nullvalue" class="form-control" placeholder="NullValue" />
        <form:errors path="nullvalue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="pattern" class="col-sm-2 control-label">Pattern</form:label>
      <div class="col-sm-10">
        <form:input path="pattern" class="form-control" placeholder="Pattern" />
        <form:errors path="pattern" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="min" class="col-sm-2 control-label">Min</form:label>
      <div class="col-sm-10">
        <form:input path="min" class="form-control" placeholder="Min" />
        <form:errors path="min" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="max" class="col-sm-2 control-label">Max</form:label>
      <div class="col-sm-10">
        <form:input path="max" class="form-control" placeholder="Max" />
        <form:errors path="max" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="decimalmin" class="col-sm-2 control-label">DecimalMin</form:label>
      <div class="col-sm-10">
        <form:input path="decimalmin" class="form-control" placeholder="DecimalMin" />
        <form:errors path="decimalmin" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="decimalmax" class="col-sm-2 control-label">DecimalMax</form:label>
      <div class="col-sm-10">
        <form:input path="decimalmax" class="form-control" placeholder="DecimalMax" />
        <form:errors path="decimalmax" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="positive" class="col-sm-2 control-label">Positive</form:label>
      <div class="col-sm-10">
        <form:input path="positive" class="form-control" placeholder="Positive" />
        <form:errors path="positive" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="positiveorzero" class="col-sm-2 control-label">PositiveOrZero</form:label>
      <div class="col-sm-10">
        <form:input path="positiveorzero" class="form-control" placeholder="PositiveOrZero" />
        <form:errors path="positiveorzero" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="negative" class="col-sm-2 control-label">Negative</form:label>
      <div class="col-sm-10">
        <form:input path="negative" class="form-control" placeholder="Negative" />
        <form:errors path="negative" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="negativeorzero" class="col-sm-2 control-label">NegativeOrZero</form:label>
      <div class="col-sm-10">
        <form:input path="negativeorzero" class="form-control" placeholder="NegativeOrZero" />
        <form:errors path="negativeorzero" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="size" class="col-sm-2 control-label">Size</form:label>
      <div class="col-sm-10">
        <form:input path="size" class="form-control" placeholder="Size" />
        <form:errors path="size" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="digits" class="col-sm-2 control-label">Digits</form:label>
      <div class="col-sm-10">
        <form:input path="digits" class="form-control" placeholder="Digits" />
        <form:errors path="digits" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="truevalue" class="col-sm-2 control-label">TrueValue</form:label>
      <div class="col-sm-10">
        <form:select path="truevalue" class="form-control">
          <form:option value="true">True</form:option>
          <form:option value="false">False</form:option>
        </form:select>
        <form:errors path="truevalue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="falsevalue" class="col-sm-2 control-label">FalseValue</form:label>
      <div class="col-sm-10">
        <form:select path="falsevalue" class="form-control">
          <form:option value="true">True</form:option>
          <form:option value="false">False</form:option>
        </form:select>
        <form:errors path="falsevalue" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="future" class="col-sm-2 control-label">Future</form:label>
      <div class="col-sm-10">
        <form:input path="future" class="form-control" placeholder="Future" />
        <form:errors path="future" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="futureorpresent" class="col-sm-2 control-label">FutureOrPresent</form:label>
      <div class="col-sm-10">
        <form:input path="futureorpresent" class="form-control" placeholder="FutureOrPresent" />
        <form:errors path="futureorpresent" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="past" class="col-sm-2 control-label">Past</form:label>
      <div class="col-sm-10">
        <form:input path="past" class="form-control" placeholder="Past" />
        <form:errors path="past" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="pastorpresent" class="col-sm-2 control-label">PastOrPresent</form:label>
      <div class="col-sm-10">
        <form:input path="pastorpresent" class="form-control" placeholder="PastOrPresent" />
        <form:errors path="pastorpresent" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="email" class="col-sm-2 control-label">Email</form:label>
      <div class="col-sm-10">
        <form:input path="email" class="form-control" placeholder="Email" />
        <form:errors path="email" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validateBeanValidation" name="validateBeanValidation" class="btn btn-default">validate(Bean Validation)</form:button>
      </div>
    </div>

  </form:form>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0101/006"
    modelAttribute="variousSimpleValidationHVForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="creditcardnumber" class="col-sm-2 control-label">CreditCardNumber</form:label>
      <div class="col-sm-10">
        <form:input path="creditcardnumber" class="form-control" placeholder="CreditCardNumber" />
        <form:errors path="creditcardnumber" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="isbn" class="col-sm-2 control-label">ISBN</form:label>
      <div class="col-sm-10">
        <form:input path="isbn" class="form-control" placeholder="ISBN" />
        <form:errors path="isbn" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="url" class="col-sm-2 control-label">Url</form:label>
      <div class="col-sm-10">
        <form:input path="url" class="form-control" placeholder="Url" />
        <form:errors path="url" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="notemptyHV" class="col-sm-2 control-label">NotEmpty</form:label>
      <div class="col-sm-10">
        <form:input path="notemptyHV" class="form-control" placeholder="NotEmpty (Hibernate Validator)" />
        <form:errors path="notemptyHV" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="notblankHV" class="col-sm-2 control-label">NotBlank</form:label>
      <div class="col-sm-10">
        <form:input path="notblankHV" class="form-control" placeholder="NotBlank (Hibernate Validator)" />
        <form:errors path="notblankHV" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="emailHV" class="col-sm-2 control-label">Email</form:label>
      <div class="col-sm-10">
        <form:input path="emailHV" class="form-control" placeholder="Email (Hibernate Validator)" />
        <form:errors path="emailHV" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validateHibernateValidator" name="validateHibernateValidator" class="btn btn-default">validate(Hibernate Validator)</form:button>
      </div>
    </div>

  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>