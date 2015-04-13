var SupportObject = Class.extend({

    init: function (name, options) {
        this.name = name;
        this.opts = options;
    },

    setOpts: function(options) {
        this.opts = options;
        return this;
    },

    setPageController: function (pc) {
        this.pc = pc;
        return this;
    },

    fireOnPageController: function(event) {
        var self = this;
        return function(id) {
            $(self.pc).trigger(event, [id]);
        }
    },

    hide: function() {
        this.clearContainer();
    },

    show: function() {
        this.addToContainer();
    },

    addToContainer: function() {
        this.opts.$container.append(this.$table);
        return this;
    },

    clearContainer: function() {
        this.opts.$container.empty();
        return this;
    },

    addTitle: function() {
        this.opts.$container.append( $("<h2>").html(this.opts.title) );
        return this;
    },

    createTable: function() {
        this.$table = $("<table>").addClass(this.opts.classes);
        return this;
    },

    subscribe: function(event, fx, scope) {
        $(this).on(event, $.proxy(fx, scope));
    },

    fire: function(event, data) {
        $(this).trigger(event, data);
    }

});