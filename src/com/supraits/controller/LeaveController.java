package com.supraits.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.supraits.service.LeaveReportImpl;
import com.supraits.service.LeaveService;
import com.supraits.service.LeaveServiceImpl;
import com.supraits.viewBean.ExpenseRequestBean;
import com.supraits.viewBean.LeaveRequestBean;
import com.supraits.viewBean.LoginBean;


@Controller
public class LeaveController {
	LeaveService guiObj;
	LeaveReportImpl guiObjReport;
	
	@Autowired
	public void setLeaveServiceImpl(LeaveServiceImpl objLeaveServiceGUI) {
		this.guiObj = objLeaveServiceGUI;
	}
	@Autowired
	public void setLeaveReportImpl(LeaveReportImpl objLeaveReportGUI) {
		this.guiObjReport = objLeaveReportGUI;
	}
	@Autowired
	private LeaveRequestBean leaveRequestBean;
	
	@RequestMapping(value = "/newLeaveRequestToOthers", method = RequestMethod.POST)
	public String newLeaveRequestToOthers(@ModelAttribute("leaveRequestBean") LeaveRequestBean leaveRequestBean,HttpSession session,HttpServletRequest request, HttpServletResponse response,BindingResult result,ModelMap model,@RequestParam(value="uid", required=true)String uid) throws SQLException {
		if(guiObj.checkAuthorization(session,uid)){
			session.setAttribute("currentLeaveReqNumber",guiObj.generateLeaveRequestNo(session,uid));
			Map<String,String> userData = guiObj.getUserDataByUsername(uid);
			model.put("leaveRequestBean", leaveRequestBean);
			model.put("applyLeaveForUser", uid);
			model.put("userData", userData);
			return "newLeaveRequest";
		}else{
			return "AccessDenied";
		}	
	}
	
	
	@RequestMapping(value = "/newLeaveRequest", method = RequestMethod.POST)
	public String newLeaveRequest(@ModelAttribute("leaveRequestBean") LeaveRequestBean leaveRequestBean,HttpSession session,HttpServletRequest request, HttpServletResponse response,BindingResult result,ModelMap model) throws SQLException {
		session.setAttribute("currentLeaveReqNumber",guiObj.generateLeaveRequestNo(session,""));
		model.put("leaveRequestBean", leaveRequestBean);
		model.put("applyLeaveForUser", "");
		return "newLeaveRequest";
	}
	
	
	@RequestMapping(value = "/leaveGetRequestNo", method = RequestMethod.POST)
	public @ResponseBody String leaveGetRequestNo(ModelMap model, HttpServletRequest request, HttpSession session,@RequestParam(value="uid", required=true)String uid)
	{
		JSONObject json = new JSONObject();
		try
		{
			String tmpReqNum = guiObj.generateLeaveRequestNo(session,uid);
			json.put("leaveId", tmpReqNum);
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
		return json.toString();
	}
	@RequestMapping(value="/getAllLeaveTypeList", method = RequestMethod.POST)
	public @ResponseBody String getAllLeaveTypeList(HttpServletRequest request, HttpSession session,@RequestParam(value="uid", required=true)String uid)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getListOfLeaveType(session,uid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getLeaveParamForUser", method = RequestMethod.POST)
	public @ResponseBody String getLeaveParamForUser(HttpServletRequest request, HttpSession session,@RequestParam(value="uid", required=true)String uid)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getLeaveBalance(session,uid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/submitNewLeaveRequest", method = RequestMethod.POST)
	public String submitNewLeaveRequest(@ModelAttribute("leaveRequestBean") LeaveRequestBean leaveRequestBean, BindingResult result, ModelMap model, HttpSession session) throws SQLException
	{	
		model.put("leaveRequestBean", leaveRequestBean);
		leaveRequestBean.setRequestNumber(session.getAttribute("currentLeaveReqNumber").toString());
		boolean status = guiObj.insertNewLeaveRequest(session,leaveRequestBean,true);
		if(status){
			model.put("successMsg", "Leave request has been successfully submitted");
		}else{
			model.put("errorMsg", "Request submission encounterd some problem!");
		}
		return "newLeaveRequest";
	}
	@RequestMapping(value="/getPendingLeaveRequestList", method = RequestMethod.POST)
	public @ResponseBody String getPendingRequestList(HttpServletRequest request, HttpSession session,@RequestParam(value="filterVar", required=true)String filterVar,@RequestParam(value="allFlag", required=true)String allFlag)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAllLeaveRequestList(session,filterVar,allFlag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	
	@RequestMapping(value="/getLeaveDetailByReqNumber", method = RequestMethod.POST)
	public @ResponseBody String getLeaveDetailByReqNumber1(HttpServletRequest request, HttpSession session,@RequestParam(value="reqNumber", required=true)String reqNumber)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getPendingLeaveRequestDetails(session,reqNumber);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	
	
	
	
	@RequestMapping(value="/getAllLeaveList", method = RequestMethod.POST)
	public @ResponseBody String getAllLeaveList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getLeaveList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/downloadLeaveBalance", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getFile(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="policyGroup", required=true)String policyGroup) throws FileNotFoundException {
		
		String rootPath = System.getProperty("user.dir");
		response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
			tempString = guiObjReport.createMonthlyLevaeReport(session,policyGroup);
			System.out.println(tempString);
		dir = new File(rootPath + File.separator + tempString);
		response.setHeader("Content-disposition","attachment; filename=" + tempString);
		response.setContentLength(new Long(dir.length()).intValue());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value="/createNewLMSLeave", method = RequestMethod.POST)
	public @ResponseBody String createNewLMSLeave(HttpServletRequest request, HttpSession session,@RequestParam(value="leaveName", required=true)String leaveName,@RequestParam(value="leaveDesc", required=true)String leaveDesc,@RequestParam(value="policyGroup", required=true)String policyGroup,@RequestParam(value="leaveGroup", required=true)String leaveGroup
			,@RequestParam(value="applyDaysBefore", required=true)String applyDaysBefore
			,@RequestParam(value="cumulativeGroup", required=true)String cumulativeGroup
			,@RequestParam(value="applyByAdmin", required=true)String applyByAdmin
			,@RequestParam(value="applyByManager", required=true)String applyByManager
			,@RequestParam(value="applyByHR", required=true)String applyByHR
			,@RequestParam(value="maxDaysPerRequest", required=true)String maxDaysPerRequest)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.createLMSLeave(session,leaveName,leaveDesc,policyGroup,leaveGroup,applyDaysBefore,cumulativeGroup,applyByAdmin,applyByManager,applyByHR,maxDaysPerRequest);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/updateLMSLeave", method = RequestMethod.POST)
	public @ResponseBody String updateRMSBucket(HttpServletRequest request, HttpSession session,@RequestParam(value="leaveId", required=true)String leaveId,@RequestParam(value="leaveName", required=true)String leaveName,@RequestParam(value="leaveDesc", required=true)String leaveDesc
			,@RequestParam(value="leaveMinDaysBefore", required=true)String leaveMinDaysBefore
			,@RequestParam(value="leaveApplyMaxDays", required=true)String leaveApplyMaxDays
			,@RequestParam(value="leaveActiveFlag", required=true)String leaveActiveFlag
			,@RequestParam(value="applyByAdmin", required=true)String applyByAdmin
			,@RequestParam(value="applyByManager1", required=true)String applyByManager1
			,@RequestParam(value="applyByHR", required=true)String applyByHR
			,@RequestParam(value="cumulativegroup", required=true)String cumulativegroup)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.updateLMSLeaveName(session,leaveName,leaveId,leaveDesc,leaveMinDaysBefore
					,leaveApplyMaxDays,leaveActiveFlag,applyByAdmin,applyByManager1,applyByHR,cumulativegroup);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/deleteLMSLeave", method = RequestMethod.POST)
	public @ResponseBody String deleteLMSLeave(HttpServletRequest request, HttpSession session,@RequestParam(value="leaveId", required=true)String leaveId)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.removeLMSLeave(session,leaveId);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getQuarterLeaveList", method = RequestMethod.POST)
	public @ResponseBody String getQuarterLeaveList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getQuarterLeaveListBandBased(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/updateLeaveRequestStatus", method = RequestMethod.POST)
	public @ResponseBody String updateRMSRequestStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="reqNumber", required=true)String reqNumber,@RequestParam(value="approveFlag", required=true)String approveFlag,@RequestParam(value="reqStatus", required=true)String reqStatus,@RequestParam(value="remark", required=true)String remark)
	{
		JSONArray jsonarr = new JSONArray();
		String statusString;
		//System.out.println(reqNumber+approveFlag+reqStatus+remark);
		try{
			statusString = guiObj.updateLMSRemarkAndStatus(session,reqNumber,approveFlag,reqStatus,remark);
			jsonarr.put(statusString);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();   
	}
	@RequestMapping(value="/getQuarterLeaveBalance", method = RequestMethod.POST)
	public @ResponseBody String getQuarterLeaveBalance(HttpServletRequest request, HttpSession session,@RequestParam(value="policyGroup", required=true)String policyGroup)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUsersLeaveBalance(session,policyGroup);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/uploadBalanceSheet",method=RequestMethod.POST)
	public @ResponseBody String saveimage(@RequestParam MultipartFile file,HttpSession session,HttpServletRequest request) throws Exception
	{
		String policyGroup = request.getParameter("policyGroupName");
		System.out.println("Policy Group"+policyGroup);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(System.getProperty("user.dir")));
		 

		String uploadPath = System.getProperty("user.dir");
		System.out.println(uploadPath);	    

		byte[] bytes = file.getBytes();
		BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(uploadPath + File.separator + file.getOriginalFilename())));
		stream.write(bytes);
		stream.flush();
		stream.close();
		guiObjReport.uploadLeaveBalanceData(policyGroup);
		return "true";
	}
	@RequestMapping(value = "/checkBalanceUpdationFlag", method = RequestMethod.POST)
	public @ResponseBody String checkBalanceUpdationFlag(HttpServletRequest request, HttpSession session,@RequestParam(value="uid", required=true)String uid)
	{	
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUserLeaveBalanceUpdationFlag(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/updateBalanceUpdationFlag", method = RequestMethod.POST)
	public @ResponseBody String updateBalanceUpdationFlag(HttpServletRequest request, HttpSession session,@RequestParam(value="updateFlag", required=true)String updateFlag,@RequestParam(value="uid", required=true)String uid)
	{	
		boolean updateStatus = false;
		try{
			updateStatus = guiObj.changeUserLeaveBalanceUpdationFlag(session,updateFlag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(updateStatus);
	}
	
	@RequestMapping(value = "/checkPrevAppliedLeaveList", method = RequestMethod.POST)
	public @ResponseBody String checkPrevAppliedLeaveList(HttpServletRequest request, HttpSession session,@RequestParam(value="dateList[]", required=true)String[] dateList,@RequestParam(value="uid", required=true)String uid)
	{	
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.checkUserAppliedLeaveList(session,Arrays.asList(dateList),uid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getAllUnpaidLeaveType", method = RequestMethod.POST)
	public @ResponseBody String getAllUnpaidLeaveType(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUnpaidLeaveList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getApplyBeforeLeaveParam", method = RequestMethod.POST)
	public @ResponseBody String getApplyBeforeLeaveParam(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getApplyBeforeLeaveParam(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getMaxDayForEachRequestLeave", method = RequestMethod.POST)
	public @ResponseBody String getMaxDayForEachRequestLeave(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getMaxDayForRequestLeaveParam(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/viewLeaveReport", method = RequestMethod.POST)
	public @ResponseBody String viewLeaveReport(HttpServletRequest request, HttpSession session,@RequestParam(value="username", required=true)String username,@RequestParam(value="hrPolicyGroup", required=true)String hrPolicyGroup,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="leaveStatus", required=true)String leaveStatus)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.viewLeaveReport(session,username,hrPolicyGroup,startDate,endDate,leaveStatus);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserOwnLeaveBalance", method = RequestMethod.POST)
	public @ResponseBody String getUserOwnLeaveBalance(HttpServletRequest request, HttpSession session,@RequestParam(value="username", required=true)String username)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUserLeaveData(session,username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserListBasedOnPolicyGroup", method = RequestMethod.POST)
	public @ResponseBody String getUserListBasedOnPolicyGroup(HttpServletRequest request, HttpSession session,@RequestParam(value="hrPolicyGroup", required=true)String hrPolicyGroup)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUserListBasedOnPolicyGroup(session,hrPolicyGroup);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/currentLeaveReport", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getLeaveReportFile(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws FileNotFoundException {
		String rootPath = System.getProperty("user.dir");
		response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
			tempString = "LeaveReport" + tempString;
		dir = new File(rootPath + File.separator + tempString);
		response.setHeader("Content-disposition","attachment; filename=" + tempString);
		response.setContentLength(new Long(dir.length()).intValue());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value="/updateUserOwnLeaveBalance", method = RequestMethod.POST)
	public @ResponseBody String updateUserOwnLeaveBalance(HttpServletRequest request, HttpSession session
			,@RequestParam(value="username", required=true)String username
			,@RequestParam(value="updatedLeave", required=false)JSONArray updatedLeave)
	{
		System.out.println(updatedLeave);
		JSONArray jsonarr = new JSONArray();
		try{
			String status = guiObj.updateUserLeaveBalance(session,username,updatedLeave);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/sendMeetingRequest", method = RequestMethod.POST)
	public @ResponseBody String sendMeetingRequest(HttpServletRequest request, HttpSession session
			,@RequestParam(value="type", required=true)String type
			,@RequestParam(value="requestNumber", required=true)String requestNumber
			,@RequestParam(value="toUser[]", required=true)String[] toUser
			,@RequestParam(value="ccUser[]", required=true)String[] ccUser
			,@RequestParam(value="toMember[]", required=true)String[] toMember
			,@RequestParam(value="meetingSTime", required=false)String meetingSTime
			,@RequestParam(value="meetingETime", required=false)String meetingETime
			,@RequestParam(value="subject", required=false)String subject
			,@RequestParam(value="message", required=true)String message
			,@RequestParam(value="location", required=false)String location)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			List<String> to = Arrays.asList(toUser);
			List<String> cc = Arrays.asList(ccUser);
			List<String> member = Arrays.asList(toMember);
			if(to.size()>0 && to != null){
				for(String mail:to){
					InternetAddress email = new InternetAddress(mail);
				      email.validate();
				}
			}
			if(cc.size()>0 && cc != null){
				for(String mail:to){
					InternetAddress email = new InternetAddress(mail);
				      email.validate();
				}
			}
			if(member.size()>0 && member != null){
				for(String mail:to){
					InternetAddress email = new InternetAddress(mail);
				      email.validate();
				}
			}
			String status = guiObj.sendMeetingRequestToUser(session,requestNumber,meetingSTime,meetingETime,subject,location,toUser,ccUser,toMember,message,type);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
			jsonarr.put("One or more invalid email address");
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/fetchMeetingHistory", method = RequestMethod.POST)
	public @ResponseBody String fetchMeetingHistory(HttpServletRequest request, HttpSession session,@RequestParam(value="reqNum", required=true)String reqNum)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUsersMeetingHistory(session,reqNum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/fetchActiveCumulativeGroup", method = RequestMethod.POST)
	public @ResponseBody String fetchActiveCumulativeGroup(HttpServletRequest request, HttpSession session,@RequestParam(value="policyGroup", required=false)String policyGroup)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getCumulativeLeaveGroupList(session,policyGroup);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserListForLeaveApply", method = RequestMethod.POST)
	public @ResponseBody String getUserListForLeaveApply(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUserListForLeave(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getEmpListOnLeaveofReqNumber", method = RequestMethod.POST)
	public @ResponseBody String getEmpListOnLeaveofReqNumber(HttpServletRequest request, HttpSession session,@RequestParam(value="requestNumber", required=true)String requestNumber,@RequestParam(value="projectId", required=true)String projectId)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getOtherUsersListOnLeave(session,requestNumber,projectId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getEmpProjectList", method = RequestMethod.POST)
	public @ResponseBody String getEmpProjectList(HttpServletRequest request, HttpSession session,@RequestParam(value="username", required=true)String username)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUsersAssignedProject(session,username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserListForLeaveMeeting", method = RequestMethod.POST)
	public @ResponseBody String getUserListForLeaveMeeting(HttpServletRequest request, HttpSession session,@RequestParam(value="searchParam", required=true)String searchParam)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUserListForLeaveMeeting(session,searchParam);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getLeaveBalHistoryOfUser", method = RequestMethod.POST)
	public @ResponseBody String getLeaveBalHistoryOfUser(HttpServletRequest request, HttpSession session
			,@RequestParam(value="username", required=true)String username
			,@RequestParam(value="leaveCode", required=false)String leaveCode)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUserLeaveBalanceHistory(session,username,leaveCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
}
