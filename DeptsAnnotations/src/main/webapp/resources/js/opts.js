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
                clicked: function(self) { return function() {
                    self.fireDeptAdd();
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
        headers: ['Employee ID', 'Employee name', 'Birthday', 'Hireday',
            'Address', 'Email', 'Department', 'Salary', 'Edit', 'Delete'],

        //--------------buttons inside table------------------------
        innerButtons: [
            {   value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function(self, id) { return function() {
                    self.fireEmpEdit(id);
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
                    self.fireEmpAdd(id);
                }}
            },

            {   value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function(self) { return function() {
                    self.fireDeptsList();
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

function getDeptEditFormOpts() {

    return {
        objType: "dept",
        action: "edit",
        $containerDiv: $(".depts-form-container"),
        $containerForm: $("<form>").addClass(".dept-edit-form"),
        classes: "dept-edit-table",
        title: "Edit dept",
        loadAllFieldsURL: "../rest/dept/",
        updateRowURL: "../rest/dept/edit/",
        labels: ["ID", "Name"],
        validate: validateDept,

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Edit dept",
                classes: "btn btn-primary cust",
                clicked: function() { }
            },

            {   value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function(self) { return function() {
                    self.fireDeptsList();
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

function getDeptAddFormOpts() {

    return {
        objType: "dept",
        action: "add",
        $containerDiv: $(".depts-form-container"),
        $containerForm: $("<form>").addClass(".dept-add-form"),
        classes: "dept-add-table",
        title: "Add dept",
        updateRowURL: "../rest/dept/add",
        template: {name: ""},
        labels: ["Name"],
        validate: validateDept,

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Add dept",
                classes: "btn btn-primary cust",
                clicked: function() { }
            },

            {   value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function(self) { return function() {
                    self.fireDeptsList();
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

function getEmpEditFormOpts() {

    return {
        objType: "employee",
        action: "edit",
        $containerDiv: $(".emps-form-container"),
        $containerForm: $("<form>").addClass(".emp-edit-form"),
        classes: "emp-edit-table",
        title: "Edit employee",
        loadAllFieldsURL: "../rest/emp/",
        updateRowURL: "../rest/emp/edit/",
        labels: ["ID", "Name", "Birthday", "HireDate",
            "Address", "Email", "Dept", "Salary"],
        validate: validateEmp,

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Edit employee",
                classes: "btn btn-primary cust",
                clicked: function() { }
            },

            {   value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function(self) { return function() {
                    self.fireDeptsList();
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

function getEmpAddFormOpts() {

    return {
        objType: "employee",
        action: "add",
        $containerDiv: $(".emps-form-container"),
        $containerForm: $("<form>").addClass(".emp-add-form"),
        classes: "emp-add-table",
        title: "Add employee",
        updateRowURL: "../rest/emp/add",
        template: {
            name: "" ,
            birthDate: "",
            hireDate: "",
            address: "",
            email: "",
            dept: {name: "Sales"},
            salary: ""
        },
        labels: ["Name", "Birthday", "HireDate",
            "Address", "Email", "Dept", "Salary"],
        validate: validateEmp,

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Add employee",
                classes: "btn btn-primary cust",
                clicked: function() { }
            },

            {   value: "Back to depts list",
                classes: "btn btn-primary btn-back",
                clicked: function(self) { return function() {
                    self.fireDeptsList();
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