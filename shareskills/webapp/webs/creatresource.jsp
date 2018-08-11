<!DOCTYPE html>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
	<head>
        <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/headerTop.css" />
		<link rel="stylesheet" type="text/css" href="../css/creatresource.css" />
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
	<!-- 用户页面头部-->
	<%@include file="/common/user_web_head.jsp"%>
		<section>
<%--            <form id="uploadForm" enctype="multipart/form-data"  >
                <input type="file" id="creatFile" name="upload"/>
            </form>
            <button id="upload">上传文件</button>--%>
			<s:form method="post" action="/addDataResource" name="data" commandName="data" enctype="multipart/form-data" >
				<h2>标题：<s:input type="text" path="zybt" /></h2>
				<h4>分类：
					<s:select path="zylx">
						<c:forEach var="tempCate" items="${dataCategoryList}">
							<option value="${tempCate.id}">${tempCate.flmc}</option>
						</c:forEach>
					</s:select>
				</h4>
				<input type="file" id="creatFile" name="upload"/>

                <s:textarea id="shuoshuo" type="text"  placeholder="说点什么吧！" path="zyjj"></s:textarea>
				<input id="complete" type="submit" value="完成"/>
			</s:form>

		</section>
		<footer>
			<span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
			<span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
		</footer>
	</body>
    <script type="text/javascript">
        $(function () {
            $("#upload").click(function () {
                var formData = new FormData($('#uploadForm')[0]);
                $.ajax({
                    type: 'post',
                    url: "/upLoadFile",
                    data: formData,
                    cache: false,
                    processData: false,
                    contentType: false,
                }).success(function (data) {
                    alert(data);
                }).error(function () {
                    alert("上传失败");
                });
            });
        });
    </script>
</html>
