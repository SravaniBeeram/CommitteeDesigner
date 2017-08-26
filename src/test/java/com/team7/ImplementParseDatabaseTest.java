package com.team7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBException;
import org.junit.Test;
import junit.framework.TestCase;

@SuppressWarnings("restriction")
public class ImplementParseDatabaseTest extends TestCase{

	@Test
	public void testParseSuccessWww() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("test.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("<?xml version='1.0' encoding='ISO-8859-1'?><dblp><www><author>testing11</author><url>testing11</url></www></dblp>"); 
		writer.close();
	    ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from Author_Details where name='testing11'");
		
		file.delete();
	}
	
	@Test
	public void testParseSuccessInproc() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("test.xml");
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
	    ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from Paper where title='testing11'");
		stmt.executeUpdate("delete from Paper where year='11116'");
		
		file.delete();
	}
	
	
	@Test
	public void testParseSuccessProc() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("test.xml");
	    FileWriter writer = new FileWriter(file);
	    writer.write("<?xml version='1.0' encoding='ISO-8859-1'?><dblp><proceedings key='conf/oopsla/1111'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>oopsla</booktitle>"
	    		+ "</proceedings>"
	    		+ "<proceedings key='conf/ecoop/1111'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>ecoop</booktitle>"
	    		+ "</proceedings>"
	    		+ "<proceedings key='conf/pldi/1111'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>pldi</booktitle>"
	    		+ "</proceedings>"
	    		+ "<proceedings key='conf/icfp/1111'>"
	    		+ "<title>testing11</title>"
	    		+ "<booktitle>icfp</booktitle>"
	    		+ "</proceedings></dblp>"); 
		writer.close();
	    ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from Paper where title='testing11'");
		
		file.delete();
	}
	
	@Test
	public void testParseSuccessArticle() throws JAXBException, SQLException, IOException {
		
		// to test the successful parsing of XML file
	    File file = new File("test.xml");
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
	    ImplementParseDatabase parse = new ImplementParseDatabase();
		String result = parse.parseXml(file);
		assertEquals("success", result);
		
		// deleting the record inserted
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("delete from Paper where title='testing11'");
		stmt.executeUpdate("delete from Paper where year='11116'");
		
		file.delete();
	}
	
}
