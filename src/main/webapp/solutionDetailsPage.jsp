<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 25.01.19
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Szczegóły rozwiązania zadania</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<nav class="nav">
    <a class="nav-link active" href="/UserServlet?userId=${solution.user.id}">Powrót</a>
</nav>
<h2>Szczegóły rozwiązania zadania: ${solution.exercise.title}</h2>
<a>${solution.description}</a>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
