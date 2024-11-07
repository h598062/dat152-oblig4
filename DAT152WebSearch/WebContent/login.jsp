<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h3>Log in</h3>
	<p><font color="red">${message}</font></p>
	<form action="login" method="post">
		<p>Username <input type="text" name="username" value="${username}"/></p>
		<p>Password <input type="password" name="password" value="${password}"/></p>
		<input type="hidden" name="client_id" value="${client_id}"/>
		<input type="hidden" name="response_type" value="${response_type}"/>
		<input type="hidden" name="scope" value="${scope}"/>
		<input type="hidden" name="redirect_uri" value="${redirect_uri}"/>
		<input type="hidden" name="state" value="${state}"/>
		<p><input type="submit" value="Log in"/></p>
	</form>
	<p><a href="index.jsp">Back</a></p>

</body>
</html>