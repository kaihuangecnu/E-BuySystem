<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>爱买 - 首页</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<%--导入头部页面--%>
<div id="header" class="wrap">
    <jsp:include page="common/top.jsp" flush="true"/>
</div>
<div id="main" class="wrap">
    <%--导入左侧页面--%>
    <div class="lefter">
        <jsp:include page="common/left.jsp" flush="true"/>
    </div>
    <%--显示主体页面--%>
    <div class="main">
        <div class="price-off">
            <h2>今日特价</h2>
            <ul class="product clear-fix">
                <c:forEach var="sp" items="${specialProducts}">
                    <li>
                        <dl>
                            <dt>
                                <a href="product!showProductDetails.action?s_product.id=${sp.id}" target="_blank"><img src="${sp.picture}"></a>
                            </dt>
                            <dd class="title">
                                <a href="product!showProductDetails.action?s_product.id=${sp.id}" target="_blank">${sp.name}</a>
                            </dd>
                            <dd class="price">RMB ${sp.price}</dd>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="side">
            <div class="news-list">
                <h4>最新公告</h4>
                <ul>
                    <c:forEach var="notice" items="${notices}">
                        <li><a href="notice!showNotice.action?noticeId=${notice.id}" target="_blank">${notice.title}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="spacer"></div>
            <div class="news-list">
                <h4>新闻动态</h4>
                <ul>
                    <c:forEach var="news" items="${news}">
                        <li><a href="news!showNews.action?newsId=${news.id}" target="_blank">${news.title}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="spacer clear"></div>
        <div class="hot">
            <h2>热卖推荐</h2>
            <ul class="product clear-fix">
                <c:forEach var="hp" items="${hotProducts}">
                    <li>
                        <dl>
                            <dt>
                                <a href="product!showProductDetails.action?s_product.id=${hp.id}" target="_blank"><img src="${hp.picture}"></a>
                            </dt>
                            <dd class="title">
                                <a href="product!showProductDetails.action?s_product.id=${hp.id}" target="_blank">${hp.name}</a>
                            </dd>
                            <dd class="price">RMB ${hp.price}</dd>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="clear"></div>
</div>
<%--导入页脚--%>
<div id="footer">
    <jsp:include page="common/footer.jsp" flush="true"/>
</div>
</body>
</html>