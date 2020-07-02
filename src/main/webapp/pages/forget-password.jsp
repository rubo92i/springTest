<%--
  Created by IntelliJ IDEA.
  User: ruben.manukyan
  Date: 5/25/2020
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% if (request.getAttribute("message") != null) { %>
<%=request.getAttribute("message")%>
<% } %>
<br><br>

<form method="post" action="/forget-password">
    username : <input type="text" name="username"><br/>
    <input type="submit" name="submit"><br>
</form>

</body>
</html>
