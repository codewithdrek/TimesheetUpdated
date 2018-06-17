<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='<c:url value="/resources/images/favicon.ico" />' rel="shortcut icon">
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>	
	<script src="<c:url value="/resources/scripts/login.js"/>"></script>
	<title>Login</title>
	<%-- <link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet"> --%>
	<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
	<%-- <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet"> --%>
	<link href="<c:url value="/resources/css/ewgeneral.css" />" rel="stylesheet">	
    <title>:: iEMS SUPRA ITS ::</title>
		
		
		
        
<style type="text/css">
body,td,th {
	font-family: "WOL_Reg","Segoe UI",Tahoma,Helvetica,sans-serif;
	font-size: 13px;
	color: #0d314b;
	font-style:italic;
}
/* .panel-login {
	border-color: #ccc;
	-webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	-moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
} */
.panel-login>.panel-heading {
	color: #00415d;
	background-color: #fff;
	border-color: #fff;
	text-align:center;
}
.panel-login>.panel-heading a{
	text-decoration: none;
	color: #666;
	font-weight: bold;
	font-size: 15px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login>.panel-heading a.active{
	color: #0d314b;
	font-size: 18px;
}
.panel-login>.panel-heading hr{
	margin-top: 10px;
	margin-bottom: 0px;
	clear: both;
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
}
.panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"] {
	height: 45px;
	border: 1px solid #ddd;
	font-size: 16px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login input:hover,
.panel-login input:focus {
	outline:none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	border-color: #ccc;
}
.btn-login {
	background-color: #0d314b;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #59B2E6;
}
.btn-login:hover,
.btn-login:focus {
	color: #fff;
	background-color: #53A3CD;
	border-color: #53A3CD;
}
.forgot-password {
	text-decoration: underline;
	color: #888;
}
.forgot-password:hover,
.forgot-password:focus {
	text-decoration: underline;
	color: #666;
}

.btn-register {
	background-color: #1CB94E;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #1CB94A;
}
.btn-register:hover,
.btn-register:focus {
	color: #fff;
	background-color: #1CA347;
	border-color: #1CA347;
}
.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 70px;
    height: 70px;
    animation: spin 2s linear infinite;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
</style>
    </head>
    <body class="body2">
      <div>
        <table  width="100%"
            border="0" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td height="1">				
					
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td style="padding:5px -15px 5px -1px;">
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                            <tbody>
                                                <tr >
                                                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="padding-left:7px;"><img src='<c:url value="/resources/images/adc6fdcf-4c4b-4787-bfd8-dabc10bcfa03.png" />' width="100px" height="95px" /></td>
    <td>&nbsp;</td>
    <td align="right" style="padding-right:7px;"><img src='<c:url value="/resources/images/e-ims-supra.jpg" />' width="200px" height="41px" /></td>
  </tr>
</table>
</td>
                                                </tr>
                                                
                                          </tbody>
                                        </table>
                                    </td>
                                </tr>
                               
                                <tr>
                                  <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                            
                                                <tr>
                                                    <td height="20px" colspan="3" bgcolor="#0d314b"></td>
                                                </tr>
                                                <tr>
                                                  <td></td>
                                                  <td valign="top" style="padding:0px 50px 0px 120px;text-align:left;"><span style="color: #0d314b; font-size: 38px; font-family: 'Signika', sans-serif; padding-bottom: 5px;">eManager</span>
												  <span style="color: #0d314b; font-size: 20px; font-family: 'Signika', sans-serif; padding-bottom: 5px;">Managed Solution!</span></td>
                                                  <td></td>
                                                </tr>
                                                <tr>
                                                  <td width="70"></td>
                                                  <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                      <td><table width="470" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td class="p3"><b>eManager</b> completely automates Timesheet,Leave process,Reimbursement from from initiation to approval of request and correspondence. All approval can be captured in the transaction itself and remain intact throughout the system. The solution ensures that all the correspondences, documents and communication centric functions are managed smartly while adhering with the Standard Operating Procedures.<br>
	It is well equipped for handling Critical Communication, Correspondences and request movement through Electronic Modes and Approval Workflow. It reduces process delays, helps to access and utilize information quickly and expedite work.

</td>
  </tr>
</table>
</td>
  <td width="35"></td>
  <td><table width="500" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="p3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link1">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
						<c:if test="${not empty message}">
							<script type="text/javascript">
								alert('${message}');
							</script>
						</c:if>
							<%-- <font color="red">${message}</font> --%>
							<div class="col-lg-12">
							<form:form id="loginForm" method="post" modelAttribute="loginBean" role="form" style="display: block;">
									<div class="form-group">
										<form:input id="username" name="username" path="username" class="form-control" placeholder="Username/Email" autofocus=""/>
									</div>
									<div class="form-group">
										<form:password id="password" name="password" path="password" class="form-control"  placeholder="Password" />
									</div>	
									<div class="col-sm-6 col-sm-offset-3">
										<button name="login-submit" id="login-submit" tabindex="4" class="btn btn-primary btn-md" value="Login">Login</button>
										<a href="#" tabindex="5" class="forgot-password pull-right" onclick="frgtPwd();">Forgot Password?</a><br />
									</div>
									<!-- <div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="#" tabindex="5" class="forgot-password" onclick="frgtPwd();">Forgot Password?</a><br />
													<span id="frgtComment" style="color:green;"></span>
												</div>
											</div>
										</div>
									</div> -->
								</form:form>
		<c:if test="${not empty successMsg}">
			<script type="text/javascript">
				alert('${successMessage}');
			</script>
			</c:if>
			<c:if test="${not empty successMsgRegister}">
			<script type="text/javascript">
				alert('${successMsgRegister}');
			</script>
			</c:if>
			<c:if test="${not empty errorMsg}">
			<script type="text/javascript">
				alert('${errorMessage}');
			</script>
			</c:if>	
				<form:form id="register-form" method="post"  modelAttribute="registerBean" role="form" style="display: none;overflow-y:scroll;overflow-x:hidden;height:148px;">
					<div class="form-group" style="display:-webkit-box;">
						<form:input type="text" path="userId" id="userId" tabindex="1" class="form-control" placeholder="Username...only alphanumeric" value="" onkeyup="checkUserNameAvailability()"/>
						<span class="mp2 nj211" id="user-availability-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;">
						<form:input id="fName" name="fName" path="fName" class="form-control" tabindex="2" placeholder="First Name" onkeyup="checkfNameAvailability()" />
						<span class="mp2 nj211" id="user-fName-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;">
						<form:input id="lName" name="lName" path="lName" class="form-control" tabindex="3" placeholder="Last Name" onkeyup="checklNameAvailability()"/>
						<span class="mp2 nj211" id="user-lName-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;">
						<form:input type="email" path="userMail" id="userMail" tabindex="4" class="form-control" placeholder="Email Address" value="" onkeyup="checkUserMailAvailability()"/>
						<span class="mp2 nj211" id="user-mail-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;">
						<form:input type="password" path="pwd" id="pwd" tabindex="5" class="form-control" placeholder="Password....minimum 8 character" onkeyup="matchPwdLogin()"/>
						<!-- <span class="mp2 nj211" id="user-pwd-status" style="margin:20px;"></span> -->
					</div>
					<div class="row">
						<div class="col-sm-6">
						<span id="8char" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Min 8 Characters Long<br>
						<span id="ucase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Uppercase Letter
						</div>
						<div class="col-sm-6">
						<span id="lcase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Lowercase Letter<br>
						<span id="num" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Number
						</div>
					</div>	
					<div class="form-group" style="display:-webkit-box;">
						<form:input type="password" path="confPwd" id="confPwd" tabindex="6" class="form-control" placeholder="Confirm Password" onkeyup="matchPwdLogin()"/>
						<!-- <span class="mp2 nj211" id="user-confpwd-status" style="margin:20px;"></span> -->
					</div>
					<div class="row">
						<div class="col-sm-12">
						<span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords Match
						</div>
					</div>	
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6 col-sm-offset-3">
								<button name="register-submit" id="register-submit" tabindex="7" class="form-control btn-primary btn-md" value="Register Now">Register</button>
							</div>
						</div>
					</div>
				</form:form>
							</div>
						</div>
					</div>
				</div>
</td>
  </tr>
</table></td>
                                                    </tr>
                                                    <tr>
                                                      <td>&nbsp;</td>
                                                      <td width="35"></td>
                                                      <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                      <td><table width="470" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr><!-- onmouseover="changeImage(this,'first');" onmouseout="normalImg(this,'first')" -->
<td><img src='<c:url value="/resources/images/wa.png" />' width="93" height="181" /></td>
<td><img src='<c:url value="/resources/images/files.png" />' width="93" height="181" /></td>
<td><img src='<c:url value="/resources/images/on.png" />' width="93" height="181" /></td>
<td><img src='<c:url value="/resources/images/cor.png" />' width="93" height="181" /></td>
  </tr>
</table>
</td>
                                        <td width="35">
                                        
                                        
                                        
                                        </td>
                                        <td valign="top">
 <table width="400" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="p3">
	<table width="470" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td id="sdb1"><strong>Process Automation</strong></td>   
  </tr>
  <tr>
    <td>&nbsp;</td>    
  </tr>
  <tr>
       
	<td class="f-text" id="sdb2">Workflow solution for enabling reduction in transition time through process optimization.</td> 
  </tr>
</table>
</td>
  </tr>
</table>
</td>
               </tr>
                                                   <!-- <tr>
                                                      <td>&nbsp;</td>
                                                      <td width="35"></td>
                                                      <td>&nbsp;</td>
                                                    </tr>   -->                                 
													</table></td>
                                                  <td width="25"></td>
                                                </tr>
                                               
                                           
                                        </table></td>
                                </tr>
                                
                                <tr>
                                    <td height="20" style="background-color:#0d314b" class="headr">Use Google Chrome 37 or higher version for better performance.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Copyright © 2017 SupraITS. All rights reserved. | <a style="color:#999;" href="http://www.supraits.com/privacy">Privacy</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
        <div id="loadingMessage" style="position:absolute;height:10px;
        width:50px;top:145px;left:900px;visibility:hidden">
            <table><tr><td style="color:red;">Loading...</td></tr></table>
        </div>
		<div id="invaliduser" style="position:absolute;height:10px;
        width:50px;top:140px;left:900px;visibility:hidden">
            <table><tr><td style="white-space:nowrap;color:red;" style="color:red;">Invalid User Credentials</td></tr></table>
        </div>
		<div id="loginMessage" style="position:absolute;height:10px;
        width:350px;top:145px;left:800px;">
        </div>
		<form id="changepassword" name="changepassword" action="ForceChangePassword.jsp" method="post">
			<input type="hidden" name="userName" value="">
			<input type="hidden" name="password" value="">
			<input type="hidden" name="cabinetName" value="">
		</form>
 <div id="loaderGif" class="loader" style="display:none;margin-left:50%;"></div></div>
 			<div class="modal fade" id="forgotPasswordModal" role="dialog" style="margin-top:5%;">
			<div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Forgot Password </h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Email id:</label>
				          <input class="input-md  textinput textInput form-control" id="idUsrMail" maxlength="100" name="taskName" placeholder="Password will be sent to this mail id" style="margin-bottom: 10px" type="text" />
				        </div>
				      <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" onclick="resetPassword();">Reset Password</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
			</div>
			</div> 
	<script type="text/javascript">
	$(document).ready(function(){
		/* $('#userNameProxyflag').change(function() {
	        if(this.checked) {
	            $("#usernameproxymail").attr("disabled", false);
	        }else{
	        	$("#usernameproxymail").attr("disabled", true);
	        }
	    }); */
	$(function() {
	    $('#login-form-link').click(function(e) {
			$("#loginForm").delay(100).fadeIn(100);
	 		$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
	 		$("#loginForm").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
	});
	
	$('#login-submit').click(function() {
				var ret = validateLogin();
				if (ret == true) {
					var x = document.getElementById("loginForm");
					x.action = "login";
					x.submit();
				} else {
					return false;
				}
	});
	$('#register-submit').click(function() {
		checkUserNameAvailability();
		checkfNameAvailability();
		checklNameAvailability();
		checkUserMailAvailability();
		//checkPwdAvailability();
		//checkConfPwdAvailability();
		matchPwdLogin();
		//alert(checkUserNameAvailability() && checkfNameAvailability() && checklNameAvailability() && checkUserMailAvailability() && matchPwdLogin());
		if(checkUserNameAvailability() && checkfNameAvailability() && checklNameAvailability() && checkUserMailAvailability() && matchPwdLogin()){
				var ret = confirm("Do you want to register?");
				if (ret == true) {
					var x = document.getElementById("register-form");
					x.action = "registerUser";
					x.submit();
				} else {
					return false;
				}
		}
	});
	
	});
	</script>		
    </body>  
</html>