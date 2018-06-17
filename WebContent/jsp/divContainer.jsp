<div id="loaderGif" class="loader modal" style="position:fixed;"></div>
<div class="panel panel-primary" id="vieTimeShtDiv" aria-labelledby="vieTimeSht" style="width: 100%;display: none;margin-bottom:5px;" scrolling="auto">
			<!-- <div class="panel-heading">
				<strong>Timesheet</strong>
			</div> -->
		<div class="panel-body" style="padding-top:0px;">
		<form:form role="form" id="regform" data-toggle="validator" method="post" modelAttribute="loginBean" enctype="multipart/form-data" name="regform">
				<div class="row">
				<!-- <div class="col-lg-8"> -->
					<table class="table table-striped" id="userLasttenWeekTbl" scrolling="auto">
						<thead>
							<tr>
								<th>S.No.</th>
								 <th>Week(Sun-Sat)</th>
								 <th>Total Effort</th>
								 <th>Status</th>
								 <th>Last Modified On</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userLasttenWeekApp">
							<tr align="center" id='userApp1'></tr>
						</tbody>
					</table>
				<!-- </div> -->
				<!-- <div class="col-lg-4" style="min-height:281px;max-height:281px;">
					<h4><b>Guidelines:</b></h4>
					<ul style="margin-left:-15px;">
						<li style="font-size:14px;font-weight:400;">Input effort HH format.</li>
						<li style="font-size:14px;font-weight:400;">Minimum Effort:  0.5 denotes 30 minutes.If effort is less than 30 minutes,can club with other task and mention in comment box.Fill 0 in case of no effort.</li>
						<li style="font-size:14px;font-weight:400;">Maximum Effort: 24H.</li>	
						<li style="font-size:14px;font-weight:400;">Total week effort must be greater or equals 45 Hours.</li>
						<li style="font-size:14px;font-weight:400;">Input effort value must be multiple of 0.5</li>
						<li style="font-size:14px;font-weight:400;">Apply leave before filling Leave Task.</li>
					</ul>
				</div> -->		
					<div class="col-sm-12 form-group">
						<!-- <button style="border:none;background:#fff;" class='glyphicon glyphicon-question-sign pull-right' onclick="setPopoverData('showPastTSHistoryTT','popover20');return false;" tabindex="0" data-toggle="popover20" data-trigger="focus" data-placement="auto" data-html="true"></button> -->
				        <a id='pastActionHistory' class="pull-right btn btn-primary" style="margin-right:0px;" onclick="showUserTimesheetHistory('${sessionScope.loggedInUser}');" title="View past Timesheet History on Month basis.">Timesheet History</a>
				        <!-- <button style="border:none;background:#fff;margin-left:2px;" class='glyphicon glyphicon-question-sign pull-right' onclick="setPopoverData('showActionHistoryTSTT','popover19');return false;" tabindex="0" data-toggle="popover19" data-trigger="focus" data-placement="auto" data-html="true"></button> -->
				        <a id='actionHistory' class="pull-right btn btn-primary" style="margin-right:5px;" onclick="showActionHistory();" title="View all action taken on selected week timesheet.">Show Action History</a>
						<div class=" col-sm-4 input-group">
				      			<span class="input-group-btn">
				        			<button class="btn btn-secondary" type="button" id="prevWeek" onclick="prevTSData();return false;" style="height:35px;" title="Prev Week"><span class="glyphicon glyphicon-backward"></span></button>
				      			</span>
				      				<input type="text" class="form-control" id='weeklyDatePickerShow' readOnly="true" style="text-align:center;" />
				      				<form:hidden path="submittedWeek" id='weeklyDatePicker' />
				      			<span class="input-group-btn">
				        			<button class="btn btn-secondary" type="button" id="nextWeek" onclick="nextTSData();return false;" style="height:35px;" title="Next Week"><span class="glyphicon glyphicon-forward"></span></button>
				      			</span>
				      	</div>
				    </div>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 column">
		<!-- <span style="font-size:12px;color:red;">Apply leave before filling Leave Task in timesheet.</span> -->
				<table class="table table-bordered table-hover" id="tab_logic" style="margin-bottom:0px;">
					<thead>
						<tr>
							<th class="text-center" style="width:20%;">
								Project List
								<button style="border:none;background:#fff;" class='glyphicon glyphicon-question-sign pull-right' onclick="setPopoverData('timesheetProjectListTT','popover4');return false;" tabindex="0" data-toggle="popover4" data-trigger="focus" data-placement="auto" data-html="true"></button>
							</th>
							<th class="text-center" style="width:20%;">
								Task List
								<button style="border:none;background:#fff;" class='glyphicon glyphicon-question-sign pull-right' onclick="setPopoverData('timesheetTaskListTT','popover18');return false;" tabindex="0" data-toggle="popover18" data-trigger="focus" data-placement="auto" data-html="true"></button>
							</th>
							<th class="text-center success" id="weekDay1">
								Sun
							</th>
							<th class="text-center" id="weekDay2">
								Mon
							</th>
							<th class="text-center" id="weekDay3">
								Tue
							</th>
							<th class="text-center" id="weekDay4">
								Wed
							</th>
							<th class="text-center" id="weekDay5">
								Thu
							</th>
							<th class="text-center" id="weekDay6">
								Fri
							</th>
							<th class="text-center success"  id="weekDay7">
								Sat
							</th>
							<th class="text-center">
								#
							</th>
						</tr>
					</thead>
					<tbody id="tab_logic1">
						<tr id='query0'>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
							<select class="form-control" name="taskMappingList[0].projectId" id="taskMappingList[0].projectId" onchange="getTaskListOnSelect(this.value,this.id)" >
								<option value="select"  selected="selected">Select Project</option>
							</select>
							<div class="help-block with-errors"></div>
		                 </div>
						</td>
							<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
							<select class="form-control" name="taskMappingList[0].taskId" id="taskMappingList[0].taskId">
							<option value="select"  selected="selected">Select Task</option>	
							</select>
							<div class="help-block with-errors"></div>
		                 </div>
							</td>
						<td class="success">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
								<i class="glyphicon glyphicon-comment" aria-hidden=""></i>
                                  <input type="text" name="taskMappingList[0].effortInHoursDay1" id="taskMappingList[0].effortInHoursDay1" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day1" id="taskMappingList[0].day1"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="glyphicon glyphicon-comment" style="border: none;background:white;" id="taskMappingList[0].day1B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day1Comment" id="taskMappingList[0].day1Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay2" id="taskMappingList[0].effortInHoursDay2" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day2" id="taskMappingList[0].day2"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="glyphicon glyphicon-comment" style="border: none;background:white;" id="taskMappingList[0].day2B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day2Comment" id="taskMappingList[0].day2Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay3" id="taskMappingList[0].effortInHoursDay3" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day3" id="taskMappingList[0].day3"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="glyphicon glyphicon-comment" style="border: none;background:white;" id="taskMappingList[0].day3B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day3Comment" id="taskMappingList[0].day3Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay4" id="taskMappingList[0].effortInHoursDay4" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day4" id="taskMappingList[0].day4"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="glyphicon glyphicon-comment" style="border: none;background:white;" id="taskMappingList[0].day4B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day4Comment" id="taskMappingList[0].day4Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay5" id="taskMappingList[0].effortInHoursDay5" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day5" id="taskMappingList[0].day5"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="glyphicon glyphicon-comment" style="border: none;background:white;" id="taskMappingList[0].day5B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day5Comment" id="taskMappingList[0].day5Comment"/>
							</div>
						</td>
						<td>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay6" id="taskMappingList[0].effortInHoursDay6" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day6" id="taskMappingList[0].day6"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="glyphicon glyphicon-comment" style="border: none;background:white;" id="taskMappingList[0].day6B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day6Comment" id="taskMappingList[0].day6Comment"/>
							</div>
						</td>
						<td class="success">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-12 input-group">
                                  <input type="text" name="taskMappingList[0].effortInHoursDay7" id="taskMappingList[0].effortInHoursDay7" value='0' class="form-control"/>
                                  <input type="hidden" name="taskMappingList[0].day7" id="taskMappingList[0].day7"/>
                                  <span class="input-group-addon" style="padding: 0px;background:white;">
									<button type="button" class="glyphicon glyphicon-comment" style="border: none;background:white;" id="taskMappingList[0].day7B" onclick="addCmt(this.id)"></button>
								  </span><input type="hidden" name="taskMappingList[0].day7Comment" id="taskMappingList[0].day7Comment"/>
							</div>
						</td>
						<td class="text-center">
								<a href="#!" onclick="deleteFTRow('0');"><span class="glyphicon glyphicon-trash" style="color:#000;margin-top:7px;"></span></a>
						</td>
						</tr>
					<tr id='query1'></tr>
					</tbody>
				</table>
				<table class="table table-bordered " id="perDayEffortItemTbl" scrolling="auto" style="margin-bottom:2px;">
					<tr>
					<td style="width:35%;background-color:#A6E4F7;">
					<span><button class='glyphicon glyphicon-question-sign pull-left' onclick="setPopoverData('fillTimesheetTT','popover21');return false;" tabindex="0" data-toggle="popover21" 
					                  data-trigger="focus" data-placement="auto" data-html="true" style="border:none;background-color:#A6E4F7;"></button></span>
					<strong class='pull-right'>Total Effort:</strong>
					</td>
					<td id="totalAllDayEffort" style="width:5%;background-color:#A6E4F7;color:red;font-weight: bolder;">0</td>
					<td id="totalDay1" class="success" style="width: 8.5714%;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay2" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay3" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay4" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay5" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay6" style="width: 8.5714%;background-color:#A6E4F7;text-align:center;font-weight: bolder;">0</td>
					<td id="totalDay7" class="success" style="width: 8.5714%;text-align:center;font-weight: bolder;">0</td>
					<td>#</td>
					</tr>
				</table>
				<input id="adminRemark" name="adminRemark" readonly="true" class="form-control" type="text" placeholder="Approver Remark" style="margin:5px;" />
			</div>
		</div>
		<div class="inline" id="timeButtonDiv">
			<a href="#l" id="add_row" class="btn btn-primary pull-left" onclick="addRowTS();return false;">Add Task Row</a>
			<!-- <a href="#l" id='delete_row' class="pull-left btn btn-primary" onclick="deleteRowTS();return false;" style="margin-left:5px;">Delete Last Task</a> -->
			<a href="#l" id='submitTime' class="pull-right btn btn-success" onclick="saveTS();" style="margin-right:10px;">Save</a>
			<a href="#l" id='saveOnlyTime' class="pull-right btn btn-primary" onclick="submitTS();" style="margin-right:10px;">Submit</a><br />
		</div>
		</form:form>
		</div>
</div>		

<div class="panel panel-primary" id="vieReportDiv" aria-labelledby="viewReport"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active"></li>
							</ol>
						</div> -->
						<!-- <div class="panel-heading">
							<strong>Timesheet Reports</strong>
						</div> -->
						<div class="panel-body">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:10px;">
									<div class="form-group" style="margin-left:5px;">
											<label><strong>Start Date</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker" class="form-control" style="width:100px;"/>
											</span>
									</div>
									<div class="form-group" style="margin-left:5px;">					
										<label><strong>End Date</strong></label><br />
										<span style="padding-left: 0px; margin-top: 5px;">
											<input type="text" id="to-datepicker" class="form-control" style="width:100px;"/>
										</span>
									</div>
										<div class="form-group" style="margin-left:5px;">
											<label><strong>Project Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<!--<select	class="form-control" id="projectList">-->
												 <select class="form-control" id="projectList" onchange="populateReportParamForDailyReport('true','usrListInPrjct',this.id);" style="max-width:140px;"> 
													<option value="select" label="Select Project" selected />
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-left:5px;">
											<label><strong>Task Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												 <select class="form-control" id="taskListForReport" style="max-width:140px;"> 
													<option value="select" label="Select Task" selected />
												</select>
											</span>
										</div>
									 	<div class="form-group" style="margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInPrjct" style="max-width:140px;">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-left:5px;">
											<label><strong>RM Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="rmListInReport" style="max-width:100px;">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-left:5px;">
											<label><strong>Status</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="statusListforReport" style="max-width:100px;">
													<option value="" label="All" selected>All</option>
												    <option value="Pending" label="Pending">Pending</option>
												    <option value="Rejected" label="Rejected">Rejected</option>
													<option value="Approved" label="Approved">Approved</option>
													<option value="Saved" label="Saved">Saved</option>
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-left:15px;">
										<label><a href="#" type="button" class="btn btn-primary" style="margin-top:30px;" onclick="getReport();">Go</a></label>
									</div>
					
					</div>
				<div id="tsDailyRprtId" style="display:none;">
				<label><a href="downloadDailyTSReport" type="button" style="margin-left:15px;" class="btn btn-primary pull-left">Download XLS</a></label>
				<div class="table-responsive" style="overflow-x:scroll;overflow-y:scroll;max-height:450px;">
					<table class="table table-bordered" style="margin:5px;" id="dailyUserTSItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center success">S No</th>
								<th class="text-center success nowrap">Transaction Date</th>
								<th class="text-center success">Name</th>
								<th class="text-center success">Project</th>
								<th class="text-center success">Task</th>
								<th class="text-center success">Effort</th>
								<th class="text-center success">Comment</th>
								<th class="text-center success nowrap">Submitted On</th>
								<th class="text-center success nowrap">LastModified On</th>
								<th class="text-center success">Last Modified By</th>
								<th class="text-center success">RM</th>
								<th class="text-center success">Status</th>
								
								<th class="text-center danger">Leave Type</th>
								<th class="text-center danger">Flag</th>
								<th class="text-center danger">Leave Status</th>
								<th class="text-center danger nowrap">Submitted On</th>
								<th class="text-center danger nowrap">LastModified On</th>
								<th class="text-center danger">Last Modified By</th>
								<th class="text-center danger">Purpose</th>
							</tr>
						</thead>
						<tbody id="tab_logic_dailyUserItemApp">
						</tbody>
					</table>
					</div>
					</div>
					</div>
					  
					<div class="row">
		<div class="col-lg-4">
			<div class="panel panel-primary" id="downloadReportDiv" aria-labelledby="downloadReport"
					style="width: 100%;display: none;" scrolling="auto">
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Generated Time:</strong><br /><span id="fileNameReportTime" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Timesheet Report:</strong><br /><span id="fileNameReport" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="files"><button type="button" class="btn btn-primary" style="margin-top:30px;">Download</button></a></label>
					</div>
			</div>
		</div>
	</div>
	</div>
	
			
	<div class="panel panel-primary" id="addUserDiv" aria-labelledby="addUserDiv" style="width: 100%;display: none;" scrolling="auto">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<strong>Update Employee Detail</strong>
			 	</div>
			 <div class="panel-body">
			 <!-- <div id="userListFilter" class="form-group pull-right"> -->
											<strong>Choose Filter:</strong> 
												<select	class="form-control" style="width:auto;display:inline;" id="userFilter" onchange="addUserFilterFunc(this.value,'')">
														<option value="All" label="All User">All User</option>
													    <option value="Created" label="Pending">Pending</option>
														<option value="Active" label="Active" selected>Active</option>
														<option value="Disable" label="Disable">Disable</option>
														<option value="Deleted" label="Deleted">Deleted</option>
												</select>
											<strong>Filter by Name:</strong> 	
												<input class="input-sm  textinput textInput form-control" style="width:20%;display:inline" id="userFilterByName" placeholder="Employee Name" onkeyup="filterAnyTableByInput(this.value,'pendingUserItemTbl','1');">
											<strong>Filter by Reporting Manager:</strong> 	
												<input class="input-sm  textinput textInput form-control" style="width:20%;display:inline" id="userRMFilterByName" placeholder="Reporting Manager" onkeyup="filterAnyTableByInput(this.value,'pendingUserItemTbl','6');">
					</div>
	    		</div>
			</div>
			 	<div id="pendingUserItemDiv" aria-labelledby="pendingUserItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" style="margin:5px;" id="pendingUserItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">S No</th>
								<th class="text-center">Name</th>
								<th class="text-center">E-Mail</th>
								<th class="text-center">Role</th>
								<th class="text-center">Group</th>
								<th class="text-center">Status</th>
								<th class="text-center">Reporting Manager</th>
								<th class="text-center">Reporting HR</th>
								<th class="text-center">Update</th>
							</tr>
						</thead>
						<tbody id="tab_logic_pendingUserItemApp">
							<tr align="center" id='userApp2'></tr>
						</tbody>
					</table>
				</div>
			    	
			<div class="panel panel-primary" id="viewApproveTimeDiv" aria-labelledby="viewApproveTimeDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active"></li>
							</ol>
						</div> -->
					<div class="panel panel-default">	
						<div class="panel-heading">
							<strong>Approve/Reject User Timesheet </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('approveRejectTSTT','popover12');" tabindex="0" data-toggle="popover12" data-trigger="focus" data-placement="auto" data-html="true"></button>
						</div>
							<div class="table-responsive" style="padding-top:15px;">
								<div class="form-inline" style="margin-left:10px;">
								<c:if test = "${sessionScope.loggedinusergroup eq 'Admin'}">
								<div class="form-group" id="timesheetStatusFilterByUserName" style="margin-left:15px;">
									<label><strong>Filter By:</strong></label><br />
									<input type="radio" name="allUserPendingTS" value='All'>
									<label>All</label>
									<input type="radio" name="allUserPendingTS" value='${sessionScope.loggedInUser}' checked="true">
									<label>Direct Reportee</label>
								</div>
							</c:if>
									<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
										<label><strong>Status:</strong></label><br />
										<span>
											<select class="form-control" id="timesheetFilter">
												<option value="All" label="All">All</option>
											    <option value="Pending" label="Pending" selected>Pending</option>
											    <option value="Rejected" label="Rejected">Rejected</option>
												<option value="Approved" label="Approved">Approved</option>
												<option value="Saved" label="Saved">Saved</option>
											</select>
										</span>	
									</div>
						<div class="form-group" id="timesheetStatusFilterByUserName" style="margin-left:15px;">
							<label><strong>Employee Name:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="tmshtFilterByName" placeholder="Employee Name">
							</span>
						</div>
						<div class="form-group" style="margin-left:15px;">
							<label><strong>Start Date:</strong></label><br />
							<span>
							 <input class="input-md  textinput textInput form-control" id="timesheetFilterStartDate" placeholder="Start Date">
							</span>
						</div>
						<div class="form-group" style="margin-left:15px;">
							<label><strong>End Date:</strong></label><br />
							<span>
							 <input class="input-md  textinput textInput form-control" id="timesheetFilterEndDate" placeholder="End Date">
							</span>
						</div>
						<div class="form-group" style="margin-left:15px;">
							<label><strong>Recorde/Page:</strong></label><br />
							<span>
											<select class="form-control" id="timesheetFilterPerPageRecord">
											    <option value="10" label="10" selected>10</option>
											    <option value="20" label="20">20</option>
												<option value="50" label="50">50</option>
												<option value="100" label="100">100</option>
											</select>
							</span>
						</div>
						<div class="form-group" style="margin-left:15px;">
							<br />
							<span><a href="#!" onclick="fetchByParam('','1');return false;" class="btn btn-primary pull-left" style="margin:5px;">Go</a></span>
						</div>
					</div>
					</div>
			<div><span id="noDataAvailableId" style="color:red;font-weight:600;margin:15px;"></span></div>		
		</div></div>
			<div id="userDataItemDiv" aria-labelledby="userTimeItems"
					style="margin-top: 2px; width: 100%;display: none;" scrolling="auto">
					<div>
					<span><a href="#!" onclick="approveSelectedTimesheet();" class="btn btn-primary pull-left" style="margin:5px;">Approve Selected</a></span>
					<span class="pull-right" style="margin-top: 3px;">
					<!-- <span class='glyphicon glyphicon-backward' style='margin-right: 10px;margin-top: 3px;border:1px solid;border-radius:3px;'></span> -->
						<Button title='1' id="firstButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">First</Button>
						<Button title='0' id="prevButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">Prev</Button>
						<Button title='2' id="nextButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">Next</Button>
						<Button title='3' id="lastButton" class="btn-sm" onclick="goToPage(this.id);" style="border:none;background-color:#5bc0de;color:#fff;">Last</Button>
					</span>					
					</div>
					<table class="table table-bordered" id="userItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th>EmpId</th>
						        <th>Name<a href="#" onclick="sortTable('userItemTbl',2)"><span class="glyphicon glyphicon-resize-vertical"></span></a></th>
						        <th>RM<a href="#" onclick="sortTable('userItemTbl',3)"><span class="glyphicon glyphicon-resize-vertical"></span></a></th>
								<th>Effort</th>
								<th>Week(Sun-Sat)</th>
								<th>Status</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userItemApp">
							<tr align="center" id='userApp1'></tr>
						</tbody>
					</table>
			</div>
				<!-- <div class="modal fade" id="myModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Update User's Role,Group and Status</h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>User Id:</label><span id="uid" style="color:red;margin-left:10px;">dddddd</span>
				        </div>
				        <div class="form-group">  
				          <label>Group*</label>
				          <select class="form-control" id="userGrp">
								<option value="Admin" label="Admin">Admin</option>
							    <option value="Approver" label="Approver">Approver</option>
								<option value="Owner" label="Project Owner">Project Owner</option>
								<option value="User" label="User" selected>User</option>
						</select>
						</div>												
						<div class="form-group">						
				          <label>Role*</label>
				          <select class="form-control" id="usrRoleFilter">
								<option value="User" label="User" selected>User</option>
							    <option value="Developer" label="Developer">Developer</option>
								<option value="Manager" label="Manager">Manager</option>
								<option value="Designer" label="Designer">Designer</option>
								<option value="Tester" label="Tester">Tester</option>
								<option value="HR" label="HR">HR</option>
								<option value="Accountant" label="Accountant">Accountant</option>
						</select>
						</div>
						<div class="form-group">						
				          <label>User Status*</label>
				          <select	class="form-control" id="userStat">
									<option value="Active" label="Active" selected>Active</option>
									<option value="Disable" label="Disable">Disable</option>
									<option value="Deleted" label="Delete">Delete</option>
							</select>
						</div>
						<div class="form-group">						
				          <label>Reporting Manager*</label>
				          <select class="form-control" id="userReportee">
														<option value="" label="Select" selected/>
												</select>
						</div>
						<div class="form-group">						
				          <label>HR Manager*</label>
				          <select class="form-control" id="userHRReportee">
							 <option value="" label="Select" selected/>
						  </select>
						</div>
						<div class="form-group">						
				          <label>Comment</label>
				          <input type = "text" class="form-control" maxlength="100" value="" id="approverComment" placeholder="Remark"/>
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveUserStatus" onclick="saveUserStatus();">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				    </div>
				  </div> -->
	 <div id="viewDModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="classInfo" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title" id="classModalLabel">
		              View Only: Weekly Timesheet Data
		            </h4>
		      </div>
		      <div class="modal-body">
		      	<!-- <strong>Project:</strong><span id="prjctName"></span><br /> -->
		      	<strong>User Name:</strong><span id="userName11" style="margin-left:10px;"></span><br />
		      	<strong>Week(Status):</strong><span id="statusWeek" style="margin-left:10px;"></span><br />
		      	<strong>User Remark:</strong><br /><span id="usrRemark" style="color:red;"></span><br />
		      	<strong>Remarks(add remark if any):</strong>
		      	<input type="text" id="approverRemark11" placeholder="Approver remark."/>
		      	<input type="hidden" id="frstDayDate11" />
		      	<input type="hidden" id="lastDayDate11" />
		      	<input type="hidden" id="userName12" />
		      	<button class= "btn btn-primary-sm" onclick="saveApproverRmrk();" style="margin:10px;">Save</button>
		        <table id="userPrjctEffortTbl" class="table table-bordered">
		        
		          <thead>
		          <tr>
		          			<th class="text-center">
								Project Name
							</th>
							<th class="text-center">
								Task Name
							</th>
							<th class="text-center success" id="weekDay11">
								Sun
							</th>
							<th class="text-center" id="weekDay22">
								Mon
							</th>
							<th class="text-center" id="weekDay33">
								Tue
							</th>
							<th class="text-center" id="weekDay44">
								Wed
							</th>
							<th class="text-center" id="weekDay55">
								Thu
							</th>
							<th class="text-center" id="weekDay66">
								Fri
							</th>
							<th class="text-center success"  id="weekDay77">
								Sat
							</th>
						</tr>
		          </thead>
		          <tbody id="tab_logic_userPrjctEffortApp">
		          </tbody>
		        </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" id='actionHistory' class="pull-left btn btn-info" style="margin-left:10px;" onclick="showActionHistoryView();"> Show Action History</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">
		          Ok
		        </button>
		      </div>
		    </div>
		  </div>
</div>
	<div class="panel panel-primary" id="viewProjectDiv" aria-labelledby="viewProjectDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active">Home</li>
							</ol>
						</div> -->
				<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Create New Project </strong>
						</div>
						<div class="panel-body">
							<div id="div_id_select" class="form-group required">
								<span style="color:red;font-size:14px;">*Kindly enable pop up before proceed.</span>
							</div>
							<div id="div_id_select" class="form-group required">
                            <label for="id_select"  class="control-label col-md-4  requiredField"> Project Type<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "  style="margin-bottom: 10px">
                                <label class="radio-inline"><input type="radio" checked="checked" name="id_select_type" id="id_select_type" value="Internal"  style="margin-bottom: 10px">Internal</label>
                                <label class="radio-inline"> <input type="radio" name="id_select_type" id="id_select_type" value="Client"  style="margin-bottom: 10px">Client </label>
                            </div>
                       		 </div>
                       		 <div id="div_id_prjctName" class="form-group required">
                            <label for="id_prjctName" class="control-label col-md-4  requiredField"> Project Name<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <input class="input-md  textinput textInput form-control" id="id_prjctName" maxlength="100" name="projctName" placeholder="Choose Project Name" style="margin-bottom: 10px" type="text" />
                            </div>
                        	</div>
                        	<div id="div_id_projectDesc" class="form-group required">
                            <label for="id_projectDesc" class="control-label col-md-4  requiredField"> Description<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <input class="input-md  textinput textInput form-control" id="id_projectDesc" maxlength="250" name="projectDesc" placeholder="Project Description" style="margin-bottom: 10px" type="text" />
                            </div>
                            </div>
                            <div id="div_id_projectStart" class="form-group required">
                            <label for="id_projectDesc" class="control-label col-md-4  requiredField"> Actual/Planned Start Date<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <input type="text" id="from-datepickerProject" class="form-control" placeholder="Project start Date"/>
                            </div>
                            </div>
                            <div id="div_id_projectEnd" class="form-group required" >
                            <label class="control-label col-md-4  requiredField" style="margin-top:7px;"> Actual/Planned Completion Date<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8" style="margin-top:7px;">
                                <input type="text" id="to-datepickerProject" class="form-control" placeholder="Project end Date"/>
                            </div>
                            </div>
                            <div class="form-group"> 
                            	<div class="controls col-md-4 ">
                                	<input type="submit" name="submit-project" value="Create" style="margin-top:5px;" class="btn btn-primary btn btn-info pull-right" id="submit-project" onclick="submitDetailPrjct();"/>
                            	</div>
                        	</div> 
                        
						
						</div>
					</div>		
				</div>	
		<div class="panel panel-primary" id="editProjectDiv" aria-labelledby="editProjectDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Edit Project Details </strong>
						</div>
						<div class="panel-body">
						<div class="form-inline panel panel-primary" style="margin-left:10px;">
							<div class="form-group" style="margin-left:5px;">
								<strong>Filter:</strong>
								<select	class="form-control" id="projectStatusFilter" onchange="getProjectDetailsForEdit(this.value);">
									<option value="Active" label="Active" selected>Active</option>
								    <option value="Inactive" label="Inactive">Inactive</option>
								</select>
							</div>
							<div class="form-group" style="margin-left:5px;">
								<strong>Search Project:</strong>
								<input class="input-sm  textinput textInput form-control" id="projectName1" placeholder="Project Name" onkeyup="filterAnyTableByInput(this.value,'editPrjctItemTbl','1');"/>
						    </div>
						</div>
						</div>
		</div>
		</div>
		<div class="panel panel-primary" id="assignProjectDiv" aria-labelledby="assignProjectDiv"	style="width: 100%;display: none;" scrolling="auto">
						<!-- <div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active">Home</li>
							</ol>
						</div> -->
					<div class="panel panel-default">	
						<div class="panel-heading">
							<strong>Project Assignment </strong>
						</div>
						<div class="panel-body">
							
						</div>
		</div>
		</div>
		<div id="editPrjctItemDiv" aria-labelledby="editPrjctItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="editPrjctItemTbl" style="margin:5px;" scrolling="auto">
						<thead>
							<tr>
								<th>Project Id</th>
								<th class="text-center">Project Name</th>
								<th class="text-center">Project Owner</th>
								<th class="text-center">Start Date</th>
								<th class="text-center">Status</th>
								<th class="text-center">Project Type</th>
								<th class="text-center">Creation Date</th>
								<th class="text-center">Add Task</th>
								<th class="text-center">Operation</th>
							</tr>
						</thead>
						<tbody id="tab_logic_editPrjctItemApp">
							<tr align="center" id='editPrjctApp1'></tr>
						</tbody>
					</table>
				</div>
		<div id="assignPrjctItemDiv" aria-labelledby="assignPrjctItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered " id="assignPrjctItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>Project Id</th>
								<th class="text-center">Project Name</th>
								<th class="text-center">Project Owner</th>
								<th class="text-center">Start Date</th>
								<th class="text-center">Status</th>
								<th class="text-center">Project Type</th>
								<th class="text-center">Creation Date</th>
								<th class="text-center">Add User</th>
							</tr>
						</thead>
						<tbody id="tab_logic_assignPrjctItemApp">
							<tr align="center" id='assignPrjctApp1'></tr>
						</tbody>
					</table>
				</div>	
				<div class="modal fade" id="addTaskModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Task to </h4>
				          <span id="id_prjctName1"></span>
				          <input type="hidden" id="pidValue" name="pidValue" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Title:</label>
				          <input class="input-md  textinput textInput form-control" id="id_taskName" maxlength="50" minlength="6" name="taskName" placeholder="Task Name" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">  
				          <label>Type</label>
				          <select id="id_taskType" class="form-control" name="taskType" style="margin-bottom: 10px">
				          		<option value="">Select Type</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Description</label>
				          <input class="input-md  textinput textInput form-control" id="id_taskDesc" maxlength="200" name="taskDesc" placeholder="TaskDescription" style="margin-bottom: 10px" type="text" />
				        </div>
				        </div>
				        <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="saveNewTask" onclick="insertNewTask();">Save</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				 <div class="modal fade" id="actionHistoryModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Action History</h4>
				        </div>
				        <div class="modal-body">
				     <table id="tmsActionHistoryTbl" class="table table-bordered">
		          		<thead>
		          		<tr>
		          			<th class="text-center">
								Action
							</th>
							<th class="text-center">
								Date
							</th>
							<th class="text-center">
								Action by
							</th>
							<th class="text-center">
								Remark
							</th>
						</tr>
		          </thead>
		          <tbody id="tab_logic_actionHistory">
		          </tbody>
		        </table>
				        </div>
				      </div>
				     </div>
				  </div>
				 <div class="modal fade" id="addCmtModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Comment </h4><span id="id_cmtId"></span>
				          <input type="hidden" id="cmtValueHidden" name="cmtValueHidden" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Remark:</label>
				          <input class="input-md  textinput textInput form-control" id="idComment" maxlength="100" name="taskName" placeholder="Write Comment...max 100 character" style="margin-bottom: 10px" type="text" />
				        </div>
				      <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="addNewCmt" onclick="captureCmt();">OK</button>
				          <!-- <button type="button" class="btn btn-info" data-dismiss="modal">Close</button> -->
				        </div>
				        </div>
				       </div>
				 </div>
				</div>  
				<!-- <div class="panel panel-primary" id="viewUserProfileDiv" aria-labelledby="viewUserProfileDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="row">
							<ol class="breadcrumb">
								<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
								<li class="active">Home</li>
							</ol>
						</div>
						<div class="panel-heading">
							<strong>Edit User Profile </strong>
						</div>
						<div class="panel-body">
							<div class="form-inline col-lg-12">
                            <label for="id_select"  class="control-label"> User Type<span class="asteriskField">*</span> </label>
                                <label class="radio-inline"><input type="radio" checked="checked" name="id_user_type" id="id_user_type" value="Internal"  style="margin-bottom: 10px">Internal</label>
                                <label class="radio-inline"> <input type="radio" name="id_user_type" id="id_user_type" value="Third_Party"  style="margin-bottom: 10px">Third_Party </label>
                       		 </div>
                       		  <div class="form-inline col-lg-12">
		                            <label class="control-label"> User Name<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> Employee Name<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> Reporting Manager<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />   
		                      </div>
		                      <div class="form-inline col-lg-12">
		                            <label class="control-label">Assigned Project<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                      </div>
		                      <div class="form-inline col-lg-12">
		                            <label class="control-label"> Gender<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> DOB<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> Joining Date<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />   
		                      </div>
                        	<div class="form-inline col-lg-12">
                        			<label class="control-label"> Group<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> Role<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> E-mail<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
                            </div>
                            <div class="form-inline col-lg-12">
                        			<label class="control-label"> Update Password<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_userName" maxlength="100"  style="margin-bottom: 10px" readOnly="true"/>
		                            <label class="control-label"> New Password<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
		                             <label class="control-label"> Confirm Password<span class="asteriskField">*</span> </label>
		                                <input type="text" class="form-control" id="id_fullName" maxlength="250" name="projectDesc" style="margin-bottom: 10px" readOnly="true" />
                            </div>
                            <div class="form-group"> 
                            <div class="controls col-md-8 ">
                                <button class="btn btn-primary btn btn-info" id="updateProfileBtn" onclick="updateProfile();">Update Detail</button>
                            </div>
                        </div> 
					</div></div> -->
					<div class="modal fade" id="chngPwdModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Change Password </h4><span id="id_cmtId"></span>
				          <input type="hidden" id="cmtValueHidden" name="cmtValueHidden" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Old Password:</label>
				          <input class="input-md  textinput textInput form-control" id="idOldPwd" maxlength="15"  placeholder="Old Password" style="margin-bottom: 10px" type="password" />
				        </div>
				        <div class="form-group">
				          <label>New Password:</label>
				          <input class="input-md  textinput textInput form-control" id="idNewPwd" maxlength="15" placeholder="Password length must be between 8 and 15 " style="margin-bottom: 10px" type="password" onkeyUp="matchPwd();" autocomplete="off" />
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
				        <div class="form-group"><br />
				          <label>Confirm Password:</label>
				          <input class="input-md  textinput textInput form-control" id="idNewConfPwd" maxlength="15"  placeholder="Confirm Password" style="margin-bottom: 10px" type="password" onkeyUp="matchPwd();" autocomplete="off"/>
				        </div>
				        <div class="row">
						<div class="col-sm-12">
						<span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords Match
						</div>
						</div>
				      <div class="modal-footer" style="margine-top:3px;">
	    			      <button type="button" class="btn btn-success" id="updateNewPwd" onclick="updateNewPwd();" data-loading-text="Changing Password..." value="Change Password">Update Password</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				 </div>
				</div>
				<div class="panel panel-primary" id="createUserDiv" aria-labelledby="createUserDiv"	style="width: 100%;display: none;" scrolling="auto">
				<div class="panel panel-default">
						<div class="panel-heading">
			    			<strong>Create New User </strong>
			 			</div>
						<div class="panel-body">
							<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
							<input type="text" id="userCreateId" tabindex="1" class="form-control" placeholder="Username...only alphanumeric" value="" onkeyup="checkUserNameAvailability1()" style="width: 80%;"/>
						<span class="mp2 nj211" id="user-availability-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
						<input id="fCreateName" name="fCreateName" class="form-control" tabindex="2" placeholder="First Name" onkeyup="checkfNameAvailability()" style="width: 80%;" />
						<span class="mp2 nj211" id="user-fName-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
						<input id="lCreateName" name="lCreateName" class="form-control" tabindex="3" placeholder="Last Name" onkeyup="checklNameAvailability()" style="width: 80%;"/>
						<span class="mp2 nj211" id="user-lName-status" style="margin:20px;"></span>
					</div>
					<div class="form-group" style="display:-webkit-box;margin-left: 14px;">
						<input type="email" id="userCreateMail" tabindex="4" class="form-control" placeholder="Email Address" value="" onkeyup="checkUserMailAvailability()" style="width: 80%;"/>
						<span class="mp2 nj211" id="user-mail-status" style="margin:20px;"></span>
					</div>
					<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">
						<select	class="form-control" id="userCreateGrp" tabindex="5">
							<option value="" label="Select User Group" selected />
						</select>
					</div>
					<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">
						<select	class="form-control" id="userAccessGrp" tabindex="5">
								<option value="" label="Select Access Group" selected />
						</select>
					</div>
					<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">	
				          <!-- <label>Role*</label> -->
				          <select class="form-control" id="usrCreateRole" tabindex="6">
       					    <option value="" label="Select User Role" selected>Select User Role</option>
							<!-- <option value="User" label="User">User</option>
						    <option value="Developer" label="Developer">Developer</option>
							<option value="Manager" label="Manager">Manager</option>
							<option value="Designer" label="Designer">Designer</option>
							<option value="Tester" label="Tester">Tester</option>
							<option value="HR" label="HR">HR</option>
							<option value="Accountant" label="Accountant">Accountant</option> -->
							</select>
						</div>
						<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">						
				          <!-- <label>User Status*</label> -->
				          <select	class="form-control" id="userCreateStatus" tabindex="7">
				          								<option value="" label="Select User Status" selected>Select User Status</option>
														<option value="Active" label="Active">Active</option>
														<option value="Disable" label="Disable">Disable</option>
														<option value="Deleted" label="Delete">Delete</option>
												</select>
				          <!-- <label>Reporting Manager*</label> -->
				         </div>
				         <div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6"> 
				          <select	class="form-control" id="userCreateReportee" tabindex="8">
														<option value="" label="Select Reporting Manager" selected/>
						</select>
						</div>
						<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6"> 
				          <select	class="form-control" id="userCreateHRReportee" tabindex="8">
														<option value="" label="Select HR Manager" selected/>
						</select>
						</div>
						<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">
						<select class="form-control" id="userCreatePolicyGroup">
							<option value="" label="HR Policy Group">HR Policy Group</option>
						</select>
						</div>		
						<!-- <div class="form-group">						
				          <input type = "text" class="form-control" maxlength="100" tabindex="9" id="approverCreateComment" placeholder="Admin remark...if any"/>
				        </div> -->
				        </div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6 col-sm-offset-3">
								<input type="button" name="create-submit" id="add-user" tabindex="10" class="form-control btn-primary btn-register" onclick="addUserByAdmin();" value="Create User">
							</div>
						</div>
					</div>
				</div>	
				</div>
				<div class="panel panel-primary" id="vieMonthlyReportDiv" aria-labelledby="viewMonthlyReport"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Monthly Timesheet Report </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('monthlyTimesheetReportTT','popover13');" tabindex="0" data-toggle="popover13" data-trigger="focus" data-placement="auto" data-html="true"></button>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:20px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Employee:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="userListForMonthlyReport" onchange="getUsersProjectList();">
													<option value="select" label="Select Employee" selected />
												</select>
											</span>
										</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Project List</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="projectListOfUser">
													<option value="" label="Select Project" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Year</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="yearsForReport">
													<option value="2016" label="2016">2016</option>
													<option value="2017" label="2017" selected>2017</option>
													<option value="2018" label="2018">2018</option>
												</select>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>Month</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="monthForReport">
													<option value="1" label="Jan">Jan</option>
													<option value="2" label="Feb">Feb</option>
													<option value="3" label="Mar">Mar</option>
													<option value="4" label="Apr">Apr</option>
													<option value="5" label="May">May</option>
													<option value="6" label="Jun">Jun</option>
													<option value="7" label="Jul">Jul</option>
													<option value="8" label="Aug">Aug</option>
													<option value="9" label="Sep">Sep</option>
													<option value="10" label="Oct">Oct</option>
													<option value="11" label="Nov">Nov</option>
													<option value="12" label="Dec">Dec</option>
												</select>
											</span>
												
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" onclick="getUserReportParameter1();">Report</button></a></label>
					</div>
					<br /><br /><span style="margin:17px;color:red;">*Project list of selected user, Only if timesheet data exist.</span>
					</div>
					</div>
					</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary" id="downloadMonthlyReportDiv" aria-labelledby="downloadMonthlyReport"
					style="width: 100%;display: none;" scrolling="auto">
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Generated Time:</strong><br /><span id="fileNameMonthlyReportTime" style="color:blue;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Timesheet Report:</strong><br /><span id="fileNameMonthlyReport" style="color:blue;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;"><input type='hidden' id='fileNameHidden' />
						<label><a href="monthlyFiles" class="getMonthlyReporthref" target="_blank" id="downloadMonthButton">
						<button type="button" class="btn btn-primary" style="margin-top:30px;">Download</button>
						</a></label>
						
					</div>
			</div>
		</div>
	</div>
	</div>	
	</div>	
	<div class="panel panel-primary" id="vieNotificationDiv" aria-labelledby="vieNotification"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Email Notification Setting </strong>
							<button style="border:none;background:#3e5871;;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('emailNotificationTT','popover7');" tabindex="0" data-toggle="popover7" data-trigger="focus" data-placement="bottom" data-html="true"></button>							
						</div>
						<div class="panel-body" id="emailNotificationDiv">
								<!-- <table id="tmsNotificationTbl" class="table table-bordered">
									<thead style="background-color:#30a5ff;color:white;">
										<tr>
											<th>Event Id</th>
											<th>Name</th>
											<th>Module</th>
											<th>Current Flag</th>
										</tr>
									</thead>
									<tbody id="tmsNotificationTblBody">
									</tbody>
								</table> -->
						</div>
	</div>
	</div>
	<div class="panel panel-primary" id="vieReportWeeklyDiv" aria-labelledby="viewReportWeekly"	style="width: 100%;display: none;" scrolling="auto">
						<!--<div class="row">
								<ol class="breadcrumb">
									<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
									<li class="active"></li>
								</ol>
							</div> -->
						<div class="panel panel-default">	
						<div class="panel-heading">
							<strong>Timesheet Weekly Status Reports </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('weeklyTSStatusReportTT','popover15');" tabindex="0" data-toggle="popover15" data-trigger="focus" data-placement="auto" data-html="true"></button>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:20px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInWeeklyReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Start Date</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepickerWeekly" class="form-control"/>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>End Date</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepickerWeekly" class="form-control"/>
											</span>
												
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Status</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="weeklyStatus">
													<option value="select" label="Select Status" selected>Select Status</option>
													<option value="Pending" label="Pending">Pending</option>
													<option value="Saved" label="Saved">Saved</option>
													<option value="Rejected" label="Rejected">Rejected</option>
													<option value="Approved" label="Approved">Approved</option>
												</select>
											</span>
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" onclick="getReportWeekly();">Generate Report</button></a></label>
					</div>
					
					</div>
					</div>
					</div>
					  
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-primary" id="downloadWeeklyReportDiv" aria-labelledby="downloadWeeklyReport"
					style="width: 100%;display: none;" scrolling="auto">
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Generated Time:</strong><br /><span id="fileNameWeeklyReportTime" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<strong>Timesheet Status Report:</strong><br /><span id="fileNameWeeklyReport" style="color:red;"></span>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="filesWeekly"><button type="button" class="btn btn-primary" style="margin-top:30px;">Download</button></a></label>
					</div>
			</div>
		</div>
	</div></div></div>
	<div class="modal fade" id="addFeedbackModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Feedback/Suggestion/Issue </h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">  
				          <label>Category</label>
				          <select id="feedbackType" class="form-control" style="margin-bottom: 10px">
				          		<option value="select" selected>Select Category</option>
				          		<option value="feedback">Feedback</option>
				          		<option value="suggestion">Suggestion</option>
				          		<option value="issue">Issue</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Select Module</label>
				          <select id="moduleType" class="form-control" style="margin-bottom: 10px">
				          		<option value="select" selected>Select Module</option>
				          		<option value="timesheet">Timesheet</option>
				          		<option value="expense">Reimbursement</option>
				          		<option value="leave">Leave</option>
				          		<option value="other">Others</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Description</label>
				          <textarea class="form-control" rows="5" id="feedBackDesc" maxlength="500" style="max-width:100%;margin-bottom: 10px" placeholder="Maximum length 500 character..."></textarea>
				        </div>
				        <input type="hidden" id="feedbackAttachmentId" value="" />
				        <div class="form-group">  
				          <form class="form-inline" method="POST" enctype="multipart/form-data" id="feedbackUploadForm" style="display:inline;">
								<input type="submit" class="btn btn-primary pull-right" title="Click to upload feedback attachment" value="Submit" onclick="uploadFeedbackAttachment();return false;" style="margin-right:5px;"/>
					 			<input type="file" name="file" onchange="valFeedbackUpload(this.value,id);" id="feedbackDocumentList" class="filestyle" style="display:inline;" data-buttonText="Upload Attachment"/>
					 		</form>
				         </div>
				         <br> 
				         <div>
				        	@Contact:<span style="margin:5px;color:red;">timesheet@supraits.com</span>
				        </div>	
				        </div>
				        <!-- <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" onclick="insertNewFeedback();">Send</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div> -->
				        </div>
				       </div>
	  </div>
	  <div class="panel panel-primary" id="viewApproveExpenseDiv" aria-labelledby="viewApproveExpenseDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Pending Reimbursement Request </strong>
							<input class="input-sm textInput form-control" style="width:30%;display:inline" id="globalFilterInputPendingExp" placeholder="Global search input..." onkeyup="filterFullTableByInput(this.value,'userExpenseItemTbl');">
						</div>
						<div class="panel-body">
							<div><span id="noDataAvailableIdExpense" style="color:red;font-weight:650;margin:15px;"></span></div>							
							<!-- <div class="table-responsive">
							<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
							<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Filter by Employee Name:</strong></label><br />
							<span>
							</span>
							</div>	
							</div>
							</div> -->
						</div>
					</div>
					</div>
			<div class="panel panel-primary" id="viewBucketExpenseDiv" aria-labelledby="viewBucketExpenseDiv"	style="width: 100%;display: none;" scrolling="auto">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Manage Buckets of Approved Reimbursement Request</strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
					<!-- <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;"> -->
					<div class="form-group">
						<input type="hidden" id="expenseFilterBucket" value="0"/>
						<table style="border:none;width:100%;" id="bucketCountTbl">
							<tbody id="bucketCountDiv"></tbody>
						</table>	        
					 </div>
						<div class="form-inline form-group" id="" style="margin-left:15px;">
							<label><strong>Filter:: Request Number:</strong></label>
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterBucketByRequestNumber" placeholder="Request Number" onkeyup="filterAnyTableByInput(this.value,'userBucketExpenseItemTbl','2');">
							</span>
							<label><strong>Username:</strong></label>
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterBucketByUsername" placeholder="Emp Name" onkeyup="filterAnyTableByInput(this.value,'userBucketExpenseItemTbl','3');">
							</span>
							<label><strong>Status:</strong></label>
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterBucketByStatus" placeholder="Status" onkeyup="filterAnyTableByInput(this.value,'userBucketExpenseItemTbl','7');">
							</span>
						</div>		
						<!-- <div class="form-group" style="margin-left:15px;">
							<label><strong>Filter By Bucket:</strong></label><br />
							<span>
								<select class="form-control" id="expenseFilterBucket" onchange="getBucketExpenseRequestList('Approved',this.value);">
								    <option value="0" label="All" selected/>
									<option value="Ready for payment" label="Ready for payment"/>
								    <option value="Document Verification Pending" label="Document Verification Pending" />
								    <option value="Document Verified" label="Document Verified" />
								    <option value="To be review" label="To be review" />
									<option value="Request Discarded" label="Request Discarded" />
									<option value="Request Completed" label="Request Completed" />
								</select>
							</span>	
						</div> -->
					<!-- </div> -->
					</div>
					</div>
			</div>
			</div>
			<div class="panel panel-primary" id="viewOwnExpenseDiv" aria-labelledby="viewOwnExpenseDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Employee Past Reimbursement Request </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('empPastRMSListTT29','popover29');" tabindex="0" data-toggle="popover29" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
							<label><strong>Choose Filter:</strong></label><br />
							<span>
								<select class="form-control" id="expenseFilterUserOwn" onchange="filterAnyTableByInput(this.value,'userOwnExpenseItemTbl','8');">
								    <option value="" label="Select" selected>Select</option>
								    <option value="Review Pending" label="Review Pending">Review Pending</option>
								    <option value="Review Failed" label="Review Failed">Review Failed</option>
									<option value="Saved as Draft" label="Saved">Saved</option>
									<option value="Approval Pending" label="Approval Pending">Approval Pending</option>
									<option value="VP Approval Pending" label="VP Approval Pending">VP Approval Pending</option>
									<option value="VP Approval Failed" label="VP Approval Failed">VP Approval Failed</option>
									<option value="Rejected" label="Rejected">Rejected</option>
									<option value="Approved" label="Approved">Approved</option>
									<option value="Withdraw" label="Withdraw">Withdraw</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Filter by Request Number:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterByReqNum" placeholder="Request Number" onkeyup="filterAnyTableByInput(this.value,'userOwnExpenseItemTbl','1');">
							</span>
						</div>	
					
					</div>
					</div>
					</div>
			</div>
			</div>
			<div id="userBucketExpenseItemDiv" aria-labelledby="userBucketExpenseItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<div><strong><span id="currentBucketName" style="color:red;margin-left:10px;"></span></strong></div>
					<table class="table table-bordered" id="userBucketExpenseItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">#</th>
								<th class="text-center"><input type='checkbox' id="checkBucketid" onclick = "selectAllBucketCheckBox('tab_logic_userBucketExpenseItemApp')" /></th>
								<th class="text-center">Request Number</th>
								<th class="text-center">User Name</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Claimed<br>(INR)</th>
								<th class="text-center">Approved<br>(INR)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Bucket Name</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userBucketExpenseItemApp">
						</tbody>
					</table>
					<div class="form-group" style="margin-left:15px;">
							<strong>Send To:</strong>
								<select class="form-control" id="expenseBucket" style="width:auto;display:inline;">
								    <option value="" label="Choose Bucket" selected/>
								</select>
							<button class="btn primary btn-sm" id="sendToBucketId" onclick="sendToBucket();return false;">Go</button>
							<button class="btn primary btn-sm pull-right" id="downloadBankSheetId" title="Click to download list" style="margin-right:10px;display:none;" onclick="downloadBucketReport();return false;">Download Sheet for Bank</button>
							<form class="form-inline pull-right" method="POST" enctype="multipart/form-data" id="bankFileUploadForm" style="display:none;">
							<button class="btn primary btn-sm" id="downloadBankRefNoSheetId" title="Click to download template" style="margin-right:10px;" onclick="downloadBankRefUpdateSheet();return false;">Download Bank Ref Sheet</button>
								<input type="submit" class="btn btn-primary pull-right" title="Click to upload Bank Ref Sheet" value="Upload" id="uploadBankSheetId" onclick="uploadBankRefUpdateSheet();return false;" style="margin-right:5px;"/>
					 			<input type="file" name="file" onchange="valBankRefSheetUpload(this.value,id);" id="rmsBankRefDocumentList" class="filestyle pull-right" style="display:inline;" data-buttonText="Upload Bank Ref Sheet"/>
					 		</form>
					</div>
			</div>
	       <div id="userExpenseItemDiv" aria-labelledby="userExpenseItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="userExpenseItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">User Name</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Claimed<br>(INR)</th>
								<th class="text-center">Approved<br>(INR)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userExpenseItemApp">
						</tbody>
					</table>
			</div>
			
			<div id="userOwnExpenseItemDiv" aria-labelledby="userOwnExpenseItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="userOwnExpenseItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Claimed<br>(INR)</th>
								<th class="text-center">Approved<br>(INR)</th>
								<th class="text-center">Reviewed By</th>
								<th class="text-center">Remark(Reviewer)</th>
								<th class="text-center">Remark(Approver)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userOwnExpenseItemApp">
						</tbody>
					</table>
			</div>  	
			<div class="modal fade" id="addExpRemarkModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Remark </h4>
				          <input type="hidden" id="remarkValueHiddenId" name="remarkValueHiddenId" value=''/>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Remark:</label>
				          <textarea class="input-md  textinput textInput form-control" id="idExpRmrk" maxlength="100" placeholder="Remarks...max 100 character" style="margin-bottom: 10px"></textarea>
				        </div>
				      <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="addExpRemark" onclick="captureExpRemark();">OK</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				   </div>
			</div>		
			<div class="panel panel-primary" id="viewManageUserAccessDiv" aria-labelledby="viewManageUserAccessDiv"	style="width: 100%;display: none;" scrolling="auto">
				<div class="panel panel-default">		
						<div class="panel-heading">
							<strong>Manage User Access </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('manageUserAccessTT','popover8');" tabindex="0" data-toggle="popover8" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Select Access Group:</strong></label>
							<select class="form-control" id="userGroupList" style="width:auto;display:inline;" onchange="getAccessFunctionList(this.value);">
							</select>
						</div>		
						<br />
						<div class="form-group" id="accessList" style="margin:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			</div>
			<div class="panel panel-primary" id="viewGroupUserAccessDiv" aria-labelledby="viewGroupUserAccessDiv"	style="width: 100%;display: none;" scrolling="auto">
				<div class="panel panel-default">		
						<div class="panel-heading">
							<strong>Manage User Group </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('manageUserGroupTT','popover9');" tabindex="0" data-toggle="popover9" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label>New Group Name:</label>
							<input type="text" class="form-control" id="newGroupName" maxlength="50" />
							<button class="btn-sm btn-primary" onclick="createGroupName();return false;">Create Group</button>
						</div>		
						<br />
						<div class="form-group" id="accessGroupList" style="margin:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			</div>
			<div class="panel panel-primary" id="viewRMSBucketDiv" aria-labelledby="viewRMSBucketDiv"	style="width: 100%;display: none;" scrolling="auto">
				<div class="panel panel-default">	
						<div class="panel-heading">
							<strong>Manage Reimbursement Bucket </strong>
						</div>
						<div class="panel-body">
					<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label>New Bucket Name:</label>
							<input type="text" class="form-control" id="newBucketName" maxlength="50" />
							<button class="btn-sm btn-primary" onclick="createBucketName();return false;">Create Bucket</button>
						</div>		
						<br />
						<div class="form-group" id="bucketList" style="margin:10px;width:100%">
						</div>
					</div>
					</div>
					</div>
			</div>
			</div>
			<div class="modal fade" id="addUsersToGroupModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Users to group:  <span id="id_groupName" style='color:red;'></span></h4>
				          <input type="hidden" id="id_groupId" />
				          <input type="hidden" id="id_addRemoveFlag" />
				        </div>
				        <div class="modal-body">
							<div class="form-group">
									    <label class="col-md-4 control-label" for="rolename">Users Email-Id</label>
									    <div id="checkboxSelectComboAddRemove"></div>
    									<input type ="hidden" id ="Hidden1"/>
							</div>
							
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-success" id="saveGrpUsers" onclick="addRemoveUsersFromGroup();return false;">Save</button>
						  <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
						</div>
						</div>
					   </div>
				</div>
				<div class="panel panel-primary" id="viewPastLeavesDiv" aria-labelledby="viewPastLeavesDiv"	style="width: 100%;display: none;" scrolling="auto">
				<div class="panel panel-default">			
						<div class="panel-heading">
							<strong>User Past/Pending Leave Request List </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('ownLeaveRequestListTT23','popover23');" tabindex="0" data-toggle="popover23" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
							<label><strong>Choose Filter:</strong></label><br />
							<span>
								<select class="form-control" id="leaveFilter" onchange="filterAnyTableByInput(this.value,'userOwnLeaveItemTbl','4');">
								    <option value="" label="Select" label="All">All</option>
								    <option value="Approval Pending" selected label="Approval Pending">Approval Pending</option>
								    <option value="Approved" label="Approved">Approved</option>
									<option value="Rejected" label="Rejected">Rejected</option>
									<option value="Cancel" label="Cancelled">Cancelled</option>
									<option value="Leave Reversed" label="Leave Reversed">Leave Reversed</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Filter by Request Number:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="expenseFilterByRequestNo" placeholder="Request Number" onkeyup="filterAnyTableByInput(this.value,'userOwnLeaveItemTbl','1');">
							</span>
						</div>
						<div class="form-group" id="" style="margin-left:15px;">	
							<br>
							<a href="#l" onclick="newLeaveRequest();" class="pull-right btn btn-success">Add New Leave</a>
						</div>							
					</div>
					</div>
					</div>
					</div>
			</div>
			<div id="userOwnLeaveItemDiv" aria-labelledby="userOwnLeaveItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="userOwnLeaveItemTbl" scrolling="auto">
						<thead style='background-color: #30a5ff;color:white;'>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Leave Days</th>
								<th class="text-center">Status</th>
								<th class="text-center">Remark(Approver)</th>
								<th class="text-center">Last Modified By</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userOwnLeaveItemApp">
						</tbody>
					</table>
			</div>
			<div class="panel panel-primary" id="viewPendingLeavesDiv" aria-labelledby="viewPendingLeavesDiv"	style="width: 100%;display: none;" scrolling="auto">
					<div class="panel panel-default">		
						<div class="panel-heading">
							<strong>Pending Leave Request List </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('leaveRequestListUnderUserTT24','popover24');" tabindex="0" data-toggle="popover24" data-trigger="focus" data-placement="bottom" data-html="true"></button>
							
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="timesheetStatusFilter" style="margin-left:15px;">
							<label><strong>Status:</strong></label>
							<span>
								<select class="form-control" id="pendingLeaveFilter" onchange="filterAnyTableByInput(this.value,'pendingLeaveItemTbl','5');">
								    <option value="" label="Select" label="All">All</option>
								    <option value="Approval Pending" selected label="Approval Pending">Approval Pending</option>
								    <option value="Approved" label="Approved">Approved</option>
									<option value="Rejected" label="Rejected">Rejected</option>
									<option value="Cancel" label="Cancelled">Cancelled</option>
									<option value="Leave Reversed" label="Leave Reversed">Leave Reversed</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Employee Name:</strong></label>
							<span>
							 <input class="input-md  textinput textInput form-control" id="pendingLeaveFilterByName" placeholder="Employee Name" onkeyup="filterAnyTableByInput(this.value,'pendingLeaveItemTbl','2');">
							</span>
						</div>	
					</div>
					</div>
					</div>
					</div>
			</div>
			<div id="pendingLeaveItemDiv" aria-labelledby="pendingLeaveItems"
					style="margin-top: 10px; width: 100%;display: none;" scrolling="auto">
					<table class="table table-bordered" id="pendingLeaveItemTbl" scrolling="auto">
						<thead style='background-color: #30a5ff;color:white;'>
							<tr>
								<th>#</th>
								<th class="text-center">Request Number</th>
								<th class="text-center">Employee Name</th>
								<th class="text-center">Created On</th>
								<th class="text-center">Leave Days</th>
								<th class="text-center">Status</th>
								<th class="text-center">LastModifiedOn</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_pendingLeaveItemApp">
						</tbody>
					</table>
			</div>
			<div class="panel panel-primary" id="viewLeaveCategoryDiv" aria-labelledby="viewLeaveCategoryDiv"	style="width: 100%;display: none;" scrolling="auto">
				<div class="panel panel-default">		
						<div class="panel-heading">
							<strong>Manage Leave Type </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('manageLeaveTypeTT25','popover25');" tabindex="0" data-toggle="popover25" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
				<div class="table-responsive" style="padding-top:15px;">
					<div class="form-inline" style="margin-left:10px;">
						<div class="form-group">
							<input type="text" class="input-md  textinput textInput form-control" id="newLeaveName" placeholder="New Leave Name(Short)" maxlength="50" />
						</div>
						<div class="form-group">	
							<input type="text" class="input-md  textinput textInput form-control" id="newLeaveDesc" placeholder="Leave Description" maxlength="100" />
						</div>
						<div class="form-group">
							<span>
								<select class="form-control" id="newLeavePolicyGroup" onchange="fetchCumulativeLeaveGroup(this.value,'newLeaveCumulativeGroup');return false;">
									<option value="" label="HR Policy Group">HR Policy Group</option>
								</select>
							</span>
						</div>
						<div class="form-group">	
							<span>
								<select class="form-control" id="newLeaveGroup">
									 <option value="" label="Leave Group">Leave Group</option>
								    <option value="Unpaid" label="Unpaid">Unpaid</option>
									<option value="Paid" label="Paid">Pai</option>
								</select>
							</span>
						</div>	
						<div class="form-group">	
							<span>
								<select class="form-control" id="newLeaveCumulativeGroup">
									 <option value="" label="Cumulative Group">Cumulative Group</option>
								</select>
							</span>
						</div>	
					</div>
					<div class="form-inline" style="margin-left:10px;margin-top:5px;">	
						<div class="form-group">	
							<input type="text" class="input-md  textinput textInput form-control" id="maxDaysPerRequest" placeholder="Max day per request" maxlength="2" />
						</div>	
						<div class="form-group">	
							<input type="text" class="input-md  textinput textInput form-control" id="applyDaysBefore" placeholder="Apply min days before" maxlength="2" />
						</div>	
						<div class="form-group">	
							<label class="checkbox-inline"><input type="checkbox" value="" id="applyByAdmin">Admin</label>
							<label class="checkbox-inline"><input type="checkbox" value="" id="applyByManager">Manager</label>
							<label class="checkbox-inline"><input type="checkbox" value="" id="applyByHR">HR</label>
						</div>	
						<div class="form-group">	
							<button class="btn-sm btn-primary pull-right" onclick="createLeaveName();return false;">Create</button>
						</div>		
					</div>
					</div>
				<div class="form-group" id="leaveList" style="margin:10px;width:100%"></div>
			</div>
			</div>
			<div class="panel panel-primary" id="viewLeaveQuarterDiv" aria-labelledby="viewLeaveQuarterDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Quarterly Leave Update Sheet :: ${sessionScope.loggedInUserPolicyGroup} </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('leaveBalanceUpdateTT26','popover26');" tabindex="0" data-toggle="popover26" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
						<div class="panel-body">
					<div class=" inline alert alert-success" style="display:none;" id="successFileUploadLeave">
						  <strong>Success!</strong> User leave balance updated.
					</div>	
					 <div class="form-inline panel panel-primary" style="margin-left:0px;padding-bottom:0px;box-shadow:none;">
					 <div class='col-lg-6'>
					 	<span style="color:red;font-size:12px;"><strong>Important!</strong></span><br>
					 	<span style="font-size:10px;">Uploaded file must be in format xlsx.</span><br>
					 	<span style="font-size:10px;">Uploaded file name must be <strong>LeaveBalanceExcel.xlsx</strong> only.</span><br>
					 	<span style="font-size:10px;">Update and rename the downloaded file and do not change template format.</span><br>
					 	<span style="font-size:10px;">All cell format type must be text only.</span><br>
					 </div>
					 <div class='col-lg-6'>
					 	<form method="POST" enctype="multipart/form-data" id="fileUploadForm" style="display:inline;">
					 	<div class="form-group">
						 	<a href="downloadLeaveBalance?policyGroup=SUPRA-Noida" id="noidaSheetId" class="btn btn-primary pull-right"style="display:inline;">Download Sheet</a>
						 	<a href="downloadLeaveBalance?policyGroup=SUPRA-Canada" id="canadaSheetId" class="btn btn-primary pull-right"style="display:inline;">Download Sheet</a>
					 	</div>
					 	<div class="form-group">
							<input type="hidden" value="SUPRA-Noida" name="policyGroupName" id="policyGroupName"/>
					 		<input type="file" name="file" onchange="valBalSheetUpload(this.value,id);" id="leaveBalDocumentList" class="filestyle pull-right" style="display:inline;" data-buttonText="Upload Sheet"/>
					 	</div>
					 	<div class="form-group">	
					 		<input type="submit" class="btn btn-primary pull-right" value="Upload" id="btnSubmit" onclick="uploadBalanceSheet('leaveBalDocumentList');return false;"/>
					 	</div>	
					 	</form>
					 	</div>
					</div>
					<div class="form-group" id="leaveQuarterList" style="margin-top:10px;width:100%">
					</div>
					</div>
			</div>
			</div>
			<div class="modal fade" id="addLeaveRemarkModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Add Remark </h4>
				          <input type="hidden" id="leaveremarkValueHiddenId" name="leaveremarkValueHiddenId" value="" />
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <label>Remark:</label>
				          <textarea class="input-md  textinput textInput form-control" id="idLeaveRmrk" maxlength="100" placeholder="Remarks...max 100 character" style="margin-bottom: 10px"></textarea>
				        </div>
				      <div class="modal-footer">
	    			      <button type="button" class="btn btn-success" id="addLeaveRemark" onclick="captureLeaveRemark();">OK</button>
				          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				        </div>
				        </div>
				       </div>
				   </div>
			</div>
			<div class="modal fade" id="rmsActionHistoryModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header" style="background-color:#30a5ff;border-radius:4%;">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          	<span id="tempReqNoForModalHeader" style="color:#fff;font-weight:600;font-size:medium;"></span>
				        </div>
				        <div class="modal-body">
				     <table id="rmsActionHistoryTbl" class="table table-bordered">
		          			<thead>
		          				<tr>
				          			<th class="text-center">Status</th>
									<th class="text-center">From</th>
									<th class="text-center">Date</th>
									<th class="text-center">Remark</th>
									<th class="text-center">Send To</th>
								</tr>
		          			</thead>
		          		<tbody id="tab_logic_rmsActionHistory"></tbody>
		        	</table>
				        </div>
				      </div>
				     </div>
				  </div>
				<div class="modal fade" id="dragDropUserModal" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header"">
				          <div class="modal-header" style="background-color:#30a5ff;">
				          <!-- <button type="button" class="btn" data-dismiss="modal">&times;</button> -->
				          <a href='#!' title='Reset User List' style='color:#fff;' onclick='resetUpdateGroupUserModal();return false;'><span class='glyphicon glyphicon-refresh pull-right' style='margin-right: 10px;'></span></a>
				          	<span style="color:#fff;font-weight:600;font-size:medium;">Add/Remove Users from Access Group</span>
				          	</div>
				          	<input type="hidden" id="id_groupId1" value="" />
				        </div>
				        <div class="modal-body">
				        <span id="id_groupName1" style="color:black;font-weight:600;font-size:medium;"></span><br>
				        <span style="color:red;font-size:12px;">*Right panel displays existing users in group.</span><br>
				        <span style="color:red;font-size:12px;">*Left panel displays active users in system who are not in this group.</span><br>
				        <select multiple="multiple" name="duallistbox_demo1" id="duallistbox_demo11">
							<option value="option3" selected="selected">Option 3</option>
							<option value="option4">Option 4</option>
						</select>
				        </div>
				        <div style="margin-bottom:7px;">
				        	<button type="button" class="btn btn-sm btn-success" style="margin-left:44%;" onclick="addRemoveUsersFromGroup();return false;">Save</button>
				        	<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				     </div>
				  </div>  
			<div id="searchResultNullPass1" style="margin:10px;display:none;"><span style="color:red">No Result found in database.</span></div>
			<div class="modal fade" id="assignUserModal1" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header"">
				          <div class="modal-header" style="background-color:#30a5ff;">
				          <button type="button" class="btn pull-right" data-dismiss="modal">&times;</button>
				          <a href='#!' class="btn pull-right" title='Reset User List' style='color:#fff;' onclick='resetUpdateProjectUserModal();return false;'><span class='glyphicon glyphicon-refresh pull-right' style='margin-right: 10px;'></span></a>
				          	<span style="color:#fff;font-weight:600;font-size:medium;">Allocate/Deallocate Users from Project</span>
				          	</div>
				          	<input type="hidden" id="id_projectId1" value="" />
				        </div>
				        <div class="modal-body">
				        <span id="id_projectName1" style="color:black;font-weight:600;font-size:medium;"></span><br>
				        <span style="color:red;font-size:12px;">*Right panel displays existing users in project.</span><br>
				        <span style="color:red;font-size:12px;">*Left panel displays active users in system who are not in this project.</span><br>
				        <select multiple="multiple" name="duallistbox_demo5" id="duallistbox_demo55">
							<option value="option3" selected="selected">Option 3</option>
							<option value="option4">Option 4</option>
						</select>
				        </div>
				        <div style="margin-bottom:7px;">
				        	<button type="button" class="btn btn-sm btn-success" style="margin-left:44%;" onclick="addRemoveUsersFromProject1();return false;">Save</button>
				        	<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
				        </div>
				      </div>
				     </div>
				  </div>
				<div class="panel panel-primary" id="vieLeaveReportDiv" aria-labelledby="viewLeaveReport" style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Generate Leave Report </strong>
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('generateLeaveReportTT27','popover27');" tabindex="0" data-toggle="popover27" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>HR Policy Group*</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="hrPolicyGroup" onchange="populateEmployee(this.value);return false;">
													<option value="" label="HR Policy Group">HR Policy Group</option>
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>Select Employee:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="userListForLeaveReport" onchange="getUsersPolicy();">
													<option value="" label="Select Employee" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>Start Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker-leave" class="form-control" style="width:100px;" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>End Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker-leave" class="form-control" style="width:100px;" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
											<label><strong>Leave Status</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="leaveStatusList">
													<option value="" label="Select Status" selected>Select Status</option>
													<option value="Approved" label="Approved">Approved</option>
													<option value="Approval Pending" label="Approval Pending">Approval Pending</option>
													<option value="Rejected" label="Rejected">Rejected</option>
													<option value="Deleted" label="Deleted">Deleted</option>
												</select>
											</span>
									</div>
								<div class="form-group" style="margin-left:15px;">
									<a href="#l" type="button" class="btn btn-primary btn-info btn-md" style="margin-top:30px;" onclick="generateLeaveReport();">View</a>
								</div>
					<br />
					<br />
					</div>
					</div>
					<div id="vieLeaveReportDataDiv"></div>
					</div>
					</div>
				</div>
				<div class="panel panel-primary" id="vieReimbursementReportDiv" aria-labelledby="viewReimbursementReport"	style="width: 100%;display: none;" scrolling="auto">
					<div class="panel panel-default">	
						<div class="panel-heading">
							<strong>Reimbursement Report </strong>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top:2px;">
											<label><strong>Select Project:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="prjctListForReimbursementReport">
													<option value="" label="Select Project" selected />
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-top:2px;">
											<label><strong>Select Employee:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="userListForReimbursementReport">
													<option value="" label="Select Employee" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top:2px;">
											<label><strong>Start Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker-expense" class="form-control" style="width:100px;" placehoder="Strat Date" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top:2px;">
											<label><strong>End Date*</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker-expense" class="form-control" style="width:100px;" onkeypress="return false;"/>
											</span>
									</div>
									<div class="form-group" style="margin-top:2px;">
											<label><strong>Status:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="expenseStatusList">
													<option value="select" label="Select Status" selected />
												</select>
											</span>
									</div>
									<div class="form-group" style="margin-top:2px;">
											<label><strong>Select Bucket:</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
											<select class="form-control" id="bucketListForReimbursementReport">
													<option value="" label="Select Employee" selected />
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top:2px;">
										<label><a href="#!" class="btn btn-primary" style="margin-top:30px;" onclick="generateExpenseReport();">Go</a></label>
									</div>
									<br />
									<span style="margin-left:10px;color:red;font-size:12px;">*Please note except<b> Deafult bucket</b> all buckets have only <b>Approved Status</b> expense request.</span>
					</div>
					</div>
					<div id="vieExpReportDataDiv"></div>
					</div>
				</div></div>
				<div class="modal fade" id="rmsDetailModal" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Expense Details</h4>
				        </div>
				        	<div class="modal-body" id="rmsModalDiv">
				        </div>
				      </div>
				     </div>
				  </div>
				  <div class="panel panel-primary" id="viewAddAssetDiv" aria-labelledby="viewAddAsset" style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Add New Asset</strong>
						</div>
						<div class="panel-body" id="addAssetDiv">
								<div class="form-group row">
                       		 	<label  class="control-label col-md-2  requiredField">Asset Category:<span class="asteriskField">*</span> </label>
	                       		 	<select id="addCategoryListForAsset" class="form-control col-md-2" style="width:66.66%;margin-bottom:5px;" onchange="populateAssetTag(this.value,'addTagForAsset',this.id)"></select>
	                       		 	<button class="glyphicon glyphicon-plus" title="Add New Category" id="addAssetCategory" onclick="addNewParam(this.id,'masterAssetCatogory');" style="background-color:#fff;margin-top:5px;border:none;"></button>
                       			 </div>
                       			 	
                       			 <div class="form-group row">
                       		 		<label  class="control-label col-md-2  requiredField">Asset Type:<span class="asteriskField">*</span> </label>
	                       		 	<select id="addCategoryListForAsset" class="form-control col-md-2" style="width:66.66%;margin-bottom:5px;">
	                       		 		<option value="Company Owned">Company Owned</option>
	                       		 		<option value="Rented">Rented</option>
	                       		 		<option value="Other">Other</option>
	                       		 	</select>
	                       		 	<button class="glyphicon glyphicon-plus" title="Add New Type" id="addAssetType" onclick="addNewParam(this.id,'masterAssetType');" style="background-color:#fff;margin-top:5px;border:none;"></button>
                       			 </div>
                       		 <div class="form-group row">
                       		 	<label  class="control-label col-md-2  requiredField">Model No<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="addModelForAsset" class="form-control col-md-2" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group row">
                       		 	<label  class="control-label col-md-2  requiredField">Vendor/Manufacture:<span class="asteriskField">*</span> </label>
                       		 	<select id="addCategoryListForAsset" class="form-control col-md-2" style="width:66.66%;margin-bottom:5px;">
	                       		 		<option value="">Select Vendor</option>
	                       		 	</select>
	                       		 	<button class="glyphicon glyphicon-plus" title="Add Vendor" onclick="addNewParam(this.id,'masterAssetVendor');" id="addNewVendorBtn" style="background-color:#fff;margin-top:5px;border:none;"></button>
                       		 </div>
                       		 <div class="form-group row">
                       		 	<label  class="control-label col-md-2  requiredField">Serial Number/Service Tag<span class="asteriskField">*</span>:</label>
                       		 	<input type="text" id="addSrNoForAsset" class="form-control col-md-2" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group row">
                       		 	<label  class="control-label col-md-2  requiredField">Asset Status:<span class="asteriskField">*</span> </label>
                       		 	<select id="addStatusForAsset" class="form-control col-md-2" style="width:66.66%;margin-bottom:5px;">
                       		 		<option value="Select Status">Select Status</option>
                       		 	</select>
                       		 	<button class="glyphicon glyphicon-plus" title="Add New Status" id="addMasterAssetStatus" onclick="addNewParam(this.id,'masterAssetCatogory');" style="background-color:#fff;margin-top:5px;border:none;"></button>
                       		 </div>
                       		 <div class="form-group row">
                       		 	<label  class="control-label col-md-2  requiredField">Purchase Date:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="assetPurchaseDate" class="form-control col-md-2 input-sm" style="width:15%;margin-bottom:5px;" />
                       		 	<a href="#l" class="btn btn-md btn-info" title="Additional Deatils" onclick="addAdditionalDetail();" style="margin-left:3px;">Additional Configuration Detail</a>
                       		 </div>
                       		 <div class="form-group row">
								<a id='addAssetAllocation' class="btn btn-success pull-left" style="margin-left:10px;" onclick="addNewAssetInSystem();">Add</a>										                       		 	
                       		 </div>
						</div>		
						</div></div>
				  <div class="panel panel-primary" id="viewAllocateAssetDiv" aria-labelledby="viewAllocateAsset" style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Asset Allocation Form </strong>
						</div>
						<div class="panel-body" id="allocationAssetDiv">
								<div class="panel-group" style="background:gray;">
									<label class="control-label col-md-4"> Pending IT Request:
									</label>
		                            <div class="controls col-md-8" style="margin-bottom: 10px">
			                            <select class="form-control" data-live-search="true" id="idApprovedAssetReq" onchange="populateITReqDetail(this.value);" style="width:709px;margin-left:-15px;">
											<option value="" label="Select Pending IT Request" selected>Select Pending IT Request</option>
											<option>Request Id 1</option>
											<option>Request Id 2</option>
										</select>
		                            </div>
                       		 	</div>
                       		 	
								<div class="form-group required">
		                            <label class="control-label col-md-4 requiredField"> Employee Policy Group:<span class="asteriskField">*</span> </label>
		                            <div class="controls col-md-8 "  style="margin-bottom: 10px">
		                            <select class="form-control" data-live-search="true" id="id_select_type_policy" onchange="getEmpListBasedOnPolicyGroup(this.value);" style="width:709px;margin-left:-15px;">
										<option value="" label="HR Policy Group">Select HR Policy Group</option>
									</select>
		                                <!-- <label class="radio-inline">
		                                	<input type="radio" checked="checked" name="id_select_type_policy" id="id_select_type_policy" value="SUPRA-Noida"  style="margin-bottom: 10px" >SUPRA-Noida</label>
		                                <label class="radio-inline"> 
		                                	<input type="radio" name="id_select_type_policy" id="id_select_type_policy" value="SUPRA-Canada" style="margin-bottom: 10px" >SUPRA-Canada </label> -->
		                            </div>
                       		 	</div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Select Employee:<span class="asteriskField">*</span> </label>
                       		 	<select id="userListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 		<option value="">Select Employee</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Category:<span class="asteriskField">*</span> </label>
                       		 	<select id="categoryListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onchange="populateAssetTag(this.value,'tagListForAsset',this.id)">
                       		 		<option value="">Select Category</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Type<span class="asteriskField">*</span> </label>
                       		 	<select id="tagListForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onchange="populateAssetIds(this.value,'idListForAsset')">
                       		 		<option value="">Select Type</option>
                       		 	</select>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Description:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="descForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4">Asset Id List:<span class="asteriskField">*</span> </label>
                       		 	<select id="idListForAsset" class="form-control col-md-2" style="margin-bottom:5px;width:50%;" onchange="populateAssetDetails(this.value)">
                       		 		<option value="">Select Asset Id</option>
                       		 	</select>
                       		 	<button style="border:none;background:#fff;padding-top:7px;padding-left:5px;" class='glyphicon glyphicon-search' onclick="fetchAvailableAsset();"></button>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Model No:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="nameForAsset" readonly="true" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Serial Number/Service Tag<span class="asteriskField">*</span>:</label>
                       		 	<input type="text" id="srNoForAsset" readonly="true" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Asset Status:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="statusForAsset" readonly="true" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;">
                       		 </div>
                       		 <div class="form-group" id="" style="margin-left:34%;">
									<button class="btn btn-primary btn-sm" onclick="addConfigurationToAsset();">Add Configuration</button>						
							</div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Allocation Start Date:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="allocationStartDateForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onkeypress="return false;">
                       		 <br>
                       		 </div>
                       		 <div class="form-group required">
                       		 	<label  class="control-label col-md-4  requiredField">Allocation End Date:<span class="asteriskField">*</span> </label>
                       		 	<input type="text" id="allocationEndDateForAsset" class="form-control col-md-4" style="width:66.66%;margin-bottom:5px;" onkeypress="return false;">
                       		 <br>
                       		 </div>
                       		 <div class="form-group required">
								<a id='assetAllocation' class="pull-right btn btn-primary" style="margin-right:10px;" onclick="allocateAssetToUser();">Submit</a>										                       		 	
                       		 </div>
						</div>
					</div>
					</div>
		<div class="panel panel-primary" id="viewTrackAssetDiv" aria-labelledby="viewTrackAssetDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Track Asset Allocation</strong>
			</div>
			 <div class="panel-body">
				<div class="table-responsive">
					<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Asset Status:</strong></label><br />
							<span>
								<select class="form-control" id="assetStatusFilter" onchange="filterAnyTableByInput(this.value,'userAssetItemTbl','3');">
								    <option value="" label="Select" selected>Select Status</option>
								</select>
							</span>	
						</div>
						<!-- <div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Asset Category:</strong></label><br />
							<span>
								<select class="form-control" id="assetCategoryFilter" onchange="filterAnyTableByInput(this.value,'userAssetItemTbl','6');">
								    <option value="" label="Select" selected>Select Category</option>
								</select>
							</span>	
						</div> -->
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Asset Name:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="assetFilterByAssetName" placeholder="Asset Name" onkeyup="filterAnyTableByInput(this.value,'userAssetItemTbl','2');">
							</span>
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Employee Name:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="assetFilterByEmpName" placeholder="Employee Name" onkeyup="filterAnyTableByInput(this.value,'userAssetItemTbl','6');">
							</span>
						</div>	
					</div>
				</div>
				<table class="table table-bordered" id="userAssetItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>#</th>
								<th class="text-center">Asset Id</th>
								<th class="text-center">Asset Name</th>
								<th class="text-center">Status</th>
								<th class="text-center">Start Date</th>
								<th class="text-center">End Date</th>
								<th class="text-center">Allocated To</th>
								<th class="text-center">Allocated By</th>
								<th class="text-center">Allocated On</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userAssetItemApp">
						</tbody>
				</table>
			  </div>
		</div>
		</div>
		<div class="panel panel-primary" id="viewUploadAttendanceDiv" aria-labelledby="viewUploadAttendanceDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Upload Attendance Data</strong>
			</div>
			 <div class="panel-body">
			 	<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:0px;padding-bottom:0px;box-shadow:none;">
					 	<div>
					 	<span style="color:red;font-size:12px;"><strong>Important!</strong></span><br>
					 	<span style="font-size:10px;">Uploaded file must be in format xlsx.</span><br>
					 	<span style="font-size:10px;">Uploaded file name must be <strong>UploadAttendance.xlsx</strong> only.</span><br>
					 	<span style="font-size:10px;">Update and rename the downloaded file and do not change template format.</span><br>
					 	<span style="font-size:10px;">All cell format type must be text only.</span><br>
					 	<br>
					 	</div>
					 	<div>
					 	<a href="downloadAttendanceTemplate" id="attendanceSheetId" class="btn btn-primary"style="display:inline;">Download Sheet</a>
					 	<form method="POST" enctype="multipart/form-data" id="attenFileUploadForm" style="display:inline;">
							<input type="submit" class="btn btn-primary pull-right" value="Upload" id="btnAttenSubmit" onclick="uploadAttendanceSheet1();return false;"/>
					 		<input type="file" name="file" onchange="valAttendanceSheetUpload(this.value,id);" id="attendanceDocumentList" class="filestyle pull-right" style="display:inline;" data-buttonText="Upload Sheet"/>
					 	</form>
					 	</div>
						<div class="form-group" id="attendanceSheetList" style="margin-top:10px;width:100%">
						</div>
					</div>
					</div>			
			 </div>
		</div>	 
		</div>
		<div class="panel panel-primary" id="viewUserAttendanceDiv" aria-labelledby="viewUserAttendanceDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Attendance Data</strong>
				<span>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('viewAttendanceTT','popover11');" tabindex="0" data-toggle="popover11" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				</span>
			</div>
			 <div class="panel-body">
			 <div class="table-responsive">
				<div class="form-inline panel panel-primary" style="margin-left:10px;">
				 		<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Start Date</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker-attendance" class="form-control" onkeypress="return false;" style="width:100px;"/>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>End Date</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker-attendance" class="form-control" onkeypress="return false;" style="width:100px;"/>
											</span>
												
						</div>
						<div class="form-group" style="margin-top: 35px;margin-left:5px;">					
											<span style="padding-left: 0px; margin-top: 5px;">
												<a id='pastAttendanceHistory' class="pull-right btn btn-primary" style="margin-right:10px;" onclick="showUserAttendanceHistory();">Go</a>
											</span>
												
						</div>
					</div>
					<table class="table table-bordered" id="userAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">Date</th>
								<th class="text-center">Shift Time</th>
								<th class="text-center">In Time</th>
								<th class="text-center">Out Time</th>
								<th class="text-center">Punched HH</th>
								<th class="text-center">Def/Extra HH</th>
								<th class="text-center">Status</th>
							</tr>
						</thead>
						<tbody id="tab_logic_userAttenItemApp">
						</tbody>
				</table>
				</div>		
			 </div>
		</div>	 
		</div>
		<div class="panel panel-primary" id="viewUserMOAFDiv" aria-labelledby="viewUserMOAFDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Fill Missed Out Attendance Form</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('fillMOAFTT','popover14');" tabindex="0" data-toggle="popover14" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
			 <div class="panel-body" style="padding:5px;">
			 	<div class="table-responsive">
					<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Category:</strong></label><br />
							<span>
								<select class="form-control" id="moafCategory">
								    <option value="MOAF" selected>Missed Punch</option>
								    <option value="Left Early">Left Early</option>
								    <option value="Onsite">Onsite</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Fill Date:</strong></label><br />
							<span>
								<input class="input-md  textinput form-control" id="moafDateId" placeholder="Missed Punch Date" onkeypress="return false;">
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Reason:</strong></label><br />
							<input id="moafPurpose" type="text" class="form-control input-md" maxlength="100" style="min-width:500px;">	
						</div>
						<br>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Select In/Out:</strong></label><br />
							<span>
								<select class="form-control input-sm" id="attendanceDirection" onchange="inOutSelect(this.value);">
								    <option value="Both" selected>Both</option>
								    <option value="In">In Time</option>
								    <option value="Out">Out Time</option>
								</select>
							</span>	
						</div>
						 <div class="input-group bootstrap-timepicker timepicker" style="margin-left:43px;width:190px;">
						 	<label><strong>In Time:</strong></label><br />
					            <input id="timepicker2" type="text" class="form-control input-sm" style="width:50%;">
					            <span class="input-group-addon" style="border:none;padding-top:70px;padding-left:10px;background:none;display:inline;">
					                <i class="glyphicon glyphicon-time" style="border:none;padding-top:7px;"></i>
					            </span>
					     </div>
					     <div class="input-group bootstrap-timepicker timepicker" style="width:190px;">
						 	<label><strong>Out Time:</strong></label><br />
					            <input id="timepicker3" type="text" class="form-control input-sm" style="width:50%;">
					            <span class="input-group-addon" style="border:none;padding-left:10px;background:none;display:inline;">
					                <i class="glyphicon glyphicon-time" style="border:none;padding-top:7px;"></i>
					            </span>
					     </div>
						<div class="form-group" id="" style="margin-left:15px;margin-top:30px;">
							<span>
								<a href="#!" class="btn btn-primary btn sm" style="margin-right:10px;" onclick="submitMOAF();return false;">Submit</a>
							</span>	
						</div>
					</div>
				</div>
				<div class="panel-heading">
					<strong>MOAF History:</strong>
				</div>
				<div id="userOwnMOAFData"></div>		
			 </div>
		</div>
		</div>
		<div class="panel panel-primary" id="viewApproveUserMOAFDiv" aria-labelledby="viewApproveMOAFDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Approve Employees Attendance</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('approveEmployeesAttendanceTT','popover16');" tabindex="0" data-toggle="popover16" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
			 <div class="panel-body">
			 	<div id="userPendingMOAF">
			 	</div>
			 </div>
		</div>
		</div>
		<div class="panel panel-primary" id="viewAttendanceReportDiv" aria-labelledby="viewAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Employees Attendance Report</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('employeesAttendanceReportTT','popover17');" tabindex="0" data-toggle="popover17" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
													<label><strong>Start Date</strong></label> <br />
													<span style="padding-left: 0px; margin-top: 5px;">
														<input type="text" id="from-date-User-Atten" class="form-control" onkeypress="return false;" />
													</span>
											</div>
											<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
																<label><strong>End Date</strong></label><br />
																<span style="padding-left: 0px; margin-top: 5px;">
																	<input type="text" id="to-date-User-Atten" class="form-control" onkeypress="return false;"/>
																</span>
																	
											</div>
										 <div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Project Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												 <select class="form-control" id="projectListForAttendence" onchange="getAllUserForReport('true','usrListInAttendenceReport',this.id);"> 
														<option value="select" label="All Project" selected />
												</select>
											</span>
										</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInAttendenceReport">
													<option value="" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="getUsersAttendenceReport();">Go</button></a></label>
					</div>
					</div>
					</div>
					<div id="userAttnDataDiv" style="display:none;">
						<a href="downloadUserAttendenceRepo" id="attnRprtId" class="btn btn-primary pull-right" style="display:inline;margin-right:67px;margin-bottom:5px;">Download</a>
					<table class="table table-bordered" id="otherUserAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="">Date</th>
								<th class="">Username</th>
								<th class="">Emp Name</th>
								<th class="">Shift Time</th>
								<th class="">In Time</th>
								<th class="">Out Time</th>
								<th class="">Punched HH</th>
								<th class="">Def/Extra HH</th>
								<th class="">Status</th>
							</tr>
						</thead>
						<tbody id="tab_logic_otherUserAttenItemApp">
						</tbody>
					</table>
					</div>
		</div>
		</div>
		<div class="panel panel-primary" id="viewWeeklyAttendanceReportDiv" aria-labelledby="viewWeeklyAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Employees Weekly Attendance Report</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('empWeeklyAttnReportTT28','popover28');" tabindex="0" data-toggle="popover28" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
													<label><strong>Start Date(Sun)</strong></label> <br />
													<span style="padding-left: 0px; margin-top: 5px;">
														<input type="text" id="from-date-User-Atten-Week" class="form-control" onkeypress="return false;" />
													</span>
											</div>
											<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
																<label><strong>End Date(Sat)</strong></label><br />
																<span style="padding-left: 0px; margin-top: 5px;">
																	<input type="text" id="to-date-User-Atten-Week" class="form-control" onkeypress="return false;"/>
																</span>
																	
											</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInWeeklyAttendenceReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="getUsersWeeklyAttendenceReport();">Go</button></a></label>
					</div>
					</div>
					</div>
					<div id="userWeeklyAttnDataDiv" style="display:none;">
					<a href="downloadUserWeeklyAttendenceRepo" id="attnWeeklyRprtId" class="btn btn-primary pull-right" style="display:inline;margin-right:67px;margin-bottom:5px;">Download</a>
					<table class="table table-bordered" id="otherUserWeeklyAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="">SrNo</th>
								<th class="">Week Interval</th>
								<th class="">Username</th>
								<th class="">Emp Name</th>
								<th class="">Working HH</th>
								<th class="">Punched HH</th>
								<th class="">Def/Extra HH</th>
							</tr>
						</thead>
						<tbody id="tab_logic_otherUserWeeklyAttenItemApp">
						</tbody>
					</table>
					</div>
		</div>
		</div>
		<div class="panel panel-primary" id="viewYearlyAttendanceReportDiv" aria-labelledby="viewYearlyAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Employees Yearly Attendance Report</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInYearlyAttendenceReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Year</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="yearListInYearAttendenceReport">
													<option value="2019" label="2019" >2019</option>
													<option value="2018" label="2018" selected>2018</option>
													<option value="2017" label="2017">2017</option>
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
											<label><a href="#"><button type="button"
														class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="viewUserYearAttnRequest();">Go</button></a></label>
					</div>
					</div>
					</div>
				</div>
				</div>
				<div class="modal fade" id="leaveDetailModal" role="dialog">
				    <div class="modal-dialog modal-sm">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">User Leave Balance</h4>
				        </div>
				        <div class="modal-body panel panel-inline" id="leaveModalDiv">
				        </div>
				      </div>
				     </div>
				  </div>
				  <div class="modal fade" id="leaveBalHistoryModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">User Leave Balance History</h4>
				        </div>
				        <div class="modal-body panel panel-inline" style="height:400px;overflow-y:scroll;">
							<div class="form-group">
								<label>Employee Name::</label><span id="empBalName"></span>
							</div>
							<div id="leaveBalDiv"></div>				        	
				        </div>
				      </div>
				     </div>
				  </div>
				  <div class="modal fade" id="leaveMeetingModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Meeting Request/Mail Intimation ::<strong id="reqNumForMeeting"></strong></h4>
				        </div>
				        <div class="modal-body" id="leaveMeetingModalDiv" style="max-height: calc(100vh - 225px);overflow-y:scroll;">
				        	<input type="hidden" value="" id="reqNumForMeetingHidden" />
				        	 <div class="tabbable-panel">
						        <ul class="nav nav-tabs" style="height:30px;">
						        	<li class="active"><a href="#tab1" style="padding:5px;height:30px;" data-toggle="tab"><b>Email</b></a></li>
						        	<li><a href="#tab2" style="padding:5px;height:30px;" data-toggle="tab"><b>Meeting</b></a></li>
						        </ul>
						        <div class="tab-content">
						        <div class="tab-pane active" id="tab1">
						        	<span style="font-size:12px;color:red;">Write recipients's email comma seperated.</span>
						            <div class="form-group" style="margin-top: 5px;margin-left:5px;">
										<label><strong>To*</strong></label>
										<input type="text" class="form-control" placeholder="Type here...." id="recipientsList" onKeyUp="getUserListForMeeting(this.value,this.id,'selectedMail','listForMail');return false;"/>
										<div style="position:fixed;background-color:#eaecef;z-index:10;width:94%" id="listForMail"></div>
										<textarea id="selectedMail" style="display:none;" class="form-control" value="" ></textarea>
									</div>
									<div class="form-group" style="margin-top: 5px;margin-left:5px;">
										<label><strong>CC</strong></label>
										<input type="text" class="form-control" placeholder="Type CC here...." id="recipientsListCC" onKeyUp="getUserListForMeeting(this.value,this.id,'selectedMailCC','ccListForMail');return false;"/>
										<div style="position:fixed;background-color:#eaecef;z-index:10;width:94%" id="ccListForMail"></div>
										<textarea id="selectedMailCC" style="display:none;" class="form-control" value="" ></textarea>
									</div>
									<div class="form-group" style="margin-left:5px;">
										<label><strong>Subject*</strong></label><input type="text" class="form-control" id="mailSubject" value="Subject regarding leave request." /> 
									</div>
									<div class="form-group" style="margin-left:5px;">
										<label><strong>Message*</strong></label>
										<textarea class="form-control" id="mailMessage" rows="2" value="Discussion regarding leave request."></textarea> 
									</div>
									<div class="form-group" style="margin-left:3px;">
													<label><a href="#l" type="button"
																class="btn btn-primary" style="" title="Send Request" onclick="sendMeetingRequestForLeave('mail');">Send</a></label>
									</div>
									<div class="panel panel-default" id="mailHistory">
								    <div class="panel-heading" style="background-color:#f5f5f5;">
								      <h4 class="panel-title" style="color:#000;">
								        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#mailCollapse">
								          Mail History
								        </a>
								      </h4>
								    </div>
								    <div id="mailCollapse" class="panel-collapse collapse">
								      <div class="panel-body" id="mailHistoryData">
								        	<span style="color:red;">No history available.</span>
								      </div>
								    </div>
							</div>									 
						        </div>
						        <div class="tab-pane" id="tab2">
						        <span style="font-size:12px;color:red;">Write member's email comma seperated.</span>
						        	<div class="form-group" style="margin-top: 5px;margin-left:5px;">
										<label><strong>Add Participant*</strong></label>
										<input type="text" class="form-control" placeholder="Type here...." id="recipientsListMeeting" onKeyUp="getUserListForMeeting(this.value,this.id,'selectedUserForMeeting','emailListForMeeting');return false;"/>
										<div style="position:fixed;background-color:#eaecef;z-index:10;width:94%" id="emailListForMeeting"></div>
										<textarea id="selectedUserForMeeting" style="display:none;" class="form-control" value="" ></textarea>
									</div>
							<div class="form-group" style="margin-left:5px;">
								<label><strong> Strat Time and Date*</strong></label>
								<div class='input-group date meetingTime' id='' style="position:relative;">
				                    <input type='text' class="form-control" id='meetingTime1'/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
								</div>
								<div class="form-group" style="margin-left:5px;">
								<label><strong> End Time and Date*</strong></label>
								<div class='input-group date meetingTime' id='' style="position:relative;">
				                    <input type='text' class="form-control" id='meetingTime2'/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
								</div>
								<div class="form-group" style="margin-left:5px;">
									<label><strong>Location*</strong></label><input type="text" class="form-control" placeholder="Meeting Location" id="meetingLocation" /> 
								</div>
								<div class="form-group" style="margin-left:5px;">
										<label><strong>Subject*</strong></label><input type="text" class="form-control" id="meetingSubject" value="Subject regarding Leave request." /> 
								</div>
								<div class="form-group" style="margin-left:5px;">
								<label><strong>Message*</strong></label>
									<textarea class="form-control" id="meetingMessage" rows="2" value="Type message"></textarea> 
								</div>
								<div class="form-group" style="margin-left:3px;">
									<label><a href="#l" type="button" class="btn btn-primary" style="" title="Send Request" onclick="sendMeetingRequestForLeave('meeting');">Send</a></label>
								</div>
								<div class="panel panel-default" id="meetingHistory">
								    <div class="panel-heading" style="background-color:#f5f5f5;">
								      <h4 class="panel-title" style="color:#000;">
								        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#meetCollapse">
								          Meeting History
								        </a>
								      </h4>
								    </div>
								    <div id="meetCollapse" class="panel-collapse collapse">
								      <div class="panel-body" id="meetingHistoryData">
								        	<span style="color:red;">No history available.</span>
								      </div>
								    </div>
							</div>
						        </div>
						        </div>
						      </div>
				        </div>
				        <div class="modal-footer"></div>
				      </div>
				     </div>
				  </div>
				  <div class="panel panel-primary" id="userAssignedTaskDiv" aria-labelledby="userAssignedTaskDiv"	style="width: 100%;display: none;" scrolling="auto">
				  <div class="panel panel-default">
			<div class="panel-heading">
				<strong>Assigned Task List to User</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Total Assigned Task::</strong></label> 
											<span margin-left: 5px;" id="userTotalTaskCount"><b>0</b></span>
										</div>
								</div>
							</div>
							<div id="assignedTaskList"></div>
						</div>
						</div>
						<div class="panel panel-primary" id="viewExceptionAttendanceReportDiv" aria-labelledby="viewExceptionAttendanceReportDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">			
			<div class="panel-heading">
				<strong>Attendance Exception Report</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
													<label><strong>Start Date(Sun)</strong></label> <br />
													<span style="padding-left: 0px; margin-top: 5px;">
														<input type="text" id="from-date-User-Atten-Exception" class="form-control" onkeypress="return false;" />
													</span>
											</div>
											<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
																<label><strong>End Date(Sat)</strong></label><br />
																<span style="padding-left: 0px; margin-top: 5px;">
																	<input type="text" id="to-date-User-Atten-Exception" class="form-control" onkeypress="return false;"/>
																</span>
																	
											</div>
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Name</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="usrListInExceptionAttendenceReport">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
					<label><a href="#!" class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="getUsersExceptionAttendenceReport();">View</a></label>
					</div>
					</div>
					</div>
					<div id="userExceptionAttnDataDiv" style="display:none;">
					<table class="table table-bordered" id="otherUserExceptionAttenItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="">SrNo</th>
								<th class="">Username</th>
								<th class="">Emp Name</th>
								<th class="">Total Working HH</th>
								<th class="">Desired Working HH</th>
								<th class="">Punched HH</th>
								<th class="">Def/Extra HH</th>
								<th class="">On Leave</th>
								<th class="">Holidays</th>
							</tr>
						</thead>
						<tbody id="tab_logic_otherUserExceptionAttenItemApp">
						</tbody>
					</table>
					<a href="downloadUserExcpetionAttendenceRepo" title="Download Excel" id="attnExceptionRprtId" class="btn btn-primary pull-left" style="display:inline;margin-right:67px;margin-bottom:5px;">Download</a>
					</div>
		</div>
		</div>	 
		<div class="panel panel-primary" id="viewNewLeavesOthersDiv" aria-labelledby="viewNewLeavesOthersDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Apply Leave for Other Employees</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('applyLeaveForOtherTT22','popover22');" tabindex="0" data-toggle="popover22" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Employee</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="applyLeaveForOtherUser">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="applyLeaveRequestForOthers('applyLeaveForOtherUser');">Apply</button></a></label>
					</div>
					</div>
					</div>
				</div>
			</div>	
	<div class="panel panel-primary" id="viewGenerateDocDiv" aria-labelledby="viewGenerateDocDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Generate Employee Documents</strong>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<!-- <div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Employee</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="empListForDoc">
													<option value="select" label="Select User" selected />
												</select>
											</span>
										</div> -->
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Doc</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="docListForDoc">
													<option value="select" label="Select Document" selected>Select Doc</option>
													<option value="1" label="Offer Letter">Offer Letter</option>
													<option value="2" label="Confirmation Letter">Confirmation Letter</option>
													<option value="3" label="Relieving Letter">Relieving Letter</option>
													<option value="4" label="Payslip">Payslip</option>
												</select>
											</span>
										</div>
									<div class="form-group" style="margin-top: 10px;margin-left:15px;">
										<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="fetchRequiredParam('','docListForDoc');">Go</button></a></label>
									</div>
					</div>
					<div class="row" id="downloadempdocdiv"></div>
					</div>
				</div>
			</div>	
		<div class="panel panel-primary" id="viewCreateDocRTEDiv" aria-labelledby="viewCreateDocRTEDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Create New Template</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('createTemplateTT','popover1');" tabindex="0" data-toggle="popover1" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-left:0px;">
											<label><strong>Document Type</strong></label> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="templateDocType" onchange="populateDocParameterDocGen(this.value)" >
													<option value="">Select Document</option>
												</select>	
											</span>
										</div>
										<div class="row" id="createRTETemplatediv" style="margin:5px;">
											<div class="col-lg-10" style="margin-right:-60px;">
												<textarea name="editor2" id="createTemplateEditorId"></textarea>
											</div>
											<div class="col-lg-2" id="populateFieldId"></div>
										</div>	
									<div class="form-group" style="margin-top:0px;margin-left:15px;">
										<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="openRTERequiredParamModal('createTemplateEditorId');">Create New Template</button></a></label>
									</div>	
							</div>
		</div>		
		</div>
		<div class="panel panel-primary" id="viewGenerateDocRTEDiv" aria-labelledby="viewGenerateDocRTEDiv"	style="width: 100%;display: none;" scrolling="auto">
			<!-- <div class="panel-heading">
				<strong>Generate Documents</strong>
			</div> -->
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Poilcy Group</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="genDocPolicyGroup" onchange="populateUserGroupListforDocGen(this.value,'genDocUserGroup')" >
													<option value=""></option>
												</select>	
											</span>
										</div>
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>User Group</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="genDocUserGroup" onchange="populateDocListforDocGen(this.value,'genDocListDoc')" >
													<option value="">Select Group</option>
												</select>	
											</span>
										</div>
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Doc Type</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="genDocListDoc" onchange="populateUserTemplateListforDocGen(this.value,'genDocTemplateList')" >
													<option value="">Select Doc Type</option>
												</select>
											</span>
										</div>
										<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Choose Template</strong></label><br /> 
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="genDocTemplateList">
													<option value="">Select Template</option>
												</select>	
											</span>
										</div>
									<div class="form-group" style="margin-left:15px;">
										<button style="border:none;background:#fff;margin-top:33px;" class='glyphicon glyphicon-question-sign pull-right' onclick="setPopoverData('generateEmpDocTT','popover5');" tabindex="0" data-toggle="popover5" data-trigger="focus" data-placement="auto" data-html="true"></button>
										<button type="button" class="btn btn-primary" style="margin-top:30px;" title="Click to View" onclick="fetchGenDocTemplate('genDocTemplateList');">View Template</button>
										<button type="button" class="btn btn-primary" style="margin-left:5px;margin-top:30px;" title="Reset" onclick="resetTemplateForm();">Reset Form</button>
									</div>
								</div>
					<div class="row col-lg-12" id="populateRTEFieldId" style="display:none;">
						<div class="row" id="downloadRTEdiv" style="margin:5px;">
							<textarea name="editor1" id="templateEditorId"></textarea>
						</div>
						<div>
							<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-6">
								<span style="padding-left: 0px; margin: 5px;">
									<a  class="btn btn-primary" style="margin-right:10px;" onclick="openGenerateNewEmpDocModal();">Create</a>
								</span>
							</div>
						</div>
					</div>	
					</div>
				</div>
				<div class="modal fade" id="viewAccessGroupModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Edit User Access Group ::<span id="id_userName1"></span></h4>
				          <input type="hidden" id="userName1Value" />
				        </div>
				        <div class="modal-body" id="accessGroupDiv" style="padding:5px;max-height:500px;overflow-y: auto;"></div>
				      </div>
				    </div>
				 </div>
				 <div class="panel panel-primary" id="viewCompNotificationDiv" aria-labelledby="viewCompNotificationDiv" style="width: 100%;display: none;" scrolling="auto">
					<div class="panel panel-default">
					<div class="panel-heading">
						<strong>Manage Company Announcement</strong>
						<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('manageAnnoncementTT','popover6');" tabindex="0" data-toggle="popover6" data-trigger="focus" data-placement="bottom" data-html="true"></button>
					</div>
					<div class="">
						<button type="button" class="btn btn-success pull-left" style="margin:5px;" onclick="addNewAnnouncement();">Add New Notification</button>
						<table class="table table-bordered" id="compNotificationTable" scrolling="auto">
						<thead>
							<tr>
								<th class="">SrNo</th>
								<th class="">Policy Group</th>
								<th class="">Type</th>
								<th class="">Title</th>
								<th class="">Is Active</th>
								<th class="">Date</th>
								<th class="">Attachment</th>
								<th class="">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_comp_notification">
						</tbody>
					</table>			
					</div>
				</div>
				</div>
				<div class="modal fade" id="addNewNotificationModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Create New Notification </h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">  
				          <label>Notification Type</label>
				          <select id="id_NotificationType" class="form-control" style="margin-bottom: 10px">
				          		<option value="PDF_Document">PDF_Document</option>
				          		<option value="Popup">Popup</option>
				          		<option value="Webpage">Webpage</option>
				          </select>
				        </div>
				        <div class="form-group">  
				          <label>Policy Group</label>
				          <select class="form-control" id="notificationPolicyGroup" style="margin-bottom: 10px">
				          		<option value="">Select Type</option>
				          </select>
				        </div>
				        <div class="form-group">
				          <label>Title:</label>
				          <input class="input-md  textinput textInput form-control" id="id_notificationName" maxlength="250" minlength="10" name="taskName" placeholder="This will be shown inside Announcement Panel" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">  
				          <label>Description</label>
				          <textarea class="input-md textarea form-control" id="id_NotificationDesc" maxlength="2000" name="taskDesc" placeholder="Link Description.....maximum 2000 characters" style="margin-bottom: 10px"></textarea>
				        </div>
				        <input type="hidden" id="id_NotificationAttach" value="" />
				        <div class="form-group">  
				          <form class="form-inline" method="POST" enctype="multipart/form-data" id="notificationDocUploadForm" style="display:inline;">
								<input type="submit" class="btn btn-primary pull-right" title="Click to upload Attachment" value="Add" onclick="uploadNotificationDoc1();return false;" style="margin-right:5px;"/>
					 			<input type="file" name="file" onchange="valNotificationUpload(this.value,id,'id_NotificationAttach');" id="uploadNotificationDoc" class="filestyle" style="display:inline;" data-buttonText="Upload Attachment"/>
					 	  </form>
				         </div>
				        </div>
				        </div>
				       </div>
				 </div>
				 <div class="modal fade" id="modifyNotificationModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Modify Notification </h4>
				        </div>
				        <div class="modal-body">
				        <input type="hidden" id="modify_NotificationRfNum" value="" />
				        <div class="form-group">  
				          <label>Notification Type</label>
				          <span id="modify_NotificationType"></span>
				        </div>
				        <div class="form-group">  
				          <label>Policy Group</label>
				          <span id="modify_notificationPolicyGroup"></span>
				        </div>
				        <div class="form-group">
				          <label>Title:</label>
				          <input class="input-md  textinput textInput form-control" id="modify_notificationName" maxlength="250" minlength="10" name="notificationName" placeholder="This will be shown inside Announcement Panel" style="margin-bottom: 10px" type="text" />
				        </div>
				        <div class="form-group">  
				          <label>Description</label>
				          <textarea class="input-md textarea form-control" id="modify_NotificationDesc" maxlength="2000" name="taskDesc" placeholder="Link Description.....maximum 2000 characters" style="margin-bottom: 10px"></textarea>
				        </div>
				        <input type="hidden" id="modify_NotificationAttach" value="" />
				        <div class="form-group">  
				          <form class="form-inline" method="POST" enctype="multipart/form-data" id="modifyNotificationDocUploadForm" style="display:inline;">
								<a href="#l" type="submit" class="btn btn-primary pull-right" title="Click to upload feedback attachment" value="Submit" onclick="uploadNotificationDoc2();return false;" style="margin-right:5px;">Update</a>
					 			<input type="file" name="file" onchange="valNotificationUpload(this.value,id,'modify_NotificationAttach');" id="uploadNotificationDoc2" class="filestyle" style="display:inline;" data-buttonText="Upload Attachment"/>
					 	  </form>
				         </div>
				        </div>
				        </div>
				       </div>
				 </div>
				 <div class="modal fade" id="addProfilePicModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Edit Profile Image :: ${sessionScope.loggedInUserFullName} </h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				        	<img src="<c:url value="${sessionScope.profilePicUrl}" />" width="100" height="100" class="img-responsive" alt="" style="border-radius:9999px !important;display:block;float:left;" id="blah">  
				        </div>	
				        <div class="form-group" style="margin-top: 110px;">  
				          <form class="form-inline" method="POST" enctype="multipart/form-data" id="profilePicUploadForm" style="display:inline;">
								<input type="submit" class="btn btn-primary pull-right" title="Click to upload JPG Image" value="Add" onclick="uploadProfilePicture('uploadProfilePicDoc');return false;" style="margin-right:5px;"/>
					 			<input type="file" name="file" onchange="valProfilePicUpload(this.value,id);" id="uploadProfilePicDoc" class="filestyle" style="display:inline;" data-buttonText="Upload Image"/>
					 	  </form>
				         </div>
				        </div>
				        </div>
				       </div>
				 </div>
				 <div class="panel panel-primary" id="viewPunchedFromOnsiteDiv" aria-labelledby="viewPunchedFromOnsiteDiv" style="width: 100%;display: none;" scrolling="auto">
					<!-- <div class="panel-heading">
						<strong>Manage Company Announancement</strong>
					</div> -->
					<div class="">
						<div class="form-inline panel panel-primary" style="margin-left:10px;">
				 		<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Start Date</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="from-datepicker-punchLoc" class="form-control" onkeypress="return false;" style="width:100px;"/>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>End Date</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<input type="text" id="to-datepicker-punchLoc" class="form-control" onkeypress="return false;" style="width:100px;"/>
											</span>
												
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
							<label><strong>Select Employee</strong></label><br />
							<span style="padding-left: 0px; margin-top: 5px;">
								<select  id="username-punchLoc" class="form-control">
								<option value="">Select User</option>
								</select>
							</span>
						</div>
						<div class="form-group" style="margin-top: 35px;margin-left:5px;">					
							<span style="padding-left: 0px; margin-top: 5px;">
								<a  class="pull-right btn btn-primary" style="margin-right:10px;" onclick="fetchPunchedRecordFromClientLoc();">Go</a>
							</span>
												
						</div>
					</div>
						<table class="table table-bordered" id="punchRecordFromOnsiteTable" scrolling="auto">
						<thead>
							<tr>
								<th class="">SrNo</th>
								<th class="">Emp Id</th>
								<!-- <th class="">Emp Name</th> -->
								<th class="">Date</th>
								<th class="">City</th>
								<th class="">Region</th>
								<th class="">Postal Code</th>
								<th class="">SignIn</th>
								<th class="">SignOut</th>
								<th class="">Status</th>
							</tr>
						</thead>
						<tbody id="tab_logic_punchRecordFromOnsite">
						</tbody>
					</table>			
					</div>
				</div>
				<div class="modal fade" id="viewClashedLeaveModal" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title" id="requestLeaveId"></h4>
				          <input type="hidden" id="requestLeaveHiddenId" value="" />
				        </div>
				        <div class="modal-body">
				        <span>Project Filter:</span><br />
				        <div class="form-group" id="projectDivForLeave"></div>
				        <div class="form-group">
				          <table class="table table-bordered" id="empLeaveOnSameDayTable" scrolling="auto">
							<thead>
								<tr>
									<th class="">SrNo</th>
									<th class="">Date</th>
									<th class="">Leave Head</th>
									<th class="">Full/Half day</th>
									<th class="">Other users on leave</th>
								</tr>
							</thead>
							<tbody id="tab_logic_empLeaveOnSameDay"></tbody>
						</table>
				        </div>
				        </div>
				       </div>
				   </div>
			</div>
			<div class="panel panel-primary" id="viewEmpDirectoryDiv" aria-labelledby="viewEmpDirectoryDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Search Employee </strong>
							<input class="input-sm  textinput textInput form-control" style="width:30%;display:inline" id="globalFilterInput" placeholder="Global search input..." onkeyup="filterFullTableByInput(this.value,'viewEmpDirTbl');">
							<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('employeeDirectoryTT','popover10');" tabindex="0" data-toggle="popover10" data-trigger="focus" data-placement="bottom" data-html="true"></button>
						</div>
						<div class="panel-body" id="emailNotificationDiv">
								<table id="viewEmpDirTbl" class="table table-bordered">
									<thead style="background-color:#30a5ff;color:white;">
										<tr>
											<th>SNo</th>
											<th>Emp Id</th>
											<th>Name</th>
											<th>Email</th>
											<th>Contact Number</th>
											<th>Extension</th>
										</tr>
									</thead>
									<tbody id="viewEmpDirTblBody">
									</tbody>
								</table>
						</div>
			</div>		
			</div>	
			<div class="modal fade" id="rteParamDetailModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Create New Letter Template</h4>
				        </div>
				        <div class="modal-body panel panel-inline" id="rteModalDiv">
				        	<input type="hidden" value="" id="rteTemplateId" />
				  			<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<select class="form-control" id="rteTempPolicyGroup"></select>
						   	</div>
						   	<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<select class="form-control" id="rteTempUserGroup"></select>
						   	</div>
						   	<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<input id="rteDocType" class="form-control" placeholder="Document Type" />
						   	</div>
						   	<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<input id="rteDocDesc" class="form-control" placeholder="Document Description" />
						   	</div>	      	
						   	<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<input id="rteTemplateName" class="form-control" placeholder="Template Name" />
						   	</div>
						   	<div class="form-group" style="margin-top: 35px;margin-left:25px;">					
								<span style="padding-left: 0px; margin-top: 5px;">
									<a  class="btn btn-primary" style="margin-right:10px;" onclick="createNewDocTemplate();">Create</a>
								</span>
							</div>
				        </div>
				      </div>
				     </div>
			</div>
			<div class="modal fade" id="rteDocUserDetailModal" role="dialog">
				    <div class="modal-dialog modal-md">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Generate New Letter</h4>
				        </div>
				        <div class="modal-body panel panel-inline" id="rteModalDiv">
				        	<input type="hidden" value="" id="rteUserDocId" />
						   	<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<input id="rteGenDocUsername" class="form-control" placeholder="Employee Id (if available otherwise leave blank)" />
						   	</div>	      	
						   	<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<input id="rteGenDocFullname" class="form-control" placeholder="Employee Full Name" />
						   	</div>
						   	<div class="form-group col-xs-10 col-sm-4 col-md-4 col-lg-12">
								<input id="rteGenDocEmail" class="form-control" placeholder="Personal Email" />
						   	</div>
						   	<div class="form-group" style="margin-top: 35px;margin-left:25px;">					
								<span style="padding-left: 0px; margin-top: 5px;">
									<a  class="btn btn-primary" style="margin-right:10px;" onclick="generateNewEmpDoc();">Create</a>
								</span>
							</div>
				        </div>
				      </div>
				     </div>
				</div>
				<div class="panel panel-primary" id="viewGeneratedDocDiv" aria-labelledby="viewGeneratedDocDiv"	style="width: 100%;display: none;" scrolling="auto">
						<div class="panel-heading">
							<strong>Search Generated Document </strong>
							<input class="input-sm  textinput textInput form-control" style="width:30%;display:inline" id="globalFilterInputGenDoc" placeholder="Global search input..." onkeyup="filterFullTableByInput(this.value,'viewEmpGenDocTbl');">
						</div>
						<div class="panel-body">
								<table id="viewEmpGenDocTbl" class="table table-bordered">
									<thead style="background-color:#30a5ff;color:white;">
										<tr>
											<th>SNo</th>
											<th>Emp Id</th>
											<th>Name</th>
											<th>Email</th>
											<th>Reference Number</th>
											<th>Download</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody id="viewEmpDirTblBody">
									</tbody>
								</table>
						</div>
			</div>
		<div class="panel panel-primary" id="viewCreateITRequestDiv" aria-labelledby="viewCreateITRequestDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>New IT Request</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('createITAssetRequestTT','popover30');" tabindex="0" data-toggle="popover30" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
			<div class="form-inline panel panel-primary">
				<div class='col-md-12' style="border: 1px solid gray;padding: 10px;margin-right: 10px;margin-top: -7px;">
					<label class="col-md-2">Username:</label>
					<span class="col-md-2">${sessionScope.loggedInUser}</span>
					<label class="col-md-2">Employee Name:</label>
					<span class="col-md-2">${sessionScope.loggedInUser}</span>
					<label class="col-md-2">Designation:</label>
					<span class="col-md-2">${sessionScope.loggedInUserDesignation}</span>
					<label class="col-md-2">Department:</label>
					<span class="col-md-2">${sessionScope.loggedInUserDepartment}</span>
					<label class="col-md-2">Reporting Manager:</label>
					<span class="col-md-2">${sessionScope.reportingManager}</span>
					<label class="col-md-2">HR Manager:</label>
					<span class="col-md-2">${sessionScope.hrManager}</span>
				</div>
				<div class='col-md-12' style="border: 1px solid gray;padding:10px;margin-right:10px;margin-top: 5px;">
				<div class="controls col-md-12">
					<h4><strong>Fill Details:</strong></h4><hr>
				</div>
				<div class="controls col-md-4">
				<label class="col-md-4">Request For:</label>
                                <label class="radio-inline"><input type="radio" checked="checked" name="itReqType" value="Hardware"  style="margin-bottom: 10px">Hardware</label>
                                <label class="radio-inline"> <input type="radio" name="itReqType"  value="Software"  style="margin-bottom: 10px">Software</label>
                 </div>
                 <div class="controls col-md-4">
					 <label class="col-md-4">Request Type:</label>
                     <select class="form-control input-md" id="itReqType">
						<option selected>Select Req Type</option>
						<option>New</option>
						<option>Repair</option>
						<option>Renew</option>
					</select>
                 </div>
                 <div class="controls col-md-4">
					 <label class="col-md-4">Request Category:</label>
                     <select class="form-control input-md" id="itReqType">
						<option selected>Select Category</option>
						<option>Desktop</option>
						<option>Laptop</option>
						<option>Printer</option>
					</select>
                 </div>
                 <div class="controls col-md-4">
					 <label class="col-md-4"><strong>Project Name:</strong></label>
                     <input type="text" class="form-control input-md" placeholder="Project Name"/>
                 </div>
                  <div class="controls col-md-8">
					 <label class="col-md-2">Request Description:</label>
                     <textarea class="form-control" id="itReqDesc" rows="3" cols="70" value="Type request description..."></textarea>
                  </div>
                  <div class="controls col-md-12"> 
                  		<label class="col-md-2">Add Attachment:</label>	 
				          <form class="form-inline" method="POST" enctype="multipart/form-data" id="itReqAttachment" style="display:inline;">
								<!-- <input type="submit" class="btn btn-primary pull-right" title="Click to add attachment" value="Submit" onclick="addAttachmentITReq();return false;" style="margin-right:5px;"/> -->
					 			<input type="file" name="file" onchange="valFeedbackUpload(this.value,id);" id="attachmentITReq" class="filestyle" style="display:inline;" data-buttonText="Upload Attachment"/>
					 		</form>
				  </div>
				  <div class="form-group col-md-2" style="margin-left:15px;margin-bottom:15px;">
						<button class="btn btn-primary" style="margin-top:10px;" title="Raise New IT Request" onclick="submitNewAssetRequest();">Submit Request</button>
				  </div>	
				</div>
			</div>
		</div>
		<div class='col-md-12' style="border:0px solid gray;margin-right:10px;margin-top:5px;padding:0px;">
		<div class="panel panel-default">
			<div class="panel-heading" style="background:#3e5871;">		
				<strong>View Past Request Status</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('viewITAssetRequestStatusTT','popover31');" tabindex="0" data-toggle="popover31" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				<span class="pull-right clickable">
					<i class="glyphicon glyphicon-chevron-up" data-toggle="collapse" data-target="#collapseITPastReq"></i>
				</span>
			</div>
			<div id="collapseITPastReq" class="panel-collapse collapse in">
				<table class="table table-bordered">
				<thead>
					<tr>
					<th>Sr No</th>
					<th>Request Id</th>
					<th>Category</th>
					<th>Created On</th>
					<th>Created By</th>
					<th>Modified On</th>
					<th>Modified By</th>
					<th>Status</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
				</table>
				<span style="color:red;font-weight:600;font-size:14px;margin-left:15px;">No request available!</span>
			</div></div></div></div>
		<div class="panel panel-primary" id="viewBulkAssetUploadDiv" aria-labelledby="viewBulkAssetUploadDiv"	style="width: 100%;display: none;" scrolling="auto">
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Bulk Asset Upload</strong>
			</div>
			 <div class="panel-body">
			 	<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:0px;padding-bottom:0px;box-shadow:none;">
					 	<div>
					 	<span style="color:red;font-size:12px;"><strong>Important!</strong></span><br>
					 	<span style="font-size:10px;">Uploaded file must be in format xlsx.</span><br>
					 	<span style="font-size:10px;">Uploaded file name must be <strong>UploadAsset.xlsx</strong> only.</span><br>
					 	<span style="font-size:10px;">Update and rename the downloaded file and do not change template format.</span><br>
					 	<span style="font-size:10px;">All cell format type must be text only.</span><br>
					 	<br>
					 	</div>
					 	<div>
					 	<a href="downloadAssetTemplate" id="AssetSheetId" class="btn btn-primary"style="display:inline;">Download Sheet</a>
					 	<form method="POST" enctype="multipart/form-data" id="assetFileUploadForm" style="display:inline;">
							<input type="submit" class="btn btn-primary pull-right" value="Upload" id="btnAssetSubmit" onclick="uploadAssetSheet1();return false;"/>
					 		<input type="file" name="file" onchange="valAssetSheetUpload(this.value,id);" id="AssetDocumentList" class="filestyle pull-right" style="display:inline;" data-buttonText="Upload Sheet"/>
					 	</form>
					 	</div>
						<div class="form-group" id="assetSheetList" style="margin-top:10px;width:100%">
						</div>
					</div>
					</div>			
			 </div>
		</div>	 
		</div>
		<div class="panel panel-primary" id="viewPendingITRequestDiv" aria-labelledby="viewPendingITRequestDiv"	style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Pending IT Asset Request </strong>
				<input class="input-sm textInput form-control" style="width:30%;display:inline" id="globalFilterInputPendingAsset" placeholder="Global search input..." onkeyup="filterFullTableByInput(this.value,'userExpenseItemTbl');">
			</div>
			<div class="panel-body">
				<div>
				<table class="table table-bordered">
				<thead>
					<tr>
					<th>Sr No</th>
					<th>Request Id</th>
					<th>Category</th>
					<th>Created On</th>
					<th>Created By</th>
					<th>Modified On</th>
					<th>Assigned To</th>
					<th>Status</th>
					<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<td>1</td>
					<td>SUPCAT980</td>
					<td>Hardware</td>
					<td>2018-02-02</td>
					<td>Test User</td>
					<td>2018-02-05</td>
					<td>RM User/Infra User/Group</td>
					<td>Pending</td>
					<td><button>View Detail</button><button>Forward</button><button>Reject</button></td>
				</tbody>
				</table>
				</div>
				<div><span id="noDataAvailableIdAsset" style="color:red;font-weight:650;margin:15px;"></span></div>							
			</div>
			</div>
		</div>
		<div class="panel panel-primary" id="viewManageAssetDiv" aria-labelledby="viewManageAssetDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Manage Asset</strong>
				<input class="input-sm textInput form-control" style="width:30%;display:inline" id="globalFilterInputManageAsset" placeholder="Global search input..." onkeyup="filterFullAssetTableByInput();">
			</div>
			 <div class="panel-body">
				<div class="table-responsive">
					<div class="form-inline panel panel-primary" style="margin-left:10px;padding-bottom:0px;box-shadow:none;">
						<div class="form-group" style="margin-left:15px;">
							<label><strong>Asset Status:</strong></label><br />
							<span>
								<select class="form-control input-md" id="manageAssetStatusFilter" onchange="filterAnyTableByInput();">
								    <option value="" label="Filter by Status" selected>Filter by Status</option>
								    <option label="Available">Available</option>
								    <option label="Allocated">Allocated</option>
								    <option label="Disposed">Disposed</option>
								</select>
							</span>	
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Model No:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="assetFilterByModelName" placeholder="Asset Name" onkeyup="filterAnyTableByInput();">
							</span>
						</div>
						<div class="form-group" id="" style="margin-left:15px;">
							<label><strong>Asset Id:</strong></label><br />
							<span>
							<input class="input-md  textinput textInput form-control" id="assetFilterByAsstId" placeholder="*Asset Id" onkeyup="filterAnyTableByInput();">
							</span>
						</div>	
					</div>
				</div>
				<table class="table table-bordered" id="manageAssetItemTbl" scrolling="auto">
						<thead>
							<tr>
								<th>Sr No</th>
								<th class="text-center">Asset Id</th>
								<th class="text-center">Category</th>
								<th class="text-center">Vendor</th>
								<th class="text-center">Added On</th>
								<th class="text-center">Added By</th>
								<th class="text-center">Price(INR)</th>
								<th class="text-center">Status</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
							<tbody id="tab_logic_manageAssetItemApp">
								<tr>
									<td>1</td>
									<td>SUPASSTDESK110</td>
									<td>Desktop</td>
									<td>Lenovo</td>
									<td>2018-02-12</td>
									<td>Inventory User</td>
									<td>0.00</td>
									<td>Available</td>
									<td class="text-center">
										<a href="#l" class="btn btn-info btn-sm">Life Cycle</a>&nbsp;
										<a href="#l" class="btn btn-warning btn-sm">Modify</a>&nbsp;
										<a href="#l" class="btn btn-danger btn-sm">Dispose</a>
									</td>
								</tr>
							</tbody>
				</table>
			  </div>
		</div>
		</div>
		<div class="panel panel-primary" id="viewAssetReportDiv" aria-labelledby="viewAssetReportDiv" style="width: 100%;display: none;" scrolling="auto">
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Generate Asset Report</strong>
			</div>
			 <div class="panel-body">
			 	<div class="table-responsive">
					 <div class="form-inline panel panel-primary" style="margin-left:0px;padding-bottom:0px;box-shadow:none;">
						<div class="col-md-4">
							<h4>Select Report</h4>
							<ul>
								<li><a href="#l" onclick="viewAssetChart('reportIdStatus');">Report based on Asset Status</a></li>
								<li><a href="#l" onclick="viewAssetChart('reportIdAllocation');">Employee Asset allocation history</a></li>
								<li><a href="#l" onclick="viewAssetChart('reportIdFY');">Finacial Purchase History</a></li>
								<li><a href="#l" onclick="viewAssetChart('reportIdCategory');">Report based on category</a></li>
							</ul>						
						</div>
						<div class="col-md-8">
							<canvas id="pieChart"></canvas>						
						</div>
					</div>
					</div>			
			 </div>
		</div>	 
		</div>
		<div id="addParamDiv" style="display:none;width:190px;height:110px;border:none;border-radius:5px;padding:5px;">
			<div class="form-inline">
				<span><strong>Create New Value</strong></span>
				<input type="text" class="form-control input-sm" placeholder="Enter Value" style="background:#f0f0f0;"/>
				<input type="hidden" id="masterParamElem" value=""/> 
				<a href="#l" class="btn btn-info btn-sm pull-left" style="margin:5px;" onclick="addParamToMaster();">Add</a>
				<a href="#l" class="btn btn-info btn-sm" style="margin:5px;" onclick="closeAddParamDiv();">Close</a>
			</div>
		</div>
		<div id="addTDSRemarkDiv" style="display:none;width:300px;height:110px;border:none;border-radius:5px;padding:5px;">
			<div class="form-inline">
				<span><strong>Add Remark........</strong></span><br>
				<input type="text" class="form-control" placeholder="Enter remark before any action" style="background:#f0f0f0;width:100%;" />
				<a href="#l" class="btn btn-success btn-sm pull-left" style="margin:5px;">Approve</a>
				<a href="#l" class="btn-danger btn-sm pull-left" style="margin:5px;">Reject</a>
				<a href="#l" class="btn-info btn-sm pull-left" style="margin:5px;" onclick="closeTDSParamDiv();">Close</a>
			</div>
		</div>
		<div class="panel panel-primary" id="viewPayslipDiv" aria-labelledby="viewPayslipDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
			<div class="panel-heading">
				<strong>View Payslip</strong>
				<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('viewPayslipTT32','popover32');" tabindex="0" data-toggle="popover32" data-trigger="focus" data-placement="bottom" data-html="true"></button>
			</div>
							<div class="table-responsive">
								<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Select Year</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="yearPaySlip">
												<option value="" label="Select Year">Select Year</option>
												<option value="2018" label="2018">2018</option>
												<option value="2017" label="2017">2017</option>
												</select>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>Select Month</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="monthPaySlip">
													<option value="" label="Select Month">Select Month</option>
													<option value="1" label="Jan">Jan</option>
													<option value="2" label="Feb">Feb</option>
													<option value="3" label="Mar">Mar</option>
													<option value="4" label="Apr">Apr</option>
													<option value="5" label="May">May</option>
													<option value="6" label="Jun">Jun</option>
													<option value="7" label="Jul">Jul</option>
													<option value="8" label="Aug">Aug</option>
													<option value="9" label="Sep">Sep</option>
													<option value="10" label="Oct">Oct</option>
													<option value="11" label="Nov">Nov</option>
													<option value="12" label="Dec">Dec</option>
												</select>
											</span>
												
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="#"><button type="button"
									class="btn btn-primary" style="margin-top:30px;" onclick="getEmpPayslip();">View</button></a></label>
					</div>
					</div>
					<div class="row" id="viewUserPayslipdiv" style="padding:10px;">
						<%@ include file="samplePayslip.jsp" %>
					</div>
					</div>
				</div>
			</div>
		<div class="panel panel-primary" id="viewTDSDeclarationDiv" aria-labelledby="viewTDSDeclarationDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>TDS Declaration for FY::</strong><b>2017-18</b>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('viewTDSDeclarationTT33','popover33');" tabindex="0" data-toggle="popover33" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				</div>
				<div class="table-responsive">
					<div class="row" id="viewUserTDSdiv" style="padding:10px;">
							<%@ include file="tdsDeclarationSample.jsp" %>
							<!-- <div src="/TimesheetManagement/tdsDeclarationSample" width="90%" align="center"></div> -->
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-primary" id="viewMapEmpPayrollDiv" aria-labelledby="viewMapEmpPayrollDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Employee-Payroll Mapping::</strong>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('empPayrollMapTT34','popover34');" tabindex="0" data-toggle="popover34" data-trigger="focus" data-placement="bottom" data-html="true"></button>
					<input class="input-sm textInput form-control" style="width:30%;display:inline" id="globalFilterInputEmpPayrollMap" placeholder="Global search input...">
				</div>
				<div class="table-responsive">
					<table class="table table-bordered" id="empPayrollMapTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">S No</th>
								<th class="text-center">Payroll Name</th>
								<th class="text-center">Username</th>
								<th class="text-center">Emp Name</th>
								<th class="text-center">Element Group</th>
								<th class="text-center">Last Updated On</th>
								<th class="text-center">Last Updated By</th>
								<th class="text-center">Status</th>
								<th class="text-center nowrap">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_empPayrollMapItemApp">
							<tr>
								<td>1</td>
								<td>SUPRA-Noida1</td>
								<td>SITS110</td>
								<td>Test User</td>
								<td item-align="center">
									<button class="btn btn-sm btn-success">EG1</button>
									<button class="btn btn-sm btn-success">EG2</button>
									<button class="btn btn-sm btn-success">EG3</button>
								</td>
								<td>2018-03-30</td>
								<td>Test Finance</td>
								<td>Active</td>
								<td item-align="center">
									<a href="#l">Edit</a>
								</td>
							</tr>
						</tbody>
					</table>					
				</div>
			</div>
		</div>
		<div class="panel panel-primary" id="viewEmpPayrollDiv" aria-labelledby="viewEmpPayrollDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Employee Payroll</strong>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('viewEmpPayrollTT35','popover35');" tabindex="0" data-toggle="popover35" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				</div>
				<div class="table-responsive">
					<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Policy Group</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="policyGroupPayroll" onchange="getUserListOnSelectPolicy(this.value)"></select>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>Employee</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="empNamePayroll">
													<option value="" label="Select Employee">Select Employee</option>
												</select>
											</span>
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" onclick="getEmpPayRoll();">View</button></a></label>
					</div>
					<div class="row" id="viewUserPayroll" style="padding:10px;">
							<%@ include file="sampleUserPayroll.jsp" %>
					</div>
					</div>					
				</div>
			</div>
		</div>
		<div class="panel panel-primary" id="viewEmpPendingTDSDiv" aria-labelledby="viewEmpPendingTDSDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Pending TDS Declaration::</strong>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('empPendingTDSTT36','popover36');" tabindex="0" data-toggle="popover36" data-trigger="focus" data-placement="bottom" data-html="true"></button>
					<input class="input-sm textInput form-control" style="width:30%;display:inline" id="globalFilterInputPendingTDS" placeholder="Global search input...">
				</div>
				<div class="table-responsive">
					<table class="table table-bordered" id="empPayrollMapTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">S No</th>
								<th class="text-center">Username</th>
								<th class="text-center">Emp Name</th>
								<th class="text-center">Policy Group</th>
								<th class="text-center">Taxable Income (INR)</th>
								<th class="text-center">Total Tax Annual (INR)</th>
								<th class="text-center">Submitted On</th>
								<th class="text-center">Status</th>
								<th class="text-center nowrap">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_empPayrollMapItemApp">
							<tr>
								<td>1</td>
								<td>SITS110</td>
								<td>Test User</td>
								<td>SUPRA-Noida</td>
								<td>350000</td>
								<td>3500</td>
								<td>2018-03-30</td>
								<td>Pending</td>
								<td>Active</td>
								<td item-align="center">
									<button class="btn btn-sm">View</button>
									<button class="btn btn-sm" id="pendingTDSBtn" onclick="addRemarkTDS(this.id);">Update</button>
								</td>
							</tr>
						</tbody>
					</table>					
				</div>
			</div>
		</div>	
		<div class="panel panel-primary" id="viewGeneratePayslipDiv" aria-labelledby="viewGeneratePayslipDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Generate Payslip</strong>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('generateEmpPayslipTT37','popover37');" tabindex="0" data-toggle="popover37" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				</div>
				<div class="table-responsive">
					<div class="form-inline panel panel-primary" style="margin-left:10px;">
									 	<div class="form-group" style="margin-top: 10px;margin-left:5px;">
											<label><strong>Payroll</strong></label> <br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="genPayslipPayroll">
													<option value="" label="Select Payroll">Select Payroll</option>
												</select>
											</span>
						</div>
						<div class="form-group" style="margin-top: 10px;margin-left:5px;">					
											<label><strong>Payroll Period</strong></label><br />
											<span style="padding-left: 0px; margin-top: 5px;">
												<select	class="form-control" id="payrollGenPeriod">
													<option value="" label="Select Period">Select Period</option>
												</select>
											</span>
						</div>
					<div class="form-group" style="margin-top: 10px;margin-left:15px;">
						<label><a href="#"><button type="button" class="btn btn-primary" style="margin-top:30px;" onclick="viewEmpsPayslip();">Go</button></a></label>
					</div>
					<div class="row" id="viewEmpsPayslipDiv" style="padding:10px;">
							<div class="table-responsive">
					<table class="table table-bordered" id="empPayrollMapTbl" scrolling="auto">
						<thead>
							<tr>
								<th class="text-center">#</th>
								<th class=""><input type='checkbox' /></th>
								<th class="">Username</th>
								<th class="">Emp Name</th>
								<th class="">Start Date</th>
								<th class="">End Date</th>
								<th class="">Gross Pay</th>
								<th class="">Deduction</th>
								<th class="">Net Payout</th>
								<th class="nowrap">Action</th>
							</tr>
						</thead>
						<tbody id="tab_logic_empPayrollMapItemApp"></tbody>
					</table>					
				</div>			
					</div>
					</div>					
				</div>
			</div>
		</div>
		<div class="panel panel-primary" id="viewPayrollSetupDiv" aria-labelledby="viewPayrollSetupDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Payroll Configuration</strong>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('payrollConfigurationTT38','popover38');" tabindex="0" data-toggle="popover38" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				</div>
				<div class="table-responsive">
					<div class="row" id="viewUserTDSdiv" style="padding:10px;">
							<%@ include file="payrollSetup.jsp" %>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-primary" id="viewPayrollReportDiv" aria-labelledby="viewPayrollReportDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Payroll Report</strong>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('payrollReportTT39','popover39');" tabindex="0" data-toggle="popover39" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				</div>
				<div class="table-responsive">
					<div class="row" id="viewUserTDSdiv" style="padding:10px;">
						<img src='<c:url value="/resources/images/e-ims-payroll.jpg" />' width="98%" height="100%" style="margin:10px;" />
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-primary" id="viewCreateLoanRequestDiv" aria-labelledby="viewCreateLoanRequestDiv" style="width: 100%;display: none;" scrolling="auto">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Create New Loan Request</strong>
					<button style="border:none;background:#3e5871;" class='glyphicon glyphicon-question-sign' onclick="setPopoverData('newLoanRequestTT40','popover40');" tabindex="0" data-toggle="popover40" data-trigger="focus" data-placement="bottom" data-html="true"></button>
				</div>
				<div class="table-responsive">
					<div class="row" id="viewUserLoandiv" style="padding:10px;">
						
					</div>
				</div>
			</div>
		</div>