<!DOCTYPE html>

<html>

<head>

    <title>Login Page</title>

    <%@ include file="/resources/jspfs/imports.jspf"%>


</head>

<body>

<div class="container">

    <%@ include file="/resources/jspfs/header.jspf" %>

    <h2>Login with Username and Password</h2>

    <c:if test="${message eq 'success'}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            You've been succefully registered,
            now you can login using your username and password.
        </div>
    </c:if>

    <c:url var="loginUrl" value="/login"></c:url>

    <form action="${loginUrl}" role="form" class="login-form" method="POST">
        <table>
            <tr>
                <td class="login-form-caption">User ID:</td>
                <td><input type='text' class="form-control" name='username'/></td>
            </tr>
            <tr>
                <td class="login-form-caption">Password:</td>
                <td><input type='password' class="form-control" name='password'/></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" class="btn btn-primary"
                                       value="Login"/></td>
            </tr>
        </table>
    </form>

    <div class="new-user">
        If you are a new user, please
        <a href="<c:url value="/registration" />"><span class="new-user-reg">register</span></a>
    </div>

    <%@ include file="/resources/jspfs/footer.jspf" %>

</div>

</body>

</html>