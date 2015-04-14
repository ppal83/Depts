define(["objects/TableDrawer",
        "objects/FormsGenerator",
        "objects/options/depts-table-opts",
        "objects/options/emps-table-opts",
        "objects/options/dept-add-form-opts",
        "objects/options/dept-edit-form-opts",
        "objects/options/emp-add-form-opts",
        "objects/options/emp-edit-form-opts"
    ],

    function(TableDrawer, FormsGenerator, depts_table_opts, emps_table_opts,
             dept_add_form_opts, dept_edit_form_opts, emp_add_form_opts, emp_edit_form_opts ) {

        return {
            modules: [
                new TableDrawer( "deptsTDrawer", depts_table_opts ),
                new TableDrawer( "empsTDrawer", emps_table_opts ),
                new FormsGenerator( "deptAddFormGenerator", dept_add_form_opts ),
                new FormsGenerator( "deptEditFormGenerator", dept_edit_form_opts ),
                new FormsGenerator( "empAddFormGenerator", emp_add_form_opts ),
                new FormsGenerator( "empEditFormGenerator", emp_edit_form_opts )
            ],

            events: [
                { event: "DrawEmpsList", objToShow: "empsTDrawer" },
                { event: "DrawDeptsList", objToShow: "deptsTDrawer" },
                { event: "DrawDeptAddForm", objToShow: "deptAddFormGenerator" },
                { event: "DrawDeptEditForm", objToShow: "deptEditFormGenerator" },
                { event: "DrawEmpAddForm", objToShow:  "empAddFormGenerator" },
                { event: "DrawEmpEditForm", objToShow:  "empEditFormGenerator" }
            ]
        }
    }
);