function getDeptEditFormOpts() {

    return {
        $container: $(".depts-form-container"),
        $containerForm: $("<form>").addClass(".dept-edit-form"),
        classes: "dept-edit-table",
        title: "Edit dept",
        loadAllFieldsURL: "../rest/dept/",
        updateRowURL: "../rest/dept/edit/",
        labels: ["ID", "Name"],
        validate: validateDept,
        events: ["DrawDeptsList"],

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Edit dept",
                isButton: true,
                classes: "btn btn-primary cust",
                clicked: function() { }
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

function getDeptAddFormOpts() {

    return {
        $container: $(".depts-form-container"),
        $containerForm: $("<form>").addClass(".dept-add-form"),
        classes: "dept-add-table",
        title: "Add dept",
        updateRowURL: "../rest/dept/add",
        template: {name: ""},
        labels: ["Name"],
        validate: validateDept,
        events: ["DrawDeptsList"],

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Add dept",
                classes: "btn btn-primary cust",
                isButton: true,
                clicked: function() { }
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

function getEmpEditFormOpts() {

    return {
        $container: $(".emps-form-container"),
        $containerForm: $("<form>").addClass(".emp-edit-form"),
        classes: "emp-edit-table",
        title: "Edit employee",
        loadAllFieldsURL: "../rest/emp/",
        updateRowURL: "../rest/emp/edit/",
        labels: ["ID", "Name", "Birthday", "HireDate",
            "Address", "Email", "Dept", "Salary"],
        validate: validateEmp,
        events: ["DrawDeptsList"],

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Edit employee",
                classes: "btn btn-primary cust",
                isButton: true,
                clicked: function() { }
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

function getEmpAddFormOpts() {

    return {
        $container: $(".emps-form-container"),
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
            dept: {name: ""},
            salary: ""
        },
        labels: ["Name", "Birthday", "HireDate",
            "Address", "Email", "Dept", "Salary"],
        validate: validateEmp,
        events: ["DrawDeptsList"],

        //--------------buttons outside form------------------------
        outerButtons: [
            {   value: "Add employee",
                classes: "btn btn-primary cust",
                isButton: true,
                clicked: function() { }
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