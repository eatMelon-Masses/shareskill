<!DOCTYPE html>
<%@page  pageEncoding="UTF-8" language="java" %>
<%@include file="/common/web_head.jsp"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../css/headerTop.css" />
		<link rel="stylesheet" type="text/css" href="../css/noteshare.css" />
		<script type="text/javascript" src="../js/noteshare.js" ></script>
		<meta charset="GBK">
		<!--<meta http-equiv="refresh" content="5"/>-->
		<title></title>
	</head>
	<body>
	<!-- 用户页面头部-->
	<%@include file="/common/user_web_head.jsp"%>
		<section >
			<div class="noteLeft">
				<div class="speak">
					<s:form commandName="blogsSphere" action="/addUserWord" method="post">
						<s:textarea  path="userWord" id="shuoshuo" name="keyWord" type="text" placeholder="说点什么吧！"></s:textarea>
						<%--<div class="release">--%>
							<%--<input type="image" src="../img/face.png"/>--%>
							<%--<input type="image" src="../img/pic1.png"/>--%>
						<%--</div>--%>
						<input id="releaseBtn" type="submit" value="发布" />
					</s:form>

				</div>
				<div id="dynamic">
					<a href="#">热门</a>
					<input type="button" value="全部"/>
					<a href="/showMyBlogSphere">我的</a>
					<input type="button" value="图片"/>
				</div>
				<div id="dynamicDiv">
					<div id="hotnews">
						<c:forEach var="tempBS" items="${bSList}">
								<div>
									<div class="userimg">
										<c:choose>
											<c:when test="${not empty tempBS.user.userImg}">
												<img src="${basePath}${tempBS.user.userImg}" id="img"/>
											</c:when>
											<c:otherwise>
												<img src="../img/hand1.png" id="img"/>
											</c:otherwise>
										</c:choose>
										<h2>${tempBS.user.yhnc}</h2><p><fmt:formatDate value="${tempBS.wordDate}" pattern="MM-dd E hh:mm"/></p>
									</div>
									<div class="userspeak">
										<p>${tempBS.userWord}</p><br/>
									</div>
									<%--<div class="visit">--%>
										<%--<input type="image" src="../img/turn.png" />--%>
										<%--<input type="image" src="../img/news.png" />--%>
										<%--<img id="zan" src="../img/zan1.png" />--%>
									<%--</div>--%>
								</div>
						</c:forEach>
					</div>
					<div>
						<c:if test="${page.pageTotal gt 1}">
							当前第[${page.pageNo}/${page.pageTotal}]页[<a href="${page.uri}&pageNo=1">首页</a>]
						</c:if>

						<c:choose>
							<c:when test="${page.prePageNo eq -1}">

							</c:when>
							<c:otherwise>
								[<a href="${page.uri}&pageNo=${page.prePageNo}">上一页</a>]
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${page.nextPageNo eq -1}"></c:when>
							<c:otherwise>
								[<a href="${page.uri}&pageNo=${page.nextPageNo}">下一页</a>]
							</c:otherwise>
						</c:choose>
						<c:if test="${page.pageTotal gt 1}">
							[<a href="${page.uri}&pageNo=${page.pageTotal}">尾页</a>]
						</c:if>

					</div>
					<div id="all">全部
					</div>
					<div>博客</div>
					<div id="pic">
						图片
					</div>
				</div>
			</div>
			<div class="noteRight">
				<div id="clock" class="date">
					<div id="clockYear">
						<p></p><p></p><p></p><p></p><p></p>
						<p></p><p></p><p></p><p></p><p></p>
					</div>
					<div id="clockHours">
						<p></p><p></p><p></p><p></p>
						<p></p><p></p><p></p><p></p>
					</div>
					<div id="clockDay">
						<p></p><p></p><p></p>
					</div>
				</div>
				<div class="noteRightbot">
					<img src="../img/bg(1).png"/>
					<div class="noteRightbot_in">
						<img src="../img/wdsc.png" /><a href="noteshareChild/collect.html">我的收藏</a><br/>
						<img src="../img/wdgz.png"/><a href="noteshareChild/attention.html">我的关注</a><br/>
						<img src="../img/ywxg.png"/><a href="noteshareChild/related.html">与我相关</a>
					</div>
				</div>
			</div>
		</section>
		
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
</html>
