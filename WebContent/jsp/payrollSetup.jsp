<ul id = "myTab" class = "nav nav-tabs custab" style="margin:5px;">
   <li class = "active"><a href = "#createPayrollPeriod" data-toggle="tab" height="14px"><strong>Payroll Period</strong></a></li>
   <li><a href = "#createPayrollType" data-toggle="tab" height="14px"><strong>Payroll Type</strong></a></li>
   <li><a href = "#payroll" data-toggle="tab" height="14px"><strong>Payroll Detail</strong></a></li>
   <li><a href = "#elementGroup" data-toggle = "tab" height="14px"><strong>Element Group</strong></a></li>
   <li><a href = "#payrollElement" data-toggle="tab" height="14px"><strong>Payroll Element</strong></a></li>
</ul>

<div id = "myTabContent" class = "tab-content">
<div class = "tab-pane fade in active" id = "createPayrollPeriod">
   <div class="InnerTab">
    <div class="row">
        <div class="col-md-12">
            <div class="panel-group" id="accordion">
			<button class="btn-sm btn-primary greeBtn" data-toggle="collapse" data-parent="#accordion" href="#collapseOneY">Create Payroll Period <span class="glyphicon glyphicon-plus"></span></button>
                <div class="panel panel-default">
                    </div>
                    <div id="collapseOneY" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                	<div class="form-group">
                                        <label for="periodSDate">Start Date*</label>
                                        <input type="text" class="form-control" id="createPRollPeriodSDate" placeholder="Period Start Date" onkeypress="return false;" />
                                    </div>
                                    <div class="form-group">
                                        <label for="periodName">Period Name*</label>
                                        <input type="text" class="form-control" id='createPRollPeriodName' placeholder="Period Name..." />                                     
                                    </div>
                                  </div>  
                                 <div class="col-md-6">
	                                 <div class="form-group">
	                                        <label>End Date*</label>
	                                        <input type="text" class="form-control" id="createPRollPeriodEDate" placeholder="Period End Date" onkeypress="return false;" />
	                                  </div>
	                                  <div class="form-group">
	                                        <label>Status*</label>
	                                        <select class="form-control" id="createPRollPeriodStatus">
												<option value="Open">Open</option>
												<option value="Closed">Closed</option>
												<option value="Never_Opened">Never_Opened</option>
											</select>
	                                  </div>    
                                </div>
								<div class="col-md-12 text-right"><button class="btn-sm btn-primary greeBtn" onclick="createNewPayrollPeriod();">Create</button></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
      <div class="table-responsive">
		 <div class="form-inline panel panel-primary" style="margin:0px;padding-bottom:0px;box-shadow:none;">
			<div class="form-group" id="tab_logic_payrollPeriodTbl" style="margin:0px;width:100%">
				<table class="table table-bordered">
					<theader>
						<tr>
							<th>S No</th>
							<th>Period Name</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Status</th>
							<th>Last Updated On</th>
							<th>Last Updated By</th>
							<th class="text-center">Action</th>
						</tr>
					</theader>
					<tbody id="tab_logic_payrollPeriodItemApp"></tbody>
				</table>
			</div>
		</div>
	 </div>
   </div>
<div class = "tab-pane fade" id = "createPayrollType">
   <div class="InnerTab">
    <div class="row">
        <div class="col-md-12">
            <div class="panel-group" id="accordion">
			<button class="btn-sm btn-primary greeBtn" data-toggle="collapse" data-parent="#accordion" href="#collapseOneX">Create Payroll Type <span class="glyphicon glyphicon-plus"></span></button>
                <div class="panel panel-default">
                    </div>
                    <div id="collapseOneX" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="typeName">Type</label>
                                        <input type="text" class="form-control" id='createPRollType' placeholder="Payroll Type Name..." required />                                     
                                    </div>
                                </div>
                                <div class="col-md-6">    
                                    <div class="form-group">
                                        <label for="typeDesc">Description</label>
                                        <input type="text" class="form-control" id="createPRollTypeDesc" placeholder="Payroll Type Description..." required />
                                    </div>

                                </div>
								<div class="col-md-12 text-right"><button class="btn-sm btn-primary greeBtn" onclick="createNewPayrollType();">Create</button></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
      <div class="table-responsive">
		 <div class="form-inline panel panel-primary" style="margin:0px;padding-bottom:0px;box-shadow:none;">
			<div class="form-group" style="margin:0px;width:100%">
				<table class="table table-bordered" id="tab_logic_payrollTypeTbl">
					<theader>
						<tr>
							<th>S No</th>
							<th>Type Name</th>
							<th>Name</th>
							<th>Is active</th>
							<th>Last Updated On</th>
							<th>Last Updated By</th>
							<th class="text-center">Action</th>
						</tr>
					</theader>
					<tbody id="tab_logic_payrollTypeItemApp"></tbody>
				</table>
			</div>
		</div>
	 </div>
   </div>
   <div class = "tab-pane fade" id = "payroll">
   <div class="InnerTab">
    <div class="row">
        <div class="col-md-12">
            <div class="panel-group" id="accordion">
			<button class="btn-sm btn-primary greeBtn" data-toggle="collapse" data-parent="#accordion" href="#collapseOne1">Create New Payroll <span class="glyphicon glyphicon-plus"></span></button>
                <div class="panel panel-default">
                    </div>
                    <div id="collapseOne1" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="grant1">Name</label>
                                        <input type="text" class="form-control" id="createPayrollName" placeholder="Payroll Name..." required />                                     
                                    </div>
                                </div>    
                                <div class="col-md-6">    
                                    <div class="form-group">
                                        <label for="grant2">Payroll Type</label>
                                        <select class="form-control" id="payrollCreateType">
											<option value="" label="Select Type">Select Type</option>
										</select>
                                    </div>
                                </div>
								<div class="col-md-12 text-right"><button class="btn-sm btn-primary greeBtn" onclick="createNewPayrollName();">Create</button></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
      <div class="table-responsive">
		 <div class="form-inline panel panel-primary" style="margin:0px;padding-bottom:0px;box-shadow:none;">
			<div class="form-group" style="margin:0px;width:100%">
				<table class="table table-bordered" id="tab_logic_payrollNameTbl">
					<theader>
						<tr>
							<th>S No</th>
							<th>Payroll Name</th>
							<th>Type</th>
							<th>Is active</th>
							<th>Last Updated On</th>
							<th>Last Updated By</th>
							<th class="text-center">Action</th>
						</tr>
					</theader>
					<tbody id="tab_logic_payrollNameItemApp"></tbody>
				</table>
			</div>
		</div>
	 </div>
   </div>
   
   
   
   <div class = "tab-pane fade" id="payrollElement">
   <div class="InnerTab">
    <div class="row">
        <div class="col-md-12">
            <div class="panel-group" id="accordion2">
			<button class="btn-sm btn-primary greeBtn"  data-toggle="collapse" data-parent="#accordion2" href="#collapseOne2">Create Payroll Element <span class="glyphicon glyphicon-plus"></span></button>
                <div class="panel panel-default">
                    </div>
                    <div id="collapseOne2" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                            <form id="payrollElemFormId" method="post"
				modelAttribute="sesM11Element" enctype="multipart/form-data" name="payrollElemFormId">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="grant1">Element Name</label>
                                        <input type="text" class="form-control" id='payrollElemName' name="elementName" placeholder="Name visible on payroll" required />                                     
                                    </div>
                                    <div class="form-group">
                                        <label for="grant1">Description</label>
                                        <input type="text" class="form-control" id='payrollElemDesc' name="description" placeholder="Element Description" required />                                     
                                    </div>
                                    <div class="form-group">
                                        <label for="grant1">Tax Exemption</label>
                                        <select class="form-control" id="payTaxExemp" name="taxExemted">
											<option value="N">N</option>
                                        	<option value="Y">Y</option>
                                        </select>                                     
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="grant1">Elemenet Type</label>
                                        <select class="form-control" id="payElemType" name="elementType">
											<option value="E">Earning</option>
                                        	<option value="D">Deduction</option>
                                        </select>                                     
                                    </div>
                                    <div class="form-group">
                                        <label for="grant1">Pay Frequency</label>
                                        <select class="form-control" id="payElementFrequency" name="payFrequency">
                                        	<option value="D">Daily</option>
                                        	<option value="W">Weekly</option>
                                        	<option value="M">Monthly</option>
                                        	<option value="Y">Yearly</option>
                                        	<option value="O">One Time</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="grant1">Bill Verification</label>
                                        <select class="form-control" id="payElemVerficationFlag" name="billVerification">
											<option value="N">N</option>
                                        	<option value="Y">Y</option>
                                        </select>                                     
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="grant1">Element Alias*(Ex: $BASIC$)</label>
                                        <input type="text" class="form-control" name="formulaAlias" id='payElemAlias' placeholder="Alias start-end with $ without spaces" required />                                     
                                    </div>
                                    <div class="form-group">
                                        <label for="grant1">Formula Type</label>
                                        <select class="form-control" id="payElemFormulaType" name="formulaType" disabled>
											<option value="">Select</option>
											<option value="I">Inline</option>
                                        	<option value="J">Java Function</option>
                                        	<option value="D">DB Function</option>
                                        </select>                                     
                                    </div>
                                    <div class="form-group">
                                       <label>Formula Text</label>
                                       <input type="text" class="form-control" id="payrollElemFormulaText" name="formulaText" placeholder="Formula Expression" disabled />                                     
                                    </div>
                                </div>
                                <div class="col-md-6">
                                	<div class="form-group">
                                        <label>Formula Flag*</label>
                                        <select class="form-control" id="formulaFlag" name="formulaFlag" onchange="enableDisableFormulaFields(this.value);return false;">
											<option value="M">Mannual</option>
                                        	<option value="F">Formula</option>
                                        </select>                                     
                                    </div>
                                    <div class="form-group">
                                        <label>Max Limit*</label>
                                        <input type="text" class="form-control" id="payrollElemMaxLimit" name="maxLimit" value="99999" required />                                     
                                    </div>
                                </div>
                                <div class="col-md-12 text-right"><button class="btn-sm btn-primary greeBtn" onclick="createPayrollElement('payrollElemFormId');return false;">Create</button></div>
                                </form>
								
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
      <div class="table-responsive">
		 <div class="form-inline panel panel-primary" style="margin:0px;padding-bottom:0px;box-shadow:none;">
			<div class="form-group" id="payrollList" style="margin:0px;width:100%">
				<table class="table table-bordered">
					<theader>
						<tr>
							<th>S No</th>
							<th>Name</th>
							<th>Description</th>
							<th>Element Group</th>
							<th>Type</th>
							<th>Formula</th>
							<th>Is active</th>
							<th>Last Updated On</th>
							<th>Last Updated By</th>
							<th class="nowrap text-center">Action</th>
						</tr>
					</theader>
					<tbody id="tab_logic_payrollElementItemApp"></tbody>
				</table>
			</div>
		</div>
	 </div>
   </div>
   
   <div class = "tab-pane fade" id = "elementGroup">
   <div class="InnerTab">
    <div class="row">
        <div class="col-md-12">
            <div class="panel-group" id="accordion3">
			<button class="btn-sm btn-primary greeBtn" data-toggle="collapse" data-parent="#accordion3" href="#collapseOne3">Create Element Group <span class="glyphicon glyphicon-plus"></span></button>
                <div class="panel panel-default">
                    </div>
                    <div id="collapseOne3" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Group Name</label>
                                        <input type="text" class="form-control" id='createPayElementGroup' placeholder="Group Name" required />                                     
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Select Elements</label>
                                        <div id="availablePayElementId"></div>	
                                    </div>
                                </div>
								<div class="col-md-12 text-right"><button class="btn-sm btn-primary greeBtn" onclick="createElementGroup();return false;">Create</button></div>
                            </div>
                         </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
      <div class="table-responsive">
		 <div class="form-inline panel panel-primary" style="margin:0px;padding-bottom:0px;box-shadow:none;">
			<div class="form-group" style="margin:0px;width:100%">
				<table class="table table-bordered">
					<theader>
						<tr>
							<th>S No</th>
							<th>Name</th>
							<th>Is active</th>
							<th>Last Updated On</th>
							<th>Last Updated By</th>
							<th class="text-center">Action</th>
						</tr>
					</theader>
					<tbody id="tab_logic_payrollGroupItemApp"></tbody>
				</table>
			</div>
		</div>
	 </div>
   </div>
</div>