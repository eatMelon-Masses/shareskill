<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%><!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../css/headerTop.css" />
	<link rel="stylesheet" type="text/css" href="../css/person.css" />
	<script src="../js/person.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="../js/jquery-1.11.0.js" ></script>
	<meta charset="UTF-8">
	<title></title>
</head>
	<body>
	<!-- 用户页面头部-->
	<%@include file="/common/user_web_head.jsp"%>
	<article>
	<label for="bgfile">
		<div class="bgimg"><!--背景图片 -->
			<c:choose>
				<c:when test="${not empty user.userBgImg}">
					<img src="${basePath}${user.userBgImg}" id="bgimg"/>
				</c:when>
				<c:otherwise>
					<img src="../img/hand1.png" id="bgimg"/>
				</c:otherwise>
			</c:choose>
			<input type="file" id="bgfile"  name="upload" style="display: none;" />
			<input type="button" value="上传背景图片" onclick="saveUser1()"/>
		</div></label>
		<%--<div class="personsourse">
			<form action="/uploaduserImg" enctype="multipart/form-data" method="post">
				<c:choose>
					<c:when test="${not empty user.userImg}">
						<img src="${user.userImg}"/>
					</c:when>
					<c:otherwise>
						<img src="../img/hand1.png" />
					</c:otherwise>
				</c:choose>
				<input type="file" name="upload" >
				<input type="submit" value="上传">
			</form>

			<div>
				<h3>${user.yhnc}</h3>
				<c:choose>
					<c:when test="${empty user.grjj}">
						<p>您还没有个人资料哦</p>
					</c:when>
					<c:otherwise>
						<p>${user.grjj}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<a href="/viewMyInf">编辑个人资料</a>
		</div>--%>
		<div class="personsourse">
			<div action="/uploaduserImg" enctype="multipart/form-data" method="post" id="accountS">
				<label for="file">
					<c:choose>
						<c:when test="${not empty user.userImg}">
							<img src="${basePath}${user.userImg}" id="img"/>
						</c:when>
						<c:otherwise>
							<img src="../img/hand1.png" id="img"/>
						</c:otherwise>
					</c:choose></label><br/>
				<input type="file" name="upload" id="file" style="display: none;"/>
				<input type="button" value="上传头像" id="submit" onclick="saveUser2()" />
			</div>

			<div>
				<h3>${user.yhnc}</h3>
				<c:choose>
					<c:when test="${empty user.grjj}">
						<p>您还没有个人资料哦</p>
					</c:when>
					<c:otherwise>
						<p>${user.grjj}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<a href="/viewMyInf">编辑个人资料</a>

		</div>
	</article>
	<section id="person">
		<div id="personMenu">
			<input type="button" value="动态"/>
			<input type="button" value="评论"/>
			<input type="button" value="博客"/>
			<input type="button" value="图片"/>
		</div>
		<section>
			<p>我的动态</p>
			<div>
				<c:forEach var="tempbc" items="${bpList}">
					<div class="myDynamic">
						<div class="txt">
								${tempbc.userWord}
						</div>
						<p><fmt:formatDate value="${tempbc.wordDate}" pattern="MM-dd E hh:mm"/></p>
					</div>
				</c:forEach>
			</div>
		</section>
		<section>
			<p>我的评论</p>
			<div>
				<c:forEach var="tempbc" items="${bcList}">
					<div class="myVisit">
						<div class="txt">
							${tempbc.plnr}
						</div>
						<p><fmt:formatDate value="${tempbc.plsj}" pattern="MM-dd E hh:mm"/></p>
						<a class="delete" href="/delBlogComment?blogId=${tempbc.blog}&bcId=${tempbc.id}&type=1">删除评论</a>
					</div>
				</c:forEach>
			</div>
		</section>
		<section>
			<p>我的博客</p>
			<div>
				<c:forEach var="tempBlog" items="${blogList}">
					<div class="myDynamic">
						<div class="txt">
							${tempBlog.bwnr}
						</div>
						<p>${tempBlog.bwcjsj}</p><p>访问量:${tempBlog.bwdjcs}</p><a href="/delBlog?id=${tempBlog.id}" class="delete">删除博客</a>
					</div>
				</c:forEach>
			</div>
		</section>
		<section>
			<p>我的图片</p>
		</section>
	</section>
	<div class="personvisit">
		<img src="../img/bg (2).jpg" />
		<div class="personvisitIn">
			<p>关注 12</p><p>粉丝 10</p><p>浏览量 111</p>
		</div>
	</div>
	</body>


	<script>

		<c:choose>
			<c:when test="${not empty massage }">
				layer.alert('${massage}', {icon: 6});
			</c:when>
		</c:choose>

        $("#file").change(function (){
            var objUrl = getObjectURL(this.files[0]) ;
            if(objUrl){
                $("#img").attr("src",objUrl);
            }
        });
        $("#bgfile").change(function (){
            var objUrl = getObjectURL(this.files[0]) ;
            if(objUrl){
                $("#bgimg").attr("src",objUrl);
            }
        });
        //请求
        //  $("#submit").click(function (){
        //  	var imgurl=document.getElementById("file").value;
        //  	$.ajaxFileUpload({
        //          url:"http://119.23.213.108/json/uploaduserImg",
        //          fileElementId: "upload", //文件上传域的ID，这里是input的ID，而不是img的
        //          dataType: 'json', //返回值类型 一般设置为json
        //          contentType: "application/x-www-form-urlencoded; charset=utf-8",
        //          success: function (data) {
        //              alert(data.code+" "+ data.msg);
        //              if (data.code==200){
        //
        //              }
        //          }
        //     });
        //  });

        //建立一個可存取到該file的url
        function getObjectURL(file) {
            var url = "" ;
            if (window.createObjectURL!=undefined) { // basic
                url = window.createObjectURL(file) ;
            } else if (window.URL!=undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file) ;
            } else if (window.webkitURL!=undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file) ;
            }
            return url ;
        }
	</script>

</html>
