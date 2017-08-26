package com.team7;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("restriction")
public class ImplementParseDatabase implements ParseDatabase {

	ImplementSchemaDB db = new ImplementSchemaDB(); 

	// Parsing the dblp.xml to extract required data, 
	// converting into java objects and inserting into the database
	public String parseXml(File file) throws JAXBException, SQLException, IOException {

		JAXBContext jaxbContext = JAXBContext.newInstance(dblp.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
 
		// to allow access to dblp.dtd
		System.setProperty("javax.xml.accessExternalDTD", "all");

		// for files with size > 1 GB
		System.setProperty("jdk.xml.maxGeneralEntitySizeLimit","0");
		System.setProperty("jdk.xml.entityExpansionLimit","0");

		// Creating the dblp object from the xml file.
		dblp data = (dblp)  jaxbUnmarshaller.unmarshal(file);

		// getting connection to the database
		Connection conn = db.getConnection();
		final int batchSize = 10000; 
		int i=0, j=0, k=0, l=0, m=0;

		// Extracting required information from the dblp object
		// Information about the home-pages, authors, papers, conferences and 
		// articles have been extracted and inserted into respective tables in the database

		if(data.getWww() != null){

			PreparedStatement statement_authorD =  conn.prepareStatement("insert into Author_Details(name,url) values (?,?)");

			for(AuthorDetails auth : data.getWww()){

				// If the author name and url is empty then do not insert
				if(auth.getAuthor() != null && auth.getUrl() != null){

					for(String name : auth.getAuthor())
					{
						statement_authorD.setString(1,name);
						statement_authorD.setString(2,auth.getUrl());
						statement_authorD.addBatch();
					}					
					if (++i % batchSize == 0){			
						statement_authorD.executeBatch();
					}
				}
			}	
			statement_authorD.executeBatch();
		}

		if (data.getInproceedings() != null) {

			PreparedStatement statement_inproceedings = conn.prepareStatement("insert into Paper(title,year,pages,confName,paperKey)"
					+ "values(?,?,?,?,?)");

			PreparedStatement statement_author = conn.prepareStatement("insert into Author(name,paperKey) values (?,?)");

			for (Paper paper: data.getInproceedings()) {
				
				// author is null then don't make an object of either paper or author 
				if ((paper.getAuthor() == null)) 
					continue;

				// UTF8  misinterpreted characters have been found in the title, 
				// such titles have not been inserted into the database
				if(!isUTF8MisInterpreted(paper.getTitle(),"Windows-1252")){
					continue;
				}

				// papers having empty titles are not needed
				if (paper.getTitle().equals("")) {
					continue;
				}

				// extracting conference name from the paper key
				String[] output  = new String[3];
				output = paper.getKey().split("/");
				String confName = null;

				if (output[0].equals("conf")){    	
					confName = output[1];
				}

				// Inserting records into paper and authors only for the 4 
				// required conferences
				if(confName != null) {
					if ((confName.equalsIgnoreCase("oopsla")) 
							|| (confName.equalsIgnoreCase("pldi"))
							|| (confName.equalsIgnoreCase("ecoop")) 
							|| (confName.equalsIgnoreCase("icfp"))) {

						statement_inproceedings.setString(1,paper.getTitle());
						statement_inproceedings.setInt(2,paper.getYear());
						statement_inproceedings.setString(3,paper.getPages());
						statement_inproceedings.setString(4,confName);
						statement_inproceedings.setString(5, paper.getKey());
						statement_inproceedings.addBatch();

						for (String author: paper.getAuthor()) {
							Author auth = new Author(author, paper.getKey());

							statement_author.setString(1, auth.getName());
							statement_author.setString(2, auth.getPaperKey());
							statement_author.addBatch();
						}

						if (++j % batchSize == 0){
							statement_inproceedings.executeBatch();
						}

						if (++m % batchSize == 0) {
							statement_author.executeBatch();
						}
					}
				}
			}
			statement_inproceedings.executeBatch();
			statement_author.executeBatch();
		}

		if (data.getProceedings() != null) {

			PreparedStatement statement_conference = conn.prepareStatement("insert into Conference(confKey,name,confDetail) values (?,?,?)");

			for (Conference conf: data.getProceedings()) {
				if (conf.getBooktitle() != null) {
					if ((conf.getBooktitle().equalsIgnoreCase("oopsla")) 
							|| (conf.getBooktitle().equalsIgnoreCase("pldi"))
							|| (conf.getBooktitle().equalsIgnoreCase("ecoop"))
							|| (conf.getBooktitle().equalsIgnoreCase("icfp"))) {

						statement_conference.setString(1, conf.getKey());
						statement_conference.setString(2, conf.getBooktitle());
						statement_conference.setString(3, conf.getTitle());
						statement_conference.addBatch(); 

						if (++k % batchSize == 0){
							statement_conference.executeBatch();
						}
					}
				}
			}
			statement_conference.executeBatch();
		}

		if (data.getArticle() != null) {

			PreparedStatement statement_article = conn.prepareStatement("insert into Article(author,title,journal,year,month,ee) values (?,?,?,?,?,?)");

			for (Article article: data.getArticle()) {

				if (article.getAuthor() ==  null)
					continue;

				// UTF8  misinterpreted characters have been found in the title, 
				// such titles have not been inserted into the database
				if(!isUTF8MisInterpreted(article.getTitle(),"Windows-1252")){
					continue;
				}

				if (article.getTitle().equals("")) {
					continue;
				}

				if (article.getKey() == null) {
					continue;
				}

				// extracting journal name from the article key
				String[] output  = new String[3];
				output = article.getKey().split("/");
				String journalName = null;

				if (output[0].equals("journals")){    	
					journalName = output[1];
				}

				// Inserting records into the article table only for the 2 
				// required journals
				if (journalName != null) {
					if (journalName.equalsIgnoreCase("tse") || 
							journalName.equalsIgnoreCase("toplas")) {
						
						for (String author: article.getAuthor()) {
							statement_article.setString(1, author);
							statement_article.setString(2, article.getTitle());
							statement_article.setString(3, journalName);
							statement_article.setInt(4, article.getYear());
							statement_article.setString(5, article.getMonth());
							statement_article.setString(6, article.getEe());

							statement_article.addBatch();

							if (++l % batchSize == 0)
								statement_article.executeBatch();
						}
					}
				}
			}
			statement_article.executeBatch();
		}


		return "success";
	}

	// to check for UTF 8 misinterpreted characters.
	private boolean isUTF8MisInterpreted(String input, String encoding) {
		// TODO Auto-generated method stub
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
		ByteBuffer tmp;
		try {
			tmp = encoder.encode(CharBuffer.wrap(input));
		}

		catch(CharacterCodingException e) {
			return false;
		}

		try {
			decoder.decode(tmp);
			return true;
		}
		catch(CharacterCodingException e){
			return false;
		}       
	}		
}
