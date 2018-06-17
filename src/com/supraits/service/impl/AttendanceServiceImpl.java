package com.supraits.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.supraits.dao.impl.GetQueryAPI;
import com.supraits.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	@Value("${ADMIN}")
	private String ADMIN;
	@Value("${ADMIN_MAIL}")
	private String ADMIN_MAIL;
	
	@Value("${NEW_MOAF_REQUEST}")
	private String NEW_MOAF_REQUEST;
	@Value("${MOAF_REQUEST_INTIMATION_TO_HR}")
	private String MOAF_REQUEST_INTIMATION_TO_HR;
	@Value("${MOAF_REQUEST_INTIMATION_TO_RM}")
	private String MOAF_REQUEST_INTIMATION_TO_RM;
	@Value("${MOAF_REQUEST_STATUS_UPDATE}")
	private String MOAF_REQUEST_STATUS_UPDATE;
	@Value("${HR}")
	private String HR;
	@Value("${domain}")
	private String domain;
	@Value("${TEST_DELIVERY_MAIL_TO}")
	private String TEST_DELIVERY_MAIL_TO;
	
	@Value("${TM21}")
	private String TM21;
	@Value("${TM11}")
	private String TM11;
	@Value("${TM80}")
	private String TM80;
	@Value("${TM91}")
	private String TM91;
	
	@Value("${ATN1}")
	private String ATN1;

	@Value("${ATN2}")
	private String ATN2;
	
	@Value("${ATN9}")
	private String ATN9;
	
	@Value("${ATN10}")
	private String ATN10;
	
	@Value("${ATN12}")
	private String ATN12;
	@Value("${ATN13}")
	private String ATN13;
	@Value("${ATN14}")
	private String ATN14;
	@Value("${ATN15}")
	private String ATN15;
	@Value("${ATN16}")
	private String ATN16;
	@Value("${ATN17}")
	private String ATN17;
	@Value("${ATN18}")
	private String ATN18;
	@Value("${ATN19}")
	private String ATN19;
	@Value("${ATN20}")
	private String ATN20;
	@Value("${ATN21}")
	private String ATN21;
	@Value("${ATN22}")
	private String ATN22;
	@Value("${ATN23}")
	private String ATN23;
	@Value("${ATN24}")
	private String ATN24;
	@Value("${ATN25}")
	private String ATN25;
	@Value("${ATN26}")
	private String ATN26;
	@Value("${ATN27}")
	private String ATN27;
	@Value("${ATN28}")
	private String ATN28;
	@Value("${ATN29}")
	private String ATN29;
	@Value("${ATN30}")
	private String ATN30;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	String currentDate = sdf2.format(new Date());
	final DecimalFormat decimalFormat = new DecimalFormat("####0.00");
	
	@Override
	public boolean updateAttendanceData(String data) throws JSONException {
		boolean status = false;
		try{
			//System.out.println(data.length()+"data==="+data);
			JSONArray json = new JSONArray(data);
			for(int i=0;i<json.length();i++){
				try{
					JSONObject obj = json.getJSONObject(i);
					String empId = obj.getString("empid");
					String direction = obj.getString("direction");
					String logTime = obj.getString("logtime");
					String C4 = obj.getString("C4");
					String C5 = obj.getString("C5");
					jdbcTemplate.update(GetQueryAPI.getQuery(ATN1,logTime,empId,direction,C4,C5));
				}catch(Exception e){
					//Thread t = Thread.currentThread();
					//t.interrupt();
					e.printStackTrace();
				}	
			}
			status = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public JSONArray fetchUserAttendanceData(HttpSession session,
			String firstDay, String lastDay,String username) {
		firstDay = firstDay + " 00:00:00";
		lastDay = lastDay + " 23:59:59";
		JSONArray jsonData = new JSONArray();
		try{
			List<Date> allDaysBetween = getDaysBetweenDates(sdf2.parse(firstDay),sdf2.parse(lastDay));
			System.out.println(allDaysBetween);
			Map<String,JSONObject> blnkMap = new LinkedHashMap<String,JSONObject>();
			Map<String,String> holidayMap = getHolidayMap(firstDay,lastDay);
			Map<String,String> leaveMap = getUserLeaveMap(firstDay,lastDay,username);
			System.out.println(leaveMap.values());
			for(Date d:allDaysBetween){
				JSONObject obj = new JSONObject();
				String date = sdf2.format(d);
				obj.put("date",date);
				obj.put("shifttime","9:00:00 to 18:00:00");
				obj.put("intime","NA");
				obj.put("outtime", "NA");
				obj.put("punchedcount","0");
				String dayName = new SimpleDateFormat("EEEE").format(d);
				if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
					obj.put("status", "Week Off");	
				else if(holidayMap.containsKey(date))
					obj.put("status", "Holiday");
				else if(leaveMap.containsKey(date)){
					if(leaveMap.get(date).equalsIgnoreCase("Approval Pending"))
						obj.put("status", "Leave Applied");
					else
						obj.put("status", "On Leave");
				}else 
					obj.put("status", "Absent");
				obj.put("extrahours","0");
				obj.put("deficienthours","0");
				blnkMap.put(date, obj);
			}
			Map<String,JSONObject> blnkMapFinal = new LinkedHashMap<String,JSONObject>();
			blnkMapFinal = jdbcTemplate.query(GetQueryAPI.getQuery(ATN2, firstDay,lastDay,"'"+username+"'"), new ResultSetExtractor<Map<String,JSONObject>>(){
				@Override
				public Map<String,JSONObject> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					Map<String,JSONObject> temp = new LinkedHashMap<String,JSONObject>();
					while(rs.next()){
						JSONObject obj = new JSONObject();
						try{
							obj.put("date", rs.getString(1).substring(0,10));
							obj.put("shifttime", rs.getString(3));
							obj.put("intime", rs.getString(1).substring(11));
							if(rs.getString(1).equalsIgnoreCase(rs.getString(2)))
								obj.put("outtime", "NA");
							else
								obj.put("outtime", rs.getString(2).substring(11));
							long diff = 0;
							Date d1 = sdf.parse(rs.getString(1));
					        Date d2 = sdf.parse(rs.getString(2));
					        diff = d2.getTime() - d1.getTime();
							obj.put("punchedcount", String.format("%02d",(diff / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(diff / (60 * 1000) % 60)) +":"+ String.format("%02d",(diff / 1000 % 60)));
							if((d2.after(d1) || d2.equals(d1)) && diff >= 0 ){
								obj.put("status", "Present");
								long extraOrDiffHours = diff - (9*60*60*1000);
								if(extraOrDiffHours >= 0)
									obj.put("extrahours", String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
								else{
									extraOrDiffHours = (9*60*60*1000) - diff;
									obj.put("deficienthours", String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
							    }
							}else{
								String dayName = new SimpleDateFormat("EEEE").format(sdf.parse(rs.getString(1)));
								if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
									obj.put("status", "Week Off");	
								else
									obj.put("status", "Absent");
								obj.put("deficienthours","00:00:00");
							}
							temp.put(rs.getString(1).substring(0,10), obj);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					return temp;
				}				
			});
			Iterator<String> itr = blnkMapFinal.keySet().iterator(); 
			while(itr.hasNext()){
				String tempD = itr.next();
				blnkMap.put(tempD,blnkMapFinal.get(tempD));
			}	
			Iterator<String> itr2 = blnkMap.keySet().iterator();
			while(itr2.hasNext())
				jsonData.put(blnkMap.get(itr2.next()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
	}

	@Override
	public JSONArray fetchUsersAttendanceData(HttpSession session,
			String firstDay, String lastDay, String projectId,String username) {
		
		firstDay = firstDay + " 00:00:01";
		lastDay = lastDay + " 23:59:59";
		
		System.out.println(firstDay + "\n"+lastDay);
		JSONArray jsonData = new JSONArray();

		try{
			String query = "";
			if(!(username.equalsIgnoreCase("select"))){
				jsonData = fetchOneUserAttendanceData(session,firstDay,lastDay,username);
				return jsonData;
				//query = GetQueryAPI.getQuery(ATN2, firstDay,lastDay,"'"+username+"'");
			}else{
				if(projectId.equalsIgnoreCase("select")){
					List<String> userList = new LinkedList<String>();
					if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR)){
						 userList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(ATN9), String.class);
					}else{
						userList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(ATN28,session.getAttribute("loggedInUser").toString()), String.class);
					}
					String userListString = "";
					for(String s:userList)
						userListString = userListString + "'"+s+"',";
					query = GetQueryAPI.getQuery(ATN2, firstDay,lastDay,userListString.substring(0, userListString.length() - 1));
				}else{
					List<String> userList = new LinkedList<String>();
					if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR)){
						userList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(ATN10,projectId), String.class);
					}else{
						userList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(ATN29,session.getAttribute("loggedInUser").toString(),projectId), String.class);
					}
					String userListString = "";
					for(String s:userList)
						userListString = userListString + "'"+s+"',";
					query = GetQueryAPI.getQuery(ATN2, firstDay,lastDay,userListString.substring(0, userListString.length() - 1));
				}
			}
			System.out.println(query);
			jsonData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray jsonarr = new JSONArray();
					while(rs.next()){
						JSONObject obj = new JSONObject();
						try{
							obj.put("date", rs.getString(1).substring(0,10));
							obj.put("shifttime", rs.getString(3));
							obj.put("intime", rs.getString(1).substring(11));
							if(rs.getString(1).equalsIgnoreCase(rs.getString(2)))
								obj.put("outtime", "NA");
							else
								obj.put("outtime", rs.getString(2).substring(11));
							long diff = 0;
							Date d1 = sdf.parse(rs.getString(1));
					        Date d2 = sdf.parse(rs.getString(2));
					        diff = d2.getTime() - d1.getTime();
					        //System.out.println(d1.getTime()+"d1111==="+rs.getString(1));
							obj.put("punchedcount", String.format("%02d",(diff / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(diff / (60 * 1000) % 60)) +":"+ String.format("%02d",(diff / 1000 % 60)));
							if((d2.after(d1) || d2.equals(d1)) && diff >= 0 ){
								obj.put("status", "Present");
								long extraOrDiffHours = diff - (9*60*60*1000);
								if(extraOrDiffHours >= 0)
									obj.put("extrahours", String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
								else{
									extraOrDiffHours = (9*60*60*1000) - diff;
									obj.put("deficienthours", String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
							    }
							}else{
								String dayName = new SimpleDateFormat("EEEE").format(sdf.parse(rs.getString(1)));
								if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
									obj.put("status", "Week Off");	
								else
									obj.put("status", "Absent");
								obj.put("deficienthours","00:00:00");
							}
							obj.put("username", rs.getString(6));
							obj.put("fullname", rs.getString(7));
							/*obj.put("projectname", rs.getString(8));*/
						}catch(Exception e){
							e.printStackTrace();
						}
						jsonarr.put(obj);
					}
					return jsonarr;
				}				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
	}

	private JSONArray fetchOneUserAttendanceData(HttpSession session,
			String firstDay, String lastDay, String username) {
		JSONArray jsonData = new JSONArray();
		try{
			JSONArray temp = fetchUserAttendanceData(session,firstDay,lastDay,username);
			for(int i=0;i<temp.length();i++){
				JSONObject tempObj = temp.getJSONObject(i);
				tempObj.put("username", username);
				tempObj.put("fullname", username);
				tempObj.put("shifttime", "9:00 :00 to 18:00:00");
				jsonData.put(tempObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
	}

	@Override
	public JSONArray fetchUsersAttendanceDataBasedOnParam(HttpSession session,
			String startDay, String endDay, String projectId, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray fetchUsersWeeklyAttendanceData(HttpSession session,
			String firstDay, String lastDay, String username) {
		JSONArray jsonData = new JSONArray();
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			String query = "";
			if(!(username.equalsIgnoreCase("select"))){
				if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR))	
					query = GetQueryAPI.getQuery(ATN12, firstDay,lastDay,"'"+username+"'");
				else
					query = GetQueryAPI.getQuery(ATN12, firstDay,lastDay,"'"+session.getAttribute("loggedInUser").toString()+"'");
			}else{
				List<String> userList = new LinkedList<String>();
				if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR))
					userList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(ATN9), String.class);
				else{
					userList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(ATN28,session.getAttribute("loggedInUser").toString()), String.class);
					userList.add(session.getAttribute("loggedInUser").toString());
				}
				String userListString = "";
				for(String s:userList)
					userListString = userListString + "'"+s+"',";
				query = GetQueryAPI.getQuery(ATN12, firstDay,lastDay,userListString.substring(0, userListString.length() - 1));
			}
			System.out.println(query);
			jsonData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray jsonarr = new JSONArray();
					while(rs.next()){
						JSONObject obj = new JSONObject();
						try{
							 Calendar cal = Calendar.getInstance();
							 cal.setTime(format.parse(rs.getString(1)));
							 
							 Calendar first = (Calendar) cal.clone();
							 first.add(Calendar.DAY_OF_WEEK,first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
							 
							 Calendar last = (Calendar) first.clone();
							 last.add(Calendar.DAY_OF_YEAR, 6);
							
							obj.put("weekinterval", format.format(first.getTime())+"-"+format.format(last.getTime()));
							obj.put("punchedhours", decimalFormat.format(Double.parseDouble(rs.getString(2))/3600));
							obj.put("fullname",rs.getString(3));
							obj.put("username",rs.getString(4));
							double workingHHinweek = calculateWorkingHoursInWeek(rs.getString(1)); 
							obj.put("workinghours",String.valueOf(workingHHinweek));
							obj.put("totaldiff",String.valueOf(decimalFormat.format((Double.parseDouble(rs.getString(2))/3600)- workingHHinweek)));
							
						}catch(Exception e){
							e.printStackTrace();
						}
						jsonarr.put(obj);
					}
					return jsonarr;
				}

				private double calculateWorkingHoursInWeek(String weekInterval) {
					return 45.0;
				}				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
	}

	@Override
	public String sendMOAFForApproval(HttpSession session, String date,String direction,
			String inTime, String outTime, String reason,String moafCategory) {
		String query = "";
		String status = "Please try after some time.";
		String logInTime = "";
		String logOutTime = "";
		Map<String,Object> mailContent = new HashMap<String,Object>();
		mailContent.put("requestedBy", session.getAttribute("loggedInUser").toString());
		mailContent.put("requestedByFullName", getEmpName(session.getAttribute("loggedInUser").toString()));
		mailContent.put("direction", direction);
		mailContent.put("date", date);
		mailContent.put("reason", reason);
		mailContent.put("category", moafCategory);
		if(!(inTime.equals("")) && inTime != null){
			String inHour = String.format("%02d", Integer.parseInt(inTime.substring(0,inTime.indexOf(":"))));
			String inMinutes = String.format("%02d",  Integer.parseInt(inTime.substring(inTime.indexOf(":")+1)));
			logInTime = date + " " + inHour + ":" + inMinutes + ":00";
			mailContent.put("inTime", inHour + ":" + inMinutes + ":00");
		}else{
			logInTime = null;
			mailContent.put("inTime", "-");
		}
		if(!(outTime.equals("")) && outTime != null){
			String outHour = String.format("%02d", Integer.parseInt(outTime.substring(0,outTime.indexOf(":"))));
			String outMinutes = String.format("%02d",  Integer.parseInt(outTime.substring(outTime.indexOf(":")+1)));
			logOutTime = date + " " + outHour + ":" + outMinutes + ":00";
			mailContent.put("outTime", outHour + ":" + outMinutes + ":00");
		}else{
			logOutTime = null;
			mailContent.put("outTime", "-");
		}
		try{
			try{
			String existingMOAFCheck = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(ATN14,date,String.valueOf(session.getAttribute("loggedInUser"))),String.class);
			if(existingMOAFCheck.equalsIgnoreCase("Pending")){
				return "MOAF has been already applied for date"+existingMOAFCheck;
			}
			}catch(Exception e){}
			if(direction.equalsIgnoreCase("Both")){
				query = GetQueryAPI.getQuery(ATN13,moafCategory,date,direction,logInTime,logOutTime,String.valueOf(session.getAttribute("loggedInUser")),
						(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),
								(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),currentDate,reason);
			}else if(logInTime == null){
				query = GetQueryAPI.getQuery(ATN15,moafCategory,date,direction,logOutTime,String.valueOf(session.getAttribute("loggedInUser")),
						(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),
								(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),currentDate,reason);
			}else{
				query = GetQueryAPI.getQuery(ATN16,moafCategory,date,direction,logInTime,String.valueOf(session.getAttribute("loggedInUser")),
						(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),
								(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),currentDate,reason);
			}
			int updateCount = jdbcTemplate.update(query);
			if(updateCount != 0){
				final Map<String,Object> mailContentFinal = mailContent;
				final String hrMail = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM91,session.getAttribute("loggedInUser").toString()), String.class);
				final String userMail = getUserMailId(session.getAttribute("loggedInUser").toString());
				final String rmMail = getUserMailId(session.getAttribute("reportingManagerUserName").toString());
				Thread t = new Thread(){
					public void run(){
						if(checkMailNotificationTrigger(NEW_MOAF_REQUEST))
							doSendTemplateEmail(userMail, "New MOAF Request initiated.", mailContentFinal,"newMOAFRequest.vm");
						if(checkMailNotificationTrigger(MOAF_REQUEST_INTIMATION_TO_HR))
							doSendTemplateEmail(hrMail, "MOAF request pending for approval.", mailContentFinal,"moafRequestIntimationToHR.vm");
						if(checkMailNotificationTrigger(MOAF_REQUEST_INTIMATION_TO_RM))
							doSendTemplateEmail(rmMail, "MOAF request pending for approval.", mailContentFinal,"moafRequestIntimationToHR.vm");
					}
				};
				t.start();
				status = "MOAF has been sent for approval";
			}	
		}catch(Exception e){
			e.printStackTrace();
			status = "Invalid Input";
		}
		return status;
	}

	@Override
	public JSONArray fetchUserAttendanceMOAFData(HttpSession session) {
		JSONArray jsonarr = new JSONArray();
		try{
			jsonarr = jdbcTemplate.query(GetQueryAPI.getQuery(ATN17,String.valueOf(session.getAttribute("loggedInUser"))), new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray jsonarrTemp = new JSONArray();
					try{
						while(rs.next()){
							JSONObject temp = new JSONObject();
							
							temp.put("fullname",rs.getString(1));
							temp.put("username",rs.getString(2));
							temp.put("date",rs.getString(3));
							temp.put("direction",rs.getString(4));
							temp.put("intime",(rs.getString(5) == null)?"-":rs.getString(5));
							temp.put("outtime",(rs.getString(6) == null)?"-":rs.getString(6));
							temp.put("reason",rs.getString(7));
							temp.put("createdate",rs.getString(8));
							temp.put("status",rs.getString(9));
							temp.put("category",rs.getString(10));
							
							jsonarrTemp.put(temp);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					return jsonarrTemp;
				}
				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr;
	}

	@Override
	public JSONArray fetchUserPendingRequest(HttpSession session) {
		JSONArray jsonarr = new JSONArray();
		String query = "";
		try{
			if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR)){
				query = GetQueryAPI.getQuery(ATN18,String.valueOf(session.getAttribute("loggedInUser")));
			}else{
				query = GetQueryAPI.getQuery(ATN24,String.valueOf(session.getAttribute("loggedInUser")),String.valueOf(session.getAttribute("loggedInUser")));
			}
			jsonarr = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray jsonarrTemp = new JSONArray();
					try{
						while(rs.next()){
							JSONObject temp = new JSONObject();
							
							temp.put("fullname",rs.getString(1));
							temp.put("username",rs.getString(2));
							temp.put("date",rs.getString(3));
							temp.put("direction",rs.getString(4));
							temp.put("intime",(rs.getString(5) == null)?"-":rs.getString(5));
							temp.put("outtime",(rs.getString(6) == null)?"-":rs.getString(6));
							temp.put("reason",rs.getString(7));
							temp.put("createdate",rs.getString(8));
							temp.put("status",rs.getString(9));
							temp.put("moafid",rs.getString(10));
							temp.put("category",rs.getString(11));
							jsonarrTemp.put(temp);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					return jsonarrTemp;
				}
				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonarr;
	}

	@Override
	public String validatePendingMOAFRequest(HttpSession session,
			String moafid, String approveFlag) {
		String status = "Try after some time";
		try{
			if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR)){
				int updateCount = 0;
				if(approveFlag.equalsIgnoreCase("true")){
					updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(ATN19,"Approved",(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),moafid));
					if(updateCount != 0){
						boolean status1 = updateAttendanceLogtable(moafid);
						Map<String,Object> mailContent = getMOAFDetails(moafid);
						if(status1){
							try {
								final Map<String,Object> mailContentFinal = mailContent;
								Thread t = new Thread(){
									public void run(){
									if(checkMailNotificationTrigger(MOAF_REQUEST_STATUS_UPDATE))
										doSendTemplateEmail(String.valueOf(mailContentFinal.get("usermail")), "MOAF Request has been approved.", mailContentFinal,"statusUpdateMOAF.vm");
									}
								};
								t.start();
							} catch (Exception e) {}
							return "MOAF has been approved successfully";
						}	
					}
				}
				if(approveFlag.equalsIgnoreCase("false")){
					updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(ATN19,"Rejected",(session.getAttribute("loggedInUserProxy") == null || session.getAttribute("loggedInUserProxy").toString().equals(""))?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),moafid));
					if(updateCount != 0){
						try {
							final Map<String,Object> mailContentFinal = getMOAFDetails(moafid);;
							Thread t = new Thread(){
								public void run(){
									if(checkMailNotificationTrigger(MOAF_REQUEST_STATUS_UPDATE))
										doSendTemplateEmail(String.valueOf(mailContentFinal.get("usermail")), "MOAF Request has been rejected.", mailContentFinal,"statusUpdateMOAF.vm");
								}
							};
							t.start();
						} catch (Exception e) {}
						return "MOAF has been rejected";
					}	
				}
			}else{
				return "Unauthorized Opertaion.Connect with HR.";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "Unauthorized Opertaion.Connect with HR.";
		}
		return "Unauthorized Opertaion.Connect with HR.";
	}

	private Map<String, Object> getMOAFDetails(String moafid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map = jdbcTemplate.query(GetQueryAPI.getQuery(ATN26, moafid),new ResultSetExtractor<Map<String, Object>>(){

				@Override
				public Map<String, Object> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					Map<String, Object> map = new HashMap<String, Object>();
					while(rs.next()){
						map.put("usermail", rs.getObject(1));
						map.put("userfullname", rs.getObject(2));
						map.put("date", rs.getObject(3));
						map.put("direction", rs.getObject(4));
						map.put("intime", (rs.getObject(5) == null)?"-":rs.getObject(5));
						map.put("outtime", (rs.getObject(6) == null)?"-":rs.getObject(6));
						map.put("submittedon", rs.getObject(7));
						map.put("status", rs.getObject(8));
						map.put("updatedby", rs.getObject(9));
						map.put("reason", rs.getObject(10));
					}
					return map;
				}
				
			});
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	private boolean updateAttendanceLogtable(String moafid) {
		boolean status = false;
		List<String> attnData = new ArrayList<String>();
		try{
			attnData = jdbcTemplate.query(GetQueryAPI.getQuery(ATN21,moafid), new ResultSetExtractor<List<String>>(){
				@Override
				public List<String> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					List<String> temp = new ArrayList<String>();
					while(rs.next()){
						String direction = rs.getString(1);
						temp.add(rs.getString(1));
						if(direction.equalsIgnoreCase("Both")){
							temp.add(rs.getString(2).substring(0,(rs.getString(2).length()- 2)));
							temp.add(rs.getString(3).substring(0,(rs.getString(2).length()- 2)));
							temp.add(rs.getString(4));
						}
						if(direction.equalsIgnoreCase("In")){
							temp.add(rs.getString(2).substring(0,(rs.getString(2).length()- 2)));
							temp.add(rs.getString(4));
						}
						if(direction.equalsIgnoreCase("Out")){
							temp.add(rs.getString(3).substring(0,(rs.getString(2).length()- 2)));
							temp.add(rs.getString(4));
						}
					}
					return temp;
				}
			});
			if(attnData.size() > 0){
				int updateCount = 0;
				if(attnData.get(0).equalsIgnoreCase("Both")){
					updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(ATN20, "in", attnData.get(1), attnData.get(3)));
					updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(ATN20, "out", attnData.get(2), attnData.get(3)));
				}else if(attnData.get(0).equalsIgnoreCase("In")){
					updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(ATN20, "in", attnData.get(1), attnData.get(2)));
				}else{
					updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(ATN20, "out", attnData.get(1), attnData.get(2)));
				}
				if(updateCount != 0)
					status = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	public List<Date> getDaysBetweenDates(Date startdate, Date enddate)
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
	public Map<String,String> getUserLeaveMap(String startDate,String endDate,String username){
		Map<String,String> leaveMap = new LinkedHashMap<String,String>();
		try{
			//System.out.println(GetQueryAPI.getQuery(ATN23,startDate,endDate,username));
			leaveMap = jdbcTemplate.query(GetQueryAPI.getQuery(ATN23,startDate,endDate,username),new ResultSetExtractor<Map<String,String>>(){
				@Override
				public Map<String,String> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					Map<String,String> temp = new LinkedHashMap<String,String>();
					try{
					while(rs.next()){
						List<Date> allDays = getDaysBetweenDates(sdf2.parse(rs.getString(1)), sdf2.parse(rs.getString(2)));
						System.out.println(allDays.toString());
						for(Date d:allDays){
							temp.put(sdf2.format(d),rs.getString(3));
						}
					}
					}catch(Exception e){
						System.out.println("Exception occured while fetching users leave");
					}
					return temp;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}	
	return leaveMap;		
}
	public Map<String,String> getHolidayMap(String startDate,String endDate){
		Map<String,String> holiMap = new LinkedHashMap<String,String>();
			try{
				System.out.println(GetQueryAPI.getQuery(ATN22,startDate,endDate));
				holiMap = jdbcTemplate.query(GetQueryAPI.getQuery(ATN22,startDate,endDate),new ResultSetExtractor<Map<String,String>>(){

					@Override
					public Map<String,String> extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						Map<String,String> temp = new LinkedHashMap<String,String>();
						while(rs.next())
							temp.put(rs.getString(1), rs.getString(2));
						return temp;
					}
				});
			}catch(Exception e){
				e.printStackTrace();
			}	
		return holiMap;		
	}

	@Override
	public JSONArray generateUserYearlyAttnData(HttpSession session,
			String uid, String year) {
		String firstDay = year+"-01-01 00:00:01";
		String lastDay = year+"-12-31 23:59:59";
		JSONArray jsonData = new JSONArray();
		try{
			List<Date> allDaysBetween = getDaysBetweenDates(sdf2.parse(firstDay),sdf2.parse(lastDay));
			System.out.println(allDaysBetween);
			Map<String,JSONObject> blnkMap = new LinkedHashMap<String,JSONObject>();
			Map<String,String> holidayMap = getHolidayMap(firstDay,lastDay);
			Map<String,String> leaveMap = getUserLeaveMap(firstDay,lastDay,String.valueOf(session.getAttribute("loggedInUser")));
			System.out.println(leaveMap.values());
			for(Date d:allDaysBetween){
				JSONObject obj = new JSONObject();
				String date = sdf2.format(d);
				obj.put("date",date);
				obj.put("startDate",date);
				obj.put("endDate",date);
				obj.put("shifttime","9:00:00 to 18:00:00");
				obj.put("intime","NA");
				obj.put("outtime", "NA");
				obj.put("punchedcount","0");
				String dayName = new SimpleDateFormat("EEEE").format(d);
				if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
					obj.put("status", "Week Off");	
				else if(holidayMap.containsKey(date))
					obj.put("status", "Holiday");
				else if(leaveMap.containsKey(date)){
					if(leaveMap.get(date).equalsIgnoreCase("Approval Pending"))
						obj.put("status", "Leave Applied");
					else
						obj.put("status", "On Leave");
				}else 
					obj.put("status", "Absent");
				obj.put("extrahours","0");
				obj.put("deficienthours","0");
				blnkMap.put(date, obj);
			}
			Map<String,JSONObject> blnkMapFinal = new LinkedHashMap<String,JSONObject>();
			blnkMapFinal = jdbcTemplate.query(GetQueryAPI.getQuery(ATN2, firstDay,lastDay,"'"+uid+"'"), new ResultSetExtractor<Map<String,JSONObject>>(){
				@Override
				public Map<String,JSONObject> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					Map<String,JSONObject> temp = new LinkedHashMap<String,JSONObject>();
					while(rs.next()){
						JSONObject obj = new JSONObject();
						try{
							obj.put("date", rs.getString(1).substring(0,10));
							obj.put("startDate", rs.getString(1).substring(0,10));
							obj.put("endDate", rs.getString(1).substring(0,10));
							obj.put("shifttime", rs.getString(3));
							obj.put("intime", rs.getString(1).substring(11));
							if(rs.getString(1).equalsIgnoreCase(rs.getString(2)))
								obj.put("outtime", "NA");
							else
								obj.put("outtime", rs.getString(2).substring(11));
							long diff = 0;
							Date d1 = sdf.parse(rs.getString(1));
					        Date d2 = sdf.parse(rs.getString(2));
					        diff = d2.getTime() - d1.getTime();
							obj.put("punchedcount", String.format("%02d",(diff / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(diff / (60 * 1000) % 60)) +":"+ String.format("%02d",(diff / 1000 % 60)));
							if((d2.after(d1) || d2.equals(d1)) && diff >= 0 ){
								obj.put("status", "Present");
								long extraOrDiffHours = diff - (9*60*60*1000);
								if(extraOrDiffHours >= 0)
									obj.put("extrahours", String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
								else{
									extraOrDiffHours = (9*60*60*1000) - diff;
									obj.put("deficienthours", String.format("%02d",(extraOrDiffHours / (60 * 60 * 1000) % 24))+":"+ String.format("%02d",(extraOrDiffHours / (60 * 1000) % 60)) +":"+ String.format("%02d",(extraOrDiffHours / 1000 % 60)));
							    }
							}else{
								String dayName = new SimpleDateFormat("EEEE").format(sdf.parse(rs.getString(1)));
								if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday"))
									obj.put("status", "Week Off");	
								else
									obj.put("status", "Absent");
								obj.put("deficienthours","00:00:00");
							}
							temp.put(rs.getString(1).substring(0,10), obj);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					return temp;
				}				
			});
			Iterator<String> itr = blnkMapFinal.keySet().iterator(); 
			while(itr.hasNext()){
				String tempD = itr.next();
				blnkMap.put(tempD,blnkMapFinal.get(tempD));
			}	
			Iterator<String> itr2 = blnkMap.keySet().iterator();
			while(itr2.hasNext())
				jsonData.put(blnkMap.get(itr2.next()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonData;
	}
	@Override
	public String getEmpName(String tempUser){
		String empName = "";
		String query = "";
		try{
			query = GetQueryAPI.getQuery(TM21, tempUser);
			empName =  jdbcTemplate.queryForObject(query, String.class);
		}catch(Exception e){
			e.printStackTrace();
			return "NA";
		}
		return empName;
	}
	@Override
	public String getEmpRMName(String tempUser){
		String empName = "";
		String query = "";
		try{
			query = GetQueryAPI.getQuery(ATN25, tempUser);
			empName =  jdbcTemplate.queryForObject(query, String.class);
		}catch(Exception e){
			e.printStackTrace();
			return "NA";
		}
		return empName;
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
	private String doSendTemplateEmail(String recipientAddress,String subject,Map<String, Object> model,String templateName) {
		//SimpleMailMessage email = new SimpleMailMessage();
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

	@Override
	public JSONArray fetchUsersExceptionAttendanceData(HttpSession session,
			String firstDay, String lastDay, String username) {
		JSONArray jsonData = new JSONArray();
		JSONArray jsonDataFinal = new JSONArray();
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			String query = "";
			if(!(username.equalsIgnoreCase("select"))){
				query = GetQueryAPI.getQuery(ATN27, firstDay,lastDay,"'"+username+"'");
			}else{
				List<String> userList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(ATN9), String.class);
				String userListString = "";
				for(String s:userList)
					userListString = userListString + "'"+s+"',";
				query = GetQueryAPI.getQuery(ATN27, firstDay,lastDay,userListString.substring(0, userListString.length() - 1));
			}
			System.out.println(query);
			jsonData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){
				@Override
				public JSONArray extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					JSONArray jsonarr = new JSONArray();
					while(rs.next()){
						JSONObject obj = new JSONObject();
						try{
							obj.put("punchedhours", decimalFormat.format(Double.parseDouble(rs.getString(1))/3600));
							obj.put("fullname",rs.getString(2));
							obj.put("username",rs.getString(3));
						}catch(Exception e){
							e.printStackTrace();
						}
						jsonarr.put(obj);
					}
					return jsonarr;
				}
			});
			List<Date> allDays = getDaysBetweenDates(sdf2.parse(firstDay), sdf2.parse(lastDay));
			Map<String,String> holidayMap =  getHolidayMap(firstDay,lastDay);
			for(int i=0;i<jsonData.length();i++){
				JSONObject obj1 = (JSONObject) jsonData.get(i);
				JSONObject obj = new JSONObject();
				Map<String,String> leaveMap =  getUserLeaveMap(firstDay,lastDay,obj1.getString("username"));
				
				int totalHH = allDays.size() * 9;
				int totalWHH = allDays.size() * 9;
				int totalHoliday = 0;
				int totalLeave = 0;
				
				for(Date d:allDays){
					String dayName = new SimpleDateFormat("EEEE").format(d);
					if(dayName.equalsIgnoreCase("Sunday") || dayName.equalsIgnoreCase("Saturday")){
						totalHH = totalHH - 9 ;	
						totalWHH = totalWHH - 9 ;	
					}else if(holidayMap.containsKey(sdf2.format(d))){
						totalHH = totalHH - 9 ;
						totalHoliday = totalHoliday + 1;
						totalWHH = totalWHH - 9 ;
					}else if(leaveMap.containsKey(sdf2.format(d))){
						totalLeave = totalLeave + 1;
						totalWHH = totalWHH - 9 ;
					}
			    }
				obj.put("punchedhours",obj1.getString("punchedhours"));
				obj.put("fullname",obj1.getString("fullname"));
				obj.put("username",obj1.getString("username"));
				obj.put("onLeave", String.valueOf(totalLeave));
				obj.put("holiday", String.valueOf(totalHoliday));
				obj.put("totalWorkingHH", String.valueOf(totalHH));
				obj.put("desiredWorkingHH", String.valueOf(totalWHH));
				obj.put("totaldiff",String.valueOf(decimalFormat.format((Double.parseDouble(obj1.getString("punchedhours")))- (Double.parseDouble(String.valueOf(totalWHH))))));
				jsonDataFinal.put(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(jsonDataFinal);
		return jsonDataFinal;
	}
	@Override
	public boolean checkUserAuthorization(HttpSession session,String username){
		try{
			if(username.equalsIgnoreCase(String.valueOf(session.getAttribute("loggedInUser"))))
				return true;
			if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR))
				return true;
			else if(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(ATN30, username,String.valueOf(session.getAttribute("loggedInUser")),String.valueOf(session.getAttribute("loggedInUser"))), Integer.class) != 0)
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
