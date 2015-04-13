function getPageControllerOpts() {

    return {
        modules: [
            new TableDrawer( "deptsTDrawer", getDeptsTableOpts() ),
            new TableDrawer( "empsTDrawer", getEmpsTableOpts() ),
            new FormsGenerator( "deptAddFormGenerator", getDeptAddFormOpts() ),
            new FormsGenerator( "deptEditFormGenerator", getDeptEditFormOpts() ),
            new FormsGenerator( "empAddFormGenerator", getEmpAddFormOpts() ),
            new FormsGenerator( "empEditFormGenerator", getEmpEditFormOpts() )
        ],
        events: [
            { event: "DrawEmpsList", objToShow: "empsTDrawer" },
            { event: "DrawDeptsList", objToShow: "deptsTDrawer" },
            { event: "DrawDeptAddForm", objToShow: "deptAddFormGenerator", id: 0 },
            { event: "DrawDeptEditForm", objToShow: "deptEditFormGenerator" },
            { event: "DrawEmpAddForm", objToShow:  "empAddFormGenerator" },
            { event: "DrawEmpEditForm", objToShow:  "empEditFormGenerator" }
        ]
    }

}