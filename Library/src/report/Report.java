package report;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Report extends JFrame {

	private JPanel contentPane;
	private JButton button1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	public Report() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 100, 400, 400);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(102, 153, 102));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        button1 = new JButton("REPORT");
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/category","root","");
					JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\HP\\Desktop\\eclipse1\\Library\\src\\report\\Blank_A4.jrxml");
					String query= "select * from category";	
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(query);
					jdesign.setQuery(updateQuery);
					
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jprint= JasperFillManager.fillReport(jreport, null,con);
					
				    JasperViewer.viewReport(jprint);			
					
					
					
					
				} catch (ClassNotFoundException e1) {
				
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        button1.setForeground(new Color(255, 255, 255));
        button1.setBackground(new Color(0, 102, 51));
        button1.setFont(new Font("Tahoma", Font.BOLD, 18));
        button1.setBounds(128, 138, 140, 60);
        contentPane.add(button1);
		
		
	}
	Connection con;
	PreparedStatement pst;

}
