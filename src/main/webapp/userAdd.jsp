<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 26.01.19
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dodawanie użytkownika</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Dodawanie nowego użytkownika:</h2>
<form action="/UsersServlet" method="post">
    <div class="form-group">
        <label for="username" class="col-sm-2 col-form-label">Imię i nazwisko:</label>
        <input type="text" class="form-group mx-sm-3 mb-2" id="username" placeholder="Wprowadź imię i nazwisko"
               name="username">
        <br>
        <label for="email" class="col-sm-2 col-form-label">Email:</label>
        <input type="text" class="form-group mx-sm-3 mb-2" id="email" placeholder="Wprowadź email" name="email">
        <br>
        <label for="password" class="col-sm-2 col-form-label">Hasło:</label>
        <input type="text" class="form-group mx-sm-3 mb-2" id="password" placeholder="Wprowadź hasło" name="password">
        <br>
        <label class="col-sm-2 col-form-label" for="select">Grupa</label>
        <select class="select-group mx-sm-3 mb-2" id="select" name="userGroupId">
            <option selected>Wybierz grupę</option>
            <c:forEach var="userGroup" items="${userGroups}">
                <option value=${userGroup.id}>${userGroup.name}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Zapisz</button>
</form>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
