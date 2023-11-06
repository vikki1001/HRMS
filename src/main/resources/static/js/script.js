 /*CheckInOut Validation,Audit Listing Report, Emp Time Summary*/
 function auditValidateForm() {
    var date = document.getElementById("date").value;
    var date1 = document.getElementById("date1").value;	
	
	 if (!date == null || date != "") {     
	       	document.getElementById("blankMsg").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg").innerHTML = "fromDate must be required ";
      return false;
    } 
	
	if (!date1 == null || date1 != "") {     
	       	document.getElementById("blankMsgs").innerHTML = "";   
    } else {
			 document.getElementById("blankMsgs").innerHTML = "toDate must be required ";
      return false;
    }
}

 /* User Audit Validation */
 function userValidateForm() {
    var date = document.getElementById("date").value;
    var date1 = document.getElementById("date1").value;
    var user = document.getElementById("user").value;
	
	 if (!date == null || date != "") {     
	       	document.getElementById("blankMsg").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg").innerHTML = "fromDate must be required ";
      return false;
    } 
	
	if (!date1 == null || date1 != "") {     
	       	document.getElementById("blankMsgs").innerHTML = "";   
    } else {
			 document.getElementById("blankMsgs").innerHTML = "toDate must be required ";
      return false;
    }
    
     if (!user == null || user != "") {     
	       	document.getElementById("userMsg").innerHTML = "";   
    } else {
			 document.getElementById("userMsg").innerHTML = "Username must be required ";
      return false;
    }

}

/* Role Audit Validation */
 function roleValidateForm() {
    var date = document.getElementById("date").value;
    var date1 = document.getElementById("date1").value;
    var role = document.getElementById("role").value;
	
	
	 if (!date == null || date != "") {     
	       	document.getElementById("blankMsg").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg").innerHTML = "fromDate must be required ";
      return false;
    } 
	
	if (!date1 == null || date1 != "") {     
	       	document.getElementById("blankMsgs").innerHTML = "";   
    } else {
			 document.getElementById("blankMsgs").innerHTML = "toDate must be required ";
      return false;
    }
    
    if (!role == null || role != "") {     
	       	document.getElementById("roleMsg").innerHTML = "";   
    } else {
			 document.getElementById("roleMsg").innerHTML = "Role code must be required ";
      return false;
    }
}

/*Upload Image*/
function readURL(input) {
  if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
          $('#blah')
              .attr('src', e.target.result);
      };

      reader.readAsDataURL(input.files[0]);
  }
}

/*Reset Password validation*/
function resetPwdValidateForm() {
    var pswrd_1 = document.getElementById("pswrd_1").value;
	var pswrd_2 = document.getElementById("pswrd_2").value;
	
 
	 if (!pswrd_1 == null || pswrd_1 != "") {     
	       	document.getElementById("blankMsg1").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg1").innerHTML = "New password must be required ";
      return false;
    } 
if (!pswrd_2 == null || pswrd_2 != "") {     
	       	document.getElementById("blankMsg2").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg2").innerHTML = "Confirm new password must be required ";
      return false;
    } 
}

/*Change Password validation*/
function cngPwdValidateForm() {
	var old_pswrd = document.getElementById("old_pswrd").value;
    var pswrd_1 = document.getElementById("pswrd_1").value;
	var pswrd_2 = document.getElementById("pswrd_2").value;
	
 
	 if (!old_pswrd == null || old_pswrd != "") {     
	       	document.getElementById("blankMsg3").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg3").innerHTML = "Old password must be required ";
      return false;
    } if (!pswrd_1 == null || pswrd_1 != "") {     
	       	document.getElementById("blankMsg1").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg1").innerHTML = "New password must be required ";
      return false;
    } 
	if (!pswrd_2 == null || pswrd_2 != "") {     
	       	document.getElementById("blankMsg2").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg2").innerHTML = "Confirm new password must be required ";
      return false;
    } 
}
 
 /*Email Template validation*/
  function emailValidateForm() {
	var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var subject = document.getElementById("subject").value;
	var msg = document.getElementById("msg").value;
	
	 if (!name == null || name != "") {     
	       	document.getElementById("blankMsg0").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg0").innerHTML = "Template name must be required ";
      return false;
    } 
    
    if(!email == null || email != ""){
			document.getElementById('blankMsg1').innerHTML="";
	}else{
		document.getElementById('blankMsg1').innerHTML="Email must be required";
			return false;
	}
	if (email.indexOf('@') <=0 ){
			document.getElementById("blankMsg1").innerHTML = "like, user@gmail.com";
    		return false;
	}if ((email.charAt(email.length-4)!='.') && (email.charAt(email.length-3)!='.')){
			document.getElementById("blankMsg1").innerHTML = "like, user@gmail.com ";
			return false;
	}
	
	if (!subject == null || subject != "") {     
	       	document.getElementById("blankMsg2").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg2").innerHTML = "Subject must be required ";
      return false;
    } 

if (!msg == null || msg != "") {     
	       	document.getElementById("blankMsg3").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg3").innerHTML = "Message must be required ";
      return false;
    } 
}

/*SMS Template validation*/
 function smsValidateForm() {
    var mobileNo = document.getElementById("mobileNo").value;
	var msg = document.getElementById("msg").value;
	    
     if (!mobileNo == null || mobileNo != "") {     
	       	document.getElementById("blankMsg").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg").innerHTML = "Mobile number must be required ";
      return false;
    } 
    if(mobileNo.length!=10){
			document.getElementById("blankMsg").innerHTML = "Mobile numbers must only contain 10 digits";
      	return false;
	}
	if(isNaN(mobileNo)){
			 document.getElementById("blankMsg").innerHTML = "Mobile numbers must only contain 10 digits";
      return false;
	}
	    
	if (!msg == null || msg != "") {     
	       	document.getElementById("blankMsg2").innerHTML = "";   
    } else {
			 document.getElementById("blankMsg2").innerHTML = "Message must be required ";
      return false;
    } 
 }

/*CheckIn & CheckOut Button & Progress Bar Fill*/ 
function change() {
            var elem = document.getElementById("myButton");
            if (elem.value == "Check Out") elem.value = "Check In",
                elem.style.backgroundColor = '#35567a';
            else elem.value = "Check Out",
                elem.style.backgroundColor = '#ff9b44';


            var elem = document.getElementById("myButton2");
            if (elem.value == "Check Out") elem.value = "Check In",
                elem.style.backgroundColor = '#35567a';
            else elem.value = "Check Out",
                elem.style.backgroundColor = '#ff9b44';
}

/*Data Toggle*/
$(document).ready(function(){
  $('[data-toggle="tab"]').tab();   
});

/*Age Calculate*/
$(document).ready(function() {
	 var fulldate = document.getElementById('fulldate');
	    var result = document.getElementById('result');
	        $('#fulldate').keyup(function(){
	         var birthdate = new Date(fulldate.value);
	         var cur = new Date();
	         var diff = cur-birthdate;
	         var age = Math.floor(diff/31536000000) + " Year " + Math.floor((diff % 31536000000)/2628000000) + " Months";
	           result.value = age;
	        });
	 });
	 
/*Time Calculate in Add New Request*/
$(document).ready(function() {
	var fromDate = document.getElementById('fromDate');
	var toDate = document.getElementById('toDate');
	var time = document.getElementById('time');
  
  $('#toDate').keyup(function change(){
     	 var inDate = new Date(fromDate.value);
     	 var outDate = new Date(toDate.value);
      
	var totalSeconds = Math.abs(outDate.getTime() - inDate.getTime());
    var hrs = Math.floor((totalSeconds % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  	var min = Math.floor((totalSeconds % (1000 * 60 * 60)) / (1000 * 60));
    
	 time.value = hrs + ':' + min + ' Hrs';
            
             });
	 });
   
/*Check-In & Check-Out Button Hide in Attendance Module*/
  function disableBtn() {
    document.getElementById("checkIn").hidden = true;
    document.getElementById("checkOut").hidden = false;
  }

  function enableBtn() {
    document.getElementById("checkIn").hidden = false;
    document.getElementById("checkOut").hidden = true;

  }

/*Upload Document validation*/
  function uploadDocValidateForm() {
    var templateName = document.getElementById("templateName").value;
    var file = document.getElementById("file").value;
    var fileDepartment = document.getElementById("fileDepartment").value;
    
     if (!templateName == null || templateName != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "Template name must be required ";
        return false;
      } 
      
    if(!file == null || file != ""){
        document.getElementById('blankMsg1').innerHTML="";
    }else{
      document.getElementById('blankMsg1').innerHTML="File must be required";
        return false;
    }
    if(!fileDepartment == null || fileDepartment != ""){
      document.getElementById('blankMsg2').innerHTML="";
    }else{
      document.getElementById('blankMsg2').innerHTML="File department must be required";
      return false;
  }
}

/*Leave Summary validation*/
 function leaveSummaryValidateForm() {
    var leaveType = document.getElementById("leaveType").value;
    
     if (!leaveType == null || leaveType != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "first select leave type";
        return false;
      }
  }
  
/*Leave Approval validation*/
function leaveApprovalValidateForm() {
    var leaveType = document.getElementById("leaveType").value;
    
     if (!leaveType == null || leaveType != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "first select leave type";
        return false;
      }
  }
  
/*Expense Reimbursement Validation*/
 function expenseReimbValidateForm() {
    var employeeName = document.getElementById("employeeName").value;
    var date = document.getElementById("date").value;
    var reimbursementType = document.getElementById("reimbursementType").value;
    var attachment = document.getElementById("attachment").value;
    var expenseAmount = document.getElementById("expenseAmount").value;
    var reasonsForExpense = document.getElementById("reasonsForExpense").value;
    
     if (!employeeName == null || employeeName != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "Employee name must be required";
        return false;
      }      
      if (!date == null || date != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "Date must be required";
        return false;
      }      
      if (!reimbursementType == null || reimbursementType != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "Reimbursement Type must be required";
        return false;
      }      
      if (!attachment == null || attachment != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "Attachement must be required";
        return false;
      }      
      if (!expenseAmount == null || expenseAmount != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "Expense Amount must be required";
        return false;
      }      
      if (!reasonsForExpense == null || reasonsForExpense != "") {     
             document.getElementById("blankMsg6").innerHTML = "";   
      } else {
         document.getElementById("blankMsg6").innerHTML = "Reasons For Expense must be required";
        return false;
      }
  }
  

/*Exit Activity Validation*/
 function exitActivityValidateForm() {
    var empId = document.getElementById("empId").value;
    var reqLastWorkingDte = document.getElementById("reqLastWorkingDte").value;
    var description = document.getElementById("description").value;
    
     if (!empId == null || empId != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "Employee ID must be required";
        return false;
      }      
      if (!reqLastWorkingDte == null || reqLastWorkingDte != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "Req last working date must be required";
        return false;
      }      
      if (!description == null || description != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "Description must be required";
        return false;
      }     
  }
  
  /*Add New Request Validation*/
 function addNewReqValidateForm() {
    var attendanceDate = document.getElementById("attendanceDate").value;
    var checkinDateTime = document.getElementById("fromDate").value;
    var checkoutDateTime = document.getElementById("toDate").value;
    var reason = document.getElementById("reason").value;
    
     if (!attendanceDate == null || attendanceDate != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "Attendance day must be required";
        return false;
      }      
      if (!checkinDateTime == null || checkinDateTime != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "Check-In date & time must be required";
        return false;
      }      
      if (!checkoutDateTime == null || checkoutDateTime != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "Check-Out date & time must be required";
        return false;
      }       
      if (!reason == null || reason != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "Reason must be required";
        return false;
      }    
  }  
 
  /*Apply Leave Validation*/
 function leaveValidateForm() {
    var empId = document.getElementById("empId").value;
    var fromDate = document.getElementById("fromDate").value;
    var leaveType = document.getElementById("leaveType").value;
    var leaveDetails = document.getElementById("leaveDetails").value;
    var phoneNo = document.getElementById("phoneNo").value;
    var fullName = document.getElementById("fullName").value;
    var toDate = document.getElementById("toDate").value;
    var projectCode = document.getElementById("projectCode").value;
    var addrDuringLeave = document.getElementById("addrDuringLeave").value;
    var managerId = document.getElementById("managerId").value;
    
     if (!empId == null || empId != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "Employee ID must be required";
        return false;
      }      
      if (!fromDate == null || fromDate != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "From date must be required";
        return false;
      }      
      if (!leaveType == null || leaveType != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "Leave Type must be required";
        return false;
      }       
      if (!leaveDetails == null || leaveDetails != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "Leave Details must be required";
        return false;
      }      
       if (!phoneNo == null || phoneNo != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "Phone no. must be required";
        return false;
      }      
      if (!fullName == null || fullName != "") {     
             document.getElementById("blankMsg6").innerHTML = "";   
      } else {
         document.getElementById("blankMsg6").innerHTML = "Fullname must be required";
        return false;
      }      
      if (!toDate == null || toDate != "") {     
             document.getElementById("blankMsg7").innerHTML = "";   
      } else {
         document.getElementById("blankMsg7").innerHTML = "To date must be required";
        return false;
      }        
      if (!projectCode == null || projectCode != "") {     
             document.getElementById("blankMsg8").innerHTML = "";   
      } else {
         document.getElementById("blankMsg8").innerHTML = "Project code must be required";
        return false;
      }      
       if (!addrDuringLeave == null || addrDuringLeave != "") {     
             document.getElementById("blankMsg9").innerHTML = "";   
      } else {
         document.getElementById("blankMsg9").innerHTML = "Address during leave must be required";
        return false;
      }      
      if (!managerId == null || managerId != "") {     
             document.getElementById("blankMsg10").innerHTML = "";   
      } else {
         document.getElementById("blankMsg10").innerHTML = "Manager id ust be required";
        return false;
      }   
  }
  
/* Daily Work Report Validation */
 function dailyWorkReportValidateForm() {
    var fullName = document.getElementById("fullName").value;
    var date = document.getElementById("date").value;
    var officeInTime = document.getElementById("officeInTime").value;
	var officeOutTime = document.getElementById("officeOutTime").value;
	var projectName = document.getElementById("projectName").value;
	var module = document.getElementById("module").value;
	var description = document.getElementById("description").value;
	var status = document.getElementById("status").value;
	var analysis = document.getElementById("analysis").value;

	if (!fullName == null || fullName != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "FullName  must be required";
        return false;
      }	 
	if (!date == null || date != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "Date must be required";
        return false;
      }
	if (!officeInTime == null || officeInTime != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "OfficeInTime must be required";
        return false;
      }
	if (!officeOutTime == null || officeOutTime != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "OfficeOutTime must be required";
        return false;
      }
	if (!projectName == null || projectName != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "ProjectName must be required";
        return false;
      }
	if (!module == null || module != "") {     
             document.getElementById("blankMsg6").innerHTML = "";   
      } else {
         document.getElementById("blankMsg6").innerHTML = "Module must be required";
        return false;
      }
	if (!description == null || description != "") {     
             document.getElementById("blankMsg7").innerHTML = "";   
      } else {
         document.getElementById("blankMsg7").innerHTML = "Description must be required";
        return false;
      }
	if (!analysis == null || analysis != "") {     
             document.getElementById("blankMsg8").innerHTML = "";   
      } else {
         document.getElementById("blankMsg8").innerHTML = "Analysis must be required";
        return false;
      }
	if (!status == null || status != "") {     
             document.getElementById("blankMsg9").innerHTML = "";   
      } else {
         document.getElementById("blankMsg9").innerHTML = "Status must be required";
        return false;
      }
}

/* Holiday Form Validation*/
function holidayFormValidate() {
    var upload = document.getElementById("holidayFile").value;

	if (!upload == null || upload != "") {     
             document.getElementById("blankMsg").innerHTML = "";  
      } else {
         document.getElementById("blankMsg").innerHTML = "File must be required";
        return false;
      }
}

  $(document).ready(function(){
    $('#holidayFile').on('change',function(){
      myfiles = $(this).val();
      var ext = myfiles.split('.').pop();
      if(ext == "xlsx" || ext == "xls" || ext == "csv"){
        $('#blankMsg').css("display","none");
      }else{
        $('#blankMsg').html("Only excel file allow");
        $('#blankMsg').css("display","block");
        $('#blankMsg').css("color","red");
      }
    });
  });
  
  
  /* Payroll Validation*/
function payrollValidate() {
    var payrollFile = document.getElementById("payrollFile").value;

	if (!payrollFile == null || payrollFile != "") {     
             document.getElementById("blankMsg").innerHTML = "";  
      } else {
         document.getElementById("blankMsg").innerHTML = "File must be required";
        return false;
      }
}

  $(document).ready(function(){
    $('#payrollFile').on('change',function(){
      myfiles = $(this).val();
      var ext = myfiles.split('.').pop();
      if(ext == "xlsx" || ext == "xls" || ext == "csv"){
        $('#blankMsg').css("display","none");
      }else{
        $('#blankMsg').html("Only excel file allow");
        $('#blankMsg').css("display","block");
        $('#blankMsg').css("color","red");
      }
    });
  });

/* Payroll Validation*/
function payrollValidate() {
    var month = document.getElementById("month").value;
    var year = document.getElementById("ddlYears").value;

	if (!month == null || month != "") {     
             document.getElementById("blankMsg").innerHTML = "";  
      } else {
         document.getElementById("blankMsg").innerHTML = "Month must be required";
        return false;
      }
      
     if (!year == null || year != "") {     
             document.getElementById("blankMsg1").innerHTML = "";  
      } else {
         document.getElementById("blankMsg1").innerHTML = "Year must be required";
        return false;
      }
}
/* Select All Check Box in Role Menu Action Access */
$("#selectAll").click(function() {
	alet("Select All...");
    $("input[type=checkbox]").prop("checked", $(this).prop("checked"));
});


/* Search Employee */
function dropDown(event){
		    var val = $("#search").val();
		    if(val.length > 1){
		    	$.ajax({
		            type: "GET",
		            url: "/ksvsofttech/search?value="+val,
		            processData: false, // prevent jQuery from automatically transforming the data into a query string
		            contentType: false,
		            cache: false,
		            timeout: 6000,
		            success: function (data) {
		            	$('.dropdown-toggle').dropdown();
		            	$("#dropdown-container").empty();
		            	$("#dropdown-container").append(data);		            	
		            },
		    	  error: function (e) {
		              console.log("ERROR : ", e);		             
		          }
		      });		    	
		    }			
		}

/* Search Escalation */
function selectList(event){
		    var val = $("#escalationSearch").val();
		    if(val.length > 1){
		    	$.ajax({
		            type: "GET",
		            url: "/ksvsofttech/escalationSearch?value="+val,
		            processData: false, // prevent jQuery from automatically transforming the data into a query string
		            contentType: false,
		            cache: false,
		            timeout: 6000,
		            success: function (data) {
		            	$('.listdown-toggle').dropdown();
		            	$("#listdown-container").empty();
		            	$("#listdown-container").append(data);		            	
		            },
		    	  error: function (e) {
		              console.log("ERROR : ", e);		             
		          }
		      });		    	
		    }			
		}

/* Country,State, City Select */
$(document).ready(function(){
	$.ajax({
		url : '/ksvsofttech/country',
		cache : false,
		dataType : 'json',
		success : function(result){
			$.each(result,function(key,value){
				$('<option>').val(key).text(value).appendTo("#country");
			});
		}
	});
});

$(document).on("change","#country",function() {
	$("#state").find('option').remove();
	$("<option>").val("").text("Select State").append("#state");
	$("#city").find('option').remove();
	$("<option>").val("").text("Select City").append("#city");
	var selectCntryId = $("#country").val();
	$.ajax({
		url : '/ksvsofttech/state',
		data : {
			countryId : selectCntryId
		},
		dataType : 'json',
		success : function(result){
			$.each(result,function(key,value){
				$('<option>').val(key).text(value).appendTo("#state");
			});
		}
	});
});

$(document).on("change","#state",function() {
	$("#city").find('option').remove();
	$("<option>").val("").text("Select City").append("#city");
	var selectStateId = $("#state").val();
	$.ajax({
		url : '/ksvsofttech/city',
		data : {
			stateId : selectStateId
		},
		dataType : 'json',
		success : function(result){
			$.each(result,function(key,value){
				$('<option>').val(key).text(value).appendTo("#city");
			});
		}
	});
});

/* Year in Payroll Slip */
    $(document).ready(function(){
        //Reference the DropDownList.
        var ddlYears = document.getElementById("ddlYears");
 
        //Determine the Current Year.
        var currentYear = (new Date()).getFullYear();
 
        //Loop and add the Year values to DropDownList.
        for (var i = 1950; i <= currentYear; i++) {
            var option = document.createElement("OPTION");
            option.innerHTML = i;
            option.value = i;
            option.selected = currentYear;
            ddlYears.appendChild(option);
        }
    });
    
/* Average Value Calculate in selfAppraisal */
$(document).ready(function() {
	  $('#selfAppraisal').keyup(function change(){
		
	var a = $("#selfRatingI").val();
	var b = $("#selfRatingII").val();
	var c = $("#selfRatingIII").val();
	var d = $("#selfRatingIV").val();
	var e = $("#selfRatingV").val();
	var f = $("#selfRatingVI").val();
	var g = $("#selfRatingVII").val();
	var h = $("#selfRatingVIII").val();
	var i = $("#selfRatingIX").val();
	var j = $("#selfRatingX").val();
	
	var selfRatingA = "A";
	var selfRatingB = "B";
	var selfRatingC = "C";
	var selfRatingD = "D";
	var selfRatingE = "E";
	var selfRatingF = "F";
	var selfRatingG = "G";
			
	if(a == null && b == null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = 0;	
		var total = sum / 0;
		if(isNaN(total)){
			$("#total").val('0');
		} else {
			$("#total").val('0');
		}
		
		var total2 = sum / 1;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else {
			$("#total2").val('0');
		}
				
		
	} else if(a != null && b == null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a);	
		var total = sum / 1;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 1;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}		
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}		
	} else if(a != null && b != null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b);	
		var total = sum / 2;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 2;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}		
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	} else if(a != null && b != null && c != null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c);	
		var total = sum / 3;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 3;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	} else if(a != null && b != null && c != null && d != null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d);	
		var total = sum / 4;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 4;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}		
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e);	
		var total = sum / 5;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 5;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}		
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f);	
		var total = sum / 6;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 6;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}	
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g);	
		var total = sum / 7;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 7;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}	
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h);	
		var total = sum / 8;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 8;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}	
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i != null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h) + parseFloat(i);	
		var total = sum / 9;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 9;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}	
				
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i != null && j != null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h) + parseFloat(i) + parseFloat(j);	
		var total = sum / 10;
		if(isNaN(total)){
			$("#total").val('0');	
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 10;
		if(isNaN(total2)){			
			$("#total").val('0');	
		} else {
			$("#total2").val(total2.toFixed(2));		
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("SELF RATING :::" + selfRatingA);
			$("#selfRating").val(selfRatingA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("SELF RATING :::" + selfRatingB);
			$("#selfRating").val(selfRatingB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("SELF RATING :::" + selfRatingC);
			$("#selfRating").val(selfRatingC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("SELF RATING :::" + selfRatingD);
			$("#selfRating").val(selfRatingD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("SELF RATING :::" + selfRatingE);
			$("#selfRating").val(selfRatingE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("SELF RATING :::" + selfRatingF);
			$("#selfRating").val(selfRatingF);
		} else if(total <= "1" && total == "0"){
			console.log("SELF RATING :::" + selfRatingG);
			$("#selfRating").val(selfRatingG);
		}
	}	
    	});
	});
	
/* Average Value Calculate in level1AppraisalReview */
$(document).ready(function() {
	  $('#level1Appraisal').keyup(function change(){
		
	var a = $("#level1ApproverRatingI").val();
	var b = $("#level1ApproverRatingII").val();
	var c = $("#level1ApproverRatingIII").val();
	var d = $("#level1ApproverRatingIV").val();
	var e = $("#level1ApproverRatingV").val();
	var f = $("#level1ApproverRatingVI").val();
	var g = $("#level1ApproverRatingVII").val();
	var h = $("#level1ApproverRatingVIII").val();
	var i = $("#level1ApproverRatingIX").val();
	var j = $("#level1ApproverRatingX").val();
	
	var level1AppraisalA = "A";
	var level1AppraisalB = "B";
	var level1AppraisalC = "C";
	var level1AppraisalD = "D";
	var level1AppraisalE = "E";
	var level1AppraisalF = "F";
	var level1AppraisalG = "G";
		
	if(a == null && b == null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = 0;
		var total = sum / 0;
		if(isNaN(total)){
			$("#total").val('0');		
		} else {
			$("#total").val('0');
		}			
		var total2 = sum / 0;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else {
			$("#total2").val('0');
		}		
		
		if(total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		} 
	} else if(a != null && b == null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a);	
		var total = sum / 1;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 1;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}	
	} else if(a != null && b != null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b);	
		var total = sum / 2;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 2;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c);	
		var total = sum / 3;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 3;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d);	
		var total = sum / 4;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 4;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e);	
		var total = sum / 5;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 5;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f);	
		var total = sum / 6;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 6;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g);	
		var total = sum / 7;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 7;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h);	
		var total = sum / 8;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 8;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i != null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h) + parseFloat(i);	
		var total = sum / 9;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 9;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i != null && j != null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h) + parseFloat(i) + parseFloat(j);	
		var total = sum / 10;
		if(isNaN(total)){
			$("#total").val('0');					
		} else {
			$("#total").val(total.toFixed(2));
		}
		
		var total2 = sum / 10;
		if(isNaN(total2)){
			$("#total2").val('0');					
		} else {
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalA);
			$("#levelIRating").val(level1AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL I RATING :::" + level1AppraisalB);
			$("#levelIRating").val(level1AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalC);
			$("#levelIRating").val(level1AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL I RATING :::" + level1AppraisalD);
			$("#levelIRating").val(level1AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL I RATING :::" + level1AppraisalE);
			$("#levelIRating").val(level1AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL I RATING :::" + level1AppraisalF);
			$("#levelIRating").val(level1AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL I RATING :::" + level1AppraisalG);
			$("#levelIRating").val(level1AppraisalG);
		}
	}	
    	});
	});
	
/* Average Value Calculate in level1AppraisalReview */
$(document).ready(function() {
	  $('#level2Appraisal').keyup(function change(){
		
	var a = $("#level2ApproverRatingI").val();
	var b = $("#level2ApproverRatingII").val();
	var c = $("#level2ApproverRatingIII").val();
	var d = $("#level2ApproverRatingIV").val();
	var e = $("#level2ApproverRatingV").val();
	var f = $("#level2ApproverRatingVI").val();
	var g = $("#level2ApproverRatingVII").val();
	var h = $("#level2ApproverRatingVIII").val();
	var i = $("#level2ApproverRatingIX").val();
	var j = $("#level2ApproverRatingX").val();
	
	var level2AppraisalA = "A";
	var level2AppraisalB = "B";
	var level2AppraisalC = "C";
	var level2AppraisalD = "D";
	var level2AppraisalE = "E";
	var level2AppraisalF = "F";
	var level2AppraisalG = "G";
		
	if(a == null && b == null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = 0;
		var total = sum / 0;
		if(isNaN(total)){
			$("#total").val('0');		
		} else {
			$("#total").val('0');
		}			
		var total2 = sum / 0;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else {
			$("#total2").val('0');
		}		
		
		if(total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		} 
	} else if(a != null && b == null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a);	
		var total = sum / 1;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 1;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c == null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b);	
		var total = sum / 2;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 2;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d == null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c);	
		var total = sum / 3;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 3;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e == null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d);	
		var total = sum / 4;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 4;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f == null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e);	
		var total = sum / 5;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 5;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g == null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f);	
		var total = sum / 6;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 6;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h == null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g);	
		var total = sum / 7;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 7;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i == null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h);	
		var total = sum / 8;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 8;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i != null && j == null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h) + parseFloat(i);	
		var total = sum / 9;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 9;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	} else if(a != null && b != null && c != null && d != null && e != null && f != null && g != null && h != null && i != null && j != null){
		var sum = parseFloat(a) + parseFloat(b)  + parseFloat(c) + parseFloat(d) + parseFloat(e) + parseFloat(f) + parseFloat(g) + parseFloat(h) + parseFloat(i) + parseFloat(j);	
		var total = sum / 10;
		if(isNaN(total)){
			$("#total").val('0');
		} else{
			$("#total").val(total.toFixed(2));
		}	
		var total2 = sum / 10;
		if(isNaN(total2)){
			$("#total2").val('0');
		} else{
			$("#total2").val(total2.toFixed(2));
		}	
		
		if(total == "4" && total >= "3.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalA);
			$("#levelIIRating").val(level2AppraisalA);
		} else if(total <= "3.5" && total >= "3"){
			console.log("LEVEL II RATING :::" + level2AppraisalB);
			$("#levelIIRating").val(level2AppraisalB);
		} else if(total <= "3" && total >= "2.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalC);
			$("#levelIIRating").val(level2AppraisalC);
		} else if(total <= "2.5" && total >= "2"){
			console.log("LEVEL II RATING :::" + level2AppraisalD);
			$("#levelIIRating").val(level2AppraisalD);
		} else if(total <= "2" && total >= "1.5"){
			console.log("LEVEL II RATING :::" + level2AppraisalE);
			$("#levelIIRating").val(level2AppraisalE);
		} else if(total <= "1.5" && total >= "1"){
			console.log("LEVEL II RATING :::" + level2AppraisalF);
			$("#levelIIRating").val(level2AppraisalF);
		} else if(total <= "1" && total == "0"){
			console.log("LEVEL II RATING :::" + level2AppraisalG);
			$("#levelIIRating").val(level2AppraisalG);
		}
	}	
    	});
	});
	
	/* Self Appraisal Validation */		
 function selfAppraisalValidateForm() {
    var selfRatingI = document.getElementById("selfRatingI").value;
    var selfRatingII = document.getElementById("selfRatingII").value;
    var selfRatingIII = document.getElementById("selfRatingIII").value;
	var selfRatingIV = document.getElementById("selfRatingIV").value;
	var selfRatingV = document.getElementById("selfRatingV").value;
	var selfRatingVI = document.getElementById("selfRatingVI").value;
	var selfRatingVII = document.getElementById("selfRatingVII").value;
	var selfRatingVIII = document.getElementById("selfRatingVIII").value;
	var selfRatingIX = document.getElementById("selfRatingIX").value;
	var selfRatingX = document.getElementById("selfRatingX").value;
	var total = document.getElementById("total").value;

	if (!selfRatingI == null || selfRatingI != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingII == null || selfRatingII != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingIII == null || selfRatingIII != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingIV == null || selfRatingIV != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingV == null || selfRatingV != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingVI == null || selfRatingVI != "") {     
             document.getElementById("blankMsg6").innerHTML = "";   
      } else {
         document.getElementById("blankMsg6").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingVII == null || selfRatingVII != "") {     
             document.getElementById("blankMsg7").innerHTML = "";   
      } else {
         document.getElementById("blankMsg7").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingVIII == null || selfRatingVIII != "") {     
             document.getElementById("blankMsg8").innerHTML = "";   
      } else {
         document.getElementById("blankMsg8").innerHTML = "This field must be required";
        return false;
      }
	if (!selfRatingIX == null || selfRatingIX != "") {     
             document.getElementById("blankMsg9").innerHTML = "";   
      } else {
         document.getElementById("blankMsg9").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingX == null || selfRatingX != "") {     
             document.getElementById("blankMsg10").innerHTML = "";   
      } else {
         document.getElementById("blankMsg10").innerHTML = "This field must be required";
        return false;
      }
     if (!total == null || total != "") {     
             document.getElementById("blankMsg11").innerHTML = "";   
      } else {
         document.getElementById("blankMsg11").innerHTML = "This field must be required";
        return false;
      }
}

/* Add & Update KRA Validation */
function addKraValidateForm(){
	var kra = document.getElementById("kra").value;
	var weightage = document.getElementById("weightage").value;
	var department = document.getElementById("department").value;
	var grade = document.getElementById("grade").value;
	var description = document.getElementById("description").value;
	
	if (!kra == null || kra != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "This field must be required";
        return false;
      }
      if (!weightage == null || weightage != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "This field must be required";
        return false;
      }
      if (!department == null || department != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "This field must be required";
        return false;
      }
      if (!grade == null || grade != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "This field must be required";
        return false;
      }
      if (!description == null || description != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "This field must be required";
        return false;
      }
}

/* Add & Update Self Rating Validation */
function selfAppraisalValidateForm(){
	var selfRatingI = document.getElementById("selfRatingI").value;
	var selfRatingII = document.getElementById("selfRatingII").value;
	var selfRatingIII = document.getElementById("selfRatingIII").value;
	var selfRatingIV = document.getElementById("selfRatingIV").value;
	var selfRatingV = document.getElementById("selfRatingV").value;
	var selfRatingVI = document.getElementById("selfRatingVI").value;
	var selfRatingVII = document.getElementById("selfRatingVII").value;
	var selfRatingVIII = document.getElementById("selfRatingVIII").value;
	var selfRatingIX = document.getElementById("selfRatingIX").value;
	var selfRatingX = document.getElementById("selfRatingX").value;
	
	if (!selfRatingI == null || selfRatingI != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingII == null || selfRatingII != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingIII == null || selfRatingIII != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingIV == null || selfRatingIV != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingV == null || selfRatingV != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingVI == null || selfRatingVI != "") {     
             document.getElementById("blankMsg6").innerHTML = "";   
      } else {
         document.getElementById("blankMsg6").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingVII == null || selfRatingVII != "") {     
             document.getElementById("blankMsg7").innerHTML = "";   
      } else {
         document.getElementById("blankMsg7").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingVIII == null || selfRatingVIII != "") {     
             document.getElementById("blankMsg8").innerHTML = "";   
      } else {
         document.getElementById("blankMsg8").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingIX == null || selfRatingIX != "") {     
             document.getElementById("blankMsg9").innerHTML = "";   
      } else {
         document.getElementById("blankMsg9").innerHTML = "This field must be required";
        return false;
      }
    if (!selfRatingX == null || selfRatingX != "") {     
             document.getElementById("blankMsg10").innerHTML = "";   
      } else {
         document.getElementById("blankMsg10").innerHTML = "This field must be required";
        return false;
      }
}

/* Add & Update Level I Rating Validation */
function levelIAppraisalValidateForm(){
	var levelIRatingI = document.getElementById("level1ApproverRatingI").value;
	var levelIRatingII = document.getElementById("level1ApproverRatingII").value;
	var levelIRatingIII = document.getElementById("level1ApproverRatingIII").value;
	var levelIRatingIV = document.getElementById("level1ApproverRatingIV").value;
	var levelIRatingV = document.getElementById("level1ApproverRatingV").value;
	var levelIRatingVI = document.getElementById("level1ApproverRatingVI").value;
	var levelIRatingVII = document.getElementById("level1ApproverRatingVII").value;
	var levelIRatingVIII = document.getElementById("level1ApproverRatingVIII").value;
	var levelIRatingIX = document.getElementById("level1ApproverRatingIX").value;
	var levelIRatingX = document.getElementById("level1ApproverRatingX").value;
	
	if (!levelIRatingI == null || levelIRatingI != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingII == null || levelIRatingII != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingIII == null || levelIRatingIII != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingIV == null || levelIRatingIV != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingV == null || levelIRatingV != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingVI == null || levelIRatingVI != "") {     
             document.getElementById("blankMsg6").innerHTML = "";   
      } else {
         document.getElementById("blankMsg6").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingVII == null || levelIRatingVII != "") {     
             document.getElementById("blankMsg7").innerHTML = "";   
      } else {
         document.getElementById("blankMsg7").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingVIII == null || levelIRatingVIII != "") {     
             document.getElementById("blankMsg8").innerHTML = "";   
      } else {
         document.getElementById("blankMsg8").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingIX == null || levelIRatingIX != "") {     
             document.getElementById("blankMsg9").innerHTML = "";   
      } else {
         document.getElementById("blankMsg9").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIRatingX == null || levelIRatingX != "") {     
             document.getElementById("blankMsg10").innerHTML = "";   
      } else {
         document.getElementById("blankMsg10").innerHTML = "This field must be required";
        return false;
      }
}

/* Add & Update Level II Rating Validation */
function levelIIAppraisalValidateForm(){
	var levelIIRatingI = document.getElementById("level2ApproverRatingI").value;
	var levelIIRatingII = document.getElementById("level2ApproverRatingII").value;
	var levelIIRatingIII = document.getElementById("level2ApproverRatingIII").value;
	var levelIIRatingIV = document.getElementById("level2ApproverRatingIV").value;
	var levelIIRatingV = document.getElementById("level2ApproverRatingV").value;
	var levelIIRatingVI = document.getElementById("level2ApproverRatingVI").value;
	var levelIIRatingVII = document.getElementById("level2ApproverRatingVII").value;
	var levelIIRatingVIII = document.getElementById("level2ApproverRatingVIII").value;
	var levelIIRatingIX = document.getElementById("level2ApproverRatingIX").value;
	var levelIIRatingX = document.getElementById("level2ApproverRatingX").value;
	
	if (!levelIIRatingI == null || levelIIRatingI != "") {     
             document.getElementById("blankMsg").innerHTML = "";   
      } else {
         document.getElementById("blankMsg").innerHTML = "This field must be required";
        return false;
      }   
    if (!levelIIRatingII == null || levelIIRatingII != "") {     
             document.getElementById("blankMsg2").innerHTML = "";   
      } else {
         document.getElementById("blankMsg2").innerHTML = "This field must be required";
        return false;
      }      
    if (!levelIIRatingIII == null || levelIIRatingIII != "") {     
             document.getElementById("blankMsg3").innerHTML = "";   
      } else {
         document.getElementById("blankMsg3").innerHTML = "This field must be required";
        return false;
      }      
    if (!levelIIRatingIV == null || levelIIRatingIV != "") {     
             document.getElementById("blankMsg4").innerHTML = "";   
      } else {
         document.getElementById("blankMsg4").innerHTML = "This field must be required";
        return false;
      }      
    if (!levelIIRatingV == null || levelIIRatingV != "") {     
             document.getElementById("blankMsg5").innerHTML = "";   
      } else {
         document.getElementById("blankMsg5").innerHTML = "This field must be required";
        return false;
      }      
    if (!levelIIRatingVI == null || levelIIRatingVI != "") {     
             document.getElementById("blankMsg6").innerHTML = "";   
      } else {
         document.getElementById("blankMsg6").innerHTML = "This field must be required";
        return false;
      }      
    if (!levelIIRatingVII == null || levelIIRatingVII != "") {     
             document.getElementById("blankMsg7").innerHTML = "";   
      } else {
         document.getElementById("blankMsg7").innerHTML = "This field must be required";
        return false;
      }      
    if (!levelIIRatingVIII == null || levelIIRatingVIII != "") {     
             document.getElementById("blankMsg8").innerHTML = "";   
      } else {
         document.getElementById("blankMsg8").innerHTML = "This field must be required";
        return false;
      }      
    if (!levelIIRatingIX == null || levelIIRatingIX != "") {     
             document.getElementById("blankMsg9").innerHTML = "";   
      } else {
         document.getElementById("blankMsg9").innerHTML = "This field must be required";
        return false;
      }
    if (!levelIIRatingX == null || levelIIRatingX != "") {     
             document.getElementById("blankMsg10").innerHTML = "";   
      } else {
         document.getElementById("blankMsg10").innerHTML = "This field must be required";
        return false;
      }
}