<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h3>登录</h3>
<%--<h5>${pageContext.servletContext.contextPath}</h5>--%>
<form action="${pageContext.servletContext.contextPath}/login" method="post">
    <c:if test="${param.error != null}">
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">用户名</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">密码</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> <%-- CSRF参数 --%>
    <button type="submit" class="btn">Log in</button>
</form>
</body>
</html>