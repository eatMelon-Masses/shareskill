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
        <a href="boke.jsp">����</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="download.jsp">����</a>&nbsp;&nbsp;
        <a href="noteshare.html">��ǩ����</a>&nbsp;&nbsp;
        <a href="person.jsp">��������</a>&nbsp;&nbsp;
    </nav>
    <div class="topsearch">
        <input type="search" class="search" placeholder="����һ��"/>
        <input type="image" src="/img/searchimg.png" class="searchbtn" />
    </div>
    <div class="boke"><img  class="bokeimg" src="/img/wboke.png" /></div>
    <nav class="toplogin">
        <a class="wboke" href="addBlogs.jsp">д����</a>&nbsp;
        <a class="login" href="/writeSignForm">��¼</a>&nbsp;
        <a class="enroll" href="enroll.jsp">ע��</a>&nbsp;
    </nav>
</header>
<section>
    <form action="/addBlogs" method="post">
        <h2>���⣺<input type="text" name="bwbt"/></h2>
        <h4>���ࣺ
            <select name="blogCategoryId">

                <mm:seletor />
                <%--            <option value="��ע">��ע</option>
                            <option value="��Ѷ">��Ѷ</option>
                            <option value="ʱ��">ʱ��</option>
                            <option value="����">����</option>
                            <option value="����">����</option>
                            <option value="�ƾ�">�ƾ�</option>
                            <option value="����">����</option>
                            <option value="��ʳ">��ʳ</option>
                            <option value="��Ӱ">��Ӱ</option>
                            <option value="����">����</option>--%>

            </select>
        </h4>
        <textarea id="editor" name="bwnr" type="text" placeholder="˵��ʲô�ɣ�"></textarea>
        <input  id="sendboke"  type="submit" value="�ϴ�"/>

    </form>

</section>
<footer>
    <span><a href="#">֪ʶ��Ȩ</a> | <a href="#">��������</a> | <a href="#">��ϵ����</a> | <a href="#">�к���Ϣ�ٱ� | </a></span>
    <span>ORSӪҵִ��:000000000000 | �����Ļ���Ӫ���֤:XXXX-XXX�� | ��ֵ����ҵ��Ӫ���֤:ORS-XXXXXXXX | ��ȫ������ </span>
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

