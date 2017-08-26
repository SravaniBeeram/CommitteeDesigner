package com.team7.uiTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.team7.parsing.ImplementSchemaDB;
import com.team7.ui.RegisterUI;

/**
 * The Class ImplementRegisterUITest.
 */
public class ImplementRegisterUITest  {
	
	/** The reg. */
	RegisterUI reg = new RegisterUI();
	
	/**
	 * Test reg user empty.
	 * 
	 * Testing the user name field being empty
	 */
	@Test
	public void testRegUserEmpty() {
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	/**
	 * Test reg pass empty.
	 * Testing the password field being empty
	 */
	@Test 
	public void testRegPassEmpty() {
		reg.UsernameTField.setText("sravani.beeram@gmail.com");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	/**
	 * Test reg user exists.
	 * Testing the user already exists 
	 */
	@Test
	public void testRegUserExists() {
		reg.UsernameTField.setText("shahbiyanta@gmail.com");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	/**
	 * Test reg user email invalid.
	 * Testing the invalid email 
	 */
	@Test 
	public void testRegUserEmailInvalid() {
		reg.UsernameTField.setText("shahbiyanta@@gmail.com");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	/**
	 * Test reg button.
	 * Testing the register button
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testRegButton() throws SQLException, IOException {
		reg.UsernameTField.setText("sravani.beeram@gmail.com");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
		
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from User where username='sravani.beeram@gmail.com'");		
		reg.dispose();
	}
	
	/**
	 * Testblank username.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testblankUsername() throws SQLException, IOException {
		reg.UsernameTField.setText(" ");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
	}
	
	/**
	 * TestQuotes username.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testQuotesUsername() throws SQLException, IOException {
		reg.UsernameTField.setText("\"");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
	}
	
	/**
	 * Testblank password.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testblankPassword() throws SQLException, IOException {
		reg.UsernameTField.setText("sravani.beeram@gmail.com");
		reg.passwordField.setText(" ");
		reg.btnRegister.doClick();
	}
	
	/**
	 * Test username length.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testUsernameLength() throws SQLException, IOException {
		reg.UsernameTField.setText("1234567494928gdhst39hdfsdgfhsgjhdfdgashdgf@gmail.com");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
	}
	
	/**
	 * Test login button.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testLoginButton() throws SQLException {
		reg.btnLogin.doClick();
		reg.dispose();
	}

}
