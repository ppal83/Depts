define(["jquery-2.1.3.min", "validation/emp-validation"], function(jquery, validateEmp) {

    return {
        $container: $(".emps-form-container"),
        $containerForm: $("<form>").addClass(".emp-add-form"),
        classes: "emp-add-table",
        title: "Add employee",
        updateRowURL: "../rest/emp/add",
        template: {
            name: "",
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
            {
                value: "Add employee",
                classes: "btn btn-primary cust",
                isButton: true,
                clicked: function () {
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