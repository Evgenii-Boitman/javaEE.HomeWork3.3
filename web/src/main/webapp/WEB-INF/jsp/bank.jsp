<%--
  Created by IntelliJ IDEA.
  User: Eugenio
  Date: 07.05.2023
  Time: 20:25
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

<h4>Банк "Речица" был основан в 2023 году.</h4>

<c:if test="${ sessionScope.user != null }">
  <h4><a href=${pageContext.request.contextPath}/accounts>Войти в аккаунт.</a></h4>
  </form>
</c:if>


<%@include file="footer.jsp" %>
</body>
</html>
