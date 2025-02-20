<div id="wrapper">
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <th class="inputColumn">First Name</th>
            <td><span id="getFirstName">${f:h(resultBean.destinationFirstName)}</span></td>
        </tr>
        <tr>
            <th class="inputColumn">Last Name</th>
            <td><span id="getLastName">${f:h(resultBean.destinationLastName)}</span></td>
        </tr>
        <tr>
            <th class="inputColumn">Age</th>
            <td><span id="getAge">${f:h(resultBean.destinationAge)}</span></td>
        </tr>
    </table>
</div>
