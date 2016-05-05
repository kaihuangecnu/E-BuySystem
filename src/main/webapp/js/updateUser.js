$(function(){
    var errorTag=$("#error");
    $("#userInfoForm").submit(function(){
        var trueNameTag=$("#trueName");
        if(!/^[\u4e00-\u9fa5]{2,6}$/.test(trueNameTag.val())){
            trueNameTag.focus();
            errorTag.html("真实姓名由2-6个中文汉字组成！");
            return false;
        }else{
            errorTag.html("");
        }
        var passwordTag = $("#password");
        if (!/^[a-zA-Z0-9_]{6,18}$/.test(passwordTag.val())) {
            errorTag.html("请输入合法的密码！");
            passwordTag.focus();
            return false;
        }else{
            errorTag.html("");
        }
        var birthdayTag=$("#birthday");
        if(!/^\d{4}-\d{1,2}-\d{1,2}$/){
            errorTag.html("请输入正确的出生日期！");
            birthdayTag.focus();
            return false;
        }else{
            errorTag.html("");
        }
        var mobileTag = $("#mobile");
        if (!/^(86)*0*13\d{9}$/.test(mobileTag.val())) {
            errorTag.html("请输入正确的手机号码！");
            mobileTag.focus();
            return false;
        } else {
            errorTag.html("");
        }
        var addressTag = $("#address");
        if (addressTag.val().trim() == "") {
            errorTag.html("请输入你的收货地址！");
            addressTag.focus();
            return false;
        } else {
            errorTag.html("");
        }
        var identityCodeTag = $("#identityCode");
        if (!/^\d{18}|\d{15}$/.test(identityCodeTag.val())) {
            errorTag.html("请输入正确的身份证号码！");
            identityCodeTag.focus();
            return false;
        } else {
            errorTag.html("");
        }
        var emailTag = $("#email");
        if (!/^\w+([-+.]\w+)*@\w+([-.] \w+)*\.\w+([-.]\w+)*$/.test(emailTag.val())) {
            errorTag.html("请输入正确的邮箱地址！");
            emailTag.focus();
            return false;
        } else {
            errorTag.html("");
        }
        return true;
    });
});
