<!DOCTYPE html>

<html>

<head>

	<title>Depts list</title>

	<%@ include file="/resources/jspfs/imports.jspf"%>

</head>

<body>

<div class="container">

	<%@ include file="/resources/jspfs/header.jspf"%>

	<c:if test="${dept.id ne 0}">
		<h2>Edit dept</h2>
	</c:if>

	<c:if test="${dept.id eq 0}">
		<h2>Add dept</h2>
	</c:if>

	<c:url var="addAction" value="/dept/add"></c:url>

	<springForm:form id="dept_form" action="${addAction}" cssClass="depts-form"
					 commandName="dept" method="GET">

		<table class="dept-table">

			<c:if test="${dept.id ne 0}">
				<tr>
					<td><springForm:label path="id">
						<spring:message text="ID" />
					</springForm:label></td>
					<td><springForm:input path="id" readonly="true" cssClass="form-control"
										  disabled="true" /> <springForm:hidden path="id" /></td>
				</tr>
			</c:if>

			<tr>
				<td><springForm:label path="name">
					<spring:message text="Department" />
				</springForm:label></td>
				<td><springForm:input path="name" cssClass="form-control"/></td>
				<td><springForm:errors path="name" cssClass="error" /></td>
			</tr>

			<tr>
				<td colspan="2"><c:if test="${dept.id ne 0}">
					<input type="submit" class="btn btn-primary cust"
						   value="<spring:message text="Edit existing dept"/>" />
				</c:if> <c:if test="${dept.id eq 0}">
					<input type="submit" class="btn btn-primary cust"
						   value="<spring:message text="Create new dept"/>" />
				</c:if></td>
			</tr>

		</table>

	</springForm:form>

	<table class="table table-bordered table-hover depts-table">

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
					<a href="<c:url value="/dept/edit/${dept.id}" />">
						<button class="btn btn-sm btn-primary">Edit</button></a>
				</td>
				<td align="center">
					<a href="<c:url value="/dept/remove/${dept.id}" />">
						<button class="btn btn-sm btn-danger">Delete</button>
					</a></td>
				<td align="center">
					<a href="<c:url value="/employees?id=${dept.id}" />">
						<button class="btn btn-sm btn-primary">View</button>
					</a></td>
			</tr>
		</c:forEach>

	</table>

	<%@ include file="/resources/jspfs/footer.jspf"%>

</div>

</body>

</html>