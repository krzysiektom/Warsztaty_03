<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 26.01.19
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edytowanie grupy</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@include file="fragments/header.jsp" %>
<h2>Edytowana grupa:</h2>
<form action="/GroupsServlet" method="post" >
    <div class="form-group">
        <input type="hidden" class="form-group mx-sm-3 mb-2" id="name"  name="groupId" value="${param.groupId}" >
    </div>
    <div class="form-group">
        <label for="text">Nazwa edytowanej grupy:</label>
        <input type="text" class="form-group mx-sm-3 mb-2" id="text"  name="name" value="${param.groupName}">
    </div>
    <button type="submit" class="btn btn-primary">Zapisz</button>
</form>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
