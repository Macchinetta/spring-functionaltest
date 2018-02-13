<html>
<head>
<title>Insert title here</title>
</head>
<body>
  <h2>Codelist</h2>
  <br>
  <form:form action="${pageContext.request.contextPath}/vldt/0501/001" method="post"
    modelAttribute="existInCheckDefaultForm">
    <table>
      <tr>
        <td><span id="errors"><form:errors path="key" style="color:red" /></span></td>
      </tr>
    </table>
    <table>
      <tr>
        <td>Input a key to check if it is exists in CodeList:</td>
        <td><form:input path="key" /></td>
      </tr>
      <tr>
        <td><input id="btn1" class="mainbtn" style="width: 200px;" type="submit"
          value="Confirm" name="existInCheckString" />
        <td>
        <td></td>
      </tr>
    </table>
  </form:form>
</body>
</html>