<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 03.05.2023
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добро пожаловать в Банк Речица</title>
</head>
<body>
<%@include file="header.jsp" %>

<h1>${sessionScope.user.name}, рады Вас приветствовать!</h1>

<c:forEach var="account" items="${requestScope.cards}">
    <h1>Владелец банковского аккаунта: ${account.ownerNameAccount} ${account.ownerSurnameAccount} </h1>
    <h4>Баланс банковского аккаунта: ${account.balanceAccount}</h4>
    <h4><a href=/account?id=${account.id}>Подробнее</a></h4>
</c:forEach>

<%@include file="footer.jsp" %>
</body>
</html>
