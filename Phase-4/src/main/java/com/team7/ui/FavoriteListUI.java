package com.team7.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.team7.parsing.ImplementSchemaDB;

/**
 * The Class FavoriteListUI.
 * Class creates User interface for favorites list
 */ 
public class FavoriteListUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The btn logout. */
	public JButton btnLogout;
	
	/** The btn search. */
	public JButton btnSearch;
	
	/** The table. */
	public JTable table;
	
	/** The btn remove. */
	public JButton btnRemove;
	
	/** The btn candidates list. */
	public JButton btnCandidatesList;

	/**
	 * Instantiates a new favorite list UI.
	 *
	 * @param favList the fav list
	 */
	public FavoriteListUI(Set<String> favList) {
		
		setVisible(true);
		setTitle("Favorite List");
		setSize(UIConstants.width,UIConstants.height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		JPanel panel = new JPanel();
		panel.setBounds(0, 6, 1382, 101);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSavedAuthors = new JLabel("My Favorite List");
		lblSavedAuthors.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSavedAuthors.setBounds(516, 61, 197, 34);
		panel.add(lblSavedAuthors);

		//button for searchUI screen
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSearch.setBounds(950, 13, 117, 34);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonEditor.savedAuthors.clear();
				dispose();
				SearchUI search = new SearchUI();
				search.setVisible(true);
				search.setSize(UIConstants.width,UIConstants.height);
				search.setLocationRelativeTo(null);				
			} 
		});

		//button to logout of application
		btnLogout = new JButton("LogOut");
		btnLogout.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnLogout.setBounds(1071, 13, 117, 34);
		panel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ButtonEditor.savedAuthors.clear();
				// make the currentUser null and redirect to login page
				ImplementLogin login = new ImplementLogin();
				login.logout();
				dispose();
				LoginUI log = new LoginUI();
				log.setVisible(true);
				log.setSize(UIConstants.width,UIConstants.height);
				log.setLocationRelativeTo(null);
			}
		});
 
		//button to view final Candidates list
		btnCandidatesList = new JButton("Candidates List");
		btnCandidatesList.setVisible(false);
		if(UIConstants.currentUserRole.equals("Program Chair")){
			btnCandidatesList.setVisible(true);
		}
		btnCandidatesList.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnCandidatesList.setBounds(18, 13, 179, 34);
		panel.add(btnCandidatesList);
		btnCandidatesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CandidateListUI cl = new CandidateListUI();
				  dispose();
				  cl.setVisible(true);
				  cl.setSize(UIConstants.width,UIConstants.height);
				  cl.setLocationRelativeTo(null);				
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(40, 134, 1154, 325);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		try {

			table = new JTable(buildTableModel(favList));
			JTableHeader header = table.getTableHeader();
			header.setDefaultRenderer(new HeaderRenderer(table));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

			// Setting the values center aligned
			for (int i=0; i< table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			// Not allowing the columns to be dragged
			table.getTableHeader().setReorderingAllowed(false);

			table.setPreferredScrollableViewportSize(new Dimension(400, 300));

			// Disabling multiple selection by the user
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scroll = new JScrollPane(table);
			setVisible(true);

			panel_1.add(scroll);
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			btnRemove = new JButton("Remove");
			btnRemove.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int index = table.getSelectedRow();
					if (index == -1) {
						// when no authors are selected and remove is pressed.
						LoginUI log = new LoginUI();
						log.messageShow("Please select an author to remove");
					}
					else{
						String author = (String) model.getValueAt(index, 0);
						ImplementSchemaDB db =  new ImplementSchemaDB();
						
						try {
							db.updateFavList(UIConstants.currentUser, author);		
							model.removeRow(index);
							
						} catch (IOException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			btnRemove.setBounds(546, 487, 97, 30);
			contentPane.add(btnRemove);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Builds the table model.
	 * rendering to the favorites list
	 *
	 * @param favList the fav list
	 * @return the table model
	 * @throws SQLException the SQL exception
	 */ 
	public TableModel buildTableModel(Set<String> favList) throws SQLException {

		Vector<String> columnNames = new Vector<String>();
			
		columnNames.add("Author Name");			
		

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(String author : favList) {
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
				//This causes all cells to be not editable
				return false; 
			}

		};

		return model;

	}

}
