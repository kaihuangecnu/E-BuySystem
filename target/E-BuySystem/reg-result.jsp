<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
    <script>
        setTimeout("location.href='${pageContext.request.contextPath}/index.jsp';",3000);
    </script>
</head>
<body>
<div id="header" class="wrap">
    <jsp:include page="common/top.jsp"/>
</div>
<div id="register" class="wrap">
    <div class="shadow">
        <em class="corner lb"></em>
        <em class="corner rt"></em>
        <div class="box">
            <h1>欢迎注册易买网</h1>
            <ul class="steps clear-fix">
                <li class="finished"><em></em>填写注册信息</li>
                <li class="last-current"><em></em>注册成功</li>
            </ul>
            <div class="msg">
                <p>恭喜：注册成功！</p>
                <p>正在进入首页...</p>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>