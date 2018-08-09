<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/css/enroll.css"/>
		<meta charset="utf-8" />
		<title>用户注册</title>
	</head>
	<body>
		<header>
			<div class="logo"><a href="index.jsp"><img src="/img/logo (2).png" /></a></div>
			<div class="exit">
				<a href="/writeSignForm">登录</a>&nbsp;&nbsp;
				<a href="/showHome">主页</a>
			</div>
		</header>
		<section class="enrollbg">
			<div class="enroll">
				<s:form commandName="user" action="/registerUser" method="post">
					<p>用户注册</p>
					<s:input type="text"  placeholder="请输入用户名" id="username" path="zh"/> <td><font color="red"><s:errors path="zh"/></font></td> <br>
					<ul class="enrollsex">
						<td>性别：</td><td><font color="red"><s:errors path="xb"/></font></td>
						<td><s:radiobutton  name="sex" value="男" path="xb"/>男</td>
						<td><s:radiobutton name="sex" value="女" path="xb"/>女</td>
					</ul>
					<s:errors path="dlmm" cssClass="errors"/>
					<s:input type="password"  placeholder="请输入密码" id="password" path="dlmm"/><td><font color="red"><s:errors path="dlmm"/></font></td> <br>
					<input type="password"  placeholder="请再次确认密码" id="password2"/><br>
					<s:input type="date" path="csny"/><td><font color="red"><s:errors path="csny"/></font></td><br>
					<s:input type="text" path="grjj"/><br>
					<input type="submit" name="enrollbtn" id="enrollbtn" value="注册" />
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
