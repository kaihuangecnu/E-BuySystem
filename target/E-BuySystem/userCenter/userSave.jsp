<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>修改资料</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageContext.request.contextPath}/js/updateUser.js"></script>
</head>
<body>
<h2>修改资料</h2>
<div class="manage">
	<form action="user!saveUpdatedUser.action" method="post" id="userInfoForm">
		<table class="form">
			<tr>
				<td class="field"><label for="userName">用户名：</label></td>
				<td><input type="text" class="text" id="userName" name="s_user.userName" value="${current_user.userName}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="field"><label for="trueName">真实姓名：</label></td>
				<td><input type="text" class="text" id="trueName" name="s_user.trueName" value="${current_user.trueName}" placeholder="${current_user.trueName eq null?'（此项尚未填写）':''}"/></td>
			</tr>
			<tr>
				<td class="field"><label for="password">密码：</label></td>
				<td><input type="text" class="text" id="password" name="s_user.password" value="${current_user.password}"/></td>
			</tr>
			<tr>
				<td class="field">性别：</td>
				<td>
                    <label>
                        <input type="radio" name="s_user.sex" value="男" ${current_user.sex eq "男"?"checked='checked'":""}/>男
                    </label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>
                        <input type="radio" name="s_user.sex" value="女" ${current_user.sex eq "女"?"checked='checked'":""}/>女
                    </label>
				</td>
			</tr>
			<tr>
				<td class="field"><label for="birthday">出生日期：</label></td>
				<td>
					<input style="height: 25px;padding: 6px;border-color: #ccc;" type="text" id="birthday" name="s_user.birthday" value="<fmt:formatDate value="${current_user.birthday}" type="date" pattern="yyyy-MM-dd"/>" class="Wdate" onclick="WdatePicker();"/>
				</td>
			</tr>
			<tr>
				<td class="field"><label for="mobile">手机号码：</label></td>
				<td><input type="text" id="mobile" class="text" name="s_user.mobile" value="${current_user.mobile}"/></td>
			</tr>
			<tr>
				<td class="field"><label for="address">收货地址：</label></td>
				<td><input type="text" class="text" id="address" name="s_user.address" value="${current_user.address}"/></td>
			</tr>
			<tr>
				<td class="field"><label for="identityCode">身份证号：</label></td>
				<td><input class="text" type="text" id="identityCode" name="s_user.identityCode" value="${current_user.identityCode}"/></td>
			</tr>
			<tr>
				<td class="field"><label for="email">Email：</label></td>
				<td><input class="text" type="text" id="email" name="s_user.email"  value="${current_user.email}" /></td>
			</tr>
			<tr>
				<td>
				    <input type="hidden" name="s_user.id" value="${current_user.id}"/>
					<input type="hidden" name="s_user.status" value="${current_user.status}"/>
				</td>
				<td><label class="ui-blue"><input type="submit" value="确认修改"/></label></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><font id="error" color="red"></font> </td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>