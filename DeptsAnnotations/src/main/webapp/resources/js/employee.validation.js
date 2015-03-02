$(document).ready(function(){
	
	//checks whether date entered is today's or the past date
	jQuery.validator.addMethod("past", 
			function(value, element, params) {
				if (value.length == 9) {
					value = value.slice(0,8) + "0" + value.slice(-1);
				}
				return (new Date(value) <= new Date()); 	
			},'Past birthday date required');

	$("#empl_form").validate({
		rules: {
			name: {
				required: true,
				minlength: 2,
				maxlength: 30,
			},
			
			birthDate: {
				required: true,
				dateISO: true,
				past: true
			},
			
			hireDate: {
				required: true,
				dateISO: true,
			},
			
			address: {
				required: true,
				minlength: 2,
				maxlength: 30
			},
			
			email: { 
				required: true,
				minlength: 3,
				maxlength: 30,
				email: true,
			},
			
			salary: { 
				required: true,
				min: 0
			}
		},

		messages: {
			name: {
				required: "Name required(cl-side)",
				minlength: "Name length should be not less than 2 characters(cl-side)",
				maxlength: "Name length should be not more than 30 characters(cl-side)"
			},
			
			birthDate: {
				required: "Birthday date required(cl-side)",
				dateISO: "Birthday date is not well formed(cl-side)"
			},
			
			hireDate: {
				required: "Hire date required(cl-side)",
				dateISO: "Hire date is not well formed(cl-side)"
			},
			
			address: {
				required: "Address required(cl-side)",
				minlength: "Address length should be not less than 2 characters(cl-side)",
				maxlength: "Address length should be not more than 30 characters(cl-side)",
			},
			
			email: {
				required: "Email required(cl-side)",
				minlength: "Email length should be not less than 3 characters(cl-side)",
				maxlength: "Email length should be not more than 30 characters(cl-side)",
				email: "Email address is not valid(cl-side)"
			},
			
			salary: {
				required: "Salary required(cl-side)",
				min: "Wrong Salary(cl-side)"
			}
		}
	});
});