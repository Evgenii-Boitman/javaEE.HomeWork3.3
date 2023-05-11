<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 08.04.2023
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добро пожаловать в Банк "Речица"</title>
</head>
<body>
<%@include file="header.jsp" %>

<h1>${sessionScope.user.name}, рады Вас приветствовать!</h1>

<form action="${pageContext.request.contextPath}/cards" method="get">
    <label for="pageId">Введите номер страницы:</label><br>
    <input type="text" id="pageId" name="page"><br>

    <label for="limitId">Введите количество позиций на странице:</label><br>
    <input type="text" id="limitId" name="limit"><br>

    <label for="balancesId">Введите максимальный баланс:</label><br>
    <input type="text" id="balancesId" name="balances"><br>

    <input type="submit" value="Submit">
</form>

<c:forEach var="card" items="${requestScope.cards}">
    <h1>Владелец кредитной карточки: ${card.ownerName} ${card.ownerSurname} </h1>
    <h4>Дата окончания действия кредитной карточки: ${card.dateCard}</h4>
    <h4>Номер кредитной карточки: ${card.cardNumber}</h4>
    <h4>Баланс кредитной карточки: ${card.balance}</h4>
    <h4><a href=/cards?id=${card.id}>Подробнее</a></h4>
</c:forEach>


<%@include file="footer.jsp" %>
</body>
</html>
