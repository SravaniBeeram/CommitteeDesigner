package com.team7.ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.team7.interfaces.Register;
import com.team7.parsing.ImplementSchemaDB;
import com.team7.parsing.User;

/**
 * The Class ImplementRegister.
 */
public class ImplementRegister implements Register {

	/** The base 64. */
	private static Base64 base64 = new Base64(true);

	/* (non-Javadoc)
	 * @see com.team7.interfaces.Register#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String createUser(String userName, String plainPass, String role, String confName) throws SQLException, IOException{

		//including an escape character if string contains '
		if(userName.contains("'")){
			userName = userName.replaceAll("'","\\\\'");
		}

		if(verifyIfUserExists(userName))
		{			
			return "exists"; 
		}  
		else{
			if (validEmailId(userName)) 
			{
				String encryptedPassword = encryptPassword(plainPass,"SECRETKEY");

				if(!encryptedPassword.equals("failure")){
					
					// if the password is correctly encrypted, insert the user into the DB.
					User user = new User(userName,encryptedPassword,role,confName);
					ImplementSchemaDB db= new ImplementSchemaDB();
					return(String.valueOf(db.insertData(user)));
					}
				}			
			return "invalid email";	
		}
	}

	/* (non-Javadoc)
	 * @see com.team7.interfaces.Register#encryptPassword(java.lang.String, java.lang.String)
	 */
	public String encryptPassword(String plainPass,String secretKey) {

		// encrypt the password
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(secretKey.getBytes(),"Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			String password = base64.encodeToString(cipher.doFinal(plainPass.getBytes("UTF8")));
			return password; 

		} catch (Exception e1) {
			return "failure";
		}

	}
	

	// checking for a valid email ID 
	// (does not check whether the email ID is registered valid ID or not, 
	// that discretion is upon the user)
	/**
	 * Valid email id.
	 *
	 * @param userName the user name
	 * @return true, if successful
	 */
	private boolean validEmailId(String userName) {

		Pattern pattern;
		Matcher matcher;
		
		final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$";
		
		pattern = Pattern.compile(EMAIL_REGEX);
		
		matcher = pattern.matcher(userName);
		return matcher.matches();
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.team7.interfaces.Register#verifyIfUserExists(java.lang.String)
	 */
	// checking if the user exists in the database
	public boolean verifyIfUserExists(String userName) throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		String sql = "Select count(*) from User where username = " + '"' +userName+ '"'; 

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		if(rs.next()){

			if(rs.getInt(1) > 0){
				return true; 
			}
		}

		return false;
	}

}
