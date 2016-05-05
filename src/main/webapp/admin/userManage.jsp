<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/themes/icon.css">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
    <script src="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
    <script>
        /**
         * 根据用户名查询用户
         * @returns {boolean}
         */
        function searchUser(){
            var username=$("#username").val().trim();
            $("#datagrid").datagrid("load",{"s_user.userName":username});
        }

        /**
         * 删除选定的用户
         */
        function deleteUsers(){
            var selectedRows=$("#datagrid").datagrid("getSelections");//获取表格中所有被选中的行
            if(selectedRows.length==0) {
                $.messager.alert("提示","请选择要删除的数据！");
            }else{
                var idsArray=[];
                for(var i=0;i<selectedRows.length;i++){
                    idsArray.push(selectedRows[i].id);
                }
                var ids=idsArray.join(",");
                $.messager.confirm("系统提示","您确定要删除这 <span style='color:red;font-weight:bold;'>"+selectedRows.length+"</span> 条数据吗？",function(r){
                    if(r){
                        $.post("user!deleteUsers.action",{"ids":ids},function(data){
                            var res=JSON.parse(data);
                            if(res.status=="success"){
                                $.messager.alert("提示","<span style='color:green;font-weight:bold'>"+res.deleteNum+"</span> 条数据已删除！");
                                $("#datagrid").datagrid("reload");
                            }else{
                                $.messager.alert("提示","删除失败，请联系管理员！");
                            }
                        });
                    }
                });
            }
            return false;
        }

        var url="";//全局变量，在进行保存和修改的时候变换url
//        显示对话框
        function showDialog(){
            $('#dialog').dialog('open').dialog('center').dialog('setTitle','新增用户');
            url="user!saveUser.action";
            return false;
        }

//        关闭对话框
        function closeDialog(){
            $('#dialog').dialog('close');
            return false;
        }



//        添加用户
        function sendRequest(){
            $("#user-form").form("submit",{
                url:url,
                onSubmit:function(){
                    if($("#sex").combobox("getValue")==""){
                        $.messager.alert("提示","请选择性别！");
                        return false;
                    }
                    return $(this).form("validate");
                },
                success:function(data){
                    var res=JSON.parse(data);
                    if(res.status=="success"){
                        $('#user-form').form('clear');
                        closeDialog();
                        $("#datagrid").datagrid("reload");
                        $.messager.alert("提示","保存成功！");
                    }else{
                        closeDialog();
                        $.messager.alert("保存失败，请联系管理员！");
                    }
                }
            });
        }

//        修改用户信息
        function updateUser(){
            var selectedRows=$("#datagrid").datagrid("getSelections");//获取表格中所有被选中的行
            if(selectedRows.length==0){
                $.messager.alert("提示","请选择要修改的信息！");
                closeDialog();
                return false;
            }
            if(selectedRows.length>1){
                $.messager.alert("提示","选择多条数据无效！");
                closeDialog();
                return false;
            }
            $("#dialog").dialog("open").dialog("setTitle","编辑用户信息");
            var row=selectedRows[0];
            $("#trueName").val(row.trueName);
            $("#s-username").val(row.userName);
            $("#password").val(row.password);
            $("#identityCode").val(row.identityCode);
            $("#email").val(row.email);
            $("#mobile").val(row.mobile);
            $("#address").val(row.address);
            $("#sex").combobox("setValue",row.sex);
            $("#birthday").datebox("setValue",row.birthday);
            url="user!updateUser.action?s_user.id="+row.id;
        }
    </script>
</head>
<body style="margin:1px;">
<table id="datagrid" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true" url="user!findUser.action" fit="true" toolbar="#toolbar">
    <thead>
        <tr>
            <th field="ids" checkbox="true" align="center"></th>
            <th field="id" align="center" hidden="hidden">编号</th>
            <th field="userName" align="center">用户名</th>
            <th field="password" align="center">密码</th>
            <th field="trueName" align="center">真实姓名</th>
            <th field="sex" align="center">性别</th>
            <th field="birthday" align="center">出生日期</th>
            <th field="identityCode" align="center">身份证号</th>
            <th field="email" align="center">邮箱</th>
            <th field="mobile" align="center">号码</th>
            <th field="address" align="center">收货地址</th>
            <th field="status" align="center">状态</th>
        </tr>
    </thead>
</table>
<%-- 工具栏 --%>
<div id="toolbar">
    <div>
        <a href="javascript:showDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true">添加</a>
        <a href="javascript:updateUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true">修改</a>
        <a href="javascript:deleteUsers();" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" plain="true">删除</a>
        &nbsp;&nbsp;
        <input type="text" id="username" placeholder="查找用户名" onkeyup="if(event.keyCode==13) searchUser();">
        <a href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true">搜索</a>
    </div>
</div>

<%-- 对话框 --%>
<div id="dialog" class="easyui-dialog" style="width:640px;height:280px;padding:15px;" closed="true" buttons="#dialog-btn">
    <form id="user-form" method="post">
        <table cellpadding="4px" style="font-size:12px;">
            <tr>
                <td><label for="trueName">真实姓名</label></td>
                <td><input type="text" id="trueName" name="s_user.trueName" placeholder="真实姓名" class="easyui-validatebox" required="true"></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="s-username">用户名</label></td>
                <td><input type="text" id="s-username" name="s_user.userName" placeholder="用户名" class="easyui-validatebox" required="true"></td>
            </tr>
            <tr>
                <td><label for="password">密码</label></td>
                <td><input type="password" id="password" name="s_user.password" placeholder="密码" class="easyui-validatebox" required="true"></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="sex">性别</label></td>
                <td>
                    <select name="s_user.sex" id="sex" class="easyui-combobox" editable="false" panelHeight="auto" style="width:171px;">
                        <option value="" selected="selected">请选择性别</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="birthday">出生日期</label></td>
                <td><input type="text" id="birthday" name="s_user.birthday" class="easyui-datebox" editable="false" required="true"></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="identityCode">身份证</label></td>
                <td><input type="text" id="identityCode" name="s_user.identityCode" class="easyui-validatebox" required="true"></td>
            </tr>
            <tr>
                <td><label for="email">邮箱</label></td>
                <td><input type="text" id="email" name="s_user.email" class="easyui-validatebox" required="true" validType="email"></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="mobile">联系电话</label></td>
                <td><input type="text" id="mobile" name="s_user.mobile" class="easyui-validatebox" required="true"></td>
            </tr>
            <tr>
                <td><label for="address">收货地址</label></td>
                <td colspan="4"><input type="text" id="address" name="s_user.address" class="easyui-validatebox" required="true"></td>
            </tr>
        </table>
    </form>
    <div id="dialog-btn">
        <a href="javascript:sendRequest();" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
        <a href="javascript:closeDialog();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
</div>
</body>
</html>
