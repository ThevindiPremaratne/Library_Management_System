package library;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	   private JTextField Username;
	   private JTextField Password;
	   private JButton btnNewButton;
	   private JButton btnNewButton_1;
	   private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 50, 600, 500);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 153, 153));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JLabel lblNewUserRegister = new JLabel("LOGIN");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblNewUserRegister.setBounds(220, 52, 650, 50);
        contentPane.add(lblNewUserRegister);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblUsername.setBounds(58, 152, 110, 43);
        contentPane.add(lblUsername);
        
        Username = new JTextField();
        Username.setFont(new Font("Tahoma", Font.PLAIN, 25));
        Username.setBounds(200, 160, 250, 30);
        contentPane.add(Username);
        Username.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPassword.setBounds(58, 243, 110, 29);
        contentPane.add(lblPassword);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        passwordField_1.setBounds(200, 243, 250, 29);
        contentPane.add(passwordField_1);
        
        
        
        btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String username=Username.getText();
        		String password=passwordField_1.getText();
        		
        		if(username.equals("Thevindi") && password.equals("123")) {
        			
        			Main m = new Main();
        		
        			m.setVisible(true);
        				
        		}
        		else {
        			
        			JOptionPane.showMessageDialog(btnNewButton,"Username and Password does not match");
        			Username.setText("");
        			Password.setText("");
        			Username.requestFocus();
        		}
        		
        	}
        });
        
        
        
        
        
        
        
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(51, 102, 153));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(172, 338, 120, 40);
        contentPane.add(btnNewButton);
        
        btnNewButton_1 = new JButton("Cancel");
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(51, 102, 153));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton_1.setBounds(330, 338, 120, 40);
        contentPane.add(btnNewButton_1);
        
        
        
        
	}
}
