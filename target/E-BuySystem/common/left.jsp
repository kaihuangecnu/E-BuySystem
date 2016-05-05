<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Left - Page</title>
</head>
<body>
<div class="box">
    <h2>商品分类</h2>
    <dl>
        <c:forEach var="bigType" items="${productBigTypes}">
            <dt>${bigType.name}</dt>
            <c:forEach var="smallType" items="${bigType.smallTypeList}">
                <dd><a href="product.action?s_product.productSmallType.id=${smallType.id}">${smallType.name}</a></dd>
            </c:forEach>
        </c:forEach>
    </dl>
</div>

<div class="spacer"></div>
<c:if test="${recentlyBrowsedProducts!=null}">
	<div class="last-view">
		<h2>最近浏览</h2>
		<dl class="clear-fix">
			<c:forEach items="${recentlyBrowsedProducts}" var="bp">
				<dt><img src="${bp.picture}" class="images" style="height:50px;width:50px;"></dt>
				<dd><a href="product!showProductDetails.action?s_product.id=${bp.id}" target="_blank">${bp.name}</a></dd>
			</c:forEach>
		</dl>
	</div>
</c:if>

</body>
</html>