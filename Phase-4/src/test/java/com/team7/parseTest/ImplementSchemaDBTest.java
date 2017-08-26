package com.team7.parseTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.team7.parsing.Author;
import com.team7.parsing.ImplementSchemaDB;
import com.team7.parsing.User;
import com.team7.ui.UIConstants;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * The Class ImplementSchemaDBTest.
 */
public class ImplementSchemaDBTest extends TestCase {

	/** The u. */
	UIConstants u  = new UIConstants("shah.bi@shah.bi", "Program Chair", "ECOOP");
	
	/**
	 * Test A get connection.
	 * 
	 * Test Case to test the DB Connection
	 * Test will fail if connection object is null
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testAGetConnection() throws IOException {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
	}

	//
	/**
	 * Test B set up.
	 * 
	 * Test Case to test Create Table
	 * Test will fail if connection object is null 
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// 
	@Test
	public void testBSetUp() throws ClassNotFoundException, SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		db.dbSetUp();
		Connection conn = new ImplementSchemaDB().getConnection();
		PreparedStatement  test = conn.prepareStatement("show tables");
     	test.executeQuery();
     	
     	boolean res = true;
     	if (conn.equals(null)) {
     		res = false;
     	}
     	
     	assertTrue(res);
	}

	//
	/**
	 * Test insert data.
	 * 
	 * Test Case to test Insert Data
	 * Test will fail if connection object is null
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testInsertData() throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = new ImplementSchemaDB().getConnection();
		Statement stmt = conn.createStatement();
		// delete the user before inserting it every time
		stmt.executeUpdate("delete from User where username='test@test.com'");		
		User test = new User("test@test.com","test","editor","OOPSLA");	
		boolean res = db.insertData(test);

		assertTrue(res);
		
	}
	
	/**
	 * Test insert data 2.
	 * Test Case to check to verify object other than user during insertion
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testInsertData2() throws SQLException, IOException {
	    Author auth = new Author("Test", "testKey");
		ImplementSchemaDB db = new ImplementSchemaDB();
		boolean res = db.insertData(auth);
		assertEquals(false, res);
	}
	
	/**
	 * Test expected exception.
	 * Test Case to verify exception if data is not valid
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test(expected = SQLException.class)
	public void testExpectedException() throws SQLException, IOException {
			ImplementSchemaDB db = new ImplementSchemaDB();
			User test = new User();	
			db.insertData(test);	  
	} 
	
	/**
	 * Test insert candidate list data.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testInsertCandidateListData() throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();

		boolean res = db.insertIntoCandidateList("Biyanta Shah");
		
		assertTrue(res);
		
		// delete the author
		res = db.updateCandidateList("Biyanta Shah", UIConstants.currentUserConf);
		
		assertTrue(res);
	} 
	
	//Test Case to test Insert Data into favorites list
	/**
	 * Test insert update fav list data.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Test will fail if connection object is null Or Insertion of Data Fails
	@Test
	public void testInsertUpdateFavListData() throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();

		// insert into fav list
		boolean res = db.insertIntoFavList(UIConstants.currentUser, UIConstants.currentUserConf, "Biyanta Shah");

		assertTrue(res);
		
		// delete the author 
		res = db.updateFavList(UIConstants.currentUser, "Biyanta Shah");

		assertTrue(res);
	}
	
	
}
