package team7;

public interface Login {
	
	/*
	 * Create a new user, if not present
	 */
	public void createUser (User userObject);
	
	/*
	 * Login to the application
	 */
	public void login(String username, String password);
	
	/*
	 * Validates the username and password
	 */
	public boolean validateUser(String username, String password);	
	
	/*
	 * Close the session and logout the user.
	 */
	public void logout(User userObject);
}
