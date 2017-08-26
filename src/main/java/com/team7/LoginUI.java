package com.team7;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

// LoginUI window
public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public JPanel contentPane;
	public JTextField userNameField;
	public JPasswordField passwordField;
	public JButton btnLogin;
	public JButton btnNewUserClick; 

	static String currentUser = null; // to maintain the 'session' for the user.
	public String userName;
	public String password;

	static LoginUI frame; 


	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, JAXBException, IOException {

		System.setProperty("java.awt.headless", "true");
		// Below was used to create database ,extract xml and insert data into tables

//		    	File file = new File("input/dblp.xml");
//		    	File comData = new File("input/committees/");
//
//		    	// Parsing the xml to create objects
//		    	ImplementParseDatabase parse = new ImplementParseDatabase();
//		    	ImplementSchemaDB db=new ImplementSchemaDB();;
//		    	ImplementCommittees com = new ImplementCommittees();
//		
//				try {
//					
//			    	db.dbSetUp();   //set up initial database
//			    	parse.parseXml(file);	//parse xml data
//			    	com.ParseFiles(comData); //parse committee data
//		
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame= new LoginUI();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("MSD PROJECT");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {

		setSize(1000, 600);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("WELCOME !");
		lblWelcome.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblWelcome.setBounds(401, 39, 153, 32);
		contentPane.add(lblWelcome); 

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsername.setBounds(317, 121, 97, 28);
		contentPane.add(lblUsername);

		userNameField = new JTextField();

		UIManager.put("ToolTip.background", Color.orange);
		UIManager.getLookAndFeelDefaults()
		.put("ToolTip.font", new Font("Lucida Grande", Font.BOLD, 14));

		userNameField.setToolTipText("Please enter registered Email-id");
		userNameField.setBounds(472, 119, 191, 34);
		contentPane.add(userNameField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPassword.setBounds(317, 208, 81, 19);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(472, 201, 191, 34);
		contentPane.add(passwordField);

		btnLogin = new JButton("Login"); 
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogin.setBounds(408, 282, 117, 34);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// encrypted password from the UI
				userName = userNameField.getText();
				String plainText = new String(passwordField.getPassword());

				if (userName.equals("")) //username should not be empty
					messageShow("Please enter username");
				else if (plainText.equals(""))
					messageShow("Please enter password"); // password should not be empty
				else {
					try {
						ImplementRegister register = new ImplementRegister();
						// check if user exists or not and then validate the password.
						try {
							if (register.verifyIfUserExists(userName)) {
								ImplementLogin login = new ImplementLogin();
								if (login.login(userName, plainText)) {

									// assign currentUser as username
									currentUser = userName;
									// let it go to the search page if login is successful
									dispose();
									SearchUI search = new SearchUI();
									search.setVisible(true);
									search.setSize(1000,600);
									search.setLocationRelativeTo(null);

								}
								else {
									messageShow("Invalid Credentials: Username and password don't match");
								}
							}
							else {
								messageShow("User does not exist. Please register");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
					}		

				}
			}
		}
				);

		btnNewUserClick = new JButton("New User? Click to Register");
		btnNewUserClick.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewUserClick.setBounds(333, 339, 287, 34);
		contentPane.add(btnNewUserClick);
		btnNewUserClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUI register;
				// go to the register page
				dispose();
				register = new RegisterUI();
				register.setVisible(true);
				register.setSize(1000,600);
				register.setLocationRelativeTo(null);
			}
		});
	} 

	public void messageShow (String msg) {

		final JDialog d = new JDialog(frame, msg, true);
		d.setSize(500, 100);
		d.setLocationRelativeTo(frame);


		d.addWindowListener(null);
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Timer timer = new Timer(3000, new ActionListener() { // 3 sec
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				d.dispose();
			}
		});

		timer.start();
		d.setVisible(true);
	}

}
