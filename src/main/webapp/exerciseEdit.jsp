<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 27.01.19
  Time: 00:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edytowanie zadania</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Edytowane zadanie:</h2>
<form action="/ExercisesServlet" method="post">
    <div class="form-group">
        <input type="hidden" class="form-group mx-sm-3 mb-2" id="name" name="exerciseId" value="${param.exerciseId}">
        <label for="title" class="col-sm-2 col-form-label">Nazwa zadania:</label>
        <input type="text" class="form-group mx-sm-3 mb-2" id="title" name="title" value="${param.title}">
        <br>
        <label for="text" class="col-sm-2 col-form-label">Opis zadania:</label>
        <textarea type="text" class="form-group mx-sm-3 mb-2" id="text" name="description" rows="5"
                  cols="70">${param.description}</textarea>
    </div>
    <button type="submit" class="btn btn-primary">Zapisz</button>
</form>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
