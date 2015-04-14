define(["class-extend"], function() {

    return Class.extend({

        init: function (name, options) {
            this.name = name;
            this.opts = options;
            this.addModules();
            this.subscribeToEvents();
        },

        setOpts: function (options) {
            this.opts = options;
            return this;
        },

        addModules: function () {
            var self = this;
            $.each(this.opts.modules, function () {
                self[this.name] = this;
                self[this.name].setPageController(self);
            });
        },

        subscribeToEvents: function () {
            var self = this;
            $.each(this.opts.events, function () {
                var objToShow = this.objToShow;
                $(self).on(this.event, function (event, id) {
                    $.each(self.opts.modules, function () {
                        self[this.name].hide();
                    });
                    self[objToShow].show(id);
                });
            });
        }

    });
});