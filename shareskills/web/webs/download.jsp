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
				<a >找资源</a>
				<a href="/browseDataResourceByUser">我的资源</a>
				<a>已下载</a>
				<a>我的收藏</a>
				<div id="sport"></div>
			</header>
			<section>
				<div class="downloadsearch">
					<form action="/searchDataByCateAndKey" method="post">
						<p>资源类型：</p>
						<select name="cateId">
							<c:forEach var="tempCate" items="${dataCategoryList}" >
								<option  value="${tempCate.id}">${tempCate.flmc}</option>
							</c:forEach>
						</select><br/><br/><br/>
						<span>关键字：</span>

						<input id="keywork" name="keyWord" type="text" placeholder="请输入关键字"/>
						<input id="dlSearchBtn" type="submit" value="搜索" />
					</form>
				</div>
				<div class="xiaobian">
					<p>小编推荐</p>
					<div id="xiaobianIn">
						<c:forEach var="tempData" items="${recDataList}">
							<div class="InDiv">
								<h3><a href="/viewDataResourceByid?id=${tempData.id}">${tempData.zybt}</a></h3>
								<div id="txt">
									${tempData.zyjj}
								</div>
								<p>发布者:${tempData.userId.yhnc}</p><p>访问量:${tempData.djcs}</p>
							</div>
						</c:forEach>
					</div>
				</div>
				<%
					int index=0;
					List list=(List)request.getAttribute("dataCordCateList");//获取控制层传来的卡片数据
				%>
				<c:forEach var="CordDataList" items="${dataCardLists}">
					<% DataCategory cordCate = (DataCategory) list.get(index++);%>
					<div class="edustudy">
						<div class="edustudytop">
							<p><%=cordCate.getFlmc()%>></p><a href="">更多</a></div>
							<c:forEach var="cordData" items="${CordDataList}" >
								<div>
									<h4><a href="/viewDataResourceByid?id=${cordData.id}">${cordData.zybt}</a></h4>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
				

			</section>
<%--			<section class="myresource">
			<div><a href="/browseDataResourceByUser">我的资源</a><a href="/writeDataResource">制作资源</a></div>

		</section>
			<section class="myload">
				<a>已下载</a>

			</section>
			<section class="mycollect">
				<a>我的收藏</a>

			</section>--%>
		</article>
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
</html>
