<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/home.css" />
		<link rel="stylesheet" type="text/css" href="/css/headerTop.css" />
		<meta charset="utf-8" />
		<title>在线资源共享首页</title>
		<script src="/js/home.js" type="text/javascript" charset="UTF-8"></script>


	</head>
	<body>
		<!-- 用户页面头部-->
		<%@include file="/common/user_web_head.jsp"%>
		<section>
			<div class="home">
				<div class="homeleft">
					<ul>
						<mm:showAllCateList cateList="${cateList}"/>
					</ul>
				</div>
				<!-- 轮播图区-->
				<div class="homeright">
					<div id="picBox">
						<ul id="ul">
							<c:forEach var="lbStr" items="${lbList}">
								<li><img src="/homeslideshow/${lbStr}"/></li>
								<li><img src="../img/lb1.png"/></li>
								<li><img src="../img/lb2.png"/></li>
								<li><img src="../img/lb3.jpg"/></li>
								<li><img src="../img/lb4.png"/></li>
							</c:forEach>
						</ul>
				    </div>
					<!-- 博文item-->
					<div class="rightbuttom">
						<mm:showAllBlogList blogList="${blogList}" pathBase="<%=basePath%>"/>
					</div>
					<!-- 分页区-->
					<div>
						当前第[${page.pageNo}/${page.pageTotal}]页[<a href="${page.uri}1">首页</a>]
						<c:choose>
							<c:when test="${page.pageNo==1}">
								[<a >上一页</a>]
							</c:when>
							<c:otherwise>
								[<a href="${page.uri}${page.prePageNo}">上一页</a>]
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${page.pageNo==page.total}">
								[<a >下一页</a>]
							</c:when>
							<c:otherwise>
								[<a href="${page.uri}${page.nextPageNo}">下一页</a>]
							</c:otherwise>
						</c:choose>
						[<a href="${page.uri}?pageNo=${page.pageTotal}">尾页</a>]
					</div>
				</div>
			</div>
		</section>
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
	<c:if test="${not empty massage }">
		<script>
            layer.alert('${massage}', {icon: 6});
		</script>
	</c:if>


</html>
