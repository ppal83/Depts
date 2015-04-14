define(["jquery-2.1.3.min","validation/dept-validation"], function(jquery, validateDept) {
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
            {
                value: "Add dept",
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