<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>用户中心</title>
</head>
<body>
<div id="header" class="wrap">
	<jsp:include page="common/top.jsp"/>
</div>
<div id="position" class="wrap">${navCode}</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>个人资料</dt>
				<dd><a href="user!getUserInfo.action">个人信息管理</a></dd>
				<dt>我的订单</dt>
				<dd><a href="order!getRequiredOrders.action">个人订单管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<jsp:include page="${mainPage}"/>
	</div>
	<div class="clear"></div>
</div>

<div id="footer">
	<jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>