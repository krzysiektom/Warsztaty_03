<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 26.01.19
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie zadania</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Dodawanie nowego zadania:</h2>
<form action="/ExercisesServlet" method="post" >
    <div class="form-group">
        <label for="title">Nazwa nowego zadania:</label>
        <input type="text" class="form-group mx-sm-3 mb-2" id="title" placeholder="Wprowadź nazwę zadania" name="title">
        <br>
        <label for="description">Wprowadź opis zadania:</label>
        <textarea class="form-group mx-sm-3 mb-2" id="description" rows="5" cols="70" placeholder="Wprowadź opis zadania" name="description"></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Zapisz</button>
</form>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
