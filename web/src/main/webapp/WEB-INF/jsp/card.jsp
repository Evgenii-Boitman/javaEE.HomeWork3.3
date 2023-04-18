<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 10.04.2023
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Банк-Речица</title>
    <title>${card.cardNumber}</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>${sessionScope.user.name}, рады Вас приветствовать!</h1>
<h1>
    <h1>Владелец кредитной карточки: ${card.ownerName}</h1>
    <h4>Дата окончания действия кредитной карточки: ${card.dateCard}</h4>
    <h4>Номер кредитной карточки: ${card.cardNumber}</h4>
    <h4>Баланс кредитной карточки: ${card.balance}</h4>
</h1>
<%@include file="footer.jsp" %>
</body>
</html>
