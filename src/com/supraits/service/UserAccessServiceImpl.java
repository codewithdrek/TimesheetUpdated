package com.supraits.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserAccessServiceImpl implements UserAccessService{

	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date date = new Date();
	String currentDate = sdf.format(date);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private JavaMailSender mailSender;
	
}
