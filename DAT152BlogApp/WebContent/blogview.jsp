<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment Page</title>
</head>
<body>

	<c:forEach var="cmt" items="${comments}">
		<p>${cmt}</p>
	</c:forEach>
	<p>
	<form action="blogservlet" method="post">
		<table>
			<tr><td>Comment</td><td><textarea name=comment rows=4 cols=40></textarea></td></tr>
			<tr><td><input type=submit name=submit value="Post Comment"></td></tr>
		</table>
	</form>
	
	<c:set var = "role" scope = "session" value = "${role}"/>
	<c:if test = "${role=='ADMIN'}">
		<form action="blogservlet" method="post">
			<input type=submit name=submit value="Delete Comments">
		</form>
	</c:if>
			
	<p><b>You are logged in as ${loggeduser}. <a href="logout">Log out</a></b></p>
	<form method="post" action="${logoutep}" >
		<input type="hidden" name="client_id" value="${clientid}">
		<input type="hidden" name="post_logout_redirect_uri" value= "${redirectep}">
		<input type=submit value="SSO:Logout">
	</form>
</body>
</html>