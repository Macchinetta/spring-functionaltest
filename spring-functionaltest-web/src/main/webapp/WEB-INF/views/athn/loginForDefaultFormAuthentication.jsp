<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>
<div id="wrapper">
    <h3 id="screenTitle">ログインフォーム(DefaultFormAuthentication)</h3>
    <c:if test="${param.containsKey('error')}">
        <span id="loginError"> <t:messagesPanel messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" /> </span>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/athn/0102/001/authenticate" method="post">
        <table>
            <tr>
                <td><label for="username">User Name</label></td>
                <td><input type="text" id="username" name="username" /></td>
            </tr>
            <tr>
                <td><label for="password">Password</label></td>
                <td><input type="password" id="password" name="password" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><button id="login">Login</button></td>
            </tr>
        </table>
    </form:form>
</div>
