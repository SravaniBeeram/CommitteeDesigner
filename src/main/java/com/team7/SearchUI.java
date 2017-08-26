package com.team7;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

// Search Criteria page is created here
@SuppressWarnings("serial")
public class SearchUI extends JFrame { 

	private JPanel contentPane;
	private JTextField ValueField_1;
	private JTextField ValueField_2;
	private JTextField ValueField_3;
	private JTextField ValueField_4;
	private JTextField ValueField_5;
	JButton NewRowButton_1;
	JButton NewRowButton_2; 
	JButton NewRowButton_3;
	JButton NewRowButton_4;
	JButton btnLogout;
	JButton btnNewButton; // submit button

	SearchParameter sp1 = new SearchParameter();
	SearchParameter sp2 = new SearchParameter();
	SearchParameter sp3 = new SearchParameter();
	SearchParameter sp4 = new SearchParameter();
	SearchParameter sp5 = new SearchParameter();

	boolean q1 = true;
	boolean q2 = false;
	boolean q3 = false;
	boolean q4 = false;
	boolean q5 = false;

	List<SearchParameter> finalList = new ArrayList<SearchParameter>();
	LoginUI log = new LoginUI();
	ImplementSearchDisplay sd = new ImplementSearchDisplay();


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SearchUI() {

		setTitle("Search UI");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSearch.setBounds(401, 39, 153, 32);
		contentPane.add(lblSearch);


		/*****************Variables Declaration*********************************/

		/***************** Creating panels******************/
		final JPanel panel_1 = new JPanel();

		final JPanel panel_2 = new JPanel();
		panel_2.setVisible(false);

		final JPanel panel_3 = new JPanel();
		panel_3.setVisible(false);

		final JPanel panel_4 = new JPanel();
		panel_4.setVisible(false);

		final JPanel panel_5 = new JPanel();
		panel_5.setVisible(false);


		/****************************Search Criteria*********************************************/
		final HashMap<String,String> Criteria = new HashMap<String,String>();
		Criteria.put("Author Name","Name");
		Criteria.put("Committee Year","Committee.Year");
		Criteria.put("Committee Conf Name", "Committee.ConfName");
		Criteria.put("Conference Name", "ConfName");
		Criteria.put("Count Of Papers", "CountNoOfPapers");
		Criteria.put("Keyword in Title", "Keyword");
		Criteria.put("Paper Published Year", "Year");
		Criteria.put("Journal Name", "JournalName");
		List<String> keys = new ArrayList<String>(Criteria.keySet());

		/*************************Connection between two queries***********************************/
		String[] JoinList = {"AND","OR"};

		/*******************Conference Names*****************************/

		final String[] conf = {"OOPSLA","ECOOP","PLDI","ICFP"};

		/*******************Journal Names*****************************/	

		final String[] jour = {"TSE","TOPLAS"};


		/*************************ComboBox for Relational Operators************************************/
		final JComboBox ComparatorComboBox_1 = new JComboBox();
		final JComboBox ComparatorComboBox_2 = new JComboBox();
		final JComboBox ComparatorComboBox_3 = new JComboBox();
		final JComboBox ComparatorComboBox_4 = new JComboBox();
		final JComboBox ComparatorComboBox_5 = new JComboBox();


		/******************************Join Condition label***********************************************************/
		final JLabel JoinLabel_1 = new JLabel("Join Condition");
		JoinLabel_1.setVisible(false);

		final JLabel JoinLabel_2 = new JLabel("Join Condition");
		JoinLabel_2.setVisible(false);

		final JLabel JoinLabel_3 = new JLabel("Join Condition");
		JoinLabel_3.setVisible(false);

		final JLabel JoinLabel_4 = new JLabel("Join Condition");
		JoinLabel_4.setVisible(false);

		/*********************************ComboBox for Join Conditions between queries*****************************************/
		final JComboBox JoinComboBox_1= new JComboBox(JoinList);
		JoinComboBox_1.setSelectedItem(JoinList[0]);	
		JoinComboBox_1.setVisible(false);

		final JComboBox JoinComboBox_2= new JComboBox(JoinList);
		JoinComboBox_2.setSelectedItem(JoinList[0]);	
		JoinComboBox_2.setVisible(false);

		final JComboBox JoinComboBox_3= new JComboBox(JoinList);
		JoinComboBox_3.setSelectedItem(JoinList[0]);	
		JoinComboBox_3.setVisible(false);

		final JComboBox JoinComboBox_4= new JComboBox(JoinList);
		JoinComboBox_4.setSelectedItem(JoinList[0]);	
		JoinComboBox_4.setVisible(false);

		/***************************************ComboBox for journal and conference names****************************************************************************/
		final JComboBox SelectionComboBox_1 = new JComboBox();
		SelectionComboBox_1.setVisible(false);

		final JComboBox SelectionComboBox_2 = new JComboBox();
		SelectionComboBox_2.setVisible(false);

		final JComboBox SelectionComboBox_3 = new JComboBox();
		SelectionComboBox_3.setVisible(false);

		final JComboBox SelectionComboBox_4 = new JComboBox();
		SelectionComboBox_4.setVisible(false);

		final JComboBox SelectionComboBox_5 = new JComboBox();
		SelectionComboBox_5.setVisible(false);

		/******************************************Text field for values**************************************************************/
		ValueField_1 = new JTextField();
		ValueField_1.setVisible(true);

		ValueField_2 = new JTextField();
		ValueField_2.setVisible(true);

		ValueField_3 = new JTextField();
		ValueField_3.setVisible(true);

		ValueField_4 = new JTextField();
		ValueField_4.setVisible(true);

		ValueField_5 = new JTextField();
		ValueField_5.setVisible(true);


		/****************************************************** Panel -1 *******************************************/		

		panel_1.setBounds(42, 136, 924, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);


		JLabel CriteriaLabel_1 = new JLabel("Criteria");
		CriteriaLabel_1.setBounds(12, 13, 56, 16);
		panel_1.add(CriteriaLabel_1);

		final JComboBox CriteriaComboBox_1 = new JComboBox(keys.toArray());
		CriteriaComboBox_1.setBounds(67, 10, 166, 22);
		panel_1.add(CriteriaComboBox_1);

		CriteriaComboBox_1.setSelectedItem(keys.toArray()[0]);
		//sp1.searchFilter = Criteria.get(CriteriaComboBox_1.getSelectedItem());
		ComparatorComboBox_1.addItem("=");
		ComparatorComboBox_1.addItem("!=");
		ComparatorComboBox_1.addItem("LIKE");
		ComparatorComboBox_1.addItem("NOT LIKE");

		CriteriaComboBox_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_1.removeAllItems();	
				ValueField_1.setVisible(true);
				SelectionComboBox_1.setVisible(false);


				if(CriteriaComboBox_1.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_1.addItem("LIKE");
					ComparatorComboBox_1.addItem("NOT LIKE");


				} else if(CriteriaComboBox_1.getSelectedItem() == "Author Name"){

					ComparatorComboBox_1.addItem("=");	
					ComparatorComboBox_1.addItem("!=");
					ComparatorComboBox_1.addItem("LIKE");
					ComparatorComboBox_1.addItem("NOT LIKE");

				}else if(CriteriaComboBox_1.getSelectedItem() == "Committee Year" || CriteriaComboBox_1.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_1.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_1.addItem(">");
					ComparatorComboBox_1.addItem("<");
					ComparatorComboBox_1.addItem("=");
					ComparatorComboBox_1.addItem(">=");
					ComparatorComboBox_1.addItem("<=");
					ComparatorComboBox_1.addItem("!=");

				}else if(CriteriaComboBox_1.getSelectedItem() == "Conference Name" || CriteriaComboBox_1.getSelectedItem() == "Committee Conf Name"){

					ValueField_1.setVisible(false);
					SelectionComboBox_1.setModel(new DefaultComboBoxModel(conf));
					SelectionComboBox_1.setSelectedItem(0);
					SelectionComboBox_1.setVisible(true);

					ComparatorComboBox_1.addItem("=");	
					ComparatorComboBox_1.addItem("!=");


				} else if(CriteriaComboBox_1.getSelectedItem() == "Journal Name"){

					ValueField_1.setVisible(false);
					SelectionComboBox_1.setModel(new DefaultComboBoxModel(jour));
					SelectionComboBox_1.setSelectedItem(0);
					SelectionComboBox_1.setVisible(true);

					ComparatorComboBox_1.addItem("=");	
					ComparatorComboBox_1.addItem("!=");

				}
			}
		});

		JLabel ComparatorLabel_1 = new JLabel("Comparator");
		ComparatorLabel_1.setBounds(245, 13, 79, 16);
		panel_1.add(ComparatorLabel_1);

		ComparatorComboBox_1.setBounds(322, 10, 107, 22);
		panel_1.add(ComparatorComboBox_1);

		JLabel ValueLabel_1 = new JLabel("Value");
		ValueLabel_1.setBounds(450, 13, 56, 16);
		panel_1.add(ValueLabel_1);

		ValueField_1.setBounds(492, 10, 116, 22);
		panel_1.add(ValueField_1);
		ValueField_1.setColumns(10);

		SelectionComboBox_1.setBounds(492, 10, 116, 22);
		panel_1.add(SelectionComboBox_1);


		NewRowButton_1 = new JButton("New row");
		NewRowButton_1.setBounds(630, 9, 86, 25);
		panel_1.add(NewRowButton_1);
		NewRowButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JoinLabel_1.setVisible(true);
				JoinComboBox_1.setVisible(true);
				panel_2.setVisible(true);
				q2 = true;

			}
 
		});

		//label for join comparator
		JoinLabel_1.setBounds(728, 12, 86, 19);
		panel_1.add(JoinLabel_1);

		//combo box for join comparator
		JoinComboBox_1.setBounds(818, 10, 68, 22);
		panel_1.add(JoinComboBox_1);

		/****************************************************** Panel -2 ********************************************/		
		panel_2.setLayout(null);
		panel_2.setBounds(42, 175, 924, 41);
		contentPane.add(panel_2);

		JLabel CriteriaLabel_2 = new JLabel("Criteria");
		CriteriaLabel_2.setBounds(12, 13, 56, 16);
		panel_2.add(CriteriaLabel_2);

		final JComboBox CriteriaComboBox_2 = new JComboBox(keys.toArray());
		CriteriaComboBox_2.setBounds(67, 10, 166, 22);
		panel_2.add(CriteriaComboBox_2);

		CriteriaComboBox_2.setSelectedItem(keys.toArray()[0]);
		ComparatorComboBox_2.addItem("=");
		ComparatorComboBox_2.addItem("!=");
		ComparatorComboBox_2.addItem("LIKE");
		ComparatorComboBox_2.addItem("NOT LIKE");

		CriteriaComboBox_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_2.removeAllItems();	
				ValueField_2.setVisible(true);
				SelectionComboBox_2.setVisible(false);


				if(CriteriaComboBox_2.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_2.addItem("LIKE");
					ComparatorComboBox_2.addItem("NOT LIKE");


				} else if(CriteriaComboBox_2.getSelectedItem() == "Author Name"){

					ComparatorComboBox_2.addItem("=");	
					ComparatorComboBox_2.addItem("!=");
					ComparatorComboBox_2.addItem("LIKE");
					ComparatorComboBox_2.addItem("NOT LIKE");

				}else if(CriteriaComboBox_2.getSelectedItem() == "Committee Year" || CriteriaComboBox_2.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_2.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_2.addItem(">");
					ComparatorComboBox_2.addItem("<");
					ComparatorComboBox_2.addItem("=");
					ComparatorComboBox_2.addItem(">=");
					ComparatorComboBox_2.addItem("<=");
					ComparatorComboBox_2.addItem("!=");


				}else if(CriteriaComboBox_2.getSelectedItem() == "Conference Name" || CriteriaComboBox_2.getSelectedItem() == "Committee Conf Name"){

					ValueField_2.setVisible(false);
					SelectionComboBox_2.setModel(new DefaultComboBoxModel(conf));
					SelectionComboBox_2.setSelectedItem(0);
					SelectionComboBox_2.setVisible(true);


					ComparatorComboBox_2.addItem("=");	
					ComparatorComboBox_2.addItem("!=");


				} else if(CriteriaComboBox_2.getSelectedItem() == "Journal Name"){

					ValueField_2.setVisible(false);
					SelectionComboBox_2.setModel(new DefaultComboBoxModel(jour));
					SelectionComboBox_2.setSelectedItem(0);
					SelectionComboBox_2.setVisible(true);

					ComparatorComboBox_2.addItem("=");	
					ComparatorComboBox_2.addItem("!=");

				}
			}
		});

		JLabel ComparatorLabel_2 = new JLabel("Comparator");
		ComparatorLabel_2.setBounds(245, 13, 79, 16);
		panel_2.add(ComparatorLabel_2);

		ComparatorComboBox_2.setBounds(322, 10, 107, 22);
		panel_2.add(ComparatorComboBox_2);


		JLabel ValueLabel_2 = new JLabel("Value");
		ValueLabel_2.setBounds(450, 13, 56, 16);
		panel_2.add(ValueLabel_2);

		ValueField_2.setColumns(10);
		ValueField_2.setBounds(492, 10, 116, 22);
		panel_2.add(ValueField_2);

		SelectionComboBox_2.setBounds(492, 10, 116, 22);
		panel_2.add(SelectionComboBox_2);


		NewRowButton_2 = new JButton("New row");
		NewRowButton_2.setBounds(630, 9, 86, 25);
		panel_2.add(NewRowButton_2);
		NewRowButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JoinLabel_2.setVisible(true);
				JoinComboBox_2.setVisible(true);
				panel_3.setVisible(true);
				q3 = true;

			}
		});

		//label for join comparator
		JoinLabel_2.setBounds(728, 12, 86, 19);
		panel_2.add(JoinLabel_2);


		//combo box for join comparator
		JoinComboBox_2.setBounds(818, 10, 68, 22);
		panel_2.add(JoinComboBox_2);

		/************************************ Panel -3 ******************************************************/

		panel_3.setLayout(null);
		panel_3.setBounds(42, 216, 924, 41);
		contentPane.add(panel_3);

		JLabel CriteriaLabel_3 = new JLabel("Criteria");
		CriteriaLabel_3.setBounds(12, 13, 56, 16);
		panel_3.add(CriteriaLabel_3);

		final JComboBox CriteriaComboBox_3 = new JComboBox(keys.toArray());
		CriteriaComboBox_3.setBounds(67, 10, 166, 22);
		panel_3.add(CriteriaComboBox_3);

		CriteriaComboBox_3.setSelectedItem(keys.toArray()[0]);
		ComparatorComboBox_3.addItem("="); //As initial criteria is AuthorName
		ComparatorComboBox_3.addItem("!=");//it's corresponding comparator should be =, !=, LIKE and NOT LIKE
		ComparatorComboBox_3.addItem("LIKE");
		ComparatorComboBox_3.addItem("NOT LIKE");

		CriteriaComboBox_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_3.removeAllItems();
				ValueField_3.setVisible(true);
				SelectionComboBox_3.setVisible(false);


				if(CriteriaComboBox_3.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_3.addItem("LIKE");
					ComparatorComboBox_3.addItem("NOT LIKE");


				} else if(CriteriaComboBox_3.getSelectedItem() == "Author Name"){

					ComparatorComboBox_3.addItem("=");	
					ComparatorComboBox_3.addItem("!=");
					ComparatorComboBox_3.addItem("LIKE");
					ComparatorComboBox_3.addItem("NOT LIKE");

				}else if(CriteriaComboBox_3.getSelectedItem() == "Committee Year" || CriteriaComboBox_3.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_3.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_3.addItem(">");
					ComparatorComboBox_3.addItem("<");
					ComparatorComboBox_3.addItem("=");
					ComparatorComboBox_3.addItem(">=");
					ComparatorComboBox_3.addItem("<=");
					ComparatorComboBox_3.addItem("!=");

				}else if(CriteriaComboBox_3.getSelectedItem() == "Conference Name" || CriteriaComboBox_3.getSelectedItem() == "Committee Conf Name"){

					ValueField_3.setVisible(false);
					SelectionComboBox_3.setModel(new DefaultComboBoxModel(conf));
					SelectionComboBox_3.setSelectedItem(0);
					SelectionComboBox_3.setVisible(true);

					ComparatorComboBox_3.addItem("=");	
					ComparatorComboBox_3.addItem("!=");


				} else if(CriteriaComboBox_3.getSelectedItem() == "Journal Name"){

					ValueField_3.setVisible(false);
					SelectionComboBox_3.setModel(new DefaultComboBoxModel(jour));
					SelectionComboBox_3.setSelectedItem(0);
					SelectionComboBox_3.setVisible(true);

					ComparatorComboBox_3.addItem("=");	
					ComparatorComboBox_3.addItem("!=");


				}
			}
		});

		JLabel ComparatorLabel_3 = new JLabel("Comparator");
		ComparatorLabel_3.setBounds(245, 13, 79, 16);
		panel_3.add(ComparatorLabel_3);

		ComparatorComboBox_3.setBounds(322, 10, 107, 22);
		panel_3.add(ComparatorComboBox_3);


		JLabel ValueLabel_3 = new JLabel("Value");
		ValueLabel_3.setBounds(450, 13, 56, 16);
		panel_3.add(ValueLabel_3);

		ValueField_3.setColumns(10);
		ValueField_3.setBounds(492, 10, 116, 22);
		panel_3.add(ValueField_3);

		SelectionComboBox_3.setBounds(492, 10, 116, 22);
		panel_3.add(SelectionComboBox_3);


		NewRowButton_3 = new JButton("New row");
		NewRowButton_3.setBounds(630, 9, 86, 25);
		panel_3.add(NewRowButton_3);
		NewRowButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinLabel_3.setVisible(true);
				JoinComboBox_3.setVisible(true);
				panel_4.setVisible(true);
				q4 = true;				
			}
		});

		//label for join comparator
		JoinLabel_3.setBounds(728, 12, 86, 19);
		panel_3.add(JoinLabel_3);

		//combo box for join comparator
		JoinComboBox_3.setBounds(818, 10, 68, 22);
		panel_3.add(JoinComboBox_3);

		/****************************************** Panel-4 ******************************************************************/

		panel_4.setLayout(null);
		panel_4.setBounds(42, 256, 924, 41);
		contentPane.add(panel_4);

		JLabel CriteriaLabel_4 = new JLabel("Criteria");
		CriteriaLabel_4.setBounds(12, 13, 56, 16);
		panel_4.add(CriteriaLabel_4);

		final JComboBox CriteriaComboBox_4 = new JComboBox(keys.toArray());
		CriteriaComboBox_4.setBounds(67, 10, 166, 22);
		panel_4.add(CriteriaComboBox_4);

		CriteriaComboBox_4.setSelectedItem(keys.toArray()[0]);
		ComparatorComboBox_4.addItem("=");
		ComparatorComboBox_4.addItem("!=");
		ComparatorComboBox_4.addItem("LIKE");
		ComparatorComboBox_4.addItem("NOT LIKE");

		CriteriaComboBox_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_4.removeAllItems();
				ValueField_4.setVisible(true);
				SelectionComboBox_4.setVisible(false);

				if(CriteriaComboBox_4.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_4.addItem("LIKE");
					ComparatorComboBox_4.addItem("NOT LIKE");


				} else if(CriteriaComboBox_4.getSelectedItem() == "Author Name"){

					ComparatorComboBox_4.addItem("=");	
					ComparatorComboBox_4.addItem("!=");
					ComparatorComboBox_4.addItem("LIKE");
					ComparatorComboBox_4.addItem("NOT LIKE");

				}else if(CriteriaComboBox_4.getSelectedItem() == "Committee Year" || CriteriaComboBox_4.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_4.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_4.addItem(">");
					ComparatorComboBox_4.addItem("<");
					ComparatorComboBox_4.addItem("=");
					ComparatorComboBox_4.addItem(">=");
					ComparatorComboBox_4.addItem("<=");
					ComparatorComboBox_4.addItem("!=");

				}else if(CriteriaComboBox_4.getSelectedItem() == "Conference Name" || CriteriaComboBox_4.getSelectedItem() == "Committee Conf Name"){

					ValueField_4.setVisible(false);
					SelectionComboBox_4.setModel(new DefaultComboBoxModel(conf));
					SelectionComboBox_4.setSelectedItem(0);
					SelectionComboBox_4.setVisible(true);

					ComparatorComboBox_4.addItem("=");	
					ComparatorComboBox_4.addItem("!=");


				} else if(CriteriaComboBox_4.getSelectedItem() == "Journal Name"){

					ValueField_4.setVisible(false);
					SelectionComboBox_4.setModel(new DefaultComboBoxModel(jour));
					SelectionComboBox_4.setSelectedItem(0);
					SelectionComboBox_4.setVisible(true);

					ComparatorComboBox_4.addItem("=");	
					ComparatorComboBox_4.addItem("!=");

				}

			}
		});


		JLabel ComparatorLabel_4 = new JLabel("Comparator");
		ComparatorLabel_4.setBounds(245, 13, 79, 16);
		panel_4.add(ComparatorLabel_4);

		ComparatorComboBox_4.setBounds(322, 10, 107, 22);
		panel_4.add(ComparatorComboBox_4);

		JLabel ValueLabel_4 = new JLabel("Value");
		ValueLabel_4.setBounds(450, 13, 56, 16);
		panel_4.add(ValueLabel_4);

		ValueField_4.setColumns(10);
		ValueField_4.setBounds(492, 10, 116, 22);
		panel_4.add(ValueField_4);

		SelectionComboBox_4.setBounds(492, 10, 116, 22);
		panel_4.add(SelectionComboBox_4);

		NewRowButton_4 = new JButton("New row");  
		NewRowButton_4.setBounds(630, 9, 86, 25);
		panel_4.add(NewRowButton_4);
		NewRowButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinLabel_4.setVisible(true);
				JoinComboBox_4.setVisible(true);
				panel_5.setVisible(true);
				q5 = true;
			}
		});

		//label for join comparator
		JoinLabel_4.setBounds(728, 12, 86, 19);
		panel_4.add(JoinLabel_4);

		//combo box for join comparator
		JoinComboBox_4.setBounds(818, 10, 68, 22);
		panel_4.add(JoinComboBox_4);

		/*********************************** Panel - 5 **********************************************/	

		panel_5.setBounds(42, 297, 924, 41);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JLabel CriteriaLabel_5 = new JLabel("Criteria");
		CriteriaLabel_5.setBounds(12, 13, 56, 16);
		panel_5.add(CriteriaLabel_5);

		final JComboBox CriteriaComboBox_5 = new JComboBox(keys.toArray());
		CriteriaComboBox_5.setBounds(67, 10, 166, 22);
		panel_5.add(CriteriaComboBox_5);

		CriteriaComboBox_5.setSelectedItem(keys.toArray()[0]);
		ComparatorComboBox_5.addItem("=");
		ComparatorComboBox_5.addItem("!=");
		ComparatorComboBox_5.addItem("LIKE");
		ComparatorComboBox_5.addItem("NOT LIKE");

		CriteriaComboBox_5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ComparatorComboBox_5.removeAllItems();	
				ValueField_5.setVisible(true);
				SelectionComboBox_5.setVisible(false);

				if(CriteriaComboBox_5.getSelectedItem() == "Keyword in Title"){

					ComparatorComboBox_5.addItem("LIKE");
					ComparatorComboBox_5.addItem("NOT LIKE");


				} else if(CriteriaComboBox_5.getSelectedItem() == "Author Name"){

					ComparatorComboBox_5.addItem("=");	
					ComparatorComboBox_5.addItem("!=");
					ComparatorComboBox_5.addItem("LIKE");
					ComparatorComboBox_5.addItem("NOT LIKE");

				}else if(CriteriaComboBox_5.getSelectedItem() == "Committee Year" || CriteriaComboBox_5.getSelectedItem() == "Count Of Papers" || CriteriaComboBox_5.getSelectedItem() == "Paper Published Year" ){

					ComparatorComboBox_5.addItem(">");
					ComparatorComboBox_5.addItem("<");
					ComparatorComboBox_5.addItem("=");
					ComparatorComboBox_5.addItem(">=");
					ComparatorComboBox_5.addItem("<=");
					ComparatorComboBox_5.addItem("!=");

				}else if(CriteriaComboBox_5.getSelectedItem() == "Conference Name" || CriteriaComboBox_5.getSelectedItem() == "Committee Conf Name"){

					ValueField_5.setVisible(false);
					SelectionComboBox_5.setModel(new DefaultComboBoxModel(conf));
					SelectionComboBox_5.setSelectedItem(0);
					SelectionComboBox_5.setVisible(true);

					ComparatorComboBox_5.addItem("=");	
					ComparatorComboBox_5.addItem("!=");


				} else if(CriteriaComboBox_5.getSelectedItem() == "Journal Name"){

					ValueField_5.setVisible(false);
					SelectionComboBox_5.setModel(new DefaultComboBoxModel(jour));
					SelectionComboBox_5.setSelectedItem(0);
					SelectionComboBox_5.setVisible(true);

					ComparatorComboBox_5.addItem("=");	
					ComparatorComboBox_5.addItem("!=");


				}	
			}
		});


		JLabel ComparatorLabel_5 = new JLabel("Comparator");
		ComparatorLabel_5.setBounds(245, 13, 79, 16);
		panel_5.add(ComparatorLabel_5);

		ComparatorComboBox_5.setBounds(322, 10, 107, 22);
		panel_5.add(ComparatorComboBox_5);


		JLabel ValueLabel_5 = new JLabel("Value");
		ValueLabel_5.setBounds(450, 13, 56, 16);
		panel_5.add(ValueLabel_5);

		ValueField_5.setColumns(10);
		ValueField_5.setBounds(492, 10, 116, 22);
		panel_5.add(ValueField_5);

		SelectionComboBox_5.setBounds(492, 10, 116, 22);
		panel_5.add(SelectionComboBox_5);


		/*****************************************Submit button****************************************************/

		btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBounds(394, 395, 97, 25);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean error = false;
				finalList.clear();

				if(q1 == true){ 

					sp1.setSearchFilter(Criteria.get(CriteriaComboBox_1.getSelectedItem()));
					sp1.setSearchComparator((String) ComparatorComboBox_1.getSelectedItem()); 
					if(CriteriaComboBox_1.getSelectedItem() == "Conference Name" || CriteriaComboBox_1.getSelectedItem() == "Committee Conf Name" || CriteriaComboBox_1.getSelectedItem() == "Journal Name"){

						sp1.setSearchValue((String) SelectionComboBox_1.getSelectedItem());
					}else{

						sp1.setSearchValue(ValueField_1.getText().trim());
						System.out.println(sp1.getSearchValue());
						if(sp1.getSearchValue().contains("'")){
							String s = sp1.getSearchValue().replaceAll("'", "''");
							sp1.setSearchValue(s);
							System.out.println("inside"+sp1.getSearchValue());
						}
					}					
					sp1.setJoinFilter((String) JoinComboBox_1.getSelectedItem()); 

					if(sp1.getSearchValue().equals("")){
						error = true;
						log.messageShow("Please enter a value for search in first row"); 
					}

					if(CriteriaComboBox_1.getSelectedItem() == "Paper Published Year" || CriteriaComboBox_1.getSelectedItem() == "Committee Year"){
						if(!(StringUtils.isNumeric(sp1.getSearchValue()) && sp1.getSearchValue().length() == 4)){
							error = true;
							log.messageShow("Please enter a valid year in first row"); 
						}
					}

					if(CriteriaComboBox_1.getSelectedItem() == "Count Of Papers"){
						if(!StringUtils.isNumeric(sp1.getSearchValue())){
							error = true;
							log.messageShow("Please enter a valid paper count in first row"); 
						}
					}

					if(!error){
						finalList.add(sp1);
					}
				}

				if(q2 == true){

					sp2.setSearchFilter(Criteria.get(CriteriaComboBox_2.getSelectedItem()));
					sp2.setSearchComparator((String) ComparatorComboBox_2.getSelectedItem());
					if(CriteriaComboBox_2.getSelectedItem() == "Conference Name" || CriteriaComboBox_2.getSelectedItem() == "Committee Conf Name" || CriteriaComboBox_2.getSelectedItem() == "Journal Name"){

						sp2.setSearchValue((String) SelectionComboBox_2.getSelectedItem());
					}else{

						sp2.setSearchValue(ValueField_2.getText().trim());
						if(sp2.getSearchValue().contains("'")){
							sp2.setSearchValue(sp2.getSearchValue().replaceAll("'", "''"));
							System.out.println(sp2.getSearchValue());
						}

					}
					sp2.setJoinFilter((String) JoinComboBox_2.getSelectedItem()); 

					if(sp2.getSearchValue().equals("")){
						error = true;
						log.messageShow("Please enter a value for search in second row"); 
					}

					if(CriteriaComboBox_2.getSelectedItem() == "Paper Published Year" || CriteriaComboBox_2.getSelectedItem() == "Committee Year"){
						if(!(StringUtils.isNumeric(sp2.getSearchValue()) && sp2.getSearchValue().length() == 4)){
							error = true;
							log.messageShow("Please enter a valid year in second row"); 
						}
					}

					if(CriteriaComboBox_2.getSelectedItem() == "Count Of Papers"){
						if(!StringUtils.isNumeric(sp2.getSearchValue())){
							error = true;
							log.messageShow("Please enter a valid paper count in second row"); 
						}
					}

					if(!error){
						finalList.add(sp2);
					}

				}

				if(q3 == true){

					sp3.setSearchFilter(Criteria.get(CriteriaComboBox_3.getSelectedItem()));
					sp3.setSearchComparator((String) ComparatorComboBox_3.getSelectedItem());
					if(CriteriaComboBox_3.getSelectedItem() == "Conference Name" || CriteriaComboBox_3.getSelectedItem() == "Committee Conf Name" || CriteriaComboBox_3.getSelectedItem() == "Journal Name"){

						sp3.setSearchValue((String) SelectionComboBox_3.getSelectedItem());
					}else{

						sp3.setSearchValue(ValueField_3.getText().trim());
						if(sp3.getSearchValue().contains("'")){
							sp3.setSearchValue(sp3.getSearchValue().replaceAll("'", "''"));
							System.out.println(sp3.getSearchValue());
						}

					}
					sp3.setJoinFilter((String) JoinComboBox_3.getSelectedItem()); 

					if(sp3.getSearchValue().equals("")){
						error = true;
						log.messageShow("Please enter a value for search in third row"); 
					}

					if(CriteriaComboBox_3.getSelectedItem() == "Paper Published Year" || CriteriaComboBox_3.getSelectedItem() == "Committee Year"){
						if(!(StringUtils.isNumeric(sp3.getSearchValue()) && sp3.getSearchValue().length() == 4)){
							error = true;
							log.messageShow("Please enter a valid year in third row"); 
						}
					}

					if(CriteriaComboBox_3.getSelectedItem() == "Count Of Papers"){
						if(!StringUtils.isNumeric(sp3.getSearchValue())){
							error = true;
							log.messageShow("Please enter a valid paper count in third row"); 
						}
					}

					if(!error){
						finalList.add(sp3);
					}

				}

				if(q4 == true){
					sp4.setSearchFilter(Criteria.get(CriteriaComboBox_4.getSelectedItem()));
					sp4.setSearchComparator((String) ComparatorComboBox_4.getSelectedItem());					
					if(CriteriaComboBox_4.getSelectedItem() == "Conference Name" || CriteriaComboBox_4.getSelectedItem() == "Committee Conf Name" || CriteriaComboBox_4.getSelectedItem() == "Journal Name"){

						sp4.setSearchValue((String) SelectionComboBox_4.getSelectedItem());
					}else{

						sp4.setSearchValue(ValueField_4.getText().trim());
						if(sp4.getSearchValue().contains("'")){
							sp4.setSearchValue(sp4.getSearchValue().replaceAll("'", "''"));
							System.out.println(sp4.getSearchValue());
						}

					}
					sp4.setJoinFilter((String) JoinComboBox_4.getSelectedItem()); 

					if(sp4.getSearchValue().equals("")){
						error = true;
						log.messageShow("Please enter a value for search in fourth row"); 
					}

					if(CriteriaComboBox_4.getSelectedItem() == "Paper Published Year" || CriteriaComboBox_4.getSelectedItem() == "Committee Year"){
						if(!(StringUtils.isNumeric(sp4.getSearchValue()) && sp4.getSearchValue().length() == 4)){
							error = true;
							log.messageShow("Please enter a valid year in fourth row"); 
						}
					}

					if(CriteriaComboBox_4.getSelectedItem() == "Count Of Papers"){
						if(!StringUtils.isNumeric(sp4.getSearchValue())){
							error = true;
							log.messageShow("Please enter a valid paper count in fourth row"); 
						}
					}
					if(!error){
						finalList.add(sp4);
					}

				}
 
				if(q5 == true){
					sp5.setSearchFilter(Criteria.get(CriteriaComboBox_5.getSelectedItem()));
					sp5.setSearchComparator ((String) ComparatorComboBox_5.getSelectedItem());
					if(CriteriaComboBox_5.getSelectedItem() == "Conference Name" || CriteriaComboBox_5.getSelectedItem() == "Committee Conf Name" || CriteriaComboBox_5.getSelectedItem() == "Journal Name"){

						sp5.setSearchValue ((String) SelectionComboBox_5.getSelectedItem());
					}else{

						sp5.setSearchValue(ValueField_5.getText().trim());
						if(sp5.getSearchValue().contains("'")){
							sp5.setSearchValue(sp5.getSearchValue().replaceAll("'", "''"));
							System.out.println(sp5.getSearchValue());
						}

					}

					if(sp5.getSearchValue().equals("")) {
						error = true;
						log.messageShow("Please enter a value for search in fifth row"); 
					}

					if(CriteriaComboBox_5.getSelectedItem() == "Paper Published Year" || CriteriaComboBox_5.getSelectedItem() == "Committee Year"){
						if(!(StringUtils.isNumeric(sp5.getSearchValue()) && sp5.getSearchValue().length() == 4)){
							error = true;
							log.messageShow("Please enter a valid year in fifth row"); 
						}
					}

					if(CriteriaComboBox_5.getSelectedItem() == "Count Of Papers"){
						if(!StringUtils.isNumeric(sp5.getSearchValue())){
							error = true;
							log.messageShow("Please enter a valid paper count in fifth row"); 
						}
					}	

					if(!error){
						finalList.add(sp5);
					}
				}

				if(finalList.size() != 0 && error == false) {

					// Display function will return a result set containing the search query results
					// send these to displayUI to render the information there.
					ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
					List<String> finalAuthors = null;
					Boolean flag = true;
					try {
						finalAuthors = searchDisplay.search(finalList);
						if (finalAuthors.size() == 0) {
							LoginUI log = new LoginUI();
							log.messageShow("Please widen your search criteria");
							flag = false;
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
					DisplayUI display;
					if (flag == true) {
						dispose();
						display = new DisplayUI(finalAuthors);
						display.setVisible(true);
						display.setSize(1000, 600);
						display.setLocationRelativeTo(null);
					}
				}				   
			}
		});

		btnLogout = new JButton("LogOut");
		btnLogout.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogout.setBounds(790, 13, 117, 34);
		contentPane.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();
				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);
				log.setSize(1000,600);
				log.setLocationRelativeTo(null);

			}
		});
	}


}
