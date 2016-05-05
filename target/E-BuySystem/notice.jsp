<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>公告信息</title>
</head>
<body>
<div id="header" class="wrap">
    <jsp:include page="common/top.jsp"/>
</div>

<div id="position" class="wrap">
    ${navCode}
</div>
<div id="main" class="wrap">
    <div class="lefter">
        <jsp:include page="common/left.jsp"/>
    </div>
    <jsp:include page="${mainPage}"/>
    <div class="clear"></div>
</div>
<div id="footer">
    <jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>