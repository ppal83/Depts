<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/style.css" />">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error 405</title>
</head>
<body>

	<%@ include file="/resources/jspfs/Header.jspf"%>

	<aside></aside>

	<section>
		<h2>Request method 'GET' not supported</h2>
		<h3>Error 405</h3>
		
		<h4>
			<a href="<c:url value="/" />" class="back">To Main Page</a>
		</h4>
	</section>

	<%@ include file="/resources/jspfs/Footer.jspf"%>

</body>
</html>