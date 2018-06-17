function getUsersInProject(tblId,pid){
	var totalRow = $("#"+tblId+" tr").length;
	var allUserFlag = true;
	$.ajax({
		type: "POST",
		url: "getUserListForProjectAllocation",
		dataType: 'json',
		data:{pid:pid,allUserFlag:allUserFlag},
		success: function(data){
		if(data.length > 0){
			for (var l = 0;l<totalRow; l++) {
				var select = document.getElementById("assignedTo["+l+"].assignedTo");
				var uname = document.getElementById("assignedTo["+l+"].assignedTo").value;
				for(var i=select.options.length-1;i>=0;i--)
	            {
	                select.remove(i);
	            }
				for (var i = 0; i < data.length; i++) {				
					if(data[i].existingInGroupFlag == 'Y'){
						select.add(new Option(data[i].userfullname+"("+data[i].username+")",data[i].username));
					}	
				 }
				if(uname == "NA"){
					select.add(new Option("NA","NA"));
				}
				for (var i = 0; i < select.options.length; i++) {				
					if (select.options[i].value == uname) {
			        	select.options[i].selected = true;
			        }
				 }
				
			}
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getAllCriticalLevel(tblId){
	var totalRow = $("#"+tblId+" tr").length;
	$.ajax({
		type: "POST",
		url: "getAllCriticalLevel",
		dataType: 'json',
		success: function(data){
			for (var l = 0;l<totalRow; l++) {
				var id = "criticality["+ l +"].criticality";
				var select = document.getElementById(id);
				for (var i = 0; i < data.length; i++) {
					if(!(document.getElementById(id).value == data[i].criticalityType))
						select.add(new Option(data[i].criticalityType,data[i].criticalityType));
				}
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});	
}
function updateProjectUsers(pid){
	var allUserFlag = true;
	$.ajax({
		type: "POST",
		url: "getUserListForProjectAllocation",
		dataType: 'json',
		data:{pid:pid,allUserFlag:allUserFlag},
		success: function(data){
		if(data.length > 0){
			$('#assignUserModal').modal('show');
			$('#id_projectName1').html("Project Id :: "+pid);
			$('#id_projectId1').val(pid);
			var select = document.getElementById("duallistbox_demo2");
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].userfullname+"("+data[i].username+")",data[i].username));
				select.options[i].title = data[i].usermail;
				if(data[i].existingInGroupFlag == 'Y'){
					select.options[i].selected = true;
				}	
			 }
			$('[name=duallistbox_demo2]').bootstrapDualListbox('refresh');
			}else
			{
				alert("No data available");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function addRemoveUsersFromProject(){
	var addRemoveFlag = "";
	var selectedUsers = [];
	selectedUsers = $('[name=duallistbox_demo2]').val(); 
	var projectId = document.getElementById('id_projectId1').value;
	if( null == selectedUsers){
		selectedUsers = [''];
	}
	$.ajax({
		type: "POST",
		url: "addRemoveUsersFromProject",
		dataType: 'json',
		data:{projectId:projectId,selectedUsers:selectedUsers,addRemoveFlag:addRemoveFlag},
		success: function(data){
			if(data[0]){
				if(selectedUsers[0] == "")
					alert("All Users from project "+ $('#projectId'+projectId).val() +" deallocated successfully.");
				else
					alert("Users in project "+ $('#projectId'+projectId).val() +" updated successfully.");
			}else{
				alert("Try after some time.");
			}
			$('#assignUserModal').modal('hide');
			window.location.reload(true);
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getAllTaskStatus(tblId){
	$.ajax({
		type: "POST",
		url: "getAllTaskStatus",
		dataType: 'json',
		success: function(data){
			var totalRow = $("#"+tblId+" tr").length;
			for (var l = 0;l<totalRow; l++) {
					var taskStatusId = "taskStatus["+ l +"].taskStatus";
					var select = document.getElementById(taskStatusId);
					for (var i = 0; i < data.length; i++) {
						if(!(document.getElementById(taskStatusId).value == data[i].taskStatusType))
							select.add(new Option(data[i].taskStatusType,data[i].taskStatusType));
					}
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}
function getAllMangerAdmin(tblId,id){
	$.ajax({
		type: "POST",
		url: "getAllReportingManager",
		dataType: 'json',
		success: function(data){
			var currProjectOwner = document.getElementById(id).value;
			var totalRow = $("#"+tblId+" tr").length;
			for (var l = 0;l<totalRow; l++) {
				var select = document.getElementById(id);
				for(var i=select.options.length-1;i>=0;i--)
	            {
	                select.remove(i);
	            }
				for (var i = 0; i < data.length; i++) {				
					select.add(new Option(data[i].fullname,data[i].username));
				   }
				for (var i = 0; i < data.length; i++) {				
					if (select.options[i].value == currProjectOwner) {
			        	select.options[i].selected = true;
			        }
				 }
			}	
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function resetUpdateProjectUserModal(){
	var projectId = document.getElementById('id_projectId1').value;
	updateProjectUsers(projectId);
}
function getPrjStatusType(tblId,id){
	var currStatus = document.getElementById(id).value;
	$.ajax({
		type: "POST",
		url: "getPrjStatusTypeList",
		dataType: 'json',
		success: function(data){
			var totalRow = $("#"+tblId+" tr").length;	
		for (var l = 0;l<totalRow; l++) {	
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			for (var i = 0; i < data.length; i++) {				
				select.add(new Option(data[i].prjStatusType,data[i].prjStatusType));
			   }
			 for (var i = 0; i < data.length; i++) {				
				if (select.options[i].value == currStatus) {
		        	select.options[i].selected = true;
		        }
			 }
		}	
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});	
}