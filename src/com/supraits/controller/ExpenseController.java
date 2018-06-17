package com.supraits.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
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

import com.supraits.service.ExpenseService;
import com.supraits.service.ExpenseServiceImpl;
import com.supraits.service.RMSReportImpl;
import com.supraits.service.TimesheetReportImpl;
import com.supraits.viewBean.ExpenseDocumentBean;
import com.supraits.viewBean.ExpenseRequestBean;


@Controller
public class ExpenseController {
	ExpenseService guiObj;
	RMSReportImpl guiObjReport;
	@Autowired
	public void setExpenseServiceImpl(ExpenseServiceImpl objExpenseServiceGUI) {
		this.guiObj = objExpenseServiceGUI;
	}
	@Autowired
	public void setRMSReportImpl(RMSReportImpl objRMSReportGUI) {
		this.guiObjReport = objRMSReportGUI;
	}
	@Autowired
	private ExpenseRequestBean expenseRequestBean;
	
	@RequestMapping(value = "/newExpenseRequest", method = RequestMethod.POST)
	public String newExpenseRequest(@ModelAttribute("expenseRequestBean") ExpenseRequestBean expenseRequestBean,HttpSession session,HttpServletRequest request, HttpServletResponse response,BindingResult result,ModelMap model) throws SQLException {
		session.setAttribute("currentExpenseNumber", guiObj.generateBillRequestNo(session));
		model.put("expenseRequestBean", expenseRequestBean);
		return "newExpenseRequest";
	}
	@RequestMapping(value = "/openExpenseRequestForEdit", method = RequestMethod.POST)
	public String openExpenseRequest(@ModelAttribute("expenseRequestBean") ExpenseRequestBean expenseRequestBean,HttpSession session,HttpServletRequest request, HttpServletResponse response,BindingResult result,ModelMap model,@RequestParam(value="requestNumber", required=true)String requestNumber) throws SQLException {
		expenseRequestBean = guiObj.fetchBillRequestDetail(session,expenseRequestBean,requestNumber);
		session.setAttribute("currentExpenseNumber",expenseRequestBean.getRequestNumber());
		model.put("expenseRequestBean", expenseRequestBean);
		return "openExpenseRequest";
	}
	@RequestMapping(value = "/billGetRequestNo", method = RequestMethod.POST)
	public @ResponseBody String billGetRequestNo(ModelMap model, HttpServletRequest request, HttpSession session)
	{

		JSONObject json = new JSONObject();
		try
		{
			String tmpReqNum = guiObj.generateBillRequestNo(session);
			System.out.println("Request Num: "+ tmpReqNum);
			json.put("billId", tmpReqNum);

		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
			
		return json.toString();

	}
	@RequestMapping(value = "/saveNewExpenseAction", method = RequestMethod.POST)
	public String saveNewExpenseAction(@ModelAttribute("expenseRequestBean") ExpenseRequestBean expenseRequestBean, BindingResult result, ModelMap model, HttpSession session,@RequestParam(value="file", required=false)MultipartFile[] files) throws SQLException
	{	
		System.out.println(expenseRequestBean.getRequestNumber());
		expenseRequestBean.setRequestNumber(session.getAttribute("currentExpenseNumber").toString());
		model.put("expenseRequestBean", expenseRequestBean);
		System.out.println("listsize"+expenseRequestBean.getExpenseList().size());
		System.out.println(files);
		if(files!= null && files.length != 0){
			for (int i = 0; i < files.length; i++) {
				if(files[i].getOriginalFilename()==null || files[i].getOriginalFilename().equals("")){
					continue;
				}
				MultipartFile file = files[i];
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("user.dir");
					File dir = new File(rootPath + File.separator + "BILL" + File.separator +"Register" + File.separator + (String)session.getAttribute("loggedInUser"));
					if (dir.exists())
						dir.delete();
					dir.mkdirs();
					File serverFile = new File(rootPath + File.separator + "BILL" + File.separator +"Register" + File.separator + (String)session.getAttribute("loggedInUser") + File.separator +file.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					serverFile = null;
					stream = null;
					System.out.println("Successfully uploaded file=" + file.getOriginalFilename());
				} catch (Exception e) {
					e.printStackTrace();
					model.put("ErrorMsg", "Request Document Upload has encountered some error. Please try again.");
					return "newExpenseRequest";
				}
			}
		}
		boolean status = guiObj.insertNewExpenseRequest(session,expenseRequestBean,false);
		
		if(status){
			model.put("successMsg", "Request saved successfully");
		}else{
			model.put("errorMsg", "Request saving encounterd some problem!");
		}
		return "newExpenseRequest";
	}
	@RequestMapping(value = "/submitNewExpenseAction", method = RequestMethod.POST)
	public String submitNewExpenseAction(@ModelAttribute("expenseRequestBean") ExpenseRequestBean expenseRequestBean, BindingResult result, ModelMap model, HttpSession session,@RequestParam(value="file", required=false)MultipartFile[] files) throws SQLException
	{	
		System.out.println(expenseRequestBean.getRequestNumber());
		model.put("expenseRequestBean", expenseRequestBean);
		//System.out.println("listsize"+expenseRequestBean.getExpenseList().size());
		//System.out.println(files);
		expenseRequestBean.setRequestNumber(session.getAttribute("currentExpenseNumber").toString());
		if(files!= null && files.length != 0){
			for (int i = 0; i < files.length; i++) {
				if(files[i].getOriginalFilename()==null || files[i].getOriginalFilename().equals("")){
					continue;
				}
				MultipartFile file = files[i];
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("user.dir");
					File dir = new File(rootPath + File.separator + "BILL" + File.separator +"Register" + File.separator + (String)session.getAttribute("loggedInUser"));
					if (!dir.exists())
						dir.mkdirs();
					File serverFile = new File(rootPath + File.separator + "BILL" + File.separator +"Register" + File.separator + (String)session.getAttribute("loggedInUser") + File.separator +file.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					serverFile = null;
					stream = null;
					System.out.println("Successfully uploaded file=" + file.getOriginalFilename());
				
				} catch (Exception e) {
					e.printStackTrace();
					model.put("ErrorMsg", "Request Document Upload has encountered some error. Please try again.");
					return "newExpenseRequest";
				}
			}
		}
		boolean status = guiObj.insertNewExpenseRequest(session,expenseRequestBean,true);
		
		if(status){
			model.put("successMsg", "Request has been successfully sent to "+session.getAttribute("reportingManager"));
		}else{
			model.put("errorMsg", "Request initiation encounterd some problem!");
		}
		return "newExpenseRequest";
	}
	@RequestMapping(value="/getAllExpenseList", method = RequestMethod.POST)
	public @ResponseBody String getAllExpenseList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getListOfExpenseType(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getExpensesListUnderUser", method = RequestMethod.POST)
	public @ResponseBody String getExpensesListUnderUser(HttpServletRequest request, HttpSession session,@RequestParam(value="status", required=true)String status)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getExpensesListUnderLoggedInUser(session,status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getPendingExpenseRequestList", method = RequestMethod.POST)
	public @ResponseBody String getPendingRequestList(HttpServletRequest request, HttpSession session,@RequestParam(value="filterVar", required=true)String filterVar,@RequestParam(value="expenseBucket", required=true)String expenseBucket)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAllPendingRequestList(session,filterVar,expenseBucket);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getExpanseDetailByReqNumber", method = RequestMethod.POST)
	public @ResponseBody String getExpanseDetailByReqNumber1(HttpServletRequest request, HttpSession session,@RequestParam(value="reqNumber", required=true)String reqNumber)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getPendingRequestDetails(session,reqNumber);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/updateRMSRequestStatus", method = RequestMethod.POST)
	public @ResponseBody String updateRMSRequestStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="reqNumber", required=true)String reqNumber,@RequestParam(value="approveFlag", required=true)String approveFlag,@RequestParam(value="reqStatus", required=true)String reqStatus,@RequestParam(value="remark", required=true)String remark)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		//System.out.println(reqNumber+approveFlag+reqStatus+remark);
		try{
			status = guiObj.updateRMSRemarkAndStatus(session,reqNumber,approveFlag,reqStatus,remark);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/deleteRMSAttachment", method = RequestMethod.POST)
	public @ResponseBody String deleteRMSAttachment(HttpServletRequest request, HttpSession session,@RequestParam(value="reqNumber", required=true)String reqNumber,@RequestParam(value="expenseCode", required=true)String expenseCode)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.updateRMSDocFlagStatus(session,reqNumber,expenseCode);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/viewExpDoc", method = RequestMethod.POST)
	@ResponseBody
	public String viewExpDoc(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="requestNumber", required=true)String requestNumber,@RequestParam(value="expenseCode", required=true)String expenseCode) throws FileNotFoundException {
		System.out.println("expenseCode"+expenseCode);
		//response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		try{
			tempString = "Doc"+requestNumber+expenseCode;
		response.setHeader("Content-disposition","inline; filename=" + tempString);
		ExpenseDocumentBean doc = guiObj.getAttachmentDetails(session,requestNumber,expenseCode);
		if(!(null == doc.getData())){
		OutputStream out = response.getOutputStream();
		response.setContentType(doc.getDoctype());
		IOUtils.copy(new ByteArrayInputStream(doc.getData()), out);
		out.flush();
		out.close();
		}else{
			return "No document has been attached";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null; 
	}
	@RequestMapping(value = "/downloadRequestForm", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadRequestForm(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="requestNumber", required=true)String requestNumber) throws FileNotFoundException {
		
		String rootPath = System.getProperty("user.dir");
		//response.setContentType("application/vnd.ms-excel");
		
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.pdf'").format(new Date());
			tempString = requestNumber + tempString;
			System.out.println(tempString);
		boolean genStatus = guiObj.generateFile(session,requestNumber,tempString);
		if(genStatus){
			dir = new File(rootPath + File.separator,tempString);
			//Path file = Paths.get(rootPath+ File.separator, tempString);
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition","attachment; filename=" + tempString);
			response.setContentLength(new Long(dir.length()).intValue());
			System.out.println("dir"+dir);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(dir);
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value="/updateBucketofRequests", method = RequestMethod.POST)
	public @ResponseBody String updateBucketofRequests(HttpServletRequest request, HttpSession session,@RequestParam(value="expenseBucket", required=true)String expenseBucket,@RequestParam(value="reqArray[]", required=true)String[] reqArray)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.updateRMSBucketofRequests(session,expenseBucket,Arrays.asList(reqArray));
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getAllBucketList", method = RequestMethod.POST)
	public @ResponseBody String getAllBucketList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getBucketList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/bucketMonthlyReport", method = RequestMethod.POST)
	@ResponseBody
	public FileSystemResource getMonthlyReport(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="currentBucket", required=true)String currentBucket) throws FileNotFoundException 
	{
		System.out.println("bucket="+currentBucket);
		String rootPath = System.getProperty("user.dir");
		response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		File dir = null;
		try{
			//tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
			//tempString = "RMSReport" + tempString;
		    tempString = guiObjReport.createMonthlyRMSReport(session,currentBucket);
			System.out.println(tempString);
		dir = new File(rootPath + File.separator + tempString);
		response.setHeader("Content-disposition","attachment; filename=" + tempString);
		response.setContentLength(new Long(dir.length()).intValue());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value="/createNewRMSBucket", method = RequestMethod.POST)
	public @ResponseBody String createNewRMSBucket(HttpServletRequest request, HttpSession session,@RequestParam(value="bucketName", required=true)String bucketName)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.createRMSBucket(session,bucketName);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/updateRMSBucket", method = RequestMethod.POST)
	public @ResponseBody String updateRMSBucket(HttpServletRequest request, HttpSession session,@RequestParam(value="bucketName", required=true)String bucketName,@RequestParam(value="bucektId", required=true)String bucektId)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.updateRMSBucketName(session,bucketName,bucektId);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getEachBucketCount", method = RequestMethod.POST)
	public @ResponseBody String getEachBucketCount(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getRMSBucketCount(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/deleteRMSBucket", method = RequestMethod.POST)
	public @ResponseBody String deleteRMSBucket(HttpServletRequest request, HttpSession session,@RequestParam(value="bucektId", required=true)String bucektId)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.removeRMSBucket(session,bucektId);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/uploadBankRefSheet",method=RequestMethod.POST)
	public @ResponseBody String updateBankRefNumber(@RequestParam MultipartFile file,HttpSession session) throws Exception
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
			status = guiObjReport.uploadRMSBankRefData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(status);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getRMSActionHistory", method = RequestMethod.POST)
	public @ResponseBody String getUsersActionHistory(HttpServletRequest request, HttpSession session,@RequestParam(value="requestNumber", required=true)String requestNumber)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			Map<String, Object> actionHistoryMap = guiObj.getRMSRequestActionHistory(requestNumber);
			Iterator<String> itr = actionHistoryMap.keySet().iterator();
			while(itr.hasNext()){
				String tempData = itr.next();
				JSONObject temp = new JSONObject();	
				
				temp.put("action",((List<Object>) actionHistoryMap.get(tempData)).get(0));
				temp.put("actionBy",((List<Object>) actionHistoryMap.get(tempData)).get(1));
				temp.put("actionDate",((List<Object>) actionHistoryMap.get(tempData)).get(2));
				temp.put("actionRemark",((List<Object>) actionHistoryMap.get(tempData)).get(3));
				temp.put("assignedTo",((List<Object>) actionHistoryMap.get(tempData)).get(4));
				temp.put("assignedToTitle",((List<Object>) actionHistoryMap.get(tempData)).get(5));
				
				jsonarr.put(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getExpenseStatusList", method = RequestMethod.POST)
	public @ResponseBody String getExpenseStatusList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getExpenseStatusList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/getUserListForExpenseReport", method = RequestMethod.POST)
	public @ResponseBody String getUserListForExpenseReport(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getUserList(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/viewReimbursementReport", method = RequestMethod.POST)
	public @ResponseBody String viewReimbursementReport(HttpServletRequest request, HttpSession session,
			@RequestParam(value="projectId", required=true)String projectId,
			@RequestParam(value="username", required=true)String username,
			@RequestParam(value="bucketId", required=true)String bucketId,@RequestParam(value="startDate", required=true)String startDate,@RequestParam(value="endDate", required=true)String endDate,@RequestParam(value="status", required=true)String status)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.viewExpenseReport(session,projectId,username,bucketId,startDate,endDate,status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value = "/currentExpenseReport", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getLeaveReportFile(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws FileNotFoundException {
		String rootPath = System.getProperty("user.dir");
		response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
			tempString = "ExpenseReport" + tempString;
		dir = new File(rootPath + File.separator + tempString);
		response.setHeader("Content-disposition","attachment; filename=" + tempString);
		response.setContentLength(new Long(dir.length()).intValue());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value="/getExpenseProjectList", method = RequestMethod.POST)
	public @ResponseBody String getExpenseProjectList(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getProjectListForReimbursement(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
}
