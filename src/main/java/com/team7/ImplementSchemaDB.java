package com.team7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
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

public class ImplementSchemaDB implements SchemaDB { 

	Properties props;

	// creating  DB and its initial skeleton 
	public void dbSetUp() throws ClassNotFoundException, SQLException, IOException{

		// JDBC driver name and database URL		
//		getDBProperties(); 
		//Database Properties 
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

		Connection conn = null;
		Statement stmt = null;
		String sql = null;

		try{
			//Register JDBC driver
			Class.forName(JDBC_DRIVER);

          			
			//selecting database created above
//			String connected_db = "jdbc:mysql://root.c9pxnh8wqisg.us-west-2.rds.amazonaws.com:3306/DBLP";
//			String userName = "root";
//			String password = "9HTa~TZ?dyQWM4}";

			String DB_URL = "jdbc:mysql://localhost?verifyServerCertificate=false&useSSL=true";
			String userName = "root";
			String password = "root";

			conn = DriverManager.getConnection(connected_db, userName, password);
			stmt = conn.createStatement();

			//creating user table
			sql = "CREATE TABLE IF NOT EXISTS User " +
					"(id       INTEGER      AUTO_INCREMENT NOT NULL, " +
					" username VARCHAR(255) UNIQUE, " + 
					" password VARCHAR(255), " + 
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
//			sql = "ALTER TABLE Paper ADD INDEX keyP(paperKey)";
//			stmt.executeUpdate(sql);
//			System.out.println("Created index on Key in paper table...");

			// creating author table
			sql = "CREATE TABLE IF NOT EXISTS Author " +
					"(id        INTEGER      AUTO_INCREMENT NOT NULL, " +
					" name      VARCHAR(255), " + 
					" paperKey  VARCHAR(255), " + 
					" PRIMARY   KEY(id))" ;

			stmt.executeUpdate(sql);

// Adding index to the paper key in author making it faster during a join
//			sql = "ALTER TABLE Author ADD INDEX keyA(paperKey)";
//			stmt.executeUpdate(sql);
//			System.out.println("Created index on Key in author table...");

			//creating AuthorDetails table
			sql = "CREATE TABLE IF NOT EXISTS Author_Details " +
					"(id        INTEGER      AUTO_INCREMENT NOT NULL, " +
					" name      VARCHAR(255), " + 
					" url       TEXT, " + 
					" PRIMARY   KEY(id))" ;

			stmt.executeUpdate(sql);

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
					" author	  VARCHAR(255), " +
					" title       TEXT, " + 
					" journal	  VARCHAR(255), " +
					" year        INTEGER, " + 
					" month       VARCHAR(255), " + 
					" ee          TEXT, "      +   
					" PRIMARY     KEY(id))" ;

			stmt.executeUpdate(sql);


		}catch(Exception se){
			//Handle errors for JDBC
			se.printStackTrace(); 

		}
		conn.close();

	}

	public Connection getConnection() throws IOException {

		Connection conn = null; 
//		getDBProperties();
		
		//Database Properties
//		String url = "jdbc:mysql://root.c9pxnh8wqisg.us-west-2.rds.amazonaws.com:3306/DBLP?useServerPrepStmts=false&rewriteBatchedStatements=true";
//		String userName = "root";
//		String password = "9HTa~TZ?dyQWM4}";

		String DB_URL = "jdbc:mysql://localhost?verifyServerCertificate=false&useSSL=true";
		String userName = "root";
		String password = "root";

		try {

			conn = DriverManager.getConnection(url, userName, password);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;
	}
	
//	private void getDBProperties() throws IOException{
//		
//		props = new Properties();
//		FileInputStream in;
//		try {
//			in = new FileInputStream("config/db.properties");
//			props.load(in);
//			in.close();
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}  
//		
//	}

	public boolean insertData(Object object_name) throws SQLException, IOException {

		Connection conn = getConnection();		

		try {
			// inserting the user into the DB
			if(object_name instanceof User) {
				
				PreparedStatement statement_inproceedings = conn.prepareStatement("insert into User(userName,password,role,confName) values(?,?,?,?)");
				statement_inproceedings.setString(1,(((User) object_name).getUserName()));
				statement_inproceedings.setString(2,((User) object_name).getPassword());
				statement_inproceedings.setString(3,  ((User) object_name).getRole());
				statement_inproceedings.setString(4, ((User) object_name).getConfName());
				statement_inproceedings.executeUpdate();

			}else{
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		conn.close();
		return true;
	}
}
