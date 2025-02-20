<div id="wrapper">
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <th class="inputColumn">First Name</th>
            <td><span id="getFirstName">${f:h(resultBean.firstName)}</span></td>
        </tr>
        <tr>
            <th class="inputColumn">Last Name</th>
            <td><span id="getLastName">${f:h(resultBean.lastName)}</span></td>
        </tr>
        <tr>
            <th class="inputColumn">Age</th>
            <td><span id="getAge">${f:h(resultBean.age)}</span></td>
        </tr>
        <tr>
            <th class="inputColumn">Birth Date</th>
            <td><span id="getBirthDate">${f:h(resultBean.birthDate)}</span></td>
        </tr>
    </table>
</div>
