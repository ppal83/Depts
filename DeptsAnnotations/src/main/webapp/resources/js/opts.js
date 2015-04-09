function getDeptsTableOpts() {

    return {

        $container: $(".depts-table-container"),
        title: "List of departments",
        loadAllRowsURL: "../rest/depts",
        deleteRowURL: "../rest/dept/delete/",
        classes: "table table-bordered table-hover depts-table",
        headers: ['Dept ID', 'Dept name', 'Edit', 'Delete', 'View'],

        //--------------buttons inside table------------------------
        innerButtons: [
            {   value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function(self, id) { return function() {
                    self.fireDeptEdit(id);
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
                    self.fireEmpsList(id);
                }}
            }],


        //--------------buttons outside table------------------------
        outerButtons: [
            {   value: "Add new dept",
                classes: "btn btn-primary dept-add-btn",
                clicked: function() { return function() {

                }}
            },

            {   value: "To previous page",
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
        headers: ['Employee ID', 'Employee name', 'Birthday', 'Hireday',
            'Address', 'Email', 'Department', 'Salary', 'Edit', 'Delete'],

        //--------------buttons inside table------------------------
        innerButtons: [
            {   value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function() {
                    return function() {
                        self.deleteRow(id);
                    }
                }
            },

            {   value: "Delete",
                classes: "btn btn-sm btn-danger",
                clicked: function(self, id) {
                    return function() {
                        self.deleteRow(id);
                    }
                }
            }],


        //--------------buttons outside table------------------------
        outerButtons: [
            {   value: "Add new employee",
                classes: "btn btn-primary emp-add-btn",
                clicked: function() {
                    return function() {

                    }
                }
            },

            {   value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function(self) {
                    return function() {
                        self.fireDeptsList();
                    }
                }
            },

            {   value: "To previous page",
                classes: "btn btn-primary btn-back",
                clicked: function() {
                    return function() {
                        window.history.back();
                    }
                }
            }
        ]
    }
}

function getDeptFormOpts() {

    return {
        $containerDiv: $(".depts-form-container"),
        $containerForm: $("<form>").addClass(".dept-edit-form"),
        classes: "dept-edit-table",
        title: "Edit dept",
        loadAllFieldsURL: "../rest/dept/",
        updateRowURL: "../rest/dept/edit/",
        labels: ["ID", "Name"],

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Edit dept",
                classes: "btn btn-primary cust",
                clicked: function() { }
            },

            {   value: "To previous page",
                classes: "btn btn-primary btn-back",
                clicked: function() {
                    return function() {
                        window.history.back();
                    }
                }
            }
        ]
    }
}