<div id="wrapper">
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <th>年</th>
            <td><span id="getDateResultYear">${f:h(resultDate.year)}</span></td>
        </tr>
        <tr>
            <th>月</th>
            <td><span id="getDateResultMonth">${f:h(resultDate.monthOfYear)}</span></td>
        </tr>
        <tr>
            <th>日</th>
            <td><span id="getDateResultDay">${f:h(resultDate.dayOfMonth)}</span></td>
        </tr>
        <tr>
            <th>週</th>
            <td><span id="getDateResultWeek">${f:h(resultDate.dayOfWeek)}</span></td>
        </tr>
        <tr>
            <th>時</th>
            <td><span id="getDateResultHour">${f:h(resultDate.hourOfDay)}</span></td>
        </tr>
        <tr>
            <th>分</th>
            <td><span id="getDateResultMinute">${f:h(resultDate.minuteOfHour)}</span></td>
        </tr>
        <tr>
            <th>秒</th>
            <td><span id="getDateResultSecond">${f:h(resultDate.secondOfMinute)}</span></td>
        </tr>
        <tr>
            <th>ミリ秒</th>
            <td><span id="getDateResultMillis">${f:h(resultDate.millisOfSecond)}</span></td>
        </tr>
    </table>
</div>