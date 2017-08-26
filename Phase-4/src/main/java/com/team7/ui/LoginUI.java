package com.team7.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;

import com.team7.abstractDesignFactory.AbstractParseFactory;
import com.team7.abstractDesignFactory.FactoryProducer;
import com.team7.interfaces.ParseCsvFiles;
import com.team7.interfaces.ParseTextFile;
import com.team7.interfaces.ParseXml;
import com.team7.parsing.ImplementAuthorAffData;
import com.team7.parsing.ImplementCommittees;
import com.team7.parsing.ImplementHomePageData;
import com.team7.parsing.ImplementParseDatabase;
import com.team7.parsing.ImplementSchemaDB;
import com.team7.parsing.ImplementUniCountryData;
import com.team7.parsing.ImplementUniversityAuthorData;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 * The Class LoginUI.
 * LoginUI window
 */ 
public class LoginUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	public JPanel contentPane;
	
	/** The user name field. */
	public JTextField userNameField;
	
	/** The password field. */
	public JPasswordField passwordField;
	
	/** The btn login. */
	public JButton btnLogin;
	
	/** The btn new user click. */
	public JButton btnNewUserClick; 

	/** The user name. */
	public String userName;	
	
	/** The password. */
	public String password;

	/** The frame. */
	static LoginUI frame; 


	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JAXBException the JAXB exception
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, JAXBException {

		System.setProperty("java.awt.headless", "true");
		
		/*
		 * The below commented lines are used for parsing. For parsing the data again, you 
		 * need to uncomment these. Refer to the Installation Instructions for more details
		 */
		
//		ImplementSchemaDB db=new ImplementSchemaDB();
//		db.dbSetUp();
//
//		// Extracting the XML file and inserting data into DB
//		AbstractParseFactory xmlFactory = FactoryProducer.getFactory("XML");
//		
//		ParseXml parse = xmlFactory.getXml("input/dblp.xml");
//		parse.parseXml();
//		
//		// Extracting the CSV file and inserting data into DB
//		AbstractParseFactory csvFactory = FactoryProducer.getFactory("CSV");
//		
//		// author and their universities
//		ParseCsvFiles uniData = csvFactory.getCsv("input/generate-author-info.csv");
//		uniData.parseCsv();
//		
//		// university and their regions
//		ParseCsvFiles countryData = csvFactory.getCsv("input/country-info.csv");
//		countryData.parseCsv();
//		
//		// authors and affiliated universities
//		ParseCsvFiles affData = csvFactory.getCsv("input/faculty-affiliations.csv");
//		affData.parseCsv();
//		
//		// authors and their home page urls
//		ParseCsvFiles hPageData = csvFactory.getCsv("input/homepages.csv");
//		hPageData.parseCsv();
//		
//		// Extracting the files from Committee folder and inserting data into DB
//		AbstractParseFactory txtFactory = FactoryProducer.getFactory("TXT");
//		
//		ParseTextFile comData = txtFactory.getText("input/committees/");
//		comData.parseText();
		

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

		pack();
		setSize(UIConstants.width, UIConstants.height);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("WELCOME !");
		lblWelcome.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblWelcome.setBounds(551, 65, 153, 32);
		contentPane.add(lblWelcome); 

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsername.setBounds(437, 151, 97, 28);
		contentPane.add(lblUsername);

		userNameField = new JTextField();

		UIManager.put("ToolTip.background", Color.orange);
		UIManager.getLookAndFeelDefaults()
		.put("ToolTip.font", new Font("Lucida Grande", Font.BOLD, 14));

		userNameField.setToolTipText("Please enter registered Email-id");
		userNameField.setBounds(592, 149, 191, 34);
		contentPane.add(userNameField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPassword.setBounds(437, 228, 81, 19);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(592, 221, 191, 34);
		contentPane.add(passwordField);

		btnLogin = new JButton("Login"); 
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogin.setBounds(558, 312, 117, 34);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// encrypted password from the UI
				userName = userNameField.getText();
				String plainText = new String(passwordField.getPassword());

				if (userName.equals("")) //userName should not be empty
					messageShow("Please enter username");
				else if (plainText.equals(""))
					messageShow("Please enter password"); // password should not be empty
				else {
					try {
						ImplementRegister register = new ImplementRegister();
						// check if user exists or not and then validate the password.
						try {
							//including an escape character if string contains '
							if(userName.contains("'")){
								userName = userName.replaceAll("'","\\\\'");
							}
							if (register.verifyIfUserExists(userName)) {
								ImplementLogin login = new ImplementLogin();
								if (login.login(userName, plainText)) {

							      // let it go to the search page if login is successful
									dispose();
									SearchUI search = new SearchUI();
									search.setVisible(true);
									setSize(UIConstants.width, UIConstants.height);
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
							e1.printStackTrace();
						}
					} catch (IOException e2) {
						e2.printStackTrace();
					}		

				}
			}
		}
				);

		//To register new user
		btnNewUserClick = new JButton("New User? Click to Register");
		btnNewUserClick.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewUserClick.setBounds(483, 369, 287, 34);
		contentPane.add(btnNewUserClick);
		btnNewUserClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUI register;
				// go to the register page
				dispose();
				register = new RegisterUI();
				register.setVisible(true);
				setSize(UIConstants.width, UIConstants.height);
				register.setLocationRelativeTo(null);
			}
		});
	} 

	/**
	 * Message show.
	 *
	 * @param msg the msg
	 */
	public void messageShow (String msg) {

		final JDialog d = new JDialog(frame, msg, true);
		d.setSize(500, 100);
		d.setLocationRelativeTo(frame);

		d.addWindowListener(null);
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Timer timer = new Timer(3000, new ActionListener() { // closes error dialog in 3 sec
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				d.dispose();
			}
		});

		timer.start();
		d.setVisible(true);
	}

}
