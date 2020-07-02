<%@ page import="static am.basic.springTest.util.constants.ParameterKeys.USERNAME_PARAM_KEY" %><%--
  Created by IntelliJ IDEA.
  User: ruben.manukyan
  Date: 6/2/2020
  Time: 19:47
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

<form method="post" action="/verify">
    <input type="hidden" name="username" value="<%=request.getAttribute(USERNAME_PARAM_KEY)%>">
    code: <input type="text" name="code"><br>
    <input type="submit" name="submit"><br>
</form>


<br><br>
<form method="post" action="/resend">
    <input type="hidden" name="username" value="<%=request.getAttribute(USERNAME_PARAM_KEY)%>">
    <input type="submit" name="submit" value="Resend code"><br>
</form>

</body>
</html>
