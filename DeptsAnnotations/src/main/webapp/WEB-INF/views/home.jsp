<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>

<!DOCTYPE html>

<html>

<head>

    <title>Home page</title>

</head>

<body>

<%@ include file="/resources/jspfs/BSHeader.jspf"%>

<div class="container">

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
            full employee list(choosing 'All departments'). If you have
            an admin access you can edit departments list also.</p>

        <table>
            <tr>
                <td><springForm:select path="id" cssClass="form-control">
                    <springForm:options items="${deptsList}" itemValue="id"
                                        itemLabel="name" />
                </springForm:select></td>
                <td><button formaction="employees" class="btn btn-primary">View</button></td>
            </tr>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <tr>
                    <td colspan="2">
                        <button formaction="depts" class="btn btn-primary cust">Customize
                            dept list</button>
                    </td>
                </tr>
            </sec:authorize>
        </table>

        <h2>Lorem ipsum</h2>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
            ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
            ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
            ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
            ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
            ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    </springForm:form>

    <%@ include file="/resources/jspfs/BSFooter.jspf"%>
</div>

</body>

</html>