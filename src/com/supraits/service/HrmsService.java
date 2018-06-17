package com.supraits.service;

import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.supraits.viewBean.NotificationDocBean;

public interface HrmsService {

	JSONArray getDocParams(HttpSession session,String docType);

	String createEmployeeDoc(HttpSession session, String dataString,
			String docType);

	String punchedTimeInDB(HttpSession session, String clientIp) throws URISyntaxException;
	
	String punchedTimeInDBByAPI(HttpSession session,String countryCode,String city,String region,String loc,String clientIp);
	
	JSONArray checkUserExistingPunchData(HttpSession session);

	boolean insertNewNotificationToTable(HttpSession session,String notificationrfnum, String type,
			String description, String policyGroup, String title,
			String attachment);

	JSONArray getAllCompNotification(HttpSession session);

	NotificationDocBean getAttachmentDetails(HttpSession session,
			String notificationid);

	JSONArray fetchUserPunchedData(HttpSession session, String startDate,
			String endDate, String username);

	boolean removeCompanyNotificationLeave(HttpSession session,
			String notificationid);

	boolean updateNotification(HttpSession session, String notificationid,
			boolean status);

	JSONArray getRTEDocTemplate(HttpSession session, String docType);

	String createNewTemplate(HttpSession session, String policyGroup,
			String userGroup, String docType, String docDesc,
			String templateName, String templateData);

	String fetchGroupListBasedOnPolicy(HttpSession session, String param);

	String fetchDocListBasedOnUserGroup(HttpSession session, String param);

	String fetchTemplateListBasedOnDocName(HttpSession session, String param);

	String generateNewDocument(HttpSession session, String username,
			String fullname,String docName,String email, String templateData);

	String fetchAllGeneretdDocList(HttpSession session, String activeFlag);

	boolean generatePDFFile(HttpSession session, String rfnum, String tempString);

	boolean generateDOCFile(HttpSession session, String rfnum, String tempString);

	boolean removeGeneratedDoc(HttpSession session, String docrfnum);

	Object getEmpDocumentData(HttpSession session, String docrfnum);

	boolean updateGeneratedTemplateDoc(HttpSession session, String docrfnum,
			String templateData);
}
