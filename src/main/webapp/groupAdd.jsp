<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 26.01.19
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie grupy</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Dodawanie nowej grupy:</h2>
<form action="/GroupsServlet" method="post" >
    <div class="form-group">
        <label for="text">Nazwa nowej grupy:</label>
        <input type="text" class="form-group mx-sm-3 mb-2" id="text" placeholder="Wprowadź nazwę grupy" name="name">
    </div>
    <button type="submit" class="btn btn-primary">Zapisz</button>
</form>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
