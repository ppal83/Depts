<!DOCTYPE html>

<html>

<head>

    <title>List of departments page</title>

    <%@ include file="/resources/jspfs/imports.jspf"%>

</head>

<body>

<div class="container">

    <%@ include file="/resources/jspfs/header.jspf"%>

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

    <h2>List of departments</h2>

    <div class = "ajax-query"></div>

    <script>
        var $div = $(".ajax-query");
        alert($div);

        $.getJSON("../rest/emp/dummy", function(data) {
            alert("got JSON object: " + data);
            $div.html(data.name);
            alert(JSON.stringify(data));
        });
    </script>

    <table class="table table-bordered table-hover depts-table">




        <%--
        <tr>
            <th>Dept ID</th>
            <th>Dept name</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>View</th>
        </tr>

        <c:forEach items="${deptsList}" var="dept">

            <form class="depts-form" method="post">

                <tr>

                    <td>${dept.id}</td>
                    <td><c:out value="${dept.name}" /></td>
                    <td>
                        <button formaction="<c:url value="/deptedit_form.html" />"
                        class="btn btn-sm btn-primary">Edit</button>
                    </td>

                    <!-- hidden input with param 'deptId'-->
                    <input type="hidden" name="deptId" value="${dept.id}" />

                    <td>
                        <button formaction="<c:url value="/deptdel.html" />"
                        class="btn btn-sm btn-danger">Delete</button>
                    </td>

                    <td>
                        <button formaction="<c:url value="/emplist.html" />"
                        class="btn btn-sm btn-primary">View</button>
                    </td>

                </tr>
            </form>

        </c:forEach>

--%>

    </table>

    <form class="emps-addbtn-form" method="post">
        <button formaction="" />"
        class="btn btn-primary dept-add-btn">Add new dept</button>
    </form>

    <button onclick="window.history.back()"
            class="btn btn-primary btn-back">Go back</button>

    <%@ include file="/resources/jspfs/footer.jspf"%>

</div>

</body>

</html>