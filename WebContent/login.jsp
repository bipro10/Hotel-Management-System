<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login page</title>
	<link rel="stylesheet" type="text/css" href="css/style.css"></link>
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css"></link>
</head>
<body>
	<div class="container">
		<img src="images/men1.png">
		<form method=get action=LoginServlet>
			<div class="form-input">
				<input type="text" name="uid" placeholder="Enter Username">
			</div>
			<div class="form-input">
				<input type="password" name="pwd" placeholder="Enter Password">
			</div>
			<input class="btn-login" type="submit" name="submit" value="LOGIN"><br/>
			<a href="#">Forget password?</a>
		</form>
	</div>
	<p>Developed by <font color=green>Biprpodas Roy</font></p>
</body>
</html>