<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<!DOCTYPE html>
<html>

<head>

<title>Registration page</title>

<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/style.css" />">
<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/messages.css" />">

<script src="<c:url value = "/resources/js/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value = "/resources/js/jquery.validate.min.js" />"></script>
<script src="<c:url value = "/resources/js/user.validation.js" />"></script>

</head>

<body>

	<%@ include file="/resources/jspfs/Header.jspf"%>

	<%@ include file="/resources/jspfs/aside.jspf"%>

	<section>
		<h2>Please provide a user registration data below</h2>

		<c:url var="registerAction" value="/register"></c:url>
		<p></p>
		<springForm:form id="reg_form" action="${registerAction}" commandName="user"
			method="POST">
			<table>
				<tr>
					<td><springForm:label path="username">
							<spring:message text="Username:" />
						</springForm:label></td>
					<td><springForm:input path="username" /></td>
					<td><springForm:errors path="username" cssClass="error" /></td>
				</tr>
				<tr>
					<td><springForm:label path="password">
							<spring:message text="Password:" />
						</springForm:label></td>
					<td><springForm:password path="password" /></td>
					<td><springForm:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td><springForm:label path="email">
							<spring:message text="Email:" />
						</springForm:label></td>
					<td><springForm:input path="email" /></td>
					<td><springForm:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>
						<input type="submit" 
									value="<spring:message text="Register user"/>" />
					</td>
				</tr>
			</table>

		</springForm:form>
		
		If you are registered user, please
		<a href="<c:url value="/login" />">login</a>
		
		<h4>
			<a href="<c:url value="/" />" class="back">Back to Main Page</a>
		</h4>
	
	</section>
	
	<%@ include file="/resources/jspfs/Footer.jspf"%>

</body>

</html>