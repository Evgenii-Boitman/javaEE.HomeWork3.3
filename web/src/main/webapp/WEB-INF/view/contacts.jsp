<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 30.04.2023
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Контакты сотрудников "Банк-Речица":</title>
</head>
<body>
<%@include file="header.jsp" %>

<h4>Контак-центр: +375 2340 5-20-70 </h4>
<h4>Приемная директора: +375 2340 5-20-71 </h4>

<c:if test="${ sessionScope.SPRING_SECURITY_CONTEXT != null }">
    <h4><a href=${pageContext.request.contextPath}/account>Вернуться в аккаунт.</a></h4>
</c:if>

<%@include file="footer.jsp" %>
</body>
</html>


