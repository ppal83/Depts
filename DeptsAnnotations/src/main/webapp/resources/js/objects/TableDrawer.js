define(["objects/DataSource", "util/date-format"], function(DataSource) {

    return DataSource.extend({

        init: function(name, options) {
            this._super(name, options);
            var self = this;
            $.each(this.opts.events, function() {
                self["fire" + this] = self.fireOnPageController(this);
            });
            this.subscribeToUpdate();
        },

        subscribeToUpdate: function() {
            this._super(function() {
                this.draw();
            }, this);
        },

        show: function(id) {
            this._super();
            this.loadAllRows(id);
        },

        addHeader: function() {
            var $tr = $("<tr>");
            $.each(this.opts.headers, function() {
                $tr.append( $("<th>").html(this) );
            });
            this.$table.append($tr);
            return this;
        },

        addRow: function(element) {
            var $tr = $("<tr>");
            $.each(element, function (k, v) {
                if (k == "birthDate" || k == "hireDate") {
                    v = new Date(v).format("yyyy-mm-dd");
                } else if (k == "dept") {
                    v = v.name;
                }
                $tr.append( $("<td>").html(v) );
            });

            this.addInnerButtons($tr, element.id);
            this.$table.append($tr);
        },

        addRows: function() {
            $.each( this.dataArray, $.proxy(function(i, e) {
                this.addRow(e);
            }, this) );
            return this;
        },

        addInnerButtons: function($tr, id) {
            if ( !this.opts.innerButtons ) return;
            var self = this;

            $.each(this.opts.innerButtons, function() {
                $("<td>").append( $("<a>").addClass(this.classes)
                    .html(this.value)
                    .click(this.clicked(self, id)) )
                    .appendTo($tr);
            });

        },

        addOuterButtons: function() {
            var self = this;
            $.each(this.opts.outerButtons, function() {
                $("<a>").addClass(this.classes)
                    .html(this.value)
                    .click(this.clicked(self))
                    .appendTo(self.opts.$container);
            })
        },

        draw: function() {
            this.clearContainer()
                .addTitle()
                .createTable()
                .addHeader()
                .addRows()
                .addToContainer()
                .addOuterButtons();
        },

        loadAllRows: function(id) {
            var self = this;
            $.getJSON(this.opts.loadAllRowsURL + ( !!id ? "/" + id : "" ),
                function(data) {
                    self.setData(data);
                }).done(function() {
                    self.fireUpdate();
                });
        },

        deleteRow: function(id) {
            var self = this;
            $.getJSON(this.opts.deleteRowURL + id)
                .done(function(data) {
                    self.loadAllRows(data.dept ? data.dept.id : "");
                });
        }

    })
});