<%--
  Created by IntelliJ IDEA.
  User: z1176
  Date: 2018/5/20
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://www.shaareskill.com/corelib" %>
<%@taglib prefix="mm" uri="http://www.shaareskill.com/mytaglib" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="/layer/layer.js"></script>