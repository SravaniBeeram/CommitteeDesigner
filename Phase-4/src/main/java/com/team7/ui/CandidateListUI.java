package com.team7.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.team7.parsing.ImplementSchemaDB;
import com.team7.queryEngine.ImplementSearchDisplay;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Class creating the user interface for the candidates 
/**
 * The Class CandidateListUI.
 */
// selection by the Program chair
public class CandidateListUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** The table 1. */
	public JTable table1; 
	
	/** The table 2. */
	public JTable table2;
	
	/** The panel 3. */
	JPanel panel_3 = new JPanel();
	
	/** The btn searchui. */
	public JButton btnSearchui;
	
	/** The btn logout. */
	public JButton btnLogout;
	
	/** The btn my favorite list. */
	public JButton btnMyFavoriteList;
	
	/** The button select. */
	public JButton buttonSelect;
	
	/** The btn remove. */
	public JButton btnRemove;
	
	/** The btn send email. */
	public JButton btnSendEmail;
    
    /** The fav list. */
    public Set<String> favList;
    
    /** The model. */
    public DefaultTableModel model;
	
	/** The model 2. */
	public DefaultTableModel model2 ;


	/**
	 * Instantiates a new candidate list UI.
	 */
	public CandidateListUI(){

		setTitle("CANDIDATE LIST OF THE PROGRAM CHAIR");
		setResizable(false);
		setSize(UIConstants.width,UIConstants.height);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		
		JPanel panel = new JPanel();
		panel.setBounds(6, 18, 1188, 113);
		contentPane.add(panel);
		panel.setLayout(null);


		JLabel lblCommittee = new JLabel("Committee List");
		lblCommittee.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblCommittee.setBounds(521, 18, 134, 22);
		panel.add(lblCommittee);


		JLabel lblSelectedCandidates = new JLabel("Selected Candidates");
		lblSelectedCandidates.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblSelectedCandidates.setBounds(224, 91, 176, 16);
		panel.add(lblSelectedCandidates);


		JLabel lblFinalCandidates = new JLabel("Final Candidates");
		lblFinalCandidates.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblFinalCandidates.setBounds(818, 91, 155, 16);
		panel.add(lblFinalCandidates);
		
		//Button to Logout of application
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogout.setBounds(1065, 6, 117, 34);
		panel.add(btnLogout);		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();
				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);
				setSize(UIConstants.width,UIConstants.height);
				log.setLocationRelativeTo(null);
			}
		});

		
		//Button for SearchUI screen
		btnSearchui = new JButton("Search");
		btnSearchui.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearchui.setBounds(932, 6, 117, 34);
		panel.add(btnSearchui);
		btnSearchui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SearchUI search = new SearchUI();
				search.setVisible(true);
				setSize(UIConstants.width,UIConstants.height);			
				search.setLocationRelativeTo(null);				
			}
		});
		
		//Button for Favorite list screen
		btnMyFavoriteList = new JButton("My Favorite List");
		btnMyFavoriteList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnMyFavoriteList.setBounds(17, 6, 176, 34);
		panel.add(btnMyFavoriteList);
		btnMyFavoriteList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImplementSearchDisplay search = new ImplementSearchDisplay();
				Set<String> favList;
				
				try {
					favList = search.favAuthors("userName", UIConstants.currentUser);
					FavoriteListUI fl = new FavoriteListUI(favList);
					dispose();
					fl.setVisible(true);
					fl.setSize(UIConstants.width, UIConstants.height);
					fl.setLocationRelativeTo(null);
					
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

 

		// favorites list table
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(16, 143, 578, 465);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ImplementSearchDisplay search = new ImplementSearchDisplay();
		

		try {	
			favList = search.favAuthors("confName", UIConstants.currentUserConf);

			table1 = new JTable(buildTableModel(favList));
			JTableHeader header1 = table1.getTableHeader();
			header1.setDefaultRenderer(new HeaderRenderer(table1));

			DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
			centerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);

			// Setting the values center aligned
			for (int i=0; i< table1.getColumnModel().getColumnCount(); i++) {
				table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
			}

			// disabled draggable headers
			table1.getTableHeader().setReorderingAllowed(false);
			
			table1.setPreferredScrollableViewportSize(new Dimension(550, 430));
			
			// disable multiple selection by users
			table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scroll1 = new JScrollPane(table1);
			setVisible(true);
			panel_1.add(scroll1);

			model = (DefaultTableModel) table1.getModel();

			buttonSelect = new JButton("Select");
			buttonSelect.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			buttonSelect.setBounds(216, 5, 97, 30);
			panel_3.add(buttonSelect);
			buttonSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					model2 = (DefaultTableModel) table2.getModel();
					int index = table1.getSelectedRow();
					if (index == -1) {
						LoginUI log = new LoginUI();
						log.messageShow("Please select an author");
					}
					else{ 
						String author = (String) model.getValueAt(index, 0);
						ImplementSchemaDB db =  new ImplementSchemaDB();
						try {
							db.insertIntoCandidateList(author);
								
							Object rows[] = new Object[1];
							rows[0] = model.getValueAt(index, 0); 
							model2.addRow(rows);
							
						} catch (IOException | SQLException e1) {
							LoginUI log = new LoginUI();
							log.messageShow("Author already included in the list");
						}
					}
				}
			});
		}catch (SQLException | IOException e2 ) {


		}

		// final committee list table
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(606, 143, 578, 465);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Set<String> candidateDetails;

		try {
			candidateDetails = search.listForCommittee(UIConstants.currentUserConf);

			table2 = new JTable(buildTableModel(candidateDetails));
			JTableHeader header2 = table2.getTableHeader();
			header2.setDefaultRenderer(new HeaderRenderer(table2));

			DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
			centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);

			// Setting the values center aligned
			for (int i=0; i< table2.getColumnModel().getColumnCount(); i++) {
				table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
			}

			// disabling draggable headers
			table2.getTableHeader().setReorderingAllowed(false);
			
			table2.setPreferredScrollableViewportSize(new Dimension(550, 430));
			
			// disabling multiple selection of rows by user
			table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scroll2 = new JScrollPane(table2);
			setVisible(true);
			panel_2.add(scroll2);

			model2 = (DefaultTableModel) table2.getModel();


			panel_3.setBounds(6, 612, 1188, 85);
			contentPane.add(panel_3);
			panel_3.setLayout(null);

            //Button to send email
			btnSendEmail = new JButton("Send Email");
			btnSendEmail.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnSendEmail.setBounds(539, 50, 149, 29);
			panel_3.add(btnSendEmail);
			Set<String> sendMail = new HashSet<String>();

			btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int count = 0; count < model2.getRowCount(); count++){
					  sendMail.add(model2.getValueAt(count,0).toString());
					}

				Boolean flag = true;

				if (sendMail.size() == 0) {
					LoginUI log = new LoginUI();
					log.messageShow("Please select some authors for your committee");
					flag = false;
				} 
				
				ImplementSearchDisplay searchDisplay = new ImplementSearchDisplay();
				if (flag == true) {
					try {
						String res = searchDisplay.sendEmail(sendMail, UIConstants.currentUser);
                        if(res == "success"){
        					LoginUI log = new LoginUI();
        					log.messageShow("Email sent successfully");
                        }
					}  catch (IOException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						
					}
				}
			}
		});



            //Button to remove an author from candidate list
			btnRemove = new JButton("Remove");
			btnRemove.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnRemove.setBounds(855, 6, 102, 29);
			panel_3.add(btnRemove);
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int index = table2.getSelectedRow();
					if (index == -1) {
						LoginUI log = new LoginUI();
						log.messageShow("Please select an author to remove");
					}
					else{
						String author = (String) model2.getValueAt(index,0);
						ImplementSchemaDB db =  new ImplementSchemaDB();
						try {
							// deleting the selected author from the Candidate_list of the current user.
							db.updateCandidateList(author, UIConstants.currentUserConf);		
							model2.removeRow(index); 
						} catch (IOException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});

		}catch (SQLException | IOException e2) {

		}

	}


	/**
	 * Builds the table model.
	 *
	 * @param favList the fav list
	 * @return the table model
	 * @throws SQLException the SQL exception
	 */
	public TableModel buildTableModel(Set<String> favList) throws SQLException {

		Vector<String> columnNames = new Vector<String>();
			
		columnNames.add("Author Name");			
		

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for (String author : favList) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= 1; columnIndex++) {
				vector.add(author);     
			}
			data.add(vector);
		}


		TableModel model = new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				//This causes all cells of the table to not be editable
				return false; 
			}

		};

		return model;

	}
}