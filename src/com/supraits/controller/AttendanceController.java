package com.supraits.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
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

import com.supraits.service.AttendanceService;
import com.supraits.service.AttendenceReportImpl;
import com.supraits.service.TimesheetServiceImpl;
import com.supraits.service.impl.AttendanceServiceImpl;
import com.supraits.viewBean.LeaveRequestBean;


@Controller
public class AttendanceController
{
	AttendanceService guiObj;
	AttendenceReportImpl reportImpl;
	//TimesheetServiceImpl guiObjTS;
	@Autowired
	public void setAttendanceServiceImpl(AttendanceServiceImpl objAttendanceServiceImpl) {
		this.guiObj = objAttendanceServiceImpl;
	}
	@Autowired
	public void setAttendanceReportImpl(AttendenceReportImpl objAttendanceReportImpl) {
		this.reportImpl = objAttendanceReportImpl;
	}
	
	@RequestMapping(value="/getDataFromAttendanceVMlogin", method = RequestMethod.POST)
	public @ResponseBody String updateDB(@RequestParam(value="data", required=false) final String data)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status = false;
		try{
			System.out.println("Data size received from VM server"+data.length());
			if(data.length() != 0){
				Thread t = new Thread(){
					@Override
					public void run(){
						try {
							boolean status = guiObj.updateAttendanceData(data);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				};
				t.start();
			}
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserOwnCurrentMonthAttendance", method = RequestMethod.POST)
	public @ResponseBody String fetchUserAttendanceData(HttpServletRequest request, HttpSession session
			,@RequestParam(value="firstDay", required=true)String firstDay,@RequestParam(value="lastDay", required=true)String lastDay)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUserAttendanceData(session,firstDay,lastDay,String.valueOf(session.getAttribute("loggedInUser")));
			System.out.println(jsonarr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserstMonthAttendance", method = RequestMethod.POST)
	public @ResponseBody String fetchAllUserAttendanceData(HttpServletRequest request, HttpSession session
			,@RequestParam(value="firstDay", required=true)String firstDay,@RequestParam(value="lastDay", required=true)String lastDay,@RequestParam(value="username", required=true)String username)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUsersAttendanceData(session,firstDay,lastDay,"",username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserAttendanceBasedOnParam", method = RequestMethod.POST)
	public @ResponseBody String getUserAttendanceBasedOnParam(HttpServletRequest request, HttpSession session
			,@RequestParam(value="startDay", required=true)String startDay,@RequestParam(value="endDay", required=true)String endDay
			,@RequestParam(value="projectId", required=true)String projectId,@RequestParam(value="username", required=true)String username)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUsersAttendanceData(session,startDay,endDay,projectId,username);
			if(username == "")
				reportImpl.createReportProjWeekly(startDay, endDay, username,projectId);
			else
				reportImpl.createOneUserMonthlyAttendanceReport(jsonarr,username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/downloadUserAttendenceRepo", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getFile(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws FileNotFoundException {
		String rootPath = System.getProperty("user.dir");
		response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
			tempString = "Attendence_Report_"+tempString;
			System.out.println(tempString);
		dir = new File(rootPath + File.separator + tempString);
		response.setHeader("Content-disposition","attachment; filename=" + tempString);
		response.setContentLength(new Long(dir.length()).intValue());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value="/getUserWeeklyAttendanceRprt", method = RequestMethod.POST)
	public @ResponseBody String getUserWeeklyAttendanceRprt(HttpServletRequest request, HttpSession session
			,@RequestParam(value="startDay", required=true)String startDay,@RequestParam(value="endDay", required=true)String endDay,@RequestParam(value="username", required=true)String username)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUsersWeeklyAttendanceData(session,startDay,endDay,username);
			reportImpl.createWeeklyReportTotalAttendance(jsonarr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/downloadUserWeeklyAttendenceRepo", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getWeeklyAttnFile(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws FileNotFoundException {
		String rootPath = System.getProperty("user.dir");
		response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
			tempString = "Attendence_Weekly_Report_"+tempString;
			System.out.println(tempString);
		dir = new File(rootPath + File.separator + tempString);
		response.setHeader("Content-disposition","attachment; filename=" + tempString);
		response.setContentLength(new Long(dir.length()).intValue());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value="/submitMOAFData", method = RequestMethod.POST)
	public @ResponseBody String submitMOAFData(HttpServletRequest request, HttpSession session,
			@RequestParam(value="date", required=true)String date,
			@RequestParam(value="direction", required=true)String direction,
			@RequestParam(value="inTime", required=true)String inTime,
			@RequestParam(value="outTime", required=true)String outTime,
			@RequestParam(value="reason", required=true)String reason,
			@RequestParam(value="moafCategory", required=true)String moafCategory)
	{
		JSONArray jsonarr = new JSONArray();
		String statusMsg = "Try after some time.";
		try{
			statusMsg = guiObj.sendMOAFForApproval(session,date,direction,inTime,outTime,reason,moafCategory);
			jsonarr.put(statusMsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUsersPendingMOAFList", method = RequestMethod.POST)
	public @ResponseBody String getUsersPendingMOAFList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUserAttendanceMOAFData(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUsersPendingMOAFRequest", method = RequestMethod.POST)
	public @ResponseBody String getUsersPendingMOAFRequest(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUserPendingRequest(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/approveMOAFData", method = RequestMethod.POST)
	public @ResponseBody String approveUserMOAFData(HttpServletRequest request, HttpSession session,
			@RequestParam(value="moafid", required=true)String moafid,
			@RequestParam(value="approveFlag", required=true)String approveFlag)
	{
		JSONArray jsonarr = new JSONArray();
		String status = "Try after some time";
		try{
				status = guiObj.validatePendingMOAFRequest(session,moafid,approveFlag);
				jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/yearlyUserReport", method = RequestMethod.POST)
	public String yearlyUserReport(HttpSession session,HttpServletRequest request, HttpServletResponse response,ModelMap model) throws SQLException {
		//session.setAttribute("currentLeaveReqNumber",guiObj.generateLeaveRequestNo(session));
		String uid = request.getParameter("uid");
		String year = request.getParameter("year");
		if(!guiObj.checkUserAuthorization(session,uid))
			uid = session.getAttribute("loggedInUser").toString();
		JSONArray arr = guiObj.generateUserYearlyAttnData(session,uid,year);
		model.put("username",uid);
		model.put("fullname",guiObj.getEmpName(uid));
		model.put("RM",guiObj.getEmpRMName(uid));
		model.put("reportyear",year);
		model.put("datasource",arr);
		return "userYearlyAttendance";
	}
	@RequestMapping(value="/getUserExceptionAttendanceRprt", method = RequestMethod.POST)
	public @ResponseBody String getUserExceptionAttendanceRprt(HttpServletRequest request, HttpSession session
			,@RequestParam(value="startDay", required=true)String startDay,@RequestParam(value="endDay", required=true)String endDay,@RequestParam(value="username", required=true)String username)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUsersExceptionAttendanceData(session,startDay,endDay,username);
			//reportImpl.createWeeklyReportTotalAttendance(jsonarr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
}
