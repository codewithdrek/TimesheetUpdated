package com.supraits.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.itextpdf.text.Image;
import com.supra.imanager.bean.DashboardAnnouncement;
import com.supra.imanager.bean.DashboardDataFromDB;
import com.supra.imanager.bean.DashboardQuickLink;
import com.supra.imanager.bean.LoginResult;
import com.supra.imanager.bean.MenuDataFromDB;
import com.supra.imanager.bean.ProjectListDetails;
import com.supraits.dao.impl.GetQueryAPI;
import com.supraits.viewBean.EmployeeAccountBean;
import com.supraits.viewBean.EmployeeBean;
import com.supraits.viewBean.EmployeeEducationBean;
import com.supraits.viewBean.EmployeeSkillBean;
import com.supraits.viewBean.EmployeeWorkBean;
import com.supraits.viewBean.LoginBean;
import com.supraits.viewBean.ProjectBean;
import com.supraits.viewBean.TaskBean;
import com.supraits.viewBean.TaskMappingBean;

@Service
public class TimesheetServiceImpl {

	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String currentDate = sdf.format(date);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	EmployeeBean tempEmployeeBean;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	/*
	 * @Autowired EmployeeWorkBean tempEmployeeWorkBean;
	 * 
	 * @Autowired EmployeeEducationBean tempEmployeeEducationBean;
	 * 
	 * @Autowired EmployeeAccountBean tempEmployeeAccountBean;
	 * 
	 * @Autowired EmployeeSkillBean tempEmployeeSkillBean;
	 */

	@Autowired
	private VelocityEngine velocityEngine;

	Connection dbConnection;

	@Value("${contextpath}")
	private String contextpath;
	@Value("${domain}")
	private String domain;
	@Value("${serverport}")
	private String serverport;
	@Value("${protocol}")
	private String protocol;
	@Value("${IGdateformat}")
	private String IGdateformat;
	@Value("${ORGANIZATION_PROJECT}")
	private String ORGANIZATION_PROJECT;
	@Value("${ADMIN_USERNAME}")
	private String ADMIN_USERNAME;
	@Value("${ADMIN_MAIL}")
	private String ADMIN_MAIL;
	@Value("${USER}")
	private String USER;
	@Value("${ADMIN}")
	private String ADMIN;
	@Value("${HR}")
	private String HR;
	@Value("${CANADA_ADMIN}")
	private String CANADA_ADMIN;
	@Value("${CANADA_HR}")
	private String CANADA_HR;
	@Value("${FINANACE}")
	private String FINANACE;
	@Value("${VP_GROUP}")
	private String VP_GROUP;
	@Value("${TEST_DELIVERY_MAIL_TO}")
	private String TEST_DELIVERY_MAIL_TO;

	@Value("${Leave_Rejected}")
	private String Leave_Rejected;

	@Value("${New_Registration}")
	private String New_Registration;
	@Value("${User_Registration_By_Admin}")
	private String User_Registration_By_Admin;
	@Value("${Timesheet_Approval}")
	private String Timesheet_Approval;
	@Value("${Timesheet_Rejection}")
	private String Timesheet_Rejection;
	@Value("${Leave_Approval}")
	private String Leave_Approval;
	@Value("${Update_User_Status}")
	private String Update_User_Status;
	@Value("${Project_Assigned}")
	private String Project_Assigned;
	@Value("${Password_Change}")
	private String Password_Change;
	@Value("${Forgot_Password}")
	private String Forgot_Password;

	@Value("${Approval_Pending}")
	private String Approval_Pending;
	@Value("${VP_APPROVAL_PENDING}")
	private String VP_APPROVAL_PENDING;

	@Value("${TM1}")
	private String TM1;
	@Value("${TM2}")
	private String TM2;
	@Value("${TM3}")
	private String TM3;
	@Value("${TM4}")
	private String TM4;
	@Value("${TM5}")
	private String TM5;
	@Value("${TM6}")
	private String TM6;
	@Value("${TM7}")
	private String TM7;
	@Value("${TM8}")
	private String TM8;
	@Value("${TM9}")
	private String TM9;
	@Value("${TM10}")
	private String TM10;
	@Value("${TM11}")
	private String TM11;
	@Value("${TM12}")
	private String TM12;
	@Value("${TM13}")
	private String TM13;
	@Value("${TM14}")
	private String TM14;
	@Value("${TM15}")
	private String TM15;
	@Value("${TM16}")
	private String TM16;
	@Value("${TM17}")
	private String TM17;
	@Value("${TM18}")
	private String TM18;
	@Value("${TM19}")
	private String TM19;
	@Value("${TM20}")
	private String TM20;
	@Value("${TM29}")
	private String TM29;
	@Value("${TM28}")
	private String TM28;
	@Value("${TM27}")
	private String TM27;
	@Value("${TM26}")
	private String TM26;
	@Value("${TM25}")
	private String TM25;
	@Value("${TM24}")
	private String TM24;
	@Value("${TM23}")
	private String TM23;
	@Value("${TM22}")
	private String TM22;
	@Value("${TM21}")
	private String TM21;
	@Value("${TM30}")
	private String TM30;
	@Value("${TM31}")
	private String TM31;
	@Value("${TM32}")
	private String TM32;
	@Value("${TM33}")
	private String TM33;
	@Value("${TM34}")
	private String TM34;
	@Value("${TM35}")
	private String TM35;
	@Value("${TM36}")
	private String TM36;
	@Value("${TM37}")
	private String TM37;
	@Value("${TM38}")
	private String TM38;
	@Value("${TM39}")
	private String TM39;
	@Value("${TM40}")
	private String TM40;
	@Value("${TM41}")
	private String TM41;
	@Value("${TM42}")
	private String TM42;
	@Value("${TM43}")
	private String TM43;
	@Value("${TM44}")
	private String TM44;
	@Value("${TM45}")
	private String TM45;
	@Value("${TM46}")
	private String TM46;
	@Value("${TM47}")
	private String TM47;
	@Value("${TM48}")
	private String TM48;
	@Value("${TM49}")
	private String TM49;
	@Value("${TM50}")
	private String TM50;
	@Value("${TM51}")
	private String TM51;
	@Value("${TM59}")
	private String TM59;
	@Value("${TM52}")
	private String TM52;
	@Value("${TM53}")
	private String TM53;
	@Value("${TM54}")
	private String TM54;
	@Value("${TM55}")
	private String TM55;
	@Value("${TM56}")
	private String TM56;
	@Value("${TM57}")
	private String TM57;
	@Value("${TM58}")
	private String TM58;
	@Value("${TM60}")
	private String TM60;
	@Value("${TM61}")
	private String TM61;
	@Value("${TM62}")
	private String TM62;
	@Value("${TM63}")
	private String TM63;
	@Value("${TM64}")
	private String TM64;
	@Value("${TM65}")
	private String TM65;
	@Value("${TM66}")
	private String TM66;
	@Value("${TM67}")
	private String TM67;
	@Value("${TM68}")
	private String TM68;
	@Value("${TM69}")
	private String TM69;
	@Value("${TM71}")
	private String TM71;
	@Value("${TM72}")
	private String TM72;
	@Value("${TM73}")
	private String TM73;
	@Value("${TM74}")
	private String TM74;
	@Value("${TM76}")
	private String TM76;
	@Value("${TM77}")
	private String TM77;
	@Value("${TM78}")
	private String TM78;
	@Value("${TM79}")
	private String TM79;
	@Value("${TM80}")
	private String TM80;
	@Value("${TM81}")
	private String TM81;
	@Value("${TM82}")
	private String TM82;
	@Value("${TM89}")
	private String TM89;
	@Value("${TM90}")
	private String TM90;
	@Value("${TM91}")
	private String TM91;
	@Value("${TM101}")
	private String TM101;
	@Value("${TM102}")
	private String TM102;
	@Value("${TM104}")
	private String TM104;
	@Value("${TM105}")
	private String TM105;
	@Value("${TM106}")
	private String TM106;
	@Value("${TM107}")
	private String TM107;
	@Value("${TM108}")
	private String TM108;
	@Value("${TM109}")
	private String TM109;
	@Value("${TM110}")
	private String TM110;
	@Value("${TM111}")
	private String TM111;
	@Value("${TM112}")
	private String TM112;
	@Value("${TM113}")
	private String TM113;
	@Value("${TM114}")
	private String TM114;
	@Value("${TM115}")
	private String TM115;
	@Value("${TM116}")
	private String TM116;
	@Value("${TM117}")
	private String TM117;
	@Value("${TM118}")
	private String TM118;
	@Value("${TM119}")
	private String TM119;
	@Value("${TM127}")
	private String TM127;
	@Value("${TM128}")
	private String TM128;
	@Value("${TM129}")
	private String TM129;
	@Value("${TM130}")
	private String TM130;
	@Value("${LMS24}")
	private String LMS24;
	@Value("${LMS25}")
	private String LMS25;
	@Value("${RMS34}")
	private String RMS34;
	@Value("${RMS35}")
	private String RMS35;
	@Value("${RMS62}")
	private String RMS62;
	@Value("${TM131}")
	private String TM131;
	@Value("${TM132}")
	private String TM132;
	@Value("${TM133}")
	private String TM133;
	@Value("${TM134}")
	private String TM134;
	@Value("${TM135}")
	private String TM135;
	@Value("${TM136}")
	private String TM136;
	@Value("${TM137}")
	private String TM137;
	@Value("${TM138}")
	private String TM138;
	@Value("${TM139}")
	private String TM139;
	@Value("${TM140}")
	private String TM140;
	@Value("${TM141}")
	private String TM141;
	@Value("${TM142}")
	private String TM142;
	@Value("${TM143}")
	private String TM143;
	@Value("${TM145}")
	private String TM145;
	@Value("${TM146}")
	private String TM146;
	@Value("${TM147}")
	private String TM147;
	@Value("${TM148}")
	private String TM148;
	@Value("${TM149}")
	private String TM149;
	@Value("${TM150}")
	private String TM150;
	@Value("${TM151}")
	private String TM151;
	@Value("${TM152}")
	private String TM152;
	@Value("${TM153}")
	private String TM153;
	@Value("${TM154}")
	private String TM154;
	@Value("${TM155}")
	private String TM155;
	@Value("${TM156}")
	private String TM156;
	@Value("${TM157}")
	private String TM157;
	@Value("${TM158}")
	private String TM158;
	@Value("${TM159}")
	private String TM159;
	@Value("${TM160}")
	private String TM160;
	@Value("${TM161}")
	private String TM161;
	@Value("${TM162}")
	private String TM162;
	@Value("${TM163}")
	private String TM163;
	@Value("${TM164}")
	private String TM164;
	@Value("${TM165}")
	private String TM165;
	@Value("${TM166}")
	private String TM166;
	@Value("${TM167}")
	private String TM167;
	@Value("${TM168}")
	private String TM168;
	@Value("${TM169}")
	private String TM169;
	@Value("${TM170}")
	private String TM170;
	@Value("${TM171}")
	private String TM171;
	@Value("${TM172}")
	private String TM172;
	@Value("${TM173}")
	private String TM173;
	@Value("${TM174}")
	private String TM174;
	@Value("${TM175}")
	private String TM175;
	@Value("${TM176}")
	private String TM176;
	@Value("${TM177}")
	private String TM177;
	@Value("${TM178}")
	private String TM178;
	@Value("${TM179}")
	private String TM179;
	@Value("${TM180}")
	private String TM180;
	@Value("${TM181}")
	private String TM181;
	@Value("${TM182}")
	private String TM182;
	@Value("${TM183}")
	private String TM183;
	@Value("${TM184}")
	private String TM184;
	@Value("${TM185}")
	private String TM185;
	@Value("${TM186}")
	private String TM186;
	@Value("${TM187}")
	private String TM187;
	@Value("${TM188}")
	private String TM188;
	@Value("${TM190}")
	private String TM190;
	@Value("${TM191}")
	private String TM191;
	@Value("${TM192}")
	private String TM192;
	@Value("${TM197}")
	private String TM197;
	@Value("${TM198}")
	private String TM198;
	@Value("${TM199}")
	private String TM199;
	@Value("${TM200}")
	private String TM200;
	@Value("${TM201}")
	private String TM201;
	@Value("${TM202}")
	private String TM202;
	@Value("${TM203}")
	private String TM203;
	@Value("${TM204}")
	private String TM204;
	@Value("${TM205}")
	private String TM205;
	@Value("${TM206}")
	private String TM206;
	@Value("${TM207}")
	private String TM207;
	@Value("${TM208}")
	private String TM208;
	@Value("${TM209}")
	private String TM209;
	@Value("${TM210}")
	private String TM210;
	@Value("${TM211}")
	private String TM211;
	@Value("${TM212}")
	private String TM212;
	@Value("${TM214}")
	private String TM214;
	@Value("${TM215}")
	private String TM215;
	@Value("${TM216}")
	private String TM216;
	@Value("${TM217}")
	private String TM217;

	@Value("${M12}")
	private String M12;
	@Value("${M13}")
	private String M13;
	
	
	@Value("${M14}")
	private String M14;
	
	
	@Value("${M15}")
	private String M15;
	
	
	@Value("${M16}")
	private String M16;
	
	@Value("${M19}")
	private String M19;
	
	@Value("${M20}")
	private String M20;
	
	@Value("${FEEDBACK_MAILED_TO}")
	private String FEEDBACK_MAILED_TO;
	
	@Value("${LEAVE_TASKID}")
	private String LEAVE_TASKID;

	
	@Value("${TEST_DELIVERY_MAIL_TO2}")
	private String TEST_DELIVERY_MAIL_TO2;
	
	
	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String insertTimesheet(HttpSession session, LoginBean loginBean, boolean submitStatus) throws SQLException {
		boolean status = false;
		try {
			if (checkFilledLeaveTaskStatus(session, loginBean))
				return "Kindly apply leave before filling leave task.";
			deleteExistingData(loginBean);
			ListIterator<TaskMappingBean> itrEachTask = loginBean.getTaskMappingList().listIterator();
			int actionHistoryInsertCount = 1;
			double totalEffort = 0;
			while (itrEachTask.hasNext()) {
				TaskMappingBean temp = itrEachTask.next();

				HashMap<String, String> dateAndEffort = new LinkedHashMap<String, String>();
				dateAndEffort.put(temp.getDay1(), temp.getEffortInHoursDay1());
				dateAndEffort.put(temp.getDay2(), temp.getEffortInHoursDay2());
				dateAndEffort.put(temp.getDay3(), temp.getEffortInHoursDay3());
				dateAndEffort.put(temp.getDay4(), temp.getEffortInHoursDay4());
				dateAndEffort.put(temp.getDay5(), temp.getEffortInHoursDay5());
				dateAndEffort.put(temp.getDay6(), temp.getEffortInHoursDay6());
				dateAndEffort.put(temp.getDay7(), temp.getEffortInHoursDay7());

				List<String> weekComments = new LinkedList<String>();
				weekComments.add(temp.getDay1Comment());
				weekComments.add(temp.getDay2Comment());
				weekComments.add(temp.getDay3Comment());
				weekComments.add(temp.getDay4Comment());
				weekComments.add(temp.getDay5Comment());
				weekComments.add(temp.getDay6Comment());
				weekComments.add(temp.getDay7Comment());
				int zCount = 0;
				StringBuilder tempUserCmt = new StringBuilder("");

				for (Map.Entry<String, String> entry : dateAndEffort.entrySet()) {
					String keyDate = entry.getKey();
					String valueEffort = entry.getValue();
					if (valueEffort != null && !(valueEffort.trim().equalsIgnoreCase(""))) {
						totalEffort += Double.parseDouble(valueEffort);
						status = insertRow(temp.getProjectId(), temp.getTaskId(), loginBean.getUsername(), keyDate,
								valueEffort, submitStatus, weekComments.get(zCount),
								session.getAttribute("loggedInUserProxy").toString());// last modified by abhinav.gupta
																						// for proxy login
						if (weekComments.get(zCount).length() > 0)
							tempUserCmt.append(weekComments.get(zCount) + ";");
					}
					zCount++;
				}
				if (submitStatus && (actionHistoryInsertCount == 1)) {
					insertIntoActionHistory(session, "Submit", loginBean.getUsername(), tempUserCmt.toString(),
							temp.getDay1(), temp.getDay7());
					actionHistoryInsertCount++;
				}
			}
			status = true;
			if (submitStatus) {
				final String ufullname = session.getAttribute("loggedInUserFullName").toString();
				final String rmemail = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(TM129, session.getAttribute("loggedInUser").toString()), String.class);
				final String startDate = loginBean.getTaskMappingList().get(0).getDay1();
				final String endDate = loginBean.getTaskMappingList().get(0).getDay7();
				JSONArray tsDataSummary = getDataByPrjctUser(session, startDate, endDate,
						session.getAttribute("loggedInUser").toString(),
						loginBean.getTaskMappingList().get(0).getDay2(),
						loginBean.getTaskMappingList().get(0).getDay3(),
						loginBean.getTaskMappingList().get(0).getDay4(),
						loginBean.getTaskMappingList().get(0).getDay5(),
						loginBean.getTaskMappingList().get(0).getDay6());
				Map<String, Object> tsDataContent = new LinkedHashMap<String, Object>();
				Map<String, Object> mailContent = new HashMap<String, Object>();
				for (int h = 0; h < tsDataSummary.length(); h++) {
					tsDataContent.put(String.valueOf(h + 1), tsDataSummary.get(h));
				}
				mailContent.put("ufullname", ufullname);
				mailContent.put("userName", session.getAttribute("loggedInUser").toString());
				mailContent.put("userTsData", tsDataContent);
				mailContent.put("startDate", startDate);
				mailContent.put("endDate", endDate);
				mailContent.put("totalEffort", totalEffort);
				final Map<String, Object> mailContentFinal = mailContent;
				Thread t = new Thread() {
					@Override
					public void run() {
						// doSendTemplateEmail(rmemail, "Approve Timesheet: "+ufullname+", Timesheet
						// approval pending for user "+ufullname+" for week "+startDate+ " to "
						// +endDate, mailContentFinal,"tsSubmitIntimationToRM.vm");
					}
				};
				t.start();
				return "Timesheet has been sent for approval.";
			} else {
				return "Timesheet saved successfully.";
			}
		} catch (Exception e) {
			System.out.println("Error encountered timesheet data CRUD operation");
			e.printStackTrace();
			return "Contact Administrator";
		}
	}

	/*
	 * private boolean checkStatus(String projectId, String taskId, String username,
	 * String keyDate) throws ParseException, SQLException { boolean status = false;
	 * String query = "";
	 * 
	 * SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd"); Date parsed =
	 * formats.parse(keyDate); java.sql.Date timesheetDate1 = new
	 * java.sql.Date(parsed.getTime()); String taskStatus = ""; try{ query =
	 * GetQueryAPI.getQuery(TM2,
	 * taskId,projectId,username,timesheetDate1.toString());
	 * System.out.println(query); taskStatus = jdbcTemplate.queryForObject(query,
	 * String.class); if(taskStatus.equalsIgnoreCase("Approved")) status = true;
	 * }catch(Exception e){
	 * System.out.println("Error encountered while checking status");
	 * e.printStackTrace(); } return status; }
	 */

	private boolean checkFilledLeaveTaskStatus(HttpSession session, LoginBean loginBean) {
		boolean status = false;
		try {
			ListIterator<TaskMappingBean> itrEachTask = loginBean.getTaskMappingList().listIterator();
			List<String> leaveTasks = new LinkedList<String>();
			while (itrEachTask.hasNext()) {
				TaskMappingBean temp = itrEachTask.next();
				if (temp.getTaskId().equalsIgnoreCase(LEAVE_TASKID)) {
					if (!(temp.getEffortInHoursDay1().equalsIgnoreCase("0")))
						leaveTasks.add(temp.getDay1());
					if (!(temp.getEffortInHoursDay2().equalsIgnoreCase("0")))
						leaveTasks.add(temp.getDay2());
					if (!(temp.getEffortInHoursDay3().equalsIgnoreCase("0")))
						leaveTasks.add(temp.getDay3());
					if (!(temp.getEffortInHoursDay4().equalsIgnoreCase("0")))
						leaveTasks.add(temp.getDay4());
					if (!(temp.getEffortInHoursDay5().equalsIgnoreCase("0")))
						leaveTasks.add(temp.getDay5());
					if (!(temp.getEffortInHoursDay6().equalsIgnoreCase("0")))
						leaveTasks.add(temp.getDay6());
					if (!(temp.getEffortInHoursDay7().equalsIgnoreCase("0")))
						leaveTasks.add(temp.getDay7());

				}
			}
			for (String date : leaveTasks) {
				// System.out.println(GetQueryAPI.getQuery(TM170,
				// date,date,session.getAttribute("loggedInUser").toString()));
				int count = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(TM170, date, session.getAttribute("loggedInUser").toString()),
						Integer.class);
				if (count == 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	private void deleteExistingData(LoginBean loginBean) throws ParseException, SQLException {

		String tempDateStart = "";
		String tempDateEnd = "";
		String query = "";
		ListIterator<TaskMappingBean> itrEachTask = loginBean.getTaskMappingList().listIterator();
		while (itrEachTask.hasNext()) {
			TaskMappingBean temp = itrEachTask.next();
			tempDateStart = temp.getDay1();
			tempDateEnd = temp.getDay7();
		}
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = formats.parse(tempDateStart);
		java.sql.Date timesheetDate1 = new java.sql.Date(parsed.getTime());
		Date parsed2 = formats.parse(tempDateEnd);
		java.sql.Date timesheetDate2 = new java.sql.Date(parsed2.getTime());
		try {
			query = GetQueryAPI.getQuery(TM20, loginBean.getUsername(), timesheetDate1.toString(),
					timesheetDate2.toString());
			// System.out.println(query);
			jdbcTemplate.update(query);
		} catch (Exception e) {
			System.out.println("Error encountered while deleting timesheet data for given week");
			e.printStackTrace();
		}

	}

	/*
	 * private boolean deleteRow(String taskId, String username, String keyDate,
	 * boolean submitStatus) throws ParseException, SQLException { boolean status =
	 * false; String query = "";
	 * 
	 * SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd"); Date parsed =
	 * formats.parse(keyDate); java.sql.Date timesheetDate1 = new
	 * java.sql.Date(parsed.getTime()); try{ query = GetQueryAPI.getQuery(TM3,
	 * taskId,username,timesheetDate1.toString()); System.out.println(query);
	 * jdbcTemplate.update(query); status = true; }catch(Exception e){ System.out.
	 * println("Error encountered while deleting timesheet data for given date");
	 * e.printStackTrace(); } return status; }
	 */

	/*
	 * private boolean updateRow(String taskId, String username, String keyDate,
	 * String valueEffort, boolean submitStatus) throws ParseException { boolean
	 * status = false; String query = ""; PreparedStatement pstmTaskName = null;
	 * 
	 * SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd"); Date parsed =
	 * formats.parse(keyDate); java.sql.Date timesheetDate1 = new
	 * java.sql.Date(parsed.getTime()); try{ query =
	 * "update user_task_mapping_table set effortinhours = '"+ valueEffort
	 * +"',submitstatus='"+ String.valueOf(submitStatus) +"' where taskid='"+ taskId
	 * +"' and username = '"+ username +"' and timesheetdate = '"+ timesheetDate1
	 * +"'"; System.out.println(query); pstmTaskName =
	 * DBConnect.getConn().prepareStatement(query); pstmTaskName.executeUpdate();
	 * status = true; pstmTaskName.close(); // DBConnect.getConn().close();
	 * }catch(SQLException e){ System.out.
	 * println("Error encountered while updating timesheet data for given date");
	 * e.printStackTrace(); } return status; }
	 */

	/*
	 * private boolean chkPrevTimesheet(String projectId, String taskId, String
	 * username, String keyDate) throws ParseException, SQLException { boolean
	 * status = false; String query = "";
	 * 
	 * SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd"); Date parsed =
	 * formats.parse(keyDate); java.sql.Date timesheetDate1 = new
	 * java.sql.Date(parsed.getTime()); try{ query = GetQueryAPI.getQuery(TM4,
	 * taskId,projectId,username,timesheetDate1.toString());
	 * System.out.println(query); int counttask =
	 * jdbcTemplate.queryForObject(query,Integer.class); if(counttask > 0) status =
	 * true; }catch(Exception e){ System.out.
	 * println("Error encountered while checking prev timesheet data for given date"
	 * ); e.printStackTrace(); } return status; }
	 */

	private boolean insertRow(String projectId, String taskId, String username, String timesheetDate, String value,
			boolean submitStatus, String userRemark, String proxyUsername) throws SQLException, ParseException {
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = formats.parse(timesheetDate);
		java.sql.Date timesheetDate1 = new java.sql.Date(parsed.getTime());
		if (userRemark.trim().length() == 0) {
			userRemark = "";
		}
		boolean flag = false;
		String query = "";
		// Added in case of proxy user login
		String submittedBy = proxyUsername;
		if (proxyUsername.length() == 0)
			submittedBy = username;
		try {
			if (submitStatus)
				query = GetQueryAPI.getQuery(TM5, projectId, taskId, username, timesheetDate1.toString(), value,
						userRemark, sdf.format(new Date()).toString(), "Pending", submittedBy,
						sdf.format(new Date()).toString(), String.valueOf(submitStatus));
			else
				query = GetQueryAPI.getQuery(TM5, projectId, taskId, username, timesheetDate1.toString(), value,
						userRemark, sdf.format(new Date()).toString(), "Saved", submittedBy,
						sdf.format(new Date()).toString(), String.valueOf(submitStatus));
			jdbcTemplate.update(query);
			flag = true;
			System.out.println("Timesheet data inserted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean createReportProj(String projectId, String startDate1, String endDate1, String username)
			throws ParseException, IOException, SQLException {
		boolean statusFile = false;
		// Header Row creation for xls file
		@SuppressWarnings("rawtypes")
		List<List> recordToAdd = new ArrayList<List>();

		List<String> headerRow = new ArrayList<String>();
		headerRow.add("Project Name");
		headerRow.add("Task Name");
		headerRow.add("Employee Id");
		headerRow.add("Employee Name");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse(startDate1);
		Date endDate = formatter.parse(endDate1);

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.add(Calendar.DATE, 1);
		Map<String, String> allDaysBetween = new LinkedHashMap<String, String>();

		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			java.sql.Date keyDate = new java.sql.Date(date.getTime());
			allDaysBetween.put(keyDate.toString(), "0");
			headerRow.add(keyDate.toString());
		}
		recordToAdd.add(headerRow);
		// Fetching task details for specified project
		if (projectId.equalsIgnoreCase("select") && username.equalsIgnoreCase("select")) {
			List<String> pIdList = getProjectList("");
			ListIterator<String> itrPrjct = pIdList.listIterator();
			while (itrPrjct.hasNext()) {
				String tempProject = itrPrjct.next();
				List<String> taskIdList = getTaskList(tempProject, "");
				ListIterator<String> itrTask = taskIdList.listIterator();
				while (itrTask.hasNext()) {
					String tempTask = itrTask.next();
					List<String> userList = getUserListBasedOnTask(tempTask);
					ListIterator<String> itrUser = userList.listIterator();
					while (itrUser.hasNext()) {
						String tempUser = itrUser.next();
						Map<String, String> effortAndDate = getUserEffort(tempUser, tempTask, startDate1, endDate1);
						if (effortAndDate.size() > 0) {
							Set<String> effortDatesKeySet = effortAndDate.keySet();
							Iterator<String> itrEfforts = effortDatesKeySet.iterator();
							while (itrEfforts.hasNext()) {
								String tempDateString = itrEfforts.next();
								allDaysBetween.put(tempDateString, effortAndDate.get(tempDateString));
							}
							// System.out.println(allDaysBetween.values());
							// System.out.println(allDaysBetween.keySet());
							List<String> recordRow = new ArrayList<String>();
							recordRow.add(getProjectName(tempProject));
							recordRow.add(getTaskName(tempTask));
							recordRow.add(tempUser);
							recordRow.add(getEmpName(tempUser));
							Collection<String> effortValuesList = allDaysBetween.values();
							Iterator<String> itrEffort11 = effortValuesList.iterator();
							while (itrEffort11.hasNext()) {
								recordRow.add(itrEffort11.next().toString());
							}
							recordToAdd.add(recordRow);
						}
					}
				}
			}
		}
		if (projectId.equalsIgnoreCase("select") && !(username.equalsIgnoreCase("select"))) {
			List<String> pIdList = getProjectList(username);
			ListIterator<String> itrPrjct = pIdList.listIterator();
			while (itrPrjct.hasNext()) {
				String tempProject = itrPrjct.next();
				List<String> taskIdList = getTaskList(tempProject, "");
				ListIterator<String> itrTask = taskIdList.listIterator();
				while (itrTask.hasNext()) {
					String tempTask = itrTask.next();
					List<String> userList = getUserListBasedOnTask(tempTask);
					ListIterator<String> itrUser = userList.listIterator();
					while (itrUser.hasNext()) {
						String tempUser = itrUser.next();
						Map<String, String> effortAndDate = getUserEffort(tempUser, tempTask, startDate1, endDate1);
						if (effortAndDate.size() > 0) {
							Set<String> effortDatesKeySet = effortAndDate.keySet();
							Iterator<String> itrEfforts = effortDatesKeySet.iterator();
							while (itrEfforts.hasNext()) {
								String tempDateString = itrEfforts.next();
								allDaysBetween.put(tempDateString, effortAndDate.get(tempDateString));
							}
							// System.out.println(allDaysBetween.values());
							// System.out.println(allDaysBetween.keySet());
							List<String> recordRow = new ArrayList<String>();
							recordRow.add(getProjectName(tempProject));
							recordRow.add(getTaskName(tempTask));
							recordRow.add(tempUser);
							recordRow.add(getEmpName(tempUser));
							Collection<String> effortValuesList = allDaysBetween.values();
							Iterator<String> itrEffort11 = effortValuesList.iterator();
							while (itrEffort11.hasNext()) {
								recordRow.add(itrEffort11.next().toString());
							}
							recordToAdd.add(recordRow);
						}
					}
				}
			}
		}
		if (!(projectId.equalsIgnoreCase("select")) && username.equalsIgnoreCase("select")) {
			List<String> taskIdList = getTaskList(projectId, "");
			ListIterator<String> itrTask = taskIdList.listIterator();
			while (itrTask.hasNext()) {
				String tempTask = itrTask.next();
				List<String> userList = getUserListBasedOnTask(tempTask);
				ListIterator<String> itrUser = userList.listIterator();
				while (itrUser.hasNext()) {
					String tempUser = itrUser.next();
					Map<String, String> effortAndDate = getUserEffort(tempUser, tempTask, startDate1, endDate1);
					if (effortAndDate.size() > 0) {
						Set<String> effortDatesKeySet = effortAndDate.keySet();
						Iterator<String> itrEfforts = effortDatesKeySet.iterator();
						while (itrEfforts.hasNext()) {
							String tempDateString = itrEfforts.next();
							allDaysBetween.put(tempDateString, effortAndDate.get(tempDateString));
						}
						// System.out.println(allDaysBetween.values());
						// System.out.println(allDaysBetween.keySet());
						List<String> recordRow = new ArrayList<String>();
						recordRow.add(getProjectName(projectId));
						recordRow.add(getTaskName(tempTask));
						recordRow.add(tempUser);
						recordRow.add(getEmpName(tempUser));
						Collection<String> effortValuesList = allDaysBetween.values();
						Iterator<String> itrEffort11 = effortValuesList.iterator();
						while (itrEffort11.hasNext()) {
							recordRow.add(itrEffort11.next().toString());
						}
						recordToAdd.add(recordRow);
					}
				}
			}
		}
		if (!(projectId.equalsIgnoreCase("select")) && !(username.equalsIgnoreCase("select"))) {

			List<String> taskIdList = getTaskList(projectId, username);
			// System.out.println("tasklist"+taskIdList);
			ListIterator<String> itrTask = taskIdList.listIterator();
			while (itrTask.hasNext()) {
				String tempTask = itrTask.next();
				Map<String, String> effortAndDate = getUserEffort(username, tempTask, startDate1, endDate1);
				if (effortAndDate.size() > 0) {
					Set<String> effortDatesKeySet = effortAndDate.keySet();
					Iterator<String> itrEfforts = effortDatesKeySet.iterator();
					while (itrEfforts.hasNext()) {
						String tempDateString = itrEfforts.next();
						allDaysBetween.put(tempDateString, effortAndDate.get(tempDateString));
					}
					// System.out.println(allDaysBetween.values());
					// System.out.println(allDaysBetween.keySet());
					List<String> recordRow = new ArrayList<String>();
					recordRow.add(getProjectName(projectId));
					recordRow.add(getTaskName(tempTask));
					recordRow.add(username);
					recordRow.add(getEmpName(username));
					Collection<String> effortValuesList = allDaysBetween.values();
					Iterator<String> itrEffort11 = effortValuesList.iterator();
					while (itrEffort11.hasNext()) {
						recordRow.add(itrEffort11.next().toString());
					}
					recordToAdd.add(recordRow);
				}
			}

		}
		// System.out.println(recordToAdd);
		statusFile = createXLSReport(recordToAdd);
		return statusFile;
	}

	public String getEmpName(String tempUser) throws SQLException {
		String empName = "";
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM21, tempUser);
			// System.out.println(query);
			empName = jdbcTemplate.queryForObject(query, String.class);
		} catch (Exception e) {
			System.out.println("Error encountered while fetching emp name");
			e.printStackTrace();
		}
		return empName;
	}

	public String getProjectName(String projectId) throws SQLException {
		String projectName = "";
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM22, projectId);
			projectName = jdbcTemplate.queryForObject(query, String.class);
		} catch (Exception e) {
			System.out.println("Error encountered while fetching projectName");
			e.printStackTrace();
		}
		return projectName;
	}

	private String getTaskName(String tempTask) throws SQLException {
		String taskName = "";
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM23, tempTask);
			taskName = jdbcTemplate.queryForObject(query, String.class);
		} catch (Exception e) {
			System.out.println("Error encountered while fetching task name");
			e.printStackTrace();
		}
		return taskName;
	}

	private boolean createXLSReport(@SuppressWarnings("rawtypes") List<List> recordToAdd) throws IOException {

		boolean createStatus = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet firstSheet = workbook.createSheet("ProjectName");
		try {
			int rownum = 0;
			for (int j = 0; j < recordToAdd.size(); j++) {
				Row row = firstSheet.createRow(rownum);
				@SuppressWarnings("unchecked")
				List<String> rowData = recordToAdd.get(j);
				for (int k = 0; k < rowData.size(); k++) {
					Cell cell = row.createCell(k);
					cell.setCellValue(rowData.get(k));
				}
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String tempString = new SimpleDateFormat("yyyyMMdd'.xlsx'").format(new Date());
		tempString = "Report" + tempString;
		try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
			workbook.write(outputStream);
			createStatus = true;
		}
		return createStatus;
	}

	private Map<String, String> getUserEffort(String tempUser, String tempTask, String startDate, String endDate)
			throws ParseException, SQLException {
		String query = "";
		HashMap<String, String> effrtDateMap = new HashMap<String, String>();
		try {
			SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = formats.parse(startDate);
			java.sql.Date stDate1 = new java.sql.Date(parsed.getTime());
			Date parsed2 = formats.parse(endDate);
			java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());

			query = GetQueryAPI.getQuery(TM24, tempUser, tempTask, stDate1.toString(), endDate1.toString());
			effrtDateMap = jdbcTemplate.query(query, new ResultSetExtractor<HashMap<String, String>>() {
				@Override
				public HashMap<String, String> extractData(ResultSet rsEffort)
						throws SQLException, DataAccessException {
					HashMap<String, String> effortAndDate = new HashMap<String, String>();
					while (rsEffort.next()) {
						effortAndDate.put(rsEffort.getString(1), rsEffort.getString(2));
					}
					return effortAndDate;
				}
			});
		} catch (Exception e) {
			System.out.println("Error encountered while fetching user effort and tiesheetdate");
			e.printStackTrace();
		}
		return effrtDateMap;
	}

	private List<String> getUserListBasedOnTask(String tempTask) throws SQLException {
		List<String> userList = new ArrayList<String>();
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM25, tempTask);
			// System.out.println("username query = "+query);
			userList = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
				@Override
				public List<String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<String> tempList = new ArrayList<String>();
					while (rsEffort.next()) {
						tempList.add(rsEffort.getString(1));
					}
					return tempList;
				}
			});
		} catch (Exception e) {
			System.out.println("Error encountered while fetching user list");
			e.printStackTrace();
		}
		return userList;
	}

	private List<String> getTaskList(String projectId, String username) throws SQLException {
		List<String> taskIdList = new ArrayList<String>();
		String query = "";
		try {
			if (username.equalsIgnoreCase(""))
				query = GetQueryAPI.getQuery(TM26, projectId);
			else
				query = GetQueryAPI.getQuery(TM27, projectId, username);
			taskIdList = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
				@Override
				public List<String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<String> tempList = new ArrayList<String>();
					while (rsEffort.next()) {
						tempList.add(rsEffort.getString(1));
					}
					return tempList;
				}
			});
		} catch (Exception e) {
			System.out.println("Error encountered while fetching task list");
			e.printStackTrace();
		}
		return taskIdList;
	}

	public JSONArray fetchUserTimeSheetData(HttpSession session, final String firstDate, final String secondDate,
			final String thirdDate, final String fourthDate, final String fifthDate, final String sixthDate,
			final String lastDate) throws SQLException {
		String query = "";
		JSONArray jasonTimeData = new JSONArray();
		List<String> taskIdList = new ArrayList<String>();
		try {
			SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");

			Date parsed = formats.parse(firstDate);
			java.sql.Date stDate1 = new java.sql.Date(parsed.getTime());

			Date parsed2 = formats.parse(lastDate);
			java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());

			List<String> daysList = new ArrayList<String>();
			daysList.add(firstDate);
			daysList.add(secondDate);
			daysList.add(thirdDate);
			daysList.add(fourthDate);
			daysList.add(fifthDate);
			daysList.add(sixthDate);
			daysList.add(lastDate);

			query = GetQueryAPI.getQuery(TM28, session.getAttribute("loggedInUser").toString(), stDate1.toString(),
					endDate1.toString());
			taskIdList = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
				@Override
				public List<String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<String> tempList = new ArrayList<String>();
					while (rsEffort.next()) {
						tempList.add(rsEffort.getString(1));
					}
					return tempList;
				}
			});
			ListIterator<String> itrTask = taskIdList.listIterator();
			while (itrTask.hasNext()) {
				/*
				 * dbConnection = dataSource.getConnection(); pstmEffort =
				 * dbConnection.prepareStatement(query); rsEffort = pstmEffort.executeQuery();
				 * JSONObject jasonTimeEachData = new JSONObject(); while(rsEffort.next()){
				 * jasonTimeEachData.put("projectId", rsEffort.getString(1));
				 * jasonTimeEachData.put("projectName",getProjectName( rsEffort.getString(1) ));
				 * jasonTimeEachData.put("taskId", rsEffort.getString(2));
				 * jasonTimeEachData.put("taskName",getTaskName( rsEffort.getString(2) ));
				 * jasonTimeEachData.put("submitStatus", rsEffort.getString(5));
				 * jasonTimeEachData.put("adminRemark", rsEffort.getString(7));
				 * jasonTimeEachData.put("tmSheetStatus", rsEffort.getString(8));
				 * if(firstDate.equalsIgnoreCase(rsEffort.getString(3))){
				 * jasonTimeEachData.put("day1",rsEffort.getString(4));
				 * jasonTimeEachData.put("day1Comment",rsEffort.getString(6)); }else{
				 * if(secondDate.equalsIgnoreCase(rsEffort.getString(3))){
				 * jasonTimeEachData.put("day2",rsEffort.getString(4));
				 * jasonTimeEachData.put("day2Comment",rsEffort.getString(6)); }else{
				 * if(thirdDate.equalsIgnoreCase(rsEffort.getString(3))){
				 * jasonTimeEachData.put("day3",rsEffort.getString(4));
				 * jasonTimeEachData.put("day3Comment",rsEffort.getString(6)); }else{
				 * if(fourthDate.equalsIgnoreCase(rsEffort.getString(3))){
				 * jasonTimeEachData.put("day4",rsEffort.getString(4));
				 * jasonTimeEachData.put("day4Comment",rsEffort.getString(6)); }else{
				 * if(fifthDate.equalsIgnoreCase(rsEffort.getString(3))){
				 * jasonTimeEachData.put("day5",rsEffort.getString(4));
				 * jasonTimeEachData.put("day5Comment",rsEffort.getString(6)); }else{
				 * if(sixthDate.equalsIgnoreCase(rsEffort.getString(3))){
				 * jasonTimeEachData.put("day6",rsEffort.getString(4));
				 * jasonTimeEachData.put("day6Comment",rsEffort.getString(6)); }else{
				 * if(lastDate.equalsIgnoreCase(rsEffort.getString(3))){
				 * jasonTimeEachData.put("day7",rsEffort.getString(4));
				 * jasonTimeEachData.put("day7Comment",rsEffort.getString(6)); } } } } }
				 * 
				 * } } } jasonTimeData.put(jasonTimeEachData);
				 */
				query = GetQueryAPI.getQuery(TM29, session.getAttribute("loggedInUser").toString(), itrTask.next(),
						stDate1.toString(), endDate1.toString());
				List<JSONObject> dataList = new ArrayList<JSONObject>();
				dataList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
					@Override
					public List<JSONObject> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
						List<JSONObject> tempList = new ArrayList<JSONObject>();
						JSONObject jasonTimeEachData = new JSONObject();
						while (rsEffort.next()) {
							try {

								jasonTimeEachData.put("projectId", rsEffort.getString(1));
								jasonTimeEachData.put("projectName", getProjectName(rsEffort.getString(1)));
								jasonTimeEachData.put("taskId", rsEffort.getString(2));
								jasonTimeEachData.put("taskName", getTaskName(rsEffort.getString(2)));
								jasonTimeEachData.put("submitStatus", rsEffort.getString(5));
								jasonTimeEachData.put("adminRemark", rsEffort.getString(7));
								jasonTimeEachData.put("tmSheetStatus", rsEffort.getString(8));
								if (firstDate.equalsIgnoreCase(rsEffort.getString(3))) {
									jasonTimeEachData.put("day1", rsEffort.getString(4));
									jasonTimeEachData.put("day1Comment", rsEffort.getString(6));
								} else {
									if (secondDate.equalsIgnoreCase(rsEffort.getString(3))) {
										jasonTimeEachData.put("day2", rsEffort.getString(4));
										jasonTimeEachData.put("day2Comment", rsEffort.getString(6));
									} else {
										if (thirdDate.equalsIgnoreCase(rsEffort.getString(3))) {
											jasonTimeEachData.put("day3", rsEffort.getString(4));
											jasonTimeEachData.put("day3Comment", rsEffort.getString(6));
										} else {
											if (fourthDate.equalsIgnoreCase(rsEffort.getString(3))) {
												jasonTimeEachData.put("day4", rsEffort.getString(4));
												jasonTimeEachData.put("day4Comment", rsEffort.getString(6));
											} else {
												if (fifthDate.equalsIgnoreCase(rsEffort.getString(3))) {
													jasonTimeEachData.put("day5", rsEffort.getString(4));
													jasonTimeEachData.put("day5Comment", rsEffort.getString(6));
												} else {
													if (sixthDate.equalsIgnoreCase(rsEffort.getString(3))) {
														jasonTimeEachData.put("day6", rsEffort.getString(4));
														jasonTimeEachData.put("day6Comment", rsEffort.getString(6));
													} else {
														if (lastDate.equalsIgnoreCase(rsEffort.getString(3))) {
															jasonTimeEachData.put("day7", rsEffort.getString(4));
															jasonTimeEachData.put("day7Comment", rsEffort.getString(6));
														}
													}
												}
											}
										}

									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						tempList.add(jasonTimeEachData);
						return tempList;
					}
				});
				jasonTimeData.put(dataList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jasonTimeData);
		return jasonTimeData;
	}

	public List<String> getProjectList(String userName) {
		String query = "";
		List<String> prjList = new ArrayList<String>();
		try {
			if (userName.length() == 0)
				query = GetQueryAPI.getQuery(TM30);
			else
				query = GetQueryAPI.getQuery(TM31, userName);
			// System.out.println("user assigned project query = "+query);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
				@Override
				public List<String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<String> tempList = new ArrayList<String>();
					while (rsEffort.next()) {
						tempList.add(rsEffort.getString(1));
					}
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList;
	}

	public JSONArray getProjectList(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjctList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM32, session.getAttribute("loggedInUser").toString());
			prjctList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("projectId", rsEffort.getString(1));
							jasonTimeEachData.put("projectName", rsEffort.getString(2));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjctList.get(0);
	}

	public JSONArray getTaskListByPID(HttpSession session, String projectId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "";
		JSONArray jasonProjectData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM33, projectId);
			List<JSONObject> mapRet2 = new ArrayList<JSONObject>();
			mapRet2 = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
				@Override
				public List<JSONObject> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<JSONObject> mapRet1 = new ArrayList<JSONObject>();
					try {
						while (rs.next()) {
							JSONObject temp = new JSONObject();
							temp.put("taskId", rs.getString("taskId"));
							temp.put("taskName", rs.getString("taskName"));
							mapRet1.add(temp);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					return mapRet1;
				}
			});
			for (int i = 0; i < mapRet2.size(); i++) {
				jasonProjectData.put(mapRet2.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonProjectData;
	}

	public JSONArray getAllProjectList(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN)
					|| ((List<String>) session.getAttribute("loggedInUserGroups")).contains(HR))
				query = GetQueryAPI.getQuery(TM34);
			else
				query = GetQueryAPI.getQuery(TM32, session.getAttribute("loggedInUser").toString());
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("projectId", rsEffort.getString(1));
							jasonTimeEachData.put("projectName", rsEffort.getString(2));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	@SuppressWarnings("unchecked")
	public JSONArray getUserList(HttpSession session, String pid, boolean allUFlag) throws SQLException, JSONException {
		String query = "";
		List<JSONArray> userList = new ArrayList<JSONArray>();
		try {
			if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(USER)
					&& !(((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN))
					&& !(((List<String>) session.getAttribute("loggedInUserGroups")).contains(HR))) {
				query = GetQueryAPI.getQuery(TM58, session.getAttribute("loggedInUser").toString());
			} else {
				if (allUFlag && (!(pid.equalsIgnoreCase("select")) || !(pid.equalsIgnoreCase("")))) {
					query = GetQueryAPI.getQuery(TM35, pid);
				} else {
					if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN)
							|| ((List<String>) session.getAttribute("loggedInUserGroups")).contains(HR))
						query = GetQueryAPI.getQuery(TM36);
					else
						query = GetQueryAPI.getQuery(TM58, session.getAttribute("loggedInUser").toString());
				}
			}
			System.out.println("query" + query);
			userList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("username", rsEffort.getString(1));
							jasonTimeEachData.put("fullname", rsEffort.getString(2));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
			if (userList.size() != 0) {
				JSONObject jasonTimeEachData = new JSONObject();
				jasonTimeEachData.put("username", session.getAttribute("loggedInUser").toString());
				jasonTimeEachData.put("fullname", getEmpName(session.getAttribute("loggedInUser").toString()));
				JSONArray jasonProjectData = userList.get(0);
				int loggedInUserInList = 1;
				for (int z = 0; z < jasonProjectData.length(); z++) {
					JSONObject temp = jasonProjectData.getJSONObject(z);
					if (temp.getString("username").equalsIgnoreCase(session.getAttribute("loggedInUser").toString()))
						loggedInUserInList = 0;
				}
				if (loggedInUserInList != 0)
					jasonProjectData.put(jasonTimeEachData);
				userList.add(jasonProjectData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList.get(0);
	}

	@SuppressWarnings("unchecked")
	public JSONArray getUsersDataUnderReportee(HttpSession session, String username, String startDate, String endDate,
			String filterVar, String allReporteeFlag, String pageno, String batchSize)
			throws ParseException, SQLException {
		String query = "";
		int pageid = Integer.parseInt(pageno);
		List<JSONArray> userList = new ArrayList<JSONArray>();
		try {
			String filterString = "";
			if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN)) {
				if (!(allReporteeFlag.equalsIgnoreCase(""))) {
					filterString = " and m.username in (select username from user where reportingmanager='"
							+ session.getAttribute("loggedInUser").toString() + "')";
				}
				if (!(filterVar.equalsIgnoreCase("All")))
					filterString = filterString + " and timesheetstatus = '" + filterVar + "'";
				if (!(username.equalsIgnoreCase("")))
					filterString = filterString + " and (a.firstname like '%" + username + "%' or a.lastname like '%"
							+ username.substring(username.indexOf(" ") + 1) + "%')";
				if (!(startDate.equalsIgnoreCase("")) && !(endDate.equalsIgnoreCase("")))
					filterString = filterString + " and timesheetdate between '" + startDate + "' and '" + endDate
							+ "'";
			} else {
				filterString = " and m.username in (select username from user where reportingmanager='"
						+ session.getAttribute("loggedInUser").toString() + "')";
				if (!(filterVar.equalsIgnoreCase("All")))
					filterString = filterString + " and timesheetstatus = '" + filterVar + "'";
				if (!(username.equalsIgnoreCase("")))
					filterString = filterString + " and (a.firstname like '%" + username + "%' or a.lastname like '%"
							+ username.substring(username.indexOf(" ") + 1) + "%')";
				if (!(startDate.equalsIgnoreCase("")) && !(endDate.equalsIgnoreCase("")))
					filterString = filterString + " and timesheetdate between '" + startDate + "' and '" + endDate
							+ "'";
			}
			query = GetQueryAPI.getQuery(TM165, filterString);
			System.out.println(query);
			/*
			 * if(!("All".equalsIgnoreCase(filterVar))){
			 * if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN)
			 * ) query = GetQueryAPI.getQuery(TM76,filterVar); else query =
			 * GetQueryAPI.getQuery(TM16,
			 * session.getAttribute("loggedInUser").toString(),filterVar); }else{
			 * if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN)
			 * ) query = GetQueryAPI.getQuery(TM77); else query = GetQueryAPI.getQuery(TM17,
			 * session.getAttribute("loggedInUser").toString()); }
			 */
			if (pageid == 1) {
			} else {
				pageid = (pageid - 1) * Integer.parseInt(batchSize) + 1;
			}
			String countQuery = "select count(*) from (" + query + ") as aggregate";
			int totalCount1 = jdbcTemplate.queryForObject(countQuery, Integer.class);
			final int totalCount = (totalCount1 / Integer.parseInt(batchSize)) + 1;
			userList = jdbcTemplate.query(query.concat(" limit " + (pageid - 1) + "," + Integer.parseInt(batchSize)),
					new ResultSetExtractor<List<JSONArray>>() {
						@Override
						public List<JSONArray> extractData(ResultSet rsEffort)
								throws SQLException, DataAccessException {
							List<JSONArray> tempList = new ArrayList<JSONArray>();
							JSONArray jasonProjectData = new JSONArray();
							while (rsEffort.next()) {
								JSONObject jasonTimeEachData = new JSONObject();
								try {
									jasonTimeEachData.put("username", rsEffort.getString(1));
									jasonTimeEachData.put("usereffort", rsEffort.getString(2));
									jasonTimeEachData.put("status", rsEffort.getString(3));
									jasonTimeEachData.put("weekDates", rsEffort.getString(4));
									jasonTimeEachData.put("userfName", rsEffort.getString(5));
									jasonTimeEachData.put("usercode", rsEffort.getString(6));
									jasonTimeEachData.put("rmManager", rsEffort.getString(7));
									jasonTimeEachData.put("rmManagerName", rsEffort.getString(8));
									jasonTimeEachData.put("totalcount", totalCount);
								} catch (JSONException e) {
									e.printStackTrace();
								}
								jasonProjectData.put(jasonTimeEachData);
							}
							tempList.add(jasonProjectData);
							return tempList;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList.get(0);
	}

	public boolean updateTimesheetStatusUser(HttpSession session, String startDate, String endDate, boolean result,
			String username, String appRmrk) throws ParseException, SQLException {
		boolean status = false;
		String query = "";
		String resultString = "";
		if (appRmrk.trim().length() == 0) {
			if (result)
				appRmrk = "Ok";
			else
				appRmrk = "Invalid effort entry";
		}
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed1 = formats.parse(startDate);
		java.sql.Date startDate1 = new java.sql.Date(parsed1.getTime());

		Date parsed2 = formats.parse(endDate);
		java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());
		try {
			if (result) {
				resultString = "Approved";
				query = GetQueryAPI.getQuery(TM38, resultString, sdf.format(new Date()),
						session.getAttribute("loggedInUser").toString(), username, startDate1.toString(),
						endDate1.toString());
				insertIntoActionHistory(session, "Approved", username, appRmrk, startDate1.toString(),
						endDate1.toString());
				if (checkMailNotificationTrigger(Timesheet_Approval)) {
					String body = "Your time sheet for week " + startDate1 + " to " + endDate1 + " has been approved.";
					doSendEmail(getUserMailId(username), "Timesheet Approved", body);
				}
				if (checkMailNotificationTrigger(Leave_Approval)) {
					sendMailToHROnLeaveApprove(username, startDate1.toString(), endDate1.toString());
				}
			} else {
				resultString = "Rejected";
				query = GetQueryAPI.getQuery(TM39, resultString, sdf.format(new Date()),
						session.getAttribute("loggedInUser").toString(), username, startDate1.toString(),
						endDate1.toString());
				insertIntoActionHistory(session, "Rejected", username, appRmrk, startDate1.toString(),
						endDate1.toString());
				if (checkMailNotificationTrigger(Leave_Rejected)) {
					String body = "Your time sheet for week " + startDate1 + " to " + endDate1 + " has been rejected.";
					doSendEmail(getUserMailId(username), "Timesheet Rejected", body);
				}
			}
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while updating timesheet status for given date and user");
			e.printStackTrace();
		}
		return status;
	}

	private void sendMailToHROnLeaveApprove(String username, String startDate, String endDate) {
		String query = "";
		List<String> leaveDays = new ArrayList<String>();
		try {
			query = GetQueryAPI.getQuery(TM90, username, LEAVE_TASKID, startDate, endDate);
			leaveDays = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
				@Override
				public List<String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<String> tempList = new ArrayList<String>();
					while (rsEffort.next()) {
						tempList.add(rsEffort.getString(1));
					}
					return tempList;
				}
			});
			if (leaveDays.size() > 0) {
				String idList = leaveDays.toString();
				String commaSeperatedDates = idList.substring(1, idList.length() - 1).replace(", ", ",");
				String message = "Leave request of " + getEmpName(username) + " has been approved for "
						+ commaSeperatedDates + " dates.";
				String queryHRManager = GetQueryAPI.getQuery(TM91, username);
				String hRManager = jdbcTemplate.queryForObject(queryHRManager, String.class);
				doSendEmail(hRManager, "Leave request Approved.", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
////////////////////////////////////////////////////
	private boolean checkMailNotificationTrigger(String requestActionId) {
		boolean status = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM80, requestActionId);
			String triggerStatus = jdbcTemplate.queryForObject(query, String.class);
			if (triggerStatus.equalsIgnoreCase("true"))
				status = true;
		} catch (Exception e) {
			System.out.println("Error encountered");
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	

	public JSONArray getPendingUserList(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> usrList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM40);
			usrList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("userid", rsEffort.getString(1));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usrList.get(0);
	}

	public JSONArray getAllUserMailList(HttpSession session, String email) throws SQLException {
		String query = "";
		JSONArray jasonUserData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM1, email);
			int mailCount = jdbcTemplate.queryForObject(query, Integer.class);
			// System.out.println("mailCOunt="+mailCount);
			JSONObject jasonTimeEachData = new JSONObject();
			if (mailCount > 0)
				jasonTimeEachData.put("status", true);
			else
				jasonTimeEachData.put("status", false);
			jasonUserData.put(jasonTimeEachData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonUserData;
	}

	public JSONArray getPendingUsers(HttpSession session, String filterVar, String filterName) throws SQLException {
		String query = "";
		List<JSONArray> userList = new ArrayList<JSONArray>();
		try {
			if (!("All".equalsIgnoreCase(filterVar)))
				query = GetQueryAPI.getQuery(TM191, session.getAttribute("loggedInUserPolicyGroup").toString(),
						filterVar);
			else
				query = GetQueryAPI.getQuery(TM190, session.getAttribute("loggedInUserPolicyGroup").toString());
			System.out.println("user list query = " + query);
			userList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("username", rsEffort.getString(1));
							jasonTimeEachData.put("firstname", rsEffort.getString(2));
							jasonTimeEachData.put("lastname", rsEffort.getString(3));
							jasonTimeEachData.put("role", rsEffort.getString(4));
							jasonTimeEachData.put("primaryemail", rsEffort.getString(5));
							jasonTimeEachData.put("usergroup", rsEffort.getString(6));
							jasonTimeEachData.put("userstatus", rsEffort.getString(7));
							jasonTimeEachData.put("dob", rsEffort.getString(8));
							jasonTimeEachData.put("creationdate", rsEffort.getString(9));
							jasonTimeEachData.put("lastmodifieddate", rsEffort.getString(10));
							jasonTimeEachData.put("reporteeId", rsEffort.getString(11));
							jasonTimeEachData.put("reporteeHRId", rsEffort.getString(12));
							jasonTimeEachData.put("reporteeName", rsEffort.getString(13));
							jasonTimeEachData.put("reporteeHRName", rsEffort.getString(14));
							jasonTimeEachData.put("primarycontact", rsEffort.getString(15));
							jasonTimeEachData.put("extension", rsEffort.getString(16));
							jasonTimeEachData.put("usercode", rsEffort.getString(17));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList.get(0);
	}

	public boolean updateStatusUser(HttpSession session, String uid, String usrGroup, String usrRole, String usrStatus,
			String usrRemark, String usrReportingManager, String usrHRManager) throws ParseException, SQLException {
		boolean status = false;
		String query = "";
		try {
			if (usrGroup.equalsIgnoreCase(""))
				query = GetQueryAPI.getQuery(TM160, usrRole, usrStatus, sdf.format(new Date()), usrRemark,
						usrReportingManager, usrHRManager, uid);
			else
				query = GetQueryAPI.getQuery(TM63, usrRole, usrGroup, usrStatus, sdf.format(new Date()), usrRemark,
						usrReportingManager, usrHRManager, uid);
			// System.out.println(query);
			int updateCount = jdbcTemplate.update(query);
			if (!(usrGroup.equalsIgnoreCase("")) && updateCount != 0) {
				String query1 = GetQueryAPI.getQuery(TM64, ORGANIZATION_PROJECT, uid, sdf.format(new Date()),
						session.getAttribute("loggedInUser").toString());
				int defaultAssignCount = 0;
				try {
					defaultAssignCount = jdbcTemplate.update(query1);
				} catch (Exception e) {
					System.out.println("Organization project assignement failed.");
				}
				if (defaultAssignCount != 0) {
					if (checkMailNotificationTrigger(Update_User_Status)) {
						String body = "Following details updated in Supra TMS:\n\n Reporting Manager:"
								+ usrReportingManager + "\n\n Role: " + usrRole + "\n\n Status: " + usrStatus;
						doSendEmail(getUserMailId(uid), "Your details has been updated in timesheet.", body);
					}
				}
			}
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while updating user details.");
			e.printStackTrace();
		}
		return status;
	}

	private String getUserMailId(String uid) {
		String query = "";
		String mailId = "";
		try {
			query = GetQueryAPI.getQuery(TM11, uid);
			mailId = jdbcTemplate.queryForObject(query, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailId;
	}

	public JSONArray getDataByPrjctUser(HttpSession session, String strtDate, String endDate, String username,
			String secondDate, String thirdDate, String fourthDate, String fifthDate, String sixthDate)
			throws ParseException, SQLException {
		String query = "";
		JSONArray jasonTimeData = new JSONArray();
		java.sql.ResultSet rsEffort = null;
		PreparedStatement pstmEffort = null;
		List<String> taskIdList = new ArrayList<String>();
		try {
			SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");

			Date parsed = formats.parse(strtDate);
			java.sql.Date stDate1 = new java.sql.Date(parsed.getTime());

			Date parsed2 = formats.parse(endDate);
			java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());

			List<String> daysList = new ArrayList<String>();
			daysList.add(strtDate);
			daysList.add(secondDate);
			daysList.add(thirdDate);
			daysList.add(fourthDate);
			daysList.add(fifthDate);
			daysList.add(sixthDate);
			daysList.add(endDate);

			query = GetQueryAPI.getQuery(TM44, username, stDate1.toString(), endDate1.toString());
			taskIdList = jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
				@Override
				public List<String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<String> tempList = new ArrayList<String>();
					while (rsEffort.next()) {
						tempList.add(rsEffort.getString(1));
					}
					return tempList;
				}
			});
			ListIterator<String> itrTask = taskIdList.listIterator();
			StringBuffer usrRemark = new StringBuffer();
			while (itrTask.hasNext()) {
				query = GetQueryAPI.getQuery(TM45, username, itrTask.next(), stDate1.toString(), endDate1.toString());
				dbConnection = dataSource.getConnection();
				pstmEffort = dbConnection.prepareStatement(query);
				rsEffort = pstmEffort.executeQuery();
				JSONObject jasonTimeEachData = new JSONObject();
				while (rsEffort.next()) {
					jasonTimeEachData.put("prjctName", getProjectName(rsEffort.getString(1)));
					jasonTimeEachData.put("taskname", getTaskName(rsEffort.getString(2)));

					if (strtDate.equalsIgnoreCase(rsEffort.getString(3))) {
						jasonTimeEachData.put("day1", rsEffort.getString(4));
						jasonTimeEachData.put("day1Remark", rsEffort.getString(6));
						usrRemark.append(
								(rsEffort.getString(6).equalsIgnoreCase("NA")) ? "" : rsEffort.getString(6) + ";");
					} else {
						if (secondDate.equalsIgnoreCase(rsEffort.getString(3))) {
							jasonTimeEachData.put("day2", rsEffort.getString(4));
							jasonTimeEachData.put("day2Remark", rsEffort.getString(6));
							usrRemark.append(
									(rsEffort.getString(6).equalsIgnoreCase("NA")) ? "" : rsEffort.getString(6) + ";");
						} else {
							if (thirdDate.equalsIgnoreCase(rsEffort.getString(3))) {
								jasonTimeEachData.put("day3", rsEffort.getString(4));
								jasonTimeEachData.put("day3Remark", rsEffort.getString(6));
								usrRemark.append((rsEffort.getString(6).equalsIgnoreCase("NA")) ? ""
										: rsEffort.getString(6) + ";");
							} else {
								if (fourthDate.equalsIgnoreCase(rsEffort.getString(3))) {
									jasonTimeEachData.put("day4", rsEffort.getString(4));
									jasonTimeEachData.put("day4Remark", rsEffort.getString(6));
									usrRemark.append((rsEffort.getString(6).equalsIgnoreCase("NA")) ? ""
											: rsEffort.getString(6) + ";");
								} else {
									if (fifthDate.equalsIgnoreCase(rsEffort.getString(3))) {
										jasonTimeEachData.put("day5", rsEffort.getString(4));
										jasonTimeEachData.put("day5Remark", rsEffort.getString(6));
										usrRemark.append((rsEffort.getString(6).equalsIgnoreCase("NA")) ? ""
												: rsEffort.getString(6) + ";");
									} else {
										if (sixthDate.equalsIgnoreCase(rsEffort.getString(3))) {
											jasonTimeEachData.put("day6", rsEffort.getString(4));
											jasonTimeEachData.put("day6Remark", rsEffort.getString(6));
											usrRemark.append((rsEffort.getString(6).equalsIgnoreCase("NA")) ? ""
													: rsEffort.getString(6) + ";");
										} else {
											if (endDate.equalsIgnoreCase(rsEffort.getString(3))) {
												jasonTimeEachData.put("day7", rsEffort.getString(4));
												jasonTimeEachData.put("day7Remark", rsEffort.getString(6));
												usrRemark.append((rsEffort.getString(6).equalsIgnoreCase("NA")) ? ""
														: rsEffort.getString(6) + ";");
											}
										}
									}
								}
							}

						}
					}
					jasonTimeEachData.put("statusWeek", rsEffort.getString(5));
					jasonTimeEachData.put("userName", rsEffort.getString(7));
					jasonTimeEachData.put("userfName", getEmpName(rsEffort.getString(7)));
					jasonTimeEachData.put("approverRemark", rsEffort.getString(8));
				}
				if (usrRemark.length() > 0)
					jasonTimeEachData.put("remarkUser", usrRemark);
				else
					jasonTimeEachData.put("remarkUser", "No comments available");
				jasonTimeData.put(jasonTimeEachData);
				// System.out.println(jasonTimeData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jasonTimeData);
		return jasonTimeData;
	}

	public String addProjectToDB(HttpSession session, String prjctType, String prjctName, String prjctDesc,
			String prjctStartDate, String prjctEndDate) throws SQLException {
		boolean status = false;
		String projectId = "";
		String val = "" + ((int) (Math.random() * 9000) + 1000);
		if (prjctName.length() <= 5)
			projectId = prjctName + val;
		else
			projectId = prjctName.substring(0, 5) + val;
		projectId = projectId.replace(" ", "");
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM46, projectId, prjctName, prjctDesc, sdf.format(new Date()),
					session.getAttribute("loggedInUser").toString(), prjctType, prjctStartDate, prjctEndDate);
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (status)
			return projectId;
		else
			return "";
	}

	@SuppressWarnings("unchecked")
	public JSONArray getAllProjectDetailList1(HttpSession session, String filterVar)
			throws ParseException, SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN))
				query = GetQueryAPI.getQuery(TM48, filterVar);
			else
				query = GetQueryAPI.getQuery(TM106, filterVar, session.getAttribute("loggedInUser").toString());
			// System.out.println(query);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("prjctId", rsEffort.getString(1));
							jasonTimeEachData.put("projectName", rsEffort.getString(2));
							jasonTimeEachData.put("projectDesc", rsEffort.getString(3));
							jasonTimeEachData.put("creationDate", rsEffort.getString(4));
							jasonTimeEachData.put("projectOwner", rsEffort.getString(5));
							jasonTimeEachData.put("type", rsEffort.getString(6));
							jasonTimeEachData.put("status", rsEffort.getString(7));
							jasonTimeEachData.put("startDate", rsEffort.getString(8));
							jasonTimeEachData.put("enddate", rsEffort.getString(9));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public boolean addTaskToProject(HttpSession session, String taskType, String taskName, String taskDesc, String pid)
			throws SQLException {
		boolean status = false;
		String taskId = "";
		String val = "" + ((int) (Math.random() * 9000) + 1000);
		if (taskName.length() <= 4)
			taskId = pid.substring(0, 4) + val + taskName.substring(0);
		else
			taskId = pid.substring(0, 4) + val + taskName.substring(0, 4);
		String query = "";
		try {
			String prjctStartDate = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM104, pid), String.class);
			String prjctEndDate = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM105, pid), String.class);
			query = GetQueryAPI.getQuery(TM49, taskId, taskName, taskType, taskDesc, pid, sdf.format(new Date()),
					"Active", session.getAttribute("loggedInUser").toString(), sdf.format(new Date()), "Medium",
					prjctStartDate, prjctEndDate, prjctStartDate, prjctEndDate);
			// System.out.println("Create task="+query);
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public JSONArray getActiveUsers(HttpSession session, String pid, boolean allUserFlag) throws SQLException {
		String query = "";
		List<JSONArray> userList = new ArrayList<JSONArray>();
		try {
			if (allUserFlag)
				query = GetQueryAPI.getQuery(TM50, pid);
			else
				query = GetQueryAPI.getQuery(TM51, pid);
			userList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							// jasonTimeEachData.put("username", rsEffort.getString(1));
							// jasonTimeEachData.put("fullname", rsEffort.getString(2) + " " +
							// rsEffort.getString(3));
							// jasonTimeEachData.put("userrole", rsEffort.getString(4));
							jasonTimeEachData.put("usermail", rsEffort.getString(5));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList.get(0);
	}

	public boolean addUsersToProject(HttpSession session, String selectedUsers, String pid) throws SQLException {
		boolean status = false;
		List<String> result = Arrays.asList(selectedUsers.split("\\s*,\\s*"));
		try {
			for (int i = 0; i < result.size(); i++) {
				String usrnameQuery = GetQueryAPI.getQuery(TM52, result.get(i));
				String tempUser = jdbcTemplate.queryForObject(usrnameQuery, String.class);
				String queryForChk = GetQueryAPI.getQuery(TM71, pid, tempUser);
				if (jdbcTemplate.queryForObject(queryForChk, Integer.class) == 0) {
					String query = GetQueryAPI.getQuery(TM62, pid, tempUser, sdf.format(new Date()),
							session.getAttribute("loggedInUser").toString());
					jdbcTemplate.update(query);
					String message = "You have been assigned in " + getProjectName(pid) + " project on "
							+ sdf.format(new Date());
					if (checkMailNotificationTrigger(Project_Assigned))
						doSendEmail(getUserMailId(result.get(i)), "Project Assigned", message);
				} else {
					String query = GetQueryAPI.getQuery(TM72, sdf.format(new Date()),
							session.getAttribute("loggedInUser").toString(), pid, tempUser);
					jdbcTemplate.update(query);
					String message = "You have been re-assigned in " + getProjectName(pid) + " project on "
							+ sdf.format(new Date());
					if (checkMailNotificationTrigger(Project_Assigned))
						doSendEmail(getUserMailId(result.get(i)), "Project Assigned", message);
				}
			}
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public ProjectBean getProjectDetails(String pid) throws SQLException {
		ProjectBean projectBean1 = new ProjectBean();
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM53, pid);
			projectBean1 = jdbcTemplate.query(query, new ResultSetExtractor<ProjectBean>() {
				@Override
				public ProjectBean extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					ProjectBean projectBean = new ProjectBean();
					while (rsEffort.next()) {
						projectBean.setProjectName(rsEffort.getString(1));
						projectBean.setProjectDesc(rsEffort.getString(2));
						projectBean.setCreationDate(rsEffort.getString(3));
						projectBean.setProjectOwner(rsEffort.getString(4));
						projectBean.setProjectType(rsEffort.getString(5));
						projectBean.setProjectStatus(rsEffort.getString(6));
						projectBean.setStartDate(rsEffort.getString(7));
						projectBean.setEndDate(rsEffort.getString(8));
						projectBean.setTimesheetApprover(rsEffort.getString(9));
					}
					return projectBean;
				}
			});
			projectBean1.setProjectId(pid);
			projectBean1.setTaskList(getProjectAllTask(pid));
			projectBean1.setUserList(getProjectAssignedUsers(pid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectBean1;
	}

	private List<LoginBean> getProjectAssignedUsers(String pid) throws SQLException {
		List<LoginBean> userList = new ArrayList<LoginBean>();
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM54, pid);
			userList = jdbcTemplate.query(query, new ResultSetExtractor<List<LoginBean>>() {
				@Override
				public List<LoginBean> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<LoginBean> tempList = new ArrayList<LoginBean>();
					while (rsEffort.next()) {
						LoginBean tempUser = new LoginBean();
						tempUser.setUsername(rsEffort.getString(1));
						tempUser.setFirstName(rsEffort.getString(2));
						tempUser.setLastName(rsEffort.getString(3));
						tempUser.setRole(rsEffort.getString(4));
						tempUser.setPrimaryMail(rsEffort.getString(5));
						tempUser.setUserGroup(rsEffort.getString(6));
						tempUser.setUserStatus(rsEffort.getString(7));
						tempUser.setDob(rsEffort.getString(8));
						tempUser.setCreationDate(rsEffort.getString(9));
						tempUser.setLastModifiedDate(rsEffort.getString(10));
						tempList.add(tempUser);
					}
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	private List<TaskBean> getProjectAllTask(String pid) throws SQLException {
		String query = "";
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		try {
			query = GetQueryAPI.getQuery(TM43, pid);
			System.out.println(query);
			taskList = jdbcTemplate.query(query, new ResultSetExtractor<List<TaskBean>>() {
				@Override
				public List<TaskBean> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<TaskBean> tempList = new ArrayList<TaskBean>();
					while (rsEffort.next()) {
						if (rsEffort.getString(16) == null || rsEffort.getString(16).equalsIgnoreCase("Y")) {
							TaskBean tempTask = new TaskBean();
							tempTask.setTaskId(rsEffort.getString(1));
							tempTask.setTaskName(rsEffort.getString(2));
							tempTask.setTaskType(rsEffort.getString(3));
							tempTask.setTaskDesc(rsEffort.getString(4));
							tempTask.setCreationDate(rsEffort.getString(5));
							tempTask.setEffortEstimation(rsEffort.getString(6));
							tempTask.setPlannedStartDate(rsEffort.getString(7));
							tempTask.setActualStartDate(rsEffort.getString(8));

							tempTask.setPlannedEndDate(rsEffort.getString(9));
							tempTask.setActualEndDate(rsEffort.getString(10));
							tempTask.setTaskStatus(rsEffort.getString(11));
							tempTask.setLastModifiedBy(rsEffort.getString(12));
							tempTask.setLastModifiedDate(rsEffort.getString(13));
							tempTask.setCriticality(rsEffort.getString(14));
							if (rsEffort.getString(16) == null)
								tempTask.setAssignedTo("NA");
							else if (rsEffort.getString(16).equalsIgnoreCase("Y"))
								tempTask.setAssignedTo(rsEffort.getString(15));
							tempList.add(tempTask);
						}
					}
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

	public boolean updateTimesheetApproverRemark(HttpSession session, String username, String startDate, String endDate,
			String appRemark) throws ParseException, SQLException {
		boolean status = false;
		String query = "";

		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed1 = formats.parse(startDate);
		java.sql.Date startDate1 = new java.sql.Date(parsed1.getTime());

		Date parsed2 = formats.parse(endDate);
		java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());
		try {
			query = GetQueryAPI.getQuery(TM55, appRemark, sdf.format(new Date()),
					session.getAttribute("loggedInUser").toString(), username, startDate1.toString(),
					endDate1.toString());
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while updating timesheet approver remark for given date and user");
			e.printStackTrace();
		}
		return status;
	}

	public String doSendEmail(String recipientAddress, String subject, String message) {
		SimpleMailMessage email = new SimpleMailMessage();
		try {
			email.setFrom(ADMIN_MAIL);
			email.setTo(recipientAddress);
			email.setSubject(subject);
			email.setText(message);
			mailSender.send(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Result";
	}

	//Method for sending email
	public boolean forgotUserPwdSentLink(HttpSession session, String usermail) throws SQLException {
		boolean status = false;
		String query = "";
		try {
			//checked email exists in database or not
			int count = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM1, usermail), Integer.class);
			if (count == 0)
				return status;
			
			/*********************   Logic to create a random token which will be sent in email *************/
			
			String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();
			StringBuilder sb = new StringBuilder(11);
			for (int i = 0; i < 11; i++)
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
			String temp = new String(sb);
			
			//token getting saved in database
			query = GetQueryAPI.getQuery(TM161, usermail, temp);
			int insertCount = jdbcTemplate.update(query);
			
						
			
			
			if (insertCount != 0) {
				//link preparation
				String activationLink = protocol + "://" + domain + "/" + contextpath + "/setNewPasswordlogin?token="
						+ temp;
				//   http://localhost:8081/TimesheetManagement/setNewPasswordlogin?token=<the generated token> 
				
				
				Map<String, Object> mailContentMap = new HashMap<String, Object>();
				mailContentMap.put("usermail", usermail);
				mailContentMap.put("link", activationLink);
				final String toMailId = usermail;
				final Map<String, Object> mailContent = mailContentMap;
				Thread t = new Thread() {
					@Override
					public void run() {
						doSendTemplateEmail(toMailId, "eManager Password reset link.", mailContent,
								"passwordResetLinkVM.vm");
					}
				};
				t.start();
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	
	

	//Method for sending email
	public boolean forgotUserPwdSentLink2( String usermail) throws SQLException {
		boolean status = false;
		String query = "";
		try {
			//checked email exists in database or not
			int count = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM1, usermail), Integer.class);
			if (count == 0)
				return status;
			
			/*********************   Logic to create a random token which will be sent in email *************/
			
			String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();
			StringBuilder sb = new StringBuilder(11);
			for (int i = 0; i < 11; i++)
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
			String temp = new String(sb);
			
			//token getting saved in database
			query = GetQueryAPI.getQuery(TM161, usermail, temp);
			int insertCount = jdbcTemplate.update(query);
			if (insertCount != 0) {
				//link preparation
				String activationLink = protocol + "://" + domain + "/" + contextpath + "/setNewPasswordlogin?token="
						+ temp;
				//   http://localhost:8081/TimesheetManagement/setNewPasswordlogin?token=<the generated token> 
				Map<String, Object> mailContentMap = new HashMap<String, Object>();
				mailContentMap.put("usermail", usermail);
				mailContentMap.put("link", activationLink);
				final String toMailId = usermail;
				final Map<String, Object> mailContent = mailContentMap;
				Thread t = new Thread() {
					@Override
					public void run() {
						doSendTemplateEmail2(toMailId, "eManager Password reset link.", mailContent,
								"passwordResetLinkVM.vm");
					}
				};

				t.start();
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	
	
	
	/////////////////////////////////////////////////////////////////////////////////
	public boolean changeUserPwd(HttpSession session, String usermail, String password, String token)
			throws SQLException {
		boolean status = false;
		String query = "";
		String SALT = "supra-its-text";
		String changedPwdassword = SALT + password;
		String hashedPwdassword = generateHash(changedPwdassword);
		try {
			System.out.println(GetQueryAPI.getQuery(TM163, token, usermail));
	
			int validateToken = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM163, token, usermail),
					Integer.class);
			if (validateToken == 0)
				return false;

			query = GetQueryAPI.getQuery(TM56, hashedPwdassword, usermail);
			System.out.println(query);
			int rowsUpdated = jdbcTemplate.update(query);
			if (rowsUpdated != 0) {
				status = true;
				jdbcTemplate.update(GetQueryAPI.getQuery(TM164, token, usermail));
				String message = "Your password has been updated on eManager Portal.\n\nKindly login with new password.";
				if (checkMailNotificationTrigger(Forgot_Password))
					doSendEmail(usermail, "Password Updated.", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	////////////////////////////////////////////////////////////////////////

	public boolean forgotUserPwd(HttpSession session, String usermail) throws SQLException {
		boolean status = false;
		String query = "";
		String SALT = "supra-its-text";
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 9; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		String temp = new String(sb);
		String changedPwdassword = SALT + temp;
		String hashedPwdassword = generateHash(changedPwdassword);
		try {
			query = GetQueryAPI.getQuery(TM56, hashedPwdassword, usermail);
			// System.out.println(query);
			int rowsUpdated = jdbcTemplate.update(query);
			if (rowsUpdated != 0) {
				String message = "Password has been updated.\n\n\n New Password: " + temp;
				if (checkMailNotificationTrigger(Forgot_Password))
					doSendEmail(usermail, "Forgot Password.", message);
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hash.toString();
	}

	public boolean deleteTimeDataForGivenWeek(HttpSession session, String pid, String taskId, String startDate,
			String endDate) throws ParseException, SQLException {
		boolean status = false;
		String query = "";

		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");

		Date parsed1 = formats.parse(startDate);
		java.sql.Date startDate1 = new java.sql.Date(parsed1.getTime());

		Date parsed2 = formats.parse(endDate);
		java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());

		try {
			query = GetQueryAPI.getQuery(TM57, pid, taskId, session.getAttribute("loggedInUser").toString(),
					startDate1.toString(), endDate1.toString());
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while updating  for given date and user");
			e.printStackTrace();
		}
		return status;
	}

	public JSONArray getReporteeUserList(HttpSession session, String loggedInUser) throws SQLException {
		String query = "";
		JSONArray jasonUserData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM58, session.getAttribute("loggedInUser").toString());
			// System.out.println("user list query = "+query);
			List<JSONObject> managerList = new ArrayList<JSONObject>();
			managerList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
				@Override
				public List<JSONObject> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONObject> tempList = new ArrayList<JSONObject>();
					while (rsEffort.next()) {
						JSONObject temp = new JSONObject();
						try {
							temp.put("username", rsEffort.getString(1));
							temp.put("fullname", rsEffort.getString(2));
							tempList.add(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					return tempList;
				}
			});
			for (int i = 0; i < managerList.size(); i++) {
				jasonUserData.put(managerList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonUserData;
	}

	public JSONArray getListOfManagers(HttpSession session) throws SQLException {
		String query = "";
		JSONArray jasonProjectData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM59, session.getAttribute("loggedInUser").toString());
			List<JSONObject> managerList = new ArrayList<JSONObject>();
			managerList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
				@Override
				public List<JSONObject> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONObject> tempList = new ArrayList<JSONObject>();
					while (rsEffort.next()) {
						JSONObject temp = new JSONObject();
						try {
							temp.put("username", rsEffort.getString(1));
							temp.put("fullname", rsEffort.getString(2) + " " + rsEffort.getString(3));
							temp.put("usermail", rsEffort.getString(4));
							tempList.add(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					return tempList;
				}
			});
			for (int i = 0; i < managerList.size(); i++) {
				jasonProjectData.put(managerList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonProjectData;
	}

	public JSONArray getListOfHRManagers(HttpSession session) throws SQLException {
		String query = "";
		JSONArray jasonProjectData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM82);
			List<JSONObject> managerList = new ArrayList<JSONObject>();
			managerList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
				@Override
				public List<JSONObject> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONObject> tempList = new ArrayList<JSONObject>();
					while (rsEffort.next()) {
						JSONObject temp = new JSONObject();
						try {
							temp.put("username", rsEffort.getString(1));
							temp.put("fullname", rsEffort.getString(2) + " " + rsEffort.getString(3));
							temp.put("usermail", rsEffort.getString(4));
							tempList.add(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					return tempList;
				}
			});
			for (int i = 0; i < managerList.size(); i++) {
				jasonProjectData.put(managerList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonProjectData;
	}

	public boolean removeUserFromProject(HttpSession session, String pid, String username)
			throws ParseException, SQLException {
		boolean status = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM60, pid, username);
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while updating project mapping status for given date and user");
			e.printStackTrace();
		}
		return status;
	}

	public boolean removeTskFromProject(HttpSession session, String pid, String taskId)
			throws ParseException, SQLException {
		boolean status = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM12, taskId, pid);
			// System.out.println(query);
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while deleteting task");
			e.printStackTrace();
		}
		return status;
	}

	public boolean changeUserPassword(HttpSession session, String oldPwd, String newPwd)
			throws ParseException, SQLException {
		boolean status = false;
		String query = "";

		String SALT = "supra-its-text";
		String passwordOld = SALT + oldPwd;
		String changedPwdasswordNew = SALT + newPwd;
		String hashedPwdasswordOld = generateHash(passwordOld);
		String hashedPwdasswordNew = generateHash(changedPwdasswordNew);

		try {
			query = GetQueryAPI.getQuery(TM10, hashedPwdasswordNew, session.getAttribute("loggedInUser").toString(),
					hashedPwdasswordOld);
			// System.out.println(query);
			int updateStatus = jdbcTemplate.update(query);
			if (updateStatus != 0) {
				String message = "Password has been changed.\n\n\n New Password: " + newPwd;
				if (checkMailNotificationTrigger(Password_Change))
					doSendEmail(getUserMailId(session.getAttribute("loggedInUser").toString()),
							"Change Password Request.", message);
				status = true;
			}
		} catch (Exception e) {
			System.out.println("Error encountered while updating  user password");
			e.printStackTrace();
		}
		return status;
	}

	public boolean createNewUser(HttpSession session, String username, String fName, String lName, String email,
			String group, String role, String status, String reportee, String remark, String reporteeHR,
			String accessGroup, String policyGroup) {
		String query = "";
		String queryEmpTable = "";
		String queryEmpWorkInfoTable = "";
		String queryEmpAccessGroupEntry = "";
		String queryEmpAttendanceId = "";
		String queryEmpClientId = "";
		boolean statusC = false;
		try {
			query = GetQueryAPI.getQuery(TM13, username.toUpperCase(), "xxxxxx", fName, lName, role, email, remark,
					group, status, "1980-01-01", sdf.format(new Date()), sdf.format(new Date()), reportee, reporteeHR,
					policyGroup);
			queryEmpTable = GetQueryAPI.getQuery(TM152, username.toUpperCase(), fName, lName, email);
			queryEmpWorkInfoTable = GetQueryAPI.getQuery(TM153, username.toUpperCase(), role, reportee, reporteeHR,
					policyGroup);
			queryEmpAccessGroupEntry = GetQueryAPI.getQuery(TM115, accessGroup, username.toUpperCase());
			queryEmpClientId = GetQueryAPI.getQuery(TM214, username.toUpperCase(),
					String.valueOf(session.getAttribute("loggedInUserClientId")));
			String attendenceId = username.toUpperCase().replaceAll("[^0-9 ]", "").equals("") ? "000"
					: username.toUpperCase().replaceAll("[^0-9 ]", "");
			queryEmpAttendanceId = GetQueryAPI.getQuery(TM186, username.toUpperCase(), attendenceId, attendenceId);
			int updateCount = jdbcTemplate.update(query);
			if (updateCount != 0) {
				String query1 = GetQueryAPI.getQuery(TM64, ORGANIZATION_PROJECT, username.toUpperCase(),
						sdf.format(new Date()), ADMIN_USERNAME);
				System.out.println(query);
				System.out.println(queryEmpTable);
				System.out.println(queryEmpWorkInfoTable);
				System.out.println(queryEmpAccessGroupEntry);
				System.out.println(queryEmpAttendanceId);
				System.out.println(query1);
				int defaultAssignCount = jdbcTemplate.update(query1);
				if (defaultAssignCount != 0) {
					// Access_Group insert user
					jdbcTemplate.update(queryEmpAccessGroupEntry);
					jdbcTemplate.update(queryEmpAttendanceId);
					jdbcTemplate.update(queryEmpClientId);
					String body = "Following details updated in Supra TMS:\n\n Reporting Manager:" + ADMIN_USERNAME
							+ "\n\n Role: " + role + "\n\n Status: " + status;
					if (checkMailNotificationTrigger(User_Registration_By_Admin)) {
						// doSendEmail(getUserMailId(username), "Your details has been updated in
						// timesheet.", body);
						final String mailUserName = username.toUpperCase();
						final String mailUserFullName = getEmpName(mailUserName);
						final String mailUserRole = role;
						final String mailUserStatus = status;
						final String mailUserMail = email;
						final String mailUserReportingPerson = getEmpName(reportee) + " (" + reportee + ")";
						final String mailUserHRManager = getEmpName(reporteeHR) + " (" + reporteeHR + ")";
						Thread t = new Thread() {
							public void run() {
								String mailSubject = "Your details has been updated in Supra EMS.";
								Map<String, Object> mailContent = new HashMap<String, Object>();
								mailContent.put("userfullname", mailUserFullName);
								mailContent.put("username", mailUserName);
								mailContent.put("userrole", mailUserRole);
								mailContent.put("userstatus", mailUserStatus);
								mailContent.put("mailUserReportingPerson", mailUserReportingPerson);
								mailContent.put("mailUserHRManager", mailUserHRManager);
								doSendTemplateEmail(mailUserMail, mailSubject, mailContent, "newUserCreated.vm");
							}
						};
						t.start();
					}
				}
			}
			statusC = true;
			jdbcTemplate.update(queryEmpTable);
			jdbcTemplate.update(queryEmpWorkInfoTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusC;
	}

	public JSONArray getAllTaskTypeList(HttpSession session) throws SQLException {
		// TODO Auto-generated method stub
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM61);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("taskType", rsEffort.getString(1));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public JSONArray getAllRoleTypeList(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM42, "Role");
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("roleType", rsEffort.getString(1));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public JSONArray getCategoryBasedValues(HttpSession session, String category) {
		JSONArray values = new JSONArray();
		try {
			values = jdbcTemplate.query(GetQueryAPI.getQuery(TM42, category), new ResultSetExtractor<JSONArray>() {

				@Override
				public JSONArray extractData(ResultSet rs) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rs.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("values", rs.getString(1));
							temp.put(tempObj);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					return temp;
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}

	public JSONArray getListOfStatusType(HttpSession session) throws SQLException {
		String query = "";
		JSONArray jasonProjectData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM15);
			// System.out.println("user list query = "+query);
			List<JSONObject> managerList = new ArrayList<JSONObject>();
			managerList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
				@Override
				public List<JSONObject> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONObject> tempList = new ArrayList<JSONObject>();
					while (rsEffort.next()) {
						JSONObject temp = new JSONObject();
						try {
							temp.put("status", rsEffort.getString(1));
							tempList.add(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					return tempList;
				}
			});
			for (int i = 0; i < managerList.size(); i++) {
				jasonProjectData.put(managerList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonProjectData;
	}

	public JSONArray getListOfGroupType(HttpSession session) throws SQLException {
		String query = "";
		JSONArray jasonProjectData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM14);
			// System.out.println("user list query = "+query);
			List<JSONObject> managerList = new ArrayList<JSONObject>();
			managerList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
				@Override
				public List<JSONObject> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONObject> tempList = new ArrayList<JSONObject>();
					while (rsEffort.next()) {
						JSONObject temp = new JSONObject();
						try {
							temp.put("grpType", rsEffort.getString(2));
							temp.put("groupid", rsEffort.getString(1));
							temp.put("groupname", rsEffort.getString(2));
							temp.put("createdby", rsEffort.getString(3));
							temp.put("createdon", rsEffort.getString(4));
							temp.put("lastmodifiedon", rsEffort.getString(5));
							temp.put("createdbyfullname", rsEffort.getString(6));
							temp.put("createdbyusercode", rsEffort.getString(7));
							tempList.add(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					return tempList;
				}
			});
			for (int i = 0; i < managerList.size(); i++) {
				jasonProjectData.put(managerList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonProjectData;
	}

	public JSONArray getListOfGroupTypeForUserGroupModification(HttpSession session) throws SQLException {
		String query = "";
		JSONArray jasonProjectData = new JSONArray();
		try {
			query = GetQueryAPI.getQuery(TM42, "User Group");
			// System.out.println("user list query = "+query);
			List<JSONObject> managerList = new ArrayList<JSONObject>();
			managerList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>() {
				@Override
				public List<JSONObject> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONObject> tempList = new ArrayList<JSONObject>();
					while (rsEffort.next()) {
						JSONObject temp = new JSONObject();
						try {
							temp.put("usergroup", rsEffort.getString(1));
							tempList.add(temp);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					return tempList;
				}
			});
			for (int i = 0; i < managerList.size(); i++) {
				jasonProjectData.put(managerList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasonProjectData;
	}

	public JSONArray getAllPrjStsTypeList(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM41);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("prjStatusType", rsEffort.getString(1));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public JSONArray getAllCriticalityTypeList(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM102);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("criticalityType", rsEffort.getString(1));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public JSONArray getAllTaskStsTypeList(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM101);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("taskStatusType", rsEffort.getString(1));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public boolean updateProjectDetails(HttpSession session, String startDate, String endDate, String statusPrj,
			String pid, String timesheetApprover, String owner) throws ParseException, SQLException {
		boolean status = false;
		String query = "";

		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed1 = formats.parse(startDate);
		java.sql.Date startDate1 = new java.sql.Date(parsed1.getTime());

		Date parsed2 = formats.parse(endDate);
		java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());
		try {
			query = GetQueryAPI.getQuery(TM18, startDate1.toString(), endDate1.toString(), statusPrj, timesheetApprover,
					owner, pid);
			// System.out.println(query);
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while updating timesheet status for given date and user");
			e.printStackTrace();
		}
		return status;
	}

	public boolean editTaskDetails(HttpSession session, String taskId, String taskName, String taskDesc,
			String taskCriticality, String taskStartdate, String taskEnddate, String taskStatus, String assignedTo)
			throws ParseException, SQLException {
		boolean status = false;
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed1 = formats.parse(taskStartdate);
		java.sql.Date startDate1 = new java.sql.Date(parsed1.getTime());

		Date parsed2 = formats.parse(taskEnddate);
		java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());
		try {
			jdbcTemplate.update(GetQueryAPI.getQuery(TM19, taskName, taskDesc, taskCriticality, startDate1.toString(),
					endDate1.toString(), taskStatus, taskId));
			// System.out.println(GetQueryAPI.getQuery(TM166,assignedTo,taskId,session.getAttribute("loggedInUser").toString(),session.getAttribute("loggedInUser").toString(),assignedTo,session.getAttribute("loggedInUser").toString(),session.getAttribute("loggedInUser").toString()));
			if (!(assignedTo == null || assignedTo.equalsIgnoreCase("NA") || assignedTo.equals("")))
				jdbcTemplate.update(
						GetQueryAPI.getQuery(TM166, assignedTo, taskId, session.getAttribute("loggedInUser").toString(),
								session.getAttribute("loggedInUser").toString(), assignedTo,
								session.getAttribute("loggedInUser").toString(),
								session.getAttribute("loggedInUser").toString()));
			status = true;
		} catch (Exception e) {
			System.out.println("Error encountered while deleteting task");
			e.printStackTrace();
		}
		return status;
	}

	public JSONArray getAllProjectListOfUser(HttpSession session, String username) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM69, username);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("projectId", rsEffort.getString(1));
							jasonTimeEachData.put("projectName", rsEffort.getString(2));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public JSONArray getUsersActionHistor(HttpSession session, String firstDate, String lastDate, String username)
			throws SQLException, ParseException {
		String query = "";
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");

		Date parsed1 = formats.parse(firstDate);
		java.sql.Date startDate1 = new java.sql.Date(parsed1.getTime());

		Date parsed2 = formats.parse(lastDate);
		java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());
		List<JSONArray> userList = new ArrayList<JSONArray>();
		try {
			if (username.trim().length() > 0)
				query = GetQueryAPI.getQuery(TM73, username, startDate1.toString(), endDate1.toString());
			else
				query = GetQueryAPI.getQuery(TM73, session.getAttribute("loggedInUser").toString(),
						startDate1.toString(), endDate1.toString());
			// System.out.println("action history query"+query);
			userList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("action", rsEffort.getString(1));
							jasonTimeEachData.put("actionDate", rsEffort.getString(2));
							jasonTimeEachData.put("actionBy", rsEffort.getString(3));
							jasonTimeEachData.put("actionRemark", rsEffort.getString(4));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(userList.get(0));
		return userList.get(0);
	}

	public boolean insertIntoActionHistory(HttpSession session, String actionType, String username, String remark,
			String startDate, String endDate) throws SQLException, ParseException {
		boolean status = false;
		String query = "";
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");

		Date parsed1 = formats.parse(startDate);
		java.sql.Date startDate1 = new java.sql.Date(parsed1.getTime());

		Date parsed2 = formats.parse(endDate);
		java.sql.Date endDate1 = new java.sql.Date(parsed2.getTime());
		try {
			if (session.getAttribute("loggedInUserProxy").toString().equalsIgnoreCase(""))
				query = GetQueryAPI.getQuery(TM74, actionType, username,
						session.getAttribute("loggedInUser").toString(), remark, sdf.format(new Date()),
						endDate1.toString(), startDate1.toString());
			else
				query = GetQueryAPI.getQuery(TM74, actionType, username,
						session.getAttribute("loggedInUserProxy").toString(), remark, sdf.format(new Date()),
						endDate1.toString(), startDate1.toString());
			jdbcTemplate.update(query);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public JSONArray getEmailNotificatinStatus(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM78);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("eventId", rsEffort.getString(1));
							jasonTimeEachData.put("eventName", rsEffort.getString(2));
							jasonTimeEachData.put("status", rsEffort.getString(3));
							jasonTimeEachData.put("moduleName", rsEffort.getString(4));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public boolean updateNotification(HttpSession session, String id, boolean status)
			throws ParseException, SQLException {
		boolean status1 = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM79, String.valueOf(status), sdf.format(new Date()),
					session.getAttribute("loggedInUser").toString(), id);
			// System.out.println(query);
			jdbcTemplate.update(query);
			status1 = true;
		} catch (Exception e) {
			System.out.println("Error encountered while updating timesheet status for given date and user");
			e.printStackTrace();
		}
		return status1;
	}

	public JSONArray getUsersLimitWeekData(HttpSession session) throws SQLException {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try {
			query = GetQueryAPI.getQuery(TM81, session.getAttribute("loggedInUser").toString());
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>() {
				@Override
				public List<JSONArray> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
					List<JSONArray> tempList = new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
					while (rsEffort.next()) {
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("totaleffort", rsEffort.getString(1));
							jasonTimeEachData.put("week", rsEffort.getString(2));
							jasonTimeEachData.put("weekDate", rsEffort.getString(3));
							jasonTimeEachData.put("status", rsEffort.getString(4));
							if (rsEffort.getString(5) != null)
								jasonTimeEachData.put("lastmodifiedon", rsEffort.getString(5).substring(0, 10));
							else
								jasonTimeEachData.put("lastmodifiedon", "NA");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
					tempList.add(jasonProjectData);
					return tempList;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	public LoginBean setProxyUser(HttpSession session, String username, String usermail, LoginBean loginBean) {
		String query = "";
		Map<String, String> userDetailMap = new HashMap<String, String>();
		query = GetQueryAPI.getQuery(TM89, usermail);
		userDetailMap = jdbcTemplate.query(query, new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
				Map<String, String> userDetailMapTemp = new HashMap<String, String>();
				while (rsEffort.next()) {
					userDetailMapTemp.put("role", rsEffort.getString(1));
					userDetailMapTemp.put("usergroup", rsEffort.getString(2));
					userDetailMapTemp.put("userstatus", rsEffort.getString(3));
					userDetailMapTemp.put("username", rsEffort.getString(4));
					userDetailMapTemp.put("primaryemail", rsEffort.getString(5));
					userDetailMapTemp.put("firstname", rsEffort.getString(6));
					userDetailMapTemp.put("lastname", rsEffort.getString(7));
					userDetailMapTemp.put("reportingmanager", rsEffort.getString(8));
					userDetailMapTemp.put("hrmanager", rsEffort.getString(9));
					userDetailMapTemp.put("accountunit", rsEffort.getString(10));
					userDetailMapTemp.put("baselocation", rsEffort.getString(11));
					userDetailMapTemp.put("designation", rsEffort.getString(12));
					userDetailMapTemp.put("department", rsEffort.getString(13));
					userDetailMapTemp.put("policygroup", rsEffort.getString(14));
					userDetailMapTemp.put("usercode", rsEffort.getString(15));
				}
				return userDetailMapTemp;
			}
		});
		loginBean.setUsernameproxyid(session.getAttribute("loggedInUser").toString());
		loginBean.setFullnameproxyid(session.getAttribute("loggedInUserFullName").toString());
		loginBean.setRole(userDetailMap.get("role"));
		loginBean.setUserGroup(userDetailMap.get("usergroup"));
		loginBean.setUserStatus(userDetailMap.get("userstatus"));
		loginBean.setUsername(userDetailMap.get("username"));
		loginBean.setPrimaryMail(userDetailMap.get("primaryemail"));
		loginBean.setFirstName(userDetailMap.get("firstname"));
		loginBean.setLastName(userDetailMap.get("lastname"));
		loginBean.setReportingManager(userDetailMap.get("reportingmanager"));
		loginBean.setHrManager(userDetailMap.get("hrmanager"));
		loginBean.setAccountUnit(userDetailMap.get("accountunit"));
		loginBean.setBaseLocation(userDetailMap.get("baselocation"));
		loginBean.setDesignation(userDetailMap.get("designation"));
		loginBean.setEmpDepartment(userDetailMap.get("department"));
		loginBean.setPolicyGroup(userDetailMap.get("policygroup"));
		loginBean.setUserCode(userDetailMap.get("usercode"));
		return loginBean;
	}

	public LoginBean setMainUser(HttpSession session, LoginBean loginBean, String uName) {
		String query = "";
		Map<String, String> userDetailMap = new HashMap<String, String>();
		query = GetQueryAPI.getQuery(TM107, uName);
		userDetailMap = jdbcTemplate.query(query, new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rsEffort) throws SQLException, DataAccessException {
				Map<String, String> userDetailMapTemp = new HashMap<String, String>();
				while (rsEffort.next()) {
					userDetailMapTemp.put("role", rsEffort.getString(1));
					userDetailMapTemp.put("usergroup", rsEffort.getString(2));
					userDetailMapTemp.put("userstatus", rsEffort.getString(3));
					userDetailMapTemp.put("username", rsEffort.getString(4));
					userDetailMapTemp.put("primaryemail", rsEffort.getString(5));
					userDetailMapTemp.put("firstname", rsEffort.getString(6));
					userDetailMapTemp.put("lastname", rsEffort.getString(7));
					userDetailMapTemp.put("reportingmanager", rsEffort.getString(8));
					userDetailMapTemp.put("hrmanager", rsEffort.getString(9));
					userDetailMapTemp.put("accountunit", rsEffort.getString(10));
					userDetailMapTemp.put("baselocation", rsEffort.getString(11));
					userDetailMapTemp.put("designation", rsEffort.getString(12));
					userDetailMapTemp.put("department", rsEffort.getString(13));
					userDetailMapTemp.put("policygroup", rsEffort.getString(14));
					userDetailMapTemp.put("usercode", rsEffort.getString(15));
					userDetailMapTemp.put("clientid", rsEffort.getString(16));
				}
				return userDetailMapTemp;
			}
		});
		loginBean.setUsernameproxyid("");
		loginBean.setRole(userDetailMap.get("role"));
		loginBean.setUserGroup(userDetailMap.get("usergroup"));
		loginBean.setUserStatus(userDetailMap.get("userstatus"));
		loginBean.setUsername(userDetailMap.get("username"));
		loginBean.setPrimaryMail(userDetailMap.get("primaryemail"));
		loginBean.setFirstName(userDetailMap.get("firstname"));
		loginBean.setLastName(userDetailMap.get("lastname"));
		loginBean.setReportingManager(userDetailMap.get("reportingmanager"));
		loginBean.setHrManager(userDetailMap.get("hrmanager"));
		loginBean.setAccountUnit(userDetailMap.get("accountunit"));
		loginBean.setBaseLocation(userDetailMap.get("baselocation"));
		loginBean.setDesignation(userDetailMap.get("designation"));
		loginBean.setEmpDepartment(userDetailMap.get("department"));
		loginBean.setPolicyGroup(userDetailMap.get("policygroup"));
		loginBean.setUserCode(userDetailMap.get("usercode"));
		loginBean.setClientId(
				Integer.parseInt(userDetailMap.get("clientid") == null ? "1" : userDetailMap.get("clientid")));
		return loginBean;
	}

	public boolean insertFeedbackToTable(HttpSession session, String feedbackType, String feedBackDesc,
			String moduleType, String attachment) {
		boolean status = false;
		String feedbackId = "";
		String val = "" + ((int) (Math.random() * 9000) + 10000);
		feedbackId = feedbackType.substring(0, 5).toUpperCase() + val;
		feedBackDesc = feedBackDesc.replaceAll("\\W", "&");
		final String feedbackString = feedBackDesc.replaceAll("&", " ");
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM108, feedbackId, feedbackType, feedBackDesc,
					session.getAttribute("loggedInUser").toString(), sdf.format(new Date()), moduleType, attachment);
			jdbcTemplate.update(query);
			status = true;
			Thread t = new Thread() {
				public void run() {
					doSendEmail(FEEDBACK_MAILED_TO, "Issue raised", feedbackString);
				}
			};
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean removeAccessGroup(HttpSession session, String bucektId) {
		int prevEntryCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM112, bucektId), Integer.class);
		if (prevEntryCount > 0)
			return false;
		else {
			jdbcTemplate.update(GetQueryAPI.getQuery(TM109, bucektId));
			return true;
		}
	}

	public boolean updateAccessGrpName(HttpSession session, String bucketName, String bucektId) {
		int updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(TM110, bucketName, bucektId));
		if (updateCount == 1)
			return true;
		else
			return false;
	}

	public boolean createAccessGrp(HttpSession session, String bucketName) {
		int createCount = jdbcTemplate.update(GetQueryAPI.getQuery(TM111, bucketName,
				(String) session.getAttribute("loggedInUser"), sdf.format(new Date()).toString()));
		if (createCount == 1)
			return true;
		else
			return false;
	}

	public JSONArray getUserListForGroupAssignment(HttpSession session, String groupId, String addRemoveFlag) {
		JSONArray userListNotInGroup = new JSONArray();
		JSONArray userListInGroup = new JSONArray();
		String queryNotSelected = GetQueryAPI.getQuery(TM113, groupId);
		String querySelected = GetQueryAPI.getQuery(TM114, groupId);
		/*
		 * if(addRemoveFlag.equalsIgnoreCase("add")){ query =
		 * GetQueryAPI.getQuery(TM113, groupId); }else{ query =
		 * GetQueryAPI.getQuery(TM114, groupId); }
		 */
		// System.out.println(query);
		userListNotInGroup = jdbcTemplate.query(queryNotSelected, new ResultSetExtractor<JSONArray>() {

			@Override
			public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
				JSONArray temp = new JSONArray();
				while (rsUserSet.next()) {
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("username", rsUserSet.getString(1));
						tempObj.put("userfullname", rsUserSet.getString(2));
						tempObj.put("usermail", rsUserSet.getString(3));
						tempObj.put("existingInGroupFlag", "N");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
		userListInGroup = jdbcTemplate.query(querySelected, new ResultSetExtractor<JSONArray>() {

			@Override
			public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
				JSONArray temp = new JSONArray();
				while (rsUserSet.next()) {
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("username", rsUserSet.getString(1));
						tempObj.put("userfullname", rsUserSet.getString(2));
						tempObj.put("usermail", rsUserSet.getString(3));
						tempObj.put("existingInGroupFlag", "Y");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
		try {
			for (int i = 0; i < userListInGroup.length(); i++)
				userListNotInGroup.put(userListInGroup.get(i));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userListNotInGroup;
	}

	public boolean addRemoveUsersFromGroup(HttpSession session, String groupId, List<String> result,
			String addRemoveFlag) {
		boolean status = false;
		try {
			String query = "";
			System.out.println(result);
			jdbcTemplate.update(GetQueryAPI.getQuery(TM116, groupId));
			if (result.size() > 0) {
				Iterator<String> itrUser = result.iterator();
				while (itrUser.hasNext()) {
					query = GetQueryAPI.getQuery(TM115, groupId, itrUser.next());
					int updateCount = jdbcTemplate.update(query);
					if (updateCount != 0)
						status = true;
				}
			}
			status = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return status;
	}

	public JSONArray getListOfFunctionAccess(HttpSession session, String groupId) {
		JSONArray functionList = new JSONArray();
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM117, groupId);
			functionList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {

				@Override
				public JSONArray extractData(ResultSet rsfunctionSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsfunctionSet.next()) {
						JSONObject jsObj = new JSONObject();
						try {
							jsObj.put("functionid", rsfunctionSet.getString(3));
							jsObj.put("functionname", rsfunctionSet.getString(4));
							jsObj.put("moduleid", rsfunctionSet.getString(1));
							jsObj.put("modulename", rsfunctionSet.getString(2));
							String tempStatString = rsfunctionSet.getString(5);
							if (null == tempStatString) {
								jsObj.put("status", "false");
							} else
								jsObj.put("status", "true");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(jsObj);
					}
					return temp;
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return functionList;
	}

	public boolean updatefunctionAcees(HttpSession session, String functionId, String groupId, boolean status) {
		boolean updateStatus = false;
		String query = "";
		try {
			if (status)
				query = GetQueryAPI.getQuery(TM119, groupId, functionId);
			else
				query = GetQueryAPI.getQuery(TM118, groupId, functionId);
			int updateCount = jdbcTemplate.update(query);
			if (updateCount != 0)
				updateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;
	}

	@SuppressWarnings("unchecked")
	public JSONArray getPendingCountParam(HttpSession session) {
		JSONArray jsonCount = new JSONArray();
		try {
			JSONObject tempObj = new JSONObject();
			int approvalCount = 0;
			int leaveApprovalCount = 0;
			int expensePendingCount = 0;
			if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN)) {
				approvalCount = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(TM128, session.getAttribute("loggedInUserPolicyGroup").toString()),
						Integer.class);
				leaveApprovalCount = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(LMS24, session.getAttribute("loggedInUserPolicyGroup").toString()),
						Integer.class);
				expensePendingCount = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(RMS34, session.getAttribute("loggedInUser").toString()), Integer.class);
			} else {
				approvalCount = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(TM127, session.getAttribute("loggedInUser").toString()), Integer.class);
				leaveApprovalCount = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(LMS25, session.getAttribute("loggedInUser").toString()), Integer.class);
				expensePendingCount = jdbcTemplate.queryForObject(
						GetQueryAPI.getQuery(RMS34, session.getAttribute("loggedInUser").toString()), Integer.class);
			}
			if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(FINANACE)) {
				expensePendingCount = expensePendingCount
						+ jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS62, FINANACE), Integer.class);
			}
			if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(VP_GROUP)) {
				expensePendingCount = expensePendingCount
						+ jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS62, VP_GROUP), Integer.class);
			}
			tempObj.put("approvalPendingCount", approvalCount);
			tempObj.put("leaveApprovalCount", leaveApprovalCount);
			tempObj.put("expensePendingCount", expensePendingCount);
			jsonCount.put(tempObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonCount;
	}

	public boolean approveTMSStatus(HttpSession session, List<String> reqArray) {
		boolean status = false;
		try {
			Iterator<String> itrList = reqArray.iterator();
			while (itrList.hasNext()) {
				String temp = itrList.next();
				String startDate = temp.substring(0, 10);
				String endDate = temp.substring(11, 21);
				String username = temp.substring(22);
				// System.out.println(username+startDate+endDate);
				updateTimesheetStatusUser(session, startDate, endDate, true, username, "");
			}
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public EmployeeBean fetchEmployeeDetails(HttpSession session, EmployeeBean employeeBean, String uid) {
		if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN)
				|| ((List<String>) session.getAttribute("loggedInUserGroups")).contains(HR)
				|| ((List<String>) session.getAttribute("loggedInUserGroups")).contains(CANADA_ADMIN)
				|| ((List<String>) session.getAttribute("loggedInUserGroups")).contains(CANADA_HR)) {
		} else {
			if (!(uid.equalsIgnoreCase(String.valueOf(session.getAttribute("loggedInUser")))))
				uid = String.valueOf(session.getAttribute("loggedInUser"));
		}
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM130, uid);
			// System.out.println(query);
			employeeBean = jdbcTemplate.query(query, new ResultSetExtractor<EmployeeBean>() {
				@Override
				public EmployeeBean extractData(final ResultSet rs) throws SQLException, DataAccessException {
					while (rs.next()) {
						tempEmployeeBean.setUserName(rs.getString(1));
						tempEmployeeBean.setSalutation(rs.getString(2));
						tempEmployeeBean.setFirstName(rs.getString(3));
						if (null == rs.getString(4))
							tempEmployeeBean.setMiddleName("");
						else
							tempEmployeeBean.setMiddleName(rs.getString(4));
						tempEmployeeBean.setLastName(rs.getString(5));
						tempEmployeeBean.setMotherName(rs.getString(6));
						tempEmployeeBean.setFatherName(rs.getString(7));
						tempEmployeeBean.setMotherTongue(rs.getString(8));
						tempEmployeeBean.setGender(rs.getString(9));
						tempEmployeeBean.setDob(rs.getString(10));
						tempEmployeeBean.setAge(rs.getString(11));
						tempEmployeeBean.setAadharNo(rs.getString(12));
						tempEmployeeBean.setPancardNo(rs.getString(13));
						tempEmployeeBean.setPassportNo(rs.getString(14));
						tempEmployeeBean.setBloodGroup(rs.getString(15));
						tempEmployeeBean.setMaritalStatus(rs.getString(16));
						tempEmployeeBean.setSpouseName(rs.getString(17));
						tempEmployeeBean.setPrimaryPhoneNum(rs.getString(18));
						tempEmployeeBean.setSecondaryPhoneNum(rs.getString(19));
						tempEmployeeBean.setEmergencyPhoneNum(rs.getString(20));
						tempEmployeeBean.setPrimaryEmailId(rs.getString(21));
						tempEmployeeBean.setSecondaryEmailId(rs.getString(22));
						tempEmployeeBean.setWebOrLinkedInLink(rs.getString(23));

						tempEmployeeBean.setBaseLocation(rs.getString(24));
						tempEmployeeBean.setDepartment(rs.getString(25));
						tempEmployeeBean.setDesignation(rs.getString(26));
						tempEmployeeBean.setRole(rs.getString(27));
						tempEmployeeBean.setReportingPerson(rs.getString(28));
						tempEmployeeBean.setHrManager(rs.getString(29));
						tempEmployeeBean.setAccountUnit(rs.getString(30));
						tempEmployeeBean.setJoiningDate(rs.getString(31));
						tempEmployeeBean.setLastWorkingDate(rs.getString(32));
						tempEmployeeBean.setPolicyGroupName(rs.getString(33));
						tempEmployeeBean.setAttendanceId(rs.getString(34) == null ? "NA" : rs.getString(34));
						tempEmployeeBean.setReportingPersonFullName(rs.getString(35) == null ? "NA" : rs.getString(35));
						tempEmployeeBean.setHrManagerFullName(rs.getString(36) == null ? "NA" : rs.getString(36));
						tempEmployeeBean.setUserCode(rs.getString(37) == null ? "NA" : rs.getString(37));
						Thread t = new Thread() {
							@Override
							public void run() {
								try {
									tempEmployeeBean.setEmpAccountList(fetchEmpAccList(rs.getString(1)));
								} catch (Exception e) {
								}
							}
						};
						Thread t1 = new Thread() {
							@Override
							public void run() {
								try {
									tempEmployeeBean.setEmpEducationList(fetchEmpEduList(rs.getString(1)));
								} catch (Exception e) {
								}
							}
						};
						Thread t2 = new Thread() {
							@Override
							public void run() {
								try {
									tempEmployeeBean.setEmpSkilltList(fetchEmpSkilList(rs.getString(1)));
								} catch (Exception e) {
								}
							}
						};
						Thread t3 = new Thread() {
							@Override
							public void run() {
								try {
									tempEmployeeBean.setEmpWorkHistoryList(fetchEmpWorkList(rs.getString(1)));
								} catch (Exception e) {
								}
							}
						};
						Thread t4 = new Thread() {
							@Override
							public void run() {
								try {
									fetchEmpAddressses(rs.getString(1));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						};
						t.start();
						t1.start();
						t2.start();
						t3.start();
						t4.start();
						try {
							t.join();
							t1.join();
							t2.join();
							t3.join();
							t4.join();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return tempEmployeeBean;
				}

				private void fetchEmpAddressses(String userName) {
					String query = "";
					try {
						query = GetQueryAPI.getQuery(TM132, userName);
						jdbcTemplate.query(query, new ResultSetExtractor<ResultSet>() {
							@Override
							public ResultSet extractData(ResultSet rs) throws SQLException, DataAccessException {
								while (rs.next()) {
									if (rs.getString(1).equalsIgnoreCase("Home") && null != rs.getString(1)) {
										tempEmployeeBean.setPermanentAddress(rs.getString(2));
										tempEmployeeBean.setPermanentAddressCountry(rs.getString(3));
										tempEmployeeBean.setPermanentAddressState(rs.getString(4));
										tempEmployeeBean.setPermanentAddressCity(rs.getString(5));
										tempEmployeeBean.setPermanentAddressZipCode(rs.getString(6));
									}
									if (rs.getString(1).equalsIgnoreCase("Communication") && null != rs.getString(1)) {
										tempEmployeeBean.setCommunicationAddress(rs.getString(2));
										tempEmployeeBean.setCommunicationAddressCountry(rs.getString(3));
										tempEmployeeBean.setCommunicationAddressState(rs.getString(4));
										tempEmployeeBean.setCommunicationAddressCity(rs.getString(5));
										tempEmployeeBean.setCommunicationAddressZipCode(rs.getString(6));
									}
									if (rs.getString(1).equalsIgnoreCase("Office") && null != rs.getString(1)) {
										tempEmployeeBean.setOfficeAddress(rs.getString(2));
										tempEmployeeBean.setOfficeAddressCountry(rs.getString(3));
										tempEmployeeBean.setOfficeAddressState(rs.getString(4));
										tempEmployeeBean.setOfficeAddressCity(rs.getString(5));
										tempEmployeeBean.setOfficeAddressZipCode(rs.getString(6));
									}
									if (rs.getString(1).equalsIgnoreCase("Client") && null != rs.getString(1)) {
										tempEmployeeBean.setClientOfficeAddress(rs.getString(2));
										tempEmployeeBean.setClientOfficeAddressCountry(rs.getString(3));
										tempEmployeeBean.setClientOfficeAddressState(rs.getString(4));
										tempEmployeeBean.setClientOfficeAddressCity(rs.getString(5));
										tempEmployeeBean.setClientOfficeAddressZipCode(rs.getString(6));
									}
								}
								return rs;
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				private List<EmployeeWorkBean> fetchEmpWorkList(String userName) {
					List<EmployeeWorkBean> empWorkList = new LinkedList<EmployeeWorkBean>();
					String query = "";
					try {
						query = GetQueryAPI.getQuery(TM133, userName);
						empWorkList = jdbcTemplate.query(query, new ResultSetExtractor<List<EmployeeWorkBean>>() {
							@Override
							public List<EmployeeWorkBean> extractData(ResultSet rs)
									throws SQLException, DataAccessException {
								List<EmployeeWorkBean> temp = new LinkedList<EmployeeWorkBean>();
								while (rs.next()) {
									EmployeeWorkBean tempEmployeeWorkBean = new EmployeeWorkBean();
									tempEmployeeWorkBean.setCompanyId(rs.getString(1));
									tempEmployeeWorkBean.setCompanyName(rs.getString(2));
									tempEmployeeWorkBean.setLastDesignation(rs.getString(3));
									tempEmployeeWorkBean.setDurationMonth(rs.getString(4));
									tempEmployeeWorkBean.setEndDate(rs.getString(5));
									tempEmployeeWorkBean.setStartDate(rs.getString(6));

									temp.add(tempEmployeeWorkBean);
								}
								return temp;
							}

						});
					} catch (Exception e) {
						e.printStackTrace();
					}
					return empWorkList;
				}

				private List<EmployeeSkillBean> fetchEmpSkilList(String userName) {
					List<EmployeeSkillBean> empSkillList = new LinkedList<EmployeeSkillBean>();
					String query = "";
					try {
						query = GetQueryAPI.getQuery(TM134, userName);
						empSkillList = jdbcTemplate.query(query, new ResultSetExtractor<List<EmployeeSkillBean>>() {

							@Override
							public List<EmployeeSkillBean> extractData(ResultSet rs)
									throws SQLException, DataAccessException {
								List<EmployeeSkillBean> temp = new LinkedList<EmployeeSkillBean>();
								while (rs.next()) {
									EmployeeSkillBean tempEmployeeSkillBean = new EmployeeSkillBean();
									tempEmployeeSkillBean.setSkillId(rs.getString(1));
									tempEmployeeSkillBean.setSkillName(rs.getString(2));
									tempEmployeeSkillBean.setSkillType(rs.getString(3));
									tempEmployeeSkillBean.setVersion(rs.getString(4));
									tempEmployeeSkillBean.setExperienceMonth(rs.getString(5));

									temp.add(tempEmployeeSkillBean);
								}
								return temp;
							}

						});
					} catch (Exception e) {
						e.printStackTrace();
					}
					return empSkillList;
				}

				private List<EmployeeEducationBean> fetchEmpEduList(String userName) {
					List<EmployeeEducationBean> empEduList = new LinkedList<EmployeeEducationBean>();
					String query = "";
					try {
						query = GetQueryAPI.getQuery(TM135, userName);
						empEduList = jdbcTemplate.query(query, new ResultSetExtractor<List<EmployeeEducationBean>>() {

							@Override
							public List<EmployeeEducationBean> extractData(ResultSet rs)
									throws SQLException, DataAccessException {
								List<EmployeeEducationBean> temp = new LinkedList<EmployeeEducationBean>();
								while (rs.next()) {
									EmployeeEducationBean tempEmployeeEducationBean = new EmployeeEducationBean();
									tempEmployeeEducationBean.setEduType(rs.getString(1));
									tempEmployeeEducationBean.setQualification(rs.getString(2));
									tempEmployeeEducationBean.setSchoolCollegeName(rs.getString(3));
									tempEmployeeEducationBean.setScore(rs.getString(4));
									tempEmployeeEducationBean.setStream(rs.getString(5));
									tempEmployeeEducationBean.setUniversityBoard(rs.getString(6));
									tempEmployeeEducationBean.setYear(rs.getString(7));
									tempEmployeeEducationBean.setQualificationId(rs.getString(8));

									temp.add(tempEmployeeEducationBean);
								}
								return temp;
							}

						});
					} catch (Exception e) {
						e.printStackTrace();
					}
					return empEduList;
				}

				private List<EmployeeAccountBean> fetchEmpAccList(String userName) {
					List<EmployeeAccountBean> empAccList = new LinkedList<EmployeeAccountBean>();
					String query = "";
					try {
						query = GetQueryAPI.getQuery(TM136, userName);
						empAccList = jdbcTemplate.query(query, new ResultSetExtractor<List<EmployeeAccountBean>>() {

							@Override
							public List<EmployeeAccountBean> extractData(ResultSet rs)
									throws SQLException, DataAccessException {
								List<EmployeeAccountBean> temp = new LinkedList<EmployeeAccountBean>();
								while (rs.next()) {
									EmployeeAccountBean tempEmployeeAccountBean = new EmployeeAccountBean();
									tempEmployeeAccountBean.setAccountId(rs.getString(1));
									tempEmployeeAccountBean.setAccountNumber(rs.getString(2));
									tempEmployeeAccountBean.setAccountType(rs.getString(3));
									tempEmployeeAccountBean.setAccountHolderName(rs.getString(4));
									tempEmployeeAccountBean.setBankName(rs.getString(5));
									tempEmployeeAccountBean.setBranchCode(rs.getString(6));
									tempEmployeeAccountBean.setIfscCode(rs.getString(7));
									tempEmployeeAccountBean.setMicrCode(rs.getString(8));
									tempEmployeeAccountBean.setPrimaryAccFlag(rs.getString(9));

									temp.add(tempEmployeeAccountBean);
								}
								return temp;
							}

						});
					} catch (Exception e) {
						e.printStackTrace();
					}
					return empAccList;
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeBean;
	}

	
	
	public String doSendTemplateEmail(
			String recipientAddress,
			String subject, 
			Map<String, Object> model,
			String templateName) {
		// SimpleMailMessage email = new SimpleMailMessage();
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
		try {
			email.setFrom(ADMIN_MAIL);
			if (domain.equalsIgnoreCase("timesheet.supraes.com"))
				email.setTo(recipientAddress);
			else
			email.setTo(TEST_DELIVERY_MAIL_TO);
			email.setSubject(subject);
			String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model);
			email.setText(message, true);
			this.mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Result"; 
	}

	
	//////////////////////Mobile API///////////////////////////
	public String doSendTemplateEmail2(
			String recipientAddress,
			String subject, 
			Map<String, Object> model,
			String templateName) {
		// SimpleMailMessage email = new SimpleMailMessage();
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
		try {
			email.setFrom(ADMIN_MAIL);
			if (domain.equalsIgnoreCase("timesheet.supraes.com"))
				email.setTo(recipientAddress);
			else
				email.setTo(TEST_DELIVERY_MAIL_TO2);
			email.setSubject(subject);
			String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model);
			email.setText(message, true);
			this.mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Result"; 
	}

	
	
	
	public boolean addUserBankAccount(HttpSession session, String bankName, String accType, String accountNumber,
			String ifscCode, String primaryFlag, String accHolderName, String username) {
		boolean status = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM146, username, ifscCode);

			if (jdbcTemplate.queryForObject(query, Integer.class) == 0) {
				query = GetQueryAPI.getQuery(TM137, username, bankName, ifscCode, accType, "", "", primaryFlag,
						accountNumber, accHolderName);
				int insertStatus = jdbcTemplate.update(query);
				if (insertStatus != 0) {
					if (primaryFlag.equalsIgnoreCase("Y")) {
						query = GetQueryAPI.getQuery(TM145, username, ifscCode);
						jdbcTemplate.update(query);
					}
					status = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean addUserPastWorkInfo(HttpSession session, String companyName, String lastDesignation,
			String experience, String startDate, String endDate, String username) {
		boolean status = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM138, companyName, lastDesignation, experience, startDate, endDate, username);
			int insertStatus = jdbcTemplate.update(query);
			if (insertStatus != 0)
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean addUserEducation(HttpSession session, String qualification, String collegeName, String boardName,
			String streamName, String year, String username, String score) {
		boolean status = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM139, qualification, "", username, collegeName, boardName, streamName, year,
					score);
			int insertStatus = jdbcTemplate.update(query);
			if (insertStatus != 0)
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean addUserSkill(HttpSession session, String skillName, String skillType, String version,
			String expMonth, String username) {
		boolean status = false;
		String query = "";
		try {
			query = GetQueryAPI.getQuery(TM140, username, skillName, skillType, version, expMonth);
			int insertStatus = jdbcTemplate.update(query);
			if (insertStatus != 0)
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean updateUserInformation(HttpSession session, EmployeeBean employeeBean) {
		boolean status = false;
		String query = "";
		String query1 = "";
		int statusUpdate = 0;
		String username = employeeBean.getUserName();
		// System.out.println(username.equalsIgnoreCase(String.valueOf(session.getAttribute("loggedInUser"))));
		System.out.println("Username" + username);
		// System.out.println(String.valueOf(session.getAttribute("loggedInUser")));
		if (((List<String>) session.getAttribute("loggedInUserGroups")).contains(ADMIN)
				|| ((List<String>) session.getAttribute("loggedInUserGroups")).contains(HR)) {
		} else {
			if (!(username.equalsIgnoreCase(String.valueOf(session.getAttribute("loggedInUser")))))
				return false;
		}
		try {
			// SUPRA_EMP table update query
			if (employeeBean.getFirstName() != null) {
				query = GetQueryAPI.getQuery(TM141, employeeBean.getSalutation(), employeeBean.getFirstName(),
						employeeBean.getMiddleName(), employeeBean.getLastName(), employeeBean.getMotherName(),
						employeeBean.getFatherName(), employeeBean.getMotherTongue(), employeeBean.getGender(),
						employeeBean.getDob(), employeeBean.getAge(), employeeBean.getAadharNo(),
						employeeBean.getPancardNo(), employeeBean.getPassportNo(), employeeBean.getBloodGroup(),
						employeeBean.getMaritalStatus(), employeeBean.getSpouseName(),
						employeeBean.getPrimaryPhoneNum(), employeeBean.getSecondaryPhoneNum(),
						employeeBean.getEmergencyPhoneNum(), employeeBean.getPrimaryEmailId(),
						employeeBean.getSecondaryEmailId(), employeeBean.getWebOrLinkedInLink(),
						employeeBean.getJoiningDate(), username);
				query1 = GetQueryAPI.getQuery(TM192, employeeBean.getFirstName(), employeeBean.getLastName(),
						employeeBean.getRole(), employeeBean.getPrimaryEmailId(), employeeBean.getDob(),
						employeeBean.getReportingPerson(), employeeBean.getHrManager(), employeeBean.getJoiningDate(),
						employeeBean.getLastWorkingDate(), employeeBean.getBaseLocation(),
						employeeBean.getDesignation(), employeeBean.getAccountUnit(), employeeBean.getDepartment(),
						employeeBean.getPolicyGroupName(), username);
			} else {
				query = GetQueryAPI.getQuery(TM159, employeeBean.getMiddleName(), employeeBean.getMotherName(),
						employeeBean.getFatherName(), employeeBean.getMotherTongue(), employeeBean.getGender(),
						employeeBean.getDob(), employeeBean.getAge(), employeeBean.getAadharNo(),
						employeeBean.getPancardNo(), employeeBean.getPassportNo(), employeeBean.getBloodGroup(),
						employeeBean.getMaritalStatus(), employeeBean.getSpouseName(),
						employeeBean.getPrimaryPhoneNum(), employeeBean.getSecondaryPhoneNum(),
						employeeBean.getEmergencyPhoneNum(), employeeBean.getSecondaryEmailId(),
						employeeBean.getWebOrLinkedInLink(), employeeBean.getJoiningDate(), username);
				query1 = GetQueryAPI.getQuery(TM192, employeeBean.getFirstName(), employeeBean.getLastName(),
						employeeBean.getRole(), employeeBean.getPrimaryEmailId(), employeeBean.getDob(),
						employeeBean.getReportingPerson(), employeeBean.getHrManager(), employeeBean.getJoiningDate(),
						employeeBean.getLastWorkingDate(), employeeBean.getBaseLocation(),
						employeeBean.getDesignation(), employeeBean.getAccountUnit(), employeeBean.getDepartment(),
						employeeBean.getPolicyGroupName(), username);
			}
			System.out.println(query1);
			statusUpdate = jdbcTemplate.update(query);
			jdbcTemplate.update(query1);
			if (statusUpdate != 0) {
				// SUPRA_EMP_CURRENT_WORK_INFO table update query
				if (employeeBean.getFirstName() != null) {
					query = GetQueryAPI.getQuery(TM142, employeeBean.getBaseLocation(), employeeBean.getDepartment(),
							employeeBean.getDesignation(), employeeBean.getRole(), employeeBean.getReportingPerson(),
							employeeBean.getHrManager(), employeeBean.getAccountUnit(),
							employeeBean.getPolicyGroupName(), currentDate,
							(String) session.getAttribute("loggedInUser"), username);
					jdbcTemplate.update(GetQueryAPI.getQuery(TM158, employeeBean.getPolicyGroupName(), username));
					updateStatusUser(session, username, "", employeeBean.getRole(), "Active", "NA",
							employeeBean.getReportingPerson(), employeeBean.getHrManager());
				}
				statusUpdate = jdbcTemplate.update(query);
				// Updating Permanent Address
				statusUpdate = jdbcTemplate.update(GetQueryAPI.getQuery(TM143, username,
						employeeBean.getPermanentAddress(), "Home", employeeBean.getPermanentAddressCountry(),
						employeeBean.getPermanentAddressState(), employeeBean.getPermanentAddressCity(),
						employeeBean.getPermanentAddressZipCode(), employeeBean.getPermanentAddress(),
						employeeBean.getPermanentAddressCountry(), employeeBean.getPermanentAddressState(),
						employeeBean.getPermanentAddressCity(), employeeBean.getPermanentAddressZipCode()));
				// Updating Communication Address
				statusUpdate = jdbcTemplate.update(GetQueryAPI.getQuery(TM143, username,
						employeeBean.getCommunicationAddress(), "Communication",
						employeeBean.getCommunicationAddressCountry(), employeeBean.getCommunicationAddressState(),
						employeeBean.getCommunicationAddressCity(), employeeBean.getCommunicationAddressZipCode(),
						employeeBean.getCommunicationAddress(), employeeBean.getCommunicationAddressCountry(),
						employeeBean.getCommunicationAddressState(), employeeBean.getCommunicationAddressCity(),
						employeeBean.getCommunicationAddressZipCode()));
				// Updating Base Office Address
				statusUpdate = jdbcTemplate.update(GetQueryAPI.getQuery(TM143, username,
						employeeBean.getOfficeAddress(), "Office", employeeBean.getOfficeAddressCountry(),
						employeeBean.getOfficeAddressState(), employeeBean.getOfficeAddressCity(),
						employeeBean.getOfficeAddressZipCode(), employeeBean.getOfficeAddress(),
						employeeBean.getOfficeAddressCountry(), employeeBean.getOfficeAddressState(),
						employeeBean.getOfficeAddressCity(), employeeBean.getOfficeAddressZipCode()));
				// Updating Client Office Address
				statusUpdate = jdbcTemplate.update(GetQueryAPI.getQuery(TM143, username,
						employeeBean.getClientOfficeAddress(), "Client", employeeBean.getClientOfficeAddressCountry(),
						employeeBean.getClientOfficeAddressState(), employeeBean.getClientOfficeAddressCity(),
						employeeBean.getClientOfficeAddressZipCode(), employeeBean.getClientOfficeAddress(),
						employeeBean.getClientOfficeAddressCountry(), employeeBean.getClientOfficeAddressState(),
						employeeBean.getClientOfficeAddressCity(), employeeBean.getClientOfficeAddressZipCode()));
				// Updating user biomatric id
				statusUpdate = jdbcTemplate.update(GetQueryAPI.getQuery(TM186, username,
						(employeeBean.getAttendanceId() == null || employeeBean.getAttendanceId().equalsIgnoreCase(""))
								? "NA"
								: employeeBean.getAttendanceId(),
						(employeeBean.getAttendanceId() == null || employeeBean.getAttendanceId().equalsIgnoreCase(""))
								? "NA"
								: employeeBean.getAttendanceId()));
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteAccountRow(HttpSession session, String accNumber) {
		if (jdbcTemplate.update(GetQueryAPI.getQuery(TM147, accNumber)) != 0)
			return true;
		else
			return false;
	}

	public boolean deleteEducationRow(HttpSession session, String qualificationId) {
		if (jdbcTemplate.update(GetQueryAPI.getQuery(TM149, qualificationId)) != 0)
			return true;
		else
			return false;
	}

	public boolean deleteSkillRow(HttpSession session, String skillId) {
		if (jdbcTemplate.update(GetQueryAPI.getQuery(TM148, skillId)) != 0)
			return true;
		else
			return false;
	}

	public boolean deleteEmployementRow(HttpSession session, String compId) {
		if (jdbcTemplate.update(GetQueryAPI.getQuery(TM150, compId)) != 0)
			return true;
		else
			return false;
	}

	public JSONArray getTMSUserData(HttpSession session, String year, String month)
			throws JSONException, ParseException {
		JSONArray userData = new JSONArray();
		String firstDate = year + "-" + month + "-01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate = dateFormat.parse(firstDate);
		Calendar c = Calendar.getInstance();
		c.setTime(convertedDate);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDate = dateFormat.format(c.getTime());
		System.out.println(firstDate + "=====" + lastDate);
		try {
			userData = jdbcTemplate.query(
					GetQueryAPI.getQuery(TM151, session.getAttribute("loggedInUser").toString(), firstDate, lastDate),
					new ResultSetExtractor<JSONArray>() {
						@Override
						public JSONArray extractData(ResultSet rs) throws SQLException, DataAccessException {
							JSONArray temp = new JSONArray();
							while (rs.next()) {
								JSONObject tempObj = new JSONObject();
								try {
									tempObj.put("projectName", rs.getString(1));
									tempObj.put("taskName", rs.getString(2));
									if (null == rs.getString(3) || "".equals(rs.getString(3)))
										tempObj.put("effortInHours", "0");
									else
										tempObj.put("effortInHours", rs.getString(3));
									if (null == rs.getString(4) || "".equals(rs.getString(4)))
										tempObj.put("remark", "Not Available");
									else
										tempObj.put("remark", rs.getString(4));
									tempObj.put("date", rs.getString(5));
									tempObj.put("dayName", rs.getString(6));

								} catch (JSONException e) {
									e.printStackTrace();
								}
								temp.put(tempObj);
							}
							return temp;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userData;
	}

	public JSONArray getActiveUsersForProject(HttpSession session, String pid, boolean allUserFlag) {
		JSONArray userList = new JSONArray();

		try {
			String query = GetQueryAPI.getQuery(TM154, pid, session.getAttribute("loggedInUserPolicyGroup").toString());
			userList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {

				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("username", rsUserSet.getString(1));
							tempObj.put("userfullname", rsUserSet.getString(2));
							tempObj.put("usermail", rsUserSet.getString(3));
							tempObj.put("existingInGroupFlag", rsUserSet.getString(4));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	public boolean addRemoveUsersFromProject(HttpSession session, String projectId, List<String> result,
			String addRemoveFlag) {
		boolean status = false;
		try {
			String query = "";
			System.out.println(result);
			jdbcTemplate.update(GetQueryAPI.getQuery(TM155, projectId));
			if (result.size() > 0) {
				Iterator<String> itrUser = result.iterator();
				while (itrUser.hasNext()) {
					query = GetQueryAPI.getQuery(TM156, projectId, itrUser.next(),
							session.getAttribute("loggedInUser").toString(), currentDate);
					int updateCount = jdbcTemplate.update(query);
					if (updateCount != 0)
						status = true;
				}
			}
			status = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return status;
	}

	public void insertIntoLoginHistory(HttpSession session, boolean flag) {
		String query = "";
		try {
			if (flag) {
				query = GetQueryAPI.getQuery(TM157, session.getAttribute("loggedInUser").toString(),
						session.getAttribute("loggedInUserProxy").toString(), "login");
				System.out.println("User Logged In time" + new Date());
			} else {
				query = GetQueryAPI.getQuery(TM157, session.getAttribute("loggedInUser").toString(),
						session.getAttribute("loggedInUserProxy").toString(), "logout");
				System.out.println("User Logged Out time" + new Date());
			}
			jdbcTemplate.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String validatePasswordToken(HttpSession session, String token) {
		try {
			return jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM162, token), String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public JSONArray fetchUserAssignedTaskList(HttpSession session) {
		JSONArray userTaskList = new JSONArray();

		try {
			String query = GetQueryAPI.getQuery(TM167, session.getAttribute("loggedInUser").toString());
			System.out.println(query);
			userTaskList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					int i = 1;
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("taskId", rsUserSet.getString(1));
							tempObj.put("taskName", rsUserSet.getString(2));
							tempObj.put("taskDesc", rsUserSet.getString(3));
							tempObj.put("projectId", rsUserSet.getString(4));
							tempObj.put("projectName", rsUserSet.getString(5));
							tempObj.put("status", rsUserSet.getString(6));
							if (rsUserSet.getString(7) == null)
								tempObj.put("workProgress", "0");
							else
								tempObj.put("workProgress", rsUserSet.getString(7));
							tempObj.put("totalCount", String.valueOf(i));
							i++;
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(userTaskList);
		return userTaskList;
	}

	public String modifyUserAssignedTaskProgress(HttpSession session, String taskId, String workProgress) {
		String query = "";
		int updateStatus = 0;
		try {
			if (workProgress.equalsIgnoreCase("100") && !(workProgress.equalsIgnoreCase("")) && workProgress != null) {
				query = GetQueryAPI.getQuery(TM168, "Completed", workProgress, taskId);
				updateStatus = jdbcTemplate.update(query);
			} else if (!(workProgress.equalsIgnoreCase("")) && workProgress != null) {
				query = GetQueryAPI.getQuery(TM169, workProgress, taskId);
				System.out.println(query);
				updateStatus = jdbcTemplate.update(query);
			} else {
				return "Please provide valid work progress.";
			}
			if (updateStatus != 0)
				return "Task updated Successfully.";
			else
				return "Please provide valid work progress.";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Please try later.";
	}

	public JSONArray fetchUsersCurrentAccessGroupList(HttpSession session, String username) {
		JSONArray userGroupList = new JSONArray();
		try {
			String query = GetQueryAPI.getQuery(TM171, username);
			System.out.println(query);
			userGroupList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("groupId", rsUserSet.getString(1));
							tempObj.put("groupName", rsUserSet.getString(2));
							tempObj.put("assignedFlag", rsUserSet.getString(3));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	public boolean updateGroupAcees(HttpSession session, String username, String groupId, boolean status) {
		boolean updateStatus = false;
		String query = "";
		try {
			if (status)
				query = GetQueryAPI.getQuery(TM115, groupId, username);
			else
				query = GetQueryAPI.getQuery(TM182, groupId, username);
			int updateCount = jdbcTemplate.update(query);
			if (updateCount != 0)
				updateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;
	}

	public boolean updateUserDisplayDivSetting(HttpSession session, String panelId, boolean status) {
		boolean updateStatus = false;
		String query = "";
		try {
			if (status)
				query = GetQueryAPI.getQuery(TM172, panelId, session.getAttribute("loggedInUser").toString());
			else
				query = GetQueryAPI.getQuery(TM173, panelId, session.getAttribute("loggedInUser").toString());
			int updateCount = jdbcTemplate.update(query);
			if (updateCount != 0)
				updateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;
	}

	public boolean updateUserQuickLinkSetting(HttpSession session, String funcLinkId, boolean status) {
		boolean updateStatus = false;
		String query = "";
		try {
			if (status)
				query = GetQueryAPI.getQuery(TM174, funcLinkId, session.getAttribute("loggedInUser").toString());
			else
				query = GetQueryAPI.getQuery(TM175, funcLinkId, session.getAttribute("loggedInUser").toString());
			int updateCount = jdbcTemplate.update(query);
			if (updateCount != 0)
				updateStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;
	}

	public JSONArray fetchUsersDisplayDivAndQuickLinksList(HttpSession session) {
		JSONArray userElementIdList = new JSONArray();
		try {
			List<String> panelList = (List<String>) jdbcTemplate.queryForList(
					GetQueryAPI.getQuery(TM176, session.getAttribute("loggedInUser").toString()), String.class);
			List<String> quickLinkList = (List<String>) jdbcTemplate.queryForList(
					GetQueryAPI.getQuery(TM177, session.getAttribute("loggedInUser").toString()), String.class);
			for (String s : panelList) {
				JSONObject tempObj = new JSONObject();
				tempObj.put("elemId", s);
				userElementIdList.put(tempObj);
			}
			for (String s : quickLinkList) {
				JSONObject tempObj = new JSONObject();
				tempObj.put("elemId", s);
				userElementIdList.put(tempObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userElementIdList;
	}

	public JSONArray fetchUserOwnItemCount(HttpSession session) {
		JSONArray userGroupList = new JSONArray();
		try {
			String query = GetQueryAPI.getQuery(TM178, session.getAttribute("loggedInUser").toString());
			userGroupList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("modulename", rsUserSet.getString(1));
							tempObj.put("count", rsUserSet.getString(2));
							tempObj.put("statusname", rsUserSet.getString(3));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}
	
	public JSONArray fetchUserOwnItemCount(String username) {
		JSONArray userGroupList = new JSONArray();
		try {
			String query = GetQueryAPI.getQuery(TM178, username);
			userGroupList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("modulename", rsUserSet.getString(1));
							tempObj.put("count", rsUserSet.getString(2));
							tempObj.put("statusname", rsUserSet.getString(3));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	public JSONArray fetchOtherUsersItemCount(HttpSession session) {
		JSONArray userGroupList = new JSONArray();
		try {
			String uName = session.getAttribute("loggedInUser").toString();
			String query = GetQueryAPI.getQuery(TM179, uName, uName, uName, uName, uName, uName, uName, uName);
			userGroupList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("modulename", rsUserSet.getString(1));
							tempObj.put("count", rsUserSet.getString(2));
							tempObj.put("statusname", rsUserSet.getString(3));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	public JSONArray fetchOtherUsersItemCount(String username) {
		JSONArray userGroupList = new JSONArray();
		try {
			String query = GetQueryAPI.getQuery(TM179, username, username, username, username, username, username, username, username);
			userGroupList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("modulename", rsUserSet.getString(1));
							tempObj.put("count", rsUserSet.getString(2));
							tempObj.put("statusname", rsUserSet.getString(3));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}
	
	public JSONArray fetchUserCountBasedOnStatus(HttpSession session) {
		JSONArray userGroupList = new JSONArray();
		try {
			String query = GetQueryAPI.getQuery(TM180, session.getAttribute("loggedInUserPolicyGroup").toString());
			userGroupList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("modulename", rsUserSet.getString(1));
							tempObj.put("count", rsUserSet.getString(2));
							tempObj.put("statusname", rsUserSet.getString(3));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	public JSONArray fetchProjectCountBasedOnStatus(HttpSession session) {
		JSONArray userGroupList = new JSONArray();
		try {
			String query = GetQueryAPI.getQuery(TM181);
			userGroupList = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("modulename", rsUserSet.getString(1));
							tempObj.put("count", rsUserSet.getString(2));
							tempObj.put("statusname", rsUserSet.getString(3));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	
	/////////////////Add by default Image///////////////////
	public Image getUserProfileImage(HttpSession session) {
		try {
			Blob sLob = jdbcTemplate.queryForObject(
					GetQueryAPI.getQuery(TM183, session.getAttribute("loggedInUser").toString()), Blob.class);
			return Image.getInstance(sLob.getBytes(1, (int) sLob.length()));
		} catch (Exception e) {
			try {
				Blob sLob = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM185), Blob.class);
				return Image.getInstance(sLob.getBytes(1, (int) sLob.length()));
			} catch (Exception e2) {
				e2.printStackTrace();
				return null;
			}
		}
	}
	
	
	
	
	
	
	//////////////////////Mobile
	
	public String getProfileImageMetadata(String username) {
//		String userdesignation = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM216, username), String.class);
//		return userdesignation;
		@SuppressWarnings("unchecked")
		List<String> imagePathList  = jdbcTemplate.query(GetQueryAPI.getQuery(TM216, username), new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString(1);
			}
		});

			if( imagePathList.isEmpty() ){
			  return null;
			} else {
			  return imagePathList.get(0);
			}
	}

	public int saveProfileImageMetadata(String username, String fileExtension) {
		String imageUrl = protocol+"://"+ domain + "/profileImages/" + username + "."+ fileExtension; 
		String query = GetQueryAPI.getQuery(TM215, imageUrl, username, username);
		return jdbcTemplate.update(query);
	}
	
	public int updateProfileImageMetadata(String username, String fileExtension) {
		String imageUrl = protocol+"://"+ domain + "/profileImages/" + username + "."+ fileExtension; 
		String query = GetQueryAPI.getQuery(TM217, imageUrl, username, username);
		return jdbcTemplate.update(query);
	}
	
	
	//add2
	public boolean uploadUsersProfileImage(HttpSession session, String fileName) {
		boolean uploadStatus = false;
		try {
			String rootPath = System.getProperty("user.dir");
			File serverFile = new File((rootPath + File.separator + fileName));
			byte[] bytes = Files.readAllBytes(new File(rootPath + File.separator + fileName).toPath());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("docName", "ProfileImage");
			parameters.addValue("docType", fileName.substring(fileName.indexOf(".") + 1));
			parameters.addValue("username", session.getAttribute("loggedInUser").toString());
			parameters.addValue("doc",
					new SqlLobValue(new ByteArrayInputStream(bytes), bytes.length, new DefaultLobHandler()),
					Types.BLOB);
			namedParameterJdbcTemplate.update(GetQueryAPI.getQuery(TM184), parameters);
			stream.close();
			serverFile = null;
			stream = null;
			uploadStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadStatus;
	}
	
	
	public JSONArray getDailyTSReport(String projectId, String startDate, String endDate, String username,
			String taskId, String rmname, String status) {
		JSONArray data = new JSONArray();
		String query = "";
		String querySubStr = "";
		try {
			if (!username.equalsIgnoreCase("") && username != null && !username.equalsIgnoreCase("select"))
				querySubStr = querySubStr + " and t.username='" + username + "'";
			if (!taskId.equalsIgnoreCase("") && taskId != null && !taskId.equalsIgnoreCase("select"))
				querySubStr = querySubStr + " and t.taskId='" + taskId + "'";
			if (!projectId.equalsIgnoreCase("") && projectId != null && !projectId.equalsIgnoreCase("select"))
				querySubStr = querySubStr + " and t.projectId='" + projectId + "'";
			if (!status.equalsIgnoreCase("") && status != null && !status.equalsIgnoreCase("select"))
				querySubStr = querySubStr + " and t.status='" + status + "'";
			if (!rmname.equalsIgnoreCase("") && rmname != null && !rmname.equalsIgnoreCase("select"))
				querySubStr = querySubStr + " and t.username in (select username from user where reportingmanager='"
						+ rmname + "')";
			query = GetQueryAPI.getQuery(TM187, startDate, endDate, querySubStr);
			data = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(ResultSet rsUserSet) throws SQLException, DataAccessException {
					JSONArray temp = new JSONArray();
					while (rsUserSet.next()) {
						JSONObject tempObj = new JSONObject();
						try {
							tempObj.put("date", rsUserSet.getString(1));
							tempObj.put("fullname", rsUserSet.getString(2));
							tempObj.put("projectname", rsUserSet.getString(3));
							tempObj.put("taskname", rsUserSet.getString(4));
							tempObj.put("effort", rsUserSet.getString(5));
							tempObj.put("comment", rsUserSet.getString(6));
							tempObj.put("submittedon", rsUserSet.getString(7) == null ? "NA" : rsUserSet.getString(7));
							tempObj.put("lastmodifiedon",
									rsUserSet.getString(8) == null ? "NA" : rsUserSet.getString(8));
							tempObj.put("lasmodifiedby", rsUserSet.getString(9));
							tempObj.put("rmname", rsUserSet.getString(10));
							tempObj.put("status", rsUserSet.getString(11));
							tempObj.put("username", rsUserSet.getString(12));

							tempObj.put("leaveType", "-");
							tempObj.put("leaveFlag", "-");
							tempObj.put("leavestatus", "-");
							tempObj.put("appliedon", "-");
							tempObj.put("approvedon", "-");
							tempObj.put("approvedby", "-");
							tempObj.put("purpose", "-");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						temp.put(tempObj);
					}
					return temp;
				}
			});
			data = fetchEmployeeLeaveData(data, startDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private JSONArray fetchEmployeeLeaveData(JSONArray data, String startDate, String endDate) {
		JSONArray usrData = new JSONArray();
		try {
			Set<String> userList = new LinkedHashSet<String>();
			for (int i = 0; i < data.length(); i++) {
				JSONObject temp = data.getJSONObject(i);
				userList.add(temp.getString("username"));
			}
			for (String usrname : userList) {
				Map<String, List<String>> leaveMap = getUserLeaveMap(startDate, endDate, usrname);
				for (int j = 0; j < data.length(); j++) {
					JSONObject tempObj = data.getJSONObject(j);
					Iterator<String> itr = leaveMap.keySet().iterator();
					int count = 0;
					while (itr.hasNext()) {
						String temp = itr.next();
						List<String> tempList = leaveMap.get(temp);
						if (count == 0 && tempObj.get("date").equals(temp)
								&& usrname.equalsIgnoreCase(tempObj.getString("username"))) {
							tempObj.put("leaveFlag", tempList.get(0));
							tempObj.put("leavestatus", tempList.get(1));
							tempObj.put("appliedon", tempList.get(2));
							tempObj.put("approvedon", tempList.get(3));
							tempObj.put("approvedby", tempList.get(4));
							tempObj.put("purpose", tempList.get(5));
							tempObj.put("leaveType", tempList.get(6));
							count = count + 1;
							data.put(j, tempObj);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Map<String, List<String>> getUserLeaveMap(String startDate, String endDate, String username) {
		Map<String, List<String>> leaveMap = new LinkedHashMap<String, List<String>>();
		try {
			leaveMap = jdbcTemplate.query(GetQueryAPI.getQuery(TM188, startDate, endDate, username),
					new ResultSetExtractor<Map<String, List<String>>>() {
						@Override
						public Map<String, List<String>> extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							Map<String, List<String>> tempMap = new LinkedHashMap<String, List<String>>();
							try {
								while (rs.next()) {
									List<Date> allDays = getDaysBetweenDates(sdf.parse(rs.getString(1)),
											sdf.parse(rs.getString(2)));
									// System.out.println(allDays.toString());
									for (Date d : allDays) {
										List<String> temp = new LinkedList<String>();

										temp.add(rs.getString(3));
										temp.add(rs.getString(4));
										temp.add(rs.getString(5));
										temp.add(rs.getString(6));
										temp.add(rs.getString(7));
										temp.add(rs.getString(8));
										temp.add(rs.getString(9));

										tempMap.put(sdf.format(d), temp);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("Exception occured while fetching users leave");
							}
							return tempMap;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveMap;
	}

	public List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		Calendar callast = new GregorianCalendar();
		callast.setTime(enddate);
		dates.add(callast.getTime());
		return dates;
	}

	public String updateUserInfoQuick(HttpSession session, String username, String paramValue, String param) {
		String statusString = "Try after some time.";
		String queryUser = "";
		String queryEmp = "";
		try {
			if (param.equalsIgnoreCase("REPORTING_PERSON")) {
				queryUser = GetQueryAPI.getQuery(TM197, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM198, paramValue, username);
			}
			if (param.equalsIgnoreCase("HR_MANAGER")) {
				queryUser = GetQueryAPI.getQuery(TM199, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM200, paramValue, username);
			}
			if (param.equalsIgnoreCase("BASE_LOCATION")) {
				queryUser = GetQueryAPI.getQuery(TM201, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM202, paramValue, username);
			}
			if (param.equalsIgnoreCase("DEPARTMENT")) {
				queryUser = GetQueryAPI.getQuery(TM203, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM204, paramValue, username);
			}
			if (param.equalsIgnoreCase("DESIGNATION")) {
				queryUser = GetQueryAPI.getQuery(TM205, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM206, paramValue, username);
			}
			if (param.equalsIgnoreCase("ROLE")) {
				queryUser = GetQueryAPI.getQuery(TM207, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM208, paramValue, username);
			}
			if (param.equalsIgnoreCase("ACCOUNT_UNIT")) {
				queryUser = GetQueryAPI.getQuery(TM209, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM210, paramValue, username);
			}
			if (param.equalsIgnoreCase("POLICY_GROUP")) {
				queryUser = GetQueryAPI.getQuery(TM211, paramValue, username);
				queryEmp = GetQueryAPI.getQuery(TM212, paramValue, username);
			}
			if (jdbcTemplate.update(queryUser) != 0)
				if (jdbcTemplate.update(queryEmp) != 0)
					statusString = "User Data updated Successfully.";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusString;
	}
	
	
	
	
	
	
	
	
	
	
	


	/******************   MOBILE APIS   *******************/
	
	public void insertIntoLoginHistoryRest(LoginResult restLoginResultBean, boolean flag) {
		String query = "";
		try {
			if (flag) {
				query = GetQueryAPI.getQuery(TM157, String.valueOf(restLoginResultBean.getEmpId()),
						restLoginResultBean.getLoggedInUserProxy(), "login");
				System.out.println("User Logged In time" + new Date());
			} else {
				query = GetQueryAPI.getQuery(TM157, String.valueOf(restLoginResultBean.getEmpId()),
						restLoginResultBean.getLoggedInUserProxy(), "logout");
				System.out.println("User Logged Out time" + new Date());
			}
			jdbcTemplate.update(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MenuDataFromDB> fetchModuleFunctionMapping(String username, String i, String j) {
		List<MenuDataFromDB> userGroupList = new ArrayList<>();
		try {
			String query = GetQueryAPI.getQuery(M12, username, i, j);
			userGroupList = jdbcTemplate.query(query, new BeanPropertyRowMapper(MenuDataFromDB.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DashboardDataFromDB> getUserAllowedPanelsWithStatus(String username) {
		List<DashboardDataFromDB> panStatusList = new ArrayList<>();
		try {
			String query = GetQueryAPI.getQuery(M13, username);
			panStatusList = jdbcTemplate.query(query, new BeanPropertyRowMapper(DashboardDataFromDB.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return panStatusList;
	}

	public List<DashboardQuickLink> getQuickLinks(String username) {
		// TODO Auto-generated method stub
		List<DashboardQuickLink> quicklink = new ArrayList<>();
		try {
			String query = GetQueryAPI.getQuery(M14, username);
			quicklink = jdbcTemplate.query(query, new BeanPropertyRowMapper(DashboardQuickLink.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quicklink;
	}
	
	
	

	public List<DashboardAnnouncement> getAnnouncementUserSpecific(String username) {
		// TODO Auto-generated method stub
		List<DashboardAnnouncement>  dashboardAnnouncements = new ArrayList<>();
       try {
    	    String query = GetQueryAPI.getQuery(M16, username);
    	   // dashboardAnnouncements = jdbcTemplate.query(query, new BeanPropertyRowMapper(DashboardAnnouncement.class));
    	    dashboardAnnouncements = jdbcTemplate.query(query, new ResultSetExtractor< List<DashboardAnnouncement>>() {

				@Override
				public List<DashboardAnnouncement> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					 List<DashboardAnnouncement> dashboardAnnouncements= new ArrayList<>(); 
					  if(rs.next()) {
						  DashboardAnnouncement  dashboardAnnouncements2 = new DashboardAnnouncement();
						  dashboardAnnouncements2.setAnnouncementId(rs.getString(1));
						  dashboardAnnouncements2.setAnnouncementName(rs.getString(2));
						  dashboardAnnouncements2.setViewMore("View More...");
						  dashboardAnnouncements2.setAnnouncementUrl(protocol+"://"+domain+"/"+contextpath+"/resources/images/announcement/"+rs.getString(2)+".pdf");
						  
						  dashboardAnnouncements.add(dashboardAnnouncements2);
					  }
					return dashboardAnnouncements;
				}
    	    	
    	    });
    	    
    	   }
       catch (Exception e) {
		// TODO: handle exception
    	   e.printStackTrace();
	}
		return dashboardAnnouncements;
	}

	public String getImageType1(String empId) {
		// TODO Auto-generated method stub
		String s = null;

		try {
			 s = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(M19, empId), String.class);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	
	}

	public List<ProjectListDetails>  getProjectListAccordingToUser(String username) {
		// TODO Auto-generated method stub
		List<ProjectListDetails>  projectListFillTimesheet = new ArrayList<>();
       try {
    	    String query = GetQueryAPI.getQuery(M20, username);
    	     projectListFillTimesheet = jdbcTemplate.query(query, new BeanPropertyRowMapper(ProjectListDetails.class));

		return projectListFillTimesheet;
	}
       catch (Exception e) {
		// TODO: handle exception
    	   e.printStackTrace();
	}
	return projectListFillTimesheet;
	}
	}