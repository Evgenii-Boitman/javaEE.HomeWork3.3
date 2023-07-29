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

<%--  <h1>${sessionScope.user.id} - Ваш ID.</h1>--%>
<%--  <c:set var = "account" value="${requestScope.accounts}"/>--%>

  <label for="userId">Ваш ID:</label><br>
  <input type="text" id="userId" name="userId" value=${sessionScope.user.id}><br>

  <label for="userId">ID Вашего User:</label><br>
  <input type="text" id="userId" name="user_id" value=${sessionScope.user.id}><br>

  <label for="nameId">Укажите Ваше имя:</label><br>
  <input type="text" id="nameId" name="name" value=${sessionScope.user.name}><br>

  <label for="surnameId">Укажите Вашу фамилию:</label><br>
  <input type="text" id="surnameId" name="surname" value=${sessionScope.user.surname}><br>

  <label for="genderId">Укажите Ваш пол:</label><br>

  <select name="gender" id="genderId">
    <option value="MALE">MALE</option>
    <option value="FEMALE">FEMALE</option>
  </select><br>

  <label for="numberAccountId">Укажите номер аккаунта:</label><br>
  <input type="number" id="numberAccountId" name="numberAccount" value=${sessionScope.user.id}><br>

  <label for="accountBalanceId">Укажите баланс аккаунта:</label><br>
  <input type="number" id="accountBalanceId" name="accountBalance"><br>

  <br>

  <input type="submit" value="Submit">

</form>

<c:if test="${ sessionScope.user != null }">
  <h4><a href=${pageContext.request.contextPath}/accounts>Войти в аккаунт.</a></h4>
  </form>
</c:if>

<%@ include file="footer.jsp" %>
</body>
</html>