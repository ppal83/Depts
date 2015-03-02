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

<title>Depts list</title>
</head>
<body>

	<%@ include file="/resources/jspfs/Header.jspf"%>

	<%@ include file="/resources/jspfs/aside.jspf"%>

	<section>

		<p></p>

		<c:if test="${dept.id ne 0}">
			<h2>Edit dept</h2>
		</c:if>
		<c:if test="${dept.id eq 0}">
			<h2>Add dept</h2>
		</c:if>

		<c:url var="addAction" value="/dept/add"></c:url>

		<springForm:form id="dept_form" action="${addAction}"
			commandName="dept" method="GET">
			<table>
				<c:if test="${dept.id ne 0}">
					<tr>
						<td><springForm:label path="id">
								<spring:message text="ID" />
							</springForm:label></td>
						<td><springForm:input path="id" readonly="true"
								disabled="true" /> <springForm:hidden path="id" /></td>
					</tr>
				</c:if>
				<tr>
					<td><springForm:label path="name">
							<spring:message text="Department" />
						</springForm:label></td>
					<td><springForm:input path="name" /></td>
					<td><springForm:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="2"><c:if test="${dept.id ne 0}">
							<input type="submit" style="width: 230px"
								value="<spring:message text="Edit existing dept"/>" />
						</c:if> <c:if test="${dept.id eq 0}">
							<input type="submit" style="width: 230px"
								value="<spring:message text="Create new dept"/>" />
						</c:if></td>
				</tr>
			</table>
		</springForm:form>

		<p></p>
		<table class="tbl">
			<tr>
				<th width="40" align="center">Dept ID</th>
				<th width="160">Dept name</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
				<th width="60">View</th>
			</tr>
			<c:forEach items="${deptsList}" var="dept">
				<tr>
					<td align="center">${dept.id}</td>
					<td>${dept.name}</td>
					<td align="center">
						<a href="<c:url value="/dept/edit/${dept.id}" />"><button>Edit</button></a>
					</td>
					<td align="center">
						<a href="<c:url value="/dept/remove/${dept.id}" />"><button>Delete</button></a></td>
					<td align="center">
						<a href="<c:url value="/employees?id=${dept.id}" />"><button>View</button></a></td>
				</tr>
			</c:forEach>

		</table>

		<h4>
			<a href="<c:url value="/" />" class="back">Back to Main Page</a>
		</h4>

	</section>

	<%@ include file="/resources/jspfs/Footer.jspf"%>

</body>
</html>