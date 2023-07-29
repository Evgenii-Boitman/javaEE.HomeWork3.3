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


<h1>${sessionScope.user.name}, рады Вас приветствовать!</h1>
<h1>${sessionScope.user.id} - Ваш ID.</h1>
<h1>
    <h4>Владелец банковского аккаунта: ${account.ownerNameAccount} ${account.ownerSurnameAccount} </h4>
    <h4>Номер банковского аккаунта: ${account.numberAccount}</h4>
    <h4>Баланс банковского аккаунта: ${account.accountBalance}</h4>
</h1>

<sec:authorize access="hasAuthority('USER')">
    <h4><a href=${pageContext.request.contextPath}/add-account>Создать новый аккаунт</a></h4>

    <form action="${pageContext.request.contextPath}/account/${account.id}/update" method="post">
        <label for="balanceAccountId">Баланс аккаунта:</label><br>
        <input type="number" id="balanceAccountId" name="pages" value="${account.accountBalance}"><br>
        <input type="submit" value="UPDATE">
    </form>

    <form action="${pageContext.request.contextPath}/account/${account.id}/delete" method="post">
        <button>DELETE</button>
    </form>
</sec:authorize>

<c:if test="${ sessionScope.SPRING_SECURITY_CONTEXT != null }">
    <h4><a href=${pageContext.request.contextPath}/account>Вернуться в аккаунт.</a></h4>
</c:if>


<%@include file="footer.jsp" %>
</body>
</html>
