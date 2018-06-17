package com.supraits.viewBean;

import org.springframework.stereotype.Component;

@Component
public class EmployeeEducationBean {
	private String eduType;
	private String qualificationId;
	private String qualification;
	private String schoolCollegeName;
	private String universityBoard;
	private String stream;
	private String year;
	private String score;
	
	
	public String getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(String qualificationId) {
		this.qualificationId = qualificationId;
	}
	public String getEduType() {
		return eduType;
	}
	public void setEduType(String eduType) {
		this.eduType = eduType;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSchoolCollegeName() {
		return schoolCollegeName;
	}
	public void setSchoolCollegeName(String schoolCollegeName) {
		this.schoolCollegeName = schoolCollegeName;
	}
	public String getUniversityBoard() {
		return universityBoard;
	}
	public void setUniversityBoard(String universityBoard) {
		this.universityBoard = universityBoard;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
}
