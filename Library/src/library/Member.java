package library;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JScrollPane;

public class Member extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Phone;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JTable memberTable1;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member frame = new Member();
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
	public Member() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 20, 1050, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lbllibrary = new JLabel("MEMBER");
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
					pst= con.prepareStatement("insert into member (name,address,phone) values(?,?,?)");
					pst.setString(1, name);
					pst.setString(2, address);
					pst.setString(3, phone);
					
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Member created");
						Name.setText("");
						textArea.setText("");
						Phone.setText("");
						
						Name.requestFocus();
						
						Member_Load();
						
						
						
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
        	
        	

        		DefaultTableModel d1 = (DefaultTableModel)memberTable1.getModel();
        		int selectIndex = memberTable1.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        		
        		String name = Name.getText();
        		String address = textArea.getText();
        		String phone = Phone.getText();
        		
        		try {
					pst= con.prepareStatement("update member set name =?, address=? , phone=? where id=?");
					pst.setString(1, name);
					pst.setString(2, address);
					pst.setString(3, phone);
					pst.setInt(4, id);
					
					
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Member updated");
						Name.setText("");
						textArea.setText("");
						Phone.setText("");
						
						Name.requestFocus();
						button1.setEnabled(true);
						Member_Load();
						
						
						
						
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
        button2.setForeground(new Color(255, 255, 255));
        button2.setBackground(new Color(51, 102, 153));
        button2.setFont(new Font("Tahoma", Font.BOLD, 18));
        button2.setBounds(257, 420, 120, 40);
        contentPane.add(button2);
        
        button3 = new JButton("DELETE");
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	

        		DefaultTableModel d1 = (DefaultTableModel)memberTable1.getModel();
        		int selectIndex = memberTable1.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        		
        		
        		try {
					pst= con.prepareStatement("delete from member where id=?");
					pst.setInt(1, id);
					
					
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Member deleted");
						Name.setText("");
						textArea.setText("");
						Phone.setText("");
						
						Name.requestFocus();
						Member_Load();
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
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		 DefaultTableModel model = (DefaultTableModel) memberTable1.getModel();
        	        int selectedRowIndex = memberTable1.getSelectedRow();
        	        
        	        // Populate text fields and labels with data from the selected row
        	        Name.setText(model.getValueAt(selectedRowIndex, 1).toString()); // Assuming the name is in the second column
        	        textArea.setText(model.getValueAt(selectedRowIndex, 2).toString()); // Assuming the address is in the third column
        	        Phone.setText(model.getValueAt(selectedRowIndex, 3).toString()); // Assuming the phone is in the fourth column
        	    
        		
        	}
        });
        scrollPane_1.setBounds(558, 110, 405, 384);
        contentPane.add(scrollPane_1);
        
        memberTable1 = new JTable();
        scrollPane_1.setViewportView(memberTable1);
        memberTable1.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Member Name", "Address", "Phone No"
        	}
        	
     
        ));
        
        
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		

        		DefaultTableModel d1 = (DefaultTableModel)memberTable1.getModel();
        		int selectIndex = memberTable1.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        		
        		
        		Name.setText(d1.getValueAt(selectIndex, 1).toString());
        		textArea.setText(d1.getValueAt(selectIndex, 2).toString());
        		Phone.setText(d1.getValueAt(selectIndex, 2).toString());
        		
        		
        		button1.setEnabled(false);
    		
        		
        		
        		
        		
        		
        		
        		
        	}
        });
        
        
        
        
        
        
             
            Connect();
            Member_Load();
	}
            
    		
    
    	
    	Connection con;
    	PreparedStatement pst;
    	ResultSet rs;
    	private JScrollPane scrollPane_1;
    
    	
    	

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
    	
    	public void Member_Load()
    	{
    		int c;
    		
    		try {
    			pst = con.prepareStatement("select * from member");
    			rs = pst.executeQuery();
    			
    			ResultSetMetaData rsd = rs.getMetaData();	
    			c= rsd.getColumnCount();
    			
    		    DefaultTableModel d = (DefaultTableModel)memberTable1.getModel();
    		    
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
