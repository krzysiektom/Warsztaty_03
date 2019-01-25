<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 25.01.19
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Szczegóły użytkownika</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<nav class="nav">
    <a class="nav-link active" href="/GroupUserServlet?groupId=${user.userGroup.id}">Powrót</a>
</nav>
<h2>Szegóły użytkownika: ${user.username}</h2>
<a>Nazwa użytkownika: </a> <b>${user.username}</b><br>
<a>Email: ${user.email}</a>

<c:if test="${solutions == null}">
    <h3>Brak rozwiązań</h3>
</c:if>
<c:if test="${not empty solutions}">
    <h3>Dodane rozwiązania zadań:</h3>
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-active">
            <th scope="col">#</th>
            <th scope="col">Tytuł zadania</th>
            <th scope="col">Data dodania</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="solution" items="${solutions}" varStatus="count">
            <tr>
                <th scope="row">${count.count}</th>
                <td>${solution.exercise.title}</td>
                <td>${solution.created}</td>
                <td><a href="/UserServlet?userId=${user.id}">Szczegóły</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
