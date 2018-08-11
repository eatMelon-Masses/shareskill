<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>registerAdmin</title>
 

  </head>
  
  <body>
  	<form:form commandName="admin" action="/admin/saveadmin" method="get">
  	<fieldset>
  		<legend>Add a admin</legend>
  		<p>
  			<label for="Zh">账户:</label>
  			<form:input id="zh" path="zh"/>
  		</p>
  		 <p>
  			<label for="Mm">密码:</label>
  			<form:input id="mm" path="mm"/>
  		</p>
  		<p id="buttons">
  			<input id="reset" type="reset" >
  			<input id="submit" type="submit" >
  		</p>
  	</fieldset>
  	</form:form>
  </body>
</html>
