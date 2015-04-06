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

    <div class = "depts-table"></div>

    <script>

        var SupportObject = Class.extend({

            init: function (name) {
                this.name = name;
            },

            subscribe: function(event, fx, scope) {
                $(this).on(event, $.proxy(fx, scope));
            },

            fire: function(event, data) {
                $(this).trigger(event, data);
            }

        });

        var DataSource = SupportObject.extend({

            init: function (name, data) {
                this._super(name);
                arguments[1] ? this.dataArray = data : this.dataArray = [];
                this._index = 0;
            },

            fireUpdate: function() {
                this.fire("updated");
            },


            subscribeToUpdate: function(fx, scope) {
                this.subscribe('updated', fx, scope);
            },

            setData: function (data) {
                this.dataArray = data;
                this.fireUpdate();
            },

            hasNext: function() {
                return this._index < this.dataArray.length;
            },

            next: function() {
                return this.hasNext() ? this.dataArray[this._index++] : null;
            },

            shift: function () {
                return this.dataArray.shift();
            },

            _resetIndex: function() {
                this._index = 0;
            },

            clear: function () {
                this._resetIndex();
                this.dataArray = [];
            }

        });

        var TableDrawer = DataSource.extend({

            init: function(name, data, opts) {
                this._super(name, data);
                this.opts = opts;
                this._currentRow = 0;
                this.subscribeToUpdate();
            },

            setOpts: function(opts) {
                this.opts = opts;
            },

            subscribeToUpdate: function() {
                this._super(function() {
                    this.draw()
                }, this);
            },

            createTable: function() {
                this.$container = this.opts.$container;
                this.$table = $("<table>");

                this.$table.addClass(this.opts.classes);
                this.$container.append(this.$table);
            },

            addHeader: function() {
                var $tr = $("<tr>");

                var self = this;

                $.each(this.opts.headers, function(i) {
                    var $th = $("<th>");
                    $tr.append( $th.html(self.opts.headers[i]) );
                });

                this.$table.append($tr);
                this._currentRow++;

            },

            addRow: function(element) {
                var $tr = $("<tr>");

                for (var key in element) {
                    if (key == "emps") continue;
                    var $td = $("<td>");
                    $tr.append( $td.html(element[key]) );
                }

                this.addButtons($tr);

                this.$table.append($tr);
                this._currentRow++;
            },

            addButtons: function($tr) {
                var btns = this.opts.buttons;
                if (!btns) return;
                for (var i = 0; i < btns.length; i++) {
                    var button = btns[i];
                    var $btn = $("<a>").addClass(button.classes)
                            .html(button.value)
                            .attr("href", button.href + "/" + this._currentRow)
                            .click(button.onclick);
                    var $td = $("<td>");
                    $td.append($btn);
                    $tr.append($td);
                }
            },

            draw: function() {
                this.createTable();

                this.addHeader();

                $.each( this.dataArray, $.proxy(function (i, e) {
                    this.addRow(e);
                }, this) );

                this.$container.append(this.$table);
            }

        });

        $(document).ready(function() {

            var tDrawer = new TableDrawer("TDrawer");

            tDrawer.setOpts({
                $container: $(".depts-table"),
                classes: "table table-bordered table-hover depts-table",
                headers: ['Dept ID', 'Dept name', 'Edit', 'Delete', 'View'],
                buttons: [
                    {
                        value: "Edit",
                        classes: "btn btn-sm btn-primary",
                        href: "/rest/dept/edit"
                    },

                    {
                        value: "Delete",
                        classes: "btn btn-sm btn-danger",
                        href: "../rest/dept/delete",
                        onclick: function() {
                            $.getJSON("../rest/dept/delete/1", function(data) {
                               return false;
                            });

                        }
                    },

                    {
                        value: "View",
                        classes: "btn btn-sm btn-primary",
                        href: "kyky_eshe"
                    }]
            });
            /*
             tDrawer.subscribeToUpdate(function() {
             tDrawer.draw();
             }, tDrawer);
             */

            $.getJSON("../rest/depts", function(data) {
                tDrawer.setData(data);
            });

        });

    </script>

    <table class="table table-bordered table-hover">




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
        <button formaction="" class="btn btn-primary dept-add-btn">Add new dept</button>
    </form>

    <button onclick="window.history.back()"
            class="btn btn-primary btn-back">Go back</button>

    <%@ include file="/resources/jspfs/footer.jspf"%>

</div>

</body>

</html>