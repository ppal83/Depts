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

    <div class="depts-table-container"></div>
    <div class="emps-table-container"></div>
    <div class="depts-form-container"></div>
    <div class="emps-form-container"></div>

    <script>

        var SupportObject = Class.extend({

            init: function (name) {
                this.name = name;
            },

            setOpts: function(opts) {
                this.opts = opts;
                return this;
            },

            setPageController: function (pc) {
                this.pc = pc;
                return this;
            },

            subscribe: function(event, fx, scope) {
                $(this).on(event, $.proxy(fx, scope));
            },

            fire: function(event, data) {
                $(this).trigger(event, data);
            }

        });


        var DataSource = SupportObject.extend({

            fireUpdate: function() {
                this.fire("updated");
            },

            subscribeToUpdate: function(fx, scope) {
                this.subscribe("updated", fx, scope);
            },

            setData: function (data) {
                this.dataArray = data;
            }

        });


        //-----------------Table drawer-----------------------------

        var TableDrawer = DataSource.extend({

            init: function(name) {
                this._super(name);
                this.subscribeToUpdate();
            },

            subscribeToUpdate: function() {
                this._super(function() {
                    this.draw();
                }, this);
            },

            fireEmpsList: function(id) {
                $(this.pc).trigger("drawEmpsList", [id]);
            },

            fireDeptsList: function() {
                $(this.pc).trigger("drawDeptsList");
            },

            fireDeptEdit: function(id) {
                $(this.pc).trigger("drawDeptForm", [id]);
            },

            hide: function() {
                this.opts.$container.empty();
            },

            show: function (id) {
                this.loadAllRows(id);
                this.opts.$container.append(this.$table);
            },

            clearContainer: function() {
                this.hide();
                return this;
            },

            addTitle: function() {
                this.opts.$container.append($("<h2>").html(this.opts.title));
                return this;
            },

            createTable: function() {
                this.$table = $("<table>");
                this.$table.addClass(this.opts.classes);

                return this;
            },

            addHeader: function() {
                var $tr = $("<tr>");
                var self = this;
                $.each(this.opts.headers, function(i) {
                    var $th = $("<th>");
                    $tr.append( $th.html(self.opts.headers[i]) );
                });
                this.$table.append($tr);
                return this;
            },

            addRow: function(element) {
                var $tr = $("<tr>");
                $.each(element, function(k, v) {
                    switch (k) {
                        case "emps": break;
                        case "birthDate":
                        case "hireDate": v = new Date(v).format("yyyy-mm-dd");
                        default: $tr.append( $("<td>").html(v) ); break;
                    }
                });

                this.addInnerButtons($tr, element.id);
                this.$table.append($tr);
            },

            addInnerButtons: function($tr, id) {
                var btns = this.opts.innerButtons;
                if (!btns) return;
                for (var i = 0; i < btns.length; i++) {
                    var self = this;

                    $("<td>").append( $("<a>").addClass(btns[i].classes)
                            .html(btns[i].value)
                            .click(btns[i].clicked(self, id)) )
                            .appendTo($tr);
                }
            },

            addOuterButtons: function() {
                var self = this;
                $.each(this.opts.outerButtons, function() {
                    $("<a>").addClass(this.classes).html(this.value)
                            .click( this.clicked(self) ).appendTo(self.opts.$container);
                })
            },

            draw: function() {
                this.clearContainer()
                        .addTitle()
                        .createTable()
                        .addHeader();

                $.each( this.dataArray, $.proxy(function (i, e) {
                    this.addRow(e);
                }, this) );

                this.opts.$container.append(this.$table);
                this.addOuterButtons();
            },

            loadAllRows: function(id) {
                var self = this;
                $.getJSON(this.opts.loadAllRowsURL + (!!id ? "/" + id : ""),
                        function(data) {
                            self.setData(data);
                        }).done( function() {
                            self.fireUpdate()
                        });
            },

            deleteRow: function(id) {
                var self = this;
                $.getJSON(this.opts.deleteRowURL + id)
                        .done( function(data) {
                            self.loadAllRows(data.dept);
                        });
            }

        });

        //-----------------------Forms generator-----------------------------------

        var FormsGenerator = DataSource.extend({

            init: function(name) {
                this._super(name);
                this.subscribeToUpdate();
            },

            subscribeToUpdate: function() {
                this._super(function() {
                    this.draw();
                }, this);
            },

            fireDeptsList: function() {
                $(this.pc).trigger("drawDeptsList");
            },

            hide: function() {
                this.opts.$containerDiv.empty();
                this.opts.$containerForm.empty();
            },

            show: function (id) {
                this.$id = id;
                this.loadAllFields(id);
                this.opts.$containerForm.attr({/*"action": this.opts.updateRowURL + this.$id,*/
                    "method": "POST",
                    "enctype": "application/json"});
                this.opts.$containerForm.append(this.$table);
                this.opts.$containerDiv.append(this.opts.$containerForm);
            },

            clearContainer: function() {
                this.hide();
                return this;
            },

            addTitle: function() {
                this.opts.$containerDiv.append($("<h2>").html(this.opts.title));
                return this;
            },

            createTable: function() {
                this.$table = $("<table>").addClass(this.opts.classes);

                return this;
            },

            addFields: function() {
                var self = this,
                        i = 0;

                $.each(this.dataArray, function(k, v) {
                    if (k != "emps") {
                        var $tr = $("<tr>");

                        $("<td>").append($("<label>")
                                .addClass("form-control")
                                .attr("for", k).html(self.opts.labels[i++]))
                                .appendTo($tr);

                        $("<td>").append($("<input>")
                                .addClass("form-control")
                                .attr({"value": v, "id": k, "name": k})
                                .prop("readonly", k == "id"))
                                .appendTo($tr);

                        $tr.appendTo(self.$table);
                    }
                });

                return this;
            },

            addOuterButtons: function() {
                var self = this;

                $.each(this.opts.outerButtons, function() {
                    var $tr = $("<tr>");

                    $("<td>").attr("colspan", 2)
                            .append( $("<button>")
                                    .addClass(this.classes)
                                    .html(this.value)
                                    .click( this.clicked(self) ) )
                            .appendTo($tr);

                    $tr.appendTo(self.$table);
                })
            },

            draw: function() {
                this.clearContainer()
                        .addTitle()
                        .createTable()
                        .addFields()
                        .addOuterButtons();

                this.opts.$containerForm.append(this.$table);
                this.opts.$containerDiv.append(this.opts.$containerForm);

                validateDept(this);

            },

            loadAllFields: function(id) {
                var self = this;
                $.getJSON(this.opts.loadAllFieldsURL + id,
                        function(data) {
                            self.setData(data);
                        }).done( function() {
                            self.fireUpdate()
                        });
            },

            updateRow: function() {
                var self = this;

                $.ajax({
                    url: this.opts.updateRowURL + this.$id,
                    method: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify({id: $("#id").val(),
                        name: $("#name").val()
                    }),
                    success: function() {
                        self.fireDeptsList();
                    }
                });
            }
        });

        //-----------------------Page controller---------------------------------

        var PageController = Class.extend({

            init: function (name) {
                this.name = name;

                //creating depts table drawer
                this.deptsTDrawer = new TableDrawer("deptsTDrawer");
                this.deptsTDrawer.setOpts(getDeptsTableOpts()).setPageController(this);
                //creating emps table drawer
                this.empsTDrawer = new TableDrawer("empsTDrawer");
                this.empsTDrawer.setOpts(getEmpsTableOpts()).setPageController(this);

                //creating dept form generator
                this.deptFormGenerator = new FormsGenerator("deptsFGenerator");
                this.deptFormGenerator.setOpts(getDeptFormOpts()).setPageController(this);

                this.subscribeToDrawEmpsList();
                this.subscribeToDrawDeptsList();
                this.subscribeToDeptEdit();
            },

            subscribeToDrawEmpsList: function () {
                $(this).on("drawEmpsList", $.proxy(function(event, id) {
                    this.empsTDrawer.show(id);
                    this.deptsTDrawer.hide();
                }, this));
            },

            subscribeToDrawDeptsList: function () {
                $(this).on("drawDeptsList", $.proxy(function() {
                    this.empsTDrawer.hide();
                    this.deptFormGenerator.hide();
                    this.deptsTDrawer.show();
                }, this));
            },

            subscribeToDeptEdit: function() {
                $(this).on("drawDeptForm", $.proxy(function(event, id) {
                    this.deptsTDrawer.hide();
                    this.deptFormGenerator.show(id);
                }, this));
            }
        });


        //----------------------------------------------------------------
        //-------------------------MAIN-----------------------------------
        //----------------------------------------------------------------

        $(document).ready(function() {

            var pc = new PageController("New PageController");
            pc.deptsTDrawer.show(); //initial view


        });

    </script>

    <%@ include file="/resources/jspfs/footer.jspf"%>

</div>

</body>

</html>