$(function () {
    var userNameTag = $("#userName");
    var submitTag = $("input[type=submit]");
    userNameTag.focus();
    userNameTag.blur(function () {
        var userName = userNameTag.val();
        if (!(/^[a-zA-Z]+?[_]??[a-zA-Z0-9]+?$/.test(userName) && userName.length >= 6)) {
            $("#userNameErrorMsg").html("用户名由英文、数字和下划线组成，只能由英文字母开头，可以有下划线但不能以下划线结尾，至少6个字符");
            userNameTag.focus();
            submitTag.attr("disabled", true);
        } else {
            $.post("user!userNameExisted.action", {userName: userName}, function (data) {
                var result = eval('(' + data + ')');
                if (result.userExisted) {
                    $("#userNameErrorMsg").html("该用户名已存在");
                    userNameTag.focus();
                } else {
                    $("#userNameErrorMsg").html("");
                }
                submitTag.attr("disabled", result.userExisted);
            });
        }
    });
    $("#regForm").submit(function () {
        var passwordTag = $("#password");
        if (!/^[a-zA-Z0-9_]{6,18}$/.test(passwordTag.val())) {
            $("#passwordErrorMsg").html("请输入合法的密码！");
            passwordTag.focus();
            return false;
        } else {
            $('#passwordErrorMsg').html("");
        }
        var rePassWordTag = $("#rePassWord");
        if (rePassWordTag.val() != passwordTag.val()) {
            $("#rePassWordErrorMsg").html("两次密码输入不一致，请重新输入！");
            rePassWordTag.focus();
            return false;
        } else {
            $("#rePassWordErrorMsg").html("");
        }
        var identityCodeTag = $("#identityCode");
        if (!/^\d{18}|\d{15}$/.test(identityCodeTag.val())) {
            $("#identityCodeErrorMsg").html("请输入正确的身份证号码！");
            identityCodeTag.focus();
            return false;
        } else {
            $("#identityCodeErrorMsg").html("");
        }
        var emailTag = $("#email");
        if (!/^\w+([-+.]\w+)*@\w+([-.] \w+)*\.\w+([-.]\w+)*$/.test(emailTag.val())) {
            $("#emailErrorMsg").html("请输入正确的邮箱地址！");
            emailTag.focus();
            return false;
        } else {
            $("#emailErrorMsg").html("");
        }
        var mobileTag = $("#mobile");
        if (!/^(86)*0*13\d{9}$/.test(mobileTag.val())) {
            $("#mobileErrorMsg").html("请输入正确的手机号码！");
            mobileTag.focus();
            return false;
        } else {
            $("#mobileErrorMsg").html("");
        }
        var addressTag = $("#address");
        if (addressTag.val() == "") {
            $("#addressErrorMsg").html("请输入你的收货地址！");
            addressTag.focus();
            return false;
        } else {
            $("#addressErrorMsg").html("");
        }
        return true;
    });
});