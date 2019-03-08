<div id="wrapper">
  <h1 id="screenTitle">単項目チェック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/vldt/0101/006"
    modelAttribute="variousSimpleValidationForm" class="form-horizontal">

    <div class="form-group">
      <form:label path="notnull" class="col-sm-2 control-label">NotNull</form:label>
      <div class="col-sm-10">
        <%--                 <form:input path="notnull" class="form-control" --%>
        <%--                     placeholder="NotNull" /> --%>
        <form:errors path="notnull" class="text-danger" />
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
      <form:label path="past" class="col-sm-2 control-label">Past</form:label>
      <div class="col-sm-10">
        <form:input path="past" class="form-control" placeholder="Past" />
        <form:errors path="past" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="creditcardnumber" class="col-sm-2 control-label">CreditCardNumber</form:label>
      <div class="col-sm-10">
        <form:input path="creditcardnumber" class="form-control" placeholder="CreditCardNumber" />
        <form:errors path="creditcardnumber" class="text-danger" />
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
      <form:label path="url" class="col-sm-2 control-label">Url</form:label>
      <div class="col-sm-10">
        <form:input path="url" class="form-control" placeholder="Url" />
        <form:errors path="url" class="text-danger" />
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
      <form:label path="notempty" class="col-sm-2 control-label">NotEmpty</form:label>
      <div class="col-sm-10">
        <form:input path="notempty" class="form-control" placeholder="NotEmpty" />
        <form:errors path="notempty" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
      </div>
    </div>

  </form:form>

</div>
