package com.supraits.dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supraits.dao.UserDao;
import com.supraits.viewBean.LoginBean;
import com.supraits.viewBean.RegisterBean;

/**
 * @author abhinav.gupta
 */
@Service
public class UserDaoImpl implements UserDao {
	@Value("${TM83}")
	private String TM83;
	@Value("${TM84}")
	private String TM84;
	@Value("${TM88}")
	private String TM88;
	@Value("${TM87}")
	private String TM87;
	@Value("${TM86}")
	private String TM86;
	@Value("${TM85}")
	private String TM85;
	@Value("${TM52}")
	private String TM52;
	@Value("${TM120}")
	private String TM120;
	@Value("${TM121}")
	private String TM121;
	@Value("${TM122}")
	private String TM122;
	@Value("${TM194}")
	private String TM194;
	@Value("${TM195}")
	private String TM195;

	@Value("${M11}")
	private String M11;

	@Value("${M15}")
	private String M15;
	
	
	@Value("${M19}")
	private String M19;
	
	

	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date date = new Date();
	String currentDate = sdf.format(date);
	public static final String SALT = "supra-its-text";

	DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	JdbcTemplate jdbcTemplate;
	private boolean resultToReturn;

	String token = generateToken();
	@Override
	public LoginBean isValidUser(LoginBean loginBean) throws SQLException {
		try {
			String saltedPassword = SALT + loginBean.getPassword();
			String hashedPassword = generateHash(saltedPassword);
			final String finalHashedPassword = hashedPassword;
			String query = "";
			String temp = loginBean.getUsername();
			int userCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM194, temp, temp), Integer.class);
			if (userCount == 0) {
				loginBean.setLoginMessage("Username is not registered with us.");
			} else {
				query = GetQueryAPI.getQuery(TM195, temp, temp);
				loginBean = jdbcTemplate.query(query, new ResultSetExtractor<LoginBean>() {
					@Override
					public LoginBean extractData(ResultSet rs) throws SQLException, DataAccessException {
						LoginBean temp = new LoginBean();
						if (rs.next()) {
							if (finalHashedPassword.equals(rs.getString(1))) {

								temp.setRole(rs.getString(2));
								temp.setUserGroup(rs.getString(3));
								temp.setUserStatus(rs.getString(4));
								temp.setUsername(rs.getString(5));
								temp.setPrimaryMail(rs.getString(6));
								temp.setFirstName(rs.getString(7));
								temp.setLastName(rs.getString(8));
								temp.setReportingManager(rs.getString(9));
								temp.setHrManager(rs.getString(10));
								temp.setAccountUnit(rs.getString(11));
								temp.setBaseLocation(rs.getString(12));
								temp.setDesignation(rs.getString(13));
								temp.setEmpDepartment(rs.getString(14));
								temp.setPolicyGroup(rs.getString(15));
								temp.setUserCode(rs.getString(16));
								temp.setClientId(Integer.parseInt(rs.getString(17)));
								temp.setLoginMessage("Login Successfull");
								temp.setUsernameproxyid("");
							} else {
								temp.setLoginMessage("Invalid credential");
							}
						}
						return temp;
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginBean.setLoginMessage("Contact administrator");
		}
		return loginBean;
	}

	@Override
	public boolean registerNewUser(RegisterBean registerBean) {
		boolean status = false;
		String query = "";
		PreparedStatement pstmt = null;
		String encPwd = "";
		if (registerBean.getPwd().equals(registerBean.getConfPwd()))
			encPwd = generateHash(SALT + registerBean.getPwd());
		try {
			query = "INSERT INTO user (username, password, role,primaryemail, creationdate, lastmodifieddate,userstatus,usergroup,firstname,lastname,reportingmanager,hrmanager) VALUES ('"
					+ registerBean.getUserId() + "', '" + encPwd + "', 'User', '" + registerBean.getUserMail() + "','"
					+ sdf.format(new Date()) + "', '" + sdf.format(new Date()) + "','Created','1','"
					+ registerBean.getfName() + "','" + registerBean.getlName() + "','SITS130','SITS130')";
			pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.executeUpdate();
			status = true;
			System.out.println("Record is inserted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hash.toString();
	}

	@Override
	public List<String> getUserGroupList(String username) {
		List<String> groupList = new ArrayList<String>();
		try {
			groupList = jdbcTemplate.query(GetQueryAPI.getQuery(TM120, username),
					new ResultSetExtractor<List<String>>() {

						@Override
						public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<String> temp = new ArrayList<String>();
							while (rs.next()) {
								temp.add(rs.getString(1));
							}
							return temp;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupList;
	}

	@Override
	public Map<String, Boolean> getAccessMap(List<String> userGroups) {
		Map<String, Boolean> accessMap = new HashMap<String, Boolean>();
		try {
			accessMap = jdbcTemplate.query(GetQueryAPI.getQuery(TM122), new ResultSetExtractor<Map<String, Boolean>>() {

				@Override
				public Map<String, Boolean> extractData(ResultSet rs) throws SQLException, DataAccessException {
					Map<String, Boolean> temp = new HashMap<String, Boolean>();
					while (rs.next()) {
						temp.put(rs.getString(1), false);
					}
					return temp;
				}
			});
			Iterator<String> itrGrpList = userGroups.iterator();
			while (itrGrpList.hasNext()) {
				String tempGroup = itrGrpList.next();
				List<String> allowedFuncList = jdbcTemplate.query(GetQueryAPI.getQuery(TM121, tempGroup),
						new ResultSetExtractor<List<String>>() {
							@Override
							public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
								List<String> temp = new ArrayList<String>();
								while (rs.next()) {
									temp.add(rs.getString(1));
								}
								return temp;
							}
						});
				Iterator<String> itrFunctionList = allowedFuncList.iterator();
				while (itrFunctionList.hasNext()) {
					accessMap.put(itrFunctionList.next(), true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessMap;
	}

	@Override
	public LoginResult isValidUserRest(final LoginInput loginBean) throws SQLException {

		LoginResult loginResult = new LoginResult();

		try {
			String saltedPassword = SALT + loginBean.getPassword();
			String hashedPassword = generateHash(saltedPassword);
			final String finalHashedPassword = hashedPassword;
			String query = "";
			String temp = loginBean.getUsername();

			int userCount = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(TM194, temp, temp), Integer.class);

			if (userCount == 0) {
				loginResult.setLoginMessage("Username is not registered with us.");
			} else {
				query = GetQueryAPI.getQuery(M11, temp);
				loginResult = jdbcTemplate.query(query, new ResultSetExtractor<LoginResult>() {
					@Override
					public LoginResult extractData(ResultSet rs) throws SQLException, DataAccessException {
						LoginResult loginResult = new LoginResult();
						if (rs.next()) {
							if (finalHashedPassword.equals(rs.getString(10))) {

								if (generateAndSaveToken(loginBean, loginResult)) {
									loginResult.setEmpId(rs.getString(1));
									loginResult.setEmpName(rs.getString(2));
									loginResult.setManagerId(rs.getString(3));
									loginResult.setManagerName(rs.getString(4));
									loginResult.setHrmId(rs.getString(5));
									loginResult.setHrmName(rs.getString(6));
									loginResult.setPrimaryEmail(rs.getString(7));
									loginResult.setFirstname(rs.getString(8));
									loginResult.setLastname(rs.getString(9));
									loginResult.setPassword("hidden - security reasons");
									loginResult.setRole(rs.getString(11));
									loginResult.setUserGroup(rs.getString(12));
									loginResult.setUserStatus(rs.getString(13));
									loginResult.setAccountUnit(rs.getString(14));
									loginResult.setBaseLocation(rs.getString(15));
									loginResult.setDesignation(rs.getString(16));
									loginResult.setDepartment(rs.getString(17));
									loginResult.setPolicyGroup(rs.getString(18));
									loginResult.setUserCode(rs.getString(19));
									loginResult.setClientId(Integer.valueOf(rs.getString(20)));
									loginResult.setLoginMessage("Login Successfull");
									loginResult.setLoggedInUserProxy("");
									loginResult.setToken(token);
								}

							} else {
								loginResult.setLoginMessage("Invalid credential");
							}
						}
						return loginResult;
					}

				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginResult.setLoginMessage("Contact administrator");
		}
		return loginResult;
	}

	
	
	private boolean generateAndSaveToken(LoginInput loginBean, LoginResult loginResult) {
		
			
			String deviceId = loginBean.getDeviceId();
			String deviceName = loginBean.getDeviceType();
			String userName = loginBean.getUsername();

			boolean resultToReturn = false;
			String query = "";
			PreparedStatement pstmt = null;

			try {
				 query = "INSERT INTO restToken (deviceId, deviceName, token,username) VALUES ('"+
						 deviceId +"', '"+ deviceName +"', '"+ token +"', '"+ userName +"')";
//				query = "INSERT INTO restToken(deviceId,deviceName,userName,token) VALUES (522653657,'IOS', 80 ,'"+token+"')";
				pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.executeUpdate();
				resultToReturn = true;
				System.out.println("Token is inserted successfully!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		return resultToReturn;
	}

	
	
	private String generateToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public boolean isValidTokenDb(String tokenToMatch) {
		// TODO Auto-generated method stub

		boolean result=false;
		try {
       Integer	counts = jdbcTemplate.queryForObject(GetQueryAPI.getQuery(M15), Integer.class);
			if(counts==1){
				result=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		
		
		return result;
	}
}