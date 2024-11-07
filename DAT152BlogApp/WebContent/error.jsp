<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<div>
		<h2>An error has occured!</h2>
		<p><font color="red">${message}</font></p>
	</div>
	<p>
	<a href="index.jsp">Back to Login Page</a>
</body>
</html>