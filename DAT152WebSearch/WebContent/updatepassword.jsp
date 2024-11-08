<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Password Update for Existing User</title>
</head>
<script>
	function validateForm() {
		var password = document.forms["updatePasswordForm"]["passwordnew"].value;
		var confirmPassword = document.forms["updatePasswordForm"]["confirm_passwordnew"].value;
		var message="";

		if (password.length < 8 || password.length > 20) {
			message += "Password must be between 8 and 20 characters long.\n";
		}
		if (!/[a-z]/.test(password)) {
			message += "Password must contain at least one lowercase letter.\n";
		}
		if (!/[A-Z]/.test(password)) {
			message += "Password must contain at least one uppercase letter.\n";
		}
		if (!/\d/.test(password)) {
			message += "Password must contain at least one digit.\n";
		}
		if (!/[@#$%^&+=]/.test(password)) {
			message += "Password must contain at least one special character.\n";
		}
		if (password !== confirmPassword) {
			message += "Passwords do not match.\n";
		}

		if (message) {
			alert(message);
			return false;
		}
		return true;
	}
</script>
<body>
	<h3>Update Password for existing user</h3>
	<p><font color="red">${message}</font></p>
	<form name="updatePasswordForm" action=updatepassword method="post" onsubmit="return validateForm()">
	<table>
	<tr>
		<td><p>New Password </td><td><input type="password" name="passwordnew" placeholder="new password" /></td>
	</tr>
	<tr>
		<td><p>Confirm New Password </td> <td><input type="password" name="confirm_passwordnew" placeholder="confirm new password" /></td>
	</tr>
	<tr>
		<td><p><input type="submit" value="Update Password"/></p></td>
	</tr>
	</table>
	</form>

	<br>
	<p><a href="searchpage">Back to Main search page</a></p>
	<p><b>You are logged in as ${user.username}. <a href="logout">Log out</a></b></p>

</body>
</html>