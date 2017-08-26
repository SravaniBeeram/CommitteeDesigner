package com.team7.uiTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.team7.parsing.ImplementSchemaDB;
import com.team7.ui.ImplementRegister;

/**
 * The Class ImplementRegisterTest.
 */
public class ImplementRegisterTest { 

	/**
	 * Test create user success.
	 * Test to verify a successful user registration - should return true
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCreateUserSuccess() throws Exception {

		ImplementRegister user = new ImplementRegister();
		// After inserting delete it, so that the next time you run, it does not fail
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from User where username='sravbiya@gmail.com'");

		String res = user.createUser("sravbiya@gmail.com","132","Associate Editor","OOPSLA");

		assertEquals("true",res);		

	}  

	/**
	 * Test user exists success.
	 * Test to verify a successful user registration - should return exists
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testUserExistsSuccess() throws Exception {

		ImplementRegister user = new ImplementRegister();
		String  res = user.createUser("sravbiya@gmail.com","132","Associate Editor","OOPSLA");
		assertEquals("exists",res);

	}

	/**
	 * Test pwd encryption success.
	 * Test to successful encryption of password
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testPwdEncryptionSuccess() throws SQLException {

		ImplementRegister user = new ImplementRegister();
		String res;
		res = user.encryptPassword("123","SECRETKEY");
		int len = res.length();
		assertEquals(len,13);
	}


	/**
	 * Test pwd encryption failure.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPwdEncryptionFailure() throws Exception {

		ImplementRegister user = new ImplementRegister();		
		assertEquals("failure",user.encryptPassword("123",""));	

	}  


	/**
	 * Test create user failure.
	 * 
	 * Test to verify a unsuccessful user registration - should return invalid email
	 * When email ID does not contain "@"
	 *
	 * @throws Exception the exception
	 */
	@Test
	
	public void testCreateUserFailure() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("sravbiya.com","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}

	/**
	 * Test create user failure 1.
	 * Test to verify a unsuccessful user registration - should return invalid email
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCreateUserFailure1() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("sravbiya@gmail@.gcom","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}

	/**
	 * Test create user failure 2.
	 * Test to verify a unsuccessful user registration - should return invalid email
	 * When email ID contain 2 "@"
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCreateUserFailure2() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("sravbiya@gmail@com","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}

	/**
	 * Test create user failure 3.
	 * Test to verify a unsuccessful user registration - should return invalid email
	 * When email ID does contain domain name
	 *
	 * @throws Exception the exception
	 */
	@Test 
	public void testCreateUserFailure3() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("mno@.com","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}

	/**
	 * Test create user failure 4.
	 * Test to verify a unsuccessful user registration - should return invalid email
	 * When contains multiple userNames
	 *
	 * @throws Exception the exception
	 */
	@Test 
	public void testCreateUserFailure4() throws Exception {

		ImplementRegister user = new ImplementRegister();

		String res = user.createUser("bsha@gmail.com,bs1@gmail.edu","132","Associate Editor","OOPSLA");

		assertEquals("invalid email",res);		

	}


	/**
	 * Test verify user exists failure.
	 *
	 * Test to verify a successful user registration - should return success
	 * 
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testVerifyUserExistsFailure() throws SQLException, IOException {

		ImplementRegister user = new ImplementRegister();
		boolean  res = user.verifyIfUserExists("bs@gmail.com");
		assertEquals(false,res);

	}
}
