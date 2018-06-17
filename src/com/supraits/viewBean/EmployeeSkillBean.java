package com.supraits.viewBean;

import org.springframework.stereotype.Component;

@Component
public class EmployeeSkillBean {
	private String skillId;
	private String skillName;
	private String skillType;
	private String version;
	private String experienceMonth;
	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getSkillType() {
		return skillType;
	}
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getExperienceMonth() {
		return experienceMonth;
	}
	public void setExperienceMonth(String experienceMonth) {
		this.experienceMonth = experienceMonth;
	}
	
	
	
}
