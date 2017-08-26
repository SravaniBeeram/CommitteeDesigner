package com.team7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ImplementRegisterUITest  {
	
	RegisterUI reg = new RegisterUI();
	
	@Test
	// Testing the user name field being empty
	public void testRegUserEmpty() {
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	@Test
	// Testing the password field being empty
	public void testRegPassEmpty() {
		reg.UsernameTField.setText("sravani.beeram@gmail.com");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	@Test
	// Testing the user already exists 
	public void testRegUserExists() {
		reg.UsernameTField.setText("shahbiyanta@gmail.com");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	@Test
	// Testing the user already exists 
	public void testRegUserEmailInvalid() {
		reg.UsernameTField.setText("shahbiyanta@@gmail.com");
		reg.passwordField.setText("abc");
		reg.btnRegister.doClick();
		reg.dispose();
	}
	
	@Test
	// Testing the register button
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
	
	@Test
	// Testing the register button
	public void testLoginButton() throws SQLException {
		reg.btnLogin.doClick();
		reg.dispose();
	}

}
