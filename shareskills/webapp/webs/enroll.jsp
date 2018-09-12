<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/enroll.css"/>
		<script src="../js/enroll.js" type="text/javascript" charset="utf-8"></script>
		<meta charset="utf-8" />
		<title>用户注册</title>
	</head>
	<body>
		<header>
			<div class="logo"><a href="/showHome"><img src="/img/logo (2).png" /></a></div>
			<div class="exit">
				<a href="/writeSignForm">登录</a>&nbsp;&nbsp;
				<a href="/showHome">主页</a>
			</div>
		</header>
		<section class="enrollbg">
			<div class="enroll">
				<s:form commandName="user" action="/registerUser" method="post">
					<p>用户注册</p>
					<s:input type="text"  placeholder="请输入用户名" id="username" path="zh"/> <s:errors path="zh" cssClass="errors" /> <br>
					<ul class="enrollsex">
						<td>性别：</td>
						<td><s:radiobutton  name="sex" value="男" path="xb"/>男</td>
						<td><s:radiobutton name="sex" value="女" path="xb"/>女</td>
						<s:errors path="xb" cssClass="errors1"/>
					</ul>
					<s:input type="password"  placeholder="请输入密码" id="password" path="dlmm"/><s:errors path="dlmm" cssClass="errors"/> <br>
					<input type="password"  placeholder="请再次确认密码" id="password2"/><s:errors path="dlmm" cssClass="errors"/><br>
					<s:input type="date" path="csny" id="csny" placeholder="请输入出生年月"/><s:errors path="csny" cssClass="errors"/><br>
					<s:input type="text" path="grjj" id="grjj" placeholder="请输入个人简介"/><br>
					<input type="text" id="codeTxt" placeholder=" 验证码" />
					<div class="code" id="codeDiv" onclick="createCode()" title="看不清?点击此处换一张"></div>
					<input type="submit" name="enrollbtn" id="enrollbtn" onclick="return judgeCode()" value="注册" />
					<a href="/writeSignForm">已有账号？立即登录</a>
					<%--<s:errors path="user.zh"/>--%>
				</s:form>
			</div>
		</section>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
</html>
