<%--
  Created by IntelliJ IDEA.
  User: z1176
  Date: 2018/5/21
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@include file="/common/web_head.jsp"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/manage.css" />
    <link rel="stylesheet" type="text/css" href="../css/headerTop.css" />
    <script src="../js/manage.js" type="text/javascript" charset="UTF-8" ></script>
    <!--<script src="js/ajaxFile.js"></script>-->
    <meta charset="GBK">
    <title>后台管理-在线资源分享</title>
</head>
<body>
<header class="top">
    <div class="logo"><a href=""><img src="../img/logo.png" /></a><p>欢迎进入ORS后台管理！</p></div>
</header>
<article>
    <ul id="menu">
        <li><input type="button" value="账号管理">
            <ul>
                <li><input type="button" id="addAccount" value="新增账号"></li>
                <li><input type="button" value="删除/查询账号"></li>
                <li><input type="button" value="修改账号"></li>
            </ul>
        </li>
        <li><input type="button" value="博文管理">
            <ul>
                <li><input type="button" value="博文全部管理"></li>
                <li><input type="button" value="博文分类管理" onclick=""></li>
            </ul>
        </li>
        <li><input type="button" value="分类标签管理">
            <ul>
                <li><input type="button" value="查询删除分类标签"></li>
                <li><input type="button" value="修改分类标签"></li>
            </ul>
        </li>
        <li><input type="button" value="数据资源管理">
            <ul>
                <li><input type="button" value="新增资源"></li>
                <li><input type="button" value="删除/查询资源"></li>
                <li><input type="button" value="修改资源"></li>
            </ul>
        </li>
    </ul>
    <section>
        <table id="accountTab">
            <thead>
            <th id="num">行号</th>
            <th id="account">账号</th>
            <th id="sex">性别</th>
            <th id="time">注册时间</th>
            <th id="state">账号状态</th>
            <th id="remark">操作</th>
            </thead>
            <tbody>
            <mm:showAllUser userList="${userList}"/>
            </tbody>
        </table>
    </section>

    <section>
        <ul class="classify">
            <mm:showAllCateList cateList="${cateList}"/>
        </ul>
        <div></div>
    </section>
    <section>
        <table>
            <thead>
            <th id="BQnum">行号</th>
            <th id="BQname">分类标签名</th>
            <th id="BQremark">操作</th>
            </thead>
            <tbody>
<%--            <tr>
                <td>1</td>
                <td>123</td>
                <td>2017-5-6</td>
                <td>可用</td>
                <td><a href="">删除</a><a href="">修改</a></td>
            </tr>--%>
                <% int i=0;%>
                <c:forEach var="tempCate" items="${cateList}">
                    <tr>
                        <td><c:out value="${i=i+1}"></c:out> </td>
                        <td><c:out value="${tempCate.flmc}"/> </td>
                        <td>可用</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
    <section>
        <table>
            <thead>
            <th id="ZYnum">行号</th>
            <th id="ZYname">资源名</th>
            <th id="account">上传资源用户</th>
            <th id="ZYtime">创建时间</th>
            <th id="ZYstate">资源状态</th>
            <th id="ZYremark">操作</th>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>123</td>
                <td>123</td>
                <td>2017-5-6</td>
                <td>可用</td>
                <td><a href="">删除</a><a href="">修改</a></td>
            </tr>
            <tr>
                <td>2</td>
                <td>124</td>
                <td>123</td>
                <td>2017-5-6</td>
                <td>可用</td>
                <td><a href="">删除</a><a href="">修改</a></td>
            </tr>
            </tbody>
        </table>
    </section>
</article>

</body>
</html>
