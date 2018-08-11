<!DOCTYPE html>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../css/headerTop.css" />
		<link rel="stylesheet" type="text/css" href="../css/downloadInner.css" />
		
		<meta charset="GBK">
		<title>ORS下载详情页面</title>
	</head>
	<body>
	<!-- 用户页面头部-->
	<%@include file="/common/user_web_head.jsp"%>
		<article>
			<!-- 资源信息-->
			<div class="download">
				<h2>${data.zybt}</h2>
				<div id="inform"><p>用户名:${data.userId.yhnc}</p><p>上传时间:<fmt:formatDate value="${data.scsj}" dateStyle="long"/></p><p class="downloadNum">浏览量:${data.djcs}</p></div>
				<div id="txt">
					<p>${data.zyjj}</p>
			    </div>
				<!-- 下载链接,并判断空-->
				<c:choose>
					<c:when test="${data.zylj eq null}">
						<%--<a href="/download?resId=${data.id}">下载</a>--%>

					</c:when>
					<c:otherwise>
						<a id="downloadBtn" href="/download?resId=${data.id}">下载</a>
					</c:otherwise>
				</c:choose>
		    </div>
		</article>    
	</body>
</html>
