package com.team7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcabi.jdbc.JdbcSession;
import junit.framework.TestCase;
import org.junit.Test;

public class ImplementSchemaDBTest extends TestCase {

 
	//Test Case to test the DB Connection
	//Test will fail if connection object is null
	@Test
	public void testAGetConnection() throws IOException {
		Connection conn = null;
		conn = new ImplementSchemaDB().getConnection();
		assertNotNull(conn);
	}

	//Test Case to test Create Table
	// Test will fail if connection object is null Or Creation of Table Fails
	@Test
	public void testBSetUp() throws ClassNotFoundException, SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		db.dbSetUp();
		Connection conn = new ImplementSchemaDB().getConnection();
		PreparedStatement  test = conn.prepareStatement("show tables");
     	test.executeQuery();

		JdbcSession sessionObject = new JdbcSession(conn)
					.sql("show tables")
					.execute();
		assertNotNull(sessionObject);		
		conn.close(); 
	}

	//Test Case to test Insert Data
	//Test will fail if connection object is null Or Insertion of Data Fails
	@Test
	public void testInsertData() throws SQLException, IOException {

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = new ImplementSchemaDB().getConnection();
		Statement stmt = conn.createStatement();
		// delete the user before inserting it every time
		stmt.executeUpdate("delete from User where username='test@test.com'");		
		User test = new User("test@test.com","test","editor","OOPSLA");	
		db.insertData(test);
		
		assertNotNull(conn);
			JdbcSession sessionObject = new JdbcSession(conn)
					.sql("select id from User where userName like 'test@test.com'")
					.execute();
		assertNotNull(sessionObject);
		conn.close();
	}
	
	//Test Case to check to verify object other than user during insertion
	@Test
	public void testInsertData2() throws SQLException, IOException {
	    Author auth = new Author("Test", "testKey");
		ImplementSchemaDB db = new ImplementSchemaDB();
		boolean res = db.insertData(auth);
		assertEquals(false, res);
	}
	
	//Test Case to verify exception if data is not valid
	@Test(expected = SQLException.class)
	public void testExpectedException() throws SQLException, IOException {
			ImplementSchemaDB db = new ImplementSchemaDB();
			User test = new User();	
			db.insertData(test);	  
	} 
	
}
