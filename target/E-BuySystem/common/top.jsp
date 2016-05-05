<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Header</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script>
        $(function(){
            $("#product-SearchForm").submit(function(){
                var txtSearchTag=$("#txtSearch");
                if(txtSearchTag.val().trim()=="") {
                    txtSearchTag.val("");
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<div id="logo">
    <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/logo.gif"/></a>
</div>
<div class="help">
    <c:choose>
        <c:when test="${not empty current_user}">
            <a href="shopping!findShoppingCart.action" class="shopping" target="_blank">查看购物车（<span id="productNum">${current_user.shoppingCarts.size()}</span>件商品）</a>
            <a href="user!preUserUpdate.action">欢迎，${current_user.userName}</a>
            <a href="javascript:if(confirm('您确认要退出当前账户吗？')) location.href='${pageContext.request.contextPath}/user!logout.action';">注销</a>
            <a href="comment.action">评论</a>
        </c:when>
        <c:otherwise>
            <a class="shopping" href="javascript:if(confirm('登录之后才能查看购物车哟！现在登录？')) location.href='${pageContext.request.contextPath}/login.jsp';">查看购物车</a>
            <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
            <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
            <a href="comment.action">评论</a>
        </c:otherwise>
    </c:choose>
    <form action="product.action" method="post" id="product-SearchForm">
        <input type="text" placeholder="输入商品名称查询商品" id="txtSearch" name="s_product.name" value="${s_product.name}"/>
        <input type="submit" id="cmdSearch" value="搜索"/><br/>
        <div id="suggest" style="width:200px"></div>
    </form>
</div>

<div class="navbar">
    <ul class="clear-fix">
        <li class="current"><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
        <c:forEach var="bigType" items="${productBigTypes}">
            <li><a href="product.action?s_product.productBigType.id=${bigType.id}">${bigType.name}</a></li>
        </c:forEach>
    </ul>
</div>
<%--标签显示模块--%>
<div id="childNav">
    <div class="wrap">
        <ul class="clear-fix">
            <c:forEach items="${tags}" var="tag" varStatus="status">
                <c:choose>
                    <c:when test="${status.index==0}">
                        <li class="first"><a href="${tag.url}" target="_blank">${tag.name}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${tag.url}" target="_blank">${tag.name}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>