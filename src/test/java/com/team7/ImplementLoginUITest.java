package com.team7;

import org.junit.Test;

public class ImplementLoginUITest {
	LoginUI login = new LoginUI();
	ImplementLogin log = new ImplementLogin();

	@Test
	// Tests when username is empty
	public void testLoginButtonInvalid1() {
		login.passwordField.setText("abc");
		login.btnLogin.doClick();
	}

	@Test
	// Tests when password is empty
	public void testLoginButtonInvalid2() {
		login.userNameField.setText("shahbiyanta@gmail.com");
		login.btnLogin.doClick();
	}

	@Test
	//Tests when user does not exist
	public void testLoginButtonInvalid3() {
		login.userNameField.setText("shah@gmail.com");
		login.passwordField.setText("123");
		login.btnLogin.doClick();
	}

	@Test
	// Tests when credentials are not correct
	public void testLoginButtonInvalid4() {
		login.userNameField.setText("xyz@gmail.com");
		login.passwordField.setText("biy");
		login.btnLogin.doClick();
	}

	/*
	 * These tests are working on local machine but failing on Jenkins
	 */

	//	@Test
	//	// Testing for when the username and password are correct
	//	public void testLoginButton() {
	//		login.userNameField.setText("shahbiyanta@gmail.com");
	//		login.passwordField.setText("abc");
	//		login.btnLogin.doClick();
	//		// logout the user then
	//		log.logout();
	//	}
	//	
	//	@Test
	//	// Testing for when the username and password are correct
	//	public void testRegisterButton() {
	//		login.btnNewUserClick.doClick();
	//	}

}
