package com.supraits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.supraits.service.UserAccessServiceImpl;

@Controller
public class UserAccessController {
	UserAccessServiceImpl guiObj;
	
	@Autowired
	public void setUserAccessServiceImpl(UserAccessServiceImpl objUserAccessServiceGUI) {
		this.guiObj = objUserAccessServiceGUI;
	}
	
	
}
