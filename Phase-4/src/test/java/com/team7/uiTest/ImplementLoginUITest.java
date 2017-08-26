package com.team7.uiTest;

import org.junit.Test;

import com.team7.ui.ImplementLogin;
import com.team7.ui.LoginUI;

/**
 * The Class ImplementLoginUITest.
 */
public class ImplementLoginUITest {

	/** The login. */
	LoginUI login = new LoginUI();

	/** The log. */
	ImplementLogin log = new ImplementLogin();

	/**
	 * Test login button invalid 1.
	 * Tests when username is empty
	 */
	@Test 
	public void testLoginButtonInvalid1() {
		login.passwordField.setText("abc");
		login.btnLogin.doClick();
	}

	/**
	 * Test login button invalid 2.
	 * Tests when password is empty
	 */
	@Test 
	public void testLoginButtonInvalid2() {
		login.userNameField.setText("shahbiyanta@gmail.com");
		login.btnLogin.doClick();
	}

	/**
	 * Test login button invalid 3.
	 * Tests when user does not exist
	 */
	@Test
	public void testLoginButtonInvalid3() {
		login.userNameField.setText("shah@gmail.com");
		login.passwordField.setText("123");
		login.btnLogin.doClick();
	}

	/**
	 * Test login button invalid 4.
	 * Tests when credentials are not correct
	 */
	@Test 
	public void testLoginButtonInvalid4() {
		login.userNameField.setText("xyz@gmail.com");
		login.passwordField.setText("biy");
		login.btnLogin.doClick();
	}
	
	/**
	 * Test login button 
	 * Tests when the username and password are correct
	 */
	@Test
	public void testLoginButton() {
		login.userNameField.setText("shahbiyanta@gmail.com");
		login.passwordField.setText("abc");
		login.btnLogin.doClick();
		// logout the user then
		log.logout();
	}

	/**
	 * Test Register button 
	 * Testing for when the username and password are correct
	 */
	@Test
	public void testRegisterButton() {
		login.btnNewUserClick.doClick();
	}

}
