<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%><!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="../css/headerTop.css" />
		<link rel="stylesheet" type="text/css" href="../css/editorPerson.css" />
		<script type="text/javascript" src="../js/editorPerson.js" ></script>
		<meta charset="UTF-8">
		<title>编辑个人资料</title>
	</head>
	<body>
	<!-- 用户页面头部-->
	<%@include file="/common/user_web_head.jsp"%>
		<section>
			<div class="inner">
				<p>个人资料</p>
				<s:form id="personInner" commandName="newUser" method="post" action="/updateUser">
					昵称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:input id="username" path="yhnc" type="text" value="${oldUser.yhnc}"/><br/>
					出生年月：<input path="csny" id="birth" type="date" value=<fmt:formatDate value="${oldUser.csny}"  pattern="yyyy-MM-dd"/>><br/>
					个性签名：<textarea name="grjj" id="sign" type="text"   >${oldUser.grjj}</textarea>

				<p>修改密码</p>
					当前密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="oldpwd" type="password" /><br/>
					输入新密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:input path="dlmm" type="password" /><br/>
					再次输入新密码：<input type="password" />
				<input id="submit" type="submit" formmethod="post" onclick="formsubmit()"/>
				</s:form>
			</div>
		</section>
	</body>
	<c:if test="${not empty massage }">
		<script>
            layer.alert('${massage}', {icon: 6});
		</script>
	</c:if>
</html>
