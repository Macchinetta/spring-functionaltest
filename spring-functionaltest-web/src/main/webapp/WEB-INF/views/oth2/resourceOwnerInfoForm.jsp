<div id="wrapper">

	<H2>Client</H2>
    <h3 id="loginWith">Enter ResourceOwner Name and Password</h3>

    <form:form modelAttribute="resourceOwner" action="${pageContext.request.contextPath}/oth2/client/01/04/index">
        <table>
            <tr>
                <td><label for="username">User:</label></td>
                <td><form:input type="text" id="username"
                    path="username" /></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><form:input type="password" id="password"
                    path="password" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><form:button name="submit" type="submit" id ="send" value="Input">Input</form:button></td>
            </tr>
        </table>
    </form:form>

</div>