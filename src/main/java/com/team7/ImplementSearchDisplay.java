package com.team7;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.*;
import javax.mail.internet.*;


public class ImplementSearchDisplay implements SearchDisplay {

	// Keeps track of all filter criteria and their values
	public List<String> search(List<SearchParameter> searchParameter) throws SQLException, IOException {
		
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		List<String> query = queryBuilderObject.createQuery(searchParameter);
        return queryBuilderObject.getResultForDisplay(query);
	
	} 
 
	// Returns the candidate details for each author selected by the user
	public ResultSet candidateDetails(Set<String> authors) throws SQLException, IOException {
		
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		ResultSet detailsResultSet = queryBuilderObject.sendQuery(query);
		return detailsResultSet; 
		
	}

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
				sb.append("This is my preferred list of authors for committee of " + conference +":");
				sb.append("\n");
				sb.append("\n");
				for (String str: authors) {
					sb.append(str + "\n");
				}

				// Body of the email
				message.setText(sb.toString());

				Transport.send(message);

				return "success";
			}  
			catch(MessagingException mex) {
				//mex.printStackTrace();
			}
		}
		return "failure";
	}
}
