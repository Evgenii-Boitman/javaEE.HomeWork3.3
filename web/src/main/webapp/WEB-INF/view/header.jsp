<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 08.04.2023
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Menu: <a href=/main>Главная</a> | <a href=/contacts>Контакты</a> | <a href=/bank>О банке</a> | <a href=/login>Войти</a> </h1>
<h1>Банк "Речица"</h1>
<%--<c:if test="${ sessionScope.user != null }">--%>
<%--    <form action="${pageContext.request.contextPath}/logout" method="get">--%>
<%--        <input type="submit" value="Logout">--%>
<%--    </form>--%>
<%--</c:if>--%>
<c:if test="${ sessionScope.user != null }">
<sec:authorize access="isAuthenticated()">
    <b>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</b>
    <form action="/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</sec:authorize>
</c:if>