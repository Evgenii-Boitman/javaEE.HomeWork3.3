<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 07.05.2023
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<%@include file="header.jsp" %>

<c:if test="${ param.error  == true}">
    Пользователь не был создан
</c:if>

<c:if test="${ param.error  == false}">
<h1>${sessionScope.user.name}, рады Вас приветствовать!</h1>
<h1>
  <h1>Владелец банковского аккаунта: ${account.ownerNameAccount} ${account.ownerSurnameAccount} </h1>
  <h4>Баланс банковского аккаунта: ${account.balanceAccount}</h4>
</h1>
</c:if>

<%@include file="footer.jsp" %>
</body>
</html>
