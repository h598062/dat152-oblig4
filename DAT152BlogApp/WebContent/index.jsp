<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to the DAT152BlogApp</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<form action="login" method="post" autocomplete="off">
<div id="rcorners">
<h3>DAT152BlogWebApp Log in</h3>
<p><font color="red">${message}</font></p>
<table>
<tr>
<td><h5>Username</h5></td>
<td><input type="text" name="username" value="" size=20></td>
</tr>
<tr>
<td><h5>Password</h5></td><td><input type="password" name="password" value="" size=20></td>
</tr>
<tr>
<td><input type=submit name="submit" value="Login"></td><td><a href="register.jsp">Register</a></td>
</tr>
</table>
</div>
</form>
<p>
<form id="ssoform" method="get" action="sso">
<div id="rcorners">
<input type="hidden" name="response_type" value="code">
<input type="hidden" name="scope" value="openid phone profile">
<input type="hidden" name="client_id" value="${client_id}">
<input type="hidden" name="state" value="${state}">
<input type="hidden" name="redirect_uri" value="${redirect_url}">
<input type=submit value="Login with Keycloak">
</div>
</form>

</body>
</html>