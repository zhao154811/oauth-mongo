<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<h2>
    <a href="<%=basePath %>oauth/authorize?client_id=ucenter-sys-client&redirect_uri=http://localhost&response_type=code&scope=read">ucenter-sys-oauth</a>
</h2>

<h2>
    <a href="<%=basePath %>oauth/authorize?client_id=ucenter-client&redirect_uri=http://localhost&response_type=code&scope=read">ucenter-oauth</a>
</h2>

<h2>
    <a href="<%=basePath %>oauth/token?client_id=ucenter-sys-client&client_secret=237022b922f2ca861af0c7a982a3dc3f&grant_type=authorization_code&code=${param.code }&redirect_uri=http://localhost">ucenter-sys-token</a>
</h2>

<h2>
    <a href="<%=basePath %>oauth/token?client_id=ucenter-client&client_secret=411622fc06ccc29fde7693d9d8c20363&grant_type=authorization_code&code=${param.code }&redirect_uri=http://localhost">ucenter-oauth</a>
</h2>

<h2>
    <a href="<%=basePath %>oauth/authorize?client_id=auto-china&redirect_uri=http://localhost&response_type=code&scope=read,write">auto-china</a>
</h2>

<h2>
    <a href="<%=basePath %>oauth/token?client_id=auto-china&client_secret=411622fc06ccc29fde7693d9d8c20363&grant_type=authorization_code&code=${param.code }&redirect_uri=http://localhost">auto-china-token</a>
</h2>
</body>
</html>
