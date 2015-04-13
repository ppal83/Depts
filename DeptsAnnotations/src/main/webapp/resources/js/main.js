$(document).ready(function() {

    var pc = new PageController( "PageController", getPageControllerOpts() );

    /*
     //adding depts table drawer
     pc.addModule( new TableDrawer("deptsTDrawer", getDeptsTableOpts()) );

     //adding emps table drawer
     pc.addModule( new TableDrawer("empsTDrawer", getEmpsTableOpts()) );

     //adding dept add-form generator
     pc.addModule( new FormsGenerator("deptAddFormGenerator", getDeptAddFormOpts()) );

     //adding dept edit-form generator
     pc.addModule( new FormsGenerator("deptEditFormGenerator", getDeptEditFormOpts()) );

     //adding emp add-form generator
     pc.addModule( new FormsGenerator("empAddFormGenerator", getEmpAddFormOpts()) );

     //adding emp edit-form generator
     pc.addModule( new FormsGenerator("empEditFormGenerator", getEmpEditFormOpts()) );




     */



    pc.deptsTDrawer.show(); // initial table

});