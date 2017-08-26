package com.team7;
 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import junit.framework.TestCase;

public class ImplementCommitteesTest extends TestCase {

	// Test case for successful parsing
	@Test
	public void testParseSuccess() throws IOException, SQLException {
		
		File test = new File("input/committees");
	    test.mkdirs();
	    
	    File file1 = new File("input/committees/ecoop1111-pc");	    
	    FileWriter writer = new FileWriter(file1);
	    writer.write("P:test");  
	    writer.close();
	    
	    File file2 = new File("input/committees/oopsla1111-pc");	    
	    FileWriter writer2 = new FileWriter(file2);
	    writer2.write("test"); 	    
		writer2.close();
		
	    File file3 = new File("input/committees/pldi1111-pc");	    
	    FileWriter writer3 = new FileWriter(file3); 
	    writer3.write("test"); 	    
		writer3.close();

	    File file4 = new File("input/committees/icfp1111-pc");	    
	    FileWriter writer4 = new FileWriter(file4); 
	    writer4.write("test"); 	    
		writer4.close();
		
		ImplementCommittees com = new ImplementCommittees();
		String result = com.ParseFiles(test);
		assertEquals("success", result); 
				
		FileUtils.deleteDirectory(test);
		
		//Deleting inserted test records
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from Committee where year=1111");
	
	}  
	
	// Test case for empty directory 
	@Test
	public void testEmptyDirectory() throws IOException, SQLException {
		
		File test = new File("input/committees/");
		test.mkdirs();
		
		ImplementCommittees com = new ImplementCommittees();
		String result = com.ParseFiles(test);
		assertEquals("No data", result);
		FileUtils.deleteDirectory(test);		
		
	}	
}
