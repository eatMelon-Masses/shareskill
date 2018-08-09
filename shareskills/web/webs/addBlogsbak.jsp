<%@ page import="com.shareskill.model.TBlogcategorytag" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.shareskill.utils.AppContextFactory" %>
<%@ page import="com.shareskill.service.CategoryService" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%--
  Created by IntelliJ IDEA.
  User: z1176
  Date: 2018/5/14
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%@taglib prefix="mm" uri="http://www.shaareskill.com/mytaglib" %>


<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/headerTop.css" />
    <link rel="stylesheet" type="text/css" href="/css/writeBoke.css" />
    <script src="../ckeditor5/ckeditor.js"></script>
    <meta charset="GBK">
    <title></title>
</head>
<body>
<header class="top">
    <div class="logo"><a href="index.jsp"><img src="/img/logo (2).png" /></a></div>
    <nav class="topmain">
        <a href="boke.jsp">博客</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="download.jsp">下载</a>&nbsp;&nbsp;
        <a href="noteshare.html">便签分享</a>&nbsp;&nbsp;
        <a href="person.jsp">个人中心</a>&nbsp;&nbsp;
    </nav>
    <div class="topsearch">
        <input type="search" class="search" placeholder="搜索一下"/>
        <input type="image" src="/img/searchimg.png" class="searchbtn" />
    </div>
    <div class="boke"><img  class="bokeimg" src="/img/wboke.png" /></div>
    <nav class="toplogin">
        <a class="wboke" href="addBlogs.jsp">写博客</a>&nbsp;
        <a class="login" href="/writeSignForm">登录</a>&nbsp;
        <a class="enroll" href="enroll.jsp">注册</a>&nbsp;
    </nav>
</header>
<section>
    <form action="/addBlogs" method="post">
        <h2>标题：<input type="text" name="bwbt"/></h2>
        <h4>分类：
            <select name="blogCategoryId">

                <mm:seletor />
                <%--            <option value="关注">关注</option>
                            <option value="资讯">资讯</option>
                            <option value="时尚">时尚</option>
                            <option value="娱乐">娱乐</option>
                            <option value="生活">生活</option>
                            <option value="财经">财经</option>
                            <option value="体育">体育</option>
                            <option value="美食">美食</option>
                            <option value="电影">电影</option>
                            <option value="动漫">动漫</option>--%>

            </select>
        </h4>
        <textarea id="editor" name="bwnr" type="text" placeholder="说点什么吧！"></textarea>
        <input  id="sendboke"  type="submit" value="上传"/>

    </form>

</section>
<footer>
    <span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
    <span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
</footer>
</body>
<script>
/*    import Image from '@ckeditor/ckeditor5-image/src/image';
    import ImageToolbar from '@ckeditor/ckeditor5-image/src/imagetoolbar';
    import ImageCaption from '@ckeditor/ckeditor5-image/src/imagecaption';
    import ImageStyle from '@ckeditor/ckeditor5-image/src/imagestyle';*/

   /* ClassicEditor
        .create( document.querySelector( '#editor' ), {
            plugins: [ Image, ImageToolbar, ImageCaption, ImageStyle ],
            image: {
                toolbar: [ 'imageTextAlternative', '|', 'imageStyle:full', 'imageStyle:side' ]
            }
        } )
        .then( ... )
    .catch( ... );*/
/*    ClassicEditor
        .create( document.querySelector( '#editor' )  )
        .catch( error => {
        console.error( error );
    } )*/
ClassicEditor
    .create( document.querySelector( '#editor' ), {
        plugins: [ Image, ImageToolbar, ImageCaption, ImageStyle ],
        image: {
            toolbar: [ 'imageTextAlternative', '|', 'imageStyle:full', 'imageStyle:side' ]
        }
    } )
    .then( ... )
.catch( ... );
</script>
</html>

