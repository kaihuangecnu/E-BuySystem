<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的订单</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script>
        function confirmReceive(orderId,status){
            $.post("order!updateOrderStatus.action",{"s_order.id":orderId,"s_order.status":status},function(data){
                if(JSON.parse(data).orderUpdated){
                    location.reload();
                    alert("交易成功！");
                }
            });
        }
    </script>
</head>
<body>
<h2>订单管理</h2>
<div class="manage">
    <div class="search">
        <form action="order!getRequiredOrders.action" method="post">
            订单号：<input type="text" placeholder="请输入您的订单号（支持模糊搜索）" class="text" name="s_order.orderNo" value="${s_order.orderNo}"/>
            <label class="ui-blue"><input type="submit" value="搜索"/></label>
        </form>
    </div>
    <div class="spacer"></div>
    <table class="list">
        <c:forEach items="${orders}" var="order">
            <tr style="background-color: #f7f4eb;font-size: 12px;">
                <td colspan="4">
                    &nbsp;
                    订单号：${order.orderNo}
                    &nbsp;
                    创建时间：<fmt:formatDate value="${order.createTime}" pattern="yyyy年M月dd日"/>
                    &nbsp;
                    状态：
                    <c:choose>
                        <c:when test="${order.status==1}">待审核</c:when>
                        <c:when test="${order.status==2}">待发货</c:when>
                        <c:when test="${order.status==3}"><input style="width:60px;height:20px;" type="button" value="确认收货" onclick="confirmReceive(${order.id},4);"></c:when>
                        <c:when test="${order.status==4}">交易已完成</c:when>
                    </c:choose>
                    &nbsp;
                    总金额：${order.cost}&nbsp;元
                </td>
            </tr>
            <c:forEach items="${order.order_productList}" var="op">
                <tr style="font-size:12px;" align="center">
                    <td width="50%">
                        <a target="_blank" href="product!showProductDetails.action?s_product.id=${op.product.id}"><img width="72px" height="72px" src="${op.product.picture}"></a>
                        <a target="_blank" href="product!showProductDetails.action?s_product.id=${op.product.id}">${op.product.name}</a>
                    </td>
                    <td width="17%">
                        单价：${op.product.price}
                    </td>
                    <td>
                        数量：${op.num}
                    </td>
                    <td width="17%">
                        小计：${op.product.price*op.num}（元）
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
</div>
</body>
</html>