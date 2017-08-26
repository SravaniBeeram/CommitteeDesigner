package com.team7.uiTest;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import com.team7.ui.AddToFavoriteList;
import com.team7.ui.FavoriteListUI;
import com.team7.ui.UIConstants;

/**
 * The Class ImplementFavUITest.
 */
public class ImplementFavUITest {
	
	/** The u. */
	UIConstants u  = new UIConstants("shahbiyanta@gmail.com", "Program Chair", "ECOOP");
	
	/** The button. */
	AddToFavoriteList button = new AddToFavoriteList(new JCheckBox());
	
	/**
	 * Test candidate details.
	 * Testing whether the candidate details shows up some records
	 */
	@SuppressWarnings({ "unchecked" })
	@Test 
	public void testCandidateDetails() {
		@SuppressWarnings("rawtypes") 
		Set<String> fav = new HashSet(); 
		fav.add("Bernhard Rumpe");
		FavoriteListUI favList = new FavoriteListUI(fav);

		String[] colNames = {"Name"};
		Object [][] row = {{"Bernhard Rumpe"}, {"author2"}};
		
		//insert  a selected author
		JTable table = new JTable(new DefaultTableModel(row, colNames));
		button.getTableCellEditorComponent(table, "save", true, 0, 0);
		
		//trying to insert same author again - throws an error pop up
		button.getTableCellEditorComponent(table, "save", true, 0, 0);
		
		//trying to insert without selecting any author
		button.getTableCellEditorComponent(table, "save", false, 0, 0);
		
		//click remove without any selection
		favList.btnRemove.doClick();
		
		//select an author to remove
		favList.table.setRowSelectionInterval(0,0); 
		favList.btnRemove.doClick();
		favList.dispose();
	}  
 
	/**
	 * Test logout.
	 */
	@Test
	public void testLogout() {
		Set<String> fav = new HashSet<String>();
		fav.add("Bernhard Rumpe");
		FavoriteListUI favList = new FavoriteListUI(fav);
		favList.btnLogout.doClick();
		favList.dispose();
	}
 	
	/**
	 * Test search UI.
	 */
	@Test
	public void testSearchUI() {
		Set<String> fav = new HashSet<String>();
		fav.add("Bernhard Rumpe");
		FavoriteListUI favList = new FavoriteListUI(fav);
		favList.btnSearch.doClick();
		favList.dispose();
	}
	
	/**
	 * Test candidate list UI.
	 */
	@Test 
	public void testCandidateListUI() {
		Set<String> fav = new HashSet<String>();
		fav.add("Bernhard Rumpe");
		FavoriteListUI favList = new FavoriteListUI(fav);
		favList.btnCandidatesList.doClick();
		favList.dispose();
	} 
}
