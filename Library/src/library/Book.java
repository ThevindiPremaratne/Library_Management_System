package library;

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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Book extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Content;
	private JTextField Page;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JTable table;
	private JComboBox<CategoryItem> comboBox;
    private JComboBox<AuthorItem> comboBox1;
    private JComboBox<PublisherItem> comboBox2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book frame = new Book();
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
	public Book() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 20, 1050, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lbllibrary = new JLabel("BOOK");
        lbllibrary.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbllibrary.setBounds(275, 27, 650, 50);
        contentPane.add(lbllibrary);
        
        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblName.setBounds(55, 117, 163, 43);
        contentPane.add(lblName);
        
        Name = new JTextField();
        Name.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Name.setBounds(257, 121, 250, 30);
        contentPane.add(Name);
        Name.setColumns(10);
        

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCategory.setBounds(55, 170, 163, 43);
        contentPane.add(lblCategory);
        
        
        comboBox = new JComboBox<>();
        comboBox.setBounds(257, 170, 250, 30);
        contentPane.add(comboBox);

     
        
        JLabel lblAuthor = new JLabel("Author");
        lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblAuthor.setBounds(55, 223, 163, 43);
        contentPane.add(lblAuthor);
        
      

        comboBox1 = new JComboBox<>();
        comboBox1.setBounds(257, 229, 250, 30);
        contentPane.add(comboBox1);


        
        JLabel lblPublisher = new JLabel("Publisher");
        lblPublisher.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPublisher.setBounds(55, 290, 163, 43);
        contentPane.add(lblPublisher);
        
        JLabel lblContents = new JLabel("Contents");
        lblContents.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblContents.setBounds(55, 343, 163, 43);
        contentPane.add(lblContents);
        
       
        Content = new JTextField();
        Content.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Content.setBounds(257, 347, 250, 30);
        contentPane.add(Content);
        Content.setColumns(10);
        
        JLabel lblPages = new JLabel("Pages");
        lblPages.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPages.setBounds(55, 396, 163, 43);
        contentPane.add(lblPages);
        

        Page = new JTextField();
        Page.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Page.setBounds(257, 400, 250, 30);
        contentPane.add(Page);
        Page.setColumns(10);
       

        comboBox2 = new JComboBox<>();
        comboBox2.setBounds(257, 296, 250, 30);
        contentPane.add(comboBox2);
        
        button1 = new JButton("ADD");
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		
        		        String bname = Name.getText();
        		        CategoryItem selectedCategory = (CategoryItem) comboBox.getSelectedItem();
        		        AuthorItem selectedAuthor = (AuthorItem) comboBox1.getSelectedItem();
        		        PublisherItem selectedPublisher = (PublisherItem) comboBox2.getSelectedItem();
        		        String contents = Content.getText();
        		        String pages = Page.getText();

        		        try {
        		            pst = con.prepareStatement("insert into book (bname, category, author, publisher, contents, pages) values(?, ?, ?, ?, ?, ?)");
        		            pst.setString(1, bname);
        		            pst.setInt(2, selectedCategory.id);
        		            pst.setInt(3, selectedAuthor.id);
        		            pst.setInt(4, selectedPublisher.id);
        		            pst.setString(5, contents);
        		            pst.setString(6, pages);

        		            int k = pst.executeUpdate();

        		            if (k == 1) {
        		                JOptionPane.showMessageDialog(button1, "Book created");
        		                Name.setText("");
        		                comboBox.setSelectedIndex(0); // Set to the default item or a specific index based on your logic
        		                comboBox1.setSelectedIndex(0); // Set to the default item or a specific index based on your logic
        		                comboBox2.setSelectedIndex(0); // Set to the default item or a specific index based on your logic
        		                Content.setText("");
        		                Page.setText("");
        		                Book_Load(); // Refresh the table after adding a new book
        		            } else {
        		                JOptionPane.showMessageDialog(button1, "Error");
        		            }
        		        } catch (SQLException e1) {
        		            e1.printStackTrace();
        		        }
        		
        		
        	}
        });
        button1.setForeground(new Color(255, 255, 255));
        button1.setBackground(new Color(51, 102, 153));
        button1.setFont(new Font("Tahoma", Font.BOLD, 18));
        button1.setBounds(87, 463, 120, 40);
        contentPane.add(button1);
        
        
        button2 = new JButton("UPDATE");
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		   // Retrieve data from the input fields and combo boxes
                String bname = Name.getText();
                CategoryItem citem = (CategoryItem) comboBox.getSelectedItem();
                AuthorItem aitem = (AuthorItem) comboBox1.getSelectedItem();
                PublisherItem pitem = (PublisherItem) comboBox2.getSelectedItem();
                String contents = Content.getText();
                String pages = Page.getText();

                // Validate input (you can add more validation logic as needed)
                if (bname.isEmpty() || contents.isEmpty() || pages.isEmpty()) {
                    JOptionPane.showMessageDialog(button2, "Please fill out all fields.");
                    return;
                }

                // Get the selected row from the table
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(button2, "Please select a book to update.");
                    return;
                }

                // Get the book ID from the selected row in the table
                int bookID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                try {
                    // Prepare the SQL query and execute it to update the selected book
                    pst = con.prepareStatement("UPDATE book SET bname=?, category=?, author=?, publisher=?, contents=?, pages=? WHERE id=?");
                    pst.setString(1, bname);
                    pst.setInt(2, citem.id);
                    pst.setInt(3, aitem.id);
                    pst.setInt(4, pitem.id);
                    pst.setString(5, contents);
                    pst.setString(6, pages);
                    pst.setInt(7, bookID);

                    int k = pst.executeUpdate();

                    if (k == 1) {
                        JOptionPane.showMessageDialog(button2, "Book updated successfully.");
                        // Clear input fields after successful update
                        Name.setText("");
                        comboBox.setSelectedIndex(0); // Assuming the first item in the combo box is a default value
                        comboBox1.setSelectedIndex(0); // Assuming the first item in the combo box is a default value
                        comboBox2.setSelectedIndex(0); // Assuming the first item in the combo box is a default value
                        Content.setText("");
                        Page.setText("");
                        // Refresh the table to display the updated book
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0); // Clear existing rows
                        Book_Load(); // Reload books from the database
                    } else {
                        JOptionPane.showMessageDialog(button2, "Error updating book.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(button2, "Error updating book: " + ex.getMessage());
                }
        		
        		
        		
        	}
        		
        		
        		
        		
        		
        	
        	
        });
        button2.setForeground(new Color(255, 255, 255));
        button2.setBackground(new Color(51, 102, 153));
        button2.setFont(new Font("Tahoma", Font.BOLD, 18));
        button2.setBounds(257, 463, 120, 40);
        contentPane.add(button2);
        
        button3 = new JButton("DELETE");
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		 int selectedRow = table.getSelectedRow();
        	        if (selectedRow == -1) {
        	            JOptionPane.showMessageDialog(button3, "Please select a book to delete.");
        	            return;
        	        }
        	        int bookID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        	        try {
        	            pst = con.prepareStatement("DELETE FROM book WHERE id = ?");
        	            pst.setInt(1, bookID);
        	            int result = pst.executeUpdate();

        	            if (result == 1) {
        	                JOptionPane.showMessageDialog(button3, "Book deleted successfully.");
        	                // Remove the deleted row from the table display
        	                DefaultTableModel model = (DefaultTableModel) table.getModel();
        	                model.removeRow(selectedRow);
        	            } else {
        	                JOptionPane.showMessageDialog(button3, "Error deleting book.");
        	            }
        	        } catch (SQLException ex) {
        	            ex.printStackTrace();
        	            JOptionPane.showMessageDialog(button3, "Error deleting book: " + ex.getMessage());
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
        scrollPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		 DefaultTableModel d1 = (DefaultTableModel) table.getModel();
        	        int selectIndex = table.getSelectedRow();

        	        // Check if a row is selected
        	        if (selectIndex != -1) {
        	            // Get the selected row data
        	            String id = d1.getValueAt(selectIndex, 0).toString();
        	            String bookName = d1.getValueAt(selectIndex, 1).toString();
        	            String category = d1.getValueAt(selectIndex, 2).toString();
        	            String author = d1.getValueAt(selectIndex, 3).toString();
        	            String publisher = d1.getValueAt(selectIndex, 4).toString();
        	            String contents = d1.getValueAt(selectIndex, 5).toString();
        	            String pages = d1.getValueAt(selectIndex, 6).toString();

        	            // Populate your fields using the retrieved data
        	            Name.setText(bookName);
        	            Content.setText(contents);
        	            Page.setText(pages);

        	            // Find the CategoryItem object corresponding to the selected category name
        	            CategoryItem selectedCategory = findCategoryByName(category);
        	            if (selectedCategory != null) {
        	                comboBox.setSelectedItem(selectedCategory);
        	            }

        	            // Find the AuthorItem object corresponding to the selected author name
        	            AuthorItem selectedAuthor = findAuthorByName(author);
        	            if (selectedAuthor != null) {
        	                comboBox1.setSelectedItem(selectedAuthor);
        	            }

        	            // Find the PublisherItem object corresponding to the selected publisher name
        	            PublisherItem selectedPublisher = findPublisherByName(publisher);
        	            if (selectedPublisher != null) {
        	                comboBox2.setSelectedItem(selectedPublisher);
        	            }
        	        }
        	    }

        	    // Helper method to find CategoryItem by name
        	    private CategoryItem findCategoryByName(String categoryName) {
        	        for (int i = 0; i < comboBox.getItemCount(); i++) {
        	            CategoryItem categoryItem = (CategoryItem) comboBox.getItemAt(i);
        	            if (categoryItem.toString().equals(categoryName)) {
        	                return categoryItem;
        	            }
        	        }
        	        return null; // Handle if category is not found
        	    }

        	    // Helper method to find AuthorItem by name
        	    private AuthorItem findAuthorByName(String authorName) {
        	        for (int i = 0; i < comboBox1.getItemCount(); i++) {
        	            AuthorItem authorItem = (AuthorItem) comboBox1.getItemAt(i);
        	            if (authorItem.toString().equals(authorName)) {
        	                return authorItem;
        	            }
        	        }
        	        return null; // Handle if author is not found
        	    }

        	    // Helper method to find PublisherItem by name
        	    private PublisherItem findPublisherByName(String publisherName) {
        	        for (int i = 0; i < comboBox2.getItemCount(); i++) {
        	            PublisherItem publisherItem = (PublisherItem) comboBox2.getItemAt(i);
        	            if (publisherItem.toString().equals(publisherName)) {
        	                return publisherItem;
        	            }
        	        }
        	        return null; // Handle if publisher is not found
        		
        		
        	}
        });
        scrollPane.setBounds(546, 65, 439, 458);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "BookName", "Category", "Author", "Publisher", "Contents", "Pages"
        	}
       
        ));
        
        	table.getColumnModel().getColumn(1).setPreferredWidth(82);
        
        Connect();
        Category();
        Author();
        Publisher();
        Book_Load();
     	
	}
	
	public class CategoryItem
	{
		int id;
		String name;
		
		public CategoryItem(int id,String name)
		{
			this.id=id;
			this.name=name;
			
		}
		
		public String toString()
		{
			
			return name;
		}	
	}
	
	
	public class AuthorItem
	{
		int id;
		private String name;
		
		public AuthorItem(int id,String name)
		{
			this.id=id;
			this.name=name;
			
		}
		   public int getId() {
		        return id;
		    }

		    @Override
		    public String toString() {
		        return name;
		    }
		
	
	}
	
	public class PublisherItem
	{
		int id;
		String name;
		
		public PublisherItem(int id,String name)
		{
			this.id=id;
			this.name=name;
			
		}
		
		public String toString()
		{
			
			return name;
		}	
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
	
	public void Book_Load()
	{
		int c;
		
		try {
			pst = con.prepareStatement("select b.id,b.bname,c.catname,a.name,p.name,b.contents,b.pages from book b JOIN category c On b.category=c.id  JOIN author a On b.author =a.id JOIN publisher p On b.publisher=p.id ");
			rs = pst.executeQuery();
			
			ResultSetMetaData rsd = rs.getMetaData();	
			c= rsd.getColumnCount();
			
		    DefaultTableModel d = (DefaultTableModel)table.getModel();
		    
		    while(rs.next())
		    {
		    	
		    	Vector v2= new Vector();
		    	
		    	for(int i=1; i<=c; i++)
		    		
		    	{
		    		v2.add(rs.getString("b.id"));
		    		v2.add(rs.getString("b.bname"));
		    		v2.add(rs.getString("c.catname"));
		    		v2.add(rs.getString("a.name"));
		    		v2.add(rs.getString("p.name"));
		    		v2.add(rs.getString("b.contents"));
		    		v2.add(rs.getString("b.pages"));
		    		
		    		
		    	}
		    	d.addRow(v2);
		    	
		    	
		    	
		    	
		    	
		    }
			

			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	
	
	public void Category()
	
	//
	{
		try {
			pst= con.prepareStatement("select* from category");
	        rs = pst.executeQuery();
	        comboBox.removeAllItems();
	        
	        while(rs.next())
	        {
	        	comboBox.addItem(new CategoryItem(rs.getInt(1),rs.getString(2)));
	        }
	        
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
		public void Author()
		{
			try {
				pst= con.prepareStatement("select* from author");
		        rs = pst.executeQuery();
				comboBox1.removeAllItems();
		        
		        while(rs.next())
		        {
		        	int authorId = rs.getInt(1);
		            String authorName = rs.getString(2);
		            AuthorItem authorItem = new AuthorItem(authorId, authorName);
		        	comboBox1.addItem(new AuthorItem(rs.getInt(1),rs.getString(2)));
		        }
		        
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

			public void Publisher()
			{
				try {
					pst= con.prepareStatement("select* from publisher");
			        rs = pst.executeQuery();
			        comboBox2.removeAllItems();
			        
			        while(rs.next())
			        {
			        	comboBox2.addItem(new PublisherItem(rs.getInt(1),rs.getString(2)));
			        }
			        
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			
			
			
	
        	
        	
      
        
        
        
        
        
        
        
        
     
        
        
				
		
	}
}
