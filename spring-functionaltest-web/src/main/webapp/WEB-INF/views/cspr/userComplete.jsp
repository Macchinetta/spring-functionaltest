<div id="wrapper">
    <h3 id="screenTitle">ユーザ登録完了画面</h3>
    
    <div>ユーザ登録が正常に完了致しました。</div>
    <br/>
    <div>本登録は仮登録となります。そのためお手数ですが以下の手順に沿って本登録をお願い致します。</div>
    <div>登録メールアドレスに送信される「Spring ID登録確認」という題名のメールを参照し、認証用のURLを押下してください。</div>
    
    <br>
    <form:form action="${pageContext.request.contextPath}/cspr"
        cssClass="form-horizontal" method="get"
        modelAttribute="userForm">
            <div class="form-group col col-md-5">
                <form:button name="return" class="btn btn-default">戻る</form:button>
            </div>
    
    </form:form>
    
</div>