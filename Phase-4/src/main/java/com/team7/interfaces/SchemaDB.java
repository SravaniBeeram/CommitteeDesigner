package com.team7.interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Interface SchemaDB.
 */
public interface SchemaDB {

	/**
	 * Db set up. 
	 * Creates database and tables
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void dbSetUp() throws ClassNotFoundException, SQLException,IOException;

	/**
	 * Gets the connection.
	 * Create connection to RDBMS 
	 *
	 * @return the connection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */ 
	public Connection getConnection() throws IOException;

	/**
	 * Insert data.
	 * Insert the user object created to database
	 *
	 * @param object_name the object name
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean insertData(Object object_name) throws SQLException,IOException;
	
	/**
	 * Insert into candidate list.
	 *
	 * @param author the author
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean insertIntoCandidateList(String author) throws SQLException,IOException;
	
	/**
	 * Insert into fav list.
	 *
	 * @param username the username
	 * @param conference the conference
	 * @param author the author
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	public boolean insertIntoFavList(String username, String conference, String author) throws IOException, SQLException;

}
