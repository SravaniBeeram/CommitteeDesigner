package com.team7.parsing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.team7.interfaces.SchemaDB;
import com.team7.ui.UIConstants;

import java.sql.PreparedStatement;

/*
 * Commenting the index creation. 
 *  We have created the database with indexes and uploaded it on RDS. However,
 *  when testing this function (dbSetup()) , it throws an error because it creates
 *  duplicate index keys. MySQL does not allow us to check whether an Index has been created or not.
 *  Since that part of the code is no longer used, we've commented it. 
 *  For the tables creation part we have a IF NOT EXISTS condition,
 *  hence they won't be created again.
 */

/**
 * The Class ImplementSchemaDB.
 */
/*
 * We have implemented the Singleton Design Pattern here, formerly the connection object was instantiated 
 * every time we needed to fetch a query result from the DB. Using Singleton Design Pattern we only create the
 * object of 'Connection' once and use it everywhere.
 */
public class ImplementSchemaDB implements SchemaDB { 

	/** The props. */
	Properties props;
	
	/** The conn. */
	static Connection conn; 

	/*
	 * @see com.team7.interfaces.SchemaDB#dbSetUp()
	 */
	// creating  DB and its initial skeleton 
	public void dbSetUp() throws ClassNotFoundException, SQLException, IOException{

		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

		Connection conn = null;
		Statement stmt = null;
		String sql = null;

		try{
			//Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// creating a connection for local DB 
//			String db_url = "jdbc:mysql://localhost?verifyServerCertificate=false&useSSL=true";
//			String userName = "root";
//			String password = "root";

			// getting the connection to local host (only for local DB)
//			conn = DriverManager.getConnection(db_url, userName, password);
//			stmt = conn.createStatement();
			 			
			// Create Database for local DB
			// On RDS you need to separately create the DB first and run the below code to create tables.
//			stmt = conn.createStatement();			      
//			sql = "CREATE DATABASE IF NOT EXISTS DBLP";
//			stmt.executeUpdate(sql);
			  
			// Database properties for RDS 
			String connected_db = "jdbc:mysql://root.c9pxnh8wqisg.us-west-2.rds.amazonaws.com:3306/DBLP";
			String userName = "root";
			String password = "9HTa~TZ?dyQWM4}";
			
			// For local DB
//			String connected_db = "jdbc:mysql://localhost/DBLP?verifyServerCertificate=false&useSSL=true&useServerPrepStmts=false&rewriteBatchedStatements=true";
			
			// Connect to the DB
			conn = DriverManager.getConnection(connected_db, userName, password);
			stmt = conn.createStatement();

			//creating user table
			sql = "CREATE TABLE IF NOT EXISTS User " +
					"(id       INTEGER      AUTO_INCREMENT NOT NULL, " +
					" username VARCHAR(255) UNIQUE NOT NULL, " + 
					" password VARCHAR(255) NOT NULL, " + 
					" role     VARCHAR(255), " + 
					" confName VARCHAR(255),"+
					" PRIMARY  KEY(id))"; 
 
			stmt.executeUpdate(sql);

			//creating conference table
			sql = "CREATE TABLE IF NOT EXISTS Conference " +
					"(id          INTEGER      AUTO_INCREMENT NOT NULL, " +
					" confKey     VARCHAR(255),"  + 
					" name        TEXT, " + 
					" confDetail  TEXT,"  +
					" PRIMARY     KEY(id))"; 

			stmt.executeUpdate(sql);

			//creating paper table
			sql = "CREATE TABLE IF NOT EXISTS Paper " +
					"(id           INTEGER   AUTO_INCREMENT NOT NULL, " + 
					" title        TEXT,"          + 
					" pages		   VARCHAR(255),"  +
					" year         INTEGER, "      + 
					" confName     VARCHAR(255),"  +
					" paperKey     VARCHAR(255), " +
					" PRIMARY      KEY(id))"; 

			stmt.executeUpdate(sql);

			// Adding index to the paper key, making it faster during a join
//			 sql = "ALTER TABLE Paper ADD INDEX keyP(paperKey)";
//			 stmt.executeUpdate(sql);

			// creating author table
			sql = "CREATE TABLE IF NOT EXISTS Author " +
					"(id        INTEGER      AUTO_INCREMENT NOT NULL, " +
					" name      VARCHAR(255), " + 
					" paperKey  VARCHAR(255), " +
					" articleKey VARCHAR(255), " +
					" university  VARCHAR(255), " +
					" uniRegion   VARCHAR(255), " +
					" affiliatedUni VARCHAR(255), "+
					" url 		TEXT, "+
					" PRIMARY   KEY(id))" ;

			stmt.executeUpdate(sql);

			// Adding index to the paper key and articleKey in author making it faster during a join
//			 sql = "ALTER TABLE Author ADD INDEX keyA(paperKey)";
//			 stmt.executeUpdate(sql);
//			 
//			 sql = "ALTER TABLE Author ADD INDEX keyA1(articleKey)";
//			 stmt.executeUpdate(sql);
			

			//creating Committees table
			sql = "CREATE TABLE IF NOT EXISTS Committee " +
					"(id              INTEGER      AUTO_INCREMENT NOT NULL, " +
					" confName        VARCHAR(255), " + 
					" year            INTEGER, "      +   
					" authorName      VARCHAR(255), " + 
					" role            VARCHAR(255), " + 
					" PRIMARY         KEY(id))" ;

			stmt.executeUpdate(sql);

			// creating Article table
			sql = "CREATE TABLE IF NOT EXISTS Article " +
					"(id          INTEGER      AUTO_INCREMENT NOT NULL, " +	
					" title       TEXT, " + 
					" journal	  VARCHAR(255), " +
					" year        INTEGER, " + 
					" ee          TEXT, "      + 
					" articleKey	  VARCHAR(255), " +
					" PRIMARY     KEY(id))" ;

			stmt.executeUpdate(sql);
			
			// Adding indexes in article table on articleKey for faster access
//			sql = "ALTER TABLE Article ADD INDEX keyR(articleKey)";
//			stmt.executeUpdate(sql);
			
			// creating Favorites table
			sql = "CREATE TABLE IF NOT EXISTS Favorite_list " +
					"(id          INTEGER      AUTO_INCREMENT NOT NULL, " +
					" userName	  VARCHAR(255), " +
					" confName       VARCHAR(255), " + 
					" selectedAuthor	  VARCHAR(255), " +   
					" UNIQUE (userName, confName, selectedAuthor), "+
					" PRIMARY     KEY(id))" ;

			stmt.executeUpdate(sql);
			
			// creating Candidates List table
			sql = "CREATE TABLE IF NOT EXISTS Candidate_list " +
					"(id          INTEGER      AUTO_INCREMENT NOT NULL, " +
					" confName	  VARCHAR(255), " + 
					" selectedAuthor	  VARCHAR(255), " +   
					" UNIQUE (confName, selectedAuthor), "+
					" PRIMARY     KEY(id))" ;

			stmt.executeUpdate(sql);


		}catch(Exception se){
			//Handle errors for JDBC
			se.printStackTrace(); 

		}

	}

	/* 
	 * @see com.team7.interfaces.SchemaDB#getConnection()
	 */
	public Connection getConnection() throws IOException {


		if (conn == null) {
			//Database Properties for RDS
			String url = "jdbc:mysql://root.c9pxnh8wqisg.us-west-2.rds.amazonaws.com:3306/DBLP?useServerPrepStmts=false&rewriteBatchedStatements=true";
			String userName = "root";
			String password = "9HTa~TZ?dyQWM4}";
			
			// Database properties for local DB
//			String url = "jdbc:mysql://localhost/DBLP?verifyServerCertificate=false&useSSL=true&useServerPrepStmts=false&rewriteBatchedStatements=true";
//			String userName = "root";
//			String password = "root";

			try {

				conn = DriverManager.getConnection(url, userName, password);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return conn;
	}

	/* (non-Javadoc)
	 * @see com.team7.interfaces.SchemaDB#insertData(java.lang.Object)
	 */
	// Inserting data into the user table
	public boolean insertData(Object object_name) throws SQLException, IOException {

		Connection conn = getConnection();		

		try {
			// inserting the user into the DB
			if(object_name instanceof User) {

				PreparedStatement statement_inproceedings = conn.prepareStatement("insert into User(userName,password,role,confName) values(?,?,?,?)");
				statement_inproceedings.setString(1,(((User) object_name).getUserName()));
				statement_inproceedings.setString(2,((User) object_name).getPassword());
				statement_inproceedings.setString(3,((User) object_name).getRole());
				statement_inproceedings.setString(4,((User) object_name).getConfName());
				statement_inproceedings.executeUpdate();

			}else{
				return false;
			}

		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.team7.interfaces.SchemaDB#insertIntoCandidateList(java.lang.String)
	 */
	// Inserting data into the Candidate List table
	public boolean insertIntoCandidateList(String author) throws IOException, SQLException {
		
		Connection conn = getConnection();		
		PreparedStatement candidDetails_stmt = conn.prepareStatement("insert into Candidate_list(confName,selectedAuthor) values(?,?)");
		candidDetails_stmt.setString(1, UIConstants.currentUserConf);
		candidDetails_stmt.setString(2,author);							
		candidDetails_stmt.executeUpdate();
		return true;
		
	}
	
	/**
	 * Update candidate list.
	 *
	 * @param author the author
	 * @param conference the conference
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	// Deleting data from the Candidate List table
	public boolean updateCandidateList(String author, String conference) throws IOException, SQLException {

		Connection conn = getConnection();		
		PreparedStatement candidDetails_stmt = conn.prepareStatement("Delete from Candidate_list where selectedAuthor=? and confName=?");
		
		candidDetails_stmt.setString(1,author);	
		candidDetails_stmt.setString(2,conference);
		
		candidDetails_stmt.executeUpdate();
		return true;

	}

	/* (non-Javadoc)
	 * @see com.team7.interfaces.SchemaDB#insertIntoFavList(java.lang.String, java.lang.String, java.lang.String)
	 */
	// Inserting data into the favorites list table
	public boolean insertIntoFavList(String userName, String conference, String author) throws IOException, SQLException {
		
		Connection conn = getConnection();
		PreparedStatement favList_stmt = conn.prepareStatement("insert into Favorite_list(userName,confName,selectedAuthor) values(?,?,?)");
		favList_stmt.setString(1,userName);
		favList_stmt.setString(2,conference);
		favList_stmt.setString(3,author);

		favList_stmt.executeUpdate();
		return true;
	}
	
	/**
	 * Update fav list.
	 * Updating the favorites list table
	 * @param userName the user name
	 * @param author the author
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
	public boolean updateFavList(String userName, String author) throws SQLException, IOException {
		
		Connection conn = getConnection();
		PreparedStatement favList_stmt = conn.prepareStatement("Delete from Favorite_list where selectedAuthor=? and userName=?");
		favList_stmt.setString(1,author);
		favList_stmt.setString(2, userName);
		
		favList_stmt.executeUpdate();	
		return true;
		
	}
}