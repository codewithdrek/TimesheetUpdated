package com.supraits.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supraits.dao.UserDao;
import com.supraits.service.UserService;
import com.supraits.viewBean.LoginBean;
import com.supraits.viewBean.RegisterBean;


public class UserServiceImpl implements UserService
{

		private UserDao userDao;

		public UserDao getUserDao()
		{
				return this.userDao;
		}

		public void setUserDao(UserDao userDao)
		{
				this.userDao = userDao;
		}

		@Override
		public LoginBean isValidUser(LoginBean loginBean) throws SQLException
		{
				return userDao.isValidUser(loginBean);
		}

		@Override
		public boolean registerNewUser(RegisterBean registerBean) {
			// TODO Auto-generated method stub
			return userDao.registerNewUser(registerBean);
		}

		@Override
		public List<String> getUserGroupList(String username) {
			// TODO Auto-generated method stub
			return userDao.getUserGroupList(username);
		}

		@Override
		public Map<String, Boolean> getAccessMap(List<String> userGroups) {
			// TODO Auto-generated method stub
			return userDao.getAccessMap(userGroups);
		}

		
		
		@Override
		public LoginResult isValidUserRest(LoginInput restLoginBean) throws SQLException {
			return userDao.isValidUserRest(restLoginBean);
		}

		@Override
		public boolean isValidToken(String tokenToMatch) {
			// TODO Auto-generated method stub
			return userDao.isValidTokenDb(tokenToMatch);
		}

	
	

}
