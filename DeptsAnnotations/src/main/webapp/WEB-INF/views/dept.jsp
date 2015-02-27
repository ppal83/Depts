<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/style.css" />">

<title>Home page</title>
</head>
<body>

	<%@ include file="/resources/jspfs/Header.jspf"%>

	<%@ include file="/resources/jspfs/aside.jspf"%>

	<section>

		<p></p>
		<springForm:form id="dept_form" commandName="dept" method="GET">
			<table>
				<tr>
					<td><springForm:label path="id">
							<spring:message text="ID" />
						</springForm:label></td>
					<td colspan="2"><springForm:input path="id" readonly="true"
							disabled="true" cssStyle="width: 197px" /> <springForm:hidden
							path="id" /></td>
				</tr>
				<tr>
					<td><springForm:label path="name">
							<spring:message text="Department" />
						</springForm:label></td>
					<td colspan="2"><springForm:input path="name"
							cssStyle="width: 197px" /></td>
					<td><springForm:errors path="name" cssClass="error" /></td>
					<td><c:if test="${not empty error}">
							<div class="error">${error}</div>
						</c:if></td>
				</tr>
				<tr>
					<td><button formaction="newdept">Create new</button></td>
					<td><button formaction="updatedept">Update current</button></td>
					<td><button formaction="deletedept">Delete current</button></td>
				</tr>
			</table>
		</springForm:form>

		<h4>
			<a href="<c:url value="/" />" class="back">Back to Main Page</a>
		</h4>

	</section>

	<%@ include file="/resources/jspfs/Footer.jspf"%>

</body>
</html>