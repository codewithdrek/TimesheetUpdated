function allocateAssetDiv(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewAllocateAssetDiv").style.display='block';
	document.getElementById('viewAllocateAssetDiv').setAttribute("style","min-height:600px;");
	$("#viewAllocateAssetDiv").appendTo("#dataDiv");
	getCategoryList('categoryListForAsset');
	//getEmpListBasedOnPolicyGroup('SUPRA-Noida');
	populatePolicyGroup('id_select_type_policy');
}
function allocateAssetToUser(){
	var username = document.getElementById('userListForAsset').value;
	var id = document.getElementById('idListForAsset').value;
	var description = document.getElementById('descForAsset').value;
	var allocationdate = document.getElementById('allocationDateForAsset').value;
	
	var conf = confirm("Do you want to allocate asset to user "+username+" ?");
	if(conf == false){
		return false;
	}
	document.getElementById("assetAllocation").disabled = true;
	$.ajax({
		type: "POST",
		url: "allocateAssetToUser",
		dataType: 'json',
		data:{id:id,username:username,description:description,allocationdate:allocationdate},
		success: function(data){
			document.getElementById("assetAllocation").disabled = false;
			alert(data);
			$("#nameForAsset").val("");
			$("#srNoForAsset").val("");
			$("#statusForAsset").val("");
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("addAssetAllocation").disabled = false;
			alert("ERROR:"+textStatus);
		}
	});
}
function addNewAsset(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewAddAssetDiv").style.display='block';
	document.getElementById('viewAddAssetDiv').setAttribute("style","min-height:550px;");
	$("#viewAddAssetDiv").appendTo("#dataDiv");
	getCategoryList('addCategoryListForAsset');
	getAssetStatusList('addStatusForAsset');
}
function trackAsset(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewTrackAssetDiv").style.display='block';
	document.getElementById('viewTrackAssetDiv').setAttribute("style","min-height:500px");
	$("#viewTrackAssetDiv").appendTo("#dataDiv");
	getCategoryList("assetCategoryFilter");
	getAssetStatusList("assetStatusFilter");
	getAllAssetListForTracking();
}
function getAllAssetListForTracking(){
	$.ajax({
		type: "POST",
		url: "getAssetsList",
		dataType: 'json',
		beforeSend: function() { $('#loaderGif').show(); },
        complete: function() { $('#loaderGif').hide(); },
		success: function(data){
			if(data.length > 0){
				$('#userAssetItemTbl tbody').empty();
				for (var i = 0; i < data.length; i++) {	
					row = $("<tr class='' />");
					$("#tab_logic_userAssetItemApp").append(row); 
					row.append($("<td nowrap class='EWTableElements'>"+(i+1)+"</td>"));
					row.append($("<td nowrap class='EWTableElements'>"+data[i].assetid+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+data[i].assetname+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+data[i].status+"</td>"));
					row.append($("<td nowrap class='EWTableElements'>"+data[i].startdate+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+data[i].enddate+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+data[i].allocatedto+"</td>"));
					row.append($("<td nowrap class='EWTableElements'>"+data[i].allocatedby+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'>"+data[i].allocatedon+"</td>"));
					row.append($("<td  nowrap class='EWTableElements'><a href='#!' onclick=removeAssetAllocation('"+ data[i].assetrfnum +"','"+ data[i].username +"','"+ data[i].startdate +"');>Deallocate</a></td>"));
				}	
			}else{
				alert("No data found");
				return false;
			}
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function removeAssetAllocation(id,username,allocationstartdate){
	var conf = confirm("Do you want to deallocate asset from user "+ username +" ?");
	if(conf == false){
		return false;
	}
	$.ajax({
		type: "POST",
		url: "deAllocateAsset",
		dataType: 'json',
		data:{id:id,username:username,allocationstartdate:allocationstartdate},
		success: function(data){
			alert(data);
			getAllAssetListForTracking();
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function getEmpListBasedOnPolicyGroup(policyGroup){
	$.ajax({
		type: "POST",
		url: "getUserListForAssetAllocation",
		data: {policyGroup:policyGroup},
		dataType: 'json',
		success: function(data){
			var select = document.getElementById("userListForAsset");
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Employee',''));
			for (var i = 0; i < data.length; i++) {				
					select.add(new Option((data[i].fullname)+"("+data[i].username+")",data[i].username));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getAssetStatusList(id){
	$.ajax({
		type: "POST",
		url: "getAssetStatusList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Status',''));
			for (var i = 0; i < data.length; i++) {				
					select.add(new Option(data[i],data[i]));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function getCategoryList(id){
	$.ajax({
		type: "POST",
		url: "getAssetCategoryList",
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Asset Category',''));
			for (var i = 0; i < data.length; i++) {				
					select.add(new Option(data[i],data[i]));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function addNewAssetInSystem(){
	var category = document.getElementById('addCategoryListForAsset').value;
	var tag = document.getElementById('addTagForAsset').value;
	var id = document.getElementById('addIdForAsset').value;
	var name = document.getElementById('addNameForAsset').value;
	var srNumber = document.getElementById('addSrNoForAsset').value;
	var status = document.getElementById('addStatusForAsset').value;
	
	var conf = confirm("Do you want to add new asset?");
	if(conf == false){
		return false;
	}
	document.getElementById("addAssetAllocation").disabled = true;
	$.ajax({
		type: "POST",
		url: "addNewAsset",
		dataType: 'json',
		data:{category:category,tag:tag,id:id,name:name,srNumber:srNumber,status:status},
		success: function(data){
			document.getElementById("addAssetAllocation").disabled = false;
			if(data == "assetIdExist"){
				alert("Asset Id "+ id +" already exist");
				return false;
			}else{
				alert(data);
				$("#addTagForAsset").val("");
				$("#addIdForAsset").val("");
				$("#addNameForAsset").val("");
				$("#addSrNoForAsset").val("");
			}
		},
		error: function(jqXHR, textStatus)
		{
			document.getElementById("addAssetAllocation").disabled = false;
			alert("ERROR:"+textStatus);
		}
	});
}
function populateAssetTag(category,id,categoryid){
	if(categoryid == "categoryListForAsset"){
		$("#nameForAsset").val("");
		$("#srNoForAsset").val("");
		$("#statusForAsset").val("");
	}
	$.ajax({
		type: "POST",
		url: "getAssetTagBasedOnCategory",
		data:{category:category},
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Asset Tag',''));
			for (var i = 0; i < data.length; i++) {				
					select.add(new Option(data[i],data[i]));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateAssetIds(tag,id){
	var category = document.getElementById('categoryListForAsset').value;
	if(id == "idListForAsset"){
		$("#nameForAsset").val("");
		$("#srNoForAsset").val("");
		$("#statusForAsset").val("");
	}
	$.ajax({
		type: "POST",
		url: "getAssetIdsBasedOnTag",
		data:{category:category,tag:tag},
		dataType: 'json',
		success: function(data){
			var select = document.getElementById(id);
			for(var i=select.options.length-1;i>=0;i--)
            {
                select.remove(i);
            }
			select.add(new Option('Select Asset Id',''));
			for (var i = 0; i < data.length; i++) {				
					select.add(new Option(data[i],data[i]));
			    }
		},
		error: function(jqXHR, textStatus)
		{
		alert("ERROR:"+textStatus);
		}
	});
}
function populateAssetDetails(assetId){
	$.ajax({
		type: "POST",
		url: "getAssetDetailsBasedOnAssetId",
		data:{assetId:assetId},
		dataType: 'json',
		success: function(data){
			if(data.length > 0){
				$("#nameForAsset").val(data[0].name);
				$("#srNoForAsset").val(data[0].srnum);
				$("#statusForAsset").val(data[0].status);
			}else{
				alert("Error in fetching asset details.");
				return false;
			}	
		},
		error: function(jqXHR, textStatus)
		{
			alert("ERROR:"+textStatus);
		}
	});
}
function modifyViewAsset(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewManageAssetDiv").style.display='block';
	document.getElementById('viewManageAssetDiv').setAttribute("style","min-height:550px;");
	$("#viewManageAssetDiv").appendTo("#dataDiv");
}
function newITRequest(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewCreateITRequestDiv").style.display='block';
	document.getElementById('viewCreateITRequestDiv').setAttribute("style","min-height:600px;");
	$("#viewCreateITRequestDiv").appendTo("#dataDiv");
}
function uploadBulkAsset(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewBulkAssetUploadDiv").style.display='block';
	document.getElementById('viewBulkAssetUploadDiv').setAttribute("style","min-height:600px;");
	$("#viewBulkAssetUploadDiv").appendTo("#dataDiv");
}
function pendingITRequest(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewPendingITRequestDiv").style.display='block';
	document.getElementById('viewPendingITRequestDiv').setAttribute("style","min-height:600px;");
	$("#viewPendingITRequestDiv").appendTo("#dataDiv");
}
function viewAssetReport(){
	var nodes = document.getElementById('dataDiv').childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].nodeName.toLowerCase() == 'div') {
	         nodes[i].style.display='none';
	     }
	}
	document.getElementById("viewAssetReportDiv").style.display='block';
	document.getElementById('viewAssetReportDiv').setAttribute("style","min-height:550px;");
	$("#viewAssetReportDiv").appendTo("#dataDiv");
	viewAssetChart("reportIdStatus");
}
function viewAssetChart(reportId){
	var labels = [] ;
	var datasets = [];	
	if(reportId == "reportIdStatus"){
		labels =  ["Allocated", "Available", "Repair", "Expired", "Disposed"];
        datasets = [
            {
                data: [300, 50, 100, 40, 120],
                backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774"]
            }
        ];
	}
	if(reportId == "reportIdAllocation"){
		labels =  ["Vendor1", "Vendor2", "Vendor3", "Vendor4", "Vendor5"];
        datasets = [
            {
                data: [30, 210, 100, 140, 10],
                backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774"]
            }
        ];
	}
	if(reportId == "reportIdFY"){
		labels =  ["2015-16", "2016-16", "2017-18"];
        datasets = [
            {
                data: [100, 40, 120],
                backgroundColor: ["#F7464A","#949FB1", "#4D5360"],
                hoverBackgroundColor: ["#FF5A5E","#A8B3C5", "#616774"]
            }
        ];
	}
	if(reportId == "reportIdCategory"){
		labels =  ["Desktop", "Printer", "Mobile Device", "Server", "Others"];
        datasets = [
            {
                data: [30, 150, 200, 40, 120],
                backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"],
                hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774"]
            }
        ];
	}
	generateAssetReportChart(labels,datasets);
}
function generateAssetReportChart(labels,datasets){
	var ctxP = document.getElementById("pieChart").getContext('2d');
	var myPieChart = new Chart(ctxP, {
	    type: 'pie',
	    data: {
	        labels: labels,
	        datasets: datasets
	    },
	    options: {
	        responsive: true
	    }    
	});
}
function closeAddParamDiv(){
	$('#addParamDiv').css({
    	display: 'none'
    });
}
function addNewParam(btnId,masterElem){
	var btn = $("#"+btnId);
	$("#masterParamElem").val(masterElem);
    $('#addParamDiv').css({
    	background: '#f0f0f0',
        position: 'absolute',
        top: btn.offset().top + btn.outerHeight() + 5,
        left: btn.offset().left + btn.outerWidth() - 50,
    }).show();
}
function addParamToMaster(){
	
}








