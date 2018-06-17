<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">
	<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link href="<c:url value="/resources/css/googleapi-fonts.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/calendarView.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
	<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/js/lumino.glyphs.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
	<script src="<c:url value="/resources/scripts/globalConstant.js"/>"></script>	
	<script src="<c:url value="/resources/scripts/login.js"/>"></script>
	<title>Reset Password</title>
    <title>:: eManager SUPRA ITS ::</title>
<style>
.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 60px;
    height: 60px;
    animation: spin 2s linear infinite;
    margin-top:300px;
    margin-left:700px;
}

</style>
</head>
<body style="background: #fff;">
<div>
			<div class="container" style="background: #f1f4f7;padding-bottom:35px;width:900px;border-radius:5px;">
			<div class="row">
			<div class="col-sm-12">
			<h1 style="margin-left:34%">Change Password</h1>
			</div>
			</div>
			<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
			<p class="text-center">Fill below details to change your password.</p>
			<form method="post" id="passwordForm">
			<input type="password" class="input-lg form-control" name="password1reset" id="password1reset" placeholder="New Password" autocomplete="off">
			<div class="row">
			<div class="col-sm-6">
			<span id="8char" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> 8 Characters Long<br>
			<span id="ucase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Uppercase Letter
			</div>
			<div class="col-sm-6">
			<span id="lcase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Lowercase Letter<br>
			<span id="num" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Number
			</div>
			</div>
			<input type="password" class="input-lg form-control" name="password2reset" id="password2reset" placeholder="Repeat Password" autocomplete="off">
			<div class="row">
			<div class="col-sm-12">
			<span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords Match
			</div>
			</div>
			<input type="button" class="col-xs-12 btn btn-primary btn-load btn-lg" data-loading-text="Changing Password..." value="Change Password" onclick="changeUserPassword('${usermail}');return false;">
			</form>
			</div>
			</div>
			</div>
</div>
<div id="loaderGif1" class="loader modal"></div>			
<script type="text/javascript">
$("input[type=password]").keyup(function(){
    var ucase = new RegExp("[A-Z]+");
	var lcase = new RegExp("[a-z]+");
	var num = new RegExp("[0-9]+");
	
	if($("#password1reset").val().length >= 8){
		$("#8char").removeClass("glyphicon-remove");
		$("#8char").addClass("glyphicon-ok");
		$("#8char").css("color","#00A41E");
	}else{
		$("#8char").removeClass("glyphicon-ok");
		$("#8char").addClass("glyphicon-remove");
		$("#8char").css("color","#FF0004");
		return false;
	}
	
	if(ucase.test($("#password1reset").val())){
		$("#ucase").removeClass("glyphicon-remove");
		$("#ucase").addClass("glyphicon-ok");
		$("#ucase").css("color","#00A41E");
	}else{
		$("#ucase").removeClass("glyphicon-ok");
		$("#ucase").addClass("glyphicon-remove");
		$("#ucase").css("color","#FF0004");
		return false;
	}
	
	if(lcase.test($("#password1reset").val())){
		$("#lcase").removeClass("glyphicon-remove");
		$("#lcase").addClass("glyphicon-ok");
		$("#lcase").css("color","#00A41E");
	}else{
		$("#lcase").removeClass("glyphicon-ok");
		$("#lcase").addClass("glyphicon-remove");
		$("#lcase").css("color","#FF0004");
		return false;
	}
	
	if(num.test($("#password1reset").val())){
		$("#num").removeClass("glyphicon-remove");
		$("#num").addClass("glyphicon-ok");
		$("#num").css("color","#00A41E");
	}else{
		$("#num").removeClass("glyphicon-ok");
		$("#num").addClass("glyphicon-remove");
		$("#num").css("color","#FF0004");
		return false;
	}
	
	if($("#password1reset").val() == $("#password2reset").val()){
		$("#pwmatch").removeClass("glyphicon-remove");
		$("#pwmatch").addClass("glyphicon-ok");
		$("#pwmatch").css("color","#00A41E");
	}else{
		$("#pwmatch").removeClass("glyphicon-ok");
		$("#pwmatch").addClass("glyphicon-remove");
		$("#pwmatch").css("color","#FF0004");
		return false;
	}
});
</script>
</body>    
</html>   



