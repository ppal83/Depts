<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/style.css" />">
<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/messages.css" />">
<title>Login Page</title>
</head>
<body>

	<%@ include file="/resources/jspfs/Header.jspf"%>

	<%@ include file="/resources/jspfs/aside.jspf"%>

	<section>
		<h2>Login with Username and Password</h2>
		
			<c:if test="${message eq 'success'}">
				<div class="successMessage">
					You've been succefully registered,
					now you can login using your username and password.
				</div>
			</c:if>
			
		<c:url var="loginUrl" value="/login"></c:url>
		
		<form action="${loginUrl}" method="POST">
			<table>
				<tr>
					<td>User ID:</td>
					<td><input type='text' name='username' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Login" /></td>
				</tr>
			</table>
		</form>
		
		If you are a new user, please
		<a href="<c:url value="/registration" />">register</a>

		<h4>
			<a href="<c:url value="/" />" class="back">Back to Main Page</a>
		</h4>
		
	</section>

	<%@ include file="/resources/jspfs/Footer.jspf"%>
	
</body>
</html>