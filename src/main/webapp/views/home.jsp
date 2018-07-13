<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<h3>首页</h3>
<p><a href="${pageContext.servletContext.contextPath}/logout">登出</a></p>
<p><a href="${pageContext.servletContext.contextPath}/download">进入下载</a></p>
<p><a href="${pageContext.servletContext.contextPath}/admin">进入管理员页面</a></p>
<h5>${pageContext.servletContext.contextPath}</h5>
<label>${user.username}</label>
</body>
</html>
