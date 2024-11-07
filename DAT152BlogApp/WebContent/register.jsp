<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register New User</title>
</head>
<body>

<form action=register method="post" autocomplete="on">
<div id="rcorners">
<h3>Register as a new user</h3>
<table>
<tr>
<td><h5>Username</h5></td>
<td><input type="text" name="username" value="" size=20></td>
</tr>
<tr>
<td><h5>Mobile</h5></td>
<td><input type="text" name="phone" value="" size=20></td>
</tr>
<tr>
<td><h5>Password</h5></td><td><input type="password" name="password" value="" size=20></td>
</tr>
<tr>
<td><h5>Confirm Password</h5></td><td><input type="password" name="password2" value="" size=20></td>
</tr>
<tr>
<td><input type=submit name=nuser value="Register"></td><td><a href="index.jsp">Home</a></td>
</tr>
</table>
</div>
</form>

</body>
</html>