<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情页</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script>
        /**
         * 添加商品到购物车
         * @param productId 商品编号
         */
        function addShoppingCart(productId){
            $.post("shopping!addShoppingCartItem.action",{"productId":productId},function(data){
                var result=JSON.parse(data);
                if (result.res == "userUndefined") {
                    if(confirm("登录之后才能将该商品添加至购物车哦！"))
                        location.href = "${pageContext.request.contextPath}/login.jsp";
                } else {
                    $("#productNum").html(result.productNum);
                    alert(result.res);
                }
            });
        }

        /**
         * 购买商品
         * @param productId 商品编号
         */
        function buyProduct(productId) {
            $.post("shopping!buyProduct.action", {"productId": productId}, function (data) {
                var result = JSON.parse(data);
                if (result.res == "userUndefined") {
                    if (confirm("登录之后才能购买商品哦！"))
                        location.href = "${pageContext.request.contextPath}/login.jsp";
                } else if (result.res == "finished")
                    location.href = "${pageContext.request.contextPath}/shopping!findShoppingCart.action";
            });
        }
    </script>
</head>
<body>
<div id="product" class="main">
    <h1>${product.name}</h1>
    <div class="info">
        <div class="thumb">
            <img class="img" src="${product.picture}"/>
        </div>
        <div class="buy">
            <br/>
            <p>
                商城价：<span class="price">￥${product.price}</span>
            </p>
            <p>库 存：${product.stock}</p>
            <br/>
            <div class="button">
                <input type="button" onclick="buyProduct('${product.id}');"/><br/>
                <a href="javascript:addShoppingCart('${product.id}');">加入购物车</a>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="introduce">
        <h2>
            <strong>商品详情</strong>
        </h2>
        <div class="text">
            ${product.description}
        </div>
    </div>
</div>
</body>
</html>