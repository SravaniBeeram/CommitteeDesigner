package com.team7;

import org.junit.Test;

public class ImplementSearchUI {
	
	SearchUI search = new SearchUI();
	
	@Test
	// Testing the new button in each row
	public void testNewRowButton() {
		search.NewRowButton_1.doClick();
		search.NewRowButton_2.doClick();
		search.NewRowButton_3.doClick();
		search.NewRowButton_4.doClick();
		search.dispose();
	}
	
	@Test
	// Testing the logout button
	public void testLogoutButton() {
		search.btnLogout.doClick();
		search.dispose();
	}
	
	@Test
	// Testing the submit button
	public void testSubmitButton() {
		search.btnNewButton.doClick();
		search.dispose();
	}

}
