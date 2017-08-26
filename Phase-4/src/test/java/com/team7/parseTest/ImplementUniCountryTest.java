package com.team7.parseTest;

import static org.junit.Assert.assertEquals;

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
import com.team7.interfaces.ParseCsvFiles;
import com.team7.parsing.ImplementSchemaDB;

/**
 * The Class ImplementUniCountryTest.
 */
public class ImplementUniCountryTest {
	
	/** The csv fac. */
	AbstractParseFactory csvFac = FactoryProducer.getFactory("CSV");
	
	/**
	 * Test parse success.
	 * to test the successful parsing of CSV file
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@Test 
	public void testParseSuccess() throws IOException, SQLException {
		
		File test = new File("input");
	    test.mkdirs();
	    
		File file = new File("input/testCountry.csv");
	    FileWriter writer = new FileWriter(file);
	    writer.write("institution,region\n"
	    		+ "Test Country,Test Country");
	    
	    writer.close();
	    
	    ParseCsvFiles p = csvFac.getCsv("input/testCountry.csv");
		String result = p.parseCsv();
		assertEquals("success", result);
		
		FileUtils.deleteDirectory(test);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("delete from Author where name='Test Country'");

		file.delete();
	}
	
	/**
	 * Parses the empty.
	 * to test the parsing of an empty CSV file
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@Test 
	public void parseEmpty() throws IOException, SQLException {
		File test = new File("input");
	    test.mkdirs();
	    
		File file = new File("input/testCountry.csv");
		FileWriter writer = new FileWriter(file);
		writer.close();
		
		ParseCsvFiles p = csvFac.getCsv("input/testCountry.csv");
		String result = p.parseCsv();
		
		FileUtils.deleteDirectory(test);
		assertEquals("No data", result);
		file.delete();
	}

}
