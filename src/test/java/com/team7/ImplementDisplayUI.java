package com.team7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class ImplementDisplayUI {

	List<String> auth = Arrays.asList("author1", "author2");

	DisplayUI display1 = new DisplayUI(new ArrayList<String>());
	DisplayUI display2 = new DisplayUI(auth);
	ButtonEditor button = new ButtonEditor(new JCheckBox());

	@Test
	// Testing whether the candidate details shows up some records
	public void testCandidateDetails() {
		String[] colNames = {"Name", "Save"};
		Object [][] row = {{"author1", "save"}, {"author2", "save"}};
		JTable table = new JTable(new DefaultTableModel(row, colNames));
		button.getTableCellEditorComponent(table, "save", true, 1, 0);
		display2.btnSavedAuthors.doClick();
		display2.dispose();
	}

	@Test
	// Testing logout button
	public void testLogout() {
		display1.btnLogout.doClick();
		display1.dispose();
	}

	@Test
	// Testing the SearchUI function
	public void testSearchUI() {
		display2.btnSearch.doClick();
		display2.dispose();
	}

	@Test
	// Testing the pop up for empty candidate details
	public void testCandidateDetailsEmpty() {
		display1.btnSavedAuthors.doClick();
		display1.dispose();
	}

}
