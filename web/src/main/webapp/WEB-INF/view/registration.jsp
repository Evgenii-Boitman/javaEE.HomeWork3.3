<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 11.04.2023
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Регистрация нового пользователя</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/registration" method="post">
  <label for="nameId">Укажите Ваше имя:</label><br>
  <input type="text" id="nameId" name="name"><br>

  <label for="surnameId">Укажите Вашу фамилию:</label><br>
  <input type="text" id="surnameId" name="surname"><br>

  <label for="emailId">Укажите Ваш Email:</label><br>
  <input type="email" id="emailId" name="email"><br>

  <label for="passwordId">Укажите Ваш пароль:</label><br>
  <input type="password" id="passwordId" name="password"><br>

  <label for="genderId">Укажите Ваш пол:</label><br>

  <select name="gender" id="genderId">
    <option value="MALE">MALE</option>
    <option value="FEMALE">FEMALE</option>
  </select><br>

  <label for="contactId">Укажите Ваш номер телефона:</label><br>
  <input type="tel" id="contactId" name="contact"><br>

<%--  <label for="rolesId">Ваша роль:</label><br>--%>

<%--  <select name="roles" id="rolesId">--%>
<%--    <option value="USER">USER</option>--%>
<%--  </select><br>--%>
  <br>

  <input type="submit" value="Submit">
</form>

<%@ include file="footer.jsp" %>
</body>
</html>
