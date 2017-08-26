package com.team7.uiTest;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import com.team7.parsing.ImplementSchemaDB;
import com.team7.ui.CandidateListUI;
import com.team7.ui.UIConstants;

/**
 * The Class ImplementCandidateListUITest.
 */
public class ImplementCandidateListUITest {

	/** The u. */
	UIConstants u  = new UIConstants("shahbiyanta@gmail.com", "Program Chair", "ECOOP");
	
	/** The can list. */
	CandidateListUI canList = new CandidateListUI();

	/**
	 * Test.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void test() throws IOException, SQLException {
		
		String[] colNames = {"Name"};
		Object[][] row = {{"Sravani Beeram"},{"test"}};
		canList.table1 = new JTable(new DefaultTableModel(row, colNames));
		
		String[] colName2 = {"Name"};
		Object[][] row2 = {{"test"},{"test"}};
		canList.table2 = new JTable(new DefaultTableModel(row2, colName2));
		
        canList.model = (DefaultTableModel) canList.table1.getModel();;
        
        //testing insert without selecting an author
		canList.buttonSelect.doClick();

		canList.table1.setRowSelectionInterval(0,0);
		canList.buttonSelect.doClick();
		
		//testing inserting already inserted author
		canList.buttonSelect.doClick();

		
		//clicking remove button without selecting author
    	canList.btnRemove.doClick();
    	
    	
		//testing remove after selecting author
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		PreparedStatement stmt = conn.prepareStatement("Select selectedAuthor from Candidate_list where confName='ECOOP'");
		ResultSet rs = stmt.executeQuery();
		canList.table2 = new JTable(buildTableModel(rs));
		canList.model2 = (DefaultTableModel) canList.table2.getModel();
		
		for (int i = canList.table2.getModel().getRowCount() - 1; i >= 0; --i) {
	            if (canList.table2.getModel().getValueAt(i, 0).equals("Sravani Beeram")) {
	            	
	            	//selecting author
	            	canList.table2.setRowSelectionInterval(i,i);
	            	
	            	//testing sendEmail button
	            	canList.btnSendEmail.doClick();
	            	
	            	//testing remove button after selecting an author
	            	canList.btnRemove.doClick();
	        		canList.dispose();
	            
	        }
	    }		
	}	
	
	/**
	 * Builds the table model.
	 *
	 * @param rs the rs
	 * @return the default table model
	 * @throws SQLException the SQL exception
	 */
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
 	
	/**
	 * Test logout.
	 */
	@Test	
	public void testLogout() {		
		canList.btnLogout.doClick();
		canList.dispose();
	}
 	
	/**
	 * Test search UI.
	 */
	@Test
	public void testSearchUI() {
		canList.btnSearchui.doClick();
		canList.dispose();
	}
	
	/**
	 * Test favorite lis UI.
	 */
	@Test 
	public void testFavoriteLisUI() {
		canList.btnMyFavoriteList.doClick();
		canList.dispose();
	} 	

}
