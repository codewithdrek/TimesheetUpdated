var alphanumers = /^[a-zA-Z0-9]+$/;
var regx = /^[a-zA-Z ]+$/;
function checkUserNameAvailability(){
	var status = false;
	var userids = document.getElementById('userId').value;
	if(userids == "" || !alphanumers.test(userids)){
		$('#user-availability-status').html('&#x274c'+ "Not available");		
		status=true;
	}else{
	$.ajax({
		type: "POST",
		url: "getExistingUserList",
		dataType: 'json',
		async: false,
		success: function(data){
			var flag = false;
			for(var i=0;i<data.length;i++)
            {	
                if(data[i].userid.toUpperCase() == userids.toUpperCase()){
                	$('#user-availability-status').html('&#x274c'+ "Not available");
                	flag = true;
                	status = false;
                }
            }
			if(flag == true)
				$('#user-availability-status').html('&#x274c'+ "Not available");
			else{
				$('#user-availability-status').html('&#x2705' + "Perfect");
				status = true;
			}		
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
	}
	return status;
}
function checkfNameAvailability(){
	var fName = document.getElementById('fName').value;	
	if(fName == "" || !regx.test(fName)){
		$('#user-fName-status').html('&#x274c'+ "Not Applicable");
    	return false;
    }else{
    	$('#user-fName-status').html('&#x2705' + "Perfect");
    }
	return true;
}
function checklNameAvailability(){
	var lName = document.getElementById('lName').value;	
	if(lName == "" || !alphanumers.test(lName)){
		$('#user-lName-status').html('&#x274c'+ "Not Applicable");
    	return false;
    }else{
    	$('#user-lName-status').html('&#x2705' + "Perfect");
    }
	return true;
}
function checkUserMailAvailability(){
	var email1 = document.getElementById('userMail').value;	
	if(email1 == "" || !(isEmail(email1))){
		$('#user-mail-status').html('&#x274c'+ "Not Applicable");
    	return false;
	}else{
	$.ajax({
		type: "POST",
		url: "getExistingUserMailList",
		data:{email:email1},
		dataType: 'json',
		success: function(data){
                if(data[0].status){
                	$('#user-mail-status').html('&#x274c'+ "Not Applicable");
                	return false;
                }else{
                	$('#user-mail-status').html('&#x2705' + "Perfect");
                	return true;
                }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
	}
	return true;
}
function checkPwdAvailability(){
	var pwd = document.getElementById('pwd').value;	
	if(pwd == "" || pwd.length<8 || pwd.length>15){
		$('#user-pwd-status').html('&#x274c'+ "Not Applicable");
    	return false;
    }else{
    	$('#user-pwd-status').html('&#x2705' + "Perfect");
    }
	return true;
}
function checkConfPwdAvailability(){
	var pwd = document.getElementById('pwd').value;	
	var confPwd = document.getElementById('confPwd').value;	
	if(confPwd == "" || confPwd.length<8 || confPwd.length>15){
		$('#user-confpwd-status').html('&#x274c'+ "Not Applicable");
    	return false;
	}else{
		if(pwd != confPwd){
			$('#user-confpwd-status').html('&#x274c'+ "Not Match");
	    	return false;
		}else{
	    	$('#user-confpwd-status').html('&#x2705' + "Perfect");
	    }
	}
	return true;
}
function isEmail(email) {
	return /^[a-z0-9]+([-._][a-z0-9]+)*@([a-z0-9]+(-[a-z0-9]+)*\.)+[a-z]{2,4}$/.test(email)
    && /^(?=.{1,64}@.{4,64}$)(?=.{6,100}$).*/.test(email);
}
function validateLogin(){
	var username = document.getElementById('username').value;	
	if(username == ""){
		alert("Please enter username/email");
		return false;
	}
	var password = document.getElementById('password').value;	
	if(password == ""){
		alert("Please enter password");
		return false;
	}
	/*if ($('#userNameProxyflag').is(":checked")){
		var usernameproxymail = document.getElementById('usernameproxymail').value;
		if(usernameproxymail == "" || !(isEmail(usernameproxymail))){
			alert("Please enter valid Email Id of other user.");
			return false;
		}
	}*/
	return true;
}
function frgtPwd(){
	$('#forgotPasswordModal').modal('show');
	$('#idUsrMail').val("");
}
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function changeUserPassword(usermail){
	var password = $("#password1reset").val();
	var url_string = window.location.href;
	var url = new URL(url_string);
	//var token = url.searchParams.get("token");
	var token = getParameterByName("token",url);
	$.ajax({
		type: "POST",
		url: "resetPasswordLogin",
		dataType: 'json',
		data:{usermail:usermail,password:password,token:token},
		beforeSend: function() { $('#loaderGif1').show(); },
        complete: function() { $('#loaderGif1').hide(); },
		success: function(data){
			if(data[0]){
				alert("Password has been changed successfully.");
				window.location = domainURLeManager;
			}else{
				alert("Please try after some time.");
				window.close();
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function resetPassword(){
	var usermail = document.getElementById('idUsrMail').value;
	if(usermail == "" || !(isEmail(usermail))){
		alert("Please enter valid user mail id.");
		return false;
	}
	document.getElementById("loaderGif").style.display='block';
	$.ajax({
		type: "POST",
		url: "generateActivationLink",
		dataType: 'json',
		data:{usermail:usermail},
		success: function(data){
			$( "#loaderGif" ).hide();
			if(data[0]){
				alert("Password reset link has been sent to email.");
				$('#forgotPasswordModal').modal('hide');
				return false;
				//$('#frgtComment').html("Password has been sent to "+email);
			}else{
				alert("Invalid email or email is not registered.");
				$('#idUsrMail').val('');
			}
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("loaderGif").style.display='none';
			alert("ERROR:"+textStatus);
		}
	});
}
function matchPwdLogin(){
		var ucase = new RegExp("[A-Z]+");
		var lcase = new RegExp("[a-z]+");
		var num = new RegExp("[0-9]+");
		
		if($("#pwd").val().length >= 8){
			$("#8char").removeClass("glyphicon-remove");
			$("#8char").addClass("glyphicon-ok");
			$("#8char").css("color","#00A41E");
		}else{
			$("#8char").removeClass("glyphicon-ok");
			$("#8char").addClass("glyphicon-remove");
			$("#8char").css("color","#FF0004");
		}
		
		if(ucase.test($("#pwd").val())){
			$("#ucase").removeClass("glyphicon-remove");
			$("#ucase").addClass("glyphicon-ok");
			$("#ucase").css("color","#00A41E");
		}else{
			$("#ucase").removeClass("glyphicon-ok");
			$("#ucase").addClass("glyphicon-remove");
			$("#ucase").css("color","#FF0004");
			return false;
		}
		
		if(lcase.test($("#pwd").val())){
			$("#lcase").removeClass("glyphicon-remove");
			$("#lcase").addClass("glyphicon-ok");
			$("#lcase").css("color","#00A41E");
		}else{
			$("#lcase").removeClass("glyphicon-ok");
			$("#lcase").addClass("glyphicon-remove");
			$("#lcase").css("color","#FF0004");
			return false;
		}
		
		if(num.test($("#pwd").val())){
			$("#num").removeClass("glyphicon-remove");
			$("#num").addClass("glyphicon-ok");
			$("#num").css("color","#00A41E");
		}else{
			$("#num").removeClass("glyphicon-ok");
			$("#num").addClass("glyphicon-remove");
			$("#num").css("color","#FF0004");
			return false;
		}
		
		if($("#pwd").val() == $("#confPwd").val()){
			$("#pwmatch").removeClass("glyphicon-remove");
			$("#pwmatch").addClass("glyphicon-ok");
			$("#pwmatch").css("color","#00A41E");
			return true;
		}else{
			$("#pwmatch").removeClass("glyphicon-ok");
			$("#pwmatch").addClass("glyphicon-remove");
			$("#pwmatch").css("color","#FF0004");
			return false;
		}
}
