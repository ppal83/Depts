function validateDept(self) {

    $("form").validate({
        //errorLabelContainer: '.depts-table-container',
        submitHandler: function () {
            self.updateRow();
        }
    });

    $("#name").rules("add", {
        required: true,
        minlength: 2,
        maxlength: 30,
        messages: {
            required: "Name reqired",
            minlength: "Name length should be not less than 2 characters",
            maxlength: "Name length should be not more than 30 characters"
        }
    });

}