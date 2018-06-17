package com.supraits.service;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;

public interface AttendanceService {

	boolean updateAttendanceData(String data) throws JSONException;

	JSONArray fetchUserAttendanceData(HttpSession session, String firstDay,
			String lastDay, String username);

	JSONArray fetchUsersAttendanceData(HttpSession session, String firstDay,
			String lastDay, String projectId,String username);

	JSONArray fetchUsersAttendanceDataBasedOnParam(HttpSession session,
			String startDay, String endDay, String projectId, String username);

	JSONArray fetchUsersWeeklyAttendanceData(HttpSession session,
			String startDay, String endDay, String username);

	String sendMOAFForApproval(HttpSession session, String date,String direction, String inTime,
			String outTime, String reason, String moafCategory);

	JSONArray fetchUserAttendanceMOAFData(HttpSession session);

	JSONArray fetchUserPendingRequest(HttpSession session);

	String validatePendingMOAFRequest(HttpSession session, String moafid,
			String approveFlag);

	JSONArray generateUserYearlyAttnData(HttpSession session, String uid, String year);

	String getEmpName(String uid);

	String getEmpRMName(String tempUser);

	JSONArray fetchUsersExceptionAttendanceData(HttpSession session,
			String startDay, String endDay, String username);

	boolean checkUserAuthorization(HttpSession session, String username);

}
