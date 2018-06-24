<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<h3>首页</h3>
<a href="${pageContext.servletContext.contextPath}/download">进入下载</a>
<h5>${pageContext.servletContext.contextPath}</h5>
<label>${user.username}</label>
</body>
</html>
