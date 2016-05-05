<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的购物车</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script>
        $(function(){
            calculateSum();
            //数量增加事件
            $(".add").click(function(){
                var num=$(this).parent().find("input[class='text_box']");   //商品数量所在标签
                var productId=$(this).parent().find("input[id='product_id']").val();
                var price=$("#price_"+productId).html();    //商品单价
                $.post("shopping!modifyProductAmount.action",{"productId":productId,"productAmount":parseInt(num.val())+1},function(data){
                    var result=JSON.parse(data);
                    if(result.flag){
                        num.val(parseInt(num.val())+1);
                        $("#total_"+productId).html(price*num.val());
                        calculateSum();
                    }
                });
            });

            //数量减少事件
            $(".min").click(function(){
                var num=$(this).parent().find("input[class='text_box']");   //商品数量所在标签
                var productId=$(this).parent().find("input[id='product_id']").val();
                var price=$("#price_"+productId).html();    //商品单价
                if(num.val()>1){
                    $.post("shopping!modifyProductAmount.action",{"productId":productId,"productAmount":num.val()-1},function(data){
                        var result=JSON.parse(data);
                        if(result.flag){
                            num.val(parseInt(num.val())-1);
                            $("#total_"+productId).html(price*num.val());
                            calculateSum();
                        }
                    });
                }
            });

            //直接修改商品数量
            $(".text_box").blur(function(){
                var textBox=$(this);
                var productId=textBox.parent().find("input[id='product_id']").val();
                var price=$("#price_"+productId).html();    //商品单价
                if(!(/^[0-9]+$/.test(textBox.val())&&textBox.val()>=1))
                    textBox.val("1");
                $.post("shopping!modifyProductAmount.action",{"productId":productId,"productAmount":textBox.val()},function(data){
                    var result=JSON.parse(data);
                    if(result.flag){
                        $("#total_"+productId).html(textBox.val()*price);
                        calculateSum();
                    }
                });
            });
        });

        /**
         * 计算总消费额
         **/
        function calculateSum(){
            var sum=0;  //总金额
            $(".productTr").each(function(){
                //商品数量
                var productAmount=$(this).find("input[class='text_box']").val();
                //商品单价
                var unitPrice=$(this).find("label[class='price_']").html();
                sum+=productAmount*unitPrice;
            });
            $("#product_total").html(sum);
        }
    </script>
</head>
<body>
<div id="shopping">
    <c:if test="${current_user.shoppingCarts.size()>0}">
        <form action="order.action" method="post">
            <table id="myTableProduct">
                <tr>
                    <th>商品名称</th>
                    <th>商品单价</th>
                    <th>金额</th>
                    <th>购买数量</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${current_user.shoppingCarts}" var="sc">
                    <tr class="productTr">
                        <td class="thumb"><img class="images" src="${sc.product.picture}"/>
                            <a href="product!showProductDetails.action?s_product.id=${sc.product.id}" target="_blank">${sc.product.name}</a>
                        </td>
                        <%--商品单价--%>
                        <td class="price"><span>￥<label class="price_" id="price_${sc.product.id}">${sc.product.price}</label></span></td>
                        <%--商品总价--%>
                        <td class="price"><span>￥<label id="total_${sc.product.id}">${sc.product.price*sc.productAmount}</label></span></td>
                        <td class="number">
                            <input type="hidden" id="product_id" value="${sc.product.id}"/>
                            <input class="min" type="button" value=" - "/>
                            <input class="text_box" type="text" value="${sc.productAmount}" style="width: 30px;text-align:center;"/>
                            <input class="add" type="button" value=" + "/>
                        </td>
                        <td class="delete"><a href="shopping!deleteProduct.action?productId=${sc.product.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="button"><input type="submit" value=""/></div>
        </form>
    </c:if>
    <c:if test="${current_user.shoppingCarts.size()==0}">
        <center style="font-size:13px;">购物车空空的哟，快点去填满它吧！<a href="${pageContext.request.contextPath}/index.jsp">回到首页</a></center>
    </c:if>
</div>

<div class="shopping_list_end">
    <ul>
        <li class="shopping_list_end_2">￥<label id="product_total"></label></li>
        <li class="shopping_list_end_3">商品金额总计：</li>
    </ul>
</div>

<div style="background-color:#FC7E31;margin-top:30px;font-size:14px;height:38px;text-align: center">
    <div style="padding-top:8px;color: white">
        <b>收件人：</b>${current_user.trueName}&nbsp;&nbsp;<b>收获地址：</b>${current_user.address }&nbsp;&nbsp;<b>联系电话：</b>${current_user.mobile}
    </div>
</div>
</body>
</html>