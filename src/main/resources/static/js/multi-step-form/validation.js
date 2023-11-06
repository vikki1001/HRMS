$(document).ready(function() {

	$.validator.addMethod('username', function(value, element, param) {
		var nameRegex = /^[a-zA-Z0-9]+$/;
		return value.match(nameRegex);
	}, 'Only a-z, A-Z, 0-9 characters are allowed');

	var val = {
		// Specify validation rules
		rules: {
			 
			/* Basic Details */
			coEmailId: {
				required: true,
				email: true
			},
			tenantId: {
				required: true,
			},
			firstName: {
				required: true,
			},
			lastName: {
				required: true,
			},
			fullName: {
				required: true,
			},
			departName: {
				required: true,
			},
			gender: {
				required: true,
			},
			grade: {
				required: true,
			},

			/* Personal Details */
			birthDate: {
				required: true,
			},
			placeOfBirth: {
				required: true,
			},
			bloodGroup: {
				required: true,
			},
			contactNo: {
				required: true,
			},
			maritalStatus: {
				required: true,
			},
			religion: {
				required: true,
			},
			addharNumber: {
				required: true,
			},
			panNumber: {
				required: true,
			},
			personalEmailId1: {
				required: true,
			},

			/*Work Details*/
			dateOfJoining: {
				required: true,
			},
			employeeType: {
				required: true,
			},
			businessUnit: {
				required: true,
			},
			repotingManager: {
				required: true,
			},
			baseLocation: {
				required: true,
			},
			currentLocation: {
				required: true,
			},

			/* Address Details*/
			presentAddress: {
				required: true,
			},
			permanentAddress: {
				required: true,
			},
			presentCity: {
				required: true,
			},
			permanentCity: {
				required: true,
			},
			presentState: {
				required: true,
			},
			permanentState: {
				required: true,
			},
			presentPinCode: {
				required: true,
			},
			permanentPinCode: {
				required: true,
			},
			presentCountry: {
				required: true,
			},
			permanentCountry: {
				required: true,
			},

			/* Emergency Contact Details*/
			emergContactName1: {
				required: true,
			},
			emergContactNo1: {
				required: true,
			},

		},

		// Specify validation error messages
		messages: {

			/* Basic Details */
			coEmailId: {
				required: "Email must be required",
				email: "Please enter a valid e-mail",
			},
			tenantId: " Please select TenantId",
			firstName: "First name must be required",
			lastName: "Last name must be required",
			fullName: "Fullname is required",
			departName: "Please select Department",
			gender: "Select your Gender ",
			grade: "Select your Grade ",


			/* Personal Details */
			birthDate: "Please Enter Your BirthDate",
			placeOfBirth: "Please Enter Birth place",
			bloodGroup: "Please Enter Or choose your Blood Group",
			contactNo: "Contact number must be required",
			maritalStatus: "Please Enter your Marital status",
			religion: "Please enter religion",
			addharNumber: "Please enter you AadharNumber",
			panNumber: "Please Enter your Pan Number",
			personalEmailId1: "Please Enter your Personal Email Id_1",

			/*Work Details*/
			dateOfJoining: "Enter Your JoiningDate",
			employeeType: "Employye Type is required",
			businessUnit: "Please Enter the Unit",
			repotingManager: "Please Enter Your Reporting Manager",
			baseLocation: "Please Enter Your Base Location",
			currentLocation: "Please Your Current Location",

			/* Address Details*/
			presentAddress: "PresentAddress must be required",
			permanentAddress: "PermanentAddress must be required",
			presentCity: "Presentcity must be required",
			permanentCity: "Permanentcity must be required",
			presentState: "Presentstate must be required",
			permanentState: "Permanentstate must be required",
			presentPinCode: "PresentpinCode must be required",
			permanentPinCode: "PermanentpinCode must be required",
			presentCountry: "Presentcountry must be required",
			permanentCountry: "PermanentCountry must be required",

			/* Emergency Contact Details*/
			emergContactName1: "Please Fill This field",
			emergContactNo1: "Please Enter your TelNum",

		}
	}
	$("#myForm").multiStepForm(
		{
			// defaultStep:0,
			beforeSubmit: function(form, submit) {
				console.log("called before submiting the form");
				console.log(form);
				console.log(submit);
			},
			validations: val,
		}
	).navigateTo(0);
});