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
				required: "Username required",
				minlength: "Username length should be not less than 2 characters",
				maxlength: "Username length should be not more than 30 characters",
				remote: $.validator.format("{0} is already in use")
			},
			
			password: {
				required: "Password required",
				minlength: "Password length should be not less than 6 characters"
			},
			
			email: {
				required: "Email required",
				minlength: "Email length should be not less than 3 characters",
				maxlength: "Email length should be not more than 30 characters",
				email: "Email address is not valid",
				remote: $.validator.format("{0} is already in use")
			}
		}
	});
});