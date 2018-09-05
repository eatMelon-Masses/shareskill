<%@ page import="javax.xml.crypto.Data" %>
<!DOCTYPE html>
<%@include file="/common/web_head.jsp"%>
<%@page import="com.shareskill.model.DataCategory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/headerTop.css" />
		<link rel="stylesheet" type="text/css" href="/css/download.css" />
		<script type="text/javascript" src="/js/download.js" ></script>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
	<!-- 用户页面头部-->
	<%@include file="/common/user_web_head.jsp"%>
		<article>
			<header id="downloadTopbar">
				<a href="/showDownload">找资源</a>
				<a href="/browseDataResourceByUser">我的资源</a>
				<div id="sport"></div>
			</header>
			<section>

			</section>
			<section class="myresource">
				<div class="myresourcetop"><a href="/browseDataResourceByUser" id="myresourceA">我的资源</a><a href="/writeDataResource">制作资源</a></div>
				<div id="myresourceIn">
			<%--		<div class="InDiv">
						<h3>标题</h3>
						<div id="txt">美食，顾名思义就是美味的食物，贵的有山珍海味，便宜的有街边小吃。其实美食是不分贵贱的，
							只要是自己喜欢的，都可以称之为美食。中国素有”烹饪王国“这个美誉。在中国这个大家庭里，我们
							有五十六个小家庭，每个家庭都有自己的特色美食。美食吃前有期待、吃后有回味，已不仅仅是简单
							的味觉感受，更是一种精神享受。享受美食也要看场合，场合好美食吃起来才有味道。世界各地美食
							文化博大精深，营养物质各不相同，品味更多美食，享受更多健康，也让人吃的更加开心。
						</div>
					</div>--%>
					<c:forEach var="tempData" items="${dataList}">
					<div class="InDiv">
						<h3><a href="/viewDataResourceByid?id=${tempData.id}">${tempData.zybt}</a></h3>
						<div id="txt">
							${tempData.zyjj}
						</div>
					</div>
					</c:forEach>
				</div>
			</section>
			<!-- 分页区-->
			<div class="pagestyle">
				<%--当前第[${page.pageNo}/${page.pageTotal}]页[<a href="${page.uri}1">首页</a>]
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
				[<a href="${page.uri}${page.pageTotal}">尾页</a>]--%>
					<c:if test="${page.pageTotal gt 1}">
						当前第[${page.pageNo}/${page.pageTotal}]页[<a href="${page.uri}pageNo=1">首页</a>]
					</c:if>

					<c:choose>
						<c:when test="${page.prePageNo eq -1}">
						</c:when>
						<c:otherwise>
							[<a href="${page.uri}pageNo=${page.prePageNo}">上一页</a>]
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${page.nextPageNo eq -1}">
						</c:when>
						<c:otherwise>
							[<a href="${page.uri}pageNo=${page.nextPageNo}">下一页</a>]
						</c:otherwise>
					</c:choose>
					<c:if test="${page.pageTotal gt 1}">
						[<a href="${page.uri}pageNo=${page.pageTotal}">尾页</a>]
					</c:if>
			</div>
			</section>
		</article>
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
</html>
