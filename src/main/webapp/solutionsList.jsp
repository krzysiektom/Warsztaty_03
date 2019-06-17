<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Stona domowa</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<c:if test="${ solutions == null || empty solutions}">
    <h3>Brak rozwiązań</h3>
</c:if>
<c:if test="${not empty solutions}">
    <h2>Ostatnio dodane rozwiązania:</h2>
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-active">
            <th scope="col">#</th>
            <th scope="col">Tytuł zadania</th>
            <th scope="col">Autor rozwiązania</th>
            <th scope="col">Data dodania</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="solution" items="${solutions}" varStatus="count">
            <tr>
                <th scope="row">${count.count}</th>
                <td>${solution.exercise.title}</td>
                <td>${solution.user.username}</td>
                <td>${solution.created}</td>
                <td><a href="/ExerciseSolutionServlet?solutionId=${solution.id}">Szczegóły</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
