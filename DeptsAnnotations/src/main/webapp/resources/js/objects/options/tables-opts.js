function getDeptsTableOpts() {

    return {
        $container: $(".depts-table-container"),
        title: "List of departments",
        loadAllRowsURL: "../rest/depts",
        deleteRowURL: "../rest/dept/delete/",
        classes: "table table-bordered table-hover depts-table",
        headers: ["Dept ID", "Dept name", "Edit", "Delete", "View"],
        events: ["DrawEmpsList", "DrawDeptsList", "DrawDeptAddForm",
            "DrawDeptEditForm"],

        //--------------buttons inside table------------------------
        innerButtons: [
            {   value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function(self, id) { return function() {
                    self.fireDrawDeptEditForm(id);
                }}
            },

            {   value: "Delete",
                classes: "btn btn-sm btn-danger",
                clicked: function(self, id) { return function() {
                    self.deleteRow(id);
                }}
            },

            {   value: "View",
                classes: "btn btn-sm btn-primary",
                clicked: function(self, id) { return function() {
                    self.fireDrawEmpsList(id);
                }}
            }],


        //--------------buttons outside table------------------------
        outerButtons: [
            {   value: "Add new dept",
                classes: "btn btn-primary dept-add-btn",
                clicked: function(self) { return function() {
                    self.fireDrawDeptAddForm();
                }}
            },

            {   value: "To main page",
                classes: "btn btn-primary btn-back",
                clicked: function() { return function() {
                    window.history.back();
                }}
            }
        ]
    }
}

function getEmpsTableOpts() {

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
            {   value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function(self, id) { return function() {
                    self.fireDrawEmpEditForm(id);
                }}
            },

            {   value: "Delete",
                classes: "btn btn-sm btn-danger",
                clicked: function(self, id) { return function() {
                    self.deleteRow(id);
                }}
            }],


        //--------------buttons outside table------------------------
        outerButtons: [
            {   value: "Add new employee",
                classes: "btn btn-primary emp-add-btn",
                clicked: function(self, id) { return function() {
                    self.fireDrawEmpAddForm(id);
                }}
            },

            {   value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function(self) { return function() {
                    self.fireDrawDeptsList();
                }}
            },

            {   value: "To main page",
                classes: "btn btn-primary btn-back",
                clicked: function() { return function() {
                    window.history.back();
                }}
            }
        ]
    }
}