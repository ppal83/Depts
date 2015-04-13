var TableDrawer = DataSource.extend({

    init: function(name, options) {
        this._super(name, options);
        var self = this;
        $.each(this.opts.events, function() {
            self["fire"+this] = self.fireOnPageController(this);
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
        var self = this;
        $.each(this.opts.headers, function(i) {
            var $th = $("<th>");
            $tr.append( $th.html(self.opts.headers[i]) );
        });
        this.$table.append($tr);
        return this;
    },

    addRow: function(element) {
        var $tr = $("<tr>");
        $.each(element, function(k, v) {
            if ( k == "birthDate" || k == "hireDate") {
                v = new Date(v).format("yyyy-mm-dd");
            }

            if ( k == "dept") {
                v = v.name;
            }
            $tr.append( $("<td>").html(v) );
        });

        this.addInnerButtons($tr, element.id);
        this.$table.append($tr);
    },

    addRows: function() {
        $.each( this.dataArray, $.proxy(function (i, e) {
            this.addRow(e);
        }, this) );
        return this;
    },

    addInnerButtons: function($tr, id) {
        var btns = this.opts.innerButtons;
        if (!btns) return;
        for (var i = 0; i < btns.length; i++) {
            var self = this;

            $("<td>").append( $("<a>").addClass(btns[i].classes)
                .html(btns[i].value)
                .click(btns[i].clicked(self, id)) )
                .appendTo($tr);
        }
    },

    addOuterButtons: function() {
        var self = this;
        $.each(this.opts.outerButtons, function() {
            $("<a>").addClass(this.classes).html(this.value)
                .click( this.clicked(self) ).appendTo(self.opts.$container);
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
            }).done( function() {
                self.fireUpdate()
            });
    },

    deleteRow: function(id) {
        var self = this;
        $.getJSON(this.opts.deleteRowURL + id)
            .done( function(data) {
                self.loadAllRows( data.dept ? data.dept.id : "" );
            });
    }

});