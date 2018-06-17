package com.supraits.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.supraits.dao.impl.GetQueryAPI;
import com.supraits.viewBean.ExpenseBean;
import com.supraits.viewBean.ExpenseDocumentBean;
import com.supraits.viewBean.ExpenseRequestBean;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpenseServiceImpl.
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {

	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String currentDate = sdf.format(date);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;
	
	/** The named parameter jdbc template. */
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/** The Review pending. */
	@Value("${Review_Pending}")
	private String Review_Pending;
	
	/** The Approval pending. */
	@Value("${Approval_Pending}")
	private String Approval_Pending;
	
	/** The Withdraw. */
	@Value("${Withdraw}")
	private String Withdraw;
	
	/** The Approved. */
	@Value("${Approved}")
	private String Approved;
	
	/** The Rejected. */
	@Value("${Rejected}")
	private String Rejected;
	
	/** The Saved as draft. */
	@Value("${Saved_as_Draft}")
	private String Saved_as_Draft;
	
	/** The Review failed. */
	@Value("${Review_Failed}")
	private String Review_Failed;
	
	/** The Delete request. */
	@Value("${Delete_Request}")
	private String Delete_Request;
	
	/** The vp approval pending. */
	@Value("${VP_APPROVAL_PENDING}")
	private String VP_APPROVAL_PENDING;
	
	/** The vp approval failed. */
	@Value("${VP_APPROVAL_FAILED}")
	private String VP_APPROVAL_FAILED;
	
	
	/** The finanace. */
	@Value("${FINANACE}")
	private String FINANACE;
	
	/** The admin. */
	@Value("${ADMIN}")
	private String ADMIN;
	
	/** The vp group. */
	@Value("${HR}")
	private String HR;
	
	/** The vp group. */
	@Value("${VP_GROUP}")
	private String VP_GROUP;
	
	/** The admin mail. */
	@Value("${ADMIN_MAIL}")
	private String ADMIN_MAIL;
	
	/** The save submit rms request. */
	@Value("${SAVE_SUBMIT_RMS_REQUEST}")
	private String SAVE_SUBMIT_RMS_REQUEST;
	
	/** The rms request status modified. */
	@Value("${RMS_REQUEST_STATUS_MODIFIED}")
	private String RMS_REQUEST_STATUS_MODIFIED;
	
	/** The document verification pending. */
	@Value("${DOCUMENT_VERIFICATION_PENDING}")
	private String DOCUMENT_VERIFICATION_PENDING;
	
	/** The document verified. */
	@Value("${DOCUMENT_VERIFIED}")
	private String DOCUMENT_VERIFIED;
	
	/** The ready for payement. */
	@Value("${READY_FOR_PAYEMENT}")
	private String READY_FOR_PAYEMENT;
	
	/** The payment done. */
	@Value("${PAYMENT_DONE}")
	private String PAYMENT_DONE;
	
	/** The payment declined. */
	@Value("${PAYMENT_DECLINED}")
	private String PAYMENT_DECLINED;
	
	/** The request to be review. */
	@Value("${REQUEST_TO_BE_REVIEW}")
	private String REQUEST_TO_BE_REVIEW;
	@Value("${domain}")
	private String domain;
	@Value("${TEST_DELIVERY_MAIL_TO}")
	private String TEST_DELIVERY_MAIL_TO;
	
	/** The rms1. */
	@Value("${RMS1}")
	private String RMS1;
	
	/** The rms2. */
	@Value("${RMS2}")
	private String RMS2;
	
	/** The rms3. */
	@Value("${RMS3}")
	private String RMS3;
	
	/** The rms4. */
	@Value("${RMS4}")
	private String RMS4;
	
	/** The rms5. */
	@Value("${RMS5}")
	private String RMS5;
	
	/** The rms6. */
	@Value("${RMS6}")
	private String RMS6;
	
	/** The rms7. */
	@Value("${RMS7}")
	private String RMS7;
	
	/** The rms8. */
	@Value("${RMS8}")
	private String RMS8;
	
	/** The rms9. */
	@Value("${RMS9}")
	private String RMS9;
	
	/** The rms10. */
	@Value("${RMS10}")
	private String RMS10;
	
	/** The rms11. */
	@Value("${RMS11}")
	private String RMS11;
	
	/** The rms12. */
	@Value("${RMS12}")
	private String RMS12;
	
	/** The rms13. */
	@Value("${RMS13}")
	private String RMS13;
	
	/** The rms14. */
	@Value("${RMS14}")
	private String RMS14;
	
	/** The rms15. */
	@Value("${RMS15}")
	private String RMS15;
	
	/** The rms16. */
	@Value("${RMS16}")
	private String RMS16;
	
	/** The rms17. */
	@Value("${RMS17}")
	private String RMS17;
	
	/** The rms18. */
	@Value("${RMS18}")
	private String RMS18;
	
	/** The rms19. */
	@Value("${RMS19}")
	private String RMS19;
	
	/** The rms20. */
	@Value("${RMS20}")
	private String RMS20;
	
	/** The rms21. */
	@Value("${RMS21}")
	private String RMS21;
	
	/** The rms22. */
	@Value("${RMS22}")
	private String RMS22;
	
	/** The tm80. */
	@Value("${TM80}")
	private String TM80;
	
	/** The tm11. */
	@Value("${TM11}")
	private String TM11;
	
	/** The tm21. */
	@Value("${TM21}")
	private String TM21;
	
	/** The rms23. */
	@Value("${RMS23}")
	private String RMS23;
	
	/** The rms24. */
	@Value("${RMS24}")
	private String RMS24;
	
	/** The rms25. */
	@Value("${RMS25}")
	private String RMS25;
	
	/** The rms27. */
	@Value("${RMS27}")
	private String RMS27;
	
	/** The rms28. */
	@Value("${RMS28}")
	private String RMS28;
	
	/** The rms29. */
	@Value("${RMS29}")
	private String RMS29;
	
	/** The rms30. */
	@Value("${RMS30}")
	private String RMS30;
	
	/** The rms31. */
	@Value("${RMS31}")
	private String RMS31;
	
	/** The rms32. */
	@Value("${RMS32}")
	private String RMS32;
	@Value("${RMS33}")
	private String RMS33;
	@Value("${RMS36}")
	private String RMS36;
	@Value("${RMS37}")
	private String RMS37;
	@Value("${RMS38}")
	private String RMS38;
	@Value("${RMS39}")
	private String RMS39;
	@Value("${RMS43}")
	private String RMS43;
	@Value("${RMS44}")
	private String RMS44;
	@Value("${RMS45}")
	private String RMS45;
	@Value("${RMS46}")
	private String RMS46;
	@Value("${RMS47}")
	private String RMS47;
	@Value("${RMS48}")
	private String RMS48;
	@Value("${RMS49}")
	private String RMS49;
	@Value("${RMS50}")
	private String RMS50;
	@Value("${RMS51}")
	private String RMS51;
	@Value("${RMS52}")
	private String RMS52;
	@Value("${RMS53}")
	private String RMS53;
	@Value("${RMS54}")
	private String RMS54;
	@Value("${RMS55}")
	private String RMS55;
	@Value("${RMS56}")
	private String RMS56;
	@Value("${RMS57}")
	private String RMS57;
	@Value("${RMS58}")
	private String RMS58;
	@Value("${RMS59}")
	private String RMS59;
	@Value("${RMS60}")
	private String RMS60;
	@Value("${RMS61}")
	private String RMS61;
	@Value("${TM36}")
	private String TM36;
	
	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#newExpenseRequest()
	 */
	@Override
	public boolean newExpenseRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#insertNewExpenseRequest(javax.servlet.http.HttpSession, com.supraits.viewBean.ExpenseRequestBean, boolean)
	 */
	@Override
	public boolean insertNewExpenseRequest(HttpSession session,
			ExpenseRequestBean expenseRequestBean, final boolean submitStatus) {
		boolean status = false;
		boolean statusReqMappingInsert = false;
		try{
			Integer prevDataCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS18, expenseRequestBean.getRequestNumber()), Integer.class);
			if(prevDataCount > 0){
				deleteAllPrevData(expenseRequestBean.getRequestNumber());
			}
			boolean statusReqInsert = insertIntoRMSRequestTbl(expenseRequestBean,submitStatus,session); 
			if(statusReqInsert){
				statusReqMappingInsert = insertIntoRMSRequestMappingTbl(expenseRequestBean,submitStatus,session);
				if(statusReqMappingInsert){
					status =  insertIntoRMSDocumentTbl(expenseRequestBean,session);
					//System.out.println(GetQueryAPI.getQuery(RMS33, ((submitStatus) ? "Submit" : "Save"),expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"),(String)session.getAttribute("loggedInUser"),"NA"));
					if(session.getAttribute("loggedInUserProxy").toString().equalsIgnoreCase("")){
						 if(submitStatus){	
							jdbcTemplate.update(GetQueryAPI.getQuery(RMS33, ((submitStatus) ? "Review Pending" : "Saved in Draft"),expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"),(String)session.getAttribute("loggedInUser"),"NA",(String)session.getAttribute("reportingManager")));
							generateApprovalLevels(expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"));
						 }else
							 jdbcTemplate.update(GetQueryAPI.getQuery(RMS33, ((submitStatus) ? "Review Pending" : "Saved in Draft"),expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"),(String)session.getAttribute("loggedInUser"),"NA",(String)session.getAttribute("loggedInUser"))); 
					}else{
						if(submitStatus){	
							jdbcTemplate.update(GetQueryAPI.getQuery(RMS33, ((submitStatus) ? "Review Pending" : "Saved in Draft"),expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"),session.getAttribute("loggedInUserProxy").toString(),"NA",(String)session.getAttribute("reportingManager")));
							generateApprovalLevels(expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"));
						}else
							jdbcTemplate.update(GetQueryAPI.getQuery(RMS33, ((submitStatus) ? "Review Pending" : "Saved in Draft"),expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"),session.getAttribute("loggedInUserProxy").toString(),"NA",(String)session.getAttribute("loggedInUser")));
					}
						if(checkMailNotificationTrigger(SAVE_SUBMIT_RMS_REQUEST)){
							final String toMailId = getUserMailId((String)session.getAttribute("loggedInUser"));
							final String requestedBy = (String)session.getAttribute("loggedInUserFullName");
							final String reqMailNumber = expenseRequestBean.getRequestNumber();
							final String requestedEmpManagerMail = getUserMailId((String)session.getAttribute("reportingManagerUserName"));
							final String reqAmount =String.valueOf( expenseRequestBean.getRequestedAmount() );
							final String currStatus = (submitStatus) ? "Submitted" : "Saved in Draft";
							
							Iterator<ExpenseBean> itrExp = expenseRequestBean.getExpenseList().iterator();
							Map<String,Object> expContent = new HashMap<String,Object>();
							while(itrExp.hasNext()){
								ExpenseBean temp =itrExp.next();
								//System.out.println(temp.getCodeName());
								if(null == temp.getCodeName()){
									continue;
								}
								expContent.put(temp.getCodeName(), temp);
							}
							final Map<String,Object> expContentFinal = expContent;
							final Map<String,Object> expContentHistoryFinal = getRMSRequestActionHistory(expenseRequestBean.getRequestNumber());
							Thread t = new Thread(){
								public void run(){
									String mailSubject ="";
									Map<String,Object> mailContent = new HashMap<String,Object>();
									mailContent.put("reqMailNumber", reqMailNumber);
									mailContent.put("requestedBy", requestedBy);
									mailContent.put("reqAmount", reqAmount);
									mailContent.put("currStatus", currStatus);
									mailContent.put("expContent", expContentFinal);
									mailContent.put("expHistoryContent", expContentHistoryFinal);
									if(submitStatus){
										mailSubject = "Request "+ reqMailNumber +" has been successfully submitted";
										doSendTemplateEmail(requestedEmpManagerMail, "Reimbursement Request("+ reqMailNumber +") by:"+requestedBy, mailContent,"rmsRequestIntimationToManager.vm");
									}else{
										mailSubject = "Request "+ reqMailNumber +" has been successfully saved in draft";
									}
									doSendTemplateEmail(toMailId, mailSubject, mailContent,"rmsRequest.vm");
								}
							};
							t.start();
						}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return statusReqMappingInsert;
	}
	
	private void generateApprovalLevels(String requestNumber,String username) {
		String query = "";
		Map<String,String> approvalLevel = new LinkedHashMap<String,String>();
		try{
			query = GetQueryAPI.getQuery(RMS46, username,username,username);
			approvalLevel = jdbcTemplate.query(query, new ResultSetExtractor<Map<String,String>>(){
				@Override
				public Map<String, String> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					Map<String,String> temp = new LinkedHashMap<String,String>();
					while(rs.next())
						temp.put(rs.getString(1), rs.getString(2));
					return temp;
				}
			});
			
			 Iterator it = approvalLevel.entrySet().iterator();
			 int levelCount = 1;
			    while (it.hasNext()) {
			        Map.Entry tempEntry = (Map.Entry)it.next();
			        //Last Modified by abhinav.gupta on April 12
			        String statusVal = tempEntry.getKey().toString().substring(1);
			        String userOrGroupType = tempEntry.getKey().toString().substring(0,1);		
			        if(levelCount == 1)   
			        	query = GetQueryAPI.getQuery(RMS44,String.valueOf(levelCount), requestNumber,username,tempEntry.getValue().toString(),statusVal,"Y",currentDate,userOrGroupType);
			        else
			        	query = GetQueryAPI.getQuery(RMS44,String.valueOf(levelCount), requestNumber,username,tempEntry.getValue().toString(),statusVal,"N",currentDate,userOrGroupType);
			        //System.out.println("insert value"+tempEntry.getKey() + " = " + tempEntry.getValue());
			        //System.out.println(query);
			        jdbcTemplate.update(query);
			        levelCount = levelCount + 1;
			    }
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getRMSRequestActionHistory(String requestNumber) {
		Map<String,Object> expHistory = new LinkedHashMap<String,Object>();
		String query = GetQueryAPI.getQuery(RMS37,"'^[[:digit:]]+$'", requestNumber);
		try{
			expHistory = jdbcTemplate.query(query, new ResultSetExtractor<Map<String,Object>>(){
				@Override
				public Map<String, Object> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					Map<String,Object> temp = new LinkedHashMap<String,Object>();
					while(rs.next()){
						List<Object> tempList = new LinkedList<Object>();
						tempList.add(rs.getObject(1));
						tempList.add(rs.getObject(2));
						tempList.add(rs.getObject(3));
						tempList.add(rs.getObject(4));
						//Last modified by abhinav.gupta on April 12
						/*try{
							String fullNameTemp = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM21, (String) rs.getObject(6)),String.class);
							tempList.add(fullNameTemp);
						}catch(Exception e){
							tempList.add(rs.getObject(6));
						}*/
						tempList.add(rs.getObject(6));
						tempList.add(getGroupUsers((String) rs.getObject(6)));
						temp.put(rs.getString(5), tempList);
					}
					return temp;
				}

				private String getGroupUsers(String groupORUserName) {
					String finalUserForTitle = "";
					try{
						if(groupORUserName.matches("(?i)Finance.*")){
							List<String> usersList = jdbcTemplate.queryForList(GetQueryAPI.getQuery(RMS55, FINANACE),String.class);
							finalUserForTitle = usersList.toString();
						}
						else{
							if(groupORUserName.indexOf("Group") >= 0 && groupORUserName.indexOf("VP") >= 0){
								List<String> usersList = jdbcTemplate.queryForList(GetQueryAPI.getQuery(RMS55, VP_GROUP),String.class);
								finalUserForTitle = usersList.toString();
							}
							//else
							//finalUserForTitle = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM21, groupORUserName),String.class);
						}
					}catch(Exception e){
						e.printStackTrace();
						finalUserForTitle = "NA";
					}
					return finalUserForTitle;
				}
				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return expHistory;
	}

	/**
	 * Delete all prev data.
	 *
	 * @param requestNumber the request number
	 */
	private void deleteAllPrevData(String requestNumber) {
		try{
		String query = GetQueryAPI.getQuery(RMS19, requestNumber);
		jdbcTemplate.update(query);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Insert into RMS document tbl.
	 *
	 * @param expenseRequestBean the expense request bean
	 * @param session the session
	 * @return true, if successful
	 */
	private boolean insertIntoRMSDocumentTbl(
			ExpenseRequestBean expenseRequestBean, HttpSession session) {
		boolean status =false;
		String query1 = "";
		ListIterator<ExpenseBean> expenseItr = (ListIterator<ExpenseBean>) expenseRequestBean.getExpenseList().listIterator();
		while(expenseItr.hasNext()){
			try{
				ExpenseBean temp = expenseItr.next();
				//System.out.println("doc name="+temp.getExpDocName());
				if(temp.getExpDocName().length() > 0 && null != temp.getExpDocName()){
					int checkPrevDocEntry = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS20,expenseRequestBean.getRequestNumber(),temp.getExpenseCode()), Integer.class);
					if(checkPrevDocEntry > 0)
						jdbcTemplate.update(GetQueryAPI.getQuery(RMS22,expenseRequestBean.getRequestNumber(),temp.getExpenseCode()));
					query1 = GetQueryAPI.getQuery(RMS4,expenseRequestBean.getRequestNumber(),temp.getExpDocName(),temp.getExpenseCode(),(String)session.getAttribute("loggedInUser"),sdf.format(new Date()).toString(),"Y",temp.getExpDocName());
					//System.out.println("query doc="+query1);
					
					jdbcTemplate.update(query1);
					try {
						String rootPath = System.getProperty("user.dir");
						File folder = new File(rootPath + File.separator + "BILL" + File.separator +"Register" + File.separator + (String)session.getAttribute("loggedInUser"));
						File[] listOfFiles = folder.listFiles();
						    for (int i = 0; i < listOfFiles.length; i++) {
						      if (listOfFiles[i].isFile()) {
						    	  //System.out.println("File " + listOfFiles[i].getName());
						    	  if(listOfFiles[i].getName().equalsIgnoreCase(temp.getExpDocName())){
						    		 File serverFile = new File(rootPath + File.separator + "BILL" + File.separator +"Register" + File.separator + (String)session.getAttribute("loggedInUser") + File.separator +temp.getExpDocName());
						    		 byte[] bytes = Files.readAllBytes(new File(rootPath + File.separator + "BILL" + File.separator +"Register" + File.separator + (String)session.getAttribute("loggedInUser") + File.separator +temp.getExpDocName()).toPath());
						    		 	//System.out.println(serverFile);
										BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
										stream.write(bytes);
										MapSqlParameterSource parameters = new MapSqlParameterSource();
										parameters.addValue("requestNumber", expenseRequestBean.getRequestNumber());
										parameters.addValue("expenseCode", temp.getExpenseCode());
										parameters.addValue("document", new SqlLobValue(new ByteArrayInputStream(bytes), bytes.length, new DefaultLobHandler()),Types.BLOB);
										//System.out.println("parameters"+expenseRequestBean.getRequestNumber());
										//System.out.println("parameters"+temp.getExpenseCode());
										//System.out.println("parameters"+bytes.length);
										namedParameterJdbcTemplate.update("update supra_rms_document set document=:document where requestNumber=:requestNumber and expenseCode=:expenseCode", parameters);
										stream.close();
										serverFile = null;
										stream = null;
						        }
						      } 
						    }
					}catch(Exception e){
						e.printStackTrace();
					} 
				status = true;
			}	
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
		return status;
	}

	/**
	 * Insert into RMS request mapping tbl.
	 *
	 * @param expenseRequestBean the expense request bean
	 * @param submitStatus the submit status
	 * @param session the session
	 * @return true, if successful
	 */
	private boolean insertIntoRMSRequestMappingTbl(
			ExpenseRequestBean expenseRequestBean, boolean submitStatus, HttpSession session) {
		boolean status =false;
		String query = "";
		ListIterator<ExpenseBean> expenseItr = (ListIterator<ExpenseBean>) expenseRequestBean.getExpenseList().listIterator();
		while(expenseItr.hasNext()){
			try{
				ExpenseBean temp = expenseItr.next();
				if(null == temp.getExpDocName())
					temp.setExpDocName("NA");
				query = GetQueryAPI.getQuery(RMS3,expenseRequestBean.getRequestNumber(),temp.getExpenseCode(),temp.getExpenseName(),temp.getBillNumber(),temp.getBillDate(),sdf.format(new Date()).toString(),sdf.format(new Date()).toString(),String.valueOf(temp.getRequestAmount()),String.valueOf(temp.getRequestAmount()),String.valueOf(temp.getBillAmount()),temp.getApplicantRemark(),temp.getExpDocName(),temp.getProjectName());
				if(temp.getBillAmount() > 0 && temp.getRequestAmount() > 0){
					jdbcTemplate.update(query);
				}	
				status = true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return status;
	}

	/**
	 * Insert into RMS request tbl.
	 *
	 * @param expenseRequestBean the expense request bean
	 * @param submitStatus the submit status
	 * @param session the session
	 * @return true, if successful
	 */
	private boolean insertIntoRMSRequestTbl(
			ExpenseRequestBean expenseRequestBean, boolean submitStatus, HttpSession session) {
		boolean status =false;
		String query = "";
		double countTotalExpense = 0;
		double countTotalBill = 0;
		Iterator<ExpenseBean> totalExpenseItr = expenseRequestBean.getExpenseList().iterator();
		while(totalExpenseItr.hasNext()){
			ExpenseBean temp = totalExpenseItr.next();
			countTotalExpense = countTotalExpense + temp.getRequestAmount();
			countTotalBill = countTotalBill + temp.getBillAmount();
			//System.out.println(countTotalBill);
		}
		if(countTotalExpense > countTotalBill){
			countTotalExpense = countTotalBill;
		}	
		expenseRequestBean.setRequestedAmount(countTotalExpense);
		expenseRequestBean.setApprovedAmount(countTotalExpense);
		try{
			if(submitStatus)
				query = GetQueryAPI.getQuery(RMS2,expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"),sdf.format(new Date()).toString(),(String)session.getAttribute("loggedInUser"),String.valueOf(countTotalExpense),String.valueOf(countTotalExpense),Review_Pending,(String)session.getAttribute("loggedInUser"),sdf.format(new Date()).toString(),"786");
			else
				query = GetQueryAPI.getQuery(RMS2,expenseRequestBean.getRequestNumber(),(String)session.getAttribute("loggedInUser"),sdf.format(new Date()).toString(),(String)session.getAttribute("loggedInUser"),String.valueOf(countTotalExpense),String.valueOf(countTotalExpense),Saved_as_Draft,(String)session.getAttribute("loggedInUser"),sdf.format(new Date()).toString(),"786");
			jdbcTemplate.update(query);
			status = true;
		}catch(Exception e){
			e.printStackTrace();
		}	
		return status;
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#generateBillRequestNo(javax.servlet.http.HttpSession)
	 */
	@Override
	public String generateBillRequestNo(HttpSession session) {
		// TODO Auto-generated method stub
		//String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String query = GetQueryAPI.getQuery(RMS1,(String)session.getAttribute("loggedInUser"));
		
		try {
			String requestNum = jdbcTemplate.queryForObject(query,String.class);
			//System.out.println("requestNum=="+requestNum);
			if(requestNum==null || "".equalsIgnoreCase(requestNum))
			{
				requestNum = (String)session.getAttribute("loggedInUserCode") +"_001";
			}
			//else if(requestNum.contains("SITS") || requestNum.contains("sits")){
			else{
				Integer num = new Integer(requestNum.substring(requestNum.lastIndexOf("_") + 1));
				num +=1;

				DecimalFormat dcfm = new DecimalFormat("000");

				requestNum = (String)session.getAttribute("loggedInUserCode")+"_"+ dcfm.format(num); 			

			}
			return requestNum;
		} catch (Exception e) {
			e.printStackTrace();
			return (String)session.getAttribute("loggedInUserCode") +"_001";
		}
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#getListOfExpenseType(javax.servlet.http.HttpSession)
	 */
	@Override
	public JSONArray getListOfExpenseType(HttpSession session) {
		String query = "";
		JSONArray jasonProjectData = new JSONArray();
		try{
			query = GetQueryAPI.getQuery(RMS5);
			List<JSONObject> expList= new ArrayList<JSONObject>();
			expList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONObject>>(){
				@Override
				public List<JSONObject> extractData(java.sql.ResultSet rsEffort)
						throws SQLException, DataAccessException {
			    	List<JSONObject> tempList= new ArrayList<JSONObject>();
						while(rsEffort.next()){
							JSONObject temp = new JSONObject();
							try {
								temp.put("expenseCode", rsEffort.getString(1));
								temp.put("expenseName", rsEffort.getString(2));
								tempList.add(temp);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
			        return tempList;
			    
				}
			});
			for(int i =0;i<expList.size();i++){
				jasonProjectData.put(expList.get(i));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jasonProjectData;
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#getAllPendingRequestList(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getAllPendingRequestList(HttpSession session,
			String filterVar,String bucketName) {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try{
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			String month = String.valueOf((c.get(Calendar.MONTH)) + 1);
				String billingEndDay = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS11),String.class);
				String billingEndDate = year+"-"+month+"-"+billingEndDay;
				if(filterVar.equalsIgnoreCase("pastUserRequest"))
					query = GetQueryAPI.getQuery(RMS12,session.getAttribute("loggedInUser").toString());
				else{
					/*if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(FINANACE)){
						if(filterVar.equalsIgnoreCase("Approved")){
							if(bucketName.equalsIgnoreCase("0"))
								query = GetQueryAPI.getQuery(RMS39,Approved);
							else
								query = GetQueryAPI.getQuery(RMS23,Approved,bucketName);
						}else{
							 query = GetQueryAPI.getQuery(RMS14,billingEndDate,Approval_Pending,billingEndDate,Review_Pending,session.getAttribute("loggedInUser").toString());
							}
					}else{
					  if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(VP_GROUP))
					  	query = GetQueryAPI.getQuery(RMS32,billingEndDate,VP_APPROVAL_PENDING);
					  else
						query = GetQueryAPI.getQuery(RMS6,billingEndDate,Review_Pending,session.getAttribute("loggedInUser").toString());  
					}*/
					if(filterVar.equalsIgnoreCase("Approved")){
						if(bucketName.equalsIgnoreCase("0"))
							query = GetQueryAPI.getQuery(RMS39,Approved);
						else
							query = GetQueryAPI.getQuery(RMS23,bucketName);//modified on Jan 29 2018
					}else{
						//List<List> currentReqList = new LinkedList<List>();
						if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(VP_GROUP)){
							//currentReqList = jdbcTemplate.queryForList(GetQueryAPI.getQuery(RMS47,billingEndDate, VP_GROUP,"G"),List.class);
							query = GetQueryAPI.getQuery(RMS47,billingEndDate, VP_GROUP,"G");
							query = query + " union " + GetQueryAPI.getQuery(RMS47,billingEndDate, session.getAttribute("loggedInUser").toString(),"U");
						}else{
							if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(FINANACE)){
								query = GetQueryAPI.getQuery(RMS47,billingEndDate, FINANACE,"G");
								query = query + " union " + GetQueryAPI.getQuery(RMS47,billingEndDate, session.getAttribute("loggedInUser").toString(),"U");	
							}else
								query = GetQueryAPI.getQuery(RMS47,billingEndDate, session.getAttribute("loggedInUser").toString(),"U");
						}
							//System.out.println(currentReqList);
						 	//query = GetQueryAPI.getQuery(RMS14,billingEndDate,Approval_Pending,billingEndDate,Review_Pending,session.getAttribute("loggedInUser").toString());
						}
				}
				System.out.println(query);
			prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>(){
			    @Override
				public List<JSONArray> extractData(java.sql.ResultSet rsEffort)
						throws SQLException, DataAccessException {
			    	List<JSONArray> tempList= new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
						while(rsEffort.next()){
							JSONObject jasonTimeEachData = new JSONObject();
							try {
								jasonTimeEachData.put("username", rsEffort.getString(1));
								jasonTimeEachData.put("fullname", rsEffort.getString(2));
								jasonTimeEachData.put("requestnumber", rsEffort.getString(3));
								jasonTimeEachData.put("creationdate", rsEffort.getString(4));
								jasonTimeEachData.put("requestedamount", rsEffort.getString(5));
								jasonTimeEachData.put("approvedamount", rsEffort.getString(6));
								jasonTimeEachData.put("reviewedby", rsEffort.getString(7));
								jasonTimeEachData.put("requeststatus", rsEffort.getString(8));
								jasonTimeEachData.put("reviewerremark", rsEffort.getString(9));
								jasonTimeEachData.put("approverremark", rsEffort.getString(10));
								if(null == rsEffort.getString(11))
									jasonTimeEachData.put("bucketname", "NA");
								else
									jasonTimeEachData.put("bucketname", rsEffort.getString(11));
							} catch (JSONException e) {
								e.printStackTrace();
							}
							jasonProjectData.put(jasonTimeEachData);
						}
							tempList.add(jasonProjectData);
							return tempList;
			    } 			
			});
		}catch(EmptyResultDataAccessException e){
			System.out.println("No row found in DB ");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#getPendingRequestDetails(javax.servlet.http.HttpSession, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getPendingRequestDetails(HttpSession session,
			String reqNumber) {
		String query = "";
		List<JSONArray> prjList = new ArrayList<JSONArray>();
		try{
			/*if(((List<String>)session.getAttribute("loggedInUserGroups")).contains(ADMIN) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(FINANACE) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(VP_GROUP) || ((List<String>)session.getAttribute("loggedInUserGroups")).contains(HR))
				query = GetQueryAPI.getQuery(RMS7,reqNumber);
			else{
				String queryAuthorizedUser = GetQueryAPI.getQuery(RMS53, reqNumber);
				String authorizedUser = jdbcTemplate.queryForObject(queryAuthorizedUser, String.class);
				if(session.getAttribute("loggedInUser").toString().equalsIgnoreCase(authorizedUser) && null != authorizedUser)	
					query = GetQueryAPI.getQuery(RMS7,reqNumber);
			}*/
			query = GetQueryAPI.getQuery(RMS7,reqNumber);
				prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>(){
			    @Override
				public List<JSONArray> extractData(java.sql.ResultSet rsEffort)
						throws SQLException, DataAccessException {
			    	List<JSONArray> tempList= new ArrayList<JSONArray>();
					JSONArray jasonProjectData = new JSONArray();
						while(rsEffort.next()){
							JSONObject jasonTimeEachData = new JSONObject();
							try {
								jasonTimeEachData.put("expensecode", rsEffort.getString(1));
								jasonTimeEachData.put("expensename", rsEffort.getString(2));
								jasonTimeEachData.put("billdate", rsEffort.getString(3));
								jasonTimeEachData.put("requestedamount", rsEffort.getString(4));
								jasonTimeEachData.put("approvedamount", rsEffort.getString(5));
								jasonTimeEachData.put("applicantremark", rsEffort.getString(6));
								if(null == rsEffort.getString(7) || "".equals(rsEffort.getString(7)))
									jasonTimeEachData.put("billnumber", "NA");
								else	
									jasonTimeEachData.put("billnumber", rsEffort.getString(7));
								jasonTimeEachData.put("billamount", rsEffort.getString(8));
								jasonTimeEachData.put("billattachment", rsEffort.getString(9));
								jasonTimeEachData.put("projectname", rsEffort.getString(10));
							} catch (JSONException e) {
								e.printStackTrace();
							}
							jasonProjectData.put(jasonTimeEachData);
						}
							tempList.add(jasonProjectData);
							return tempList;
			    } 			
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return prjList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#updateRMSRemarkAndStatus(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateRMSRemarkAndStatus(HttpSession session,
			String reqNumber, String approveFlag, String reqStatus,
			String remark) {
			boolean status = false;
			String query = "";
			String actionBy = session.getAttribute("loggedInUser").toString();
			String assignee = "";
			String updatedStatus = "";
			String currentReqNoLevel = "";
			String nextStatus = "";
			try{
				currentReqNoLevel = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS48,reqNumber),String.class);
				nextStatus = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS49,reqNumber,String.valueOf(Integer.parseInt(currentReqNoLevel) + 1)),String.class);
			}catch(Exception e){
				System.out.println(nextStatus);
				nextStatus = "";
				e.printStackTrace();
			}
			String updateApprovalListQuery = "";
		if(session.getAttribute("loggedInUserProxy").toString().length() > 0)
			actionBy = session.getAttribute("loggedInUserProxy").toString();
		 if(approveFlag.equalsIgnoreCase("true")){
			 if(null == nextStatus || "" == nextStatus){
				 //Request dumped in Documnet Verification Pending Bucket(id:2)
				 query = GetQueryAPI.getQuery(RMS9,Approved,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,"2",reqNumber);
				 assignee = "Finance Team";
				 updatedStatus = Approved;
				 updateApprovalListQuery = GetQueryAPI.getQuery(RMS52,reqNumber);
			 }else{
				 query = GetQueryAPI.getQuery(RMS8,nextStatus,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,reqNumber);
				 if(VP_APPROVAL_PENDING.equalsIgnoreCase(nextStatus)){
					 assignee = "VP Group";
					 updatedStatus = VP_APPROVAL_PENDING;
				 }else{
					 assignee = "Finance Team";
					 updatedStatus = Approval_Pending;
				 }
				 //updateApprovalListQuery = GetQueryAPI.getQuery(RMS50,nextStatus,reqNumber,String.valueOf(Integer.parseInt(currentReqNoLevel) + 1));
				 updateApprovalListQuery = GetQueryAPI.getQuery(RMS50,nextStatus,reqNumber);
				 //System.out.println(updateApprovalListQuery);
			 }
			 
			 
			 /*if(reqStatus.equalsIgnoreCase(Review_Pending)){
				 //Forwarded to Finance Department
				 if(Review_Pending.equalsIgnoreCase(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS13,reqNumber),String.class))){
					 query = GetQueryAPI.getQuery(RMS8,VP_APPROVAL_PENDING,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,reqNumber);
					 assignee = "VP Group";
					 updatedStatus = VP_APPROVAL_PENDING;
				 }
			 }
			 if(reqStatus.equalsIgnoreCase(VP_APPROVAL_PENDING)){
				 //Forwarded to Finance Department
				 if(VP_APPROVAL_PENDING.equalsIgnoreCase(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS13,reqNumber),String.class))){
					 query = GetQueryAPI.getQuery(RMS31,Approval_Pending,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,reqNumber);
					 assignee = "Finance Team";
					 updatedStatus = Approval_Pending;
				 }
			 }
			 if(reqStatus.equalsIgnoreCase(Approval_Pending)){
				 //Final approval by Finance department
				 if(Approval_Pending.equalsIgnoreCase(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS13,reqNumber),String.class))){
					 query = GetQueryAPI.getQuery(RMS9,Approved,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,"2",reqNumber);
					 assignee = "Finance Team";
					 updatedStatus = Approved;
				 }
			 }*/
			 
		 }
		 if(approveFlag.equalsIgnoreCase("false")){
			 updateApprovalListQuery = GetQueryAPI.getQuery(RMS52,reqNumber);
			 assignee = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS38,reqNumber),String.class);
			 if(reqStatus.equalsIgnoreCase(Review_Pending)){
				 //Rejected By Reviewer
				 if(Review_Pending.equalsIgnoreCase(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS13,reqNumber),String.class))){
					 query = GetQueryAPI.getQuery(RMS8,Review_Failed,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,reqNumber);
					 updatedStatus = Review_Failed;
				 }
		 }
			 if(reqStatus.equalsIgnoreCase(VP_APPROVAL_PENDING)){
				 //Forwarded to Finance Department
				 if(VP_APPROVAL_PENDING.equalsIgnoreCase(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS13,reqNumber),String.class))){
					 query = GetQueryAPI.getQuery(RMS31,VP_APPROVAL_FAILED,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,reqNumber);
					 updatedStatus = VP_APPROVAL_FAILED;
			 }
			 }
			 if(reqStatus.equalsIgnoreCase(Approval_Pending)){
				 //Rejected by Finance department
				 if(Approval_Pending.equalsIgnoreCase(jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS13,reqNumber),String.class))){
					 query = GetQueryAPI.getQuery(RMS9,Rejected,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),remark,reqNumber);
					 updatedStatus = Rejected;
			 }
			 }
		 }
		 if(approveFlag.equalsIgnoreCase(Withdraw)){
			 updateApprovalListQuery = GetQueryAPI.getQuery(RMS51,reqNumber,session.getAttribute("loggedInUser").toString());
			 assignee = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS38,reqNumber),String.class);
			 //Withdrawal by applicant him/herself.
			 query = GetQueryAPI.getQuery(RMS10,Withdraw,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),reqNumber,session.getAttribute("loggedInUser").toString());
			 updatedStatus = Withdraw;
		 }
		 if(approveFlag.equalsIgnoreCase(Delete_Request)){
			 updateApprovalListQuery = GetQueryAPI.getQuery(RMS51,reqNumber,session.getAttribute("loggedInUser").toString());
			 assignee = "Not Applicable";
			 //Deleted by applicant him/herself.
			 query = GetQueryAPI.getQuery(RMS10,Delete_Request,session.getAttribute("loggedInUser").toString(),sdf.format(new Date()),reqNumber,session.getAttribute("loggedInUser").toString());
			 updatedStatus = Delete_Request;
		 }
	     try{
	    	//System.out.println(query);
			int updateCount = jdbcTemplate.update(query);
			jdbcTemplate.update(updateApprovalListQuery);
			if(updateCount != 0){
				status = true;
				String temp = "";
				if(approveFlag.equalsIgnoreCase("true") || approveFlag.equalsIgnoreCase("false"))
					temp = reqStatus;
				else
					temp = approveFlag;
				//System.out.println("query to rms action history :: "+GetQueryAPI.getQuery(RMS33, updatedStatus,reqNumber,reqNumber.substring(0, reqNumber.indexOf("_")),actionBy,remark,assignee));
					jdbcTemplate.update(GetQueryAPI.getQuery(RMS33, updatedStatus,reqNumber,reqNumber.substring(0, reqNumber.indexOf("_")),actionBy,remark,assignee));
			}	
			if(status){
				if(checkMailNotificationTrigger(RMS_REQUEST_STATUS_MODIFIED)){
					final String toMailId = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM11,jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS38,reqNumber),String.class)),String.class);
					final String toMailIdReportingMangr = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM11,(String)session.getAttribute("reportingManagerUserName")),String.class);
					final String currentReqStatus = updatedStatus;
					final String mailContent = "Request status: "+ currentReqStatus + "\n Remark: "+remark;
					final String mailContentRM = "Reimbursement request " +reqNumber + "\n Requested By: "+ session.getAttribute("loggedInUserFullName") +"("+session.getAttribute("loggedInUser")+")";
					final String mailSubject ="Reimbursement request " +reqNumber + " status has been updated";
					final String requestTempNumber = reqNumber;
					final String finalRequestTempNumber = currentReqStatus;
					Iterator<ExpenseBean> itrExp = getExpenseListByReqNumber(reqNumber).iterator();
					Map<String,Object> expContent = new HashMap<String,Object>();
					while(itrExp.hasNext()){
						ExpenseBean temp =itrExp.next();
						//System.out.println(temp.getCodeName());
						if(null == temp.getCodeName()){
							continue;
						}
						expContent.put(temp.getCodeName(), temp);
					}
					final Map<String,Object> expContentFinal = expContent;
					final Map<String,Object> expContentHistoryFinal = getRMSRequestActionHistory(reqNumber);
					final String requestedBy = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS38,reqNumber),String.class);
					final String reqAmount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS45,reqNumber),String.class);
					Thread t = new Thread(){
						public void run(){
							Map<String,Object> mailContentMap = new HashMap<String,Object>();
							mailContentMap.put("reqMailNumber",requestTempNumber);
							mailContentMap.put("currStatus", finalRequestTempNumber);
							mailContentMap.put("expHistoryContent", expContentHistoryFinal);
							mailContentMap.put("requestedBy", requestedBy);
							mailContentMap.put("reqAmount", reqAmount);
							mailContentMap.put("expContent", expContentFinal);
							mailContentMap.put("expHistoryContent", expContentHistoryFinal);
							
							//Mail intimation to User him/herself on each status update.
							doSendTemplateEmail(toMailId, mailSubject, mailContentMap,"rmsUpdateIntimationToUser.vm");
							
							//Mail intimation to Manager/VP Group/Finanace Group for further action
							
							if(currentReqStatus.equalsIgnoreCase(VP_APPROVAL_PENDING)){
									@SuppressWarnings("unchecked")
									List<String> vpGroupUserMail = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS43,VP_GROUP), List.class);
									for(String email : vpGroupUserMail)
										doSendTemplateEmail(email, "Reimbursement request "+ requestTempNumber +" pending for further action", mailContentMap,"rmsUpdateIntimationToGroup.vm");
							}if(currentReqStatus.equalsIgnoreCase(Approval_Pending)){
									@SuppressWarnings("unchecked")
									List<String> vpGroupUserMail = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS43,FINANACE), List.class);
									for(String email : vpGroupUserMail)
										doSendTemplateEmail(email, "Reimbursement request "+ requestTempNumber +" pending for further action", mailContentMap,"rmsUpdateIntimationToGroup.vm");
							}if(currentReqStatus.equalsIgnoreCase(Withdraw)){
										doSendTemplateEmail(toMailIdReportingMangr, "Reimbursement request "+requestTempNumber+" has been withdrawn by user", mailContentMap, "rmsWithdrawnIntimationToRM.vm");
							}
						}
					};
					t.start();
				}
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#fetchBillRequestDetail(javax.servlet.http.HttpSession, com.supraits.viewBean.ExpenseRequestBean, java.lang.String)
	 */
	@Override
	public ExpenseRequestBean fetchBillRequestDetail(HttpSession session,ExpenseRequestBean expenseRequestBean,
			String requestNumber) {
		String query = "";
		
		try{
			query = GetQueryAPI.getQuery(RMS15,requestNumber,session.getAttribute("loggedInUser").toString());
			expenseRequestBean = jdbcTemplate.query(query, new ResultSetExtractor<ExpenseRequestBean>(){
			    @Override
				public ExpenseRequestBean extractData(java.sql.ResultSet rsEffort)
						throws SQLException, DataAccessException {
			    	ExpenseRequestBean temp= new ExpenseRequestBean();
						while(rsEffort.next()){
							temp.setRequestNumber(rsEffort.getString(1));
							temp.setUsername( rsEffort.getString(2));
							temp.setCreatedOn( rsEffort.getString(3));
							temp.setCreatedBy( rsEffort.getString(4));
							temp.setRequestedAmount(Double.parseDouble( rsEffort.getString(5)));
							temp.setApprovedAmount(Double.parseDouble( rsEffort.getString(6)));
							temp.setRequestStatus( rsEffort.getString(7));
							temp.setReviewerRemark( rsEffort.getString(8));
							temp.setApproverRemark( rsEffort.getString(9));
							temp.setExpenseList(getExpenseListByReqNumber(rsEffort.getString(1)));
						}
							return temp;
			    }
			});
		}catch(Exception e){
			e.printStackTrace();
		}	
		return expenseRequestBean;
	}
	public List<ExpenseBean> getExpenseListByReqNumber(
			String reqNo) {
		//System.out.println(GetQueryAPI.getQuery(RMS16,reqNo));
		List<ExpenseBean> expList = new ArrayList<ExpenseBean>();
		expList = jdbcTemplate.query(GetQueryAPI.getQuery(RMS16,reqNo), new ResultSetExtractor<List<ExpenseBean>>(){

			@Override
			public List<ExpenseBean> extractData(
					java.sql.ResultSet expRSSet) throws SQLException,
					DataAccessException {
				List<ExpenseBean> expTempList = new ArrayList<ExpenseBean>();
				while(expRSSet.next()){
					ExpenseBean tempExpObj = new ExpenseBean();
					tempExpObj.setExpenseCode(expRSSet.getString(1));
					tempExpObj.setBillNumber(expRSSet.getString(2));
					tempExpObj.setRequestAmount(Double.parseDouble( expRSSet.getString(3)));
					tempExpObj.setApprovedAmount(Double.parseDouble( expRSSet.getString(4)));
					tempExpObj.setBillAmount(Double.parseDouble( expRSSet.getString(5)));
					tempExpObj.setExpenseName(expRSSet.getString(6));
					tempExpObj.setBillDate(expRSSet.getString(7));
					tempExpObj.setApplicantRemark(expRSSet.getString(8));
					tempExpObj.setCodeName(expRSSet.getString(9));
					if(null == expRSSet.getString(10))
						tempExpObj.setExpDocName("NA");
					else
						tempExpObj.setExpDocName(expRSSet.getString(10));
					tempExpObj.setProjectName(expRSSet.getString(11));
					expTempList.add(tempExpObj);
				}
				
				return expTempList;
			}
			
		});
		//System.out.println(expList);
		//System.out.println(expList.size());
		return expList;
	}
	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#getAttachmentDetails(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
	 */
	@Override
	public ExpenseDocumentBean getAttachmentDetails(HttpSession session,
			String requestNumber, String expenseCode) {
		String query = "";
		ExpenseDocumentBean expenseDocumentBean = new ExpenseDocumentBean();
		try{
			query = GetQueryAPI.getQuery(RMS17,requestNumber,expenseCode);
			expenseDocumentBean = jdbcTemplate.query(query, new ResultSetExtractor<ExpenseDocumentBean>(){
			    @Override
				public ExpenseDocumentBean extractData(java.sql.ResultSet rsEffort)
						throws SQLException, DataAccessException {
			    	ExpenseDocumentBean temp= new ExpenseDocumentBean();
						while(rsEffort.next()){
							temp.setDocName(rsEffort.getString(1));
							temp.setDocId(rsEffort.getString(2));
							temp.setDocName(rsEffort.getString(3));
							temp.setDocSize(rsEffort.getString(4));
							temp.setUploadedby(rsEffort.getString(5));
							temp.setUploadedon(rsEffort.getString(6));
							temp.setDocFlag(rsEffort.getString(7));
							temp.setDoctype( rsEffort.getString(8));
							temp.setData( rsEffort.getBytes(9));
						}
						return temp;
						}
			});
		}catch(Exception e){
			e.printStackTrace();
		}	
		return expenseDocumentBean;
	}

	/* (non-Javadoc)
	 * @see com.supraits.service.ExpenseService#updateRMSDocFlagStatus(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateRMSDocFlagStatus(HttpSession session,
			String reqNumber, String expenseCode) {
		boolean status = false;
		String query = "";
		try{
			query = GetQueryAPI.getQuery(RMS22, reqNumber,expenseCode);
			int updateStatus = jdbcTemplate.update(query);
			if(updateStatus != 0){
				updateStatus = jdbcTemplate.update(GetQueryAPI.getQuery(RMS36, reqNumber,expenseCode));
				if(updateStatus != 0)
					status = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return status;
	}

/**
 * Check mail notification trigger.
 *
 * @param requestActionId the request action id
 * @return true, if successful
 */
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

/**
 * Do send email.
 *
 * @param recipientAddress the recipient address
 * @param subject the subject
 * @param message the message
 * @return the string
 */
public String doSendEmail(String recipientAddress,String subject,String message) {
	SimpleMailMessage email = new SimpleMailMessage();
	try{
	email.setFrom(ADMIN_MAIL);	
	email.setTo(recipientAddress);
	email.setSubject(subject);
	email.setText(message);
	mailSender.send(email);
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

/**
 * Gets the user mail id.
 *
 * @param uid the uid
 * @return the user mail id
 */
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

/* (non-Javadoc)
 * @see com.supraits.service.ExpenseService#generateFile(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
 */
@Override
public boolean generateFile(HttpSession session, String requestNumber,String fileName) {
	boolean status = false;
	try {
		String FILE = System.getProperty("user.dir")+ File.separator + fileName;
        Document document = new Document();
        //System.out.println("doc"+FILE);
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        addMetaData(session,document,requestNumber);
        addContent(session,document,requestNumber);
        document.close();
        status = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
	return status;
}

/**
 * Adds the content.
 *
 * @param session the session
 * @param document the document
 * @param requestNumber the request number
 * @throws DocumentException the document exception
 */
private void addContent(HttpSession session, Document document, String requestNumber) throws DocumentException {
	
		  Map<String,String> empDetails = getEmployeeDetails(session,(String)session.getAttribute("loggedInUser"));	
		  Map<String,ExpenseBean> empExpensesDetails = getEmpExpensesDetails((String)session.getAttribute("loggedInUser"),requestNumber);
		  //System.out.println(empExpensesDetails.values());
	 	  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	      Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	      Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	      Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
	      Paragraph title = new Paragraph();
	        addEmptyLine(title, 1);
	        title.add(new Paragraph("Reimbursement Form", catFont));
	        addEmptyLine(title, 1);
	        title.add(new Paragraph("Form generated by: " + (String)session.getAttribute("loggedInUser") + ", " + new Date(),smallBold));
	        addEmptyLine(title, 3);
	        title.add(new Paragraph("To, Finance Department Supra ITS ",subFont));
	        addEmptyLine(title, 3);
	        Iterator<String> expTotalExpItr = empExpensesDetails.keySet().iterator();
	        double totalExp = 0;
	        while(expTotalExpItr.hasNext()){
	        	String temp = expTotalExpItr.next();
	        	totalExp = totalExp + empExpensesDetails.get(temp).getApprovedAmount();
	        }
	        title.add(new Paragraph("I have incurred the following expenses of Rs." +totalExp+ " which may kindly be reimbursed. ",subFont));
	        addEmptyLine(title, 3);
	        document.add(title);
	        PdfPTable empDetailTable = new PdfPTable(2);
	        Iterator<String> empItr = empDetails.keySet().iterator();
	        while(empItr.hasNext()){
	        	String temp = empItr.next();
	        	empDetailTable.addCell(temp);
		        empDetailTable.addCell(empDetails.get(temp));
	        }
	        document.add(empDetailTable);
	        addEmptyLine(title, 5);
	        Paragraph title1 = new Paragraph("Reimbursement Details", catFont);
	        addEmptyLine(title1, 2);
	        document.add(title1);
	        PdfPTable expDetailTable = new PdfPTable(5);
	        
	        PdfPCell c1 = new PdfPCell(new Phrase("Date"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expDetailTable.addCell(c1);
	         c1 = new PdfPCell(new Phrase("Expense Type"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expDetailTable.addCell(c1);
	         c1 = new PdfPCell(new Phrase("Purpose"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expDetailTable.addCell(c1);
	         c1 = new PdfPCell(new Phrase("Amount(INR)"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expDetailTable.addCell(c1);
	         c1 = new PdfPCell(new Phrase("*Bill Attached"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expDetailTable.addCell(c1);
	        expDetailTable.setHeaderRows(1);
	        
	        Iterator<String> expItr = empExpensesDetails.keySet().iterator();
	        while(expItr.hasNext()){
	        	String temp = expItr.next();
	        	expDetailTable.addCell(empExpensesDetails.get(temp).getBillDate());
	        	expDetailTable.addCell(empExpensesDetails.get(temp).getCodeName());
	        	expDetailTable.addCell(empExpensesDetails.get(temp).getExpenseName());
	        	expDetailTable.addCell(String.valueOf(empExpensesDetails.get(temp).getApprovedAmount()));
	        	expDetailTable.addCell(empExpensesDetails.get(temp).getExpDocName());
	        }
	        addEmptyLine(title, 5);
	        document.add(expDetailTable);
	        addEmptyLine(title, 5);
	        document.add(new Paragraph("* If Original Bill are available kindly submit them separately to Accounts Department. ",redFont));
	        addEmptyLine(title, 1);
	        document.add(new Paragraph("Your Email Signature ",subFont));
}

/**
 * Gets the emp expenses details.
 *
 * @param attribute the attribute
 * @param requestNumber the request number
 * @return the emp expenses details
 */
private Map<String, ExpenseBean> getEmpExpensesDetails(String username,
		String requestNumber) {
	Map<String, ExpenseBean> tempExpMap = new TreeMap<String, ExpenseBean>();
	String query = "";
	try {
		String queryAuthorizedUser = GetQueryAPI.getQuery(RMS53, requestNumber);
		String authorizedUser = jdbcTemplate.queryForObject(queryAuthorizedUser, String.class);
		if(username.equalsIgnoreCase(authorizedUser) && null != authorizedUser)
			query = GetQueryAPI.getQuery(RMS21, requestNumber);
		//System.out.println(query);
		tempExpMap = jdbcTemplate.query(query, new ResultSetExtractor<Map<String,ExpenseBean>>(){
			@Override
			public Map<String, ExpenseBean> extractData(java.sql.ResultSet resultSetExp)
					throws SQLException, DataAccessException {
				Map<String, ExpenseBean> temp = new TreeMap<String, ExpenseBean>();
				while(resultSetExp.next()){
					ExpenseBean tempExpObj = new ExpenseBean();
					tempExpObj.setExpenseCode(resultSetExp.getString(1));
					tempExpObj.setCodeName(resultSetExp.getString(2));
					tempExpObj.setExpenseName(resultSetExp.getString(3));
					tempExpObj.setBillDate(resultSetExp.getString(4));
					tempExpObj.setApprovedAmount(Double.valueOf(resultSetExp.getString(5)));
					if(null == resultSetExp.getString(6))
						tempExpObj.setExpDocName("N");
					else
						tempExpObj.setExpDocName(resultSetExp.getString(6));
					tempExpObj.setProjectName(resultSetExp.getString(7));
					temp.put(tempExpObj.getExpenseCode(),tempExpObj);
				}
				return temp;
			}
		});
	} catch (DataAccessException e) {
		e.printStackTrace();
	}
	return tempExpMap;
}

/**
 * Gets the employee details.
 *
 * @param session the session
 * @param username the username
 * @return the employee details
 */
private Map<String, String> getEmployeeDetails(HttpSession session,String username) {
	Map<String,String> empDetails = new TreeMap<String,String>();
	empDetails.put("Employee Name", (String) session.getAttribute("loggedInUserFullName") +"("+username+")");
	empDetails.put("Expense Period", "");
	empDetails.put("Department", (String) session.getAttribute("loggedInUserDepartment"));
	empDetails.put("Manager", (String) session.getAttribute("reportingManager"));
	empDetails.put("Approved By", "Finance Department-Noida");
	return empDetails;
}

/**
 * Adds the empty line.
 *
 * @param paragraph the paragraph
 * @param number the number
 */
private void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
        paragraph.add(new Paragraph(" "));
    }
}

/**
 * Adds the meta data.
 *
 * @param session the session
 * @param document the document
 * @param requestNumber the request number
 */
private void addMetaData(HttpSession session, Document document, String requestNumber) {
	// TODO Auto-generated method stub
	
}

/* (non-Javadoc)
 * @see com.supraits.service.ExpenseService#updateRMSBucketofRequests(javax.servlet.http.HttpSession, java.lang.String, java.util.List)
 */
@Override
public boolean updateRMSBucketofRequests(HttpSession session,String expenseBucket,
		List<String> reqList) {
	String query = "";
	boolean status= false;
	try{
		Iterator<String> itrReq = reqList.iterator();
		while(itrReq.hasNext()){
			String tempReq = itrReq.next();
			query = GetQueryAPI.getQuery(RMS24,expenseBucket,tempReq);
			int updateStatus = jdbcTemplate.update(query);
			if(updateStatus != 0){
				jdbcTemplate.update(GetQueryAPI.getQuery(RMS33,expenseBucket,tempReq,tempReq.substring(0, tempReq.indexOf("_")),session.getAttribute("loggedInUserProxy").toString().equalsIgnoreCase("")?session.getAttribute("loggedInUser").toString():session.getAttribute("loggedInUserProxy").toString(),"NA","Finance Team"));
			}
		}
		status = true;
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	return status;
}

/* (non-Javadoc)
 * @see com.supraits.service.ExpenseService#getBucketList(javax.servlet.http.HttpSession)
 */
@Override
public JSONArray getBucketList(HttpSession session) {
	// TODO Auto-generated method stub
	JSONArray bucketList = new JSONArray();
	bucketList = jdbcTemplate.query(GetQueryAPI.getQuery(RMS25), new ResultSetExtractor<JSONArray>(){

		@Override
		public JSONArray extractData(java.sql.ResultSet rsBucket)
				throws SQLException, DataAccessException {
			JSONArray tempList = new JSONArray();
			while(rsBucket.next()){
				JSONObject obj = new JSONObject();
				try {
					obj.put("bucketid",rsBucket.getString(1));
					obj.put("bucketname",rsBucket.getString(2));
					obj.put("bucketcreatedby",rsBucket.getString(3));
					obj.put("bucketcreatedon",rsBucket.getString(4));
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

/* (non-Javadoc)
 * @see com.supraits.service.ExpenseService#removeRMSBucket(javax.servlet.http.HttpSession, java.lang.String)
 */
@Override
public boolean removeRMSBucket(HttpSession session, String bucektId) {
	int prevEntryCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS30, bucektId),Integer.class);
	if(prevEntryCount > 0)
		return false;
	else{
		 jdbcTemplate.update(GetQueryAPI.getQuery(RMS27, bucektId));
		 return true;
	}	
}

/* (non-Javadoc)
 * @see com.supraits.service.ExpenseService#updateRMSBucketName(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
 */
@Override
public boolean updateRMSBucketName(HttpSession session, String bucketName,
		String bucektId) {
	int updateCount = jdbcTemplate.update(GetQueryAPI.getQuery(RMS28,bucketName, bucektId));
	if(updateCount == 1)
		return true;
	else
		return false;
}

/* (non-Javadoc)
 * @see com.supraits.service.ExpenseService#createRMSBucket(javax.servlet.http.HttpSession, java.lang.String)
 */
@Override
public boolean createRMSBucket(HttpSession session, String bucketName) {
	int createCount = jdbcTemplate.update(GetQueryAPI.getQuery(RMS29,bucketName,(String)session.getAttribute("loggedInUser"),sdf.format(new Date()).toString()));
	if(createCount == 1)
		return true;
	else
		return false;
}

@Override
public JSONArray getRMSBucketCount(HttpSession session) throws JSONException{
	JSONArray bucketValues = new JSONArray();
	String query = "";
	try{
		query = GetQueryAPI.getQuery(RMS54);
		bucketValues = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				try {
					while(rs.next()){
						JSONObject tempObj = new JSONObject();
						tempObj.put("bucketCount", rs.getString(1));
						tempObj.put("bucketName", rs.getString(2));
						tempObj.put("bucketId", rs.getString(3));
						temp.put(tempObj);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return temp;
			}
			
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return bucketValues;
}

@Override
public JSONArray getExpenseStatusList(HttpSession session) {
	JSONArray bucketValues = new JSONArray();
	String query = "";
	try{
		query = GetQueryAPI.getQuery(RMS56);
		bucketValues = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				try {
					while(rs.next()){
						JSONObject tempObj = new JSONObject();
						tempObj.put("status", rs.getString(1));
						temp.put(tempObj);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return temp;
			}
			
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return bucketValues;

}
@Override
public JSONArray getUserList(HttpSession session) {
	JSONArray bucketValues = new JSONArray();
	String query = "";
	try{
		query = GetQueryAPI.getQuery(TM36);
		bucketValues = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				try {
					while(rs.next()){
						JSONObject tempObj = new JSONObject();
						tempObj.put("username", rs.getString(1));
						tempObj.put("fullname", rs.getString(2));
						temp.put(tempObj);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return temp;
			}
			
		});
	}catch(Exception e){
		e.printStackTrace();
	}
	return bucketValues;

}

@Override
public JSONArray viewExpenseReport(HttpSession session,String projectName, String username,
		String bucketId, String startDate, String endDate, String status) {

	JSONArray expenseReportData = new JSONArray();
	String query = "";
	String appendQuery = "";
	try{
		if(!(bucketId.equalsIgnoreCase("")))
			appendQuery = "and r.bucketid='"+bucketId+"'";
		if(!(status.equalsIgnoreCase("")))
			appendQuery = appendQuery + " and r.status='"+status+"'";
		if(!(username.equalsIgnoreCase("")))
			appendQuery = appendQuery + " and r.username='"+username+"'";
		if(!(projectName.equalsIgnoreCase("")))
			appendQuery = appendQuery + " and m.projectName='"+projectName+"'";
		query = GetQueryAPI.getQuery(RMS57,startDate,endDate,appendQuery);
		System.out.println(query);
		expenseReportData = jdbcTemplate.query(query, new ResultSetExtractor<JSONArray>(){

			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray temp = new JSONArray();
				while(rs.next()){
					JSONObject tempObj = new JSONObject();
					try {
						tempObj.put("fullname", rs.getString(1));
						tempObj.put("rmname", rs.getString(2));
						tempObj.put("requestedamount", rs.getString(3));
						tempObj.put("approvedamount", rs.getString(4));
						tempObj.put("status", rs.getString(5));
						tempObj.put("bucketname", rs.getString(6));
						tempObj.put("lastmodifiedon", rs.getString(7));
						tempObj.put("lastmodifiedby", rs.getString(8));
						tempObj.put("createdon", rs.getString(9));
						tempObj.put("requestnumber", rs.getString(10));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					temp.put(tempObj);
				}
				return temp;
			}
		});
		List<String> reqIds = new LinkedList<String>();
		for(int i=0;i<expenseReportData.length();i++){
			reqIds.add(expenseReportData.getJSONObject(i).getString("requestnumber"));
		}
		if(reqIds.size() > 0 && reqIds != null){
			final List<String> reqIdsFinal = reqIds;
			Thread t = new Thread(){
				public void run(){
					generateCurrentExpenseReport(reqIdsFinal);
				}
			};
			t.start();
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return expenseReportData;
}
private void generateCurrentExpenseReport(List<String> reqIdsFinal) {
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("reqIds", reqIdsFinal);
	try{
		String query = GetQueryAPI.getQuery(RMS58);
		JSONArray reportData = namedParameterJdbcTemplate.query(query,parameters,new ResultSetExtractor<JSONArray>() {
			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				JSONArray reportDataTemp = new JSONArray();
				while(rs.next()){
					JSONObject temp = new JSONObject();
					try{
						temp.put("username",rs.getString(1));
						temp.put("fullname",rs.getString(2));
						temp.put("email",rs.getString(3));	
						temp.put("rmfullname",rs.getString(4));
						temp.put("rmemail",rs.getString(5));
						temp.put("expensehead",rs.getString(6));
						temp.put("projectname",rs.getString(7));
						temp.put("expensedesc",rs.getString(8));	
						temp.put("billdate",rs.getString(9));
						temp.put("billamount",rs.getString(10));
						temp.put("approvedamount",rs.getString(11));
						temp.put("createdon",rs.getString(12));
						temp.put("applicantremark",rs.getString(13));
						temp.put("billflag",rs.getString(14));
						temp.put("status",rs.getString(15));
						temp.put("requestnumber",rs.getString(16));
					}catch(Exception e){
						e.printStackTrace();
					}
					reportDataTemp.put(temp);
				}
				return reportDataTemp;
			}
		});
		generateExpenseXLSReport(reportData);
	}catch(Exception e){
		e.printStackTrace();
	}
}
private void generateExpenseXLSReport(JSONArray reportData) {
	boolean statusFile = false;
	List<List<String>> recordToAdd = new ArrayList<List<String>>();
	try{
		List<String> headerRow = new ArrayList<String>();
		headerRow.add("Employee Id");
		headerRow.add("Employee Name");
		headerRow.add("Employee Mail");
		headerRow.add("Supervisor Name");
		headerRow.add("Supervisor Mail");
		headerRow.add("Request No");
		headerRow.add("Status");
		headerRow.add("Expense Type");
		headerRow.add("Project Name");
		headerRow.add("Expense Desc");
		headerRow.add("Bill Date");
		headerRow.add("Bill Amount");
		headerRow.add("Approved Amount");
		headerRow.add("Applied Date");
		headerRow.add("Purpose");
		headerRow.add("Bill Attached");
		
		recordToAdd.add(headerRow);
		
		for(int i=0;i<reportData.length();i++){
			List<String> recordRow = new ArrayList<String>();
			recordRow.add(reportData.getJSONObject(i).getString("username"));
			recordRow.add(reportData.getJSONObject(i).getString("fullname"));
			recordRow.add(reportData.getJSONObject(i).getString("email"));
			recordRow.add(reportData.getJSONObject(i).getString("rmfullname"));
			recordRow.add(reportData.getJSONObject(i).getString("rmemail"));
			recordRow.add(reportData.getJSONObject(i).getString("requestnumber"));
			recordRow.add(reportData.getJSONObject(i).getString("status"));
			recordRow.add(reportData.getJSONObject(i).getString("expensehead"));
			recordRow.add(reportData.getJSONObject(i).getString("projectname"));
			recordRow.add(reportData.getJSONObject(i).getString("expensedesc"));
			recordRow.add(reportData.getJSONObject(i).getString("billdate"));
			recordRow.add(reportData.getJSONObject(i).getString("billamount"));
			recordRow.add(reportData.getJSONObject(i).getString("approvedamount"));
			recordRow.add(reportData.getJSONObject(i).getString("createdon"));
			recordRow.add(reportData.getJSONObject(i).getString("applicantremark"));
			recordRow.add(reportData.getJSONObject(i).getString("billflag"));
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
		 tempString = "ExpenseReport" + tempString;
		 try (FileOutputStream outputStream = new FileOutputStream(tempString)) {
	            workbook.write(outputStream);
	            statusFile = true;
	        }
	}catch(Exception e){
		e.printStackTrace();
	}
}

@Override
public JSONArray getProjectListForReimbursement(HttpSession session) {
	JSONArray projList = new JSONArray();
	try{
		List<String> projectList = (List<String>) jdbcTemplate.queryForList(GetQueryAPI.getQuery(RMS59),String.class);
		for(String p:projectList){
			JSONObject temp = new JSONObject();
			temp.put("projectName", p);
			projList.put(temp);
		}
	}catch(Exception e){
		System.out.println("No project Found");
		e.printStackTrace();
	}
	return projList;
}

@Override
public JSONArray getExpensesListUnderLoggedInUser(HttpSession session,
		String status) {
	String query = "";
	List<JSONArray> prjList = new ArrayList<JSONArray>();
	try{
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf((c.get(Calendar.MONTH)) + 1);
			String billingEndDay = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(RMS11),String.class);
			String billingEndDate = year+"-"+month+"-"+billingEndDay;
			if(status.equalsIgnoreCase("Approved")){
				query = GetQueryAPI.getQuery(RMS60, session.getAttribute("loggedInUser").toString(), session.getAttribute("loggedInUser").toString());
			}else{
				query = GetQueryAPI.getQuery(RMS61, session.getAttribute("loggedInUser").toString(), session.getAttribute("loggedInUser").toString());
			}
			System.out.println(query);
		prjList = jdbcTemplate.query(query, new ResultSetExtractor<List<JSONArray>>(){
		    @Override
			public List<JSONArray> extractData(java.sql.ResultSet rsEffort)
					throws SQLException, DataAccessException {
		    	List<JSONArray> tempList= new ArrayList<JSONArray>();
				JSONArray jasonProjectData = new JSONArray();
					while(rsEffort.next()){
						JSONObject jasonTimeEachData = new JSONObject();
						try {
							jasonTimeEachData.put("username", rsEffort.getString(1));
							jasonTimeEachData.put("fullname", rsEffort.getString(2));
							jasonTimeEachData.put("requestnumber", rsEffort.getString(3));
							jasonTimeEachData.put("creationdate", rsEffort.getString(4));
							jasonTimeEachData.put("requestedamount", rsEffort.getString(5));
							jasonTimeEachData.put("approvedamount", rsEffort.getString(6));
							jasonTimeEachData.put("reviewedby", rsEffort.getString(7));
							jasonTimeEachData.put("requeststatus", rsEffort.getString(8));
							jasonTimeEachData.put("reviewerremark", rsEffort.getString(9));
							jasonTimeEachData.put("approverremark", rsEffort.getString(10));
							if(null == rsEffort.getString(11))
								jasonTimeEachData.put("bucketname", "NA");
							else
								jasonTimeEachData.put("bucketname", rsEffort.getString(11));
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jasonProjectData.put(jasonTimeEachData);
					}
						tempList.add(jasonProjectData);
						return tempList;
		    } 			
		});
	}catch(EmptyResultDataAccessException e){
		System.out.println("No row found in DB ");
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return prjList.get(0);
}
}