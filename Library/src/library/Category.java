package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Category extends JFrame {

	private JPanel contentPane;
	private JTextField CName;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JTable table_1;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Category frame = new Category();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Category() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 20, 1050, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JLabel lbllibrary = new JLabel("CATEGORY");
        lbllibrary.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbllibrary.setBounds(275, 27, 272, 50);
        contentPane.add(lbllibrary);
        
        
        JLabel lblCName = new JLabel("Category Name");
        lblCName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCName.setBounds(55, 156, 163, 43);
        contentPane.add(lblCName);
        
        CName = new JTextField();
        CName.setFont(new Font("Tahoma", Font.PLAIN, 25));
        CName.setBounds(257, 160, 250, 30);
        contentPane.add(CName);
        CName.setColumns(10);
        
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblStatus.setBounds(55, 240, 163, 43);
        contentPane.add(lblStatus);
   
		JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Active", "DeActive"}));
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
        comboBox.setBounds(257, 240, 250, 43);
        contentPane.add(comboBox);
        
        //buttons
        
        button1 = new JButton("ADD");
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String category= CName.getText();
        		String status= comboBox.getSelectedItem().toString();
        		
        		try {
					pst= con.prepareStatement("insert into Category (catname,status) values(?,?)");
					pst.setString(1, category);
					pst.setString(2, status);
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Category created");
						CName.setText("");
						comboBox.setSelectedIndex(1);
						CName.requestFocus();
						Category_Load();
						
						
					}
					else {
						
						JOptionPane.showMessageDialog(button1, "Error");
						
						
					}	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        		
        	}
        });
        
        
        
        button1.setForeground(new Color(255, 255, 255));
        button1.setBackground(new Color(51, 102, 153));
        button1.setFont(new Font("Tahoma", Font.BOLD, 18));
        button1.setBounds(74, 359, 120, 40);
        contentPane.add(button1);
        
        button2 = new JButton("UPDATE");
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 DefaultTableModel d1 = (DefaultTableModel) table.getModel();
        	        int selectedIndex = table.getSelectedRow();

        	        if (selectedIndex != -1) {
        	            int id = Integer.parseInt(d1.getValueAt(selectedIndex, 0).toString());
        	            String category = CName.getText();
        	            String status = comboBox.getSelectedItem().toString();

        	            try {
        	                pst = con.prepareStatement("UPDATE Category SET catname=?, status=? WHERE id=?");
        	                pst.setString(1, category);
        	                pst.setString(2, status);
        	                pst.setInt(3, id);

        	                int rowsAffected = pst.executeUpdate();

        	                if (rowsAffected > 0) {
        	                    JOptionPane.showMessageDialog(button2, "Category Updated");
        	                    CName.setText("");
        	                    comboBox.setSelectedIndex(1);
        	                    CName.requestFocus();
        	                    Category_Load();
        	                    button1.setEnabled(true);
        	                } else {
        	                    JOptionPane.showMessageDialog(button2, "Error updating category");
        	                }
        	            } catch (SQLException ex) {
        	                ex.printStackTrace();
        	            }
        	        } else {
        	            JOptionPane.showMessageDialog(button2, "Please select a category to update");
        	        }
        	    }
        		
        		
        		
        	
        });
        
        
        
        button2.setForeground(new Color(255, 255, 255));
        button2.setBackground(new Color(51, 102, 153));
        button2.setFont(new Font("Tahoma", Font.BOLD, 18));
        button2.setBounds(220, 359, 120, 40);
        contentPane.add(button2);
        
        button3 = new JButton("DELETE");
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		
        		DefaultTableModel d1 = (DefaultTableModel)table.getModel();
        		int selectIndex = table.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        	
        		try {
					pst= con.prepareStatement("delete from Category where id=?");
				
					pst.setInt(1, id);
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Category Deleted");
						CName.setText("");
						comboBox.setSelectedIndex(1);
						CName.requestFocus();
						Category_Load();
						button1.setEnabled(true);
						
						
					}
					else {
						
						JOptionPane.showMessageDialog(button1, "Error");
					
					}	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        		
        		
        		
        		
        		
        		
        		
        	}
        });
        button3.setForeground(new Color(255, 255, 255));
        button3.setBackground(new Color(51, 102, 153));
        button3.setFont(new Font("Tahoma", Font.BOLD, 18));
        button3.setBounds(70, 422, 124, 40);
        contentPane.add(button3);
        
       
        
        button4 = new JButton("CANCEL");
        button4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	
        		dispose(); // Close the current JFrame
                // Open the main JFrame (assuming your main frame class is named MainFrame)
                 Main mainFrame = new Main();
                mainFrame.setVisible(true);

        		
        	}
        });
        button4.setForeground(new Color(255, 255, 255));
        button4.setBackground(new Color(51, 102, 153));
        button4.setFont(new Font("Tahoma", Font.BOLD, 18));
        button4.setBounds(220, 422, 120, 40);
        contentPane.add(button4);
        
        scrollPane = new JScrollPane();
        
        scrollPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		DefaultTableModel d1 = (DefaultTableModel)table.getModel();
        		int selectIndex = table.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        		CName.setText(d1.getValueAt(selectIndex, 1).toString());
        		comboBox.setSelectedItem(d1.getValueAt(selectIndex, 2).toString());
        		
        		button1.setEnabled(false);
        	}
        });
        scrollPane.setBounds(537, 108, 453, 407);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Category Name", "Status"
        	}
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(82);
        
        Connect();
        Category_Load();
        
		
		
        
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	
	
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SLibrary","root","");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void Category_Load()
	{
		int c;
		
		try {
			pst = con.prepareStatement("select * from Category");
			rs = pst.executeQuery();
			
			ResultSetMetaData rsd = rs.getMetaData();	
			c= rsd.getColumnCount();
			
		    DefaultTableModel d = (DefaultTableModel)table.getModel();
		    
		    while(rs.next())
		    {
		    	
		    	Vector v2= new Vector();
		    	
		    	for(int i=1; i<=c; i++)
		    		
		    	{
		    		v2.add(rs.getString("id"));
		    		v2.add(rs.getString("catname"));
		    		v2.add(rs.getString("status"));
		    		
		    	}
		    	d.addRow(v2);
		    	
		    	
		    	
		    	
		    	
		    }
			

			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
