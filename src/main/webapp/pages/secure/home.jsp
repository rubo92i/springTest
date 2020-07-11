<%@ page import="am.basic.springTest.model.User" %>
<%@ page import="static am.basic.springTest.util.constants.ParameterKeys.MESSAGE_ATTRIBUTE_KEY" %>
<%@ page import="static am.basic.springTest.util.constants.Pages.INDEX_PAGE" %>
<%@ page import="static am.basic.springTest.util.constants.Messages.SESSION_EXPIRED_MESSAGE" %>
<%@ page import="static am.basic.springTest.util.constants.ParameterKeys.USER_ATTRIBUTE_KEY" %><%--
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

<a href="<%=request.getServletContext().getContextPath()%>/logout" style="float: right">Logout</a>
<h1 style="background: cornflowerblue">This is home page</h1>

<%
    User user = (User) session.getAttribute(USER_ATTRIBUTE_KEY);
    response.getWriter().write("Hello dear " + user.getName() + " " + user.getSurname());
%>

<br><br>
<% if (request.getAttribute(MESSAGE_ATTRIBUTE_KEY) != null) { %>
<%=request.getAttribute(MESSAGE_ATTRIBUTE_KEY)%>
<% } %>
<br><br>

<form method="post" action="<%=request.getServletContext().getContextPath()%>/secure/change-password">
    old password : <input type="text" name="password"><br>
    new password : <input type="text" name="newPassword"><br>

    <input type="submit" name="submit"><br>
</form>

<a href="<%=request.getServletContext().getContextPath()%>/secure/comments">comments</a>
<br><br>


</body>
</html>


