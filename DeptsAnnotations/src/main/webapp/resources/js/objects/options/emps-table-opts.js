define(["jquery-2.1.3.min"], function() {
    return {
        $container: $(".emps-table-container"),
        title: "List of employees",
        loadAllRowsURL: "../rest/emps",
        deleteRowURL: "../rest/emp/delete/",
        classes: "table table-bordered table-hover empls-table",
        headers: ["Employee ID", "Employee name", "Birthday", "Hireday",
            "Address", "Email", "Department", "Salary", "Edit", "Delete"],
        events: ["DrawDeptsList", "DrawEmpAddForm", "DrawEmpEditForm"],

        //--------------buttons inside table------------------------
        innerButtons: [
            {
                value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function (self, id) {
                    return function () {
                        self.fireDrawEmpEditForm(id);
                    }
                }
            },

            {
                value: "Delete",
                classes: "btn btn-sm btn-danger",
                clicked: function (self, id) {
                    return function () {
                        self.deleteRow(id);
                    }
                }
            }],


        //--------------buttons outside table------------------------
        outerButtons: [
            {
                value: "Add new employee",
                classes: "btn btn-primary emp-add-btn",
                clicked: function (self, id) {
                    return function () {
                        self.fireDrawEmpAddForm(id);
                    }
                }
            },

            {
                value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function (self) {
                    return function () {
                        self.fireDrawDeptsList();
                    }
                }
            },

            {
                value: "To main page",
                classes: "btn btn-primary btn-back",
                clicked: function () {
                    return function () {
                        window.history.back();
                    }
                }
            }
        ]
    }
});