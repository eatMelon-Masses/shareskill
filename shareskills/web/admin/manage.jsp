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
    <title>��̨����-������Դ����</title>
</head>
<body>
<header class="top">
    <div class="logo"><a href=""><img src="../img/logo.png" /></a><p>��ӭ����ORS��̨����</p></div>
</header>
<article>
    <ul id="menu">
        <li><input type="button" value="�˺Ź���">
            <ul>
                <li><input type="button" id="addAccount" value="�����˺�"></li>
                <li><input type="button" value="ɾ��/��ѯ�˺�"></li>
                <li><input type="button" value="�޸��˺�"></li>
            </ul>
        </li>
        <li><input type="button" value="���Ĺ���">
            <ul>
                <li><input type="button" value="����ȫ������"></li>
                <li><input type="button" value="���ķ������" onclick=""></li>
            </ul>
        </li>
        <li><input type="button" value="�����ǩ����">
            <ul>
                <li><input type="button" value="��ѯɾ�������ǩ"></li>
                <li><input type="button" value="�޸ķ����ǩ"></li>
            </ul>
        </li>
        <li><input type="button" value="������Դ����">
            <ul>
                <li><input type="button" value="������Դ"></li>
                <li><input type="button" value="ɾ��/��ѯ��Դ"></li>
                <li><input type="button" value="�޸���Դ"></li>
            </ul>
        </li>
    </ul>
    <section>
        <table id="accountTab">
            <thead>
            <th id="num">�к�</th>
            <th id="account">�˺�</th>
            <th id="sex">�Ա�</th>
            <th id="time">ע��ʱ��</th>
            <th id="state">�˺�״̬</th>
            <th id="remark">����</th>
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
            <th id="BQnum">�к�</th>
            <th id="BQname">�����ǩ��</th>
            <th id="BQremark">����</th>
            </thead>
            <tbody>
<%--            <tr>
                <td>1</td>
                <td>123</td>
                <td>2017-5-6</td>
                <td>����</td>
                <td><a href="">ɾ��</a><a href="">�޸�</a></td>
            </tr>--%>
                <% int i=0;%>
                <c:forEach var="tempCate" items="${cateList}">
                    <tr>
                        <td><c:out value="${i=i+1}"></c:out> </td>
                        <td><c:out value="${tempCate.flmc}"/> </td>
                        <td>����</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
    <section>
        <table>
            <thead>
            <th id="ZYnum">�к�</th>
            <th id="ZYname">��Դ��</th>
            <th id="account">�ϴ���Դ�û�</th>
            <th id="ZYtime">����ʱ��</th>
            <th id="ZYstate">��Դ״̬</th>
            <th id="ZYremark">����</th>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>123</td>
                <td>123</td>
                <td>2017-5-6</td>
                <td>����</td>
                <td><a href="">ɾ��</a><a href="">�޸�</a></td>
            </tr>
            <tr>
                <td>2</td>
                <td>124</td>
                <td>123</td>
                <td>2017-5-6</td>
                <td>����</td>
                <td><a href="">ɾ��</a><a href="">�޸�</a></td>
            </tr>
            </tbody>
        </table>
    </section>
</article>

</body>
</html>
