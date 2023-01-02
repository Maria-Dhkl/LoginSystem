<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form action="Registration" method="POST">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" required></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$" title="Must be in the format abc@def.xyz" required></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"  title="Must contain at least one number and one uppercase and lowercase letter and at least one special character, and at least 8 or more characters" required>
					
				</td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" required></td>
			</tr>
			<tr>
				<td>Contact Number:</td>
				<td><input type="tel" name="phoneNumber" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Register"></td>
			</tr>
		</table>
	</form>
	
	<script type="text/javascript">
	function showPassword() {
		  let pass = document.getElementById("pass");
		  if (pass.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
		}
	</script>
</body>
</html>