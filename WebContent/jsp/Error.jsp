<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Encountered</title>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
</head>
<body>
	<div style="text-align:center;">
		<h2>Session Expired or Invalid session.</h2>
	</div>
	<div style="text-align:center;">
		<h3>You will be redirected to login page in 5 seconds...</h3>
		<br />
		<a href="login">Click here to login</a>
	</div>
<script>
$(document).ready(function() {
	  setTimeout(function() {
	    window.location.href = "login";
	  }, 5000);
	});
</script>	
</body>
</html>