<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/check_reg.js"></script>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
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
            <h1>欢迎注册中国移动支付商城</h1>
            <ul class="steps clear-fix">
                <li class="current"><em></em>填写注册信息</li>
                <li class="last"><em></em>注册成功</li>
            </ul>
            <form id="regForm" method="post" action="user!userReg.action">
                <table>
                    <tr>
                        <td class="field"><label for="userName">用户名(*)：</label></td>
                        <td><input class="text" type="text" id="userName" name="s_user.userName"/>&nbsp;
                            <font id="userNameErrorMsg" style="color: red;font-size: 12px;"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="password">登录密码(*)：</label></td>
                        <td><input class="text" type="password" id="password" name="s_user.password"/>&nbsp;
                            <font id="passwordErrorMsg" style="color: red;font-size: 12px;"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="rePassWord">确认密码(*)：</label></td>
                        <td><input class="text" type="password" id="rePassWord"/>&nbsp;
                            <font id="rePassWordErrorMsg" style="color: red;font-size: 12px;"></font>
                        </td>
                    </tr>

                    <tr>
                        <td class="field">性别(*)：</td>
                        <td>
                            <input type="radio" name="s_user.sex" id="male" value="男" checked="checked"/><label for="male">&nbsp;男</label>&nbsp;&nbsp;
                            <input type="radio" name="s_user.sex" id="female" value="女"/><label for="female">&nbsp;女</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="birthday">出生日期：</label></td>
                        <td>
                            <input type="text" id="birthday" name="s_user.birthday" readonly="readonly" class="Wdate" onclick="WdatePicker();"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="identityCode">身份证号(*)：</label></td>
                        <td><input class="text" type="text" id="identityCode" name="s_user.identityCode"/>&nbsp;
                            <font id="identityCodeErrorMsg" style="color: red;font-size: 12px;"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="email">Email：</label></td>
                        <td><input class="text" type="text" id="email" name="s_user.email"/>&nbsp;
                            <font id="emailErrorMsg" style="color: red;font-size: 12px;"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="mobile">手机号码(*)：</label></td>
                        <td><input class="text" type="text" id="mobile" name="s_user.mobile"/>&nbsp;
                            <font id="mobileErrorMsg" style="color: red;font-size: 12px;"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field"><label for="address">收获地址(*)：</label></td>
                        <td><input class="text" type="text" id="address" name="s_user.address"/>&nbsp;
                            <font id="addressErrorMsg" style="color: red;font-size: 12px;"></font>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-green"><input type="submit" value="确认注册"/></label></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><font id="error" style="color: red;font-size: 12px;">带“*”的为必填项</font></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>