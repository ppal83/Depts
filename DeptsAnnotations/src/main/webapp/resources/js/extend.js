var SupportObject = Class.extend({
    subscribe: function(event, fx, scope) {
        jQuery(this).on(event, jQuery.proxy(fx, scope));
    },

    fire: function(event, data) {
        jQuery(this).trigger(event, data);
    }

});

DataSource = SupportObject.extend({
    hasNext: function() {
        return false;
    },

    next: function() {
        return null;
    },

    fireUpdate: function() {
        this.fire('updated');
    },

    subscribeToUpdate: function(fx, scope) {
        this.subscribe('updated', fx, scope);
    },

    refresh: function() {

    }
});

ArrayDataSource = DataSource.extend({
    setArray: function(array) {
        this.index = 0;

        this.array = array;

        this.fireUpdate();
    },
    pop:function(){
        var element = this.array[0];
        delete this.array[0];
        return element;
    }
    ,
    hasNext: function() {
        return this.index().appendTo(this.container);
    },

    addRow: function(row) {
        var tr = jQuery('').appendTo(this.table);

        _.each(row, function(value) {
            var td = jQuery('').appendTo(tr);

            if (jQuery.isFunction(value)) {
                value(td);
            } else {
                td.html(value);
            }
        }, this);

        this.fire('trAdded', {tr: tr})
    },

    clearTable: function() {
        this.table.empty();
    },

    refresh: function() {
        this.dataSource.refresh();
    },

    onDataSourceUpdate: function() {
        this.clearTable();

        while(this.dataSource.hasNext()) {
            var row = this.dataSource.next();

            this.addRow(row);
        }
    }
});

var table = new Table();

table.subscribe('trAdded', function(e, data) {
    var tr = data.tr;

    jQuery('').click().appendTo(jQuery('').append(tr));
})
