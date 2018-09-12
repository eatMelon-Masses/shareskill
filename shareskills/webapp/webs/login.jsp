<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/login.css"/>
		<script src="/js/login.js" type="text/javascript" charset="utf-8"></script>
		<meta http-equiv="content-type" content="text/html" charset="utf-8" />
		<title>用户登录</title>
	</head>
	<body>
		<header>
			<div class="logo"><a href="/showHome"><img src="/img/logo (2).png" /></a></div>
			<div class="exit">
				<a class="enroll" href="/writeReisForm">注册</a>&nbsp;&nbsp;
			    <a href="/showHome">主页</a>
			</div>
		</header> 
		<section class="loginbg">
			<div class="login">
				<s:form commandName="user" action="/signInMember" method="post">
						<p>用户登录</p>
						<s:input type="text" placeholder="请输入用户名" id="zh" name="zh" path="zh"/><br>
						<s:input type="password" placeholder="请输入密码" id="dlmm" name="dlmm" path="dlmm"/><br>
						<div class="rembpwd">
							<input type="checkbox" id="rembpwd" onmouseover="mouseover()" onmouseout="mouseout()"/>记住我的密码
							<div id="ts">为了您的信息安全，请您不要在公共的设备上开启记住密码哦！</div>
						</div>
						<input type="submit" name="loginbtn" id="loginbtn" value="登录" />
				</s:form>
				<a href="/writeReisForm" id="aEnroll">没有账号?去注册</a>
			</div>
		</section>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
	<script>
		<c:if test="${not empty massage }">
				layer.alert('${massage}', {icon: 6});
		</c:if>
	</script>
</html>
