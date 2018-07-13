<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>管理员</title>

    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<h3>管理员可见</h3>
<h5>${pageContext.servletContext.contextPath}</h5>
<button type="button" onclick="reInitDB()">初始化数据库</button>
</body>
<script>
    function reInitDB() {
        $.ajax({
            async: true,
            type: 'post',
            url: '${pageContext.servletContext.contextPath}/admin/reInitDB',
            dataType: 'json',
            data: {},
            success: function (data) {
                if (data.code === 200){
                    alert("成功");
                }else {
                    alert("失败");
                }
            },
            error: function (data) {
                console.log("异常");
            }
        })
    }
</script>
</html>