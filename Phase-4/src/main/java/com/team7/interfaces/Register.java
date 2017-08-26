package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The Interface Register.
 */
public interface Register {

	/**
	 * Creates the user.
	 * Create a new user, if not present
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param role the role
	 * @param confName the conf name
	 * @return the string
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String createUser(String userName, String password, String role, String confName) throws SQLException,IOException;

	/**
	 * Verify if user exists.
	 * Check if user already exists
	 *
	 * @param userName the user name
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean verifyIfUserExists(String userName) throws SQLException,IOException;
	
	/**
	 * Encrypt password.
	 *
	 * @param plainPass the plain pass
	 * @param secretKey the secret key
	 * @return the string
	 */
	public String encryptPassword(String plainPass,String secretKey);

}
