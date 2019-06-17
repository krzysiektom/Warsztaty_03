<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 26.01.19
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Zarządzanie zadaniami</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Zarządzanie zadaniami:</h2>
<nav class="nav justify-content-end">
    <a class="nav-link active" href="exerciseAdd.jsp">Dodaj zadanie</a>
</nav>
<c:if test="${ exercises == null || empty exercises}">
    <h3>Brak zadań</h3>
</c:if>
<c:if test="${not empty exercises}">
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-active">
            <th scope="col">#</th>
            <th scope="col">Nazwa zadania</th>
            <th scope="col">Opis zadania</th>
            <th scope="col">Akcje</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="exercise" items="${exercises}" varStatus="count">
            <tr>
                <th scope="row">${count.count}</th>
                <td>${exercise.title}</td>
                <td>${exercise.description}</td>
                <td>
                    <a href="exerciseEdit.jsp?exerciseId=${exercise.id}&title=${exercise.title}&description=${exercise.description}">Edytuj</a>
                    <a href="/ExercisesServlet?exerciseId=${exercise.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
