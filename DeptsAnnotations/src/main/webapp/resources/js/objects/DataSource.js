define(["objects/SupportObject"], function(SupportObject) {

    return SupportObject.extend({

        fireUpdate: function () {
            this.fire("updated");
        },

        subscribeToUpdate: function (fx, scope) {
            this.subscribe("updated", fx, scope);
        },

        setData: function (data) {
            this.dataArray = data;
        }

    })
});
