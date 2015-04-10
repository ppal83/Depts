function validateEmp(self) {

    $("form").validate({
        //errorLabelContainer: '.depts-table-container',
        submitHandler: function () {
            self.updateRow();
        }
    });

    $.validator.addMethod("past", function(val) {
        if (val.length == 9) {
            val = val.slice(0, 8) + "0" + val.slice(-1);
        }
        return ( new Date(val) <= new Date() );
    },'Past birthday date required');

    $("#name").rules("add", {
        required: true,
        minlength: 2,
        maxlength: 30,
        remote: function() {
            return {
                url: "../rest/check/emp/name",
                method: "post",
                contentType: "application/json",
                dataType: "json",
                data: getEmpObject()
            }
        },

        messages: {
            required: "Name reqired",
            minlength: "Name length should be not less than 2 characters",
            maxlength: "Name length should be not more than 30 characters",
            remote: "Name exists"
        }

    });

    $("#birthDate").rules("add", {
        required: true,
        dateISO: true,
        past: true,
        messages: {
            required: "Birthday date required",
            dateISO: "Birthday date is not well formed"
        }
    });

    $("#hireDate").rules("add", {
        required: true,
        dateISO: true,
        messages: {
            required: "Hire date required",
            dateISO: "Hire date is not well formed"
        }
    });

    $("#address").rules("add", {
        required: true,
        minlength: 2,
        maxlength: 30,
        messages: {
            required: "Address required",
            minlength: "Address length should be not less than 2 characters",
            maxlength: "Address length should be not more than 30 characters"
        }
    });

    $("#email").rules("add", {
        required: true,
        minlength: 3,
        maxlength: 30,
        email: true,
        remote: function() {
            return {
                url: "../rest/check/emp/email",
                method: "post",
                contentType: "application/json",
                dataType: "json",
                data: getEmpObject()
            }
        },
        messages: {
            required: "Email required",
            minlength: "Email length should be not less than 3 characters",
            maxlength: "Email length should be not more than 30 characters",
            email: "Email address is not valid",
            remote: "Email exists"
        }
    });

    $("#dept").rules("add", {
        remote: function() {
            return {
                url: "../rest/check/dept/name-exists",
                method: "get",
                data: $("#dept").val()
            }
        },
        messages: {
            remote: "Dept not exists"
        }
    });

    $("#salary").rules("add", {
        required: true,
        min: 0,
        messages: {
            required: "Salary required",
            min: "Wrong Salary"
        }
    });

    function getEmpObject() {
        return JSON.stringify({
            id: $("#id").val(),
            name: $("#name").val(),
            birthDate: $("#birthDate").val(),
            hireDate: $("#hireDate").val(),
            address: $("#address").val(),
            email: $("#email").val(),
            dept: self.dataArray.dept,
            salary: $("#salary").val()
        })
    }
}

