$(function(){
    $("#userName").focus();
    $("#loginForm").submit(function(){
        var userNameTag=$("#userName");
        var userName=userNameTag.val();
        var errorTag=$("#error");
        if (!(/^[a-zA-Z]+?[_]??[a-zA-Z0-9]+?$/.test(userName) && userName.length >= 6)) {
            errorTag.html("请输入正确的用户名！");
            userNameTag.focus();
            return false;
        }
        var passwordTag=$("#password");
        var password=passwordTag.val();
        if (!/^[a-zA-Z0-9_]{6,18}$/.test(password)) {
            errorTag.html("密码不合法！");
            passwordTag.focus();
            return false;
        }
        var imageCodeTag=$("#imageCode");
        var imageCode=imageCodeTag.val();
        if(!/^[0-9a-z]{4}$/.test(imageCode)){
            errorTag.html("请输入正确的验证码！");
            imageCodeTag.focus();
            return false;
        }
        errorTag.html("");
        return true;
    });
});
