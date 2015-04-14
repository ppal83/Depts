define(["jquery-2.1.3.min"], function() {
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
            {
                value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function (self, id) {
                    return function () {
                        self.fireDrawDeptEditForm(id);
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
            },

            {
                value: "View",
                classes: "btn btn-sm btn-primary",
                clicked: function (self, id) {
                    return function () {
                        self.fireDrawEmpsList(id);
                    }
                }
            }],


        //--------------buttons outside table------------------------
        outerButtons: [
            {
                value: "Add new dept",
                classes: "btn btn-primary dept-add-btn",
                clicked: function (self) {
                    return function () {
                        self.fireDrawDeptAddForm();
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