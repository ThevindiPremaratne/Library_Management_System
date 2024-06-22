package library;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import library.lendbook.BookItem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JScrollPane;

public class returnbook extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private JTextField txtname;
	private JTextField txtbook;
	private JTextField txtdate;
	private JTextField Elapse;
	private JTextField Fine;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					returnbook frame = new returnbook();
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
	public returnbook() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 20, 1050, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lbllibrary = new JLabel("RETURN BOOK");
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
        		
        		String id = ID.getText();
        		try {
					pst = con.prepareStatement("select m.name,b.bname,l.returndate,DATEDIFF(now(),l.returndate)as elap from lendbook l JOIN member m ON l.memberid=m.id JOIN book b ON l.bookid=b.id and l.memberid=?");
					pst.setString(1, id);
					rs=pst.executeQuery();
					
					if(rs.next() == false)
					{
						JOptionPane.showMessageDialog(ID, "Member ID not found");
					}
					else
					{
						String mname=rs.getString("m.name");
						String bname = rs.getString("b.bname");
						
						txtname.setText(mname.trim());
						txtbook.setText(bname.trim());
						
						String date = rs.getString("l.returndate");
						txtdate.setText(date);
						
						String elp = rs.getString("elap");
						
						int elapsed= Integer.parseInt(elp);
						
						if(elapsed>0)
						{
							Elapse.setText(elp);
							int fine = elapsed * 100;
							Fine.setText(String.valueOf(fine));
						}
						else {
							
							Elapse.setText("");
							Fine.setText("");
						}
						
						
						
						
					}
        		
        		
        		
        		
        		} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
        
        JLabel txtname = new JLabel("New label");
        txtname.setBounds(257, 183, 250, 32);
        contentPane.add(txtname);
        
        JLabel lblBook = new JLabel("Book");
        lblBook.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblBook.setBounds(55, 236, 163, 43);
        contentPane.add(lblBook);
        
      
        JLabel txtbook = new JLabel("New label");
        txtbook.setBounds(257, 236, 250, 32);
        contentPane.add(txtbook);
        
        JLabel lblDate = new JLabel("Return Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDate.setBounds(55, 296, 163, 43);
        contentPane.add(lblDate);
        
        JLabel lblElapse = new JLabel("Elapsed");
        lblElapse.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblElapse.setBounds(55, 362, 163, 43);
        contentPane.add(lblElapse);
        
        Elapse = new JTextField();
        Elapse.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Elapse.setBounds(257, 366, 258, 30);
        contentPane.add(Elapse);
        Elapse.setColumns(10);
        
        JLabel lblFine = new JLabel("Fine");
        lblFine.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblFine.setBounds(55, 400, 180, 55);
        contentPane.add(lblFine);
        
        Fine = new JTextField();
        Fine.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Fine.setBounds(257, 410, 258, 30);
        contentPane.add(Fine);
        Fine.setColumns(10);
        
        button1 = new JButton("ADD");
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		String mid= ID.getText();
        		String membername= txtname.getText();
        		String bookname=txtbook.getText();
        		String returndate= txtdate.getText();
        		String elpdays=Elapse.getText();
        		String fine=Fine.getText();
        		
        		
        		

        		try {
					pst= con.prepareStatement("insert into returnbook (mid, mname, bname, returndate, elp, fine) values (?, ?, ?, ?, ?, ?)");
					pst.setString(1, mid);
					pst.setString(2, membername);
					pst.setString(3, bookname);
					pst.setString(4, returndate);
					pst.setString(5, elpdays);
					pst.setString(6, fine);
					
					
					int k=pst.executeUpdate();
					
					pst=con.prepareStatement("delete from lendbook where memberid=?");
					pst.setString(1, mid);
					pst.executeQuery();
					
				
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(button1, "Return Book Successful");
						
						ID.setText("");
						txtname.setText("");
						txtbook.setText("");
						txtdate.setText("");
						Elapse.setText("");
						Fine.setText("");
						
						ID.requestFocus();
						
						
						
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
        button1.setBounds(87, 465, 120, 40);
        contentPane.add(button1);
        
        
        button2 = new JButton("UPDATE");
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String mid = ID.getText();
        	        String membername = txtname.getText();
        	        String bookname = txtbook.getText();
        	        String returndate = txtdate.getText();
        	        String elpdays = Elapse.getText();
        	        String fine = Fine.getText();

        	        try {
        	            pst = con.prepareStatement("update returnbook set mname=?, bname=?, returndate=?, elp=?, fine=? where mid=?");
        	            pst.setString(1, membername);
        	            pst.setString(2, bookname);
        	            pst.setString(3, returndate);
        	            pst.setString(4, elpdays);
        	            pst.setString(5, fine);
        	            pst.setString(6, mid);

        	            int k = pst.executeUpdate();

        	            if (k == 1) {
        	                JOptionPane.showMessageDialog(button2, "Record Updated Successfully");

        	                ID.setText("");
        	                txtname.setText("");
        	                txtbook.setText("");
        	                txtdate.setText("");
        	                Elapse.setText("");
        	                Fine.setText("");

        	                ID.requestFocus();

        	                // Refresh the table data
        	                DefaultTableModel model = (DefaultTableModel) table.getModel();
        	                model.setRowCount(0); // Clear existing table data
        	                Returnbook_Load(); // Load updated data into the table
        	            } else {
        	                JOptionPane.showMessageDialog(button2, "Error Updating Record");
        	            }
        	        } catch (SQLException e1) {
        	            e1.printStackTrace();
        	        }
        		
        	}
        });
        button2.setForeground(new Color(255, 255, 255));
        button2.setBackground(new Color(51, 102, 153));
        button2.setFont(new Font("Tahoma", Font.BOLD, 18));
        button2.setBounds(257, 465, 120, 40);
        contentPane.add(button2);
        
        button3 = new JButton("DELETE");
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	     String mid = ID.getText();

        	        try {
        	            pst = con.prepareStatement("delete from returnbook where mid=?");
        	            pst.setString(1, mid);

        	            int k = pst.executeUpdate();

        	            if (k == 1) {
        	                JOptionPane.showMessageDialog(button3, "Record Deleted Successfully");

        	                ID.setText("");
        	                txtname.setText("");
        	                txtbook.setText("");
        	                txtdate.setText("");
        	                Elapse.setText("");
        	                Fine.setText("");

        	                ID.requestFocus();

        	                // Refresh the table data
        	                DefaultTableModel model = (DefaultTableModel) table.getModel();
        	                model.setRowCount(0); // Clear existing table data
        	                Returnbook_Load(); // Load updated data into the table
        	            } else {
        	                JOptionPane.showMessageDialog(button3, "Error Deleting Record");
        	            }
        	        } catch (SQLException e1) {
        	            e1.printStackTrace();
        	        }
        		
        		
        		
        	}
        });
        button3.setForeground(new Color(255, 255, 255));
        button3.setBackground(new Color(51, 102, 153));
        button3.setFont(new Font("Tahoma", Font.BOLD, 18));
        button3.setBounds(87, 513, 120, 40);
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
        button4.setBounds(257, 513, 120, 40);
        contentPane.add(button4);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(580, 108, 404, 413);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "Member ID", "MemberName", "Book", "ReturnBook", "Days Elapse", "Fine"
        	}
       
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(82);
        
        
        JLabel txtdate = new JLabel("New label");
        txtdate.setBounds(261, 315, 225, 13);
        contentPane.add(txtdate);
        
     
        Connect();
        Returnbook_Load();
        
        
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
	
	
	
	
	
	
	

	public void Returnbook_Load()
	{
		
		
		try {

			int c;
			
			pst = con.prepareStatement("select * from returnbook");
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
			    		v2.add(rs.getString("mid"));
			    		v2.add(rs.getString("mname"));
			    		v2.add(rs.getString("bname"));
			    		v2.add(rs.getString("returndate"));
			    		v2.add(rs.getString("elp"));
			    		v2.add(rs.getString("fine"));
			    		
			    		
			    	}
			    	d.addRow(v2);
			    	
			    	
			    
			    	
			    	
			    }
				

				
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
	
        
	
        
       
	}
}


