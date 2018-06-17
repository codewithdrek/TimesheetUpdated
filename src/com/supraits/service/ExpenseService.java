package com.supraits.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;

import com.supraits.viewBean.ExpenseDocumentBean;
import com.supraits.viewBean.ExpenseRequestBean;

public interface ExpenseService {
	public boolean newExpenseRequest();

	public boolean insertNewExpenseRequest(HttpSession session,
			ExpenseRequestBean expenseRequestBean, boolean submitStatus);

	public String generateBillRequestNo(HttpSession session);

	public JSONArray getListOfExpenseType(HttpSession session);

	public JSONArray getAllPendingRequestList(HttpSession session,
			String filterVar,String expenseBucket);

	public JSONArray getPendingRequestDetails(HttpSession session,
			String reqNumber);

	public boolean updateRMSRemarkAndStatus(HttpSession session,
			String reqNumber, String approveFlag, String reqStatus,
			String remark);

	public ExpenseRequestBean fetchBillRequestDetail(HttpSession session,
			ExpenseRequestBean expenseRequestBean, String requestNumber);

	public ExpenseDocumentBean getAttachmentDetails(HttpSession session,
			String requestNumber, String expenseCode);

	public boolean updateRMSDocFlagStatus(HttpSession session,
			String reqNumber, String expenseCode);

	public boolean generateFile(HttpSession session, String requestNumber, String tempString);

	public boolean updateRMSBucketofRequests(HttpSession session,
			String expenseBucket, List<String> reqArray);

	public JSONArray getBucketList(HttpSession session);

	public boolean removeRMSBucket(HttpSession session, String bucektId);

	public boolean updateRMSBucketName(HttpSession session, String bucketName,
			String bucektId);

	public boolean createRMSBucket(HttpSession session, String bucketName);

	public Map<String, Object> getRMSRequestActionHistory(String requestNumber);

	public JSONArray getRMSBucketCount(HttpSession session) throws JSONException;

	public JSONArray getExpenseStatusList(HttpSession session);

	JSONArray getUserList(HttpSession session);

	public JSONArray viewExpenseReport(HttpSession session,String projectId, String username,
			String bucketId, String startDate, String endDate, String status);

	public JSONArray getProjectListForReimbursement(HttpSession session);

	public JSONArray getExpensesListUnderLoggedInUser(HttpSession session,
			String status);
}
