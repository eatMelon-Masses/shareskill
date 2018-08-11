<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<@
<%@include file="/common/web_head.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <c:out value="helloworld"/>
    <!-- ??????????????? -->
    <mm:msgdialog basepath="<%=basePath%>" height="1020" top="140">

    </mm:msgdialog>
  </body>
</html>
