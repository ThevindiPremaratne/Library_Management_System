package library;

import java.awt.Color;
import library.lendbook;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDayChooser;

import library.Book.AuthorItem;
import library.Book.CategoryItem;
import library.Book.PublisherItem;

import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class lendbook extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField ID;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JComboBox<BookItem> comboBox;
	private JTable table;
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lendbook frame = new lendbook();
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
	public lendbook() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 20, 1050, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lbllibrary = new JLabel("ISSUE BOOK");
        lbllibrary.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbllibrary.setBounds(275, 27, 650, 50);
        contentPane.add(lbllibrary);
        
        JLabel lblName = new JLabel("Member ID");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblName.setBounds(55, 119, 163, 43);
        contentPane.add(lblName);
        
        ID = new JTextField();
        ID.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent evt) {
        		
        		
				if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        		{
        			String mid = ID.getText();
        			try {
        			pst = con.prepareStatement("select * from member where id=?");
        			pst.setString(1, mid);
        			rs = pst.executeQuery();
        			
        			
        			if(rs.next() == false) {
        				
        				JOptionPane.showMessageDialog(ID,"Member ID not found");
        				
        			}
        			else {
        				
        				String membername =rs.getString("name");
        				Name.setText(membername.trim());
        			}
        			
        			
        			
        			
        			} catch (SQLException e3) {
    				
    					e3.printStackTrace();
    				}
        		
        		}
        		
				
        		
        		
        		
        	}
        });
        ID.setFont(new Font("Tahoma", Font.PLAIN, 25));
        ID.setBounds(257, 123, 250, 30);
        contentPane.add(ID);
        ID.setColumns(10);
        

        JLabel lblAddress = new JLabel("Member Name");
        lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblAddress.setBounds(55, 183, 163, 43);
        contentPane.add(lblAddress);
        
        Name = new JTextField();
        Name.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Name.setBounds(257, 187, 250, 30);
        contentPane.add(Name);
        Name.setColumns(10);
        

        JLabel lblBook = new JLabel("Book");
        lblBook.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblBook.setBounds(55, 236, 163, 43);
        contentPane.add(lblBook);
      
        
        comboBox = new JComboBox<>();
        comboBox.setBounds(257, 246, 250, 30);
        contentPane.add(comboBox);
        
        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDate.setBounds(55, 296, 163, 43);
        contentPane.add(lblDate);
        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(257, 309, 250, 30);
        contentPane.add(dateChooser);
        
        JLabel lblReturn = new JLabel("Return");
        lblReturn.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblReturn.setBounds(55, 362, 163, 43);
        contentPane.add(lblReturn);
        
        
        JDateChooser dateChooser_1 = new JDateChooser();
        dateChooser_1.setBounds(257, 376, 250, 29);
        contentPane.add(dateChooser_1);
        
        
        
  
        button1 = new JButton("ADD");
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String mid = ID.getText();
        		BookItem bitem= (BookItem) comboBox.getSelectedItem();
        		
        		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        		String issuedate = date_format.format(dateChooser.getDate());

        		SimpleDateFormat date_format1 = new SimpleDateFormat("yyyy-MM-dd");
        		String returndate = date_format.format(dateChooser_1.getDate());
        		
        		
        		
        		
        		try {
					pst= con.prepareStatement("insert into lendbook (memberid,bookid,issuedate,returndate) values(?,?,?,?)");
					pst.setString(1, mid);
					pst.setInt(2, bitem.id);
					pst.setString(3, issuedate);
					pst.setString(4, returndate);
					
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Book issued");
						
						ID.setText("");
						comboBox.setSelectedIndex(1);
						Name.setText("");
						Issuebook_Load();
						
						
						
					//	Publisher_Load();
						
						
						
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
        button1.setBounds(87, 443, 120, 40);
        contentPane.add(button1);
        
        
        button2 = new JButton("UPDATE");
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String mid = ID.getText();
        		BookItem bitem= (BookItem) comboBox.getSelectedItem();
        		
        		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        		String issuedate = date_format.format(dateChooser.getDate());

        		SimpleDateFormat date_format1 = new SimpleDateFormat("yyyy-MM-dd");
        		String returndate = date_format.format(dateChooser_1.getDate());
        		
        		
        		
        		
        		try {
					pst= con.prepareStatement("update lendbook set memberid=?, bookid=?, issuedate=?, returndate=? where id=?");
					pst.setString(1, mid);
					pst.setInt(2, bitem.id);
					pst.setString(3, issuedate);
					pst.setString(4, returndate);
					
					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Book updated");
						
						ID.setText("");
						comboBox.setSelectedIndex(1);
						Name.setText("");
						Issuebook_Load();
						
						
						
					//	Publisher_Load();
						
						
						
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
        button2.setBounds(257, 443, 120, 40);
        contentPane.add(button2);
        
        button3 = new JButton("DELETE");
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String mid = ID.getText();
                // You might want to add additional validation before performing the deletion
                
                try {
                    pst = con.prepareStatement("DELETE FROM lendbook WHERE id=?");
                    pst.setString(1, mid);
                    int k = pst.executeUpdate();
                    
                    if (k == 1) {
                        JOptionPane.showMessageDialog(button1, "Book issued deleted");
                        ID.setText("");
                        comboBox.setSelectedIndex(0); // Set the default index if needed
                        Name.setText("");
                        Issuebook_Load();
                    } else {
                        JOptionPane.showMessageDialog(button1, "Error");
                    }
                } catch (SQLException e1) {
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
        scrollPane.setBounds(592, 72, 370, 400);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Member ID", "MemberName", "Book", "Date", "ReturnDate"
        	}
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(82);
        
       
        scrollPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		

        		DefaultTableModel d1 = (DefaultTableModel)table.getModel();
        		int selectIndex = table.getSelectedRow();
        		
        		int id= Integer.parseInt(d1.getValueAt(selectIndex,0).toString());
        		
        		
        		Name.setText(d1.getValueAt(selectIndex, 1).toString());
        		
        		
        		button1.setEnabled(false);
    		
        		
        		
        		
        		
        		
        		
        		
        	}
        });
        
        
        
        
       
        
        Connect();
        Issuebook_Load();
		Book();
}
        
		

	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JScrollPane scrollPane;
	
	
	

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
	
	
	public class BookItem
	{
		int id;
	    String name;

	    public BookItem(int id, String name) {
	        this.id = id;
	        this.name = name;
	    }

	    @Override
	    public String toString() {
	        return name;
	    }
	    }
	
	
	
	public void Book() 
	{
		 
		DefaultComboBoxModel<BookItem> comboBoxModel = (DefaultComboBoxModel<BookItem>) comboBox.getModel();
		    comboBoxModel.removeAllElements();


	    try {
	        pst = con.prepareStatement("select * from book");
	        rs = pst.executeQuery();
	        comboBox.removeAllItems();

	        while (rs.next()) {
	            int id = rs.getInt(1);
	            String name = rs.getString(2);
	            comboBox.addItem(new BookItem(id, name));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		
	}
	
	
	
	
	
	
	public void Issuebook_Load()
	{
	
		
		try {

			int c;
			
			pst = con.prepareStatement("select l.id,m.name,b.bname,l.issuedate,l.returndate from lendbook l JOIN member m ON l.memberid=m.id JOIN book b ON l.bookid=b.id");
				rs = pst.executeQuery();
				
				ResultSetMetaData rsd = rs.getMetaData();	
				c= rsd.getColumnCount();
				
			    DefaultTableModel d = (DefaultTableModel)table.getModel();
			    
			    while(rs.next())
			    {
			    	
			    	Vector v2= new Vector();
			    	
			    	for(int i=1; i<=c; i++)
			    		
			    	{
			    		v2.add(rs.getString("l.id"));
			    		v2.add(rs.getString("m.name"));
			    		v2.add(rs.getString("b.bname"));
			    		v2.add(rs.getString("l.issuedate"));
			    		v2.add(rs.getString("l.returndate"));
			    		
			    		
			    	}
			    	d.addRow(v2);
			    	
			    	
			    	
			    	
			    	
			    }
			    int itemCount = comboBox.getModel().getSize();
			    if (itemCount > 0) {
			        comboBox.setSelectedIndex(0); // Set the selected index to the first item (index 0)
			    }

				

				
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
	
        
        
        
       
	}
}
