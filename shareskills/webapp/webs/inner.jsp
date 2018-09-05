<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../css/headerTop.css" />
		<link rel="stylesheet" type="text/css" href="../css/inner.css" />
		<meta charset="UTF-8">
		<title>ORS</title>
	</head>
	<body>
	<!-- 用户页面头部-->
	<%@include file="/common/user_web_head.jsp"%>
		<article>
			<h2>${blog.bwbt}</h2>
			<div id="inform"><p>作者:${blog.editor}</p><p>时间:${blog.bwcjsj}</p><p>点击量:${blog.bwdjcs}</p></div>
			<div id="txt">
				${blog.bwnr}
			</div>
		</article>
		<section>
			<%--<div class="visit">--%>
				<%--<input type="image" src="../img/turn.png" />--%>
				<%--<input type="image" src="../img/zan.png" />--%>
				<%--<input id="visit" type="image" src="../img/news.png" />--%>
			<%--</div>--%>
			<s:form commandName="bc" method="post" action="/addBlogComment?blogId=${blog.id}">
				<s:input path="plnr" id="visitInner" type="text" placeholder="我也说一句"/>
				<input id="visitBtn" type="submit" value="发表" />
			</s:form>
			<div class="visitIn">
				<!-- 用户评论区-->
				<c:forEach var="userBc" items="${bcList}">
				<div><p>${userBc.user.yhnc}</p><p>:</p><p>${userBc.plnr}</p>
					<c:if test="${not empty user and user.id eq userBc.user.id}">
					<a href="/delBlogComment?blogId=${blog.id}&bcId=${userBc.id}" class="delete">删除</a>
					</c:if></div>
					<%--<div><p>${userBc.user.yhnc}</p><p>:${userBc.plnr}</p></div>--%>
				</c:forEach>
			</div>
		</section>
	</body>
</html>
