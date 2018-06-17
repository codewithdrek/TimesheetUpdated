<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/resources/images/favicon.ico" />' rel="shortcut icon">
<title>Employee Timesheet History</title>
	<link href='<c:url value="/resources/css/bootstrap.min3.css" />' rel="stylesheet">
	<link href="<c:url value="/resources/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
	<link href="<c:url value="/resources/css/googleapi-fonts.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-table.js"/>"></script>
	<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/js/lumino.glyphs.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-filestyle.min.js" />"></script>
	<script src="<c:url value="/resources/scripts/userProfile.js"/>"></script>
<script type="text/javascript">
var sessionuser = '${sessionScope.loggedInUser}';
if(sessionuser == "" ||  sessionuser == null ||sessionuser == undefined){
	alert("Session Expired.");
	window.close();
}
</script>
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
<body style="padding-top: 5px;">
<div class="container" style="width:100%;">
<div>
<h4>
	<strong>Timesheet History :: ${sessionScope.loggedInUserFullName}</strong>
</h4>
</div>
					<div class="form-inline" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Year</strong></label>
											<span style="padding-left: 0px; margin-top: 5px;margin-left: 5px;">
												<select	class="form-control" id="yearsForTS" style="width:10%;">
													<option value="2016" label="2016" />
													<option value="2017" label="2017" selected />
													<option value="2018" label="2018" />
												</select>
											</span>
											<label style="margin-left: 5px;"><strong>Month</strong></label>
											<span style="padding-left: 0px; margin-top: 5px;margin-left: 5px;">
												<select	class="form-control" id="monthForTS" style="width:10%;">
													<option value="1" label="Jan" />
													<option value="2" label="Feb" />
													<option value="3" label="Mar" />
													<option value="4" label="Apr" />
													<option value="5" label="May" />
													<option value="6" label="Jun" />
													<option value="7" label="Jul" />
													<option value="8" label="Aug" />
													<option value="9" label="Sep" />
													<option value="10" label="Oct" />
													<option value="11" label="Nov" />
													<option value="12" label="Dec" />
												</select>
											</span>
												<a href="#"><button type="button" class="btn-sm btn-primary" style="margin-left:5px;" onclick="getUserPastTS();">Go</button></a>
						</div>
						<br><br>
						<div id="tsUserData"></div>
</div>
<div class="footer" style="text-align:center;margin-top: 5px;">
	<div class="copyrights">Copyright © 2017 SupraITS. All rights reserved. | <a style="color:#999;" title="supraits.com/privacy" href="http://www.supraits.com/privacy">Privacy</a> 
</div>
</div>
							 	
<div id="loaderGif1" class="loader modal"></div>

<script>
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}
$(document).ready(function() {
	var dateObj = new Date();
	var month = dateObj.getUTCMonth() + 1; 
	var day = dateObj.getUTCDate();
	var year = dateObj.getUTCFullYear();
	var elementYear = document.getElementById('yearsForTS');
	elementYear.value = year;
    var elementMonth = document.getElementById('monthForTS');
    elementMonth.value = month-1;
    getUserPastTS();
});
</script>
</body>
</html>
