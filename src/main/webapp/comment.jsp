<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>评论详情</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery-2.2.3.min.js"></script>
    <script>
        $(function(){
            $("#comment_form").submit(function(){
                var nickNameTag=$("#nickName");
                var contentTag=$("#content");
                var errorTag=$("#error");
                if(nickNameTag.val().trim()==""){
                    errorTag.html("请输入昵称");
                    nickNameTag.focus();
                    return false;
                }
                if(contentTag.val().trim()==""){
                    errorTag.html("请输入评论内容");
                    contentTag.focus();
                    return false;
                }
                errorTag.html("");
                return true;
            });
        });
    </script>
</head>
<body>
<div id="header" class="wrap">
    <jsp:include page="common/top.jsp"/>
</div>

<div id="main" class="wrap">
    <div class="lefter">
        <jsp:include page="common/left.jsp"/>
    </div>

    <div class="main">
        <div class="guest_book">
            <h2>全部留言</h2>
            <ul>
                <c:forEach var="c" items="${comments}">
                    <li>
                        <dl>
                            <dt>${c.content}</dt>
                            <dd class="author">
                                来自【${c.nickName}】
                                <span class="timer">
                                    <fmt:formatDate value="${c.createTime}" pattern="yyyy年M月dd日"/>
                                </span>
                                <c:if test="${not empty c.replyContent}">
                                    <dd>
                                        官方回复：${c.replyContent}&nbsp;&nbsp;
                                        <span class="timer">
                                            <fmt:formatDate value="${c.replyTime}" pattern="yyyy年M月dd日"/>
                                        </span>
                                    </dd>
                                </c:if>
                            </dd>
                        </dl>
                    </li>
                    <br/>
                </c:forEach>
            </ul>
            <div class="clear"></div>
            <div class="pager">
                <ul class="clear-fix">
                    ${pageCode}
                </ul>
            </div>
            <div id="reply-box">
                <form action="comment!saveOrUpdateComment.action" method="post" id="comment_form">
                    <table>
                        <tr>
                            <td class="field">昵称：</td>
                            <td><input class="text" type="text" id="nickName" name="comment.nickName" /></td>
                        </tr>
                        <tr>
                            <td class="field">留言内容：</td>
                            <td><textarea id="content" name="comment.content"></textarea></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <label class="ui-blue"><input type="submit" value="提交留言"/></label>&nbsp;&nbsp;
                                <font id="error" color="red">${error}</font>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="clear"></div>

<div id="footer">
    <jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>