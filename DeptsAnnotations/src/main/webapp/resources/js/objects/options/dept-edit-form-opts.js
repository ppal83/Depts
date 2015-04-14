define(["jquery-2.1.3.min", "validation/dept-validation"], function(jquery, validateDept) {
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
            {
                value: "Edit dept",
                isButton: true,
                classes: "btn btn-primary cust",
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