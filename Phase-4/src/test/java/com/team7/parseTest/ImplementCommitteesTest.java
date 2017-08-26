package com.team7.parseTest;
 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.team7.abstractDesignFactory.AbstractParseFactory;
import com.team7.abstractDesignFactory.FactoryProducer;
import com.team7.interfaces.ParseTextFile;
import com.team7.parsing.ImplementSchemaDB;

import junit.framework.TestCase;

/**
 * The Class ImplementCommitteesTest.
 */
public class ImplementCommitteesTest extends TestCase {
	
	/** The txt fac. */
	AbstractParseFactory txtFac = FactoryProducer.getFactory("TXT");

	/**
	 * Test parse success.
	 * Test case for successful parsing
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testParseSuccess() throws IOException, SQLException {
		
		File test = new File("input/committes");
	    test.mkdirs();
	    
	    File file1 = new File("input/committes/ecoop1111-pc");	    
	    FileWriter writer = new FileWriter(file1);
	    writer.write("P:test");  
	    writer.close();
	    
	    File file2 = new File("input/committes/oopsla1111-pc");	    
	    FileWriter writer2 = new FileWriter(file2);
	    writer2.write("test"); 	    
		writer2.close();
		
	    File file3 = new File("input/committes/pldi1111-pc");	    
	    FileWriter writer3 = new FileWriter(file3); 
	    writer3.write("test"); 	    
		writer3.close();

	    File file4 = new File("input/committes/icfp1111-pc");	    
	    FileWriter writer4 = new FileWriter(file4); 
	    writer4.write("test"); 	    
		writer4.close();
		
		ParseTextFile parse = txtFac.getText("input/committes");
		String result = parse.parseText();
		
		assertEquals("success", result); 
				
		FileUtils.deleteDirectory(test);
		
		//Deleting inserted test records
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from Committee where year=1111");
	
	}  
	
	/**
	 * Test empty directory.
	 * Test case for empty directory 
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testEmptyDirectory() throws IOException, SQLException {
		
		File test = new File("input/committes/");
		test.mkdirs();
		
		ParseTextFile parse = txtFac.getText("input/committes");
		String result = parse.parseText();
		assertEquals("No data", result);
		FileUtils.deleteDirectory(test);		
		
	}	
}
