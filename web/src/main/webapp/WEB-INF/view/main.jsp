<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 20.07.2023
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>О Банке "Речица":</title>
</head>
<body>
<%@include file="header.jsp" %>

<h4>Добро пожаловать в веб-приложение "Банк "Речица"".</h4>


<c:if test="${ sessionScope.SPRING_SECURITY_CONTEXT != null }">
    <h4><a href=${pageContext.request.contextPath}/account>Вернуться в аккаунт.</a></h4>
</c:if>


<%@include file="footer.jsp" %>
</body>
</html>
