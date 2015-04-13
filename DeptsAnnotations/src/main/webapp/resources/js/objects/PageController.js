var PageController = Class.extend({

    init: function (name, options) {
        this.name = name;
        this.opts = options;
        this.addModules();
        this.subscribeToEvents();
    },

    setOpts: function(options) {
        this.opts = options;
        return this;
    },

    addModules: function() {
        var self = this;
        $.each(this.opts.modules, function() {
            self[this.name] = this;
            self[this.name].setPageController(self);
        });
    },

    subscribeToEvents: function() {
        var self = this;
        $.each(this.opts.events, function() {
            var objToShow = this.objToShow;
            $(self).on(this.event, function (event, id) {
                $.each(self.opts.modules, function() {
                    alert("self[self.opts.modules.name]" + self[this.name]);
                    self[this.name].hide();
                });
                alert("this = " + this);
                alert(self[objToShow] + " " + objToShow);
                self[objToShow].show(/*this.id ? this.id : */id);
            });
        });
    }


    /*
     init: function (name) {
     this.name = name;

     this.subscribeToDrawEmpsList();
     this.subscribeToDrawDeptsList();
     this.subscribeToDeptAdd();
     this.subscribeToDeptEdit();
     this.subscribeToEmpEdit();
     this.subscribeToEmpAdd();
     },

     subscribeToDrawEmpsList: function () {
     $(this).on("DrawEmpsList", $.proxy(function (event, id) {
     this.empEditFormGenerator.hide();
     this.deptsTDrawer.hide();
     this.empsTDrawer.show(id);
     }, this));
     },

     subscribeToDrawDeptsList: function () {
     $(this).on("DrawDeptsList", $.proxy(function () {
     this.empsTDrawer.hide();
     this.deptEditFormGenerator.hide();
     this.empEditFormGenerator.hide();
     this.deptsTDrawer.show();
     }, this));
     },

     subscribeToDeptEdit: function () {
     $(this).on("DrawDeptEditForm", $.proxy(function (event, id) {
     this.deptsTDrawer.hide();
     this.deptEditFormGenerator.show(id);
     }, this));
     },

     subscribeToDeptAdd: function () {
     $(this).on("DrawDeptAddForm", $.proxy(function () {
     this.deptsTDrawer.hide();
     this.deptAddFormGenerator.show(0);
     }, this));
     },

     subscribeToEmpEdit: function () {
     $(this).on("DrawEmpEditForm", $.proxy(function (event, id) {
     this.empsTDrawer.hide();
     this.empEditFormGenerator.show(id);
     }, this));
     },

     subscribeToEmpAdd: function () {
     $(this).on("DrawEmpAddForm", $.proxy(function (event, id) {
     this.empsTDrawer.hide();
     this.empAddFormGenerator.show(id);
     }, this));
     }
     */

});