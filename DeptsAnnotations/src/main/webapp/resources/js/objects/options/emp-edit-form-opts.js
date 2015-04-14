define(["jquery-2.1.3.min", "validation/emp-validation"], function(jquery, validateEmp) {

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
            {
                value: "Edit employee",
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