<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eManager::Modify Employee Document</title>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="https://cdn.ckeditor.com/4.9.0/full-all/ckeditor.js"></script>

<script src="<c:url value="/resources/scripts/hrms.js"/>"></script>
</head>
<body>
<textarea name="editor3" id="editEmpDocEditorId">
	${fileData}
</textarea>
<div>
<button class="btn btn-primary pull-left" style="margin:5px;" title="Update Document" onclick="updateEmployeeDocumentRTE('${docrfnum}');">Save</button>
<button class="btn btn-primary pull-left" style="margin:5px;" title="Reset Documnet" onClick="window.location.reload()">Reset</button>
</div>
<script>
	CKEDITOR.replace( 'editor3',{
		height:"450px"
    });
</script>
</body>
</html>