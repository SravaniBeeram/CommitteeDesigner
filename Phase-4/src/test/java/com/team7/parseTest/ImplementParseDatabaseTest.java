package com.team7.parseTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBException;
import org.junit.Test;

import com.team7.abstractDesignFactory.AbstractParseFactory;
import com.team7.abstractDesignFactory.FactoryProducer;
import com.team7.interfaces.ParseXml;
import com.team7.parsing.ImplementSchemaDB;

import junit.framework.TestCase;

/**
 * The Class ImplementParseDatabaseTest.
 */
public class ImplementParseDatabaseTest extends TestCase{
	
	/** The xml fac. */
	AbstractParseFactory xmlFac = FactoryProducer.getFactory("XML");
	
	/**
	 * Test parse success of inproceedings.
	 *
	 * @throws JAXBException the JAXB exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testParseSuccessInproc() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("msdxml.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("<?xml version='1.0' encoding='ISO-8859-1'?><dblp><inproceedings key='conf/oopsla/1111'><author>testing11</author>"
	    		+ "<title>testing11</title><pages>15151</pages>"
	    		+ "<year>1111</year>"
	    		+ "</inproceedings>"
	    		+ "<inproceedings key='conf/ecoop/1111'><author>testing11</author>"
	    		+ "<title>testing11</title><pages>15151</pages>"
	    		+ "<year>1111</year>"
	    		+ "</inproceedings>"
	    		+ "<inproceedings key='conf/pldi/1111'><author>testing11</author>"
	    		+ "<title>testing11</title><pages>15151</pages>"
	    		+ "<year>1111</year>"
	    		+ "</inproceedings>"
	    		+ "<inproceedings key='conf/icfp/1111'><author>testing11</author>"
	    		+ "<title>testing11</title><pages>15151</pages>"
	    		+ "<year>1111</year>"
	    		+ "</inproceedings>"
	    		+ "<inproceedings key='conf/lcns/1111'>"
	    		+ "<title>testing11</title><pages>15151</pages>"
	    		+ "<year>11116</year>"
	    		+ "</inproceedings>"
	    		+ "<inproceedings key='journals/lcns/1111'><author>testing11</author>"
	    		+ "<title></title><pages>15151</pages>"
	    		+ "<year>11116</year>"
	    		+ "</inproceedings></dblp>"); 
		writer.close();
		
		ParseXml parse = xmlFac.getXml("msdxml.xml");
		String result = parse.parseXml();
	   
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from Paper where title='testing11'");
		stmt.executeUpdate("delete from Paper where year='11116'");
		stmt.executeUpdate("delete from Author where name='testing11'");
		
		file.delete();
	}
	
	
	/**
	 * Test parse success proceedings.
	 *
	 * @throws JAXBException the JAXB exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testParseSuccessProc() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("msdxml.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("<?xml version='1.0' encoding='ISO-8859-1'?><dblp><proceedings key='conf/oopsla/111g'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>oopsla</booktitle>"
	    		+ "</proceedings>"
	    		+ "<proceedings key='conf/ecoop/111g'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>ecoop</booktitle>"
	    		+ "</proceedings>"
	    		+ "<proceedings key='conf/pldi/111g'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>pldi</booktitle>"
	    		+ "</proceedings>"
	    		+ "<proceedings key='conf/icfp/111g'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>icfp</booktitle>"
	    		+ "</proceedings></dblp>"); 
		writer.close();
		
		ParseXml parse = xmlFac.getXml("msdxml.xml");
		String result = parse.parseXml();
		
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from Conference where confDetail='testing11'");
		
		file.delete();
	}
	
	/**
	 * Test parse success article.
	 *
	 * @throws JAXBException the JAXB exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testParseSuccessArticle() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("msdxml.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("<?xml version='1.0' encoding='ISO-8859-1'?><dblp><article key='journals/tse/1111'>"
	    		+ "<author>testing11</author>"
	    		+ "<title>testing11</title>"
	    		+ "<year>1111</year>"
	    		+ "<month>xxx</month>"
	    		+ "<ee>testing11</ee>"
	    		+ "</article>"
	    		+ "<article key='journals/toplas/1111'>"
	    		+ "<author>testing11</author>"
	    		+ "<title>testing11</title>"
	    		+ "<year>1111</year>"
	    		+ "<month>xxx</month>"
	    		+ "<ee>testing11</ee>"
	    		+ "</article>"
	    		+ "<article key='journals/lcns/1111'>"
	    		+ "<title>testing11</title>"
	    		+ "<year>11116</year>"
	    		+ "<month>xxx</month>"
	    		+ "<ee>testing11</ee>"
	    		+ "</article>"
	    		+ "<article>"
	    		+ "<author>testing11</author>"
	    		+ "<title>testing11</title>"
	    		+ "<year>1111</year>"
	    		+ "<month>xxx</month>"
	    		+ "<ee>testing11</ee>"
	    		+ "</article>"
	    		+ "<article key='journals/toplas/1111'>"
	    		+ "<author>testing11</author>"
	    		+ "<title></title>"
	    		+ "<year>11116</year>"
	    		+ "<month>xxx</month>"
	    		+ "<ee>testing11</ee>"
	    		+ "</article></dblp>"); 
		writer.close();
		
		ParseXml parse = xmlFac.getXml("msdxml.xml");
		String result = parse.parseXml();
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from Article where title='testing11'");
		stmt.executeUpdate("delete from Article where year='11116'");
		
		file.delete();
	}
	
}
