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



    <div class ="depts-container"></div>

    <div class ="emps-container"></div>

    <div class ="forms-container"></div>

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

            fireUpdate: function() {
                this.fire("updated");
            },


            subscribeToUpdate: function(fx, scope) {
                this.subscribe("updated", fx, scope);
            },

            setData: function (data) {
                this.dataArray = data;
            },

            clear: function () {
                this.dataArray = [];
            }

        });


        //----------------------------------------------

        var TableDrawer = DataSource.extend({

            init: function(name) {
                this._super(name);
                this.subscribeToUpdate();
            },

            setOpts: function(opts) {
                this.opts = opts;
                return this;
            },

            setPageController: function (pc) {
                this.pc = pc;
                return this;
            },

            subscribeToUpdate: function() {
                this._super(function() {
                    this.draw();
                }, this);
            },

            /*
             subscribeToSwitchView: function(fx, scope) {
             this.subscribe("switchView", fx, scope);
             },
             */

            fireSwitchView: function(id) {
                $(this.pc).trigger("switchView", [id]);
            },

            clearContainer: function() {
                this.hide();
                return this;
            },

            hide: function() {
                this.opts.$container.empty();
            },

            show: function (id) {
                this.loadAllRows(id);
                this.opts.$container.append(this.$table);
            },

            createTable: function() {
                this.$table = $("<table>");
                this.$table.addClass(this.opts.classes);

                return this;
            },

            addTitle: function() {
                this.opts.$container.append($("<h2>").html(this.opts.title));
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

            },

            draw: function() {
                this.clearContainer().createTable().addTitle().addHeader();

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
                $.getJSON(this.opts.deleteRowURL + id).done( function() {
                    self.loadAllRows();
                });
            }

        });

        //--------------------------------------------------------


        var PageController = Class.extend({

            init: function (name) {
                this.name = name;

                this.deptsTDrawer = new TableDrawer("deptsTDrawer");
                this.deptsTDrawer.setOpts(getDeptsTableOpts()).setPageController(this);

                this.empsTDrawer = new TableDrawer("empsTDrawer");
                this.empsTDrawer.setOpts(getEmpsTableOpts()).setPageController(this);
                this.subscribeToSwitchView();
            },

            subscribeToSwitchView: function () {
                $(this).on("switchView", $.proxy(function(event, id) {
                    this.empsTDrawer.show(id);
                    this.deptsTDrawer.hide();
                }, this));
            }

        });


        //---------------------------------------------------------



        $(document).ready(function() {

            var pc = new PageController("New PageController");

            //alert("showing");

            pc.deptsTDrawer.show();


            //alert("hiding");
            //pc.deptsTDrawer.hide();

            // pc.empsTDrawer.show();

            /*
             var deptsTDrawer = new TableDrawer("deptsTDrawer");

             deptsTDrawer.setOpts(getDeptsTableOpts());

             deptsTDrawer.show();

             //---------------------------------------------------------

             var empsTDrawer = new TableDrawer("empsTDrawer");

             empsTDrawer.setOpts(getEmpsTableOpts());*/

            /*
             empsTDrawer.subscribeToSwitchView(function() {
             alert("event handled");
             deptsTDrawer.hide();
             empsTDrawer.show();
             }, empsTDrawer);*/

            /*
             empsTDrawer.subscribeToSwitchView(function() {

             alert(this);

             }, empsTDrawer);
             */


            /*
             $(empsTDrawer).on("my", function () {
             alert(this.name);
             deptsTDrawer.hide();
             });

             $(empsTDrawer).trigger("my");

             */
            //empsTDrawer.show();

        });

    </script>

    <form class="emps-addbtn-form" method="post">
        <button formaction="" class="btn btn-primary dept-add-btn">Add new dept</button>
    </form>

    <button onclick="window.history.back()"
            class="btn btn-primary btn-back">Go back</button>

    <%@ include file="/resources/jspfs/footer.jspf"%>

</div>

</body>

</html>