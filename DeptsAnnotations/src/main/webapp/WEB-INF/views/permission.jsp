<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/style.css" />">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="/resources/jspfs/Header.jspf"%>

	<%@ include file="/resources/jspfs/aside.jspf"%>

	<section>
		<h2>You have no permission to access this URL!</h2>
		<h4><a href="<c:url value="/login" />">Back</a></h4>
	</section>

	<%@ include file="/resources/jspfs/Footer.jspf"%>

</body>
</html>