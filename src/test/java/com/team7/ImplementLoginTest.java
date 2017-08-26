package com.team7;

import junit.framework.TestCase;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;


public class ImplementLoginTest extends TestCase{

	
	 @Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testLoginSuccess() throws SQLException, IOException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("xyz@gmail.com","132");
		Boolean val = true;
		assertEquals(val,res);  
		
	}  
	 
	 	  
	 @Test
     //Test to verify a  if details are incorrect - should return false
     public void testWrongPassword() throws SQLException, IOException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("xyz@gmail.com","123");
		Boolean val = false;
		assertEquals(val,res);
		
	}
	 
	 @Test
     //Test to verify a  unregistered user  - should return false
     public void testUnregisteredUser() throws SQLException, IOException {
		
		ImplementLogin login = new ImplementLogin();
		Boolean res = login.login("xyz@hello.com","123");
		Boolean val = false;
		assertEquals(val,res);
		
	}

	 
	 
	 @Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testPwdDecryptionSuccess() throws SQLException {
		
		ImplementLogin login = new ImplementLogin();
		String res = login.decryptPassword("oU4jbVTElTw","SECRETKEY");
		assertEquals(res,"123");
		
	} 
	 
	 @Test
	 //Test to verify a valid registered user - should return true and enter the search page
     public void testPwdDecryptionFailure() throws SQLException {
		
		ImplementLogin login = new ImplementLogin();
		String res = login.decryptPassword("oU4jbVTElTw","");
		assertEquals(res,"failure");
		 
	} 
 	  
	 @Test
     //Test to verify successful logout - should return success
     public void testLogoutSuccess() {
		
		ImplementLogin login = new ImplementLogin();
		String res = null;
		res = login.logout(); 
		assertEquals("success",res);
		
	} 
}
