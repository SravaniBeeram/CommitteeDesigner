package com.team7;

// This class contains information about the users of the application 
public class User {

	private String userName;
	private String password;
	private String role;
	private String confName;
     
	public User(){
		
	}

    public User(String userName, String password, String role, String confName) {
		// TODO Auto-generated constructor stub
    	this.userName = userName; //email id 
    	this.password = password;
    	this.role = role;
    	this.confName = confName;
	}

    // getter and setters
	public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getConfName() {
        return confName;
    }
}
