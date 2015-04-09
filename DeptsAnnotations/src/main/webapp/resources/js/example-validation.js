$(document).ready(function(){
    $("#reg_form").validate({
        rules: {
            username: {
                required: true,
                minlength: 2,
                maxlength: 30,
                remote: {
                    url: "checkUserName",
                    type: "post",
                    data: {
                        username: function() {
                            return $("#username").val();
                        }
                    }
                }
            },

            password: {
                required: true,
                minlength: 6
            },

            email: {
                required: true,
                minlength: 3,
                maxlength: 30,
                email: true,
                remote: {
                    url: "checkUserEmail",
                    type: "post",
                    data: {
                        email: function() {
                            return $("#email").val();
                        }
                    }
                }
            }
        },

        messages: {
            username: {
                required: "Username required(cl-side)",
                minlength: "Username length should be not less than 2 characters(cl-side)",
                maxlength: "Username length should be not more than 30 characters(cl-side)",
                remote: $.validator.format("{0} is already in use(cl-side)")
            },

            password: {
                required: "Password required(cl-side)",
                minlength: "Password length should be not less than 6 characters(cl-side)"
            },

            email: {
                required: "Email required(cl-side)",
                minlength: "Email length should be not less than 3 characters(cl-side)",
                maxlength: "Email length should be not more than 30 characters(cl-side)",
                email: "Email address is not valid(cl-side)",
                remote: $.validator.format("{0} is already in use(cl-side)")
            }
        }
    });
});