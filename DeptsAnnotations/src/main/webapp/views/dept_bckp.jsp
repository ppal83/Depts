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

  <div class ="tables-container"></div>

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

    var AjaxQueriesSupportObject = Class.extend({

      getAllDepts:1


    });



    var DataSource = SupportObject.extend({

      /*
       init: function (name, data) {
       this._super(name);
       //arguments[1] ? this.dataArray = data : this.dataArray = [];
       },
       */

      fireUpdate: function() {
        this.fire("updated");
      },


      subscribeToUpdate: function(fx, scope) {
        this.subscribe('updated', fx, scope);
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

      init: function(name, data, opts) {
        this._super(name, data);
        //arguments[1] ? this.dataArray = data : this.dataArray = [];
        //arguments[2] ? this.opts = opts: this.opts = {};
        this.subscribeToUpdate();
        //this.loadAllRows();
      },

      setOpts: function(opts) {
        this.opts = opts;
        this.$container = this.opts.$container;
      },

      subscribeToUpdate: function() {
        this._super(function() {
          this.draw();
        }, this);
      },

      clearContainer: function() {
        this.hide();
        return this;
      },

      hide: function() {
        this.$container.empty();
      },

      show: function () {
        // alert("this.dataArray = " + this.dataArray);
        // alert(this.$container.get(0).outerHTML);
        // alert(this.$table.get(0).outerHTML);
        this.loadAllRows();
        this.$container.append(this.$table);
      },

      createTable: function() {
        this.$table = $("<table>");
        this.$table.addClass(this.opts.classes);
        //this.$container.append(this.$table);
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
        for (var key in element) {
          if (key == "emps") continue;
          $tr.append( $("<td>").html(element[key]) );
        }
        this.addInnerButtons($tr, element.id);
        this.$table.append($tr);
      },

      addInnerButtons: function($tr, deptId) {
        var btns = this.opts.innerButtons;
        if (!btns) return;
        for (var i = 0; i < btns.length; i++) {
          var self = this;

          $("<td>").append( $("<a>").addClass(btns[i].classes)
                  .html(btns[i].value)
                  .click(function() {
                    self.deleteRow(deptId)
                  })).appendTo($tr);
        }
      },

      draw: function() {
        this.clearContainer().createTable().addHeader();

        $.each( this.dataArray, $.proxy(function (i, e) {
          this.addRow(e);
        }, this) );

        this.$container.append(this.$table);
        //this.show();
      },

      loadAllRows: function() {
        var self = this;
        $.getJSON("../rest/depts", function(data) {
          self.setData(data);
          alert("data has been set");
        }).done( function() {
          self.fireUpdate()
        });
      },

      deleteRow: function(deptId) {
        var self = this;
        $.getJSON("../rest/dept/delete/" + deptId).done( function() {
          self.loadAllRows();
        });
      }

    });

    //--------------------------------------------------------

    $(document).ready(function() {

      var deptsTDrawer = new TableDrawer("deptsTDrawer");

      deptsTDrawer.setOpts({
        $container: $(".tables-container"),
        classes: "table table-bordered table-hover depts-table",
        headers: ['Dept ID', 'Dept name', 'Edit', 'Delete', 'View'],
        innerButtons: [
          {
            value: "Edit",
            classes: "btn btn-sm btn-primary",
            href: "/rest/dept/edit"
          },

          {
            value: "Delete",
            classes: "btn btn-sm btn-danger",
            href: "../rest/dept/delete"
          },

          {
            value: "View",
            classes: "btn btn-sm btn-primary",
            href: "kyky_eshe",
            clicked: function() {
              this.hide();
            }
          }]
      });

      deptsTDrawer.show();

      var empsTDrawer = new TableDrawer("empsTDrawer");

      empsTDrawer.setOpts({
        $container: $(".emps-container"),
        classes: "table table-bordered table-hover emps-table",
        headers: ['Employee ID', 'Employee name', 'Birthday', 'Hireday', 'Address', 'Email',
          'Department', 'Salary', 'Edit', 'Delete'],
        innerButtons: [
          {
            value: "Edit",
            classes: "btn btn-sm btn-primary",
            href: "/rest/dept/edit"
          },

          {
            value: "Delete",
            classes: "btn btn-sm btn-danger",
            href: "../rest/dept/delete"
          },

          {
            value: "View",
            classes: "btn btn-sm btn-primary",
            href: "kyky_eshe",
            clicked: function() {
              this.hide();
            }
          }]
      });



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