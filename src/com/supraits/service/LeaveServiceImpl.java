package com.supraits.service;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.mysql.jdbc.StringUtils;
import com.supraits.dao.impl.GetQueryAPI;
import com.supraits.viewBean.ExpenseBean;
import com.supraits.viewBean.LeaveBean;
import com.supraits.viewBean.LeaveRequestBean;


@Service
public class LeaveServiceImpl implements LeaveService {

	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String currentDate = sdf.format(new Date());
	Calendar now = Calendar.getInstance();  
	int currYear = now.get(Calendar.YEAR);
	String currentYear = String.valueOf(currYear);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Value("${ADMIN}")
	private String ADMIN;
	@Value("${HR}")
	private String HR;
	@Value("${CANADA_ADMIN}")
	private String CANADA_ADMIN;
	@Value("${CANADA_HR}")
	private String CANADA_HR;
	@Value("${ADMIN_MAIL}")
	private String ADMIN_MAIL;
	
	@Value("${LEAVE_DELETED}")
	private String LEAVE_DELETED;
	@Value("${LEAVE_APPROVAL_PENDING}")
	private String LEAVE_APPROVAL_PENDING;
	@Value("${NEW_LEAVE_REQUEST}")
	private String NEW_LEAVE_REQUEST;
	@Value("${LEAVE_REQUEST_INTIMATION_TO_HR}")
	private String LEAVE_REQUEST_INTIMATION_TO_HR;
	@Value("${LEAVE_REQUEST_INTIMATION_TO_RM}")
	private String LEAVE_REQUEST_INTIMATION_TO_RM;
	@Value("${LEAVE_REQUEST_STATUS_UPDATE}")
	private String LEAVE_REQUEST_STATUS_UPDATE;
	@Value("${LEAVE_REQUEST_MEETING}")
	private String LEAVE_REQUEST_MEETING;
	
	@Value("${LEAVE_APPROVED}")
	private String LEAVE_APPROVED;
	@Value("${LEAVE_REJECTED}")
	private String LEAVE_REJECTED;
	@Value("${LEAVE_REVERSAL}")
	private String LEAVE_REVERSAL;
	@Value("${LEAVE_REVERSAL_APPROVED}")
	private String LEAVE_REVERSAL_APPROVED;
	
	@Value("${domain}")
	private String domain;
	@Value("${TEST_DELIVERY_MAIL_TO}")
	private String TEST_DELIVERY_MAIL_TO;
	
	
	@Value("${TM11}")
	private String TM11;
	@Value("${TM80}")
	private String TM80;
	@Value("${TM129}")
	private String TM129;
	@Value("${LMS1}")
	private String LMS1;
	@Value("${LMS2}")
	private String LMS2;
	@Value("${LMS3}")
	private String LMS3;
	@Value("${LMS4}")
	private String LMS4;
	@Value("${LMS5}")
	private String LMS5;
	@Value("${LMS6}")
	private String LMS6;
	@Value("${LMS7}")
	private String LMS7;
	@Value("${LMS8}")
	private String LMS8;
	@Value("${LMS9}")
	private String LMS9;
	@Value("${LMS10}")
	private String LMS10;
	@Value("${LMS11}")
	private String LMS11;
	@Value("${LMS12}")
	private String LMS12;
	@Value("${LMS13}")
	private String LMS13;
	@Value("${LMS14}")
	private String LMS14;
	@Value("${LMS15}")
	private String LMS15;
	@Value("${LMS16}")
	private String LMS16;
	@Value("${LMS17}")
	private String LMS17;
	@Value("${LMS18}")
	private String LMS18;
	@Value("${LMS19}")
	private String LMS19;
	@Value("${LMS20}")
	private String LMS20;
	@Value("${LMS21}")
	private String LMS21;
	@Value("${LMS22}")
	private String LMS22;
	@Value("${LMS23}")
	private String LMS23;
	@Value("${LMS26}")
	private String LMS26;
	@Value("${LMS27}")
	private String LMS27;
	@Value("${LMS28}")
	private String LMS28;
	@Value("${LMS29}")
	private String LMS29;
	@Value("${LMS30}")
	private String LMS30;
	@Value("${LMS32}")
	private String LMS32;
	@Value("${LMS33}")
	private String LMS33;
	@Value("${LMS34}")
	private String LMS34;
	@Value("${LMS35}")
	private String LMS35;
	@Value("${LMS36}")
	private String LMS36;
	@Value("${LMS37}")
	private String LMS37;
	@Value("${LMS38}")
	private String LMS38;
	@Value("${LMS40}")
	private String LMS40;
	@Value("${LMS41}")
	private String LMS41;
	@Value("${LMS42}")
	private String LMS42;
	@Value("${LMS43}")
	private String LMS43;
	@Value("${LMS44}")
	private String LMS44;
	@Value("${LMS45}")
	private String LMS45;
	@Value("${LMS46}")
	private String LMS46;
	@Value("${LMS47}")
	private String LMS47;
	@Value("${LMS48}")
	private String LMS48;
	@Value("${LMS49}")
	private String LMS49;
	@Value("${LMS50}")
	private String LMS50;
	@Value("${LMS51}")
	private String LMS51;
	@Value("${LMS52}")
	private String LMS52;
	@Value("${LMS53}")
	private String LMS53;
	@Value("${LMS54}")
	private String LMS54;
	@Value("${LMS55}")
	private String LMS55;
	@Value("${LMS56}")
	private String LMS56;
	@Value("${LMS57}")
	private String LMS57;
	@Value("${LMS58}")
	private String LMS58;
	@Value("${LMS59}")
	private String LMS59;
	@Value("${LMS60}")
	private String LMS60;
	@Value("${LMS61}")
	private String LMS61;
	@Value("${LMS62}")
	private String LMS62;
	@Value("${LMS63}")
	private String LMS63;
	@Value("${LMS64}")
	private String LMS64;
	@Value("${LMS65}")
	private String LMS65;
	@Value("${LMS66}")
	private String LMS66;
	@Value("${LMS67}")
	private String LMS67;
	@Value("${LMS68}")
	private String LMS68;
	@Value("${LMS69}")
	private String LMS69;
	@Value("${LMS70}")
	private String LMS70;
	@Value("${LMS71}")
	private String LMS71;
	@Value("${LMS72}")
	private String LMS72;
	@Value("${LMS73}")
	private String LMS73;
	@Value("${LMS74}")
	private String LMS74;
	@Value("${LMS75}")
	private String LMS75;
	
	@Value("${TM131}")
	private String TM131;
	@Value("${TM21}")
	private String TM21;
	@Value("${TM196}")
	private String TM196;
	@Override
	public String generateLeaveRequestNo(HttpSession session,String uid) {
		String query = "";
		String username = (String)session.getAttribute("loggedInUser");
		if(!(uid.equalsIgnoreCase("")) && uid != null)
			username = uid;
		try {
			query = GetQueryAPI.getQuery(LMS1,username);
			String requestNum = jdbcTemplate.queryForObject(query,String.class);
			if(null==requestNum || "".equalsIgnoreCase(requestNum))
			{
				requestNum = "LEAVE"+ username +"-0001";
			}
			else if(requestNum.contains("LEAVE") || requestNum.contains("leave")){
			//requestNum = LEAVE112-0008
				Integer num = new Integer(requestNum.substring(requestNum.lastIndexOf("-") + 1));
			//num = 0008
				num +=1; // num = num+1
			//num=0009
				DecimalFormat dcfm = new DecimalFormat("0000");
				requestNum = "LEAVE" +username+"-"+ dcfm.format(num);
			//requestNum = LEAVE112-0009
			}
			return requestNum;
		} catch (Exception e) {
			return "LEAVE"+ username +"-0001";
		}
	}
	
	@Override
	public JSONArray getListOfLeaveType(HttpSession session,String uid) {
		JSONArray listOfLeave = new JSONArray();
		JSONArray listOfLeaveFinal = new JSONArray();
		try{
			listOfLeave = jdbcTemplate.query(GetQueryAPI.getQuery(LMS2,session.getAttribute("loggedInUserPolicyGroup").toString()), new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
						while(rs.next()){
							try{
								JSONObject tempObj = new JSONObject();
								tempObj.put("leaveCode", rs.getString(1));
								tempObj.put("leaveName", rs.getString(2));
								tempObj.put("leavedesc", rs.getString(3));
								tempObj.put("applyByAdmin", rs.getString(4));
								tempObj.put("applyByManager", rs.getString(5));
								tempObj.put("applyByHR", rs.getString(6));
								tempObj.put("maxDaysPerRequest", rs.getString(7));
								temp.put(tempObj);
							}catch(Exception e){
								e.printStackTrace();
							}	
						}
					return temp;
				}	
				});
			if(uid != null && uid.length() != 0 && !(uid.equalsIgnoreCase(""))){
				boolean adminFlag = false;
				boolean rmFlag = false;
				boolean hrFlag = false;
				if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(CANADA_ADMIN)){
					adminFlag = true;
				}else{
					String userRM = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM131, uid), String.class);
					if(userRM != null && userRM.equalsIgnoreCase(session.getAttribute("loggedInUser").toString()))
						rmFlag = true;
					String userHR = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS61, uid), String.class);
					if(userHR != null && userHR.equalsIgnoreCase(session.getAttribute("loggedInUser").toString()))
						hrFlag = true;
					
				}
				for(int i=0;i<listOfLeave.length();i++){
					JSONObject temp = listOfLeave.getJSONObject(i);
					JSONObject tempObj = new JSONObject();
					tempObj.put("leaveCode", temp.get("leaveCode"));
					tempObj.put("leaveName", temp.get("leaveName"));
					tempObj.put("leavedesc", temp.get("leavedesc"));
					tempObj.put("maxDaysPerRequest", temp.get("maxDaysPerRequest"));
					if(adminFlag)
						tempObj.put("leaveFlag", temp.get("applyByAdmin"));
					if(rmFlag)
						tempObj.put("leaveFlag", temp.get("applyByManager"));
					if(hrFlag)
						tempObj.put("leaveFlag", temp.get("applyByHR"));
					listOfLeaveFinal.put(tempObj);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return (listOfLeaveFinal.length() > 0) ? listOfLeaveFinal : listOfLeave;
	}
	@Override
	public JSONArray getLeaveBalance(HttpSession session,String uid) {
		JSONArray leaveBalance = new JSONArray();
		Calendar currDate = Calendar.getInstance();
		String currYear = String.valueOf(currDate.get(Calendar.YEAR));
		String username = uid.equalsIgnoreCase("")?(String)session.getAttribute("loggedInUser"):uid;
		try{
			String userPolicyGroup = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS74,username), String.class);
			leaveBalance = jdbcTemplate.query(GetQueryAPI.getQuery(LMS3,username,currYear,userPolicyGroup,
					currYear,userPolicyGroup,currYear,userPolicyGroup,username) ,new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
						while(rs.next()){
							try{
								JSONObject tempObj = new JSONObject();
								tempObj.put("currLeaveYear", rs.getString(1));
								tempObj.put("currLeaveBal", rs.getString(2));
								tempObj.put("currLeaveId", rs.getString(3));
								tempObj.put("currLeaveName", rs.getString(4));
								tempObj.put("currLeaveDesc", rs.getString(5));
								tempObj.put("currLeaveCumulativeGroup", rs.getString(6));
								tempObj.put("applyByAdmin", rs.getString(7));
								tempObj.put("applyByManager", rs.getString(8));
								tempObj.put("applyByHR", rs.getString(9));
								tempObj.put("maxDaysPerRequest", rs.getString(10));
								tempObj.put("cumulativeGroupName", rs.getString(11));
								temp.put(tempObj);
							}catch(Exception e){
								e.printStackTrace();
							}	
						}
					return temp;
				}	
				});
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println(leaveBalance);
		return leaveBalance;
	}
	@Override
	public boolean insertNewLeaveRequest(HttpSession session,
			LeaveRequestBean leaveRequestBean, boolean submitStatus) {
		boolean insertStatus = false;
		try{
			boolean status = insertIntoLeaveRequestTable(session,leaveRequestBean); 
			if(status){
				boolean statusMappingTable = insertIntoLeaveRequestMappingTable(session,leaveRequestBean);
				if(statusMappingTable){
					updateUserLeaveBalance(session,leaveRequestBean.getRequestNumber(),"true");
					insertStatus = true;
					Iterator<LeaveBean> itrExp = leaveRequestBean.getLeaveList().iterator();
					Map<String,Object> leaveContent = new LinkedHashMap<String,Object>();
					while(itrExp.hasNext()){
						LeaveBean temp =itrExp.next();
						if(null == temp.getLeaveStartDate()){
							continue;
						}
						leaveContent.put(temp.getLeaveStartDate(), temp);
					}
					final Map<String,Object> finalLeaveContent = leaveContent;
					if(checkMailNotificationTrigger(NEW_LEAVE_REQUEST)){
						final String toMailId = getUserMailId(leaveRequestBean.getUsername());
						final String finalLeaveReqNum = leaveRequestBean.getRequestNumber();
						final String finalReqBy = (String)session.getAttribute("loggedInUserFullName");
						final String userfullname = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM21, leaveRequestBean.getUsername()),String.class);
						final String userdesignation = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM196, leaveRequestBean.getUsername()),String.class);
						String subLine = "";
						if(session.getAttribute("loggedInUser").toString().equalsIgnoreCase(leaveRequestBean.getRequestedBy()))
							subLine = "New Leave Request "+ finalLeaveReqNum +" has been initiated.";
						else
							subLine = "New Leave Request "+ finalLeaveReqNum +" has been initiated on your behalf by "+finalReqBy;
						final String finalSubLine = subLine; 
						Thread t = new Thread(){
							public void run(){
								Map<String,Object> mailContent = new HashMap<String,Object>();
								mailContent.put("leaveReqNumber", finalLeaveReqNum);
								mailContent.put("requestedBy", finalReqBy);
								mailContent.put("userfullname", userfullname);
								mailContent.put("userdesignation", userdesignation);
								mailContent.put("leaveList", finalLeaveContent);
								doSendTemplateEmail(toMailId,finalSubLine,mailContent,"newLeaveRequest.vm");
							}
						};
						t.start();
					}
					if(checkMailNotificationTrigger(LEAVE_REQUEST_INTIMATION_TO_RM)){
						//final String toRMMailId = getUserMailId((String)session.getAttribute("reportingManagerUserName"));
						final String toRMMailId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM129, leaveRequestBean.getUsername()),String.class);
						final String finalLeaveReqNum = leaveRequestBean.getRequestNumber();
						final String finalReqBy = (String)session.getAttribute("loggedInUserFullName");
						final String userfullname = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM21, leaveRequestBean.getUsername()),String.class);
						final String userdesignation = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM196, leaveRequestBean.getUsername()),String.class);
						Thread t = new Thread(){
							public void run(){
								Map<String,Object> mailContent = new HashMap<String,Object>();
								mailContent.put("leaveReqNumber", finalLeaveReqNum);
								mailContent.put("requestedBy", finalReqBy);
								mailContent.put("userfullname", userfullname);
								mailContent.put("userdesignation", userdesignation);
								mailContent.put("leaveList", finalLeaveContent);
								doSendTemplateEmail(toRMMailId,"New Leave Request "+ finalLeaveReqNum +" has been initiated and pending for your approval.",mailContent,"newLeaveRequest.vm");
							}
						};
						t.start();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return insertStatus;
	}
	private boolean insertIntoLeaveRequestMappingTable(HttpSession session,
			LeaveRequestBean leaveRequestBean) {
		boolean status = false;
		String query = "";
		try{
			Iterator<LeaveBean> leaveListItr = leaveRequestBean.getLeaveList().iterator(); 
			while(leaveListItr.hasNext()){
				LeaveBean temp = leaveListItr.next();
				Date date1 = sdf.parse(temp.getLeaveStartDate());
			    Date date2 = sdf.parse(temp.getLeaveEndDate());
			    long diff = date2.getTime() - date1.getTime();
			    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
			    double totatlDays = 0.0;
			    if(!(temp.getFullDayFlag().equalsIgnoreCase("Full Day")))
			    	totatlDays =  0.5 *((double) days);
			    else
			    	totatlDays =  (double) days;
			    if(session.getAttribute("loggedInUserProxy").toString().length() > 0)
			    	query = GetQueryAPI.getQuery(LMS5,leaveRequestBean.getRequestNumber(),temp.getLeaveCode(),leaveRequestBean.getUsername(),temp.getLeaveStartDate(),temp.getLeaveEndDate(),session.getAttribute("loggedInUserProxy").toString(),currentDate,temp.getLeavePurpose().replaceAll("'", "''"),String.valueOf(totatlDays),temp.getFullDayFlag());
				else
					query = GetQueryAPI.getQuery(LMS5,leaveRequestBean.getRequestNumber(),temp.getLeaveCode(),leaveRequestBean.getUsername(),temp.getLeaveStartDate(),temp.getLeaveEndDate(),session.getAttribute("loggedInUser").toString(),currentDate,temp.getLeavePurpose().replaceAll("'", "''"),String.valueOf(totatlDays),temp.getFullDayFlag());
			    System.out.println("Leave Request Mapping Query= "+query);
			    int updateStatus = jdbcTemplate.update(query);
				if(updateStatus>0)
					status = true;
			}
		}catch(Exception e){
			jdbcTemplate.update(GetQueryAPI.getQuery(LMS71,leaveRequestBean.getRequestNumber()));
			jdbcTemplate.update(GetQueryAPI.getQuery(LMS72,leaveRequestBean.getRequestNumber()));
			e.printStackTrace();
		}
		return status;
	}
	private boolean insertIntoLeaveRequestTable(HttpSession session,
			LeaveRequestBean leaveRequestBean) {
		boolean status = false;
		String query = "";
		try{
			double totatlDays = 0; 
			for(int i=0;i<leaveRequestBean.getLeaveList().size();i++){
				Date date1 = sdf.parse(leaveRequestBean.getLeaveList().get(i).getLeaveStartDate());
			    Date date2 = sdf.parse(leaveRequestBean.getLeaveList().get(i).getLeaveEndDate());
			    long diff = date2.getTime() - date1.getTime();
			    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
			    if(!(leaveRequestBean.getLeaveList().get(i).getFullDayFlag().equalsIgnoreCase("Full Day")))
			    	totatlDays = totatlDays + 0.5 *((double) days);
			    else
			    	totatlDays = totatlDays + (double) days;
			}
			if(session.getAttribute("loggedInUserProxy").toString().length() > 0)
				query = GetQueryAPI.getQuery(LMS4,leaveRequestBean.getRequestNumber(),String.valueOf(totatlDays),leaveRequestBean.getUsername(),currentDate,session.getAttribute("loggedInUser").toString(),"NA",LEAVE_APPROVAL_PENDING,session.getAttribute("loggedInUserProxy").toString(),currentDate,currentYear);
			else
				query = GetQueryAPI.getQuery(LMS4,leaveRequestBean.getRequestNumber(),String.valueOf(totatlDays),leaveRequestBean.getUsername(),currentDate,session.getAttribute("loggedInUser").toString(),"NA",LEAVE_APPROVAL_PENDING,session.getAttribute("loggedInUser").toString(),currentDate,currentYear);
			System.out.println("Leave Request Query= "+query);
			int updateStatus = jdbcTemplate.update(query);
			if(updateStatus>0)
				status = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
private boolean checkMailNotificationTrigger(String requestActionId) {
	boolean status = false;
	String query = "";
	try{
		query = GetQueryAPI.getQuery(TM80, requestActionId);
		String triggerStatus = jdbcTemplate.queryForObject(query,String.class); 
		if(triggerStatus.equalsIgnoreCase("true"))
			status = true;
	}catch(Exception e){
		System.out.println("Error encountered");
		e.printStackTrace();
	}
	return status;
}
public String doSendTemplateEmail(String recipientAddress,String subject,Map<String, Object> model,String templateName) {
	final MimeMessage mimeMessage = mailSender.createMimeMessage();
	MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
	try{
	email.setFrom(ADMIN_MAIL);	
	if(domain.equalsIgnoreCase("timesheet.supraes.com"))
		email.setTo(recipientAddress);
	else
		email.setTo(TEST_DELIVERY_MAIL_TO);
	email.setSubject(subject);
    String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,"UTF-8",model);
	email.setText(message,true);
	this.mailSender.send(mimeMessage);
	}catch(Exception e){
		e.printStackTrace();
	}
	return "Result";
}
public String doSendTemplateEmail(String[] to,String[] cc,String subject,Map<String, Object> model,String templateName) {
	final MimeMessage mimeMessage = mailSender.createMimeMessage();
	MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
	try{
	email.setFrom(ADMIN_MAIL);	
	if(domain.equalsIgnoreCase("timesheet.supraes.com")){
		email.setTo(to);
		email.setCc(cc);
	}else
		email.setTo(TEST_DELIVERY_MAIL_TO);
	email.setSubject(subject);
    String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,"UTF-8",model);
	email.setText(message,true);
	this.mailSender.send(mimeMessage);
	}catch(Exception e){
		e.printStackTrace();
	}
	return "Result";
}
private String getUserMailId(String uid) {
	String query = "";
	String mailId = "";
	try{
	query =GetQueryAPI.getQuery(TM11, uid);
	mailId = jdbcTemplate.queryForObject(query, String.class);
	}catch(Exception e){
		e.printStackTrace();
	}
	return mailId;
}
@SuppressWarnings("unchecked")
@Override
public JSONArray getAllLeaveRequestList(HttpSession session, String filterVar,
		String allFlag) {
	String query = "";
	JSONArray reqList = new JSONArray();
	try{
		if(filterVar.equalsIgnoreCase("pendingUserRequest")){
			if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(CANADA_ADMIN)){
				query = GetQueryAPI.getQuery(LMS26,session.getAttribute("loggedInUserPolicyGroup").toString());
				//System.out.println(query);
			}else{
				query = GetQueryAPI.getQuery(LMS9,session.getAttribute("loggedInUser").toString());
			}
		}else{
			query = GetQueryAPI.getQuery(LMS6,session.getAttribute("loggedInUser").toString());
		}
		reqList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("requestnumber", rs.getString(1));
						tempObj.put("creationdate", rs.getString(2));
						tempObj.put("totalLeaveDays", rs.getString(3));
						tempObj.put("requeststatus", rs.getString(4));
						tempObj.put("approverremark", rs.getString(5));
						tempObj.put("lastmodifiedby", rs.getString(6));
						tempObj.put("lastmodifiedon", rs.getString(7));
						tempObj.put("username", rs.getString(8));
						tempObj.put("userfullname", rs.getString(9));
						//System.out.println(GetQueryAPI.getQuery(LMS32,rs.getString(1)));
						tempObj.put("leavestartdate", jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS32,rs.getString(1)),String.class));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
			
		}); 
	}catch(Exception e){
		e.printStackTrace();
	}
	return reqList;
}
@Override
public JSONArray getPendingLeaveRequestDetails(HttpSession session,
		String reqNumber) {

	String query = "";
	JSONArray reqDetailList = new JSONArray();
	try{
		query = GetQueryAPI.getQuery(LMS7,reqNumber);
		reqDetailList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("leavehead", rs.getString(1));
						tempObj.put("startdate", rs.getString(2));
						tempObj.put("enddate", rs.getString(3));
						tempObj.put("days", rs.getString(4));
						tempObj.put("purpose", rs.getString(5));
						tempObj.put("fulldayflag", rs.getString(6));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
			
		}); 
	}catch(Exception e){
		e.printStackTrace();
	}
	return reqDetailList;
}
@Override
public JSONArray getLeaveList(HttpSession session) {

	// TODO Auto-generated method stub
	JSONArray bucketList = new JSONArray();
	bucketList = jdbcTemplate.query(GetQueryAPI.getQuery(LMS10), new ResultSetExtractor<JSONArray>(){

		@Override
		public JSONArray extractData(java.sql.ResultSet rsBucket)
				throws SQLException, DataAccessException {
			JSONArray tempList = new JSONArray();
			while(rsBucket.next()){
				JSONObject obj = new JSONObject();
				try {
					obj.put("leaveid",rsBucket.getString(1));
					obj.put("leavename",rsBucket.getString(2));
					obj.put("leavecreatedby",rsBucket.getString(3));
					obj.put("leavecreatedon",rsBucket.getString(4));
					obj.put("leavedesc",rsBucket.getString(5));
					obj.put("policygroup",rsBucket.getString(6));
					obj.put("leavegroup",rsBucket.getString(7));
					obj.put("applymindaysbefore",rsBucket.getString(8));
					obj.put("maxdaysperrequest",rsBucket.getString(9));
					obj.put("applybyadmin",rsBucket.getString(10));
					obj.put("applybymanager",rsBucket.getString(11));
					obj.put("applybyhr",rsBucket.getString(12));
					obj.put("cumulativegroupname",rsBucket.getString(13));
					obj.put("activeflag",rsBucket.getString(14));
					obj.put("cumulativegroup",rsBucket.getString(15));
					tempList.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return tempList;
		}
	});
	return bucketList;

}
@Override
public boolean createLMSLeave(HttpSession session, String leaveName, String leaveDesc, String policyGroup, 
		String leaveGroup, String applyDaysBefore, String cumulativeGroup, String applyByAdmin, String applyByManager, String applyByHR, String maxDaysPerRequest) {
	int createCount = 0;
	if(Integer.parseInt(applyDaysBefore) < 0){
		return false;
	}
	if(policyGroup.equalsIgnoreCase("SUPRA-Noida") || policyGroup.equalsIgnoreCase("SUPRA-Canada")){
		createCount = jdbcTemplate.update(GetQueryAPI.getQuery(LMS13,leaveName,leaveDesc,(String)session.getAttribute("loggedInUser"),sdf.format(new Date()).toString(),policyGroup,leaveGroup,applyDaysBefore,cumulativeGroup,applyByAdmin,applyByManager,applyByHR,maxDaysPerRequest));
		if(cumulativeGroup.equalsIgnoreCase("") && cumulativeGroup.trim().length() == 0){
			String leaveCode = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS65,leaveName),String.class);
			jdbcTemplate.update(GetQueryAPI.getQuery(LMS63,leaveCode,leaveName));
		}
	}
	if(createCount == 1)
		return true;
	else
		return false;
}
@Override
public boolean updateLMSLeaveName(HttpSession session, String leaveName,
		String leaveId,String leaveDesc,String applyMinDaysBefore, String leaveApplyMaxDays, String leaveActiveFlag, String applyByAdmin, String applyByManager, String applyByHR, String cumulativegroup) {
	int updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(LMS12,leaveName,leaveDesc,applyMinDaysBefore,leaveApplyMaxDays,leaveActiveFlag,applyByAdmin,applyByManager,applyByHR,cumulativegroup,leaveId));
	//System.out.println(GetQueryAPI.getQuery(LMS12,leaveName,leaveDesc,applyMinDaysBefore,leaveApplyMaxDays,leaveActiveFlag,applyByAdmin,applyByManager,applyByHR,cumulativegroup,leaveId));
	if(updateCount == 1)
		return true;
	else
		return false;
}
@Override
public boolean removeLMSLeave(HttpSession session, String leaveId) {
	int prevEntryCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS14, leaveId),Integer.class);
	if(prevEntryCount > 0)
		return false;
	else{
		 jdbcTemplate.update(GetQueryAPI.getQuery(LMS11, leaveId));
		 return true;
	}	
}
@Override
public JSONArray getQuarterLeaveListBandBased(HttpSession session) {
	JSONArray bandList = new JSONArray();
	bandList = jdbcTemplate.query(GetQueryAPI.getQuery(LMS15), new ResultSetExtractor<JSONArray>(){

		@Override
		public JSONArray extractData(java.sql.ResultSet rsBucket)
				throws SQLException, DataAccessException {
			JSONArray tempList = new JSONArray();
			while(rsBucket.next()){
				JSONObject obj = new JSONObject();
				try {
					obj.put("bandid",rsBucket.getString(1));
					obj.put("leavename",rsBucket.getString(2));
					obj.put("quartercount",rsBucket.getString(3));
					obj.put("annualcount",rsBucket.getString(4));
					obj.put("leaveid",rsBucket.getString(5));
					tempList.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return tempList;
		}
	});
	return bandList;
}
@Override
public String updateLMSRemarkAndStatus(HttpSession session, String reqNumber,
		String approveFlag, String reqStatus, String remark) {
	boolean status = false;
	String modifiedStatus = "";
	try{
		if(approveFlag.equalsIgnoreCase("true")){
			String currStatus = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS23,reqNumber),String.class);
			if(LEAVE_REVERSAL.equalsIgnoreCase(currStatus)){
				int updateStatus = jdbcTemplate.update(GetQueryAPI.getQuery(LMS16,LEAVE_REVERSAL_APPROVED,remark,session.getAttribute("loggedInUser").toString(), reqNumber));
				status= updateUserLeaveBalance(session,reqNumber,approveFlag);
				//updateTimesheetTable(session,reqNumber,LEAVE_REVERSAL_APPROVED);
				modifiedStatus = LEAVE_REVERSAL_APPROVED;
			}else{
				int updateStatus = 0;
				//Modified by abhinav_gupta on Jan 18 2018 
				//updation of Timesheet table removed. 
				//boolean timesheetStatusUpdate = updateTimesheetTable(session,reqNumber,LEAVE_APPROVED);
				//if(timesheetStatusUpdate)
					updateStatus = jdbcTemplate.update(GetQueryAPI.getQuery(LMS16,LEAVE_APPROVED,remark,session.getAttribute("loggedInUser").toString(), reqNumber));
				//else
				//	return "Kindly reject timesheet before leave approval.";
				if(updateStatus != 0){
					status= true;
					modifiedStatus = LEAVE_APPROVED;
				}
			}
		}
		if(approveFlag.equalsIgnoreCase("false")){
			int updateStatus = jdbcTemplate.update(GetQueryAPI.getQuery(LMS16,LEAVE_REJECTED,remark,session.getAttribute("loggedInUser").toString(), reqNumber));
			if(updateStatus != 0){
				status= updateUserLeaveBalance(session,reqNumber,approveFlag);
				modifiedStatus = LEAVE_REJECTED;
			}	
		}
		if(approveFlag.equalsIgnoreCase(LEAVE_REVERSAL)){
			int updateStatus = jdbcTemplate.update(GetQueryAPI.getQuery(LMS17,LEAVE_REVERSAL,remark,session.getAttribute("loggedInUser").toString(), reqNumber));
			if(updateStatus != 0){
				status= updateUserLeaveBalance(session,reqNumber,approveFlag);
				modifiedStatus = LEAVE_REVERSAL;
			}
		}
		if(approveFlag.equalsIgnoreCase(LEAVE_DELETED)){
			int updateStatus = jdbcTemplate.update(GetQueryAPI.getQuery(LMS17,LEAVE_DELETED,remark,session.getAttribute("loggedInUser").toString(), reqNumber));
			if(updateStatus != 0){
				status= updateUserLeaveBalance(session,reqNumber,approveFlag);
				modifiedStatus = LEAVE_DELETED;
			}
		}
		JSONArray userCurrLeave = getPendingLeaveRequestDetails(session,reqNumber);
		Map<String,Object> updatedLeaveSummaryMap = new LinkedHashMap<String,Object>();
		for(int i=0;i<userCurrLeave.length();i++){
			updatedLeaveSummaryMap.put("leaveType"+i,userCurrLeave.get(i));
		}
		final Map<String,Object> updatedLeaveSummary = updatedLeaveSummaryMap;
		if(checkMailNotificationTrigger(LEAVE_REQUEST_STATUS_UPDATE)){
			final String toMailId = getUserMailId(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS34, reqNumber), String.class));
			final String finalLeaveReqNum = reqNumber;
			final String finalReqBy = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS34, reqNumber), String.class);
			final String finalCurrStatus = modifiedStatus;
			Thread t = new Thread(){
				public void run(){
					Map<String,Object> mailContent = new HashMap<String,Object>();
					mailContent.put("leaveReqNumber", finalLeaveReqNum);
					mailContent.put("requestedBy", finalReqBy);
					mailContent.put("currentLeaveStatus", finalCurrStatus);
					mailContent.put("leaveList", updatedLeaveSummary);
					doSendTemplateEmail(toMailId,"Leave Request "+finalLeaveReqNum+" has been modified.",mailContent,"lmsUpdateIntimationToUser.vm");
				}
			};
			t.start();
		}
		if(checkMailNotificationTrigger(LEAVE_REQUEST_INTIMATION_TO_HR)){
			final String toHRMailId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS33, jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS34, reqNumber), String.class)), String.class);
			final String finalLeaveReqNum = reqNumber;
			final String finalReqBy = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS34, reqNumber), String.class);
			final String finalCurrStatus = modifiedStatus;
			Thread t = new Thread(){
				public void run(){
					Map<String,Object> mailContent = new HashMap<String,Object>();
					mailContent.put("leaveReqNumber", finalLeaveReqNum);
					mailContent.put("currentLeaveStatus", finalCurrStatus);
					mailContent.put("requestedBy", finalReqBy);
					mailContent.put("leaveList", updatedLeaveSummary);
					doSendTemplateEmail(toHRMailId,"Leave Request "+ finalLeaveReqNum +" has been modified",mailContent,"lmsUpdateIntimationToHR.vm");
				}
			};
			t.start();
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return String.valueOf(status);
}
private boolean updateTimesheetTable(HttpSession session,String reqNumber, String approveReverseFlag) {
	final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	String queryFetch = "";
	boolean status = false;
	try{
		if(!checkUserAccessToTS(reqNumber))
			return true;
		queryFetch = GetQueryAPI.getQuery(LMS48, reqNumber);
		Map<String,String> leaveData = jdbcTemplate.query(queryFetch, new ResultSetExtractor<Map<String,String>>(){

			@Override
			public Map<String, String> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<String,String> leaveData = new LinkedHashMap<String,String>();
				try{
					while(rs.next()){
						List<Date> allLeaveDate = getDaysBetweenDates(sdf2.parse(rs.getString(1)),sdf2.parse(rs.getString(1)));
						for(Date d: allLeaveDate){
							if(rs.getString(3).equalsIgnoreCase("Full Day")){
								leaveData.put(sdf2.format(d), "9$"+rs.getString(4));
							}else{
								leaveData.put(sdf2.format(d), "4.5$"+rs.getString(4));
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				System.out.println(leaveData);
				return leaveData;
			}
		});
		if(leaveData.size() > 0){
			Iterator<String> itr = leaveData.keySet().iterator();
			while(itr.hasNext()){
				String keyDate = itr.next();
				String keyValue = leaveData.get(keyDate);
				String username = keyValue.substring(keyValue.indexOf("$") + 1);
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(keyDate));
			    cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
				String firstDayOfWeek = sdf.format(cal.getTime());
				//If leave reversal is approved,leave task entry will be deleted from user_task_mapping table
				if(approveReverseFlag.equalsIgnoreCase(LEAVE_REVERSAL_APPROVED)){
					jdbcTemplate.update(GetQueryAPI.getQuery(LMS51,username,"PSUP7991Leav",keyDate,keyDate));
					status = true;
				}else{
					String effort = keyValue.substring(0,keyValue.indexOf("$"));
					try {
						String statusStr = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS50, username,keyDate), String.class);
						if(statusStr.equalsIgnoreCase("Rejected")){
							jdbcTemplate.update(GetQueryAPI.getQuery(LMS51,username, "PSUP7991Leav",keyDate,keyDate));
							//jdbcTemplate.update(GetQueryAPI.getQuery(LMS49, "PSUP7991Leav",username,firstDayOfWeek,"0","Rejected",(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),"PSUPRA001","false"));
							jdbcTemplate.update(GetQueryAPI.getQuery(LMS49, "PSUP7991Leav",username,keyDate,effort,"Rejected",
								(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),"PSUPRA001","false"));
							status = true;
						}else if(statusStr.equalsIgnoreCase("Saved")){
							jdbcTemplate.update(GetQueryAPI.getQuery(LMS51,username, "PSUP7991Leav",keyDate,keyDate));
							//jdbcTemplate.update(GetQueryAPI.getQuery(LMS49, "PSUP7991Leav",username,firstDayOfWeek,"0","Saved",(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),"PSUPRA001","false"));
							jdbcTemplate.update(GetQueryAPI.getQuery(LMS49, "PSUP7991Leav",username,keyDate,effort,"Saved",
									(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),"PSUPRA001","false"));
							status = true;
						}else{
							status = false;
							break;
						}
				  	}catch (Exception e) {
					//If Timesheet is not filled
				  		jdbcTemplate.update(GetQueryAPI.getQuery(LMS49, "PSUP7991Leav",username,firstDayOfWeek,"0","Saved",
								(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),"PSUPRA001","false"));	
					jdbcTemplate.update(GetQueryAPI.getQuery(LMS49, "PSUP7991Leav",username,keyDate,effort,"Saved",
							(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),"PSUPRA001","false"));
					status = true;
				    }
				}
			}
		}
	}catch(Exception e){
		System.out.println("Timesheet updation encountered error!");
		e.printStackTrace();
	}
	//System.out.println(status);
	return status;
}
private boolean checkUserAccessToTS(String reqNum) {
	try{
		int count = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS52, reqNum),Integer.class);
		if(count != 0)
			return true;
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return false;
}
private List<Date> getDaysBetweenDates(Date startdate, Date enddate)
{
    List<Date> dates = new ArrayList<Date>();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(startdate);
    
    while (calendar.getTime().before(enddate))
    {
        Date result = calendar.getTime();
        dates.add(result);
        calendar.add(Calendar.DATE, 1);
    }
    Calendar callast = new GregorianCalendar();
    callast.setTime(enddate);
    dates.add(callast.getTime());
    return dates;
}
private boolean updateUserLeaveBalance(HttpSession session,String reqNumber, String approveFlag) {
	boolean status = false;
	try{
		String leaveGroup = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS47, reqNumber),String.class);
		if(leaveGroup.equalsIgnoreCase("Unpaid") && leaveGroup != null)
			return true;
		JSONArray fetchLeave = jdbcTemplate.query(GetQueryAPI.getQuery(LMS18, reqNumber), new ResultSetExtractor<JSONArray>(){
			@Override
			public JSONArray extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("leaveHead", rs.getString(1));
						tempObj.put("currValue", rs.getString(2));
						tempObj.put("username", rs.getString(3));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
		for(int i=0;i<fetchLeave.length();i++){
			JSONObject temp = fetchLeave.getJSONObject(i);
			String cumulativeGroupId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS64,temp.get("leaveHead").toString()), String.class);
			System.out.println("cumulativeGroupId="+cumulativeGroupId);
			double leaveDays = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS19, reqNumber,cumulativeGroupId), Double.class);
			System.out.println("leaveDays="+leaveDays);
			double userExistingBalance = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS20, temp.get("username").toString(),cumulativeGroupId), Double.class);
			System.out.println(i+"UserExistingBalance="+userExistingBalance);
			String leaveYear = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS22, temp.get("username").toString(),temp.get("leaveHead").toString()), String.class);
			System.out.println("leaveYear="+leaveYear);
			String lastModifiedBy = session.getAttribute("loggedInUserProxy").toString().equalsIgnoreCase("")?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString();
			double userUpdatedBalance = userExistingBalance; 
			if(approveFlag.equalsIgnoreCase("true"))
				userUpdatedBalance = userExistingBalance - leaveDays;
			else
				userUpdatedBalance = userExistingBalance + leaveDays;
			System.out.println(i+"userUpdatedBalance="+userUpdatedBalance);
			System.out.println(GetQueryAPI.getQuery(LMS21,String.valueOf(userUpdatedBalance),lastModifiedBy,temp.get("username").toString(),cumulativeGroupId,leaveYear));
			int updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(LMS21,String.valueOf(userUpdatedBalance),lastModifiedBy,temp.get("username").toString(),cumulativeGroupId,leaveYear));
			System.out.println(i+"leave code "+cumulativeGroupId+" updated "+updateCount+" row");
		}
		
		status = true;
	}catch(Exception e){
		e.printStackTrace();
	}
	return status;
}
@Override
public JSONArray getUsersLeaveBalance(HttpSession session, String policyGroup){
	List<String> leaveType = jdbcTemplate.queryForList(GetQueryAPI.getQuery(LMS27,policyGroup),String.class);
	List<String> leaveCodeList1 = new ArrayList<String>();
	for (String list: leaveType){
		leaveCodeList1.add("leavecode"+list);	
	}
	JSONObject tempListHead = new JSONObject();
	try{
		tempListHead.put("tempListHead", jdbcTemplate.queryForList(GetQueryAPI.getQuery(LMS30,policyGroup),String.class));
		tempListHead.put("tempListCode",leaveCodeList1);
	}catch(Exception e){e.printStackTrace();}	
	StringBuffer queryString = new StringBuffer();
	for (String list: leaveType){
		queryString.append(GetQueryAPI.getQuery(LMS28,list,list));	
	}
	System.out.println(queryString);
	//System.out.println(GetQueryAPI.getQuery(LMS29,queryString.toString(),policyGroup));
	JSONArray bandList = new JSONArray();
	final List<String> tempList1 = leaveType;
	bandList = jdbcTemplate.query(GetQueryAPI.getQuery(LMS29,queryString.toString(),policyGroup) , new ResultSetExtractor<JSONArray>(){
		@Override
		public JSONArray extractData(java.sql.ResultSet rsBucket)
				throws SQLException, DataAccessException {
			JSONArray tempList = new JSONArray();
			while(rsBucket.next()){
				JSONObject obj = new JSONObject();
				try {
					int k = 1;
					for (String list: tempList1){
						if(null == rsBucket.getString(k))
							obj.put("leavecode"+list,"0");
						else
							obj.put("leavecode"+list,rsBucket.getString(k));
						k++;
					}
					if(null == rsBucket.getString(tempList1.size()+1))
						obj.put("bandid","1.0");
					else
						obj.put("bandid",rsBucket.getString(tempList1.size()+1));
					obj.put("username",rsBucket.getString(tempList1.size()+2));
					obj.put("userfullname",rsBucket.getString(tempList1.size()+3));
					
					tempList.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return tempList;
		}
	});
	bandList.put(tempListHead);
	//System.out.println(bandList);
	return bandList;
}
@Override
public JSONArray getUserLeaveBalanceUpdationFlag(HttpSession session) {
	JSONArray userCurrLeave = new JSONArray();
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	try{
		String query = GetQueryAPI.getQuery(LMS35,(String)session.getAttribute("loggedInUser"),String.valueOf(year),(String)session.getAttribute("loggedInUserPolicyGroup"));
		userCurrLeave = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("leaveCode", rs.getString(1));
						tempObj.put("leaveName", rs.getString(2));
						tempObj.put("leaveUpdatedBalance", rs.getString(3));
						tempObj.put("leaveUpdationFlag", rs.getString(4));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	//System.out.println(userCurrLeave);
	return userCurrLeave;
}
@Override
public boolean changeUserLeaveBalanceUpdationFlag(HttpSession session,String updateFlag) {
	boolean statusUpdate = false;
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	try{
		if(updateFlag.equalsIgnoreCase("true")){
			jdbcTemplate.update(GetQueryAPI.getQuery(LMS36,"N",(String)session.getAttribute("loggedInUser"),String.valueOf(year)));
		}else{
			final String hrMail = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS33,(String)session.getAttribute("loggedInUser")), String.class);
			final String rmMail = getUserMailId((String)session.getAttribute("reportingManagerUserName"));
			final String finalUserName = (String)session.getAttribute("loggedInUser");
			final String finalFullUserName = (String)session.getAttribute("loggedInUserFullName");
			final String subject = "Employee "+(String)session.getAttribute("loggedInUserFullName")+" has descrepency in last leave balance updation.";
			JSONArray userCurrLeave = getUserLeaveBalanceUpdationFlag(session);
			Map<String,Object> updatedLeaveSummaryMap = new LinkedHashMap<String,Object>();
			for(int i=0;i<userCurrLeave.length();i++){
				updatedLeaveSummaryMap.put("leaveType"+i,userCurrLeave.get(i));
			}
			final Map<String,Object> updatedLeaveSummary = updatedLeaveSummaryMap;
			Thread t = new Thread(){
				@Override
				public void run(){
				Map<String,Object> mailContent = new LinkedHashMap<String,Object>();
				mailContent.put("username", finalUserName);
				mailContent.put("userfullname", finalFullUserName);
				mailContent.put("updatedleavesummary", updatedLeaveSummary);
					doSendTemplateEmail(rmMail, subject, mailContent, "leaveBalanceDescrepency.vm");
					doSendTemplateEmail(hrMail, subject, mailContent, "leaveBalanceDescrepency.vm");
				}
			};
			t.start();
		}
		statusUpdate = true;
	}catch(Exception e){
		e.printStackTrace();
	}
	return statusUpdate;
}
@Override
public JSONArray checkUserAppliedLeaveList(HttpSession session,
		List<String> applyDateList,String uid) {
	JSONArray jsList = new JSONArray();
	String queryStartDate = "";
	String queryEndDate = "";
	List<String> distinctAppliedList = new LinkedList<String>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	try{
		queryStartDate = GetQueryAPI.getQuery(LMS37,uid.equalsIgnoreCase("")?session.getAttribute("loggedInUser").toString():uid,String.valueOf(year));
		queryEndDate = GetQueryAPI.getQuery(LMS38, uid.equalsIgnoreCase("")?session.getAttribute("loggedInUser").toString():uid,String.valueOf(year));
		/*@SuppressWarnings("unchecked")
		List<java.sql.Date> getStartDates  = jdbcTemplate.queryForObject(queryStartDate,List.class);
		@SuppressWarnings("unchecked")
		List<java.sql.Date> getEndDates  = jdbcTemplate.queryForObject(queryEndDate,List.class);*/
		
		List<String> getStartDates = jdbcTemplate.query(queryStartDate, new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
            }
       });
		List<String> getEndDates = jdbcTemplate.query(queryEndDate, new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
            }
       });
		for(int i=0;i<getStartDates.size();i++){
			List<String> dates = new ArrayList<String>();
		    Calendar calendar = new GregorianCalendar();
		    calendar.setTime(sdf.parse(getStartDates.get(i)));

		    do{
		        Date result = calendar.getTime();
		        dates.add(sdf.format(result));
		        calendar.add(Calendar.DATE, 1);
		    }
		    while(calendar.getTime().before(sdf.parse(getEndDates.get(i))));
		    //System.out.println(dates);
		    distinctAppliedList.addAll(dates);
		}
		List<String> commonDate = new LinkedList<String>();
		for(int k=0;k<applyDateList.size();k++){
			if(distinctAppliedList.contains(applyDateList.get(k)))
				commonDate.add(applyDateList.get(k));
		}
		//applyDateList.retainAll(distinctAppliedList);
		//System.out.println(commonDate);
		if(commonDate.size() > 0)
			jsList.put(commonDate);
		//System.out.println(jsList.length());
	}catch(Exception e){
		e.printStackTrace();
	}
	//System.out.println(jsList);
	return jsList;
}
@Override
public JSONArray getUnpaidLeaveList(HttpSession session) {
	List<Integer> unpaidLeaveList = new LinkedList<Integer>();
	try{
		unpaidLeaveList = jdbcTemplate.queryForList(LMS40, Integer.class);
	}catch(Exception e){
		e.printStackTrace();
	}
	return new JSONArray(unpaidLeaveList);
}
@Override
public JSONArray getApplyBeforeLeaveParam(HttpSession session) {

	JSONArray userCurrLeave = new JSONArray();
	try{
		String query = GetQueryAPI.getQuery(LMS41);
		userCurrLeave = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("leaveCode", rs.getString(1));
						tempObj.put("applyDaysBefore", rs.getString(2));
						tempObj.put("leavename", rs.getString(3));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
	}catch(Exception e){
		
	}
	//System.out.println(userCurrLeave);
	return userCurrLeave;

}
@Override
public JSONArray getMaxDayForRequestLeaveParam(HttpSession session) {

	JSONArray userCurrLeave = new JSONArray();
	try{
		String query = GetQueryAPI.getQuery(LMS62);
		userCurrLeave = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("leaveCode", rs.getString(1));
						tempObj.put("maxDaysAllowedPerReq", rs.getString(2));
						tempObj.put("leavename", rs.getString(3));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
	}catch(Exception e){
		
	}
	//System.out.println(userCurrLeave);
	return userCurrLeave;

}
@Override
public JSONArray viewLeaveReport(HttpSession session, String username,
		String hrPolicyGroup, String startDate, String endDate,
		String leaveStatus) {
	JSONArray leaveReportData = new JSONArray();
	String query = "";
	try{
		if(username.equalsIgnoreCase("")){
			if(!(leaveStatus.equalsIgnoreCase("")))
				query = GetQueryAPI.getQuery(LMS43,hrPolicyGroup,leaveStatus,startDate,endDate);
			else
				query = GetQueryAPI.getQuery(LMS46,hrPolicyGroup,startDate,endDate);
		}else{
			if(!(leaveStatus.equalsIgnoreCase("")))
				query = GetQueryAPI.getQuery(LMS42,username,hrPolicyGroup,leaveStatus,startDate,endDate);
			else
				query = GetQueryAPI.getQuery(LMS45,username,hrPolicyGroup,startDate,endDate);
		}	
		//System.out.println(query);
		leaveReportData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("policygroup", rs.getString(1));
						tempObj.put("fullname", rs.getString(2));
						tempObj.put("email", rs.getString(3));
						tempObj.put("rmname", rs.getString(4));
						tempObj.put("rmemail", rs.getString(5));
						tempObj.put("leavename", rs.getString(6));
						tempObj.put("startdate", rs.getString(7));
						tempObj.put("enddate", rs.getString(8));
						tempObj.put("totaldays", rs.getString(9));
						tempObj.put("applydate", rs.getString(10));
						tempObj.put("status", rs.getString(11));
						tempObj.put("purpose", rs.getString(12));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
		final JSONArray reportData = leaveReportData;
		Thread t = new Thread(){
			public void run(){
				generateXLSReport(reportData);
			}
		};
		t.start();
	}catch(Exception e){
		e.printStackTrace();
	}
	return leaveReportData;
}
private void generateXLSReport(JSONArray reportData) {
	boolean statusFile = false;
	List<List<String>> recordToAdd = new ArrayList<List<String>>();
	try{
		List<String> headerRow = new ArrayList<String>();
		headerRow.add("HR Policy Name");
		headerRow.add("Employee Name");
		headerRow.add("Employee Mail");
		headerRow.add("Supervisor Name");
		headerRow.add("Supervisor Mail");
		headerRow.add("Leave Name");
		headerRow.add("Start Date");
		headerRow.add("End Date");
		headerRow.add("Total Days");
		headerRow.add("Applied Date");
		headerRow.add("Leave Status");
		headerRow.add("Purpose");
		
		recordToAdd.add(headerRow);
		
		for(int i=0;i<reportData.length();i++){
			List<String> recordRow = new ArrayList<String>();
			recordRow.add(reportData.getJSONObject(i).getString("policygroup"));
			recordRow.add(reportData.getJSONObject(i).getString("fullname"));
			recordRow.add(reportData.getJSONObject(i).getString("email"));
			recordRow.add(reportData.getJSONObject(i).getString("rmname"));
			recordRow.add(reportData.getJSONObject(i).getString("rmemail"));
			recordRow.add(reportData.getJSONObject(i).getString("leavename"));
			recordRow.add(reportData.getJSONObject(i).getString("startdate"));
			recordRow.add(reportData.getJSONObject(i).getString("enddate"));
			recordRow.add(reportData.getJSONObject(i).getString("totaldays"));
			recordRow.add(reportData.getJSONObject(i).getString("applydate"));
			recordRow.add(reportData.getJSONObject(i).getString("status"));
			recordRow.add(reportData.getJSONObject(i).getString("purpose"));
			recordToAdd.add(recordRow);
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet firstSheet = workbook.createSheet("Sheet1");
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFCellStyle cellStyleAll = workbook.createCellStyle();
        XSSFFont font= workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);
        cellStyleAll.setWrapText(true);
        int rownum = 0;
		for (int j = 0; j < recordToAdd.size(); j++) {
			Row row = firstSheet.createRow(rownum);
			List<String> rowData= recordToAdd.get(j);
			for(int k=0; k<rowData.size(); k++)
			{
				Cell cell = row.createCell(k);
				cell.setCellValue(rowData.get(k));
				cell.setCellStyle(cellStyleAll);
				if(j == 0){
					cell.setCellStyle(cellStyle);
				}
			}
			firstSheet.autoSizeColumn(j);
			rownum++;
		}
		
		String tempString =  new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
		 tempString = "LeaveReport" + tempString;
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
	            workbook.write(outputStream);
	            statusFile = true;
	        }
	}catch(Exception e){
		e.printStackTrace();
	}
}
@Override
public JSONArray getUserListBasedOnPolicyGroup(HttpSession session,
		String hrPolicyGroup) {
	String query = "";
	JSONArray jasonUserData = new JSONArray();
	try{
		query = GetQueryAPI.getQuery(LMS44,hrPolicyGroup);
		jasonUserData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
		    @Override
		    public JSONArray extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
		    	JSONArray tempArr = new JSONArray();
					while(rsEffort.next()){
						JSONObject temp = new JSONObject();
						try {
							temp.put("fullname", rsEffort.getString(1));
							temp.put("username", rsEffort.getString(2));
							tempArr.put(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
		        return tempArr;
		    }
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return jasonUserData;
}
@Override
public JSONArray getUserLeaveData(HttpSession session, String username) {
	String query = "";
	JSONArray jasonUserData = new JSONArray();
	try{
		query = GetQueryAPI.getQuery(LMS53,username,username,username,username);
		jasonUserData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
		    @Override
		    public JSONArray extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
		    	JSONArray tempArr = new JSONArray();
					while(rsEffort.next()){
						JSONObject temp = new JSONObject();
						try {
							temp.put("fullname", rsEffort.getString(1));
							if(rsEffort.getString(2) == null)
								temp.put("bandid", "1");
							else
								temp.put("bandid", rsEffort.getString(2));
							temp.put("leavetype", rsEffort.getString(3));
							if(rsEffort.getString(4) == null)
								temp.put("balance", "0");
							else
								temp.put("balance", rsEffort.getString(4));
							temp.put("leaveid", rsEffort.getString(5));
							tempArr.put(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
		        return tempArr;
		    }
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return jasonUserData;
}
@Override
public String updateUserLeaveBalance(HttpSession session, String username,
		JSONArray updatedLeave) {
	try{
		for(int i=0;i<updatedLeave.length();i++){
			JSONObject obj = (JSONObject) updatedLeave.get(i);
			Iterator<String> itr = obj.keys();
			while(itr.hasNext()){
				String leaveCode = (String) itr.next();
				String updatedBalance = (String) obj.get(leaveCode);
				//Modified on march 20 2018 by abhinav.gupta@supraits.com
				jdbcTemplate.update(GetQueryAPI.getQuery(LMS54,username,"1.0",leaveCode,updatedBalance,"2018","Y"
						,session.getAttribute("loggedInUser").toString(),updatedBalance,"Y",session.getAttribute("loggedInUser").toString()));
			}
		}
		return "Data successfully updated for user: "+username;
	}catch(Exception e){
		e.printStackTrace();
		return "Encountered error";
	}
}
@Override
public String sendMeetingRequestToUser(HttpSession session,
		String requestNumber, String meetingSTime, String meetingETime, String subject,
		String location, final String[] toUser,final String[] ccUser,final String[] toMember, String message, String type){
		String query="";
		//System.out.println(meetingSTime+"----"+meetingETime);
	try{
		if(checkMailNotificationTrigger(LEAVE_REQUEST_MEETING)){
			JSONArray userCurrLeave = getPendingLeaveRequestDetails(session,requestNumber);
			Map<String,Object> updatedLeaveSummaryMap = new LinkedHashMap<String,Object>();
			for(int i=0;i<userCurrLeave.length();i++){
				updatedLeaveSummaryMap.put("leaveType"+i,userCurrLeave.get(i));
			}
			final Map<String,Object> updatedLeaveSummary = updatedLeaveSummaryMap;
			/*final String toRMMailId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM129, jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS34, requestNumber), String.class)), String.class);
			final String toUserMailId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM11, jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS34, requestNumber), String.class)), String.class);
			final String finalReqBy = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS34, requestNumber), String.class);*/
			final String finalLeaveReqNum = requestNumber;
			final String subFinal = subject;
			final String finalmessage = message;
			if(type.equalsIgnoreCase("Mail")){
			Thread t = new Thread(){
				public void run(){
					Map<String,Object> mailContent = new HashMap<String,Object>();
					mailContent.put("leaveReqNumber", finalLeaveReqNum);
					mailContent.put("subject", subFinal);
					mailContent.put("message", finalmessage);
					mailContent.put("leaveList", updatedLeaveSummary);
					doSendTemplateEmail(toUser,ccUser,subFinal,mailContent,"meetingRequestforLeave.vm");
				}
			};
			t.start();
			}else if(type.equalsIgnoreCase("Meeting")){
				final String finalLoc = location;
				final String meetingSTimeFinal = meetingSTime;
				final String meetingETimeFinal = meetingETime;
				final HttpSession session1 = session;
				Thread t = new Thread(){
					public void run(){
						try{
							Map<String,Object> mailContent = new HashMap<String,Object>();
							mailContent.put("leaveReqNumber", finalLeaveReqNum);
							mailContent.put("subject", subFinal);
							mailContent.put("message", finalmessage);
							mailContent.put("leaveList", updatedLeaveSummary);
							sendLeaveMeetingRequest(session1,toMember,subFinal,finalLoc,finalmessage,meetingSTimeFinal,meetingETimeFinal,mailContent,"leaveMeetingVM.vm");
						}catch(Exception e){
							System.out.println("Meeting request has not been sent.");
						}	
					}
				};
				t.start();
			}
			query = GetQueryAPI.getQuery(LMS55, requestNumber,type.toUpperCase(),String.valueOf(Arrays.asList(toMember)),String.valueOf(Arrays.asList(toUser)),String.valueOf(Arrays.asList(ccUser)),location,meetingSTime,meetingETime,message,subject,session.getAttribute("loggedInUserProxy").toString().equalsIgnoreCase("")?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString());
			jdbcTemplate.update(query);
		}
		return "Meeting/Mail request sent successfully";
	}catch(Exception e){
		e.printStackTrace();
		return "One or more invalid parameter.";
	}
}
@Override
public JSONArray getUsersMeetingHistory(HttpSession session, String reqNum) {
	String query = "";
	JSONArray jasonUserData = new JSONArray();
	try{
		query = GetQueryAPI.getQuery(LMS56,reqNum);
		jasonUserData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
		    @Override
		    public JSONArray extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
		    	JSONArray tempArr = new JSONArray();
					while(rsEffort.next()){
						JSONObject temp = new JSONObject();
						try {
							temp.put("subject", rsEffort.getString(1));
							temp.put("location", rsEffort.getString(2));
							temp.put("requestedby", rsEffort.getString(3));
							temp.put("meetingstarttime", rsEffort.getString(4));
							temp.put("meetingendtime", rsEffort.getString(5));
							temp.put("time", rsEffort.getString(6));
							temp.put("participents", rsEffort.getString(7));
							temp.put("touser", rsEffort.getString(8));
							temp.put("ccuser", rsEffort.getString(9));
							temp.put("type", rsEffort.getString(10));
							tempArr.put(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
		        return tempArr;
		    }
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return jasonUserData;

}
@Override
public JSONArray getCumulativeLeaveGroupList(HttpSession session,
		String policyGroup) {
	JSONArray jasonUserData = new JSONArray();
	try{
		String query = GetQueryAPI.getQuery(LMS57, policyGroup);
		jasonUserData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
		    @Override
		    public JSONArray extractData(ResultSet rsEffort) throws SQLException,DataAccessException{
		    	JSONArray tempArr = new JSONArray();
					while(rsEffort.next()){
						JSONObject temp = new JSONObject();
						try {
							temp.put("cumulativeGroup", rsEffort.getString(1));
							temp.put("leaveName", rsEffort.getString(2));
							tempArr.put(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
		        return tempArr;
		    }
		});	
	}catch(Exception e){
		e.printStackTrace();
	}
	return jasonUserData;
}
@Override
public JSONArray getUserListForLeave(HttpSession session) {
	JSONArray jsonData = new JSONArray();
	String query = "";
	try{
		if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(CANADA_ADMIN)){
			query = GetQueryAPI.getQuery(LMS58, session.getAttribute("loggedInUserPolicyGroup").toString(),session.getAttribute("loggedInUser").toString());
		}else{
			query = GetQueryAPI.getQuery(LMS59, session.getAttribute("loggedInUser").toString(),session.getAttribute("loggedInUser").toString(),session.getAttribute("loggedInUser").toString());
		}
		jsonData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray jsonDataTemp = new JSONArray();
				while(rs.next()){
					try {
						JSONObject temp = new JSONObject();
						temp.put("username",rs.getString(1));
						temp.put("fullname",rs.getString(2));
						temp.put("email",rs.getString(3));
						jsonDataTemp.put(temp);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				return jsonDataTemp;
			}
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return jsonData;
}
@Override
public Map<String, String> getUserDataByUsername(String uid) {
	Map<String, String> userData = new LinkedHashMap<String,String>();
	try{
		userData = jdbcTemplate.query(GetQueryAPI.getQuery(LMS60, uid), new ResultSetExtractor<Map<String, String>>(){
			@Override
			public Map<String, String> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<String, String> temp = new LinkedHashMap<String,String>();
				while(rs.next()){
					temp.put("policyGroup", rs.getString(1));
					temp.put("baseLocation", rs.getString(2));
					temp.put("department", rs.getString(3));
					temp.put("username", rs.getString(4));
					temp.put("fullname", rs.getString(5));
					temp.put("designation", rs.getString(6));
					temp.put("reportingManager", rs.getString(7));
				}
				return temp;
			}		
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return userData;
}
@Override
public JSONArray getOtherUsersListOnLeave(HttpSession session,
		String requestNumber, String projectId) {
	JSONArray otherLeavesOnSameDay = new JSONArray();
	Map<String,LeaveBean> usrLeaveDates = new LinkedHashMap<String,LeaveBean>();
	try{
		usrLeaveDates = jdbcTemplate.query(GetQueryAPI.getQuery(LMS66, requestNumber), new ResultSetExtractor<Map<String, LeaveBean>>(){

			@Override
			public Map<String, LeaveBean> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<String,LeaveBean> temp = new LinkedHashMap<String,LeaveBean>();
				while(rs.next()){
					try{
						LeaveBean leaveBean = new LeaveBean();
						List<Date> allDays = getDaysBetweenDates(sdf.parse(rs.getString(1)), sdf.parse(rs.getString(2)));
						leaveBean.setLeaveName(rs.getString(3));
						leaveBean.setFullDayFlag(rs.getString(4));
						leaveBean.setUsername(rs.getString(5));
						for(Date d:allDays){
							temp.put(sdf.format(d), leaveBean);
						}
					}catch(Exception e){}
					
				}
				return temp;
			}
		});
		Iterator<String> itr = usrLeaveDates.keySet().iterator();
		while(itr.hasNext()){
			String date = itr.next();
			LeaveBean tempBean = usrLeaveDates.get(date);
			List<String> usrList = new LinkedList<String>();
			if(!projectId.equals("") && projectId != null)
				usrList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(LMS67,tempBean.getUsername(),projectId,session.getAttribute("loggedInUserPolicyGroup").toString(),date), String.class);
			else
				usrList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(LMS70,tempBean.getUsername(),session.getAttribute("loggedInUserPolicyGroup").toString(),date), String.class);
			JSONObject tempObj = new JSONObject();
			tempObj.put("date", date);
			tempObj.put("leavehead", tempBean.getLeaveName());
			tempObj.put("fulldayflag", tempBean.getFullDayFlag());
			tempObj.put("userslist",usrList.size() == 0?"-":usrList.toString());
			otherLeavesOnSameDay.put(tempObj);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return otherLeavesOnSameDay;
}
@Override
public JSONArray getUsersAssignedProject(HttpSession session, String username) {
	JSONArray jsonData = new JSONArray();
	try{
		jsonData = jdbcTemplate.query(GetQueryAPI.getQuery(LMS68, username), new ResultSetExtractor<JSONArray>(){
			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray jsonDataTemp = new JSONArray();
				while(rs.next()){
					try {
						JSONObject temp = new JSONObject();
						temp.put("username",rs.getString(1));
						temp.put("projectId",rs.getString(2));
						temp.put("projectName",rs.getString(3));
						jsonDataTemp.put(temp);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				return jsonDataTemp;
			}
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return jsonData;
}
@Override
public JSONArray fetchUserListForLeaveMeeting(HttpSession session, String searchParam) {
	JSONArray jsonData = new JSONArray();
	String query = "";
	try{
		query = GetQueryAPI.getQuery(LMS69, session.getAttribute("loggedInUserPolicyGroup").toString(),searchParam,searchParam,searchParam,searchParam);
		jsonData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray jsonDataTemp = new JSONArray();
				while(rs.next()){
					try {
						JSONObject temp = new JSONObject();
						temp.put("usermail",rs.getString(1));
						temp.put("username",rs.getString(2));
						temp.put("fullname",rs.getString(3));
						jsonDataTemp.put(temp);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				return jsonDataTemp;
			}
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return jsonData;
}
public void sendLeaveMeetingRequest(HttpSession session,String[] toMember,String subject,String location,String description,String meetingSTime,String meetingETime,Map<String, Object> model,String templateName) throws Exception {
    try {
        String from = "timesheet@supraits.in";
        String messageBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName,"UTF-8",model);
        MimeMessage message = mailSender.createMimeMessage();
        message.addHeaderLine("method=REQUEST");
        message.addHeaderLine("charset=UTF-8");
        message.addHeaderLine("component=VEVENT");
        message.setFrom(new InternetAddress(from));
        List<String> toAddresses = new ArrayList<String>(Arrays.asList(toMember));
        for (String address : toAddresses) {
        	message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(address));
        }
        message.setSubject(subject);
        final String inputDateFrmt = "MM/dd/yyyy HH:mm";
        DateFormat format = new SimpleDateFormat(inputDateFrmt);
        //System.out.println(format.getTimeZone());
        format.setTimeZone(TimeZone.getTimeZone("IST"));
        //System.out.println(format.getTimeZone());
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm00");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+5:30"));
        String startTime = dateFormat.format(format.parse(meetingSTime));
        String endTime = dateFormat.format(format.parse(meetingETime));
        //System.out.println(dateFormat.getTimeZone());
        //System.out.println(startTime+"---"+endTime);
        String loggedInUserMail = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM11,session.getAttribute("loggedInUser").toString()),String.class);
        StringBuffer sb = new StringBuffer();
        StringBuffer buffer = sb.append("BEGIN:VCALENDAR\n" +
                "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" +
                "VERSION:2.0\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:"+loggedInUserMail+"\n" +
                "ORGANIZER:MAILTO:"+loggedInUserMail+"\n" +
                "DTSTART:"+startTime+"\n" +
                "DTEND:"+endTime+"\n" +
                "LOCATION:"+location+"\n" +
                "TRANSP:OPAQUE\n" +
                "SEQUENCE:0\n" +
                "UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n" +
                " 000004377FE5C37984842BF9440448399EB02\n" +
                "DTSTAMP:"+startTime+"\n" +
                "CATEGORIES:Meeting Invite\n" +
                "DESCRIPTION:"+description+"\n\n" +
                "X-ALT-DESC;FMTTYPE=text/html:"+messageBody+
                "SUMMARY:Leave Request Meeting\n" +
                "PRIORITY:4\n" +
                "CLASS:PUBLIC\n" +
                "BEGIN:VALARM\n" +
                "TRIGGER:PT1440M\n" +
                "ACTION:DISPLAY\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR");
        Multipart multipart = new MimeMultipart();
        
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
        messageBodyPart.setHeader("Content-ID", "calendar_message");
        messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(buffer.toString(), "text/calendar")));
        multipart.addBodyPart(messageBodyPart);
        
        /*BodyPart messageBodyPart2 = new MimeBodyPart();
        messageBodyPart2.setDataHandler(new DataHandler(messageBody, "text/html"));
        multipart.addBodyPart(messageBodyPart2);*/
        
        //message.setDescription(messageBody, "text/html");
        message.setContent(multipart);
        mailSender.send(message);
    } catch (MessagingException me) {
        me.printStackTrace();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
@Override
public boolean checkAuthorization(HttpSession session, String uid) {
	try{
		if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(CANADA_ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(CANADA_HR))
			return true;
		else{
			int count = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(LMS73,uid,session.getAttribute("loggedInUser").toString()), Integer.class);
			return count==0?false:true;		
		}
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
}
@Override
public JSONArray getUserLeaveBalanceHistory(HttpSession session,
		String username, String leaveCode) {
	JSONArray leaveBalance = new JSONArray();
	Calendar currDate = Calendar.getInstance();
	String currYear = String.valueOf(currDate.get(Calendar.YEAR));
	try{
		leaveBalance = jdbcTemplate.query(GetQueryAPI.getQuery(LMS75,username) ,new ResultSetExtractor<JSONArray>(){
			@Override
			public JSONArray extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				JSONArray temp = new JSONArray();
					while(rs.next()){
						try{
							JSONObject tempObj = new JSONObject();
							tempObj.put("leaveCode", rs.getString(1));
							tempObj.put("balance", rs.getString(2));
							tempObj.put("updatedOn", rs.getString(3));
							tempObj.put("updatedBy", rs.getString(4));
							temp.put(tempObj);
						}catch(Exception e){
							e.printStackTrace();
						}	
					}
				return temp;
			}	
			});
	}catch(Exception e){
		e.printStackTrace();
	}
	return leaveBalance;
}

}