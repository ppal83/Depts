<!DOCTYPE html>

<html>

<head>

    <title>Registration page</title>

    <%@ include file="/resources/jspfs/imports.jspf"%>

</head>

<body>

<div class="container">

    <%@ include file="/resources/jspfs/header.jspf" %>

    <h2>Please provide a user registration data below</h2>

    <c:url var="registerAction" value="/register"></c:url>

    <springForm:form id="reg_form" action="${registerAction}" commandName="user"
                     cssClass="reg-form" method="POST">
        <table>
            <tr>
                <td><springForm:label path="username">
                    <spring:message text="Username:" />
                </springForm:label></td>
                <td><springForm:input path="username" cssClass="form-control"/></td>
                <td><springForm:errors path="username" cssClass="error" /></td>
            </tr>
            <tr>
                <td><springForm:label path="password">
                    <spring:message text="Password:" />
                </springForm:label></td>
                <td><springForm:password path="password" cssClass="form-control"/></td>
                <td><springForm:errors path="password" cssClass="error" /></td>
            </tr>
            <tr>
                <td><springForm:label path="email">
                    <spring:message text="Email:" />
                </springForm:label></td>
                <td><springForm:input path="email" cssClass="form-control"/></td>
                <td><springForm:errors path="email" cssClass="error" /></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" class="btn btn-primary"
                           value="<spring:message text="Register user"/>" />
                </td>
            </tr>
        </table>

    </springForm:form>

    <div class="registered-user">
        If you are registered user, please
        <a href="<c:url value="/login" />"><span class="registered-user-log">login</span></a>
    </div>

    <%@ include file="/resources/jspfs/footer.jspf" %>

</div>

</body>

</html>