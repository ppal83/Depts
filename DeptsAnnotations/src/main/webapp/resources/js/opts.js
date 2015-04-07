function getDeptsTableOpts() {

    return {

        $container: $(".depts-container"),
        title: "List of departments",
        loadAllRowsURL: "../rest/depts",
        deleteRowURL: "../rest/dept/delete/",
        classes: "table table-bordered table-hover depts-table",
        headers: ['Dept ID', 'Dept name', 'Edit', 'Delete', 'View'],
        innerButtons: [
            {
                value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function () {
                    return function () {
                        self.deleteRow(id);
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
                        self.fireSwitchView(id);
                    }
                }
            }],

        outerButtons: [
            {},

            {}
        ]
    };

}

function getEmpsTableOpts() {

    return {

        $container: $(".emps-container"),
        title: "List of employees",
        loadAllRowsURL: "../rest/emps",
        deleteRowURL: "../rest/emp/delete/",
        classes: "table table-bordered table-hover emps-table",
        headers: ['Employee ID', 'Employee name', 'Birthday', 'Hireday', 'Address', 'Email',
            'Department', 'Salary', 'Edit', 'Delete'],
        innerButtons: [
            {
                value: "Edit",
                classes: "btn btn-sm btn-primary",
                clicked: function () {
                    return function () {
                        self.deleteRow(id)
                    }
                }
            },

            {
                value: "Delete",
                classes: "btn btn-sm btn-danger",
                clicked: function (self, id) {
                    return function () {
                        self.deleteRow(id)
                    }
                }
            }]
    };

}