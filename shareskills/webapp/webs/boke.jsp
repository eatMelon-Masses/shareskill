<!DOCTYPE html>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/headerTop.css" />
		<link rel="stylesheet" type="text/css" href="/css/boke.css" />
		<script type="text/javascript" src="/js/boke.js"></script>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<!-- 用户页面头部-->
		<%@include file="/common/user_web_head.jsp"%>
		<article>
			<!-- 头部分类区-->
			<div class="fenleiimg"><img src="../img/category.png"></div>
			<div id="bokedivMenu" class="bokedivMenu" >
				<c:forEach var="tempCate" items="${catesList}">
					<c:choose>
						<c:when test="${tempCate.id!=cateId}">
							<a href="/browseBlogsByCate?id=${tempCate.id}&order=0">${tempCate.flmc}</a>
						</c:when>
						<c:otherwise>
							<a class="click" href="/browseBlogsByCate?id=${tempCate.id}&order=0">${tempCate.flmc}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<!-- 博客区头部-->
			<div id="bokedivIn">
				<div>
					<div class="bokeDivimg" >
						<%--<img src="../img/bokeDiv/focus.png"/>--%>
							<c:choose>
								<c:when test="${cateId eq null}">

								</c:when>
								<c:otherwise>
									<a href="/browseBlogsByCate?id=${cateId}&order=1">热门排序</a> |<a href="/browseBlogsByCate?id=${cateId}&order=0">时间排序</a>
								</c:otherwise>
							</c:choose>
							<%--<a href="/browseBlogsByCate?id=${tempCate.id}&order=1">热门排序</a> |<a href="/browseBlogsByCate?id=${tempCate.id}&order=0">时间排序</a>--%>
					</div>
					<!--博客正文 -->
					<div class="bokeDivInner">
						<c:forEach var="blogItem" items="${blogsList}">
							<div>
								<a href="/viewBlog?id=${blogItem.id}"><h3>${blogItem.bwbt}</h3></a>
								<img src="/img/zan%20(2).png"><p>${blogItem.editor}</p>
								<div id="txt">${blogItem.bwnr}</div>
								<%--<div>--%>
									<%--<img src="/img/wdsc.png" />--%>
									<%--<img src="/img/news.png" />--%>
								<%--</div>--%>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="pagestyle">
					<c:if test="${page.pageTotal gt 1}">
						当前第[${page.pageNo}/${page.pageTotal}]页[<a href="${page.uri}&pageNo=1">首页</a>]
					</c:if>

							<c:choose>
								<c:when test="${page.prePageNo eq -1}">
									[<a >上一页</a>]
								</c:when>
								<c:otherwise>
									[<a href="${page.uri}&pageNo=${page.prePageNo}">上一页</a>]
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${page.nextPageNo eq -1}">
									[<a >下一页</a>]
								</c:when>
								<c:otherwise>
									[<a href="${page.uri}&pageNo=${page.nextPageNo}">下一页</a>]
								</c:otherwise>
							</c:choose>
					<c:if test="${page.pageTotal gt 1}">
						[<a href="${page.uri}&pageNo=${page.pageTotal}">尾页</a>]
					</c:if>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">

					</div>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
					</div>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
					</div>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
					</div>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
					</div>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
					</div>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
					</div>
				</div>
				<div>
					<div class="bokeDivimg" >
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
					</div>
				</div>
			</div>
		</article>
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
</html>
