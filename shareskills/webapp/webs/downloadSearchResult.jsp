<%--
  Created by IntelliJ IDEA.
  User: z1176
  Date: 2018/6/14
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="/common/web_head.jsp"%><html>
<	<head>
    <link rel="stylesheet" type="text/css" href="../css/headerTop.css" />
    <link rel="stylesheet" type="text/css" href="../css/download.css" />
    <script type="text/javascript" src="../js/download.js" ></script>
    <meta charset="UTF-8">
    <title></title>
</head>
    <body>
    <!-- 用户页面头部-->
        <%@include file="/common/user_web_head.jsp"%>
        <article>
            <header id="downloadTopbar">
                <a href="/showDownload">找资源</a>
                <a href="/browseDataResourceByUser">我的资源</a>
                <div id="sport"></div>
            </header>
            <section>
                <div class="downloadsearch">
                    <form action="/searchDataByCateAndKey" method="get">
                        <p>资源类型：</p>
                        <select name="cateId">
                            <c:forEach var="tempCate" items="${dataCategoryList}" >
                                <option  value="${tempCate.id}">${tempCate.flmc}</option>
                            </c:forEach>
                        </select><br/><br/><br/>
                        <span>关键字：</span>

                            <input id="keywork" name="keyWord" type="text" placeholder="请输入关键字"/>
                            <input id="dlSearchBtn" type="submit" value="搜索" />
                    </form>

                </div>
                <div class="xiaobian">
                    <p>搜索结果</p>
                    <div id="xiaobianIn">
                        <c:forEach var="tempData" items="${dataList}">
                            <div class="InDiv">
                                <h3><a href="/viewDataResourceByid?id=${tempData.id}">${tempData.zybt}</a></h3>
                                <div id="txt">
                                        ${tempData.zyjj}
                                </div>
                                <p>发布者:${tempData.userId.yhnc}</p><p>访问量:${tempData.djcs}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>

               <%-- <mm:searchDatapage  keyword="${keyWord}" cateId="${cateId}" url="${uri}" pageNo="${dataPage.pageNo}"/>--%>
                <!-- 分页区-->
                <div>
                    <c:if test="${page.pageTotal gt 1}">
                        当前第[${page.pageNo}/${page.pageTotal}]页[<a href="${page.uri}&pageNo=1">首页</a>]
                    </c:if>

                    <c:choose>
                        <c:when test="${page.prePageNo eq -1}">

                        </c:when>
                        <c:otherwise>
                            [<a href="${page.uri}&pageNo=${page.prePageNo}">上一页</a>]
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${page.nextPageNo eq -1}"></c:when>
                        <c:otherwise>
                            [<a href="${page.uri}&pageNo=${page.nextPageNo}">下一页</a>]
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${page.pageTotal gt 1}">
                        [<a href="${page.uri}&pageNo=${page.pageTotal}">尾页</a>]
                    </c:if>

                </div>
            </section>

        </article>
        <footer>
            <span><a href="#">知识产权</a> | <a href="#">关于我们</a> | <a href="#">联系我们</a> | <a href="#">有害信息举报 | </a></span>
            <span>ORS营业执照:000000000000 | 网络文化经营许可证:XXXX-XXX号 | 增值电信业务经营许可证:ORS-XXXXXXXX | 安全责任书 </span>
        </footer>
    </body>
</html>
