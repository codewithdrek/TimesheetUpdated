package com.supraits.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.supraits.dao.impl.GetQueryAPI;


@Component
public class TSReminderScheduler {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ServletContext servletContext;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;

	@Value("${ADMIN_MAIL}")
	private String ADMIN_MAIL;
	@Value("${TIMESHEET_REMINDER}")
	private String TIMESHEET_REMINDER;
	@Value("${FILL_TIMESHEET}")
	private String FILL_TIMESHEET;
	@Value("${domain}")
	private String domain;
	@Value("${TEST_DELIVERY_MAIL_TO}")
	private String TEST_DELIVERY_MAIL_TO;
	
	@Value("${TM80}")
	private String TM80;
	@Value("${TM123}")
	private String TM123;
	@Value("${TM124}")
	private String TM124;
	@Value("${TM125}")
	private String TM125;
	@Value("${TM126}")
	private String TM126;
	@Value("${TM189}")
	private String TM189;
	@Value("${TM213}")
	private String TM213;

	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public void printMe() {
		System.out.println("TS Sceduler:: Sending reminder mail for missing timesheet to users");
	if(checkMailNotificationTrigger(TIMESHEET_REMINDER)){
		Map<String,String> weekAndStatus = new LinkedHashMap<String,String>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		System.out.println("Start of this week:       " +formatter.format(cal.getTime()));
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.HOUR_OF_DAY, 0); 
		cal1.clear(Calendar.MINUTE);
		cal1.clear(Calendar.SECOND);
		cal1.clear(Calendar.MILLISECOND);
		cal1.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek());
		cal1.add(Calendar.DAY_OF_WEEK, 6);
		System.out.println("End of this week:       " +formatter.format(cal1.getTime()));
		String startDate = "";
		String endDate = "";
		for(int i=0;i<8;i++){
			cal.add(Calendar.DAY_OF_WEEK, -7);
			
			cal1.add(Calendar.DAY_OF_WEEK, -7);
			if(i==0)
				endDate=formatter.format(cal1.getTime());
			if(i==7)
				startDate=formatter.format(cal.getTime());
			weekAndStatus.put(formatter.format(cal.getTime()) +"-"+formatter.format(cal1.getTime()) , "Not Filled");
		}
		//System.out.println(weekAndStatus.keySet());
		
		String query = GetQueryAPI.getQuery(TM123);
		//System.out.println(query);
		Map<String,String> userList = jdbcTemplate.query(query, new ResultSetExtractor<Map<String,String>>(){
			@Override
			public Map<String,String> extractData(ResultSet rsUserSet)
					throws SQLException, DataAccessException {
				Map<String,String> temp = new HashMap<String,String>();
					while(rsUserSet.next()){
						temp.put(rsUserSet.getString(1),rsUserSet.getString(2));
					}
				return temp;
			}
		});
		Iterator<String> itrUser = userList.keySet().iterator();
		
		while(itrUser.hasNext()){
			String tempUsermail = itrUser.next();
			String tempUsername = userList.get(tempUsermail);
			if(!checkUserAccessFillTimesheet(tempUsername))
				continue;
			System.out.println(GetQueryAPI.getQuery(TM124,startDate,endDate,tempUsername));
		  Map<String,String> weekStatus = jdbcTemplate.query(GetQueryAPI.getQuery(TM124,startDate,endDate,tempUsername), new ResultSetExtractor<Map<String,String>>(){	
			//Map<String,String> weekStatus = jdbcTemplate.query(GetQueryAPI.getQuery(TM124,startDate,endDate, jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM125, "abhinav.gupta@supraits.com"), String.class)), new ResultSetExtractor<Map<String,String>>(){
				@Override
				public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
					Map<String,String> temp = new LinkedHashMap<String,String>(); 
					while(rs.next()){
						temp.put(rs.getString(1), rs.getString(2));
					}
					return temp;
				}
			});
		  	System.out.println(weekStatus.keySet());
		  	System.out.println(weekStatus.values());
			weekAndStatus.putAll(weekStatus);
			if(weekAndStatus.values().contains("Not Filled")){
				final String tomail = tempUsermail;
				Map<String,Object> mailContent = new HashMap<String,Object>();
				int i =1;
				Iterator<String> itrIterator = weekAndStatus.keySet().iterator();
				while(itrIterator.hasNext()){
					String week = itrIterator.next();
					String temp = weekAndStatus.get(week);
					
					mailContent.put("week"+i, week.substring(8, 10)+"/"+week.substring(5, 7)+" to "+week.substring(19, 21)+"/"+week.substring(16, 18));
					mailContent.put("status"+i, temp);
					i= i+1;
				}
				System.out.println(mailContent.keySet());
				System.out.println(mailContent.values());
				try {
					doSendTemplateEmail(tomail, "Kindly submit your timesheet.", mailContent,"timesheetReminder.vm");
					//doSendTemplateEmail("guptaabhinav29@gmail.com", "Kindly submit your timesheet.", mailContent,"timesheetReminder.vm");
					insertIntoMailAuditTable(tempUsermail,"Timesheet Reminder");
					System.out.println("Mail sent successfully"+tomail);
				}catch(Exception e){ 
					System.out.println("Mail Send encountered error to user"+tomail); }
				}
				try {
					//birthdayWishReminder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		  }else{
			  System.out.println("TS Sceduler:: Sending reminder mail notification disabled");
		  }
		}
	private boolean checkUserAccessFillTimesheet(String tempUsername) {
		boolean fillFlag = false;
		try{
			String query = GetQueryAPI.getQuery(TM213, tempUsername,FILL_TIMESHEET);
			int count = jdbcTemplate.queryForObject(query, Integer.class);
			if(count > 0)
				return true;
		}catch(Exception e){
			System.out.println("Error while checking user Fill Timesheeet access");
		}
		return fillFlag;
	}
	public void insertIntoMailAuditTable(String mail,String action){
			try{
				String query = GetQueryAPI.getQuery(TM189,mail,action);
				jdbcTemplate.update(query);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	public String doSendEmail(String recipientAddress,String subject,String message) {
		//SimpleMailMessage email = new SimpleMailMessage();
		 final MimeMessage mimeMessage = mailSender.createMimeMessage();
	        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
		try{
			messageHelper.setSubject(subject);
			messageHelper.setFrom(ADMIN_MAIL);
			messageHelper.setTo(recipientAddress);
			messageHelper.setText(message, true /* isHtml */);
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
	private void birthdayWishReminder() throws MessagingException{
		Map<String,String> userMap = jdbcTemplate.query(GetQueryAPI.getQuery(TM123), new ResultSetExtractor<Map<String,String>>(){
			@Override
			public Map<String,String> extractData(ResultSet rsUserSet)
					throws SQLException, DataAccessException {
				Map<String,String> temp = new HashMap<String,String>();
					while(rsUserSet.next()){
						temp.put(rsUserSet.getString(1),rsUserSet.getString(3));
					}
				return temp;
			}
		});
		List<String> userList = new ArrayList<String>(userMap.keySet());
		Iterator<String> itrUser = userMap.keySet().iterator();
		List<String> tempMail = new ArrayList<String>();
		while(itrUser.hasNext()){
				String keyMail = itrUser.next();
				if(formatter.format(new Date()).equalsIgnoreCase(userMap.get(keyMail))){
					doSendEmailWithAttch(keyMail, "Happy Birthday", "");
					tempMail.add(keyMail);
					userList.remove(keyMail);
				}
		}
		if(tempMail.size() > 0)
			doSendEmailToOther(tempMail,userList);
	}
	private void doSendEmailToOther(List<String> tempMail,List<String> ccList) {
		 final MimeMessage mimeMessage = mailSender.createMimeMessage();
	        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
		try{
			messageHelper.setSubject("Todays's Birthday List");
			messageHelper.setFrom(ADMIN_MAIL);
			InternetAddress[] address = new InternetAddress[ccList.size()];
		    for (int i = 0; i < ccList.size(); i++) {
		        address[i] = new InternetAddress(ccList.get(i));
		    }
			//messageHelper.setBcc(address);
		    messageHelper.setBcc("guptaabhinav29@gmail.com");
			StringBuilder mailBody = new StringBuilder("<html><body>");
			for (int i = 0; i < tempMail.size(); i++) {
				mailBody.append("<p>"+tempMail.get(i)+"</p>");
		    }
			mailBody.append("</body></html>");
			messageHelper.setText(mailBody.toString(), true);
		this.mailSender.send(mimeMessage);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String doSendEmailWithAttch(String recipientAddress,String subject,String message) throws MessagingException {
		//SimpleMailMessage email = new SimpleMailMessage();
		 final MimeMessage mimeMessage = mailSender.createMimeMessage();
	        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
	        StringBuilder emailBody = new StringBuilder("");
		try{
			messageHelper.setSubject(subject);
			messageHelper.setFrom(ADMIN_MAIL);
			messageHelper.setTo(recipientAddress);
			//messageHelper.setText(message, true /* isHtml */);
			File file = new File( servletContext.getRealPath("/WEB-INF/resource/birthdayImage.jpg") );
			//DataSource imageSource = (DataSource) new ClassPathResource(servletContext.getRealPath("/WEB-INF/resource/birthdayImage.jpg"));
			FileSystemResource res = new FileSystemResource(file);
			emailBody.append("<html><body><p><b><i>Wish you many more happy returns of the day</i></b></><br><img src='cid:birthdayImage.jpg'></img><div>Happy Birthday</div></body></html>");
			messageHelper.setText(emailBody.toString(), true /* isHtml */);
			messageHelper.addInline("birthdayImage.jpg", res);
			
		this.mailSender.send(mimeMessage);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Result";
	}
	public String doSendTemplateEmail(String recipientAddress,String subject,Map<String, Object> model,String templateName) {
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
}
