requirejs.config({
    baseUrl: "../resources/js/lib",
    shim: {
        "bootstrap" : { "deps" :['jquery'] },
        "jquery.validate" : { "deps" :['jquery'] }
    },
    paths: {
        jquery: "jquery-2.1.3.min",
        bootstrap: "bootstrap.min",
        "jquery.validate": "jquery.validate.min",
        app: "../app",
        objects: "../objects",
        validation: "../validation",
        util: "../util"
    }
});

requirejs(["jquery-2.1.3.min",
           "objects/PageController",
           "objects/options/pc-opts"],

    function(jquery, PageController, pc_opts) {
        $(document).ready(function() {
            var pc = new PageController( "PageController", pc_opts );
            pc.deptsTDrawer.show(); // initial table
        });
    }
);

