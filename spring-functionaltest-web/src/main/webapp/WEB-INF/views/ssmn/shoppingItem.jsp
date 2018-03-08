<!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
<div id="wrapper">
  <h1 id="screenTitle">商品</h1>
  <div id="cartLink">
    <a id="confirmCartLink" href="../cart" class="btn btn-info"><span
      class="glyphicon glyphicon-shopping-cart"></span> カートを確認</a>
  </div>
  <div id="itemListLink">
    <a id="continueShoppingLink" href="../items" class="btn btn-success"><span
      class="glyphicon glyphicon-play"></span> ショッピングを続ける</a>
  </div>
  <form:form id="cartItemForm" action="./cart" cssClass="form-horizontal" method="post"
    modelAttribute="cartItemForm">
    <div class="form-group">
      <label class="col col-md-2 control-label">商品番号</label>
      <div class="col col-md-10">
        ${f:h(item.itemId)}
        <form:hidden path="itemId" />
      </div>
    </div>
    <div class="form-group">
      <label class="col col-md-2 control-label">商品名</label>
      <div class="col col-md-10">${f:h(item.itemName)}</div>
    </div>
    <div class="form-group">
      <label class="col col-md-2 control-label">価格</label>
      <div class="col col-md-10">${f:h(item.price)}</div>
    </div>
    <div class="form-group">
      <label class="col col-md-2 control-label">商品概要</label>
      <div class="col col-md-10">${f:h(item.itemName)}</div>
    </div>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:button name="add" class="btn btn-default">カートへ追加</form:button>
        <span id="resultMessage" class="text-success"></span>
      </div>
    </div>
  </form:form>
</div>
