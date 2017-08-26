package com.team7.uiTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import com.team7.ui.ButtonEditor;
import com.team7.ui.DisplayUI;
import com.team7.ui.UIConstants;

/**
 * The Class ImplementDisplayUITest.
 */
public class ImplementDisplayUITest {
	
	/** The u. */
	UIConstants u  = new UIConstants("shahbiyanta@gmail.com", "Conference Chair", "ECOOP");

	/** The auth. */
	List<String> auth = Arrays.asList("author1", "author2");

	/** The display 1. */
	DisplayUI display1 = new DisplayUI(new ArrayList<String>());
	
	/** The display 2. */
	DisplayUI display2 = new DisplayUI(auth);
	
	/** The button. */
	ButtonEditor button = new ButtonEditor(new JCheckBox());

	/**
	 * Test candidate details.
	 */
	@Test
	public void testCandidateDetails() {
		String[] colNames = {"Name", "Save"};
		Object [][] row = {{"author1", "save"}, {"author2", "save"}};
		JTable table = new JTable(new DefaultTableModel(row, colNames));
		button.getTableCellEditorComponent(table, "save", true, 1, 0);
		display2.btnPubDetails.doClick();
		display2.dispose();
	}

	/**
	 * Test logout button.
	 */
	@Test
	public void testLogoutButton() {
		display1.btnLogout.doClick();
		display1.dispose();
	}

	/**
	 * Test search UI.
	 */
	@Test
	public void testSearchUI() {
		display2.btnSearch.doClick();
		display2.dispose();
	}

	/**
	 * Test candidate details empty.
	 */
	@Test
	public void testCandidateDetailsEmpty() {
		display1.btnPubDetails.doClick();
		display1.dispose();
	}

}
