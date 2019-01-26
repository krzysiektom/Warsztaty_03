<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 26.01.19
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Zarządzanie grupami</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Zarządzanie grupami:</h2>
<nav class="nav justify-content-end">
    <a class="nav-link active" href="groupAdd.jsp">Dodaj grupę</a>
</nav>
<c:if test="${ userGroups == null || empty userGroups}">
    <h3>Brak grup</h3>
</c:if>
<c:if test="${not empty userGroups}">
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-active">
            <th scope="col">#</th>
            <th scope="col">Nazwa grupy</th>
            <th scope="col">Akcje</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="userGroup" items="${userGroups}" varStatus="count">
            <tr>
                <th scope="row">${count.count}</th>
                <td>${userGroup.name}</td>
                <td>
                    <a href="groupEdit.jsp?groupId=${userGroup.id}&groupName=${userGroup.name}">Edytuj</a>
                    <a href="/GroupsServlet?groupId=${userGroup.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@include file="fragments/footer.jsp" %>
</body>
</body>
</html>
