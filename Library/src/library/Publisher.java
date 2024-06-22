package library;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Publisher extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Phone;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Publisher frame = new Publisher();
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
	public Publisher() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 20, 1050, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lbllibrary = new JLabel("PUBLISHER");
        lbllibrary.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbllibrary.setBounds(275, 27, 650, 50);
        contentPane.add(lbllibrary);
        
        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblName.setBounds(55, 134, 163, 43);
        contentPane.add(lblName);
        
        Name = new JTextField();
        Name.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Name.setBounds(257, 138, 250, 30);
        contentPane.add(Name);
        Name.setColumns(10);
        

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblAddress.setBounds(55, 222, 163, 43);
        contentPane.add(lblAddress);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Tahoma", Font.BOLD, 20));
        textArea.setBounds(257, 214, 250, 83);
        contentPane.add(textArea);
        
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPhone.setBounds(55, 338, 163, 43);
        contentPane.add(lblPhone);
        
        Phone = new JTextField();
        Phone.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Phone.setBounds(257, 342, 250, 30);
        contentPane.add(Phone);
        Phone.setColumns(10);
        
        button1 = new JButton("ADD");
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	

        		String name = Name.getText();
        		String address = textArea.getText();
        		String phone = Phone.getText();
        		
        		try {
					pst= con.prepareStatement("insert into publisher (name,address,phone) values(?,?,?)");
					pst.setString(1, name);
					pst.setString(2, address);
					pst.setString(3, phone);
					
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Publisher created");
						Name.setText("");
						textArea.setText("");
						Phone.setText("");
						
						Name.requestFocus();
						
						Publisher_Load();
						
						
						
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
        button1.setBounds(87, 420, 120, 40);
        contentPane.add(button1);
        
        
        button2 = new JButton("UPDATE");
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        	
        		DefaultTableModel d1 = (DefaultTableModel) table.getModel();
                int selectIndex = table.getSelectedRow();

                // Get the selected row's ID
                int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());

                // Get updated values from the text fields
                String name = Name.getText();
                String address = textArea.getText();
                String phone = Phone.getText();

                try {
                    // Prepare the SQL update statement
                    pst = con.prepareStatement("update publisher set name=?, address=?, phone=? where id=?");
                    pst.setString(1, name);
                    pst.setString(2, address);
                    pst.setString(3, phone);
                    pst.setInt(4, id);

                    // Execute the update statement
                    int k = pst.executeUpdate();

                    if (k == 1) {
                        // Update successful
                        JOptionPane.showMessageDialog(button1, "Publisher updated");
                        Name.setText("");
                        textArea.setText("");
                        Phone.setText("");

                        Name.requestFocus();
                        button1.setEnabled(true);
                        // Reload the table with updated data
                        Publisher_Load();
                    } else {
                        // Update failed
                        JOptionPane.showMessageDialog(button1, "Error");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
        		
        	}
        });
        button2.setForeground(new Color(255, 255, 255));
        button2.setBackground(new Color(51, 102, 153));
        button2.setFont(new Font("Tahoma", Font.BOLD, 18));
        button2.setBounds(257, 420, 120, 40);
        contentPane.add(button2);
        
        button3 = new JButton("DELETE");
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        	
        		DefaultTableModel d1 = (DefaultTableModel)table.getModel();
        		int selectIndex = table.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        		
        		
        		try {
					pst= con.prepareStatement("delete from publisher where id=?");
					pst.setInt(1, id);
					
					
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Publisher deleted");
						Name.setText("");
						textArea.setText("");
						Phone.setText("");
						
						Name.requestFocus();
						Publisher_Load();
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
        button3.setBounds(87, 493, 120, 40);
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
        button4.setBounds(257, 493, 120, 40);
        contentPane.add(button4);
        
        scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		

        		DefaultTableModel d1 = (DefaultTableModel)table.getModel();
        		int selectIndex = table.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        		
        		
        		Name.setText(d1.getValueAt(selectIndex, 1).toString());
        		textArea.setText(d1.getValueAt(selectIndex, 2).toString());
        		Phone.setText(d1.getValueAt(selectIndex, 2).toString());
        		
        		
        		button1.setEnabled(false);
    		
        		
        		
        		
        		
        		
        		
        		
        	}
        });
        scrollPane.setBounds(555, 80, 430, 453);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Publisher Name", "Address", "Phone"
        	}
        
        	
        	));
        table.getColumnModel().getColumn(1).setPreferredWidth(82);
        
        Connect();
        Publisher_Load();
        
        
		
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
	
	public void Publisher_Load()
	{
		int c;
		
		try {
			pst = con.prepareStatement("select * from publisher");
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
		    		v2.add(rs.getString("name"));
		    		v2.add(rs.getString("address"));
		    		v2.add(rs.getString("phone"));
		    		
		    	}
		    	d.addRow(v2);
		    	
		    	
		    	
		    	
		    	
		    }
			

			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
