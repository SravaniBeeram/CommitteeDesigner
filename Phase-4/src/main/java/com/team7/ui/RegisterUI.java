package com.team7.ui;

import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 * The Class RegisterUI.
 */
// Register UI window
@SuppressWarnings("serial")
public class RegisterUI extends JFrame {

	/** The content pane. */
	public JPanel contentPane;
	
	/** The UserName T field. */
	public JTextField UsernameTField;
	
	/** The password field. */
	public JPasswordField passwordField; 
	
	/** The register button. */
	public JButton btnRegister; 
	
	/** The  login button. */
	public JButton btnLogin;

	/** The user name. */
	String userName;
	
	/** The password. */
	String password;
	
	/** The role. */
	String role; 
	
	/** The conference name. */
	String confName;
	
	/** The log. */
	LoginUI log = new LoginUI();

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public RegisterUI() {

		setTitle("REGISTER");
		setResizable(false);

		setSize(UIConstants.width, UIConstants.height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsername.setBounds(420, 168, 97, 28);
		contentPane.add(lblUsername);

		UsernameTField = new JTextField(10);

		UIManager.put("ToolTip.background", Color.orange);
		UIManager.getLookAndFeelDefaults()
		.put("ToolTip.font", new Font("Lucida Grande", Font.BOLD, 14));

		UsernameTField.setToolTipText("Please enter valid Email-id");

		UsernameTField.setBounds(580, 166, 191, 34);
		contentPane.add(UsernameTField);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPassword.setBounds(420, 238, 81, 19);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(580, 231, 191, 34);
		contentPane.add(passwordField);


		JLabel lblRole = new JLabel("Role: ");
		lblRole.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblRole.setBounds(420, 298, 56, 16);
		contentPane.add(lblRole);
		String[] roleList = {"Conference Chair","General Chair","Member for External Review Committee","Program Chair"};

		@SuppressWarnings("rawtypes")
		final JComboBox role_combo = new JComboBox(roleList);
		role = roleList[0]; //setting initial value
		role_combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				role = String.valueOf(role_combo.getSelectedItem());

			}
		});
		role_combo.setBounds(580, 291, 191, 34);
		contentPane.add(role_combo);


		JLabel lblConference = new JLabel("Conference: ");
		lblConference.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblConference.setBounds(420, 359, 97, 28);
		contentPane.add(lblConference);
		String[] confList = {"ECOOP","ICFP","OOPSLA","PLDI"};

		@SuppressWarnings("rawtypes")
		final JComboBox conf_combo = new JComboBox(confList);
		confName = confList[0];  //setting initial value
		conf_combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				confName = String.valueOf(conf_combo.getSelectedItem());

			}
		});
		conf_combo.setBounds(580, 358, 191, 34);
		contentPane.add(conf_combo);

		JLabel lblRegistrationPage = new JLabel("Registration Page");
		lblRegistrationPage.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblRegistrationPage.setBounds(487, 76, 234, 28);
		contentPane.add(lblRegistrationPage);

		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnRegister.setBounds(515, 451, 114, 34);
		contentPane.add(btnRegister);

		btnRegister.addActionListener(new ActionListener()
		{

			String res = null;

			public void actionPerformed(ActionEvent e)
			{
				ImplementRegister register = new ImplementRegister();
				userName = UsernameTField.getText();
				String plainPwd = new String(passwordField.getPassword());

				// empty/blank user name or password is not allowed
				if(userName.equals("")) {
					log.messageShow("Please enter user name");
				} 
				else if (userName.contains("\"")) {
					log.messageShow("Username cannot contain double quotes");
				}
				else if (userName.contains(" ")) {
					log.messageShow("Username cannot contain blank spaces");
				}
				else if (userName.length() > 50) {
					// user name having length > 50 is also not allowed
					log.messageShow("Email ID should not have more than 50 characters");
				}
				else if(plainPwd.equals("")) {
					log.messageShow("Please enter password");
				} 
				else if (plainPwd.contains(" ")) {
					log.messageShow("Password cannot contain blank spaces");
				}
				else {
 
					try {
						//insert data into table
						res = register.createUser(userName,plainPwd,role,confName);
						
					} catch (Exception e1) { 
						System.out.println("error");
					} 

					if(res.equals("true")){
						
						//set current user to session
						UIConstants.currentUser = userName;
						UIConstants.currentUserRole = role;
						UIConstants.currentUserConf = confName;
						
						//connect to search page
						dispose();
						SearchUI search = new SearchUI();
						search.setVisible(true);
						setSize(UIConstants.width, UIConstants.height);
						search.setLocationRelativeTo(null);
					}

					// if user already exists
					else if (res.equals("exists")) {
						log.messageShow("Username already exists");
					}
					
					else if (res.equals("invalid email")) {
						log.messageShow("Enter a valid email ID");
					}
				}}});
		
		//button to login screen
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogin.setBounds(1028, 13, 117, 34);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 LoginUI log = new LoginUI();
				 log.setVisible(true);
				 setSize(UIConstants.width, UIConstants.height);
				 log.setLocationRelativeTo(null);
			}
		});
	}
}
