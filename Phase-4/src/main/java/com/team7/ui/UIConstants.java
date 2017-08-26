package com.team7.ui;


/**
 * The Class UIConstants.
 */
public class UIConstants {
	
	/** The current user. */
	// to maintain the 'session' for the user.
	public static String currentUser; 
	
	/** The current user role. */
	public static String currentUserRole;
	
	/** The current user conference name. */
	public static String currentUserConf;
	
	/**
	 * Instantiates a new UI constants.
	 *
	 * @param username the userName
	 * @param role the role
	 * @param conference the conference
	 */
	public UIConstants(String username, String role, String conference) {
		currentUser = username;
		currentUserRole = role;
		currentUserConf = conference;
	}

	/** The Highest role. */
	//To give authority for final selection of candidates
	static String HighestRole = "Program Chair";
	
	/** The width. */
	static int width = 1200;
	
	/** The height. */
	static int height = 770;

	
	

}
