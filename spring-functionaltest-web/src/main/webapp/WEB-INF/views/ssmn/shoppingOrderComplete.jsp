<!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
<div id="wrapper">
    <h1 id="screenTitle">注文完了</h1>
    <t:messagesPanel />
    <div id="itemListLink">
        <a id="continueShoppingLink" href="./items" class="btn btn-success"><span class="glyphicon glyphicon-play"></span> ショッピングを続ける</a>
    </div>

    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <th class="orderIdLabel">注文番号</th>
            <td>${f:h(order.orderId)}</td>
        </tr>
    </table>
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

        <c:forEach var="orderItem" items="${order.orderItems}" varStatus="rowStatus">
            <tr>
                <td class="no">${rowStatus.count}</td>
                <td class="itemId">${f:h(orderItem.itemId)}</td>
                <td class="itemName">${f:h(orderItem.item.itemName)}</td>
                <td class="quantity">${f:h(orderItem.quantity)}</td>
                <td class="price"><fmt:formatNumber value="${orderItem.subtotalPrice}" pattern="###,###" /></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="buttonArea" colspan="4"></td>
            <td class="totalPrice"><fmt:formatNumber value="${order.totalPrice}" pattern="###,###" /></td>
        </tr>
    </table>
    <!-- セッション内Form確認 -->
    <div id="checkFormInSession" style="visibility: hidden">${f:h(sessionScope['scopedTarget.cart'].getCartItems().size())}</div>
</div>
