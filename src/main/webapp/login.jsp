<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>易买网 - 登录</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="js/check_login.js"></script>
</head>
<body>
<div id="header" class="wrap">
    <jsp:include page="common/top.jsp"/>
</div>
<div id="register" class="wrap">
    <div class="shadow">
        <em class="corner lb"></em> <em class="corner rt"></em>
        <div class="box">
            <h1>欢迎</h1>
            <form id="loginForm" method="post" action="user!userLogin.action">
                <table>
                    <tr>
                        <td class="field"><label for="userName">用户名：</label></td>
                        <td>
                            <input placeholder="请输入帐号" class="text" type="text" id="userName" name="s_user.userName" value="${s_user.userName }"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="password">登录密码：</label></td>
                        <td>
                            <input placeholder="请输入密码" class="text" type="password" id="password" name="s_user.password" value="${s_user.password }"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="role">账户类型：</label></td>
                        <td class="">
                            <select id="role" name="s_user.status">
                                <option value="0" ${s_user.status==0?"selected='selected'":""}>正常</option>
                                <option value="1" ${s_user.status==1?"selected='selected'":""}>管理员</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="imageCode">验证码：</label></td>
                        <td>
                            <input placeholder="请输入验证码" class="text" style="width:124px;height:24px;" type="text" value="${imageCode}" name="imageCode" id="imageCode">
                            <img onclick="this.src='${pageContext.request.contextPath}/kaptcha.jpg?param='+Math.random();" title="换一张试试" alt="换一张试试" name="randImage" id="randImage" src="${pageContext.request.contextPath}/kaptcha.jpg" width="70px" height="24px" border="1" align="absmiddle">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <label class="ui-green"><input type="submit" value="立即登录"/></label>&nbsp;&nbsp;&nbsp;&nbsp;
                            <font id="error" color="red">${error}</font>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="footer">
            <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</div>
</body>
</html>