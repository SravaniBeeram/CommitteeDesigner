package com.team7;

import java.io.IOException;
import java.sql.SQLException;

public interface Login {
		
	// Login to the application by validating the userName and password	 
	public boolean login(String username, String password) throws SQLException,IOException;
	
	
	// Close the session and logout the user.
	public String logout();
	
	//Decrypt password
	public String decryptPassword(String plainText,String secretKey);

}
