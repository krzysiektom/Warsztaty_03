<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista użytkowników</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Lista użytkowników grupy: ${userGroup.name}</h2>
<c:if test="${users == null}">
    <h3>Brak użytkowników</h3>
</c:if>
<c:if test="${not empty users}">
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-active">
            <th scope="col">#</th>
            <th scope="col">Nazwa grupy</th>
            <th scope="col">Akcje</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}" varStatus="count">
            <tr>
                <th scope="row">${count.count}</th>
                <td>${user.username}</td>
                <td><a href="/UserServlet?userId=${user.id}">Szczegóły</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
