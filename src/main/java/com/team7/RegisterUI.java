package com.team7;

import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

// Register UI window
@SuppressWarnings("serial")
public class RegisterUI extends JFrame {

	public JPanel contentPane;
	public JTextField UsernameTField;
	public JPasswordField passwordField; 
	JButton btnRegister; 
	JButton btnLogin;

	String userName;
	String password;
	String role; 
	String confName;
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	LoginUI log = new LoginUI();

	/**
	 * Create the frame.
	 * @throws SQLException  
	 */
	@SuppressWarnings("unchecked")
	public RegisterUI() {

		setTitle("REGISTER");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsername.setBounds(257, 148, 97, 28);
		contentPane.add(lblUsername);

		UsernameTField = new JTextField(10);

		UIManager.put("ToolTip.background", Color.orange);
		UIManager.getLookAndFeelDefaults()
		.put("ToolTip.font", new Font("Lucida Grande", Font.BOLD, 14));


		UsernameTField.setToolTipText("Please enter valid Email-id");

		UsernameTField.setBounds(521, 146, 191, 34);
		contentPane.add(UsernameTField);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPassword.setBounds(257, 218, 81, 19);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(521, 211, 191, 34);
		contentPane.add(passwordField);


		JLabel lblRole = new JLabel("Role: ");
		lblRole.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblRole.setBounds(257, 293, 56, 16);
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
		role_combo.setBounds(521, 286, 191, 34);
		contentPane.add(role_combo);


		JLabel lblConference = new JLabel("Conference: ");
		lblConference.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblConference.setBounds(257, 359, 97, 28);
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
		conf_combo.setBounds(521, 353, 191, 34);
		contentPane.add(conf_combo);

		JLabel lblRegistrationPage = new JLabel("Registration Page");
		lblRegistrationPage.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblRegistrationPage.setBounds(340, 56, 234, 28);
		contentPane.add(lblRegistrationPage);

		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnRegister.setBounds(394, 451, 114, 34);
		contentPane.add(btnRegister);

		btnRegister.addActionListener(new ActionListener()
		{

			String res = null;

			public void actionPerformed(ActionEvent e)
			{
				ImplementRegister register = new ImplementRegister();
				userName = UsernameTField.getText();
				String plainPwd = new String(passwordField.getPassword());

				// empty username or password is not allowed
				if(userName.equals("")) {
					log.messageShow("Please enter  user name");
				} else if(plainPwd.equals("")) {
					log.messageShow("Please enter password");
				} else {
 
					try {
						//insert data into table
						res = register.createUser(userName,plainPwd,role,confName);
						
					} catch (Exception e1) { 
						// TODO Auto-generated catch block
					} 

					if(res.equals("true")){
						//connect to search page
						dispose();
						SearchUI search = new SearchUI();
						search.setVisible(true);
						search.setSize(1000, 600);
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
		
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogin.setBounds(735, 28, 117, 34);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 LoginUI log = new LoginUI();
				 log.setVisible(true);
				 log.setSize(1000,600); 
				 log.setLocationRelativeTo(null);
			}
		});


	}
}
