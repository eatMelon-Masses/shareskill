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
		<header class="top">
			<div class="logo"><a href="../index.html"><img src="/img/logo (2).png" /></a></div>
			<nav class="topmain">
				<a href="../index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="focus.html">博客</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="../download.html">下载</a>&nbsp;&nbsp;
				<a href="../noteshare.html">便签分享</a>&nbsp;&nbsp;
			    <a href="../person.html">个人中心</a>&nbsp;&nbsp;
			</nav>
			<div class="topsearch">
				<form method="post" action="/searchAllBlogs">
					<input type="text"  name="searchContent" class="search" placeholder="搜索一下"/>
					<input type="submit" src="/img/searchimg.png" class="searchbtn" />
				</form>
			</div>
			<div class="boke"><img  class="bokeimg" src="/img/wboke.png" /></div>
			<nav class="toplogin">
				<a class="wboke" href="../writeBoke.html">写博客</a>&nbsp;
				<a class="login" href="../login.html">登录</a>&nbsp;
				<a class="enroll" href="../enroll.html">注册</a>&nbsp;
			</nav>
		</header>
		<section>
			<div id="bokediv">
				<div class="fenleiimg"><img src="/img/category.png"></div>
				<div id="bokedivMenu" class="bokedivMenu">
					<a href="focus.html">关注</a>
					<a href="information.jsp" style="background: #66CCCC;">资讯</a>
					<a href="fashion.html">时尚</a>
					<a href="entertainment.html">娱乐</a>
					<a href="life.html">生活</a>
					<a href="finance.html">财经</a>
					<a href="sports.html">体育</a>
					<a href="food.html">美食</a>
					<a href="film.html">电影</a>
					<a href="cartoon.html">动漫</a>
				</div>

				<div class="bokedivIn">
					<div class="bokeDivimg" >
						<img src="/img/bokeDiv/infomation.png"/>
						<input type="button" value="热门排序" />|<input type="button" value="时间排序"/>
					</div>
					<div class="bokeDivInner">
								<c:forEach var="blogItem" items="${blogsList}">
                                    <div>
                                        <h3>${blogItem.bwbt}</h3>
                                        <img src="/img/zan%20(2).png"><p>${blogItem.editor}</p>
                                        <div id="txt">${blogItem.bwnr}</div>
                                        <%--<div>--%>
                                            <%--<img src="/img/wdsc.png" />--%>
                                            <%--<img src="/img/news.png" />--%>
                                        <%--</div>--%>
                                    </div>
                                </c:forEach>



						<div>
							<h3>标题</h3>
							<img src="/img/zan (2).png"><p>用户名</p>
							<div id="txt">美食，顾名思义就是美味的食物，贵的有山珍海味，便宜的有街边小吃。其实美食是不分贵贱的，
								只要是自己喜欢的，都可以称之为美食。中国素有”烹饪王国“这个美誉。在中国这个大家庭里，我们
								有五十六个小家庭，每个家庭都有自己的特色美食。美食吃前有期待、吃后有回味，已不仅仅是简单
								的味觉感受，更是一种精神享受。享受美食也要看场合，场合好美食吃起来才有味道。世界各地美食
								文化博大精深，营养物质各不相同，品味更多美食，享受更多健康，也让人吃的更加开心。</div>
							<div>
								<img src="/img/wdsc.png" />
								<img src="/img/news.png" />
							</div>
						</div>

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
