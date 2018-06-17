<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eManager-Generated Employee Document</title>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
</head>
<body>
<div id="fileDataId">
${fileData}
</div>
</body>
</html>