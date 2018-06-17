package com.supraits.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DomainCombiner;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Image;
import com.supraits.delegate.LoginDelegate;
import com.supraits.service.TimesheetReportImpl;
import com.supraits.service.TimesheetServiceImpl;
import com.supraits.viewBean.EmployeeBean;
import com.supraits.viewBean.ExpenseRequestBean;
import com.supraits.viewBean.LoginBean;
import com.supraits.viewBean.ProjectBean;
import com.supraits.viewBean.RegisterBean;




@Controller
public class LoginController
{
		TimesheetServiceImpl guiObj;
		TimesheetReportImpl guiObjReport;
		
		@Autowired
		private LoginDelegate loginDelegate;
		
		@Autowired
		private ProjectBean projectBean;
		
		@Autowired
		public void setTimesheetServiceImpl(TimesheetServiceImpl objTimesheetServiceGUI) {
			this.guiObj = objTimesheetServiceGUI;
		}
		
		@Autowired
		public void setTimesheetReportImpl(TimesheetReportImpl objTimesheetReportGUI) {
			this.guiObjReport = objTimesheetReportGUI;
		}
		
		@Value("${MODIFY_USER}")
		private String MODIFY_USER;
		
		@RequestMapping(value={"/login", "/"},method=RequestMethod.GET)
		public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,RegisterBean registerBean)
		{
			ModelAndView model= null;
			try{
				HttpSession session = request.getSession(false);
				if(session.getAttribute("loggedInUser").toString().equalsIgnoreCase("") || session == null){
					model = new ModelAndView("login");
					model.addObject("loginBean", loginBean);
					model.addObject("registerBean",registerBean);
				}else{
					model = new ModelAndView("homenew2");
				}
			}catch(Exception e){
				model = new ModelAndView("login");
				model.addObject("loginBean", loginBean);
				model.addObject("registerBean",registerBean);
			}
			return model;
		}
		@RequestMapping(value="/login",method=RequestMethod.POST)
		public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute("loginBean")LoginBean loginBean)
		{
				ModelAndView model= null;
				try
				{
						loginBean = loginDelegate.isValidUser(loginBean);
						//System.out.println(loginBean.getUserStatus());
						if(loginBean.getLoginMessage().equalsIgnoreCase("Login Successfull") && loginBean != null)
						{
							if(!("Active".equalsIgnoreCase(loginBean.getUserStatus()))){
								model = new ModelAndView("login");
								model.addObject(new RegisterBean());
								request.setAttribute("message", "Kindly ask your Manager to activate your id!!");
							}else{
									//System.out.println("User Login Successful**");
									request.setAttribute("loggedInUser", loginBean.getUsername());
									session = request.getSession();
									System.out.println(session);
									session.setAttribute("loggedInUserFullName", loginBean.getFirstName()+ " "+loginBean.getLastName());
									session.setAttribute("loggedInUser", loginBean.getUsername());
									session.setAttribute("loggedInUserCode", loginBean.getUserCode());
									session.setAttribute("loggedinuserrole", loginBean.getRole());
									session.setAttribute("loggedinusergroup", loginBean.getUserGroup());	
									session.setAttribute("reportingManager",guiObj.getEmpName( loginBean.getReportingManager()));
									session.setAttribute("reportingManagerUserName", loginBean.getReportingManager());
									session.setAttribute("hrManager",guiObj.getEmpName( loginBean.getHrManager()));	
									session.setAttribute("loggedInUserProxy", loginBean.getUsernameproxyid());
									session.setAttribute("globalProxyCount", "");
									session.setAttribute("loggedInUserLocation", loginBean.getBaseLocation());
									session.setAttribute("loggedInUserAccUnit", loginBean.getAccountUnit());
									session.setAttribute("loggedInUserDepartment", loginBean.getEmpDepartment());
									session.setAttribute("loggedInUserDesignation", loginBean.getDesignation());
									session.setAttribute("loggedInUserPolicyGroup", loginBean.getPolicyGroup());
									List<String> userGroups = loginDelegate.getUserGroupList(session.getAttribute("loggedInUser").toString());
									Map<String,Boolean> accessHashMap = loginDelegate.getAccessMap(userGroups);
									session.setAttribute("loggedInUserAccessMap", accessHashMap);
									session.setAttribute("loggedInUserGroups", userGroups);
									session.setAttribute("loggedInUserClientId", loginBean.getClientId());
									session.setAttribute("profilePicUrl", getUserProfilePic(session));
								model = new ModelAndView("homenew2");
								guiObj.insertIntoLoginHistory(session,true);
							}	
						}
						else
						{
								model = new ModelAndView("login");
								model.addObject(new RegisterBean());
								request.setAttribute("message", loginBean.getLoginMessage());
						}
							
				}
				catch(Exception e)
				{
				  e.printStackTrace();
				}
				return model;
		}
		@ResponseBody
		@RequestMapping(value="/loginAsProxy",method=RequestMethod.POST)
		public ModelAndView executeProxyLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute("loginBean")LoginBean loginBean,@RequestParam(value="username", required=true)String username,@RequestParam(value="usermail", required=true)String usermail)
		{
				ModelAndView model= null;
				try
				{
						if(username.equalsIgnoreCase(session.getAttribute("loggedInUser").toString()))
							return model = new ModelAndView("homenew2");
						loginBean = guiObj.setProxyUser(session,username,usermail,loginBean);
							if(!("Active".equalsIgnoreCase(loginBean.getUserStatus()))){
								model = new ModelAndView("login");
								model.addObject(new RegisterBean());
								request.setAttribute("message", "Given email yet not activated/registered.");
							}else{
									System.out.println("Proxy User Login Successful**");
									request.setAttribute("loggedInUser", loginBean.getUsername());
									session = request.getSession();
									session.setAttribute("loggedInUserFullName", loginBean.getFirstName()+ " "+loginBean.getLastName());
									session.setAttribute("loggedInUser", loginBean.getUsername());
									session.setAttribute("loggedInUserCode", loginBean.getUserCode());
									session.setAttribute("loggedinuserrole", loginBean.getRole());
									session.setAttribute("loggedinusergroup", loginBean.getUserGroup());	
									session.setAttribute("reportingManager",guiObj.getEmpName( loginBean.getReportingManager()));
									session.setAttribute("reportingManagerUserName", loginBean.getReportingManager());
									session.setAttribute("hrManager",guiObj.getEmpName( loginBean.getHrManager()));
									session.setAttribute("loggedInUserLocation", loginBean.getBaseLocation());
									session.setAttribute("loggedInUserAccUnit", loginBean.getAccountUnit());
									session.setAttribute("loggedInUserDepartment", loginBean.getEmpDepartment());
									session.setAttribute("loggedInUserDesignation", loginBean.getDesignation());
									session.setAttribute("loggedInUserPolicyGroup", loginBean.getPolicyGroup());
									session.setAttribute("loggedInUserProxy", loginBean.getUsernameproxyid());
									session.setAttribute("loggedInUserFullNameProxy", loginBean.getFullnameproxyid());
									session.setAttribute("globalProxyCount", "");
									List<String> userGroups = loginDelegate.getUserGroupList(session.getAttribute("loggedInUser").toString());
									Map<String,Boolean> accessHashMap = loginDelegate.getAccessMap(userGroups);
									session.setAttribute("loggedInUserAccessMap", accessHashMap);
									session.setAttribute("loggedInUserGroups", userGroups);
									session.setAttribute("loggedInUserClientId", loginBean.getClientId());
									model = new ModelAndView("homenew2");
									guiObj.insertIntoLoginHistory(session,true);
							}
				}
				catch(Exception e)
				{
				  e.printStackTrace();
				}
				return model;
		}
		@RequestMapping(value="/backToMainLogin",method=RequestMethod.GET)
		public ModelAndView backToMainLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute("loginBean")LoginBean loginBean,@RequestParam(value="uName", required=true)String uName)
		{
				ModelAndView model= null;
				try
				{
						loginBean = guiObj.setMainUser(session,loginBean,uName);
							if(!("Active".equalsIgnoreCase(loginBean.getUserStatus()))){
								model = new ModelAndView("login");
								model.addObject(new RegisterBean());
								request.setAttribute("message", "Given email yet not activated/registered.");
							}else{
									//System.out.println("Proxy User Login Successful**");
									session.invalidate();
									request.setAttribute("loggedInUser", loginBean.getUsername());
									session = request.getSession();
									session.setAttribute("loggedInUserFullName", loginBean.getFirstName()+ " "+loginBean.getLastName());
									session.setAttribute("loggedInUser", loginBean.getUsername());
									session.setAttribute("loggedInUserCode", loginBean.getUserCode());
									session.setAttribute("loggedinuserrole", loginBean.getRole());
									session.setAttribute("loggedinusergroup", loginBean.getUserGroup());	
									session.setAttribute("reportingManager",guiObj.getEmpName( loginBean.getReportingManager()));	
									session.setAttribute("reportingManagerUserName", loginBean.getReportingManager());
									session.setAttribute("hrManager",guiObj.getEmpName( loginBean.getHrManager()));
									session.setAttribute("loggedInUserLocation", loginBean.getBaseLocation());
									session.setAttribute("loggedInUserAccUnit", loginBean.getAccountUnit());
									session.setAttribute("loggedInUserDepartment", loginBean.getEmpDepartment());
									session.setAttribute("loggedInUserDesignation", loginBean.getDesignation());
									session.setAttribute("loggedInUserPolicyGroup", loginBean.getPolicyGroup());
									List<String> userGroups = loginDelegate.getUserGroupList(session.getAttribute("loggedInUser").toString());
									Map<String,Boolean> accessHashMap = loginDelegate.getAccessMap(userGroups);
									session.setAttribute("loggedInUserAccessMap", accessHashMap);
									session.setAttribute("loggedInUserGroups", userGroups);
									session.setAttribute("loggedInUserProxy", "");
									session.setAttribute("loggedInUserFullNameProxy", "");
									session.setAttribute("globalProxyCount", "1");
									session.setAttribute("loggedInUserDesignation", loginBean.getDesignation());
									session.setAttribute("loggedInUserClientId", loginBean.getClientId());
								model = new ModelAndView("homenew2");
								guiObj.insertIntoLoginHistory(session,true);
							}
				}
				catch(Exception e)
				{
				  e.printStackTrace();
				}
				return model;
		}
		@RequestMapping(value="/registerUser",method=RequestMethod.POST)
		public String registerUser(@ModelAttribute("registerBean") RegisterBean registerBean, BindingResult result, ModelMap model){
			try{
				boolean status = loginDelegate.registerNewUser(registerBean);
				if(status){
					String body ="Please find below your login details:\n Username: "+registerBean.getUserId()+"\n Password: "+registerBean.getPwd()+"You can also use your registered mail id for login.\n\n You will be able to login after approval process.\n";
					guiObj.doSendEmail(registerBean.getUserMail(),"Successfully Registerd.", body);
					model.put("successMsgRegister", "You will be able to login once role is assigned by admin");
				}else{
					model.put("errorMessage", "Please try later");
				}
			}catch(Exception e){
				System.out.println("User registration encountered some problem");
				model.put("message", "Please try later");
				e.printStackTrace();
			}
			return "redirect:/login";
		}
		@RequestMapping(value="/submitTimeSheetAction",headers="Accept=*/*",  produces="application/json", method = RequestMethod.POST)
		public @ResponseBody String submitTimeSheetAction(@ModelAttribute("loginBean") LoginBean loginBean, BindingResult result, ModelMap model, HttpSession session) throws SQLException
		{	
			String statusString = "";
			JSONArray json = new JSONArray();
			try {
				model.put("loginBean", loginBean);
				loginBean.setUsername(session.getAttribute("loggedInUser").toString());
				loginBean.setRole(session.getAttribute("loggedinuserrole").toString());
				statusString = guiObj.insertTimesheet(session,loginBean,true);
				json.put(statusString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return json.toString();
		}
		@RequestMapping(value="/saveTimeSheetAction",headers="Accept=*/*",  produces="application/json", method = RequestMethod.POST)
		public @ResponseBody String saveTimeSheetAction(@ModelAttribute("loginBean") LoginBean loginBean, BindingResult result, ModelMap model, HttpSession session) throws Exception
		{	
			String statusString = "";
			JSONArray json = new JSONArray();
			try {
				model.put("loginBean", loginBean);
				loginBean.setUsername(session.getAttribute("loggedInUser").toString());
				loginBean.setRole(session.getAttribute("loggedinuserrole").toString());
				statusString = guiObj.insertTimesheet(session,loginBean,false);
				json.put(statusString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return json.toString();
		}
		@RequestMapping(value = "/homenew2", method = RequestMethod.GET)
		public String goToHome(@ModelAttribute("loginBean") LoginBean loginBean, BindingResult result, ModelMap model,HttpServletRequest request, HttpSession session)
		{	
			if(session == null){
				return "redirect:/login";
			}else if(session.getAttribute("loggedInUser").toString().equals("")){
				return "redirect:/login";
			}
			request.getSession().setAttribute("globalProxyCount", "");
			boolean status = true;
			if(status){
				model.put("onlyWhenSubSaveSucc", "Timesheet saved/submitted successfully");
			}else{
				model.put("errorMsg", "Encountered error,Try after some time!");
			}
			return "homenew2";
		}
		@RequestMapping(value = "/logoutUser", method = RequestMethod.GET)
		 public String handleRequest(HttpServletRequest request, HttpServletResponse response,LoginBean loginBean,RegisterBean registerBean){
			   try {
				ModelAndView model = new ModelAndView("login");
				   guiObj.insertIntoLoginHistory(request.getSession(),false);
				   request.getSession().invalidate();
				   model.addObject("loginBean", loginBean);
				   model.addObject("registerBean", registerBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			   return "redirect:/login";
		}
		@RequestMapping(value="/renderreportsparam", method = RequestMethod.POST)
		public @ResponseBody String renderreportsparam(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				//jsonarr = guiObj.renderreports(session,constituency,year);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/generateReport", method = RequestMethod.POST)
		public @ResponseBody String generateReport(HttpServletRequest request,HttpServletResponse response, HttpSession session
				,@RequestParam(value="projectId", required=true)String projectId
				,@RequestParam(value="startDate", required=true)String startDate
				,@RequestParam(value="endDate", required=true)String endDate
				,@RequestParam(value="username", required=true)String username
				,@RequestParam(value="taskId", required=true)String taskId
				,@RequestParam(value="rmname", required=true)String rmname
				,@RequestParam(value="status", required=true)String status)
		{
			/*JSONArray jsonarr = new JSONArray();
			JSONObject jsobj = new JSONObject();*/
			JSONArray data = new JSONArray();
			try{
				data = guiObj.getDailyTSReport(projectId,startDate,endDate,username,taskId,rmname,status);
				guiObjReport.generateDailyTSReport(session,data);
				/*
			    boolean fileGenFlag = guiObj.createReportProj(projectId,startDate,endDate,username);
				if(fileGenFlag){
					String tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
					tempString = "Report" + tempString;
					jsobj.put("FileName",tempString);
				}else{
					jsobj.put("FileName", "Please try after some time.");
				}
				*/
			}catch(Exception e){
				e.printStackTrace();
			}
		//return jsonarr.put(jsobj).toString();
			return data.toString();
		}
		@RequestMapping(value = "/downloadDailyTSReport", method = RequestMethod.GET)
		@ResponseBody
		public FileSystemResource downloadDailyTSReport(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws FileNotFoundException {
			String rootPath = System.getProperty("user.dir");
			response.setContentType("application/vnd.ms-excel");
			String tempString = "";
			File dir = null;
			try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
			tempString = "TSDailyReport"+tempString;
			dir = new File(rootPath + File.separator + tempString);
			response.setHeader("Content-disposition","attachment; filename=" + tempString);
			response.setContentLength(new Long(dir.length()).intValue());
			}catch(Exception e){
				e.printStackTrace();
			}
			return new FileSystemResource(dir); 
		}
		@RequestMapping(value="/generateReportWeekly", method = RequestMethod.POST)
		public @ResponseBody String generateReportWeekly(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="status", required=true)String status,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			JSONObject jsobj = new JSONObject();
			try{
				boolean fileGenFlag = guiObjReport.createReportProjWeekly(status,startDate,endDate,username);
				if(fileGenFlag){
					String tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
					tempString = "Status_Report_" + tempString;
					jsobj.put("FileName",tempString);
				}else{
					jsobj.put("FileName", "Please try after some time.");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.put(jsobj).toString();
		}
		@RequestMapping(value="/generateMonthlyReport", method = RequestMethod.POST)
		public @ResponseBody String generateMonthlyReport(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="projectId", required=true)String projectId,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			JSONObject jsobj = new JSONObject();
			String fileGenFlag = "";
			try{
				fileGenFlag = guiObjReport.createMonthlyReportProj(session,projectId,startDate,endDate,username,guiObj);
				if(fileGenFlag.length()>0){
					jsobj.put("FileName",fileGenFlag);
				}else{
					jsobj.put("FileName", "Please try after some time.");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.put(jsobj).toString();
		}
		@RequestMapping(value = "/files", method = RequestMethod.GET)
		@ResponseBody
		public FileSystemResource getFile(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws FileNotFoundException {
			
			String rootPath = System.getProperty("user.dir");
			response.setContentType("application/vnd.ms-excel");
			String tempString = "";
			File dir = null;
			try{
				tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
				tempString = "Report" + tempString;
				//System.out.println(tempString);
			dir = new File(rootPath + File.separator + tempString);
			response.setHeader("Content-disposition","attachment; filename=" + tempString);
			response.setContentLength(new Long(dir.length()).intValue());
			}catch(Exception e){
				e.printStackTrace();
			}
			return new FileSystemResource(dir); 
		}
		@RequestMapping(value = "/filesWeekly", method = RequestMethod.GET)
		@ResponseBody
		public FileSystemResource getStatusFileReport(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws FileNotFoundException {
			
			String rootPath = System.getProperty("user.dir");
			response.setContentType("application/vnd.ms-excel");
			String tempString = "";
			File dir = null;
			try{
				tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
				tempString = "Status_Report_" + tempString;
				//System.out.println(tempString);
			dir = new File(rootPath + File.separator + tempString);
			response.setHeader("Content-disposition","attachment; filename=" + tempString);
			response.setContentLength(new Long(dir.length()).intValue());
			}catch(Exception e){
				e.printStackTrace();
			}
			return new FileSystemResource(dir); 
		}
		@RequestMapping(value = "/monthlyFiles", method = RequestMethod.GET)
		@ResponseBody
		public FileSystemResource getMonthlyFile(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam("filename") String filename) throws FileNotFoundException {
			//System.out.println("filename"+filename);
			String rootPath = System.getProperty("user.dir");
			response.setContentType("application/vnd.ms-excel");
			String tempString = "";
			File dir = null;
			try{
				tempString =  new SimpleDateFormat("yyyyMMdd'.xls'").format(new Date());
				tempString = request.getParameter("filename");
			 dir = new File(rootPath + File.separator + tempString);
			response.setHeader("Content-disposition","attachment; filename=" + tempString);
			response.setContentLength(new Long(dir.length()).intValue());
			}catch(Exception e){
				e.printStackTrace();
			}
			return new FileSystemResource(dir); 
		}
		@RequestMapping(value="/fetchCurrentWeekData", method = RequestMethod.POST)
		public @ResponseBody String fetchCurrentWeekData(HttpServletRequest request, HttpSession session,@RequestParam(value="firstDate", required=true)String firstDate,@RequestParam(value="secondDate", required=true)String secondDate,@RequestParam(value="thirdDate", required=true)String thirdDate,@RequestParam(value="fourthDate", required=true)String fourthDate,@RequestParam(value="fifthDate", required=true)String fifthDate,@RequestParam(value="sixthDate", required=true)String sixthDate,@RequestParam(value="lastDate", required=true)String lastDate)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchUserTimeSheetData(session,firstDate,secondDate,thirdDate,fourthDate,fifthDate,sixthDate,lastDate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getProjectList", method = RequestMethod.POST)
		public @ResponseBody String getProjectList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getProjectList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllProjectList", method = RequestMethod.POST)
		public @ResponseBody String getAllProjectList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllProjectList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getTaskList", method = RequestMethod.POST)
		public @ResponseBody String getTaskList(HttpServletRequest request, HttpSession session,@RequestParam(value="projectId", required=true)String projectId)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getTaskListByPID(session,projectId);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getUserList", method = RequestMethod.POST)
		public @ResponseBody String getUserList(HttpServletRequest request, HttpSession session,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="allUFlag", required=true)boolean allUFlag)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getUserList(session,pid,allUFlag);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getUserListUnderUser", method = RequestMethod.POST)
		public @ResponseBody String getUserListUnderUser(HttpServletRequest request, HttpSession session,@RequestParam(value="loggedInUser", required=true)String loggedInUser)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getReporteeUserList(session,loggedInUser);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getTimesheetData", method = RequestMethod.POST)
		public @ResponseBody String getTimesheetData(HttpServletRequest request, HttpSession session
				,@RequestParam(value="userId", required=true)String userId
				,@RequestParam(value="startDate", required=true)String startDate
				,@RequestParam(value="endDate", required=true)String endDate
				,@RequestParam(value="filterVar", required=true)String filterVar
				,@RequestParam(value="allReporteeFlag", required=true)String allReporteeFlag
				,@RequestParam(value="batchSize", required=true)String batchSize
				,@RequestParam(value="pageno", required=true)String pageno)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getUsersDataUnderReportee(session,userId,startDate,endDate,filterVar,allReporteeFlag,pageno,batchSize);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchTimeDataBasedPrjctUser", method = RequestMethod.POST)
		public @ResponseBody String getTimesheetDataPrjctUser(HttpServletRequest request, HttpSession session,@RequestParam(value="strtDate", required=true)String strtDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="username", required=true)String username,@RequestParam(value="secondDate", required=true)String secondDate,@RequestParam(value="thirdDate", required=true)String thirdDate,@RequestParam(value="fourthDate", required=true)String fourthDate,@RequestParam(value="fifthDate", required=true)String fifthDate,@RequestParam(value="sixthDate", required=true)String sixthDate)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getDataByPrjctUser(session,strtDate,endDate,username,secondDate,thirdDate,fourthDate,fifthDate,sixthDate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateTimesheetStatus", method = RequestMethod.POST)
		public @ResponseBody String updateTimesheetStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="username", required=true)String username,@RequestParam(value="result", required=true)boolean result,@RequestParam(value="appRmrk", required=true)String appRmrk)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.updateTimesheetStatusUser(session,startDate,endDate,result,username,appRmrk);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getExistingUserList", method = RequestMethod.POST)
		public @ResponseBody String getExistingUserList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
			  if(((Map<String,Boolean>) session.getAttribute("loggedInUserAccessMap")).get(MODIFY_USER))
				  jsonarr = guiObj.getPendingUserList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getExistingUserMailList", method = RequestMethod.POST)
		public @ResponseBody String getExistingUserMailList(HttpServletRequest request, HttpSession session,@RequestParam(value="email", required=true)String email)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllUserMailList(session,email);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getPendingUserList", method = RequestMethod.POST)
		public @ResponseBody String getPendingUserList(HttpServletRequest request, HttpSession session,@RequestParam(value="filterVar", required=true)String filterVar,@RequestParam(value="filterName", required=true)String filterName)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getPendingUsers(session,filterVar,filterName);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateUserData", method = RequestMethod.POST)
		public @ResponseBody String updateUserData(HttpServletRequest request, HttpSession session,@RequestParam(value="uid", required=true)String uid,@RequestParam(value="usrGroup", required=true)String usrGroup,@RequestParam(value="usrRole", required=true)String usrRole,@RequestParam(value="usrStatus", required=true)String usrStatus,@RequestParam(value="usrRemark", required=false)String usrRemark,@RequestParam(value="usrReportingManager", required=false)String usrReportingManager,@RequestParam(value="usrHRManager", required=false)String usrHRManager)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.updateStatusUser(session,uid,usrGroup,usrRole,usrStatus,usrRemark,usrReportingManager,usrHRManager);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/createNewProject", method = RequestMethod.POST)
		public @ResponseBody String createNewProject(HttpServletRequest request, HttpSession session,@RequestParam(value="prjctType", required=true)String prjctType,@RequestParam(value="prjctName", required=true)String prjctName,@RequestParam(value="prjctDesc", required=true)String prjctDesc,@RequestParam(value="prjctStartDate", required=true)String prjctStartDate,@RequestParam(value="prjctEndDate", required=true)String prjctEndDate)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				String pid = guiObj.addProjectToDB(session,prjctType,prjctName,prjctDesc,prjctStartDate,prjctEndDate);
				jsonarr.put(pid);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllProjectDetailList", method = RequestMethod.POST)
		public @ResponseBody String getAllProjectDetailList(HttpServletRequest request, HttpSession session,@RequestParam(value="filterVar", required=true)String filterVar)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllProjectDetailList1(session,filterVar);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/addNewTaskToPrjct", method = RequestMethod.POST)
		public @ResponseBody String addNewTaskToPrjct(HttpServletRequest request, HttpSession session,@RequestParam(value="taskType", required=true)String taskType,@RequestParam(value="taskName", required=true)String taskName,@RequestParam(value="taskDesc", required=true)String taskDesc,@RequestParam(value="pid", required=true)String pid)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.addTaskToProject(session,taskType,taskName,taskDesc,pid);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/addNewFeedback", method = RequestMethod.POST)
		public @ResponseBody String addNewFeedback(HttpServletRequest request, HttpSession session,@RequestParam(value="feedbackType", required=true)String feedbackType,@RequestParam(value="feedBackDesc", required=true)String feedBackDesc,@RequestParam(value="moduleType", required=true)String moduleType,@RequestParam(value="attachment", required=true)String attachment)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.insertFeedbackToTable(session,feedbackType,feedBackDesc,moduleType,attachment);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllActiveUsersList", method = RequestMethod.POST)
		public @ResponseBody String getAllActiveUsersList(HttpServletRequest request, HttpSession session,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="allUserFlag", required=true)boolean allUserFlag)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getActiveUsers(session,pid,allUserFlag);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/getUserListForProjectAllocation", method = RequestMethod.POST)
		public @ResponseBody String getUserListForProjectAllocation(HttpServletRequest request, HttpSession session,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="allUserFlag", required=true)boolean allUserFlag)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getActiveUsersForProject(session,pid,allUserFlag);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllReportingManager", method = RequestMethod.POST)
		public @ResponseBody String getAllReportingManager(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getListOfManagers(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllHRManager", method = RequestMethod.POST)
		public @ResponseBody String getAllHRManager(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getListOfHRManagers(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllStatusType", method = RequestMethod.POST)
		public @ResponseBody String getAllStatusType(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getListOfStatusType(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/groupListForUserModification", method = RequestMethod.POST)
		public @ResponseBody String groupListForUserModification(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getListOfGroupTypeForUserGroupModification(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllGroupType", method = RequestMethod.POST)
		public @ResponseBody String getAllGroupType(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getListOfGroupType(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/insertToPrjctAssignTbl", method = RequestMethod.POST)
		public @ResponseBody String addNewUserToPrjct(HttpServletRequest request, HttpSession session,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="selectedUsers", required=true)String selectedUsers)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.addUsersToProject(session,selectedUsers,pid);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value = "/openProjecttdetailPage", method = RequestMethod.POST)
		public String openProjecttdetailPage(@ModelAttribute("projectBean") ProjectBean projectBean,HttpServletRequest request, HttpServletResponse response,BindingResult result,@RequestParam("pid") String pid,ModelMap model) throws SQLException {
			projectBean = guiObj.getProjectDetails(pid);
			model.put("projectBean", projectBean);
			//System.out.println("prjct detail get request");
			return "viewProjectPage";
		}
		@RequestMapping(value="/updateApproverRemark", method = RequestMethod.POST)
		public @ResponseBody String updateApproverRemark(HttpServletRequest request, HttpSession session,@RequestParam(value="username", required=true)String username,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="appRemark", required=true)String appRemark)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.updateTimesheetApproverRemark(session,username,startDate,endDate,appRemark);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/generateActivationLink", method = RequestMethod.POST)
		public @ResponseBody String generateActivationLink(HttpServletRequest request, HttpSession session,@RequestParam(value="usermail", required=true)String usermail)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;;
			try{
				InternetAddress emailAddr = new InternetAddress(usermail);
			    emailAddr.validate();
			    status = guiObj.forgotUserPwdSentLink(session,usermail);
				jsonarr.put(status);
			}catch(Exception e){
				jsonarr.put(status);
				e.printStackTrace();
			}
			System.out.println(status);
			return jsonarr.toString();
		}
		@RequestMapping(value="/resetPasswordLogin", method = RequestMethod.POST)
		public @ResponseBody String forgotUserPwd(HttpServletRequest request, HttpSession session,@RequestParam(value="usermail", required=true)String usermail,@RequestParam(value="password", required=true)String password,@RequestParam(value="token", required=true)String token)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;;
			try{
				InternetAddress emailAddr = new InternetAddress(usermail);
			    emailAddr.validate();
			    status = guiObj.changeUserPwd(session,usermail,password,token);
				jsonarr.put(status);
			}catch(Exception e){
				jsonarr.put(status);
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/setNewPasswordlogin", method = RequestMethod.GET)
		public ModelAndView setNewPasswordlogin(HttpServletRequest request, HttpSession session,@RequestParam(value="token", required=true)String token)
		{
			String usermail = "";
			try{
				usermail = guiObj.validatePasswordToken(session,token);
				if(usermail.length() > 0 && usermail != null){
					ModelAndView model = new ModelAndView("resetPasswordPage");
					model.addObject("usermail", usermail);
					return model;
				}else{
					ModelAndView model = new ModelAndView("linkExpiredPage");
					model.addObject("linkexxpired", "Invalid or expired link.");
					return model;
				}
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		@RequestMapping(value="/deleteTaskForGivenWeek", method = RequestMethod.POST)
		public @ResponseBody String deleteTaskForGivenWeek(HttpServletRequest request, HttpSession session,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="taskId", required=true)String taskId)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.deleteTimeDataForGivenWeek(session,pid,taskId,startDate,endDate);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/removeUserFrmProject", method = RequestMethod.POST)
		public @ResponseBody String removeUserFrmProject(HttpSession session,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.removeUserFromProject(session,pid,username);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/removeTaskFrmProject", method = RequestMethod.POST)
		public @ResponseBody String removeTaskFrmProject(HttpSession session,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="taskId", required=true)String taskId)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.removeTskFromProject(session,pid,taskId);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/editTasksOfPrject", method = RequestMethod.POST)
		public @ResponseBody String editTasksOfPrject(HttpSession session,
				@RequestParam(value="taskName", required=true)String taskName,
				@RequestParam(value="taskId", required=true)String taskId,
				@RequestParam(value="taskDesc", required=true)String taskDesc,
				@RequestParam(value="taskCriticality", required=true)String taskCriticality,
				@RequestParam(value="taskStartdate", required=true)String taskStartdate,
				@RequestParam(value="taskEnddate", required=true)String taskEnddate,
				@RequestParam(value="taskStatus", required=true)String taskStatus,
				@RequestParam(value="assignedTo", required=true)String assignedTo)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.editTaskDetails(session,taskId,taskName,taskDesc,taskCriticality,taskStartdate,taskEnddate,taskStatus,assignedTo);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateUserPassword", method = RequestMethod.POST)
		public @ResponseBody String updateUserPassword(HttpSession session,@RequestParam(value="oldPwd", required=true)String oldPwd,@RequestParam(value="newPwd", required=true)String newPwd)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.changeUserPassword(session,oldPwd,newPwd);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/createNewUserInDB", method = RequestMethod.POST)
		public @ResponseBody String createNewUserInDB(HttpServletRequest request, HttpSession session
				,@RequestParam(value="username", required=true)String username,
				 @RequestParam(value="fName", required=true)String fName,
				 @RequestParam(value="lName", required=true)String lName,
				 @RequestParam(value="email", required=true)String email,
				 @RequestParam(value="group", required=true)String group,
				 @RequestParam(value="role", required=true)String role,
				 @RequestParam(value="status", required=true)String status,
				 @RequestParam(value="reportee", required=true)String reportee,
				 @RequestParam(value="remark", required=true)String remark,
				 @RequestParam(value="reporteeHR", required=true)String reporteeHR,
				 @RequestParam(value="accessgroup", required=true)String accessGroup,
				 @RequestParam(value="policyGroup", required=true)String policyGroup
				)
		{
			JSONArray jsonarr = new JSONArray();
			boolean statusCreate = false;
			try{
				statusCreate = guiObj.createNewUser(session,username,fName,lName,email,group,role,status,reportee,remark,reporteeHR,accessGroup,policyGroup);
				jsonarr.put(statusCreate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getTaskTypeList", method = RequestMethod.POST)
		public @ResponseBody String getTaskTypeList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllTaskTypeList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getRoleTypeList", method = RequestMethod.POST)
		public @ResponseBody String getRoleTypeList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllRoleTypeList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/getValuesBasedOnCategory", method = RequestMethod.POST)
		public @ResponseBody String getValuesBasedOnCategory(HttpServletRequest request, HttpSession session,@RequestParam(value="category", required=true)String category)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getCategoryBasedValues(session,category);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/getPrjStatusTypeList", method = RequestMethod.POST)
		public @ResponseBody String getPrjStatusTypeList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllPrjStsTypeList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllCriticalLevel", method = RequestMethod.POST)
		public @ResponseBody String getAllCriticalLevel(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllCriticalityTypeList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllTaskStatus", method = RequestMethod.POST)
		public @ResponseBody String getAllTaskStatus(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllTaskStsTypeList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateProjectStatus", method = RequestMethod.POST)
		public @ResponseBody String updateProjectStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="pid", required=true)String pid,@RequestParam(value="status", required=true)String status,@RequestParam(value="timesheetApprover", required=true)String timesheetApprover,@RequestParam(value="owner", required=true)String owner)
		{
			JSONArray jsonarr = new JSONArray();
			boolean statusUpdate;
			try{
				statusUpdate = guiObj.updateProjectDetails(session,startDate,endDate,status,pid,timesheetApprover,owner);
				jsonarr.put(statusUpdate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getUsersAllProjectList", method = RequestMethod.POST)
		public @ResponseBody String getUsersAllProjectList(HttpServletRequest request, HttpSession session,@RequestParam(value="uid", required=true)String uid)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getAllProjectListOfUser(session,uid);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getUsersActionHistory", method = RequestMethod.POST)
		public @ResponseBody String getUsersActionHistory(HttpServletRequest request, HttpSession session,@RequestParam(value="firstDate", required=true)String firstDate,@RequestParam(value="lastDate", required=true)String lastDate,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getUsersActionHistor(session,firstDate,lastDate,username);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getEmailNotificationFlagStatus", method = RequestMethod.POST)
		public @ResponseBody String getEmailNotificationFlagStatus(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getEmailNotificatinStatus(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateEmailNotificationStatus", method = RequestMethod.POST)
		public @ResponseBody String updateNotificationStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="id", required=true)String id,@RequestParam(value="status", required=true)boolean status)
		{
			JSONArray jsonarr = new JSONArray();
			boolean statusUpdate;
			try{
				statusUpdate = guiObj.updateNotification(session,id,status);
				jsonarr.put(statusUpdate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getRecentTimesheetData", method = RequestMethod.POST)
		public @ResponseBody String getRecentTimesheetData(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getUsersLimitWeekData(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/createNewAccessGroup", method = RequestMethod.POST)
		public @ResponseBody String createNewAccessGroup(HttpServletRequest request, HttpSession session,@RequestParam(value="groupName", required=true)String groupName)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.createAccessGrp(session,groupName);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateAccessGroupName", method = RequestMethod.POST)
		public @ResponseBody String updateAccessGroupName(HttpServletRequest request, HttpSession session,@RequestParam(value="groupName", required=true)String groupName,@RequestParam(value="groupId", required=true)String groupId)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.updateAccessGrpName(session,groupName,groupId);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/deleteGroupFromApp", method = RequestMethod.POST)
		public @ResponseBody String deleteGroupFromApp(HttpServletRequest request, HttpSession session,@RequestParam(value="groupId", required=true)String groupId)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.removeAccessGroup(session,groupId);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getUnassignedAssignedUserListBasedOnFlag", method = RequestMethod.POST)
		public @ResponseBody String getUnassignedAssignedUserListBasedOnFlag(HttpServletRequest request, HttpSession session,@RequestParam(value="groupId", required=true)String groupId,@RequestParam(value="addRemoveFlag", required=true)String addRemoveFlag)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getUserListForGroupAssignment(session,groupId,addRemoveFlag);
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(jsonarr);
			return jsonarr.toString();
		}
		@RequestMapping(value="/addRemoveUsersInGroup", method = RequestMethod.POST)
		public @ResponseBody String updateUsersInGroup(HttpServletRequest request, HttpSession session,@RequestParam(value="groupId", required=true)String groupId,@RequestParam(value="selectedUsers[]", required=true)String[] selectedUsers,@RequestParam(value="addRemoveFlag", required=true)String addRemoveFlag)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;
			System.out.println(selectedUsers);
			try{
				status = guiObj.addRemoveUsersFromGroup(session,groupId,Arrays.asList(selectedUsers),addRemoveFlag);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/addRemoveUsersFromProject", method = RequestMethod.POST)
		public @ResponseBody String updateUsersFromProject(HttpServletRequest request, HttpSession session,@RequestParam(value="projectId", required=true)String projectId,@RequestParam(value="selectedUsers[]", required=true)String[] selectedUsers,@RequestParam(value="addRemoveFlag", required=true)String addRemoveFlag)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;
			System.out.println(selectedUsers);
			try{
				status = guiObj.addRemoveUsersFromProject(session,projectId,Arrays.asList(selectedUsers),addRemoveFlag);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getAllFunctionAccess", method = RequestMethod.POST)
		public @ResponseBody String getAllFunctionAccess(HttpServletRequest request, HttpSession session,@RequestParam(value="groupId", required=true)String groupId)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getListOfFunctionAccess(session,groupId);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updatefunctionAccessStatus", method = RequestMethod.POST)
		public @ResponseBody String updatefunctionAccessStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="functionId", required=true)String functionId,@RequestParam(value="groupId", required=true)String groupId,@RequestParam(value="status", required=true)boolean status)
		{
			JSONArray jsonarr = new JSONArray();
			//System.out.println(functionId+groupId+status);
			boolean statusUpdate;
			try{
				statusUpdate = guiObj.updatefunctionAcees(session,functionId,groupId,status);
				jsonarr.put(statusUpdate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/getCountParameter", method = RequestMethod.POST)
		public @ResponseBody String getCountParameter(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getPendingCountParam(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/approveMultipleTimesheet", method = RequestMethod.POST)
		public @ResponseBody String approveTimesheetStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="reqArray[]", required=false)String[] reqArray)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;
			try{
				status = guiObj.approveTMSStatus(session,Arrays.asList(reqArray));
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value = "/editUserProfile", method = RequestMethod.POST)
		public String editUserProfile(@ModelAttribute("employeeBean") EmployeeBean employeeBean,HttpSession session,HttpServletRequest request, HttpServletResponse response,BindingResult result,ModelMap model,@RequestParam(value="uid", required=true)String uid) throws SQLException {
			employeeBean = guiObj.fetchEmployeeDetails(session,employeeBean,uid);
			model.put("employeeBean", employeeBean);
			return "editUserProfile";
		}
		@RequestMapping(value = "/editUserProfileOwn", method = RequestMethod.POST)
		public String editUserProfileOwn(@ModelAttribute("employeeBean") EmployeeBean employeeBean,HttpSession session,HttpServletRequest request, HttpServletResponse response,BindingResult result,ModelMap model,@RequestParam(value="uid", required=true)String uid) throws SQLException {
			employeeBean = guiObj.fetchEmployeeDetails(session,employeeBean,uid);
			model.put("employeeBean", employeeBean);
			return "editUserOwnProfile";
		}
		@RequestMapping(value = "/showUserTimesheetHistory", method = RequestMethod.GET)
		public String showUserTimesheetHistory(HttpSession session,HttpServletRequest request, HttpServletResponse response,ModelMap model,@RequestParam(value="uid", required=true)String uid) throws SQLException {
			//employeeBean = guiObj.fetchEmployeeDetails(session,employeeBean,uid);
			//model.put("employeeBean", employeeBean);
			return "userTimesheetData";
		}
		@RequestMapping(value="/getUserTSData", method = RequestMethod.POST)
		public @ResponseBody String getUserTSData(HttpServletRequest request, HttpSession session,@RequestParam(value="year", required=true)String year,@RequestParam(value="month", required=true)String month)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.getTMSUserData(session,year,month);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/saveEmployeeData",headers="Accept=*/*",  produces="application/json", method = RequestMethod.POST)
		public @ResponseBody String doPost(@ModelAttribute("employeeBean") EmployeeBean employeeBean,HttpSession session){
			boolean status = false;
			try{
					//System.out.println(result);
					status = guiObj.updateUserInformation(session,employeeBean);
					System.out.println(status);
				}catch(Exception e){
					e.printStackTrace();
				}
			
			return String.valueOf(status);
		}
		@RequestMapping(value="/deleteAccountRow", method = RequestMethod.POST)
		public @ResponseBody String deleteAccountRow(HttpServletRequest request, HttpSession session,@RequestParam(value="accNumber", required=true)String accNumber)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.deleteAccountRow(session,accNumber);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/deleteEducationRow", method = RequestMethod.POST)
		public @ResponseBody String deleteEducationRow(HttpServletRequest request, HttpSession session,@RequestParam(value="qualificationId", required=true)String qualificationId)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.deleteEducationRow(session,qualificationId);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/deleteSkillRow", method = RequestMethod.POST)
		public @ResponseBody String deleteSkillRow(HttpServletRequest request, HttpSession session,@RequestParam(value="skillId", required=true)String skillId)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.deleteSkillRow(session,skillId);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/deleteEmployementRow", method = RequestMethod.POST)
		public @ResponseBody String deleteEmployementRow(HttpServletRequest request, HttpSession session,@RequestParam(value="compId", required=true)String compId)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status;
			try{
				status = guiObj.deleteEmployementRow(session,compId);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/uploadFeedbackAttachment",method=RequestMethod.POST)
		public @ResponseBody String uploadFeedbackAttachment(@RequestParam MultipartFile file,HttpSession session) throws Exception
		{
			boolean status = false;
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setRepository(new File(System.getProperty("user.dir")));

				String uploadPath = System.getProperty("user.dir");
				System.out.println(uploadPath);	    

				byte[] bytes = file.getBytes();
				
				BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(uploadPath + File.separator + file.getOriginalFilename())));
				stream.write(bytes);
				stream.flush();
				stream.close();
				//status = guiObjReport.uploadRMSBankRefData();
				status = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return String.valueOf(status);
		}
		@RequestMapping(value="/addUserBankDetails", method = RequestMethod.POST)
		public @ResponseBody String addUserBankDetails(HttpServletRequest request, HttpSession session,@RequestParam(value="bankName", required=true)String bankName,@RequestParam(value="accType", required=true)String accType,@RequestParam(value="accountNumber", required=true)String accountNumber,@RequestParam(value="ifscCode", required=true)String ifscCode,@RequestParam(value="primaryFlag", required=true)String primaryFlag,@RequestParam(value="accHolderName", required=true)String accHolderName,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;
			try{
				status = guiObj.addUserBankAccount(session,bankName,accType,accountNumber,ifscCode,primaryFlag,accHolderName,username);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/addUserEmployementHistory", method = RequestMethod.POST)
		public @ResponseBody String addUserEmployementHistory(HttpServletRequest request,HttpSession session,@RequestParam(value="companyName", required=true)String companyName,@RequestParam(value="lastDesignation", required=true)String lastDesignation,@RequestParam(value="experience", required=true)String experience,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;
			try{
				status = guiObj.addUserPastWorkInfo(session,companyName,lastDesignation,experience,startDate,endDate,username);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/addNewQualificationOfUser", method = RequestMethod.POST)
		public @ResponseBody String addNewQualificationOfUser(HttpServletRequest request, HttpSession session,@RequestParam(value="qualification", required=true)String qualification,@RequestParam(value="collegeName", required=true)String collegeName,@RequestParam(value="boardName", required=true)String boardName,@RequestParam(value="streamName", required=true)String streamName,@RequestParam(value="year", required=true)String year,@RequestParam(value="username", required=true)String username,@RequestParam(value="score", required=true)String score)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;
			try{
				status = guiObj.addUserEducation(session,qualification,collegeName,boardName,streamName,year,username,score);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/addNewUserSkill", method = RequestMethod.POST)
		public @ResponseBody String addNewUserSkill(HttpServletRequest request, HttpSession session,@RequestParam(value="skillName", required=true)String skillName,@RequestParam(value="skillType", required=true)String skillType,@RequestParam(value="version", required=true)String version,@RequestParam(value="expMonth", required=true)String expMonth,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			boolean status = false;
			try{
				status = guiObj.addUserSkill(session,skillName,skillType,version,expMonth,username);
				jsonarr.put(status);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchAssignedTaskList", method = RequestMethod.POST)
		public @ResponseBody String fetchAssignedTaskList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchUserAssignedTaskList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateAssignedTaskProgress", method = RequestMethod.POST)
		public @ResponseBody String updateAssignedTaskProgress(HttpServletRequest request, HttpSession session,@RequestParam(value="taskId", required=true)String taskId,@RequestParam(value="workProgress", required=true)String workProgress) throws JSONException
		{
			String stausString = "Please try later";
			JSONArray jsonarr = new JSONArray();
			try{
				stausString = guiObj.modifyUserAssignedTaskProgress(session,taskId,workProgress);
				jsonarr.put(stausString);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchUserCurrentAccessGroupList", method = RequestMethod.POST)
		public @ResponseBody String fetchUserCurrentAccessGroupList(HttpServletRequest request, HttpSession session,@RequestParam(value="username", required=true)String username)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchUsersCurrentAccessGroupList(session,username);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateUsrGroupAccessStatus", method = RequestMethod.POST)
		public @ResponseBody String updateUsrGroupAccessStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="username", required=true)String username,@RequestParam(value="groupId", required=true)String groupId,@RequestParam(value="status", required=true)boolean status)
		{
			JSONArray jsonarr = new JSONArray();
			//System.out.println(functionId+groupId+status);
			boolean statusUpdate;
			try{
				statusUpdate = guiObj.updateGroupAcees(session,username,groupId,status);
				jsonarr.put(statusUpdate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateUsrDisplayDivStatus", method = RequestMethod.POST)
		public @ResponseBody String updateUsrDisplayDivStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="panelId", required=true)String panelId,@RequestParam(value="status", required=true)boolean status)
		{
			JSONArray jsonarr = new JSONArray();
			//System.out.println(functionId+groupId+status);
			boolean statusUpdate;
			try{
				statusUpdate = guiObj.updateUserDisplayDivSetting(session,panelId,status);
				jsonarr.put(statusUpdate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/updateUsrQuickLinkStatus", method = RequestMethod.POST)
		public @ResponseBody String updateUsrQuickLinkStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="funcLinkId", required=true)String funcLinkId,@RequestParam(value="status", required=true)boolean status)
		{
			JSONArray jsonarr = new JSONArray();
			//System.out.println(functionId+groupId+status);
			boolean statusUpdate;
			try{
				statusUpdate = guiObj.updateUserQuickLinkSetting(session,funcLinkId,status);
				jsonarr.put(statusUpdate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchUsrQuickLinkAndDisplayDiv", method = RequestMethod.POST)
		public @ResponseBody String fetchUsrQuickLinkAndDisplayDiv(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchUsersDisplayDivAndQuickLinksList(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchUserOwnPendigItemCount", method = RequestMethod.POST)
		public @ResponseBody String fetchUserOwnPendigItemCount(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchUserOwnItemCount(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchOthersUsersItemCount", method = RequestMethod.POST)
		public @ResponseBody String fetchOthersUsersItemCountList(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchOtherUsersItemCount(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchUserCountInSystem", method = RequestMethod.POST)
		public @ResponseBody String fetchUsersCountInSystem(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchUserCountBasedOnStatus(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@RequestMapping(value="/fetchProjectCountInSystem", method = RequestMethod.POST)
		public @ResponseBody String fetchProjectsCountInSystem(HttpServletRequest request, HttpSession session)
		{
			JSONArray jsonarr = new JSONArray();
			try{
				jsonarr = guiObj.fetchProjectCountBasedOnStatus(session);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
		@Value("${profileImageDir}")
		private String profileImageDir;
		
		@RequestMapping(value="/uploadUserProfilePicture",method=RequestMethod.POST)
		public @ResponseBody String uploadUserProfilePicture(@RequestParam MultipartFile file,HttpSession session) throws Exception
		{
			boolean status = false;
			try {
				String username = (String) session.getAttribute("loggedInUser");
				String fileExtension = file.getOriginalFilename().split("\\.")[1];
				String filePath = profileImageDir + File.separator + username+"."+fileExtension;
				String dbImagePath = guiObj.getProfileImageMetadata(username);
				

				int result;
				if(dbImagePath != null) {
					//delete old image
					String imagePathToDelete = profileImageDir + File.separator + dbImagePath.substring(dbImagePath.lastIndexOf("/")+1, dbImagePath.length());
					deleteOldImage(imagePathToDelete);

					//upload new image
					uploadImage(file, username, fileExtension, filePath);
					
					//update db data
					result = guiObj.updateProfileImageMetadata(username, fileExtension);
				}
				else {
					//upload new image
					uploadImage(file, username, fileExtension, filePath);
					
					//create data in db
					result = guiObj.saveProfileImageMetadata(username, fileExtension);
				}
				
				
//				status = guiObj.saveProfileImageMetadata(session, filePath);
				if(result > 0) {
					session.setAttribute("profilePicUrl", getUserProfilePic(session));
					status = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return String.valueOf(status);
		}
		
		private void deleteOldImage(String imagePath) {
			File image = new File(imagePath);
			image.delete();
		}
		
		private void uploadImage(MultipartFile file, String username, String fileExtension, String filePath) throws IOException {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(profileImageDir));
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(bytes);
			stream.flush();
			stream.close();
		}
		
		@Value("${domain}")
		private String domain;
		@Value("${serverport}")
		private String serverport;
		@Value("${protocol}")
		private String protocol;
		
		@RequestMapping(value = "/userProfilePic",method=RequestMethod.GET)
		@ResponseBody
		public String getUserProfilePic(HttpSession session)  {
		  /*Image image = guiObj.getUserProfileImage(session);
		  System.out.println(image);
		  return image.getOriginalData();*/
			String dbImagePath = guiObj.getProfileImageMetadata((String)session.getAttribute("loggedInUser"));
			String filePath = null;
			if(dbImagePath == null) {
				//default Image
				filePath = protocol+"://"+ domain + "/profileImages/sample.png"; 
			}
			else {
				filePath = dbImagePath;
			}
			return filePath;
		}
		@RequestMapping(value="/updateUserInfoByParam", method = RequestMethod.POST)
		public @ResponseBody String updateUserInfoByParam(HttpServletRequest request, HttpSession session
				,@RequestParam(value="username", required=true)String username
				,@RequestParam(value="paramValue", required=true)String paramValue
				,@RequestParam(value="param", required=true)String param)
		{
			JSONArray jsonarr = new JSONArray();
			String statusUpdate;
			try{
				statusUpdate = guiObj.updateUserInfoQuick(session,username,paramValue,param);
				jsonarr.put(statusUpdate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return jsonarr.toString();
		}
}
