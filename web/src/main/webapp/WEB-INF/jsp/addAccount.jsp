<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 07.05.2023
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Регистрация нового банковского аккаунта</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/addAccount" method="post">
  <label for="nameId">Укажите Ваше имя:</label><br>
  <input type="text" id="nameId" name="name"><br>

  <label for="surnameId">Укажите Вашу фамилию:</label><br>
  <input type="text" id="surnameId" name="surname"><br>

  <label for="accountNumber">Укажите номер аккаунта:</label><br>
  <input type="text" id="accountNumber" name="accountNumber"><br>

  <label for="balanceAccount">Укажите баланс аккаунта:</label><br>
  <input type="text" id="balanceAccount" name="balanceAccount"><br>

  <%--  <label for="genderId">Укажите Ваш пол (MALE или FEMALE):</label><br>--%>
  <%--  <input type="gender" id="genderId" name="gender"><br>c--%>

  <%--  <label for="roleId">Укажите Вашу роль (ADMIN или USER):</label><br>--%>
  <%--  <input type="role" id="roleId" name="role"><br>--%>

  <br>


  <input type="submit" value="Submit">

</form>

<%@ include file="footer.jsp" %>
</body>
</html>