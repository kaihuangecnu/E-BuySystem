<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>我的资料</title>
</head>
<body>
<h2>我的资料</h2>
<div class="manage">
	<table class="list">
		<tr>
			<%--<th>ID</th>--%>
			<th>真实姓名</th>
			<th>性别</th>
			<th>Email</th>
			<th>手机</th>
			<th>操作</th>
		</tr>
		<tr>
			<%--<td class="first w4 c">${currentUser.id}</td>--%>
			<td class="w1 c" width="90px">${current_user.trueName eq null ?"（尚未填写此项）":current_user.trueName}</td>
			<td class="w2 c">${current_user.sex}</td>
			<td class="w3 c">${current_user.email}</td>
			<td class="w4 c">${current_user.mobile}</td>
			<td class="w1 c"><a href="user!userUpdate.action">修改</a></td>
		</tr>
	</table>
</div>
</body>
</html>