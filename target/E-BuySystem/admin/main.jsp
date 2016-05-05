<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>爱买网 - 管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/themes/icon.css">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
    <script src="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/jQuery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
    <script>
        /**
         *
         * @param title Tab的标题
         * @param src 需要显示页面的相对地址
         * @param iconCls 图标
         */
        function displayTab(title,src,iconCls){
            var tab=$("#tabs");
            if(tab.tabs("exists",title)){
                tab.tabs("select",title);
            }else{
                var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;' src='${pageContext.request.contextPath}/"+src+"'></iframe>";
                tab.tabs("add",{
                    title:title,
                    iconCls:iconCls,
                    closable:true,
                    content:content
                });
            }
        }
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height:78px;background-color:#E0ECFF;">
    <table style="padding:5px;width:100%;">
        <tr>
            <td width="50%"><img src="${pageContext.request.contextPath}/images/bglogo.png"></td>
            <td valign="bottom" align="right" width="50%" style="font-size: 14px;color:#902b2b;font-family:微软雅黑,serif;">&nbsp;&nbsp;当前登录：${current_user.userName}</td>
        </tr>
    </table>
</div>
<div region="center" style="width:100%;">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top:100px;">
                <font color="red" size="5">欢迎使用</font>
            </div>
        </div>
    </div>
</div>
<div region="west" style="width:200px;" title="功能菜单" split="true">
    <div class="easyui-accordion" fit="true" border="false">
        <div title="用户管理" data-options="iconCls:'icon-user'" style="padding:4px;">
            <a href="javascript:displayTab('用户管理','admin/userManage.jsp','icon-userManage');" class="easyui-linkbutton" plain="true" iconCls="icon-manage">用户管理</a>
        </div>
        <div title="商品管理" data-options="iconCls:'icon-product'" style="padding:4px;">
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理商品</a><br>
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理商品大类</a><br>
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理商品小类</a>
        </div>
        <div title="订单管理"  data-options="iconCls:'icon-order'" style="padding:4px">
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理订单</a>
        </div>
        <div title="留言管理" data-options="iconCls:'icon-comment'" style="padding:4px">
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理留言</a>
        </div>
        <div title="公告管理" data-options="iconCls:'icon-notice'" style="padding:4px">
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理公告</a>
        </div>
        <div title="新闻管理" data-options="iconCls:'icon-news'" style="padding:4px">
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理新闻</a>
        </div>
        <div title="标签管理" data-options="iconCls:'icon-tag'" style="padding:4px">
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-manage">管理标签</a>
        </div>
        <div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:4px">
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-modifyPassword">修改密码</a><br>
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-exit">安全退出</a><br>
            <a href="" class="easyui-linkbutton" plain="true" iconCls="icon-refresh">刷新系统缓存</a>
        </div>
    </div>
</div>
<div region="south" style="text-align: center;font-family:微软雅黑,serif;">中国心动信息技术有限公司 2015-2016 &copy; 版权所有</div>
</body>
</html>
