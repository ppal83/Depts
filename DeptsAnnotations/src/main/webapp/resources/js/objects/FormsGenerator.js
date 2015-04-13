var FormsGenerator = DataSource.extend({

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
        this.$id = id;
        this.loadAllFields(id);
    },

    addToContainer: function() {
        this.opts.$containerForm.append(this.$table);
        this.opts.$container.append(this.opts.$containerForm);
        return this;
    },

    clearContainer: function() {
        this._super();
        this.opts.$containerForm.empty();
        return this;
    },

    addFields: function() {
        var self = this, i = 0;

        $.each(this.dataArray, function(k, v) {
            var $tr = $("<tr>");

            $("<td>").append($("<label>")
                .addClass("form-control")
                .attr("for", k).html(self.opts.labels[i++]))
                .appendTo($tr);

            $("<td>").append($("<input>")
                .addClass("form-control")
                .attr({"value": k == "dept" ? v.name : v,
                    "id": k, "name": k,
                    "placeholder": k == "birthDate" ||
                    k == "hireDate" ? "yyyy-MM-dd" : "" })
                .prop("readonly", k == "id") )
                .appendTo($tr);
            $("<td>").append( $("<span>").addClass("error") ).appendTo($tr);

            $tr.appendTo(self.$table);
        });

        return this;
    },

    addOuterButtons: function() {
        var self = this;

        $.each(this.opts.outerButtons, function() {
            var $tr = $("<tr>");
            var btn = this.isButton ? $("<button>") : $("<a>");

            $("<td>").attr("colspan", 2)
                .append( btn.addClass(this.classes)
                    .html(this.value)
                    .click( this.clicked(self) ) )
                .appendTo($tr);

            $tr.appendTo(self.$table);
        });
        return this;
    },

    validate: function () {
        this.opts.validate(this);
        return this;
    },

    draw: function() {
        this.clearContainer()
            .addTitle()
            .createTable()
            .addFields()
            .addOuterButtons()
            .addToContainer()
            .validate();
    },

    loadAllFields: function(id) {
        var self = this;
        if ( id ) {
            $.getJSON(this.opts.loadAllFieldsURL + id,
                function (data) {
                    self.setData(data);
                }).done(function () {
                    self.fireUpdate();
                });
        } else {
            this.dataArray = this.opts.template;
            this.draw();
        }
    },

    updateRow: function() {
        var self = this;
        var jsonObject = {id: this.$id};

        $.each(this.dataArray, function(k) {
            if (k == "dept") {
                jsonObject[k] = { name: $("#" + k).val() }
            } else {
                jsonObject[k] = $("#" + k).val();
            }
        });

        $.ajax({
            url: this.opts.updateRowURL +
            ( !!this.$id ? this.$id : "" ),
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(jsonObject),
            success: function() {
                self.fireDrawDeptsList();
            }
        });
    }
});