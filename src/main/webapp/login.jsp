<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Oauth login</title>
</head>
<body>
<h3>Oauth login</h3>

<form action="<%=path %>pc/login.do" method="post">

    <label for="username">Username:</label>
    <input type="text" id="username" name="j_username" value="admin"/>
    <br/>
    <br/>
    <label for="password">Password:</label>
    <input type="password" name="j_password" id="password" value="admin"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>