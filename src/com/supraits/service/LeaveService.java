package com.supraits.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.supraits.viewBean.LeaveRequestBean;

public interface LeaveService {

	String generateLeaveRequestNo(HttpSession session,String uid);

	JSONArray getListOfLeaveType(HttpSession session, String uid);

	JSONArray getLeaveBalance(HttpSession session,String uid);

	boolean insertNewLeaveRequest(HttpSession session,
			LeaveRequestBean leaveRequestBean, boolean submitStatus);

	JSONArray getAllLeaveRequestList(HttpSession session, String filterVar,
			String allFlag);

	JSONArray getPendingLeaveRequestDetails(HttpSession session,
			String reqNumber);

	JSONArray getLeaveList(HttpSession session);

	boolean createLMSLeave(HttpSession session, String leaveName, String leaveDesc, String policyGroup, String leaveGroup, String applyDaysBefore, String cumulativeGroup, String applyByAdmin, String applyByManager, String applyByHR, String maxDaysPerRequest);

	boolean updateLMSLeaveName(HttpSession session, String leaveName,
			String leaveId, String leaveDesc, String leaveMinDaysBefore, String leaveApplyMaxDays, String leaveActiveFlag, String applyByAdmin, String applyByManager, String applyByHR, String cumulativegroup);

	boolean removeLMSLeave(HttpSession session, String leaveId);

	JSONArray getQuarterLeaveListBandBased(HttpSession session);

	String updateLMSRemarkAndStatus(HttpSession session, String reqNumber,
			String approveFlag, String reqStatus, String remark);

	JSONArray getUsersLeaveBalance(HttpSession session, String policyGroup);

	JSONArray getUserLeaveBalanceUpdationFlag(HttpSession session);

	boolean changeUserLeaveBalanceUpdationFlag(HttpSession session,String updateFlag);

	JSONArray checkUserAppliedLeaveList(HttpSession session, List<String> asList,String uid);

	JSONArray getUnpaidLeaveList(HttpSession session);

	JSONArray getApplyBeforeLeaveParam(HttpSession session);

	JSONArray viewLeaveReport(HttpSession session, String username,
			String hrPolicyGroup, String startDate, String endDate,
			String leaveStatus);

	JSONArray getUserListBasedOnPolicyGroup(HttpSession session,
			String hrPolicyGroup);

	JSONArray getUserLeaveData(HttpSession session, String username);

	String updateUserLeaveBalance(HttpSession session, String username,
			JSONArray updatedLeave);

	String sendMeetingRequestToUser(HttpSession session, String requestNumber,
			String meetingSTime,String meetingETime, String subject, String location, String[] toUser, String[] ccUser, String[] toMember, String message, String type);

	JSONArray getUsersMeetingHistory(HttpSession session, String reqNum);

	JSONArray getCumulativeLeaveGroupList(HttpSession session,
			String policyGroup);

	JSONArray getUserListForLeave(HttpSession session);

	Map<String, String> getUserDataByUsername(String uid);

	JSONArray getMaxDayForRequestLeaveParam(HttpSession session);

	JSONArray getOtherUsersListOnLeave(HttpSession session, String requestNumber, String projectId);

	JSONArray getUsersAssignedProject(HttpSession session, String username);

	JSONArray fetchUserListForLeaveMeeting(HttpSession session, String searchParam);

	boolean checkAuthorization(HttpSession session, String uid);

	JSONArray getUserLeaveBalanceHistory(HttpSession session, String username,
			String leaveCode);

}
