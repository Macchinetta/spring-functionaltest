<div id="wrapper">
    <h1 id="screenTitle">会員情報登録</h1>
    <br>
    <form:form cssClass="form-horizontal" method="post"
        modelAttribute="member">
        <legend>個人情報登録確認</legend>
        登録内容は以下の内容でよろしいでしょうか。
        <table
            class="table table-striped table-bordered table-condensed">
            <tr>
                <th>登録項目</th>
                <th>登録内容</th>
            </tr>
            <tr>
                <th>氏名（姓）</th>
                <td><span id="firstName">${f:h(member.firstName)}</span>
                <input type="hidden" name="firstName" value="佐藤">
                </td>
            </tr>
            <tr>
                <th>氏名（名）</th>
                <td><span id="lastName">${f:h(member.lastName)}</span>
                <input type="hidden" name="lastName" value="次郎">
                </td>
            </tr>
            <tr>
                <th>氏名カナ（姓）</th>
                <td><span id="firstNameKana">${f:h(member.firstNameKana)}</span>
                <input type="hidden" name="firstNameKana" value="サトウ"></td>
            </tr>
            <tr>
                <th>氏名カナ（名）</th>
                <td><span id="lastNameKana">${f:h(member.lastNameKana)}</span>
                <input type="hidden" name="lastNameKana" value="ジロウ"></td>
            </tr>
            <tr>
                <th>年齢</th>
                <td><span id="age">${f:h(member.age)}</span>
                <input type="hidden" name="age" value="90"></td>
            </tr>
            <tr>
                <th>性別</th>
                <td><span id="gender">${f:h(CL_GENDER[member.gender])}</span>
                <input type="hidden" name="gender" value="1"></td>
            </tr>
            <tr>
                <th>郵便番号</th>
                <td><span id="zipCode">${f:h(member.zipCode)}</span>
                <input type="hidden" name="zipCode" value="123-1234"></td>
            </tr>
            <tr>
                <th>都道府県</th>
                <td><span id="state">${f:h(member.state)}</span>
                <input type="hidden" name="state" value="神奈川県"></td>
            </tr>
            <tr>
                <th>市区町村</th>
                <td><span id="city">${f:h(member.city)}</span>
                <input type="hidden" name="city" value="西浦和"></td>
            </tr>
            <tr>
                <th>番地建物</th>
                <td><span id="address">${f:h(member.address)}</span>
                <input type="hidden" name="address" value="100-100-100"></td>
            </tr>
            <tr>
                <th>職業</th>
                <td><span id="occupation">${f:h(member.occupation)}</span>
                <input type="hidden" name="occupation" value="無職"></td>
            </tr>
            <tr>
                <th>メールアドレス</th>
                <td><span id="mailAddress">${f:h(member.mailAddress)}</span>
                <input type="hidden" name="mailAddress" value="a@a.co.jp"></td>
            </tr>
        </table>
        <div class="form-group">
            <form:button name="redo" class="btn btn-default">戻る</form:button>
            <form:button class="btn btn-default">登録</form:button>
        </div>
    </form:form>
</div>