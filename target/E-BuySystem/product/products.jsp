<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品列表</title>
</head>
<body>
<div class="main">
    <div class="product-list">
        <h2>全部商品</h2>
        <ul class="product clear-fix">
            <c:forEach var="p" items="${products}">
                <li>
                    <dl>
                        <dt>
                            <a href="product!showProductDetails.action?s_product.id=${p.id}" target="_blank"><img src="${p.picture}"></a>
                        </dt>
                        <dd class="title">
                            <a href="product!showProductDetails.action?s_product.id=${p.id}" target="_blank">${p.name}</a>
                        </dd>
                        <dd class="price">RMB ${p.price}</dd>
                    </dl>
                </li>
            </c:forEach>
        </ul>
        <div class="clear"></div>
        <div class="pager">
            <ul class="clear-fix">
                ${pageCode}
            </ul>
        </div>
    </div>
</div>
</body>
</html>