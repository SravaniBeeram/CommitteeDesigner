package com.team7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64; 

public class ImplementRegister implements Register {

	private static Base64 base64 = new Base64(true);

	public String createUser(String userName, String plainPass, String role, String confName) throws SQLException, IOException{

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
	private boolean validEmailId(String userName) {
		// TODO Auto-generated method stub

		if (! userName.contains("@")) 
			return false;

		if (userName.contains("@.")) 
			return false;

		if (! ((userName.substring (userName.length() - 4, userName.length()).equals(".com"))
				|| (userName.substring (userName.length() - 4, userName.length()).equals(".edu"))) ) 
			return false;

		if (containsMoreThanone(userName))
			return false;

		else 
			return true;
	}

	private boolean containsMoreThanone(String userName) {
		// TODO Auto-generated method stub
		int counter = 0;
		for( int i=0; i<userName.length(); i++ ) {
			if (userName.charAt(i) == '@')
				counter++;
		}

		return ((counter > 1) ? true : false);
	}

	// checking if the user exists
	public boolean verifyIfUserExists(String userName) throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "Select count(*) from User where username = " + "'" +userName+ "'"; 

		ResultSet rs = stmt.executeQuery(sql);

		if(rs.next()){

			if(rs.getInt(1) > 0){
				return true; 
			}
		}

		return false;
	}

}
