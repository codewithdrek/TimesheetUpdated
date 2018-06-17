package com.supraits.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.supraits.service.HrmsService;
import com.supraits.service.impl.HrmsServiceImpl;
import com.supraits.viewBean.NotificationDocBean;




@Controller
public class HrmsController
{
	HrmsService guiObj;
	@Autowired
	public void setHrmsServiceImpl(HrmsServiceImpl objHrmsServiceImpl) {
		this.guiObj = objHrmsServiceImpl;
	}
	
	
	@RequestMapping(value="/fetchDocRequiredParamList", method = RequestMethod.POST)
	public @ResponseBody String fetchDocRequiredParamList(HttpServletRequest request, HttpSession session,@RequestParam(value="docType", required=false)String docType)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getDocParams(session,docType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	
	@RequestMapping(value="/fetchRTEDocRequiredParamList", method = RequestMethod.POST)
	public @ResponseBody String fetchRTEDocRequiredParamList(HttpServletRequest request, HttpSession session,@RequestParam(value="docType", required=false)String docType)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getRTEDocTemplate(session,docType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	
	@RequestMapping(value="/createUserDocumentById", method = RequestMethod.POST)
	public @ResponseBody String createUserDocumentById(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="dataString", required=true)String dataString,@RequestParam(value="docType", required=true)String docType)
	{
		JSONArray jsonarr = new JSONArray();
		JSONObject jsobj = new JSONObject();
		String fileGenFlag = "";
		try{
			fileGenFlag = guiObj.createEmployeeDoc(session,dataString,docType);
			if(fileGenFlag.length()>0){
				jsobj.put("fileName",fileGenFlag);
			}else{
				jsobj.put("fileName", "Please try after some time.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.put(jsobj).toString();
	}
	@RequestMapping(value = "/downloadEmpDoc", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadEmpDoc(HttpServletRequest request,HttpServletResponse response, HttpSession session
			,@RequestParam(value="filename", required=true)String filename) throws FileNotFoundException {
		String rootPath = System.getProperty("user.dir");
		//response.setContentType("application/vnd.ms-excel");
		File dir = null;
		try{
		if(filename.length()> 0){
			dir = new File(rootPath + File.separator,filename);
			//Path file = Paths.get(rootPath+ File.separator, tempString);
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition","attachment; filename=" + filename);
			response.setContentLength(new Long(dir.length()).intValue());
			System.out.println("dir"+dir);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(dir);
		return new FileSystemResource(dir); 
	}
	@RequestMapping(value = "/punchedTimeFromClientSide", method = RequestMethod.POST)
	public @ResponseBody String punchedTimeFromClientSide(HttpServletRequest request, HttpSession session,@RequestParam(value="countryCode")String countryCode,@RequestParam(value="city")String city,@RequestParam(value="region")String region,@RequestParam(value="loc")String loc,@RequestParam(value="clientIp", required=true)String clientIp) {
	//public @ResponseBody String punchedTimeFromClientSide(HttpServletRequest request, HttpSession session,@RequestParam(value="clientIp", required=true)String clientIp) {
		String stausString = "Please try later";
		JSONArray jsonarr = new JSONArray();
		try {
			stausString = guiObj.punchedTimeInDBByAPI(session,countryCode,city,region,loc,clientIp);
			jsonarr.put(stausString);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/checkUsersCurrentPunchedRecord", method = RequestMethod.POST)
	public @ResponseBody String checkUsersCurrentPunchedRecord(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.checkUserExistingPunchData(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/uploadNotificationAttachment",method=RequestMethod.POST)
	public @ResponseBody String uploadNotificationAttachment(@RequestParam MultipartFile file,HttpSession session) throws Exception
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
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(status);
	}
	@RequestMapping(value="/addModifyNewCompAnnouncement", method = RequestMethod.POST)
	public @ResponseBody String addNewCompAnnouncement(HttpServletRequest request, HttpSession session
			,@RequestParam(value="notificationrfnum", required=true)String notificationrfnum
			,@RequestParam(value="type", required=true)String type
			,@RequestParam(value="description", required=true)String description
			,@RequestParam(value="policyGroup", required=true)String policyGroup
			,@RequestParam(value="title", required=true)String title
			,@RequestParam(value="attachment", required=true)String attachment)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.insertNewNotificationToTable(session,notificationrfnum,type,description,policyGroup,title,attachment);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/fetchManageCompanyNotifications", method = RequestMethod.POST)
	public @ResponseBody String fetchManageCompanyNotifications(HttpServletRequest request, HttpSession session)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.getAllCompNotification(session);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	
	
	@RequestMapping(value = "/viewCompanyNotificationDoc", method = RequestMethod.GET)
	@ResponseBody
	public String viewExpDoc(HttpServletRequest request,HttpServletResponse response, HttpSession session,
			@RequestParam(value="notificationid", required=true)String notificationid) throws FileNotFoundException {
		//System.out.println("expenseCode"+expenseCode);
		//response.setContentType("application/vnd.ms-excel");
		String tempString = "";
		try{
			tempString = "NotificationDoc";
		response.setHeader("Content-disposition","inline; filename=" + tempString);
		NotificationDocBean doc = guiObj.getAttachmentDetails(session,notificationid);
		if(!(null == doc.getData())){
		OutputStream out = response.getOutputStream();
		//response.setContentType(doc.getDoctype());
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
	
	@RequestMapping(value="/getCompanyAnnouncementList", method = RequestMethod.POST)
	public @ResponseBody String getCompanyAnnouncementList(HttpServletRequest request, HttpSession session)
	{
		JSONArray finalData = new JSONArray();
		try{
			JSONArray jsonarr = guiObj.getAllCompNotification(session);
			for(int i=0;i<jsonarr.length();i++){
				JSONObject temp = jsonarr.getJSONObject(i);
				if(temp.getString("isActive").equalsIgnoreCase("Y") && temp.getString("policyGroup").equalsIgnoreCase(String.valueOf(session.getAttribute("loggedInUserPolicyGroup"))))
					finalData.put(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalData.toString();
		
		
	}
	@RequestMapping(value="/fetchPunchedRecordFromRemoteLoc", method = RequestMethod.POST)
	public @ResponseBody String fetchPunchedRecordFromRemoteLoc(HttpServletRequest request, HttpSession session
			,@RequestParam(value="startDate", required=true)String startDate,
			@RequestParam(value="endDate", required=true)String endDate,
			@RequestParam(value="username", required=true)String username)
	{
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = guiObj.fetchUserPunchedData(session,startDate,endDate,username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/deleteHRMSNotfication", method = RequestMethod.POST)
	public @ResponseBody String deleteHRMSNotfication(HttpServletRequest request, HttpSession session,@RequestParam(value="notificationid", required=true)String notificationid)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.removeCompanyNotificationLeave(session,notificationid);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/updateNotificationStatus", method = RequestMethod.POST)
	public @ResponseBody String updateAnnounancementStatus(HttpServletRequest request, HttpSession session,@RequestParam(value="notificationid", required=true)String notificationid,@RequestParam(value="status", required=true)boolean status)
	{
		JSONArray jsonarr = new JSONArray();
		boolean statusUpdate;
		try{
			statusUpdate = guiObj.updateNotification(session,notificationid,status);
			jsonarr.put(statusUpdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value="/createNewDocTemplate", method = RequestMethod.POST)
	public @ResponseBody String createNewDocTemplate(HttpServletRequest request,HttpServletResponse response, HttpSession session
			,@RequestParam(value="policyGroup", required=true)String policyGroup
			,@RequestParam(value="userGroup", required=true)String userGroup
			,@RequestParam(value="docType", required=true)String docType
			,@RequestParam(value="docDesc", required=true)String docDesc
			,@RequestParam(value="templateName", required=true)String templateName
			,@RequestParam(value="templateData", required=true)String templateData)
	{
		JSONArray jsonarr = new JSONArray();
		JSONObject jsobj = new JSONObject();
		String fileGenFlag = "";
		try{
			fileGenFlag = guiObj.createNewTemplate(session,policyGroup,userGroup,docType,docDesc,templateName,templateData);
			if(fileGenFlag.length()>0){
				jsobj.put("fileName",fileGenFlag);
			}else{
				jsobj.put("fileName", "Please try after some time.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.put(jsobj).toString();
	}
	@RequestMapping(value="/getUserGroupListBasedOnPolicy", method = RequestMethod.POST)
	public @ResponseBody String getUserGroupListBasedOnPolicy(HttpServletRequest request, HttpSession session,
			@RequestParam(value="param", required=true)String param)
	{
		try{
			return guiObj.fetchGroupListBasedOnPolicy(session,param);
		}catch(Exception e){
			e.printStackTrace();
			return null; 
		}
	}
	@RequestMapping(value="/getDocListBasedOnPolicyGroup", method = RequestMethod.POST)
	public @ResponseBody String getDocListBasedOnUserGroup(HttpServletRequest request, HttpSession session,
			@RequestParam(value="param", required=true)String param)
	{
		try{
			return guiObj.fetchDocListBasedOnUserGroup(session,param);
		}catch(Exception e){
			e.printStackTrace();
			return null; 
		}
	}
	@RequestMapping(value="/getTemplateListBasedOnDocName", method = RequestMethod.POST)
	public @ResponseBody String getTemplateListBasedOnDocName(HttpServletRequest request, HttpSession session,
			@RequestParam(value="param", required=true)String param)
	{
		try{
			return guiObj.fetchTemplateListBasedOnDocName(session,param);
		}catch(Exception e){
			e.printStackTrace();
			return null; 
		}
	}
	@RequestMapping(value="/generateEmpDocThroughTemplate", method = RequestMethod.POST)
	public @ResponseBody String generateEmpDocThroughTemplate(HttpServletRequest request,HttpServletResponse response, HttpSession session
			,@RequestParam(value="username", required=true)String username
			,@RequestParam(value="fullname", required=true)String fullname
			,@RequestParam(value="docName", required=true)String docName
			,@RequestParam(value="email", required=true)String email
			,@RequestParam(value="templateData", required=true)String templateData)
	{
		JSONArray jsonarr = new JSONArray();
		JSONObject jsobj = new JSONObject();
		String fileGenFlag = "";
		try{
			fileGenFlag = guiObj.generateNewDocument(session,username,fullname,docName,email,templateData);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.put(jsobj).toString();
	}
	@RequestMapping(value="/getAllGeneratedDocuments", method = RequestMethod.POST)
	public @ResponseBody String getAllGeneratedDocuments(HttpServletRequest request, HttpSession session,
			@RequestParam(value="activeFlag", required=true)String activeFlag)
	{
		try{
			return guiObj.fetchAllGeneretdDocList(session,activeFlag);
		}catch(Exception e){
			e.printStackTrace();
			return null; 
		}
	}
	@RequestMapping(value = "/downloadEmpDocPdf", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadRequestForm(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="rfnum", required=true)String rfnum) throws FileNotFoundException {
		String rootPath = System.getProperty("user.dir");
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.pdf'").format(new Date());
			tempString = rfnum + tempString;
			System.out.println(tempString);
		boolean genStatus = guiObj.generatePDFFile(session,rfnum,tempString);
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
	@RequestMapping(value = "/downloadEmpDocxFile", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadRequestDocForm(HttpServletRequest request,HttpServletResponse response, HttpSession session,@RequestParam(value="rfnum", required=true)String rfnum) throws FileNotFoundException {
		String rootPath = System.getProperty("user.dir");
		String tempString = "";
		File dir = null;
		try{
			tempString =  new SimpleDateFormat("yyyyMMdd'.docx'").format(new Date());
			tempString = rfnum + tempString;
			System.out.println(tempString);
		boolean genStatus = guiObj.generateDOCFile(session,rfnum,tempString);
		if(genStatus){
			dir = new File(rootPath + File.separator,tempString);
			//Path file = Paths.get(rootPath+ File.separator, tempString);
			response.setContentType("application/docx");
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
	@RequestMapping(value="/deleteGeneratedTempDoc", method = RequestMethod.POST)
	public @ResponseBody String deleteGeneratedTempDoc(HttpServletRequest request,
			HttpSession session,@RequestParam(value="docrfnum", required=true)String docrfnum)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.removeGeneratedDoc(session,docrfnum);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
	@RequestMapping(value={"/viewGeneratedTempDoc"},method=RequestMethod.GET)
	public String viewGeneratedTempDoc(HttpServletRequest request, HttpServletResponse response,ModelMap model
			,@RequestParam(value="docrfnum", required=true)String docrfnum)
	{
		try{
				HttpSession session = request.getSession(false);
				model.put("fileData", guiObj.getEmpDocumentData(session,docrfnum));
				model.put("docrfnum", docrfnum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "viewGeneratedDoc";
	}
	@RequestMapping(value={"/editGeneratedTempDoc"},method=RequestMethod.GET)
	public String editGeneratedTempDoc(HttpServletRequest request, HttpServletResponse response,ModelMap model
			,@RequestParam(value="docrfnum", required=true)String docrfnum)
	{
		try{
			HttpSession session = request.getSession(false);
				 model.put("fileData", guiObj.getEmpDocumentData(session,docrfnum));
				 model.put("docrfnum", docrfnum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "editGeneratedDoc";
	}
	@RequestMapping(value="/updateEmpDocTemplate", method = RequestMethod.POST)
	public @ResponseBody String updateEmpDocTemplate(HttpServletRequest request,
			HttpSession session,@RequestParam(value="docrfnum", required=true)String docrfnum
			,@RequestParam(value="templateData", required=true)String templateData)
	{
		JSONArray jsonarr = new JSONArray();
		boolean status;
		try{
			status = guiObj.updateGeneratedTemplateDoc(session,docrfnum,templateData);
			jsonarr.put(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr.toString();
	}
}
