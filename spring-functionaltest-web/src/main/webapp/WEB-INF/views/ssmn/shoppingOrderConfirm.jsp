<!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
<div id="wrapper">
  <h1 id="screenTitle">注文確認</h1>

  <spring:eval var="cart" expression="@cart" />

  <form action="./order" method="post">
    <table class="table table-striped table-bordered table-condensed">

      <thead>
        <tr>
          <th class="no">No</th>
          <th class="itemId">商品番号</th>
          <th class="itemName">商品名</th>
          <th class="quantity">個数</th>
          <th class="price">価格</th>
        </tr>
      </thead>

      <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="rowStatus">
        <tr>
          <td class="no">${rowStatus.count}</td>
          <td class="itemId">${f:h(cartItem.itemId)}</td>
          <td class="itemName">${f:h(cartItem.item.itemName)}</td>
          <td class="quantity">${f:h(cartItem.quantity)}</td>
          <td class="price"><fmt:formatNumber value="${cartItem.subtotalPrice}"
              pattern="###,###" /></td>
        </tr>
      </c:forEach>
      <tr>
        <td class="buttonArea" colspan="4"><a id="confirmCartLink" href="./cart"
          class="btn btn-default">カートへ戻る</a>
          <button class="btn btn-default">注文確定</button></td>
        <td class="totalPrice"><fmt:formatNumber value="${cart.totalPrice}" pattern="###,###" /></td>
      </tr>

    </table>
    <t:transaction />
    <input type="hidden" name="${f:h(_csrf.parameterName)}" value="${f:h(_csrf.token)}" />
  </form>
  <!-- セッション内Form確認 -->
  <div id="checkFormInSession" style="visibility: hidden">${f:h(sessionScope['scopedTarget.cart'].getClass().getName())}</div>
</div>