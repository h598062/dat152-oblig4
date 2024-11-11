<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="owasp.encoder.jakarta" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My details</title>
</head>
<body>
	<h3>Search Results</h3>
	<p><b>Search key: ${e:forHtml(searchkey)}</b></p>
	<c:forEach var="entry" items="${e:forHtml(result)}">
		<p>${entry}</p>
	</c:forEach><br>
	<p><a href="searchpage">Back to Main search page</a></p>
	<p><b>You are logged in as ${e:forHtml(user.username)}. <a href="logout">Log out</a></b></p>
</body>
</html>
