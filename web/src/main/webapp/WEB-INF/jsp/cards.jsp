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
<c:if test="${sessionScope.user.name == 'Admin'}">
    <h1>${sessionScope.user.name}, рады Вас приветствовать!</h1>

    <form action="${pageContext.request.contextPath}/cards" method="get">
        <label for="pageId">Введите номер страницы:</label><br>
        <input type="text" id="pageId" name="page"><br>

        <label for="limitId">Введите количество позиций на странице:</label><br>
        <input type="text" id="limitId" name="limit"><br>

        <label for="balanceId">Введите максимальный баланс:</label><br>
        <input type="text" id="balanceId" name="balance"><br>

        <input type="submit" value="Submit">
    </form>

    <c:forEach var="cards" items="${requestScope.cards}">
        <h1>Владелец кредитной карточки: ${cards.ownerName} ${cards.ownerSurname} </h1>
        <h4>Номер кредитной карточки: ${cards.cardNumber}</h4>
        <h4>Баланс кредитной карточки: ${cards.balance}</h4>
        <h4><a href=${pageContext.request.contextPath}/cards?id=${cards.id}>Подробнее</a></h4>
    </c:forEach>
</c:if>

<c:if test="${sessionScope.user.name != 'Admin'}">
    <h1>${sessionScope.user.name}, рады Вас приветствовать!</h1>
    <h1>
        <h4>Владелец кредитной карточки: ${card.ownerName} ${card.ownerSurname} </h4>
        <h4>Номер кредитной карточки: ${card.cardNumber}</h4>
        <h4>Баланс кредитной карточки: ${card.balance}</h4>
    </h1>
</c:if>

<%@include file="footer.jsp" %>
</body>
</html>
