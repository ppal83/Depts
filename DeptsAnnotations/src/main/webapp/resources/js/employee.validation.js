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
				maxlength: 30
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
			
			salary: { 
				required: true,
				min: 0
			}
		},

		messages: {
			name: {
				required: "Name required",
				minlength: "Name length should be not less than 2 characters",
				maxlength: "Name length should be not more than 30 characters",
			},
			
			birthDate: {
				required: "Birthday date required",
				dateISO: "Birthday date is not well formed"
			},
			
			hireDate: {
				required: "Hire date required",
				dateISO: "Hire date is not well formed"
			},
			
			address: {
				required: "Address required",
				minlength: "Address length should be not less than 2 characters",
				maxlength: "Address length should be not more than 30 characters",
			},
			
			salary: {
				required: "Salary required",
				min: "Wrong Salary"
			}
		}
	});
});