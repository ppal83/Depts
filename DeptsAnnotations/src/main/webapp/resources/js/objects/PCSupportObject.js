var PCSupportObject = Class.extend({

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
            self[self.opts.modules.name] = this;
            self[self.opts.modules.name].setPageController(self);
            alert(self[self.opts.modules.name].name);
        });
    },

    subscribeToEvents: function() {
        var self = this;
        $.each(this.opts.events, function() {
            $(self).on(this.event, function (event, id) {
                $.each(self.opts.modules, function() {
                    self[self.opts.modules.name].hide();
                });
                this.objToShow.show(this.id ? this.id : id);
            });
        });
    }

});