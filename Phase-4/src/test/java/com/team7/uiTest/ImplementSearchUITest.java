package com.team7.uiTest;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.team7.ui.SearchUI;
import com.team7.ui.UIConstants;

/**
 * The Class ImplementSearchUITest.
 */
public class ImplementSearchUITest {
	
	/** The u. */
	UIConstants u  = new UIConstants("shahbiyanta@gmail.com", "Conference Chair", "ECOOP");
	
	/** The search. */
	SearchUI search = new SearchUI();
	
	/**
	 * Test new row button.
	 */
	@Test
	// Testing the new button in each row
	public void testNewRowButton() {
		search.NewRowButton_1.doClick();
		search.NewRowButton_2.doClick();
		search.NewRowButton_3.doClick();
		search.NewRowButton_4.doClick();
		search.dispose();
	}
	
	/**
	 * Test logout button.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testLogoutButton() throws SQLException, IOException {
		search.btnLogout.doClick();
		search.dispose();
	}
	
	/**
	 * Test submit button.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testSubmitButton() throws SQLException, IOException {
		search.btnNewButton.doClick();
		search.dispose();
	}

}
