package com.team7;

import java.io.IOException;
import java.sql.SQLException;

public interface Register {

	//Create a new user, if not present
	public String createUser(String userName, String password, String role, String confName) throws SQLException,IOException;

	// Check if user already exists
	public boolean verifyIfUserExists(String userName) throws SQLException,IOException;
	
	//Encrypts password
	public String encryptPassword(String plainPass,String secretKey);

}
