package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The Interface Login.
 */
public interface Login {
		
	/**
	 * Login.
	 * Login to the application by validating the userName and password
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */	 
	public boolean login(String username, String password) throws SQLException,IOException;
	
	
	/**
	 * Logout.
	 *
	 * @return the string
	 */
	// Close the session and logout the user.
	public String logout();
	
	/**
	 * Decrypt password.
	 *
	 * @param plainText the plain text
	 * @param secretKey the secret key
	 * @return the string
	 */
	//Decrypt password
	public String decryptPassword(String plainText,String secretKey);

}
