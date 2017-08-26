package com.team7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementCommittees implements Committees {

	// parsing the files in the committee folder and 
	// extracting needed information from it
	public String ParseFiles(File textFile) throws IOException, SQLException {
		// TODO Auto-generated method stub

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into Committee(confName,year,authorName,role) values(?,?,?,?)");

		String rec = null;
		String authorName = null;
		String role = null;
		String confName = null;
		int year;

		final int batchSize = 1000;
		int i=0;
		if(textFile.list().length > 0 ){
			
			for(File fName : textFile.listFiles()){
				
				BufferedReader bf = new BufferedReader(new FileReader(fName));		
				String fileName = fName.getName();	

				// .DS_Store is a file that stores custom attributes of its containing folder, 
				// such as the position of icons or the choice of a background image
				// This file is created in a folder by the 'mac OS operating system', and since we don't need to
				// use that for parsing, removing it while parsing.

				if (!fileName.equals(".DS_Store")){

					List<String> output = new ArrayList<String>();
					Matcher match = Pattern.compile("[0-9]+|[a-z]+|[A-Z]").matcher(fileName);
					while (match.find()) {
						output.add(match.group());
					}

					while ((rec = bf.readLine()) != null)	    	
					{
						if(rec.contains(":")) {
							role = rec.substring(0,rec.indexOf(':'));
							authorName = rec.substring(rec.indexOf(':')+1,rec.length());	
						}
						else {
							role = null;
							authorName = rec;
						}
 
						// extracting the the conference name and the year of the committee
						// from the file name
						confName = output.get(0);
						year = Integer.parseInt(output.get(1));
						// information about only the 4 conferences required.
						if (  (confName.equalsIgnoreCase("oopsla")) 
						   || (confName.equalsIgnoreCase("pldi"))
						   || (confName.equalsIgnoreCase("ecoop")) 
						   || (confName.equalsIgnoreCase("icfp")) )
						{
							stmt.setString(1,confName);
							stmt.setInt(2,year);
							stmt.setString(3,authorName);
							stmt.setString(4,role);
							stmt.addBatch(); 

							if (++i % batchSize == 0){ 
								stmt.executeBatch();  
							}
						}
					}
					bf.close();
				}
			}
			stmt.executeBatch();
			return "success";
		}
		return "No data";
	}
}
