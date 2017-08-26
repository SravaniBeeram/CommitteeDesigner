package com.team7;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class ImplementSavedUITest {
	
	Set<String> authors = new HashSet<String> (Arrays.asList("Shahar Maoz"));
	SavedAuthorsUI saved;
	ButtonEditor button = new ButtonEditor(new JCheckBox());
	
	@Test
	// Testing logout button
	public void testLogoutButton() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);
		
		saved = new SavedAuthorsUI(rs);
		
		saved.btnNewButton.doClick();
		saved.dispose();
				
	}
	
	@Test
	// Testing searchUI button
	public void testSearchButton() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);
		
		saved = new SavedAuthorsUI(rs);		
		saved.btnSearch.doClick();
		saved.dispose();
	}
	
	@Test
	// Testing send email button with proper list
	public void testEmailButtonWithList() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);
		
		saved = new SavedAuthorsUI(rs);
		
		String[] colNames = {"Name", "Select"};
		Object [][] row = {{"author1", "select"}, {"author2", "select"}};
		JTable table = new JTable(new DefaultTableModel(row, colNames));
		button.getTableCellEditorComponent(table, "select", true, 1, 0);
		
		saved.btnSendEmail.doClick();
		saved.dispose();		
	}
	
	@Test
	// Testing send email button with empty list
	public void testEmailButtonWithEmptyList() throws SQLException, IOException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet rs = queryBuilderObject.sendQuery(query);
		
		saved = new SavedAuthorsUI(rs);
		
		saved.btnSendEmail.doClick();
		saved.dispose();		
	}
	
	
	
	

}
