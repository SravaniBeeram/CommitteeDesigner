package com.team7;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class ImplementLogin implements Login {

	String secretKey = "SECRETKEY"; // used for decryption of the password

	public boolean login(String username, String password) throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection(); 
		Statement stmt = conn.createStatement();

		String sql = "select password from User where username = '" +username +"'";

		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) { 

			String plainText = rs.getString(1);
			String decryptPassword = decryptPassword(plainText,"SECRETKEY");

			if(!decryptPassword.equals("failure")){
				
				if (decryptPassword.equals(password)) {
					// if inserted password is correct then allow the user to login
					LoginUI.currentUser = username;
					return true;
				}
				else { 
					// if inserted password is incorrect the do not allow the user to login
					return false; 
				}
			}
		} 
		return false; // if user does not exist;
	}

	public String decryptPassword(String plainText,String secretKey){
 
		try { 
			// decrypting the password
			byte[] encryptedData = Base64.decodeBase64(plainText);
			SecretKeySpec skeyspec = new SecretKeySpec(secretKey.getBytes(),"Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted = cipher.doFinal(encryptedData);
			String decryptPassword = new String(decrypted);
			return decryptPassword;

		} catch (Exception e2) {
			
			return "failure";
			
		}

	}

	public String logout() {
		// TODO Auto-generated method stub
		LoginUI.currentUser = null;		 
		return "success";
	}

}
