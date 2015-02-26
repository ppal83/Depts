<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>

<title>Employees list</title>

<link rel="stylesheet"
	href="<c:url value = "/resources/theme/css/style.css" />">

<script src="<c:url value = "/resources/js/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value = "/resources/js/jquery.validate.min.js" />"></script>
<script src="<c:url value = "/resources/js/employee.validation.js" />"></script>

</head>
<body>
	<%@ include file="/resources/jspfs/Header.jspf"%>
	
	<%@ include file="/resources/jspfs/aside.jspf"%>

	<section>

		<sec:authorize access="hasRole('ROLE_ADMIN')">
			
				<c:if test="${employee.id ne 0}">
					<h2>Edit employee</h2>
				</c:if>
				<c:if test="${employee.id eq 0}">
					<h2>Add employee</h2>
				</c:if>
			
			<c:url var="addAction" value="/employee/add"></c:url>
			
				<springForm:form id="empl_form" action="${addAction}" commandName="employee"
					method="GET">
					<table>
						<c:if test="${employee.id ne 0}">
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
									<spring:message text="Name" />
								</springForm:label></td>
							<td><springForm:input path="name" /></td>
							<td><springForm:errors path="name" cssClass="error" /></td>
						</tr>
						<tr>
							<td><springForm:label path="birthDate">
									<spring:message text="Birthday" />
								</springForm:label></td>
							<td><springForm:input path="birthDate"
									placeholder="yyyy-MM-dd" /></td>
							<td><springForm:errors path="birthDate" cssClass="error" /></td>
						</tr>
						<tr>
							<td><springForm:label path="hireDate">
									<spring:message text="Hire date" />
								</springForm:label></td>
							<td><springForm:input path="hireDate"
									placeholder="yyyy-MM-dd" /></td>
							<td><springForm:errors path="hireDate" cssClass="error" /></td>
						</tr>
						<tr>
							<td><springForm:label path="address">
									<spring:message text="Address" />
								</springForm:label></td>
							<td><springForm:input path="address" /></td>
							<td><springForm:errors path="address" cssClass="error" /></td>
						</tr>
						<tr>
							<td><springForm:label path="dept.name">
									<spring:message text="Department" />
								</springForm:label>
							</td>
							<td><select name="deptId" style="width: 153px">
									<c:if test="${!empty employee.dept.name}">
										<option value="${employee.dept.id}">${employee.dept.name}
									</c:if>
									<c:forEach items="${deptsList}" var="dept">
										<c:if test="${dept.id ne employee.dept.id}">
											<option value="${dept.id}">${dept.name}
										</c:if>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><springForm:label path="salary">
									<spring:message text="Salary" />
								</springForm:label></td>
							<td><c:if test="${employee.salary ne 0}">
									<springForm:input path="salary" />
								</c:if> <c:if test="${employee.salary eq 0}">
									<springForm:input path="salary" />
								</c:if></td>
							<td><springForm:errors path="salary" cssClass="error" /></td>
						</tr>
						<tr>
							<td colspan="2"><c:if test="${employee.id ne 0}">
									<input type="submit" style="width: 230px"
										value="<spring:message text="Edit employee"/>" />
								</c:if> <c:if test="${employee.id eq 0}">
									<input type="submit" style="width: 230px"
										value="<spring:message text="Add employee"/>" />
								</c:if></td>
						</tr>
					</table>
				</springForm:form>
			
		</sec:authorize>

		<c:if test="${!empty empList}">
			<p></p>
			<table class="tbl">
				<tr>
					<th width="40" align="center">Employee ID</th>
					<th width="160">Employee Name</th>
					<th width="80">Birthday</th>
					<th width="80">Hireday</th>
					<th width="140">Address</th>
					<th width="120">Department</th>
					<th width="60">Salary</th>

					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<th width="60">Edit</th>
						<th width="60">Delete</th>
					</sec:authorize>

				</tr>
				<c:forEach items="${empList}" var="employee">
					<tr>
						<td align="center">${employee.id}</td>
						<td>${employee.name}</td>
						<td align="center">${employee.birthDate}</td>
						<td align="center">${employee.hireDate}</td>
						<td>${employee.address}</td>
						<td>${employee.dept.name}</td>
						<td align="center">${employee.salary}</td>

						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<td align="center"><a
								href="<c:url value="/edit/${employee.id}" />">Edit</a></td>
							<td align="center"><a
								href="<c:url value="/remove/${employee.id}" />">Delete</a></td>
						</sec:authorize>

					</tr>
				</c:forEach>
			</table>
		</c:if>

		<h4><a href="<c:url value="/" />" class="back">Back to Main Page</a></h4>

	</section>

	<%@ include file="/resources/jspfs/Footer.jspf"%>

</body>
</html>