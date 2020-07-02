<%--
  Created by IntelliJ IDEA.
  User: ruben.manukyan
  Date: 5/23/2020
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% if (request.getAttribute("message") != null) {  %>
<%=request.getAttribute("message")%>
<% } %>
<br><br>

<form method="post" action="/register">
    name     : <input type="text" name="name"><br/>
    surname  : <input type="text" name="surname"><br>
    username : <input type="text" name="username"><br/>
    password : <input type="text" name="password"><br>
    <input type="submit" name="submit"><br>
</form>




</body>
</html>
