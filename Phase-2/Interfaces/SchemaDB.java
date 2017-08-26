package team7;

import java.sql.Connection;
import java.util.List;

public interface SchemaDB {

	/*
	 * Creates objects based on the incoming data and returns them
	 * List of <T> will be List <Object> where Object will be of type either publications or journal or author or article
	 */
	public <T> List<T> objectCreation(String className, String attributeName);
	
	/*
	 * Create connection to RDBMS 
	 */
	public Connection getConnection();

	/*
	 * Insert the objects created to Database
	 *
	 */
	public <T> void insertData(List<T> object_name);
	
}
