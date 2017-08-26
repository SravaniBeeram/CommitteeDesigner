package com.team7.parsing;

/**
 * The Class User.
 * This class contains information about the users of the application 
 */
public class User {

	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The role. */
	private String role;
	
	/** The conf name. */
	private String confName;
     
	/**
	 * Instantiates a new user.
	 */
	public User(){
		
	}

    /**
     * Instantiates a new user.
     *
     * @param userName the user name
     * @param password the password
     * @param role the role
     * @param confName the conf name
     */
    public User(String userName, String password, String role, String confName) {
    	this.userName = userName; //email id 
    	this.password = password;
    	this.role = role;
    	this.confName = confName;
	}

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    // getter and setters
	public String getUserName() {
        return userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the conf name.
     *
     * @return the conf name
     */
    public String getConfName() {
        return confName;
    }
}
