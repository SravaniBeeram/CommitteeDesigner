package com.team7.queryEngine;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.mail.*;
import javax.mail.internet.*;

import com.team7.interfaces.SearchDisplay;
import com.team7.parsing.ImplementSchemaDB;


/**
 * The Class ImplementSearchDisplay.
 */
public class ImplementSearchDisplay implements SearchDisplay {

	/* 
	 * @see com.team7.interfaces.SearchDisplay#search(java.util.List)
	 */
	// Keeps track of all filter criteria and their values
	public List<String> search(List<SearchParameter> searchParameter) throws SQLException, IOException {
		
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		List<String> query = queryBuilderObject.createQuery(searchParameter);
        return queryBuilderObject.getResultForDisplay(query);
	
	} 
	
	/* 
	 * @see com.team7.interfaces.SearchDisplay#similarAuthor(java.lang.String)
	 */
	// to extract a list of similar authors based on the given author
	public Set<String> similarAuthor(String author) throws SQLException, IOException {
		List<String> similarAuth = new ArrayList<String>();
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForSimilarAuthors(author);
		ResultSet similarResultSet = queryBuilderObject.sendQuery(query);
		
		while (similarResultSet.next()) {
			similarAuth.add(similarResultSet.getString(1));
		}
		
		return new TreeSet<String>(similarAuth);
		
	}
	
	/**
	 * Favorite authors.
	 *
	 * @param attName the  name
	 * @param attValue the  value
	 * @return the sets the
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// to extract favorites for one author or from one conference
	public Set<String> favAuthors(String attName, String attValue) throws SQLException, IOException {
		List<String> favList = new ArrayList<String>();
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForFavList(attName, attValue);
		ResultSet favResultSet = queryBuilderObject.sendQuery(query);
		
		while (favResultSet.next()) {
			favList.add(favResultSet.getString(1));
		}
		
		return new TreeSet<String>(favList);
	}
	
	/**
	 * List for committee.
	 *
	 * @param conference the conference
	 * @return the sets the
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// to extract favorites of program chair
	public Set<String> listForCommittee(String conference) throws SQLException, IOException {
		
		List<String> CommList = new ArrayList<String>();
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForCommitteeList(conference);
		ResultSet CommResultSet = queryBuilderObject.sendQuery(query);
		
		while (CommResultSet.next()) {
			CommList.add(CommResultSet.getString(1));
		}
		
		return new TreeSet<String>(CommList);
	}
 
	/* 
	 * @see com.team7.interfaces.SearchDisplay#candidateDetails(java.util.Set)
	 */
	// Returns the candidate details for each author selected by the user
	public ResultSet candidateDetails(Set<String> authors) throws SQLException, IOException {
		
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet detailsResultSet = queryBuilderObject.sendQuery(query);
		return detailsResultSet; 
		
	}

	/* 
	 * @see com.team7.interfaces.SearchDisplay#sendEmail(java.util.Set, java.lang.String)
	 */
	// sends an email to the users with the list of finalized authors
	public String sendEmail(Set<String> authors, String userName) throws SQLException, IOException {
		// TODO Auto-generated method stub
		// get the conference of the current user
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		String conference = null;

		ResultSet rs1 = stmt.executeQuery("select confName from User where username='"+userName+"'");
		while (rs1.next()) {
			conference = rs1.getString(1);
		}

		// In the user table, extract user names of all authors in the same conference 
		// as the current user. 
		ResultSet rs2 = stmt.executeQuery("select username from User where confName='" +conference+"'");

		// Then send an email to that list of users with the set of authors obtained from UI
		while (rs2.next()) {
			
			// Recipient's email ID
			String to = rs2.getString("username");

			//Sender's email ID
			String from = "dummy4235@gmail.com"; // This is a valid email ID which has been configured

			// Get system properties and set up mail server
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			// Get the default Session object.
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// set actual password
					return new PasswordAuthentication("dummy4235@gmail.com", "qazwsxedc123");
				}
			});

			try {
				// Create a default MimeMessage object
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Subject line
				message.setSubject("List of authors selected by " + userName );

				// Format the incoming list into a string
				StringBuilder sb = new StringBuilder();
				sb.append("Hi All,");
				sb.append("\n");
				sb.append("\n");

				sb.append("This is list of authors for committee of " + conference +":");
				sb.append("\n");
				sb.append("\n");
				for (String str: authors) {
					sb.append(str + "\n");
				}
				
				sb.append("\n");
				sb.append("\n");
				sb.append("Thank you");

				// Body of the email
				message.setText(sb.toString());

				Transport.send(message);

			}  
			catch(MessagingException mex) {
				//mex.printStackTrace();
			}
		}
		return "success";
	}


}
