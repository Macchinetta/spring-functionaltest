<div class="row">

  <h1 id="screenTitle">トランザクション処理</h1>

  <form:form modelAttribute="deliveryOrderProcessForm"
    action="${pageContext.request.contextPath}/dmly/deliveryorder/process">
    <fieldset>
      <legend>伝播モード</legend>
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <label class="form-label">Database ID : </label> <a id="databaseId" class="form-label">${f:h(databaseId)}</a>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">1</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequired" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">2</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequired" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">3</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; REQUIRED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredRequired" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">4</label>
          </div>
          <div class="col-md-1">
            <label class="form-label form-label">REQUIRED -&gt; REQUIRED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredRequired" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">5</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRES_NEW</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiresNew" class="btn btn-default">実行</form:button>
          </div>

          <div class="col-md-1">
            <label class="form-label">6</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRES_NEW (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiresNew" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">7</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; REQUIRES_NEW</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredRequiresNew" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">8</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; REQUIRES_NEW (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredRequiresNew" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">9</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED (rollback) -&gt; REQUIRES_NEW</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredRequiresNew2" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">10</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">SUPPORTS</label>
          </div>
          <div class="col-md-1">
            <form:button name="successSupports" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">11</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">SUPPORTS (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackSupports" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">12</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; SUPPORTS</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredSupports" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">13</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; SUPPORTS (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredSupports" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">14</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NOT_SUPPORTED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successNotSupported" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">15</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NOT_SUPPORTED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackNotSupported" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">16</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NOT_SUPPORTED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredNotSupported" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">17</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NOT_SUPPORTED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredNotSupported" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">18</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">MANDATORY (exception)</label>
          </div>
          <div class="col-md-1">
            <form:button name="exceptionMandatory" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">19</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; MANDATORY</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredMandatory" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">20</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; MANDATORY (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredMandatory" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">21</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NEVER</label>
          </div>
          <div class="col-md-1">
            <form:button name="successNever" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">22</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NEVER (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackNever" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">23</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NEVER (exception)</label>
          </div>
          <div class="col-md-1">
            <form:button name="exceptionRequiredNever" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">24</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NESTED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successNested" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">25</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NESTED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackNested" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">26</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NESTED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredNested" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">27</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NESTED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredNested" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">28</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED (rollback) -&gt; NESTED</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredNested2" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">29</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NESTED (rollback) success after
              rollback</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredNested3" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-10">
            <label class="form-label">javax.transaction.Transactional</label>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">30</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">31</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">32</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; REQUIRED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredRequiredJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">33</label>
          </div>
          <div class="col-md-1">
            <label class="form-label form-label">REQUIRED -&gt; REQUIRED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredRequiredJTA12" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">34</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRES_NEW</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiresNewJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">35</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRES_NEW (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiresNewJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">36</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; REQUIRES_NEW</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredRequiresNewJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">37</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; REQUIRES_NEW (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredRequiresNewJTA12" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">38</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED (rollback) -&gt; REQUIRES_NEW</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredRequiresNew2JTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">39</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">SUPPORTS</label>
          </div>
          <div class="col-md-1">
            <form:button name="successSupportsJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">40</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">SUPPORTS (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackSupportsJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">41</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; SUPPORTS</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredSupportsJTA12" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">

          <div class="col-md-1">
            <label class="form-label">42</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; SUPPORTS (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredSupportsJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">43</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NOT_SUPPORTED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successNotSupportedJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">44</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NOT_SUPPORTED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackNotSupportedJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">45</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NOT_SUPPORTED</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredNotSupportedJTA12" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">46</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NOT_SUPPORTED (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredNotSupportedJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">47</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">MANDATORY (exception)</label>
          </div>
          <div class="col-md-1">
            <form:button name="exceptionMandatoryJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">48</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; MANDATORY</label>
          </div>
          <div class="col-md-1">
            <form:button name="successRequiredMandatoryJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">49</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; MANDATORY (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackRequiredMandatoryJTA12" class="btn btn-default">実行</form:button>
          </div>
        </div>
        <br>

        <div class="row">
          <div class="col-md-1">
            <label class="form-label">50</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NEVER</label>
          </div>
          <div class="col-md-1">
            <form:button name="successNeverJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">51</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">NEVER (rollback)</label>
          </div>
          <div class="col-md-1">
            <form:button name="rollbackNeverJTA12" class="btn btn-default">実行</form:button>
          </div>
          <div class="col-md-1">
            <label class="form-label">52</label>
          </div>
          <div class="col-md-1">
            <label class="form-label">REQUIRED -&gt; NEVER (exception)</label>
          </div>
          <div class="col-md-1">
            <form:button name="exceptionRequiredNeverJTA12" class="btn btn-default">実行</form:button>
          </div>
        </div>

      </div>
    </fieldset>
  </form:form>

  <form:form modelAttribute="deliveryOrderProcessForm"
    action="${pageContext.request.contextPath}/dmly/deliveryorder/process">
    <fieldset>
      <legend>配達オーダ表示</legend>
      <div class="container">
        <div class="row">
          <div class="col-md-2">
            <label class="form-label form-label">配達No : </label>
          </div>
          <div class="col-md-2">
            <form:input path="displayDeliveryNo" class="form-control form-input" value="" />
          </div>
          <div class="col-md-7"></div>
          <div class="col-md-1">
            <form:button name="display" class="btn btn-default">表示</form:button>
          </div>
        </div>
      </div>
    </fieldset>
  </form:form>

</div>
