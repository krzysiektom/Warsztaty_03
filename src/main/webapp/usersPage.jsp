<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 26.01.19
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Zarządzanie użytkownikami</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Zarządzanie użytkownikami:</h2>
<nav class="nav justify-content-end">
    <a class="nav-link active" href="userAdd.jsp">Dodaj użytkownika</a>
</nav>
<c:if test="${ users == null || empty users}">
    <h3>Brak użytkowników</h3>
</c:if>
<c:if test="${not empty users}">
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-active">
            <th scope="col">#</th>
            <th scope="col">Imię i nazwisko</th>
            <th scope="col">Email</th>
            <th scope="col">Grupa</th>
            <th scope="col">Akcje</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}" varStatus="count">
            <tr>
                <th scope="row">${count.count}</th>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.userGroup.name}</td>
                <td>
                    <a href="userEdit.jsp?userId=${user.id}&username=${user.username}&email=${user.email}&password=${user.password}&groupId=${user.userGroup.id}&groupName=${user.userGroup.name}">Edytuj</a>
                    <a href="/UsersServlet?userId=${user.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
