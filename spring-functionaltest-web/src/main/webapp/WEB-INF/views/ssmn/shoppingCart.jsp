<!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
<div id="wrapper">
  <h1 id="screenTitle">カート</h1>
  <div id="itemListLink">
    <a id="continueShoppingLink" href="./items" class="btn btn-success"><span
      class="glyphicon glyphicon-play"></span> ショッピングを続ける</a>
  </div>

  <spring:eval var="cart" expression="@cart" />

  <c:choose>
    <c:when test="${not empty cart}">
      <form:form action="./cart" method="post" modelAttribute="cartItemsForm">
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
            <spring:nestedPath path="cartItemForms[${rowStatus.index}]">
              <tr>
                <td class="no">${rowStatus.count}</td>
                <td class="itemId">${f:h(cartItem.itemId)}</td>
                <td class="itemName">${f:h(cartItem.item.itemName)}</td>
                <td class="quantity"><form:input path="quantity"
                    cssClass="quantity form-control" /> <form:errors cssClass="errorMessage"
                    path="quantity"></form:errors></td>
                <td class="price"><fmt:formatNumber value="${cartItem.subtotalPrice}"
                    pattern="###,###" /></td>
              </tr>
              <form:hidden path="itemId" />
            </spring:nestedPath>
          </c:forEach>
          <tr>
            <td class="buttonArea" colspan="4"><form:button name="order"
                class="btn btn-default">注文へ</form:button></td>
            <td class="totalPrice"><fmt:formatNumber value="${cart.totalPrice}"
                pattern="###,###" /></td>
          </tr>

        </table>

      </form:form>

    </c:when>

    <c:otherwise>
      <spring:message code="label.sf.ssmn.0002" />
    </c:otherwise>

  </c:choose>

</div>