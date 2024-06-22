package library;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button7_1;
	private JButton button8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 50, 590, 650);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lbllibrary = new JLabel("Library Management");
        lbllibrary.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lbllibrary.setBounds(121, 10, 650, 50);
        contentPane.add(lbllibrary);
        
        button1 = new JButton("Category");
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Category c= new Category();
        		c.setVisible(true);
        		
        		
        		
        		
        	}
        });
        
        
        
        
        
        button1.setForeground(new Color(255, 255, 255));
        button1.setBackground(new Color(51, 102, 153));
        button1.setFont(new Font("Tahoma", Font.BOLD, 18));
        button1.setBounds(220, 93, 120, 40);
        contentPane.add(button1);
        
        button2 = new JButton("Author");
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Author a = new Author();
        		a.setVisible(true);
        		
        		
        		
        	}
        });
        button2.setForeground(new Color(255, 255, 255));
        button2.setBackground(new Color(51, 102, 153));
        button2.setFont(new Font("Tahoma", Font.BOLD, 18));
        button2.setBounds(220, 159, 120, 40);
        contentPane.add(button2);
        
        button3 = new JButton("Publisher");
        button3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        	Publisher p= new Publisher();
        	p.setVisible(true);
        	
        	}
        });
        button3.setForeground(new Color(255, 255, 255));
        button3.setBackground(new Color(51, 102, 153));
        button3.setFont(new Font("Tahoma", Font.BOLD, 18));
        button3.setBounds(216, 228, 124, 40);
        contentPane.add(button3);
        
        button4 = new JButton("Book");
        button4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Book b = new Book();
        		b.setVisible(true);
        		
        		
        		
        	}
        });
        button4.setForeground(new Color(255, 255, 255));
        button4.setBackground(new Color(51, 102, 153));
        button4.setFont(new Font("Tahoma", Font.BOLD, 18));
        button4.setBounds(220, 294, 120, 40);
        contentPane.add(button4);
        
        button5 = new JButton("Member");
        button5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Member m= new Member();
        		m.setVisible(true);
        		
        	}
        });
        button5.setForeground(new Color(255, 255, 255));
        button5.setBackground(new Color(51, 102, 153));
        button5.setFont(new Font("Tahoma", Font.BOLD, 18));
        button5.setBounds(220, 359, 120, 40);
        contentPane.add(button5);
        
        button6 = new JButton("Issue Book");
        button6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		lendbook l = new lendbook();
        		l.setVisible(true);
        	}
        });
        button6.setForeground(new Color(255, 255, 255));
        button6.setBackground(new Color(51, 102, 153));
        button6.setFont(new Font("Tahoma", Font.BOLD, 18));
        button6.setBounds(209, 424, 147, 40);
        contentPane.add(button6);
        
        button7 = new JButton("Return book");
        button7.setForeground(new Color(255, 255, 255));
        button7.setBackground(new Color(0, 102, 51));
        button7.setFont(new Font("Tahoma", Font.BOLD, 18));
        button7.setBounds(172, 250, 120, 40);
        contentPane.add(button1);
        
        button7_1 = new JButton("Return book");
        button7_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		returnbook r = new returnbook();
        		r.setVisible(true);
        		
        	}
        });
        button7_1.setForeground(new Color(255, 255, 255));
        button7_1.setBackground(new Color(51, 102, 153));
        button7_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        button7_1.setBounds(199, 488, 157, 40);
        contentPane.add(button7_1);
        
        button8 = new JButton("Logout");
        button8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 System.exit(0); // Exit the application
        	}
        });
        button8.setForeground(new Color(255, 255, 255));
        button8.setBackground(new Color(51, 102, 153));
        button8.setFont(new Font("Tahoma", Font.BOLD, 18));
        button8.setBounds(220, 550, 120, 40);
        contentPane.add(button8);
	}

}
