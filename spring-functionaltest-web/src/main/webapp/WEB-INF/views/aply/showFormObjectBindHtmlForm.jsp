<div id="wrapper">

    <h1 id="screenTitle">フォームオブジェクトからHTMLフォームへバインドできることの確認</h1>

    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <th class="inputColumn">firstName</th>
            <td><span id="getFirstName">${f:h(jspForm.firstName)}</span></td>
        </tr>
        <tr>
            <th class="inputColumn">lastName</th>
            <td><span id="getLastName">${f:h(jspForm.lastName)}</span></td>
        </tr>
    </table>
</div>
