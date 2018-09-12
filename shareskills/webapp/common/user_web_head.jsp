<%--
  Created by IntelliJ IDEA.
  User: z1176
  Date: 2018/6/8
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<header class="top">
    <div class="logo"><a href="/showHome"><img src="../img/logo (2).png" /></a></div>
    <nav class="topmain">
        <a href="/showHome">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/browseBlogsByCate">博客</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/showDownload">下载</a>&nbsp;&nbsp;
        <a href="/showUserBlogSphereByType">便签分享</a>&nbsp;&nbsp;
        <a href="/showMypersionInf">个人中心</a>&nbsp;&nbsp;
     <%--   <a href="/writeDataResource">数据上传</a>--%>
    </nav>
    <div class="topsearch">
        <form method="post" action="/searchAllBlogs">
            <input type="text"  name="searchContent" class="search" placeholder="搜索一下"/>
            <input type="submit" src="../img/searchbg.png" class="searchbtn" value="" />
        </form>
    </div>
    <div class="boke"><img  class="bokeimg" src="../img/wboke.png" /></div>
    <nav class="toplogin">
        <a class="wboke" href="/preAddBlog">写博客</a>&nbsp;
        <c:if test="${empty mumber}">

        </c:if>
        <c:choose >
            <c:when test="${empty mumber}">
                <a class="enroll" href="/writeReisForm">注册</a>&nbsp;
                <a class="login" href="/writeSignForm">登录</a>&nbsp;
            </c:when>
            <c:otherwise>
                <a class="login" href="/showMypersionInf">欢迎用户:${mumber.yhnc}访问</a>&nbsp;
                <a class="enroll" href="/logoutMember">退出</a>&nbsp;
            </c:otherwise>
        </c:choose>
       <%-- <c:choose >
            <c:when test="${empty mumber}">
                <a class="enroll" href="/writeReisForm">注册</a>&nbsp;
                <a class="login" href="/writeSignForm">登录</a>&nbsp;
            </c:when>
            <c:otherwise>
                <a class="login" href="">欢迎用户:${mumber.yhnc}访问</a>&nbsp;
                <a class="enroll" href="/logoutMember">退出</a>&nbsp;
            </c:otherwise>
        </c:choose>--%>
    </nav>
</header>

