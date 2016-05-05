<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的购物车</title>
</head>
<body>
<div id="header" class="wrap">
    <jsp:include page="common/top.jsp"/>
</div>
<div id="position" class="wrap">
    ${navCode}
</div>

<div class="wrap">
    <jsp:include page="${mainPage}"/>
</div>

<div id="footer">
    <jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>