var patternAadhar = /^\d{12}$/;
var patternMobileNo = /^\d{10}$/;
var patternPAN=/^([A-Z0-9]+)$/;
var patternGeneralName=/^[A-Za-z\s]+$/;
var patternPassport = /^[a-zA-Z0-9-]+$/;
var patternMail=/^[a-z0-9]+([-._][a-z0-9]+)*@([a-z0-9]+(-[a-z0-9]+)*\.)+[a-z]{2,4}$/;
var patternURL = /(http|https):\/\/(\w+:{0,1}\w*)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%!\-\/]))?/;
var patternZip = /^[0-9]+$/;
var patternVersion = /^[0-9.]+$/;
function isPositiveInteger(n) {
    return n >>> 0 === parseFloat(n);
}
function validateFormFields(){
	var salutation = document.getElementById('salutation').value;	
	if(salutation == ""){
		alert("Invalid salutation.");
		return false;
	}
	var fName = document.getElementById('firstName').value;	
	if(fName == "" || !patternGeneralName.test(fName)){
		alert("Invalid first name.");
		return false;
	}
	//var middleName = document.getElementById('middleName').value;	
	/*if(middleName != "" || !patternGeneralName.test(middleName)){
		alert("Invalid middle name.");
		return false;
	}*/
	var lastName = document.getElementById('lastName').value;	
	if(lastName == "" || !patternGeneralName.test(lastName)){
		alert("Invalid last name.");
		return false;
	}
	var motherName = document.getElementById('motherName').value;	
	if(!patternGeneralName.test(motherName) && motherName != ""){
		alert("Invalid mother name.");
		return false;
	}
	var fatherName = document.getElementById('fatherName').value;	
	if(!patternGeneralName.test(fatherName) && fatherName != ""){
		alert("Invalid father name.");
		return false;
	}
	var motherTongue = document.getElementById('motherTongue').value;	
	if(!patternGeneralName.test(motherTongue) && motherTongue != ""){
		alert("Invalid mother tongue value.");
		return false;
	}
	var aadharNo = document.getElementById('aadharNo').value;	
	if((!patternAadhar.test(aadharNo) || aadharNo.length != 12) && aadharNo != ""){
		alert("Invalid aadhar number.");
		return false;
	}
	var pancardNo = document.getElementById('pancardNo').value;	
	if((!patternPAN.test(pancardNo) || pancardNo.length != 10) && pancardNo != ""){
		alert("Invalid PAN.");
		return false;
	}
	var passportNo = document.getElementById('passportNo').value;	
	if(!patternPassport.test(passportNo) && passportNo != ""){
		alert("Invalid passport number.");
		return false;
	}
	var spouseName = document.getElementById('spouseNameId').value;	
	if(!patternGeneralName.test(spouseName) && spouseName != ""){
		alert("Invalid spouse name.");
		return false;
	}
	var primaryPhoneNum = document.getElementById('primaryPhoneNum').value;
	if(!patternMobileNo.test(primaryPhoneNum) && primaryPhoneNum != ""){
		alert("Invalid primary contact number.");
		return false;
	}
	var secondaryPhoneNum = document.getElementById('secondaryPhoneNum').value;	
	if(!patternMobileNo.test(secondaryPhoneNum) && secondaryPhoneNum != ""){
		alert("Invalid secondary contact number.");
		return false;
	}
	var emergencyPhoneNum = document.getElementById('emergencyPhoneNum').value;	
	if(!patternMobileNo.test(emergencyPhoneNum) && emergencyPhoneNum != ""){
		alert("Invalid emergency contact number.");
		return false;
	}
	var primaryEmailId = document.getElementById('primaryEmailId').value;	
	if(!patternMail.test(primaryEmailId) && primaryEmailId != ""){
		alert("Invalid primary email id.");
		return false;
	}
	var secondaryEmailId = document.getElementById('secondaryEmailId').value;	
	if(!patternMail.test(secondaryEmailId) && secondaryEmailId != ""){
		alert("Invalid secondary email id.");
		return false;
	}
	var webOrLinkedInLink = document.getElementById('webOrLinkedInLink').value;	
	if(!patternURL.test(webOrLinkedInLink) && webOrLinkedInLink != ""){
		alert("Invalid weblink/linkedin url.");
		return false;
	}
	var permanentAddressZipCode = document.getElementById('permanentAddressZipCode').value;
	if(!patternZip.test(permanentAddressZipCode) && permanentAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	var communicationAddressZipCode = document.getElementById('communicationAddressZipCode').value;
	if(!patternZip.test(communicationAddressZipCode) && communicationAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	var officeAddressZipCode = document.getElementById('officeAddressZipCode').value;
	if(!patternZip.test(officeAddressZipCode)&& officeAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	var clientOfficeAddressZipCode = document.getElementById('clientOfficeAddressZipCode').value;
	if(!patternZip.test(clientOfficeAddressZipCode) && clientOfficeAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	return true;
}
function editUserProfile(uid){
	var top=0;
    var left=0;
    var height=screen.Height;
    var width=screen.Width;
    //window.open('editUserProfile?uid='+uid ,'UserScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
    var params = { 'uid' : uid };
    openNewRequest("editUserProfile",params);
}
function editUserProfileOwn(uid){
	var top=0;
    var left=0;
    var height=screen.Height;
    var width=screen.Width;
    //window.open('editUserProfileOwn?uid='+uid ,'UserScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
    var params = { 'uid' : uid };
    openNewRequest("editUserProfileOwn",params);
}
function openBankModal(){
	$("#addBankModal").modal('show');
	document.getElementById("bankName").options[0].selected = true;
	document.getElementById("accType").options[0].selected = true;
	document.getElementById("accountNumber").value = '';
	document.getElementById("ifscCode").value = '';
	document.getElementById("primaryFlag").options[0].selected = true;
	document.getElementById("accHolderName").value = '';
}
function insertNewUserAcc(username){
	var bankName = document.getElementById("bankName").value;
	var accType = document.getElementById("accType").value;
	var accountNumber = document.getElementById("accountNumber").value;
	var ifscCode = document.getElementById("ifscCode").value;
	var primaryFlag = document.getElementById("primaryFlag").value;
	var accHolderName = document.getElementById("accHolderName").value;
	
	if(accountNumber == "" || accountNumber == undefined || accountNumber == "null" || accountNumber == null || accountNumber.length < 6){
		alert("Please enter valid account number");
		return false;
	}
	if(ifscCode == "" || ifscCode == undefined || ifscCode == "null" || ifscCode == null || ifscCode.length != 11 || !(patternPAN.test(ifscCode))){
		alert("Please enter valid IFSC.");
		return false;
	}
	if(accHolderName == "" || accHolderName == undefined || accHolderName == "null" || accHolderName == null || !(patternGeneralName.test(accHolderName))){
		alert("Please enter valid account holder name.");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "addUserBankDetails",
		dataType: 'json',
		data:{bankName:bankName,accType:accType,accountNumber:accountNumber,ifscCode:ifscCode,primaryFlag:primaryFlag,accHolderName:accHolderName,username:username},
		success: function(data){
			if(data[0]){
				alert("Bank Details added successfully.");
				window.location.reload(true);
			}else{
				alert("There is already an account with given details.");
			}
			$('#addBankModal').modal('hide');
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function openEmployementModal(){
	$("#addEmployementModal").modal('show');
	document.getElementById("companyName").value = '';
	document.getElementById("lastDesignation").value = '';
	document.getElementById("experience").value = '';
	document.getElementById("startDate").value = '';
	document.getElementById("endDate").value = '';
}
function insertNewEmployement(username){
	var companyName = document.getElementById("companyName").value;
	var lastDesignation = document.getElementById("lastDesignation").value;
	var experience = document.getElementById("experience").value;
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	if(companyName == "" || companyName == undefined || companyName == "null" || companyName == null || companyName.length < 3){
		alert("Please enter valid organization name.");
		return false;
	}
	if(lastDesignation == "" || lastDesignation == undefined || lastDesignation == "null" || lastDesignation == null || lastDesignation.length < 3){
		alert("Please enter valid designation");
		return false;
	}
	if(experience == "" || experience == undefined || experience == "null" || experience == null || experience.length > 3 || !(isPositiveInteger(experience))){
		alert("Please enter valid experience in month");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "addUserEmployementHistory",
		dataType: 'json',
		data:{companyName:companyName,lastDesignation:lastDesignation,experience:experience,startDate:startDate,endDate:endDate,username:username},
		success: function(data){
			if(data[0]){
				alert("User data updated successfully.");
				window.location.reload(true);
			}else{
				alert("Try after some time.");
			}
			$('#addEmployementModal').modal('hide');
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function openEduModal(){
	$("#addEduModal").modal('show');
	 document.getElementById("qualification").options[0].selected = true;
	 document.getElementById("collegeName").value = '';
	 document.getElementById("boardName").value = '';
	 document.getElementById("streamName").value = '';
	 document.getElementById("yearName").value = '';
}
function insertNewEdu(username){
	var qualification = document.getElementById("qualification").value;
	var collegeName = document.getElementById("collegeName").value;
	var boardName = document.getElementById("boardName").value;
	var streamName = document.getElementById("streamName").value;
	var year = document.getElementById("yearName").value;
	var score = document.getElementById("scoreCGPA").value;
	
	if(collegeName == "" || collegeName == undefined || collegeName == "null" || collegeName == null || collegeName.length < 5){
		alert("Please enter valid college/school name.");
		return false;
	}
	if(boardName == "" || boardName == undefined || boardName == "null" || boardName == null || boardName.length < 3){
		alert("Please enter valid board/university name");
		return false;
	}
	if(streamName == "" || streamName == undefined || streamName == "null" || streamName == null || streamName.length > 3){
		alert("Please enter valid stream or specilization");
		return false;
	}
	if(year == "" || year == undefined || year == "null" || year == null || year.length != 4){
		alert("Please enter valid year");
		return false;
	}
	if(score == "" || score == undefined || score == "null" || score == null){
		alert("Please enter valid score(Percentage/CGPA).");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "addNewQualificationOfUser",
		dataType: 'json',
		data:{qualification:qualification,collegeName:collegeName,boardName:boardName,streamName:streamName,year:year,username:username,score:score},
		success: function(data){
			if(data[0]){
				alert("User data updated successfully.");
				window.location.reload(true);
			}else{
				alert("Try after some time.");
			}
			$('#addEduModal').modal('hide');
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function openSkillModal(){
	$("#addSkillModal").modal('show');
	document.getElementById("skillName").value = '';
	document.getElementById("skillType").options[0].selected = true;
	document.getElementById("version").value = '';
	document.getElementById("expMonths").value = '';
}
function insertNewSkill(username){
	var skillName = document.getElementById("skillName").value;
	var skillType = document.getElementById("skillType").value;
	var version = document.getElementById("version").value;
	var expMonth = document.getElementById("expMonths").value;

	if(skillName == "" || skillName == undefined || skillName == "null" || skillName == null || skillName.length < 2 || !(patternGeneralName.test(skillName))){
		alert("Please enter valid technology");
		return false;
	}
	if(version == "" || version == undefined || version == "null" || version == null || version.length < 1 || !(patternVersion.test(version))){
		alert("Please enter valid version of technology");
		return false;
	}
	if(expMonth == "" || expMonth == undefined || expMonth == "null" || expMonth == null || !(isPositiveInteger(expMonth))){
		alert("Please enter valid experience in months");
		return false;
	}
	$.ajax({
		type: "POST",
		url: "addNewUserSkill",
		dataType: 'json',
		data:{skillName:skillName,skillType:skillType,version:version,expMonth:expMonth,username:username},
		success: function(data){
			if(data[0]){
				alert("New skill added successfully.");
				window.location.reload(true);
			}else{
				alert("Try after some time.");
			}
			$('#addSkillModal').modal('hide');
			
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function calculateAge (birthDate) {
	//alert(birthDate);
    birthDate = new Date(birthDate);
    otherDate = new Date();

    var years = (otherDate.getFullYear() - birthDate.getFullYear());

    if (otherDate.getMonth() < birthDate.getMonth() || 
        otherDate.getMonth() == birthDate.getMonth() && otherDate.getDate() < birthDate.getDate()) {
        years--;
    }

    $("#userAgeId").val(years);
}
function saveUserDetails(){
	//alert($('#empForm').serialize());
	var uid = document.getElementById("userName").value;
	var hrUser = document.getElementById("hrManager").value;
    var rmUser = document.getElementById("reportingPerson").value;
	if(validateFormFields() && validateHR(hrUser,uid) && validateRM(rmUser,uid)){
	    var ret=confirm("Do you want to save details?");
	    if (ret == true) {
			//e.preventDefault();
			   var formData =  $("#empForm").serialize();
			   $('#loaderGif1').show();
			    $.ajax({
		            type: 'POST',
		            url: "saveEmployeeData",
		            dataType : "json",
		            data : $('#empForm').serialize(),
			        success : function(res) {
			        		$('#loaderGif1').hide();
							if(res){
								alert("User details updated successfully.");
								window.location.reload(true);
							}else{
								alert("Contact Administrator.");
								return false;
							}		        	 
			        	 }
			         });
			} else {
				return false;
			}
	}
}
function updateSpouseNameField(status){
	if(status == "Married"){
		$("#spouseNameId").prop("readonly", false);
	}else{
		$("#spouseNameId").val('');
		$("#spouseNameId").prop("readonly", true);
	}
}
function deleteAccountRow(accNumber){
	$.ajax({
		type: "POST",
		url: "deleteAccountRow",
		data:{accNumber:accNumber},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					window.location.reload(true);
			}else{
				alert("Try after some time!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function deleteEducationRow(qualificationId){
	$.ajax({
		type: "POST",
		url: "deleteEducationRow",
		data:{qualificationId:qualificationId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					window.location.reload(true);
			}else{
				alert("Try after some time!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function deleteSkillRow(skillId){
	$.ajax({
		type: "POST",
		url: "deleteSkillRow",
		data:{skillId:skillId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					window.location.reload(true);
			}else{
				alert("Try after some time!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function deleteEmployementRow(compId){
	$.ajax({
		type: "POST",
		url: "deleteEmployementRow",
		data:{compId:compId},
		dataType: 'json',
		success: function(result){
			if(result == 'true'){
					alert("Successfully removed");
					window.location.reload(true);
			}else{
				alert("Try after some time!");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateBaseLocations(id){
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:'Base Location'},
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			/*for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }*/
			/*select.add(new Option('Select Role','select'));*/
			for (var i = 0; i < data.length; i++) {
				if(!(document.getElementById(id).value == data[i].values))
					select.add(new Option(data[i].values,data[i].values));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateDesignation(id){
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:'Designation'},
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			/*for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }*/
			/*select.add(new Option('Select Role','select'));*/
			for (var i = 0; i < data.length; i++) {
				if(!(document.getElementById(id).value == data[i].values))
					select.add(new Option(data[i].values,data[i].values));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateDepartment(id){
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:'Department'},
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			/*for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }*/
			/*select.add(new Option('Select Role','select'));*/
			for (var i = 0; i < data.length; i++) {				
				if(!(document.getElementById(id).value == data[i].values))
				select.add(new Option(data[i].values,data[i].values));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateAccountUnit(id){
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:'Account Unit'},
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			/*for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }*/
			/*select.add(new Option('Select Role','select'));*/
			for (var i = 0; i < data.length; i++) {				
				if(!(document.getElementById(id).value == data[i].values))
				select.add(new Option(data[i].values,data[i].values));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateRoles(id){
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:'Role'},
		success: function(data){
			var select = document.getElementById(id);
			//alert(select);
			/*for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }*/
			/*select.add(new Option('Select Role','select'));*/
			for (var i = 0; i < data.length; i++) {
				if(!(document.getElementById(id).value == data[i].values))
				select.add(new Option(data[i].values,data[i].values));
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateRMModal(currentRM){
	$('#populateRMsModal').modal('show');
	$.ajax({
		type: "POST",
		url: "getAllReportingManager",
		dataType: 'json',
		success: function(data){
			var elem = "";
			for (var i = 0; i < data.length; i++) {
				var temp = "";
				if((currentRM == data[i].username)){
					temp = "<input type='radio' name='rmRadioButton' value='"+data[i].username+"' checked='true'><label>"+data[i].fullname+"</label><br>";
			    }else{
			    	temp = "<input type='radio' name='rmRadioButton' value='"+data[i].username+"'><label>"+data[i].fullname+"</label><br>";
			    }
			    elem = elem + temp;
			}
			elem = elem + "<button class='btn-sm btn-info pull-right' onclick=modifyUserParam('REPORTING_PERSON','rmRadioButton','populateRMsModal');>Save</button>"
			$("#rmModalDiv").empty();
			$("#rmModalDiv").append(elem);
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateHRModal(currentHR){
	$('#populateHRsModal').modal('show');
	$.ajax({
		type: "POST",
		url: "getAllHRManager",
		dataType: 'json',
		success: function(data){
			var elem = "";
			for (var i = 0; i < data.length; i++) {
				var temp = "";
				if((currentHR == data[i].username)){
					temp = "<input type='radio' name='hrRadioButton' value='"+data[i].username+"' checked='true'><label>"+data[i].fullname+"</label><br>";
			    }else{
			    	temp = "<input type='radio' name='hrRadioButton' value='"+data[i].username+"'><label>"+data[i].fullname+"</label><br>";
			    }
			    elem = elem + temp;
			}
			elem = elem + "<button class='btn-sm btn-info pull-right' onclick=modifyUserParam('HR_MANAGER','hrRadioButton','populateHRsModal');>Save</button>"
			$("#hrModalDiv").empty();
			$("#hrModalDiv").append(elem);
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function modifyUserParam(param,paramValueId,modalId){
	var username = document.getElementById("userName").value;
	var paramValue = $("input[name="+paramValueId+"]:checked").val();
	//param = replaceAll(param,"$"," ");
	if(param == "HR_MANAGER"){
		if(!(validateHR(paramValue,username)))
			return false;
	}
	if(param == "REPORTING_PERSON"){
		if(!(validateRM(paramValue,username)))
			return false;
	}
	$.ajax({
		type: "POST",
		url: "updateUserInfoByParam",
		dataType: 'json',
		data:{username:username,paramValue:paramValue,param:param},
		success: function(data){
			$("#"+modalId).modal('hide');
			alert(data[0]);
			window.location.reload(true);
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function populateHRPerson(id){
	var currentHR = document.getElementById(id).value;
	$.ajax({
		type: "POST",
		url: "getAllHRManager",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			/*select.add(new Option('Select HR Manager','select'));*/
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].fullname,data[i].username));
				if((currentHR == data[i].username))
					select.options[i].selected = true;
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateRMPerson(id){
	var currentRM = document.getElementById(id).value;
	$.ajax({
		type: "POST",
		url: "getAllReportingManager",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			for (var i = 0; i < data.length; i++) {
				select.add(new Option(data[i].fullname,data[i].username));
				if((currentRM == data[i].username))
					select.options[i].selected = true;
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function onlyUnique(value, index, self) { 
    return self.indexOf(value) === index;
}
function getUserPastTS(){
	var year = document.getElementById("yearsForTS").value;
	var month = document.getElementById("monthForTS").value;	
	//var monthLabel = $("#monthForTS").find("option:selected").text();
	var monthLabel = $('#monthForTS :selected').attr('label'); 
	var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
	$.ajax({
		type: "POST",
		url: "getUserTSData",
		dataType: 'json',
		data:{year:year,month:month},
		success: function(data){
			var dateArray = new Array();
			var dayArray = new Array();
			if(data.length>0){
				var totalEffort = Number ('0');
				$("#tsUserData").empty();
				$('#tsUserData').append($("<div class='col-lg-12'><table class='table table-bordered' style='background-color: #EBF5FB;width:100%;' scrolling='auto'>" +
						"<thead style='background-color: #30a5ff;color:white;'><tr><th style='width:10%;'>Date</th><th style='width:10%;'>Day</th><th style='width:25%;'>Project Name</th><th style='width:25%;'>Task Name</th><th style='width:10%;'>Effort</th><th style='width:25%;'>Remark</th></tr></thead><tbody id='perDay'></tbody></table></div>"));
				for(var i = 0;i<data.length;i++){
					dateArray.push(data[i].date);
					dayArray.push(data[i].dayName);
				}
			var uniqueArrDate = dateArray.filter( onlyUnique );
			var uniqueArrDay = dayArray.filter( onlyUnique );
			for(var j = 0;j<uniqueArrDate.length;j++){
				var d = new Date(uniqueArrDate[j]);
				
				for(var k = 0;k<data.length;k++){
					if(uniqueArrDate[j] == data[k].date){
						if(Number( data[k].effortInHours ) > 0){
							var row = "";
							if(data[k].taskName == "Holiday" || data[k].taskName == "On Leave" || data[k].dayName == "Sunday" || data[k].dayName == "Saturday")
								row = $("<tr class='danger' />");
							else{
								if(data[k].dayName == "Monday")
									row = $("<tr class='success' />");
								else
									row = $("<tr class='info' />");
							}	
						 	$("#perDay").append(row); 
						 	row.append($("<td class='EWTableElements'>"+data[k].date+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+data[k].dayName+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+data[k].projectName+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+data[k].taskName+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+data[k].effortInHours+"</td>"));
						 	row.append($("<td class='EWTableElements'>"+data[k].remark+"</td>"));
						 	if("Organization" != data[k].projectName)
						 		totalEffort = totalEffort + Number( data[k].effortInHours );
						}
					}
				}
			}
			$('#tsUserData').append($("<div style='display:block;'><span>Total Billing Hours <strong style='color:red;'>&nbsp"+monthLabel+","+year+" :: "+totalEffort+"</strong></span></div>"));
			}else{
				$("#tsUserData").empty();
				$('#tsUserData').append($("<div class=''><span style='color:red;'>No data found in databse.</span></div>"));
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function showUserTimesheetHistory(uid){
	var top=0;
    var left=0;
    var height=screen.Height;
    var width=screen.Width;
    window.open('showUserTimesheetHistory?uid='+uid ,'ProjectScreen','scrollbars=yes,resizable=yes,toolbar=no,Addressbar=no,menubar=no,status=yes,location=no,top='+top+',left='+left+',height='+height+',width='+width);
}
function populatePGModal(currentValue,param,category){
	$('#populatePGsModal').modal('show');
	//param = replaceAll(param," ","$");
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:category},
		success: function(data){
			var elem = "";
			for (var i = 0; i < data.length; i++) {
				var temp = "";
				if((currentValue == data[i].values)){
					temp = "<input type='radio' name='crrRadioButton' value='"+data[i].values+"' checked='true'><label>"+data[i].values+"</label><br>";
			    }else{
			    	temp = "<input type='radio' name='crrRadioButton' value='"+data[i].values+"'><label>"+data[i].values+"</label><br>";
			    }
			    elem = elem + temp;
			}
			elem = elem + "<button class='btn-sm btn-info pull-right' onclick=modifyUserParam('"+param+"','crrRadioButton','populatePGsModal');>Save</button>"
			$("#pgModalDiv").empty();
			$("#pgModalDiv").append(elem);
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateUserInfoDiv(currentValue,param,category,btnId){
	if(category == "REPORTING_PERSON"){
		$.ajax({
			type: "POST",
			url: "getAllReportingManager",
			dataType: 'json',
			success: function(data){
				var elem = "<div><button class='btn pull-right' style='background:#fff;' onclick=closeDivElem('editUserInfoDiv');><span class='glyphicon glyphicon-remove'></span></button></div><div style='margin-top:25px;'>";
				for (var i = 0; i < data.length; i++) {
					var temp = "";
					if((currentValue == data[i].username)){
						temp = "<input type='radio' name='crrRadioButton' value='"+data[i].username+"' checked='true'><label>"+data[i].fullname+"</label><br>";
				    }else{
				    	temp = "<input type='radio' name='crrRadioButton' value='"+data[i].username+"'><label style='font-weight:400;'>"+data[i].fullname+"</label><br>";
				    }
				    elem = elem + temp;
				}
				elem = elem + "</div><button class='btn btn-sm btn-danger pull-right' style='margin-left:5px;' onclick=closeDivElem('editUserInfoDiv');>Close</button>" +
							  "<button class='btn btn-sm btn-success pull-right' onclick=modifyUserParam('"+param+"','crrRadioButton','populatePGsModal');>Save</button>";
				$("#editUserInfoDiv").empty();
				$("#editUserInfoDiv").html(elem);
				var btn = $("#"+btnId);
			    $('#editUserInfoDiv').css({
			    	background: '#fff',
			        position: 'absolute',
			        top: btn.offset().top + btn.outerHeight() + 5,
			        left: btn.offset().left + btn.outerWidth() - 50,
			    }).show();
			},
			error: function(jqXHR, textStatus)
			{
			alert("ERROR:"+textStatus);
			}
		});
	}else if(category == "HR_MANAGER"){
		$.ajax({
			type: "POST",
			url: "getAllHRManager",
			dataType: 'json',
			success: function(data){
				var elem = "<div><button class='btn pull-right' style='background:#fff;' onclick=closeDivElem('editUserInfoDiv');><span class='glyphicon glyphicon-remove'></span></button></div><div style='margin-top:25px;'>";
				for (var i = 0; i < data.length; i++) {
					var temp = "";
					if((currentValue == data[i].username)){
						temp = "<input type='radio' name='crrRadioButton' value='"+data[i].username+"' checked='true'><label>"+data[i].fullname+"</label><br>";
				    }else{
				    	temp = "<input type='radio' name='crrRadioButton' value='"+data[i].username+"'><label style='font-weight:400;'>"+data[i].fullname+"</label><br>";
				    }
				    elem = elem + temp;
				}
				elem = elem + "</div><button class='btn btn-sm btn-danger pull-right' style='margin-left:5px;' onclick=closeDivElem('editUserInfoDiv');>Close</button>" +
							  "<button class='btn btn-sm btn-success pull-right' onclick=modifyUserParam('"+param+"','crrRadioButton','populatePGsModal');>Save</button>";
				$("#editUserInfoDiv").empty();
				$("#editUserInfoDiv").html(elem);
				var btn = $("#"+btnId);
			    $('#editUserInfoDiv').css({
			    	background: '#fff',
			        position: 'absolute',
			        top: btn.offset().top + btn.outerHeight() + 5,
			        left: btn.offset().left + btn.outerWidth() - 50,
			    }).show();
			},
			error: function(jqXHR, textStatus)
			{
				alert("ERROR:"+textStatus);
			}
		});
	}else{
		$.ajax({
			type: "POST",
			url: "getValuesBasedOnCategory",
			dataType: 'json',
			data:{category:category},
			success: function(data){
				var elem = "<div><button class='btn pull-right' style='background:#fff;' onclick=closeDivElem('editUserInfoDiv');><span class='glyphicon glyphicon-remove'></span></button></div><div style='margin-top:25px;'>";
				for (var i = 0; i < data.length; i++) {
					var temp = "";
					if((currentValue == data[i].values)){
						temp = "<input type='radio' name='crrRadioButton' value='"+data[i].values+"' checked='true'><label>"+data[i].values+"</label><br>";
				    }else{
				    	temp = "<input type='radio' name='crrRadioButton' value='"+data[i].values+"'><label style='font-weight:400;'>"+data[i].values+"</label><br>";
				    }
				    elem = elem + temp;
				}
				elem = elem + "</div><button class='btn btn-sm btn-danger pull-right' style='margin-left:5px;' onclick=closeDivElem('editUserInfoDiv');>Close</button>" +
							  "<button class='btn btn-sm btn-success pull-right' onclick=modifyUserParam('"+param+"','crrRadioButton','populatePGsModal');>Save</button>";
				$("#editUserInfoDiv").empty();
				$("#editUserInfoDiv").html(elem);
				var btn = $("#"+btnId);
			    $('#editUserInfoDiv').css({
			    	background: '#fff',
			        position: 'absolute',
			        top: btn.offset().top + btn.outerHeight() + 5,
			        left: btn.offset().left + btn.outerWidth() - 50,
			    }).show();
			},
			error: function(jqXHR, textStatus)
			{
				alert("ERROR:"+textStatus);
			}
		});
	}
}
function closeDivElem(id){
	$("#"+id).empty();
	$("#"+id).hide();
}
function populateHRPolicyGroup(id){
	$.ajax({
		type: "POST",
		url: "getValuesBasedOnCategory",
		dataType: 'json',
		data:{category:'HR Policy Group'},
		success: function(data){
			var select = document.getElementById(id);
			for (var i = 0; i < data.length; i++) {				
				if(!(document.getElementById(id).value == data[i].values))
				select.add(new Option(data[i].values,data[i].values));
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function validateOwnFormFields(){
	/*var salutation = document.getElementById('salutation').value;	
	if(salutation == ""){
		alert("Invalid salutation.");
		return false;
	}*/
	/*var fName = document.getElementById('firstName').value;	
	if(fName == "" || !patternGeneralName.test(fName)){
		alert("Invalid first name.");
		return false;
	}*/
	//var middleName = document.getElementById('middleName').value;	
	/*if(middleName != "" || !patternGeneralName.test(middleName)){
		alert("Invalid middle name.");
		return false;
	}*/
	/*var lastName = document.getElementById('lastName').value;	
	if(lastName == "" || !patternGeneralName.test(lastName)){
		alert("Invalid last name.");
		return false;
	}*/
	var motherName = document.getElementById('motherName').value;	
	if(!patternGeneralName.test(motherName) && motherName != ""){
		alert("Invalid mother name.");
		return false;
	}
	var fatherName = document.getElementById('fatherName').value;	
	if(!patternGeneralName.test(fatherName) && fatherName != ""){
		alert("Invalid father name.");
		return false;
	}
	var motherTongue = document.getElementById('motherTongue').value;	
	if(!patternGeneralName.test(motherTongue) && motherTongue != ""){
		alert("Invalid mother tongue value.");
		return false;
	}
	var aadharNo = document.getElementById('aadharNo').value;	
	if((!patternAadhar.test(aadharNo) || aadharNo.length != 12) && aadharNo != ""){
		alert("Invalid aadhar number.");
		return false;
	}
	var pancardNo = document.getElementById('pancardNo').value;	
	if((!patternPAN.test(pancardNo) || pancardNo.length != 10) && pancardNo != ""){
		alert("Invalid PAN.");
		return false;
	}
	var passportNo = document.getElementById('passportNo').value;	
	if(!patternPassport.test(passportNo) && passportNo != ""){
		alert("Invalid passport number.");
		return false;
	}
	var spouseName = document.getElementById('spouseNameId').value;	
	if(!patternGeneralName.test(spouseName) && spouseName != ""){
		alert("Invalid spouse name.");
		return false;
	}
	var primaryPhoneNum = document.getElementById('primaryPhoneNum').value;
	if(!patternMobileNo.test(primaryPhoneNum) && primaryPhoneNum != ""){
		alert("Invalid primary contact number.");
		return false;
	}
	var secondaryPhoneNum = document.getElementById('secondaryPhoneNum').value;	
	if(!patternMobileNo.test(secondaryPhoneNum) && secondaryPhoneNum != ""){
		alert("Invalid secondary contact number.");
		return false;
	}
	var emergencyPhoneNum = document.getElementById('emergencyPhoneNum').value;	
	if(!patternMobileNo.test(emergencyPhoneNum) && emergencyPhoneNum != ""){
		alert("Invalid emergency contact number.");
		return false;
	}
	/*var primaryEmailId = document.getElementById('primaryEmailId').value;	
	if(!patternMail.test(primaryEmailId) && primaryEmailId != ""){
		alert("Invalid primary email id.");
		return false;
	}*/
	var secondaryEmailId = document.getElementById('secondaryEmailId').value;	
	if(!patternMail.test(secondaryEmailId) && secondaryEmailId != ""){
		alert("Invalid secondary email id.");
		return false;
	}
	var webOrLinkedInLink = document.getElementById('webOrLinkedInLink').value;	
	if(!patternURL.test(webOrLinkedInLink) && webOrLinkedInLink != ""){
		alert("Invalid weblink/linkedin url.");
		return false;
	}
	var permanentAddressZipCode = document.getElementById('permanentAddressZipCode').value;
	if(!patternZip.test(permanentAddressZipCode) && permanentAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	var communicationAddressZipCode = document.getElementById('communicationAddressZipCode').value;
	if(!patternZip.test(communicationAddressZipCode) && communicationAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	var officeAddressZipCode = document.getElementById('officeAddressZipCode').value;
	if(!patternZip.test(officeAddressZipCode)&& officeAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	var clientOfficeAddressZipCode = document.getElementById('clientOfficeAddressZipCode').value;
	if(!patternZip.test(clientOfficeAddressZipCode) && clientOfficeAddressZipCode != ""){
		alert("Invalid zip code.");
		return false;
	}
	return true;
}
function viewEmpDirectory(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewEmpDirectoryDiv").style.display='block';
	document.getElementById('viewEmpDirectoryDiv').setAttribute("style","min-height:500px;");
	$("#viewEmpDirectoryDiv").appendTo("#dataDiv");

	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "getPendingUserList",
		data:{filterVar:"All",filterName:""},
		dataType: 'json',
		success: function(result){
			document.getElementById("loaderGif").style.display='none';
			if(result.length >0){
			$('#viewEmpDirTbl td').remove();
			for (var i = 0; i < result.length; i++) {
				var row = "";
							if(i%2 == 0)
								row = $("<tr class='info' />");
							else
								row = $("<tr class='info' />");														
				 	$("#viewEmpDirTblBody").append(row); 
				 	row.append($("<td class='EWTableElements'>"+(i+1)+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].usercode+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].firstname+ " " +result[i].lastname+"</td>"));
				 	row.append($("<td class='EWTableElements'>"+result[i].primaryemail+"</td>"));
				 	if(result[i].primarycontact == undefined)
				 		row.append($("<td class='EWTableElements'>-</td>"));
				 	else
				 		row.append($("<td class='EWTableElements'>"+result[i].primarycontact+"</td>"));
				 	if(result[i].extension == undefined)
				 		row.append($("<td class='EWTableElements'>-</td>"));
				 	else
				 		row.append($("<td class='EWTableElements'>"+result[i].extension+"</td>"));
			    }
			}else{
				alert("No result found in database");
				addUserFilterFunc('All','');
				$("select#userFilter").prop('selectedIndex', 0);
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function filterFullTableByInput(input,id) {
	  // Declare variables 
	  var filter, table, tr, td, i;
	  filter = input.toUpperCase();
	  table = document.getElementById(id);
	  tr = table.getElementsByTagName("tr");
	  //alert(table +"  "+tr);
	  for (i = 0; i < tr.length; i++) {
	   var td1 = tr[i].getElementsByTagName("td");  
	     for (var l = 0; l < td1.length; l++) {  
		    td = tr[i].getElementsByTagName("td")[l];
		    if (td) {
			    if(td.innerHTML.toUpperCase().indexOf(filter) > -1) {
				        tr[i].style.display = "";
				        break;
				      } else {
				    	   tr[i].style.display = "none";
			    }
		    } 
		  }
	  }
}
function validateHR(hr,username){
	if(hr == username){
		alert("Employee can not be his/her own HR.");
		return false;
	}else{
		return true;
	}
}
function validateRM(rm,username){
	if(rm == username){
		alert("Employee can not be his/her own RM");
		return false;
	}else{
		return true;
	}
}
function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}