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
		<springForm:form commandName="dept" method="GET">
			<h2>About</h2>

			<p>We are proud to present to you this very first version of our
				amazing staff managing software. This is the result of backbreaking
				work of our world class IT team...o_O...</p>

			<h2>Authorization</h2>

			<p>
				Use <strong><em>paul</em></strong> username and <strong><em>123</em></strong>
				password for admin access(authorities to add/remove and edit
				employees) and <strong><em>alex</em></strong> username and <strong><em>456</em></strong>
				password for user access (view-only authority).
			</p>

			<h2>Registration</h2>

			<p>You can also register new user with view-only authority.</p>

			<h2>Employee list</h2>

			<p>You can view our employee list by different departments or a
				full employee list(choosing 'All departments').</p>

			<table>
				<tr>
					<td><springForm:select path="id" cssStyle="width: 160px">
							<springForm:options items="${deptsList}" itemValue="id"
								itemLabel="name" />
							<springForm:option cssStyle="color:blue" label="All departments"
								value="0" />
						</springForm:select></td>
					<td><button formaction="employees">View</button></td>
				</tr>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<tr>
					<td colspan="2">
						<button formaction="dept" style="width: 100%">Edit /
							add dept</button>
					</td>
				</tr>
				</sec:authorize>
			</table>
		</springForm:form>

	</section>

	<%@ include file="/resources/jspfs/Footer.jspf"%>

</body>
</html>