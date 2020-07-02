<%--
  Created by IntelliJ IDEA.
  User: ruben.manukyan
  Date: 5/23/2020
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Hello world</h1><br/><br/>

<% if (request.getAttribute("message") != null) {  %>
<%=request.getAttribute("message")%>
<% } %>
<br><br>

<form method="post" action="/login">
    username : <input type="text" name="username"><br/>
    password : <input type="text" name="password"><br>
    remember : <input type="checkbox" name="remember"><br>
    <input type="submit" name="submit"><br>
</form>
<a href="go-forget-password">forget password</a>
<a href="go-register">register</a>


<br>
<br>
</body>
</html>
