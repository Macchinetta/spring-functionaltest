<div id="wrapper">
  <H2>Authorization Remote</H2>
  <h3 id="loginWith">Login with Username and Password</h3>

  <c:if test="${not empty param.error}">
    <t:messagesPanel messagesType="error" messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
  </c:if>

  <form:form action="${pageContext.request.contextPath}/oth2/login">
    <table>
      <tr>
        <td><label for="username">User:</label></td>
        <td><input type="text" id="username" name="username" /></td>
      </tr>
      <tr>
        <td><label for="password">Password:</label></td>
        <td><input type="password" id="password" name="password" /></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input name="submit" type="submit" id="send" value="Login" /></td>
      </tr>
    </table>
  </form:form>
</div>